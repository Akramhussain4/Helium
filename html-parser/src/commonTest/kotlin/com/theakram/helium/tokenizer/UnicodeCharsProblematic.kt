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

import kotlin.test.Test

/**
 * Contains all 5 tests from here:
 * https://github.com/html5lib/html5lib-tests/blob/master/tokenizer/unicodeCharsProblematic.test
 */
internal class UnicodeCharsProblematic : TokenizerTestBase() {

    @Test
    fun `Invalid Unicode character U+DFFF`() = assertTokenization(
        input = """\\uDFFF""",
        listOf(
            char('\\'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid Unicode character U+D800`() = assertTokenization(
        input = """\\uD800""",
        listOf(
            char('\\'),
            char('\\'),
            char('u'),
            char('D'),
            char('8'),
            char('0'),
            char('0'),
        ),
    )

    @Test
    fun `Invalid Unicode character U+DFFF with valid preceding character`() = assertTokenization(
        input = """a\\uDFFF""",
        listOf(
            char('a'),
            char('\\'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid Unicode character U+D800 with valid following character`() = assertTokenization(
        input = """\\uD800a""",
        listOf(
            char('\\'),
            char('\\'),
            char('u'),
            char('D'),
            char('8'),
            char('0'),
            char('0'),
            char('a'),
        ),
    )

    @Test
    fun `CR followed by U+0000`() = assertTokenization(
        input = "\r\u0000",
        listOf(
            char('\n'),
            char('\u0000')
        ),
    )
}