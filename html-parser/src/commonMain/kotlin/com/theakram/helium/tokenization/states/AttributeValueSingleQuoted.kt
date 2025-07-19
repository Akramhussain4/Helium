package com.theakram.helium.tokenization.states

import com.theakram.helium.util.Chars
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS

internal class AttributeValueSingleQuoted(val currentTagToken: Token) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.IS(Chars.Apostrophe) -> consumeNextChar {
                AfterAttributeValueQuoted(currentTagToken)
            }

            codePoint.IS(Chars.Ampersand) -> {
                val returnState = AttributeValueSingleQuoted(currentTagToken)
                consumeNextChar { CharacterReference(returnState) }
            }

            codePoint == -1 -> {
                tokenizer.logParseError("eof-in-attribute-value")
                endOfFile { Data }
            }

            else -> {
                tokenizer.currentAttributeValue.append(codePoint.toChar())
                consumeNextChar { this }
            }
        }
    }
}