/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.inbody

import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TokenizerStateType
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode

internal object BlockElementHandler {

    fun handleBlockElement(
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

    fun handlePreElement(
        treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }

        treeBuilder.insertElement(tagName, attributes = attributes)

        // TODO: If the next token is a U+000A LINE FEED (LF) character token, then ignore that token
        // This requires peeking at the next token, which is not currently supported

        treeBuilder.setFramesetNotOk()
        return continueInCurrentMode()
    }

    fun handlePlaintextElement(
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

    fun handleHrElement(
        treeBuilder: TreeBuilder,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        if (treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }
        treeBuilder.insertElement("hr", attributes = attributes)
        treeBuilder.popCurrentElement()
        treeBuilder.setFramesetNotOk()
        return continueInCurrentMode()
    }

    fun handlePEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        if (!treeBuilder.hasElementInButtonScope("p")) {
            // Parse error
            treeBuilder.insertElement("p", attributes = emptyList())
        }
        InBodyHelpers.closePElement(treeBuilder)
        return continueInCurrentMode()
    }

    fun handleBlockEndTag(treeBuilder: TreeBuilder, tagName: String): InsertionModeTransition {
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
        return continueInCurrentMode()
    }
}
