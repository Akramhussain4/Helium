package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token
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

    private fun findNamedCharacterReference(buffer: String): Triple<String?, String?, Boolean> {
        // Check for potential longer matches
        val potentialMatches = NamedChars.keys.filter { ref ->
            ref.startsWith("&$buffer")
        }

        // If there's a possibility of a longer match, continue consuming
        if (potentialMatches.any { ref ->
                ref.removePrefix("&").length > buffer.length
            }) {
            return Triple(null, null, true)
        }

        // If we can't continue, check for partial matches
        for (refLength in buffer.length downTo 1) {
            val partialBuffer = buffer.substring(0, refLength)
            val match = "&$partialBuffer"
            NamedChars[match]?.let { replacement ->
                return Triple(match, replacement, false)
            }
        }

        return Triple(null, null, false)
    }

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        tokenizer.tempBuffer.append(codePoint.toChar())
        val currentBuffer = tokenizer.tempBuffer.toString().removePrefix("&")

        val (match, replacement, canContinue) = findNamedCharacterReference(currentBuffer)

        return when {
            returnState.isAttributeState() -> {
                val nextChar = tokenizer.peekNextChar().code
                if (nextChar == Chars.Equals.char.code || nextChar.isAsciiAlphaNumeric()) {
                    returnState.flushCodePoints(tokenizer)
                    consumeNextChar { returnState }
                } else {
                    consumeNextChar { this }
                }
            }

            match != null && replacement != null -> {
                if (!match.endsWith(";")) {
                    tokenizer.logParseError("missing-semicolon-after-character-reference")
                }

                // Clear buffer and emit replacement
                tokenizer.tempBuffer.clear()
                replacement.forEach { char ->
                    tokenizer.emitToken(Token.Character(char))
                }

                // Handle remaining characters after match
                val matchText = match.removePrefix("&")
                val remainingText = currentBuffer.substring(matchText.length)
                remainingText.forEach { char ->
                    tokenizer.emitToken(Token.Character(char))
                }

                consumeNextChar { returnState }
            }

            canContinue -> {
                consumeNextChar { this }
            }

            else -> {
                // Failed to match - emit & and character
                tokenizer.tempBuffer.clear()
                tokenizer.emitToken(Token.Character('&'))
                if (currentBuffer.isNotEmpty()) {
                    tokenizer.emitToken(Token.Character(currentBuffer[0]))
                }

                if (currentBuffer.length > 1) {
                    reconsumeCurrentChar { returnState }
                } else {
                    consumeNextChar { returnState }
                }
            }
        }
    }
}