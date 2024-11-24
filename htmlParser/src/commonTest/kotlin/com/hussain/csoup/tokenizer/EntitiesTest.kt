package com.hussain.csoup.tokenizer

import kotlin.test.Test

/**
 * Contains all 80 tests from here:
 * https://github.com/html5lib/html5lib-tests/blob/master/tokenizer/entities.test
 */
internal class EntitiesTest : TokenizerTestBase() {

    @Test
    fun `Undefined named entity in a double-quoted attribute value ending in semicolon and whose name starts with a known entity name`() =
        assertTokenization(
            input = "<h a=\"&noti;\">",
            expectedTokens = listOf(
                startTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&noti;")
                )
            )
        )

    @Test
    fun `Entity name requiring semicolon instead followed by the equals sign in a double-quoted attribute value`() =
        assertTokenization(
            input = "<h a=\"&lang=\">",
            expectedTokens = listOf(
                startTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&lang=")
                )
            ),
        )

    @Test
    fun `Valid entity name followed by the equals sign in a double-quoted attribute value`() = assertTokenization(
        input = "<h a=\"&not=\">",
        expectedTokens = listOf(
            startTag(
                name = "h",
                attributes = mutableMapOf("a" to "&not=")
            )
        ),
    )

    @Test
    fun `Undefined named entity in a single-quoted attribute value ending in semicolon and whose name starts with a known entity name`() =
        assertTokenization(
            input = "<h a='&noti;'>",
            expectedTokens = listOf(
                startTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&noti;")
                )
            ),
        )

    @Test
    fun `Entity name requiring semicolon instead followed by the equals sign in a single-quoted attribute value`() =
        assertTokenization(
            input = "<h a='&lang='>",
            expectedTokens = listOf(
                startTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&lang=")
                )
            ),
        )

    @Test
    fun `Valid entity name followed by the equals sign in a single-quoted attribute value`() = assertTokenization(
        input = "<h a='&not='>",
        expectedTokens = listOf(
            startTag(
                name = "h",
                attributes = mutableMapOf("a" to "&not=")
            )
        ),
    )

    @Test
    fun `Undefined named entity in an unquoted attribute value ending in semicolon and whose name starts with a known entity name`() =
        assertTokenization(
            input = "<h a=&noti;>",
            expectedTokens = listOf(
                startTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&noti;")
                )
            ),
        )

    @Test
    fun `Entity name requiring semicolon instead followed by the equals sign in an unquoted attribute value`() =
        assertTokenization(
            input = "<h a=&lang=>",
            expectedTokens = listOf(
                startTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&lang=")
                )
            ),
        )

    @Test
    fun `Valid entity name followed by the equals sign in an unquoted attribute value`() = assertTokenization(
        input = "<h a=&not=>",
        expectedTokens = listOf(
            startTag(
                name = "h",
                attributes = mutableMapOf("a" to "&not=")
            )
        ),
    )

    @Test
    fun `Ambiguous ampersand`() = assertTokenization(
        input = "&rrrraannddom;",
        expectedTokens = listOf(
            char('&'),
            char('r'),
            char('r'),
            char('r'),
            char('r'),
            char('a'),
            char('a'),
            char('n'),
            char('n'),
            char('d'),
            char('d'),
            char('o'),
            char('m'),
            char(';')
        ),
    )

    @Test
    fun `Semicolonless named entity 'not' followed by 'i' in body`() = assertTokenization(
        input = "&noti;",
        expectedTokens = listOf(
            char('¬'),
            char('i'),
            char(';')
        ),
    )

    @Test
    fun `Very long undefined named entity in body`() = assertTokenization(
        input = "&ammmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmp;",
        expectedTokens = chars("&ammmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmp;")
    )

    @Test
    fun `CR as numeric entity`() = assertTokenization(
        input = "&#013;",
        expectedTokens = listOf(
            char('\r')
        ),
    )

    @Test
    fun `CR as hexadecimal numeric entity`() = assertTokenization(
        input = "&#x00D;",
        expectedTokens = listOf(
            char('\r')
        ),
    )

    @Test
    fun `Windows-1252 EURO SIGN numeric entity`() = assertTokenization(
        input = "&#0128;",
        expectedTokens = listOf(
            char('€')
        ),
    )

    @Test
    fun `Windows-1252 REPLACEMENT CHAR numeric entity`() = assertTokenization(
        input = "&#0129;",
        expectedTokens = listOf(
            char('')
        ),
    )

    @Test
    fun `Windows-1252 SINGLE LOW-9 QUOTATION MARK numeric entity`() = assertTokenization(
        input = "&#0130;",
        expectedTokens = listOf(
            char('‚')
        ),
    )

    @Test
    fun `Windows-1252 LATIN SMALL LETTER F WITH HOOK numeric entity`() = assertTokenization(
        input = "&#0131;",
        expectedTokens = listOf(
            char('ƒ')
        ),
    )

    @Test
    fun `Windows-1252 DOUBLE LOW-9 QUOTATION MARK numeric entity`() = assertTokenization(
        input = "&#0132;",
        expectedTokens = listOf(
            char('„')
        ),
    )

    @Test
    fun `Windows-1252 HORIZONTAL ELLIPSIS numeric entity`() = assertTokenization(
        input = "&#0133;",
        expectedTokens = listOf(
            char('…')
        ),
    )

    @Test
    fun `Windows-1252 DAGGER numeric entity`() = assertTokenization(
        input = "&#0134;",
        expectedTokens = listOf(
            char('†')
        ),
    )

    @Test
    fun `Windows-1252 DOUBLE DAGGER numeric entity`() = assertTokenization(
        input = "&#0135;",
        expectedTokens = listOf(
            char('‡')
        ),
    )

    @Test
    fun `Windows-1252 MODIFIER LETTER CIRCUMFLEX ACCENT numeric entity`() = assertTokenization(
        input = "&#0136;",
        expectedTokens = listOf(
            char('ˆ')
        ),
    )

    @Test
    fun `Windows-1252 PER MILLE SIGN numeric entity`() = assertTokenization(
        input = "&#0137;",
        expectedTokens = listOf(
            char('‰')
        ),
    )

    @Test
    fun `Windows-1252 LATIN CAPITAL LETTER S WITH CARON numeric entity`() = assertTokenization(
        input = "&#0138;",
        expectedTokens = listOf(
            char('Š')
        ),
    )

    @Test
    fun `Windows-1252 SINGLE LEFT-POINTING ANGLE QUOTATION MARK numeric entity`() = assertTokenization(
        input = "&#0139;",
        expectedTokens = listOf(
            char('‹')
        ),
    )

    @Test
    fun `Windows-1252 LATIN CAPITAL LIGATURE OE numeric entity`() = assertTokenization(
        input = "&#0140;",
        expectedTokens = listOf(
            char('Œ')
        ),
    )

    @Test
    fun `Windows-1252 REPLACEMENT CHAR numeric entity 3`() = assertTokenization(
        input = "&#0141;",
        expectedTokens = listOf(
            char('')
        ),
    )

    @Test
    fun `Windows-1252 LATIN CAPITAL LETTER Z WITH CARON numeric entity`() = assertTokenization(
        input = "&#0142;",
        expectedTokens = listOf(
            char('Ž')
        ),
    )

    @Test
    fun `Windows-1252 REPLACEMENT CHAR numeric entity 1`() = assertTokenization(
        input = "&#0143;",
        expectedTokens = listOf(
            char('')
        ),
    )

    @Test
    fun `Windows-1252 REPLACEMENT CHAR numeric entity 2`() = assertTokenization(
        input = "&#0144;",
        expectedTokens = listOf(
            char('')
        ),
    )

    @Test
    fun `Windows-1252 LEFT SINGLE QUOTATION MARK numeric entity`() = assertTokenization(
        input = "&#0145;",
        expectedTokens = listOf(
            char('‘')
        ),
    )

    @Test
    fun `Windows-1252 RIGHT SINGLE QUOTATION MARK numeric entity`() = assertTokenization(
        input = "&#0146;",
        expectedTokens = listOf(
            char('’')
        ),
    )

    @Test
    fun `Windows-1252 LEFT DOUBLE QUOTATION MARK numeric entity`() = assertTokenization(
        input = "&#0147;",
        expectedTokens = listOf(
            char('“')
        ),
    )

    @Test
    fun `Windows-1252 RIGHT DOUBLE QUOTATION MARK numeric entity`() = assertTokenization(
        input = "&#0148;",
        expectedTokens = listOf(
            char('”')
        ),
    )

    @Test
    fun `Windows-1252 BULLET numeric entity`() = assertTokenization(
        input = "&#0149;",
        expectedTokens = listOf(
            char('•')
        ),
    )

    @Test
    fun `Windows-1252 EN DASH numeric entity`() = assertTokenization(
        input = "&#0150;",
        expectedTokens = listOf(
            char('–')
        ),
    )

    @Test
    fun `Windows-1252 EM DASH numeric entity`() = assertTokenization(
        input = "&#0151;",
        expectedTokens = listOf(
            char('—')
        ),
    )

    @Test
    fun `Windows-1252 SMALL TILDE numeric entity`() = assertTokenization(
        input = "&#0152;",
        expectedTokens = listOf(
            char('˜')
        ),
    )

    @Test
    fun `Windows-1252 TRADE MARK SIGN numeric entity`() = assertTokenization(
        input = "&#0153;",
        expectedTokens = listOf(
            char('™')
        ),
    )

    @Test
    fun `Windows-1252 LATIN SMALL LETTER S WITH CARON numeric entity`() = assertTokenization(
        input = "&#0154;",
        expectedTokens = listOf(
            char('š')
        ),
    )

    @Test
    fun `Windows-1252 SINGLE RIGHT-POINTING ANGLE QUOTATION MARK numeric entity`() = assertTokenization(
        input = "&#0155;",
        expectedTokens = listOf(
            char('›')
        ),
    )

    @Test
    fun `Windows-1252 LATIN SMALL LIGATURE OE numeric entity`() = assertTokenization(
        input = "&#0156;",
        expectedTokens = listOf(
            char('œ')
        ),
    )

    @Test
    fun `Windows 1252 REPLACEMENT CHAR numeric entity`() = assertTokenization(
        input = "&#0157;",
        expectedTokens = listOf(
            char('')
        ),
    )

    @Test
    fun `Windows-1252 EURO SIGN hexadecimal numeric entity`() = assertTokenization(
        input = "&#x080;",
        expectedTokens = listOf(
            char('€')
        ),
    )

    @Test
    fun `Windows-1252 REPLACEMENT CHAR hexadecimal numeric entity`() = assertTokenization(
        input = "&#x081;",
        expectedTokens = listOf(
            char('')
        ),
    )

    @Test
    fun `Windows-1252 SINGLE LOW-9 QUOTATION MARK hexadecimal numeric entity`() = assertTokenization(
        input = "&#x082;",
        expectedTokens = listOf(
            char('‚')
        ),
    )

    @Test
    fun `Windows-1252 LATIN SMALL LETTER F WITH HOOK hexadecimal numeric entity`() = assertTokenization(
        input = "&#x083;",
        expectedTokens = listOf(
            char('ƒ')
        ),
    )

    @Test
    fun `Windows-1252 DOUBLE LOW-9 QUOTATION MARK hexadecimal numeric entity`() = assertTokenization(
        input = "&#x084;",
        expectedTokens = listOf(
            char('„')
        ),
    )

    @Test
    fun `Windows-1252 HORIZONTAL ELLIPSIS hexadecimal numeric entity`() = assertTokenization(
        input = "&#x085;",
        expectedTokens = listOf(
            char('…')
        ),
    )

    @Test
    fun `Windows-1252 DAGGER hexadecimal numeric entity`() = assertTokenization(
        input = "&#x086;",
        expectedTokens = listOf(
            char('†')
        ),
    )

    @Test
    fun `Windows-1252 DOUBLE DAGGER hexadecimal numeric entity`() = assertTokenization(
        input = "&#x087;",
        expectedTokens = listOf(
            char('‡')
        ),
    )

    @Test
    fun `Windows-1252 MODIFIER LETTER CIRCUMFLEX ACCENT hexadecimal numeric entity`() = assertTokenization(
        input = "&#x088;",
        expectedTokens = listOf(
            char('ˆ')
        ),
    )

    @Test
    fun `Windows-1252 PER MILLE SIGN hexadecimal numeric entity`() = assertTokenization(
        input = "&#x089;",
        expectedTokens = listOf(
            char('‰')
        ),
    )

    @Test
    fun `Windows-1252 LATIN CAPITAL LETTER S WITH CARON hexadecimal numeric entity`() = assertTokenization(
        input = "&#x08A;",
        expectedTokens = listOf(
            char('Š')
        ),
    )

    @Test
    fun `Windows-1252 SINGLE LEFT-POINTING ANGLE QUOTATION MARK hexadecimal numeric entity`() = assertTokenization(
        input = "&#x08B;",
        expectedTokens = listOf(
            char('‹')
        ),
    )

    @Test
    fun `Windows-1252 LATIN CAPITAL LIGATURE OE hexadecimal numeric entity`() = assertTokenization(
        input = "&#x08C;",
        expectedTokens = listOf(
            char('Œ')
        ),
    )

    @Test
    fun `Windows 1252 REPLACEMENT CHAR hexadecimal numeric entity 3`() = assertTokenization(
        input = "&#x08D;",
        expectedTokens = listOf(
            char('')
        ),
    )

    @Test
    fun `Windows-1252 LATIN CAPITAL LETTER Z WITH CARON hexadecimal numeric entity`() = assertTokenization(
        input = "&#x08E;",
        expectedTokens = listOf(
            char('Ž')
        ),
    )

    @Test
    fun `Windows 1252 REPLACEMENT CHAR hexadecimal numeric entity 1`() = assertTokenization(
        input = "&#x08F;",
        expectedTokens = listOf(
            char('')
        ),
    )

    @Test
    fun `Windows 1252 REPLACEMENT CHAR hexadecimal numeric entity 2`() = assertTokenization(
        input = "&#x090;",
        expectedTokens = listOf(char(''))
    )

    @Test
    fun `Windows-1252 LEFT SINGLE QUOTATION MARK hexadecimal numeric entity`() = assertTokenization(
        input = "&#x091;",
        expectedTokens = listOf(char('‘'))
    )

    @Test
    fun `Windows-1252 RIGHT SINGLE QUOTATION MARK hexadecimal numeric entity`() = assertTokenization(
        input = "&#x092;",
        expectedTokens = listOf(char('’'))
    )

    @Test
    fun `Windows-1252 LEFT DOUBLE QUOTATION MARK hexadecimal numeric entity`() = assertTokenization(
        input = "&#x093;",
        expectedTokens = listOf(char('“'))
    )

    @Test
    fun `Windows-1252 RIGHT DOUBLE QUOTATION MARK hexadecimal numeric entity`() = assertTokenization(
        input = "&#x094;",
        expectedTokens = listOf(char('”'))
    )

    @Test
    fun `Windows-1252 BULLET hexadecimal numeric entity`() = assertTokenization(
        input = "&#x095;",
        expectedTokens = listOf(char('•'))
    )

    @Test
    fun `Windows-1252 EN DASH hexadecimal numeric entity`() = assertTokenization(
        input = "&#x096;",
        expectedTokens = listOf(char('–'))
    )

    @Test
    fun `Windows-1252 EM DASH hexadecimal numeric entity`() = assertTokenization(
        input = "&#x097;",
        expectedTokens = listOf(char('—'))
    )

    @Test
    fun `Windows-1252 SMALL TILDE hexadecimal numeric entity`() = assertTokenization(
        input = "&#x098;",
        expectedTokens = listOf(char('˜'))
    )

    @Test
    fun `Windows-1252 TRADE MARK SIGN hexadecimal numeric entity`() = assertTokenization(
        input = "&#x099;",
        expectedTokens = listOf(char('™'))
    )

    @Test
    fun `Windows-1252 LATIN SMALL LETTER S WITH CARON hexadecimal numeric entity`() = assertTokenization(
        input = "&#x09A;",
        expectedTokens = listOf(char('š'))
    )

    @Test
    fun `Windows-1252 SINGLE RIGHT-POINTING ANGLE QUOTATION MARK hexadecimal numeric entity`() = assertTokenization(
        input = "&#x09B;",
        expectedTokens = listOf(char('›'))
    )

    @Test
    fun `Windows-1252 LATIN SMALL LIGATURE OE hexadecimal numeric entity`() = assertTokenization(
        input = "&#x09C;",
        expectedTokens = listOf(char('œ'))
    )

    @Test
    fun `Windows 1252 REPLACEMENT CHAR hexadecimal numeric entity`() = assertTokenization(
        input = "&#x09D;",
        expectedTokens = listOf(char(''))
    )

    @Test
    fun `Windows-1252 LATIN SMALL LETTER Z WITH CARON hexadecimal numeric entity`() = assertTokenization(
        input = "&#x09E;",
        expectedTokens = listOf(char('ž'))
    )

    @Test
    fun `Windows-1252 LATIN CAPITAL LETTER Y WITH DIAERESIS hexadecimal numeric entity`() = assertTokenization(
        input = "&#x09F;",
        expectedTokens = listOf(char('Ÿ'))
    )

    @Test
    fun `Decimal numeric entity followed by hex character a`() = assertTokenization(
        input = "&#97a",
        expectedTokens = listOf(
            char('a'),
            char('a')
        ),
    )

    @Test
    fun `Decimal numeric entity followed by hex character A 1`() = assertTokenization(
        input = "&#97A",
        expectedTokens = listOf(
            char('a'),
            char('A')
        ),
    )

    @Test
    fun `Decimal numeric entity followed by hex character f`() = assertTokenization(
        input = "&#97f",
        expectedTokens = listOf(
            char('a'),
            char('f')
        ),
    )

    @Test
    fun `Decimal numeric entity followed by hex character A 2`() = assertTokenization(
        input = "&#97F",
        expectedTokens = listOf(
            char('a'),
            char('F')
        ),
    )
}