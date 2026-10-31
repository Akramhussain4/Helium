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
import com.theakram.helium.treebuilder.*

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-incdata
 */
internal object Text : InsertionMode {

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character -> {
                treeBuilder.insertCharacter(token.codePoint)
                continueInCurrentMode()
            }

            is Token.EndTag if token.name == "script" -> {
                // TODO: If the active speculative HTML parser is null and the JavaScript execution context stack is empty,

                treeBuilder.popCurrentElement()
                val originalMode = treeBuilder.getOriginalInsertionMode() ?: InBody

                // TODO: Let the old insertion point have the same value as the current insertion point.
                // TODO: Let the insertion point be just before the next input character.
                // TODO: Increment the parser's script nesting level by one.
                // TODO: If the active speculative HTML parser is null, then prepare the script element script.
                // TODO: Decrement the parser's script nesting level by one.
                // TODO: If the parser's script nesting level is zero, then set the parser pause flag to false.
                // TODO: Let the insertion point have the value of the old insertion point.
                // TODO: Handle pending parsing-blocking script

                switchTo(originalMode)
            }

            is Token.EndTag -> {
                treeBuilder.popCurrentElement()
                val originalMode = treeBuilder.getOriginalInsertionMode() ?: InBody
                switchTo(originalMode)
            }

            is Token.EOF -> {
                // Parse error.
                val currentNode = treeBuilder.currentNode
                if (currentNode.namespace == Namespaces.HTML && currentNode.localName == "script") {
                    // TODO: Set script element's already started to true
                }
                treeBuilder.popCurrentElement()
                val originalMode = treeBuilder.getOriginalInsertionMode() ?: InBody
                reprocessIn(originalMode)
            }

            else -> {
                // Parse error
                val currentNode = treeBuilder.currentNode
                if (currentNode.namespace == Namespaces.HTML && currentNode.localName == "script") {
                    // TODO: Set script element's already started to true
                }
                treeBuilder.popCurrentElement()
                val originalMode = treeBuilder.getOriginalInsertionMode() ?: InBody
                reprocessIn(originalMode)
            }
        }
    }
}
