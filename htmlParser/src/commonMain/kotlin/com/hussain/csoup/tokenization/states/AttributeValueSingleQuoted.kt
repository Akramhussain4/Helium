package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal class AttributeValueSingleQuoted(
    private val currentTagToken: Token,
    private val currentAttributeName: String
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.Apostrophe.code -> consumeNextChar { AfterAttributeValueQuoted(currentTagToken) }

            CharCodes.Ampersand.code -> consumeNextChar { CharacterReference(this) }

            else -> {
                when (currentTagToken) {
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
    }
}