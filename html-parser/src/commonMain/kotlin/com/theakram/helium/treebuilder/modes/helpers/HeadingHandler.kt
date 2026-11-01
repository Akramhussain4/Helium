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

internal object HeadingHandler {

    /** A start tag whose tag name is one of: "h1", "h2", "h3", "h4", "h5", "h6". */
    fun handleHeadingStartTag(
        treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }

        if (isHtmlHeading(treeBuilder.currentNode)) {
            treeBuilder.parseError()
            treeBuilder.popCurrentElement()
        }

        treeBuilder.insertElement(tagName, attributes = attributes)
        return continueInCurrentMode()
    }

    /** An end tag whose tag name, [tagName], is one of: "h1", "h2", "h3", "h4", "h5", "h6". */
    fun handleHeadingEndTag(treeBuilder: TreeBuilder, tagName: String): InsertionModeTransition {
        if (!treeBuilder.hasElementInScopeMatching(::isHtmlHeading)) {
            treeBuilder.parseError()
            return continueInCurrentMode()
        }

        InBodyHelpers.generateImpliedEndTags(treeBuilder)
        if (!InBodyHelpers.isHtmlElement(treeBuilder.currentNode, tagName)) {
            treeBuilder.parseError()
        }
        while (true) {
            val popped = checkNotNull(treeBuilder.popCurrentElement()) { "A heading in scope is on the stack" }
            if (isHtmlHeading(popped)) break
        }
        return continueInCurrentMode()
    }

    // An HTML element whose tag name is one of "h1", "h2", "h3", "h4", "h5", or "h6"
    private fun isHtmlHeading(node: TreeNode): Boolean =
        node.namespace == Namespaces.HTML && node.localName in TreeConstructionConstants.HEADING_ELEMENTS
}
