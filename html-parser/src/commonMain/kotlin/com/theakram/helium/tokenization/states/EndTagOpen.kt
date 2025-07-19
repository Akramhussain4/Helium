package com.theakram.helium.tokenization.states

import com.theakram.helium.util.Chars
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.isAsciiAlpha

internal data object EndTagOpen : TokenizationState {

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