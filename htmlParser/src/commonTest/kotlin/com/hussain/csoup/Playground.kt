package com.hussain.csoup

import com.hussain.csoup.entity.NamedChars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.states.Action
import com.hussain.csoup.tokenization.states.AmbiguousAmpersand
import com.hussain.csoup.tokenization.states.AttributeValueDoubleQuoted
import com.hussain.csoup.tokenization.states.AttributeValueSingleQuoted
import com.hussain.csoup.tokenization.states.AttributeValueUnquoted
import com.hussain.csoup.tokenization.states.TokenizationState
import com.hussain.csoup.tokenization.states.consumeNextChar
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.util.isAsciiAlphaNumeric
import com.hussain.csoup.util.isAttributeState
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
        if (CharCodes.Ampersand.code == input[offset].code) {
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

/**
 * https://html.spec.whatwg.org/multipage/parsing.html#named-character-reference-state
 */
internal class NamedCharacterReference2(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        tempBuffer.append(codePoint.toChar())

        val matches = NamedChars.keys.filter { it.startsWith(tempBuffer.toString()) }

        return when {
            matches.isEmpty() -> {
                parse(tokenizer)
                consumeNextChar { AmbiguousAmpersand(returnState) }
            }

            matches.size == 1 && matches.contains(tempBuffer.toString()) -> {
                if (tempBuffer.last().code != CharCodes.SemiColon.code
                    && (codePoint == CharCodes.Equals.code || codePoint.isAsciiAlphaNumeric())
                ) {
                    // Do not consume the character reference and switch to the return state
                    parse(tokenizer)
                    return consumeNextChar { returnState }
                } else {
                    if (tempBuffer.last().code != CharCodes.SemiColon.code) {
                        // This is a missing-semicolon-after-character-reference parse error.
                        // We handle this by appending a semicolon to the buffer.
                        tempBuffer.append(CharCodes.SemiColon.code.toChar())
                    }
                    val referenceName =
                        NamedChars[matches.first()] ?: error("Unexpected reference name")
                    tempBuffer.clear()
                    tempBuffer.append(referenceName)
                    parse(tokenizer)
                    return consumeNextChar { returnState }
                }
            }

            else -> consumeNextChar { this }
        }
    }

    private fun parse(tokenizer: Tokenizer) {
        var start = 0
        while (start < tempBuffer.length) {
            var foundMatch = false
            for ((key, value) in NamedChars) {
                if (tempBuffer.substring(start).startsWith(key)) {
                    tokenizer.emitToken(Token.Character(value[0]))
                    start += key.length
                    foundMatch = true
                    break
                }
            }
            if (!foundMatch) {
                tokenizer.emitToken(Token.Character(tempBuffer[start]))
                start++
            }
        }
        tempBuffer.clear()
    }

    private fun flushCodePoints(tokenizer: Tokenizer) {
        when (returnState) {
            is AttributeValueDoubleQuoted -> {
                val currentToken = returnState.currentTagToken
                val attributeName = returnState.currentAttributeName
                updateAttributeValue(currentToken, attributeName)
            }

            is AttributeValueSingleQuoted -> {
                val currentToken = returnState.currentTagToken
                val attributeName = returnState.currentAttributeName
                updateAttributeValue(currentToken, attributeName)
            }

            is AttributeValueUnquoted -> {
                val currentToken = returnState.currentTagToken
                val attributeName = returnState.currentAttributeName
                updateAttributeValue(currentToken, attributeName)
            }

            else -> {
                val token = Token.Character(tempBuffer[0])
                tokenizer.emitToken(token)
            }
        }
    }

    private fun updateAttributeValue(currentToken: Token, attributeName: String) {
        when (currentToken) {
            is Token.StartTag -> {
                val currentValue = currentToken.attributes.getValue(attributeName)
                currentToken.attributes[attributeName] = currentValue + tempBuffer.toString()
            }

            else -> throw IllegalStateException("Expected a StartTag token, got $currentToken")
        }
    }
}


//////// WORKING EXCEPT WITHOUT ; in ATTRIBUTE ////////
internal class NamedCharacterReference(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        tempBuffer.append(codePoint.toChar())

        val matches = NamedChars.keys.filter { it.startsWith(tempBuffer.toString()) }

        return when {
            matches.isEmpty() -> {
                parse(tokenizer)
                consumeNextChar { AmbiguousAmpersand(returnState) }
            }

            matches.size == 1 && matches.contains(tempBuffer.toString()) -> {
                if (returnState.isAttributeState()) {
                    flushCodePoints(tokenizer)
                    return consumeNextChar { returnState }
                } else if (tempBuffer.last().code != CharCodes.SemiColon.code) {
                    flushCodePoints(tokenizer)
                    return consumeNextChar { returnState }
                } else if (codePoint == CharCodes.Equals.code || codePoint.isAsciiAlphaNumeric()) {
                    flushCodePoints(tokenizer)
                    return consumeNextChar { returnState }
                } else {
                    if (tempBuffer.last().code != CharCodes.SemiColon.code) {
                        // This is a missing-semicolon-after-character-reference parse error.
                        // We handle this by appending a semicolon to the buffer.
//                        tempBuffer.append(CharCodes.SemiColon.code.toChar())
                    }
                    val referenceName =
                        NamedChars[matches.first()] ?: error("Unexpected reference name")
                    tempBuffer.clear()
                    tempBuffer.append(referenceName)
                    flushCodePoints(tokenizer)
                    return consumeNextChar { returnState }
                }
            }

            else -> consumeNextChar { this }
        }
    }

    private fun parse(tokenizer: Tokenizer) {
        var start = 0
        while (start < tempBuffer.length) {
            var foundMatch = false
            for ((key, value) in NamedChars) {
                if (tempBuffer.substring(start).startsWith(key)) {
                    tokenizer.emitToken(Token.Character(value[0]))
                    start += key.length
                    foundMatch = true
                    break
                }
            }
            if (!foundMatch) {
                tokenizer.emitToken(Token.Character(tempBuffer[start]))
                start++
            }
        }
        tempBuffer.clear()
    }

    private fun flushCodePoints(tokenizer: Tokenizer) {
        when (returnState) {
            is AttributeValueDoubleQuoted -> {
                val currentToken = returnState.currentTagToken
                val attributeName = returnState.currentAttributeName
                updateAttributeValue(currentToken, attributeName)
            }

            is AttributeValueSingleQuoted -> {
                val currentToken = returnState.currentTagToken
                val attributeName = returnState.currentAttributeName
                updateAttributeValue(currentToken, attributeName)
            }

            is AttributeValueUnquoted -> {
                val currentToken = returnState.currentTagToken
                val attributeName = returnState.currentAttributeName
                updateAttributeValue(currentToken, attributeName)
            }

            else -> {
                val token = Token.Character(tempBuffer[0])
                tokenizer.emitToken(token)
            }
        }
    }

    private fun updateAttributeValue(currentToken: Token, attributeName: String) {
        when (currentToken) {
            is Token.StartTag -> {
                val currentValue = currentToken.attributes.getValue(attributeName)
                currentToken.attributes[attributeName] = currentValue + tempBuffer.toString()
            }

            else -> throw IllegalStateException("Expected a StartTag token, got $currentToken")
        }
    }
}