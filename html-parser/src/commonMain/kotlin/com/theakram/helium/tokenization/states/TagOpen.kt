package com.theakram.helium.tokenization.states

import com.theakram.helium.util.Chars
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.IS
import com.theakram.helium.util.isAsciiAlpha

internal data object TagOpen : TokenizationState {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        return when {
            codePoint.IS(Chars.ExclamationMark) -> consumeNextChar { MarkupDeclarationOpen }

            codePoint.IS(Chars.Solidus) -> consumeNextChar { EndTagOpen }

            codePoint.isAsciiAlpha() -> {
                val startTagToken = Token.StartTag(name = "")
                reconsumeCurrentChar { TagName(startTagToken) }
            }

            codePoint.IS(Chars.QuestionMark) -> {
                tokenizer.logParseError("unexpected-question-mark-instead-of-tag-name")
                val commentToken = Token.Comment(data = "")
                reconsumeCurrentChar { BogusComment(commentToken) }
            }

            codePoint == -1 -> {
                tokenizer.logParseError("eof-before-tag-name")
                tokenizer.emitToken(Token.Character(data = '<'))
                endOfFile { this }
            }

            else -> {
                tokenizer.logParseError("invalid-first-character-of-tag-name")
                val charToken = Token.Character(data = '<')
                tokenizer.emitToken(charToken)
                reconsumeCurrentChar { Data }
            }
        }
    }
}