package com.hussain.csoup.tokenization.states

import com.hussain.csoup.util.CharCodes
import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.util.isAsciiAlpha

internal data object EndTagOpen : TokenizationState() {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        return when {
            codePoint.isAsciiAlpha() -> {
                val endTagToken = Token.EndTag(name = "")
                reconsumeCurrentChar { TagName(endTagToken) }
            }

            codePoint == CharCodes.GreaterThan.code -> {
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