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
