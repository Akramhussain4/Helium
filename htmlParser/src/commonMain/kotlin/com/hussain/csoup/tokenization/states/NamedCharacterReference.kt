package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer

internal data object NamedCharacterReference : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        TODO("Not yet implemented")
    }
}
