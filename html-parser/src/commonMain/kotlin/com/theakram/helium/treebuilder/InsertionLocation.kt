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

import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.NodeType
import com.theakram.helium.tree.TreeNode

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#appropriate-place-for-inserting-a-node
 */
internal class InsertionLocation(
    private val stack: StackOfOpenElements,
    private val fosterParenting: FosterParenting,
    private val getCurrentNode: () -> TreeNode,
    private val getDocument: () -> TreeNode
) {

    /**
     * Insert a character while processing a token.
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#insert-a-character
     */
    fun insertCharacter(data: String, overrideTarget: TreeNode? = null) {
        // Step 1, 2:
        val location = getAdjustedInsertionLocation(overrideTarget)
        // Step 3:
        if (location.parent.type == NodeType.DOCUMENT) {
            return
        }
        // Step 4:
        val previousSibling = if (location.beforeNode != null) {
            location.beforeNode.previousSibling
        } else {
            location.parent.lastChild
        }
        if (previousSibling?.type == NodeType.TEXT) {
            previousSibling.data = (previousSibling.data ?: "") + data
        } else {
            // Step 5:
            val textNode = TreeNode.createText(data)
            if (location.beforeNode != null) {
                location.parent.insertBefore(textNode, location.beforeNode)
            } else {
                location.parent.appendChild(textNode)
            }
        }
    }

    fun insertAtAppropriatePlace(node: TreeNode, overrideTarget: TreeNode? = null) {
        val location = getAdjustedInsertionLocation(overrideTarget)
        if (location.beforeNode != null) {
            location.parent.insertBefore(node, location.beforeNode)
        } else {
            location.parent.appendChild(node)
        }
    }

    private data class AdjustedLocation(
        val parent: TreeNode,
        val beforeNode: TreeNode? = null
    )

    /**
     * Find the appropriate place for inserting a node.
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#appropriate-place-for-inserting-a-node
     */
    private fun getAdjustedInsertionLocation(overrideTarget: TreeNode? = null): AdjustedLocation {
        // Step 1:
        val target = overrideTarget ?: getCurrentNode()
        // Step 2:
        if (fosterParenting.isEnabled && TreeConstructionConstants.requiresFosterParenting(target)) {
            return getFosterParentingLocation()
        }
        // Otherwise:
        val adjustedInsertionParent = target
        // Step 3:
        if (adjustedInsertionParent.namespace == Namespaces.HTML &&
            adjustedInsertionParent.localName == "template"
        ) {
            // TODO: When template content is implemented, use template content
            // For now, insert into the template element itself
        }
        // Step 4:
        return AdjustedLocation(adjustedInsertionParent, null)
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#appropriate-place-for-inserting-a-node
     */
    private fun getFosterParentingLocation(): AdjustedLocation {
        // Step 1:
        val lastTemplateIndex = stack.indexOfLastTagName("template")
        val lastTemplate = if (lastTemplateIndex >= 0) stack[lastTemplateIndex] else null

        // Step 2:
        val lastTableIndex = stack.indexOfLastTagName("table")
        val lastTable = if (lastTableIndex >= 0) stack[lastTableIndex] else null

        // Step 3:
        if (lastTemplate != null && (lastTable == null || lastTemplateIndex > lastTableIndex)) {
            // Let adjusted insertion location be inside last template's template contents,
            // after its last child (if any)
            // TODO: When template content is implemented, insert into template content
            // For now, insert into the template element itself
            return AdjustedLocation(lastTemplate, null)
        }

        // Step 4:
        if (lastTable == null) {
            val firstElement = if (stack.size > 0) stack[0] else getDocument()
            return AdjustedLocation(firstElement, null)
        }

        // Step 5:
        val tableParent = lastTable.parent
        if (tableParent != null) {
            return AdjustedLocation(tableParent, lastTable)
        }

        // Step 6-7:
        if (lastTableIndex > 0) {
            val previousElement = stack[lastTableIndex - 1]
            return AdjustedLocation(previousElement, null)
        }

        // Fallback (shouldn't happen in practice)
        return AdjustedLocation(lastTable, null)
    }
}
