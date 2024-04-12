package com.hussain.csoup.tokenizer

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import kotlin.test.Test
import kotlin.test.assertEquals

class TokenizerTest4 {

    private val tokenizer = Tokenizer()

    @Test
    fun `lt in attribute name`() {
        val input = "<z/0  <>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "z",
                    attributes = mutableMapOf(
                        "0" to "",
                        "<" to "",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `lt in unquoted attribute value`() {
        val input = "<z x=<>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "z",
                    attributes = mutableMapOf(
                        "x" to "<",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `= in unquoted attribute value`() {
        val input = "<z z=z=z>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "z",
                    attributes = mutableMapOf(
                        "z" to "z=z",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `= attribute`() {
        val input = "<z =>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "z",
                    attributes = mutableMapOf(
                        "=" to "",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `== attribute`() {
        val input = "<z ==>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "z",
                    attributes = mutableMapOf(
                        "=" to "",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `=== attribute`() {
        val input = "<z ===>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "z",
                    attributes = mutableMapOf(
                        "=" to "=",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `==== attribute`() {
        val input = "<z ====>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "z",
                    attributes = mutableMapOf(
                        "=" to "==",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `solidus after ampersand in double-quoted attribute value`() {
        val input = "<z z=\"&\">"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "z",
                    attributes = mutableMapOf(
                        "z" to "&",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `' after ampersand in double-quoted attribute value`() {
        val input = "<z z=\"&'\">"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "z",
                    attributes = mutableMapOf(
                        "z" to "&'",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `' after ampersand in single-quoted attribute value`() {
        val input = "<z z='&'>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "z",
                    attributes = mutableMapOf(
                        "z" to "&",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `solidus after ampersand in single-quoted attribute value`() {
        val input = "<z z='&\"'>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "z",
                    attributes = mutableMapOf(
                        "z" to "&\"",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Text after bogus character reference`() {
        val input = "<z z='&xlink_xmlns;'>bar<z>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "z",
                    attributes = mutableMapOf(
                        "z" to "&xlink_xmlns;",
                    )
                ),
                Token.Character('b'),
                Token.Character('a'),
                Token.Character('r'),
                Token.StartTag(name = "z"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Text after hex character reference`() {
        val input = "<z z='&#x0020; foo'>bar<z>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "z",
                    attributes = mutableMapOf(
                        "z" to "&#x0020; foo",
                    )
                ),
                Token.Character('b'),
                Token.Character('a'),
                Token.Character('r'),
                Token.StartTag(name = "z"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Attribute name starting with quote`() {
        val input = "<foo \"='bar'>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "foo",
                    attributes = mutableMapOf(
                        "\"" to "bar",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Attribute name starting with '`() {
        val input = "<foo '='bar'>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "foo",
                    attributes = mutableMapOf(
                        "'" to "bar",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Attribute name containing`() {
        val input = "<foo a\"b='bar'>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "foo",
                    attributes = mutableMapOf(
                        "a\"b" to "bar",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Attribute name containing '`() {
        val input = "<foo a'b='bar'>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "foo",
                    attributes = mutableMapOf(
                        "a'b" to "bar",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Unquoted attribute value containing '`() {
        val input = "<foo a=b'c>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "foo",
                    attributes = mutableMapOf(
                        "a" to "b'c",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Unquoted attribute value containing`() {
        val input = "<foo a=b\"c>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "foo",
                    attributes = mutableMapOf(
                        "a" to "b\"c",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Double-quoted attribute value not followed by whitespace`() {
        val input = "<foo a=\"b\"c>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "foo",
                    attributes = mutableMapOf(
                        "a" to "b",
                        "c" to "",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Single-quoted attribute value not followed by whitespace`() {
        val input = "<foo a='b'c>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "foo",
                    attributes = mutableMapOf(
                        "a" to "b",
                        "c" to "",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Quoted attribute followed by permitted slash`() {
        val input = "<br a='b'/>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "br",
                    attributes = mutableMapOf(
                        "a" to "b",
                    ),
                    selfClosing = true
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Quoted attribute followed by non-permitted slash`() {
        val input = "<bar a='b'/>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "bar",
                    attributes = mutableMapOf(
                        "a" to "b",
                    ),
                    selfClosing = true
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `CR EOF after doctype name`() {
        val input = "<!doctype html \r"
        tokenizer.tokenize(input)
//        assertEquals(
//            listOf<Token>(
//
//            ),
//            tokenizer.emittedTokens
//        )
//        "output":[["DOCTYPE", "html", null, null, false]],
    }

    @Test
    fun `CR EOF in tag name`() {
        val input = "<z\r"
        tokenizer.tokenize(input)
        assertEquals(
            emptyList(),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Slash EOF in tag name`() {
        val input = "<z/"
        tokenizer.tokenize(input)
        assertEquals(
            emptyList(),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Zero hex numeric entity`() {
        val input = "&#x0"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
        //output":[["Character", "\uFFFD"]],
    }

    @Test
    fun `Zero decimal numeric entity`() {
        val input = "&#0"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["Character", "\uFFFD"]],
    }

    @Test
    fun `Zero-prefixed hex numeric entity`() {
        val input =
            "&#x000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000041;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('A')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Zero-prefixed decimal numeric entity`() {
        val input =
            "&#000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000065;"

        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('A')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Empty hex numeric entities`() {
        val input = "&#x &#X "
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('&'),
                Token.Character('#'),
                Token.Character('x'),
                Token.Character(' '),
                Token.Character('&'),
                Token.Character('#'),
                Token.Character('X'),
                Token.Character(' '),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid digit in hex numeric entity`() {
        val input = "&#xZ"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('&'),
                Token.Character('#'),
                Token.Character('x'),
                Token.Character('Z'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Empty decimal numeric entities`() {
        val input = "&# &#; "
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('&'),
                Token.Character('#'),
                Token.Character(' '),
                Token.Character('&'),
                Token.Character('#'),
                Token.Character(';'),
                Token.Character(' '),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid digit in decimal numeric entity`() {
        val input = "&#A"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('&'),
                Token.Character('#'),
                Token.Character('A'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Non-BMP numeric entity`() {
        val input = "&#x10000;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(0x10000.toChar())
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Maximum non-BMP numeric entity`() {
        val input = "&#X10FFFF;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(0x10FFFF.toChar())
            ),
            tokenizer.emittedTokens
        )
    }


    @Test
    fun `Above maximum numeric entity`() {
        val input = "&#x110000;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(0xFFFD.toChar())
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `32-bit hex numeric entity`() {
        val input = "&#x80000041;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(0xFFFD.toChar())
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `33-bit hex numeric entity`() {
        val input = "&#x100000041;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(0xFFFD.toChar())
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `33-bit decimal numeric entity`() {
        val input = "&#4294967361;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(0xFFFD.toChar())
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `65-bit hex numeric entity`() {
        val input = "&#x10000000000000041;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(0xFFFD.toChar())
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `65-bit decimal numeric entity`() {
        val input = "&#18446744073709551681;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(0xFFFD.toChar())
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Surrogate code point edge cases`() {
        val input = "&#xD7FF;&#xD800;&#xD801;&#xDFFE;&#xDFFF;&#xE000;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["Character", "\uD7FF\uFFFD\uFFFD\uFFFD\uFFFD\uE000"]],
    }

    @Test
    fun `Uppercase start tag name`() {
        val input = "<X>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(name = "X"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Uppercase end tag name`() {
        val input = "</X>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.EndTag(name = "x"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Uppercase attribute name`() {
        val input = "<x X>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "x",
                    attributes = mutableMapOf(
                        "x" to "",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Tag attribute name case edge values`() {
        val input = "<x@AZ[`az{ @AZ[`az{>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "x@az[`az{",
                    attributes = mutableMapOf(
                        "@az[`az{" to "",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Duplicate different-case attributes`() {
        val input = "<x x=1 x=2 X=3>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "x",
                    attributes = mutableMapOf(
                        "x" to "1",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Uppercase close tag attributes`() {
        val input = "</x X>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.EndTag(name = "x"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Duplicate close tag attributes`() {
        val input = "</x x x>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.EndTag(name = "x"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Permitted slash`() {
        val input = "<br/>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(name = "br", selfClosing = true),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Non-permitted slash`() {
        val input = "<xr/>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(name = "xr", selfClosing = true)
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Permitted slash but in close tag`() {
        val input = "</br/>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.EndTag(name = "br"),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Doctype public case-sensitivity 1`() {
        val input = "<!DoCtYpE HtMl PuBlIc \"AbC\" \"XyZ\">"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["DOCTYPE", "html", "AbC", "XyZ", true]]
    }

    @Test
    fun `Doctype public case-sensitivity 2`() {
        val input = "<!dOcTyPe hTmL pUbLiC \"aBc\" \"xYz\">"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["DOCTYPE", "html", "aBc", "xYz", true]]
    }

    @Test
    fun `Doctype system case-sensitivity 1`() {
        val input = "<!DoCtYpE HtMl SyStEm \"XyZ\">"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["DOCTYPE", "html", null, "XyZ", true]]
    }

    @Test
    fun `Doctype system case-sensitivity 2`() {
        val input = "<!dOcTyPe hTmL sYsTeM \"xYz\">"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["DOCTYPE", "html", null, "xYz", true]]
    }

    @Test
    fun `U+0000 in lookahead region after non-matching character`() {
        val input = "<!doc>\u0000"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["Comment", "doc"], ["Character", "\u0000"]],
    }

    @Test
    fun `U+0000 in lookahead region`() {
        val input = "<!doc\u0000"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["Comment", "doc\uFFFD"]],
    }

    @Test
    fun `U+0080 in lookahead region`() {
        val input = "<!doc\u0080"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
        // output":[["Comment", "doc\u0080"]],
    }

    @Test
    fun `U+FDD1 in lookahead region`() {
        val input = "<!doc\uFDD1"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["Comment", "doc\uFDD1"]],
    }

    @Test
    fun `U+1FFFF in lookahead region`() {
        val input = "<!doc\uD83F\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["Comment", "doc\uD83F\uDFFF"]],
    }

    @Test
    fun `CR followed by non-LF`() {
        val input = "\r?"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\n'),
                Token.Character('?'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `CR at EOF`() {
        val input = "\r"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\n'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `LF at EOF`() {
        val input = "\n"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\n'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `CR LF`() {
        val input = "\r\n"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\n'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `CR CR`() {
        val input = "\r\r"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\n'),
                Token.Character('\n'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `LF LF`() {
        val input = "\n\n"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\n'),
                Token.Character('\n'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `LF CR`() {
        val input = "\n\r"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\n'),
                Token.Character('\n'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `text CR CR CR text`() {
        val input = "text\r\r\rtext"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('t'),
                Token.Character('e'),
                Token.Character('x'),
                Token.Character('t'),
                Token.Character('\n'),
                Token.Character('\n'),
                Token.Character('\n'),
                Token.Character('t'),
                Token.Character('e'),
                Token.Character('x'),
                Token.Character('t'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Doctype publik`() {
        val input = "<!DOCTYPE html PUBLIK \"AbC\" \"XyZ\">"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["DOCTYPE", "html", null, null, false]],
    }

    @Test
    fun `Doctype publi`() {
        val input = "<!DOCTYPE html PUBLI"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["DOCTYPE", "html", null, null, false]],
    }

    @Test
    fun `Doctype sistem`() {
        val input = "<!DOCTYPE html SISTEM \"AbC\">"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["DOCTYPE", "html", null, null, false]],
    }

    @Test
    fun `Doctype sys`() {
        val input = "<!DOCTYPE html SYS"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["DOCTYPE", "html", null, null, false]],
    }

    @Test
    fun `Doctype html xgt text`() {
        val input = "<!DOCTYPE html x>text"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(

            ),
            tokenizer.emittedTokens
        )
//        "output":[["DOCTYPE", "html", null, null, false], ["Character", "text"]],
    }

    @Test
    fun `Grave accent in unquoted attribute`() {
        val input = "<a a=aa`>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "a",
                    attributes = mutableMapOf(
                        "a" to "aa`",
                    )
                ),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `EOF in tag name state `() {
        val input = "<a"
        tokenizer.tokenize(input)
        assertEquals(
            emptyList(),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `EOF in before attribute name state`() {
        val input = "<a "
        tokenizer.tokenize(input)
        assertEquals(
            emptyList(),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `EOF in attribute name state`() {
        val input = "<a a"
        tokenizer.tokenize(input)
        assertEquals(
            emptyList(),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `EOF in after attribute name state`() {
        val input = "<a a "
        tokenizer.tokenize(input)
        assertEquals(
            emptyList(),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `EOF in before attribute value state`() {
        val input = "<a a ="
        tokenizer.tokenize(input)
        assertEquals(
            emptyList(),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `EOF in attribute value double quoted state`() {
        val input = "<a a =\"a"
        tokenizer.tokenize(input)
        assertEquals(
            emptyList(),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `EOF in attribute value single quoted state`() {
        val input = "<a a ='a"
        tokenizer.tokenize(input)
        assertEquals(
            emptyList(),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `EOF in attribute value unquoted state`() {
        val input = "<a a =a"
        tokenizer.tokenize(input)
        assertEquals(
            emptyList(),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `EOF in after attribute value state`() {
        val input = "<a a ='a'"
        tokenizer.tokenize(input)
        assertEquals(
            emptyList(),
            tokenizer.emittedTokens
        )
    }
}