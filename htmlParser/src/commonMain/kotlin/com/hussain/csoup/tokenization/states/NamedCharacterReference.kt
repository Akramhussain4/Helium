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
        val namedChar = NamedChars[tempBuffer.toString()]

        when {
            // If there is a match
            NamedChars.containsKey(tempBuffer.toString()) -> {
                when {
                    returnState is AttributeValueDoubleQuoted &&
                            tempBuffer.last().code != CharCodes.SemiColon.code &&
                            (tokenizer.peekNextChar().code == CharCodes.Equals.code || codePoint.isAsciiAlphaNumeric()) -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(
                            currentToken,
                            attributeName,
                            tempBuffer.get(0)
                        )
                        return consumeNextChar { returnState }
                    }

                    returnState is AttributeValueSingleQuoted &&
                            tempBuffer.last().code != CharCodes.SemiColon.code &&
                            (tokenizer.peekNextChar().code == CharCodes.Equals.code || codePoint.isAsciiAlphaNumeric()) -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(
                            currentToken,
                            attributeName,
                            codePoint.toChar()
                        )
                        return consumeNextChar { returnState }
                    }

                    returnState is AttributeValueUnquoted &&
                            tempBuffer.last().code != CharCodes.SemiColon.code &&
                            (tokenizer.peekNextChar().code == CharCodes.Equals.code || codePoint.isAsciiAlphaNumeric()) -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(
                            currentToken,
                            attributeName,
                            codePoint.toChar()
                        )
                        return consumeNextChar { returnState }
                    }

                    // Otherwise:
                    else -> {
                        if (tempBuffer.last().code != CharCodes.SemiColon.code) {
                            // TODO Implement parse error
                        }
                        tempBuffer.clear()
                        tempBuffer.append(namedChar?.get(0))
                        when (returnState) {
                            is AttributeValueDoubleQuoted -> {
                                val currentToken = returnState.currentTagToken
                                val attributeName = returnState.currentAttributeName
                                updateAttributeValue(
                                    currentToken,
                                    attributeName,
                                    codePoint.toChar()
                                )
                            }

                            is AttributeValueSingleQuoted -> {
                                val currentToken = returnState.currentTagToken
                                val attributeName = returnState.currentAttributeName
                                updateAttributeValue(
                                    currentToken,
                                    attributeName,
                                    codePoint.toChar()
                                )
                            }

                            is AttributeValueUnquoted -> {
                                val currentToken = returnState.currentTagToken
                                val attributeName = returnState.currentAttributeName
                                updateAttributeValue(
                                    currentToken,
                                    attributeName,
                                    codePoint.toChar()
                                )
                            }

                            else -> {
                                tokenizer.emitToken(Token.Character(tempBuffer.get(0)))
                            }
                        }
                        consumeNextChar { returnState }
                    }
                }
            }
            // Otherwise
            else -> {
                when (returnState) {
                    is AttributeValueDoubleQuoted -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(
                            currentToken,
                            attributeName,
                            codePoint.toChar()
                        )
                    }

                    is AttributeValueSingleQuoted -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(
                            currentToken,
                            attributeName,
                            codePoint.toChar()
                        )
                    }

                    is AttributeValueUnquoted -> {
                        val currentToken = returnState.currentTagToken
                        val attributeName = returnState.currentAttributeName
                        updateAttributeValue(
                            currentToken,
                            attributeName,
                            codePoint.toChar()
                        )
                    }

                    else -> {
                        tokenizer.emitToken(
                            Token.Character(
                                codePoint.toChar()
                            )
                        )
                    }
                }
                consumeNextChar { AmbiguousAmpersand(returnState) }
            }

        }
        return consumeNextChar { this }
    }

    private fun updateAttributeValue(
        currentToken: Token,
        attributeName: String,
        replacement: Char?
    ) {
        when (currentToken) {
            is Token.StartTag -> {
                val currentValue = currentToken.attributes.getValue(attributeName)
                currentToken.attributes[attributeName] = currentValue + replacement
            }

            else -> throw IllegalStateException("Expected a StartTag token, got $currentToken")
        }
    }
}