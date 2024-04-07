package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.util.isAsciiDigit

internal class DecimalCharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isAsciiDigit() -> {
                tokenizer.charRefCode *= 10
                tokenizer.charRefCode += codePoint - 0x30
                consumeNextChar { this }
            }

            codePoint == CharCodes.SemiColon.code -> {
                consumeNextChar {
                    NumericCharacterReferenceEnd(returnState = returnState)
                }
            }

            else -> {
                // TODO Implement parse error
                reconsumeCurrentChar {
                    NumericCharacterReferenceEnd(returnState = returnState)
                }
            }
        }
    }
}