/*
 * MIT License
 *
 * Copyright (c) 2023-2026 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.helpers

import com.theakram.helium.tree.Attribute
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TokenizerStateType
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode

internal object BlockElementHandler {

    /**
     * A start tag whose tag name is one of: "address", "article", "aside", "blockquote", "center", "details",
     * "dialog", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "main", "menu",
     * "nav", "ol", "p", "search", "section", "summary", "ul".
     */
    fun handleBlockStartTag(
        treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }

        treeBuilder.insertElement(tagName, attributes = attributes)
        return continueInCurrentMode()
    }

    /** A start tag whose tag name is one of: "pre", "listing". */
    fun handlePreListingStartTag(
        treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }

        treeBuilder.insertElement(tagName, attributes = attributes)

        treeBuilder.ignoreNextLineFeed()

        treeBuilder.setFramesetNotOk()
        return continueInCurrentMode()
    }

    /** A start tag whose tag name is "plaintext". */
    fun handlePlaintextStartTag(
        treeBuilder: TreeBuilder,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }
        treeBuilder.insertElement("plaintext", attributes = attributes)
        treeBuilder.switchTokenizerState(TokenizerStateType.PLAINTEXT)
        return continueInCurrentMode()
    }

    /** A start tag whose tag name is "hr". */
    fun handleHrStartTag(
        treeBuilder: TreeBuilder,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }

        if (treeBuilder.hasElementInScope("select")) {
            InBodyHelpers.generateImpliedEndTags(treeBuilder)
            if (treeBuilder.hasElementInScope("option") || treeBuilder.hasElementInScope("optgroup")) {
                treeBuilder.parseError()
            }
        }

        treeBuilder.insertElement("hr", attributes = attributes)
        treeBuilder.popCurrentElement()
        treeBuilder.acknowledgeSelfClosingFlag()
        treeBuilder.setFramesetNotOk()
        return continueInCurrentMode()
    }

    /** An end tag whose tag name is "p". */
    fun handlePEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        if (!treeBuilder.hasElementInButtonScope("p")) {
            treeBuilder.parseError()
            treeBuilder.insertElement("p", attributes = emptyList())
        }
        InBodyHelpers.closePElement(treeBuilder)
        return continueInCurrentMode()
    }

    /**
     * An end tag whose tag name is one of: "address", "article", "aside", "blockquote", "button", "center",
     * "details", "dialog", "dir", "div", "dl", "fieldset", "figcaption", "figure", "footer", "header", "hgroup",
     * "listing", "main", "menu", "nav", "ol", "pre", "search", "section", "select", "summary", "ul".
     */
    fun handleBlockEndTag(treeBuilder: TreeBuilder, tagName: String): InsertionModeTransition {
        if (!treeBuilder.hasElementInScope(tagName)) {
            treeBuilder.parseError()
            return continueInCurrentMode()
        }
        InBodyHelpers.generateImpliedEndTags(treeBuilder)
        if (!InBodyHelpers.isHtmlElement(treeBuilder.currentNode, tagName)) {
            treeBuilder.parseError()
        }
        treeBuilder.popUntil(tagName)
        return continueInCurrentMode()
    }
}
