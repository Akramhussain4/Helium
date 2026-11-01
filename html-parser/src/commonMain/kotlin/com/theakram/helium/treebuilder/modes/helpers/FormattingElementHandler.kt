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

internal object FormattingElementHandler {

    /** A start tag whose tag name is "a". */
    fun handleAStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        val existingA = treeBuilder.activeFormattingElements.findLastElementByTagName("a")
        if (existingA != null) {
            treeBuilder.parseError()
            runAdoptionAgencyAlgorithm(treeBuilder, "a")
            // The adoption agency algorithm might not have removed it, if it is not in table scope
            treeBuilder.activeFormattingElements.remove(existingA)
            treeBuilder.removeElement(existingA)
        }

        treeBuilder.reconstructActiveFormattingElements()
        val element = treeBuilder.insertElement("a", attributes = attributes)
        treeBuilder.activeFormattingElements.push(element, attributes)
        return continueInCurrentMode()
    }

    /**
     * A start tag whose tag name is one of: "b", "big", "code", "em", "font", "i", "s", "small", "strike", "strong",
     * "tt", "u".
     */
    fun handleFormattingElementStartTag(
        treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        treeBuilder.reconstructActiveFormattingElements()
        val element = treeBuilder.insertElement(tagName, attributes = attributes)
        treeBuilder.activeFormattingElements.push(element, attributes)
        return continueInCurrentMode()
    }

    /** A start tag whose tag name is "nobr". */
    fun handleNobrStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        treeBuilder.reconstructActiveFormattingElements()

        if (treeBuilder.hasElementInScope("nobr")) {
            treeBuilder.parseError()
            runAdoptionAgencyAlgorithm(treeBuilder, "nobr")
            treeBuilder.reconstructActiveFormattingElements()
        }

        val element = treeBuilder.insertElement("nobr", attributes = attributes)
        treeBuilder.activeFormattingElements.push(element, attributes)
        return continueInCurrentMode()
    }

    /**
     * An end tag whose tag name is one of: "a", "b", "big", "code", "em", "font", "i", "nobr", "s", "small",
     * "strike", "strong", "tt", "u".
     */
    fun handleFormattingElementEndTag(treeBuilder: TreeBuilder, tagName: String): InsertionModeTransition {
        runAdoptionAgencyAlgorithm(treeBuilder, tagName)
        return continueInCurrentMode()
    }

    // Runs the adoption agency algorithm for a token whose tag name is subject, acting as described in the "any other
    // end tag" entry where the algorithm says to
    private fun runAdoptionAgencyAlgorithm(treeBuilder: TreeBuilder, subject: String) {
        if (!treeBuilder.runAdoptionAgency(subject)) {
            EndTagHandler.handleAnyOtherEndTag(treeBuilder, subject)
        }
    }
}
