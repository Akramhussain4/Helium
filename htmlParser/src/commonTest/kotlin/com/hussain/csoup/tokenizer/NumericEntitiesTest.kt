package com.hussain.csoup.tokenizer

import kotlin.test.Test

/**
 * Contains all 336 tests from here:
 * https://github.com/html5lib/html5lib-tests/blob/master/tokenizer/numericEntities.test
 */
internal class NumericEntitiesTest : TokenizerTestBase() {

    @Test
    fun `Invalid unterminated numeric entity character overflow before EOF 1`() = assertTokenization(
        input = "&#11111111111",
        expectedTokens = listOf(char('\uFFFD')),
    )

    @Test
    fun `Invalid unterminated numeric entity character overflow before EOF 2`() = assertTokenization(
        input = "&#1111111111",
        expectedTokens = listOf(char('\uFFFD')),
    )

    @Test
    fun `Invalid unterminated numeric entity character overflow before EOF 3`() = assertTokenization(
        input = "&#111111111111",
        expectedTokens = listOf(char('\uFFFD')),
    )

    @Test
    fun `Invalid unterminated numeric entity character overflow 1`() = assertTokenization(
        input = "&#11111111111x",
        expectedTokens = listOf(
            char('ᧇ'),
            char('x'),
        )
    )

    @Test
    fun `Invalid unterminated numeric entity character overflow 2`() = assertTokenization(
        input = "&#1111111111x",
        expectedTokens = listOf(
            char('�'),
            char('x'),
        ),
    )

    @Test
    fun `Invalid unterminated numeric entity character overflow 3`() = assertTokenization(
        input = "&#111111111111x",
        expectedTokens = listOf(
            char('Ǉ'),
            char('x'),
        ),
    )

    @Test
    fun `Invalid numeric entity character overflow 1`() = assertTokenization(
        input = "&#11111111111;",
        expectedTokens = listOf(char('ᧇ')),
    )

    @Test
    fun `Invalid numeric entity character overflow 2`() = assertTokenization(
        input = "&#1111111111;",
        expectedTokens = listOf(char('\uFFFD')),
    )

    @Test
    fun `Invalid numeric entity character overflow 3`() = assertTokenization(
        input = "&#111111111111;",
        expectedTokens = listOf(char('Ǉ')),
    )

    @Test
    fun `Invalid numeric entity character U+0000`() = assertTokenization(
        input = "&#x0000;",
        expectedTokens = listOf(char('\uFFFD')),
    )

    @Test
    fun `Invalid numeric entity character U+0001`() = assertTokenization(
        input = "&#x0001;",
        expectedTokens = listOf(char('\u0001')),
    )

    @Test
    fun `Invalid numeric entity character U+0002`() = assertTokenization(
        input = "&#x0002;",
        expectedTokens = listOf(char('\u0002')),
    )

    @Test
    fun `Invalid numeric entity character U+0003`() = assertTokenization(
        input = "&#x0003;",
        expectedTokens = listOf(char('\u0003')),
    )

    @Test
    fun `Invalid numeric entity character U+0004`() = assertTokenization(
        input = "&#x0004;",
        expectedTokens = listOf(char('\u0004')),
    )

    @Test
    fun `Invalid numeric entity character U+0005`() = assertTokenization(
        input = "&#x0005;",
        expectedTokens = listOf(char('\u0005')),
    )

    @Test
    fun `Invalid numeric entity character U+0006`() = assertTokenization(
        input = "&#x0006;",
        expectedTokens = listOf(char('\u0006')),
    )

    @Test
    fun `Invalid numeric entity character U+0007`() = assertTokenization(
        input = "&#x0007;",
        expectedTokens = listOf(char('\u0007')),
    )

    @Test
    fun `Invalid numeric entity character U+0008`() = assertTokenization(
        input = "&#x0008;",
        expectedTokens = listOf(char('\u0008')),
    )

    @Test
    fun `Invalid numeric entity character U+000B`() = assertTokenization(
        input = "&#x000b;",
        expectedTokens = listOf(char('\u000b')),
    )

    @Test
    fun `Invalid numeric entity character U+000E`() = assertTokenization(
        input = "&#x000e;",
        expectedTokens = listOf(char('\u000e')),
    )

    @Test
    fun `Invalid numeric entity character U+000F`() = assertTokenization(
        input = "&#x000f;",
        expectedTokens = listOf(char('\u000f')),
    )

    @Test
    fun `Invalid numeric entity character U+0010`() = assertTokenization(
        input = "&#x0010;",
        expectedTokens = listOf(char('\u0010')),
    )

    @Test
    fun `Invalid numeric entity character U+0011`() = assertTokenization(
        input = "&#x0011;",
        expectedTokens = listOf(char('\u0011')),
    )

    @Test
    fun `Invalid numeric entity character U+0012`() = assertTokenization(
        input = "&#x0012;",
        expectedTokens = listOf(char('\u0012')),
    )

    @Test
    fun `Invalid numeric entity character U+0013`() = assertTokenization(
        input = "&#x0013;",
        expectedTokens = listOf(char('\u0013')),
    )

    @Test
    fun `Invalid numeric entity character U+0014`() = assertTokenization(
        input = "&#x0014;",
        expectedTokens = listOf(char('\u0014')),
    )

    @Test
    fun `Invalid numeric entity character U+0015`() = assertTokenization(
        input = "&#x0015;",
        expectedTokens = listOf(char('\u0015')),
    )

    @Test
    fun `Invalid numeric entity character U+0016`() = assertTokenization(
        input = "&#x0016;",
        expectedTokens = listOf(char('\u0016')),
    )

    @Test
    fun `Invalid numeric entity character U+0017`() = assertTokenization(
        input = "&#x0017;",
        expectedTokens = listOf(char('\u0017')),
    )

    @Test
    fun `Invalid numeric entity character U+0018`() = assertTokenization(
        input = "&#x0018;",
        expectedTokens = listOf(char('\u0018')),
    )

    @Test
    fun `Invalid numeric entity character U+0019`() = assertTokenization(
        input = "&#x0019;",
        expectedTokens = listOf(char('\u0019')),
    )

    @Test
    fun `Invalid numeric entity character U+001A`() = assertTokenization(
        input = "&#x001a;",
        expectedTokens = listOf(char('\u001a')),
    )

    @Test
    fun `Invalid numeric entity character U+001B`() = assertTokenization(
        input = "&#x001b;",
        expectedTokens = listOf(char('\u001b')),
    )

    @Test
    fun `Invalid numeric entity character U+001C`() = assertTokenization(
        input = "&#x001c;",
        expectedTokens = listOf(char('\u001c')),
    )

    @Test
    fun `Invalid numeric entity character U+001D`() = assertTokenization(
        input = "&#x001d;",
        expectedTokens = listOf(char('\u001d')),
    )

    @Test
    fun `Invalid numeric entity character U+001E`() = assertTokenization(
        input = "&#x001e;",
        expectedTokens = listOf(char('\u001e')),
    )

