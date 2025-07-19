package com.theakram.helium.tokenization.states

import com.theakram.helium.tokenization.Tokenizer

internal data object MarkupDeclarationOpen : TokenizationState {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        TODO("Not yet implemented")
    }
}