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

import com.theakram.helium.tokenization.Token
import kotlin.test.Test

/**
 * Contains all 46 tests from here:
 * https://github.com/html5lib/html5lib-tests/blob/master/tokenizer/test2.test
 *
 * The commented (17) test cases are for Tokenization states that haven't been implemented yet.
 */
internal class TokenizerTest2 : TokenizerTestBase() {

    @Test
    fun `DOCTYPE without name`() = assertTokenization(
        input = "<!DOCTYPE>",
        expectedTokens = listOf(
            Token.DocType(name = null, publicId = null, systemId = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE without space before name`() = assertTokenization(
        input = "<!DOCTYPEhtml>",
        expectedTokens = listOf(
            Token.DocType(name = "html", publicId = null, systemId = null, forceQuirks = false)
        )
    )

    @Test
    fun `Incorrect DOCTYPE without a space before name`() = assertTokenization(
        input = "<!DOCTYPEfoo>",
        expectedTokens = listOf(
            Token.DocType(name = "foo", publicId = null, systemId = null, forceQuirks = false)
        )
    )

    @Test
    fun `DOCTYPE with publicId`() = assertTokenization(
        input = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML Transitional 4.01//EN\">",
        expectedTokens = listOf(
            Token.DocType(
                name = "html",
                publicId = "-//W3C//DTD HTML Transitional 4.01//EN",
                systemId = null,
                forceQuirks = false
            )
        )
    )

    @Test
    fun `DOCTYPE with EOF after PUBLIC`() = assertTokenization(
        input = "<!DOCTYPE html PUBLIC",
        expectedTokens = listOf(
            Token.DocType(name = "html", publicId = null, systemId = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE with EOF after PUBLIC '`() = assertTokenization(
        input = "<!DOCTYPE html PUBLIC '",
        expectedTokens = listOf(
            Token.DocType(name = "html", publicId = "", systemId = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE with EOF after PUBLIC 'x`() = assertTokenization(
        input = "<!DOCTYPE html PUBLIC 'x",
        expectedTokens = listOf(
            Token.DocType(name = "html", publicId = "x", systemId = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE with systemId`() = assertTokenization(
        input = "<!DOCTYPE html SYSTEM \"-//W3C//DTD HTML Transitional 4.01//EN\">",
        expectedTokens = listOf(
            Token.DocType(
                name = "html",
                publicId = null,
                systemId = "-//W3C//DTD HTML Transitional 4.01//EN",
                forceQuirks = false
            )
        )
    )

    @Test
    fun `DOCTYPE with single-quoted systemId`() = assertTokenization(
        input = "<!DOCTYPE html SYSTEM '-//W3C//DTD HTML Transitional 4.01//EN'>",
        expectedTokens = listOf(
            Token.DocType(
                name = "html",
                publicId = null,
                systemId = "-//W3C//DTD HTML Transitional 4.01//EN",
                forceQuirks = false
            )
        )
    )

    @Test
    fun `DOCTYPE with publicId and systemId`() = assertTokenization(
        input = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML Transitional 4.01//EN\" \"-//W3C//DTD HTML Transitional 4.01//EN\">",
        expectedTokens = listOf(
            Token.DocType(
                name = "html",
                publicId = "-//W3C//DTD HTML Transitional 4.01//EN",
                systemId = "-//W3C//DTD HTML Transitional 4.01//EN",
                forceQuirks = false
            )
        )
    )

    @Test
    fun `DOCTYPE with greater than in double-quoted publicId`() = assertTokenization(
        input = "<!DOCTYPE html PUBLIC \">x",
        expectedTokens = listOf(
            Token.DocType(name = "html", publicId = "", systemId = null, forceQuirks = true),
            char('x')
        )
    )

    @Test
    fun `DOCTYPE with greater than in single-quoted publicId`() = assertTokenization(
        input = "<!DOCTYPE html PUBLIC '>x",
        expectedTokens = listOf(
            Token.DocType(name = "html", publicId = "", systemId = null, forceQuirks = true),
            char('x')
        )
    )

    @Test
    fun `DOCTYPE with greater than in double-quoted systemId`() = assertTokenization(
        input = "<!DOCTYPE html PUBLIC \"foo\" \">x",
        expectedTokens = listOf(
            Token.DocType(name = "html", publicId = "foo", systemId = "", forceQuirks = true),
            char('x')
        )
    )

    @Test
    fun `DOCTYPE with greater than in single-quoted systemId`() = assertTokenization(
        input = "<!DOCTYPE html PUBLIC 'foo' '>x",
        expectedTokens = listOf(
            Token.DocType(name = "html", publicId = "foo", systemId = "", forceQuirks = true),
            char('x')
        )
    )

    @Test
    fun `Incomplete doctype`() = assertTokenization(
        input = "<!DOCTYPE html ",
        expectedTokens = listOf(
            Token.DocType(name = "html", publicId = null, systemId = null, forceQuirks = true)
        )
    )

    @Test
    fun `Numeric entity representing the NUL character`() = assertTokenization(
        input = "&#0000;",
        expectedTokens = listOf(char('\uFFFD'))
    )

    @Test
    fun `Hexadecimal entity representing the NUL character`() = assertTokenization(
        input = "&#x0000;",
        expectedTokens = listOf(char('\uFFFD'))
    )

    @Test
    fun `Numeric entity representing a codepoint after 1114111 U+10FFFF`() = assertTokenization(
        input = "&#2225222;",
        expectedTokens = listOf(char('\uFFFD'))
    )

    @Test
    fun `Hexadecimal entity representing a codepoint after 1114111 U+10FFFF`() = assertTokenization(
        input = "&#x1010FFFF;",
        expectedTokens = listOf(char('\uFFFD'))
    )

    @Test
    fun `Hexadecimal entity pair representing a surrogate pair`() = assertTokenization(
        input = "&#xD869;&#xDED6;",
        expectedTokens = listOf(
            char('\uFFFD'),
            char('\uFFFD')
        )
    )

    @Test
    fun `Hexadecimal entity with mixed uppercase and lowercase`() = assertTokenization(
        input = "&#xaBcD;",
        expectedTokens = listOf(char('\uABCD'))
    )

    @Test
    fun `Entity without a name`() = assertTokenization(
        input = "&;",
        expectedTokens = chars("&;")
    )

    @Test
    fun `Unescaped ampersand in attribute value`() = assertTokenization(
        input = "<h a='&'>",
        expectedTokens = listOf(
            startTag(
                name = "h",
                attributes = mapOf("a" to "&")
            )
        )
    )

    @Test
    fun `StartTag containing less than`() = assertTokenization(
        input = "<a<b>",
        expectedTokens = listOf(startTag("a<b"))
    )

    @Test
    fun `Non-void element containing trailing -`() = assertTokenization(
        input = "<h/>",
        expectedTokens = listOf(
            startTag(
                name = "h",
                selfClosing = true
            )
        )
    )

    @Test
    fun `Void element with permitted slash`() = assertTokenization(
        input = "<br/>",
        expectedTokens = listOf(
            startTag(
                name = "br",
                selfClosing = true
            )
        )
    )

    @Test
    fun `Void element with permitted slash with attribute`() = assertTokenization(
        input = "<br foo='bar'/>",
        expectedTokens = listOf(
            startTag(
                name = "br",
                selfClosing = true,
                attributes = mapOf("foo" to "bar")
            )
        )
    )

    @Test
    fun `StartTag containing -`() = assertTokenization(
        input = "<h/a='b'>",
        expectedTokens = listOf(
            startTag(
                name = "h",
                attributes = mapOf("a" to "b")
            )
        )
    )

    @Test
    fun `Double-quoted attribute value`() = assertTokenization(
        input = "<h a=\"b\">",
        expectedTokens = listOf(
            startTag(
                name = "h",
                attributes = mapOf("a" to "b")
            )
        )
    )

    @Test
    fun `Unescaped less than-`() = assertTokenization(
        input = "</",
        expectedTokens = chars("</")
    )

    @Test
    fun `Illegal end tag name`() = assertTokenization(
        input = "</1>",
        expectedTokens = listOf(comment("1"))
    )

    @Test
    fun `Processing instruction`() = assertTokenization(
        input = "<?namespace>",
        expectedTokens = listOf(processingInstruction(target = "namespace", data = ""))
    )

    @Test
    fun `A processing instruction delimited by question mark greater than does not contain the delimiting question mark`() =
        assertTokenization(
            input = "<?foo bar?>",
            expectedTokens = listOf(processingInstruction(target = "foo", data = "bar"))
        )

    @Test
    fun `A processing instruction data delimited by question mark greater than contains the correct amount of question marks`() =
        assertTokenization(
            input = "<?foo bar??>",
            expectedTokens = listOf(processingInstruction(target = "foo", data = "bar?"))
        )

    @Test
    fun `Unescaped less than`() = assertTokenization(
        input = "foo < bar",
        expectedTokens = chars("foo < bar")
    )

    @Test
    fun `Null Byte Replacement`() = assertTokenization(
        input = "\u0000",
        expectedTokens = chars("\u0000")
    )

    @Test
    fun `Comment with dash`() = assertTokenization(
        input = "<!---x",
        expectedTokens = listOf(comment("-x"))
    )

    @Test
    fun `Entity + newline`() = assertTokenization(
        input = "\nx\n&gt;\n",
        expectedTokens = chars("\nx\n>\n")
    )

    @Test
    fun `Start tag with no attributes but space before the greater-than sign`() = assertTokenization(
        input = "<h >",
        expectedTokens = listOf(startTag("h"))
    )

    @Test
    fun `Empty attribute followed by uppercase attribute`() = assertTokenization(
        input = "<h a B=''>",
        expectedTokens = listOf(
            startTag(
                name = "h",
                attributes = mapOf("a" to "", "b" to "")
            )
        )
    )

    @Test
    fun `Double-quote after attribute name`() = assertTokenization(
        input = "<h a \">",
        expectedTokens = listOf(
            startTag(
                name = "h",
                attributes = mapOf("a" to "", "\"" to "")
            )
        )
    )

    @Test
    fun `Single-quote after attribute name`() = assertTokenization(
        input = "<h a '>",
        expectedTokens = listOf(
            startTag(
                name = "h",
                attributes = mapOf("a" to "", "'" to "")
            )
        )
    )

    @Test
    fun `Empty end tag with following characters`() = assertTokenization(
        input = "a</>bc",
        expectedTokens = chars("abc")
    )

    @Test
    fun `Empty end tag with following tag`() = assertTokenization(
        input = "a</><b>c",
        expectedTokens = listOf(
            char('a'),
            startTag("b"),
            char('c')
        )
    )

    @Test
    fun `Empty end tag with following comment`() = assertTokenization(
        input = "a</><!--b-->c",
        expectedTokens = listOf(
            *chars("a").toTypedArray(),
            comment("b"),
            *chars("c").toTypedArray()
        )
    )

    @Test
    fun `Empty end tag with following end tag`() = assertTokenization(
        input = "a</></b>c",
        expectedTokens = listOf(
            char('a'),
            endTag("b"),
            char('c')
        )
    )
}