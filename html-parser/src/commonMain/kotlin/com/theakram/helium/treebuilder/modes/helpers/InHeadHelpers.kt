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

package com.theakram.helium.treebuilder.modes.helpers

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tree.Attribute
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TokenizerStateType
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.modes.Text
import com.theakram.helium.treebuilder.switchTo

internal object InHeadHelpers {

    fun toAttributeList(attributes: Map<String, String>): List<Attribute> {
        return attributes.map { (name, value) ->
            Attribute(
                namespace = null,
                prefix = null,
                localName = name,
                value = value
            )
        }
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#generic-raw-text-element-parsing-algorithm
     */
    fun followGenericRCDATAElementParsingAlgorithm(
        token: Token.StartTag,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        val attributes = toAttributeList(token.attributes)
        treeBuilder.insertElement(token.name, attributes = attributes)
        treeBuilder.switchTokenizerState(TokenizerStateType.RCDATA)
        treeBuilder.saveOriginalInsertionMode()
        return switchTo(Text)
    }

    fun followGenericRawTextElementParsingAlgorithm(
        token: Token.StartTag,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        val attributes = toAttributeList(token.attributes)
        treeBuilder.insertElement(token.name, attributes = attributes)
        treeBuilder.switchTokenizerState(TokenizerStateType.RAWTEXT)
        treeBuilder.saveOriginalInsertionMode()
        return switchTo(Text)
    }
}