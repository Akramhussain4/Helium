package com.hussain.csoup

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import kotlin.test.Test
import kotlin.test.assertEquals

class TokenizerTest {

    @Test
    fun `test open close tags and chars`() {
        val tokenizer = Tokenizer()
        val html = """
                    <p>hello</p>
        """.trimIndent()
        tokenizer.tokenize(html)
        assertEquals(
            listOf(
                Token.StartTag(name = "p"),
                Token.Character(data = 'h'),
                Token.Character(data = 'e'),
                Token.Character(data = 'l'),
                Token.Character(data = 'l'),
                Token.Character(data = 'o'),
                Token.EndTag(name = "p")
            ),
            tokenizer.emittedTokens,
        )
    }

    @Test
    fun `test open tag state`() {
        val tokenizer = Tokenizer()
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
    fun `test close tag state`() {
        val tokenizer = Tokenizer()
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
    fun `test attributes`() {
        val tokenizer = Tokenizer()
        val html = """
            <p class="test">a</p>
        """.trimIndent()
        tokenizer.tokenize(html)
        assertEquals(
            listOf(
                Token.StartTag(
                    name = "p",
                    attributes = mutableMapOf(
                        "class" to "test",
                    )
                ),
                Token.Character(data = 'a'),
                Token.EndTag(name = "p")
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `self closing start tag`() {
        val tokenizer = Tokenizer()
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
    fun `test long html`() {
        val tokenizer = Tokenizer()
        val html = """
            <html><head><title>Test</title></head><body><p>Test</p><br/></body></html>
        """.trimIndent()
        tokenizer.tokenize(html)
        assertEquals(
            listOf(
                Token.StartTag(name = "html"),
                Token.StartTag(name = "head"),
                Token.StartTag(name = "title"),
                Token.Character(data = 'T'),
                Token.Character(data = 'e'),
                Token.Character(data = 's'),
                Token.Character(data = 't'),
                Token.EndTag(name = "title"),
                Token.EndTag(name = "head"),
                Token.StartTag(name = "body"),
                Token.StartTag(name = "p"),
                Token.Character(data = 'T'),
                Token.Character(data = 'e'),
                Token.Character(data = 's'),
                Token.Character(data = 't'),
                Token.EndTag(name = "p"),
                Token.StartTag(
                    name = "br",
                    selfClosing = true,
                ),
                Token.EndTag(name = "body"),
                Token.EndTag(name = "html"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `test char ref attribute value else case`() {
        val tokenizer = Tokenizer()
        val html = """
            <p class="This is a &[ test"></p>
        """.trimIndent()
        tokenizer.tokenize(html)
        assertEquals(
            listOf(
                Token.StartTag(
                    name = "p",
                    attributes = mutableMapOf(
                        "class" to "This is a &[ test",
                    )
                ),
                Token.EndTag(name = "p")
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `test ∉ char ref`() {
        val tokenizer = Tokenizer()
        val html = """
            <p>I'm &notin; I tell</p>
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
                Token.EndTag(name = "p")
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `test ¬it char ref`() {
        val tokenizer = Tokenizer()
        val html1 = """
            <p>I'm &notit; I tell</p>
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
                Token.EndTag(name = "p")
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `test nbsp char ref in string`() {
        val tokenizer = Tokenizer()
        val html1 = """
                &nbsp;&nbsp;&nbsp;
        """.trimIndent()
        tokenizer.tokenize(html1)
        assertEquals(
            listOf<Token>(
                Token.Character(data = ' '),
                Token.Character(data = ' '),
                Token.Character(data = ' '),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `numeric entity test`(){
        val tokenizer = Tokenizer()
        val html = """
            <p>&#8230;</p>
        """.trimIndent()
        tokenizer.tokenize(html)
        assertEquals(
            listOf(
                Token.StartTag(name = "p"),
                Token.Character(data = '…'),
                Token.EndTag(name = "p")
            ),
            tokenizer.emittedTokens
        )
    }
}