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
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.modes.helpers.InBodyHelpers
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-incell
 */
internal object InCell : InsertionMode {

    private val CELLS = setOf("td", "th")

    private val CELL_CLOSING_START_TAGS = setOf(
        "caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr"
    )

    private val IGNORED_END_TAGS = setOf("body", "caption", "col", "colgroup", "html")

    private val CELL_CLOSING_END_TAGS = setOf("table", "tbody", "tfoot", "thead", "tr")

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.EndTag if token.name in CELLS -> {
                val tagName = token.name
                if (!treeBuilder.hasElementInTableScope(tagName)) {
                    // This is a parse error; ignore the token.
                    treeBuilder.parseError()
                    return continueInCurrentMode()
                }
                // Step 1: Generate implied end tags.
                InBodyHelpers.generateImpliedEndTags(treeBuilder)

                // Step 2: Now, if the current node is not an HTML element with the same tag name as the token, then
                // this is a parse error.
                val currentNode = treeBuilder.currentNode
                if (currentNode.namespace != Namespaces.HTML || currentNode.localName != tagName) {
                    treeBuilder.parseError()
                }

                // Step 3: Pop elements from the stack of open elements until an HTML element with the same tag name
                // as the token has been popped from the stack.
                treeBuilder.popUntil(tagName)

                // Step 4: Clear the list of active formatting elements up to the last marker.
                treeBuilder.activeFormattingElements.clearToLastMarker()

                // Step 5: Switch the insertion mode to "in row".
                switchTo(InRow)
            }

            is Token.StartTag if token.name in CELL_CLOSING_START_TAGS -> {
                // Assert: the stack of open elements has a td or th element in table scope.
                check(CELLS.any { treeBuilder.hasElementInTableScope(it) }) {
                    "The stack of open elements must have a td or th element in table scope"
                }
                closeTheCell(treeBuilder)
                reprocessIn(InRow)
            }

            is Token.EndTag if token.name in IGNORED_END_TAGS -> {
                // Parse error. Ignore the token.
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            is Token.EndTag if token.name in CELL_CLOSING_END_TAGS -> {
                if (!treeBuilder.hasElementInTableScope(token.name)) {
                    // This is a parse error; ignore the token.
                    treeBuilder.parseError()
                    continueInCurrentMode()
                } else {
                    closeTheCell(treeBuilder)
                    reprocessIn(InRow)
                }
            }

            else -> InBody.process(token, treeBuilder)
        }
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#close-the-cell
     *
     * The caller switches the insertion mode to "in row" (step 5) together with reprocessing the token.
     */
    private fun closeTheCell(treeBuilder: TreeBuilder) {
        // Step 1: Generate implied end tags.
        InBodyHelpers.generateImpliedEndTags(treeBuilder)

        // Step 2: If the current node is not now a td element or a th element, then this is a parse error.
        if (!isCell(treeBuilder.currentNode)) {
            treeBuilder.parseError()
        }

        // Step 3: Pop elements from the stack of open elements until a td element or a th element has been popped
        // from the stack.
        do {
            val popped = checkNotNull(treeBuilder.popCurrentElement()) {
                "The stack of open elements has a td or th element when the cell is closed"
            }
        } while (!isCell(popped))

        // Step 4: Clear the list of active formatting elements up to the last marker.
        treeBuilder.activeFormattingElements.clearToLastMarker()
    }

    private fun isCell(node: TreeNode): Boolean = node.namespace == Namespaces.HTML && node.localName in CELLS
}
