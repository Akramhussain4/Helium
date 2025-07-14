package com.hussain.csoup.tree_builder

import com.hussain.csoup.tokenization.Token

internal interface InsertionMode {
    fun process(token: Token, treeBuilder: TreeBuilder)
}