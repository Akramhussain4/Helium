/*
 * MIT License
 *
 * Copyright (c) 2023-2026 Mohammed Akram Hussain
 * Website: https://theakram.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.theakram.helium.tokenization.states

import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.NamedChars
import com.theakram.helium.tokenization.util.flushCodePoints
import com.theakram.helium.tokenization.util.isAttributeState
import com.theakram.helium.util.isAsciiAlphaNumeric

/**
 * https://html.spec.whatwg.org/multipage/parsing.html#named-character-reference-state
 */
internal class NamedCharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        val match = consumeMaximalMatch(tokenizer)

        return if (match != null) {
            processMatch(match, tokenizer)
        } else {
            processNoMatch(tokenizer)
        }
    }

    private fun consumeMaximalMatch(tokenizer: Tokenizer): EntityMatch? {
        var currentNode = NamedCharacterReferenceTrie.root
        var longestMatch: EntityMatch? = null
        var currentLength = 0

        val initialPosition = tokenizer.currentPosition
        val inputBuffer = tokenizer.htmlBuffer

        while (initialPosition + currentLength < inputBuffer.length) {
            val char = inputBuffer[initialPosition + currentLength]
            val nextNode = currentNode.children[char] ?: break

            currentNode = nextNode
            currentLength++
            currentNode.entityMatch?.let {
                longestMatch = it.copy(matchLength = currentLength)
            }
        }
        return longestMatch
    }

    private fun processNoMatch(tokenizer: Tokenizer): StateTransition {
        returnState.flushCodePoints(tokenizer)
        return reconsumeIn(AmbiguousAmpersand(returnState))
    }

    private fun processMatch(match: EntityMatch, tokenizer: Tokenizer): StateTransition {
        val endsWithSemicolon = match.name.endsWith(';')
        val nextChar = tokenizer.htmlBuffer.getOrNull(tokenizer.currentPosition + match.matchLength)

        return when {
            returnState.isAttributeState() && !endsWithSemicolon && nextChar != null &&
                    (nextChar == '=' || nextChar.code.isAsciiAlphaNumeric()) -> {
                returnState.flushCodePoints(tokenizer)
                tokenizer.currentAttributeValue.append(match.name)
                tokenizer.currentPosition += match.matchLength
                reconsumeIn(returnState)
            }

            else -> processStandardMatch(match, endsWithSemicolon, tokenizer)
        }
    }

    private fun processStandardMatch(
        match: EntityMatch,
        endsWithSemicolon: Boolean,
        tokenizer: Tokenizer
    ): StateTransition {
        if (!endsWithSemicolon) {
            tokenizer.logParseError("missing-semicolon-after-character-reference")
        }
        tokenizer.tempBuffer.clear()
        tokenizer.tempBuffer.append(match.characters)
        returnState.flushCodePoints(tokenizer)
        tokenizer.currentPosition += match.matchLength
        return reconsumeIn(returnState)
    }
}

private data class EntityMatch(
    val name: String,
    val characters: String,
    val matchLength: Int = 0
)

private data class TrieNode(
    val children: MutableMap<Char, TrieNode> = mutableMapOf(),
    var entityMatch: EntityMatch? = null
)

private object NamedCharacterReferenceTrie {
    val root = TrieNode()

    init {
        buildTrie()
    }

    private fun buildTrie() {
        NamedChars.forEach { (fullName, characters) ->
            val entityName = fullName.removePrefix("&")
            val leafNode = entityName.fold(root) { node, char ->
                node.children.getOrPut(char) { TrieNode() }
            }
            leafNode.entityMatch = EntityMatch(
                name = entityName,
                characters = characters
            )
        }
    }
}
