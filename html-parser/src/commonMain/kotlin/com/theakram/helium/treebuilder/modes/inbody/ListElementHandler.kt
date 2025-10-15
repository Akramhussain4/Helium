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

internal object ListElementHandler {

    fun handleLiStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        treeBuilder.setFramesetNotOk()

        val stack = treeBuilder.elementStack
        for (i in stack.lastIndex downTo 0) {
            val node = stack[i]
            if (node.namespace == Namespaces.HTML && node.localName == "li") {
                InBodyHelpers.generateImpliedEndTags(treeBuilder, except = "li")
                if (treeBuilder.currentNode.localName != "li") {
                    // Parse error
                }
                treeBuilder.popUntil("li")
                break
            }

            if (TreeConstructionConstants.isSpecialElement(node) &&
                node.localName !in setOf("address", "div", "p")) {
                break
            }
        }

        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }

        treeBuilder.insertElement("li", attributes = attributes)
        return continueInCurrentMode()
    }

    fun handleDdDtStartTag(
        treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        treeBuilder.setFramesetNotOk()

        val stack = treeBuilder.elementStack
        for (i in stack.lastIndex downTo 0) {
            val node = stack[i]
            val nodeName = node.localName

            if (node.namespace == Namespaces.HTML && nodeName == "dd") {
                InBodyHelpers.generateImpliedEndTags(treeBuilder, except = "dd")
                if (treeBuilder.currentNode.localName != "dd") {
                    // Parse error
                }
                treeBuilder.popUntil("dd")
                break
            }

            if (node.namespace == Namespaces.HTML && nodeName == "dt") {
                InBodyHelpers.generateImpliedEndTags(treeBuilder, except = "dt")
                if (treeBuilder.currentNode.localName != "dt") {
                    // Parse error
                }
                treeBuilder.popUntil("dt")
                break
            }

            if (TreeConstructionConstants.isSpecialElement(node) &&
                nodeName !in setOf("address", "div", "p")) {
                break
            }
        }

        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }

        treeBuilder.insertElement(tagName, attributes = attributes)
        return continueInCurrentMode()
    }

    fun handleLiEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        if (!treeBuilder.hasElementInListItemScope("li")) {
            // Parse error
            return continueInCurrentMode()
        }

        InBodyHelpers.generateImpliedEndTags(treeBuilder, except = "li")

        if (treeBuilder.currentNode.localName != "li" ||
            treeBuilder.currentNode.namespace != Namespaces.HTML) {
            // Parse error
        }

        treeBuilder.popUntil("li")
        return continueInCurrentMode()
    }

    fun handleDdDtEndTag(treeBuilder: TreeBuilder, tagName: String): InsertionModeTransition {
        if (!treeBuilder.hasElementInScope(tagName)) {
            // Parse error
            return continueInCurrentMode()
        }

        InBodyHelpers.generateImpliedEndTags(treeBuilder, except = tagName)

        if (treeBuilder.currentNode.localName != tagName ||
            treeBuilder.currentNode.namespace != Namespaces.HTML) {
            // Parse error
        }

        treeBuilder.popUntil(tagName)
        return continueInCurrentMode()
    }
}
