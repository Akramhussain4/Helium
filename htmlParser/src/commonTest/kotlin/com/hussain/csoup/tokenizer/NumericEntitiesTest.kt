package com.hussain.csoup.tokenizer

import com.hussain.csoup.tokenization.Token
import com.hussain.csoup.tokenization.Tokenizer
import kotlin.test.Test
import kotlin.test.assertEquals

class NumericEntitiesTest {

    private val tokenizer = Tokenizer()

    @Test
    fun `Invalid unterminated numeric entity character overflow before EOF 1`() {
        val input = "&#11111111111"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\uFFFD')), actual)
    }

    @Test
    fun `Invalid unterminated numeric entity character overflow before EOF 2`() {
        val input = "&#1111111111"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\uFFFD')), actual)
    }

    @Test
    fun `Invalid unterminated numeric entity character overflow before EOF 3`() {
        val input = "&#111111111111"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\uFFFD')), actual)
    }

    @Test
    fun `Invalid unterminated numeric entity character overflow 1`() {
        val input = "&#11111111111x"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ᧇ'),
                Token.Character('x'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid unterminated numeric entity character overflow 2`() {
        val input = "&#1111111111x"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('�'),
                Token.Character('x'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid unterminated numeric entity character overflow 3`() {
        val input = "&#111111111111x"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ǉ'),
                Token.Character('x'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character overflow 1`() {
        val input = "&#11111111111;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ᧇ'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character overflow 2`() {
        val input = "&#1111111111;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\uFFFD'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character overflow 3`() {
        val input = "&#111111111111;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ǉ'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+0000`() {
        val input = "&#x0000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\uFFFD')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0001`() {
        val input = "&#x0001;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0001')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0002`() {
        val input = "&#x0002;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0002')), actual)
    }


    @Test
    fun `Invalid numeric entity character U+0003`() {
        val input = "&#x0003;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0003')), actual)
    }


    @Test
    fun `Invalid numeric entity character U+0004`() {
        val input = "&#x0004;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0004')), actual)
    }


    @Test
    fun `Invalid numeric entity character U+0005`() {
        val input = "&#x0005;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0005')), actual)
    }


    @Test
    fun `Invalid numeric entity character U+0006`() {
        val input = "&#x0006;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0006')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0007`() {
        val input = "&#x0007;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0007')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0008`() {
        val input = "&#x0008;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0008')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+000B`() {
        val input = "&#x000b;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u000b')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+000E`() {
        val input = "&#x000e;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u000e')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+000F`() {
        val input = "&#x000f;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u000f')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0010`() {
        val input = "&#x0010;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0010')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0011`() {
        val input = "&#x0011;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0011')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0012`() {
        val input = "&#x0012;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0012')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0013`() {
        val input = "&#x0013;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0013')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0014`() {
        val input = "&#x0014;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0014')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0015`() {
        val input = "&#x0015;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0015')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0016`() {
        val input = "&#x0016;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0016')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0017`() {
        val input = "&#x0017;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0017')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0018`() {
        val input = "&#x0018;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0018')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+0019`() {
        val input = "&#x0019;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u0019')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+001A`() {
        val input = "&#x001a;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u001a')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+001B`() {
        val input = "&#x001b;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u001b')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+001C`() {
        val input = "&#x001c;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u001c')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+001D`() {
        val input = "&#x001d;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u001d')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+001E`() {
        val input = "&#x001e;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u001e')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+001F`() {
        val input = "&#x001f;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u001f')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+007F`() {
        val input = "&#x007f;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\u007f')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+D800`() {
        val input = "&#xd800;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\uFFFD')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+DFFF`() {
        val input = "&#xdfff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\uFFFD')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDD0`() {
        val input = "&#xfdd0;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdd0')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDD1`() {
        val input = "&#xfdd1;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdd1')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDD2`() {
        val input = "&#xfdd2;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdd2')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDD3`() {
        val input = "&#xfdd3;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdd3')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDD4`() {
        val input = "&#xfdd4;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdd4')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDD5`() {
        val input = "&#xfdd5;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdd5')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDD6`() {
        val input = "&#xfdd6;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdd6')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDD7`() {
        val input = "&#xfdd7;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdd7')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDD8`() {
        val input = "&#xfdd8;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdd8')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDD9`() {
        val input = "&#xfdd9;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdd9')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDDA`() {
        val input = "&#xfdda;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdda')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDDB`() {
        val input = "&#xfddb;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufddb')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDDC`() {
        val input = "&#xfddc;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufddc')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDDD`() {
        val input = "&#xfddd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufddd')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDDE`() {
        val input = "&#xfdde;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdde')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDDF`() {
        val input = "&#xfddf;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufddf')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDE0`() {
        val input = "&#xfde0;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufde0')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDE1`() {
        val input = "&#xfde1;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufde1')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDE2`() {
        val input = "&#xfde2;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufde2')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDE3`() {
        val input = "&#xfde3;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufde3')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDE4`() {
        val input = "&#xfde4;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufde4')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDE5`() {
        val input = "&#xfde5;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufde5')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDE6`() {
        val input = "&#xfde6;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufde6')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDE7`() {
        val input = "&#xfde7;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufde7')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDE8`() {
        val input = "&#xfde8;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufde8')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDE9`() {
        val input = "&#xfde9;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufde9')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDEA`() {
        val input = "&#xfdea;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdea')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDEB`() {
        val input = "&#xfdeb;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdeb')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDEC`() {
        val input = "&#xfdec;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdec')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDED`() {
        val input = "&#xfded;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufded')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDEE`() {
        val input = "&#xfdee;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdee')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FDEF`() {
        val input = "&#xfdef;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufdef')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FFFE`() {
        val input = "&#xfffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\ufffe')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+FFFF`() {
        val input = "&#xffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\uffff')), actual)
    }

    @Test
    fun `Invalid numeric entity character U+1FFFE`() {
        val input = "&#x1fffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('8'),
                Token.Character('3'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+1FFFF`() {
        val input = "&#x1ffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('8'),
                Token.Character('3'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+2FFFE`() {
        val input = "&#x2fffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('8'),
                Token.Character('7'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+2FFFF`() {
        val input = "&#x2ffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('8'),
                Token.Character('7'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+3FFFE`() {
        val input = "&#x3fffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('8'),
                Token.Character('B'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+3FFFF`() {
        val input = "&#x3ffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('8'),
                Token.Character('B'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+4FFFE`() {
        val input = "&#x4fffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('8'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+4FFFF`() {
        val input = "&#x4ffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('8'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+5FFFE`() {
        val input = "&#x5fffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('9'),
                Token.Character('3'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+5FFFF`() {
        val input = "&#x5ffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('9'),
                Token.Character('3'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+6FFFE`() {
        val input = "&#x6fffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('9'),
                Token.Character('7'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+6FFFF`() {
        val input = "&#x6ffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('9'),
                Token.Character('7'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+7FFFE`() {
        val input = "&#x7fffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('9'),
                Token.Character('B'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+7FFFF`() {
        val input = "&#x7ffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('9'),
                Token.Character('B'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+8FFFE`() {
        val input = "&#x8fffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('9'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+8FFFF`() {
        val input = "&#x8ffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('9'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+9FFFE`() {
        val input = "&#x9fffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('A'),
                Token.Character('3'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+9FFFF`() {
        val input = "&#x9ffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('A'),
                Token.Character('3'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+AFFFE`() {
        val input = "&#xafffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('A'),
                Token.Character('7'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+AFFFF`() {
        val input = "&#xaffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('A'),
                Token.Character('7'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+BFFFE`() {
        val input = "&#xbfffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('A'),
                Token.Character('B'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+BFFFF`() {
        val input = "&#xbffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('A'),
                Token.Character('B'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+CFFFE`() {
        val input = "&#xcfffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('A'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+CFFFF`() {
        val input = "&#xcffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('A'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+DFFFE`() {
        val input = "&#xdfffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('B'),
                Token.Character('3'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+DFFFF`() {
        val input = "&#xdffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('B'),
                Token.Character('3'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+EFFFE`() {
        val input = "&#xefffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('B'),
                Token.Character('7'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+EFFFF`() {
        val input = "&#xeffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('B'),
                Token.Character('7'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+FFFFE`() {
        val input = "&#xffffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('B'),
                Token.Character('B'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+FFFFF`() {
        val input = "&#xfffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('B'),
                Token.Character('B'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+10FFFE`() {
        val input = "&#x10fffe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('B'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Invalid numeric entity character U+10FFFF`() {
        val input = "&#x10ffff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('B'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0009`() {
        val input = "&#x0009;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('	')), actual)
    }

    @Test
    fun `Valid numeric entity character U+000A`() {
        val input = "&#x000a;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('\n')), actual)
    }

    @Test
    fun `Valid numeric entity character U+0020`() {
        val input = "&#x0020;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character(' ')), actual)
    }

    @Test
    fun `Valid numeric entity character U+0021`() {
        val input = "&#x0021;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('!')), actual)
    }

    @Test
    fun `Valid numeric entity character U+0022`() {
        val input = "&#x0022;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(listOf<Token>(Token.Character('"')), actual)
    }

    @Test
    fun `Valid numeric entity character U+0023`() {
        val input = "&#x0023;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('#'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0024`() {
        val input = "&#x0024;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('$'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0025`() {
        val input = "&#x0025;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('%'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0026`() {
        val input = "&#x0026;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('&'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0027`() {
        val input = "&#x0027;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\''),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0028`() {
        val input = "&#x0028;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('('),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0029`() {
        val input = "&#x0029;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character(')'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+002A`() {
        val input = "&#x002a;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('*'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+002B`() {
        val input = "&#x002b;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('+'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+002C`() {
        val input = "&#x002c;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character(','),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+002D`() {
        val input = "&#x002d;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('-'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+002E`() {
        val input = "&#x002e;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('.'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+002F`() {
        val input = "&#x002f;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('/'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0030`() {
        val input = "&#x0030;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('0'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0031`() {
        val input = "&#x0031;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('1'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0032`() {
        val input = "&#x0032;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('2'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0033`() {
        val input = "&#x0033;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('3'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0034`() {
        val input = "&#x0034;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('4'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0035`() {
        val input = "&#x0035;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('5'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0036`() {
        val input = "&#x0036;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('6'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0037`() {
        val input = "&#x0037;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('7'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0038`() {
        val input = "&#x0038;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('8'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0039`() {
        val input = "&#x0039;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('9'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+003A`() {
        val input = "&#x003a;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character(':'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+003B`() {
        val input = "&#x003b;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character(';'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+003C`() {
        val input = "&#x003c;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('<'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+003D`() {
        val input = "&#x003d;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('='),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+003E`() {
        val input = "&#x003e;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('>'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+003F`() {
        val input = "&#x003f;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('?'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0040`() {
        val input = "&#x0040;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('@'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0041`() {
        val input = "&#x0041;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('A'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0042`() {
        val input = "&#x0042;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('B'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0043`() {
        val input = "&#x0043;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('C'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0044`() {
        val input = "&#x0044;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('D'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0045`() {
        val input = "&#x0045;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('E'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0046`() {
        val input = "&#x0046;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('F'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0047`() {
        val input = "&#x0047;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('G'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0048`() {
        val input = "&#x0048;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('H'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0049`() {
        val input = "&#x0049;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('I'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+004A`() {
        val input = "&#x004a;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('J'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+004B`() {
        val input = "&#x004b;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('K'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+004C`() {
        val input = "&#x004c;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('L'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+004D`() {
        val input = "&#x004d;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('M'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+004E`() {
        val input = "&#x004e;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('N'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+004F`() {
        val input = "&#x004f;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('O'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0050`() {
        val input = "&#x0050;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('P'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0051`() {
        val input = "&#x0051;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Q'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0052`() {
        val input = "&#x0052;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('R'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0053`() {
        val input = "&#x0053;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('S'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0054`() {
        val input = "&#x0054;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('T'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0055`() {
        val input = "&#x0055;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('U'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0056`() {
        val input = "&#x0056;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('V'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0057`() {
        val input = "&#x0057;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('W'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0058`() {
        val input = "&#x0058;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('X'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0059`() {
        val input = "&#x0059;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Y'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+005A`() {
        val input = "&#x005a;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Z'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+005B`() {
        val input = "&#x005b;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('['),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+005C`() {
        val input = "&#x005c;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+005D`() {
        val input = "&#x005d;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character(']'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+005E`() {
        val input = "&#x005e;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('^'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+005F`() {
        val input = "&#x005f;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('_'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0060`() {
        val input = "&#x0060;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('`'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0061`() {
        val input = "&#x0061;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('a'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0062`() {
        val input = "&#x0062;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('b'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0063`() {
        val input = "&#x0063;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('c'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0064`() {
        val input = "&#x0064;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('d')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0065`() {
        val input = "&#x0065;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('e')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0066`() {
        val input = "&#x0066;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('f')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0067`() {
        val input = "&#x0067;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('g')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0068`() {
        val input = "&#x0068;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('h')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0069`() {
        val input = "&#x0069;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('i')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+006A`() {
        val input = "&#x006a;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('j')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+006B`() {
        val input = "&#x006b;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('k')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+006C`() {
        val input = "&#x006c;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('l')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+006D`() {
        val input = "&#x006d;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('m')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+006E`() {
        val input = "&#x006e;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('n')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+006F`() {
        val input = "&#x006f;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('o')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0070`() {
        val input = "&#x0070;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('p')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0071`() {
        val input = "&#x0071;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('q')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0072`() {
        val input = "&#x0072;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('r')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0073`() {
        val input = "&#x0073;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('s')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0074`() {
        val input = "&#x0074;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('t')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0075`() {
        val input = "&#x0075;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('u')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0076`() {
        val input = "&#x0076;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('v')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0077`() {
        val input = "&#x0077;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('w')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0078`() {
        val input = "&#x0078;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('x')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+0079`() {
        val input = "&#x0079;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('y')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+007A`() {
        val input = "&#x007a;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('z')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+007B`() {
        val input = "&#x007b;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('{')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+007C`() {
        val input = "&#x007c;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('|')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+007D`() {
        val input = "&#x007d;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('}')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+007E`() {
        val input = "&#x007e;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('~')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00A0`() {
        val input = "&#x00a0;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character(' ')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00A1`() {
        val input = "&#x00a1;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¡')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00A2`() {
        val input = "&#x00a2;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¢')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00A3`() {
        val input = "&#x00a3;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('£')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00A4`() {
        val input = "&#x00a4;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¤')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00A5`() {
        val input = "&#x00a5;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¥')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00A6`() {
        val input = "&#x00a6;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¦')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00A7`() {
        val input = "&#x00a7;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('§')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00A8`() {
        val input = "&#x00a8;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¨')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00A9`() {
        val input = "&#x00a9;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('©')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00AA`() {
        val input = "&#x00aa;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ª')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00AB`() {
        val input = "&#x00ab;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('«')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00AC`() {
        val input = "&#x00ac;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¬')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00AD`() {
        val input = "&#x00ad;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('­')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00AE`() {
        val input = "&#x00ae;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('®')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00AF`() {
        val input = "&#x00af;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¯')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00B0`() {
        val input = "&#x00b0;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('°')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00B1`() {
        val input = "&#x00b1;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('±')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00B2`() {
        val input = "&#x00b2;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('²')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00B3`() {
        val input = "&#x00b3;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('³')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00B4`() {
        val input = "&#x00b4;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('´')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00B5`() {
        val input = "&#x00b5;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('µ')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00B6`() {
        val input = "&#x00b6;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¶')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00B7`() {
        val input = "&#x00b7;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('·')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00B8`() {
        val input = "&#x00b8;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¸')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00B9`() {
        val input = "&#x00b9;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¹')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00BA`() {
        val input = "&#x00ba;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('º')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00BB`() {
        val input = "&#x00bb;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('»')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00BC`() {
        val input = "&#x00bc;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¼')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00BD`() {
        val input = "&#x00bd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('½')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00BE`() {
        val input = "&#x00be;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¾')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00BF`() {
        val input = "&#x00bf;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('¿')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00C0`() {
        val input = "&#x00c0;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('À')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00C1`() {
        val input = "&#x00c1;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Á')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00C2`() {
        val input = "&#x00c2;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Â')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00C3`() {
        val input = "&#x00c3;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ã')
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00C4`() {
        val input = "&#x00c4;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ä'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00C5`() {
        val input = "&#x00c5;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Å'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00C6`() {
        val input = "&#x00c6;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Æ'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00C7`() {
        val input = "&#x00c7;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ç'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00C8`() {
        val input = "&#x00c8;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('È'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00C9`() {
        val input = "&#x00c9;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('É'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00CA`() {
        val input = "&#x00ca;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ê'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00CB`() {
        val input = "&#x00cb;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ë'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00CC`() {
        val input = "&#x00cc;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ì'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00CD`() {
        val input = "&#x00cd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Í'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00CE`() {
        val input = "&#x00ce;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Î'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00CF`() {
        val input = "&#x00cf;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ï'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00D0`() {
        val input = "&#x00d0;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ð'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00D1`() {
        val input = "&#x00d1;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ñ'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00D2`() {
        val input = "&#x00d2;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ò'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00D3`() {
        val input = "&#x00d3;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ó'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00D4`() {
        val input = "&#x00d4;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ô'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00D5`() {
        val input = "&#x00d5;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Õ'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00D6`() {
        val input = "&#x00d6;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ö'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00D7`() {
        val input = "&#x00d7;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('×'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00D8`() {
        val input = "&#x00d8;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ø'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00D9`() {
        val input = "&#x00d9;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ù'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00DA`() {
        val input = "&#x00da;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ú'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00DB`() {
        val input = "&#x00db;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Û'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00DC`() {
        val input = "&#x00dc;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ü'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00DD`() {
        val input = "&#x00dd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Ý'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00DE`() {
        val input = "&#x00de;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('Þ'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00DF`() {
        val input = "&#x00df;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ß'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00E0`() {
        val input = "&#x00e0;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('à'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00E1`() {
        val input = "&#x00e1;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('á'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00E2`() {
        val input = "&#x00e2;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('â'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00E3`() {
        val input = "&#x00e3;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ã'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00E4`() {
        val input = "&#x00e4;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ä'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00E5`() {
        val input = "&#x00e5;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('å'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00E6`() {
        val input = "&#x00e6;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('æ'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00E7`() {
        val input = "&#x00e7;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ç'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00E8`() {
        val input = "&#x00e8;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('è'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00E9`() {
        val input = "&#x00e9;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('é'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00EA`() {
        val input = "&#x00ea;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ê'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00EB`() {
        val input = "&#x00eb;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ë'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00EC`() {
        val input = "&#x00ec;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ì'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00ED`() {
        val input = "&#x00ed;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('í'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00EE`() {
        val input = "&#x00ee;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('î'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00EF`() {
        val input = "&#x00ef;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ï'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00F0`() {
        val input = "&#x00f0;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ð'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00F1`() {
        val input = "&#x00f1;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ñ'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00F2`() {
        val input = "&#x00f2;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ò'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00F3`() {
        val input = "&#x00f3;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ó'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00F4`() {
        val input = "&#x00f4;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ô'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00F5`() {
        val input = "&#x00f5;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('õ'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00F6`() {
        val input = "&#x00f6;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ö'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00F7`() {
        val input = "&#x00f7;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('÷'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00F8`() {
        val input = "&#x00f8;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ø'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00F9`() {
        val input = "&#x00f9;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ù'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00FA`() {
        val input = "&#x00fa;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ú'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00FB`() {
        val input = "&#x00fb;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('û'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00FC`() {
        val input = "&#x00fc;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ü'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00FD`() {
        val input = "&#x00fd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ý'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00FE`() {
        val input = "&#x00fe;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('þ'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+00FF`() {
        val input = "&#x00ff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ÿ'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+D7FF`() {
        val input = "&#xd7ff;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\uD7FF'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+E000`() {
        val input = "&#xe000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character(''),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+FDCF`() {
        val input = "&#xfdcf;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\uFDCF'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+FDF0`() {
        val input = "&#xfdf0;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('ﷰ'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+FFFD`() {
        val input = "&#xfffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\uFFFD'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+10000`() {
        val input = "&#x10000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+1FFFD`() {
        val input = "&#x1fffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+20000`() {
        val input = "&#x20000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+2FFFD`() {
        val input = "&#x2fffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+30000`() {
        val input = "&#x30000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+3FFFD`() {
        val input = "&#x3fffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+40000`() {
        val input = "&#x40000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+4FFFD`() {
        val input = "&#x4fffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+50000`() {
        val input = "&#x50000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+5FFFD`() {
        val input = "&#x5fffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+60000`() {
        val input = "&#x60000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+6FFFD`() {
        val input = "&#x6fffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+70000`() {
        val input = "&#x70000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+7FFFD`() {
        val input = "&#x7fffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+80000`() {
        val input = "&#x80000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+8FFFD`() {
        val input = "&#x8fffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+90000`() {
        val input = "&#x90000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+9FFFD`() {
        val input = "&#x9fffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+A0000`() {
        val input = "&#xa0000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+AFFFD`() {
        val input = "&#xafffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+B0000`() {
        val input = "&#xb0000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+BFFFD`() {
        val input = "&#xbfffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+C0000`() {
        val input = "&#xc0000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+CFFFD`() {
        val input = "&#xcfffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+D0000`() {
        val input = "&#xd0000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+DFFFD`() {
        val input = "&#xdfffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+E0000`() {
        val input = "&#xe0000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+EFFFD`() {
        val input = "&#xefffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+F0000`() {
        val input = "&#xf0000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+FFFFD`() {
        val input = "&#xffffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\uFFFD'),
            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+100000`() {
        val input = "&#x100000;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(

            ),
            actual
        )
    }

    @Test
    fun `Valid numeric entity character U+10FFFD`() {
        val input = "&#x10fffd;"
        tokenizer.tokenize(input)
        val actual = tokenizer.emittedTokens
        assertEquals(
            listOf<Token>(
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('B'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('\\'),
                Token.Character('u'),
                Token.Character('D'),
                Token.Character('F'),
                Token.Character('F'),
                Token.Character('D'),
            ),
            actual
        )
    }
}