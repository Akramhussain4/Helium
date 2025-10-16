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
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-intable
 */
internal object InTable : InsertionMode {

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character if (treeBuilder.currentNode.namespace == Namespaces.HTML &&
                    treeBuilder.currentNode.localName in setOf(
                "table", "tbody", "template", "tfoot", "thead", "tr"
            )) -> {
                treeBuilder.pendingTableCharacterTokens.clear()
                treeBuilder.saveOriginalInsertionMode()
                reprocessIn(InTableText)
            }

            is Token.Comment -> {
                treeBuilder.insertComment(token.data)
                continueInCurrentMode()
            }

            is Token.DocType -> {
                // Parse error. Ignore the token.
                continueInCurrentMode()
            }

            is Token.StartTag -> {
                val tagName = token.name.lowercase()
                val attributes = token.attributes.map { (name, value) ->
                    Attribute(namespace = null, prefix = null, localName = name, value = value)
                }

                when (tagName) {
                    "caption" -> {
                        clearStackBackToTableContext(treeBuilder)
                        treeBuilder.activeFormattingElements.insertMarker()
                        treeBuilder.insertElement("caption", attributes = attributes)
                        switchTo(InCaption)
                    }

                    "colgroup" -> {
                        clearStackBackToTableContext(treeBuilder)
                        treeBuilder.insertElement("colgroup", attributes = attributes)
                        switchTo(InColumnGroup)
                    }

                    "col" -> {
                        clearStackBackToTableContext(treeBuilder)
                        treeBuilder.insertElement("colgroup", attributes = emptyList())
                        reprocessIn(InColumnGroup)
                    }

                    "tbody", "tfoot", "thead" -> {
                        clearStackBackToTableContext(treeBuilder)
                        treeBuilder.insertElement(tagName, attributes = attributes)
                        switchTo(InTableBody)
                    }

                    "td", "th", "tr" -> {
                        clearStackBackToTableContext(treeBuilder)
                        treeBuilder.insertElement("tbody", attributes = emptyList())
                        reprocessIn(InTableBody)
                    }

                    "table" -> {
                        // Parse error
                        if (!treeBuilder.hasElementInTableScope("table")) {
                            continueInCurrentMode()
                        } else {
                            treeBuilder.popUntil("table")
                            // Reset the insertion mode appropriately
                            treeBuilder.resetInsertionModeAppropriately()
                            switchTo(treeBuilder.currentMode)
                        }
                    }

                    "style", "script", "template" -> InHead.process(token, treeBuilder)

                    "input" -> {
                        val typeAttr = attributes.find { it.localName.equals("type", ignoreCase = true) }
                        val isHidden = typeAttr?.value?.equals("hidden", ignoreCase = true) == true
                        if (!isHidden) {
                            handleAnythingElse(token, treeBuilder)
                        } else {
                            // Parse error
                            treeBuilder.insertElement("input", attributes = attributes)
                            treeBuilder.popCurrentElement()
                            // TODO: Acknowledge the token's self-closing flag, if it is set
                            continueInCurrentMode()
                        }
                    }

                    "form" -> {
                        // Parse error
                        val hasTemplateInStack = treeBuilder.elementStack.any {
                            it.localName == "template" && it.namespace == Namespaces.HTML
                        }
                        if (hasTemplateInStack || treeBuilder.formElement != null) {
                            continueInCurrentMode()
                        } else {
                            val formElement = treeBuilder.insertElement("form", attributes = attributes)
                            treeBuilder.formElement = formElement
                            treeBuilder.popCurrentElement()
                            continueInCurrentMode()
                        }
                    }

                    else -> handleAnythingElse(token, treeBuilder)
                }
            }

            is Token.EndTag -> {
                val tagName = token.name.lowercase()

                when (tagName) {
                    "table" -> {
                        if (!treeBuilder.hasElementInTableScope("table")) {
                            // Parse error
                            continueInCurrentMode()
                        } else {
                            treeBuilder.popUntil("table")
                            // Reset the insertion mode appropriately
                            treeBuilder.resetInsertionModeAppropriately()
                            switchTo(treeBuilder.currentMode)
                        }
                    }

                    "body", "caption", "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr" -> {
                        // Parse error. Ignore the token.
                        continueInCurrentMode()
                    }

                    "template" -> InHead.process(token, treeBuilder)

                    else -> handleAnythingElse(token, treeBuilder)
                }
            }

            is Token.EOF -> InBody.process(token, treeBuilder)

            else -> handleAnythingElse(token, treeBuilder)
        }
    }

    private fun handleAnythingElse(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        // Parse error
        treeBuilder.enableFosterParenting()
        val result = InBody.process(token, treeBuilder)
        treeBuilder.disableFosterParenting()
        return result
    }

    private fun clearStackBackToTableContext(treeBuilder: TreeBuilder) {
        while (true) {
            val currentNode = treeBuilder.currentNode
            if (currentNode.namespace == Namespaces.HTML &&
                currentNode.localName in setOf("table", "template", "html")
            ) {
                break
            }
            treeBuilder.popCurrentElement()
        }
    }
}
