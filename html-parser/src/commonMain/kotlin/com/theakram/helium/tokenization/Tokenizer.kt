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

import com.theakram.helium.tokenization.states.StateTransition.ContinueInCurrentState
import com.theakram.helium.tokenization.states.StateTransition.SwitchTo
import com.theakram.helium.tokenization.states.StateTransition.ReconsumeIn
import com.theakram.helium.tokenization.states.StateTransition.Halt
import com.theakram.helium.tokenization.states.Data
import com.theakram.helium.tokenization.states.PlainText
import com.theakram.helium.tokenization.states.RCData
import com.theakram.helium.tokenization.states.RawText
import com.theakram.helium.tokenization.states.TokenizationState
import com.theakram.helium.treebuilder.TokenizerStateType
import com.theakram.helium.util.Chars

internal class Tokenizer(private val onTokenEmitted: (Token) -> Unit) {
    var currentPosition = 0
    lateinit var htmlBuffer: String
    private var currentState: TokenizationState = Data

    var characterReferenceCode = 0L
    val tempBuffer = StringBuilder(DEFAULT_BUFFER_SIZE)

    var currentAttributeName = StringBuilder(DEFAULT_BUFFER_SIZE)
    var currentAttributeValue = StringBuilder(DEFAULT_BUFFER_SIZE)

    var currentToken: Token? = null
    var lastStartTagName: String = ""

    fun tokenize(html: String) {
        htmlBuffer = preprocess(html)

        while (currentPosition <= htmlBuffer.length) {
            val codePoint = htmlBuffer.getOrNull(currentPosition)?.code ?: -1
            val stateBeforeConsume = currentState
            val transition = currentState.consume(codePoint, this)

            when (transition) {
                ContinueInCurrentState -> currentPosition++
                is SwitchTo -> {
                    if (currentState == stateBeforeConsume) {
                        currentState = transition.newState
                    }
                    currentPosition++
                }
                is ReconsumeIn -> currentState = transition.newState
                Halt -> return
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

    fun emitToken(token: Token) {
        if (token is Token.StartTag) {
            lastStartTagName = token.name
        }
        onTokenEmitted(token)
    }

    fun peekNextChar(): Char = htmlBuffer.getOrNull(currentPosition + 1) ?: Chars.EOF.char

    fun logParseError(error: String) = System.err.println("Parse error: $error at position: $currentPosition")

    fun switchState(stateType: TokenizerStateType) {
        currentState = when (stateType) {
            TokenizerStateType.DATA -> Data
            TokenizerStateType.RCDATA -> RCData
            TokenizerStateType.RAWTEXT -> RawText
            TokenizerStateType.SCRIPT_DATA -> RawText  // SCRIPT_DATA not implemented, treating as RAWTEXT
            TokenizerStateType.PLAINTEXT -> PlainText
        }
    }
}