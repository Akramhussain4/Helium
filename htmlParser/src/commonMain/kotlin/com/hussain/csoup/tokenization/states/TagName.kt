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
            CharCodes.Space.code -> consumeNextChar { BeforeAttributeName }

            CharCodes.Solidus.code -> consumeNextChar { SelfClosingStartTag }

            CharCodes.GreaterThan.code -> {
                tokenizer.emitToken(currentTagToken)
                consumeNextChar { Data }
            }

            in CharCodes.UpperA.code..CharCodes.UpperZ.code -> {
                when (currentTagToken) {
                    is Token.StartTag -> {
                        val newStartTagName = currentTagToken.name + (codePoint + 0x0020).toChar()
                        val updatedStartTagToken = currentTagToken.copy(name = newStartTagName)
                        consumeNextChar { TagName(updatedStartTagToken)  }
                    }
                    is Token.EndTag -> {
                        val newEndTagName = currentTagToken.name + (codePoint + 0x0020).toChar()
                        val updatedEndTagToken = currentTagToken.copy(name = newEndTagName)
                        consumeNextChar { TagName(updatedEndTagToken)  }
                    }
                    else -> throw IllegalStateException("Expected a StartTag or EndTag token")
                }
            }

            else -> {
                val token = when (currentTagToken) {
                    is Token.StartTag -> {
                        val newStartTagTokenName = currentTagToken.name + codePoint.toChar()
                        currentTagToken.copy(name = newStartTagTokenName)
                    }

                    is Token.EndTag -> {
                        val newStartTagTokenName = currentTagToken.name + codePoint.toChar()
                        currentTagToken.copy(name = newStartTagTokenName)
                    }

                    else -> currentTagToken
                }
                consumeNextChar { TagName(currentTagToken = token) }
            }
        }
    }
}