package com.theakram.helium.tokenization.states

import com.theakram.helium.util.Chars
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.isWhiteSpace

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