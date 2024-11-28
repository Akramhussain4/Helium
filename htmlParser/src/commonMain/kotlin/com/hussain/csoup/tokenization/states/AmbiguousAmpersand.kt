package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.Chars
import com.hussain.csoup.util.isAsciiAlphaNumeric
import com.hussain.csoup.tokenization.util.isAttributeState
import com.hussain.csoup.tokenization.util.updateAttributeValue

internal class AmbiguousAmpersand(
    private val returnState: TokenizationState,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isAsciiAlphaNumeric() -> {
                updateAttributeValue(returnState, codePoint, tokenizer)
                consumeNextChar { returnState }
            }
            codePoint.IS(Chars.SemiColon) -> {
                tokenizer.logParseError("unknown-named-character-reference")
                reconsumeCurrentChar { returnState }
            }

            else -> reconsumeCurrentChar { returnState }
        }
    }

    private fun updateAttributeValue(
        state: TokenizationState,
        codePoint: Int,
        tokenizer: Tokenizer
    ) {
        when (state) {
            is AttributeValueDoubleQuoted -> {
                val currentToken = state.currentTagToken
                val attributeName = state.currentAttributeName
                currentToken.updateAttributeValue(attributeName, tokenizer)
            }

            is AttributeValueSingleQuoted -> {
                val currentToken = state.currentTagToken
                val attributeName = state.currentAttributeName
                currentToken.updateAttributeValue(attributeName, tokenizer)
            }

            is AttributeValueUnquoted -> {
                val currentToken = state.currentTagToken
                val attributeName = state.currentAttributeName
                currentToken.updateAttributeValue(attributeName, tokenizer)
            }

            else -> {
                val characterToken = Token.Character(data = codePoint.toChar())
                tokenizer.emitToken(characterToken)
            }
        }
    }
}