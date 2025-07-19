package com.theakram.helium.tokenization.util

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.states.TokenizationState

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