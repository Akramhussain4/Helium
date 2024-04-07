package com.hussain.csoup.tokenization

import com.hussain.csoup.tokenization.states.Action
import com.hussain.csoup.tokenization.states.Data
import com.hussain.csoup.tokenization.states.TokenizationState

internal class Tokenizer {
    var characterReferenceCode = 0
    val tempBuffer = StringBuilder()
    val emittedTokens: MutableList<Token> = mutableListOf()

    private var currentPosition = 0
    private var htmlBuffer: String = ""

    fun tokenize(html: String) {
        htmlBuffer = html
        var currentState: TokenizationState = Data
        while (currentPosition < htmlBuffer.length) {
            val codePoint = htmlBuffer[currentPosition].code
            val (nextState, action) = currentState.consume(codePoint, this)

            currentState = nextState
            when (action) {
                is Action.ConsumeNext -> currentPosition++
                is Action.Reconsume -> Unit // Do nothing, reconsume
            }
        }
    }

    fun emitToken(token: Token) {
        emittedTokens.add(token)
    }

    fun peekNextChar(): Char {
        if (currentPosition + 1 < htmlBuffer.length) {
            return htmlBuffer[currentPosition + 1]
        }
        return '\u0000'
    }
}