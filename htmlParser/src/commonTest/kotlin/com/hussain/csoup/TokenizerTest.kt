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
            listOf<Token>(
                Token.StartTag(
                    name = "p",
                    attributes = mapOf(
                        "class" to "test",
                    )
                ),
                Token.Character(data = 'a'),
                Token.EndTag(name = "p")
            ),
            tokenizer.emittedTokens
        )
    }
}