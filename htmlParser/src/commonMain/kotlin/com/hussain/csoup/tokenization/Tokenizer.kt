package com.hussain.csoup.tokenization

import com.hussain.csoup.tokenization.states.Action
import com.hussain.csoup.tokenization.states.Data
import com.hussain.csoup.tokenization.states.TokenizationState
import com.hussain.csoup.util.Chars

internal class Tokenizer {
    var currentPosition = 0
    lateinit var htmlBuffer: String
    private var currentState: TokenizationState = Data

    var characterReferenceCode = 0
    val tempBuffer = StringBuilder(DEFAULT_BUFFER_SIZE)
    val emittedTokens = mutableListOf<Token>()

    var currentAttributeName = StringBuilder(DEFAULT_BUFFER_SIZE)
    var currentAttributeValue = StringBuilder(DEFAULT_BUFFER_SIZE)

    fun tokenize(html: String) {
        htmlBuffer = preprocess(html)

        while (currentPosition <= htmlBuffer.length) {
            if (currentPosition == htmlBuffer.length) {
                val (nextState, action) = handleEOF()
                if (action == Action.None) break
                currentState = nextState
                continue
            }

            val (nextState, action) = currentState.consume(
                codePoint = htmlBuffer[currentPosition].code,
                tokenizer = this
            )

            when (action) {
                Action.ConsumeNext -> {
                    currentPosition++
                    currentState = nextState
                }

                Action.Reconsume -> currentState = nextState
                Action.None -> break
            }
        }
    }

    private fun preprocess(input: String): String {
        val step1 = input.replace("\r\n", "\n")
        return step1.replace('\r', '\n')
    }

    fun addCurrentAttributeToToken(currentToken: Token) {
        if (currentToken is Token.StartTag && currentAttributeName.isNotEmpty()) {
            val name = currentAttributeName.toString()
            val value = currentAttributeValue.toString()
            currentToken.addAttribute(name, value)
        }
        // Clear buffers for the next attribute
        currentAttributeName.clear()
        currentAttributeValue.clear()
    }

    private fun handleEOF() = currentState.consume(-1, this)

    fun emitToken(token: Token) = emittedTokens.add(token)

    fun peekNextChar(): Char = htmlBuffer.getOrNull(currentPosition + 1) ?: Chars.EOF.char

    fun logParseError(error: String) = System.err.println("Parse error: $error at position: $currentPosition")
}