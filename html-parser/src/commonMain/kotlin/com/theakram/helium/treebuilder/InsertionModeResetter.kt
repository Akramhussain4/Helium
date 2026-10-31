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
import com.theakram.helium.treebuilder.modes.*

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#reset-the-insertion-mode-appropriately
 */
internal class InsertionModeResetter(
    private val stack: StackOfOpenElements,
    private val getContextElement: () -> TreeNode?,
    private val getHeadElement: () -> TreeNode?,
    private val getCurrentTemplateInsertionMode: () -> InsertionMode?
) {

    fun reset(): InsertionMode {
        for (nodeIndex in (stack.size - 1) downTo 0) {
            val last = nodeIndex == 0
            // In the fragment case, the root html element stands in for the context element
            val node = getContextElement()?.takeIf { last } ?: stack[nodeIndex]

            if (!last && node.namespace == Namespaces.HTML && node.localName in setOf("td", "th")) {
                return InCell
            }

            if (node.namespace == Namespaces.HTML && node.localName == "tr") {
                return InRow
            }

            if (node.namespace == Namespaces.HTML && node.localName in setOf("tbody", "thead", "tfoot")) {
                return InTableBody
            }

            if (node.namespace == Namespaces.HTML && node.localName == "caption") {
                return InCaption
            }

            if (node.namespace == Namespaces.HTML && node.localName == "colgroup") {
                return InColumnGroup
            }

            if (node.namespace == Namespaces.HTML && node.localName == "table") {
                return InTable
            }

            if (node.namespace == Namespaces.HTML && node.localName == "template") {
                getCurrentTemplateInsertionMode()?.let { return it }
            }

            if (!last && node.namespace == Namespaces.HTML && node.localName == "head") {
                return InHead
            }

            if (node.namespace == Namespaces.HTML && node.localName == "body") {
                return InBody
            }

            if (node.namespace == Namespaces.HTML && node.localName == "frameset") {
                return InFrameSet
            }

            if (node.namespace == Namespaces.HTML && node.localName == "html") {
                return if (getHeadElement() == null) BeforeHead else AfterHead
            }

            if (last) {
                return InBody
            }
        }
        // Fallback (shouldn't reach here)
        return InBody
    }
}
