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

import com.theakram.helium.util.Chars
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.isAsciiUpperAlpha
import com.theakram.helium.util.appendUnicode
import com.theakram.helium.util.isWhiteSpace

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#tag-name-state
 */
internal data object TagName : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isWhiteSpace() -> switchTo(BeforeAttributeName)

            codePoint.IS(Chars.Solidus) -> switchTo(SelfClosingStartTag)

            codePoint.IS(Chars.GreaterThan) -> {
                tokenizer.switchToDataStateAndEmitCurrentTag()
            }

            codePoint.isAsciiUpperAlpha() -> {
                appendToTagName(tokenizer, codePoint + 0x0020)
                switchTo(this)
            }

            codePoint.IS(Chars.Null) -> {
                tokenizer.logParseError("unexpected-null-character")
                appendToTagName(tokenizer, 0xFFFD)
                switchTo(this)
            }

            codePoint == -1 -> {
                tokenizer.logParseError("eof-in-tag")
                tokenizer.emitToken(Token.EOF)
                continueInCurrentState()
            }

            else -> {
                appendToTagName(tokenizer, codePoint)
                switchTo(this)
            }
        }
    }

    private fun appendToTagName(tokenizer: Tokenizer, codePoint: Int) {
        tokenizer.currentTag.name.appendUnicode(codePoint)
    }
}
