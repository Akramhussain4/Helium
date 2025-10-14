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
        // Step 1: Let subject be token's tag name
        val subject = tagName

        // Step 2: If the current node is an HTML element whose tag name is subject,
        // and the current node is not in the list of active formatting elements,
        // then pop the current node off the stack of open elements and return.
        val currentNode = stack.currentNode
        if (currentNode != null &&
            currentNode.namespace == Namespaces.HTML &&
            currentNode.localName?.equals(subject, ignoreCase = true) == true &&
            !activeFormattingElements.contains(currentNode)) {
            stack.pop()
            return
        }

        // Step 3: Let the outer loop counter be 0
        var outerLoopCounter = 0

        // Step 4: While true (outer loop)
        while (true) {
            // Step 4.1: If the outer loop counter is greater than or equal to 8, return
            if (outerLoopCounter >= 8) {
                return
            }

            // Step 4.2: Increment outer loop counter by 1
            outerLoopCounter++

            // Step 4.3: Let a formatting element be the last element in the list of active
            // formatting elements that is between the end of the list and the last marker in the list,
            // and has the tag name subject
            val formattingElement = activeFormattingElements.findLastElementByTagName(subject)

            // If there is no such element, return and instead act as "any other end tag"
            if (formattingElement == null) {
                return
            }

            // Step 4.4: If a formatting element is not in the stack of open elements,
            // this is a parse error; remove the element from the list, and return
            if (!stack.contains(formattingElement)) {
                // Parse error: formatting element not in stack
                activeFormattingElements.remove(formattingElement)
                return
            }

            // Step 4.5: If a formatting element is in the stack of open elements,
            // but the element is not in scope, this is a parse error; return
            if (!scopeChecker.isElementInScope(formattingElement)) {
                // Parse error: formatting element not in scope
                return
            }

            // Step 4.6: If a formatting element is not the current node, this is a parse error.
            // (But do not return.)
            // Note: We don't track parse errors yet, so we just continue to step 4.7
            @Suppress("ControlFlowWithEmptyBody")
            if (stack.currentNode !== formattingElement) {
                // Parse error would be reported here in a full implementation
            }

            // Step 4.7: Let the furthest block be the topmost node in the stack of open elements
            // that is lower in the stack than a formatting element and is a special element
            val furthestBlock = findFurthestBlock(formattingElement)

            // Step 4.8: If there is no furthest block, pop all nodes from the bottom of the
            // stack up to and including a formatting element, remove a formatting element from
            // active formatting elements, and return
            if (furthestBlock == null) {
                stack.popUntilElement(formattingElement)
                activeFormattingElements.remove(formattingElement)
                return
            }

            // Step 4.9: Let a common ancestor be the element immediately above formatting element
            // in the stack of open elements
            val formattingElementIndex = stack.indexOf(formattingElement)
            val commonAncestor = if (formattingElementIndex > 0) {
                stack[formattingElementIndex - 1]
            } else {
                // This shouldn't happen in practice; treat as a document
                continue
            }

            // Step 4.10: Let a bookmark note the position of the formatting element in the list
            // of active formatting elements relative to the elements on either side of it
            var bookmark = formattingElement

            // Step 4.11: Let node and last node be the furthest block
            var node: TreeNode = furthestBlock
            var lastNode: TreeNode = furthestBlock

            // Step 4.12: Let the inner loop counter be 0
            var innerLoopCounter = 0

            // Track the node above for when nodes get removed from the stack
            var nodeAboveInStack: TreeNode? = null

            // Step 4.13: While true (inner loop)
            while (true) {
                // Step 4.13.1: Increment inner loop counter by 1
                innerLoopCounter++

                // Step 4.13.2: Let the node be the element immediately above node in the stack
                // of open elements, or if the node is no longer in the stack (e.g., because it
                // got removed by this algorithm), the element that was immediately above node
                // in the stack of open elements before the node was removed
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

                // Step 4.13.3: If node is formatting element, break
                if (node === formattingElement) {
                    break
                }

                // Step 4.13.4: If the inner loop counter is greater than 3 and a node is in the
                // list of active formatting elements, remove the node from the list
                if (innerLoopCounter > 3 && activeFormattingElements.contains(node)) {
                    activeFormattingElements.remove(node)
                }

                // Step 4.13.5: If a node is not in the list of active formatting elements,
                // remove the node from the stack of open elements and continue
                if (!activeFormattingElements.contains(node)) {
                    stack.remove(node)
                    continue
                }

                // Step 4.13.6: Create an element for the token for which the element node
                // was created, with a common ancestor as the intended parent
                val newElement = TreeNode.createElement(
                    localName = node.localName ?: "",
                    namespace = node.namespace ?: Namespaces.HTML,
                    attributes = node.attributes.toList()
                )

                // Replace the entry for a node in the list of active formatting elements
                // with an entry for the new element
                activeFormattingElements.replace(node, newElement)

                // Replace the entry for a node in the stack of open elements with
                // an entry for the new element
                stack.replace(node, newElement)

                // Let the node be the new element
                node = newElement

                // Step 4.13.7: If the last node is the furthest block, move the bookmark to be
                // immediately after the new node in the list of active formatting elements
                if (lastNode === furthestBlock) {
                    bookmark = node
                }

                // Step 4.13.8: Append last node to node
                lastNode.parent?.removeChild(lastNode)
                node.appendChild(lastNode)

                // Step 4.13.9: Set the last node to node
                lastNode = node
            }

            // Step 4.14: Insert whatever last node ended up being in the previous step at
            // the appropriate place for inserting a node, but using common ancestor as the
            // override target
            lastNode.parent?.removeChild(lastNode)
            treeBuilder.insertAtAppropriatePlace(lastNode, commonAncestor)

            // Step 4.15: Create an element for the token for which a formatting element was
            // created, with furthest block as the intended parent
            val newFormattingElement = TreeNode.createElement(
                localName = formattingElement.localName ?: "",
                namespace = formattingElement.namespace ?: Namespaces.HTML,
                attributes = formattingElement.attributes.toList()
            )

            // Step 4.16: Take all of the child nodes of the furthest block and append them to
            // the element created in the last step
            val childrenToMove = furthestBlock.children.toList()
            childrenToMove.forEach { child ->
                furthestBlock.removeChild(child)
                newFormattingElement.appendChild(child)
            }

            // Step 4.17: Append that new element to the furthest block
            furthestBlock.appendChild(newFormattingElement)

            // Step 4.18: Remove a formatting element from the list of active formatting elements,
            // and insert the new element into the list immediately after the bookmark
            activeFormattingElements.remove(formattingElement)
            activeFormattingElements.insertAfter(bookmark, newFormattingElement)

            // Step 4.19: Remove a formatting element from the stack of open elements, and insert
            // the new element into the stack immediately below furthest block
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
