package com.theakram.helium.tree_builder

import com.theakram.helium.tokenization.Token

internal interface InsertionMode {
    fun process(token: Token, treeBuilder: TreeBuilder)
}