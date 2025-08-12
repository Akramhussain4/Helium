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

import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.Chars
import com.theakram.helium.util.isAsciiDigit
import com.theakram.helium.util.isAsciiLowerHexDigit
import com.theakram.helium.util.isAsciiUpperHexDigit

internal class HexadecimalCharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isAsciiDigit() -> {
                tokenizer.characterReferenceCode *= 16
                tokenizer.characterReferenceCode += codePoint - 0x0030
                if (tokenizer.characterReferenceCode > 0x10FFFF) {
                    tokenizer.characterReferenceCode = 0x110000L
                }
                consumeNextChar { this }
            }

            codePoint.isAsciiUpperHexDigit() -> {
                tokenizer.characterReferenceCode *= 16
                tokenizer.characterReferenceCode += codePoint - 0x0037
                if (tokenizer.characterReferenceCode > 0x10FFFF) {
                    tokenizer.characterReferenceCode = 0x110000L
                }
                consumeNextChar { this }
            }

            codePoint.isAsciiLowerHexDigit() -> {
                tokenizer.characterReferenceCode *= 16
                tokenizer.characterReferenceCode += codePoint - 0x0057
                if (tokenizer.characterReferenceCode > 0x10FFFF) {
                    tokenizer.characterReferenceCode = 0x110000L
                }
                consumeNextChar { this }
            }

            codePoint.IS(Chars.SemiColon) -> {
                consumeNextChar {
                    NumericCharacterReferenceEnd(returnState = returnState)
                }
            }

            else -> {
                tokenizer.logParseError("missing-semicolon-after-character-reference")
                reconsumeCurrentChar {
                    NumericCharacterReferenceEnd(returnState = returnState)
                }
            }
        }
    }
}