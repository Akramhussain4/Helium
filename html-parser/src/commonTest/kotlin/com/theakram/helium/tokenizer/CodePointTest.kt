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
package com.theakram.helium.tokenizer

import com.theakram.helium.HTMLParser
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * The tokenizer reads the input as code points, so a supplementary character is one input character, not two
 * UTF-16 surrogates.
 */
internal class CodePointTest {

    private fun errors(html: String) = HTMLParser().run {
        parse(html)
        errors.map { it.code to it.position }
    }

    @Test
    fun `a supplementary character is not a surrogate in the input stream`() {
        assertEquals(emptyList(), errors("<p>😀</p>"))
    }

    @Test
    fun `a lone surrogate is a surrogate in the input stream`() {
        // Built at run time: incremental Kotlin/JS builds turn a lone surrogate in a string literal into "?"
        val loneSurrogate = charArrayOf('\uD800').concatToString()
        assertEquals(listOf("surrogate-in-input-stream" to 3), errors("<p>$loneSurrogate</p>"))
    }

    @Test
    fun `a noncharacter above U+FFFF is reported once`() {
        assertEquals(listOf("noncharacter-in-input-stream" to 3), errors("<p>🿿</p>"))
    }

    @Test
    fun `controls are reported where they are in the input`() {
        assertEquals(
            listOf("control-character-in-input-stream" to 1, "control-character-in-input-stream" to 3),
            errors("a\u0001b\u007F\u0009c")
        )
    }

    @Test
    fun `input stream errors are reported in order with the tokenizer's`() {
        // The comment is opened wrongly at position 2; the noncharacter is reached at position 5, while consuming it
        assertEquals(
            listOf("incorrectly-opened-comment" to 2, "noncharacter-in-input-stream" to 5),
            errors("<!doc﷑")
        )
    }

    @Test
    fun `supplementary characters in tag and attribute names are kept whole`() {
        val element = HTMLParser().parse("<x-😀 a😀=\"😀\">")
            .findChildElement("html")!!.findChildElement("body")!!.children.single()

        assertEquals("x-😀", element.localName)
        assertEquals("a😀", element.attributes.single().localName)
        assertEquals("😀", element.attributes.single().value)
    }
}
