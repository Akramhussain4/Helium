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

package com.theakram.helium.tokenization.states

import com.theakram.helium.util.appendUnicode
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.util.equalsAsciiCaseInsensitive

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#markup-declaration-open-state
 */
internal data object MarkupDeclarationOpen : TokenizationState {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        if (codePoint == -1) {
            return handleAnythingElse(tokenizer)
        }

        // The state looks at the next few characters, of which codePoint is the first, before consuming any
        val next7 = StringBuilder(7).appendUnicode(codePoint).append(tokenizer.peekNextChars(6)).toString()

        return when {
            codePoint == '-'.code && tokenizer.peekNextChar() == '-'.code -> {
                tokenizer.currentPosition += 1
                tokenizer.currentCommentData.clear()
                switchTo(CommentStart)
            }

            next7.equalsAsciiCaseInsensitive("DOCTYPE") -> {
                tokenizer.currentPosition += 6
                switchTo(DOCTYPE)
            }

            next7 == "[CDATA[" -> {
                tokenizer.currentPosition += 6
                if (tokenizer.isInForeignContent()) {
                    switchTo(CDATASection)
                } else {
                    tokenizer.logParseError("cdata-in-html-content")
                    tokenizer.currentCommentData.clear()
                    tokenizer.currentCommentData.append("[CDATA[")
                    switchTo(BogusComment)
                }
            }

            else -> handleAnythingElse(tokenizer)
        }
    }

    private fun handleAnythingElse(tokenizer: Tokenizer): StateTransition {
        tokenizer.logParseError("incorrectly-opened-comment")
        tokenizer.currentCommentData.clear()
        return reconsumeIn(BogusComment)
    }
}