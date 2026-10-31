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
 * Parses [input] as the contents of a target element, as setting innerHTML does. The parser has no sanitizer
 * configuration and doesn't allow declarative shadow roots, as for innerHTML.
 *
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#html-fragment-parsing-algorithm
 */
internal object FragmentParser {

    /**
     * @param target the element, or a document fragment whose host is the element, to parse the contents of
     * @param isContextDocumentScriptingDisabled whether scripting is disabled for the context's node document
     * @return a document fragment holding the parsed nodes
     */
    fun parseFragment(
        target: TreeNode,
        input: String,
        scriptingMode: ScriptingMode = ScriptingMode.INERT,
        isContextDocumentScriptingDisabled: Boolean = false,
        onParseError: (String, Int) -> Unit = { _, _ -> }
    ): TreeNode {
        // Step 1:
        check(scriptingMode == ScriptingMode.INERT || scriptingMode == ScriptingMode.FRAGMENT) {
            "The fragment parsing scripting mode must be Inert or Fragment"
        }

        // Steps 2-3:
        val context = checkNotNull(if (target.type == NodeType.ELEMENT) target else target.host) {
            "The target must be an element or a document fragment with a host"
        }

        // Step 5:
        val contextDocument = getNodeDocument(context)

        // Step 8:
        val parserScriptingMode = if (isContextDocumentScriptingDisabled) ScriptingMode.DISABLED else scriptingMode

        // Step 9:
        lateinit var tokenizer: Tokenizer
        val treeBuilder = TreeBuilder(
            onSwitchTokenizerState = { stateType ->
                tokenizer.switchState(stateType)
            },
            scriptingMode = parserScriptingMode
        )
        treeBuilder.contextElement = context
        tokenizer = Tokenizer(
            onTokenEmitted = { token ->
                treeBuilder.process(token)
            },
            onParseError = onParseError,
            hasForeignAdjustedCurrentNode = treeBuilder::hasForeignAdjustedCurrentNode
        )

        // Step 4:
        val document = treeBuilder.document

        // Steps 6-7:
        val contextQuirksMode = contextDocument?.quirksMode
        if (contextQuirksMode == QuirksMode.QUIRKS || contextQuirksMode == QuirksMode.LIMITED_QUIRKS) {
            document.quirksMode = contextQuirksMode
        }

        // Step 10:
        tokenizer.switchState(getInitialTokenizerState(context, parserScriptingMode))

        // Step 11:
        val root = TreeNode.createElement(
            localName = "html",
            namespace = Namespaces.HTML,
            attributes = emptyList()
        )

        // Step 12:
        document.appendChild(root)

        // Step 13:
        treeBuilder.pushElement(root)

        // Step 14:
        val fragment = TreeNode.createDocumentFragment()

        // Step 15:
        treeBuilder.insertionTargetRedirectionMap[root] = InsertionLocation(fragment, null)

        // Step 16:
        if (context.namespace == Namespaces.HTML && context.localName == "template") {
            treeBuilder.pushTemplateInsertionMode(InTemplate)
        }

        // Step 17: The context's start tag token is read from the context element itself, e.g. for
        // annotation-xml HTML integration points

        // Step 18:
        treeBuilder.resetInsertionModeAppropriately()

        // Step 19:
        setFormElementPointer(treeBuilder, context)

        // Steps 20-21:
        tokenizer.tokenize(input)

        // Steps 22-23: The fragment has no node document to adopt it into
        return fragment
    }

    // A node inside template contents belongs to a document with the same mode as its template's
    private fun getNodeDocument(node: TreeNode): TreeNode? {
        var current = node
        while (true) {
            current = current.parent ?: current.host ?: break
        }
        return current.takeIf { it.type == NodeType.DOCUMENT }
    }

    private fun getInitialTokenizerState(context: TreeNode, scriptingMode: ScriptingMode): TokenizerStateType {
        if (context.namespace != Namespaces.HTML) {
            return TokenizerStateType.DATA
        }

        return when (context.localName) {
            "title", "textarea" -> TokenizerStateType.RCDATA
            "style", "xmp", "iframe", "noembed", "noframes" -> TokenizerStateType.RAWTEXT
            "script" -> TokenizerStateType.SCRIPT_DATA
            "noscript" -> if (scriptingMode != ScriptingMode.DISABLED) TokenizerStateType.RAWTEXT else TokenizerStateType.DATA
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