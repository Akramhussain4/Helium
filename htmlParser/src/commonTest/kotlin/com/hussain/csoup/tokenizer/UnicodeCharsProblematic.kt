package com.hussain.csoup.tokenizer

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import kotlin.test.Test
import kotlin.test.assertEquals

class UnicodeCharsProblematic {

    private val tokenizer = Tokenizer()

    @Test
    fun `Invalid Unicode character U+DFFF`() {
        tokenizer.tokenize("""\\uDFFF""")
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+D800`(){
        tokenizer.tokenize("""\\uD800""")
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('8'),
                Token.Character('0'),
                Token.Character('0'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+DFFF with valid preceding character`(){
        tokenizer.tokenize("""a\\uDFFF""")
        assertEquals(
            listOf<Token>(
                Token.Character('a'),
                Token.Character('\\'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+D800 with valid following character`(){
        tokenizer.tokenize("""\\uD800a""")
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('8'),
                Token.Character('0'),
                Token.Character('0'),
                Token.Character('a'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `CR followed by U+0000`(){
        val input = "\r\u0000"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\n'),
                Token.Character('\u0000')
            ),
            tokenizer.emittedTokens
        )
    }
}