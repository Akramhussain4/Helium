package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer

internal data object EndTagOpen : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when (codePoint) {
            in CharCodes.UpperA.code..CharCodes.UpperZ.code,
            in CharCodes.LowerA.code..CharCodes.LowerZ.code -> {
                val endTagToken = Token.EndTag(name = "")
                reconsumeCurrentChar { TagName(endTagToken) }
            }

            CharCodes.GreaterThan.code -> {
                // TODO Implement parse error
                consumeNextChar { Data }
            }

            else -> {
                // TODO Implement parse error
                val commentToken = Token.Comment(data = "")
                reconsumeCurrentChar { BogusComment(commentToken) }
            }
        }
    }
}