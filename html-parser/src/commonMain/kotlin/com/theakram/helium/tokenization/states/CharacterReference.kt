package com.theakram.helium.tokenization.states

import com.theakram.helium.util.Chars
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.tokenization.util.flushCodePoints
import com.theakram.helium.util.isAsciiAlphaNumeric

/**
https://html.spec.whatwg.org/multipage/parsing.html#character-reference-state
 */
internal class CharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        tokenizer.tempBuffer.clear()
        tokenizer.tempBuffer.append(Chars.Ampersand.char.code.toChar())

        return when {
            codePoint.isAsciiAlphaNumeric() -> reconsumeCurrentChar {
                NamedCharacterReference(returnState)
            }

            codePoint.IS(Chars.NumberSign) -> {
                tokenizer.tempBuffer.append(codePoint.toChar())
                consumeNextChar { NumericCharacterReference(returnState) }
            }

            else -> {
                returnState.flushCodePoints(tokenizer)
                reconsumeCurrentChar { returnState }
            }
        }
    }
}