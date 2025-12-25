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

package com.theakram.helium.treebuilder

import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.TreeNode

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-html-fragments
 */
public object FragmentParser {

    public fun parseFragment(
        context: TreeNode,
        input: String,
        scriptingEnabled: Boolean = true
    ): List<TreeNode> {
        // Step 1:
        val document = TreeNode.createDocument()

        // Steps 2-3:
        context.quirksMode?.let { document.quirksMode = it }

        // Step 4: Not yet implemented

        // Step 5:
        lateinit var tokenizer: Tokenizer
        val treeBuilder = TreeBuilder(
            onSwitchTokenizerState = { stateType ->
                tokenizer.switchState(stateType)
            },
        )
        tokenizer = Tokenizer(
            onTokenEmitted = { token ->
                treeBuilder.process(token)
            },
            onParseError = { _, _ -> }
        )

        // Step 6:
        val tokenizerState = getInitialTokenizerState(context, scriptingEnabled)
        tokenizer.switchState(tokenizerState)

        // Step 7:
        val root = TreeNode.createElement(
            localName = "html",
            namespace = Namespaces.HTML,
            attributes = emptyList()
        )

        // Step 8:
        document.appendChild(root)

        // Step 9:
        treeBuilder.pushElement(root)

        // Step 10:
        if (context.namespace == Namespaces.HTML && context.localName == "template") {
            // TODO Implement when InTemplate mode is added
            // treeBuilder.pushTemplateInsertionMode(InTemplate)
        }

        // Step 11:

        // Step 12:
        treeBuilder.resetInsertionModeAppropriately()

        // Step 13:
        setFormElementPointer(treeBuilder, context)

        // Step 14-15:
        tokenizer.tokenize(input)

        // Step 16:
        return root.children.toList()
    }

    private fun getInitialTokenizerState(context: TreeNode, scriptingEnabled: Boolean): TokenizerStateType {
        if (context.namespace != Namespaces.HTML) {
            return TokenizerStateType.DATA
        }

        return when (context.localName) {
            "title", "textarea" -> TokenizerStateType.RCDATA
            "style", "xmp", "iframe", "noembed", "noframes" -> TokenizerStateType.RAWTEXT
            "script" -> TokenizerStateType.SCRIPT_DATA
            "noscript" -> if (scriptingEnabled) TokenizerStateType.RAWTEXT else TokenizerStateType.DATA
            "plaintext" -> TokenizerStateType.PLAINTEXT
            else -> TokenizerStateType.DATA
        }
    }

    private fun setFormElementPointer(treeBuilder: TreeBuilder, context: TreeNode) {
        var current: TreeNode? = context
        while (current != null) {
            if (current.namespace == Namespaces.HTML && current.localName == "form") {
                treeBuilder.formElement = current
                return
            }
            current = current.parent
        }
    }
}