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

    fun insertAtAppropriatePlace(node: TreeNode, overrideTarget: TreeNode? = null) {
        // Step 1: If there was an override target specified, then let target be the override target.
        // Otherwise, let the target be the current node.
        val target = overrideTarget ?: getCurrentNode()

        // Step 2: Determine the adjusted insertion location using the first matching steps:

        // If foster parenting is enabled and target is a table, tbody, tfoot, thead, or tr element
        if (fosterParenting.isEnabled &&
            target.namespace == Namespaces.HTML &&
            target.localName in setOf("table", "tbody", "tfoot", "thead", "tr")
        ) {
            insertWithFosterParenting(node)
            return
        }

        // Otherwise: Let adjusted insertion location be inside target, after its last child (if any)
        var adjustedInsertionParent = target

        // Step 3: If the adjusted insertion location is inside a template element,
        // let it instead be inside the template element's template contents, after its last child (if any)
        if (adjustedInsertionParent.namespace == Namespaces.HTML &&
            adjustedInsertionParent.localName == "template"
        ) {
            // TODO: When template content is implemented, use template content
            // For now, insert into the template element itself
        }

        // Step 4: Return the adjusted insertion location (we insert the node here)
        adjustedInsertionParent.appendChild(node)
    }

    private fun insertWithFosterParenting(node: TreeNode) {
        // Let the last template be the last template element in the stack of open elements, if any
        val lastTemplateIndex = stack.indexOfLastTagName("template")
        val lastTemplate = if (lastTemplateIndex >= 0) stack[lastTemplateIndex] else null

        // Let the last table be the last table element in the stack of open elements, if any
        val lastTableIndex = stack.indexOfLastTagName("table")
        val lastTable = if (lastTableIndex >= 0) stack[lastTableIndex] else null

        // If there is a last template, and either there is no last table, or there is one,
        // but the last template is lower (more recently added) than the last table in the stack of open elements
        if (lastTemplate != null && (lastTable == null || lastTemplateIndex > lastTableIndex)) {
            // Let adjusted insertion location be inside last template's template contents,
            // after its last child (if any)
            // TODO: When template content is implemented, insert into template content
            // For now, insert into the template element itself
            lastTemplate.appendChild(node)
            return
        }

        // If there is no last table, then let the adjusted insertion location be inside
        // the first element in the stack of open elements (the html element),
        // after its last child (if any) (fragment case)
        if (lastTable == null) {
            val firstElement = if (stack.size > 0) stack[0] else getDocument()
            firstElement.appendChild(node)
            return
        }

        // If the last table has a parent node, then let adjusted insertion location be
        // inside last table's parent node, immediately before last table
        val tableParent = lastTable.parent
        if (tableParent != null) {
            tableParent.insertBefore(node, lastTable)
            return
        }

        // Let the previous element be the element immediately above last table in the stack of open elements
        // Let adjusted insertion location be inside previous element, after its last child (if any)
        if (lastTableIndex > 0) {
            val previousElement = stack[lastTableIndex - 1]
            previousElement.appendChild(node)
            return
        }

        // Fallback (shouldn't happen in practice)
        lastTable.appendChild(node)
    }
}
