package com.hussain.csoup

import com.hussain.csoup.entity.NamedChars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.Chars
import kotlin.test.Test
import kotlin.test.assertEquals

class PlaygroundTest {

    @Test
    fun testingLookup() {
        val translator = LookupTranslator(NamedChars)
        val input = "This is a &notit test &gt; string &amp; with &quot; some &apos; characters"
        val expected = "This is a ¬it test > string & with \" some ' characters"
        val input2 = """<p class="Hello&amp;">I'm &amp; I tell</p>""".trimIndent()
        val expected2 = """<p class="Hello&">I'm & I tell</p>"""
        val actual = translator.translate(input2)
        assertEquals(expected2, actual)
    }

    @Test
    fun test() {
        val tokenizer = Tokenizer()
        tokenizer.tokenize("""<a href="hello&amp;world">""")
        assertEquals(
            listOf<Token>(
                Token.StartTag("a", attributes = mutableMapOf("href" to "hello&amp;world")),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun test1() {
        val tokenizer = Tokenizer()
        tokenizer.tokenize("""&amp;""")
        assertEquals(
            listOf<Token>(
                Token.Character('&'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun namedchar() {
        val tokenizer = Tokenizer()
        tokenizer.tokenize("I'm &notit; I tell you")
        assertEquals(
            listOf<Token>(
                Token.Character('I'),
                Token.Character('\''),
                Token.Character('m'),
                Token.Character(' '),
                Token.Character('¬'),
                Token.Character('i'),
                Token.Character('t'),
                Token.Character(';'),
                Token.Character(' '),
                Token.Character('I'),
                Token.Character(' '),
                Token.Character('t'),
                Token.Character('e'),
                Token.Character('l'),
                Token.Character('l'),
                Token.Character(' '),
                Token.Character('y'),
                Token.Character('o'),
                Token.Character('u')
            ),
            tokenizer.emittedTokens
        )
    }
}

internal class LookupTranslator(private val lookupMap: Map<String, String>) {

    /** The length of the shortest key in the lookupMap.  */
    private val shortest: Int

    /** The length of the longest key in the lookupMap.  */
    private val longest: Int

    init {
        var currentShortest = Int.MAX_VALUE
        var currentLongest = 0
        for ((key) in lookupMap) {
            val sz = key.length
            if (sz < currentShortest) {
                currentShortest = sz
            }
            if (sz > currentLongest) {
                currentLongest = sz
            }
        }
        shortest = currentShortest
        longest = currentLongest
    }

    fun translate(input: String): String {
        val stringBuilder = StringBuilder(input.length * 2)
        translate(input, stringBuilder)
        return stringBuilder.toString()
    }

    private fun translate(input: String, stringBuilder: StringBuilder) {
        var pos = 0
        val len = input.length
        while (pos < len) {
            val consumed = translate(input, pos, stringBuilder)
            if (consumed == 0) {
                // inlined implementation of Character.toChars(Character.codePointAt(input, pos))
                // avoids allocating temp char arrays and duplicate checks
                val c1 = input[pos]
                stringBuilder.append(c1)
                pos++
                if (c1.isHighSurrogate() && pos < len) {
                    val c2 = input[pos]
                    if (c2.isLowSurrogate()) {
                        stringBuilder.append(c2)
                        pos++
                    }
                }
                continue
            }
            // contract with translators is that they have to understand code points,
            // and they just took care of a surrogate pair
            for (pt in 0 until consumed) {
                pos++
            }
        }
    }

    private fun translate(input: String, offset: Int, stringBuilder: StringBuilder): Int {
        // check if translation exists for the input at position offset
        if (Chars.Ampersand.char.code == input[offset].code) {
            var max = longest
            if (offset + longest > input.length) {
                max = input.length - offset
            }
            // implement greedy algorithm by trying maximum match first
            for (i in max downTo shortest) {
                val subSeq = input.subSequence(offset, offset + i)
                val result = lookupMap[subSeq.toString()]
                if (result != null) {
                    stringBuilder.append(result)
                    return subSeq.length // return the length of the matching subSeq
                }
            }
        }
        return 0
    }
}