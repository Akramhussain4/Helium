package com.hussain.csoup.tokenization.util

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import com.hussain.csoup.tokenization.states.AttributeValueDoubleQuoted
import com.hussain.csoup.tokenization.states.AttributeValueSingleQuoted
import com.hussain.csoup.tokenization.states.AttributeValueUnquoted
import com.hussain.csoup.tokenization.states.TokenizationState

internal fun TokenizationState.flushCodePoints(tokenizer: Tokenizer) {
    when (this) {
        is AttributeValueDoubleQuoted -> {
            val currentToken = currentTagToken
            val attributeName = currentAttributeName
            currentToken.updateAttributeValue(attributeName, tokenizer)
        }

        is AttributeValueSingleQuoted -> {
            val currentToken = currentTagToken
            val attributeName = currentAttributeName
            currentToken.updateAttributeValue(attributeName, tokenizer)
        }

        is AttributeValueUnquoted -> {
            val currentToken = currentTagToken
            val attributeName = currentAttributeName
            currentToken.updateAttributeValue(attributeName, tokenizer)
        }

        else -> {
            tokenizer.tempBuffer.forEach { char ->
                val token = Token.Character(char)
                tokenizer.emitToken(token)
            }
        }
    }
}

internal fun Token.updateAttributeValue(attributeName: String, tokenizer: Tokenizer) {
    when (this) {
        is Token.StartTag -> {
            val currentValue = attributes.getValue(attributeName)
            attributes[attributeName] = currentValue + tokenizer.tempBuffer.toString()
        }

        else -> throw IllegalStateException("Expected a StartTag token, got $this")
    }
}