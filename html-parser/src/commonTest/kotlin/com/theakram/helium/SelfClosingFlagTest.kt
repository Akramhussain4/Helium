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
package com.theakram.helium

import kotlin.test.Test
import kotlin.test.assertEquals

internal class SelfClosingFlagTest {

    private fun codes(html: String) = HTMLParser().run {
        parse("<!DOCTYPE html>$html")
        // Only the errors with codes, as the self-closing flag's is; the rest are tree construction's own
        errors.mapNotNull { it.code }
    }

    @Test
    fun `a non-void html element with a trailing solidus is a parse error`() {
        assertEquals(listOf("non-void-html-element-start-tag-with-trailing-solidus"), codes("<div/>"))
    }

    @Test
    fun `void and foreign elements acknowledge the self-closing flag`() {
        // The hidden input's value is quoted: in an unquoted value the solidus would be part of the value
        val html = "<link/><basefont/><meta/><br/><img/><input/><hr/><source/><svg/><math/><svg><path/></svg>" +
            "<svg><script/></svg><table><col/><input type=\"hidden\"/></table>"
        assertEquals(emptyList(), codes(html))
    }

    @Test
    fun `a frameset frame acknowledges the self-closing flag`() {
        assertEquals(emptyList(), codes("<frameset><frame/></frameset>"))
    }
}
