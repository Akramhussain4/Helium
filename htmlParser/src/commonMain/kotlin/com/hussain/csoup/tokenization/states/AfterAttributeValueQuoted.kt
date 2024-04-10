package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.isWhiteSpace

internal class AfterAttributeValueQuoted(private val currentTagToken: Token) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isWhiteSpace() -> consumeNextChar { BeforeAttributeName(currentTagToken) }

            codePoint.IS(CharCodes.Solidus) -> consumeNextChar {
                SelfClosingStartTag(currentTagToken)
            }

            codePoint.IS(CharCodes.GreaterThan) -> {
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            else -> {
                tokenizer.logParseError("missing-whitespace-between-attributes")
                reconsumeCurrentChar { BeforeAttributeName(currentTagToken) }
            }
        }
    }
}