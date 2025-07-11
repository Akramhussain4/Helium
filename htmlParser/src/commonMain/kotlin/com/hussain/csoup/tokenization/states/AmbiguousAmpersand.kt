package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.tokenization.util.isAttributeState
import com.hussain.csoup.util.Chars
import com.hussain.csoup.util.isAsciiAlphaNumeric

internal class AmbiguousAmpersand(
    private val returnState: TokenizationState,
) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isAsciiAlphaNumeric() -> {
                if (returnState.isAttributeState()) {
                    tokenizer.currentAttributeValue.append(codePoint.toChar())
                } else {
                    tokenizer.emitToken(Token.Character(data = codePoint.toChar()))
                }
                consumeNextChar { this }
            }
            codePoint.IS(Chars.SemiColon) -> {
                tokenizer.logParseError("unknown-named-character-reference")
                reconsumeCurrentChar { returnState }
            }

            else -> reconsumeCurrentChar { returnState }
        }
    }
}