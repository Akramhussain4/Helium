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
package com.theakram.helium.tokenizer.helpers

/**
 * A minimal JSON reader for the html5lib tokenizer tests. Objects become [Map]s, arrays [List]s, numbers [Double]s.
 */
internal class Json private constructor(private val text: String) {
    private var index = 0

    companion object {
        fun parse(text: String): Any? = Json(text).run {
            val value = readValue()
            skipWhitespace()
            check(index == text.length) { "Unexpected trailing content at $index" }
            value
        }
    }

    private fun readValue(): Any? {
        skipWhitespace()
        return when (val c = text[index]) {
            '{' -> readObject()
            '[' -> readArray()
            '"' -> readString()
            't' -> readLiteral("true", true)
            'f' -> readLiteral("false", false)
            'n' -> readLiteral("null", null)
            else -> if (c == '-' || c.isDigit()) readNumber() else error("Unexpected '$c' at $index")
        }
    }

    private fun readObject(): Map<String, Any?> {
        val result = LinkedHashMap<String, Any?>()
        expect('{')
        skipWhitespace()
        if (text[index] == '}') return result.also { index++ }
        while (true) {
            skipWhitespace()
            val key = readString()
            skipWhitespace()
            expect(':')
            result[key] = readValue()
            skipWhitespace()
            if (text[index] == '}') return result.also { index++ }
            expect(',')
        }
    }

    private fun readArray(): List<Any?> {
        val result = mutableListOf<Any?>()
        expect('[')
        skipWhitespace()
        if (text[index] == ']') return result.also { index++ }
        while (true) {
            result.add(readValue())
            skipWhitespace()
            if (text[index] == ']') return result.also { index++ }
            expect(',')
        }
    }

    private fun readString(): String {
        expect('"')
        val result = StringBuilder()
        while (true) {
            when (val c = text[index++]) {
                '"' -> return result.toString()
                '\\' -> when (val escape = text[index++]) {
                    'u' -> result.append(text.substring(index, index + 4).toInt(16).toChar()).also { index += 4 }
                    'n' -> result.append('\n')
                    't' -> result.append('\t')
                    'r' -> result.append('\r')
                    'b' -> result.append('\b')
                    'f' -> result.append('\u000C')
                    '"', '\\', '/' -> result.append(escape)
                    else -> error("Unknown escape \\$escape at $index")
                }
                else -> result.append(c)
            }
        }
    }

    private fun readNumber(): Double {
        val start = index
        while (index < text.length && (text[index].isDigit() || text[index] in "+-.eE")) index++
        return text.substring(start, index).toDouble()
    }

    private fun readLiteral(literal: String, value: Any?): Any? {
        check(text.startsWith(literal, index)) { "Expected $literal at $index" }
        index += literal.length
        return value
    }

    private fun expect(c: Char) {
        check(text[index] == c) { "Expected '$c' at $index but found '${text[index]}'" }
        index++
    }

    private fun skipWhitespace() {
        while (index < text.length && text[index].isWhitespace()) index++
    }
}
