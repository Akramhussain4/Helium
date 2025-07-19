package com.theakram.helium.tokenization.states

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.Chars

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

            codePoint == -1 -> {
                tokenizer.logParseError("eof-in-tag")
                endOfFile { Data }
            }

            else -> {
                tokenizer.logParseError("unexpected-solidus-in-tag")
                reconsumeCurrentChar { BeforeAttributeName(currentTagToken) }
            }
        }
    }
}