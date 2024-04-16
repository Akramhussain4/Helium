package com.hussain.csoup.tokenizer

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import kotlin.test.Test
import kotlin.test.assertEquals

class UnicodeCharsTest {

    private val tokenizer = Tokenizer()

    @Test
    fun `Invalid Unicode character U+0001`() {
        val input = "\u0001"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0001')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0002`() {
        val input = "\u0002"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0002')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0003`() {
        val input = "\u0003"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0003')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0004`() {
        val input = "\u0004"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0004')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0005`() {
        val input = "\u0005"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0005')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0006`() {
        val input = "\u0006"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0006')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0007`() {
        val input = "\u0007"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0007')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0008`() {
        val input = "\u0008"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0008')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+000B`() {
        val input = "\u000B"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u000B')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+000E`() {
        val input = "\u000E"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u000E')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+000F`() {
        val input = "\u000F"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u000F')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0010`() {
        val input = "\u0010"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0010')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0011`() {
        val input = "\u0011"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0011')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0012`() {
        val input = "\u0012"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0012')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0013`() {
        val input = "\u0013"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0013')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0014`() {
        val input = "\u0014"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0014')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0015`() {
        val input = "\u0015"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0015')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0016`() {
        val input = "\u0016"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0016')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0017`() {
        val input = "\u0017"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0017')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0018`() {
        val input = "\u0018"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0018')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+0019`() {
        val input = "\u0019"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0019')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+001A`() {
        val input = "\u001A"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u001A')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+001B`() {
        val input = "\u001B"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u001B')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+001C`() {
        val input = "\u001C"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u001C')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+001D`() {
        val input = "\u001D"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u001D')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+001E`() {
        val input = "\u001E"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u001E')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+001F`() {
        val input = "\u001F"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u001F')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+007F`() {
        val input = "\u007F"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u007F')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDD0`() {
        val input = "\uFDD0"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDD0')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDD1`() {
        val input = "\uFDD1"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDD1')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDD2`() {
        val input = "\uFDD2"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDD2')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDD3`() {
        val input = "\uFDD3"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDD3')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDD4`() {
        val input = "\uFDD4"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDD4')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDD5`() {
        val input = "\uFDD5"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDD5')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDD6`() {
        val input = "\uFDD6"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDD6')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDD7`() {
        val input = "\uFDD7"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDD7')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDD8`() {
        val input = "\uFDD8"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDD8')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDD9`() {
        val input = "\uFDD9"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDD9')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDDA`() {
        val input = "\uFDDA"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDDA')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDDB`() {
        val input = "\uFDDB"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDDB')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDDC`() {
        val input = "\uFDDC"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDDC')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDDD`() {
        val input = "\uFDDD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDDD')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDDE`() {
        val input = "\uFDDE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDDE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDDF`() {
        val input = "\uFDDF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDDF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDE0`() {
        val input = "\uFDE0"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDE0')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDE1`() {
        val input = "\uFDE1"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDE1')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDE2`() {
        val input = "\uFDE2"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDE2')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDE3`() {
        val input = "\uFDE3"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDE3')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDE4`() {
        val input = "\uFDE4"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDE4')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDE5`() {
        val input = "\uFDE5"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDE5')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDE6`() {
        val input = "\uFDE6"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDE6')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDE7`() {
        val input = "\uFDE7"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDE7')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDE8`() {
        val input = "\uFDE8"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDE8')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDE9`() {
        val input = "\uFDE9"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDE9')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDEA`() {
        val input = "\uFDEA"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDEA')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDEB`() {
        val input = "\uFDEB"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDEB')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDEC`() {
        val input = "\uFDEC"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDEC')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDED`() {
        val input = "\uFDED"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDED')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDEE`() {
        val input = "\uFDEE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDEE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FDEF`() {
        val input = "\uFDEF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDEF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FFFE`() {
        val input = "\uFFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FFFF`() {
        val input = "\uFFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+1FFFE`() {
        val input = "\uD83F\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD83F'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+1FFFF`() {
        val input = "\uD83F\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD83F'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+2FFFE`() {
        val input = "\uD87F\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD87F'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+2FFFF`() {
        val input = "\uD87F\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD87F'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+3FFFE`() {
        val input = "\uD8BF\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD8BF'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+3FFFF`() {
        val input = "\uD8BF\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD8BF'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+4FFFE`() {
        val input = "\uD8FF\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD8FF'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+4FFFF`() {
        val input = "\uD8FF\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD8FF'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+5FFFE`() {
        val input = "\uD93F\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD93F'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+5FFFF`() {
        val input = "\uD93F\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD93F'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+6FFFE`() {
        val input = "\uD97F\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD97F'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+6FFFF`() {
        val input = "\uD97F\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD97F'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+7FFFE`() {
        val input = "\uD9BF\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD9BF'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+7FFFF`() {
        val input = "\uD9BF\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD9BF'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+8FFFE`() {
        val input = "\uD9FF\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD9FF'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+8FFFF`() {
        val input = "\uD9FF\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD9FF'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+9FFFE`() {
        val input = "\uDA3F\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDA3F'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+9FFFF`() {
        val input = "\uDA3F\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDA3F'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+AFFFE`() {
        val input = "\uDA7F\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDA7F'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+AFFFF`() {
        val input = "\uDA7F\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDA7F'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+BFFFE`() {
        val input = "\uDABF\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDABF'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+BFFFF`() {
        val input = "\uDABF\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDABF'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+CFFFE`() {
        val input = "\uDAFF\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDAFF'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+CFFFF`() {
        val input = "\uDAFF\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDAFF'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+DFFFE`() {
        val input = "\uDB3F\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDB3F'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+DFFFF`() {
        val input = "\uDB3F\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDB3F'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+EFFFE`() {
        val input = "\uDB7F\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDB7F'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+EFFFF`() {
        val input = "\uDB7F\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDB7F'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FFFFE`() {
        val input = "\uDBBF\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDBBF'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+FFFFF`() {
        val input = "\uDBBF\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDBBF'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+10FFFE`() {
        val input = "\uDBFF\uDFFE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDBFF'),
                Token.Character('\uDFFE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Invalid Unicode character U+10FFFF`() {
        val input = "\uDBFF\uDFFF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDBFF'),
                Token.Character('\uDFFF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0009`() {
        val input = "\u0009"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0009')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+000A`() {
        val input = "\u000A"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u000A')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0020`() {
        val input = "\u0020"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0020')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0021`() {
        val input = "\u0021"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0021')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0022`() {
        val input = "\u0022"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0022')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0023`() {
        val input = "\u0023"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0023')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0024`() {
        val input = "\u0024"
        tokenizer.tokenize(input)
        assertEquals(listOf<Token>(
            Token.Character('\u0024')
        ), tokenizer.emittedTokens)
    }

    @Test
    fun `Valid Unicode character U+0025`() {
        val input = "\u0025"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0025')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0026`() {
        val input = "\u0026"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0026')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0027`() {
        val input = "\u0027"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0027')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0028`() {
        val input = "\u0028"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0028')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0029`() {
        val input = "\u0029"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0029')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+002A`() {
        val input = "\u002A"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u002A')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+002B`() {
        val input = "\u002B"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u002B')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+002C`() {
        val input = "\u002C"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u002C')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+002D`() {
        val input = "\u002D"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u002D')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+002E`() {
        val input = "\u002E"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u002E')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+002F`() {
        val input = "\u002F"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u002F')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0030`() {
        val input = "\u0030"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0030')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0031`() {
        val input = "\u0031"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0031')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0032`() {
        val input = "\u0032"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0032')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0033`() {
        val input = "\u0033"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0033')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0034`() {
        val input = "\u0034"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0034')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0035`() {
        val input = "\u0035"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0035')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0036`() {
        val input = "\u0036"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0036')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0037`() {
        val input = "\u0037"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0037')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0038`() {
        val input = "\u0038"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0038')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0039`() {
        val input = "\u0039"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0039')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+003A`() {
        val input = "\u003A"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u003A')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+003B`() {
        val input = "\u003B"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u003B')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+003D`() {
        val input = "\u003D"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u003D')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+003E`() {
        val input = "\u003E"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u003E')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+003F`() {
        val input = "\u003F"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u003F')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0040`() {
        val input = "\u0040"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0040')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0041`() {
        val input = "\u0041"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0041')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0042`() {
        val input = "\u0042"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0042')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0043`() {
        val input = "\u0043"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0043')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0044`() {
        val input = "\u0044"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0044')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0045`() {
        val input = "\u0045"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0045')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0046`() {
        val input = "\u0046"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0046')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0047`() {
        val input = "\u0047"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0047')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0048`() {
        val input = "\u0048"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0048')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0049`() {
        val input = "\u0049"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0049')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+004A`() {
        val input = "\u004A"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u004A')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+004B`() {
        val input = "\u004B"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u004B')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+004C`() {
        val input = "\u004C"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u004C')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+004D`() {
        val input = "\u004D"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u004D')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+004E`() {
        val input = "\u004E"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u004E')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+004F`() {
        val input = "\u004F"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u004F')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0050`() {
        val input = "\u0050"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0050')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0051`() {
        val input = "\u0051"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0051')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0052`() {
        val input = "\u0052"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0052')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0053`() {
        val input = "\u0053"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0053')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0054`() {
        val input = "\u0054"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0054')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0055`() {
        val input = "\u0055"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0055')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0056`() {
        val input = "\u0056"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0056')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0057`() {
        val input = "\u0057"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0057')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0058`() {
        val input = "\u0058"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0058')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0059`() {
        val input = "\u0059"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0059')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+005A`() {
        val input = "\u005A"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u005A')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+005B`() {
        val input = "\u005B"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u005B')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+005C`() {
        val input = "\u005C"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u005C')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+005D`() {
        val input = "\u005D"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u005D')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+005E`() {
        val input = "\u005E"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u005E')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+005F`() {
        val input = "\u005F"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u005F')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0060`() {
        val input = "\u0060"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0060')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0061`() {
        val input = "\u0061"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0061')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0062`() {
        val input = "\u0062"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0062')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0063`() {
        val input = "\u0063"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0063')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0064`() {
        val input = "\u0064"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0064')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0065`() {
        val input = "\u0065"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0065')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0066`() {
        val input = "\u0066"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0066')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0067`() {
        val input = "\u0067"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0067')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0068`() {
        val input = "\u0068"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0068')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0069`() {
        val input = "\u0069"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0069')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+006A`() {
        val input = "\u006A"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u006A')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+006B`() {
        val input = "\u006B"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u006B')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+006C`() {
        val input = "\u006C"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u006C')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+006D`() {
        val input = "\u006D"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u006D')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+006E`() {
        val input = "\u006E"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u006E')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+006F`() {
        val input = "\u006F"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u006F')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0070`() {
        val input = "\u0070"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0070')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0071`() {
        val input = "\u0071"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0071')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0072`() {
        val input = "\u0072"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0072')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0073`() {
        val input = "\u0073"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0073')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0074`() {
        val input = "\u0074"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0074')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0075`() {
        val input = "\u0075"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0075')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0076`() {
        val input = "\u0076"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0076')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0077`() {
        val input = "\u0077"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0077')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0078`() {
        val input = "\u0078"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0078')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+0079`() {
        val input = "\u0079"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u0079')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+007A`() {
        val input = "\u007A"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u007A')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+007B`() {
        val input = "\u007B"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u007B')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+007C`() {
        val input = "\u007C"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u007C')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+007D`() {
        val input = "\u007D"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u007D')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+007E`() {
        val input = "\u007E"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u007E')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00A0`() {
        val input = "\u00A0"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00A0')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00A1`() {
        val input = "\u00A1"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00A1')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00A2`() {
        val input = "\u00A2"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00A2')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00A3`() {
        val input = "\u00A3"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00A3')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00A4`() {
        val input = "\u00A4"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00A4')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00A5`() {
        val input = "\u00A5"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00A5')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00A6`() {
        val input = "\u00A6"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00A6')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00A7`() {
        val input = "\u00A7"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00A7')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00A8`() {
        val input = "\u00A8"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00A8')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00A9`() {
        val input = "\u00A9"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00A9')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00AA`() {
        val input = "\u00AA"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00AA')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00AB`() {
        val input = "\u00AB"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00AB')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00AC`() {
        val input = "\u00AC"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00AC')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00AD`() {
        val input = "\u00AD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00AD')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00AE`() {
        val input = "\u00AE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00AE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00AF`() {
        val input = "\u00AF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00AF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00B0`() {
        val input = "\u00B0"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00B0')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00B1`() {
        val input = "\u00B1"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00B1')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00B2`() {
        val input = "\u00B2"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00B2')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00B3`() {
        val input = "\u00B3"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00B3')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00B4`() {
        val input = "\u00B4"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00B4')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00B5`() {
        val input = "\u00B5"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00B5')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00B6`() {
        val input = "\u00B6"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00B6')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00B7`() {
        val input = "\u00B7"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00B7')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00B8`() {
        val input = "\u00B8"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00B8')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00B9`() {
        val input = "\u00B9"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00B9')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00BA`() {
        val input = "\u00BA"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00BA')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00BB`() {
        val input = "\u00BB"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00BB')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00BC`() {
        val input = "\u00BC"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00BC')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00BD`() {
        val input = "\u00BD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00BD')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00BE`() {
        val input = "\u00BE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00BE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00BF`() {
        val input = "\u00BF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00BF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00C0`() {
        val input = "\u00C0"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00C0')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00C1`() {
        val input = "\u00C1"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00C1')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00C2`() {
        val input = "\u00C2"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00C2')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00C3`() {
        val input = "\u00C3"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00C3')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00C4`() {
        val input = "\u00C4"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00C4')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00C5`() {
        val input = "\u00C5"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00C5')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00C6`() {
        val input = "\u00C6"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00C6')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00C7`() {
        val input = "\u00C7"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00C7')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00C8`() {
        val input = "\u00C8"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00C8')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00C9`() {
        val input = "\u00C9"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00C9')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00CA`() {
        val input = "\u00CA"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00CA')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00CB`() {
        val input = "\u00CB"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00CB')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00CC`() {
        val input = "\u00CC"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00CC')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00CD`() {
        val input = "\u00CD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00CD')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00CE`() {
        val input = "\u00CE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00CE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00CF`() {
        val input = "\u00CF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00CF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00D0`() {
        val input = "\u00D0"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00D0')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00D1`() {
        val input = "\u00D1"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00D1')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00D2`() {
        val input = "\u00D2"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00D2')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00D3`() {
        val input = "\u00D3"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00D3')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00D4`() {
        val input = "\u00D4"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00D4')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00D5`() {
        val input = "\u00D5"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00D5')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00D6`() {
        val input = "\u00D6"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00D6')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00D7`() {
        val input = "\u00D7"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00D7')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00D8`() {
        val input = "\u00D8"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00D8')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00D9`() {
        val input = "\u00D9"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00D9')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00DA`() {
        val input = "\u00DA"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00DA')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00DB`() {
        val input = "\u00DB"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00DB')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00DC`() {
        val input = "\u00DC"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00DC')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00DD`() {
        val input = "\u00DD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00DD')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00DE`() {
        val input = "\u00DE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00DE')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00DF`() {
        val input = "\u00DF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00DF')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00E0`() {
        val input = "\u00E0"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00E0')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00E1`() {
        val input = "\u00E1"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00E1')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00E2`() {
        val input = "\u00E2"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00E2')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00E3`() {
        val input = "\u00E3"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00E3')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00E4`() {
        val input = "\u00E4"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00E4')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00E5`() {
        val input = "\u00E5"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00E5')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00E6`() {
        val input = "\u00E6"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00E6')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00E7`() {
        val input = "\u00E7"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00E7')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00E8`() {
        val input = "\u00E8"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00E8')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00E9`() {
        val input = "\u00E9"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00E9')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00EA`() {
        val input = "\u00EA"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00EA')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00EB`() {
        val input = "\u00EB"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00EB')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00EC`() {
        val input = "\u00EC"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00EC')
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00ED`() {
        val input = "\u00ED"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00ED'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00EE`() {
        val input = "\u00EE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00EE'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00EF`() {
        val input = "\u00EF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00EF'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00F0`() {
        val input = "\u00F0"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00F0'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00F1`() {
        val input = "\u00F1"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00F1'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00F2`() {
        val input = "\u00F2"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00F2'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00F3`() {
        val input = "\u00F3"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00F3'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00F4`() {
        val input = "\u00F4"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00F4'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00F5`() {
        val input = "\u00F5"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00F5'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00F6`() {
        val input = "\u00F6"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00F6'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00F7`() {
        val input = "\u00F7"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00F7'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00F8`() {
        val input = "\u00F8"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00F8'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00F9`() {
        val input = "\u00F9"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00F9'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00FA`() {
        val input = "\u00FA"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00FA'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00FB`() {
        val input = "\u00FB"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00FB'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00FC`() {
        val input = "\u00FC"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00FC'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00FD`() {
        val input = "\u00FD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00FD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00FE`() {
        val input = "\u00FE"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00FE'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+00FF`() {
        val input = "\u00FF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\u00FF'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+D7FF`() {
        val input = "\uD7FF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD7FF'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+E000`() {
        val input = "\uE000"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uE000'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+FDCF`() {
        val input = "\uFDCF"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDCF'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+FDF0`() {
        val input = "\uFDF0"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDF0'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+FFFD`() {
        val input = "\uFFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uFFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+10000`() {
        val input = "\uD800\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD800'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+1FFFD`() {
        val input = "\uD83F\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD83F'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+20000`() {
        val input = "\uD840\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD840'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+2FFFD`() {
        val input = "\uD87F\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD87F'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+30000`() {
        val input = "\uD880\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD880'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+3FFFD`() {
        val input = "\uD8BF\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD8BF'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+40000`() {
        val input = "\uD8C0\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD8C0'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+4FFFD`() {
        val input = "\uD8FF\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD8FF'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+50000`() {
        val input = "\uD900\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD900'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+5FFFD`() {
        val input = "\uD93F\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD93F'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+60000`() {
        val input = "\uD940\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD940'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+6FFFD`() {
        val input = "\uD97F\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD97F'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+70000`() {
        val input = "\uD980\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD980'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+7FFFD`() {
        val input = "\uD9BF\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD9BF'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+80000`() {
        val input = "\uD9C0\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD9C0'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+8FFFD`() {
        val input = "\uD9FF\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uD9FF'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+90000`() {
        val input = "\uDA00\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDA00'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+9FFFD`() {
        val input = "\uDA3F\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDA3F'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+A0000`() {
        val input = "\uDA40\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDA40'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+AFFFD`() {
        val input = "\uDA7F\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDA7F'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+B0000`() {
        val input = "\uDA80\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDA80'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+BFFFD`() {
        val input = "\uDABF\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDABF'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+C0000`() {
        val input = "\uDAC0\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDAC0'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+CFFFD`() {
        val input = "\uDAFF\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDAFF'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+D0000`() {
        val input = "\uDB00\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDB00'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+DFFFD`() {
        val input = "\uDB3F\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDB3F'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+E0000`() {
        val input = "\uDB40\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDB40'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+EFFFD`() {
        val input = "\uDB7F\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDB7F'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+F0000`() {
        val input = "\uDB80\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDB80'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+FFFFD`() {
        val input = "\uDBBF\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDBBF'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+100000`() {
        val input = "\uDBC0\uDC00"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDBC0'),
                Token.Character('\uDC00'),
            ), tokenizer.emittedTokens
        )
    }

    @Test
    fun `Valid Unicode character U+10FFFD`() {
        val input = "\uDBFF\uDFFD"
        tokenizer.tokenize(input)
        assertEquals(
            listOf<Token>(
                Token.Character('\uDBFF'),
                Token.Character('\uDFFD'),
            ), tokenizer.emittedTokens
        )
    }
}