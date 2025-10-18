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
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.Chars
import com.theakram.helium.util.isAsciiUpperAlpha
import com.theakram.helium.util.isWhiteSpace

internal class AttributeName(private val currentTagToken: Token) : TokenizationState {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.IS(
                Chars.Solidus,
                Chars.GreaterThan
            ) || codePoint.isWhiteSpace() || codePoint == -1 -> reconsumeIn(AfterAttributeName(currentTagToken = currentTagToken))

            codePoint.IS(Chars.Equals) -> switchTo(BeforeAttributeValue(currentTagToken = currentTagToken))

            codePoint.isAsciiUpperAlpha() -> {
                tokenizer.currentAttributeName.append((codePoint + 0x0020).toChar())
                switchTo(this)
            }

            codePoint.IS(Chars.Null) -> {
                tokenizer.logParseError("unexpected-null-character")
                tokenizer.currentAttributeName.append('\uFFFD')
                switchTo(this)
            }

            codePoint.IS(
                Chars.QuotationMark,
                Chars.Apostrophe,
                Chars.LessThan
            ) -> {
                tokenizer.logParseError("unexpected-character-in-attribute-name")
                tokenizer.currentAttributeName.append(codePoint.toChar())
                switchTo(this)
            }

            else -> {
                tokenizer.currentAttributeName.append(codePoint.toChar())
                switchTo(this)
            }
        }
    }
}