package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal class AfterAttributeValueQuoted(private val currentTagToken: Token) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.Tab.code,
            CharCodes.LineFeed.code,
            CharCodes.FormFeed.code,
            CharCodes.Space.code -> consumeNextChar { BeforeAttributeName(currentTagToken) }

            CharCodes.Solidus.code -> consumeNextChar { SelfClosingStartTag }

            CharCodes.GreaterThan.code -> {
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            else -> {
                // TODO Implement parse error
                reconsumeCurrentChar { BeforeAttributeName(currentTagToken) }
            }
        }
    }
}