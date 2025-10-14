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
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#has-an-element-in-scope
 */
internal class ScopeChecker(private val stack: StackOfOpenElements) {

    fun hasElementInScope(tagName: String): Boolean {
        return hasElementInScopeWithBoundaries(tagName, DEFAULT_SCOPE_BOUNDARIES)
    }

    fun hasElementInListItemScope(tagName: String): Boolean {
        return hasElementInScopeWithBoundaries(tagName, LIST_ITEM_SCOPE_BOUNDARIES)
    }

    fun hasElementInButtonScope(tagName: String): Boolean {
        return hasElementInScopeWithBoundaries(tagName, BUTTON_SCOPE_BOUNDARIES)
    }

    fun hasElementInTableScope(tagName: String): Boolean {
        return hasElementInScopeWithBoundaries(tagName, TABLE_SCOPE_BOUNDARIES)
    }

    /**
     * Check if an element is in a select scope.
     * Select scope = all elements EXCEPT optgroup and option act as boundaries
     */
    fun hasElementInSelectScope(tagName: String): Boolean {
        // Walk backwards through stack
        for (i in stack.lastIndex downTo 0) {
            val element = stack[i]
            val elementName = element.localName

            // If we found the target element
            if (elementName?.equals(tagName, ignoreCase = true) == true) {
                return true
            }

            // In select scope, everything EXCEPT optgroup and option acts as boundary
            if (elementName != "optgroup" && elementName != "option") {
                return false
            }
        }
        return false
    }

    /**
     * Generic scope checking with custom boundaries.
     * Walks backwards from the current node until finding the target or hitting the boundary.
     */
    private fun hasElementInScopeWithBoundaries(tagName: String, boundaries: Set<String>): Boolean {
        // Walk backwards through the stack of open elements
        for (i in stack.lastIndex downTo 0) {
            val element = stack[i]
            val elementName = element.localName
            val namespace = element.namespace

            // If we found the target element
            if (elementName?.equals(tagName, ignoreCase = true) == true) {
                return true
            }

            // Check if this is a scope boundary
            if (isScopeBoundary(elementName, namespace, boundaries)) {
                return false
            }
        }
        return false
    }

    private fun isScopeBoundary(localName: String?, namespace: String?, boundaries: Set<String>): Boolean {
        if (localName == null) return false

        if (namespace == Namespaces.HTML && localName in boundaries) {
            return true
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
