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
internal data object NamedCharacterReference : TokenizationState {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        // "Consume the maximum number of characters possible, where the consumed characters are one of the
        // identifiers in the first column of the named character references table. Append each character to the
        // temporary buffer when it's consumed." The state is entered by reconsuming, so the current input character
        // is the first that can be consumed.
        val match = longestMatch(tokenizer)
            ?: run {
                // Otherwise: nothing was consumed, so switching reconsumes the current input character
                tokenizer.returnState.flushCodePoints(tokenizer)
                return reconsumeIn(AmbiguousAmpersand)
            }
        tokenizer.tempBuffer.append(match.name)
        tokenizer.currentPosition += match.name.length

        val lastCharacterMatchedIsSemicolon = match.name.endsWith(';')
        val nextInputCharacter = tokenizer.htmlBuffer.getOrNull(tokenizer.currentPosition)

        // The matched characters are consumed, so from here "switch to" a state reconsumes the next input character
        if (tokenizer.returnState.isAttributeState() && !lastCharacterMatchedIsSemicolon && nextInputCharacter != null &&
            (nextInputCharacter == '=' || nextInputCharacter.code.isAsciiAlphaNumeric())
        ) {
            // For historical reasons
            tokenizer.returnState.flushCodePoints(tokenizer)
            return reconsumeIn(tokenizer.returnState)
        }

        if (!lastCharacterMatchedIsSemicolon) {
            tokenizer.logParseError("missing-semicolon-after-character-reference")
        }
        tokenizer.tempBuffer.clear()
        tokenizer.tempBuffer.append(match.characters)
        tokenizer.returnState.flushCodePoints(tokenizer)
        return reconsumeIn(tokenizer.returnState)
    }

    private fun longestMatch(tokenizer: Tokenizer): EntityMatch? {
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
            currentNode.entityMatch?.let { longestMatch = it }
        }
        return longestMatch
    }
}

private class EntityMatch(
    val name: String,
    val characters: String
)

private class TrieNode(
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
