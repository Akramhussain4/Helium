package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.util.isAsciiDigit
import com.hussain.csoup.util.isAsciiLowerHexDigit
import com.hussain.csoup.util.isAsciiUpperHexDigit

internal class HexadecimalCharacterReference(
    private val returnState: TokenizationState,
    private val tempBuffer: StringBuilder,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        when {
            codePoint.isAsciiDigit() -> {
                tokenizer.charRefCode *= 16
                tokenizer.charRefCode += codePoint - CharCodes.Zero.code
            }

            codePoint.isAsciiUpperHexDigit() -> {
                tokenizer.charRefCode *= 16
                tokenizer.charRefCode += codePoint - 0x0037
            }

            codePoint.isAsciiLowerHexDigit() -> {
                tokenizer.charRefCode *= 16
                tokenizer.charRefCode += codePoint - 0x0057
            }
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