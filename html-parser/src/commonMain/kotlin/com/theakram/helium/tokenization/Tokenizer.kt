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

package com.theakram.helium.tokenization

import com.theakram.helium.tokenization.states.Action
import com.theakram.helium.tokenization.states.Data
import com.theakram.helium.tokenization.states.TokenizationState
import com.theakram.helium.util.Chars

internal class Tokenizer(private val onTokenEmitted: (Token) -> Unit) {
    var currentPosition = 0
    lateinit var htmlBuffer: String
    private var currentState: TokenizationState = Data

    var characterReferenceCode = 0
    val tempBuffer = StringBuilder(DEFAULT_BUFFER_SIZE)

    var currentAttributeName = StringBuilder(DEFAULT_BUFFER_SIZE)
    var currentAttributeValue = StringBuilder(DEFAULT_BUFFER_SIZE)

    fun tokenize(html: String) {
        htmlBuffer = preprocess(html)

        while (currentPosition <= htmlBuffer.length) {
            if (currentPosition == htmlBuffer.length) {
                val (nextState, action) = handleEOF()
                if (action == Action.None) break
                currentState = nextState
                continue
            }

            val (nextState, action) = currentState.consume(
                codePoint = htmlBuffer[currentPosition].code,
                tokenizer = this
            )

            when (action) {
                Action.ConsumeNext -> {
                    currentPosition++
                    currentState = nextState
                }

                Action.Reconsume -> currentState = nextState
                Action.None -> break
            }
        }
    }

    private fun preprocess(input: String): String {
        val step1 = input.replace("\r\n", "\n")
        return step1.replace('\r', '\n')
    }

    fun addCurrentAttributeToToken(currentToken: Token) {
        if (currentToken is Token.StartTag && currentAttributeName.isNotEmpty()) {
            val name = currentAttributeName.toString()
            val value = currentAttributeValue.toString()
            currentToken.addAttribute(name, value)
        }
        // Clear buffers for the next attribute
        currentAttributeName.clear()
        currentAttributeValue.clear()
    }

    private fun handleEOF() = currentState.consume(-1, this)

    fun emitToken(token: Token) = onTokenEmitted(token)

    fun peekNextChar(): Char = htmlBuffer.getOrNull(currentPosition + 1) ?: Chars.EOF.char

    fun logParseError(error: String) = System.err.println("Parse error: $error at position: $currentPosition")
}