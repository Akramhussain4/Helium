package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.isWhiteSpace

internal class AttributeValueUnquoted(
    val currentTagToken: Token,
    val currentAttributeName: String
) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isWhiteSpace() -> consumeNextChar {
                BeforeAttributeName(currentTagToken = currentTagToken)
            }

            codePoint.IS(Chars.Ampersand) -> consumeNextChar {
                CharacterReference(this)
            }

            codePoint.IS(Chars.GreaterThan) -> {
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            codePoint.IS(
                Chars.QuotationMark,
                Chars.Apostrophe,
                Chars.LessThan,
                Chars.Equals,
                Chars.GraveAccent
            ) -> {
                tokenizer.logParseError("unexpected-character-in-unquoted-attribute-value")
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