package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.util.NumCharRef
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.flushCodePoints
import com.hussain.csoup.util.isAsciiWhitespace
import com.hussain.csoup.util.isNonCharacter
import com.hussain.csoup.util.isSurrogate

/**
 * https://html.spec.whatwg.org/multipage/parsing.html#numeric-character-reference-end-state
 */
internal class NumericCharacterReferenceEnd(
    private val returnState: TokenizationState,
) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        val number = tokenizer.characterReferenceCode
        when {
            number == 0x00 -> {
                tokenizer.logParseError("null-character-reference")
                tokenizer.characterReferenceCode = 0xFFFD
            }

            number > 0x10FFFF -> {
                tokenizer.logParseError("character-reference-outside-unicode-range")
                tokenizer.characterReferenceCode = 0xFFFD
            }

            number.isSurrogate() -> {
                tokenizer.logParseError("surrogate-character-reference")
                tokenizer.characterReferenceCode = 0xFFFD
            }

            number.isNonCharacter() -> {
                tokenizer.logParseError("noncharacter-character-reference")
            }
        }

        if (number == 0x0D || (number.toChar().isISOControl() && !number.isAsciiWhitespace())) {
            tokenizer.logParseError("control-character-reference")
            NumCharRef[number]?.let {
                tokenizer.characterReferenceCode = it
            }
        }
        tokenizer.tempBuffer.clear()

        /**
         * JVM-specific implementation detail:
         * The HTML spec operates on Unicode code points, but Java/Kotlin Char type is limited to 16 bits (UTF-16).
         * For code points above U+FFFF (supplementary characters), we need to encode them as UTF-16 surrogate pairs
         * to properly represent them in JVM strings.
         *
         * Example: U+1FFFE is encoded as two chars: U+D83F (high surrogate) and U+DFFE (low surrogate)
         **/
        if (tokenizer.characterReferenceCode > 0xFFFF) {
            val cp = tokenizer.characterReferenceCode - 0x10000
            val highSurrogate = (0xD800 + (cp shr 10)).toChar()
            val lowSurrogate = (0xDC00 + (cp and 0x3FF)).toChar()
            tokenizer.tempBuffer.append(highSurrogate)
            tokenizer.tempBuffer.append(lowSurrogate)
        } else {
            tokenizer.tempBuffer.append(tokenizer.characterReferenceCode.toChar())
        }

        returnState.flushCodePoints(tokenizer)
        return reconsumeCurrentChar { returnState }
    }
}