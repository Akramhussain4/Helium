package com.theakram.helium.treebuilder

import com.theakram.helium.tokenization.Token

internal interface InsertionMode {
    fun process(token: Token, treeBuilder: TreeBuilder)
}