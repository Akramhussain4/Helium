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

import com.theakram.helium.tree.TreeNode

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#the-list-of-active-formatting-elements
 */
internal class ActiveFormattingElements {

    private sealed interface Entry {
        data class Element(val node: TreeNode) : Entry

        data object Marker : Entry
    }

    private val entries = mutableListOf<Entry>()

    val isEmpty: Boolean get() = entries.isEmpty()

    val size: Int get() = entries.size

    fun isLastEntryMarker(): Boolean {
        val lastEntry = entries.lastOrNull()
        return lastEntry is Entry.Marker
    }

    fun getLastElement(): TreeNode? {
        val lastEntry = entries.lastOrNull()
        return when (lastEntry) {
            is Entry.Element -> lastEntry.node
            is Entry.Marker -> null
            null -> null
        }
    }

    internal fun getEntriesForReconstruction(): List<TreeNode?> {
        return entries.map { entry ->
            when (entry) {
                is Entry.Element -> entry.node
                is Entry.Marker -> null
            }
        }
    }

    fun push(element: TreeNode) {
        // Apply Noah's Ark clause
        applyNoahsArk(element)

        // Add the element to the end of the list
        entries.add(Entry.Element(element))
    }

    fun insertMarker() {
        entries.add(Entry.Marker)
    }

    fun clearToLastMarker() {
        // Remove entries from the end until we find and remove a marker
        while (entries.isNotEmpty()) {
            val removed = entries.removeLast()
            if (removed is Entry.Marker) {
                break
            }
        }
    }

    fun remove(element: TreeNode): Boolean {
        val index = entries.indexOfLast { it is Entry.Element && it.node === element }
        if (index >= 0) {
            entries.removeAt(index)
            return true
        }
        return false
    }

    fun replace(oldElement: TreeNode, newElement: TreeNode): Boolean {
        val index = entries.indexOfLast { it is Entry.Element && it.node === oldElement }
        if (index >= 0) {
            entries[index] = Entry.Element(newElement)
            return true
        }
        return false
    }

    fun contains(element: TreeNode): Boolean {
        return entries.any { it is Entry.Element && it.node === element }
    }

    fun getElements(): List<TreeNode> {
        return entries.mapNotNull { entry ->
            when (entry) {
                is Entry.Element -> entry.node
                is Entry.Marker -> null
            }
        }
    }

    fun getElementsAfterLastMarker(): List<TreeNode> {
        val lastMarkerIndex = entries.indexOfLast { it is Entry.Marker }
        val startIndex = if (lastMarkerIndex >= 0) lastMarkerIndex + 1 else 0

        return entries.subList(startIndex, entries.size).mapNotNull { entry ->
            when (entry) {
                is Entry.Element -> entry.node
                is Entry.Marker -> null
            }
        }
    }

    fun findLastElementByTagName(tagName: String): TreeNode? {
        val lastMarkerIndex = entries.indexOfLast { it is Entry.Marker }
        val startIndex = if (lastMarkerIndex >= 0) lastMarkerIndex + 1 else 0

        for (i in entries.lastIndex downTo startIndex) {
            val entry = entries[i]
            if (entry is Entry.Element &&
                entry.node.localName == tagName) {
                return entry.node
            }
        }
        return null
    }

    /**
     * Insert an element immediately after another element (or at the beginning if afterElement is null).
     * This is used to insert elements at a bookmark position in the adoption agency algorithm.
     */
    fun insertAfter(afterElement: TreeNode?, element: TreeNode) {
        if (afterElement == null) {
            // Insert at beginning
            entries.add(0, Entry.Element(element))
            return
        }

        val index = entries.indexOfLast { it is Entry.Element && it.node === afterElement }
        if (index >= 0) {
            entries.add(index + 1, Entry.Element(element))
        } else {
            // Fallback: add at end
            entries.add(Entry.Element(element))
        }
    }

    fun clear() {
        entries.clear()
    }

    private fun applyNoahsArk(newElement: TreeNode) {
        val lastMarkerIndex = entries.indexOfLast { it is Entry.Marker }
        val startIndex = if (lastMarkerIndex >= 0) lastMarkerIndex + 1 else 0
        val matchingElements = mutableListOf<Pair<Int, TreeNode>>()
        for (i in startIndex until entries.size) {
            val entry = entries[i]
            if (entry is Entry.Element && areElementsEquivalent(entry.node, newElement)) {
                matchingElements.add(i to entry.node)
            }
        }

        // If there are already 3 matching elements, remove the earliest one
        if (matchingElements.size >= 3) {
            val (indexToRemove, _) = matchingElements.first()
            entries.removeAt(indexToRemove)
        }
    }

    private fun areElementsEquivalent(element1: TreeNode, element2: TreeNode): Boolean {
        // Check tag name
        if (element1.localName != element2.localName) return false

        // Check namespace
        if (element1.namespace != element2.namespace) return false

        // Check attributes
        val attrs1 = element1.attributes
        val attrs2 = element2.attributes

        if (attrs1.size != attrs2.size) return false

        // Check each attribute match
        return attrs1.all { attr1 ->
            attrs2.any { attr2 ->
                attr1.localName == attr2.localName &&
                attr1.namespace == attr2.namespace &&
                attr1.value == attr2.value
            }
        }
    }

    override fun toString(): String {
        return entries.joinToString(separator = ", ") { entry ->
            when (entry) {
                is Entry.Element -> "<${entry.node.localName}>"
                is Entry.Marker -> "MARKER"
            }
        }
    }
}
