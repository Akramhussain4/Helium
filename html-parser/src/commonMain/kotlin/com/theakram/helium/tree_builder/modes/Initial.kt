package com.theakram.helium.tree_builder.modes

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tree_builder.InsertionMode
import com.theakram.helium.tree_builder.TreeBuilder

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