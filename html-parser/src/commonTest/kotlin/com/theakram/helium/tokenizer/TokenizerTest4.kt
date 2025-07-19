package com.theakram.helium.tokenizer

import kotlin.test.Test

/**
 * Contains all 85 tests from here:
 * https://github.com/html5lib/html5lib-tests/blob/master/tokenizer/test4.test
 *
 * The commented (15) test cases are for Tokenization states that haven't been implemented yet.
 */
internal class TokenizerTest4 : TokenizerTestBase() {

    @Test
    fun `lt in attribute name`() = assertTokenization(
        input = "<z/0  <>",
        expectedTokens = listOf(
            startTag(
                name = "z",
                attributes = mapOf(
                    "0" to "",
                    "<" to "",
                )
            ),
        )
    )

    @Test
    fun `lt in unquoted attribute value`() = assertTokenization(
        input = "<z x=<>",
        expectedTokens = listOf(
            startTag(
                name = "z",
                attributes = mapOf(
                    "x" to "<",
                )
            ),
        )
    )

    @Test
    fun `= in unquoted attribute value`() = assertTokenization(
        input = "<z z=z=z>",
        expectedTokens = listOf(
            startTag(
                name = "z",
                attributes = mapOf(
                    "z" to "z=z",
                )
            ),
        )
    )

    @Test
    fun `= attribute`() = assertTokenization(
        input = "<z =>",
        expectedTokens = listOf(
            startTag(
                name = "z",
                attributes = mapOf(
                    "=" to "",
                )
            ),
        )
    )

    @Test
    fun `== attribute`() = assertTokenization(
        input = "<z ==>",
        expectedTokens = listOf(
            startTag(
                name = "z",
                attributes = mapOf(
                    "=" to "",
                )
            ),
        )
    )

    @Test
    fun `=== attribute`() = assertTokenization(
        input = "<z ===>",
        expectedTokens = listOf(
            startTag(
                name = "z",
                attributes = mapOf(
                    "=" to "=",
                )
            ),
        )
    )

    @Test
    fun `==== attribute`() = assertTokenization(
        input = "<z ====>",
        expectedTokens = listOf(
            startTag(
                name = "z",
                attributes = mapOf(
                    "=" to "==",
                )
            ),
        )
    )

    @Test
    fun `solidus after ampersand in double-quoted attribute value`() = assertTokenization(
        input = "<z z=\"&\">",
        expectedTokens = listOf(
            startTag(
                name = "z",
                attributes = mapOf(
                    "z" to "&",
                )
            ),
        )
    )

    @Test
    fun `' after ampersand in double-quoted attribute value`() = assertTokenization(
        input = "<z z=\"&'\">",
        expectedTokens = listOf(
            startTag(
                name = "z",
                attributes = mapOf(
                    "z" to "&'",
                )
            ),
        )
    )

    @Test
    fun `' after ampersand in single-quoted attribute value`() = assertTokenization(
        input = "<z z='&'>",
        expectedTokens = listOf(
            startTag(
                name = "z",
                attributes = mapOf(
                    "z" to "&",
                )
            ),
        )
    )

    @Test
    fun `solidus after ampersand in single-quoted attribute value`() = assertTokenization(
        input = "<z z='&\"'>",
        expectedTokens = listOf(
            startTag(
                name = "z",
                attributes = mapOf(
                    "z" to "&\"",
                )
            ),
        )
    )

    @Test
    fun `Text after bogus character reference`() = assertTokenization(
        input = "<z z='&xlink_xmlns;'>bar<z>",
        listOf(
            startTag(
                name = "z",
                attributes = mapOf(
                    "z" to "&xlink_xmlns;",
                )
            ),
            char('b'),
            char('a'),
            char('r'),
            startTag(name = "z"),
        )
    )

