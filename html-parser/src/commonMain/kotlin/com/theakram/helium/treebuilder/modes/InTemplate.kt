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

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character,
            is Token.Comment,
            is Token.DocType -> InBody.process(token, treeBuilder)

            is Token.StartTag if token.name.lowercase() in setOf(
                "base", "basefont", "bgsound", "link", "meta", "noframes", "script", "style", "template", "title"
            ) -> InHead.process(token, treeBuilder)

            is Token.EndTag if token.name.equals("template", ignoreCase = true) -> InHead.process(token, treeBuilder)

            is Token.StartTag if token.name.lowercase() in setOf(
                "caption", "colgroup", "tbody", "tfoot", "thead"
            ) -> {
                treeBuilder.popTemplateInsertionMode()
                treeBuilder.pushTemplateInsertionMode(InTable)
                reprocessIn(InTable)
            }

            is Token.StartTag if token.name.equals("col", ignoreCase = true) -> {
                treeBuilder.popTemplateInsertionMode()
                treeBuilder.pushTemplateInsertionMode(InColumnGroup)
                reprocessIn(InColumnGroup)
            }

            is Token.StartTag if token.name.equals("tr", ignoreCase = true) -> {
                treeBuilder.popTemplateInsertionMode()
                treeBuilder.pushTemplateInsertionMode(InTableBody)
                reprocessIn(InTableBody)
            }

            is Token.StartTag if token.name.lowercase() in setOf("td", "th") -> {
                treeBuilder.popTemplateInsertionMode()
                treeBuilder.pushTemplateInsertionMode(InRow)
                reprocessIn(InRow)
            }

            is Token.StartTag -> {
                treeBuilder.popTemplateInsertionMode()
                treeBuilder.pushTemplateInsertionMode(InBody)
                reprocessIn(InBody)
            }

            is Token.EndTag -> {
                // Parse error. Ignore the token.
                continueInCurrentMode()
            }

            is Token.EOF -> {
                val hasTemplate = treeBuilder.elementStack.any {
                    it.localName == "template" && it.namespace == Namespaces.HTML
                }
                if (!hasTemplate) {
                    // Stop parsing
                    return stopParsing()
                }
                // Parse error
                treeBuilder.popUntil("template")
                treeBuilder.activeFormattingElements.clearToLastMarker()
                treeBuilder.popTemplateInsertionMode()
                treeBuilder.resetInsertionModeAppropriately()
                reprocessIn(treeBuilder.currentMode)
            }
        }
    }
}
