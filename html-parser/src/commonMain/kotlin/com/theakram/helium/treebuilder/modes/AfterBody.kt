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
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.treebuilder.*
import com.theakram.helium.util.isAsciiWhitespace

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-afterbody
 */
internal object AfterBody : InsertionMode {
    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character if token.data.code.isAsciiWhitespace() -> InBody.process(token, treeBuilder)

            is Token.Comment -> {
                val htmlElement = treeBuilder.elementStack.firstOrNull()
                htmlElement?.appendChild(TreeNode.createComment(token.data))
                continueInCurrentMode()
            }

            is Token.DocType -> continueInCurrentMode()

            is Token.StartTag if token.name.equals(other = "html", ignoreCase = true) -> InBody.process(
                token = token,
                treeBuilder = treeBuilder
            )

            is Token.EndTag if token.name.equals(other = "html", ignoreCase = true) -> {
                // TODO: Implement proper fragment case detection
                // For now, we assume we're not in a fragment case and switch to AfterAfterBody
                switchTo(AfterAfterBody)
            }

            else -> reprocessIn(InBody)
        }
    }
}