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

package com.theakram.helium.tokenization

import com.theakram.helium.tokenization.states.StateTransition.ContinueInCurrentState
import com.theakram.helium.tokenization.states.StateTransition.SwitchTo
import com.theakram.helium.tokenization.states.StateTransition.ReconsumeIn
import com.theakram.helium.tokenization.states.Data
import com.theakram.helium.tokenization.states.PlainText
import com.theakram.helium.tokenization.states.RCData
import com.theakram.helium.tokenization.states.RawText
import com.theakram.helium.tokenization.states.TokenizationState
import com.theakram.helium.treebuilder.TokenizerStateType
import com.theakram.helium.util.Chars
import com.theakram.helium.util.isNonCharacter
import com.theakram.helium.util.isSurrogate

private const val INITIAL_BUFFER_CAPACITY = 64

internal class Tokenizer(
    private val onTokenEmitted: (Token) -> Unit,
    private val onParseError: (String, Int) -> Unit
) {
    var currentPosition = 0
    lateinit var htmlBuffer: String
    private var currentState: TokenizationState = Data

    var characterReferenceCode = 0L
    val tempBuffer = StringBuilder(INITIAL_BUFFER_CAPACITY)

    var currentAttributeName = StringBuilder(INITIAL_BUFFER_CAPACITY)
    var currentAttributeValue = StringBuilder(INITIAL_BUFFER_CAPACITY)

    val docTypeBuilder = DocTypeBuilder()
    val startTagBuilder = StartTagBuilder()
    var currentEndTagName = StringBuilder(INITIAL_BUFFER_CAPACITY)
    var currentCommentData = StringBuilder(INITIAL_BUFFER_CAPACITY)

    var isCurrentTagStartTag: Boolean = true
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
            }
        }
    }

    private fun preprocess(input: String): String {
        val step1 = input.replace("\r\n", "\n")
        val step2 = step1.replace('\r', '\n')
        step2.forEach { char ->
            val codePoint = char.code
            when {
                codePoint.isSurrogate() -> logParseError("surrogate-in-input-stream")
                codePoint.isNonCharacter() -> logParseError("noncharacter-in-input-stream")
            }
        }
        return step2
    }

    fun addCurrentAttribute() {
        if (isCurrentTagStartTag && currentAttributeName.isNotEmpty()) {
            startTagBuilder.addAttribute(
                currentAttributeName.toString(),
                currentAttributeValue.toString()
            )
        }
        // Clear buffers for the next attribute (for both start and end tags)
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

    fun peekNextChars(count: Int): String {
        val endIndex = (currentPosition + 1 + count).coerceAtMost(htmlBuffer.length)
        return htmlBuffer.substring(currentPosition + 1, endIndex)
    }

    fun logParseError(error: String) {
        onParseError(error, currentPosition)
    }

    fun setState(state: TokenizationState) {
        currentState = state
    }

    fun switchState(stateType: TokenizerStateType) {
        currentState = when (stateType) {
            TokenizerStateType.DATA -> Data
            TokenizerStateType.RCDATA -> RCData
            TokenizerStateType.RAWTEXT -> RawText
            TokenizerStateType.SCRIPT_DATA -> RawText  // SCRIPT_DATA not implemented, treating as RAWTEXT
            TokenizerStateType.PLAINTEXT -> PlainText
        }
    }

    fun reset() {
        currentPosition = 0
        currentState = Data
        characterReferenceCode = 0L
        tempBuffer.clear()
        currentAttributeName.clear()
        currentAttributeValue.clear()
        docTypeBuilder.reset()
        startTagBuilder.reset()
        currentEndTagName.clear()
        currentCommentData.clear()
        isCurrentTagStartTag = true
        lastStartTagName = ""
    }

    fun emitStartTag(selfClosing: Boolean = false) {
        val tag = startTagBuilder.build(selfClosing)
        lastStartTagName = tag.name
        onTokenEmitted(tag)
        startTagBuilder.reset()
    }

    fun emitEndTag() {
        onTokenEmitted(Token.EndTag(currentEndTagName.toString()))
        currentEndTagName.clear()
    }

    fun emitDocType() {
        onTokenEmitted(docTypeBuilder.build())
        docTypeBuilder.reset()
    }

    fun emitComment() {
        onTokenEmitted(Token.Comment(currentCommentData.toString()))
        currentCommentData.clear()
    }

    fun emitCurrentTag(selfClosing: Boolean = false) {
        if (isCurrentTagStartTag) {
            emitStartTag(selfClosing)
        } else {
            emitEndTag()
        }
    }
}