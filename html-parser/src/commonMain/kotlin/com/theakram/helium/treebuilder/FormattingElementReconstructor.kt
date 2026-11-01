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

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#reconstruct-the-active-formatting-elements
 */
internal class FormattingElementReconstructor(
    private val stack: StackOfOpenElements,
    private val activeFormattingElements: ActiveFormattingElements,
    private val treeBuilder: TreeBuilder
) {

    fun reconstruct() {
        // Step 1:
        if (activeFormattingElements.isEmpty) return

        // Step 2:
        var index = activeFormattingElements.size - 1
        val lastElement = activeFormattingElements.elementAt(index)
        if (lastElement == null || lastElement in stack) return

        // Step 3: entry is the element at index

        // Step 4: rewind, unless there are no entries before entry
        while (index > 0) {
            // Step 5:
            index--

            // Step 6: if entry is neither a marker nor an element in the stack, rewind again
            val entry = activeFormattingElements.elementAt(index)
            if (entry == null || entry in stack) {
                // Step 7:
                index++
                break
            }
        }

        while (true) {
            // Step 8: insert an HTML element for the token for which entry was created
            val entry = checkNotNull(activeFormattingElements.elementAt(index)) {
                "Only elements follow the entry that reconstruction advances from"
            }
            val newElement = treeBuilder.insertElement(
                tagName = checkNotNull(entry.localName),
                namespace = Namespaces.HTML,
                attributes = activeFormattingElements.tokenAttributesAt(index)
            )

            // Step 9:
            activeFormattingElements.replaceAt(index, newElement)

            // Step 10:
            if (index == activeFormattingElements.size - 1) break

            // Step 7:
            index++
        }
    }
}
