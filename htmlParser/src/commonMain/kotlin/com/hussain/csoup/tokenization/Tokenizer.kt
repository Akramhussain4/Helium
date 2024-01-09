package com.hussain.csoup.tokenization

import com.hussain.csoup.tokenization.states.Action
import com.hussain.csoup.tokenization.states.Data
import com.hussain.csoup.tokenization.states.TokenizationState

internal class Tokenizer() {

    val emittedTokens: MutableList<Token> = mutableListOf()
    private var currentPosition = 0
    private var htmlBuffer: String = ""

    fun tokenize(html: String) {
        var currentState: TokenizationState = Data
        this.htmlBuffer = html
        while (currentPosition < html.length) {
            val codePoint = html[currentPosition].code
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
        return htmlBuffer[currentPosition + 1]
    }
}