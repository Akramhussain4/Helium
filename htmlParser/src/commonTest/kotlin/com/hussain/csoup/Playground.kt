package com.hussain.csoup

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import kotlin.test.Test
import kotlin.test.assertEquals

class PlaygroundTest {

    @Test
    fun test1() {
        val tokenizer = Tokenizer()
        tokenizer.tokenize("""&amp;""")
        assertEquals(
            listOf<Token>(
                Token.Character('&'),
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun namedchar() {
        val tokenizer = Tokenizer()
        tokenizer.tokenize("I'm &notit; I tell you")
        assertEquals(
            listOf<Token>(
                Token.Character('I'),
                Token.Character('\''),
                Token.Character('m'),
                Token.Character(' '),
                Token.Character('¬'),
                Token.Character('i'),
                Token.Character('t'),
                Token.Character(';'),
                Token.Character(' '),
                Token.Character('I'),
                Token.Character(' '),
                Token.Character('t'),
                Token.Character('e'),
                Token.Character('l'),
                Token.Character('l'),
                Token.Character(' '),
                Token.Character('y'),
                Token.Character('o'),
                Token.Character('u')
            ),
            tokenizer.emittedTokens
        )
    }
}