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

package com.theakram.helium.tokenization

internal sealed interface Token {

    data class DocType(
        val name: String?,
        val publicId: String?,
        val systemId: String?,
        val forceQuirks: Boolean
    ) : Token

    data class StartTag(
        val name: String,
        val selfClosing: Boolean = false,
        val attributes: Map<String, String> = emptyMap()
    ) : Token

    /**
     * An end tag token. Like a start tag token it has a self-closing flag and attributes, which the tokenizer
     * reports as parse errors and tree construction ignores.
     */
    data class EndTag(
        val name: String,
        val selfClosing: Boolean = false,
        val attributes: Map<String, String> = emptyMap()
    ) : Token

    data class Comment(val data: String) : Token

    data class ProcessingInstruction(val target: String, val data: String) : Token

    /** A character token, holding one code point. */
    data class Character(val codePoint: Int) : Token

    data object EOF : Token
}

/**
 * Builder for DocType tokens. Accumulates state during tokenization.
 */
internal class DocTypeBuilder {
    val name = StringBuilder()
    val publicId = StringBuilder()
    val systemId = StringBuilder()
    var forceQuirks: Boolean = false
    var publicIdSet: Boolean = false
    var systemIdSet: Boolean = false

    fun build(): Token.DocType = Token.DocType(
        name = if (name.isEmpty()) null else name.toString(),
        publicId = if (publicIdSet) publicId.toString() else null,
        systemId = if (systemIdSet) systemId.toString() else null,
        forceQuirks = forceQuirks
    )

    fun reset() {
        name.clear()
        publicId.clear()
        systemId.clear()
        forceQuirks = false
        publicIdSet = false
        systemIdSet = false
    }
}

/**
 * The current tag token, a start or an end tag, as the tokenizer builds it.
 *
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#tokenization
 */
internal class TagTokenBuilder {
    private class AttributeBuilder {
        val name = StringBuilder()
        val value = StringBuilder()
    }

    var isStartTag: Boolean = true
        private set
    val name = StringBuilder()
    var selfClosing: Boolean = false
    private val attributes = mutableListOf<AttributeBuilder>()
    private val attributeNames = HashSet<String>()

    // The attribute being built. One removed as a duplicate stays here, so the rest of it is consumed and dropped.
    private var currentAttribute = AttributeBuilder()

    val currentAttributeName: StringBuilder get() = currentAttribute.name
    val currentAttributeValue: StringBuilder get() = currentAttribute.value
    val hasAttributes: Boolean get() = attributes.isNotEmpty()

    /** Starts a new start or end tag token, with an empty name, the self-closing flag unset and no attributes. */
    fun create(isStartTag: Boolean) {
        this.isStartTag = isStartTag
        name.clear()
        selfClosing = false
        attributes.clear()
        attributeNames.clear()
        currentAttribute = AttributeBuilder()
    }

    /** "Start a new attribute in the current tag token": its name and value are the empty string. */
    fun startNewAttribute() {
        currentAttribute = AttributeBuilder()
        attributes += currentAttribute
    }

    /**
     * Run when leaving the attribute name state: "if there is already an attribute on the token with the exact same
     * name, then this is a duplicate-attribute parse error and the new attribute must be removed from the token."
     *
     * @return whether the attribute was a duplicate
     */
    fun removeCurrentAttributeIfDuplicate(): Boolean {
        if (attributeNames.add(currentAttribute.name.toString())) return false
        // The current attribute is always the one added last
        attributes.removeAt(attributes.lastIndex)
        return true
    }

    fun build(): Token {
        val attributes = attributes.associate { it.name.toString() to it.value.toString() }
        return if (isStartTag) {
            Token.StartTag(name = name.toString(), selfClosing = selfClosing, attributes = attributes)
        } else {
            Token.EndTag(name = name.toString(), selfClosing = selfClosing, attributes = attributes)
        }
    }
}
