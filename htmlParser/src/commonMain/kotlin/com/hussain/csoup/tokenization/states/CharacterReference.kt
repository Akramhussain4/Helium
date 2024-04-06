package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.isAsciiAlphaNumeric

/**
https://html.spec.whatwg.org/multipage/parsing.html#character-reference-state
 */
internal class CharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState() {

    private val tempBuffer = StringBuilder()

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        tempBuffer.append(CharCodes.Ampersand.code.toChar())

        return when {
            codePoint.isAsciiAlphaNumeric() -> reconsumeCurrentChar {
                NamedCharacterReference(returnState, tempBuffer)
            }

            codePoint == CharCodes.NumberSign.code -> {
                tempBuffer.append(codePoint.toChar())
                consumeNextChar { NumericCharacterReference(returnState, tempBuffer) }
            }

            else -> {
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