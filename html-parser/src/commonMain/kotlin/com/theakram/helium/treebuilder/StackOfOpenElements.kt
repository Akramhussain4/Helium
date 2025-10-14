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

import com.theakram.helium.tree.TreeNode

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#the-stack-of-open-elements
 */
internal class StackOfOpenElements {

    private val elements = mutableListOf<TreeNode>()

    val currentNode: TreeNode?
        get() = elements.lastOrNull()

    val size: Int
        get() = elements.size

    fun isEmpty(): Boolean = elements.isEmpty()

    fun isNotEmpty(): Boolean = elements.isNotEmpty()

    fun push(element: TreeNode) {
        elements.add(element)
    }

    fun pop(): TreeNode? = elements.removeLastOrNull()

    fun popUntil(tagName: String) {
        while (elements.isNotEmpty()) {
            val element = pop()
            if (element?.localName?.equals(tagName, ignoreCase = true) == true) {
                break
            }
        }
    }

    fun popUntilElement(element: TreeNode) {
        while (elements.isNotEmpty()) {
            val popped = pop()
            if (popped === element) break
        }
    }

    inline fun popWhile(predicate: (TreeNode) -> Boolean) {
        while (elements.isNotEmpty() && currentNode?.let(predicate) == true) {
            pop()
        }
    }

    operator fun contains(element: TreeNode): Boolean = elements.contains(element)

    fun indexOf(element: TreeNode): Int = elements.indexOf(element)

    fun indexOfLastTagName(tagName: String): Int {
        return elements.indexOfLast {
            it.localName?.equals(tagName, ignoreCase = true) == true
        }
    }

    fun insertAfter(afterElement: TreeNode, newElement: TreeNode) {
        val index = elements.indexOf(afterElement)
        require(index >= 0) { "Element not found in stack" }
        elements.add(index + 1, newElement)
    }

    fun replace(oldElement: TreeNode, newElement: TreeNode): Boolean {
        val index = elements.indexOf(oldElement)
        return if (index >= 0) {
            elements[index] = newElement
            true
        } else {
            false
        }
    }


    inline fun findLast(predicate: (TreeNode) -> Boolean): TreeNode? {
        return elements.findLast(predicate)
    }

    inline fun any(predicate: (TreeNode) -> Boolean): Boolean = elements.any(predicate)

    operator fun get(index: Int): TreeNode = elements[index]

    operator fun set(index: Int, element: TreeNode) {
        elements[index] = element
    }

    fun remove(element: TreeNode): Boolean = elements.remove(element)

    fun toList(): List<TreeNode> = elements.toList()

    val lastIndex: Int
        get() = elements.lastIndex

    fun clear() {
        elements.clear()
    }

    override fun toString(): String {
        return elements.joinToString(" -> ") { "<${it.localName}>" }
    }
}
