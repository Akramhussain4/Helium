package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.isWhiteSpace

internal class AfterAttributeName(
    private val currentTagToken: Token,
    private val currentAttributeName: String
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isWhiteSpace() -> consumeNextChar { this }

            codePoint.IS(Chars.Solidus) -> consumeNextChar {
                SelfClosingStartTag(currentTagToken)
            }

            codePoint.IS(Chars.Equals) -> consumeNextChar {
                BeforeAttributeValue(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            codePoint.IS(Chars.GreaterThan) -> {
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            else -> {
                addEmptyAttribute()
                reconsumeCurrentChar {
                    AttributeName(
                        currentTagToken = currentTagToken,
                        currentAttributeName = ""
                    )
                }
            }
        }
    }

    private fun addEmptyAttribute() {
        when (currentTagToken) {
            is Token.StartTag -> {
                currentTagToken.attributes[""] = ""
            }

            is Token.EndTag -> Unit

            else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
        }
    }
}