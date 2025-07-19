package com.theakram.helium.tokenization.states

import com.theakram.helium.util.Chars
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.isWhiteSpace

internal class AfterAttributeValueQuoted(private val currentTagToken: Token) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        if (currentTagToken is Token.StartTag && tokenizer.currentAttributeName.isNotEmpty()) {
            currentTagToken.addAttribute(
                tokenizer.currentAttributeName.toString(),
                tokenizer.currentAttributeValue.toString()
            )
        }

        return when {
            codePoint.isWhiteSpace() -> consumeNextChar { BeforeAttributeName(currentTagToken) }

            codePoint.IS(Chars.Solidus) -> consumeNextChar {
                SelfClosingStartTag(currentTagToken)
            }

            codePoint.IS(Chars.GreaterThan) -> {
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            codePoint == -1 -> {
                tokenizer.logParseError("eof-in-tag")
                endOfFile { Data }
            }

            else -> {
                tokenizer.logParseError("missing-whitespace-between-attributes")
                reconsumeCurrentChar { BeforeAttributeName(currentTagToken) }
            }
        }
    }
}