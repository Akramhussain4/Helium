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
import com.theakram.helium.tree.TreeNode

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#has-an-element-in-scope
 */
internal class ScopeChecker(private val stack: StackOfOpenElements) {

    fun hasElementInScope(tagName: String): Boolean {
        return hasTagNameInScopeWithBoundaries(tagName, DEFAULT_SCOPE_BOUNDARIES)
    }

    fun hasElementInListItemScope(tagName: String): Boolean {
        return hasTagNameInScopeWithBoundaries(tagName, LIST_ITEM_SCOPE_BOUNDARIES)
    }

    fun hasElementInButtonScope(tagName: String): Boolean {
        return hasTagNameInScopeWithBoundaries(tagName, BUTTON_SCOPE_BOUNDARIES)
    }

    fun hasElementInTableScope(tagName: String): Boolean {
        return hasTagNameInScopeWithBoundaries(tagName, TABLE_SCOPE_BOUNDARIES)
    }

    /**
     * Check if ANY element with the given tag name is in the select scope.
     * Select scope is special: all elements EXCEPT optgroup and option act as boundaries.
     * @param tagName The tag name to search for
     */
    fun hasElementInSelectScope(tagName: String): Boolean {
        for (i in stack.lastIndex downTo 0) {
            val element = stack[i]
            val elementName = element.localName

            if (element.namespace == Namespaces.HTML && elementName?.equals(tagName, ignoreCase = true) == true) {
                return true
            }

            // In select scope, everything EXCEPT optgroup and option acts as boundary
            if (elementName != "optgroup" && elementName != "option") {
                return false
            }
        }
        return false
    }

    fun isElementInScope(element: TreeNode): Boolean {
        return isElementInScopeWithBoundaries(element, DEFAULT_SCOPE_BOUNDARIES)
    }

    fun isElementInButtonScope(element: TreeNode): Boolean {
        return isElementInScopeWithBoundaries(element, BUTTON_SCOPE_BOUNDARIES)
    }

    fun isElementInListItemScope(element: TreeNode): Boolean {
        return isElementInScopeWithBoundaries(element, LIST_ITEM_SCOPE_BOUNDARIES)
    }

    fun isElementInTableScope(element: TreeNode): Boolean {
        return isElementInScopeWithBoundaries(element, TABLE_SCOPE_BOUNDARIES)
    }

    private fun hasTagNameInScopeWithBoundaries(tagName: String, boundaries: Set<String>): Boolean {
        for (i in stack.lastIndex downTo 0) {
            val element = stack[i]
            val elementName = element.localName
            val namespace = element.namespace

            // If we found an HTML element with the target tag name
            if (namespace == Namespaces.HTML && elementName?.equals(tagName, ignoreCase = true) == true) {
                return true
            }

            // Check if this is a scope boundary
            if (isScopeBoundary(elementName, namespace, boundaries)) {
                return false
            }
        }
        return false
    }

    private fun isElementInScopeWithBoundaries(element: TreeNode, boundaries: Set<String>): Boolean {
        for (i in stack.lastIndex downTo 0) {
            val node = stack[i]

            // If we found the target element (by reference)
            if (node === element) {
                return true
            }

            // Check if this is a scope boundary
            if (isScopeBoundary(node.localName, node.namespace, boundaries)) {
                return false
            }
        }
        return false
    }

    internal fun isScopeBoundary(localName: String?, namespace: String?, boundaries: Set<String>): Boolean {
        if (localName == null) return false

        if (namespace == Namespaces.HTML && localName in boundaries) {
            return true
        }

        // Table scope is the only scope whose boundaries are all HTML elements
        if (boundaries === TABLE_SCOPE_BOUNDARIES) {
            return false
        }

        if (namespace == Namespaces.MATHML && localName in MATHML_SCOPE_BOUNDARIES) {
            return true
        }

        if (namespace == Namespaces.SVG && localName in SVG_SCOPE_BOUNDARIES) {
            return true
        }

        return false
    }

    companion object {
        private val DEFAULT_SCOPE_BOUNDARIES = setOf(
            "applet", "caption", "html", "table", "td", "th",
            "marquee", "object", "select", "template"
        )

        private val LIST_ITEM_SCOPE_BOUNDARIES = DEFAULT_SCOPE_BOUNDARIES + setOf("ol", "ul")

        private val BUTTON_SCOPE_BOUNDARIES = DEFAULT_SCOPE_BOUNDARIES + setOf("button")

        private val TABLE_SCOPE_BOUNDARIES = setOf("html", "table", "template")

        private val MATHML_SCOPE_BOUNDARIES = setOf(
            "mi", "mo", "mn", "ms", "mtext", "annotation-xml"
        )

        private val SVG_SCOPE_BOUNDARIES = setOf(
            "foreignObject", "desc", "title"
        )
    }
}
