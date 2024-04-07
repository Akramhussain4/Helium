package com.hussain.csoup.tokenizer

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import kotlin.test.Test
import kotlin.test.assertEquals

class TokenizerTest2 {

    private val tokenizer = Tokenizer()

    @Test
    fun `Numeric entity representing the NUL character`() {
        tokenizer.tokenize("&#0000;")
        assertEquals(
            listOf<Token>(
                Token.Character('\u0000'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Hexadecimal entity representing the NUL character`() {
        tokenizer.tokenize("&#x0000;")
        assertEquals(
            listOf<Token>(
                Token.Character('\uFFFD'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Numeric entity representing a codepoint after U+10FFFF`() {
        tokenizer.tokenize("&#2225222;")
        assertEquals(
            listOf<Token>(
                Token.Character('\uFFFD'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Hexadecimal entity representing a codepoint after U+10FFFF`() {
        tokenizer.tokenize("&#x1010FFFF;")
        assertEquals(
            listOf<Token>(
                Token.Character('\uFFFD'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Hexadecimal entity pair representing a surrogate pair`() {
        tokenizer.tokenize("&#xD869;&#xDED6;")
        assertEquals(
            listOf<Token>(
                Token.Character('\uFFFD'),
                Token.Character('\uFFFD'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Hexadecimal entity with mixed uppercase and lowercase`() {
        tokenizer.tokenize("&#xaBcD;")
        assertEquals(
            listOf<Token>(
                Token.Character('\u01A9'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity without a name`() {
        tokenizer.tokenize("&;")
        assertEquals(
            listOf<Token>(
                Token.Character('&'),
                Token.Character(';'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Unescaped ampersand in attribute value`() {
        tokenizer.tokenize("<h a='&'>")
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&")
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `StartTag containing lt`() {
        tokenizer.tokenize("<a<b>")
        assertEquals(
            listOf<Token>(
                Token.StartTag(name = "a<b"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Non-void element containing trailing slash`() {
        tokenizer.tokenize("<h/>")
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    selfClosing = true
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Void element with permitted slash`() {
        tokenizer.tokenize("<br/>")
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "br",
                    selfClosing = true
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Void element with permitted slash with attribute`() {
        tokenizer.tokenize("<br foo='bar'/>")
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "br",
                    selfClosing = true,
                    attributes = mutableMapOf("foo" to "bar")
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `StartTag containing slash`() {
        tokenizer.tokenize("<h/a='b'>")
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "b")
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Double-quoted attribute value`() {
        tokenizer.tokenize("""<h a=\"b\">""".trimIndent())
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "b")
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Unescaped slash`() {
        tokenizer.tokenize("</")
        assertEquals(
            listOf<Token>(
                Token.Character('<'),
                Token.Character('/'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Illegal end tag name`() {
        tokenizer.tokenize("</1>")
        assertEquals(
            listOf<Token>(
                Token.Comment("1"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Simili processing instruction`() {
        tokenizer.tokenize("<?namespace>")
        assertEquals(
            listOf<Token>(
                Token.Comment("?namespace"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `A bogus comment stops at gt even if preceded by two dashes`() {
        tokenizer.tokenize("<?foo-->")
        assertEquals(
            listOf<Token>(
                Token.Comment("?foo--"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Unescaped lt`() {
        tokenizer.tokenize("foo < bar")
        assertEquals(
            listOf<Token>(
                Token.Character('f'),
                Token.Character('o'),
                Token.Character('o'),
                Token.Character(' '),
                Token.Character('<'),
                Token.Character(' '),
                Token.Character('b'),
                Token.Character('a'),
                Token.Character('r'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Null Byte Replacement`() {
        tokenizer.tokenize("\u0000")
        assertEquals(
            listOf<Token>(
                Token.Character('\u0000'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Comment with dash`() {
        tokenizer.tokenize("<!---x")
        assertEquals(
            listOf<Token>(
                Token.Comment("-x"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity + newline`() {
        tokenizer.tokenize("\nx\n&gt;\n")
        assertEquals(
            listOf<Token>(
                Token.Character('\n'),
                Token.Character('x'),
                Token.Character('\n'),
                Token.Character('>'),
                Token.Character('\n'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Start tag with no attributes but space before the greater-than sign`() {
        tokenizer.tokenize("<h >")
        assertEquals(
            listOf<Token>(
                Token.StartTag(name = "h"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Empty attribute followed by uppercase attribute`() {
        tokenizer.tokenize("<h a B=''>")
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf(
                        "a" to "",
                        "b" to ""
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Double-quote after attribute name`() {
        tokenizer.tokenize("""<h a \">""")
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf(
                        "a" to "",
                        "\\" to "",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Single-quote after attribute name`() {
        tokenizer.tokenize("<h a '>")
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf(
                        "a" to "",
                        "'" to "",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Empty end tag with following characters`() {
        tokenizer.tokenize("a</>bc")
        assertEquals(
            listOf<Token>(
                Token.Character('a'),
                Token.Character('b'),
                Token.Character('c'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Empty end tag with following tag`(){
        tokenizer.tokenize("a</><b>c")
        assertEquals(
            listOf(
                Token.Character('a'),
                Token.StartTag(name = "b"),
                Token.Character('c'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Empty end tag with following comment`(){
        tokenizer.tokenize("a</><!--b-->c")
        assertEquals(
            listOf(
                Token.Character('a'),
                Token.Comment("b"),
                Token.Character('c'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Empty end tag with following end tag`(){
        tokenizer.tokenize("a</></b>c")
        assertEquals(
            listOf(
                Token.Character('a'),
                Token.EndTag("b"),
                Token.Character('c'),
            ),
            tokenizer.emittedTokens
        )
    }
}