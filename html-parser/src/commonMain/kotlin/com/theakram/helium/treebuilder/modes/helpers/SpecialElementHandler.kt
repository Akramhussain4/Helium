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
import com.theakram.helium.treebuilder.TokenizerStateType
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.modes.Text
import com.theakram.helium.treebuilder.switchTo

internal object SpecialElementHandler {

    fun handleButtonStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        if (treeBuilder.hasElementInButtonScope("button")) {
            // Parse error
            InBodyHelpers.generateImpliedEndTags(treeBuilder)
            treeBuilder.popUntil("button")
        }

        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement("button", attributes = attributes)
        treeBuilder.setFramesetNotOk()
        return continueInCurrentMode()
    }

    fun handleAppletStartTag(treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement(tagName, attributes = attributes)
        treeBuilder.activeFormattingElements.insertMarker()
        treeBuilder.setFramesetNotOk()
        return continueInCurrentMode()
    }

    fun handleTextareaStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        treeBuilder.insertElement("textarea", attributes = attributes)
        // TODO: If the next token is a U+000A LINE FEED (LF) character token, then ignore that token
        treeBuilder.switchTokenizerState(TokenizerStateType.RCDATA)
        treeBuilder.saveOriginalInsertionMode()
        treeBuilder.setFramesetNotOk()
        return switchTo(Text)
    }

    fun handleXmpStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }
        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.setFramesetNotOk()
        InBodyHelpers.parseGenericRawTextElement("xmp", attributes, treeBuilder)
        return switchTo(Text)
    }

    fun handleIframeStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        treeBuilder.setFramesetNotOk()
        InBodyHelpers.parseGenericRawTextElement("iframe", attributes, treeBuilder)
        return switchTo(Text)
    }

    fun handleNoembedStartTag(treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        InBodyHelpers.parseGenericRawTextElement(tagName, attributes, treeBuilder)
        return switchTo(Text)
    }

    fun handleSelectStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        // TODO: Implement fragment case detection
        if (treeBuilder.hasElementInScope("select")) {
            treeBuilder.popUntil("select")
            return continueInCurrentMode()
        }

        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement("select", attributes = attributes)
        treeBuilder.setFramesetNotOk()
        return continueInCurrentMode()
    }

    fun handleOptionStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        if (treeBuilder.hasElementInScope("select")) {
            InBodyHelpers.generateImpliedEndTags(treeBuilder, except = "optgroup")
            if (treeBuilder.hasElementInScope("option")) {
                // Parse error
            }
        } else {
            if (treeBuilder.currentNode.localName == "option" &&
                treeBuilder.currentNode.namespace == Namespaces.HTML) {
                treeBuilder.popCurrentElement()
            }
        }
        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement("option", attributes = attributes)
        return continueInCurrentMode()
    }

    fun handleOptgroupStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        if (treeBuilder.hasElementInScope("select")) {
            InBodyHelpers.generateImpliedEndTags(treeBuilder)
            if (treeBuilder.hasElementInScope("option") || treeBuilder.hasElementInScope("optgroup")) {
                // Parse error
            }
        } else {
            if (treeBuilder.currentNode.localName == "option" &&
                treeBuilder.currentNode.namespace == Namespaces.HTML) {
                treeBuilder.popCurrentElement()
            }
        }
        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement("optgroup", attributes = attributes)
        return continueInCurrentMode()
    }

    fun handleAppletEndTag(treeBuilder: TreeBuilder, tagName: String): InsertionModeTransition {
        if (!treeBuilder.hasElementInScope(tagName)) {
            // Parse error
            return continueInCurrentMode()
        }

        InBodyHelpers.generateImpliedEndTags(treeBuilder)

        if (treeBuilder.currentNode.localName != tagName ||
            treeBuilder.currentNode.namespace != Namespaces.HTML) {
            // Parse error
        }

        treeBuilder.popUntil(tagName)
        treeBuilder.activeFormattingElements.clearToLastMarker()
        return continueInCurrentMode()
    }
}
