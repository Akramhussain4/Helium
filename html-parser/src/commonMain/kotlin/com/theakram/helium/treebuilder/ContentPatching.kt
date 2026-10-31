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

import com.theakram.helium.tree.NodeType
import com.theakram.helium.tree.ProcessingInstructionAttributes
import com.theakram.helium.tree.TreeNode

/**
 * Lets a template element with a for attribute replace the content between processing instruction markers,
 * for declarative out-of-order streaming.
 */
internal object ContentPatching {

    /**
     * Spec: https://html.spec.whatwg.org/multipage/scripting.html#prepare-content-patching
     */
    fun prepareContentPatching(scope: TreeNode, template: TreeNode): Boolean {
        // Steps 1-2:
        val markerName = checkNotNull(template.getAttribute("for")) { "The template must have a for attribute" }

        // Step 3:
        if (markerName.isEmpty()) return false

        // Step 4:
        val (start, end) = findMarkers(scope, markerName)

        // Step 5:
        if (start == null) return false

        // Steps 6-8:
        template.insertionTarget = start.parent
        template.insertionStartMarker = start
        template.insertionEndMarker = end

        // Step 9:
        if (start === end) return true

        // Steps 10-12:
        val removedNodes = mutableListOf<TreeNode>()
        var currentNode = start.nextSibling
        while (currentNode != null) {
            if (currentNode === end) break
            removedNodes.add(currentNode)
            currentNode = currentNode.nextSibling
        }

        // Step 13:
        for (node in removedNodes) {
            node.parent?.removeChild(node)
        }

        // Step 14:
        return true
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/scripting.html#find-markers
     */
    private fun findMarkers(scope: TreeNode, name: String): Pair<TreeNode?, TreeNode?> {
        // Step 1:
        for (descendant in descendants(scope)) {
            // Step 1.1:
            if (descendant.type != NodeType.PROCESSING_INSTRUCTION) continue
            // Step 1.2:
            if (ProcessingInstructionAttributes.get(descendant, "name") != name) continue
            // Step 1.3:
            if (descendant.name == "marker") return descendant to descendant
            // Step 1.4:
            if (descendant.name == "start") {
                val start = descendant
                var nestingLevel = 0
                var sibling = start.nextSibling
                while (sibling != null) {
                    if (sibling.type == NodeType.PROCESSING_INSTRUCTION) {
                        if (sibling.name == "start") nestingLevel++
                        if (sibling.name == "end") {
                            if (nestingLevel == 0) return start to sibling
                            nestingLevel--
                        }
                    }
                    sibling = sibling.nextSibling
                }
                return start to null
            }
        }
        // Step 2:
        return null to null
    }

    // The descendants of node, in tree order
    private fun descendants(node: TreeNode): Sequence<TreeNode> = sequence {
        for (child in node.children) {
            yield(child)
            yieldAll(descendants(child))
        }
    }
}
