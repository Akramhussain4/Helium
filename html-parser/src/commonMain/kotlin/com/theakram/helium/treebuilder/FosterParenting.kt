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
            // No table element - insert into the current node
            // This shouldn't happen in normal parsing but handle gracefully
            val current = stack.currentNode
            requireNotNull(current) { "No current node available for foster parenting" }
            current.appendChild(element)
            return
        }

        val lastTable = stack[lastTableIndex]
        val tableParent = lastTable.parent

        if (tableParent != null) {
            // Table has a parent - insert element before the table
            tableParent.insertBefore(element, lastTable)
        } else {
            // Table has no parent - insert into the element immediately before the table in the stack
            if (lastTableIndex > 0) {
                val previousElement = stack[lastTableIndex - 1]
                previousElement.appendChild(element)
            } else {
                // Table is at the root of the stack - insert into it as a last resort
                // This shouldn't happen in practice
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
            // Table has a parent - insert before the table, merging with the previous text if possible
            val previousSibling = lastTable.previousSibling
            if (previousSibling?.type == NodeType.TEXT) {
                // Merge with existing text node
                previousSibling.data = (previousSibling.data ?: "") + data
            } else {
                // Create a new text node before the table
                val textNode = TreeNode.createText(data)
                tableParent.insertBefore(textNode, lastTable)
            }
        } else {
            // Table has no parent - insert into the element immediately before the table
            if (lastTableIndex > 0) {
                val previousElement = stack[lastTableIndex - 1]
                appendOrMergeText(previousElement, data)
            } else {
                // Table is at the root - insert into the table as a last resort
                val textNode = TreeNode.createText(data)
                lastTable.appendChild(textNode)
            }
        }
    }

    private fun insertTextIntoCurrentNode(data: String) {
        val currentNode = stack.currentNode
        requireNotNull(currentNode) { "No current node available for text insertion" }
        appendOrMergeText(currentNode, data)
    }

    private fun appendOrMergeText(parent: TreeNode, data: String) {
        val lastChild = parent.lastChild

        if (lastChild?.type == NodeType.TEXT) {
            // Merge with existing text node
            lastChild.data = (lastChild.data ?: "") + data
        } else {
            // Create a new text node
            val textNode = TreeNode.createText(data)
            parent.appendChild(textNode)
        }
    }
}
