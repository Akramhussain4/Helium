package com.theakram.helium.treebuilder.modes

import com.theakram.helium.tokenization.Token
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.TreeBuilder

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