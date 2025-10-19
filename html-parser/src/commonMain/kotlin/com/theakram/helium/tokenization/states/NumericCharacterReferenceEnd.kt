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

import com.theakram.helium.tokenization.util.NumCharRef
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.flushCodePoints
import com.theakram.helium.util.isAsciiWhitespace
import com.theakram.helium.util.isNonCharacter
import com.theakram.helium.util.isSurrogate

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#numeric-character-reference-end-state
 */
internal class NumericCharacterReferenceEnd(
    private val returnState: TokenizationState,
) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        val number = tokenizer.characterReferenceCode
        when {
            number == 0x00L -> {
                tokenizer.logParseError("null-character-reference")
                tokenizer.characterReferenceCode = 0xFFFD
            }

            number > 0x10FFFF -> {
                tokenizer.logParseError("character-reference-outside-unicode-range")
                tokenizer.characterReferenceCode = 0xFFFD
            }

            number.toInt().isSurrogate() -> {
                tokenizer.logParseError("surrogate-character-reference")
                tokenizer.characterReferenceCode = 0xFFFD
            }

            number.toInt().isNonCharacter() -> {
                tokenizer.logParseError("noncharacter-character-reference")
            }
        }

        if (number == 0x0DL || (number <= 0xFFFF && number.toInt().toChar().isISOControl() && !number.toInt().isAsciiWhitespace())) {
            tokenizer.logParseError("control-character-reference")
            NumCharRef[number.toInt()]?.let {
                tokenizer.characterReferenceCode = it.toLong()
            }
        }
        tokenizer.tempBuffer.clear()
        tokenizer.tempBuffer.appendCodePoint(tokenizer.characterReferenceCode.toInt())
        returnState.flushCodePoints(tokenizer)
        return reconsumeIn(returnState)
    }
}