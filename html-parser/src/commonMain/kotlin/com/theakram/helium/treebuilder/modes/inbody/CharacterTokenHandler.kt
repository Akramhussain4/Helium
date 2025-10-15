/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.inbody

import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.util.isAsciiWhitespace

internal object CharacterTokenHandler {

    fun handleCharacter(treeBuilder: TreeBuilder, char: Char): InsertionModeTransition {
        return when {
            char == '\u0000' -> {
                // Parse error: unexpected null character
                continueInCurrentMode()
            }

            char.code.isAsciiWhitespace() -> {
                treeBuilder.reconstructActiveFormattingElements()
                treeBuilder.insertCharacter(char)
                continueInCurrentMode()
            }

            else -> {
                treeBuilder.reconstructActiveFormattingElements()
                treeBuilder.insertCharacter(char)
                treeBuilder.setFramesetNotOk()
                continueInCurrentMode()
            }
        }
    }

    fun handleComment(treeBuilder: TreeBuilder, data: String): InsertionModeTransition {
        treeBuilder.insertComment(data)
        return continueInCurrentMode()
    }

    fun handleDocType(): InsertionModeTransition {
        // Parse error: unexpected DOCTYPE in body
        return continueInCurrentMode()
    }
}
