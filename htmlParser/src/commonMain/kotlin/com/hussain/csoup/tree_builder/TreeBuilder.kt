package com.hussain.csoup.tree_builder

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tree_builder.modes.Initial

internal class TreeBuilder {
    private var insertionMode: InsertionMode = Initial

    fun process(token: Token) {
        insertionMode.process(token, this)
    }

    fun switch(to: InsertionMode) {
        insertionMode = to
    }
}