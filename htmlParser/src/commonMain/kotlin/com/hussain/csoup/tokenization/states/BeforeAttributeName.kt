package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.isWhiteSpace

internal class BeforeAttributeName(private val currentTagToken: Token) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isWhiteSpace() -> consumeNextChar { this }

            codePoint.IS(
                Chars.Solidus,
                Chars.GreaterThan
            ) -> reconsumeCurrentChar {
                AfterAttributeName(
                    currentTagToken = currentTagToken,
                    currentAttributeName = ""
                )
            }

            codePoint.IS(Chars.Equals) -> {
                tokenizer.logParseError("unexpected-equals-sign-before-attribute-name")
                val attributeName = codePoint.toChar().toString()
                updateAttribute(attributeName)
                consumeNextChar {
                    AttributeName(
                        currentTagToken = currentTagToken,
                        currentAttributeName = attributeName
                    )
                }
            }

            else -> {
                updateAttribute("")
                reconsumeCurrentChar {
                    AttributeName(
                        currentTagToken = currentTagToken,
                        currentAttributeName = ""
                    )
                }
            }
        }
    }

    private fun updateAttribute(attributeName: String) {
        when (currentTagToken) {
            is Token.StartTag -> {
                currentTagToken.attributes[attributeName] = ""
            }

            is Token.EndTag -> Unit

            else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
        }
    }
}