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
import com.theakram.helium.treebuilder.modes.helpers.EndTagHandler

/**
 * The parser has no sanitizer configuration, so every sanitizer action is "Keep", and pendingRedirects stays empty.
 *
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
            currentNode.localName == subject &&
            !activeFormattingElements.contains(currentNode)
        ) {
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
            val formattingElement = activeFormattingElements.findLastElementByTagName(subject)
            if (formattingElement == null) {
                EndTagHandler.handleAnyOtherEndTag(treeBuilder, subject)
                return
            }

            // Step 4.4:
            if (!stack.contains(formattingElement)) {
                // Parse error
                activeFormattingElements.remove(formattingElement)
                return
            }

            // Step 4.5:
            if (!scopeChecker.isElementInScope(formattingElement)) {
                // Parse error
                return
            }

            // Step 4.6:
            if (stack.currentNode !== formattingElement) {
                // Parse error
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
            val commonAncestor = stack[stack.indexOf(formattingElement) - 1]

            // Step 4.10: The bookmark is at formattingElement's position until it is moved after a new node
            var bookmarkAfter: TreeNode? = null

            // Step 4.11:
            var node: TreeNode = furthestBlock
            var lastNode: TreeNode = furthestBlock
            // Removing node from the stack doesn't move the elements above it, so its index keeps finding them
            var nodeIndex = stack.indexOf(furthestBlock)

            // Steps 4.12-4.13: pendingRedirects stays empty without a sanitizer configuration

            // Step 4.14:
            var innerLoopCounter = 0

            // Step 4.15:
            var bookmarkMoved = false

            // Step 4.16:
            while (true) {
                // Step 4.16.1:
                innerLoopCounter++

                // Step 4.16.2:
                nodeIndex--
                node = stack[nodeIndex]

                // Step 4.16.3:
                if (node === formattingElement) {
                    break
                }

                // Step 4.16.4:
                if (innerLoopCounter > 3 && activeFormattingElements.contains(node)) {
                    activeFormattingElements.remove(node)
                }

                // Step 4.16.5:
                if (!activeFormattingElements.contains(node)) {
                    stack.remove(node)
                    continue
                }

                // Step 4.16.6:
                val newElement = createElementLike(node)
                activeFormattingElements.replace(node, newElement)
                stack.replace(node, newElement)
                node = newElement

                // Step 4.16.7:
                if (!bookmarkMoved) {
                    bookmarkAfter = node
                    bookmarkMoved = true
                }

                // Steps 4.16.8-4.16.11: With nodeAction "Keep", append lastNode to node
                node.appendChild(lastNode)
                lastNode = node
            }

            // Step 4.17:
            val insertionLocation = treeBuilder.appropriatePlaceForInsertingNode(commonAncestor)

            // Step 4.18:
            val (target, refNode) = treeBuilder.adjustedInsertionLocation(insertionLocation)

            // Step 4.19: pendingRedirects is empty

            // Step 4.20:
            lastNode.parent?.removeChild(lastNode)

            // Step 4.21:
            if (lastNode.parent == null &&
                !lastNode.isHostIncludingInclusiveAncestorOf(target) &&
                (target.type != NodeType.DOCUMENT || target.children.none { it.type == NodeType.ELEMENT }) &&
                (refNode == null || refNode.parent === target)
            ) {
                target.insertBefore(lastNode, refNode)
            }

            // Step 4.22:
            val newElement = createElementLike(formattingElement)

            // Step 4.23:
            if (bookmarkAfter == null) {
                activeFormattingElements.replace(formattingElement, newElement)
            } else {
                activeFormattingElements.remove(formattingElement)
                activeFormattingElements.insertAfter(bookmarkAfter, newElement)
            }

            // Step 4.24:
            stack.remove(formattingElement)
            stack.insertAfter(furthestBlock, newElement)

            // Steps 4.25-4.27: With sanitizerAction "Keep", move furthestBlock's children into newElement
            furthestBlock.children.toList().forEach { child -> newElement.appendChild(child) }
            val (newElementParent, newElementRefNode) =
                treeBuilder.adjustedInsertionLocation(InsertionLocation(furthestBlock, null))
            newElementParent.insertBefore(newElement, newElementRefNode)

            // Steps 4.28-4.29:
            var belowIndex = stack.indexOf(newElement) + 1
            while (belowIndex < stack.size && treeBuilder.insertionTargetRedirectionMap.containsKey(stack[belowIndex])) {
                treeBuilder.insertionTargetRedirectionMap[stack[belowIndex]] =
                    treeBuilder.adjustedInsertionLocation(InsertionLocation(newElement, null))
                belowIndex++
            }
        }
    }

    // The topmost node lower in the stack than formattingElement that is in the special category
    private fun findFurthestBlock(formattingElement: TreeNode): TreeNode? {
        for (i in (stack.indexOf(formattingElement) + 1) until stack.size) {
            if (TreeConstructionConstants.isSpecialElement(stack[i])) {
                return stack[i]
            }
        }
        return null
    }

    // Creates an element for the token for which element was created
    private fun createElementLike(element: TreeNode): TreeNode = TreeNode.createElement(
        localName = element.localName!!,
        namespace = Namespaces.HTML,
        attributes = element.attributes.toList()
    )
}
