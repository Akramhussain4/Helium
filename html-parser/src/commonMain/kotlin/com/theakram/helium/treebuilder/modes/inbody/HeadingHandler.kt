/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.inbody

import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.TreeConstructionConstants
import com.theakram.helium.treebuilder.continueInCurrentMode

internal object HeadingHandler {

    fun handleHeadingStartTag(
        treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }

        val currentNode = treeBuilder.currentNode
        if (currentNode.namespace == Namespaces.HTML &&
            currentNode.localName in TreeConstructionConstants.HEADING_ELEMENTS) {
            // Parse error
            treeBuilder.popCurrentElement()
        }

        treeBuilder.insertElement(tagName, attributes = attributes)
        return continueInCurrentMode()
    }

    fun handleHeadingEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        val hasHeadingInScope = TreeConstructionConstants.HEADING_ELEMENTS.any {
            treeBuilder.hasElementInScope(it)
        }

        if (!hasHeadingInScope) {
            // Parse error
            return continueInCurrentMode()
        }

        InBodyHelpers.generateImpliedEndTags(treeBuilder)
        val currentNode = treeBuilder.currentNode
        if (currentNode.namespace != Namespaces.HTML ||
            currentNode.localName !in TreeConstructionConstants.HEADING_ELEMENTS) {
            // Parse error
        }
        while (true) {
            val popped = treeBuilder.popCurrentElement()
            if (popped?.namespace == Namespaces.HTML &&
                popped.localName in TreeConstructionConstants.HEADING_ELEMENTS) {
                break
            }
        }
        return continueInCurrentMode()
    }
}
