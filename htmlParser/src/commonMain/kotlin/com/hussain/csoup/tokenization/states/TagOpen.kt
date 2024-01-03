package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal data object TagOpen : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            CharCodes.ExclamationMark.code -> consumeNextChar { MarkupDeclarationOpen }
            CharCodes.Solidus.code -> consumeNextChar { EndTagOpen }

            in CharCodes.UpperA.code..CharCodes.UpperZ.code,
            in CharCodes.LowerA.code..CharCodes.LowerZ.code -> {
                val startTagToken = Token.StartTag(name = "")
                reconsumeCurrentChar { TagName(startTagToken) }
            }

            CharCodes.QuestionMark.code -> {
                // TODO: Implement parse error
                val commentToken = Token.Comment(data = "")
                reconsumeCurrentChar { BogusComment(commentToken) }
            }

            else -> {
                // TODO: Implement parse error
                val charToken = Token.Character(data = CharCodes.LessThan.code.toChar())
                tokenizer.emitToken(charToken)
                reconsumeCurrentChar { Data }
            }
        }
    }
}