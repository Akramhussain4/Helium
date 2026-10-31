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

    /** A start tag whose tag name, [tagName], is one of: "area", "br", "embed", "img", "keygen", "wbr". */
    fun handleAreaBrEmbedImgKeygenWbrStartTag(
        treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement(tagName, attributes = attributes)
        treeBuilder.popCurrentElement()
        treeBuilder.acknowledgeSelfClosingFlag()
        treeBuilder.setFramesetNotOk()
        return continueInCurrentMode()
    }

    /** A start tag whose tag name is "input". */
    fun handleInputStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        if (InBodyHelpers.isSelectContext(treeBuilder)) {
            treeBuilder.parseError()
            return continueInCurrentMode()
        }

        if (treeBuilder.hasElementInScope("select")) {
            treeBuilder.parseError()
            treeBuilder.popUntil("select")
        }

        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement("input", attributes = attributes)
        treeBuilder.popCurrentElement()
        treeBuilder.acknowledgeSelfClosingFlag()

        val typeAttr = attributes.find { it.localName == "type" }
        if (typeAttr == null || !typeAttr.value.equalsAsciiCaseInsensitive("hidden")) {
            treeBuilder.setFramesetNotOk()
        }
        return continueInCurrentMode()
    }

    /** A start tag whose tag name, [tagName], is one of: "param", "source", "track". */
    fun handleParamSourceTrackStartTag(
        treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        treeBuilder.insertElement(tagName, attributes = attributes)
        treeBuilder.popCurrentElement()
        treeBuilder.acknowledgeSelfClosingFlag()
        return continueInCurrentMode()
    }

    /** An end tag whose tag name is "br". */
    fun handleBrEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        treeBuilder.parseError()
        // Drop the attributes from the token, and act as if this was a "br" start tag token with no attributes
        return handleAreaBrEmbedImgKeygenWbrStartTag(treeBuilder, "br", emptyList())
    }
}
