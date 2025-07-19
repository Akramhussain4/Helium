package com.theakram.helium.tokenization.states

import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.flushCodePoints
import com.theakram.helium.util.isAsciiHexDigit

internal class HexadecimalCharacterReferenceStart(
    private val returnState: TokenizationState,
) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isAsciiHexDigit() -> {
                reconsumeCurrentChar {
                    HexadecimalCharacterReference(returnState = returnState)
                }
            }

            else -> {
                tokenizer.logParseError("absence-of-digits-in-numeric-character-reference")
                returnState.flushCodePoints(tokenizer)
                reconsumeCurrentChar { returnState }
            }
        }
    }
}