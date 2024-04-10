package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.util.isAsciiAlphaNumeric
import com.hussain.csoup.tokenization.util.isAttributeState

internal class AmbiguousAmpersand(
    private val returnState: TokenizationState,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {

        if (codePoint.isAsciiAlphaNumeric()) {
            if (returnState.isAttributeState()) {
                // TODO: append the current input character to the current attribute's value
                return consumeNextChar { this }
            } else {
                val characterToken = Token.Character(data = codePoint.toChar())
                tokenizer.emitToken(characterToken)
            }
        }

        return when {
            codePoint.IS(CharCodes.SemiColon) -> {
                tokenizer.logParseError("unknown-named-character-reference")
                reconsumeCurrentChar { returnState }
            }

            else -> reconsumeCurrentChar { returnState }
        }
    }
}