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

package com.theakram.helium.tree

/**
 * A processing instruction's attribute map: the pseudo-attributes in its data, such as name="x".
 *
 * Spec: https://dom.spec.whatwg.org/#concept-pi-attribute-map
 */
internal object ProcessingInstructionAttributes {

    /**
     * Spec: https://dom.spec.whatwg.org/#get-a-processing-instruction-attribute
     */
    fun get(processingInstruction: TreeNode, name: String): String? =
        parsePseudoAttributes(processingInstruction.data.orEmpty())?.get(name)

    /**
     * Returns the pseudo-attributes in [string], or null if parsing it is an error. Updating a processing
     * instruction's attributes from its data stores nothing when parsing is an error.
     *
     * Spec: https://www.w3.org/TR/xml-stylesheet/#dt-parsing
     */
    fun parsePseudoAttributes(string: String): Map<String, String>? {
        val pseudoAttributes = LinkedHashMap<String, String>()
        var position = 0

        // [1a] PseudoAtts ::= PseudoAtt? (S PseudoAtt)* S?
        while (true) {
            val whitespaceStart = position
            while (position < string.length && string[position].isXmlWhitespace()) position++
            if (position == string.length) break
            if (pseudoAttributes.isNotEmpty() && position == whitespaceStart) return null

            // [2] PseudoAtt ::= Name S? "=" S? PseudoAttValue
            val nameEnd = endOfName(string, position) ?: return null
            val name = string.substring(position, nameEnd)
            position = nameEnd
            while (position < string.length && string[position].isXmlWhitespace()) position++
            if (position == string.length || string[position] != '=') return null
            position++
            while (position < string.length && string[position].isXmlWhitespace()) position++

            // [3] PseudoAttValue
            if (position == string.length || (string[position] != '"' && string[position] != '\'')) return null
            val quote = string[position]
            position++
            val value = StringBuilder()
            while (true) {
                if (position == string.length) return null
                val char = string[position]
                when (char) {
                    quote -> break
                    '<' -> return null
                    '&' -> {
                        val referenceEnd = string.indexOf(';', position)
                        if (referenceEnd == -1) return null
                        val replacement = replaceReference(string.substring(position, referenceEnd + 1)) ?: return null
                        value.append(replacement)
                        position = referenceEnd + 1
                    }
                    else -> {
                        value.append(char)
                        position++
                    }
                }
            }
            position++

            // More than one pseudo-attribute with the same name is an error
            if (pseudoAttributes.containsKey(name)) return null
            pseudoAttributes[name] = value.toString()
        }
        return pseudoAttributes
    }

    // [4] PredefEntityRef, and CharRef replaced with the character it represents, if that is a legal character
    private fun replaceReference(reference: String): String? {
        when (reference) {
            "&amp;" -> return "&"
            "&lt;" -> return "<"
            "&gt;" -> return ">"
            "&quot;" -> return "\""
            "&apos;" -> return "'"
        }
        // CharRef ::= '&#' [0-9]+ ';' | '&#x' [0-9a-fA-F]+ ';'
        val codePoint = when {
            reference.startsWith("&#x") -> reference.substring(3, reference.length - 1)
                .takeIf { digits -> digits.isNotEmpty() && digits.all { it in '0'..'9' || it in 'a'..'f' || it in 'A'..'F' } }
                ?.toLongOrNull(16)

            reference.startsWith("&#") -> reference.substring(2, reference.length - 1)
                .takeIf { digits -> digits.isNotEmpty() && digits.all { it in '0'..'9' } }
                ?.toLongOrNull()

            else -> null
        } ?: return null
        if (!isXmlChar(codePoint)) return null
        val code = codePoint.toInt()
        return if (code < 0x10000) {
            code.toChar().toString()
        } else {
            val offset = code - 0x10000
            charArrayOf((0xD800 + (offset shr 10)).toChar(), (0xDC00 + (offset and 0x3FF)).toChar()).concatToString()
        }
    }

    // Returns the index after the Name starting at [start], or null if there isn't one
    private fun endOfName(string: String, start: Int): Int? {
        var position = start
        var first = true
        while (position < string.length) {
            val codePoint = codePointAt(string, position)
            val isNameChar = if (first) isNameStartChar(codePoint) else isNameChar(codePoint)
            if (!isNameChar) break
            position += if (codePoint >= 0x10000) 2 else 1
            first = false
        }
        return if (first) null else position
    }

    private fun codePointAt(string: String, index: Int): Int {
        val high = string[index]
        if (high.isHighSurrogate() && index + 1 < string.length && string[index + 1].isLowSurrogate()) {
            return 0x10000 + ((high.code - 0xD800) shl 10) + (string[index + 1].code - 0xDC00)
        }
        return high.code
    }

    // S ::= (#x20 | #x9 | #xD | #xA)+
    private fun Char.isXmlWhitespace(): Boolean = this == ' ' || this == '\t' || this == '\r' || this == '\n'

    // Char ::= #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
    private fun isXmlChar(codePoint: Long): Boolean =
        codePoint == 0x9L || codePoint == 0xAL || codePoint == 0xDL ||
                codePoint in 0x20L..0xD7FFL || codePoint in 0xE000L..0xFFFDL || codePoint in 0x10000L..0x10FFFFL

    // NameStartChar, from https://www.w3.org/TR/xml/#NT-NameStartChar
    private fun isNameStartChar(c: Int): Boolean =
        c == ':'.code || c in 'A'.code..'Z'.code || c == '_'.code || c in 'a'.code..'z'.code ||
                c in 0xC0..0xD6 || c in 0xD8..0xF6 || c in 0xF8..0x2FF || c in 0x370..0x37D ||
                c in 0x37F..0x1FFF || c in 0x200C..0x200D || c in 0x2070..0x218F || c in 0x2C00..0x2FEF ||
                c in 0x3001..0xD7FF || c in 0xF900..0xFDCF || c in 0xFDF0..0xFFFD || c in 0x10000..0xEFFFF

    // NameChar, from https://www.w3.org/TR/xml/#NT-NameChar
    private fun isNameChar(c: Int): Boolean =
        isNameStartChar(c) || c == '-'.code || c == '.'.code || c in '0'.code..'9'.code || c == 0xB7 ||
                c in 0x0300..0x036F || c in 0x203F..0x2040
}
