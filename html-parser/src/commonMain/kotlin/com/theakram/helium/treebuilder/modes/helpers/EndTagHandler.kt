/*
 * MIT License
 *
 * Copyright (c) 2023-2026 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.helpers

import com.theakram.helium.tree.Namespaces
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.TreeConstructionConstants
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.modes.AfterBody
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo

internal object EndTagHandler {

    fun handleBodyEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        if (!treeBuilder.hasElementInScope("body")) {
            // Parse error
            return continueInCurrentMode()
        }

        val allowedElements = setOf(
            "dd", "dt", "li", "optgroup", "option", "p", "rb", "rp", "rt", "rtc",
            "tbody", "td", "tfoot", "th", "thead", "tr", "body", "html"
        )

        val hasInvalidElement = treeBuilder.elementStack.any { node ->
            node.namespace == Namespaces.HTML && node.localName !in allowedElements
        }

        if (hasInvalidElement) {
            // Parse error
        }
        return switchTo(AfterBody)
    }

    fun handleHtmlEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        if (!treeBuilder.hasElementInScope("body")) {
            // Parse error
            return continueInCurrentMode()
        }

        val allowedElements = setOf(
            "dd", "dt", "li", "optgroup", "option", "p", "rb", "rp", "rt", "rtc",
            "tbody", "td", "tfoot", "th", "thead", "tr", "body", "html"
        )

        val hasInvalidElement = treeBuilder.elementStack.any { node ->
            node.namespace == Namespaces.HTML && node.localName !in allowedElements
        }

        if (hasInvalidElement) {
            // Parse error
        }
        return reprocessIn(AfterBody)
    }

    fun handleAnyOtherEndTag(treeBuilder: TreeBuilder, tagName: String): InsertionModeTransition {
        val stack = treeBuilder.elementStack
        for (i in stack.lastIndex downTo 0) {
            val node = stack[i]
            if (node.namespace == Namespaces.HTML &&
                node.localName == tagName) {
                InBodyHelpers.generateImpliedEndTags(treeBuilder, except = tagName)
                if (treeBuilder.currentNode !== node) {
                    // Parse error
                }
                while (treeBuilder.currentNode !== node && treeBuilder.elementStack.isNotEmpty()) {
                    treeBuilder.popCurrentElement()
                }
                if (treeBuilder.elementStack.isNotEmpty()) {
                    treeBuilder.popCurrentElement()
                }
                return continueInCurrentMode()
            }

            if (TreeConstructionConstants.isSpecialElement(node)) {
                // Parse error
                return continueInCurrentMode()
            }
        }
        return continueInCurrentMode()
    }
}