    @Test
    fun `Text after hex character reference`() = assertTokenization(
        input = "<z z='&#x0020; foo'>bar<z>",
        expectedTokens = listOf(
            startTag(
                name = "z",
                attributes = mapOf(
                    "z" to "  foo",
                )
            ),
            char('b'),
            char('a'),
            char('r'),
            startTag(name = "z"),
        )
    )

    @Test
    fun `Attribute name starting with quote`() = assertTokenization(
        input = "<foo \"='bar'>",
        expectedTokens = listOf(
            startTag(
                name = "foo",
                attributes = mapOf(
                    "\"" to "bar",
                )
            ),
        )
    )

    @Test
    fun `Attribute name starting with '`() = assertTokenization(
        input = "<foo '='bar'>",
        expectedTokens = listOf(
            startTag(
                name = "foo",
                attributes = mapOf(
                    "'" to "bar",
                )
            ),
        )
    )

    @Test
    fun `Attribute name containing`() = assertTokenization(
        input = "<foo a\"b='bar'>",
        expectedTokens = listOf(
            startTag(
                name = "foo",
                attributes = mapOf(
                    "a\"b" to "bar",
                )
            ),
        )
    )

    @Test
    fun `Attribute name containing '`() = assertTokenization(
        input = "<foo a'b='bar'>",
        expectedTokens = listOf(
            startTag(
                name = "foo",
                attributes = mapOf(
                    "a'b" to "bar",
                )
            ),
        )
    )

    @Test
    fun `Unquoted attribute value containing '`() = assertTokenization(
        input = "<foo a=b'c>",
        expectedTokens = listOf(
            startTag(
                name = "foo",
                attributes = mapOf(
                    "a" to "b'c",
                )
            ),
        )
    )

    @Test
    fun `Unquoted attribute value containing`() = assertTokenization(
        input = "<foo a=b\"c>",
        expectedTokens = listOf(
            startTag(
                name = "foo",
                attributes = mapOf(
                    "a" to "b\"c",
                )
            ),
        )
    )

    @Test
    fun `Double-quoted attribute value not followed by whitespace`() = assertTokenization(
        input = "<foo a=\"b\"c>",
        expectedTokens = listOf(
            startTag(
                name = "foo",
                attributes = mapOf(
                    "a" to "b",
                    "c" to "",
                )
            ),
        )
    )

    @Test
    fun `Single-quoted attribute value not followed by whitespace`() = assertTokenization(
        input = "<foo a='b'c>",
        expectedTokens = listOf(
            startTag(
                name = "foo",
                attributes = mapOf(
                    "a" to "b",
                    "c" to "",
                )
            ),
        )
    )

    @Test
    fun `Quoted attribute followed by permitted slash`() = assertTokenization(
        input = "<br a='b'/>",
        expectedTokens = listOf(
            startTag(
                name = "br",
                attributes = mapOf(
                    "a" to "b",
                ),
                selfClosing = true
            ),
        )
    )

    @Test
    fun `Quoted attribute followed by non-permitted slash`() = assertTokenization(
        input = "<bar a='b'/>",
        expectedTokens = listOf(
            startTag(
                name = "bar",
                attributes = mapOf(
                    "a" to "b",
                ),
                selfClosing = true
            ),
        )
    )

//    @Test
//    fun `CR EOF after doctype name`() = assertTokenization(
//        input = "<!doctype html \r",
//        emptyList()
//         "output":[["DOCTYPE", "html", null, null, false]],
//    )

    @Test
    fun `CR EOF in tag name`() = assertTokenization(
        input = "<z\r",
        emptyList()
    )

    @Test
    fun `Slash EOF in tag name`() = assertTokenization(
        input = "<z/",
        emptyList()
    )

    @Test
    fun `Zero hex numeric entity`() = assertTokenization(
        input = "&#x0",
        listOf(char('\uFFFD'))
    )

    @Test
    fun `Zero decimal numeric entity`() = assertTokenization(
        input = "&#0",
        listOf(char('\uFFFD'))
    )

