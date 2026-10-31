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

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.Chars
import com.theakram.helium.util.isAsciiLowerAlpha
import com.theakram.helium.util.isAsciiUpperAlpha

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#script-data-end-tag-name-state
 */
internal data object ScriptDataEndTagName : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.IS(Chars.Tab) ||
            codePoint.IS(Chars.LineFeed) ||
            codePoint.IS(Chars.FormFeed) ||
            codePoint.IS(Chars.Space) -> {
                if (isAppropriateEndTag(tokenizer)) {
                    switchTo(BeforeAttributeName)
                } else {
                    anythingElse(tokenizer)
                }
            }

            codePoint.IS(Chars.Solidus) -> {
                if (isAppropriateEndTag(tokenizer)) {
                    switchTo(SelfClosingStartTag)
                } else {
                    anythingElse(tokenizer)
                }
            }

            codePoint.IS(Chars.GreaterThan) -> {
                if (isAppropriateEndTag(tokenizer)) {
                    tokenizer.emitEndTag()
                    switchTo(Data)
                } else {
                    anythingElse(tokenizer)
                }
            }

            codePoint.isAsciiUpperAlpha() -> {
                tokenizer.currentEndTagName.append(codePoint.toChar().lowercaseChar())
                tokenizer.tempBuffer.append(codePoint.toChar())
                continueInCurrentState()
            }

            codePoint.isAsciiLowerAlpha() -> {
                tokenizer.currentEndTagName.append(codePoint.toChar())
                tokenizer.tempBuffer.append(codePoint.toChar())
                continueInCurrentState()
            }

            else -> anythingElse(tokenizer)
        }
    }

    private fun isAppropriateEndTag(tokenizer: Tokenizer): Boolean {
        return tokenizer.currentEndTagName.toString() == tokenizer.lastStartTagName
    }

    private fun anythingElse(tokenizer: Tokenizer): StateTransition {
        tokenizer.emitToken(Token.Character('<'))
        tokenizer.emitToken(Token.Character('/'))
        tokenizer.tempBuffer.forEach { char ->
            tokenizer.emitToken(Token.Character(char))
        }
        return reconsumeIn(ScriptData)
    }
}
