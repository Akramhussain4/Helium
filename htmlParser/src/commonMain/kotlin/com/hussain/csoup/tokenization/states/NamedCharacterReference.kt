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

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        // Look at what we have without the current character
        val currentText = tokenizer.tempBuffer.toString().removePrefix("&")
        val nextChar = codePoint.toChar()

        // Special handling for attribute context where we want to preserve the original text
        if (returnState.isAttributeState() &&
            (nextChar.code == Chars.Equals.char.code || nextChar.code.isAsciiAlphaNumeric())) {
            // Add current character to buffer before flushing
            tokenizer.tempBuffer.append(nextChar)
            // Emit the complete buffer contents in attribute context
            returnState.flushCodePoints(tokenizer)
            return consumeNextChar { returnState }
        }

        // See if adding the current character would complete a named reference
        val withNextChar = "$currentText$nextChar"

        // If the current character is semicolon, try to match the complete reference
        if (nextChar == ';' && NamedChars.containsKey("&$currentText;")) {
            tokenizer.tempBuffer.clear()
            NamedChars["&$currentText;"]!!.forEach { char ->
                tokenizer.emitToken(Token.Character(char))
            }
            return consumeNextChar { Data }
        }

        // If we have a match without semicolon
        if (NamedChars.containsKey("&$currentText")) {
            // Check if adding next char would make a longer valid reference
            val couldBeLonger = NamedChars.keys.any { key ->
                key.startsWith("&$withNextChar") || key.startsWith("&$withNextChar;")
            }

            if (!couldBeLonger) {
                tokenizer.tempBuffer.clear()
                tokenizer.logParseError("missing-semicolon-after-character-reference")
                NamedChars["&$currentText"]!!.forEach { char ->
                    tokenizer.emitToken(Token.Character(char))
                }
                return reconsumeCurrentChar { Data }
            }
        }

        // See if we should keep consuming
        if (NamedChars.keys.any { key ->
                key.startsWith("&$withNextChar") || key.startsWith("&$withNextChar;")
            }) {
            tokenizer.tempBuffer.append(nextChar)
            return consumeNextChar { this }
        }

        // No valid match found or possible
        if (tokenizer.tempBuffer.isEmpty()) {
            // If buffer is empty, just emit the current character
            tokenizer.emitToken(Token.Character(nextChar))
        } else {
            // Otherwise emit buffer contents and reconsume current character
            val chars = tokenizer.tempBuffer.toString()
            tokenizer.tempBuffer.clear()
            chars.forEach { char ->
                tokenizer.emitToken(Token.Character(char))
            }
            return reconsumeCurrentChar { returnState }
        }

        return consumeNextChar { returnState }
    }
}