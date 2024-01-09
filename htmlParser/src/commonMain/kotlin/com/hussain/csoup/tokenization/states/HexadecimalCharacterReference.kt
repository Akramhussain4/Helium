package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes

internal class HexadecimalCharacterReference(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder,
    private val charRefCode: Int
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            in CharCodes.Zero.code..CharCodes.Nine.code -> {
                val newCharRefCode = (charRefCode * 16) + (codePoint - CharCodes.Zero.code)
                consumeNextChar {
                    HexadecimalCharacterReference(
                        returnState = returnState,
                        tempBuffer = tempBuffer.append(codePoint.toChar()),
                        charRefCode = newCharRefCode
                    )
                }
            }

            in CharCodes.UpperA.code..CharCodes.UpperF.code -> {
                val newCharRefCode = (charRefCode * 16) + (codePoint - 0x0037)
                consumeNextChar {
                    HexadecimalCharacterReference(
                        returnState = returnState,
                        tempBuffer = tempBuffer.append(codePoint.toChar()),
                        charRefCode = newCharRefCode
                    )
                }
            }

            in CharCodes.LowerA.code..CharCodes.LowerF.code -> {
                val newCharRefCode = (charRefCode * 16) + (codePoint - 0x0057)
                consumeNextChar {
                    HexadecimalCharacterReference(
                        returnState = returnState,
                        tempBuffer = tempBuffer.append(codePoint.toChar()),
                        charRefCode = newCharRefCode
                    )
                }
            }

            CharCodes.SemiColon.code -> {
                consumeNextChar {
                    NumericCharacterReferenceEnd(charRefCode)
                }
            }

            else -> {
                // TODO Implement parse error
                reconsumeCurrentChar { NumericCharacterReferenceEnd(charRefCode) }
            }
        }
    }
}