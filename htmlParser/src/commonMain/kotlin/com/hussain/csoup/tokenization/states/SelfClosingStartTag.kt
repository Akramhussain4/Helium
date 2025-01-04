package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.Chars

internal class SelfClosingStartTag(private val currentTagToken: Token) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.IS(Chars.GreaterThan) -> {
                when (currentTagToken) {
                    is Token.StartTag -> {
                        val token = currentTagToken.copy(selfClosing = true)
                        tokenizer.emitToken(token)
                    }

                    is Token.EndTag -> {
                        tokenizer.emitToken(currentTagToken)
                    }

                    else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
                }
                consumeNextChar { Data }
            }

            codePoint.IS(Chars.EOF) -> {
                tokenizer.logParseError("eof-in-tag")
                endOfFile { this }
            }

            else -> {
                tokenizer.logParseError("unexpected-solidus-in-tag")
                reconsumeCurrentChar { BeforeAttributeName(currentTagToken) }
            }
        }
    }
}