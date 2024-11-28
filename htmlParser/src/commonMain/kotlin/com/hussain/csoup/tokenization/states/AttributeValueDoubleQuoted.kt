package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS

internal class AttributeValueDoubleQuoted(
    val currentTagToken: Token,
    val currentAttributeName: String
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.IS(Chars.QuotationMark) -> consumeNextChar {
                AfterAttributeValueQuoted(currentTagToken)
            }

            codePoint.IS(Chars.Ampersand) -> consumeNextChar {
                CharacterReference(
                    returnState = AttributeValueDoubleQuoted(
                        currentTagToken = currentTagToken,
                        currentAttributeName = currentAttributeName
                    )
                )
            }

            else -> {
                when (currentTagToken) {
                    is Token.StartTag -> {
                        val currentValue = currentTagToken.attributes.getValue(currentAttributeName)
                        currentTagToken.attributes[currentAttributeName] =
                            currentValue + codePoint.toChar()

                        consumeNextChar {
                            AttributeValueDoubleQuoted(
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