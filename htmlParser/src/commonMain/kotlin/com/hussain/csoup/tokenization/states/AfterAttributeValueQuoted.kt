package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.isWhiteSpace

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