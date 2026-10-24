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

import com.theakram.helium.tokenization.states.CDATASection
import com.theakram.helium.treebuilder.TokenizerStateType
import kotlin.test.Ignore
import kotlin.test.Test

internal class DomJs : TokenizerTestBase() {

    @Test
    fun `CR in bogus comment state 0`() {
        assertTokenization(
            input = "<?\r",
            expectedTokens = listOf(
                comment("?\n")
            )
        )
    }

    @Test
    fun `CRLF in bogus comment state 1`() {
        assertTokenization(
            input = "<?\r\n",
            expectedTokens = listOf(
                comment("?\n")
            )
        )
    }

    @Test
    fun `CRLFLF in bogus comment state 2`() {
        assertTokenization(
            input = "<?\r\n\n",
            expectedTokens = listOf(
                comment("?\n\n")
            )
        )
    }

    @Test
    fun `Raw NUL replacement RCDATA 3`() {
        assertTokenization(
            input = "\u0000",
            expectedTokens = listOf(
                char('\uFFFD')
            )
            ,initialState = TokenizerStateType.RCDATA
        )
    }

    @Test
    fun `Raw NUL replacement RAWTEXT 3`() {
        assertTokenization(
            input = "\u0000",
            expectedTokens = listOf(
                char('\uFFFD')
            )
            ,initialState = TokenizerStateType.RAWTEXT
        )
    }

    @Test
    fun `Raw NUL replacement PLAINTEXT 3`() {
        assertTokenization(
            input = "\u0000",
            expectedTokens = listOf(
                char('\uFFFD')
            )
            ,initialState = TokenizerStateType.PLAINTEXT
        )
    }

