package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS

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