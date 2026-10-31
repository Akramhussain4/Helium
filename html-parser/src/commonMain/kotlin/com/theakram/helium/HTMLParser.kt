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

package com.theakram.helium

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.treebuilder.FragmentParser
import com.theakram.helium.treebuilder.TokenizerStateType
import com.theakram.helium.treebuilder.TreeBuilder

/**
 * Parses HTML documents following the WHATWG HTML parsing algorithm.
 *
 * A parser can be reused: each call to [parse] or [parseFragment] produces a new, independent tree.
 * Instances are not thread-safe.
 *
 * @param scriptingEnabled whether to parse as a browser with scripting enabled does, which is the default.
 * The only difference is `<noscript>`: with scripting enabled its content is raw text, and with scripting
 * disabled it is parsed as markup.
 */
public class HTMLParser(private val scriptingEnabled: Boolean = true) {

    private val _errors = mutableListOf<ParseError>()
    private val tokenizer = Tokenizer(
        onTokenEmitted = ::processToken,
        onParseError = ::handleParseError,
        hasForeignAdjustedCurrentNode = ::hasForeignAdjustedCurrentNode
    )
    private val treeBuilder = TreeBuilder(
        onSwitchTokenizerState = ::switchTokenizerState,
        scriptingEnabled = scriptingEnabled
    )

    /** The document produced by the most recent call to [parse]. */
    public val document: TreeNode
        get() = treeBuilder.document

    /**
     * Parse errors reported by the tokenizer during the most recent call to [parse] or [parseFragment].
     *
     * Tree construction errors are not reported yet.
     */
    public val errors: List<ParseError>
        get() = _errors.toList()

    /**
     * Parses [html] into a document tree.
     *
     * Like a browser, the parser recovers from malformed markup instead of rejecting it, recording a
     * [ParseError] in [errors] where the tokenizer detects one.
     */
    public fun parse(html: String): TreeNode {
        _errors.clear()
        tokenizer.reset()
        treeBuilder.reset()
        tokenizer.tokenize(html)
        return document
    }

    /**
     * Parses [html] as the contents of [context], the way setting `innerHTML` on it does, and returns the
     * resulting nodes. The context decides how the markup is read: `<td>` is only allowed in a table row, for
     * example, and content of an `<svg>` context is SVG.
     *
     * [context] is not modified; the nodes are returned without a parent.
     */
    public fun parseFragment(html: String, context: TreeNode): List<TreeNode> {
        _errors.clear()
        val nodes = FragmentParser.parseFragment(
            context = context,
            input = html,
            scriptingEnabled = scriptingEnabled,
            onParseError = ::handleParseError
        )
        // Detach the nodes from the html element the algorithm parses them into
        nodes.forEach { it.parent?.removeChild(it) }
        return nodes
    }

    private fun handleParseError(code: String, position: Int) {
        _errors.add(ParseError(code, position))
    }

    private fun processToken(token: Token) {
        treeBuilder.process(token)
    }

    private fun hasForeignAdjustedCurrentNode(): Boolean = treeBuilder.hasForeignAdjustedCurrentNode()

    private fun switchTokenizerState(stateType: TokenizerStateType) {
        tokenizer.switchState(stateType)
    }
}