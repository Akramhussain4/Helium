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

package com.theakram.helium

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenizer.TokenizerTestBase
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