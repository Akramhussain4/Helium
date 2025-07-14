package com.hussain.csoup.tree_builder.modes

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tree_builder.InsertionMode
import com.hussain.csoup.tree_builder.TreeBuilder
import com.hussain.csoup.util.isWhiteSpace

internal object Initial : InsertionMode {
    override fun process(token: Token, treeBuilder: TreeBuilder) {
        when (token) {
            is Token.Character -> {

            }

            else -> {
                treeBuilder.switch(to = BeforeHTML)
                treeBuilder.process(token)
            }
        }
    }
}