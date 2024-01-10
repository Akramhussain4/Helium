package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes

internal class NumericCharacterReference(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder
) : TokenizationState() {

    private val charRefCode = 0

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            in CharCodes.LowerX.code..CharCodes.UpperX.code -> {
                tempBuffer.append(codePoint.toChar())
                consumeNextChar {
                    HexadecimalCharacterReferenceStart(
                        returnState = returnState,
                        tempBuffer = tempBuffer,
                        charRefCode = charRefCode
                    )
                }
            }

            else -> {
                reconsumeCurrentChar {
                    DecimalCharacterReferenceStart(
                        returnState = returnState,
                        tempBuffer = tempBuffer,
                        charRefCode = charRefCode
                    )
                }
            }
        }
    }
}