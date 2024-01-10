package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes

internal class DecimalCharacterReferenceStart(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder,
    private val charRefCode: Int
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            in CharCodes.Zero.code..CharCodes.Nine.code -> {
                tempBuffer.append(codePoint.toChar())
                consumeNextChar {
                    DecimalCharacterReference(
                        returnState = returnState,
                        tempBuffer = tempBuffer,
                        charRefCode = charRefCode
                    )
                }
            }

            else -> {
                // TODO: Implement parse error
                when (returnState) {
                    is AttributeValueDoubleQuoted -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(currentToken, attributeName)
                    }

                    is AttributeValueSingleQuoted -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(currentToken, attributeName)
                    }

                    is AttributeValueUnquoted -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(currentToken, attributeName)
                    }

                    else -> {
                        val token = Token.Character(codePoint.toChar())
                        tokenizer.emitToken(token)
                    }
                }
                reconsumeCurrentChar { returnState }
            }
        }
    }

    private fun updateAttributeValue(currentToken: Token, attributeName: String) {
        when (currentToken) {
            is Token.StartTag -> {
                val currentValue = currentToken.attributes.getValue(attributeName)
                currentToken.attributes[attributeName] = currentValue + tempBuffer.toString()
            }

            else -> throw IllegalStateException("Expected a StartTag token, got $currentToken")
        }
    }
}