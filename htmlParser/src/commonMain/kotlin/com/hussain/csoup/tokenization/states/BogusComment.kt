package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes

internal class BogusComment(private val commentToken: Token.Comment) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.GreaterThan.code -> {
                tokenizer.emitToken(commentToken)
                consumeNextChar { Data }
            }

            else -> {
                val data = commentToken.data + codePoint.toChar()
                consumeNextChar { BogusComment(commentToken.copy(data = data)) }
            }
        }
    }
}