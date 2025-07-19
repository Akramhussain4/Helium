package com.theakram.helium.tokenizer

import kotlin.test.Test

/**
 * Contains all 5 tests from here:
 * https://github.com/html5lib/html5lib-tests/blob/master/tokenizer/unicodeCharsProblematic.test
 */
internal class UnicodeCharsProblematic : TokenizerTestBase() {

    @Test
    fun `Invalid Unicode character U+DFFF`() = assertTokenization(
        input = """\\uDFFF""",
        listOf(
            char('\\'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid Unicode character U+D800`() = assertTokenization(
        input = """\\uD800""",
        listOf(
            char('\\'),
            char('\\'),
            char('u'),
            char('D'),
            char('8'),
            char('0'),
            char('0'),
        ),
    )

    @Test
    fun `Invalid Unicode character U+DFFF with valid preceding character`() = assertTokenization(
        input = """a\\uDFFF""",
        listOf(
            char('a'),
            char('\\'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid Unicode character U+D800 with valid following character`() = assertTokenization(
        input = """\\uD800a""",
        listOf(
            char('\\'),
            char('\\'),
            char('u'),
            char('D'),
            char('8'),
            char('0'),
            char('0'),
            char('a'),
        ),
    )

    @Test
    fun `CR followed by U+0000`() = assertTokenization(
        input = "\r\u0000",
        listOf(
            char('\n'),
            char('\u0000')
        ),
    )
}