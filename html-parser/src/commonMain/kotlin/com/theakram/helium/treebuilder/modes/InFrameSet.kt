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

package com.theakram.helium.treebuilder.modes

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.treebuilder.*
import com.theakram.helium.util.isAsciiWhitespace

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-inframeset
 */
internal object InFrameSet : InsertionMode {

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
                // Parse error. Ignore the token.
                continueInCurrentMode()
            }

            is Token.StartTag if token.name.equals("html", ignoreCase = true) -> InBody.process(token, treeBuilder)

            is Token.StartTag if token.name.equals("frameset", ignoreCase = true) -> {
                val attributes = token.attributes.map { (name, value) ->
                    Attribute(namespace = null, prefix = null, localName = name, value = value)
                }
                treeBuilder.insertElement("frameset", attributes = attributes)
                continueInCurrentMode()
            }

            is Token.EndTag if token.name.equals("frameset", ignoreCase = true) -> {
                if (treeBuilder.currentNode.localName == "html" &&
                    treeBuilder.currentNode.namespace == Namespaces.HTML &&
                    treeBuilder.elementStack.size == 1
                ) {
                    // Parse error. Ignore the token.
                    continueInCurrentMode()
                } else {
                    treeBuilder.popCurrentElement()
                    // TODO: Implement proper fragment case detection
                    // For now, we check if current node is no longer a frameset element
                    if (treeBuilder.currentNode.localName != "frameset" ||
                        treeBuilder.currentNode.namespace != Namespaces.HTML
                    ) {
                        switchTo(AfterFrameSet)
                    } else {
                        continueInCurrentMode()
                    }
                }
            }

            is Token.StartTag if token.name.equals("frame", ignoreCase = true) -> {
                val attributes = token.attributes.map { (name, value) ->
                    Attribute(namespace = null, prefix = null, localName = name, value = value)
                }
                treeBuilder.insertElement("frame", attributes = attributes)
                treeBuilder.popCurrentElement()
                // TODO: Acknowledge the token's self-closing flag, if it is set
                continueInCurrentMode()
            }

            is Token.StartTag if token.name.equals("noframes", ignoreCase = true) -> InHead.process(token, treeBuilder)

            is Token.EOF -> {
                if (treeBuilder.currentNode.localName != "html" ||
                    treeBuilder.currentNode.namespace != Namespaces.HTML ||
                    treeBuilder.elementStack.size != 1
                ) {
                    // Parse error
                }
                stopParsing()
            }

            else -> {
                // Parse error. Ignore the token.
                continueInCurrentMode()
            }
        }
    }
}
