package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer

internal class HexadecimalCharacterReference(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        TODO("Not yet implemented")
    }
}