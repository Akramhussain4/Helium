package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS

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