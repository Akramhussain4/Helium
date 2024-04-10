package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.tokenization.util.updateKey
import com.hussain.csoup.util.isAsciiUpperAlpha
import com.hussain.csoup.util.isWhiteSpace

internal class AttributeName(
    private val currentTagToken: Token,
    private val currentAttributeName: String
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.IS(
                CharCodes.Solidus,
                CharCodes.GreaterThan
            ) || codePoint.isWhiteSpace() -> reconsumeCurrentChar {
                AfterAttributeName(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            codePoint.IS(CharCodes.Equals) -> consumeNextChar {
                BeforeAttributeValue(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            codePoint.isAsciiUpperAlpha() -> {
                val attributeName = currentAttributeName + codePoint.toChar() + 0x0020
                updateAttributeName(attributeName = attributeName)
                consumeNextChar {
                    AttributeName(
                        currentTagToken = currentTagToken,
                        currentAttributeName = attributeName
                    )
                }
            }

            codePoint.IS(
                CharCodes.QuestionMark,
                CharCodes.Apostrophe,
                CharCodes.LessThan
            ) -> {
                tokenizer.logParseError("unexpected-character-in-attribute-name")
                val attributeName = currentAttributeName + codePoint.toChar()
                updateAttributeName(attributeName = attributeName)
                consumeNextChar {
                    AttributeName(
                        currentTagToken = currentTagToken,
                        currentAttributeName = attributeName
                    )
                }
            }

            else -> {
                val attributeName = currentAttributeName + codePoint.toChar()
                updateAttributeName(attributeName = attributeName)
                consumeNextChar {
                    AttributeName(
                        currentTagToken = currentTagToken,
                        currentAttributeName = attributeName
                    )
                }
            }
        }
    }

    private fun updateAttributeName(attributeName: String) {
        when (currentTagToken) {
            is Token.StartTag -> {
                currentTagToken.attributes.updateKey(
                    oldKey = currentAttributeName,
                    newKey = attributeName
                )
            }

            else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
        }
    }
}