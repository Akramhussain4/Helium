package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.flushCodePoints
import com.hussain.csoup.util.isAsciiAlphaNumeric

/**
https://html.spec.whatwg.org/multipage/parsing.html#character-reference-state
 */
internal class CharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        tokenizer.tempBuffer.clear()
        tokenizer.tempBuffer.append(CharCodes.Ampersand.code.toChar())

        return when {
            codePoint.isAsciiAlphaNumeric() -> reconsumeCurrentChar {
                NamedCharacterReference(returnState)
            }

            codePoint == CharCodes.NumberSign.code -> {
                tokenizer.tempBuffer.append(codePoint.toChar())
                consumeNextChar { NumericCharacterReference(returnState) }
            }

            else -> {
                returnState.flushCodePoints(codePoint, tokenizer)
                reconsumeCurrentChar { returnState }
            }
        }
    }
}