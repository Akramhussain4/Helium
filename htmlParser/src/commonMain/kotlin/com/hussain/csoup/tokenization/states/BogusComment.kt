package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Token.Comment
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.Chars

internal class BogusComment(private val commentToken: Comment) : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.IS(Chars.GreaterThan) -> {
                tokenizer.emitToken(commentToken)
                consumeNextChar { Data }
            }

            codePoint == -1 -> {
                tokenizer.emitToken(commentToken)
                endOfFile { Data }
            }

            codePoint.IS(Chars.Null) -> {
                tokenizer.logParseError("unexpected-null-character")
                val newToken = Comment(commentToken.data + '\uFFFD')
                consumeNextChar { BogusComment(newToken) }
            }

            else -> {
                val newToken = Comment(commentToken.data + codePoint.toChar())
                consumeNextChar { BogusComment(newToken) }
            }
        }
    }
}