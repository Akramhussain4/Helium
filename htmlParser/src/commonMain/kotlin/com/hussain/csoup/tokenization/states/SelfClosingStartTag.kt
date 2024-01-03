package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes

internal class SelfClosingStartTag(private val currentTagToken: Token) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.GreaterThan.code -> {
                when (currentTagToken) {
                    is Token.StartTag -> {
                        val token = currentTagToken.copy(selfClosing = true)
                        tokenizer.emitToken(token)
                    }

                    else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
                }
                consumeNextChar { Data }
            }

            else -> {
                // TODO Implement parse error
                reconsumeCurrentChar { BeforeAttributeName(currentTagToken) }
            }
        }
    }
}