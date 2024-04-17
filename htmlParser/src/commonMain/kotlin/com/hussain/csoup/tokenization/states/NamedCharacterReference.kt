package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.util.NamedChars
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.util.flushCodePoints
import com.hussain.csoup.util.isAsciiAlphaNumeric
import com.hussain.csoup.tokenization.util.isAttributeState

/**
 * https://html.spec.whatwg.org/multipage/parsing.html#named-character-reference-state
 */
internal class NamedCharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        tokenizer.tempBuffer.append(codePoint.toChar())

        val match = NamedChars.keys.firstOrNull { it.startsWith(tokenizer.tempBuffer) }
        if (match.isNullOrEmpty().not()) {
            return consumeNextChar { this }
        }

        if (match.isNullOrEmpty()) {
            returnState.flushCodePoints(tokenizer)
            return consumeNextChar { AmbiguousAmpersand(returnState) }
        }

        val nextChar = tokenizer.peekNextChar().code
        if (returnState.isAttributeState() &&
            match.last().code != Chars.SemiColon.char.code
        ) {
            if (nextChar == Chars.Equals.char.code || nextChar.isAsciiAlphaNumeric()) {
                returnState.flushCodePoints(tokenizer)
                return consumeNextChar { returnState }
            }
        }

        if (match.last().code != Chars.SemiColon.char.code) {
            tokenizer.logParseError("missing-semicolon-after-character-reference")
        }

        tokenizer.tempBuffer.clear()
        tokenizer.tempBuffer.append(match)
        returnState.flushCodePoints(tokenizer)
        return consumeNextChar { returnState }
    }
}