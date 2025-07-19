package com.theakram.helium.tokenization.states

import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.Chars
import com.theakram.helium.util.isAsciiDigit

internal class DecimalCharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
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