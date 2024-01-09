package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer

internal class NumericCharacterReferenceEnd(private val charRefCode: Int) : TokenizationState() {
    override fun consume(codePoint: Int, tokenizer: Tokenizer): Pair<TokenizationState, Action> {
        when {
            charRefCode == 0x00 -> {
                // TODO Implement parse error
                val newCharRefCode = 0xFFFD
            }

            charRefCode > 0x10FFFF -> {
                // TODO Implement parse error
                val newCharRefCode = 0xFFFD
            }

            charRefCode in 0xD800..0xDBFF ||
                    charRefCode in 0xDC00..0xDFFF -> {
                // TODO Implement parse error
                val newCharRefCode = 0xFFFD
            }

            // TODO Add more cases
            charRefCode in 0xFDD0..0xFDEF -> {
                // TODO Implement parse error
                val newCharRefCode = 0xFFFD
            }

            // TODO Add more cases
            charRefCode == 0x0D -> {
                val newCharRefCode = 0x000D
            }

            else -> {
                val newCharRefCode = charRefCode
            }
        }
        TODO()
    }
}