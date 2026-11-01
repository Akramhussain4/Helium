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

import com.theakram.helium.tokenization.Token
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.modes.helpers.InHeadHelpers
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-intr
 */
internal object InRow : InsertionMode {

    private val CELL_START_TAGS = setOf("th", "td")

    private val ROW_CLOSING_START_TAGS = setOf("caption", "col", "colgroup", "tbody", "tfoot", "thead", "tr")

    private val TABLE_SECTION_END_TAGS = setOf("tbody", "tfoot", "thead")

    private val IGNORED_END_TAGS = setOf("body", "caption", "col", "colgroup", "html", "td", "th")

    /** Spec: "while the current node is not a tr, template, or html element, pop elements" */
    private val TABLE_ROW_CONTEXT = setOf("tr", "template", "html")

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.StartTag if token.name in CELL_START_TAGS -> {
                treeBuilder.clearStackBackTo(TABLE_ROW_CONTEXT)
                treeBuilder.insertElement(token.name, attributes = InHeadHelpers.toAttributeList(token.attributes))
                treeBuilder.activeFormattingElements.insertMarker()
                switchTo(InCell)
            }

            is Token.EndTag if token.name == "tr" -> {
                if (!treeBuilder.hasElementInTableScope("tr")) {
                    // This is a parse error; ignore the token.
                    treeBuilder.parseError()
                    continueInCurrentMode()
                } else {
                    popTheRow(treeBuilder)
                    switchTo(InTableBody)
                }
            }

            is Token.StartTag if token.name in ROW_CLOSING_START_TAGS -> closeTheRowAndReprocess(treeBuilder)

            is Token.EndTag if token.name == "table" -> closeTheRowAndReprocess(treeBuilder)

            is Token.EndTag if token.name in TABLE_SECTION_END_TAGS -> {
                if (!treeBuilder.hasElementInTableScope(token.name)) {
                    // This is a parse error; ignore the token.
                    treeBuilder.parseError()
                    continueInCurrentMode()
                } else if (!treeBuilder.hasElementInTableScope("tr")) {
                    // Ignore the token.
                    continueInCurrentMode()
                } else {
                    popTheRow(treeBuilder)
                    reprocessIn(InTableBody)
                }
            }

            is Token.EndTag if token.name in IGNORED_END_TAGS -> {
                // Parse error. Ignore the token.
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            else -> InTable.process(token, treeBuilder)
        }
    }

    /**
     * The steps for the start tags that close the row, and for the table end tag.
     */
    private fun closeTheRowAndReprocess(treeBuilder: TreeBuilder): InsertionModeTransition {
        if (!treeBuilder.hasElementInTableScope("tr")) {
            // This is a parse error; ignore the token.
            treeBuilder.parseError()
            return continueInCurrentMode()
        }
        popTheRow(treeBuilder)
        return reprocessIn(InTableBody)
    }

    /**
     * Clear the stack back to a table row context, then pop the current node (which will be a tr element) from
     * the stack of open elements. The caller switches the insertion mode to "in table body".
     */
    private fun popTheRow(treeBuilder: TreeBuilder) {
        treeBuilder.clearStackBackTo(TABLE_ROW_CONTEXT)
        treeBuilder.popCurrentElement()
    }
}
