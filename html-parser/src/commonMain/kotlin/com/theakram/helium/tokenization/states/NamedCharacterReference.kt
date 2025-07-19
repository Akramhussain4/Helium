package com.theakram.helium.tokenization.states

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.util.NamedChars
import com.theakram.helium.tokenization.util.flushCodePoints
import com.theakram.helium.tokenization.util.isAttributeState

/**
 * https://html.spec.whatwg.org/multipage/parsing.html#named-character-reference-state
 */
internal class NamedCharacterReference(
    private val returnState: TokenizationState,
) : TokenizationState {

    override fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition {
        var currentNode = EntityTrie.root
        var consumedChars = ""
        var lastMatch: Triple<String, IntArray, Int>? = null
        var currentPosition = 0

        val inputBuffer = tokenizer.htmlBuffer
        val initialPosition = tokenizer.currentPosition

        // 1. Consume as many characters as possible that form a valid path in the Trie.
        while (true) {
            val charCode = inputBuffer.getOrNull(initialPosition + currentPosition)?.code ?: -1
            if (charCode == -1) break

            val char = charCode.toChar()
            val nextNode = currentNode.children[char] ?: break

            currentNode = nextNode
            consumedChars += char
            currentPosition++

            currentNode.codepoints?.let {
                lastMatch = Triple(consumedChars, it, currentPosition)
            }
        }

        // 2. PROCESS THE RESULT of the maximal munch.
        if (lastMatch == null) {
            // No valid named entity was ever matched (e.g., "&foo").
            // This is an "ambiguous ampersand". Flush the ampersand and switch to the return state.
            returnState.flushCodePoints(tokenizer) // Flushes the initial '&' from the temp buffer
            return reconsumeCurrentChar { returnState }
        }

        val (match, codepoints, matchLength) = lastMatch
        val isSemicolonTerminated = match.endsWith(';')

        val nextChar = inputBuffer.getOrNull(initialPosition + matchLength)
        if (returnState.isAttributeState() && !isSemicolonTerminated && nextChar != null && (nextChar.isLetterOrDigit() || nextChar == '=')) {
            // Legacy behavior: The entity is NOT decoded. Treat the ampersand and the
            // matched characters as literal text.
            returnState.flushCodePoints(tokenizer) // Flushes the initial '&'
            tokenizer.currentAttributeValue.append(match) // Appends the matched text e.g., "not"

            tokenizer.currentPosition += matchLength
            return reconsumeCurrentChar { returnState }
        } else {
            // Standard behavior for all other cases (in the data state or terminated correctly in the attribute state).
            if (!isSemicolonTerminated) {
                tokenizer.logParseError("missing-semicolon-after-character-reference")
            }

            tokenizer.currentPosition += matchLength
            emitCodepoints(codepoints, tokenizer)
            return reconsumeCurrentChar { returnState }
        }
    }

    private fun emitCodepoints(codepoints: IntArray, tokenizer: Tokenizer) {
        val decodedChars = String(codepoints, 0, codepoints.size)
        if (returnState.isAttributeState()) {
            tokenizer.currentAttributeValue.append(decodedChars)
        } else {
            decodedChars.forEach { char ->
                tokenizer.emitToken(Token.Character(char))
            }
        }
        tokenizer.tempBuffer.clear()
    }
}

/**
 * A singleton object responsible for building and holding the master Trie
 * for all named character references. This ensures the expensive build process
 * happens only once.
 */
private object EntityTrie {
    val root = TrieNode()
    init {
        NamedChars.forEach { (name, characters) ->
            val entityName = name.removePrefix("&")
            var currentNode = root
            entityName.forEach { char ->
                currentNode = currentNode.children.getOrPut(char) { TrieNode() }
            }
            currentNode.codepoints = characters.codePoints().toArray()
        }
    }
}

private data class TrieNode(
    val children: MutableMap<Char, TrieNode> = mutableMapOf(),
    // If this node marks the end of a valid entity, 'codepoints' will hold the decoded value.
    var codepoints: IntArray? = null
)