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

internal class ContentModelFlags : TokenizerTestBase() {

    @Test
    fun `PLAINTEXT content model flag`() = assertTokenization(
        input = "<head>&body;",
        expectedTokens = listOf(
            *chars("<head>&body;").toTypedArray()
        )
    )

    @Test
    fun `PLAINTEXT with seeming close tag`() = assertTokenization(
        input = "</plaintext>&body;",
        expectedTokens = listOf(
            *chars("</plaintext>&body;").toTypedArray()
        )
    )

    @Test
    fun `End tag closing RCDATA or RAWTEXT`() = assertTokenization(
        input = "foo</xmp>",
        expectedTokens = listOf(
            *chars("foo").toTypedArray(),
            endTag("xmp")
        )
    )

    @Test
    fun `End tag closing RCDATA or RAWTEXT caseinsensitivity`() = assertTokenization(
        input = "foo</xMp>",
        expectedTokens = listOf(
            *chars("foo").toTypedArray(),
            endTag("xmp")
        )
    )

    @Test
    fun `End tag closing RCDATA or RAWTEXT ending with space`() = assertTokenization(
        input = "foo</xmp ",
        expectedTokens = listOf(
            *chars("foo").toTypedArray()
        )
    )

    @Test
    fun `End tag closing RCDATA or RAWTEXT ending with EOF`() = assertTokenization(
        input = "foo</xmp",
        expectedTokens = listOf(
            *chars("foo</xmp").toTypedArray()
        )
    )

    @Test
    fun `End tag closing RCDATA or RAWTEXT ending with slash`() = assertTokenization(
        input = "foo</xmp/",
        expectedTokens = listOf(
            *chars("foo").toTypedArray()
        )
    )

    @Test
    fun `End tag not closing RCDATA or RAWTEXT ending with leftanglebracket`() = assertTokenization(
        input = "foo</xmp<",
        expectedTokens = listOf(
            *chars("foo</xmp<").toTypedArray()
        )
    )

    @Test
    fun `End tag with incorrect name in RCDATA or RAWTEXT`() = assertTokenization(
        input = "</foo>bar</xmp>",
        expectedTokens = listOf(
            *chars("</foo>bar").toTypedArray(),
            endTag("xmp")
        )
    )

    @Test
    fun `Partial end tags leading straight into partial end tags`() = assertTokenization(
        input = "</xmp</xmp</xmp>",
        expectedTokens = listOf(
            *chars("</xmp</xmp").toTypedArray(),
            endTag("xmp")
        )
    )

    @Test
    fun `End tag with incorrect name in RCDATA or RAWTEXT starting like correct name`() = assertTokenization(
        input = "</foo>bar</xmpaar>",
        expectedTokens = listOf(
            *chars("</foo>bar</xmpaar>").toTypedArray()
        )
    )

    @Test
    fun `End tag closing RCDATA or RAWTEXT switching back to PCDATA`() = assertTokenization(
        input = "foo</xmp></baz>",
        expectedTokens = listOf(
            *chars("foo").toTypedArray(),
            endTag("xmp"),
            endTag("baz")
        )
    )

    @Test
    fun `RAWTEXT w something looking like an entity`() = assertTokenization(
        input = "&foo;",
        expectedTokens = listOf(
            *chars("&foo;").toTypedArray()
        )
    )

    @Test
    fun `RCDATA w an entity`() = assertTokenization(
        input = "&lt;",
        expectedTokens = listOf(
            char('<')
        )
    )
}
