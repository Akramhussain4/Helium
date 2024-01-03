package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal class TagName(private val currentTagToken: Token) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.Tab.code,
            CharCodes.LineFeed.code,
            CharCodes.FormFeed.code,
            CharCodes.Space.code -> consumeNextChar { BeforeAttributeName(currentTagToken) }

            CharCodes.Solidus.code -> consumeNextChar { SelfClosingStartTag }

            CharCodes.GreaterThan.code -> {
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            in CharCodes.UpperA.code..CharCodes.UpperZ.code -> {
                val token = updateTagName(codePoint + 0x0020)
                consumeNextChar { TagName(token) }
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
                val newStartTagTokenName = currentTagToken.name + codePoint.toChar()
                currentTagToken.copy(name = newStartTagTokenName)
            }

            else -> throw IllegalStateException("Expected a StartTag or EndTag token, got $currentTagToken")
        }
    }
}