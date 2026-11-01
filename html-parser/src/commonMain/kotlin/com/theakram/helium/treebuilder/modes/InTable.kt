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
import com.theakram.helium.treebuilder.modes.helpers.InHeadHelpers
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo
import com.theakram.helium.util.equalsAsciiCaseInsensitive

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-intable
 */
internal object InTable : InsertionMode {

    /** The current nodes for which a character token switches to "in table text". */
    private val TABLE_TEXT_PARENTS = setOf("table", "tbody", "template", "tfoot", "thead", "tr")

    /** Spec: "while the current node is not a table, template, or html element, pop elements" */
    private val TABLE_CONTEXT = setOf("table", "template", "html")

    private val IGNORED_END_TAGS = setOf(
        "body", "caption", "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr"
    )

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character if treeBuilder.currentNode.let {
                it.namespace == Namespaces.HTML && it.localName in TABLE_TEXT_PARENTS
            } -> {
                treeBuilder.pendingTableCharacterTokens.clear()
                treeBuilder.saveOriginalInsertionMode()
                reprocessIn(InTableText)
            }

            is Token.Comment -> {
                treeBuilder.insertComment(token.data)
                continueInCurrentMode()
            }

            is Token.ProcessingInstruction -> {
                treeBuilder.insertProcessingInstruction(token.target, token.data)
                continueInCurrentMode()
            }

            is Token.DocType -> {
                // Parse error. Ignore the token.
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            is Token.StartTag -> processStartTag(token, treeBuilder)

            is Token.EndTag -> when (token.name) {
                "table" -> {
                    if (!treeBuilder.hasElementInTableScope("table")) {
                        // This is a parse error; ignore the token.
                        treeBuilder.parseError()
                    } else {
                        treeBuilder.popUntil("table")
                        // The reset switches the insertion mode itself, which ends the processing of this token
                        treeBuilder.resetInsertionModeAppropriately()
                    }
                    continueInCurrentMode()
                }

                in IGNORED_END_TAGS -> {
                    // Parse error. Ignore the token.
                    treeBuilder.parseError()
                    continueInCurrentMode()
                }

                "template" -> InHead.process(token, treeBuilder)

                else -> processUsingAnythingElseRules(token, treeBuilder)
            }

            is Token.EOF -> InBody.process(token, treeBuilder)

            else -> processUsingAnythingElseRules(token, treeBuilder)
        }
    }

    private fun processStartTag(token: Token.StartTag, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token.name) {
            "caption" -> {
                treeBuilder.clearStackBackTo(TABLE_CONTEXT)
                treeBuilder.activeFormattingElements.insertMarker()
                treeBuilder.insertElement("caption", attributes = InHeadHelpers.toAttributeList(token.attributes))
                switchTo(InCaption)
            }

            "colgroup" -> {
                treeBuilder.clearStackBackTo(TABLE_CONTEXT)
                treeBuilder.insertElement("colgroup", attributes = InHeadHelpers.toAttributeList(token.attributes))
                switchTo(InColumnGroup)
            }

            "col" -> {
                treeBuilder.clearStackBackTo(TABLE_CONTEXT)
                treeBuilder.insertElement("colgroup")
                reprocessIn(InColumnGroup)
            }

            "tbody", "tfoot", "thead" -> {
                treeBuilder.clearStackBackTo(TABLE_CONTEXT)
                treeBuilder.insertElement(token.name, attributes = InHeadHelpers.toAttributeList(token.attributes))
                switchTo(InTableBody)
            }

            "td", "th", "tr" -> {
                treeBuilder.clearStackBackTo(TABLE_CONTEXT)
                treeBuilder.insertElement("tbody")
                reprocessIn(InTableBody)
            }

            "table" -> {
                // Parse error.
                treeBuilder.parseError()
                if (!treeBuilder.hasElementInTableScope("table")) {
                    continueInCurrentMode()
                } else {
                    treeBuilder.popUntil("table")
                    treeBuilder.resetInsertionModeAndReprocess()
                }
            }

            "style", "script", "template" -> InHead.process(token, treeBuilder)

            "input" -> {
                val type = token.attributes["type"]
                if (type == null || !type.equalsAsciiCaseInsensitive("hidden")) {
                    processUsingAnythingElseRules(token, treeBuilder)
                } else {
                    // Parse error.
                    treeBuilder.parseError()
                    treeBuilder.insertElement("input", attributes = InHeadHelpers.toAttributeList(token.attributes))
                    treeBuilder.popCurrentElement()
                    treeBuilder.acknowledgeSelfClosingFlag()
                    continueInCurrentMode()
                }
            }

            "form" -> {
                // Parse error.
                treeBuilder.parseError()
                if (treeBuilder.formElement != null && !treeBuilder.isParsingTemplateContents) {
                    continueInCurrentMode()
                } else {
                    val formElement = treeBuilder.insertElement(
                        "form",
                        attributes = InHeadHelpers.toAttributeList(token.attributes)
                    )
                    if (!treeBuilder.isParsingTemplateContents) {
                        treeBuilder.formElement = formElement
                    }
                    treeBuilder.popCurrentElement()
                    continueInCurrentMode()
                }
            }

            else -> processUsingAnythingElseRules(token, treeBuilder)
        }
    }

    /**
     * The "anything else" entry, which "in table text" also uses for each of its pending character tokens:
     * "Parse error. Enable foster parenting, process the token using the rules for the "in body" insertion mode,
     * and then disable foster parenting."
     */
    internal fun processUsingAnythingElseRules(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        treeBuilder.parseError()
        treeBuilder.enableFosterParenting()
        val transition = InBody.process(token, treeBuilder)
        treeBuilder.disableFosterParenting()
        return transition
    }
}
