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

import com.theakram.helium.treebuilder.TokenizerStateType
import kotlin.test.Test

internal class DomJs : TokenizerTestBase() {

    @Test
    fun `CR in bogus comment state`() = assertTokenization(
        input = "<?\r",
        expectedTokens = listOf(
            comment(
                "?\u000a"
            )
        )
    )

    @Test
    fun `CRLF in bogus comment state`() = assertTokenization(
        input = "<?\r\n",
        expectedTokens = listOf(
            comment(
                "?\u000a"
            )
        )
    )

    @Test
    fun `CRLFLF in bogus comment state`() = assertTokenization(
        input = "?\u000d\u000a\u000a",
        expectedTokens = listOf(
            comment(
                "?\u000a\u000a")
            )
        )

        @Test
        fun `Raw NUL replacement`() = assertTokenization(
            input = "\\u0000",
            expectedTokens = listOf(
                *chars("\\uFFFD").toTypedArray()
            )
        )

        @Test
        fun `NUL in CDATA section`() = assertTokenization(
            input = "\\u0000]]>",
            expectedTokens = listOf(
                *chars("\\u0000").toTypedArray()
            )
        )

        @Test
        fun `NUL in script HTML comment`() = assertTokenization(
            input = "<!--test\\u0000--><!--test-\\u0000--><!--test--\\u0000-->",
            expectedTokens = listOf(
                *chars("<!--test\\uFFFD--><!--test-\\uFFFD--><!--test--\\uFFFD-->").toTypedArray()
            )
        )

        @Test
        fun `NUL in script HTML comment double escaped`() = assertTokenization(
            input = "<!--<script>\\u0000--><!--<script>-\\u0000--><!--<script>--\\u0000-->",
            expectedTokens = listOf(
                *chars("<!--<script>\\uFFFD--><!--<script>-\\uFFFD--><!--<script>--\\uFFFD-->").toTypedArray()
            )
        )

        @Test
        fun `EOF in script HTML comment`() = assertTokenization(
            input = "<!--test",
            expectedTokens = listOf(
                *chars("<!--test").toTypedArray()
            )
        )

        @Test
        fun `EOF in script HTML comment after dash`() = assertTokenization(
            input = "<!--test-",
            expectedTokens = listOf(
                *chars("<!--test-").toTypedArray()
            )
        )

        @Test
        fun `EOF in script HTML comment after dash dash`() = assertTokenization(
            input = "<!--test--",
            expectedTokens = listOf(
                *chars("<!--test--").toTypedArray()
            )
        )

        @Test
        fun `EOF in script HTML comment double escaped after dash`() = assertTokenization(
            input = "<!--<script>-",
            expectedTokens = listOf(
                *chars("<!--<script>-").toTypedArray()
            )
        )

        @Test
        fun `EOF in script HTML comment double escaped after dash dash`() = assertTokenization(
            input = "<!--<script>--",
            expectedTokens = listOf(
                *chars("<!--<script>--").toTypedArray()
            )
        )

        @Test
        fun `EOF in script HTML comment double escaped`() = assertTokenization(
            input = "<!--<script>",
            expectedTokens = listOf(
                *chars("<!--<script>").toTypedArray()
            )
        )

        @Test
        fun `Dash in script HTML comment`() = assertTokenization(
            input = "<!-- - -->",
            expectedTokens = listOf(
                *chars("<!-- - -->").toTypedArray()
            )
        )

        @Test
        fun `Dash lessthan in script HTML comment`() = assertTokenization(
            input = "<!-- -< -->",
            expectedTokens = listOf(
                *chars("<!-- -< -->").toTypedArray()
            )
        )

        @Test
        fun `Dash at end of script HTML comment`() = assertTokenization(
            input = "<!--test--->",
            expectedTokens = listOf(
                *chars("<!--test--->").toTypedArray()
            )
        )

        @Test
        fun `script in script HTML comment`() = assertTokenization(
            input = "<!-- </script> --></script>",
            expectedTokens = listOf(
                *chars("<!-- ").toTypedArray(),
                endTag("script"),
                *chars(" -->").toTypedArray(),
                endTag("script")
            )
        )

        @Test
        fun `script in script HTML comment double escaped`() = assertTokenization(
            input = "<!-- <script></script> --></script>",
            expectedTokens = listOf(
                *chars("<!-- <script></script> -->").toTypedArray(),
                endTag("script")
            )
        )

        @Test
        fun `script in script HTML comment double escaped with nested script`() = assertTokenization(
            input = "<!-- <script><script></script></script> --></script>",
            expectedTokens = listOf(
                *chars("<!-- <script><script></script>").toTypedArray(),
                endTag("script"),
                *chars(" -->").toTypedArray(),
                endTag("script")
            )
        )

        @Test
        fun `script in script HTML comment double escaped with abrupt end`() = assertTokenization(
            input = "<!-- <script>--></script> --></script>",
            expectedTokens = listOf(
                *chars("<!-- <script>-->").toTypedArray(),
                endTag("script"),
                *chars(" -->").toTypedArray(),
                endTag("script")
            )
        )

        @Test
        fun `Incomplete start tag in script HTML comment double escaped`() = assertTokenization(
            input = "<!--<scrip></script>-->",
            expectedTokens = listOf(
                *chars("<!--<scrip>").toTypedArray(),
                endTag("script"),
                *chars("-->").toTypedArray()
            )
        )

