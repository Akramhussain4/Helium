package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal class AttributeValueUnquoted(
    val currentTagToken: Token,
    val currentAttributeName: String
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.Tab.code,
            CharCodes.LineFeed.code,
            CharCodes.FormFeed.code,
            CharCodes.Space.code -> consumeNextChar {
                BeforeAttributeName(currentTagToken = currentTagToken)
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
                consumeNextChar { this }
            }

            else -> {
                updateAttributeValue(codePoint)
                consumeNextChar { this }
            }
        }
    }

    private fun updateAttributeValue(codePoint: Int) {
        when (currentTagToken) {
            is Token.StartTag -> {
                val currentValue = currentTagToken.attributes.getValue(currentAttributeName)
                currentTagToken.attributes[currentAttributeName] =
                    currentValue + codePoint.toChar()
            }

            else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
        }
    }
}