/*
 * MIT License
 *
 * Copyright (c) 2023-2026 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.helpers

import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode

internal object FormElementHandler {

    fun handleFormStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        if (treeBuilder.formElement != null && !treeBuilder.isParsingTemplateContents) {
            // Parse error
            return continueInCurrentMode()
        }

        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }

        val formElement = treeBuilder.insertElement("form", attributes = attributes)
        if (!treeBuilder.isParsingTemplateContents) {
            treeBuilder.formElement = formElement
        }
        return continueInCurrentMode()
    }

    fun handleFormEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        if (!treeBuilder.isParsingTemplateContents) {
            val node = treeBuilder.formElement
            treeBuilder.formElement = null
            if (node == null || !treeBuilder.hasElementInScope(node)) {
                // Parse error
                return continueInCurrentMode()
            }
            InBodyHelpers.generateImpliedEndTags(treeBuilder)
            if (treeBuilder.currentNode !== node) {
                // Parse error
            }
            treeBuilder.removeElement(node)
        } else {
            if (!treeBuilder.hasElementInScope("form")) {
                // Parse error
                return continueInCurrentMode()
            }
            InBodyHelpers.generateImpliedEndTags(treeBuilder)
            if (treeBuilder.currentNode.localName != "form" ||
                treeBuilder.currentNode.namespace != Namespaces.HTML) {
                // Parse error
            }
            treeBuilder.popUntil("form")
        }
        return continueInCurrentMode()
    }
}
