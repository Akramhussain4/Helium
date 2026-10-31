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
import com.theakram.helium.util.equalsAsciiCaseInsensitive

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
        if (InBodyHelpers.isSelectContext(treeBuilder)) {
            // Parse error. Ignore the token.
            return continueInCurrentMode()
        }

        // An input closes an open select, then is inserted after it
        if (treeBuilder.hasElementInScope("select")) {
            // Parse error
            treeBuilder.popUntil("select")
        }

        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement("input", attributes = attributes)
        treeBuilder.popCurrentElement()

        // If NOT type="hidden": set frameset-ok
        val typeAttr = attributes.find { it.localName == "type" }
        if (typeAttr == null || !typeAttr.value.equalsAsciiCaseInsensitive("hidden")) {
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
