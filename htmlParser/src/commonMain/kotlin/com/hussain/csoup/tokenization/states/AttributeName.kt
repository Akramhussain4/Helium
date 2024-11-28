package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.tokenization.util.updateKey
import com.hussain.csoup.util.isAsciiUpperAlpha
import com.hussain.csoup.util.isWhiteSpace

internal class AttributeName(
    private val currentTagToken: Token,
    private val currentAttributeName: String
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.IS(
                Chars.Solidus,
                Chars.GreaterThan,
                Chars.EOF,
            ) || codePoint.isWhiteSpace() -> reconsumeCurrentChar {
                AfterAttributeName(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            codePoint.IS(Chars.Equals) -> consumeNextChar {
                BeforeAttributeValue(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            codePoint.isAsciiUpperAlpha() -> {
                val attributeName = currentAttributeName + (codePoint + 0x0020).toChar()
                updateAttributeName(attributeName = attributeName)
                consumeNextChar {
                    AttributeName(
                        currentTagToken = currentTagToken,
                        currentAttributeName = attributeName
                    )
                }
            }

            codePoint.IS(
                Chars.QuestionMark,
                Chars.Apostrophe,
                Chars.LessThan
            ) -> {
                tokenizer.logParseError("unexpected-character-in-attribute-name")
                val attributeName = currentAttributeName + codePoint.toChar()
                updateAttributeName(attributeName = attributeName)
                consumeNextChar {
                    AttributeName(
                        currentTagToken = currentTagToken,
                        currentAttributeName = attributeName
                    )
                }
            }

            else -> {
                val attributeName = currentAttributeName + codePoint.toChar()
                updateAttributeName(attributeName = attributeName)
                consumeNextChar {
                    AttributeName(
                        currentTagToken = currentTagToken,
                        currentAttributeName = attributeName
                    )
                }
            }
        }
    }

    private fun updateAttributeName(attributeName: String) {
        when (currentTagToken) {
            is Token.StartTag -> {
                currentTagToken.attributes.updateKey(
                    oldKey = currentAttributeName,
                    newKey = attributeName
                )
            }

            is Token.EndTag -> Unit

            else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
        }
    }
}