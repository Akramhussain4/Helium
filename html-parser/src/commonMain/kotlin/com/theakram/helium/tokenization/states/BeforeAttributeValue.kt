package com.theakram.helium.tokenization.states

import com.theakram.helium.util.Chars
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.isWhiteSpace

internal class BeforeAttributeValue(private val currentTagToken: Token) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isWhiteSpace() -> consumeNextChar { this }

            codePoint.IS(Chars.QuotationMark) -> consumeNextChar {
                AttributeValueDoubleQuoted(currentTagToken = currentTagToken)
            }

            codePoint.IS(Chars.Apostrophe) -> consumeNextChar {
                AttributeValueSingleQuoted(currentTagToken = currentTagToken)
            }

            codePoint.IS(Chars.GreaterThan) -> {
                tokenizer.logParseError("missing-attribute-value")
                tokenizer.addCurrentAttributeToToken(currentTagToken)
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            else -> {
                reconsumeCurrentChar {
                    AttributeValueUnquoted(currentTagToken = currentTagToken)
                }
            }
        }
    }
}