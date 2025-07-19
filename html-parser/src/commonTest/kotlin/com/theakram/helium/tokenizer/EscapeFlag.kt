/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
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

internal class EscapeFlag : TokenizerTestBase() {

    @Test
    fun `Commented close tag in RCDATA or RAWTEXT`() = assertTokenization(
        input = "foo<!--</xmp>--></xmp>",
        expectedTokens = listOf(
            *chars("foo<!--").toTypedArray(),
            endTag("xmp"),
            *chars("-->").toTypedArray(),
            endTag("xmp")
        )
    )

    @Test
    fun `Bogus comment in RCDATA or RAWTEXT`() = assertTokenization(
        input = "foo<!-->baz</xmp>",
        expectedTokens = listOf(
            *chars("foo<!-->baz").toTypedArray(),
            endTag("xmp")
        )
    )

    @Test
    fun `End tag surrounded by bogus comment in RCDATA or RAWTEXT`() = assertTokenization(
        input = "foo<!--></xmp><!-->baz</xmp>",
        expectedTokens = listOf(
            *chars("foo<!-->").toTypedArray(),
            endTag("xmp"),
            comment(""),
            *chars("baz").toTypedArray(),
            endTag("xmp")
        )
    )

    @Test
    fun `Commented entities in RCDATA`() = assertTokenization(
        input = " &amp; <!-- &amp; --> &amp; </xmp>",
        expectedTokens = listOf(
            *chars(" & <!-- & --> & ").toTypedArray(),
            endTag("xmp")
        )
    )

    @Test
    fun `Incorrect comment ending sequences in RCDATA or RAWTEXT`() = assertTokenization(
        input = "foo<!-- x --x>x-- >x--!>x--<></xmp>",
        expectedTokens = listOf(
            *chars("foo<!-- x --x>x-- >x--!>x--<>").toTypedArray(),
            endTag("xmp")
        )
    )
}
