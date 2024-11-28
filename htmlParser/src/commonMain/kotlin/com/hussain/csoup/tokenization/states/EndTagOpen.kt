package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.isAsciiAlpha

internal data object EndTagOpen : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.isAsciiAlpha() -> {
                val endTagToken = Token.EndTag(name = "")
                reconsumeCurrentChar { TagName(endTagToken) }
            }

            codePoint.IS(Chars.GreaterThan) -> {
                tokenizer.logParseError("missing-end-tag-name")
                consumeNextChar { Data }
            }

            codePoint == -1 -> {
                tokenizer.logParseError("eof-before-tag-name")
                tokenizer.emitToken(Token.Character(data = '<'))
                tokenizer.emitToken(Token.Character(data = '/'))
                endOfFile { this }
            }

            else -> {
                tokenizer.logParseError("invalid-first-character-of-tag-name")
                val commentToken = Token.Comment(data = "")
                reconsumeCurrentChar { BogusComment(commentToken) }
            }
        }
    }
}