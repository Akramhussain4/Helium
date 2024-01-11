package com.hussain.csoup.tokenization.states

import com.hussain.csoup.entity.NamedChars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.util.isAsciiAlphaNumeric

/**
 * https://html.spec.whatwg.org/multipage/parsing.html#named-character-reference-state
 */
internal class NamedCharacterReference(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        tempBuffer.append(codePoint.toChar())

        val matches = NamedChars.keys.filter { it.startsWith(tempBuffer.toString()) }

        return when {
            matches.isEmpty() -> {
                flushCodePoints(tokenizer)
                consumeNextChar { AmbiguousAmpersand(returnState) }
            }

            matches.size == 1 && matches.contains(tempBuffer.toString()) -> {
                val nextChar = tokenizer.peekNextChar().code
                if (
                    (returnState is AttributeValueDoubleQuoted ||
                            returnState is AttributeValueSingleQuoted ||
                            returnState is AttributeValueUnquoted) &&
                    tempBuffer.last().code != CharCodes.SemiColon.code
                    && (nextChar == CharCodes.Equals.code || nextChar.isAsciiAlphaNumeric())
                ) {
                    flushCodePoints(tokenizer)
                    return consumeNextChar { returnState }
                } else {
                    if (tempBuffer.last().code != CharCodes.SemiColon.code) {
                        // TODO Implement parse error
                    }
                    tempBuffer.clear()
                    tempBuffer.append(NamedChars[matches.first()])
                    flushCodePoints(tokenizer)
                    return consumeNextChar { returnState }
                }
            }

            else -> consumeNextChar { this }
        }
    }


    private fun flushCodePoints(tokenizer: Tokenizer) {
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
                val token = Token.Character(tempBuffer[0])
                tokenizer.emitToken(token)
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