package com.hussain.csoup.tokenization.states

import com.hussain.csoup.entity.CharRefMap.NamedChars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes

internal class NamedCharacterReference(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        tempBuffer.append(codePoint.toChar())
        when (codePoint) {
            in CharCodes.Zero.code..CharCodes.Nine.code,
            in CharCodes.UpperA.code..CharCodes.UpperZ.code,
            in CharCodes.LowerA.code..CharCodes.LowerZ.code -> {
                return consumeNextChar { this }
            }

            CharCodes.SemiColon.code -> {
                val entity = tempBuffer.toString()
                val replacement = NamedChars[entity]
                when (returnState) {
                    is AttributeValueDoubleQuoted -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(currentToken, attributeName, replacement)
                    }

                    is AttributeValueSingleQuoted -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(currentToken, attributeName, replacement)
                    }

                    is AttributeValueUnquoted -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(currentToken, attributeName, replacement)
                    }

                    else -> {
                        val token = Token.Character(replacement?.get(0) ?: ' ')
                        tokenizer.emitToken(token)
                    }
                }
                return consumeNextChar { returnState }
            }

            else -> {
                val token = Token.Character(CharCodes.Ampersand.code.toChar())
                tokenizer.emitToken(token)
                return reconsumeCurrentChar { returnState }
            }
        }
    }
}

private fun updateAttributeValue(currentToken: Token, attributeName: String, replacement: String?) {
    when (currentToken) {
        is Token.StartTag -> {
            val currentValue = currentToken.attributes.getValue(attributeName)
            currentToken.attributes[attributeName] = currentValue + replacement
        }

        else -> throw IllegalStateException("Expected a StartTag token, got $currentToken")
    }
}