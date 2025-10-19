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

package com.theakram.helium.treebuilder.modes

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-incaption
 */
internal object InCaption : InsertionMode {

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.EndTag if token.name.equals("caption", ignoreCase = true) -> {
                closeCaptionAndSwitchMode(treeBuilder, reprocess = false)
            }

            is Token.StartTag if token.name.lowercase() in setOf(
                "caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr"
            ) -> {
                closeCaptionAndSwitchMode(treeBuilder, reprocess = true)
            }

            is Token.EndTag if token.name.equals("table", ignoreCase = true) -> {
                closeCaptionAndSwitchMode(treeBuilder, reprocess = true)
            }

            is Token.EndTag if token.name.lowercase() in setOf(
                "body", "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr"
            ) -> {
                // Parse error. Ignore the token.
                continueInCurrentMode()
            }

            else -> InBody.process(token, treeBuilder)
        }
    }

    private fun closeCaptionAndSwitchMode(
        treeBuilder: TreeBuilder,
        reprocess: Boolean
    ): InsertionModeTransition {
        if (!treeBuilder.hasElementInTableScope("caption")) {
            // Parse error (fragment case)
            return continueInCurrentMode()
        }
        generateImpliedEndTags(treeBuilder)
        if (treeBuilder.currentNode.localName != "caption" ||
            treeBuilder.currentNode.namespace != Namespaces.HTML
        ) {
            // Parse error
        }
        treeBuilder.popUntil("caption")
        treeBuilder.activeFormattingElements.clearToLastMarker()
        return if (reprocess) {
            reprocessIn(InTable)
        } else {
            switchTo(InTable)
        }
    }

    private fun generateImpliedEndTags(treeBuilder: TreeBuilder) {
        while (treeBuilder.elementStack.isNotEmpty()) {
            val currentNode = treeBuilder.currentNode
            if (currentNode.namespace == Namespaces.HTML &&
                currentNode.localName in setOf(
                    "dd", "dt", "li", "optgroup", "option", "p", "rb", "rp", "rt", "rtc"
                )
            ) {
                treeBuilder.popCurrentElement()
            } else {
                break
            }
        }
    }
}
