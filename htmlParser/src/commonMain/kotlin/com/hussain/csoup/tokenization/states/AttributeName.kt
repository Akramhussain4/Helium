package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.updateKey

internal class AttributeName(
    private val currentTagToken: Token,
    private val currentAttributeName: String
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.Tab.code,
            CharCodes.LineFeed.code,
            CharCodes.FormFeed.code,
            CharCodes.Space.code,
            CharCodes.Solidus.code,
            CharCodes.GreaterThan.code -> reconsumeCurrentChar {
                AfterAttributeName(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            CharCodes.Equals.code -> consumeNextChar {
                BeforeAttributeValue(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            in CharCodes.UpperA.code..CharCodes.UpperZ.code -> {
                val attributeName = currentAttributeName + codePoint.toChar() + 0x0020
                updateAttributeName(attributeName = attributeName)
                consumeNextChar {
                    AttributeName(
                        currentTagToken = currentTagToken,
                        currentAttributeName = attributeName
                    )
                }
            }

            CharCodes.QuotationMark.code,
            CharCodes.Apostrophe.code,
            CharCodes.LessThan.code -> {
                // TODO Implement parse error
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