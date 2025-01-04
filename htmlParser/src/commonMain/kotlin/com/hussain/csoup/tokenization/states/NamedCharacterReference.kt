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
        if (returnState.isAttributeState() &&
            (codePoint == Chars.Equals.char.code || codePoint.isAsciiAlphaNumeric())
        ) {
            tokenizer.tempBuffer.append(codePoint.toChar())
            returnState.flushCodePoints(tokenizer)
            return consumeNextChar { returnState }
        }

        val nextChar = codePoint.toChar()
        val currentText = tokenizer.tempBuffer.toString().removePrefix("&")
        val currentRef = "&$currentText"
        val withNextChar = "$currentRef$nextChar"

        // Quick check for perfect match with semicolon
        if (codePoint == Chars.SemiColon.char.code && NamedChars[withNextChar] != null) {
            tokenizer.tempBuffer.clear()
            emitString(NamedChars[withNextChar]!!, tokenizer)
            return consumeNextChar { returnState }
        }

        // Find potential longer matches
        val hasPotentialLongerMatch = NamedChars.keys.any { key ->
            key.startsWith(withNextChar) && key != withNextChar
        }

        // Check for exact match with semicolon
        val hasMatchWithSemicolon = NamedChars["$withNextChar;"] != null

        val matchingPrefix = NamedChars.entries
            .filter { (key, _) -> currentRef.startsWith(key) && key.length < currentRef.length }
            .maxByOrNull { it.key.length }

        return when {
            // Could match longer
            hasPotentialLongerMatch || hasMatchWithSemicolon -> {
                tokenizer.tempBuffer.append(nextChar)
                consumeNextChar { NamedCharacterReference(returnState) }
            }

            // Found shorter match (like "&not" in "&notit")
            matchingPrefix != null -> {
                tokenizer.logParseError("missing-semicolon-after-character-reference")
                tokenizer.tempBuffer.clear()

                // Emit the matched entity
                emitString(matchingPrefix.value, tokenizer)

                // Emit remaining characters
                val remainingText = currentText.substring(matchingPrefix.key.length - 1)
                emitString(remainingText, tokenizer)

                reconsumeCurrentChar { returnState }
            }

            // Exact match (not in attribute context)
            NamedChars[currentRef] != null && !returnState.isAttributeState() -> {
                tokenizer.logParseError("missing-semicolon-after-character-reference")
                tokenizer.tempBuffer.clear()
                emitString(NamedChars[currentRef]!!, tokenizer)
                reconsumeCurrentChar { returnState }
            }

            // No valid matches
            else -> {
                if (currentText.all { it.code.isAsciiAlphaNumeric() } && nextChar == ';') {
                    tokenizer.logParseError("unknown-named-character-reference")
                }
                tokenizer.emitToken(Token.Character('&'))
                emitString(currentText, tokenizer)
                tokenizer.tempBuffer.clear()
                reconsumeCurrentChar { returnState }
            }
        }
    }

    private fun emitString(str: String, tokenizer: Tokenizer) {
        str.forEach { char -> tokenizer.emitToken(Token.Character(char)) }
    }
}