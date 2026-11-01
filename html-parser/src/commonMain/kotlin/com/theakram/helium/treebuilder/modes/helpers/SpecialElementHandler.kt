/*
 * MIT License
 *
 * Copyright (c) 2023-2026 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.helpers

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tree.Attribute
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TokenizerStateType
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.modes.Text
import com.theakram.helium.treebuilder.switchTo

internal object SpecialElementHandler {

    /** A start tag whose tag name is "button". */
    fun handleButtonStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        if (treeBuilder.hasElementInScope("button")) {
            treeBuilder.parseError()
            InBodyHelpers.generateImpliedEndTags(treeBuilder)
            treeBuilder.popUntil("button")
        }

        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement("button", attributes = attributes)
        treeBuilder.setFramesetNotOk()
        return continueInCurrentMode()
    }

    /** A start tag whose tag name, [tagName], is one of: "applet", "marquee", "object". */
    fun handleAppletMarqueeObjectStartTag(
        treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement(tagName, attributes = attributes)
        treeBuilder.activeFormattingElements.insertMarker()
        treeBuilder.setFramesetNotOk()
        return continueInCurrentMode()
    }

    /** A start tag whose tag name is "textarea". */
    fun handleTextareaStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        treeBuilder.insertElement("textarea", attributes = attributes)
        treeBuilder.ignoreNextLineFeed()
        treeBuilder.switchTokenizerState(TokenizerStateType.RCDATA)
        treeBuilder.saveOriginalInsertionMode()
        treeBuilder.setFramesetNotOk()
        return switchTo(Text)
    }

    /** A start tag whose tag name is "xmp". */
    fun handleXmpStartTag(treeBuilder: TreeBuilder, token: Token.StartTag): InsertionModeTransition {
        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }
        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.setFramesetNotOk()
        return InHeadHelpers.followGenericRawTextElementParsingAlgorithm(token, treeBuilder)
    }

    /** A start tag whose tag name is "iframe". */
    fun handleIframeStartTag(treeBuilder: TreeBuilder, token: Token.StartTag): InsertionModeTransition {
        treeBuilder.setFramesetNotOk()
        return InHeadHelpers.followGenericRawTextElementParsingAlgorithm(token, treeBuilder)
    }

    /** A start tag whose tag name is "noembed", or "noscript" if scripting mode is not Disabled. */
    fun handleNoembedNoscriptStartTag(treeBuilder: TreeBuilder, token: Token.StartTag): InsertionModeTransition =
        InHeadHelpers.followGenericRawTextElementParsingAlgorithm(token, treeBuilder)

    /** A start tag whose tag name is "select". */
    fun handleSelectStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        if (InBodyHelpers.isSelectContext(treeBuilder)) {
            treeBuilder.parseError()
            return continueInCurrentMode()
        }

        if (treeBuilder.hasElementInScope("select")) {
            treeBuilder.parseError()
            treeBuilder.popUntil("select")
            return continueInCurrentMode()
        }

        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement("select", attributes = attributes)
        treeBuilder.setFramesetNotOk()
        return continueInCurrentMode()
    }

    /** A start tag whose tag name is "option". */
    fun handleOptionStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        if (treeBuilder.hasElementInScope("select")) {
            InBodyHelpers.generateImpliedEndTags(treeBuilder, except = "optgroup")
            if (treeBuilder.hasElementInScope("option")) {
                treeBuilder.parseError()
            }
        } else if (InBodyHelpers.isHtmlElement(treeBuilder.currentNode, "option")) {
            treeBuilder.popCurrentElement()
        }
        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement("option", attributes = attributes)
        return continueInCurrentMode()
    }

    /** A start tag whose tag name is "optgroup". */
    fun handleOptgroupStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        if (treeBuilder.hasElementInScope("select")) {
            InBodyHelpers.generateImpliedEndTags(treeBuilder)
            if (treeBuilder.hasElementInScope("option") || treeBuilder.hasElementInScope("optgroup")) {
                treeBuilder.parseError()
            }
        } else if (InBodyHelpers.isHtmlElement(treeBuilder.currentNode, "option")) {
            treeBuilder.popCurrentElement()
        }
        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement("optgroup", attributes = attributes)
        return continueInCurrentMode()
    }

    /** An end tag whose tag name, [tagName], is one of: "applet", "marquee", "object". */
    fun handleAppletMarqueeObjectEndTag(treeBuilder: TreeBuilder, tagName: String): InsertionModeTransition {
        if (!treeBuilder.hasElementInScope(tagName)) {
            treeBuilder.parseError()
            return continueInCurrentMode()
        }

        InBodyHelpers.generateImpliedEndTags(treeBuilder)

        if (!InBodyHelpers.isHtmlElement(treeBuilder.currentNode, tagName)) {
            treeBuilder.parseError()
        }

        treeBuilder.popUntil(tagName)
        treeBuilder.activeFormattingElements.clearToLastMarker()
        return continueInCurrentMode()
    }
}
