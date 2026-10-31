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
import com.theakram.helium.treebuilder.ScriptingMode
import com.theakram.helium.treebuilder.TokenizerStateType
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.modes.helpers.InHeadHelpers
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo
import com.theakram.helium.util.isAsciiWhitespace

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-inhead
 */
internal object InHead : InsertionMode {

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character if token.data.code.isAsciiWhitespace() -> {
                treeBuilder.insertCharacter(token.data)
                continueInCurrentMode()
            }

            is Token.Comment -> {
                treeBuilder.insertComment(token.data)
                continueInCurrentMode()
            }

            is Token.DocType -> {
                // Parse error
                continueInCurrentMode()
            }

            is Token.StartTag if token.name == "html" -> InBody.process(token, treeBuilder)

            is Token.StartTag if token.name in setOf("base", "basefont", "bgsound", "link") -> {
                val attributes = InHeadHelpers.toAttributeList(token.attributes)
                treeBuilder.insertElement(token.name, attributes = attributes)
                treeBuilder.popCurrentElement()
                // TODO: Acknowledge the token's self-closing flag, if it is set
                continueInCurrentMode()
            }

            is Token.StartTag if token.name == "meta" -> {
                val attributes = InHeadHelpers.toAttributeList(token.attributes)
                treeBuilder.insertElement(token.name, attributes = attributes)
                treeBuilder.popCurrentElement()
                // TODO: Acknowledge the token's self-closing flag, if it is set
                // TODO: Handle character encoding detection (if active speculative HTML parser is null)
                continueInCurrentMode()
            }

            is Token.StartTag if token.name == "title" -> {
                InHeadHelpers.followGenericRCDATAElementParsingAlgorithm(token, treeBuilder)
            }

            is Token.StartTag if token.name in setOf("noframes", "style") -> {
                InHeadHelpers.followGenericRawTextElementParsingAlgorithm(token, treeBuilder)
            }

            is Token.StartTag if token.name == "noscript" -> {
                if (treeBuilder.scriptingMode != ScriptingMode.DISABLED) {
                    InHeadHelpers.followGenericRawTextElementParsingAlgorithm(token, treeBuilder)
                } else {
                    val attributes = InHeadHelpers.toAttributeList(token.attributes)
                    treeBuilder.insertElement(token.name, attributes = attributes)
                    switchTo(InHeadNoScript)
                }
            }

            is Token.StartTag if token.name == "script" -> {
                val attributes = InHeadHelpers.toAttributeList(token.attributes)
                // Steps 1-2, 6-7:
                val scriptElement = treeBuilder.insertElement(token.name, attributes = attributes)
                // Step 3:
                // TODO: Set parser document
                // TODO: Set force async to false
                // Step 4:
                // TODO: Check if fragment parsing and set already started flag
                // Step 5:
                // TODO: Check for document.write() invocation
                // Step 8:
                treeBuilder.switchTokenizerState(TokenizerStateType.SCRIPT_DATA)
                // Step 9:
                treeBuilder.saveOriginalInsertionMode()
                // Step 10:
                return switchTo(Text)
            }

            is Token.EndTag if token.name == "head" -> {
                treeBuilder.popCurrentElement()
                switchTo(AfterHead)
            }

            is Token.EndTag if token.name in setOf("body", "html", "br") -> {
                treeBuilder.popCurrentElement()
                reprocessIn(AfterHead)
            }

            is Token.StartTag if token.name == "template" -> {
                // Step 1:
                treeBuilder.activeFormattingElements.insertMarker()
                // Step 2:
                treeBuilder.setFramesetNotOk()
                // Step 4:
                treeBuilder.pushTemplateInsertionMode(InTemplate)
                // TODO: Implement full declarative shadow DOM logic from the spec:
                // For now, we just insert a regular template element:
                val attributes = InHeadHelpers.toAttributeList(token.attributes)
                treeBuilder.insertElement(token.name, attributes = attributes)
                // Step 3:
                switchTo(InTemplate)
            }

            is Token.EndTag if token.name == "template" -> {
                val hasTemplateInStack = treeBuilder.elementStack.any {
                    it.localName == "template" && it.namespace == com.theakram.helium.tree.Namespaces.HTML
                }
                if (!hasTemplateInStack) {
                    // Parse error: ignore the token
                    return continueInCurrentMode()
                }

                // Step 1:
                // TODO: Implement generateAllImpliedEndTagsThoroughly()
                // For now, we'll skip this step

                // Step 2:
                if (treeBuilder.currentNode.localName != "template" ||
                    treeBuilder.currentNode.namespace != com.theakram.helium.tree.Namespaces.HTML
                ) {
                    // Parse error
                }

                // Step 3:
                treeBuilder.popUntil("template")
                // Step 4:
                treeBuilder.activeFormattingElements.clearToLastMarker()
                // Step 5:
                treeBuilder.popTemplateInsertionMode()
                // Step 6:
                treeBuilder.resetInsertionModeAppropriately()
                switchTo(treeBuilder.currentMode)
            }

            is Token.StartTag if token.name == "head" -> {
                // Parse error
                continueInCurrentMode()
            }

            is Token.EndTag -> {
                // Parse error
                continueInCurrentMode()
            }

            else -> {
                treeBuilder.popCurrentElement()
                reprocessIn(AfterHead)
            }
        }
    }
}
