package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.isAsciiUpperAlpha
import com.hussain.csoup.util.isWhiteSpace

internal class TagName(private val currentTagToken: Token) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isWhiteSpace() -> consumeNextChar {
                BeforeAttributeName(currentTagToken)
            }

            codePoint.IS(Chars.Solidus) -> consumeNextChar {
                SelfClosingStartTag(currentTagToken)
            }

            codePoint.IS(Chars.GreaterThan) -> {
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            codePoint.isAsciiUpperAlpha() -> {
                val token = updateTagName(codePoint + 0x0020)
                consumeNextChar { TagName(token) }
            }

            codePoint == -1 -> {
                tokenizer.logParseError("eof-in-tag")
                endOfFile { Data }
            }

            else -> {
                val token = updateTagName(codePoint)
                consumeNextChar { TagName(currentTagToken = token) }
            }
        }
    }


    private fun updateTagName(codePoint: Int): Token {
        return when (currentTagToken) {
            is Token.StartTag -> {
                val newStartTagTokenName = currentTagToken.name + codePoint.toChar()
                currentTagToken.copy(name = newStartTagTokenName)
            }

            is Token.EndTag -> {
                val newEndTagTokenName = currentTagToken.name + codePoint.toChar()
                currentTagToken.copy(name = newEndTagTokenName)
            }

            else -> throw Exception("Expected a StartTag or EndTag token, got $currentTagToken")
        }
    }
}