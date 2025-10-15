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
import com.theakram.helium.tree.QuirksMode
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo
import com.theakram.helium.util.isAsciiWhitespace

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#the-initial-insertion-mode
 */
internal object Initial : InsertionMode {
    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character if token.data.code.isAsciiWhitespace() -> continueInCurrentMode()

            is Token.Comment -> {
                treeBuilder.document.appendChild(TreeNode.createComment(token.data))
                continueInCurrentMode()
            }

            is Token.DocType -> {
                // Full DOCTYPE handling requires tokenizer to emit name, publicId, systemId, forceQuirks
                // For now, insert a basic DOCTYPE and assume no-quirks mode
                treeBuilder.insertDoctype(name = "html", publicId = null, systemId = null)
                treeBuilder.document.quirksMode = QuirksMode.NO_QUIRKS
                switchTo(BeforeHTML)
            }

            else -> {
                if (!treeBuilder.document.isIframeSrcdoc) {
                    // Parse error (not tracked yet)
                    if (!treeBuilder.document.parserCannotChangeMode) {
                        treeBuilder.document.quirksMode = QuirksMode.QUIRKS
                    }
                }
                reprocessIn(BeforeHTML)
            }
        }
    }
}
