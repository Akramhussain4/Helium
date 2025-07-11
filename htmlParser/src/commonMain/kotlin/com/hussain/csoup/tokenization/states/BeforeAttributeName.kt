package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.isWhiteSpace

internal class BeforeAttributeName(private val currentTagToken: Token) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isWhiteSpace() -> consumeNextChar { this }

            codePoint.IS(
                Chars.Solidus,
                Chars.GreaterThan
            ) -> reconsumeCurrentChar {
                AfterAttributeName(currentTagToken = currentTagToken)
            }

            codePoint.IS(Chars.Equals) -> {
                tokenizer.logParseError("unexpected-equals-sign-before-attribute-name")
                tokenizer.currentAttributeName.clear()
                tokenizer.currentAttributeValue.clear()
                tokenizer.currentAttributeName.append(codePoint.toChar())
                consumeNextChar {
                    AttributeName(currentTagToken = currentTagToken)
                }
            }

            else -> {
                tokenizer.currentAttributeName.clear()
                tokenizer.currentAttributeValue.clear()
                reconsumeCurrentChar {
                    AttributeName(currentTagToken = currentTagToken)
                }
            }
        }
    }
}