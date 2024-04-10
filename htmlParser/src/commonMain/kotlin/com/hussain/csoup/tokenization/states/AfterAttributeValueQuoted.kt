package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.isWhiteSpace

internal class AfterAttributeValueQuoted(private val currentTagToken: Token) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isWhiteSpace() -> consumeNextChar { BeforeAttributeName(currentTagToken) }

            codePoint == CharCodes.Solidus.code -> consumeNextChar {
                SelfClosingStartTag(currentTagToken)
            }

            codePoint == CharCodes.GreaterThan.code -> {
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