    @Test
    fun `Zero-prefixed hex numeric entity`() = assertTokenization(
        input = "&#x000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000041;",
        expectedTokens = listOf(char('A'))
    )

    @Test
    fun `Zero-prefixed decimal numeric entity`() = assertTokenization(
        input = "&#000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000065;",
        expectedTokens = listOf(char('A'))
    )

    @Test
    fun `Empty hex numeric entities`() = assertTokenization(
        input = "&#x &#X ",
        expectedTokens = listOf(
            char('&'),
            char('#'),
            char('x'),
            char(' '),
            char('&'),
            char('#'),
            char('X'),
            char(' '),
        )
    )

    @Test
    fun `Invalid digit in hex numeric entity`() = assertTokenization(
        input = "&#xZ",
        expectedTokens = listOf(
            char('&'),
            char('#'),
            char('x'),
            char('Z'),
        )
    )

    @Test
    fun `Empty decimal numeric entities`() = assertTokenization(
        input = "&# &#; ",
        expectedTokens = listOf(
            char('&'),
            char('#'),
            char(' '),
            char('&'),
            char('#'),
            char(';'),
            char(' '),
        )
    )

    @Test
    fun `Invalid digit in decimal numeric entity`() = assertTokenization(
        input = "&#A",
        expectedTokens = listOf(
            char('&'),
            char('#'),
            char('A'),
        )
    )

    @Test
    fun `Non-BMP numeric entity`() = assertTokenization(
        input = "&#x10000;",
        expectedTokens = listOf(
            char('\uD800'),
            char('\uDC00')
        )
    )

    @Test
    fun `Maximum non-BMP numeric entity`() = assertTokenization(
        input = "&#X10FFFF;",
        expectedTokens = listOf(
            char('\uDBFF'),
            char('\uDFFF')
        )
    )

    @Test
    fun `Above maximum numeric entity`() = assertTokenization(
        input = "&#x110000;",
        expectedTokens = listOf(char(0xFFFD.toChar()))
    )

    @Test
    fun `32-bit hex numeric entity`() = assertTokenization(
        input = "&#x80000041;",
        expectedTokens = listOf(char('\uFFFD'))
    )

    @Test
    fun `33-bit hex numeric entity`() = assertTokenization(
        input = "&#x100000041;",
        expectedTokens = listOf(char('\uFFFD'))
    )

    @Test
    fun `33-bit decimal numeric entity`() = assertTokenization(
        input = "&#4294967361;",
        expectedTokens = listOf(char('\uFFFD'))
    )

    @Test
    fun `65-bit hex numeric entity`() = assertTokenization(
        input = "&#x10000000000000041;",
        expectedTokens = listOf(char('\uFFFD'))
    )

    @Test
    fun `65-bit decimal numeric entity`() = assertTokenization(
        input = "&#18446744073709551681;",
        expectedTokens = listOf(char('\uFFFD'))
    )

    @Test
    fun `Surrogate code point edge cases`() = assertTokenization(
        input = "&#xD7FF;&#xD800;&#xD801;&#xDFFE;&#xDFFF;&#xE000;",
        expectedTokens = listOf(
            char('\uD7FF'),
            char('\uFFFD'),
            char('\uFFFD'),
            char('\uFFFD'),
            char('\uFFFD'),
            char('\uE000'),
        )
    )

    @Test
    fun `Uppercase start tag name`() = assertTokenization(
        input = "<X>",
        expectedTokens = listOf(startTag(name = "x"))
    )

    @Test
    fun `Uppercase end tag name`() = assertTokenization(
        input = "</X>",
        expectedTokens = listOf(endTag(name = "x"))
    )

    @Test
    fun `Uppercase attribute name`() = assertTokenization(
        input = "<x X>",
        expectedTokens = listOf(
            startTag(
                name = "x",
                attributes = mapOf(
                    "x" to "",
                )
            ),
        )
    )

