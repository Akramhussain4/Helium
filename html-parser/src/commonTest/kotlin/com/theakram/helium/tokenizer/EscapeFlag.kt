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
