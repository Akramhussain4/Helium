package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes

internal class NumericCharacterReference(
    private val returnState: TokenizationState
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        tokenizer.charRefCode = 0
        return when (codePoint) {
            CharCodes.LowerX.code,
            CharCodes.UpperX.code -> {
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