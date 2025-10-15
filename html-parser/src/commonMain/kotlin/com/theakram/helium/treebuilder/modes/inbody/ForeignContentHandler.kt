/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.inbody

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode

internal object ForeignContentHandler {

    fun handleMathStartTag(treeBuilder: TreeBuilder, token: Token.StartTag): InsertionModeTransition {
        treeBuilder.reconstructActiveFormattingElements()

        // TODO: Adjust MathML attributes for the token
        // TODO: Adjust foreign attributes for the token

        val element = treeBuilder.insertElement(
            "math",
            Namespaces.MATHML,
            InBodyHelpers.toAttributeList(token.attributes)
        )

        if (token.selfClosing) {
            treeBuilder.popCurrentElement()
        }
        return continueInCurrentMode()
    }

    fun handleSvgStartTag(treeBuilder: TreeBuilder, token: Token.StartTag): InsertionModeTransition {
        treeBuilder.reconstructActiveFormattingElements()

        // TODO: Adjust SVG attributes for the token
        // TODO: Adjust foreign attributes for the token

        val element = treeBuilder.insertElement(
            "svg",
            Namespaces.SVG,
            InBodyHelpers.toAttributeList(token.attributes)
        )

        if (token.selfClosing) {
            treeBuilder.popCurrentElement()
        }
        return continueInCurrentMode()
    }
}
