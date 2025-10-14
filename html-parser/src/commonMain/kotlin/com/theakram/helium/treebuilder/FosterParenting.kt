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

package com.theakram.helium.treebuilder

import com.theakram.helium.tree.NodeType
import com.theakram.helium.tree.TreeNode

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#foster-parent
 */
internal class FosterParenting(private val stack: StackOfOpenElements) {

    var isEnabled: Boolean = false
        private set

    fun enable() {
        isEnabled = true
    }

    fun disable() {
        isEnabled = false
    }

    fun fosterParentElement(element: TreeNode) {
        // Find the last table element in the stack of open elements
        val lastTableIndex = stack.indexOfLastTagName("table")

        if (lastTableIndex == -1) {
            // No table element - insert into the current node (shouldn't happen in practice)
            stack.currentNode?.appendChild(element)
            return
        }

        val lastTable = stack[lastTableIndex]

        // If the table has a parent node, insert before the table
        val tableParent = lastTable.parent
        if (tableParent != null) {
            tableParent.insertBefore(element, lastTable)
        } else {
            // Table has no parent - insert into the element before the table in the stack
            if (lastTableIndex > 0) {
                val previousElement = stack[lastTableIndex - 1]
                previousElement.appendChild(element)
            } else {
                // Table is at the bottom of the stack - insert into it (shouldn't happen)
                lastTable.appendChild(element)
            }
        }
    }

    fun fosterParentText(data: String) {
        // Find the last table element in the stack of open elements
        val lastTableIndex = stack.indexOfLastTagName("table")

        if (lastTableIndex == -1) {
            // No table element - insert into current node
            insertTextIntoCurrentNode(data)
            return
        }

        val lastTable = stack[lastTableIndex]
        val tableParent = lastTable.parent

        if (tableParent != null) {
            // Insert before the table, merging with the previous text node if possible
            val previousSibling = lastTable.previousSibling
            if (previousSibling?.type == NodeType.TEXT) {
                previousSibling.data = (previousSibling.data ?: "") + data
            } else {
                val textNode = TreeNode.createText(data)
                tableParent.insertBefore(textNode, lastTable)
            }
        } else {
            // Table has no parent - insert into the element before the table in the stack
            if (lastTableIndex > 0) {
                val previousElement = stack[lastTableIndex - 1]
                val lastChild = previousElement.lastChild
                if (lastChild?.type == NodeType.TEXT) {
                    lastChild.data = (lastChild.data ?: "") + data
                } else {
                    val textNode = TreeNode.createText(data)
                    previousElement.appendChild(textNode)
                }
            } else {
                // Table is at the bottom-insert into it
                val textNode = TreeNode.createText(data)
                lastTable.appendChild(textNode)
            }
        }
    }

    private fun insertTextIntoCurrentNode(data: String) {
        val currentNode = stack.currentNode ?: return
        val lastChild = currentNode.lastChild

        if (lastChild?.type == NodeType.TEXT) {
            lastChild.data = (lastChild.data ?: "") + data
        } else {
            val textNode = TreeNode.createText(data)
            currentNode.appendChild(textNode)
        }
    }
}
