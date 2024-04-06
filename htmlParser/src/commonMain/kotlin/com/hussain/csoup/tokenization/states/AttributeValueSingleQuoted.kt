package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal class AttributeValueSingleQuoted(
    val currentTagToken: Token,
    val currentAttributeName: String
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.Apostrophe.code -> consumeNextChar { AfterAttributeValueQuoted(currentTagToken) }

            CharCodes.Ampersand.code -> {
                val returnState = AttributeValueSingleQuoted(currentTagToken, currentAttributeName)
                consumeNextChar { CharacterReference(returnState) }
            }

            else -> {
                when (currentTagToken) {
                    is Token.StartTag -> {
                        val currentValue = currentTagToken.attributes.getValue(currentAttributeName)
                        currentTagToken.attributes[currentAttributeName] =
                            currentValue + codePoint.toChar()

                        consumeNextChar {
                            AttributeValueSingleQuoted(
                                currentTagToken = currentTagToken,
                                currentAttributeName = currentAttributeName
                            )
                        }
                    }

                    else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
                }
            }
        }
    }
}