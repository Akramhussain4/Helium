package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.util.isAsciiDigit
import com.hussain.csoup.util.isAsciiLowerHexDigit
import com.hussain.csoup.util.isAsciiUpperHexDigit

internal class HexadecimalCharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isAsciiDigit() -> {
                tokenizer.characterReferenceCode *= 16
                tokenizer.characterReferenceCode += codePoint - CharCodes.Zero.code
                consumeNextChar { this }
            }

            codePoint.isAsciiUpperHexDigit() -> {
                tokenizer.characterReferenceCode *= 16
                tokenizer.characterReferenceCode += codePoint - 0x0037
                consumeNextChar { this }
            }

            codePoint.isAsciiLowerHexDigit() -> {
                tokenizer.characterReferenceCode *= 16
                tokenizer.characterReferenceCode += codePoint - 0x0057
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