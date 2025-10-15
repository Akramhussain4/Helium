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
import com.theakram.helium.treebuilder.continueInCurrentMode

internal object RubyElementHandler {

    fun handleRbStartTag(treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        if (treeBuilder.hasElementInScope("ruby")) {
            InBodyHelpers.generateImpliedEndTags(treeBuilder)
            if (treeBuilder.currentNode.localName != "ruby" ||
                treeBuilder.currentNode.namespace != Namespaces.HTML) {
                // Parse error
            }
        }
        treeBuilder.insertElement(tagName, attributes = attributes)
        return continueInCurrentMode()
    }

    fun handleRpStartTag(treeBuilder: TreeBuilder,
                         tagName: String,
                         attributes: List<Attribute>
    ): InsertionModeTransition {
        if (treeBuilder.hasElementInScope("ruby")) {
            InBodyHelpers.generateImpliedEndTags(treeBuilder, except = "rtc")
            val currentNode = treeBuilder.currentNode
            if (currentNode.namespace == Namespaces.HTML &&
                currentNode.localName !in setOf("rtc", "ruby")) {
                // Parse error
            }
        }
        treeBuilder.insertElement(tagName, attributes = attributes)
        return continueInCurrentMode()
    }
}
