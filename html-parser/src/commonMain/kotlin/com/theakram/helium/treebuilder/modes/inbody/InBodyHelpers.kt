/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.inbody

import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.treebuilder.TokenizerStateType
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.TreeConstructionConstants

internal object InBodyHelpers {

    fun closePElement(treeBuilder: TreeBuilder) {
        generateImpliedEndTags(treeBuilder, except = "p")
        if (treeBuilder.currentNode.localName != "p" ||
            treeBuilder.currentNode.namespace != Namespaces.HTML) {
            // Parse error
        }
        treeBuilder.popUntil("p")
    }

    fun generateImpliedEndTags(treeBuilder: TreeBuilder, except: String? = null) {
        while (treeBuilder.elementStack.isNotEmpty()) {
            val currentNode = treeBuilder.currentNode
            if (currentNode.namespace == Namespaces.HTML &&
                currentNode.localName in TreeConstructionConstants.IMPLIED_END_TAG_ELEMENTS &&
                currentNode.localName != except) {
                treeBuilder.popCurrentElement()
            } else {
                break
            }
        }
    }

    fun parseGenericRawTextElement(
        tagName: String,
        attributes: List<Attribute>,
        treeBuilder: TreeBuilder
    ) {
        treeBuilder.insertElement(tagName, attributes = attributes)
        treeBuilder.switchTokenizerState(TokenizerStateType.RAWTEXT)
        treeBuilder.saveOriginalInsertionMode()
    }

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
}
