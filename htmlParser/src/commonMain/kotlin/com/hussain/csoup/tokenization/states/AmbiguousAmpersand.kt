package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.util.flushCodePoints
import com.hussain.csoup.util.isAsciiAlphaNumeric

internal class AmbiguousAmpersand(
    private val returnState: TokenizationState,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isAsciiAlphaNumeric() -> {
                returnState.flushCodePoints(codePoint, tokenizer)
                consumeNextChar { returnState }
            }

            codePoint == CharCodes.SemiColon.code -> {
                // TODO Implement parse error
                reconsumeCurrentChar { returnState }
            }

            else -> reconsumeCurrentChar { returnState }
        }
    }
}