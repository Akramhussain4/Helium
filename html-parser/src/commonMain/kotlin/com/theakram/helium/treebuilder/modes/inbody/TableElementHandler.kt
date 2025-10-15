/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.inbody

import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.QuirksMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.modes.InTable
import com.theakram.helium.treebuilder.switchTo

internal object TableElementHandler {

    fun handleTableStartTag(treeBuilder: TreeBuilder, attributes: List<Attribute>): InsertionModeTransition {
        if (treeBuilder.document.quirksMode != QuirksMode.QUIRKS &&
            treeBuilder.hasElementInButtonScope("p")) {
            InBodyHelpers.closePElement(treeBuilder)
        }

        treeBuilder.insertElement("table", attributes = attributes)
        treeBuilder.setFramesetNotOk()
        return switchTo(InTable)
    }
}
