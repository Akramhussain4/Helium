package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.isWhiteSpace

internal class AttributeValueUnquoted(val currentTagToken: Token) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isWhiteSpace() -> {
                tokenizer.addCurrentAttributeToToken(currentTagToken)
                consumeNextChar {
                    BeforeAttributeName(currentTagToken = currentTagToken)
                }
            }

            codePoint.IS(Chars.Ampersand) -> consumeNextChar {
                CharacterReference(this)
            }

            codePoint.IS(Chars.GreaterThan) -> {
                tokenizer.addCurrentAttributeToToken(currentTagToken)
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            codePoint.IS(
                Chars.QuotationMark,
                Chars.Apostrophe,
                Chars.LessThan,
                Chars.Equals,
                Chars.GraveAccent
            ) -> {
                tokenizer.logParseError("unexpected-character-in-unquoted-attribute-value")
                tokenizer.currentAttributeValue.append(codePoint.toChar())
                consumeNextChar { this }
            }

            codePoint == -1 -> {
                tokenizer.logParseError("eof-in-attribute-value")
                tokenizer.addCurrentAttributeToToken(currentTagToken)
                endOfFile { Data }
            }

            else -> {
                tokenizer.currentAttributeValue.append(codePoint.toChar())
                consumeNextChar { this }
            }
        }
    }
}