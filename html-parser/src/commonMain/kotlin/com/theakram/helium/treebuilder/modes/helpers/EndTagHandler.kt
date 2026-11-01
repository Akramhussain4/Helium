/*
 * MIT License
 *
 * Copyright (c) 2023-2026 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.helpers

import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.TreeConstructionConstants
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.modes.AfterBody
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo

internal object EndTagHandler {

    /** An end tag whose tag name is "body". */
    fun handleBodyEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        if (!treeBuilder.hasElementInScope("body")) {
            treeBuilder.parseError()
            return continueInCurrentMode()
        }
        if (InBodyHelpers.hasNodeNotAllowedAtEndOfBody(treeBuilder)) {
            treeBuilder.parseError()
        }
        return switchTo(AfterBody)
    }

    /** An end tag whose tag name is "html". */
    fun handleHtmlEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        if (!treeBuilder.hasElementInScope("body")) {
            treeBuilder.parseError()
            return continueInCurrentMode()
        }
        if (InBodyHelpers.hasNodeNotAllowedAtEndOfBody(treeBuilder)) {
            treeBuilder.parseError()
        }
        return reprocessIn(AfterBody)
    }

    /**
     * Any other end tag, whose tag name is [tagName]. The adoption agency algorithm acts as described here too.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-inbody
     */
    fun handleAnyOtherEndTag(treeBuilder: TreeBuilder, tagName: String): InsertionModeTransition {
        // Steps 1, 4 and 5: Initialize node to be the current node, then set it to the previous entry each time round
        for (node in treeBuilder.elementStack.asReversed()) {
            // Step 2:
            if (InBodyHelpers.isHtmlElement(node, tagName)) {
                // Step 2.1:
                InBodyHelpers.generateImpliedEndTags(treeBuilder, except = tagName)

                // Step 2.2:
                if (node !== treeBuilder.currentNode) {
                    treeBuilder.parseError()
                }

                // Step 2.3:
                treeBuilder.popUntilElement(node)
                return continueInCurrentMode()
            }

            // Step 3:
            if (TreeConstructionConstants.isSpecialElement(node)) {
                treeBuilder.parseError()
                return continueInCurrentMode()
            }
        }
        error("The html element at the top of the stack of open elements is in the special category")
    }
}
