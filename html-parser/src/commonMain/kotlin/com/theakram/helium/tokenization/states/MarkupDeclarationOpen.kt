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

package com.theakram.helium.tokenization.states

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#markup-declaration-open-state
 */
internal data object MarkupDeclarationOpen : TokenizationState {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        val next2 = tokenizer.peekNextChars(2)
        val next7 = tokenizer.peekNextChars(7)

        return when {
            next2 == "--" -> {
                tokenizer.currentPosition += 1
                tokenizer.currentToken = Token.Comment("")
                switchTo(CommentStart(Token.Comment("")))
            }

            next7.equals("DOCTYPE", ignoreCase = true) -> {
                tokenizer.currentPosition += 6
                TODO("Switch to DOCTYPE state - not yet implemented")
            }

            next7 == "[CDATA[" -> {
                tokenizer.currentPosition += 6
                // TODO: Check if there is an adjusted current node and it is not an element in the HTML namespace
                // For now, treat as CDATA in HTML content (parse error)
                tokenizer.logParseError("cdata-in-html-content")
                val commentToken = Token.Comment("[CDATA[")
                switchTo(BogusComment(commentToken))
            }

            else -> {
                tokenizer.logParseError("incorrectly-opened-comment")
                val commentToken = Token.Comment("")
                reconsumeIn(BogusComment(commentToken))
            }
        }
    }
}