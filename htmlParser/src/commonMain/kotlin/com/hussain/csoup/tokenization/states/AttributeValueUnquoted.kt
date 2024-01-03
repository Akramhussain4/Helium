package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal class AttributeValueUnquoted(
    private val currentTagToken: Token,
    private val currentAttributeName: String
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.Tab.code,
            CharCodes.LineFeed.code,
            CharCodes.FormFeed.code,
            CharCodes.Space.code -> consumeNextChar {
                BeforeAttributeName(
                    currentTagToken = currentTagToken
                )
            }

            CharCodes.Ampersand.code -> consumeNextChar { CharacterReference(this) }

            CharCodes.GreaterThan.code -> {
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            CharCodes.QuotationMark.code,
            CharCodes.Apostrophe.code,
            CharCodes.LessThan.code,
            CharCodes.Equals.code,
            CharCodes.GraveAccent.code -> {
                // TODO Implement parse error
                updateAttributeValue(codePoint)
            }

            else -> {
                updateAttributeValue(codePoint)
            }
        }
    }

    private fun updateAttributeValue(codePoint: Int): Pair<TokenizationState, Action.ConsumeNext> {
        return when (currentTagToken) {
            is Token.StartTag -> {
                val attributes = currentTagToken.attributes.toMutableMap()
                val updatedAttribute = attributes[currentAttributeName] + codePoint.toChar()
                attributes[currentAttributeName] = updatedAttribute

                val updatedToken = currentTagToken.copy(attributes = attributes)

                consumeNextChar {
                    AttributeValueDoubleQuoted(
                        updatedToken,
                        currentAttributeName
                    )
                }
            }

            else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
        }
    }
}