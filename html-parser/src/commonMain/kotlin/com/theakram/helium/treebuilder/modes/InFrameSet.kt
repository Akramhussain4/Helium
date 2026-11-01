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
import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.stopParsing
import com.theakram.helium.treebuilder.switchTo
import com.theakram.helium.util.isAsciiWhitespace

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-inframeset
 */
internal object InFrameSet : InsertionMode {

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
                // Parse error. Ignore the token.
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            is Token.StartTag if token.name == "html" -> InBody.process(token, treeBuilder)

            is Token.StartTag if token.name == "frameset" -> {
                val attributes = token.attributes.map { (name, value) ->
                    Attribute(namespace = null, prefix = null, localName = name, value = value)
                }
                treeBuilder.insertElement("frameset", attributes = attributes)
                continueInCurrentMode()
            }

            is Token.EndTag if token.name == "frameset" -> {
                if (isCurrentNodeRootHtmlElement(treeBuilder)) {
                    // Parse error; ignore the token. (fragment case)
                    treeBuilder.parseError()
                    continueInCurrentMode()
                } else {
                    treeBuilder.popCurrentElement()
                    if (!treeBuilder.isFragmentCase &&
                        (treeBuilder.currentNode.localName != "frameset" ||
                                treeBuilder.currentNode.namespace != Namespaces.HTML)
                    ) {
                        switchTo(AfterFrameSet)
                    } else {
                        continueInCurrentMode()
                    }
                }
            }

            is Token.StartTag if token.name == "frame" -> {
                val attributes = token.attributes.map { (name, value) ->
                    Attribute(namespace = null, prefix = null, localName = name, value = value)
                }
                treeBuilder.insertElement("frame", attributes = attributes)
                treeBuilder.popCurrentElement()
                treeBuilder.acknowledgeSelfClosingFlag()
                continueInCurrentMode()
            }

            is Token.StartTag if token.name == "noframes" -> InHead.process(token, treeBuilder)

            is Token.EOF -> {
                // The current node can only be the root html element in the fragment case
                if (!isCurrentNodeRootHtmlElement(treeBuilder)) {
                    treeBuilder.parseError()
                }
                stopParsing()
            }

            else -> {
                // Parse error. Ignore the token.
                treeBuilder.parseError()
                continueInCurrentMode()
            }
        }
    }

    // The root html element is the first element on the stack of open elements
    private fun isCurrentNodeRootHtmlElement(treeBuilder: TreeBuilder): Boolean =
        treeBuilder.openElementCount == 1 &&
                treeBuilder.currentNode.namespace == Namespaces.HTML &&
                treeBuilder.currentNode.localName == "html"
}
