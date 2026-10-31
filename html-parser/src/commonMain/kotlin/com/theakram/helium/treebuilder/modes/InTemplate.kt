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
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.stopParsing

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-intemplate
 */
internal object InTemplate : InsertionMode {

    private val IN_HEAD_START_TAGS = setOf(
        "base", "basefont", "bgsound", "link", "meta", "noframes", "script", "style", "template", "title"
    )

    private val TABLE_START_TAGS = setOf("caption", "colgroup", "tbody", "tfoot", "thead")

    private val CELL_START_TAGS = setOf("td", "th")

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character,
            is Token.Comment,
            is Token.ProcessingInstruction,
            is Token.DocType -> InBody.process(token, treeBuilder)

            is Token.StartTag if token.name in IN_HEAD_START_TAGS -> InHead.process(token, treeBuilder)

            is Token.EndTag if token.name == "template" -> InHead.process(token, treeBuilder)

            is Token.StartTag if token.name in TABLE_START_TAGS -> replaceTemplateModeAndReprocess(InTable, treeBuilder)

            is Token.StartTag if token.name == "col" -> replaceTemplateModeAndReprocess(InColumnGroup, treeBuilder)

            is Token.StartTag if token.name == "tr" -> replaceTemplateModeAndReprocess(InTableBody, treeBuilder)

            is Token.StartTag if token.name in CELL_START_TAGS -> replaceTemplateModeAndReprocess(InRow, treeBuilder)

            is Token.StartTag -> replaceTemplateModeAndReprocess(InBody, treeBuilder)

            is Token.EndTag -> {
                // Parse error. Ignore the token.
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            is Token.EOF -> {
                val hasTemplate = treeBuilder.elementStack.any {
                    it.namespace == Namespaces.HTML && it.localName == "template"
                }
                if (!hasTemplate) {
                    // If there is no template element on the stack of open elements, then stop parsing.
                    // (fragment case)
                    return stopParsing()
                }
                // Otherwise, this is a parse error.
                treeBuilder.parseError()

                // Pop elements from the stack of open elements until a template element has been popped from the
                // stack.
                treeBuilder.popUntil("template")

                // Clear the list of active formatting elements up to the last marker.
                treeBuilder.activeFormattingElements.clearToLastMarker()

                // Pop the current template insertion mode off the stack of template insertion modes.
                treeBuilder.popTemplateInsertionMode()

                // Reset the insertion mode appropriately, then reprocess the token.
                treeBuilder.resetInsertionModeAndReprocess()
            }
        }
    }

    /**
     * Pop the current template insertion mode off the stack of template insertion modes, push [mode] onto it so
     * that it is the new current template insertion mode, then switch the insertion mode to [mode] and reprocess
     * the token.
     */
    private fun replaceTemplateModeAndReprocess(
        mode: InsertionMode,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        treeBuilder.popTemplateInsertionMode()
        treeBuilder.pushTemplateInsertionMode(mode)
        return reprocessIn(mode)
    }
}
