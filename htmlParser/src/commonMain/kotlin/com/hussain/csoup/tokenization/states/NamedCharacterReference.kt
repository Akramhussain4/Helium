package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.NamedChars
import com.hussain.csoup.tokenization.util.flushCodePoints
import com.hussain.csoup.tokenization.util.isAttributeState
import com.hussain.csoup.util.Chars
import com.hussain.csoup.util.isAsciiAlphaNumeric

/**
 * https://html.spec.whatwg.org/multipage/parsing.html#named-character-reference-state
 */
internal class NamedCharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        val currentText = tokenizer.tempBuffer.toString().removePrefix("&")
        val nextChar = codePoint.toChar()

        // Early exit for attribute context
        if (returnState.isAttributeState() &&
            (nextChar.code == Chars.Equals.char.code || nextChar.code.isAsciiAlphaNumeric())
        ) {
            tokenizer.tempBuffer.append(nextChar)
            returnState.flushCodePoints(tokenizer)
            return consumeNextChar { returnState }
        }

        val currentReference = "&$currentText"
        val withNextChar = "$currentText$nextChar"
        val withNextCharReference = "&$withNextChar"

        // Check current reference without semicolon
        val currentMatch = NamedChars[currentReference]

        // Check if adding next char with semicolon would make a valid reference
        val nextCharWithSemicolonMatch = if (nextChar == ';') {
            NamedChars[withNextCharReference]
        } else null

        when {
            // Perfect match with semicolon
            nextCharWithSemicolonMatch != null -> {
                tokenizer.tempBuffer.clear()
                nextCharWithSemicolonMatch.forEach { char ->
                    tokenizer.emitToken(Token.Character(char))
                }
                return consumeNextChar { returnState }
            }

            // Current reference exists without semicolon
            currentMatch != null -> {
                // Only consume the current reference if:
                // 1. The next character wouldn't form part of a longer valid reference, or
                // 2. We're in a special case where we need to consume immediately
                val shouldConsumeNow = when {
                    // Special case for "&not" followed by "i"
                    currentReference == "&not" && nextChar == 'i' -> {
                        // Only consume if this isn't part of "&notin;"
                        !(withNextChar == "noti" && tokenizer.peekNextChar() == 'n')
                    }
                    // Otherwise, check if this could be part of a longer reference
                    else -> !NamedChars.keys.any { key ->
                        key.startsWith(withNextCharReference) ||
                                (nextChar != ';' && key.startsWith("$withNextCharReference;"))
                    }
                }

                if (shouldConsumeNow) {
                    tokenizer.tempBuffer.clear()
                    currentMatch.forEach { char ->
                        tokenizer.emitToken(Token.Character(char))
                    }
                    return reconsumeCurrentChar { returnState }
                }
            }
        }

        // If we reach here, either:
        // 1. This could be the start of a valid reference, or
        // 2. We need to flush invalid characters
        if (NamedChars.keys.any { it.startsWith(withNextCharReference) }) {
            tokenizer.tempBuffer.append(nextChar)
            return consumeNextChar { this }
        }

        // No valid reference possible, flush characters
        if (tokenizer.tempBuffer.isEmpty()) {
            tokenizer.emitToken(Token.Character(nextChar))
            return consumeNextChar { returnState }
        } else {
            val chars = tokenizer.tempBuffer.toString()
            tokenizer.tempBuffer.clear()
            chars.forEach { char ->
                tokenizer.emitToken(Token.Character(char))
            }
            return reconsumeCurrentChar { returnState }
        }
    }
}