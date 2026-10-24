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
import com.theakram.helium.treebuilder.TokenizerStateType
import com.theakram.helium.treebuilder.TreeBuilder

public class HTMLParser {

    private val _errors = mutableListOf<ParseError>()
    private val tokenizer = Tokenizer(
        onTokenEmitted = ::processToken,
        onParseError = ::handleParseError
    )
    private val treeBuilder = TreeBuilder(onSwitchTokenizerState = ::switchTokenizerState)

    public val document: TreeNode
        get() = treeBuilder.document

    public val errors: List<ParseError>
        get() = _errors.toList()

    public fun parse(html: String): TreeNode {
        _errors.clear()
        tokenizer.reset()
        treeBuilder.reset()
        tokenizer.tokenize(html)
        return document
    }

    private fun handleParseError(code: String, position: Int) {
        _errors.add(ParseError(code, position))
    }

    private fun processToken(token: Token) {
        treeBuilder.process(token)
    }

    private fun switchTokenizerState(stateType: TokenizerStateType) {
        tokenizer.switchState(stateType)
    }
}