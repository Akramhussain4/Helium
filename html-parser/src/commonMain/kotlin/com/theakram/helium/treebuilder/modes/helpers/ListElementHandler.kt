/*
 * MIT License
 *
 * Copyright (c) 2023-2026 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.helpers

import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.TreeConstructionConstants
import com.theakram.helium.treebuilder.continueInCurrentMode

internal object ListElementHandler {

    private val ADDRESS_DIV_P = setOf("address", "div", "p")

    /** A start tag whose tag name is "li". */
    fun handleLiStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        // Step 1:
        treeBuilder.setFramesetNotOk()

        // Steps 2-5:
        for (node in treeBuilder.elementStack.asReversed()) {
            // Step 3: Loop
            if (InBodyHelpers.isHtmlElement(node, "li")) {
                closeListItem(treeBuilder, "li")
                break
            }

            // Step 4:
            if (isSpecialButNotAddressDivOrP(node)) break
        }

        // Step 6: Done
        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }

        // Step 7:
        treeBuilder.insertElement("li", attributes = attributes)
        return continueInCurrentMode()
    }

    /** A start tag whose tag name, [tagName], is one of: "dd", "dt". */
    fun handleDdDtStartTag(
        treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        // Step 1:
        treeBuilder.setFramesetNotOk()

        // Steps 2-6:
        for (node in treeBuilder.elementStack.asReversed()) {
            // Step 3: Loop
            if (InBodyHelpers.isHtmlElement(node, "dd")) {
                closeListItem(treeBuilder, "dd")
                break
            }

            // Step 4:
            if (InBodyHelpers.isHtmlElement(node, "dt")) {
                closeListItem(treeBuilder, "dt")
                break
            }

            // Step 5:
            if (isSpecialButNotAddressDivOrP(node)) break
        }

        // Step 7: Done
        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }

        // Step 8:
        treeBuilder.insertElement(tagName, attributes = attributes)
        return continueInCurrentMode()
    }

    /** An end tag whose tag name is "li". */
    fun handleLiEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        if (!treeBuilder.hasElementInListItemScope("li")) {
            treeBuilder.parseError()
            return continueInCurrentMode()
        }

        closeListItem(treeBuilder, "li")
        return continueInCurrentMode()
    }

    /** An end tag whose tag name, [tagName], is one of: "dd", "dt". */
    fun handleDdDtEndTag(treeBuilder: TreeBuilder, tagName: String): InsertionModeTransition {
        if (!treeBuilder.hasElementInScope(tagName)) {
            treeBuilder.parseError()
            return continueInCurrentMode()
        }

        closeListItem(treeBuilder, tagName)
        return continueInCurrentMode()
    }

    // The substeps shared by the li, dd and dt start and end tags: generate implied end tags, except for HTML
    // elements with the tag name; if the current node is not such an element, this is a parse error; pop elements
    // until one has been popped
    private fun closeListItem(treeBuilder: TreeBuilder, tagName: String) {
        InBodyHelpers.generateImpliedEndTags(treeBuilder, except = tagName)
        if (!InBodyHelpers.isHtmlElement(treeBuilder.currentNode, tagName)) {
            treeBuilder.parseError()
        }
        treeBuilder.popUntil(tagName)
    }

    // In the special category, but not an address, div, or p element
    private fun isSpecialButNotAddressDivOrP(node: TreeNode): Boolean =
        TreeConstructionConstants.isSpecialElement(node) &&
            !(node.namespace == Namespaces.HTML && node.localName in ADDRESS_DIV_P)
}
