/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.inhead

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
