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
import com.theakram.helium.treebuilder.modes.helpers.InBodyHelpers
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-incaption
 */
internal object InCaption : InsertionMode {

    private val CAPTION_CLOSING_START_TAGS = setOf(
        "caption", "col", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr"
    )

    private val IGNORED_END_TAGS = setOf(
        "body", "col", "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", "tr"
    )

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.EndTag if token.name == "caption" -> closeTheCaption(treeBuilder, reprocess = false)

            is Token.StartTag if token.name in CAPTION_CLOSING_START_TAGS -> {
                closeTheCaption(treeBuilder, reprocess = true)
            }

            is Token.EndTag if token.name == "table" -> closeTheCaption(treeBuilder, reprocess = true)

            is Token.EndTag if token.name in IGNORED_END_TAGS -> {
                // Parse error. Ignore the token.
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            else -> InBody.process(token, treeBuilder)
        }
    }

    /**
     * The steps shared by the caption end tag, which then switches to "in table", and the tags that also
     * reprocess the token there.
     */
    private fun closeTheCaption(treeBuilder: TreeBuilder, reprocess: Boolean): InsertionModeTransition {
        if (!treeBuilder.hasElementInTableScope("caption")) {
            // This is a parse error; ignore the token. (fragment case)
            treeBuilder.parseError()
            return continueInCurrentMode()
        }
        // Step 1: Generate implied end tags.
        InBodyHelpers.generateImpliedEndTags(treeBuilder)

        // Step 2: Now, if the current node is not a caption element, then this is a parse error.
        val currentNode = treeBuilder.currentNode
        if (currentNode.namespace != Namespaces.HTML || currentNode.localName != "caption") {
            treeBuilder.parseError()
        }

        // Step 3: Pop elements from this stack until a caption element has been popped from the stack.
        treeBuilder.popUntil("caption")

        // Step 4: Clear the list of active formatting elements up to the last marker.
        treeBuilder.activeFormattingElements.clearToLastMarker()

        // Step 5: Switch the insertion mode to "in table" (and, for all but the caption end tag, reprocess the token).
        return if (reprocess) reprocessIn(InTable) else switchTo(InTable)
    }
}
