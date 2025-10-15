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

internal object FormattingElementHandler {

    fun handleAStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        val existingA = treeBuilder.activeFormattingElements.findLastElementByTagName("a")
        if (existingA != null) {
            // Parse error
            treeBuilder.runAdoptionAgency("a")
            treeBuilder.activeFormattingElements.remove(existingA)
            treeBuilder.removeElement(existingA)
        }

        treeBuilder.reconstructActiveFormattingElements()
        val element = treeBuilder.insertElement("a", attributes = attributes)
        treeBuilder.activeFormattingElements.push(element)
        return continueInCurrentMode()
    }

    fun handleFormattingElementStartTag(
        treeBuilder: TreeBuilder,
        tagName: String,
        attributes: List<Attribute>
    ): InsertionModeTransition {
        treeBuilder.reconstructActiveFormattingElements()
        val element = treeBuilder.insertElement(tagName, attributes = attributes)
        treeBuilder.activeFormattingElements.push(element)
        return continueInCurrentMode()
    }

    fun handleNobrStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        treeBuilder.reconstructActiveFormattingElements()

        if (treeBuilder.hasElementInScope("nobr")) {
            // Parse error
            treeBuilder.runAdoptionAgency("nobr")
            treeBuilder.reconstructActiveFormattingElements()
        }

        val element = treeBuilder.insertElement("nobr", attributes = attributes)
        treeBuilder.activeFormattingElements.push(element)
        return continueInCurrentMode()
    }

    fun handleFormattingElementEndTag(treeBuilder: TreeBuilder, tagName: String): InsertionModeTransition {
        treeBuilder.runAdoptionAgency(tagName)
        return continueInCurrentMode()
    }
}