    @Test
    fun `Tag attribute name case edge values`() = assertTokenization(
        input = "<x@AZ[`az{ @AZ[`az{>",
        expectedTokens = listOf(
            startTag(
                name = "x@az[`az{",
                attributes = mapOf(
                    "@az[`az{" to "",
                )
            ),
        )
    )

    @Test
    fun `Duplicate different-case attributes`() = assertTokenization(
        input = "<x x=1 x=2 X=3>",
        expectedTokens = listOf(
            startTag(
                name = "x",
                attributes = mapOf(
                    "x" to "1",
                )
            ),
        )
    )

    @Test
    fun `Uppercase close tag attributes`() = assertTokenization(
        input = "</x X>",
        expectedTokens = listOf(endTag(name = "x"))
    )

    @Test
    fun `Duplicate close tag attributes`() = assertTokenization(
        input = "</x x x>",
        expectedTokens = listOf(endTag(name = "x"))
    )

    @Test
    fun `Permitted slash`() = assertTokenization(
        input = "<br/>",
        expectedTokens = listOf(startTag(name = "br", selfClosing = true))
    )

    @Test
    fun `Non-permitted slash`() = assertTokenization(
        input = "<xr/>",
        expectedTokens = listOf(startTag(name = "xr", selfClosing = true))
    )

    @Test
    fun `Permitted slash but in close tag`() = assertTokenization(
        input = "</br/>",
        expectedTokens = listOf(endTag(name = "br"))
    )

//    @Test
//    fun `Doctype public case-sensitivity 1`() = assertTokenization(
//        input = "<!DoCtYpE HtMl PuBlIc \"AbC\" \"XyZ\">",
//         "output":[["DOCTYPE", "html", "AbC", "XyZ", true]]
//        emptyList(),
//    )

//    @Test
//    fun `Doctype public case-sensitivity 2`() = assertTokenization(
//        input = "<!dOcTyPe hTmL pUbLiC \"aBc\" \"xYz\">",
//         "output":[["DOCTYPE", "html", "aBc", "xYz", true]]
//        emptyList(),
//    )

//    @Test
//    fun `Doctype system case-sensitivity 1`() = assertTokenization(
//        input = "<!DoCtYpE HtMl SyStEm \"XyZ\">",
//         "output":[["DOCTYPE", "html", null, "XyZ", true]]
//        emptyList()
//    )

//    @Test
//    fun `Doctype system case-sensitivity 2`() = assertTokenization(
//        input = "<!dOcTyPe hTmL sYsTeM \"xYz\">",
//         "output":[["DOCTYPE", "html", null, "xYz", true]]
//        emptyList()
//    )

//    @Test
//    fun `U+0000 in lookahead region after non-matching character`() = assertTokenization(
//        input = "<!doc>\u0000",
//         "output":[["Comment", "doc"], ["Character", "\u0000"]],
//        emptyList()
//    )

//    @Test
//    fun `U+0000 in lookahead region`() = assertTokenization(
//        input = "<!doc\u0000",
//         "output":[["Comment", "doc\uFFFD"]],
//        emptyList()
//    )

//    @Test
//    fun `U+0080 in lookahead region`() = assertTokenization(
//        input = "<!doc\u0080",
//        // output":[["Comment", "doc\u0080"]],
//        emptyList()
//    )

//    @Test
//    fun `U+FDD1 in lookahead region`() = assertTokenization(
//        input = "<!doc\uFDD1",
//         "output":[["Comment", "doc\uFDD1"]],
//        emptyList()
//    )

//    @Test
//    fun `U+1FFFF in lookahead region`() = assertTokenization(
//        input = "<!doc\uD83F\uDFFF",
//         "output":[["Comment", "doc\uD83F\uDFFF"]],
//        emptyList()
//    )

    @Test
    fun `CR followed by non-LF`() = assertTokenization(
        input = "\r?",
        expectedTokens = listOf(
            char('\n'),
            char('?'),
        )
    )

