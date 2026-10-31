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

import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.TreeNode

/**
 * Each element in the list is associated with the token for which it was created, so that further elements can be
 * created for that token. The token's tag name is the element's local name, which never changes, so an entry keeps
 * the token's attributes, as they were on the token.
 *
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#the-list-of-active-formatting-elements
 */
internal class ActiveFormattingElements {

    private sealed interface Entry {
        data class Element(val node: TreeNode, val tokenAttributes: List<Attribute>) : Entry

        data object Marker : Entry
    }

    private val entries = mutableListOf<Entry>()

    val isEmpty: Boolean get() = entries.isEmpty()

    val size: Int get() = entries.size

    /** The element of the entry at [index], or null if that entry is a marker. */
    fun elementAt(index: Int): TreeNode? = (entries[index] as? Entry.Element)?.node

    /** The attributes of the token for which the element of the entry at [index] was created. */
    fun tokenAttributesAt(index: Int): List<Attribute> = elementEntryAt(index).tokenAttributes

    /** The attributes of the token for which [element], which must be in the list, was created. */
    fun tokenAttributesOf(element: TreeNode): List<Attribute> = elementEntryAt(indexOf(element)).tokenAttributes

    /** Replaces the entry at [index] with an entry for [element], which was created for the same token. */
    fun replaceAt(index: Int, element: TreeNode) {
        entries[index] = elementEntryAt(index).copy(node = element)
    }

    /**
     * Pushes [element], created for a token with [tokenAttributes], onto the list.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#push-onto-the-list-of-active-formatting-elements
     */
    fun push(element: TreeNode, tokenAttributes: List<Attribute>) {
        // Step 1:
        applyNoahsArk(element, tokenAttributes)

        // Step 2:
        entries.add(Entry.Element(element, tokenAttributes))
    }

    fun insertMarker() {
        entries.add(Entry.Marker)
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#clear-the-list-of-active-formatting-elements-up-to-the-last-marker
     */
    fun clearToLastMarker() {
        while (entries.isNotEmpty()) {
            val removed = entries.removeLast()
            if (removed is Entry.Marker) {
                break
            }
        }
    }

    fun remove(element: TreeNode): Boolean {
        val index = indexOf(element)
        if (index >= 0) {
            entries.removeAt(index)
            return true
        }
        return false
    }

    /**
     * Replaces the entry for [oldElement], which must be in the list, with an entry for [newElement], which was
     * created for the same token.
     */
    fun replace(oldElement: TreeNode, newElement: TreeNode) {
        replaceAt(indexOf(oldElement), newElement)
    }

    fun contains(element: TreeNode): Boolean = indexOf(element) >= 0

    /**
     * The last element that is between the end of the list and the last marker in the list, if any, or the start of
     * the list otherwise, and has the tag name [tagName].
     */
    fun findLastElementByTagName(tagName: String): TreeNode? {
        for (i in entries.lastIndex downTo 0) {
            when (val entry = entries[i]) {
                is Entry.Marker -> return null
                is Entry.Element -> if (entry.node.localName == tagName) return entry.node
            }
        }
        return null
    }

    /**
     * Inserts an entry for [element], created for a token with [tokenAttributes], immediately after the entry for
     * [afterElement]: the adoption agency algorithm's bookmark once it has moved.
     */
    fun insertAfter(afterElement: TreeNode, element: TreeNode, tokenAttributes: List<Attribute>) {
        val index = indexOf(afterElement)
        check(index >= 0) { "The bookmark's element must be in the list of active formatting elements" }
        entries.add(index + 1, Entry.Element(element, tokenAttributes))
    }

    fun clear() {
        entries.clear()
    }

    private fun indexOf(element: TreeNode): Int =
        entries.indexOfLast { it is Entry.Element && it.node === element }

    private fun elementEntryAt(index: Int): Entry.Element =
        checkNotNull(entries.getOrNull(index) as? Entry.Element) { "No element entry at index $index" }

    // The Noah's Ark clause: if there are already three elements after the last marker, if any, or anywhere in the
    // list if there are no markers, that have the same tag name, namespace, and attributes as element, then remove
    // the earliest such element. The attributes are compared as they were on the tokens for which the elements were
    // created.
    private fun applyNoahsArk(element: TreeNode, tokenAttributes: List<Attribute>) {
        var matchCount = 0
        var earliestMatchIndex = -1
        for (i in entries.lastIndex downTo 0) {
            val entry = entries[i]
            if (entry is Entry.Marker) break
            if (entry is Entry.Element &&
                entry.node.localName == element.localName &&
                entry.node.namespace == element.namespace &&
                haveSameAttributes(entry.tokenAttributes, tokenAttributes)
            ) {
                matchCount++
                earliestMatchIndex = i
            }
        }
        if (matchCount >= 3) {
            entries.removeAt(earliestMatchIndex)
        }
    }

    // Whether the attributes can be paired such that the two attributes in each pair have identical names,
    // namespaces, and values. A token has no two attributes with the same name, so each has at most one partner.
    private fun haveSameAttributes(attributes: List<Attribute>, otherAttributes: List<Attribute>): Boolean =
        attributes.size == otherAttributes.size && attributes.all { attribute ->
            otherAttributes.any {
                it.localName == attribute.localName &&
                    it.namespace == attribute.namespace &&
                    it.value == attribute.value
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
