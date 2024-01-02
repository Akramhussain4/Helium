package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal class BogusComment(commentToken: Token.Comment) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        TODO("Not yet implemented")
    }
}