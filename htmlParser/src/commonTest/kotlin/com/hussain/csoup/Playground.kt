package com.hussain.csoup

import com.hussain.csoup.tokenizer.TokenizerTestBase
import kotlin.test.Test

internal class PlaygroundTest : TokenizerTestBase() {

    @Test
    fun namedchar() = assertTokenization(
        input = "I'm &notit; I tell you",
        expectedTokens = chars(
            "I'm ¬it; I tell you"
        )
    )

    @Test
    fun namedChar2() = assertTokenization(
        input = "I'm &notin; I tell you",
        expectedTokens = chars(
            "I'm ∉ I tell you"
        )
    )
}