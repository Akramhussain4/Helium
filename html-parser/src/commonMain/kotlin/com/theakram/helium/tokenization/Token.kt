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

    data class EndTag(val name: String) : Token

    data class Comment(val data: String) : Token

    data class ProcessingInstruction(val target: String, val data: String) : Token

    data class Character(val data: Char) : Token

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
 * Builder for StartTag tokens. Accumulates attributes during tokenization.
 */
internal class StartTagBuilder {
    val name = StringBuilder()
    private val attributes = LinkedHashMap<String, String>()

    fun addAttribute(attrName: String, attrValue: String) {
        if (!attributes.containsKey(attrName)) {
            attributes[attrName] = attrValue
        }
    }

    fun build(selfClosing: Boolean = false) = Token.StartTag(
        name = name.toString(),
        selfClosing = selfClosing,
        attributes = attributes.toMap()
    )

    fun reset() {
        name.clear()
        attributes.clear()
    }
}