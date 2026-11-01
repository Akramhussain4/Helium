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

package com.theakram.helium.treebuilder.modes

import com.theakram.helium.tokenization.Token
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.modes.helpers.InHeadHelpers
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo
import com.theakram.helium.util.isAsciiWhitespace

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#the-after-head-insertion-mode
 */
internal object AfterHead : InsertionMode {
    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character if token.codePoint.isAsciiWhitespace() -> {
                treeBuilder.insertCharacter(token.codePoint)
                continueInCurrentMode()
            }

            is Token.Comment -> {
                treeBuilder.insertComment(token.data)
                continueInCurrentMode()
            }

            is Token.ProcessingInstruction -> {
                treeBuilder.insertProcessingInstruction(token.target, token.data)
                continueInCurrentMode()
            }

            is Token.DocType -> {
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            is Token.StartTag if token.name == "html" -> InBody.process(token, treeBuilder)

            is Token.StartTag if token.name == "body" -> {
                treeBuilder.insertElement(token.name, attributes = InHeadHelpers.toAttributeList(token.attributes))
                treeBuilder.setFramesetNotOk()
                switchTo(InBody)
            }

            is Token.StartTag if token.name == "frameset" -> {
                treeBuilder.insertElement(token.name, attributes = InHeadHelpers.toAttributeList(token.attributes))
                switchTo(InFrameSet)
            }

            is Token.StartTag if token.name in IN_HEAD_START_TAGS -> {
                treeBuilder.parseError()
                val headElement = checkNotNull(treeBuilder.headElement) {
                    "The head element pointer cannot be null at this point"
                }
                treeBuilder.pushElement(headElement)
                val transition = InHead.process(token, treeBuilder)
                treeBuilder.removeElement(headElement)
                transition
            }

            is Token.EndTag if token.name == "template" -> InHead.process(token, treeBuilder)

            is Token.EndTag if token.name in ANYTHING_ELSE_END_TAGS -> handleAnythingElse(treeBuilder)

            is Token.StartTag if token.name == "head" -> {
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            is Token.EndTag -> {
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            else -> handleAnythingElse(treeBuilder)
        }
    }

    private fun handleAnythingElse(treeBuilder: TreeBuilder): InsertionModeTransition {
        treeBuilder.createImplicitElement("body")
        treeBuilder.setFramesetOk()
        return reprocessIn(InBody)
    }

    private val IN_HEAD_START_TAGS = setOf(
        "base", "basefont", "bgsound", "link", "meta", "noframes", "script", "style", "template", "title"
    )

    private val ANYTHING_ELSE_END_TAGS = setOf("body", "html", "br")
}
