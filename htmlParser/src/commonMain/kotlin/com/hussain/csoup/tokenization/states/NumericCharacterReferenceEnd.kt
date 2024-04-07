package com.hussain.csoup.tokenization.states

import com.hussain.csoup.entity.NumCharRef
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.flushCodePoints
import com.hussain.csoup.util.isNonCharacter
import com.hussain.csoup.util.isSurrogate

internal class NumericCharacterReferenceEnd(
    private val returnState: TokenizationState,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        val number = tokenizer.charRefCode
        when {
            number == 0x00 -> {
                // TODO Implement parse error
                tokenizer.charRefCode = 0xFFFD
            }

            number > 0x10FFFF -> {
                // TODO Implement parse error
                tokenizer.charRefCode = 0xFFFD
            }

            number.isSurrogate() -> {
                // TODO Implement parse error
                tokenizer.charRefCode = 0xFFFD
            }

            number.isNonCharacter() -> {
                // TODO Implement parse error
            }
        }

        if (number == 0x0D || (number.toChar().isISOControl() && !number.toChar().isWhitespace())) {
            // TODO Implement parse error
            if (NumCharRef.containsKey(number)) {
                tokenizer.charRefCode = NumCharRef[number]!!
            }
        }

        tokenizer.tempBuffer.clear()
        tokenizer.tempBuffer.append(tokenizer.charRefCode.toChar())
        returnState.flushCodePoints(tokenizer)
        return reconsumeCurrentChar { returnState }
    }
}