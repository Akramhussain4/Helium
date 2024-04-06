package com.hussain.csoup.tokenization.states

import com.hussain.csoup.entity.NumCharRef
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.isNonCharacter
import com.hussain.csoup.util.isSurrogate

internal class NumericCharacterReferenceEnd(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        val number = tokenizer.charRefCode
        when {
            number == 0x00 -> {
                // TODO Implement parse error
                tokenizer.charRefCode = 0xFFFD
            }

            number > 0x10FFFF -> {
                // TODO Implement parse error
                tokenizer.charRefCode = 0xFFFD
            }

            number.isSurrogate() -> {
                // TODO Implement parse error
                tokenizer.charRefCode = 0xFFFD
            }

            number.isNonCharacter() -> {
                // TODO Implement parse error
            }
        }

        if (number == 0x0D || (number.toChar().isISOControl() && !number.toChar().isWhitespace())) {
            // TODO Implement parse error
            if (NumCharRef.containsKey(number)) {
                tokenizer.charRefCode = NumCharRef[number]!!
            }
        }

        tempBuffer.clear()
        tempBuffer.append(tokenizer.charRefCode.toChar())
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
        return consumeNextChar { returnState }
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