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
package com.theakram.helium.treebuilder.modes

import com.theakram.helium.tree.Namespaces
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.reprocessIn

// Steps that the table insertion modes ("in table", "in table body", "in row") and "in template" share

/**
 * Pops elements from the stack of open elements while the current node is not an HTML element named in [context]:
 * the "clear the stack back to a table context" steps and their table body and table row variants.
 *
 * Every context includes html, which is always the topmost element of the stack, so the stack never runs out.
 */
internal fun TreeBuilder.clearStackBackTo(context: Set<String>) {
    while (currentNode.namespace != Namespaces.HTML || currentNode.localName !in context) {
        checkNotNull(popCurrentElement()) { "The html element ends every table context" }
    }
}

/**
 * "Reset the insertion mode appropriately" followed by "Reprocess the token": the reset picks the new insertion
 * mode, and the token is reprocessed in it.
 */
internal fun TreeBuilder.resetInsertionModeAndReprocess(): InsertionModeTransition {
    resetInsertionModeAppropriately()
    return reprocessIn(currentMode)
}
