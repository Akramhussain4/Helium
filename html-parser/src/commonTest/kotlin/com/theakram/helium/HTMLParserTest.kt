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

import com.theakram.helium.tree.QuirksMode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame

internal class HTMLParserTest {

    @Test
    fun `parsing again leaves the previous document intact`() {
        val parser = HTMLParser()

        val first = parser.parse("<!DOCTYPE html><p>first</p>")
        val firstTree = first.toTreeString()
        val firstQuirksMode = first.quirksMode

        val second = parser.parse("<p>second</p>")

        assertNotSame(first, second)
        assertEquals(firstTree, first.toTreeString())
        assertEquals(firstQuirksMode, first.quirksMode)
        assertEquals(QuirksMode.NO_QUIRKS, first.quirksMode)
        assertEquals(QuirksMode.QUIRKS, second.quirksMode)
    }

    @Test
    fun `document property returns the latest parse result`() {
        val parser = HTMLParser()

        parser.parse("<p>first</p>")
        val second = parser.parse("<p>second</p>")

        assertEquals(second, parser.document)
    }
}
