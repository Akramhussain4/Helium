package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes

internal class DecimalCharacterReference(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder,
    private val charRefCode: Int
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            in CharCodes.Zero.code..CharCodes.Nine.code -> {
                val newCharRefCode = charRefCode * 10 + (codePoint - 0x30)
                consumeNextChar {
                    DecimalCharacterReference(
                        returnState = returnState,
                        tempBuffer = tempBuffer,
                        charRefCode = newCharRefCode
                    )
                }
            }

            CharCodes.SemiColon.code -> {
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