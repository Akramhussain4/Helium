package com.hussain.csoup.tokenizer

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import kotlin.test.assertEquals

internal abstract class TokenizerTestBase {

    protected val tokenizer = Tokenizer()

    protected fun assertTokenization(
        input: String,
        expectedTokens: List<Token>
    ) {
        tokenizer.tokenize(input)
        assertEquals(expectedTokens, tokenizer.emittedTokens)
    }

    protected fun assertTokenizationWithErrors(
        input: String,
        expectedTokens: List<Token>,
        expectedErrors: List<String>
    ) {
        tokenizer.tokenize(input)
        assertEquals(expectedTokens, tokenizer.emittedTokens)
        //assertEquals(expectedErrors, tokenizer.errors)
    }

    protected companion object {
        fun startTag(
            name: String,
            selfClosing: Boolean = false,
            attributes: Map<String, String> = emptyMap()
        ) = Token.StartTag(
            name = name,
            selfClosing = selfClosing,
            attributes = attributes.toMutableMap()
        )

        fun endTag(name: String) = Token.EndTag(name)

        fun char(c: Char) = Token.Character(c)

        fun comment(data: String) = Token.Comment(data)

        fun chars(text: String) = text.map { char(it) }
    }
}