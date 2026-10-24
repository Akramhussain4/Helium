/*
 * MIT License
 *
 * Copyright (c) 2023-2026 Mohammed Akram Hussain
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
import com.theakram.helium.tokenization.states.TokenizationState
import com.theakram.helium.treebuilder.TokenizerStateType

internal abstract class TokenizerTestBase {

    protected fun tokenize(
        input: String,
        initialState: TokenizerStateType = TokenizerStateType.DATA,
        lastStartTag: String? = null
    ): List<Token> {
        val emittedTokens = mutableListOf<Token>()
        val tokenizer = Tokenizer(onTokenEmitted = emittedTokens::add, onParseError = { _, _ -> })

        // Set the initial state
        tokenizer.switchState(initialState)

        // Set the lastStartTagName if provided
        if (lastStartTag != null) {
            tokenizer.lastStartTagName = lastStartTag
        }

        tokenizer.tokenize(html = input)
        return emittedTokens.filterNot { it is Token.EOF }
    }

    protected fun tokenizeWithDirectState(
        input: String,
        initialState: TokenizationState,
        lastStartTag: String? = null
    ): List<Token> {
        val emittedTokens = mutableListOf<Token>()
        val tokenizer = Tokenizer(onTokenEmitted = emittedTokens::add, onParseError = { _, _ -> })

        // Set the initial state using
        tokenizer.setState(initialState)

        // Set the lastStartTagName if provided
        if (lastStartTag != null) {
            tokenizer.lastStartTagName = lastStartTag
        }

        tokenizer.tokenize(html = input)
        return emittedTokens.filterNot { it is Token.EOF }
    }

    protected fun assertTokenization(
        input: String,
        expectedTokens: List<Token>,
        initialState: TokenizerStateType = TokenizerStateType.DATA,
        lastStartTag: String? = null
    ) {
        val actualTokens = tokenize(input, initialState, lastStartTag)
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

        fun doctype(
            name: String? = null,
            publicId: String? = null,
            systemId: String? = null,
            forceQuirks: Boolean = false
        ) = Token.DocType(
            name = name,
            publicId = publicId,
            systemId = systemId,
            forceQuirks = forceQuirks
        )

        fun chars(text: String) = text.map { char(it) }
    }
}