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

package com.theakram.helium.treebuilder

import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.NodeType
import com.theakram.helium.tree.QuirksMode
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.treebuilder.modes.InTemplate

/**
 * Parses [input] as the contents of a context element, as setting innerHTML does.
 *
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-html-fragments
 */
internal object FragmentParser {

    fun parseFragment(
        context: TreeNode,
        input: String,
        scriptingEnabled: Boolean = true,
        onParseError: (String, Int) -> Unit = { _, _ -> }
    ): List<TreeNode> {
        // Step 6:
        lateinit var tokenizer: Tokenizer
        val treeBuilder = TreeBuilder(
            onSwitchTokenizerState = { stateType ->
                tokenizer.switchState(stateType)
            },
            scriptingEnabled = scriptingEnabled
        )
        treeBuilder.contextElement = context
        tokenizer = Tokenizer(
            onTokenEmitted = { token ->
                treeBuilder.process(token)
            },
            onParseError = onParseError,
            hasForeignAdjustedCurrentNode = treeBuilder::hasForeignAdjustedCurrentNode
        )

        // Step 2:
        val document = treeBuilder.document

        // Steps 3-4:
        val contextQuirksMode = getNodeDocument(context)?.quirksMode
        if (contextQuirksMode == QuirksMode.QUIRKS || contextQuirksMode == QuirksMode.LIMITED_QUIRKS) {
            document.quirksMode = contextQuirksMode
        }

        // Step 7:
        tokenizer.switchState(getInitialTokenizerState(context, scriptingEnabled))

        // Step 8:
        val root = TreeNode.createElement(
            localName = "html",
            namespace = Namespaces.HTML,
            attributes = emptyList()
        )

        // Step 9:
        document.appendChild(root)

        // Step 10:
        treeBuilder.pushElement(root)

        // Step 11:
        if (context.namespace == Namespaces.HTML && context.localName == "template") {
            treeBuilder.pushTemplateInsertionMode(InTemplate)
        }

        // Step 12: The context's attributes are read from the context element itself, e.g. for
        // annotation-xml HTML integration points

        // Step 13:
        treeBuilder.resetInsertionModeAppropriately()

        // Step 14:
        setFormElementPointer(treeBuilder, context)

        // Steps 15-16:
        tokenizer.tokenize(input)

        // Step 17:
        return root.children.toList()
    }

    private fun getNodeDocument(node: TreeNode): TreeNode? {
        var current = node
        while (true) {
            current = current.parent ?: break
        }
        return current.takeIf { it.type == NodeType.DOCUMENT }
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