    @Test
    fun `Raw NUL replacement Scriptdata 3`() {
        assertTokenization(
            input = "\u0000",
            expectedTokens = listOf(
                char('\uFFFD')
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }

    @Test
    fun `NUL in CDATA section CDATAsection 4`() {
        val actualTokens = tokenizeWithDirectState(
            input = "\u0000]]>",
            initialState = CDATASection
        )
        val expectedTokens = listOf(
            char('\u0000')
        )
        if (actualTokens.size != expectedTokens.size) {
            throw AssertionError(
                "Token count mismatch. Expected ${expectedTokens.size}, but got ${actualTokens.size}\nExpected: $expectedTokens\nActual: $actualTokens"
            )
        }
        expectedTokens.forEachIndexed { i, token ->
            if (token != actualTokens[i]) {
                throw AssertionError("Token mismatch at index $i.\nExpected: $token\nActual: ${actualTokens[i]}")
            }
        }
    }

    @Test
    fun `NUL in script HTML comment Scriptdata 5`() {
        assertTokenization(
            input = "<!--test\u0000--><!--test-\u0000--><!--test--\u0000-->",
            expectedTokens = listOf(
                *chars("<!--test\uFFFD--><!--test-\uFFFD--><!--test--\uFFFD-->").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }

    @Test
    fun `NUL in script HTML comment double escaped Scriptdata 6`() {
        assertTokenization(
            input = "<!--<script>\u0000--><!--<script>-\u0000--><!--<script>--\u0000-->",
            expectedTokens = listOf(
                *chars("<!--<script>\uFFFD--><!--<script>-\uFFFD--><!--<script>--\uFFFD-->").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }

    @Test
    fun `EOF in script HTML comment Scriptdata 7`() {
        assertTokenization(
            input = "<!--test",
            expectedTokens = listOf(
                *chars("<!--test").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }

    @Test
    fun `EOF in script HTML comment after dash Scriptdata 8`() {
        assertTokenization(
            input = "<!--test-",
            expectedTokens = listOf(
                *chars("<!--test-").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }

    @Test
    fun `EOF in script HTML comment after dash dash Scriptdata 9`() {
        assertTokenization(
            input = "<!--test--",
            expectedTokens = listOf(
                *chars("<!--test--").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }

    @Test
    fun `EOF in script HTML comment double escaped after dash Scriptdata 10`() {
        assertTokenization(
            input = "<!--<script>-",
            expectedTokens = listOf(
                *chars("<!--<script>-").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }

    @Test
    fun `EOF in script HTML comment double escaped after dash dash Scriptdata 11`() {
        assertTokenization(
            input = "<!--<script>--",
            expectedTokens = listOf(
                *chars("<!--<script>--").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }

    @Test
    fun `EOF in script HTML comment double escaped Scriptdata 12`() {
        assertTokenization(
            input = "<!--<script>",
            expectedTokens = listOf(
                *chars("<!--<script>").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }

    @Test
    fun `Dash in script HTML comment Scriptdata 13`() {
        assertTokenization(
            input = "<!-- - -->",
            expectedTokens = listOf(
                *chars("<!-- - -->").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }

    @Test
    fun `Dash lessthan in script HTML comment Scriptdata 14`() {
        assertTokenization(
            input = "<!-- -< -->",
            expectedTokens = listOf(
                *chars("<!-- -< -->").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }

    @Test
    fun `Dash at end of script HTML comment Scriptdata 15`() {
        assertTokenization(
            input = "<!--test--->",
            expectedTokens = listOf(
                *chars("<!--test--->").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }

    @Test
    fun `script in script HTML comment Scriptdata 16`() {
        assertTokenization(
            input = "<!-- </script> --></script>",
            expectedTokens = listOf(
                *chars("<!-- ").toTypedArray(),
            endTag("script"),
            *chars(" -->").toTypedArray(),
            endTag("script")
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
            ,lastStartTag = "script"
        )
    }

    // Needs the script data double escaped states, which the tokenizer does not implement yet
    @Ignore
    @Test
    fun `script in script HTML comment double escaped Scriptdata 17`() {
        assertTokenization(
            input = "<!-- <script></script> --></script>",
            expectedTokens = listOf(
                *chars("<!-- <script></script> -->").toTypedArray(),
            endTag("script")
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
            ,lastStartTag = "script"
        )
    }

    // Needs the script data double escaped states, which the tokenizer does not implement yet
    @Ignore
    @Test
    fun `script in script HTML comment double escaped with nested script Scriptdata 18`() {
        assertTokenization(
            input = "<!-- <script><script></script></script> --></script>",
            expectedTokens = listOf(
                *chars("<!-- <script><script></script>").toTypedArray(),
            endTag("script"),
            *chars(" -->").toTypedArray(),
            endTag("script")
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
            ,lastStartTag = "script"
        )
    }

    @Test
    fun `script in script HTML comment double escaped with abrupt end Scriptdata 19`() {
        assertTokenization(
            input = "<!-- <script>--></script> --></script>",
            expectedTokens = listOf(
                *chars("<!-- <script>-->").toTypedArray(),
            endTag("script"),
            *chars(" -->").toTypedArray(),
            endTag("script")
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
            ,lastStartTag = "script"
        )
    }

    @Test
    fun `Incomplete start tag in script HTML comment double escaped Scriptdata 20`() {
        assertTokenization(
            input = "<!--<scrip></script>-->",
            expectedTokens = listOf(
                *chars("<!--<scrip>").toTypedArray(),
            endTag("script"),
            *chars("-->").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
            ,lastStartTag = "script"
        )
    }

    @Test
    fun `Unclosed start tag in script HTML comment double escaped Scriptdata 21`() {
        assertTokenization(
            input = "<!--<script</script>-->",
            expectedTokens = listOf(
                *chars("<!--<script").toTypedArray(),
            endTag("script"),
            *chars("-->").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
            ,lastStartTag = "script"
        )
    }

    @Test
    fun `Incomplete end tag in script HTML comment double escaped Scriptdata 22`() {
        assertTokenization(
            input = "<!--<script></scrip>-->",
            expectedTokens = listOf(
                *chars("<!--<script></scrip>-->").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
            ,lastStartTag = "script"
        )
    }

    @Test
    fun `Unclosed end tag in script HTML comment double escaped Scriptdata 23`() {
        assertTokenization(
            input = "<!--<script></script-->",
            expectedTokens = listOf(
                *chars("<!--<script></script-->").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
            ,lastStartTag = "script"
        )
    }

    @Test
    fun `leading UFEFF must pass through Data 24`() {
        assertTokenization(
            input = "\uFEFFfoo\uFEFFbar",
            expectedTokens = listOf(
                *chars("\uFEFFfoo\uFEFFbar").toTypedArray()
            )
            ,initialState = TokenizerStateType.DATA
        )
    }

    @Test
    fun `leading UFEFF must pass through RCDATA 24`() {
        assertTokenization(
            input = "\uFEFFfoo\uFEFFbar",
            expectedTokens = listOf(
                *chars("\uFEFFfoo\uFEFFbar").toTypedArray()
            )
            ,initialState = TokenizerStateType.RCDATA
        )
    }

    @Test
    fun `leading UFEFF must pass through RAWTEXT 24`() {
        assertTokenization(
            input = "\uFEFFfoo\uFEFFbar",
            expectedTokens = listOf(
                *chars("\uFEFFfoo\uFEFFbar").toTypedArray()
            )
            ,initialState = TokenizerStateType.RAWTEXT
        )
    }

    @Test
    fun `leading UFEFF must pass through Scriptdata 24`() {
        assertTokenization(
            input = "\uFEFFfoo\uFEFFbar",
            expectedTokens = listOf(
                *chars("\uFEFFfoo\uFEFFbar").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }

    @Test
    fun `Non BMPcharref in RCDATA RCDATA 25`() {
        assertTokenization(
            input = "&NotEqualTilde;",
            expectedTokens = listOf(
                *chars("\u2242\u0338").toTypedArray()
            )
            ,initialState = TokenizerStateType.RCDATA
        )
    }

    @Test
    fun `Bad charref in RCDATA RCDATA 26`() {
        assertTokenization(
            input = "&NotEqualTild;",
            expectedTokens = listOf(
                *chars("&NotEqualTild;").toTypedArray()
            )
            ,initialState = TokenizerStateType.RCDATA
        )
    }

    @Test
    fun `lowercase endtags RCDATA 27`() {
        assertTokenization(
            input = "</XMP>",
            expectedTokens = listOf(
                endTag("xmp")
            )
            ,initialState = TokenizerStateType.RCDATA
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `lowercase endtags RAWTEXT 27`() {
        assertTokenization(
            input = "</XMP>",
            expectedTokens = listOf(
                endTag("xmp")
            )
            ,initialState = TokenizerStateType.RAWTEXT
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `lowercase endtags Scriptdata 27`() {
        assertTokenization(
            input = "</XMP>",
            expectedTokens = listOf(
                endTag("xmp")
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `bad endtag space before name RCDATA 28`() {
        assertTokenization(
            input = "</ XMP>",
            expectedTokens = listOf(
                *chars("</ XMP>").toTypedArray()
            )
            ,initialState = TokenizerStateType.RCDATA
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `bad endtag space before name RAWTEXT 28`() {
        assertTokenization(
            input = "</ XMP>",
            expectedTokens = listOf(
                *chars("</ XMP>").toTypedArray()
            )
            ,initialState = TokenizerStateType.RAWTEXT
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `bad endtag space before name Scriptdata 28`() {
        assertTokenization(
            input = "</ XMP>",
            expectedTokens = listOf(
                *chars("</ XMP>").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `bad endtag not matching last start tag RCDATA 29`() {
        assertTokenization(
            input = "</xm>",
            expectedTokens = listOf(
                *chars("</xm>").toTypedArray()
            )
            ,initialState = TokenizerStateType.RCDATA
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `bad endtag not matching last start tag RAWTEXT 29`() {
        assertTokenization(
            input = "</xm>",
            expectedTokens = listOf(
                *chars("</xm>").toTypedArray()
            )
            ,initialState = TokenizerStateType.RAWTEXT
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `bad endtag not matching last start tag Scriptdata 29`() {
        assertTokenization(
            input = "</xm>",
            expectedTokens = listOf(
                *chars("</xm>").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `bad endtag without close bracket RCDATA 30`() {
        assertTokenization(
            input = "</xm ",
            expectedTokens = listOf(
                *chars("</xm ").toTypedArray()
            )
            ,initialState = TokenizerStateType.RCDATA
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `bad endtag without close bracket RAWTEXT 30`() {
        assertTokenization(
            input = "</xm ",
            expectedTokens = listOf(
                *chars("</xm ").toTypedArray()
            )
            ,initialState = TokenizerStateType.RAWTEXT
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `bad endtag without close bracket Scriptdata 30`() {
        assertTokenization(
            input = "</xm ",
            expectedTokens = listOf(
                *chars("</xm ").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `bad endtag trailing solidus RCDATA 31`() {
        assertTokenization(
            input = "</xm/",
            expectedTokens = listOf(
                *chars("</xm/").toTypedArray()
            )
            ,initialState = TokenizerStateType.RCDATA
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `bad endtag trailing solidus RAWTEXT 31`() {
        assertTokenization(
            input = "</xm/",
            expectedTokens = listOf(
                *chars("</xm/").toTypedArray()
            )
            ,initialState = TokenizerStateType.RAWTEXT
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `bad endtag trailing solidus Scriptdata 31`() {
        assertTokenization(
            input = "</xm/",
            expectedTokens = listOf(
                *chars("</xm/").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
            ,lastStartTag = "xmp"
        )
    }

    @Test
    fun `Non BMPcharref in attribute 32`() {
        assertTokenization(
            input = "<p id=\"&NotEqualTilde;\">",
            expectedTokens = listOf(
                startTag(name = "p", attributes = mapOf("id" to "\u2242\u0338"))
            )
        )
    }

    @Test
    fun `NUL in comment 33`() {
        assertTokenization(
            input = "<!----!\u0000-->",
            expectedTokens = listOf(
                comment("--!\uFFFD")
            )
        )
    }

    @Test
    fun `space EOF after doctype 34`() {
        assertTokenization(
            input = "<!DOCTYPE html ",
            expectedTokens = listOf(
                doctype(name = "html", forceQuirks = true)
            )
        )
    }

    @Test
    fun `CDATA in HTML content 35`() {
        assertTokenization(
            input = "<![CDATA[foo]]>",
            expectedTokens = listOf(
                comment("[CDATA[foo]]")
            )
        )
    }

    @Test
    fun `CDATA content CDATAsection 36`() {
        val actualTokens = tokenizeWithDirectState(
            input = "foo&#32;]]>",
            initialState = CDATASection
        )
        val expectedTokens = listOf(
            *chars("foo&#32;").toTypedArray()
        )
        if (actualTokens.size != expectedTokens.size) {
            throw AssertionError(
                "Token count mismatch. Expected ${expectedTokens.size}, but got ${actualTokens.size}\nExpected: $expectedTokens\nActual: $actualTokens"
            )
        }
        expectedTokens.forEachIndexed { i, token ->
            if (token != actualTokens[i]) {
                throw AssertionError("Token mismatch at index $i.\nExpected: $token\nActual: ${actualTokens[i]}")
            }
        }
    }

    @Test
    fun `CDATA followed by HTML content CDATAsection 37`() {
        val actualTokens = tokenizeWithDirectState(
            input = "foo&#32;]]>&#32;",
            initialState = CDATASection
        )
        val expectedTokens = listOf(
            *chars("foo&#32; ").toTypedArray()
        )
        if (actualTokens.size != expectedTokens.size) {
            throw AssertionError(
                "Token count mismatch. Expected ${expectedTokens.size}, but got ${actualTokens.size}\nExpected: $expectedTokens\nActual: $actualTokens"
            )
        }
        expectedTokens.forEachIndexed { i, token ->
            if (token != actualTokens[i]) {
                throw AssertionError("Token mismatch at index $i.\nExpected: $token\nActual: ${actualTokens[i]}")
            }
        }
    }

    @Test
    fun `CDATA with extra bracket CDATAsection 38`() {
        val actualTokens = tokenizeWithDirectState(
            input = "foo]]]>",
            initialState = CDATASection
        )
        val expectedTokens = listOf(
            *chars("foo]").toTypedArray()
        )
        if (actualTokens.size != expectedTokens.size) {
            throw AssertionError(
                "Token count mismatch. Expected ${expectedTokens.size}, but got ${actualTokens.size}\nExpected: $expectedTokens\nActual: $actualTokens"
            )
        }
        expectedTokens.forEachIndexed { i, token ->
            if (token != actualTokens[i]) {
                throw AssertionError("Token mismatch at index $i.\nExpected: $token\nActual: ${actualTokens[i]}")
            }
        }
    }

    @Test
    fun `CDATA without end marker CDATAsection 39`() {
        val actualTokens = tokenizeWithDirectState(
            input = "foo",
            initialState = CDATASection
        )
        val expectedTokens = listOf(
            *chars("foo").toTypedArray()
        )
        if (actualTokens.size != expectedTokens.size) {
            throw AssertionError(
                "Token count mismatch. Expected ${expectedTokens.size}, but got ${actualTokens.size}\nExpected: $expectedTokens\nActual: $actualTokens"
            )
        }
        expectedTokens.forEachIndexed { i, token ->
            if (token != actualTokens[i]) {
                throw AssertionError("Token mismatch at index $i.\nExpected: $token\nActual: ${actualTokens[i]}")
            }
        }
    }

    @Test
    fun `CDATA with single bracket ending CDATAsection 40`() {
        val actualTokens = tokenizeWithDirectState(
            input = "foo]",
            initialState = CDATASection
        )
        val expectedTokens = listOf(
            *chars("foo]").toTypedArray()
        )
        if (actualTokens.size != expectedTokens.size) {
            throw AssertionError(
                "Token count mismatch. Expected ${expectedTokens.size}, but got ${actualTokens.size}\nExpected: $expectedTokens\nActual: $actualTokens"
            )
        }
        expectedTokens.forEachIndexed { i, token ->
            if (token != actualTokens[i]) {
                throw AssertionError("Token mismatch at index $i.\nExpected: $token\nActual: ${actualTokens[i]}")
            }
        }
    }

    @Test
    fun `CDATA with two brackets ending CDATAsection 41`() {
        val actualTokens = tokenizeWithDirectState(
            input = "foo]]",
            initialState = CDATASection
        )
        val expectedTokens = listOf(
            *chars("foo]]").toTypedArray()
        )
        if (actualTokens.size != expectedTokens.size) {
            throw AssertionError(
                "Token count mismatch. Expected ${expectedTokens.size}, but got ${actualTokens.size}\nExpected: $expectedTokens\nActual: $actualTokens"
            )
        }
        expectedTokens.forEachIndexed { i, token ->
            if (token != actualTokens[i]) {
                throw AssertionError("Token mismatch at index $i.\nExpected: $token\nActual: ${actualTokens[i]}")
            }
        }
    }

    @Test
    fun `HTML tag in script data Scriptdata 42`() {
        assertTokenization(
            input = "<b>hello world</b>",
            expectedTokens = listOf(
                *chars("<b>hello world</b>").toTypedArray()
            )
            ,initialState = TokenizerStateType.SCRIPT_DATA
        )
    }
}