    @Test
    fun `Invalid numeric entity character U+001F`() = assertTokenization(
        input = "&#x001f;",
        expectedTokens = listOf(char('\u001f')),
    )

    @Test
    fun `Invalid numeric entity character U+007F`() = assertTokenization(
        input = "&#x007f;",
        expectedTokens = listOf(char('\u007f')),
    )

    @Test
    fun `Invalid numeric entity character U+D800`() = assertTokenization(
        input = "&#xd800;",
        expectedTokens = listOf(char('\uFFFD')),
    )

    @Test
    fun `Invalid numeric entity character U+DFFF`() = assertTokenization(
        input = "&#xdfff;",
        expectedTokens = listOf(char('\uFFFD')),
    )

    @Test
    fun `Invalid numeric entity character U+FDD0`() = assertTokenization(
        input = "&#xfdd0;",
        expectedTokens = listOf(char('\ufdd0')),
    )

    @Test
    fun `Invalid numeric entity character U+FDD1`() = assertTokenization(
        input = "&#xfdd1;",
        expectedTokens = listOf(char('\ufdd1')),
    )

    @Test
    fun `Invalid numeric entity character U+FDD2`() = assertTokenization(
        input = "&#xfdd2;",
        expectedTokens = listOf(char('\ufdd2')),
    )

    @Test
    fun `Invalid numeric entity character U+FDD3`() = assertTokenization(
        input = "&#xfdd3;",
        expectedTokens = listOf(char('\ufdd3')),
    )

    @Test
    fun `Invalid numeric entity character U+FDD4`() = assertTokenization(
        input = "&#xfdd4;",
        expectedTokens = listOf(char('\ufdd4')),
    )

    @Test
    fun `Invalid numeric entity character U+FDD5`() = assertTokenization(
        input = "&#xfdd5;",
        expectedTokens = listOf(char('\ufdd5')),
    )

    @Test
    fun `Invalid numeric entity character U+FDD6`() = assertTokenization(
        input = "&#xfdd6;",
        expectedTokens = listOf(char('\ufdd6')),
    )

    @Test
    fun `Invalid numeric entity character U+FDD7`() = assertTokenization(
        input = "&#xfdd7;",
        expectedTokens = listOf(char('\ufdd7')),
    )

    @Test
    fun `Invalid numeric entity character U+FDD8`() = assertTokenization(
        input = "&#xfdd8;",
        expectedTokens = listOf(char('\ufdd8')),
    )

    @Test
    fun `Invalid numeric entity character U+FDD9`() = assertTokenization(
        input = "&#xfdd9;",
        expectedTokens = listOf(char('\ufdd9')),
    )

    @Test
    fun `Invalid numeric entity character U+FDDA`() = assertTokenization(
        input = "&#xfdda;",
        expectedTokens = listOf(char('\ufdda')),
    )

    @Test
    fun `Invalid numeric entity character U+FDDB`() = assertTokenization(
        input = "&#xfddb;",
        expectedTokens = listOf(char('\ufddb')),
    )

    @Test
    fun `Invalid numeric entity character U+FDDC`() = assertTokenization(
        input = "&#xfddc;",
        expectedTokens = listOf(char('\ufddc')),
    )

    @Test
    fun `Invalid numeric entity character U+FDDD`() = assertTokenization(
        input = "&#xfddd;",
        expectedTokens = listOf(char('\ufddd')),
    )

    @Test
    fun `Invalid numeric entity character U+FDDE`() = assertTokenization(
        input = "&#xfdde;",
        expectedTokens = listOf(char('\ufdde')),
    )

    @Test
    fun `Invalid numeric entity character U+FDDF`() = assertTokenization(
        input = "&#xfddf;",
        expectedTokens = listOf(char('\ufddf')),
    )

    @Test
    fun `Invalid numeric entity character U+FDE0`() = assertTokenization(
        input = "&#xfde0;",
        expectedTokens = listOf(char('\ufde0')),
    )

    @Test
    fun `Invalid numeric entity character U+FDE1`() = assertTokenization(
        input = "&#xfde1;",
        expectedTokens = listOf(char('\ufde1')),
    )

    @Test
    fun `Invalid numeric entity character U+FDE2`() = assertTokenization(
        input = "&#xfde2;",
        expectedTokens = listOf(char('\ufde2')),
    )

    @Test
    fun `Invalid numeric entity character U+FDE3`() = assertTokenization(
        input = "&#xfde3;",
        expectedTokens = listOf(char('\ufde3')),
    )

    @Test
    fun `Invalid numeric entity character U+FDE4`() = assertTokenization(
        input = "&#xfde4;",
        expectedTokens = listOf(char('\ufde4')),
    )

    @Test
    fun `Invalid numeric entity character U+FDE5`() = assertTokenization(
        input = "&#xfde5;",
        expectedTokens = listOf(char('\ufde5')),
    )

    @Test
    fun `Invalid numeric entity character U+FDE6`() = assertTokenization(
        input = "&#xfde6;",
        expectedTokens = listOf(char('\ufde6')),
    )

    @Test
    fun `Invalid numeric entity character U+FDE7`() = assertTokenization(
        input = "&#xfde7;",
        expectedTokens = listOf(char('\ufde7')),
    )

    @Test
    fun `Invalid numeric entity character U+FDE8`() = assertTokenization(
        input = "&#xfde8;",
        expectedTokens = listOf(char('\ufde8')),
    )

    @Test
    fun `Invalid numeric entity character U+FDE9`() = assertTokenization(
        input = "&#xfde9;",
        expectedTokens = listOf(char('\ufde9')),
    )

    @Test
    fun `Invalid numeric entity character U+FDEA`() = assertTokenization(
        input = "&#xfdea;",
        expectedTokens = listOf(char('\ufdea')),
    )

    @Test
    fun `Invalid numeric entity character U+FDEB`() = assertTokenization(
        input = "&#xfdeb;",
        expectedTokens = listOf(char('\ufdeb')),
    )

    @Test
    fun `Invalid numeric entity character U+FDEC`() = assertTokenization(
        input = "&#xfdec;",
        expectedTokens = listOf(char('\ufdec')),
    )

    @Test
    fun `Invalid numeric entity character U+FDED`() = assertTokenization(
        input = "&#xfded;",
        expectedTokens = listOf(char('\ufded')),
    )

    @Test
    fun `Invalid numeric entity character U+FDEE`() = assertTokenization(
        input = "&#xfdee;",
        expectedTokens = listOf(char('\ufdee')),
    )

    @Test
    fun `Invalid numeric entity character U+FDEF`() = assertTokenization(
        input = "&#xfdef;",
        expectedTokens = listOf(char('\ufdef')),
    )

    @Test
    fun `Invalid numeric entity character U+FFFE`() = assertTokenization(
        input = "&#xfffe;",
        expectedTokens = listOf(char('\ufffe')),
    )

