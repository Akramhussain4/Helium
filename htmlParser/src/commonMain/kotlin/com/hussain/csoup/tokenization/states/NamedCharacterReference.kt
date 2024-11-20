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

    private val MAX_LENGTH = NamedChars.keys.maxOf { it.removePrefix("&").length }

    private fun findNamedCharacterReference(buffer: String): Triple<String?, String?, Boolean> {
        // If the buffer is too long, don't even try to match
        if (buffer.length > MAX_LENGTH) {
            return Triple(null, null, false)
        }

        // Check for potential matches
        val potentialMatches = NamedChars.keys.filter { ref ->
            ref.startsWith("&$buffer")
        }

        // If there's a possibility of a longer match, continue consuming
        if (potentialMatches.any { ref ->
                ref.removePrefix("&").length > buffer.length
            }
        ) {
            return Triple(null, null, true)
        }

        // No potential matches, check for partial matches
        for (i in buffer.length downTo 1) {
            val partial = buffer.substring(0, i)
            val match = "&$partial"
            NamedChars[match]?.let { value ->
                return Triple(match, value, false)
            }
        }

        // No matches found
        return Triple(null, null, false)
    }

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        // Add current character to buffer
        tokenizer.tempBuffer.append(codePoint.toChar())
        val currentBuffer = tokenizer.tempBuffer.toString().removePrefix("&")

        // If we exceed max length, immediately emit all characters
        if (currentBuffer.length > MAX_LENGTH) {
            val chars = tokenizer.tempBuffer.toString()
            tokenizer.tempBuffer.clear()
            chars.forEach { char ->
                tokenizer.emitToken(Token.Character(char))
            }
            return consumeNextChar { returnState }
        }

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

                // Emit replacement character
                tokenizer.tempBuffer.clear()
                replacement.forEach { char ->
                    tokenizer.emitToken(Token.Character(char))
                }

                // Handle remaining characters
                val matchLength = match.removePrefix("&").length
                val remainingText = if (currentBuffer.length > matchLength) {
                    currentBuffer.substring(matchLength)
                } else {
                    ""
                }

                remainingText.forEach { char ->
                    tokenizer.emitToken(Token.Character(char))
                }

                consumeNextChar { returnState }
            }

            canContinue -> consumeNextChar { this }

            else -> {
                // Failed to match - emit & and all accumulated characters
                tokenizer.tempBuffer.toString().forEach { char ->
                    tokenizer.emitToken(Token.Character(char))
                }
                tokenizer.tempBuffer.clear()
                consumeNextChar { returnState }
            }
        }
    }
}