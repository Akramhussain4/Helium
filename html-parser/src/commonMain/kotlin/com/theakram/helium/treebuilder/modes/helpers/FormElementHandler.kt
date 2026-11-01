/*
 * MIT License
 *
 * Copyright (c) 2023-2026 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.helpers

import com.theakram.helium.tree.Attribute
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode

internal object FormElementHandler {

    /** A start tag whose tag name is "form". */
    fun handleFormStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        if (treeBuilder.formElement != null && !treeBuilder.isParsingTemplateContents) {
            treeBuilder.parseError()
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

    /** An end tag whose tag name is "form". */
    fun handleFormEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        if (!treeBuilder.isParsingTemplateContents) {
            val node = treeBuilder.formElement
            treeBuilder.formElement = null
            if (node == null || !treeBuilder.hasElementInScope(node)) {
                treeBuilder.parseError()
                return continueInCurrentMode()
            }
            InBodyHelpers.generateImpliedEndTags(treeBuilder)
            if (treeBuilder.currentNode !== node) {
                treeBuilder.parseError()
            }
            treeBuilder.removeElement(node)
        } else {
            if (!treeBuilder.hasElementInScope("form")) {
                treeBuilder.parseError()
                return continueInCurrentMode()
            }
            InBodyHelpers.generateImpliedEndTags(treeBuilder)
            if (!InBodyHelpers.isHtmlElement(treeBuilder.currentNode, "form")) {
                treeBuilder.parseError()
            }
            treeBuilder.popUntil("form")
        }
        return continueInCurrentMode()
    }
}
