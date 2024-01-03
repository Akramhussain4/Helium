package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal class BeforeAttributeName(private val currentTagToken: Token) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.Tab.code,
            CharCodes.LineFeed.code,
            CharCodes.FormFeed.code,
            CharCodes.Space.code -> consumeNextChar { this }

            CharCodes.Solidus.code,
            CharCodes.GreaterThan.code -> reconsumeCurrentChar {
                AfterAttributeName(
                    currentTagToken = currentTagToken,
                    currentAttributeName = ""
                )
            }

            CharCodes.Equals.code -> {
                // TODO Implement parse error
                val attributeName = codePoint.toChar().toString()
                updateAttribute(attributeName)
                consumeNextChar {
                    AttributeName(
                        currentTagToken = currentTagToken,
                        currentAttributeName = attributeName
                    )
                }
            }

            else -> {
                updateAttribute("")
                reconsumeCurrentChar {
                    AttributeName(
                        currentTagToken = currentTagToken,
                        currentAttributeName = ""
                    )
                }
            }
        }
    }

    private fun updateAttribute(attributeName: String) {
        when (currentTagToken) {
            is Token.StartTag -> {
                currentTagToken.attributes[attributeName] = ""
            }

            else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
        }
    }
}