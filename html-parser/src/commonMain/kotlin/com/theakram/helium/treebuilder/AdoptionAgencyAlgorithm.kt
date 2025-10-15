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
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#adoption-agency-algorithm
 */
internal class AdoptionAgencyAlgorithm(
    private val stack: StackOfOpenElements,
    private val activeFormattingElements: ActiveFormattingElements,
    private val scopeChecker: ScopeChecker,
    private val treeBuilder: TreeBuilder
) {

    fun run(tagName: String) {
        // Step 1:
        val subject = tagName

        // Step 2:
        val currentNode = stack.currentNode
        if (currentNode != null &&
            currentNode.namespace == Namespaces.HTML &&
            currentNode.localName?.equals(subject, ignoreCase = true) == true &&
            !activeFormattingElements.contains(currentNode)) {
            stack.pop()
            return
        }

        // Step 3:
        var outerLoopCounter = 0

        // Step 4:
        while (true) {
            // Step 4.1:
            if (outerLoopCounter >= 8) {
                return
            }

            // Step 4.2:
            outerLoopCounter++

            // Step 4.3:
            val formattingElement = activeFormattingElements.findLastElementByTagName(subject) ?: return

            // Step 4.4:
            if (!stack.contains(formattingElement)) {
                // Parse error: formatting element not in stack
                activeFormattingElements.remove(formattingElement)
                return
            }

            // Step 4.5:
            if (!scopeChecker.isElementInScope(formattingElement)) {
                // Parse error:
                return
            }

            // Step 4.6:
            @Suppress("ControlFlowWithEmptyBody")
            if (stack.currentNode !== formattingElement) {
                // Parse error would be reported here in a full implementation
            }

            // Step 4.7:
            val furthestBlock = findFurthestBlock(formattingElement)

            // Step 4.8:
            if (furthestBlock == null) {
                stack.popUntilElement(formattingElement)
                activeFormattingElements.remove(formattingElement)
                return
            }

            // Step 4.9:
            val formattingElementIndex = stack.indexOf(formattingElement)
            if (formattingElementIndex <= 0) {
                // This shouldn't happen in a well-formed document, but if it does,
                // we cannot proceed with this iteration
                return
            }
            val commonAncestor = stack[formattingElementIndex - 1]

            // Step 4.10:
            var bookmark = formattingElement

            // Step 4.11:
            var node: TreeNode = furthestBlock
            var lastNode: TreeNode = furthestBlock

            // Step 4.12:
            var innerLoopCounter = 0
            var nodeAboveInStack: TreeNode? = null

            // Step 4.13:
            while (true) {
                // Step 4.13.1:
                innerLoopCounter++

                // Step 4.13.2:
                val nodeIndex = stack.indexOf(node)
                if (nodeIndex > 0) {
                    nodeAboveInStack = stack[nodeIndex - 1]
                    node = nodeAboveInStack
                } else if (nodeAboveInStack != null) {
                    node = nodeAboveInStack
                    val newIndex = stack.indexOf(node)
                    if (newIndex > 0) {
                        nodeAboveInStack = stack[newIndex - 1]
                    }
                } else {
                    break
                }

                // Step 4.13.3:
                if (node === formattingElement) {
                    break
                }

                // Step 4.13.4:
                if (innerLoopCounter > 3 && activeFormattingElements.contains(node)) {
                    activeFormattingElements.remove(node)
                }

                // Step 4.13.5:
                if (!activeFormattingElements.contains(node)) {
                    stack.remove(node)
                    continue
                }

                // Step 4.13.6:
                val newElement = TreeNode.createElement(
                    localName = node.localName ?: "",
                    namespace = node.namespace ?: Namespaces.HTML,
                    attributes = node.attributes.toList()
                )
                activeFormattingElements.replace(node, newElement)
                stack.replace(node, newElement)
                node = newElement

                // Step 4.13.7:
                if (lastNode === furthestBlock) {
                    bookmark = node
                }

                // Step 4.13.8:
                lastNode.parent?.removeChild(lastNode)
                node.appendChild(lastNode)

                // Step 4.13.9:
                lastNode = node
            }

            // Step 4.14:
            lastNode.parent?.removeChild(lastNode)
            treeBuilder.insertAtAppropriatePlace(lastNode, commonAncestor)

            // Step 4.15:
            val newFormattingElement = TreeNode.createElement(
                localName = formattingElement.localName ?: "",
                namespace = formattingElement.namespace ?: Namespaces.HTML,
                attributes = formattingElement.attributes.toList()
            )

            // Step 4.16:
            val childrenToMove = furthestBlock.children.toList()
            childrenToMove.forEach { child ->
                furthestBlock.removeChild(child)
                newFormattingElement.appendChild(child)
            }

            // Step 4.17:
            furthestBlock.appendChild(newFormattingElement)

            // Step 4.18:
            activeFormattingElements.remove(formattingElement)
            activeFormattingElements.insertAfter(bookmark, newFormattingElement)

            // Step 4.19:
            stack.remove(formattingElement)
            stack.insertAfter(furthestBlock, newFormattingElement)
        }
    }

    private fun findFurthestBlock(formattingElement: TreeNode): TreeNode? {
        val formattingIndex = stack.indexOf(formattingElement)
        if (formattingIndex < 0) return null

        // Look for special elements above the formatting element in the stack
        for (i in (formattingIndex + 1) until stack.size) {
            val element = stack[i]
            if (TreeConstructionConstants.isSpecialElement(element)) {
                return element
            }
        }
        return null
    }
}
