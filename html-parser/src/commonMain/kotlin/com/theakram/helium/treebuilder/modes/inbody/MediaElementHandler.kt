/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.inbody

import com.theakram.helium.tree.Attribute
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode

internal object MediaElementHandler {

    fun handleAreaStartTag(treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement(tagName, attributes = attributes)
        treeBuilder.popCurrentElement()
        treeBuilder.setFramesetNotOk()
        return continueInCurrentMode()
    }

    fun handleInputStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        // If fragment case AND context element is select:
        //   Parse error, ignore, return
        // TODO: Implement fragment case detection

        // If select in scope:
        if (treeBuilder.hasElementInScope("select")) {
            // Parse error
            // Pop elements until select popped
            treeBuilder.popUntil("select")
            return continueInCurrentMode()
        }

        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement("input", attributes = attributes)
        treeBuilder.popCurrentElement()

        // If NOT type="hidden": set frameset-ok
        val typeAttr = attributes.find { it.localName.equals("type", ignoreCase = true) }
        if (typeAttr == null || !typeAttr.value.equals("hidden", ignoreCase = true)) {
            treeBuilder.setFramesetNotOk()
        }
        return continueInCurrentMode()
    }

    fun handleParamStartTag(treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        treeBuilder.insertElement(tagName, attributes = attributes)
        treeBuilder.popCurrentElement()
        return continueInCurrentMode()
    }

    fun handleImageStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        // Parse error
        return handleAreaStartTag(treeBuilder, "img", attributes)
    }

    fun handleBrEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        // Parse error
        return handleAreaStartTag(treeBuilder, "br", emptyList())
    }
}
