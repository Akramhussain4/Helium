package com.hussain.csoup.tokenization.states

import com.hussain.csoup.entity.NamedChars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.util.flushCodePoints
import com.hussain.csoup.util.isAsciiAlphaNumeric

/**
 * https://html.spec.whatwg.org/multipage/parsing.html#named-character-reference-state
 */
internal class NamedCharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        tokenizer.tempBuffer.append(codePoint.toChar())

        val matches = NamedChars.keys.filter { it.startsWith(tokenizer.tempBuffer.toString()) }

        return when {
            matches.isEmpty() -> {
                flushCodePoints(tokenizer)
                consumeNextChar { AmbiguousAmpersand(returnState) }
            }

            matches.size == 1 && matches.contains(tokenizer.tempBuffer.toString()) -> {
                val nextChar = tokenizer.peekNextChar().code
                if (
                    (returnState is AttributeValueDoubleQuoted ||
                            returnState is AttributeValueSingleQuoted ||
                            returnState is AttributeValueUnquoted) &&
                    tokenizer.tempBuffer.last().code != CharCodes.SemiColon.code
                    && (nextChar == CharCodes.Equals.code || nextChar.isAsciiAlphaNumeric())
                ) {
                    flushCodePoints(tokenizer)
                    return consumeNextChar { returnState }
                } else {
                    if (tokenizer.tempBuffer.last().code != CharCodes.SemiColon.code) {
                        // TODO Implement parse error
                    }
                    val referenceName =
                        NamedChars[matches.first()] ?: error("Unexpected reference name")
                    if (returnState !is AttributeValueUnquoted) {
                        tokenizer.tempBuffer.clear()
                        tokenizer.tempBuffer.append(referenceName)
                    } else {
                        tokenizer.tempBuffer.clear()
                        tokenizer.tempBuffer.append(referenceName)
                    }
                    flushCodePoints(tokenizer)
                    return consumeNextChar { returnState }
                }
            }

            else -> consumeNextChar { this }
        }
    }
}