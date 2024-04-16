package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.Chars
import com.hussain.csoup.util.isAsciiDigit

internal class DecimalCharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isAsciiDigit() -> {
                tokenizer.characterReferenceCode *= 10
                tokenizer.characterReferenceCode += codePoint - 0x0030
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