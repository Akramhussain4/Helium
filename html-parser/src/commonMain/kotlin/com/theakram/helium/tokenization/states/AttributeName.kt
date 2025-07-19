package com.theakram.helium.tokenization.states

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.Chars
import com.theakram.helium.util.isAsciiUpperAlpha
import com.theakram.helium.util.isWhiteSpace

internal class AttributeName(private val currentTagToken: Token) : TokenizationState {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.IS(
                Chars.Solidus,
                Chars.GreaterThan
            ) || codePoint.isWhiteSpace() || codePoint == -1 -> reconsumeCurrentChar {
                AfterAttributeName(currentTagToken = currentTagToken)
            }

            codePoint.IS(Chars.Equals) -> consumeNextChar {
                BeforeAttributeValue(currentTagToken = currentTagToken)
            }

            codePoint.isAsciiUpperAlpha() -> {
                tokenizer.currentAttributeName.append((codePoint + 0x0020).toChar())
                consumeNextChar { this }
            }

            codePoint.IS(
                Chars.QuotationMark,
                Chars.Apostrophe,
                Chars.LessThan
            ) -> {
                tokenizer.logParseError("unexpected-character-in-attribute-name")
                tokenizer.currentAttributeName.append(codePoint.toChar())
                consumeNextChar { this }
            }

            else -> {
                tokenizer.currentAttributeName.append(codePoint.toChar())
                consumeNextChar { this }
            }
        }
    }
}