    @Test
    fun `CR at EOF`() = assertTokenization(
        input = "\r",
        expectedTokens = listOf(char('\n'))
    )

    @Test
    fun `LF at EOF`() = assertTokenization(
        input = "\n",
        expectedTokens = listOf(char('\n'))
    )

    @Test
    fun `CR LF`() = assertTokenization(
        input = "\r\n",
        expectedTokens = listOf(
            char('\n'),
        )
    )

    @Test
    fun `CR CR`() = assertTokenization(
        input = "\r\r",
        expectedTokens = listOf(
            char('\n'),
            char('\n'),
        )
    )

    @Test
    fun `LF LF`() = assertTokenization(
        input = "\n\n",
        expectedTokens = listOf(
            char('\n'),
            char('\n'),
        )
    )

    @Test
    fun `LF CR`() = assertTokenization(
        input = "\n\r",
        expectedTokens = listOf(
            char('\n'),
            char('\n'),
        )
    )

    @Test
    fun `text CR CR CR text`() = assertTokenization(
        input = "text\r\r\rtext",
        expectedTokens = listOf(
            char('t'),
            char('e'),
            char('x'),
            char('t'),
            char('\n'),
            char('\n'),
            char('\n'),
            char('t'),
            char('e'),
            char('x'),
            char('t'),
        )
    )

//    @Test
//    fun `Doctype publik`() = assertTokenization(
//        input = "<!DOCTYPE html PUBLIK \"AbC\" \"XyZ\">",
//         "output":[["DOCTYPE", "html", null, null, false]],
//        emptyList()
//    )

//    @Test
//    fun `Doctype publi`() = assertTokenization(
//        input = "<!DOCTYPE html PUBLI",
//         "output":[["DOCTYPE", "html", null, null, false]],
//        emptyList()
//    )

//    @Test
//    fun `Doctype sistem`() = assertTokenization(
//        input = "<!DOCTYPE html SISTEM \"AbC\">",
//         "output":[["DOCTYPE", "html", null, null, false]],
//        emptyList()
//    )

//    @Test
//    fun `Doctype sys`() = assertTokenization(
//        input = "<!DOCTYPE html SYS",
//         "output":[["DOCTYPE", "html", null, null, false]],
//        emptyList()
//    )

//    @Test
//    fun `Doctype html xgt text`() = assertTokenization(
//        input = "<!DOCTYPE html x>text",
//         "output":[["DOCTYPE", "html", null, null, false], ["Character", "text"]],
//        emptyList()
//    )

    @Test
    fun `Grave accent in unquoted attribute`() = assertTokenization(
        input = "<a a=aa`>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf(
                    "a" to "aa`",
                )
            ),
        )
    )

    @Test
    fun `EOF in tag name state `() = assertTokenization(
        input = "<a",
        emptyList()
    )

    @Test
    fun `EOF in before attribute name state`() = assertTokenization(
        input = "<a ",
        emptyList()
    )

    @Test
    fun `EOF in attribute name state`() = assertTokenization(
        input = "<a a",
        emptyList()
    )

    @Test
    fun `EOF in after attribute name state`() = assertTokenization(
        input = "<a a ",
        emptyList()
    )

    @Test
    fun `EOF in before attribute value state`() = assertTokenization(
        input = "<a a =",
        emptyList()
    )

    @Test
    fun `EOF in attribute value double quoted state`() = assertTokenization(
        input = "<a a =\"a",
        emptyList()
    )

    @Test
    fun `EOF in attribute value single quoted state`() = assertTokenization(
        input = "<a a ='a",
        emptyList()
    )

    @Test
    fun `EOF in attribute value unquoted state`() = assertTokenization(
        input = "<a a =a",
        emptyList()
    )

    @Test
    fun `EOF in after attribute value state`() = assertTokenization(
        input = "<a a ='a'",
        emptyList()
    )
}