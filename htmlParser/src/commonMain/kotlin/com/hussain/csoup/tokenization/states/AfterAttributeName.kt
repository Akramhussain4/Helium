package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal class AfterAttributeName(
    private val currentTagToken: Token,
    private val currentAttributeName: String
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.Tab.code,
            CharCodes.LineFeed.code,
            CharCodes.FormFeed.code,
            CharCodes.Space.code -> consumeNextChar { this }

            CharCodes.Solidus.code -> consumeNextChar { SelfClosingStartTag }

            CharCodes.Equals.code -> consumeNextChar {
                BeforeAttributeValue(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            CharCodes.GreaterThan.code -> {
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            else -> {
                val emptyAttribute = "" to ""
                val updatedToken = updateCurrentStartToken(emptyAttribute)
                reconsumeCurrentChar {
                    AttributeName(
                        currentTagToken = updatedToken,
                        currentAttributeName = ""
                    )
                }
            }
        }
    }

    private fun updateCurrentStartToken(newAttribute: Pair<String, String>): Token.StartTag {
        return when (currentTagToken) {
            is Token.StartTag -> {
                val updatedAttributes = currentTagToken.attributes.plus(newAttribute).toMutableMap()
                currentTagToken.copy(attributes = updatedAttributes)
            }

            else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
        }
    }
}