package com.hussain.csoup

import com.hussain.csoup.tokenization.Token
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

    @Test
    fun namedCharAttribute() = assertTokenization(
        input = """<h1 class="I'm &notin; I tell you">Heading</h1>""",
        expectedTokens = listOf(
            Token.StartTag(
                "h1",
                attributes = mutableMapOf("class" to "I'm ∉ I tell you")
            ),
            char('H'),
            char('e'),
            char('a'),
            char('d'),
            char('i'),
            char('n'),
            char('g'),
            endTag("h1")
        )
    )
}