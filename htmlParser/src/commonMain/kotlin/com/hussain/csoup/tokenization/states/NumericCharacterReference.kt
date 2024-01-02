package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer

internal data object NumericCharacterReference: TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        TODO("Not yet implemented")
    }
}