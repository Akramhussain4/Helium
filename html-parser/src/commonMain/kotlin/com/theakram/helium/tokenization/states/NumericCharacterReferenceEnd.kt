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

import com.theakram.helium.tokenization.util.NumCharRef
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.flushCodePoints
import com.theakram.helium.util.appendUnicode
import com.theakram.helium.util.isAsciiWhitespace
import com.theakram.helium.util.isControl
import com.theakram.helium.util.isNonCharacter
import com.theakram.helium.util.isSurrogate

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#numeric-character-reference-end-state
 */
internal class NumericCharacterReferenceEnd(
    private val returnState: TokenizationState,
) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        // Check the character reference code. Each check reads the code as the checks before it left it, so a
        // null character reference becomes U+FFFD before the control check runs.
        if (tokenizer.characterReferenceCode == 0x00L) {
            tokenizer.logParseError("null-character-reference")
            tokenizer.characterReferenceCode = 0xFFFD
        }

        if (tokenizer.characterReferenceCode > 0x10FFFF) {
            tokenizer.logParseError("character-reference-outside-unicode-range")
            tokenizer.characterReferenceCode = 0xFFFD
        }

        if (tokenizer.characterReferenceCode.toInt().isSurrogate()) {
            tokenizer.logParseError("surrogate-character-reference")
            tokenizer.characterReferenceCode = 0xFFFD
        }

        if (tokenizer.characterReferenceCode.toInt().isNonCharacter()) {
            tokenizer.logParseError("noncharacter-character-reference")
        }

        val number = tokenizer.characterReferenceCode.toInt()
        if (number == 0x0D || (number.isControl() && !number.isAsciiWhitespace())) {
            tokenizer.logParseError("control-character-reference")
            NumCharRef[number]?.let {
                tokenizer.characterReferenceCode = it.toLong()
            }
        }

        tokenizer.tempBuffer.clear()
        tokenizer.tempBuffer.appendUnicode(tokenizer.characterReferenceCode.toInt())
        returnState.flushCodePoints(tokenizer)
        // This state consumes no input character, so reconsuming the one it was given in the return state is the
        // spec's "Switch to the return state"
        return reconsumeIn(returnState)
    }
}