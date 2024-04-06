package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.util.isAsciiDigit

internal class DecimalCharacterReference(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder,
    private val charRefCode: Int
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isAsciiDigit() -> {
                val newCharRefCode = charRefCode * 10 + (codePoint - 0x30)
                consumeNextChar {
                    DecimalCharacterReference(
                        returnState = returnState,
                        tempBuffer = tempBuffer,
                        charRefCode = newCharRefCode
                    )
                }
            }

            codePoint == CharCodes.SemiColon.code -> {
                consumeNextChar { NumericCharacterReferenceEnd(charRefCode) }
            }

            else -> {
                // TODO Implement parse error
                reconsumeCurrentChar {
                    NumericCharacterReferenceEnd(charRefCode)
                }
            }
        }
    }
}