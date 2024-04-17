package com.hussain.csoup.tokenizer

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import kotlin.test.Test
import kotlin.test.assertEquals

class EntitiesTest {

    private val tokenizer = Tokenizer()

    @Test
    fun `Undefined named entity in a double-quoted attribute value ending in semicolon and whose name starts with a known entity name`() {
        val input = "<h a=\"&noti;\">"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&noti;")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity name requiring semicolon instead followed by the equals sign in a double-quoted attribute value`() {
        val input = "<h a=\"&lang=\">"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&lang=")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid entity name followed by the equals sign in a double-quoted attribute value`() {
        val input = "<h a=\"&not=\">"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&not=")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Undefined named entity in a single-quoted attribute value ending in semicolon and whose name starts with a known entity name`() {
        val input = "<h a='&noti;'>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&noti;")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity name requiring semicolon instead followed by the equals sign in a single-quoted attribute value`() {
        val input = "<h a='&lang='>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&lang=")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid entity name followed by the equals sign in a single-quoted attribute value`() {
        val input = "<h a='&not='>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&not=")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Undefined named entity in an unquoted attribute value ending in semicolon and whose name starts with a known entity name`() {
        val input = "<h a=&noti;>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&noti;")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Entity name requiring semicolon instead followed by the equals sign in an unquoted attribute value`() {
        val input = "<h a=&lang=>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&lang=")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid entity name followed by the equals sign in an unquoted attribute value`() {
        val input = "<h a=&not=>"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.StartTag(
                    name = "h",
                    attributes = mutableMapOf("a" to "&not=")
                )
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Ambiguous ampersand`() {
        val input = "&rrrraannddom;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('&'),
                Token.Character('r'),
                Token.Character('r'),
                Token.Character('r'),
                Token.Character('r'),
                Token.Character('a'),
                Token.Character('a'),
                Token.Character('n'),
                Token.Character('n'),
                Token.Character('d'),
                Token.Character('d'),
                Token.Character('o'),
                Token.Character('m'),
                Token.Character(';')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Semicolonless named entity 'not' followed by 'i' in body`() {
        val input = "&noti;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('¬'),
                Token.Character('i'),
                Token.Character(';')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Very long undefined named entity in body`() {
        val input =
            "&ammmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmp;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character(data = '&'),
                Token.Character(data = 'a'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'm'),
                Token.Character(data = 'p'),
                Token.Character(data = ';')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `CR as numeric entity`() {
        val input = "&#013;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\r')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `CR as hexadecimal numeric entity`() {
        val input = "&#x00D;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\r')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 EURO SIGN numeric entity`() {
        val input = "&#0128;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('€')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 REPLACEMENT CHAR numeric entity`() {
        val input = "&#0129;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 SINGLE LOW-9 QUOTATION MARK numeric entity`() {
        val input = "&#0130;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('‚')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN SMALL LETTER F WITH HOOK numeric entity`() {
        val input = "&#0131;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('ƒ')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 DOUBLE LOW-9 QUOTATION MARK numeric entity`() {
        val input = "&#0132;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('„')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 HORIZONTAL ELLIPSIS numeric entity`() {
        val input = "&#0133;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('…')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 DAGGER numeric entity`() {
        val input = "&#0134;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('†')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 DOUBLE DAGGER numeric entity`() {
        val input = "&#0135;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('‡')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 MODIFIER LETTER CIRCUMFLEX ACCENT numeric entity`() {
        val input = "&#0136;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('ˆ')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 PER MILLE SIGN numeric entity`() {
        val input = "&#0137;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('‰')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN CAPITAL LETTER S WITH CARON numeric entity`() {
        val input = "&#0138;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('Š')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 SINGLE LEFT-POINTING ANGLE QUOTATION MARK numeric entity`() {
        val input = "&#0139;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('‹')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN CAPITAL LIGATURE OE numeric entity`() {
        val input = "&#0140;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('Œ')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 REPLACEMENT CHAR numeric entity 3`() {
        val input = "&#0141;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN CAPITAL LETTER Z WITH CARON numeric entity`() {
        val input = "&#0142;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('Ž')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 REPLACEMENT CHAR numeric entity 1`() {
        val input = "&#0143;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 REPLACEMENT CHAR numeric entity 2`() {
        val input = "&#0144;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LEFT SINGLE QUOTATION MARK numeric entity`() {
        val input = "&#0145;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('‘')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 RIGHT SINGLE QUOTATION MARK numeric entity`() {
        val input = "&#0146;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('’')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LEFT DOUBLE QUOTATION MARK numeric entity`() {
        val input = "&#0147;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('“')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 RIGHT DOUBLE QUOTATION MARK numeric entity`() {
        val input = "&#0148;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('”')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 BULLET numeric entity`() {
        val input = "&#0149;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('•')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 EN DASH numeric entity`() {
        val input = "&#0150;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('–')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 EM DASH numeric entity`() {
        val input = "&#0151;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('—')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 SMALL TILDE numeric entity`() {
        val input = "&#0152;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('˜')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 TRADE MARK SIGN numeric entity`() {
        val input = "&#0153;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('™')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN SMALL LETTER S WITH CARON numeric entity`() {
        val input = "&#0154;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('š')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 SINGLE RIGHT-POINTING ANGLE QUOTATION MARK numeric entity`() {
        val input = "&#0155;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('›')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN SMALL LIGATURE OE numeric entity`() {
        val input = "&#0156;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('œ')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows 1252 REPLACEMENT CHAR numeric entity`() {
        val input = "&#0157;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 EURO SIGN hexadecimal numeric entity`() {
        val input = "&#x080;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('€')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 REPLACEMENT CHAR hexadecimal numeric entity`() {
        val input = "&#x081;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 SINGLE LOW-9 QUOTATION MARK hexadecimal numeric entity`() {
        val input = "&#x082;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('‚')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN SMALL LETTER F WITH HOOK hexadecimal numeric entity`() {
        val input = "&#x083;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('ƒ')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 DOUBLE LOW-9 QUOTATION MARK hexadecimal numeric entity`() {
        val input = "&#x084;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('„')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 HORIZONTAL ELLIPSIS hexadecimal numeric entity`() {
        val input = "&#x085;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('…')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 DAGGER hexadecimal numeric entity`() {
        val input = "&#x086;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('†')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 DOUBLE DAGGER hexadecimal numeric entity`() {
        val input = "&#x087;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('‡')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 MODIFIER LETTER CIRCUMFLEX ACCENT hexadecimal numeric entity`() {
        val input = "&#x088;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('ˆ')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 PER MILLE SIGN hexadecimal numeric entity`() {
        val input = "&#x089;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('‰')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN CAPITAL LETTER S WITH CARON hexadecimal numeric entity`() {
        val input = "&#x08A;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('Š')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 SINGLE LEFT-POINTING ANGLE QUOTATION MARK hexadecimal numeric entity`() {
        val input = "&#x08B;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('‹')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN CAPITAL LIGATURE OE hexadecimal numeric entity`() {
        val input = "&#x08C;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('Œ')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows 1252 REPLACEMENT CHAR hexadecimal numeric entity 3`() {
        val input = "&#x08D;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN CAPITAL LETTER Z WITH CARON hexadecimal numeric entity`() {
        val input = "&#x08E;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('Ž')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows 1252 REPLACEMENT CHAR hexadecimal numeric entity 1`() {
        val input = "&#x08F;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows 1252 REPLACEMENT CHAR hexadecimal numeric entity 2`() {
        val input = "&#x090;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LEFT SINGLE QUOTATION MARK hexadecimal numeric entity`() {
        val input = "&#x091;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('‘')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 RIGHT SINGLE QUOTATION MARK hexadecimal numeric entity`() {
        val input = "&#x092;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('’')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LEFT DOUBLE QUOTATION MARK hexadecimal numeric entity`() {
        val input = "&#x093;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('“')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 RIGHT DOUBLE QUOTATION MARK hexadecimal numeric entity`() {
        val input = "&#x094;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('”')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 BULLET hexadecimal numeric entity`() {
        val input = "&#x095;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('•')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 EN DASH hexadecimal numeric entity`() {
        val input = "&#x096;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('–')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 EM DASH hexadecimal numeric entity`() {
        val input = "&#x097;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('—')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 SMALL TILDE hexadecimal numeric entity`() {
        val input = "&#x098;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('˜')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 TRADE MARK SIGN hexadecimal numeric entity`() {
        val input = "&#x099;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('™')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN SMALL LETTER S WITH CARON hexadecimal numeric entity`() {
        val input = "&#x09A;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('š')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 SINGLE RIGHT-POINTING ANGLE QUOTATION MARK hexadecimal numeric entity`() {
        val input = "&#x09B;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('›')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN SMALL LIGATURE OE hexadecimal numeric entity`() {
        val input = "&#x09C;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('œ')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows 1252 REPLACEMENT CHAR hexadecimal numeric entity`() {
        val input = "&#x09D;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN SMALL LETTER Z WITH CARON hexadecimal numeric entity`() {
        val input = "&#x09E;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('ž')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Windows-1252 LATIN CAPITAL LETTER Y WITH DIAERESIS hexadecimal numeric entity`() {
        val input = "&#x09F;"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('Ÿ')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Decimal numeric entity followed by hex character a`() {
        val input = "&#97a"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('a'),
                Token.Character('a')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Decimal numeric entity followed by hex character A 1`() {
        val input = "&#97A"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('a'),
                Token.Character('A')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Decimal numeric entity followed by hex character f`() {
        val input = "&#97f"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('a'),
                Token.Character('f')
            ),
            tokenizer.emittedTokens
        )
    }

    @Test
    fun `Decimal numeric entity followed by hex character A 2`() {
        val input = "&#97F"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('a'),
                Token.Character('F')
            ),
            tokenizer.emittedTokens
        )
    }
}