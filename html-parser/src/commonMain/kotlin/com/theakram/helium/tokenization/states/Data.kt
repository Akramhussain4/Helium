package com.theakram.helium.tokenization.states

import com.theakram.helium.util.Chars
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS

/**
 * The data state is the state when the parser is not in any special state.
 * https://html.spec.whatwg.org/multipage/parsing.html#data-state
 */
internal data object Data : TokenizationState {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.IS(Chars.Ampersand) -> consumeNextChar {
                CharacterReference(returnState = this)
            }

            codePoint.IS(Chars.LessThan) -> consumeNextChar { TagOpen }

            codePoint == -1 -> endOfFile { this }

            else -> {
                val characterToken = Token.Character(data = codePoint.toChar())
                tokenizer.emitToken(characterToken)
                consumeNextChar { Data }
            }
        }
    }
}