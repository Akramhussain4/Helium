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
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-intbody
 */
internal object InTableBody : InsertionMode {

    private val CELL_START_TAGS = setOf("th", "td")

    private val TABLE_SECTIONS = setOf("tbody", "tfoot", "thead")

    private val TABLE_BODY_CLOSING_START_TAGS = setOf("caption", "col", "colgroup", "tbody", "tfoot", "thead")

    private val IGNORED_END_TAGS = setOf("body", "caption", "col", "colgroup", "html", "td", "th", "tr")

    /** Spec: "while the current node is not a tbody, tfoot, thead, template, or html element, pop elements" */
    private val TABLE_BODY_CONTEXT = setOf("tbody", "tfoot", "thead", "template", "html")

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.StartTag if token.name == "tr" -> {
                treeBuilder.clearStackBackTo(TABLE_BODY_CONTEXT)
                treeBuilder.insertElement("tr", attributes = InHeadHelpers.toAttributeList(token.attributes))
                switchTo(InRow)
            }

            is Token.StartTag if token.name in CELL_START_TAGS -> {
                // Parse error.
                treeBuilder.parseError()
                treeBuilder.clearStackBackTo(TABLE_BODY_CONTEXT)
                treeBuilder.insertElement("tr")
                reprocessIn(InRow)
            }

            is Token.EndTag if token.name in TABLE_SECTIONS -> {
                if (!treeBuilder.hasElementInTableScope(token.name)) {
                    // This is a parse error; ignore the token.
                    treeBuilder.parseError()
                    continueInCurrentMode()
                } else {
                    treeBuilder.clearStackBackTo(TABLE_BODY_CONTEXT)
                    treeBuilder.popCurrentElement()
                    switchTo(InTable)
                }
            }

            is Token.StartTag if token.name in TABLE_BODY_CLOSING_START_TAGS -> closeTheTableBody(treeBuilder)

            is Token.EndTag if token.name == "table" -> closeTheTableBody(treeBuilder)

            is Token.EndTag if token.name in IGNORED_END_TAGS -> {
                // Parse error. Ignore the token.
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            else -> InTable.process(token, treeBuilder)
        }
    }

    /**
     * The steps for the start tags that close the table body, and for the table end tag.
     */
    private fun closeTheTableBody(treeBuilder: TreeBuilder): InsertionModeTransition {
        // A tbody, thead, or tfoot element in table scope: none of them is a table scope boundary, so checking
        // each name on its own gives the same answer as one walk looking for any of them
        val hasTableSection = TABLE_SECTIONS.any { treeBuilder.hasElementInTableScope(it) }
        if (!hasTableSection) {
            // This is a parse error; ignore the token.
            treeBuilder.parseError()
            return continueInCurrentMode()
        }
        // Step 1: Clear the stack back to a table body context.
        treeBuilder.clearStackBackTo(TABLE_BODY_CONTEXT)

        // Step 2: Pop the current node from the stack of open elements. Switch the insertion mode to "in table".
        treeBuilder.popCurrentElement()

        // Step 3: Reprocess the token.
        return reprocessIn(InTable)
    }
}
