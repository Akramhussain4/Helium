package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.util.NumCharRef
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.flushCodePoints
import com.hussain.csoup.util.isAsciiWhitespace
import com.hussain.csoup.util.isNonCharacter
import com.hussain.csoup.util.isSurrogate

/**
 * https://html.spec.whatwg.org/multipage/parsing.html#numeric-character-reference-end-state
 */
internal class NumericCharacterReferenceEnd(
    private val returnState: TokenizationState,
) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        val number = tokenizer.characterReferenceCode
        when {
            number == 0x00 -> {
                tokenizer.logParseError("null-character-reference")
                tokenizer.characterReferenceCode = 0xFFFD
            }

            number > 0x10FFFF -> {
                tokenizer.logParseError("character-reference-outside-unicode-range")
                tokenizer.characterReferenceCode = 0xFFFD
            }

            number.isSurrogate() -> {
                tokenizer.logParseError("surrogate-character-reference")
                tokenizer.characterReferenceCode = 0xFFFD
            }

            number.isNonCharacter() -> {
                tokenizer.logParseError("noncharacter-character-reference")
            }
        }

        if (number == 0x0D || (number.toChar().isISOControl() && !number.isAsciiWhitespace())) {
            tokenizer.logParseError("control-character-reference")
            NumCharRef[number]?.let {
                tokenizer.characterReferenceCode = it
            }
        }

        tokenizer.tempBuffer.clear()
        tokenizer.tempBuffer.append(tokenizer.characterReferenceCode.toChar())
        returnState.flushCodePoints(tokenizer)
        // FIXME: This should be consumeNextChar
        return reconsumeCurrentChar { returnState }
    }
}