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
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#reconstruct-the-active-formatting-elements
 */
internal class FormattingElementReconstructor(
    private val stack: StackOfOpenElements,
    private val activeFormattingElements: ActiveFormattingElements,
    private val getCurrentNode: () -> TreeNode
) {

    fun reconstruct() {
        // Step 1: If there are no entries in the list of active formatting elements,
        // then there is nothing to reconstruct; stop this algorithm
        if (activeFormattingElements.isEmpty) return

        // Step 2: If the last entry is a marker, or if it is an element that is in the
        // stack of open elements, then there is nothing to reconstruct; stop
        if (activeFormattingElements.isLastEntryMarker()) return

        val lastElement = activeFormattingElements.getLastElement()
        if (lastElement != null && stack.contains(lastElement)) return

        // Step 3: Let entry be the last element in the list
        // Get all entries (elements and markers as null)
        val entries = activeFormattingElements.getEntriesForReconstruction()
        var entryIndex = entries.lastIndex

        // Step 4-6: Rewind - find the first entry that needs reconstruction
        // We rewind until we hit a marker (null) or an element in the stack, or the beginning
        while (entryIndex > 0) {
            val previousEntry = entries[entryIndex - 1]

            // Step 6: If an entry is neither a marker nor an element in the stack, continue rewinding
            if (previousEntry == null) {
                // Hit a marker - stop rewinding
                break
            } else if (stack.contains(previousEntry)) {
                // Hit an element in the stack - stop rewinding
                break
            }

            // Step 5: Let entry be the entry one earlier
            entryIndex--
        }

        // Step 7-10: Advance and Create - recreate elements from this point forward
        while (entryIndex < entries.size) {
            val entry = entries[entryIndex]

            // Skip markers (shouldn't happen in practice after rewind, but be safe)
            if (entry == null) {
                entryIndex++
                continue
            }

            val localName = entry.localName ?: run {
                entryIndex++
                continue
            }
            val namespace = entry.namespace ?: Namespaces.HTML

            // Step 8: Create - Insert an HTML element for the token
            val newElement = TreeNode.createElement(
                localName = localName,
                namespace = namespace,
                attributes = entry.attributes.toList()
            )

            // Insert into the current node and push onto the stack
            val currentNode = getCurrentNode()
            currentNode.appendChild(newElement)
            stack.push(newElement)

            // Step 9: Replace the entry for entry in the list with an entry for a new element
            activeFormattingElements.replace(entry, newElement)

            // Step 10: If not the last entry, continue to advance
            entryIndex++
        }
    }
}
