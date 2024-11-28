package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.flushCodePoints
import com.hussain.csoup.util.isAsciiDigit

internal class DecimalCharacterReferenceStart(
    private val returnState: TokenizationState,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isAsciiDigit() -> {
                reconsumeCurrentChar {
                    DecimalCharacterReference(returnState = returnState)
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