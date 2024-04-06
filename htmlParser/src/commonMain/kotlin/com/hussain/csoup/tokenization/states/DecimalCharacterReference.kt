package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.util.isAsciiDigit

internal class DecimalCharacterReference(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        if (codePoint.isAsciiDigit()) {
            tokenizer.charRefCode *= 10
            tokenizer.charRefCode += codePoint - 0x30
        }
        return when {
            codePoint == CharCodes.SemiColon.code -> {
                consumeNextChar {
                    NumericCharacterReferenceEnd(
                        returnState = returnState,
                        tempBuffer = tempBuffer,
                    )
                }
            }

            else -> {
                // TODO Implement parse error
                reconsumeCurrentChar {
                    NumericCharacterReferenceEnd(
                        returnState = returnState,
                        tempBuffer = tempBuffer,
                    )
                }
            }
        }
    }
}