    @Test
    fun `Invalid numeric entity character U+FFFF`() = assertTokenization(
        input = "&#xffff;",
        expectedTokens = listOf(char('\uffff')),
    )

    @Test
    fun `Invalid numeric entity character U+1FFFE`() = assertTokenization(
        input = "&#x1fffe;",
        expectedTokens = listOf(
            char('\uD83F'),
            char('\uDFFE')
        ),
    )

    @Test
    fun `Invalid numeric entity character U+1FFFF`() = assertTokenization(
        input = "&#x1ffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('8'),
            char('3'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+2FFFE`() = assertTokenization(
        input = "&#x2fffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('8'),
            char('7'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+2FFFF`() = assertTokenization(
        input = "&#x2ffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('8'),
            char('7'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+3FFFE`() = assertTokenization(
        input = "&#x3fffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('8'),
            char('B'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+3FFFF`() = assertTokenization(
        input = "&#x3ffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('8'),
            char('B'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+4FFFE`() = assertTokenization(
        input = "&#x4fffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('8'),
            char('F'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+4FFFF`() = assertTokenization(
        input = "&#x4ffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('8'),
            char('F'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+5FFFE`() = assertTokenization(
        input = "&#x5fffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('9'),
            char('3'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+5FFFF`() = assertTokenization(
        input = "&#x5ffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('9'),
            char('3'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+6FFFE`() = assertTokenization(
        input = "&#x6fffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('9'),
            char('7'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+6FFFF`() = assertTokenization(
        input = "&#x6ffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('9'),
            char('7'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+7FFFE`() = assertTokenization(
        input = "&#x7fffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('9'),
            char('B'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+7FFFF`() = assertTokenization(
        input = "&#x7ffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('9'),
            char('B'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+8FFFE`() = assertTokenization(
        input = "&#x8fffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('9'),
            char('F'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+8FFFF`() = assertTokenization(
        input = "&#x8ffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('9'),
            char('F'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+9FFFE`() = assertTokenization(
        input = "&#x9fffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('A'),
            char('3'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+9FFFF`() = assertTokenization(
        input = "&#x9ffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('A'),
            char('3'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+AFFFE`() = assertTokenization(
        input = "&#xafffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('A'),
            char('7'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+AFFFF`() = assertTokenization(
        input = "&#xaffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('A'),
            char('7'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+BFFFE`() = assertTokenization(
        input = "&#xbfffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('A'),
            char('B'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+BFFFF`() = assertTokenization(
        input = "&#xbffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('A'),
            char('B'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+CFFFE`() = assertTokenization(
        input = "&#xcfffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('A'),
            char('F'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+CFFFF`() = assertTokenization(
        input = "&#xcffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('A'),
            char('F'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+DFFFE`() = assertTokenization(
        input = "&#xdfffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('B'),
            char('3'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+DFFFF`() = assertTokenization(
        input = "&#xdffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('B'),
            char('3'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+EFFFE`() = assertTokenization(
        input = "&#xefffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('B'),
            char('7'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+EFFFF`() = assertTokenization(
        input = "&#xeffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('B'),
            char('7'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+FFFFE`() = assertTokenization(
        input = "&#xffffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('B'),
            char('B'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+FFFFF`() = assertTokenization(
        input = "&#xfffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('B'),
            char('B'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+10FFFE`() = assertTokenization(
        input = "&#x10fffe;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('B'),
            char('F'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('E'),
        ),
    )

    @Test
    fun `Invalid numeric entity character U+10FFFF`() = assertTokenization(
        input = "&#x10ffff;",
        expectedTokens = listOf(
            char('\\'),
            char('u'),
            char('D'),
            char('B'),
            char('F'),
            char('F'),
            char('\\'),
            char('u'),
            char('D'),
            char('F'),
            char('F'),
            char('F'),
        ),
    )

    @Test
    fun `Valid numeric entity character U+0009`() = assertTokenization(
        input = "&#x0009;",
        expectedTokens = listOf(char('	')),
    )

    @Test
    fun `Valid numeric entity character U+000A`() = assertTokenization(
        input = "&#x000a;",
        expectedTokens = listOf(char('\n')),
    )

    @Test
    fun `Valid numeric entity character U+0020`() = assertTokenization(
        input = "&#x0020;",
        expectedTokens = listOf(char(' ')),
    )

    @Test
    fun `Valid numeric entity character U+0021`() = assertTokenization(
        input = "&#x0021;",
        expectedTokens = listOf(char('!')),
    )

    @Test
    fun `Valid numeric entity character U+0022`() = assertTokenization(
        input = "&#x0022;",
        expectedTokens = listOf(char('"')),
    )

    @Test
    fun `Valid numeric entity character U+0023`() = assertTokenization(
        input = "&#x0023;",
        expectedTokens = listOf(char('#')),
    )

    @Test
    fun `Valid numeric entity character U+0024`() = assertTokenization(
        input = "&#x0024;",
        expectedTokens = listOf(char('$')),
    )

    @Test
    fun `Valid numeric entity character U+0025`() = assertTokenization(
        input = "&#x0025;",
        expectedTokens = listOf(char('%')),
    )

    @Test
    fun `Valid numeric entity character U+0026`() = assertTokenization(
        input = "&#x0026;",
        expectedTokens = listOf(char('&')),
    )

    @Test
    fun `Valid numeric entity character U+0027`() = assertTokenization(
        input = "&#x0027;",
        expectedTokens = listOf(char('\'')),
    )

    @Test
    fun `Valid numeric entity character U+0028`() = assertTokenization(
        input = "&#x0028;",
        expectedTokens = listOf(char('(')),
    )

    @Test
    fun `Valid numeric entity character U+0029`() = assertTokenization(
        input = "&#x0029;",
        expectedTokens = listOf(char(')')),
    )

    @Test
    fun `Valid numeric entity character U+002A`() = assertTokenization(
        input = "&#x002a;",
        expectedTokens = listOf(char('*')),
    )

    @Test
    fun `Valid numeric entity character U+002B`() = assertTokenization(
        input = "&#x002b;",
        expectedTokens = listOf(char('+')),
    )

    @Test
    fun `Valid numeric entity character U+002C`() = assertTokenization(
        input = "&#x002c;",
        expectedTokens = listOf(char(',')),
    )

    @Test
    fun `Valid numeric entity character U+002D`() = assertTokenization(
        input = "&#x002d;",
        expectedTokens = listOf(char('-')),
    )

    @Test
    fun `Valid numeric entity character U+002E`() = assertTokenization(
        input = "&#x002e;",
        expectedTokens = listOf(char('.')),
    )

    @Test
    fun `Valid numeric entity character U+002F`() = assertTokenization(
        input = "&#x002f;",
        expectedTokens = listOf(char('/')),
    )

    @Test
    fun `Valid numeric entity character U+0030`() = assertTokenization(
        input = "&#x0030;",
        expectedTokens = listOf(char('0')),
    )

    @Test
    fun `Valid numeric entity character U+0031`() = assertTokenization(
        input = "&#x0031;",
        expectedTokens = listOf(char('1')),
    )

    @Test
    fun `Valid numeric entity character U+0032`() = assertTokenization(
        input = "&#x0032;",
        expectedTokens = listOf(char('2')),
    )

    @Test
    fun `Valid numeric entity character U+0033`() = assertTokenization(
        input = "&#x0033;",
        expectedTokens = listOf(char('3')),
    )

    @Test
    fun `Valid numeric entity character U+0034`() = assertTokenization(
        input = "&#x0034;",
        expectedTokens = listOf(char('4')),
    )

    @Test
    fun `Valid numeric entity character U+0035`() = assertTokenization(
        input = "&#x0035;",
        expectedTokens = listOf(char('5')),
    )

    @Test
    fun `Valid numeric entity character U+0036`() = assertTokenization(
        input = "&#x0036;",
        expectedTokens = listOf(char('6')),
    )

    @Test
    fun `Valid numeric entity character U+0037`() = assertTokenization(
        input = "&#x0037;",
        expectedTokens = listOf(char('7')),
    )

    @Test
    fun `Valid numeric entity character U+0038`() = assertTokenization(
        input = "&#x0038;",
        expectedTokens = listOf(char('8')),
    )

    @Test
    fun `Valid numeric entity character U+0039`() = assertTokenization(
        input = "&#x0039;",
        expectedTokens = listOf(char('9')),
    )

    @Test
    fun `Valid numeric entity character U+003A`() = assertTokenization(
        input = "&#x003a;",
        expectedTokens = listOf(char(':')),
    )

    @Test
    fun `Valid numeric entity character U+003B`() = assertTokenization(
        input = "&#x003b;",
        expectedTokens = listOf(char(';')),
    )

    @Test
    fun `Valid numeric entity character U+003C`() = assertTokenization(
        input = "&#x003c;",
        expectedTokens = listOf(char('<')),
    )

    @Test
    fun `Valid numeric entity character U+003D`() = assertTokenization(
        input = "&#x003d;",
        expectedTokens = listOf(char('=')),
    )

    @Test
    fun `Valid numeric entity character U+003E`() = assertTokenization(
        input = "&#x003e;",
        expectedTokens = listOf(char('>')),
    )

    @Test
    fun `Valid numeric entity character U+003F`() = assertTokenization(
        input = "&#x003f;",
        expectedTokens = listOf(char('?')),
    )

    @Test
    fun `Valid numeric entity character U+0040`() = assertTokenization(
        input = "&#x0040;",
        expectedTokens = listOf(char('@')),
    )

    @Test
    fun `Valid numeric entity character U+0041`() = assertTokenization(
        input = "&#x0041;",
        expectedTokens = listOf(char('A')),
    )

    @Test
    fun `Valid numeric entity character U+0042`() = assertTokenization(
        input = "&#x0042;",
        expectedTokens = listOf(char('B')),
    )

    @Test
    fun `Valid numeric entity character U+0043`() = assertTokenization(
        input = "&#x0043;",
        expectedTokens = listOf(char('C')),
    )

    @Test
    fun `Valid numeric entity character U+0044`() = assertTokenization(
        input = "&#x0044;",
        expectedTokens = listOf(char('D')),
    )

    @Test
    fun `Valid numeric entity character U+0045`() = assertTokenization(
        input = "&#x0045;",
        expectedTokens = listOf(char('E')),
    )

    @Test
    fun `Valid numeric entity character U+0046`() = assertTokenization(
        input = "&#x0046;",
        expectedTokens = listOf(char('F')),
    )

    @Test
    fun `Valid numeric entity character U+0047`() = assertTokenization(
        input = "&#x0047;",
        expectedTokens = listOf(char('G')),
    )

    @Test
    fun `Valid numeric entity character U+0048`() = assertTokenization(
        input = "&#x0048;",
        expectedTokens = listOf(char('H')),
    )

    @Test
    fun `Valid numeric entity character U+0049`() = assertTokenization(
        input = "&#x0049;",
        expectedTokens = listOf(char('I')),
    )

    @Test
    fun `Valid numeric entity character U+004A`() = assertTokenization(
        input = "&#x004a;",
        expectedTokens = listOf(char('J')),
    )

    @Test
    fun `Valid numeric entity character U+004B`() = assertTokenization(
        input = "&#x004b;",
        expectedTokens = listOf(char('K')),
    )

    @Test
    fun `Valid numeric entity character U+004C`() = assertTokenization(
        input = "&#x004c;",
        expectedTokens = listOf(char('L')),
    )

    @Test
    fun `Valid numeric entity character U+004D`() = assertTokenization(
        input = "&#x004d;",
        expectedTokens = listOf(char('M')),
    )

    @Test
    fun `Valid numeric entity character U+004E`() = assertTokenization(
        input = "&#x004e;",
        expectedTokens = listOf(char('N')),
    )

    @Test
    fun `Valid numeric entity character U+004F`() = assertTokenization(
        input = "&#x004f;",
        expectedTokens = listOf(char('O')),
    )

    @Test
    fun `Valid numeric entity character U+0050`() = assertTokenization(
        input = "&#x0050;",
        expectedTokens = listOf(char('P')),
    )

    @Test
    fun `Valid numeric entity character U+0051`() = assertTokenization(
        input = "&#x0051;",
        expectedTokens = listOf(char('Q')),
    )

    @Test
    fun `Valid numeric entity character U+0052`() = assertTokenization(
        input = "&#x0052;",
        expectedTokens = listOf(char('R')),
    )

    @Test
    fun `Valid numeric entity character U+0053`() = assertTokenization(
        input = "&#x0053;",
        expectedTokens = listOf(char('S')),
    )

    @Test
    fun `Valid numeric entity character U+0054`() = assertTokenization(
        input = "&#x0054;",
        expectedTokens = listOf(char('T')),
    )

    @Test
    fun `Valid numeric entity character U+0055`() = assertTokenization(
        input = "&#x0055;",
        expectedTokens = listOf(char('U')),
    )

    @Test
    fun `Valid numeric entity character U+0056`() = assertTokenization(
        input = "&#x0056;",
        expectedTokens = listOf(char('V')),
    )

    @Test
    fun `Valid numeric entity character U+0057`() = assertTokenization(
        input = "&#x0057;",
        expectedTokens = listOf(char('W')),
    )

    @Test
    fun `Valid numeric entity character U+0058`() = assertTokenization(
        input = "&#x0058;",
        expectedTokens = listOf(char('X')),
    )

    @Test
    fun `Valid numeric entity character U+0059`() = assertTokenization(
        input = "&#x0059;",
        expectedTokens = listOf(char('Y')),
    )

    @Test
    fun `Valid numeric entity character U+005A`() = assertTokenization(
        input = "&#x005a;",
        expectedTokens = listOf(char('Z')),
    )

    @Test
    fun `Valid numeric entity character U+005B`() = assertTokenization(
        input = "&#x005b;",
        expectedTokens = listOf(char('[')),
    )

    @Test
    fun `Valid numeric entity character U+005C`() = assertTokenization(
        input = "&#x005c;",
        expectedTokens = listOf(char('\\')),
    )

    @Test
    fun `Valid numeric entity character U+005D`() = assertTokenization(
        input = "&#x005d;",
        expectedTokens = listOf(char(']')),
    )

    @Test
    fun `Valid numeric entity character U+005E`() = assertTokenization(
        input = "&#x005e;",
        expectedTokens = listOf(char('^')),
    )

    @Test
    fun `Valid numeric entity character U+005F`() = assertTokenization(
        input = "&#x005f;",
        expectedTokens = listOf(char('_')),
    )

    @Test
    fun `Valid numeric entity character U+0060`() = assertTokenization(
        input = "&#x0060;",
        expectedTokens = listOf(char('`')),
    )

    @Test
    fun `Valid numeric entity character U+0061`() = assertTokenization(
        input = "&#x0061;",
        expectedTokens = listOf(char('a')),
    )

    @Test
    fun `Valid numeric entity character U+0062`() = assertTokenization(
        input = "&#x0062;",
        expectedTokens = listOf(char('b')),
    )

    @Test
    fun `Valid numeric entity character U+0063`() = assertTokenization(
        input = "&#x0063;",
        expectedTokens = listOf(char('c')),
    )

    @Test
    fun `Valid numeric entity character U+0064`() = assertTokenization(
        input = "&#x0064;",
        expectedTokens = listOf(char('d')),
    )

    @Test
    fun `Valid numeric entity character U+0065`() = assertTokenization(
        input = "&#x0065;",
        expectedTokens = listOf(char('e')),
    )

    @Test
    fun `Valid numeric entity character U+0066`() = assertTokenization(
        input = "&#x0066;",
        expectedTokens = listOf(char('f')),
    )

    @Test
    fun `Valid numeric entity character U+0067`() = assertTokenization(
        input = "&#x0067;",
        expectedTokens = listOf(char('g')),
    )

    @Test
    fun `Valid numeric entity character U+0068`() = assertTokenization(
        input = "&#x0068;",
        expectedTokens = listOf(char('h')),
    )

    @Test
    fun `Valid numeric entity character U+0069`() = assertTokenization(
        input = "&#x0069;",
        expectedTokens = listOf(char('i')),
    )

    @Test
    fun `Valid numeric entity character U+006A`() = assertTokenization(
        input = "&#x006a;",
        expectedTokens = listOf(char('j')),
    )

    @Test
    fun `Valid numeric entity character U+006B`() = assertTokenization(
        input = "&#x006b;",
        expectedTokens = listOf(char('k')),
    )

    @Test
    fun `Valid numeric entity character U+006C`() = assertTokenization(
        input = "&#x006c;",
        expectedTokens = listOf(char('l')),
    )

    @Test
    fun `Valid numeric entity character U+006D`() = assertTokenization(
        input = "&#x006d;",
        expectedTokens = listOf(char('m')),
    )

    @Test
    fun `Valid numeric entity character U+006E`() = assertTokenization(
        input = "&#x006e;",
        expectedTokens = listOf(char('n')),
    )

    @Test
    fun `Valid numeric entity character U+006F`() = assertTokenization(
        input = "&#x006f;",
        expectedTokens = listOf(char('o')),
    )

    @Test
    fun `Valid numeric entity character U+0070`() = assertTokenization(
        input = "&#x0070;",
        expectedTokens = listOf(char('p')),
    )

    @Test
    fun `Valid numeric entity character U+0071`() = assertTokenization(
        input = "&#x0071;",
        expectedTokens = listOf(char('q')),
    )

    @Test
    fun `Valid numeric entity character U+0072`() = assertTokenization(
        input = "&#x0072;",
        expectedTokens = listOf(char('r')),
    )

    @Test
    fun `Valid numeric entity character U+0073`() = assertTokenization(
        input = "&#x0073;",
        expectedTokens = listOf(char('s')),
    )

    @Test
    fun `Valid numeric entity character U+0074`() = assertTokenization(
        input = "&#x0074;",
        expectedTokens = listOf(char('t')),
    )

    @Test
    fun `Valid numeric entity character U+0075`() = assertTokenization(
        input = "&#x0075;",
        expectedTokens = listOf(char('u')),
    )

    @Test
    fun `Valid numeric entity character U+0076`() = assertTokenization(
        input = "&#x0076;",
        expectedTokens = listOf(char('v')),
    )

    @Test
    fun `Valid numeric entity character U+0077`() = assertTokenization(
        input = "&#x0077;",
        expectedTokens = listOf(char('w')),
    )

    @Test
    fun `Valid numeric entity character U+0078`() = assertTokenization(
        input = "&#x0078;",
        expectedTokens = listOf(char('x')),
    )

    @Test
    fun `Valid numeric entity character U+0079`() = assertTokenization(
        input = "&#x0079;",
        expectedTokens = listOf(char('y')),
    )

    @Test
    fun `Valid numeric entity character U+007A`() = assertTokenization(
        input = "&#x007a;",
        expectedTokens = listOf(char('z')),
    )

    @Test
    fun `Valid numeric entity character U+007B`() = assertTokenization(
        input = "&#x007b;",
        expectedTokens = listOf(char('{')),
    )

    @Test
    fun `Valid numeric entity character U+007C`() = assertTokenization(
        input = "&#x007c;",
        expectedTokens = listOf(char('|')),
    )

    @Test
    fun `Valid numeric entity character U+007D`() = assertTokenization(
        input = "&#x007d;",
        expectedTokens = listOf(char('}')),
    )

    @Test
    fun `Valid numeric entity character U+007E`() = assertTokenization(
        input = "&#x007e;",
        expectedTokens = listOf(char('~')),
    )

    @Test
    fun `Valid numeric entity character U+00A0`() = assertTokenization(
        input = "&#x00a0;",
        expectedTokens = listOf(char(' ')),
    )

    @Test
    fun `Valid numeric entity character U+00A1`() = assertTokenization(
        input = "&#x00a1;",
        expectedTokens = listOf(char('¡')),
    )

    @Test
    fun `Valid numeric entity character U+00A2`() = assertTokenization(
        input = "&#x00a2;",
        expectedTokens = listOf(char('¢')),
    )

    @Test
    fun `Valid numeric entity character U+00A3`() = assertTokenization(
        input = "&#x00a3;",
        expectedTokens = listOf(char('£')),
    )

    @Test
    fun `Valid numeric entity character U+00A4`() = assertTokenization(
        input = "&#x00a4;",
        expectedTokens = listOf(char('¤')),
    )

    @Test
    fun `Valid numeric entity character U+00A5`() = assertTokenization(
        input = "&#x00a5;",
        expectedTokens = listOf(char('¥')),
    )

    @Test
    fun `Valid numeric entity character U+00A6`() = assertTokenization(
        input = "&#x00a6;",
        expectedTokens = listOf(char('¦')),
    )

    @Test
    fun `Valid numeric entity character U+00A7`() = assertTokenization(
        input = "&#x00a7;",
        expectedTokens = listOf(char('§')),
    )

    @Test
    fun `Valid numeric entity character U+00A8`() = assertTokenization(
        input = "&#x00a8;",
        expectedTokens = listOf(char('¨')),
    )

    @Test
    fun `Valid numeric entity character U+00A9`() = assertTokenization(
        input = "&#x00a9;",
        expectedTokens = listOf(char('©')),
    )

    @Test
    fun `Valid numeric entity character U+00AA`() = assertTokenization(
        input = "&#x00aa;",
        expectedTokens = listOf(char('ª')),
    )

    @Test
    fun `Valid numeric entity character U+00AB`() = assertTokenization(
        input = "&#x00ab;",
        expectedTokens = listOf(char('«')),
    )

    @Test
    fun `Valid numeric entity character U+00AC`() = assertTokenization(
        input = "&#x00ac;",
        expectedTokens = listOf(char('¬')),
    )

    @Test
    fun `Valid numeric entity character U+00AD`() = assertTokenization(
        input = "&#x00ad;",
        expectedTokens = listOf(char('­')),
    )

    @Test
    fun `Valid numeric entity character U+00AE`() = assertTokenization(
        input = "&#x00ae;",
        expectedTokens = listOf(char('®')),
    )

    @Test
    fun `Valid numeric entity character U+00AF`() = assertTokenization(
        input = "&#x00af;",
        expectedTokens = listOf(char('¯')),
    )

    @Test
    fun `Valid numeric entity character U+00B0`() = assertTokenization(
        input = "&#x00b0;",
        expectedTokens = listOf(char('°')),
    )

    @Test
    fun `Valid numeric entity character U+00B1`() = assertTokenization(
        input = "&#x00b1;",
        expectedTokens = listOf(char('±')),
    )

    @Test
    fun `Valid numeric entity character U+00B2`() = assertTokenization(
        input = "&#x00b2;",
        expectedTokens = listOf(char('²')),
    )

    @Test
    fun `Valid numeric entity character U+00B3`() = assertTokenization(
        input = "&#x00b3;",
        expectedTokens = listOf(char('³')),
    )

    @Test
    fun `Valid numeric entity character U+00B4`() = assertTokenization(
        input = "&#x00b4;",
        expectedTokens = listOf(char('´')),
    )

    @Test
    fun `Valid numeric entity character U+00B5`() = assertTokenization(
        input = "&#x00b5;",
        expectedTokens = listOf(char('µ')),
    )

    @Test
    fun `Valid numeric entity character U+00B6`() = assertTokenization(
        input = "&#x00b6;",
        expectedTokens = listOf(char('¶')),
    )

    @Test
    fun `Valid numeric entity character U+00B7`() = assertTokenization(
        input = "&#x00b7;",
        expectedTokens = listOf(char('·')),
    )

    @Test
    fun `Valid numeric entity character U+00B8`() = assertTokenization(
        input = "&#x00b8;",
        expectedTokens = listOf(char('¸')),
    )

    @Test
    fun `Valid numeric entity character U+00B9`() = assertTokenization(
        input = "&#x00b9;",
        expectedTokens = listOf(char('¹')),
    )

    @Test
    fun `Valid numeric entity character U+00BA`() = assertTokenization(
        input = "&#x00ba;",
        expectedTokens = listOf(char('º')),
    )

    @Test
    fun `Valid numeric entity character U+00BB`() = assertTokenization(
        input = "&#x00bb;",
        expectedTokens = listOf(char('»')),
    )

    @Test
    fun `Valid numeric entity character U+00BC`() = assertTokenization(
        input = "&#x00bc;",
        expectedTokens = listOf(char('¼')),
    )

    @Test
    fun `Valid numeric entity character U+00BD`() = assertTokenization(
        input = "&#x00bd;",
        expectedTokens = listOf(char('½')),
    )

    @Test
    fun `Valid numeric entity character U+00BE`() = assertTokenization(
        input = "&#x00be;",
        expectedTokens = listOf(char('¾')),
    )

    @Test
    fun `Valid numeric entity character U+00BF`() = assertTokenization(
        input = "&#x00bf;",
        expectedTokens = listOf(char('¿')),
    )

    @Test
    fun `Valid numeric entity character U+00C0`() = assertTokenization(
        input = "&#x00c0;",
        expectedTokens = listOf(char('À')),
    )

    @Test
    fun `Valid numeric entity character U+00C1`() = assertTokenization(
        input = "&#x00c1;",
        expectedTokens = listOf(char('Á')),
    )

    @Test
    fun `Valid numeric entity character U+00C2`() = assertTokenization(
        input = "&#x00c2;",
        expectedTokens = listOf(char('Â')),
    )

    @Test
    fun `Valid numeric entity character U+00C3`() = assertTokenization(
        input = "&#x00c3;",
        expectedTokens = listOf(char('Ã')),
    )

    @Test
    fun `Valid numeric entity character U+00C4`() = assertTokenization(
        input = "&#x00c4;",
        expectedTokens = listOf(char('Ä')),
    )

    @Test
    fun `Valid numeric entity character U+00C5`() = assertTokenization(
        input = "&#x00c5;",
        expectedTokens = listOf(char('Å')),
    )

    @Test
    fun `Valid numeric entity character U+00C6`() = assertTokenization(
        input = "&#x00c6;",
        expectedTokens = listOf(char('Æ')),
    )

    @Test
    fun `Valid numeric entity character U+00C7`() = assertTokenization(
        input = "&#x00c7;",
        expectedTokens = listOf(char('Ç')),
    )

    @Test
    fun `Valid numeric entity character U+00C8`() = assertTokenization(
        input = "&#x00c8;",
        expectedTokens = listOf(char('È')),
    )

    @Test
    fun `Valid numeric entity character U+00C9`() = assertTokenization(
        input = "&#x00c9;",
        expectedTokens = listOf(char('É')),
    )

    @Test
    fun `Valid numeric entity character U+00CA`() = assertTokenization(
        input = "&#x00ca;",
        expectedTokens = listOf(char('Ê')),
    )

    @Test
    fun `Valid numeric entity character U+00CB`() = assertTokenization(
        input = "&#x00cb;",
        expectedTokens = listOf(char('Ë')),
    )

    @Test
    fun `Valid numeric entity character U+00CC`() = assertTokenization(
        input = "&#x00cc;",
        expectedTokens = listOf(char('Ì')),
    )

    @Test
    fun `Valid numeric entity character U+00CD`() = assertTokenization(
        input = "&#x00cd;",
        expectedTokens = listOf(char('Í')),
    )

    @Test
    fun `Valid numeric entity character U+00CE`() = assertTokenization(
        input = "&#x00ce;",
        expectedTokens = listOf(char('Î')),
    )

    @Test
    fun `Valid numeric entity character U+00CF`() = assertTokenization(
        input = "&#x00cf;",
        expectedTokens = listOf(char('Ï')),
    )

    @Test
    fun `Valid numeric entity character U+00D0`() = assertTokenization(
        input = "&#x00d0;",
        expectedTokens = listOf(char('Ð')),
    )

    @Test
    fun `Valid numeric entity character U+00D1`() = assertTokenization(
        input = "&#x00d1;",
        expectedTokens = listOf(char('Ñ')),
    )

    @Test
    fun `Valid numeric entity character U+00D2`() = assertTokenization(
        input = "&#x00d2;",
        expectedTokens = listOf(char('Ò')),
    )

    @Test
    fun `Valid numeric entity character U+00D3`() = assertTokenization(
        input = "&#x00d3;",
        expectedTokens = listOf(char('Ó')),
    )

    @Test
    fun `Valid numeric entity character U+00D4`() = assertTokenization(
        input = "&#x00d4;",
        expectedTokens = listOf(char('Ô')),
    )

    @Test
    fun `Valid numeric entity character U+00D5`() = assertTokenization(
        input = "&#x00d5;",
        expectedTokens = listOf(char('Õ')),
    )

    @Test
    fun `Valid numeric entity character U+00D6`() = assertTokenization(
        input = "&#x00d6;",
        expectedTokens = listOf(char('Ö')),
    )

    @Test
    fun `Valid numeric entity character U+00D7`() = assertTokenization(
        input = "&#x00d7;",
        expectedTokens = listOf(char('×')),
    )

    @Test
    fun `Valid numeric entity character U+00D8`() = assertTokenization(
        input = "&#x00d8;",
        expectedTokens = listOf(char('Ø')),
    )

    @Test
    fun `Valid numeric entity character U+00D9`() = assertTokenization(
        input = "&#x00d9;",
        expectedTokens = listOf(char('Ù')),
    )

    @Test
    fun `Valid numeric entity character U+00DA`() = assertTokenization(
        input = "&#x00da;",
        expectedTokens = listOf(char('Ú')),
    )

    @Test
    fun `Valid numeric entity character U+00DB`() = assertTokenization(
        input = "&#x00db;",
        expectedTokens = listOf(char('Û')),
    )

    @Test
    fun `Valid numeric entity character U+00DC`() = assertTokenization(
        input = "&#x00dc;",
        expectedTokens = listOf(char('Ü')),
    )

    @Test
    fun `Valid numeric entity character U+00DD`() = assertTokenization(
        input = "&#x00dd;",
        expectedTokens = listOf(char('Ý')),
    )

    @Test
    fun `Valid numeric entity character U+00DE`() = assertTokenization(
        input = "&#x00de;",
        expectedTokens = listOf(char('Þ')),
    )

    @Test
    fun `Valid numeric entity character U+00DF`() = assertTokenization(
        input = "&#x00df;",
        expectedTokens = listOf(char('ß')),
    )

    @Test
    fun `Valid numeric entity character U+00E0`() = assertTokenization(
        input = "&#x00e0;",
        expectedTokens = listOf(char('à')),
    )

    @Test
    fun `Valid numeric entity character U+00E1`() = assertTokenization(
        input = "&#x00e1;",
        expectedTokens = listOf(char('á')),
    )

    @Test
    fun `Valid numeric entity character U+00E2`() = assertTokenization(
        input = "&#x00e2;",
        expectedTokens = listOf(char('â')),
    )

    @Test
    fun `Valid numeric entity character U+00E3`() = assertTokenization(
        input = "&#x00e3;",
        expectedTokens = listOf(char('ã')),
    )

    @Test
    fun `Valid numeric entity character U+00E4`() = assertTokenization(
        input = "&#x00e4;",
        expectedTokens = listOf(char('ä')),
    )

    @Test
    fun `Valid numeric entity character U+00E5`() = assertTokenization(
        input = "&#x00e5;",
        expectedTokens = listOf(char('å')),
    )

    @Test
    fun `Valid numeric entity character U+00E6`() = assertTokenization(
        input = "&#x00e6;",
        expectedTokens = listOf(char('æ')),
    )

    @Test
    fun `Valid numeric entity character U+00E7`() = assertTokenization(
        input = "&#x00e7;",
        expectedTokens = listOf(char('ç')),
    )

    @Test
    fun `Valid numeric entity character U+00E8`() = assertTokenization(
        input = "&#x00e8;",
        expectedTokens = listOf(char('è')),
    )

    @Test
    fun `Valid numeric entity character U+00E9`() = assertTokenization(
        input = "&#x00e9;",
        expectedTokens = listOf(char('é')),
    )

    @Test
    fun `Valid numeric entity character U+00EA`() = assertTokenization(
        input = "&#x00ea;",
        expectedTokens = listOf(char('ê')),
    )

    @Test
    fun `Valid numeric entity character U+00EB`() = assertTokenization(
        input = "&#x00eb;",
        expectedTokens = listOf(char('ë')),
    )

    @Test
    fun `Valid numeric entity character U+00EC`() = assertTokenization(
        input = "&#x00ec;",
        expectedTokens = listOf(char('ì')),
    )

    @Test
    fun `Valid numeric entity character U+00ED`() = assertTokenization(
        input = "&#x00ed;",
        expectedTokens = listOf(char('í')),
    )

    @Test
    fun `Valid numeric entity character U+00EE`() = assertTokenization(
        input = "&#x00ee;",
        expectedTokens = listOf(char('î')),
    )

    @Test
    fun `Valid numeric entity character U+00EF`() = assertTokenization(
        input = "&#x00ef;",
        expectedTokens = listOf(char('ï')),
    )

    @Test
    fun `Valid numeric entity character U+00F0`() = assertTokenization(
        input = "&#x00f0;",
        expectedTokens = listOf(char('ð')),
    )

    @Test
    fun `Valid numeric entity character U+00F1`() = assertTokenization(
        input = "&#x00f1;",
        expectedTokens = listOf(char('ñ')),
    )

    @Test
    fun `Valid numeric entity character U+00F2`() = assertTokenization(
        input = "&#x00f2;",
        expectedTokens = listOf(char('ò')),
    )

    @Test
    fun `Valid numeric entity character U+00F3`() = assertTokenization(
        input = "&#x00f3;",
        expectedTokens = listOf(char('ó')),
    )

    @Test
    fun `Valid numeric entity character U+00F4`() = assertTokenization(
        input = "&#x00f4;",
        expectedTokens = listOf(char('ô')),
    )

    @Test
    fun `Valid numeric entity character U+00F5`() = assertTokenization(
        input = "&#x00f5;",
        expectedTokens = listOf(char('õ')),
    )

    @Test
    fun `Valid numeric entity character U+00F6`() = assertTokenization(
        input = "&#x00f6;",
        expectedTokens = listOf(char('ö')),
    )

    @Test
    fun `Valid numeric entity character U+00F7`() = assertTokenization(
        input = "&#x00f7;",
        expectedTokens = listOf(char('÷')),
    )

    @Test
    fun `Valid numeric entity character U+00F8`() = assertTokenization(
        input = "&#x00f8;",
        expectedTokens = listOf(char('ø')),
    )

    @Test
    fun `Valid numeric entity character U+00F9`() = assertTokenization(
        input = "&#x00f9;",
        expectedTokens = listOf(char('ù')),
    )

    @Test
    fun `Valid numeric entity character U+00FA`() = assertTokenization(
        input = "&#x00fa;",
        expectedTokens = listOf(char('ú')),
    )

    @Test
    fun `Valid numeric entity character U+00FB`() = assertTokenization(
        input = "&#x00fb;",
        expectedTokens = listOf(char('û')),
    )

    @Test
    fun `Valid numeric entity character U+00FC`() = assertTokenization(
        input = "&#x00fc;",
        expectedTokens = listOf(char('ü')),
    )

    @Test
    fun `Valid numeric entity character U+00FD`() = assertTokenization(
        input = "&#x00fd;",
        expectedTokens = listOf(char('ý')),
    )

    @Test
    fun `Valid numeric entity character U+00FE`() = assertTokenization(
        input = "&#x00fe;",
        expectedTokens = listOf(char('þ')),
    )

    @Test
    fun `Valid numeric entity character U+00FF`() = assertTokenization(
        input = "&#x00ff;",
        expectedTokens = listOf(char('ÿ')),
    )

    @Test
    fun `Valid numeric entity character U+D7FF`() = assertTokenization(
        input = "&#xd7ff;",
        expectedTokens = listOf(char('\uD7FF')),
    )

    @Test
    fun `Valid numeric entity character U+E000`() = assertTokenization(
        input = "&#xe000;",
        expectedTokens = listOf(char('')),
    )

    @Test
    fun `Valid numeric entity character U+FDCF`() = assertTokenization(
        input = "&#xfdcf;",
        expectedTokens = listOf(char('\uFDCF')),
    )

    @Test
    fun `Valid numeric entity character U+FDF0`() = assertTokenization(
        input = "&#xfdf0;",
        expectedTokens = listOf(char('ﷰ')),
    )

    @Test
    fun `Valid numeric entity character U+FFFD`() = assertTokenization(
        input = "&#xfffd;",
        expectedTokens = listOf(char('\uFFFD')),
    )

    @Test
    fun `Valid numeric entity character U+10000`() = assertTokenization(
        input = "&#x10000;",
        expectedTokens = listOf(
            char('\uD800'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+1FFFD`() = assertTokenization(
        input = "&#x1fffd;",
        expectedTokens = listOf(
            char('\uD83F'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+20000`() = assertTokenization(
        input = "&#x20000;",
        expectedTokens = listOf(
            char('\uD840'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+2FFFD`() = assertTokenization(
        input = "&#x2fffd;",
        expectedTokens = listOf(
            char('\uD87F'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+30000`() = assertTokenization(
        input = "&#x30000;",
        expectedTokens = listOf(
            char('\uD880'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+3FFFD`() = assertTokenization(
        input = "&#x3fffd;",
        expectedTokens = listOf(
            char('\uD8BF'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+40000`() = assertTokenization(
        input = "&#x40000;",
        expectedTokens = listOf(
            char('\uD8C0'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+4FFFD`() = assertTokenization(
        input = "&#x4fffd;",
        expectedTokens = listOf(
            char('\uD8FF'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+50000`() = assertTokenization(
        input = "&#x50000;",
        expectedTokens = listOf(
            char('\uD900'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+5FFFD`() = assertTokenization(
        input = "&#x5fffd;",
        expectedTokens = listOf(
            char('\uD93F'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+60000`() = assertTokenization(
        input = "&#x60000;",
        expectedTokens = listOf(
            char('\uD940'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+6FFFD`() = assertTokenization(
        input = "&#x6fffd;",
        expectedTokens = listOf(
            char('\uD97F'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+70000`() = assertTokenization(
        input = "&#x70000;",
        expectedTokens = listOf(
            char('\uD980'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+7FFFD`() = assertTokenization(
        input = "&#x7fffd;",
        expectedTokens = listOf(
            char('\uD9BF'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+80000`() = assertTokenization(
        input = "&#x80000;",
        expectedTokens = listOf(
            char('\uD9C0'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+8FFFD`() = assertTokenization(
        input = "&#x8fffd;",
        expectedTokens = listOf(
            char('\uD9FF'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+90000`() = assertTokenization(
        input = "&#x90000;",
        expectedTokens = listOf(
            char('\uDA00'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+9FFFD`() = assertTokenization(
        input = "&#x9fffd;",
        expectedTokens = listOf(
            char('\uDA3F'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+A0000`() = assertTokenization(
        input = "&#xa0000;",
        expectedTokens = listOf(
            char('\uDA40'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+AFFFD`() = assertTokenization(
        input = "&#xafffd;",
        expectedTokens = listOf(
            char('\uDA7F'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+B0000`() = assertTokenization(
        input = "&#xb0000;",
        expectedTokens = listOf(
            char('\uDA80'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+BFFFD`() = assertTokenization(
        input = "&#xbfffd;",
        expectedTokens = listOf(
            char('\uDABF'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+C0000`() = assertTokenization(
        input = "&#xc0000;",
        expectedTokens = listOf(
            char('\uDAC0'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+CFFFD`() = assertTokenization(
        input = "&#xcfffd;",
        expectedTokens = listOf(
            char('\uDAFF'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+D0000`() = assertTokenization(
        input = "&#xd0000;",
        expectedTokens = listOf(
            char('\uDB00'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+DFFFD`() = assertTokenization(
        input = "&#xdfffd;",
        expectedTokens = listOf(
            char('\uDB3F'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+E0000`() = assertTokenization(
        input = "&#xe0000;",
        expectedTokens = listOf(
            char('\uDB40'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+EFFFD`() = assertTokenization(
        input = "&#xefffd;",
        expectedTokens = listOf(
            char('\uDB7F'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+F0000`() = assertTokenization(
        input = "&#xf0000;",
        expectedTokens = listOf(
            char('\uDB80'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+FFFFD`() = assertTokenization(
        input = "&#xffffd;",
        expectedTokens = listOf(
            char('\uDBBF'),
            char('\uDFFD')
        ),
    )

    @Test
    fun `Valid numeric entity character U+100000`() = assertTokenization(
        input = "&#x100000;",
        expectedTokens = listOf(
            char('\uDBC0'),
            char('\uDC00')
        ),
    )

    @Test
    fun `Valid numeric entity character U+10FFFD`() = assertTokenization(
        input = "&#x10fffd;",
        expectedTokens = listOf(
            char('\uDBFF'),
            char('\uDFFD')
        ),
    )
}