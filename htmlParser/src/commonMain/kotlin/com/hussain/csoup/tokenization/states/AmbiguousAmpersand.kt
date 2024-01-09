package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.util.isAsciiAlphaNumeric

internal class AmbiguousAmpersand(
    private val returnState: TokenizationState,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isAsciiAlphaNumeric() -> {
                when (returnState) {
                    is AttributeValueDoubleQuoted -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(currentToken, attributeName, codePoint)
                    }

                    is AttributeValueSingleQuoted -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(currentToken, attributeName, codePoint)
                    }

                    is AttributeValueUnquoted -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(currentToken, attributeName, codePoint)
                    }

                    else -> {
                        val token = Token.Character(codePoint.toChar())
                        tokenizer.emitToken(token)
                    }
                }
                consumeNextChar { returnState }
            }

            codePoint == CharCodes.SemiColon.code -> {
                // TODO Implement parse error
                reconsumeCurrentChar { returnState }
            }

            else -> {
                reconsumeCurrentChar { returnState }
            }
        }
    }

    private fun updateAttributeValue(currentToken: Token, attributeName: String, codePoint: Int) {
        when (currentToken) {
            is Token.StartTag -> {
                val currentValue = currentToken.attributes.getValue(attributeName)
                currentToken.attributes[attributeName] = currentValue + codePoint.toChar()
            }

            else -> throw IllegalStateException("Expected a StartTag token, got $currentToken")
        }
    }
}