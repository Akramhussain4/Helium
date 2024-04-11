package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.Chars
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
                tokenizer.characterReferenceCode += codePoint - Chars.Zero.char.code
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

            codePoint.IS(Chars.SemiColon) -> {
                consumeNextChar {
                    NumericCharacterReferenceEnd(returnState = returnState)
                }
            }

            else -> {
                tokenizer.logParseError("missing-semicolon-after-character-reference")
                reconsumeCurrentChar {
                    NumericCharacterReferenceEnd(returnState = returnState)
                }
            }
        }
    }
}