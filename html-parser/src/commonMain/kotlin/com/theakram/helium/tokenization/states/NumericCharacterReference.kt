package com.theakram.helium.tokenization.states

import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.Chars

internal class NumericCharacterReference(
    private val returnState: TokenizationState
) : TokenizationState {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        tokenizer.characterReferenceCode = 0
        return when {
            codePoint.IS(
                Chars.LowerX,
                Chars.UpperX
            ) -> {
                tokenizer.tempBuffer.append(codePoint.toChar())
                consumeNextChar {
                    HexadecimalCharacterReferenceStart(returnState = returnState)
                }
            }

            else -> reconsumeCurrentChar {
                DecimalCharacterReferenceStart(returnState = returnState)
            }
        }
    }
}