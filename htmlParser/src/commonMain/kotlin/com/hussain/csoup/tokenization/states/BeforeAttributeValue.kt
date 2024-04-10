package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.isWhiteSpace

internal class BeforeAttributeValue(
    private val currentTagToken: Token,
    private val currentAttributeName: String
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isWhiteSpace() -> consumeNextChar { this }

            codePoint.IS(CharCodes.QuotationMark) -> consumeNextChar {
                AttributeValueDoubleQuoted(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            codePoint.IS(CharCodes.Apostrophe) -> consumeNextChar {
                AttributeValueSingleQuoted(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            codePoint.IS(CharCodes.GreaterThan) -> {
                tokenizer.logParseError("missing-attribute-value")
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