package com.theakram.helium.tokenizer

import kotlin.test.Test

internal class XmlViolation : TokenizerTestBase() {

    @Test
    fun `NonXML character`() = assertTokenization(
        input = "a￿b",
        expectedTokens = listOf(
            *chars("a�b").toTypedArray()
        )
    )

    @Test
    fun `NonXML space`() = assertTokenization(
        input = "ab",
        expectedTokens = listOf(
            *chars("a b").toTypedArray()
        )
    )

    @Test
    fun `Double hyphen in comment`() = assertTokenization(
        input = "<!-- foo -- bar -->",
        expectedTokens = listOf(
            comment(" foo - - bar ")
        )
    )

    @Test
    fun `FF between attributes`() = assertTokenization(
        input = "<a b=''c=''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("b" to "", "c" to ""))
        )
    )
}
