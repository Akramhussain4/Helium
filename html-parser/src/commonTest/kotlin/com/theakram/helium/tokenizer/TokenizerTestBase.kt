/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
 * Website: https://theakram.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.theakram.helium.tokenizer

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer

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