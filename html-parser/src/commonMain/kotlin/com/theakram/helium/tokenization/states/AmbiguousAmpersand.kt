package com.theakram.helium.tokenization.states

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.tokenization.util.isAttributeState
import com.theakram.helium.util.Chars
import com.theakram.helium.util.isAsciiAlphaNumeric

internal class AmbiguousAmpersand(
    private val returnState: TokenizationState,
) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isAsciiAlphaNumeric() -> {
                if (returnState.isAttributeState()) {
                    tokenizer.currentAttributeValue.append(codePoint.toChar())
                } else {
                    tokenizer.emitToken(Token.Character(data = codePoint.toChar()))
                }
                consumeNextChar { this }
            }
            codePoint.IS(Chars.SemiColon) -> {
                tokenizer.logParseError("unknown-named-character-reference")
                reconsumeCurrentChar { returnState }
            }

            else -> reconsumeCurrentChar { returnState }
        }
    }
}