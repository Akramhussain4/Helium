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
import com.theakram.helium.tokenization.states.StateTransition
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

    /**
     * The state the character reference states return to.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#return-state
     */
    var returnState: TokenizationState = Data

    var characterReferenceCode = 0L
    val tempBuffer = StringBuilder(INITIAL_BUFFER_CAPACITY)

    val docTypeBuilder = DocTypeBuilder()
    val currentTag = TagTokenBuilder()
    val currentAttributeName: StringBuilder get() = currentTag.currentAttributeName
    val currentAttributeValue: StringBuilder get() = currentTag.currentAttributeValue
    var currentCommentData = StringBuilder(INITIAL_BUFFER_CAPACITY)
    var currentProcessingInstructionTarget: String = ""
    val currentProcessingInstructionData = StringBuilder(INITIAL_BUFFER_CAPACITY)

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
            val transition = currentState.consume(codePoint, this)

            when (transition) {
                ContinueInCurrentState -> currentPosition += currentCharacterLength
                is SwitchTo -> {
                    currentState = transition.newState
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

    /** Emits [token], which is not a tag token: those are emitted with [switchToDataStateAndEmitCurrentTag]. */
    fun emitToken(token: Token) {
        onTokenEmitted(token)
    }

    /**
     * "Leaves the attribute name state": compares the attribute's complete name with the token's others, and removes
     * it as a duplicate-attribute parse error if one has the same name.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#attribute-name-state
     */
    fun leaveAttributeName() {
        if (currentTag.removeCurrentAttributeIfDuplicate()) {
            logParseError("duplicate-attribute")
        }
    }

    /**
     * "Switch to the data state. Emit the current tag token." The tokenizer switches first, so that tree construction
     * can switch it to another state while it processes the token.
     *
     * An end tag token emitted with attributes is an end-tag-with-attributes parse error, and one emitted with its
     * self-closing flag set is an end-tag-with-trailing-solidus parse error.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#tokenization
     */
    fun switchToDataStateAndEmitCurrentTag(): StateTransition {
        currentState = Data
        if (currentTag.isStartTag) {
            lastStartTagName = currentTag.name.toString()
        } else {
            if (currentTag.hasAttributes) logParseError("end-tag-with-attributes")
            if (currentTag.selfClosing) logParseError("end-tag-with-trailing-solidus")
        }
        onTokenEmitted(currentTag.build())
        // The current input character is consumed, in whatever state the tokenizer is now in
        return ContinueInCurrentState
    }

    /** The input character after the current one, without consuming it, or -1 at the end of the input. */
    fun peekNextChar(): Int {
        val index = currentPosition + currentCharacterLength
        return if (index < htmlBuffer.length) htmlBuffer.codePointAt(index) else -1
    }

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
        returnState = Data
        characterReferenceCode = 0L
        tempBuffer.clear()
        docTypeBuilder.reset()
        currentTag.create(isStartTag = true)
        currentCommentData.clear()
        currentProcessingInstructionTarget = ""
        currentProcessingInstructionData.clear()
        lastStartTagName = ""
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
}
