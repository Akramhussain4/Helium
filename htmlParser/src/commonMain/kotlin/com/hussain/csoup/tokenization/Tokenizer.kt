package com.hussain.csoup.tokenization

import com.hussain.csoup.tokenization.states.Action
import com.hussain.csoup.tokenization.states.Data
import com.hussain.csoup.tokenization.states.TokenizationState
import com.hussain.csoup.util.Chars

internal class Tokenizer {
    var characterReferenceCode = 0
    val tempBuffer = StringBuilder()
    val emittedTokens: MutableList<Token> = mutableListOf()

    private var currentPosition = 0
    private var htmlBuffer: String = ""

    fun tokenize(html: String) {
        htmlBuffer = html
        tokenizeRecursive(Data)
    }

    private tailrec fun tokenizeRecursive(currentState: TokenizationState) {
        if (currentPosition < htmlBuffer.length) {
            val codePoint = htmlBuffer[currentPosition].code
            val (nextState, action) = currentState.consume(codePoint, this)

            when (action) {
                Action.ConsumeNext -> {
                    currentPosition++
                    tokenizeRecursive(nextState)
                }

                Action.Reconsume -> {
                    tokenizeRecursive(nextState)
                }

                Action.None -> Unit
            }
        } else {
            currentState.consume(Chars.EOF.char.code, this)
        }
    }

    fun emitToken(token: Token) {
        emittedTokens.add(token)
    }

    fun peekNextChar(): Char {
        if (currentPosition + 1 < htmlBuffer.length) {
            return htmlBuffer[currentPosition + 1]
        }
        return Chars.Null.char
    }

    fun logParseError(error: String) {
        println("‼️Parse error: $error at position: $currentPosition")
    }
}