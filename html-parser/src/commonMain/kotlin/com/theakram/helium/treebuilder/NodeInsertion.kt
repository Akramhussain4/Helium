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

package com.theakram.helium.treebuilder

import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.NodeType
import com.theakram.helium.tree.TreeNode

/**
 * The parser has no sanitizer configuration, so the steps that apply one are not implemented.
 *
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#creating-and-inserting-nodes
 */
internal class NodeInsertion(
    private val stack: StackOfOpenElements,
    private val fosterParenting: FosterParenting,
    private val getCurrentNode: () -> TreeNode
) {

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#insertion-target-redirection-map
     */
    val insertionTargetRedirectionMap: MutableMap<TreeNode, InsertionLocation> = mutableMapOf()

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#appropriate-place-for-inserting-a-node
     */
    fun appropriatePlaceForInsertingNode(overrideTarget: TreeNode? = null): InsertionLocation {
        // Step 1:
        var targetParent = overrideTarget ?: getCurrentNode()
        // Step 2:
        var referenceChild: TreeNode? = null

        // Step 3:
        if (fosterParenting.isEnabled && TreeConstructionConstants.requiresFosterParenting(targetParent)) {
            // Step 3.1:
            val lastTemplateOrTableIndex = stack.toList().indexOfLast {
                it.namespace == Namespaces.HTML && (it.localName == "template" || it.localName == "table")
            }
            // Step 3.2:
            if (lastTemplateOrTableIndex == -1) {
                return InsertionLocation(stack[0], null)
            }
            val lastTemplateOrTable = stack[lastTemplateOrTableIndex]
            val lastTemplateOrTableParent = lastTemplateOrTable.parent
            when {
                // Step 3.3:
                lastTemplateOrTable.localName == "template" -> targetParent = lastTemplateOrTable

                // Step 3.4:
                lastTemplateOrTableParent != null -> {
                    targetParent = lastTemplateOrTableParent
                    referenceChild = lastTemplateOrTable
                }

                // Step 3.5:
                else -> targetParent = stack[lastTemplateOrTableIndex - 1]
            }
        }

        // Step 4:
        if (!targetParent.isTemplate) {
            return InsertionLocation(targetParent, referenceChild)
        }
        // Step 5:
        val patchInsertionTarget = targetParent.insertionTarget
        // Step 6:
        if (patchInsertionTarget == null) {
            return InsertionLocation(targetParent.templateContent!!, null)
        }
        // Step 7:
        val insertionEndMarker = targetParent.insertionEndMarker
        if (insertionEndMarker != null && insertionEndMarker.parent === patchInsertionTarget) {
            return InsertionLocation(patchInsertionTarget, insertionEndMarker)
        }
        // Step 8:
        return InsertionLocation(patchInsertionTarget, null)
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#adjusted-insertion-location
     */
    fun adjustedInsertionLocation(insertionLocation: InsertionLocation? = null): InsertionLocation {
        // Steps 1-2:
        val location = insertionLocation ?: appropriatePlaceForInsertingNode()
        // Step 3:
        val redirection = insertionTargetRedirectionMap[location.targetParent] ?: return location
        // Step 4:
        check(location.referenceChild == null) { "A redirected insertion location must not have a reference child" }
        // Step 5:
        return redirection
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#insert-an-element-at-the-adjusted-insertion-location
     */
    fun insertElementAtAdjustedInsertionLocation(element: TreeNode, overrideLocation: InsertionLocation? = null) {
        // Step 1:
        val (targetParent, referenceChild) = adjustedInsertionLocation(overrideLocation)
        // Step 2:
        if (element.parent != null ||
            element.isHostIncludingInclusiveAncestorOf(targetParent) ||
            (targetParent.type == NodeType.DOCUMENT && targetParent.children.any { it.type == NodeType.ELEMENT })
        ) {
            return
        }
        // Steps 3-7: Insert element into targetParent before referenceChild
        targetParent.insertBefore(element, referenceChild)
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#insert-a-character
     */
    fun insertCharacter(data: String) {
        // Step 2:
        val (targetParent, referenceChild) = adjustedInsertionLocation()
        // Step 3:
        if (targetParent.type == NodeType.DOCUMENT) {
            return
        }
        // Step 4:
        val previousSibling = if (referenceChild != null) referenceChild.previousSibling else targetParent.lastChild
        if (previousSibling?.type == NodeType.TEXT) {
            // Step 5:
            previousSibling.data = (previousSibling.data ?: "") + data
        } else {
            // Step 6:
            targetParent.insertBefore(TreeNode.createText(data), referenceChild)
        }
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#insert-a-comment
     */
    fun insertComment(data: String, insertionLocation: InsertionLocation? = null) {
        // Step 3:
        val (targetParent, referenceChild) = adjustedInsertionLocation(insertionLocation)
        // Steps 4-5:
        targetParent.insertBefore(TreeNode.createComment(data), referenceChild)
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#insert-a-processing-instruction
     */
    fun insertProcessingInstruction(target: String, data: String, insertionLocation: InsertionLocation? = null) {
        // Step 4:
        val (targetParent, referenceChild) = adjustedInsertionLocation(insertionLocation)
        // Steps 5-6:
        targetParent.insertBefore(TreeNode.createProcessingInstruction(target, data), referenceChild)
    }
}
