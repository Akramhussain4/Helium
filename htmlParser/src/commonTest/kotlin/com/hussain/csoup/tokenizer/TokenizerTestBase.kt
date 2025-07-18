package com.hussain.csoup.tokenizer

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal abstract class TokenizerTestBase {

    private val emittedTokens = mutableListOf<Token>()
    private val tokenizer = Tokenizer(onTokenEmitted = emittedTokens::add)

    protected fun tokenize(input: String): List<Token> {
        emittedTokens.clear()
        tokenizer.tokenize(html = input)
        return emittedTokens.toList()
    }

    protected fun assertTokenization(input: String, expectedTokens: List<Token>) {
        val actualTokens = tokenize(input)
        if (actualTokens.size != expectedTokens.size) {
            throw AssertionError(
                "Token count mismatch. Expected ${expectedTokens.size}," +
                        " but got ${actualTokens.size}\nExpected: $expectedTokens\nActual:   $actualTokens"
            )
        }

        expectedTokens.forEachIndexed { i, token ->
            if (token != actualTokens[i]) {
                throw AssertionError(
                    "Token mismatch at index $i.\n" +
                            "Expected: $token\nActual:   ${actualTokens[i]}"
                )
            }
        }
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