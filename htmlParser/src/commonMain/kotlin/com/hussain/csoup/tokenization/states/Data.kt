package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

/**
 * The data state is the state when the parser is not in any special state.
 * https://html.spec.whatwg.org/multipage/parsing.html#data-state
 */
internal data object Data : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.Ampersand.code -> consumeNextChar { CharacterReference(returnState = this) }
            CharCodes.LessThan.code -> consumeNextChar { TagOpen }
            else -> {
                val characterToken = Token.Character(data = codePoint.toChar())
                tokenizer.emitToken(characterToken)
                consumeNextChar { Data }
            }
        }
    }
}