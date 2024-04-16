package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS

internal class AttributeValueSingleQuoted(
    val currentTagToken: Token,
    val currentAttributeName: String
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.IS(Chars.Apostrophe) -> consumeNextChar {
                AfterAttributeValueQuoted(currentTagToken)
            }

            codePoint.IS(Chars.Ampersand) -> {
                val returnState = AttributeValueSingleQuoted(currentTagToken, currentAttributeName)
                consumeNextChar { CharacterReference(returnState) }
            }

            else -> {
                when (currentTagToken) {
                    is Token.StartTag -> {
                        val currentValue = currentTagToken.attributes.getValue(currentAttributeName)
                        currentTagToken.attributes[currentAttributeName] =
                            currentValue + codePoint.toChar()
                    }

                    is Token.EndTag -> Unit

                    else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
                }
                consumeNextChar {
                    AttributeValueSingleQuoted(
                        currentTagToken = currentTagToken,
                        currentAttributeName = currentAttributeName
                    )
                }
            }
        }
    }
}