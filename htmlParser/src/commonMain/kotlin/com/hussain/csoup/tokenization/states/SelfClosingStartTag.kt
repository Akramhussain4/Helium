package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.Chars

internal class SelfClosingStartTag(private val currentTagToken: Token) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.IS(Chars.GreaterThan) -> {
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
                tokenizer.logParseError("unexpected-solidus-in-tag")
                reconsumeCurrentChar { BeforeAttributeName(currentTagToken) }
            }
        }
    }
}