        @Test
        fun `Unclosed start tag in script HTML comment double escaped`() = assertTokenization(
            input = "<!--<script</script>-->",
            expectedTokens = listOf(
                *chars("<!--<script").toTypedArray(),
                endTag("script"),
                *chars("-->").toTypedArray()
            )
        )

        @Test
        fun `Incomplete end tag in script HTML comment double escaped`() = assertTokenization(
            input = "<!--<script></scrip>-->",
            expectedTokens = listOf(
                *chars("<!--<script></scrip>-->").toTypedArray()
            )
        )

        @Test
        fun `Unclosed end tag in script HTML comment double escaped`() = assertTokenization(
            input = "<!--<script></script-->",
            expectedTokens = listOf(
                *chars("<!--<script></script-->").toTypedArray()
            )
        )

        @Test
        fun `leading UFEFF must pass through`() = assertTokenization(
            input = "\\uFEFFfoo\\uFEFFbar",
            expectedTokens = listOf(
                *chars("\\uFEFFfoo\\uFEFFbar").toTypedArray()
            )
        )

        @Test
        fun `Non BMPcharref in RCDATA`() = assertTokenization(
            input = "&NotEqualTilde;",
            expectedTokens = listOf(
                *chars("≂̸").toTypedArray()
            )
        )

        @Test
        fun `Bad charref in RCDATA`() = assertTokenization(
            input = "&NotEqualTild;",
            expectedTokens = listOf(
                *chars("&NotEqualTild;").toTypedArray()
            )
        )

        @Test
        fun `lowercase endtags`() = assertTokenization(
            input = "</XMP>",
            expectedTokens = listOf(
                endTag("xmp")
            ),
            initialState = TokenizerStateType.RAWTEXT,
            lastStartTag = "xmp"
        )

        @Test
        fun `bad endtag space before name`() = assertTokenization(
            input = "</ XMP>",
            expectedTokens = listOf(
                *chars("</ XMP>").toTypedArray()
            ),
            initialState = TokenizerStateType.RAWTEXT,
            lastStartTag = "xmp"
        )

        @Test
        fun `bad endtag not matching last start tag`() = assertTokenization(
            input = "</xm>",
            expectedTokens = listOf(
                *chars("</xm>").toTypedArray()
            ),
            initialState = TokenizerStateType.RAWTEXT,
            lastStartTag = "xmp"
        )

        @Test
        fun `bad endtag without close bracket`() = assertTokenization(
            input = "</xm ",
            expectedTokens = listOf(
                *chars("</xm ").toTypedArray()
            ),
            initialState = TokenizerStateType.RAWTEXT,
            lastStartTag = "xmp"
        )

        @Test
        fun `bad endtag trailing solidus`() = assertTokenization(
            input = "</xm/",
            expectedTokens = listOf(
                *chars("</xm/").toTypedArray()
            ),
            initialState = TokenizerStateType.RAWTEXT,
            lastStartTag = "xmp"
        )

        @Test
        fun `Non BMPcharref in attribute`() = assertTokenization(
            input = "<p id=\"&NotEqualTilde;\">",
            expectedTokens = listOf(
                startTag(name = "p", attributes = mapOf("id" to "≂̸"))
            )
        )

        @Test
        fun `NUL in comment`() = assertTokenization(
            input = "<!----!\\u0000-->",
            expectedTokens = listOf(
                comment("--!\uFFFD")
            )
        )

        @Test
        fun `space EOF after doctype`() = assertTokenization(
            input = "<!DOCTYPE html ",
            expectedTokens = listOf(

            )
        )

        @Test
        fun `CDATA in HTML content`() = assertTokenization(
            input = "<![CDATA[foo]]>",
            expectedTokens = listOf(
                comment("[CDATA[foo]]")
            )
        )

        @Test
        fun `CDATA content`() = assertTokenization(
            input = "foo&#32;]]>",
            expectedTokens = listOf(
                *chars("foo&#32;").toTypedArray()
            )
        )

        @Test
        fun `CDATA followed by HTML content`() = assertTokenization(
            input = "foo&#32;]]>&#32;",
            expectedTokens = listOf(
                *chars("foo&#32; ").toTypedArray()
            )
        )

        @Test
        fun `CDATA with extra bracket`() = assertTokenization(
            input = "foo]]]>",
            expectedTokens = listOf(
                *chars("foo]").toTypedArray()
            )
        )

        @Test
        fun `CDATA without end marker`() = assertTokenization(
            input = "foo",
            expectedTokens = listOf(
                *chars("foo").toTypedArray()
            )
        )

        @Test
        fun `CDATA with single bracket ending`() = assertTokenization(
            input = "foo]",
            expectedTokens = listOf(
                *chars("foo]").toTypedArray()
            )
        )

        @Test
        fun `CDATA with two brackets ending`() = assertTokenization(
            input = "foo]]",
            expectedTokens = listOf(
                *chars("foo]]").toTypedArray()
            )
        )

        @Test
        fun `HTML tag in script data`() = assertTokenization(
            input = "<b>hello world</b>",
            expectedTokens = listOf(
                *chars("<b>hello world</b>").toTypedArray()
            )
        )
}
