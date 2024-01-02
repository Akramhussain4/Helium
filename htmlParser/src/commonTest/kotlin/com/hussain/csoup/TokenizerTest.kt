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
            tokenizer.emittedTokens, listOf(
                Token.StartTag(name = "p"),
                Token.Character(data = 'h'),
                Token.Character(data = 'e'),
                Token.Character(data = 'l'),
                Token.Character(data = 'l'),
                Token.Character(data = 'o'),
                Token.EndTag(name = "p")
            )
        )
    }
}