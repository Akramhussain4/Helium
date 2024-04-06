package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.util.isAsciiDigit
import com.hussain.csoup.util.isAsciiLowerHexDigit
import com.hussain.csoup.util.isAsciiUpperHexDigit

internal class HexadecimalCharacterReference(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder,
    private val charRefCode: Int
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isAsciiDigit() -> {
                val newCharRefCode = (charRefCode * 16) + (codePoint - CharCodes.Zero.code)
                consumeNextChar {
                    HexadecimalCharacterReference(
                        returnState = returnState,
                        tempBuffer = tempBuffer.append(codePoint.toChar()),
                        charRefCode = newCharRefCode
                    )
                }
            }

            codePoint.isAsciiUpperHexDigit() -> {
                val newCharRefCode = (charRefCode * 16) + (codePoint - 0x0037)
                consumeNextChar {
                    HexadecimalCharacterReference(
                        returnState = returnState,
                        tempBuffer = tempBuffer.append(codePoint.toChar()),
                        charRefCode = newCharRefCode
                    )
                }
            }

            codePoint.isAsciiLowerHexDigit() -> {
                val newCharRefCode = (charRefCode * 16) + (codePoint - 0x0057)
                consumeNextChar {
                    HexadecimalCharacterReference(
                        returnState = returnState,
                        tempBuffer = tempBuffer.append(codePoint.toChar()),
                        charRefCode = newCharRefCode
                    )
                }
            }

            codePoint == CharCodes.SemiColon.code -> {
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