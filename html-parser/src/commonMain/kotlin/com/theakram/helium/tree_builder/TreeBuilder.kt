package com.theakram.helium.tree_builder

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tree_builder.modes.Initial

internal class TreeBuilder {
    private var insertionMode: InsertionMode = Initial

    fun process(token: Token) {
        insertionMode.process(token, this)
    }

    fun switch(to: InsertionMode) {
        insertionMode = to
    }
}