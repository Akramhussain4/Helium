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
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.modes.inbody.InBodyHelpers
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-incell
 */
internal object InCell : InsertionMode {

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.EndTag if token.name.lowercase() in setOf("td", "th") -> {
                val tagName = token.name.lowercase()
                if (!treeBuilder.hasElementInTableScope(tagName)) {
                    continueInCurrentMode()
                } else {
                    InBodyHelpers.generateImpliedEndTags(treeBuilder)
                    if (treeBuilder.currentNode.localName != tagName ||
                        treeBuilder.currentNode.namespace != Namespaces.HTML
                    ) {
                        // Parse error
                    }
                    treeBuilder.popUntil(tagName)
                    treeBuilder.activeFormattingElements.clearToLastMarker()
                    switchTo(InRow)
                }
            }

            is Token.StartTag if token.name.lowercase() in setOf(
                "caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr"
            ) -> {
                assert(
                    value = treeBuilder.hasElementInTableScope("td") ||
                            treeBuilder.hasElementInTableScope("th")
                ) { "The stack of open elements must have a td or th element in table scope" }
                closeTheCell(treeBuilder)
                reprocessIn(InRow)
            }

            is Token.EndTag if token.name.lowercase() in setOf(
                "body", "caption", "col", "colgroup", "html"
            ) -> {
                // Parse error. Ignore the token.
                continueInCurrentMode()
            }

            is Token.EndTag if token.name.lowercase() in setOf(
                "table", "tbody", "tfoot", "thead", "tr"
            ) -> {
                val tagName = token.name.lowercase()
                if (!treeBuilder.hasElementInTableScope(tagName)) {
                    // Parse error. Ignore the token.
                    continueInCurrentMode()
                } else {
                    closeTheCell(treeBuilder)
                    reprocessIn(InRow)
                }
            }

            else -> InBody.process(token, treeBuilder)
        }
    }

    private fun closeTheCell(treeBuilder: TreeBuilder): InsertionModeTransition {
        InBodyHelpers.generateImpliedEndTags(treeBuilder)

        val currentNode = treeBuilder.currentNode
        if (currentNode.namespace != Namespaces.HTML ||
            currentNode.localName !in setOf("td", "th")
        ) {
            // Parse error
        }
        while (treeBuilder.elementStack.isNotEmpty()) {
            val node = treeBuilder.currentNode
            treeBuilder.popCurrentElement()
            if (node.namespace == Namespaces.HTML && node.localName in setOf("td", "th")) {
                break
            }
        }
        treeBuilder.activeFormattingElements.clearToLastMarker()
        return switchTo(InRow)
    }
}
