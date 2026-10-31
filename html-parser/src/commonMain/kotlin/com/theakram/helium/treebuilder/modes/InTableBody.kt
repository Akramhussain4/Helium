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
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-intbody
 */
internal object InTableBody : InsertionMode {

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.StartTag if token.name == "tr" -> {
                clearStackBackToTableBodyContext(treeBuilder)
                val attributes = token.attributes.map { (name, value) ->
                    Attribute(namespace = null, prefix = null, localName = name, value = value)
                }
                treeBuilder.insertElement("tr", attributes = attributes)
                switchTo(InRow)
            }

            is Token.StartTag if token.name in setOf("th", "td") -> {
                clearStackBackToTableBodyContext(treeBuilder)
                treeBuilder.insertElement("tr", attributes = emptyList())
                reprocessIn(InRow)
            }

            is Token.EndTag if token.name in setOf("tbody", "tfoot", "thead") -> {
                val tagName = token.name
                if (!treeBuilder.hasElementInTableScope(tagName)) {
                    // Parse error. Ignore the token.
                    continueInCurrentMode()
                } else {
                    clearStackBackToTableBodyContext(treeBuilder)
                    treeBuilder.popCurrentElement()
                    switchTo(InTable)
                }
            }

            is Token.StartTag if token.name in setOf(
                "caption", "col", "colgroup", "tbody", "tfoot", "thead"
            ) -> {
                if (!hasTableBodyElementInScope(treeBuilder)) {
                    // Parse error. Ignore the token.
                    continueInCurrentMode()
                } else {
                    clearStackBackToTableBodyContext(treeBuilder)
                    treeBuilder.popCurrentElement()
                    reprocessIn(InTable)
                }
            }

            is Token.EndTag if token.name == "table" -> {
                if (!hasTableBodyElementInScope(treeBuilder)) {
                    // Parse error. Ignore the token.
                    continueInCurrentMode()
                } else {
                    clearStackBackToTableBodyContext(treeBuilder)
                    treeBuilder.popCurrentElement()
                    reprocessIn(InTable)
                }
            }

            is Token.EndTag if token.name in setOf(
                "body", "caption", "col", "colgroup", "html", "td", "th", "tr"
            ) -> {
                // Parse error. Ignore the token.
                continueInCurrentMode()
            }

            else -> InTable.process(token, treeBuilder)
        }
    }

    private fun clearStackBackToTableBodyContext(treeBuilder: TreeBuilder) {
        while (treeBuilder.elementStack.isNotEmpty()) {
            val currentNode = treeBuilder.currentNode
            if (currentNode.namespace == Namespaces.HTML &&
                currentNode.localName in setOf("tbody", "tfoot", "thead", "template", "html")
            ) {
                break
            }
            treeBuilder.popCurrentElement()
        }
    }

    private fun hasTableBodyElementInScope(treeBuilder: TreeBuilder): Boolean {
        return treeBuilder.hasElementInTableScope("tbody") ||
                treeBuilder.hasElementInTableScope("thead") ||
                treeBuilder.hasElementInTableScope("tfoot")
    }
}
