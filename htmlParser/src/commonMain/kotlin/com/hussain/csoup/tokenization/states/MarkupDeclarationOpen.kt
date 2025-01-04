package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer

internal data object MarkupDeclarationOpen : TokenizationState {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        TODO("Not yet implemented")
    }
}