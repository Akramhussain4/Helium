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
import com.theakram.helium.tokenization.states.ScriptData
import com.theakram.helium.tokenization.states.TokenizationState
import com.theakram.helium.treebuilder.TokenizerStateType
import com.theakram.helium.util.Chars
import com.theakram.helium.util.codePointAt
import com.theakram.helium.util.isAsciiWhitespace
import com.theakram.helium.util.isControl
import com.theakram.helium.util.isNonCharacter
import com.theakram.helium.util.isSurrogate
import com.theakram.helium.util.utf16Length

private const val INITIAL_BUFFER_CAPACITY = 64

internal class Tokenizer(
    private val onTokenEmitted: (Token) -> Unit,
    private val onParseError: (String, Int) -> Unit,
    // Whether the tree builder's adjusted current node is an SVG or MathML element, where <![CDATA[ is allowed
    private val hasForeignAdjustedCurrentNode: () -> Boolean = { false }
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
    var currentProcessingInstructionTarget: String = ""
    val currentProcessingInstructionData = StringBuilder(INITIAL_BUFFER_CAPACITY)

    var isCurrentTagStartTag: Boolean = true
    var lastStartTagName: String = ""

    /** The number of UTF-16 units the current input character takes in [htmlBuffer]: 2 for a supplementary one. */
    private var currentCharacterLength = 1

    /** Where the input stream's parse errors have been checked up to, as a position in [htmlBuffer]. */
    private var checkedPosition = 0

    fun tokenize(html: String) {
        htmlBuffer = normalizeNewlines(html)

        while (currentPosition <= htmlBuffer.length) {
            val codePoint = if (currentPosition < htmlBuffer.length) htmlBuffer.codePointAt(currentPosition) else -1
            currentCharacterLength = codePoint.utf16Length()
            checkInputStreamUpTo(currentPosition)
            val stateBeforeConsume = currentState
            val transition = currentState.consume(codePoint, this)

            when (transition) {
                ContinueInCurrentState -> currentPosition += currentCharacterLength
                is SwitchTo -> {
                    if (currentState == stateBeforeConsume) {
                        currentState = transition.newState
                    }
                    currentPosition += currentCharacterLength
                }
                is ReconsumeIn -> currentState = transition.newState
            }
        }
    }

    /**
     * Normalizes newlines in the input stream, the first step of preprocessing it. Its parse errors are reported by
     * [checkInputStreamUpTo] as the tokenizer reaches each character.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#preprocessing-the-input-stream
     */
    private fun normalizeNewlines(input: String): String = input.replace("\r\n", "\n").replace('\r', '\n')

    /**
     * Reports the input stream's parse errors for every code point up to and including the one at [position]: "Any
     * occurrences of surrogates are surrogate-in-input-stream parse errors. Any occurrences of noncharacters are
     * noncharacter-in-input-stream parse errors and any occurrences of controls other than ASCII whitespace and
     * U+0000 NULL characters are control-character-in-input-stream parse errors."
     *
     * Each one is reported when the tokenizer first reaches it, so the errors come in the order of the input, and a
     * character that a state only looks ahead at is reported once it is consumed.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#preprocessing-the-input-stream
     */
    private fun checkInputStreamUpTo(position: Int) {
        while (checkedPosition <= position && checkedPosition < htmlBuffer.length) {
            val codePoint = htmlBuffer.codePointAt(checkedPosition)
            when {
                codePoint.isSurrogate() -> onParseError("surrogate-in-input-stream", checkedPosition)
                codePoint.isNonCharacter() -> onParseError("noncharacter-in-input-stream", checkedPosition)
                codePoint.isControl() && !codePoint.isAsciiWhitespace() && codePoint != 0x0000 ->
                    onParseError("control-character-in-input-stream", checkedPosition)
            }
            checkedPosition += codePoint.utf16Length()
        }
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

    fun peekNextChar(): Char = htmlBuffer.getOrNull(currentPosition + currentCharacterLength) ?: Chars.EOF.char

    fun peekNextChars(count: Int): String {
        val startIndex = (currentPosition + currentCharacterLength).coerceAtMost(htmlBuffer.length)
        val endIndex = (startIndex + count).coerceAtMost(htmlBuffer.length)
        return htmlBuffer.substring(startIndex, endIndex)
    }

    fun isInForeignContent(): Boolean = hasForeignAdjustedCurrentNode()

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
            TokenizerStateType.SCRIPT_DATA -> ScriptData
            TokenizerStateType.PLAINTEXT -> PlainText
        }
    }

    fun reset() {
        currentPosition = 0
        currentCharacterLength = 1
        checkedPosition = 0
        currentState = Data
        characterReferenceCode = 0L
        tempBuffer.clear()
        currentAttributeName.clear()
        currentAttributeValue.clear()
        docTypeBuilder.reset()
        startTagBuilder.reset()
        currentEndTagName.clear()
        currentCommentData.clear()
        currentProcessingInstructionTarget = ""
        currentProcessingInstructionData.clear()
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

    fun createProcessingInstruction(target: String) {
        currentProcessingInstructionTarget = target
        currentProcessingInstructionData.clear()
    }

    fun emitProcessingInstruction() {
        onTokenEmitted(
            Token.ProcessingInstruction(currentProcessingInstructionTarget, currentProcessingInstructionData.toString())
        )
        currentProcessingInstructionData.clear()
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#convert-the-temporary-buffer-to-a-comment
     */
    fun convertTemporaryBufferToComment() {
        currentCommentData.clear()
        currentCommentData.append('?')
        currentCommentData.append(tempBuffer)
    }

    fun emitCurrentTag(selfClosing: Boolean = false) {
        if (isCurrentTagStartTag) {
            emitStartTag(selfClosing)
        } else {
            emitEndTag()
        }
    }
}