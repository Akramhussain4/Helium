package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Tokenizer

/**
https://html.spec.whatwg.org/multipage/parsing.html#character-reference-state
 */
internal class CharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState() {

    private val tempBuffer = StringBuilder()

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        tempBuffer.append(CharCodes.Ampersand.code.toChar())

        return when (codePoint) {
            in CharCodes.Zero.code..CharCodes.Nine.code,
            in CharCodes.UpperA.code..CharCodes.UpperZ.code,
            in CharCodes.LowerA.code..CharCodes.LowerZ.code -> reconsumeCurrentChar {
                NamedCharacterReference
            }

            CharCodes.NumberSign.code -> {
                tempBuffer.append(codePoint.toChar())
                reconsumeCurrentChar { NumericCharacterReference }
            }

            else -> {
                // TODO: Flush code points consumed as a character reference.
                reconsumeCurrentChar { returnState }
            }
        }
    }
}