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
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo

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

            is Token.EOF -> {
                treeBuilder.parseError()
                // N/A: "If the current node is a script element, then set its already started to true." Helium doesn't
                // execute scripts, so script elements have no already started.
                treeBuilder.popCurrentElement()
                reprocessIn(originalInsertionMode(treeBuilder))
            }

            is Token.EndTag if token.name == "script" -> {
                // N/A: Helium has no JavaScript execution context stack to perform a microtask checkpoint for.
                // N/A: "Let script be the current node". Helium doesn't prepare the script element, so it isn't kept.
                treeBuilder.popCurrentElement()
                val transition = switchTo(originalInsertionMode(treeBuilder))
                // N/A: The remaining steps save and restore the insertion point, prepare the script element and execute
                // pending parsing-blocking scripts. Helium doesn't execute scripts and has no document.write(), so none
                // of them has an effect.
                transition
            }

            is Token.EndTag -> {
                treeBuilder.popCurrentElement()
                switchTo(originalInsertionMode(treeBuilder))
            }

            // The tree builder only switches to "text" after switching the tokenizer to the RCDATA, RAWTEXT or script
            // data state, which emit nothing but character, end tag and end-of-file tokens
            else -> error("The \"text\" insertion mode can't receive a ${token::class.simpleName} token")
        }
    }

    private fun originalInsertionMode(treeBuilder: TreeBuilder): InsertionMode =
        checkNotNull(treeBuilder.getOriginalInsertionMode()) {
            "The original insertion mode is set before switching to the \"text\" insertion mode"
        }
}
