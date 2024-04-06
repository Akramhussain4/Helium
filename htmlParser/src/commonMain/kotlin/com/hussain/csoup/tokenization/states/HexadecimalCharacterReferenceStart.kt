package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.flushCodePoints
import com.hussain.csoup.util.isAsciiHexDigit

internal class HexadecimalCharacterReferenceStart(
    private val returnState: TokenizationState,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isAsciiHexDigit() -> {
                reconsumeCurrentChar {
                    HexadecimalCharacterReference(returnState = returnState)
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