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
) : TokenizationState {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        val nextChar = codePoint.toChar()
        val currentText = tokenizer.tempBuffer.toString().removePrefix("&")
        val withNextChar = "&$currentText$nextChar"

        // Fast path for attribute context
        if (returnState.isAttributeState() &&
            (codePoint == Chars.Equals.char.code || codePoint.isAsciiAlphaNumeric())
        ) {
            tokenizer.tempBuffer.append(nextChar)
            returnState.flushCodePoints(tokenizer)
            return consumeNextChar { returnState }
        }

        // Quick check for perfect match with semicolon
        if (codePoint == Chars.SemiColon.char.code && NamedChars[withNextChar] != null) {
            tokenizer.tempBuffer.clear()
            emitString(NamedChars[withNextChar]!!, tokenizer)
            return consumeNextChar { returnState }
        }

        // Try to find the longest possible match
        val possibleMatch = findLongestMatch(withNextChar)

        return when {
            // Continue consuming if there could be a longer match or if adding semicolon would make a match
            possibleMatch.couldMatchLonger || possibleMatch.hasMatchWithSemicolon -> {
                tokenizer.tempBuffer.append(nextChar)
                consumeNextChar { NamedCharacterReference(returnState) }
            }

            // Case 2: Found shorter match (like "&not" in "&notit")
            possibleMatch.partialMatch != null -> {
                handlePartialMatch(
                    tokenizer,
                    possibleMatch.partialMatch,
                    currentText,
                    returnState
                )
            }

            // Case 3: Exact match without semicolon (not in attribute context)
            possibleMatch.exactMatch != null && !returnState.isAttributeState() -> {
                tokenizer.logParseError("missing-semicolon-after-character-reference")
                tokenizer.tempBuffer.clear()
                emitString(possibleMatch.exactMatch, tokenizer)
                reconsumeCurrentChar { returnState }
            }

            // Case 4: No valid matches - Switch to ambiguous ampersand state
            else -> {
                // First flush any consumed code points
                tokenizer.emitToken(Token.Character('&'))
                emitString(currentText, tokenizer)
                tokenizer.tempBuffer.clear()

                reconsumeCurrentChar { AmbiguousAmpersand(returnState) }
            }
        }
    }

    private data class MatchResult(
        val couldMatchLonger: Boolean,
        val hasMatchWithSemicolon: Boolean,
        val exactMatch: String?,
        val partialMatch: Map.Entry<String, String>?
    )

    private fun findLongestMatch(withNextChar: String): MatchResult {
        // Check for potential longer matches (including with semicolon)
        val couldMatchLonger = NamedChars.keys.any { key ->
            key.startsWith(withNextChar) && key != withNextChar
        }

        val hasMatchWithSemicolon = NamedChars["$withNextChar;"] != null
        val exactMatch = NamedChars[withNextChar]

        // Find partial matches (like "&not" in "&notit")
        val partialMatch = NamedChars.entries
            .filter { (key, _) ->
                withNextChar.startsWith(key) && key.length < withNextChar.length
            }
            .maxByOrNull { it.key.length }

        return MatchResult(
            couldMatchLonger = couldMatchLonger,
            hasMatchWithSemicolon = hasMatchWithSemicolon,
            exactMatch = exactMatch,
            partialMatch = partialMatch
        )
    }

    private fun handlePartialMatch(
        tokenizer: Tokenizer,
        matchingPrefix: Map.Entry<String, String>,
        currentText: String,
        returnState: TokenizationState
    ): StateTransition {
        tokenizer.logParseError("missing-semicolon-after-character-reference")
        tokenizer.tempBuffer.clear()

        // Emit the matched entity
        emitString(matchingPrefix.value, tokenizer)

        // Emit remaining characters
        val remainingText = currentText.substring(matchingPrefix.key.length - 1)
        emitString(remainingText, tokenizer)

        return reconsumeCurrentChar { returnState }
    }

    private fun emitString(str: String, tokenizer: Tokenizer) {
        str.forEach { char -> tokenizer.emitToken(Token.Character(char)) }
    }
}