package com.hussain.csoup.tokenizer

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import kotlin.test.Test
import kotlin.test.assertEquals

class TokenizerTest1 {

    private val tokenizer = Tokenizer()

    @Test
    fun `Single Start Tag`() {
        val html = "<p>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(name = "p"),
            ),
            tokenizer.emittedTokens,
        )
    }

    @Test
    fun `Single End tag`() {
        val html = "</p>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.EndTag(name = "p"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Empty end tag`() {
        val html = "</>"
        tokenizer.tokenize(html)
        assertEquals(
            emptyList(),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Empty start tag`() {
        val html = "<>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.Character(data = '<'),
                Token.Character(data = '>'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Start Tag with attribute`() {
        val html = "<h a='b'>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "b")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Start Tag with attribute no quotes`(){
        val html = "<h a=b>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "b")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Start and End Tag`(){
        val html = "<h></h>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(name = "h"),
                Token.EndTag(name = "h"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Two unclosed start tags`() {
        val html = "<p>One<p>Two"
        tokenizer.tokenize(html)
        assertEquals(
            listOf(
                Token.StartTag(name = "p"),
                Token.Character(data = 'O'),
                Token.Character(data = 'n'),
                Token.Character(data = 'e'),
                Token.StartTag(name = "p"),
                Token.Character(data = 'T'),
                Token.Character(data = 'w'),
                Token.Character(data = 'o'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `End Tag with attribute`() {
        val html = "<h></h a='b'>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf(
                Token.StartTag(name = "h"),
                Token.EndTag(name = "h"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Multiple attributes`() {
        val html = "<h a='b' c='d'>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf(
                        "a" to "b",
                        "c" to "d",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Multiple attributes no space`() {
        val html = "<h a='b'c='d'>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf(
                        "a" to "b",
                        "c" to "d",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Repeated attributes`() {
        val html = "<h a='b' a='d'>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf(
                        "a" to "b",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Ampersand EOF`() {
        val input = "&"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = '&'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Ampersand ampersand EOF`() {
        val input = "&&"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = '&'),
                Token.Character(data = '&'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Ampersand space EOF`() {
        val input = "& "
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = '&'),
                Token.Character(data = ' '),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Unfinished entity`() {
        val input = "&f"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = '&'),
                Token.Character(data = 'f'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Ampersand and number sign`() {
        val input = "&#"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = '&'),
                Token.Character(data = '#'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Unfinished numeric entity`() {
        val input = "&#x"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = '&'),
                Token.Character(data = '#'),
                Token.Character(data = 'x'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Self closing start tag`() {
        val html = """
            <br/>
        """.trimIndent()
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "br",
                    selfClosing = true,
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity with trailing semicolon 1`() {
        val input = "I'm &not;it"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = 'I'),
                Token.Character(data = '\''),
                Token.Character(data = 'm'),
                Token.Character(data = ' '),
                Token.Character(data = '¬'),
                Token.Character(data = 'i'),
                Token.Character(data = 't'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity with trailing semicolon 2`() {
        val input = "I'm &notin;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = 'I'),
                Token.Character(data = '\''),
                Token.Character(data = 'm'),
                Token.Character(data = ' '),
                Token.Character(data = '∉'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity without trailing semicolon 1`() {
        val input = "I'm &notit"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = 'I'),
                Token.Character(data = '\''),
                Token.Character(data = 'm'),
                Token.Character(data = ' '),
                Token.Character(data = '¬'),
                Token.Character(data = 'i'),
                Token.Character(data = 't'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity without trailing semicolon 2`() {
        val input = "I'm &notin"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = 'I'),
                Token.Character(data = '\''),
                Token.Character(data = 'm'),
                Token.Character(data = ' '),
                Token.Character(data = '∉'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Partial entity match at end of file`() {
        val input = "I'm &no"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = 'I'),
                Token.Character(data = '\''),
                Token.Character(data = 'm'),
                Token.Character(data = ' '),
                Token.Character(data = '&'),
                Token.Character(data = 'n'),
                Token.Character(data = 'o'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Non-ASCII character reference name`() {
        val input = "&\u00AC;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = '&'),
                Token.Character(data = '¬'),
                Token.Character(data = ';'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `ASCII decimal entity`() {
        val input = "&#0036;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = '$'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `ASCII hexadecimal entity`() {
        val input = "&#x3f;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = '?'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Hexadecimal entity in attribute`() {
        val html = "<h a='&#x3f;'></h>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf(
                        "a" to "?",
                    )
                ),
                Token.EndTag(name = "h"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity in attribute without semicolon ending in x`() {
        val html = "<h a='&notx'>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&notx")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity in attribute without semicolon ending in 1`() {
        val html = "<h a='&not1'>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&not1")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity in attribute without semicolon ending in i`() {
        val html = "<h a='&noti'>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&noti")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity in attribute without semicolon`() {
        val html = """
            <h a='&COPY'>
        """.trimIndent()
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "©")
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Unquoted attribute ending in ampersand`() {
        val html = "<s o=& t>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "s",
                    attributes = mutableMapOf(
                        "o" to "&",
                        "t" to "",
                    )
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Unquoted attribute at end of tag with final character and with tag followed by characters`() {
        val html = "<a a=a&>foo"
        tokenizer.tokenize(html)
        assertEquals(
            listOf(
                Token.StartTag(
                    name = "a",
                    attributes = mutableMapOf("a" to "a&")
                ),
                Token.Character('f'),
                Token.Character('o'),
                Token.Character('o'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Open angled bracket in unquoted attribute value state`() {
        val html = "<a a=f<>"
        tokenizer.tokenize(html)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "a",
                    attributes = mutableMapOf(
                        "a" to "f<"
                    )
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity '∉' in string`() {
        val html = """
            <p>I'm &notin; I tell you</p>
        """.trimIndent()
        tokenizer.tokenize(html)
        assertEquals(
            listOf(
                Token.StartTag(name = "p"),
                Token.Character(data = 'I'),
                Token.Character(data = '\''),
                Token.Character(data = 'm'),
                Token.Character(data = ' '),
                Token.Character(data = '∉'),
                Token.Character(data = ' '),
                Token.Character(data = 'I'),
                Token.Character(data = ' '),
                Token.Character(data = 't'),
                Token.Character(data = 'e'),
                Token.Character(data = 'l'),
                Token.Character(data = 'l'),
                Token.Character(data = ' '),
                Token.Character(data = 'y'),
                Token.Character(data = 'o'),
                Token.Character(data = 'u'),
                Token.EndTag(name = "p")
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity ¬it in string`() {
        val html1 = """
            <p>I'm &notit; I tell you</p>
        """.trimIndent()
        tokenizer.tokenize(html1)
        assertEquals(
            listOf(
                Token.StartTag(name = "p"),
                Token.Character(data = 'I'),
                Token.Character(data = '\''),
                Token.Character(data = 'm'),
                Token.Character(data = ' '),
                Token.Character(data = '¬'),
                Token.Character(data = 'i'),
                Token.Character(data = 't'),
                Token.Character(data = ';'),
                Token.Character(data = ' '),
                Token.Character(data = 'I'),
                Token.Character(data = ' '),
                Token.Character(data = 't'),
                Token.Character(data = 'e'),
                Token.Character(data = 'l'),
                Token.Character(data = 'l'),
                Token.Character(data = ' '),
                Token.Character(data = 'y'),
                Token.Character(data = 'o'),
                Token.Character(data = 'u'),
                Token.EndTag(name = "p")
            ),
            tokenizer.emittedTokens
        )
    }
}