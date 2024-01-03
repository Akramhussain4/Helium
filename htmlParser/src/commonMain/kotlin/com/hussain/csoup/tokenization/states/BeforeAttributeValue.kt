package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal class BeforeAttributeValue(
    private val currentTagToken: Token,
    private val currentAttributeName: String
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.Tab.code,
            CharCodes.LineFeed.code,
            CharCodes.FormFeed.code,
            CharCodes.Space.code -> consumeNextChar { this }

            CharCodes.QuotationMark.code -> consumeNextChar {
                AttributeValueDoubleQuoted(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            CharCodes.Apostrophe.code -> consumeNextChar {
                AttributeValueSingleQuoted(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            CharCodes.GreaterThan.code -> {
                // TODO Implement parse error
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            else -> {
                reconsumeCurrentChar {
                    AttributeValueUnquoted(
                        currentTagToken = currentTagToken,
                        currentAttributeName = currentAttributeName
                    )
                }
            }
        }
    }
}