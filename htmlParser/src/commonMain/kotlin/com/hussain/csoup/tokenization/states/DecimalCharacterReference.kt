package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer

internal class DecimalCharacterReference : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        TODO()
    }
}