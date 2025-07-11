package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.isWhiteSpace

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