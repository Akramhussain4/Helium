package com.theakram.helium.tokenization.states

import com.theakram.helium.util.Chars
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.isWhiteSpace

internal class AfterAttributeName(private val currentTagToken: Token) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isWhiteSpace() -> consumeNextChar { this }

            codePoint.IS(Chars.Solidus) -> {
                tokenizer.addCurrentAttributeToToken(currentTagToken)
                consumeNextChar {
                    SelfClosingStartTag(currentTagToken)
                }
            }

            codePoint.IS(Chars.Equals) -> consumeNextChar {
                BeforeAttributeValue(currentTagToken = currentTagToken)
            }

            codePoint.IS(Chars.GreaterThan) -> {
                tokenizer.addCurrentAttributeToToken(currentTagToken)
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            codePoint == -1 -> {
                tokenizer.logParseError("eof-in-tag")
                endOfFile { Data }
            }

            else -> {
                tokenizer.addCurrentAttributeToToken(currentTagToken)
                tokenizer.currentAttributeName.clear()
                tokenizer.currentAttributeValue.clear()
                reconsumeCurrentChar {
                    AttributeName(currentTagToken = currentTagToken)
                }
            }
        }
    }
}