package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal class AttributeName(
    private val currentTagToken: Token,
    private val currentAttributeName: String
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.Tab.code,
            CharCodes.LineFeed.code,
            CharCodes.FormFeed.code,
            CharCodes.Space.code,
            CharCodes.Solidus.code,
            CharCodes.GreaterThan.code -> reconsumeCurrentChar {
                AfterAttributeName(currentTagToken, currentAttributeName)
            }

            CharCodes.Equals.code -> consumeNextChar {
                BeforeAttributeValue(
                    currentTagToken = currentTagToken,
                    currentAttributeName = currentAttributeName
                )
            }

            in CharCodes.UpperA.code..CharCodes.UpperZ.code -> updateAndReturnNextState(
                codePoint = codePoint + 0x0020
            )

            CharCodes.QuotationMark.code,
            CharCodes.Apostrophe.code,
            CharCodes.LessThan.code -> {
                // TODO Implement parse error
                updateAndReturnNextState(codePoint)
            }

            else -> updateAndReturnNextState(codePoint)
        }
    }

    private fun updateAndReturnNextState(
        codePoint: Int,
    ): Pair<TokenizationState, Action> {
        when (currentTagToken) {
            is Token.StartTag -> {
                val updatedAttributeName = currentAttributeName.append(codePoint.toChar())
                currentTagToken.attributes.updateKey(
                    currentAttributeName,
                    updatedAttributeName
                )
                return consumeNextChar { AttributeName(currentTagToken, updatedAttributeName) }
            }

            else -> throw IllegalStateException("Expected a StartTag token, got $currentTagToken")
        }
    }

    private fun String.append(char: Char) = this + char
}

internal fun <K, V> MutableMap<K, V>.updateKey(oldKey: K, newKey: K) {
    this[oldKey]?.let { value ->
        this.remove(oldKey)
        this[newKey] = value
    }
}