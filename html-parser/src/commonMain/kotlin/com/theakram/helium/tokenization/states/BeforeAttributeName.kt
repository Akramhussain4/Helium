package com.theakram.helium.tokenization.states

import com.theakram.helium.util.Chars
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.isWhiteSpace

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