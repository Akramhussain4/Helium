package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS

/**
 * The data state is the state when the parser is not in any special state.
 * https://html.spec.whatwg.org/multipage/parsing.html#data-state
 */
internal data object Data : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.IS(CharCodes.Ampersand) -> consumeNextChar {
                CharacterReference(returnState = this)
            }

            codePoint.IS(CharCodes.LessThan) -> consumeNextChar { TagOpen }

            else -> {
                val characterToken = Token.Character(data = codePoint.toChar())
                tokenizer.emitToken(characterToken)
                consumeNextChar { Data }
            }
        }
    }
}