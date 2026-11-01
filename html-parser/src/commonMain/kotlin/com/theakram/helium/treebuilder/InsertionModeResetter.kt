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
import com.theakram.helium.treebuilder.modes.AfterHead
import com.theakram.helium.treebuilder.modes.BeforeHead
import com.theakram.helium.treebuilder.modes.InBody
import com.theakram.helium.treebuilder.modes.InCaption
import com.theakram.helium.treebuilder.modes.InCell
import com.theakram.helium.treebuilder.modes.InColumnGroup
import com.theakram.helium.treebuilder.modes.InFrameSet
import com.theakram.helium.treebuilder.modes.InHead
import com.theakram.helium.treebuilder.modes.InRow
import com.theakram.helium.treebuilder.modes.InTable
import com.theakram.helium.treebuilder.modes.InTableBody

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#reset-the-insertion-mode-appropriately
 */
internal class InsertionModeResetter(
    private val stack: StackOfOpenElements,
    private val getContextElement: () -> TreeNode?,
    private val getHeadElement: () -> TreeNode?,
    private val getCurrentTemplateInsertionMode: () -> InsertionMode?
) {

    /**
     * Returns the insertion mode to switch to.
     */
    fun reset(): InsertionMode {
        check(stack.isNotEmpty()) { "The stack of open elements is empty" }

        // Step 1:
        var last = false
        // Step 2:
        var nodeIndex = stack.size - 1

        while (true) {
            var node = stack[nodeIndex]

            // Step 3:
            if (nodeIndex == 0) {
                last = true
                // Fragment case
                getContextElement()?.let { node = it }
            }

            if (node.namespace == Namespaces.HTML) {
                when (node.localName) {
                    // Step 4:
                    "td", "th" -> if (!last) return InCell

                    // Step 5:
                    "tr" -> return InRow

                    // Step 6:
                    "tbody", "thead", "tfoot" -> return InTableBody

                    // Step 7:
                    "caption" -> return InCaption

                    // Step 8:
                    "colgroup" -> return InColumnGroup

                    // Step 9:
                    "table" -> return InTable

                    // Step 10:
                    "template" -> return checkNotNull(getCurrentTemplateInsertionMode()) {
                        "The stack of template insertion modes is empty while a template element is open"
                    }

                    // Step 11:
                    "head" -> if (!last) return InHead

                    // Step 12:
                    "body" -> return InBody

                    // Step 13: (fragment case)
                    "frameset" -> return InFrameSet

                    // Step 14:
                    "html" -> return if (getHeadElement() == null) {
                        // Step 14.1: (fragment case)
                        BeforeHead
                    } else {
                        // Step 14.2:
                        AfterHead
                    }
                }
            }

            // Step 15: (fragment case)
            if (last) return InBody

            // Step 16:
            nodeIndex--
            // Step 17: Return to the step labeled loop.
        }
    }
}
