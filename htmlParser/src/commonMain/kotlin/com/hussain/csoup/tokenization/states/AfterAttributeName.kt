package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.isWhiteSpace

internal class AfterAttributeName(
    private val currentTagToken: Token,
    private val currentAttributeName: String
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isWhiteSpace() -> consumeNextChar { this }

            codePoint == CharCodes.Solidus.code -> consumeNextChar {
                SelfClosingStartTag(currentTagToken)
            }

            codePoint == CharCodes.Equals.code -> consumeNextChar {
                BeforeAttributeValue(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            codePoint == CharCodes.GreaterThan.code -> {
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            else -> {
                val emptyAttribute = "" to ""
                addEmptyAttribute(emptyAttribute)
                reconsumeCurrentChar {
                    AttributeName(
                        currentTagToken = currentTagToken,
                        currentAttributeName = ""
                    )
                }
            }
        }
    }

    private fun addEmptyAttribute(newAttribute: Pair<String, String>) {
        when (currentTagToken) {
            is Token.StartTag -> {
                currentTagToken.attributes.plus(newAttribute).toMutableMap()
            }

            else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
        }
    }
}