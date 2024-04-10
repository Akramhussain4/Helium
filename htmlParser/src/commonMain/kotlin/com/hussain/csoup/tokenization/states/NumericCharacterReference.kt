package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.CharCodes

internal class NumericCharacterReference(
    private val returnState: TokenizationState
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        tokenizer.characterReferenceCode = 0
        return when {
            codePoint.IS(
                CharCodes.LowerX,
                CharCodes.UpperX
            ) -> {
                tokenizer.tempBuffer.append(codePoint.toChar())
                consumeNextChar {
                    HexadecimalCharacterReferenceStart(returnState = returnState)
                }
            }

            else -> {
                reconsumeCurrentChar {
                    DecimalCharacterReferenceStart(returnState = returnState)
                }
            }
        }
    }
}