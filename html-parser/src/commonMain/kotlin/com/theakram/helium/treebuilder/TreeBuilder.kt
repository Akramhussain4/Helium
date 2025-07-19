package com.theakram.helium.treebuilder

import com.theakram.helium.tokenization.Token
import com.theakram.helium.treebuilder.modes.Initial

internal class TreeBuilder {
    private var insertionMode: InsertionMode = Initial

    fun process(token: Token) {
        insertionMode.process(token, this)
    }

    fun switch(to: InsertionMode) {
        insertionMode = to
    }
}