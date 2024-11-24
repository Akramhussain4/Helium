package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.Chars
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.util.IS
import com.hussain.csoup.util.isAsciiAlpha

internal data object TagOpen : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
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