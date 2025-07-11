package com.hussain.csoup.tokenization.util

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.states.TokenizationState

internal fun TokenizationState.flushCodePoints(tokenizer: Tokenizer) {
    if (this.isAttributeState()) {
        tokenizer.currentAttributeValue.append(tokenizer.tempBuffer)
    } else {
        tokenizer.tempBuffer.forEach { char ->
            tokenizer.emitToken(Token.Character(char))
        }
    }
    tokenizer.tempBuffer.clear()
}