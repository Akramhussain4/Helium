package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.flushCodePoints
import com.hussain.csoup.util.isAsciiDigit

internal class DecimalCharacterReferenceStart(
    private val returnState: TokenizationState,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isAsciiDigit() -> {
                reconsumeCurrentChar {
                    DecimalCharacterReference(returnState = returnState)
                }
            }

            else -> {
                // TODO: Implement parse error
                returnState.flushCodePoints(codePoint, tokenizer)
                reconsumeCurrentChar { returnState }
            }
        }
    }
}