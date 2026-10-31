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

package com.theakram.helium.tokenizer

import kotlin.test.Test

/**
 * Contains all 1606 test from here:
 * https://github.com/html5lib/html5lib-tests/blob/master/tokenizer/test3.test
 *
 */
@Suppress("RemoveRedundantBackticks")
internal class TokenizerTest3 : TokenizerTestBase() {

    @Test
    fun `empty 0`() = assertTokenization(
        input = "",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `empty 1`() = assertTokenization(
        input = "",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `u0009 2`() = assertTokenization(
        input = "\t",
        expectedTokens = listOf(
            char('\t')
        )
    )

    @Test
    fun `u0009 3`() = assertTokenization(
        input = "\t",
        expectedTokens = listOf(
            char('\t')
        )
    )

    @Test
    fun `u000A 4`() = assertTokenization(
        input = "\n",
        expectedTokens = listOf(
            char('\n')
        )
    )

    @Test
    fun `u000A 5`() = assertTokenization(
        input = "\n",
        expectedTokens = listOf(
            char('\n')
        )
    )

    @Test
    fun `u000B 6`() = assertTokenization(
        input = "\u000b",
        expectedTokens = listOf(
            char('\u000b')
        )
    )

    @Test
    fun `u000B 7`() = assertTokenization(
        input = "\u000b",
        expectedTokens = listOf(
            char('\u000b')
        )
    )

    @Test
    fun `u000C 8`() = assertTokenization(
        input = "\u000c",
        expectedTokens = listOf(
            char('\u000c')
        )
    )

    @Test
    fun `u000C 9`() = assertTokenization(
        input = "\u000c",
        expectedTokens = listOf(
            char('\u000c')
        )
    )

    @Test
    fun `test 10`() = assertTokenization(
        input = " ",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `test 11`() = assertTokenization(
        input = " ",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `test 12`() = assertTokenization(
        input = "!",
        expectedTokens = listOf(
            char('!')
        )
    )

    @Test
    fun `test 13`() = assertTokenization(
        input = "!",
        expectedTokens = listOf(
            char('!')
        )
    )

    @Test
    fun `test 14`() = assertTokenization(
        input = "\"",
        expectedTokens = listOf(
            char('"')
        )
    )

    @Test
    fun `test 15`() = assertTokenization(
        input = "\"",
        expectedTokens = listOf(
            char('"')
        )
    )

    @Test
    fun `test 16`() = assertTokenization(
        input = "%",
        expectedTokens = listOf(
            char('%')
        )
    )

    @Test
    fun `test 17`() = assertTokenization(
        input = "%",
        expectedTokens = listOf(
            char('%')
        )
    )

    @Test
    fun `test 18`() = assertTokenization(
        input = "&",
        expectedTokens = listOf(
            char('&')
        )
    )

    @Test
    fun `test 19`() = assertTokenization(
        input = "&",
        expectedTokens = listOf(
            char('&')
        )
    )

    @Test
    fun `test 20`() = assertTokenization(
        input = "'",
        expectedTokens = listOf(
            char('\'')
        )
    )

    @Test
    fun `test 21`() = assertTokenization(
        input = "'",
        expectedTokens = listOf(
            char('\'')
        )
    )

    @Test
    fun `test 22`() = assertTokenization(
        input = ",",
        expectedTokens = listOf(
            char(',')
        )
    )

    @Test
    fun `test 23`() = assertTokenization(
        input = ",",
        expectedTokens = listOf(
            char(',')
        )
    )

    @Test
    fun `test 24`() = assertTokenization(
        input = "-",
        expectedTokens = listOf(
            char('-')
        )
    )

    @Test
    fun `test 25`() = assertTokenization(
        input = "-",
        expectedTokens = listOf(
            char('-')
        )
    )

    @Test
    fun `test 26`() = assertTokenization(
        input = ".",
        expectedTokens = listOf(
            char('.')
        )
    )

    @Test
    fun `test 27`() = assertTokenization(
        input = ".",
        expectedTokens = listOf(
            char('.')
        )
    )

    @Test
    fun `test 28`() = assertTokenization(
        input = "/",
        expectedTokens = listOf(
            char('/')
        )
    )

    @Test
    fun `test 29`() = assertTokenization(
        input = "/",
        expectedTokens = listOf(
            char('/')
        )
    )

    @Test
    fun `test 0 30`() = assertTokenization(
        input = "0",
        expectedTokens = listOf(
            char('0')
        )
    )

    @Test
    fun `test 0 31`() = assertTokenization(
        input = "0",
        expectedTokens = listOf(
            char('0')
        )
    )

    @Test
    fun `test 1 32`() = assertTokenization(
        input = "1",
        expectedTokens = listOf(
            char('1')
        )
    )

    @Test
    fun `test 1 33`() = assertTokenization(
        input = "1",
        expectedTokens = listOf(
            char('1')
        )
    )

    @Test
    fun `test 9 34`() = assertTokenization(
        input = "9",
        expectedTokens = listOf(
            char('9')
        )
    )

    @Test
    fun `test 9 35`() = assertTokenization(
        input = "9",
        expectedTokens = listOf(
            char('9')
        )
    )

    @Test
    fun `test 36`() = assertTokenization(
        input = ";",
        expectedTokens = listOf(
            char(';')
        )
    )

    @Test
    fun `test 37`() = assertTokenization(
        input = ";",
        expectedTokens = listOf(
            char(';')
        )
    )

    @Test
    fun `test 38`() = assertTokenization(
        input = ";=",
        expectedTokens = listOf(
            *chars(";=").toTypedArray()
        )
    )

    @Test
    fun `test 39`() = assertTokenization(
        input = ";=",
        expectedTokens = listOf(
            *chars(";=").toTypedArray()
        )
    )

    @Test
    fun `test 40`() = assertTokenization(
        input = ";>",
        expectedTokens = listOf(
            *chars(";>").toTypedArray()
        )
    )

    @Test
    fun `test 41`() = assertTokenization(
        input = ";>",
        expectedTokens = listOf(
            *chars(";>").toTypedArray()
        )
    )

    @Test
    fun `test 42`() = assertTokenization(
        input = ";?",
        expectedTokens = listOf(
            *chars(";?").toTypedArray()
        )
    )

    @Test
    fun `test 43`() = assertTokenization(
        input = ";?",
        expectedTokens = listOf(
            *chars(";?").toTypedArray()
        )
    )

    @Test
    fun `test 44`() = assertTokenization(
        input = ";@",
        expectedTokens = listOf(
            *chars(";@").toTypedArray()
        )
    )

    @Test
    fun `test 45`() = assertTokenization(
        input = ";@",
        expectedTokens = listOf(
            *chars(";@").toTypedArray()
        )
    )

    @Test
    fun `A 46`() = assertTokenization(
        input = ";A",
        expectedTokens = listOf(
            *chars(";A").toTypedArray()
        )
    )

    @Test
    fun `A 47`() = assertTokenization(
        input = ";A",
        expectedTokens = listOf(
            *chars(";A").toTypedArray()
        )
    )

    @Test
    fun `B 48`() = assertTokenization(
        input = ";B",
        expectedTokens = listOf(
            *chars(";B").toTypedArray()
        )
    )

    @Test
    fun `B 49`() = assertTokenization(
        input = ";B",
        expectedTokens = listOf(
            *chars(";B").toTypedArray()
        )
    )

    @Test
    fun `Y 50`() = assertTokenization(
        input = ";Y",
        expectedTokens = listOf(
            *chars(";Y").toTypedArray()
        )
    )

    @Test
    fun `Y 51`() = assertTokenization(
        input = ";Y",
        expectedTokens = listOf(
            *chars(";Y").toTypedArray()
        )
    )

    @Test
    fun `Z 52`() = assertTokenization(
        input = ";Z",
        expectedTokens = listOf(
            *chars(";Z").toTypedArray()
        )
    )

    @Test
    fun `Z 53`() = assertTokenization(
        input = ";Z",
        expectedTokens = listOf(
            *chars(";Z").toTypedArray()
        )
    )

    @Test
    fun `test 54`() = assertTokenization(
        input = ";`",
        expectedTokens = listOf(
            *chars(";`").toTypedArray()
        )
    )

    @Test
    fun `test 55`() = assertTokenization(
        input = ";`",
        expectedTokens = listOf(
            *chars(";`").toTypedArray()
        )
    )

    @Test
    fun `a 56`() = assertTokenization(
        input = ";a",
        expectedTokens = listOf(
            *chars(";a").toTypedArray()
        )
    )

    @Test
    fun `a 57`() = assertTokenization(
        input = ";a",
        expectedTokens = listOf(
            *chars(";a").toTypedArray()
        )
    )

    @Test
    fun `b 58`() = assertTokenization(
        input = ";b",
        expectedTokens = listOf(
            *chars(";b").toTypedArray()
        )
    )

    @Test
    fun `b 59`() = assertTokenization(
        input = ";b",
        expectedTokens = listOf(
            *chars(";b").toTypedArray()
        )
    )

    @Test
    fun `y 60`() = assertTokenization(
        input = ";y",
        expectedTokens = listOf(
            *chars(";y").toTypedArray()
        )
    )

    @Test
    fun `y 61`() = assertTokenization(
        input = ";y",
        expectedTokens = listOf(
            *chars(";y").toTypedArray()
        )
    )

    @Test
    fun `z 62`() = assertTokenization(
        input = ";z",
        expectedTokens = listOf(
            *chars(";z").toTypedArray()
        )
    )

    @Test
    fun `z 63`() = assertTokenization(
        input = ";z",
        expectedTokens = listOf(
            *chars(";z").toTypedArray()
        )
    )

    @Test
    fun `test 64`() = assertTokenization(
        input = ";{",
        expectedTokens = listOf(
            *chars(";{").toTypedArray()
        )
    )

    @Test
    fun `test 65`() = assertTokenization(
        input = ";{",
        expectedTokens = listOf(
            *chars(";{").toTypedArray()
        )
    )

    @Test
    fun `uDBC0uDC00 66`() = assertTokenization(
        input = ";\uDBC0\uDC00",
        expectedTokens = listOf(
            *chars(";\uDBC0\uDC00").toTypedArray()
        )
    )

    @Test
    fun `uDBC0uDC00 67`() = assertTokenization(
        input = ";\uDBC0\uDC00",
        expectedTokens = listOf(
            *chars(";\uDBC0\uDC00").toTypedArray()
        )
    )

    @Test
    fun `test 68`() = assertTokenization(
        input = "<",
        expectedTokens = listOf(
            char('<')
        )
    )

    @Test
    fun `u0000 69`() = assertTokenization(
        input = "<\u0000",
        expectedTokens = listOf(
            *chars("<\u0000").toTypedArray()
        )
    )

    @Test
    fun `u0009 70`() = assertTokenization(
        input = "<\t",
        expectedTokens = listOf(
            *chars("<\t").toTypedArray()
        )
    )

    @Test
    fun `u000A 71`() = assertTokenization(
        input = "<\n",
        expectedTokens = listOf(
            *chars("<\n").toTypedArray()
        )
    )

    @Test
    fun `u000B 72`() = assertTokenization(
        input = "<\u000b",
        expectedTokens = listOf(
            *chars("<\u000b").toTypedArray()
        )
    )

    @Test
    fun `u000C 73`() = assertTokenization(
        input = "<\u000c",
        expectedTokens = listOf(
            *chars("<\u000c").toTypedArray()
        )
    )

    @Test
    fun `test 74`() = assertTokenization(
        input = "< ",
        expectedTokens = listOf(
            *chars("< ").toTypedArray()
        )
    )

    @Test
    fun `test 75`() = assertTokenization(
        input = "<!",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `u0000 76`() = assertTokenization(
        input = "<!\u0000",
        expectedTokens = listOf(
            comment("�")
        )
    )

    @Test
    fun `u0009 77`() = assertTokenization(
        input = "<!\t",
        expectedTokens = listOf(
            comment("\t")
        )
    )

    @Test
    fun `u000A 78`() = assertTokenization(
        input = "<!\n",
        expectedTokens = listOf(
            comment("\n")
        )
    )

    @Test
    fun `u000B 79`() = assertTokenization(
        input = "<!\u000b",
        expectedTokens = listOf(
            comment("\u000b")
        )
    )

    @Test
    fun `u000C 80`() = assertTokenization(
        input = "<!\u000c",
        expectedTokens = listOf(
            comment("\u000c")
        )
    )

    @Test
    fun `test 81`() = assertTokenization(
        input = "<! ",
        expectedTokens = listOf(
            comment(" ")
        )
    )

    @Test
    fun `u0000 82`() = assertTokenization(
        input = "<! \u0000",
        expectedTokens = listOf(
            comment(" �")
        )
    )

    @Test
    fun `test 83`() = assertTokenization(
        input = "<!!",
        expectedTokens = listOf(
            comment("!")
        )
    )

    @Test
    fun `test 84`() = assertTokenization(
        input = "<!\"",
        expectedTokens = listOf(
            comment("\"")
        )
    )

    @Test
    fun `test 85`() = assertTokenization(
        input = "<!&",
        expectedTokens = listOf(
            comment("&")
        )
    )

    @Test
    fun `test 86`() = assertTokenization(
        input = "<!'",
        expectedTokens = listOf(
            comment("'")
        )
    )

    @Test
    fun `test 87`() = assertTokenization(
        input = "<!-",
        expectedTokens = listOf(
            comment("-")
        )
    )

    @Test
    fun `test 88`() = assertTokenization(
        input = "<!--",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `u0000 89`() = assertTokenization(
        input = "<!--\u0000",
        expectedTokens = listOf(
            comment("�")
        )
    )

    @Test
    fun `u0009 90`() = assertTokenization(
        input = "<!--\t",
        expectedTokens = listOf(
            comment("\t")
        )
    )

    @Test
    fun `u000A 91`() = assertTokenization(
        input = "<!--\n",
        expectedTokens = listOf(
            comment("\n")
        )
    )

    @Test
    fun `u000B 92`() = assertTokenization(
        input = "<!--\u000b",
        expectedTokens = listOf(
            comment("\u000b")
        )
    )

    @Test
    fun `u000C 93`() = assertTokenization(
        input = "<!--\u000c",
        expectedTokens = listOf(
            comment("\u000c")
        )
    )

    @Test
    fun `test 94`() = assertTokenization(
        input = "<!-- ",
        expectedTokens = listOf(
            comment(" ")
        )
    )

    @Test
    fun `u0000 95`() = assertTokenization(
        input = "<!-- \u0000",
        expectedTokens = listOf(
            comment(" �")
        )
    )

    @Test
    fun `u0009 96`() = assertTokenization(
        input = "<!-- \t",
        expectedTokens = listOf(
            comment(" \t")
        )
    )

    @Test
    fun `u000A 97`() = assertTokenization(
        input = "<!-- \n",
        expectedTokens = listOf(
            comment(" \n")
        )
    )

    @Test
    fun `u000B 98`() = assertTokenization(
        input = "<!-- \u000b",
        expectedTokens = listOf(
            comment(" \u000b")
        )
    )

    @Test
    fun `u000C 99`() = assertTokenization(
        input = "<!-- \u000c",
        expectedTokens = listOf(
            comment(" \u000c")
        )
    )

    @Test
    fun `test 100`() = assertTokenization(
        input = "<!--  ",
        expectedTokens = listOf(
            comment("  ")
        )
    )

    @Test
    fun `test 101`() = assertTokenization(
        input = "<!-- !",
        expectedTokens = listOf(
            comment(" !")
        )
    )

    @Test
    fun `test 102`() = assertTokenization(
        input = "<!-- \"",
        expectedTokens = listOf(
            comment(" \"")
        )
    )

    @Test
    fun `test 103`() = assertTokenization(
        input = "<!-- &",
        expectedTokens = listOf(
            comment(" &")
        )
    )

    @Test
    fun `test 104`() = assertTokenization(
        input = "<!-- '",
        expectedTokens = listOf(
            comment(" '")
        )
    )

    @Test
    fun `test 105`() = assertTokenization(
        input = "<!-- ,",
        expectedTokens = listOf(
            comment(" ,")
        )
    )

    @Test
    fun `test 106`() = assertTokenization(
        input = "<!-- -",
        expectedTokens = listOf(
            comment(" ")
        )
    )

    @Test
    fun `u0000 107`() = assertTokenization(
        input = "<!-- -\u0000",
        expectedTokens = listOf(
            comment(" -�")
        )
    )

    @Test
    fun `u0009 108`() = assertTokenization(
        input = "<!-- -\t",
        expectedTokens = listOf(
            comment(" -\t")
        )
    )

    @Test
    fun `u000A 109`() = assertTokenization(
        input = "<!-- -\n",
        expectedTokens = listOf(
            comment(" -\n")
        )
    )

    @Test
    fun `u000B 110`() = assertTokenization(
        input = "<!-- -\u000b",
        expectedTokens = listOf(
            comment(" -\u000b")
        )
    )

    @Test
    fun `u000C 111`() = assertTokenization(
        input = "<!-- -\u000c",
        expectedTokens = listOf(
            comment(" -\u000c")
        )
    )

    @Test
    fun `test 112`() = assertTokenization(
        input = "<!-- - ",
        expectedTokens = listOf(
            comment(" - ")
        )
    )

    @Test
    fun `test 113`() = assertTokenization(
        input = "<!-- -!",
        expectedTokens = listOf(
            comment(" -!")
        )
    )

    @Test
    fun `test 114`() = assertTokenization(
        input = "<!-- -\"",
        expectedTokens = listOf(
            comment(" -\"")
        )
    )

    @Test
    fun `test 115`() = assertTokenization(
        input = "<!-- -&",
        expectedTokens = listOf(
            comment(" -&")
        )
    )

    @Test
    fun `test 116`() = assertTokenization(
        input = "<!-- -'",
        expectedTokens = listOf(
            comment(" -'")
        )
    )

    @Test
    fun `test 117`() = assertTokenization(
        input = "<!-- -,",
        expectedTokens = listOf(
            comment(" -,")
        )
    )

    @Test
    fun `test 118`() = assertTokenization(
        input = "<!-- --",
        expectedTokens = listOf(
            comment(" ")
        )
    )

    @Test
    fun `test 119`() = assertTokenization(
        input = "<!-- -.",
        expectedTokens = listOf(
            comment(" -.")
        )
    )

    @Test
    fun `test 120`() = assertTokenization(
        input = "<!-- -/",
        expectedTokens = listOf(
            comment(" -/")
        )
    )

    @Test
    fun `test 0 121`() = assertTokenization(
        input = "<!-- -0",
        expectedTokens = listOf(
            comment(" -0")
        )
    )

    @Test
    fun `test 1 122`() = assertTokenization(
        input = "<!-- -1",
        expectedTokens = listOf(
            comment(" -1")
        )
    )

    @Test
    fun `test 9 123`() = assertTokenization(
        input = "<!-- -9",
        expectedTokens = listOf(
            comment(" -9")
        )
    )

    @Test
    fun `test 124`() = assertTokenization(
        input = "<!-- -<",
        expectedTokens = listOf(
            comment(" -<")
        )
    )

    @Test
    fun `test 125`() = assertTokenization(
        input = "<!-- -=",
        expectedTokens = listOf(
            comment(" -=")
        )
    )

    @Test
    fun `test 126`() = assertTokenization(
        input = "<!-- ->",
        expectedTokens = listOf(
            comment(" ->")
        )
    )

    @Test
    fun `test 127`() = assertTokenization(
        input = "<!-- -?",
        expectedTokens = listOf(
            comment(" -?")
        )
    )

    @Test
    fun `test 128`() = assertTokenization(
        input = "<!-- -@",
        expectedTokens = listOf(
            comment(" -@")
        )
    )

    @Test
    fun `A 129`() = assertTokenization(
        input = "<!-- -A",
        expectedTokens = listOf(
            comment(" -A")
        )
    )

    @Test
    fun `B 130`() = assertTokenization(
        input = "<!-- -B",
        expectedTokens = listOf(
            comment(" -B")
        )
    )

    @Test
    fun `Y 131`() = assertTokenization(
        input = "<!-- -Y",
        expectedTokens = listOf(
            comment(" -Y")
        )
    )

    @Test
    fun `Z 132`() = assertTokenization(
        input = "<!-- -Z",
        expectedTokens = listOf(
            comment(" -Z")
        )
    )

    @Test
    fun `test 133`() = assertTokenization(
        input = "<!-- -`",
        expectedTokens = listOf(
            comment(" -`")
        )
    )

    @Test
    fun `a 134`() = assertTokenization(
        input = "<!-- -a",
        expectedTokens = listOf(
            comment(" -a")
        )
    )

    @Test
    fun `b 135`() = assertTokenization(
        input = "<!-- -b",
        expectedTokens = listOf(
            comment(" -b")
        )
    )

    @Test
    fun `y 136`() = assertTokenization(
        input = "<!-- -y",
        expectedTokens = listOf(
            comment(" -y")
        )
    )

    @Test
    fun `z 137`() = assertTokenization(
        input = "<!-- -z",
        expectedTokens = listOf(
            comment(" -z")
        )
    )

    @Test
    fun `test 138`() = assertTokenization(
        input = "<!-- -{",
        expectedTokens = listOf(
            comment(" -{")
        )
    )

    @Test
    fun `uDBC0uDC00 139`() = assertTokenization(
        input = "<!-- -\uDBC0\uDC00",
        expectedTokens = listOf(
            comment(" -\uDBC0\uDC00")
        )
    )

    @Test
    fun `test 140`() = assertTokenization(
        input = "<!-- .",
        expectedTokens = listOf(
            comment(" .")
        )
    )

    @Test
    fun `test 141`() = assertTokenization(
        input = "<!-- /",
        expectedTokens = listOf(
            comment(" /")
        )
    )

    @Test
    fun `test 0 142`() = assertTokenization(
        input = "<!-- 0",
        expectedTokens = listOf(
            comment(" 0")
        )
    )

    @Test
    fun `test 1 143`() = assertTokenization(
        input = "<!-- 1",
        expectedTokens = listOf(
            comment(" 1")
        )
    )

    @Test
    fun `test 9 144`() = assertTokenization(
        input = "<!-- 9",
        expectedTokens = listOf(
            comment(" 9")
        )
    )

    @Test
    fun `test 145`() = assertTokenization(
        input = "<!-- <",
        expectedTokens = listOf(
            comment(" <")
        )
    )

    @Test
    fun `test 146`() = assertTokenization(
        input = "<!-- =",
        expectedTokens = listOf(
            comment(" =")
        )
    )

    @Test
    fun `test 147`() = assertTokenization(
        input = "<!-- >",
        expectedTokens = listOf(
            comment(" >")
        )
    )

    @Test
    fun `test 148`() = assertTokenization(
        input = "<!-- ?",
        expectedTokens = listOf(
            comment(" ?")
        )
    )

    @Test
    fun `test 149`() = assertTokenization(
        input = "<!-- @",
        expectedTokens = listOf(
            comment(" @")
        )
    )

    @Test
    fun `A 150`() = assertTokenization(
        input = "<!-- A",
        expectedTokens = listOf(
            comment(" A")
        )
    )

    @Test
    fun `B 151`() = assertTokenization(
        input = "<!-- B",
        expectedTokens = listOf(
            comment(" B")
        )
    )

    @Test
    fun `Y 152`() = assertTokenization(
        input = "<!-- Y",
        expectedTokens = listOf(
            comment(" Y")
        )
    )

    @Test
    fun `Z 153`() = assertTokenization(
        input = "<!-- Z",
        expectedTokens = listOf(
            comment(" Z")
        )
    )

    @Test
    fun `test 154`() = assertTokenization(
        input = "<!-- `",
        expectedTokens = listOf(
            comment(" `")
        )
    )

    @Test
    fun `a 155`() = assertTokenization(
        input = "<!-- a",
        expectedTokens = listOf(
            comment(" a")
        )
    )

    @Test
    fun `b 156`() = assertTokenization(
        input = "<!-- b",
        expectedTokens = listOf(
            comment(" b")
        )
    )

    @Test
    fun `y 157`() = assertTokenization(
        input = "<!-- y",
        expectedTokens = listOf(
            comment(" y")
        )
    )

    @Test
    fun `z 158`() = assertTokenization(
        input = "<!-- z",
        expectedTokens = listOf(
            comment(" z")
        )
    )

    @Test
    fun `test 159`() = assertTokenization(
        input = "<!-- {",
        expectedTokens = listOf(
            comment(" {")
        )
    )

    @Test
    fun `uDBC0uDC00 160`() = assertTokenization(
        input = "<!-- \uDBC0\uDC00",
        expectedTokens = listOf(
            comment(" \uDBC0\uDC00")
        )
    )

    @Test
    fun `test 161`() = assertTokenization(
        input = "<!--!",
        expectedTokens = listOf(
            comment("!")
        )
    )

    @Test
    fun `test 162`() = assertTokenization(
        input = "<!--\"",
        expectedTokens = listOf(
            comment("\"")
        )
    )

    @Test
    fun `test 163`() = assertTokenization(
        input = "<!--&",
        expectedTokens = listOf(
            comment("&")
        )
    )

    @Test
    fun `test 164`() = assertTokenization(
        input = "<!--'",
        expectedTokens = listOf(
            comment("'")
        )
    )

    @Test
    fun `test 165`() = assertTokenization(
        input = "<!--,",
        expectedTokens = listOf(
            comment(",")
        )
    )

    @Test
    fun `test 166`() = assertTokenization(
        input = "<!---",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `u0000 167`() = assertTokenization(
        input = "<!---\u0000",
        expectedTokens = listOf(
            comment("-�")
        )
    )

    @Test
    fun `u0009 168`() = assertTokenization(
        input = "<!---\t",
        expectedTokens = listOf(
            comment("-\t")
        )
    )

    @Test
    fun `u000A 169`() = assertTokenization(
        input = "<!---\n",
        expectedTokens = listOf(
            comment("-\n")
        )
    )

    @Test
    fun `u000B 170`() = assertTokenization(
        input = "<!---\u000b",
        expectedTokens = listOf(
            comment("-\u000b")
        )
    )

    @Test
    fun `u000C 171`() = assertTokenization(
        input = "<!---\u000c",
        expectedTokens = listOf(
            comment("-\u000c")
        )
    )

    @Test
    fun `test 172`() = assertTokenization(
        input = "<!--- ",
        expectedTokens = listOf(
            comment("- ")
        )
    )

    @Test
    fun `test 173`() = assertTokenization(
        input = "<!---!",
        expectedTokens = listOf(
            comment("-!")
        )
    )

    @Test
    fun `test 174`() = assertTokenization(
        input = "<!---\"",
        expectedTokens = listOf(
            comment("-\"")
        )
    )

    @Test
    fun `test 175`() = assertTokenization(
        input = "<!---&",
        expectedTokens = listOf(
            comment("-&")
        )
    )

    @Test
    fun `test 176`() = assertTokenization(
        input = "<!---'",
        expectedTokens = listOf(
            comment("-'")
        )
    )

    @Test
    fun `test 177`() = assertTokenization(
        input = "<!---,",
        expectedTokens = listOf(
            comment("-,")
        )
    )

    @Test
    fun `test 178`() = assertTokenization(
        input = "<!----",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `u0000 179`() = assertTokenization(
        input = "<!----\u0000",
        expectedTokens = listOf(
            comment("--�")
        )
    )

    @Test
    fun `u0009 180`() = assertTokenization(
        input = "<!----\t",
        expectedTokens = listOf(
            comment("--\t")
        )
    )

    @Test
    fun `u000A 181`() = assertTokenization(
        input = "<!----\n",
        expectedTokens = listOf(
            comment("--\n")
        )
    )

    @Test
    fun `u000B 182`() = assertTokenization(
        input = "<!----\u000b",
        expectedTokens = listOf(
            comment("--\u000b")
        )
    )

    @Test
    fun `u000C 183`() = assertTokenization(
        input = "<!----\u000c",
        expectedTokens = listOf(
            comment("--\u000c")
        )
    )

    @Test
    fun `test 184`() = assertTokenization(
        input = "<!---- ",
        expectedTokens = listOf(
            comment("-- ")
        )
    )

    @Test
    fun `test 185`() = assertTokenization(
        input = "<!---- -",
        expectedTokens = listOf(
            comment("-- ")
        )
    )

    @Test
    fun `test 186`() = assertTokenization(
        input = "<!---- --",
        expectedTokens = listOf(
            comment("-- ")
        )
    )

    @Test
    fun `test 187`() = assertTokenization(
        input = "<!---- -->",
        expectedTokens = listOf(
            comment("-- ")
        )
    )

    @Test
    fun `test 188`() = assertTokenization(
        input = "<!----  -->",
        expectedTokens = listOf(
            comment("--  ")
        )
    )

    @Test
    fun `a 189`() = assertTokenization(
        input = "<!---- a-->",
        expectedTokens = listOf(
            comment("-- a")
        )
    )

    @Test
    fun `test 190`() = assertTokenization(
        input = "<!----!",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `test 191`() = assertTokenization(
        input = "<!----!>",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `test 192`() = assertTokenization(
        input = "<!----! >",
        expectedTokens = listOf(
            comment("--! >")
        )
    )

    @Test
    fun `LF 193`() = assertTokenization(
        input = "<!----!\n>",
        expectedTokens = listOf(
            comment("--!\n>")
        )
    )

    @Test
    fun `CR 194`() = assertTokenization(
        input = "<!----!\r>",
        expectedTokens = listOf(
            comment("--!\n>")
        )
    )

    @Test
    fun `CRLF 195`() = assertTokenization(
        input = "<!----!\r\n>",
        expectedTokens = listOf(
            comment("--!\n>")
        )
    )

    @Test
    fun `a 196`() = assertTokenization(
        input = "<!----!a",
        expectedTokens = listOf(
            comment("--!a")
        )
    )

    @Test
    fun `a 197`() = assertTokenization(
        input = "<!----!a-",
        expectedTokens = listOf(
            comment("--!a")
        )
    )

    @Test
    fun `a 198`() = assertTokenization(
        input = "<!----!a--",
        expectedTokens = listOf(
            comment("--!a")
        )
    )

    @Test
    fun `a 199`() = assertTokenization(
        input = "<!----!a-->",
        expectedTokens = listOf(
            comment("--!a")
        )
    )

    @Test
    fun `test 200`() = assertTokenization(
        input = "<!----!-",
        expectedTokens = listOf(
            comment("--!")
        )
    )

    @Test
    fun `test 201`() = assertTokenization(
        input = "<!----!--",
        expectedTokens = listOf(
            comment("--!")
        )
    )

    @Test
    fun `test 202`() = assertTokenization(
        input = "<!----!-->",
        expectedTokens = listOf(
            comment("--!")
        )
    )

    @Test
    fun `test 203`() = assertTokenization(
        input = "<!----\"",
        expectedTokens = listOf(
            comment("--\"")
        )
    )

    @Test
    fun `test 204`() = assertTokenization(
        input = "<!----&",
        expectedTokens = listOf(
            comment("--&")
        )
    )

    @Test
    fun `test 205`() = assertTokenization(
        input = "<!----'",
        expectedTokens = listOf(
            comment("--'")
        )
    )

    @Test
    fun `test 206`() = assertTokenization(
        input = "<!----,",
        expectedTokens = listOf(
            comment("--,")
        )
    )

    @Test
    fun `test 207`() = assertTokenization(
        input = "<!-----",
        expectedTokens = listOf(
            comment("-")
        )
    )

    @Test
    fun `test 208`() = assertTokenization(
        input = "<!----.",
        expectedTokens = listOf(
            comment("--.")
        )
    )

    @Test
    fun `test 209`() = assertTokenization(
        input = "<!----/",
        expectedTokens = listOf(
            comment("--/")
        )
    )

    @Test
    fun `test 0 210`() = assertTokenization(
        input = "<!----0",
        expectedTokens = listOf(
            comment("--0")
        )
    )

    @Test
    fun `test 1 211`() = assertTokenization(
        input = "<!----1",
        expectedTokens = listOf(
            comment("--1")
        )
    )

    @Test
    fun `test 9 212`() = assertTokenization(
        input = "<!----9",
        expectedTokens = listOf(
            comment("--9")
        )
    )

    @Test
    fun `test 213`() = assertTokenization(
        input = "<!----<",
        expectedTokens = listOf(
            comment("--<")
        )
    )

    @Test
    fun `test 214`() = assertTokenization(
        input = "<!----=",
        expectedTokens = listOf(
            comment("--=")
        )
    )

    @Test
    fun `test 215`() = assertTokenization(
        input = "<!---->",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `test 216`() = assertTokenization(
        input = "<!----?",
        expectedTokens = listOf(
            comment("--?")
        )
    )

    @Test
    fun `test 217`() = assertTokenization(
        input = "<!----@",
        expectedTokens = listOf(
            comment("--@")
        )
    )

    @Test
    fun `A 218`() = assertTokenization(
        input = "<!----A",
        expectedTokens = listOf(
            comment("--A")
        )
    )

    @Test
    fun `B 219`() = assertTokenization(
        input = "<!----B",
        expectedTokens = listOf(
            comment("--B")
        )
    )

    @Test
    fun `Y 220`() = assertTokenization(
        input = "<!----Y",
        expectedTokens = listOf(
            comment("--Y")
        )
    )

    @Test
    fun `Z 221`() = assertTokenization(
        input = "<!----Z",
        expectedTokens = listOf(
            comment("--Z")
        )
    )

    @Test
    fun `test 222`() = assertTokenization(
        input = "<!----`",
        expectedTokens = listOf(
            comment("--`")
        )
    )

    @Test
    fun `a 223`() = assertTokenization(
        input = "<!----a",
        expectedTokens = listOf(
            comment("--a")
        )
    )

    @Test
    fun `b 224`() = assertTokenization(
        input = "<!----b",
        expectedTokens = listOf(
            comment("--b")
        )
    )

    @Test
    fun `y 225`() = assertTokenization(
        input = "<!----y",
        expectedTokens = listOf(
            comment("--y")
        )
    )

    @Test
    fun `z 226`() = assertTokenization(
        input = "<!----z",
        expectedTokens = listOf(
            comment("--z")
        )
    )

    @Test
    fun `test 227`() = assertTokenization(
        input = "<!----{",
        expectedTokens = listOf(
            comment("--{")
        )
    )

    @Test
    fun `uDBC0uDC00 228`() = assertTokenization(
        input = "<!----\uDBC0\uDC00",
        expectedTokens = listOf(
            comment("--\uDBC0\uDC00")
        )
    )

    @Test
    fun `test 229`() = assertTokenization(
        input = "<!---.",
        expectedTokens = listOf(
            comment("-.")
        )
    )

    @Test
    fun `test 230`() = assertTokenization(
        input = "<!---/",
        expectedTokens = listOf(
            comment("-/")
        )
    )

    @Test
    fun `test 0 231`() = assertTokenization(
        input = "<!---0",
        expectedTokens = listOf(
            comment("-0")
        )
    )

    @Test
    fun `test 1 232`() = assertTokenization(
        input = "<!---1",
        expectedTokens = listOf(
            comment("-1")
        )
    )

    @Test
    fun `test 9 233`() = assertTokenization(
        input = "<!---9",
        expectedTokens = listOf(
            comment("-9")
        )
    )

    @Test
    fun `test 234`() = assertTokenization(
        input = "<!---<",
        expectedTokens = listOf(
            comment("-<")
        )
    )

    @Test
    fun `test 235`() = assertTokenization(
        input = "<!---=",
        expectedTokens = listOf(
            comment("-=")
        )
    )

    @Test
    fun `test 236`() = assertTokenization(
        input = "<!---?",
        expectedTokens = listOf(
            comment("-?")
        )
    )

    @Test
    fun `test 237`() = assertTokenization(
        input = "<!---@",
        expectedTokens = listOf(
            comment("-@")
        )
    )

    @Test
    fun `A 238`() = assertTokenization(
        input = "<!---A",
        expectedTokens = listOf(
            comment("-A")
        )
    )

    @Test
    fun `B 239`() = assertTokenization(
        input = "<!---B",
        expectedTokens = listOf(
            comment("-B")
        )
    )

    @Test
    fun `Y 240`() = assertTokenization(
        input = "<!---Y",
        expectedTokens = listOf(
            comment("-Y")
        )
    )

    @Test
    fun `Z 241`() = assertTokenization(
        input = "<!---Z",
        expectedTokens = listOf(
            comment("-Z")
        )
    )

    @Test
    fun `test 242`() = assertTokenization(
        input = "<!---`",
        expectedTokens = listOf(
            comment("-`")
        )
    )

    @Test
    fun `a 243`() = assertTokenization(
        input = "<!---a",
        expectedTokens = listOf(
            comment("-a")
        )
    )

    @Test
    fun `b 244`() = assertTokenization(
        input = "<!---b",
        expectedTokens = listOf(
            comment("-b")
        )
    )

    @Test
    fun `y 245`() = assertTokenization(
        input = "<!---y",
        expectedTokens = listOf(
            comment("-y")
        )
    )

    @Test
    fun `z 246`() = assertTokenization(
        input = "<!---z",
        expectedTokens = listOf(
            comment("-z")
        )
    )

    @Test
    fun `test 247`() = assertTokenization(
        input = "<!---{",
        expectedTokens = listOf(
            comment("-{")
        )
    )

    @Test
    fun `uDBC0uDC00 248`() = assertTokenization(
        input = "<!---\uDBC0\uDC00",
        expectedTokens = listOf(
            comment("-\uDBC0\uDC00")
        )
    )

    @Test
    fun `test 249`() = assertTokenization(
        input = "<!--.",
        expectedTokens = listOf(
            comment(".")
        )
    )

    @Test
    fun `test 250`() = assertTokenization(
        input = "<!--/",
        expectedTokens = listOf(
            comment("/")
        )
    )

    @Test
    fun `test 0 251`() = assertTokenization(
        input = "<!--0",
        expectedTokens = listOf(
            comment("0")
        )
    )

    @Test
    fun `test 1 252`() = assertTokenization(
        input = "<!--1",
        expectedTokens = listOf(
            comment("1")
        )
    )

    @Test
    fun `test 9 253`() = assertTokenization(
        input = "<!--9",
        expectedTokens = listOf(
            comment("9")
        )
    )

    @Test
    fun `test 254`() = assertTokenization(
        input = "<!--<",
        expectedTokens = listOf(
            comment("<")
        )
    )

    @Test
    fun `test 255`() = assertTokenization(
        input = "<!--=",
        expectedTokens = listOf(
            comment("=")
        )
    )

    @Test
    fun `test 256`() = assertTokenization(
        input = "<!--?",
        expectedTokens = listOf(
            comment("?")
        )
    )

    @Test
    fun `test 257`() = assertTokenization(
        input = "<!--@",
        expectedTokens = listOf(
            comment("@")
        )
    )

    @Test
    fun `A 258`() = assertTokenization(
        input = "<!--A",
        expectedTokens = listOf(
            comment("A")
        )
    )

    @Test
    fun `B 259`() = assertTokenization(
        input = "<!--B",
        expectedTokens = listOf(
            comment("B")
        )
    )

    @Test
    fun `Y 260`() = assertTokenization(
        input = "<!--Y",
        expectedTokens = listOf(
            comment("Y")
        )
    )

    @Test
    fun `Z 261`() = assertTokenization(
        input = "<!--Z",
        expectedTokens = listOf(
            comment("Z")
        )
    )

    @Test
    fun `test 262`() = assertTokenization(
        input = "<!--`",
        expectedTokens = listOf(
            comment("`")
        )
    )

    @Test
    fun `a 263`() = assertTokenization(
        input = "<!--a",
        expectedTokens = listOf(
            comment("a")
        )
    )

    @Test
    fun `b 264`() = assertTokenization(
        input = "<!--b",
        expectedTokens = listOf(
            comment("b")
        )
    )

    @Test
    fun `y 265`() = assertTokenization(
        input = "<!--y",
        expectedTokens = listOf(
            comment("y")
        )
    )

    @Test
    fun `z 266`() = assertTokenization(
        input = "<!--z",
        expectedTokens = listOf(
            comment("z")
        )
    )

    @Test
    fun `test 267`() = assertTokenization(
        input = "<!--{",
        expectedTokens = listOf(
            comment("{")
        )
    )

    @Test
    fun `uDBC0uDC00 268`() = assertTokenization(
        input = "<!--\uDBC0\uDC00",
        expectedTokens = listOf(
            comment("\uDBC0\uDC00")
        )
    )

    @Test
    fun `test 269`() = assertTokenization(
        input = "<!/",
        expectedTokens = listOf(
            comment("/")
        )
    )

    @Test
    fun `test 0 270`() = assertTokenization(
        input = "<!0",
        expectedTokens = listOf(
            comment("0")
        )
    )

    @Test
    fun `test 1 271`() = assertTokenization(
        input = "<!1",
        expectedTokens = listOf(
            comment("1")
        )
    )

    @Test
    fun `test 9 272`() = assertTokenization(
        input = "<!9",
        expectedTokens = listOf(
            comment("9")
        )
    )

    @Test
    fun `test 273`() = assertTokenization(
        input = "<!<",
        expectedTokens = listOf(
            comment("<")
        )
    )

    @Test
    fun `test 274`() = assertTokenization(
        input = "<!=",
        expectedTokens = listOf(
            comment("=")
        )
    )

    @Test
    fun `test 275`() = assertTokenization(
        input = "<!>",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `test 276`() = assertTokenization(
        input = "<!?",
        expectedTokens = listOf(
            comment("?")
        )
    )

    @Test
    fun `test 277`() = assertTokenization(
        input = "<!@",
        expectedTokens = listOf(
            comment("@")
        )
    )

    @Test
    fun `A 278`() = assertTokenization(
        input = "<!A",
        expectedTokens = listOf(
            comment("A")
        )
    )

    @Test
    fun `B 279`() = assertTokenization(
        input = "<!B",
        expectedTokens = listOf(
            comment("B")
        )
    )

    @Test
    fun `DOCTYPE 280`() = assertTokenization(
        input = "<!DOCTYPE",
        expectedTokens = listOf(
            doctype(name = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEu0000 281`() = assertTokenization(
        input = "<!DOCTYPE\u0000",
        expectedTokens = listOf(
            doctype(name = "�", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEu0008 282`() = assertTokenization(
        input = "<!DOCTYPE\u0008",
        expectedTokens = listOf(
            doctype(name = "\u0008", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEu0009 283`() = assertTokenization(
        input = "<!DOCTYPE\t",
        expectedTokens = listOf(
            doctype(name = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEu000A 284`() = assertTokenization(
        input = "<!DOCTYPE\n",
        expectedTokens = listOf(
            doctype(name = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEu000B 285`() = assertTokenization(
        input = "<!DOCTYPE\u000b",
        expectedTokens = listOf(
            doctype(name = "\u000b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEu000C 286`() = assertTokenization(
        input = "<!DOCTYPE\u000c",
        expectedTokens = listOf(
            doctype(name = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEu000D 287`() = assertTokenization(
        input = "<!DOCTYPE\r",
        expectedTokens = listOf(
            doctype(name = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEu001F 288`() = assertTokenization(
        input = "<!DOCTYPE\u001f",
        expectedTokens = listOf(
            doctype(name = "\u001f", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 289`() = assertTokenization(
        input = "<!DOCTYPE ",
        expectedTokens = listOf(
            doctype(name = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE u0000 290`() = assertTokenization(
        input = "<!DOCTYPE \u0000",
        expectedTokens = listOf(
            doctype(name = "�", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE u0008 291`() = assertTokenization(
        input = "<!DOCTYPE \u0008",
        expectedTokens = listOf(
            doctype(name = "\u0008", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE u0009 292`() = assertTokenization(
        input = "<!DOCTYPE \t",
        expectedTokens = listOf(
            doctype(name = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE u000A 293`() = assertTokenization(
        input = "<!DOCTYPE \n",
        expectedTokens = listOf(
            doctype(name = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE u000B 294`() = assertTokenization(
        input = "<!DOCTYPE \u000b",
        expectedTokens = listOf(
            doctype(name = "\u000b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE u000C 295`() = assertTokenization(
        input = "<!DOCTYPE \u000c",
        expectedTokens = listOf(
            doctype(name = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE u000D 296`() = assertTokenization(
        input = "<!DOCTYPE \r",
        expectedTokens = listOf(
            doctype(name = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE u001F 297`() = assertTokenization(
        input = "<!DOCTYPE \u001f",
        expectedTokens = listOf(
            doctype(name = "\u001f", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 298`() = assertTokenization(
        input = "<!DOCTYPE  ",
        expectedTokens = listOf(
            doctype(name = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 299`() = assertTokenization(
        input = "<!DOCTYPE !",
        expectedTokens = listOf(
            doctype(name = "!", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 300`() = assertTokenization(
        input = "<!DOCTYPE \"",
        expectedTokens = listOf(
            doctype(name = "\"", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 301`() = assertTokenization(
        input = "<!DOCTYPE &",
        expectedTokens = listOf(
            doctype(name = "&", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 302`() = assertTokenization(
        input = "<!DOCTYPE '",
        expectedTokens = listOf(
            doctype(name = "'", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 303`() = assertTokenization(
        input = "<!DOCTYPE -",
        expectedTokens = listOf(
            doctype(name = "-", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 304`() = assertTokenization(
        input = "<!DOCTYPE /",
        expectedTokens = listOf(
            doctype(name = "/", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 0 305`() = assertTokenization(
        input = "<!DOCTYPE 0",
        expectedTokens = listOf(
            doctype(name = "0", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 1 306`() = assertTokenization(
        input = "<!DOCTYPE 1",
        expectedTokens = listOf(
            doctype(name = "1", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 9 307`() = assertTokenization(
        input = "<!DOCTYPE 9",
        expectedTokens = listOf(
            doctype(name = "9", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 308`() = assertTokenization(
        input = "<!DOCTYPE <",
        expectedTokens = listOf(
            doctype(name = "<", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 309`() = assertTokenization(
        input = "<!DOCTYPE =",
        expectedTokens = listOf(
            doctype(name = "=", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 310`() = assertTokenization(
        input = "<!DOCTYPE >",
        expectedTokens = listOf(
            doctype(name = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 311`() = assertTokenization(
        input = "<!DOCTYPE ?",
        expectedTokens = listOf(
            doctype(name = "?", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 312`() = assertTokenization(
        input = "<!DOCTYPE @",
        expectedTokens = listOf(
            doctype(name = "@", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE A 313`() = assertTokenization(
        input = "<!DOCTYPE A",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE B 314`() = assertTokenization(
        input = "<!DOCTYPE B",
        expectedTokens = listOf(
            doctype(name = "b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE Y 315`() = assertTokenization(
        input = "<!DOCTYPE Y",
        expectedTokens = listOf(
            doctype(name = "y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE Z 316`() = assertTokenization(
        input = "<!DOCTYPE Z",
        expectedTokens = listOf(
            doctype(name = "z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 317`() = assertTokenization(
        input = "<!DOCTYPE [",
        expectedTokens = listOf(
            doctype(name = "[", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 318`() = assertTokenization(
        input = "<!DOCTYPE `",
        expectedTokens = listOf(
            doctype(name = "`", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 319`() = assertTokenization(
        input = "<!DOCTYPE a",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE au0000 320`() = assertTokenization(
        input = "<!DOCTYPE a\u0000",
        expectedTokens = listOf(
            doctype(name = "a�", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE au0008 321`() = assertTokenization(
        input = "<!DOCTYPE a\u0008",
        expectedTokens = listOf(
            doctype(name = "a\u0008", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE au0009 322`() = assertTokenization(
        input = "<!DOCTYPE a\t",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE au000A 323`() = assertTokenization(
        input = "<!DOCTYPE a\n",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE au000B 324`() = assertTokenization(
        input = "<!DOCTYPE a\u000b",
        expectedTokens = listOf(
            doctype(name = "a\u000b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE au000C 325`() = assertTokenization(
        input = "<!DOCTYPE a\u000c",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE au000D 326`() = assertTokenization(
        input = "<!DOCTYPE a\r",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE au001F 327`() = assertTokenization(
        input = "<!DOCTYPE a\u001f",
        expectedTokens = listOf(
            doctype(name = "a\u001f", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 328`() = assertTokenization(
        input = "<!DOCTYPE a ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a u0000 329`() = assertTokenization(
        input = "<!DOCTYPE a \u0000",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a u0008 330`() = assertTokenization(
        input = "<!DOCTYPE a \u0008",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a u0009 331`() = assertTokenization(
        input = "<!DOCTYPE a \t",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a u000A 332`() = assertTokenization(
        input = "<!DOCTYPE a \n",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a u000B 333`() = assertTokenization(
        input = "<!DOCTYPE a \u000b",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a u000C 334`() = assertTokenization(
        input = "<!DOCTYPE a \u000c",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a u000D 335`() = assertTokenization(
        input = "<!DOCTYPE a \r",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a u001F 336`() = assertTokenization(
        input = "<!DOCTYPE a \u001f",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 337`() = assertTokenization(
        input = "<!DOCTYPE a  ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 338`() = assertTokenization(
        input = "<!DOCTYPE a !",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 339`() = assertTokenization(
        input = "<!DOCTYPE a \"",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 340`() = assertTokenization(
        input = "<!DOCTYPE a &",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 341`() = assertTokenization(
        input = "<!DOCTYPE a '",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 342`() = assertTokenization(
        input = "<!DOCTYPE a -",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 343`() = assertTokenization(
        input = "<!DOCTYPE a /",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 0 344`() = assertTokenization(
        input = "<!DOCTYPE a 0",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 1 345`() = assertTokenization(
        input = "<!DOCTYPE a 1",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 9 346`() = assertTokenization(
        input = "<!DOCTYPE a 9",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 347`() = assertTokenization(
        input = "<!DOCTYPE a <",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 348`() = assertTokenization(
        input = "<!DOCTYPE a =",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 349`() = assertTokenization(
        input = "<!DOCTYPE a >",
        expectedTokens = listOf(
            doctype(name = "a")
        )
    )

    @Test
    fun `DOCTYPE a 350`() = assertTokenization(
        input = "<!DOCTYPE a ?",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 351`() = assertTokenization(
        input = "<!DOCTYPE a @",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a A 352`() = assertTokenization(
        input = "<!DOCTYPE a A",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a B 353`() = assertTokenization(
        input = "<!DOCTYPE a B",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 354`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu0000 355`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\u0000",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu0008 356`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\u0008",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu0009 357`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\t",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000A 358`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\n",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000B 359`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\u000b",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000C 360`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\u000c",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000D 361`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\r",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu001F 362`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\u001f",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 363`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 364`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC!",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 365`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu0000 366`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\u0000",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "�", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu0009 367`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\t",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\t", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000A 368`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\n",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\n", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000B 369`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\u000b",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\u000b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000C 370`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\u000c",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\u000c", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 371`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\" ",
        expectedTokens = listOf(
            doctype(name = "a", publicId = " ", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 372`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"!",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "!", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 373`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\"",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu0000 374`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\"\u0000",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC u0000 375`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\" \u0000",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 376`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"#",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "#", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 377`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"&",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "&", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 378`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"'",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "'", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 379`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"-",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "-", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 380`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"/",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "/", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC0 381`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"0",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "0", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC1 382`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"1",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "1", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC9 383`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"9",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "9", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 384`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"<",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "<", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 385`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"=",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "=", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 386`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\">",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 387`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"?",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "?", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 388`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"@",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "@", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICA 389`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"A",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "A", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICB 390`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"B",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "B", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICY 391`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"Y",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "Y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICZ 392`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"Z",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "Z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 393`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"`",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "`", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICa 394`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"a",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICb 395`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"b",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICy 396`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"y",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICz 397`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"z",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 398`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"{",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "{", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICuDBC0uDC00 399`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\uDBC0\uDC00", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 400`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC#",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 401`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC&",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 402`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu0000 403`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'\u0000",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "�", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu0009 404`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'\t",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\t", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000A 405`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'\n",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\n", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000B 406`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'\u000b",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\u000b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000C 407`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'\u000c",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\u000c", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 408`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC' ",
        expectedTokens = listOf(
            doctype(name = "a", publicId = " ", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 409`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'!",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "!", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 410`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'\"",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\"", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 411`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'&",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "&", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 412`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu0000 413`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\u0000",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu0008 414`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\u0008",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu0009 415`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\t",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000A 416`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\n",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000B 417`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\u000b",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000C 418`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\u000c",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu000D 419`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\r",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu001F 420`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\u001f",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 421`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'' ",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 422`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''!",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 423`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\"",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 424`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''#",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 425`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''&",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 426`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'''",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICu0000 427`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''''\u0000",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a PUBLICxu0000 428`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''''x\u0000",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC u0000 429`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'''' \u0000",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC xu0000 430`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'''' x\u0000",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 431`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''(",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 432`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''-",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 433`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''/",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC0 434`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''0",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC1 435`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''1",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC9 436`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''9",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 437`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''<",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 438`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''=",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 439`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''>",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "")
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 440`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''?",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 441`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''@",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICA 442`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''A",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICB 443`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''B",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICY 444`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''Y",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICZ 445`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''Z",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 446`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''`",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICa 447`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''a",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICb 448`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''b",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICy 449`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''y",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICz 450`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''z",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 451`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''{",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICuDBC0uDC00 452`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 453`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'(",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "(", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 454`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'-",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "-", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 455`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'/",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "/", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC0 456`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'0",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "0", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC1 457`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'1",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "1", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC9 458`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'9",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "9", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 459`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'<",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "<", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 460`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'=",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "=", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 461`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'>",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 462`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'?",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "?", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 463`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'@",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "@", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICA 464`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'A",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "A", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICB 465`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'B",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "B", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICY 466`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'Y",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "Y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICZ 467`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'Z",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "Z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 468`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'`",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "`", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICa 469`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'a",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICb 470`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'b",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICy 471`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'y",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICz 472`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'z",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 473`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'{",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "{", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICuDBC0uDC00 474`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\uDBC0\uDC00", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 475`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC(",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 476`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC-",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 477`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC/",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC0 478`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC0",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC1 479`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC1",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC9 480`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC9",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 481`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC<",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 482`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC=",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 483`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC>",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 484`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC?",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 485`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC@",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICA 486`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICA",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICB 487`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICB",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICY 488`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICY",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICZ 489`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICZ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 490`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC`",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICa 491`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICa",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICb 492`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICb",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICy 493`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICy",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICz 494`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICz",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLIC 495`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC{",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a PUBLICuDBC0uDC00 496`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 497`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu0000 498`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\u0000",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM u0000 499`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM \u0000",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM xu0000 500`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM \u0000",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu0008 501`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\u0008",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu0009 502`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\t",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000A 503`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\n",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000B 504`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\u000b",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000C 505`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\u000c",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000D 506`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\r",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu001F 507`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\u001f",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 508`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 509`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM!",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 510`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu0000 511`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"\u0000",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "�", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu0009 512`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"\t",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\t", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000A 513`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"\n",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\n", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000B 514`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"\u000b",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\u000b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000C 515`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"\u000c",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\u000c", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 516`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\" ",
        expectedTokens = listOf(
            doctype(name = "a", systemId = " ", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 517`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"!",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "!", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 518`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"\"",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 519`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"#",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "#", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 520`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"&",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "&", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 521`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"'",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "'", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 522`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"-",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "-", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 523`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"/",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "/", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM0 524`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"0",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "0", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM1 525`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"1",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "1", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM9 526`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"9",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "9", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 527`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"<",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "<", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 528`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"=",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "=", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 529`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\">",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 530`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"?",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "?", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 531`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"@",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "@", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMA 532`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"A",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "A", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMB 533`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"B",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "B", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMY 534`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"Y",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "Y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMZ 535`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"Z",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "Z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 536`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"`",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "`", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMa 537`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"a",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMb 538`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"b",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMy 539`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"y",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMz 540`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"z",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 541`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"{",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "{", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMuDBC0uDC00 542`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\uDBC0\uDC00", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 543`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM#",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 544`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM&",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 545`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu0000 546`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'\u0000",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "�", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu0009 547`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'\t",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\t", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000A 548`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'\n",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\n", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000B 549`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'\u000b",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\u000b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000C 550`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'\u000c",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\u000c", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 551`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM' ",
        expectedTokens = listOf(
            doctype(name = "a", systemId = " ", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 552`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'!",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "!", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 553`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'\"",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\"", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 554`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'&",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "&", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 555`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu0000 556`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\u0000",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu0008 557`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\u0008",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu0009 558`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\t",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000A 559`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\n",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000B 560`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\u000b",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000C 561`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\u000c",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu000D 562`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\r",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMu001F 563`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\u001f",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 564`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'' ",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM u0000 565`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'' \u0000",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM xu0000 566`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'' x\u0000",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 567`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''!",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 568`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\"",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 569`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''&",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 570`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'''",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 571`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''-",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 572`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''/",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM0 573`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''0",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM1 574`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''1",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM9 575`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''9",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 576`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''<",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 577`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''=",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 578`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''>",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 579`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''?",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 580`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''@",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMA 581`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''A",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMB 582`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''B",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMY 583`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''Y",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMZ 584`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''Z",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 585`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''`",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMa 586`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''a",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMb 587`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''b",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMy 588`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''y",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMz 589`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''z",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 590`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''{",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMuDBC0uDC00 591`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 592`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'(",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "(", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 593`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'-",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "-", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 594`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'/",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "/", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM0 595`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'0",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "0", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM1 596`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'1",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "1", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM9 597`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'9",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "9", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 598`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'<",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "<", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 599`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'=",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "=", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 600`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'>",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 601`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'?",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "?", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 602`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'@",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "@", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMA 603`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'A",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "A", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMB 604`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'B",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "B", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMY 605`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'Y",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "Y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMZ 606`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'Z",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "Z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 607`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'`",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "`", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMa 608`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'a",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMb 609`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'b",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMy 610`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'y",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMz 611`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'z",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 612`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'{",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "{", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMuDBC0uDC00 613`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\uDBC0\uDC00", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 614`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM(",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 615`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM-",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 616`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM/",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM0 617`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM0",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM1 618`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM1",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM9 619`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM9",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 620`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM<",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 621`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM=",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 622`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM>",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 623`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM?",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 624`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM@",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMA 625`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMA",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMB 626`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMB",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMY 627`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMY",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMZ 628`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMZ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 629`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM`",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMa 630`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMa",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMb 631`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMb",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMy 632`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMy",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMz 633`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMz",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEM 634`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM{",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a SYSTEMuDBC0uDC00 635`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a Y 636`() = assertTokenization(
        input = "<!DOCTYPE a Y",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a Z 637`() = assertTokenization(
        input = "<!DOCTYPE a Z",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 638`() = assertTokenization(
        input = "<!DOCTYPE a `",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 639`() = assertTokenization(
        input = "<!DOCTYPE a a",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a au0000 640`() = assertTokenization(
        input = "<!DOCTYPE a a\u0000",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a au0009 641`() = assertTokenization(
        input = "<!DOCTYPE a a\t",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a au000A 642`() = assertTokenization(
        input = "<!DOCTYPE a a\n",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a au000B 643`() = assertTokenization(
        input = "<!DOCTYPE a a\u000b",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a au000C 644`() = assertTokenization(
        input = "<!DOCTYPE a a\u000c",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 645`() = assertTokenization(
        input = "<!DOCTYPE a a ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 646`() = assertTokenization(
        input = "<!DOCTYPE a a!",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 647`() = assertTokenization(
        input = "<!DOCTYPE a a\"",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 648`() = assertTokenization(
        input = "<!DOCTYPE a a&",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 649`() = assertTokenization(
        input = "<!DOCTYPE a a'",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 650`() = assertTokenization(
        input = "<!DOCTYPE a a-",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 651`() = assertTokenization(
        input = "<!DOCTYPE a a/",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a0 652`() = assertTokenization(
        input = "<!DOCTYPE a a0",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a1 653`() = assertTokenization(
        input = "<!DOCTYPE a a1",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a9 654`() = assertTokenization(
        input = "<!DOCTYPE a a9",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 655`() = assertTokenization(
        input = "<!DOCTYPE a a<",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 656`() = assertTokenization(
        input = "<!DOCTYPE a a=",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 657`() = assertTokenization(
        input = "<!DOCTYPE a a>",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 658`() = assertTokenization(
        input = "<!DOCTYPE a a?",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 659`() = assertTokenization(
        input = "<!DOCTYPE a a@",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a aA 660`() = assertTokenization(
        input = "<!DOCTYPE a aA",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a aB 661`() = assertTokenization(
        input = "<!DOCTYPE a aB",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a aY 662`() = assertTokenization(
        input = "<!DOCTYPE a aY",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a aZ 663`() = assertTokenization(
        input = "<!DOCTYPE a aZ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 664`() = assertTokenization(
        input = "<!DOCTYPE a a`",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a aa 665`() = assertTokenization(
        input = "<!DOCTYPE a aa",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a ab 666`() = assertTokenization(
        input = "<!DOCTYPE a ab",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a ay 667`() = assertTokenization(
        input = "<!DOCTYPE a ay",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a az 668`() = assertTokenization(
        input = "<!DOCTYPE a az",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a a 669`() = assertTokenization(
        input = "<!DOCTYPE a a{",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a auDBC0uDC00 670`() = assertTokenization(
        input = "<!DOCTYPE a a\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a b 671`() = assertTokenization(
        input = "<!DOCTYPE a b",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a y 672`() = assertTokenization(
        input = "<!DOCTYPE a y",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a z 673`() = assertTokenization(
        input = "<!DOCTYPE a z",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 674`() = assertTokenization(
        input = "<!DOCTYPE a {",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a uDBC0uDC00 675`() = assertTokenization(
        input = "<!DOCTYPE a \uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 676`() = assertTokenization(
        input = "<!DOCTYPE a!",
        expectedTokens = listOf(
            doctype(name = "a!", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 677`() = assertTokenization(
        input = "<!DOCTYPE a\"",
        expectedTokens = listOf(
            doctype(name = "a\"", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 678`() = assertTokenization(
        input = "<!DOCTYPE a&",
        expectedTokens = listOf(
            doctype(name = "a&", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 679`() = assertTokenization(
        input = "<!DOCTYPE a'",
        expectedTokens = listOf(
            doctype(name = "a'", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 680`() = assertTokenization(
        input = "<!DOCTYPE a-",
        expectedTokens = listOf(
            doctype(name = "a-", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 681`() = assertTokenization(
        input = "<!DOCTYPE a/",
        expectedTokens = listOf(
            doctype(name = "a/", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a0 682`() = assertTokenization(
        input = "<!DOCTYPE a0",
        expectedTokens = listOf(
            doctype(name = "a0", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a1 683`() = assertTokenization(
        input = "<!DOCTYPE a1",
        expectedTokens = listOf(
            doctype(name = "a1", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a9 684`() = assertTokenization(
        input = "<!DOCTYPE a9",
        expectedTokens = listOf(
            doctype(name = "a9", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 685`() = assertTokenization(
        input = "<!DOCTYPE a<",
        expectedTokens = listOf(
            doctype(name = "a<", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 686`() = assertTokenization(
        input = "<!DOCTYPE a=",
        expectedTokens = listOf(
            doctype(name = "a=", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 687`() = assertTokenization(
        input = "<!DOCTYPE a>",
        expectedTokens = listOf(
            doctype(name = "a")
        )
    )

    @Test
    fun `DOCTYPE a 688`() = assertTokenization(
        input = "<!DOCTYPE a?",
        expectedTokens = listOf(
            doctype(name = "a?", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 689`() = assertTokenization(
        input = "<!DOCTYPE a@",
        expectedTokens = listOf(
            doctype(name = "a@", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE aA 690`() = assertTokenization(
        input = "<!DOCTYPE aA",
        expectedTokens = listOf(
            doctype(name = "aa", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE aB 691`() = assertTokenization(
        input = "<!DOCTYPE aB",
        expectedTokens = listOf(
            doctype(name = "ab", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE aY 692`() = assertTokenization(
        input = "<!DOCTYPE aY",
        expectedTokens = listOf(
            doctype(name = "ay", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE aZ 693`() = assertTokenization(
        input = "<!DOCTYPE aZ",
        expectedTokens = listOf(
            doctype(name = "az", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 694`() = assertTokenization(
        input = "<!DOCTYPE a[",
        expectedTokens = listOf(
            doctype(name = "a[", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 695`() = assertTokenization(
        input = "<!DOCTYPE a`",
        expectedTokens = listOf(
            doctype(name = "a`", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE aa 696`() = assertTokenization(
        input = "<!DOCTYPE aa",
        expectedTokens = listOf(
            doctype(name = "aa", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE ab 697`() = assertTokenization(
        input = "<!DOCTYPE ab",
        expectedTokens = listOf(
            doctype(name = "ab", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE ay 698`() = assertTokenization(
        input = "<!DOCTYPE ay",
        expectedTokens = listOf(
            doctype(name = "ay", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE az 699`() = assertTokenization(
        input = "<!DOCTYPE az",
        expectedTokens = listOf(
            doctype(name = "az", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE a 700`() = assertTokenization(
        input = "<!DOCTYPE a{",
        expectedTokens = listOf(
            doctype(name = "a{", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE auDBC0uDC00 701`() = assertTokenization(
        input = "<!DOCTYPE a\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a\uDBC0\uDC00", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE b 702`() = assertTokenization(
        input = "<!DOCTYPE b",
        expectedTokens = listOf(
            doctype(name = "b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE y 703`() = assertTokenization(
        input = "<!DOCTYPE y",
        expectedTokens = listOf(
            doctype(name = "y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE z 704`() = assertTokenization(
        input = "<!DOCTYPE z",
        expectedTokens = listOf(
            doctype(name = "z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 705`() = assertTokenization(
        input = "<!DOCTYPE {",
        expectedTokens = listOf(
            doctype(name = "{", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE uDBC0uDC00 706`() = assertTokenization(
        input = "<!DOCTYPE \uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "\uDBC0\uDC00", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 707`() = assertTokenization(
        input = "<!DOCTYPE!",
        expectedTokens = listOf(
            doctype(name = "!", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 708`() = assertTokenization(
        input = "<!DOCTYPE\"",
        expectedTokens = listOf(
            doctype(name = "\"", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 709`() = assertTokenization(
        input = "<!DOCTYPE&",
        expectedTokens = listOf(
            doctype(name = "&", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 710`() = assertTokenization(
        input = "<!DOCTYPE'",
        expectedTokens = listOf(
            doctype(name = "'", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 711`() = assertTokenization(
        input = "<!DOCTYPE-",
        expectedTokens = listOf(
            doctype(name = "-", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 712`() = assertTokenization(
        input = "<!DOCTYPE/",
        expectedTokens = listOf(
            doctype(name = "/", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE0 713`() = assertTokenization(
        input = "<!DOCTYPE0",
        expectedTokens = listOf(
            doctype(name = "0", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE1 714`() = assertTokenization(
        input = "<!DOCTYPE1",
        expectedTokens = listOf(
            doctype(name = "1", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE9 715`() = assertTokenization(
        input = "<!DOCTYPE9",
        expectedTokens = listOf(
            doctype(name = "9", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 716`() = assertTokenization(
        input = "<!DOCTYPE<",
        expectedTokens = listOf(
            doctype(name = "<", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 717`() = assertTokenization(
        input = "<!DOCTYPE=",
        expectedTokens = listOf(
            doctype(name = "=", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 718`() = assertTokenization(
        input = "<!DOCTYPE>",
        expectedTokens = listOf(
            doctype(name = null, forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 719`() = assertTokenization(
        input = "<!DOCTYPE?",
        expectedTokens = listOf(
            doctype(name = "?", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 720`() = assertTokenization(
        input = "<!DOCTYPE@",
        expectedTokens = listOf(
            doctype(name = "@", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEA 721`() = assertTokenization(
        input = "<!DOCTYPEA",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEB 722`() = assertTokenization(
        input = "<!DOCTYPEB",
        expectedTokens = listOf(
            doctype(name = "b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEY 723`() = assertTokenization(
        input = "<!DOCTYPEY",
        expectedTokens = listOf(
            doctype(name = "y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEZ 724`() = assertTokenization(
        input = "<!DOCTYPEZ",
        expectedTokens = listOf(
            doctype(name = "z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 725`() = assertTokenization(
        input = "<!DOCTYPE`",
        expectedTokens = listOf(
            doctype(name = "`", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 726`() = assertTokenization(
        input = "<!DOCTYPEa",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEau0000 727`() = assertTokenization(
        input = "<!DOCTYPEa\u0000",
        expectedTokens = listOf(
            doctype(name = "a�", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEau0008 728`() = assertTokenization(
        input = "<!DOCTYPEa\u0008",
        expectedTokens = listOf(
            doctype(name = "a\u0008", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEau0009 729`() = assertTokenization(
        input = "<!DOCTYPEa\t",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEau000A 730`() = assertTokenization(
        input = "<!DOCTYPEa\n",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEau000B 731`() = assertTokenization(
        input = "<!DOCTYPEa\u000b",
        expectedTokens = listOf(
            doctype(name = "a\u000b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEau000C 732`() = assertTokenization(
        input = "<!DOCTYPEa\u000c",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEau000D 733`() = assertTokenization(
        input = "<!DOCTYPEa\r",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEau001F 734`() = assertTokenization(
        input = "<!DOCTYPEa\u001f",
        expectedTokens = listOf(
            doctype(name = "a\u001f", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 735`() = assertTokenization(
        input = "<!DOCTYPEa ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa u0000 736`() = assertTokenization(
        input = "<!DOCTYPEa \u0000",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa u0008 737`() = assertTokenization(
        input = "<!DOCTYPEa \u0008",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa u0009 738`() = assertTokenization(
        input = "<!DOCTYPEa \t",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa u000A 739`() = assertTokenization(
        input = "<!DOCTYPEa \n",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa u000B 740`() = assertTokenization(
        input = "<!DOCTYPEa \u000b",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa u000C 741`() = assertTokenization(
        input = "<!DOCTYPEa \u000c",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa u000D 742`() = assertTokenization(
        input = "<!DOCTYPEa \r",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa u001F 743`() = assertTokenization(
        input = "<!DOCTYPEa \u001f",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 744`() = assertTokenization(
        input = "<!DOCTYPEa  ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 745`() = assertTokenization(
        input = "<!DOCTYPEa !",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 746`() = assertTokenization(
        input = "<!DOCTYPEa \"",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 747`() = assertTokenization(
        input = "<!DOCTYPEa &",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 748`() = assertTokenization(
        input = "<!DOCTYPEa '",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 749`() = assertTokenization(
        input = "<!DOCTYPEa -",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 750`() = assertTokenization(
        input = "<!DOCTYPEa /",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 0 751`() = assertTokenization(
        input = "<!DOCTYPEa 0",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1 752`() = assertTokenization(
        input = "<!DOCTYPEa 1",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 9 753`() = assertTokenization(
        input = "<!DOCTYPEa 9",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 754`() = assertTokenization(
        input = "<!DOCTYPEa <",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 755`() = assertTokenization(
        input = "<!DOCTYPEa =",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 756`() = assertTokenization(
        input = "<!DOCTYPEa >",
        expectedTokens = listOf(
            doctype(name = "a")
        )
    )

    @Test
    fun `DOCTYPEa 757`() = assertTokenization(
        input = "<!DOCTYPEa ?",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 758`() = assertTokenization(
        input = "<!DOCTYPEa @",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa A 759`() = assertTokenization(
        input = "<!DOCTYPEa A",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa B 760`() = assertTokenization(
        input = "<!DOCTYPEa B",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 761`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu0000 762`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\u0000",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu0008 763`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\u0008",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu0009 764`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\t",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000A 765`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\n",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000B 766`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\u000b",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000C 767`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\u000c",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000D 768`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\r",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu001F 769`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\u001f",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 770`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 771`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC!",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 772`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu0000 773`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"\u0000",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "�", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu0009 774`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"\t",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\t", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000A 775`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"\n",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\n", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000B 776`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"\u000b",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\u000b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000C 777`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"\u000c",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\u000c", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 778`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\" ",
        expectedTokens = listOf(
            doctype(name = "a", publicId = " ", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 779`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"!",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "!", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 780`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"\"",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 781`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"#",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "#", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 782`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"&",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "&", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 783`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"'",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "'", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 784`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"-",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "-", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 785`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"/",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "/", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC0 786`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"0",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "0", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC1 787`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"1",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "1", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC9 788`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"9",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "9", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 789`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"<",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "<", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 790`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"=",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "=", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 791`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\">",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 792`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"?",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "?", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 793`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"@",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "@", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICA 794`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"A",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "A", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICB 795`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"B",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "B", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICY 796`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"Y",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "Y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICZ 797`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"Z",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "Z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 798`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"`",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "`", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICa 799`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"a",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICb 800`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"b",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICy 801`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"y",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICz 802`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"z",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 803`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"{",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "{", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICuDBC0uDC00 804`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\uDBC0\uDC00", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 805`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC#",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 806`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC&",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 807`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu0000 808`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'\u0000",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "�", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu0009 809`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'\t",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\t", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000A 810`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'\n",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\n", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000B 811`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'\u000b",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\u000b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000C 812`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'\u000c",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\u000c", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 813`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC' ",
        expectedTokens = listOf(
            doctype(name = "a", publicId = " ", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 814`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'!",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "!", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 815`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'\"",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\"", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 816`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'&",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "&", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 817`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu0000 818`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\u0000",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu0008 819`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\u0008",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu0009 820`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\t",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000A 821`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\n",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000B 822`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\u000b",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000C 823`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\u000c",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu000D 824`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\r",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICu001F 825`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\u001f",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 826`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'' ",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 827`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''!",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 828`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\"",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 829`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''#",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 830`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''&",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 831`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'''",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 832`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''(",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 833`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''-",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 834`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''/",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC0 835`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''0",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC1 836`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''1",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC9 837`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''9",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 838`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''<",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 839`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''=",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 840`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''>",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "")
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 841`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''?",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 842`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''@",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICA 843`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''A",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICB 844`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''B",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICY 845`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''Y",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICZ 846`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''Z",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 847`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''`",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICa 848`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''a",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICb 849`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''b",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICy 850`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''y",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICz 851`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''z",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 852`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''{",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICuDBC0uDC00 853`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 854`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'(",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "(", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 855`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'-",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "-", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 856`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'/",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "/", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC0 857`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'0",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "0", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC1 858`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'1",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "1", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC9 859`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'9",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "9", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 860`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'<",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "<", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 861`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'=",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "=", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 862`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'>",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 863`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'?",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "?", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 864`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'@",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "@", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICA 865`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'A",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "A", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICB 866`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'B",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "B", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICY 867`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'Y",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "Y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICZ 868`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'Z",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "Z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 869`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'`",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "`", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICa 870`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'a",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICb 871`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'b",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICy 872`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'y",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICz 873`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'z",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 874`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'{",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "{", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICuDBC0uDC00 875`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", publicId = "\uDBC0\uDC00", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 876`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC(",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 877`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC-",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 878`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC/",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC0 879`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC0",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC1 880`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC1",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC9 881`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC9",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 882`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC<",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 883`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC=",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 884`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC>",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 885`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC?",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 886`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC@",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICA 887`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICA",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICB 888`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICB",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICY 889`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICY",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICZ 890`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICZ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 891`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC`",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICa 892`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICa",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICb 893`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICb",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICy 894`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICy",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICz 895`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICz",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLIC 896`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC{",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa PUBLICuDBC0uDC00 897`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 898`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu0000 899`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\u0000",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu0008 900`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\u0008",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu0009 901`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\t",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000A 902`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\n",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000B 903`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\u000b",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000C 904`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\u000c",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000D 905`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\r",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu001F 906`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\u001f",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 907`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 908`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM!",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 909`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu0000 910`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"\u0000",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "�", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu0009 911`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"\t",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\t", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000A 912`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"\n",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\n", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000B 913`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"\u000b",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\u000b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000C 914`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"\u000c",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\u000c", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 915`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\" ",
        expectedTokens = listOf(
            doctype(name = "a", systemId = " ", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 916`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"!",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "!", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 917`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"\"",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 918`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"#",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "#", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 919`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"&",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "&", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 920`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"'",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "'", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 921`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"-",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "-", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 922`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"/",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "/", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM0 923`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"0",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "0", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM1 924`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"1",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "1", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM9 925`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"9",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "9", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 926`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"<",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "<", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 927`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"=",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "=", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 928`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\">",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 929`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"?",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "?", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 930`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"@",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "@", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMA 931`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"A",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "A", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMB 932`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"B",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "B", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMY 933`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"Y",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "Y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMZ 934`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"Z",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "Z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 935`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"`",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "`", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMa 936`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"a",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMb 937`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"b",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMy 938`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"y",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMz 939`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"z",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 940`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"{",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "{", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMuDBC0uDC00 941`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\uDBC0\uDC00", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 942`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM#",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 943`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM&",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 944`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu0000 945`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'\u0000",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "�", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu0009 946`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'\t",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\t", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000A 947`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'\n",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\n", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000B 948`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'\u000b",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\u000b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000C 949`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'\u000c",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\u000c", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 950`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM' ",
        expectedTokens = listOf(
            doctype(name = "a", systemId = " ", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 951`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'!",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "!", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 952`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'\"",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\"", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 953`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'&",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "&", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 954`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu0000 955`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\u0000",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu0008 956`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\u0008",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu0009 957`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\t",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000A 958`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\n",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000B 959`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\u000b",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000C 960`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\u000c",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu000D 961`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\r",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMu001F 962`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\u001f",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 963`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'' ",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 964`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''!",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 965`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\"",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 966`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''&",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 967`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'''",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 968`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''-",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 969`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''/",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM0 970`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''0",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM1 971`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''1",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM9 972`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''9",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 973`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''<",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 974`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''=",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 975`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''>",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 976`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''?",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 977`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''@",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMA 978`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''A",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMB 979`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''B",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMY 980`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''Y",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMZ 981`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''Z",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 982`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''`",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMa 983`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''a",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMb 984`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''b",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMy 985`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''y",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMz 986`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''z",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 987`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''{",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMuDBC0uDC00 988`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "")
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 989`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'(",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "(", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 990`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'-",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "-", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 991`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'/",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "/", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM0 992`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'0",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "0", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM1 993`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'1",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "1", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM9 994`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'9",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "9", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 995`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'<",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "<", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 996`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'=",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "=", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 997`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'>",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 998`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'?",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "?", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 999`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'@",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "@", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMA 1000`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'A",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "A", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMB 1001`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'B",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "B", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMY 1002`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'Y",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "Y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMZ 1003`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'Z",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "Z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 1004`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'`",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "`", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMa 1005`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'a",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMb 1006`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'b",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMy 1007`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'y",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMz 1008`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'z",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 1009`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'{",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "{", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMuDBC0uDC00 1010`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", systemId = "\uDBC0\uDC00", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 1011`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM(",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 1012`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM-",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 1013`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM/",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM0 1014`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM0",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM1 1015`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM1",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM9 1016`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM9",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 1017`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM<",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 1018`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM=",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 1019`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM>",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 1020`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM?",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 1021`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM@",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMA 1022`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMA",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMB 1023`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMB",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMY 1024`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMY",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMZ 1025`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMZ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 1026`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM`",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMa 1027`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMa",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMb 1028`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMb",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMy 1029`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMy",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMz 1030`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMz",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEM 1031`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM{",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa SYSTEMuDBC0uDC00 1032`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa Y 1033`() = assertTokenization(
        input = "<!DOCTYPEa Y",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa Z 1034`() = assertTokenization(
        input = "<!DOCTYPEa Z",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1035`() = assertTokenization(
        input = "<!DOCTYPEa `",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1036`() = assertTokenization(
        input = "<!DOCTYPEa a",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa au0000 1037`() = assertTokenization(
        input = "<!DOCTYPEa a\u0000",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa au0009 1038`() = assertTokenization(
        input = "<!DOCTYPEa a\t",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa au000A 1039`() = assertTokenization(
        input = "<!DOCTYPEa a\n",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa au000B 1040`() = assertTokenization(
        input = "<!DOCTYPEa a\u000b",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa au000C 1041`() = assertTokenization(
        input = "<!DOCTYPEa a\u000c",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1042`() = assertTokenization(
        input = "<!DOCTYPEa a ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1043`() = assertTokenization(
        input = "<!DOCTYPEa a!",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1044`() = assertTokenization(
        input = "<!DOCTYPEa a\"",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1045`() = assertTokenization(
        input = "<!DOCTYPEa a&",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1046`() = assertTokenization(
        input = "<!DOCTYPEa a'",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1047`() = assertTokenization(
        input = "<!DOCTYPEa a-",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1048`() = assertTokenization(
        input = "<!DOCTYPEa a/",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a0 1049`() = assertTokenization(
        input = "<!DOCTYPEa a0",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a1 1050`() = assertTokenization(
        input = "<!DOCTYPEa a1",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a9 1051`() = assertTokenization(
        input = "<!DOCTYPEa a9",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1052`() = assertTokenization(
        input = "<!DOCTYPEa a<",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1053`() = assertTokenization(
        input = "<!DOCTYPEa a=",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1054`() = assertTokenization(
        input = "<!DOCTYPEa a>",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1055`() = assertTokenization(
        input = "<!DOCTYPEa a?",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1056`() = assertTokenization(
        input = "<!DOCTYPEa a@",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa aA 1057`() = assertTokenization(
        input = "<!DOCTYPEa aA",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa aB 1058`() = assertTokenization(
        input = "<!DOCTYPEa aB",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa aY 1059`() = assertTokenization(
        input = "<!DOCTYPEa aY",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa aZ 1060`() = assertTokenization(
        input = "<!DOCTYPEa aZ",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1061`() = assertTokenization(
        input = "<!DOCTYPEa a`",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa aa 1062`() = assertTokenization(
        input = "<!DOCTYPEa aa",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa ab 1063`() = assertTokenization(
        input = "<!DOCTYPEa ab",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa ay 1064`() = assertTokenization(
        input = "<!DOCTYPEa ay",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa az 1065`() = assertTokenization(
        input = "<!DOCTYPEa az",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa a 1066`() = assertTokenization(
        input = "<!DOCTYPEa a{",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa auDBC0uDC00 1067`() = assertTokenization(
        input = "<!DOCTYPEa a\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa b 1068`() = assertTokenization(
        input = "<!DOCTYPEa b",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa y 1069`() = assertTokenization(
        input = "<!DOCTYPEa y",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa z 1070`() = assertTokenization(
        input = "<!DOCTYPEa z",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1071`() = assertTokenization(
        input = "<!DOCTYPEa {",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa uDBC0uDC00 1072`() = assertTokenization(
        input = "<!DOCTYPEa \uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1073`() = assertTokenization(
        input = "<!DOCTYPEa!",
        expectedTokens = listOf(
            doctype(name = "a!", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1074`() = assertTokenization(
        input = "<!DOCTYPEa\"",
        expectedTokens = listOf(
            doctype(name = "a\"", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1075`() = assertTokenization(
        input = "<!DOCTYPEa&",
        expectedTokens = listOf(
            doctype(name = "a&", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1076`() = assertTokenization(
        input = "<!DOCTYPEa'",
        expectedTokens = listOf(
            doctype(name = "a'", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1077`() = assertTokenization(
        input = "<!DOCTYPEa-",
        expectedTokens = listOf(
            doctype(name = "a-", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1078`() = assertTokenization(
        input = "<!DOCTYPEa/",
        expectedTokens = listOf(
            doctype(name = "a/", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa0 1079`() = assertTokenization(
        input = "<!DOCTYPEa0",
        expectedTokens = listOf(
            doctype(name = "a0", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa1 1080`() = assertTokenization(
        input = "<!DOCTYPEa1",
        expectedTokens = listOf(
            doctype(name = "a1", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa9 1081`() = assertTokenization(
        input = "<!DOCTYPEa9",
        expectedTokens = listOf(
            doctype(name = "a9", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1082`() = assertTokenization(
        input = "<!DOCTYPEa<",
        expectedTokens = listOf(
            doctype(name = "a<", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1083`() = assertTokenization(
        input = "<!DOCTYPEa=",
        expectedTokens = listOf(
            doctype(name = "a=", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1084`() = assertTokenization(
        input = "<!DOCTYPEa>",
        expectedTokens = listOf(
            doctype(name = "a")
        )
    )

    @Test
    fun `DOCTYPEa 1085`() = assertTokenization(
        input = "<!DOCTYPEa?",
        expectedTokens = listOf(
            doctype(name = "a?", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1086`() = assertTokenization(
        input = "<!DOCTYPEa@",
        expectedTokens = listOf(
            doctype(name = "a@", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEaA 1087`() = assertTokenization(
        input = "<!DOCTYPEaA",
        expectedTokens = listOf(
            doctype(name = "aa", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEaB 1088`() = assertTokenization(
        input = "<!DOCTYPEaB",
        expectedTokens = listOf(
            doctype(name = "ab", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEaY 1089`() = assertTokenization(
        input = "<!DOCTYPEaY",
        expectedTokens = listOf(
            doctype(name = "ay", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEaZ 1090`() = assertTokenization(
        input = "<!DOCTYPEaZ",
        expectedTokens = listOf(
            doctype(name = "az", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1091`() = assertTokenization(
        input = "<!DOCTYPEa[",
        expectedTokens = listOf(
            doctype(name = "a[", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1092`() = assertTokenization(
        input = "<!DOCTYPEa`",
        expectedTokens = listOf(
            doctype(name = "a`", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEaa 1093`() = assertTokenization(
        input = "<!DOCTYPEaa",
        expectedTokens = listOf(
            doctype(name = "aa", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEab 1094`() = assertTokenization(
        input = "<!DOCTYPEab",
        expectedTokens = listOf(
            doctype(name = "ab", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEay 1095`() = assertTokenization(
        input = "<!DOCTYPEay",
        expectedTokens = listOf(
            doctype(name = "ay", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEaz 1096`() = assertTokenization(
        input = "<!DOCTYPEaz",
        expectedTokens = listOf(
            doctype(name = "az", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEa 1097`() = assertTokenization(
        input = "<!DOCTYPEa{",
        expectedTokens = listOf(
            doctype(name = "a{", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEauDBC0uDC00 1098`() = assertTokenization(
        input = "<!DOCTYPEa\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "a\uDBC0\uDC00", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEb 1099`() = assertTokenization(
        input = "<!DOCTYPEb",
        expectedTokens = listOf(
            doctype(name = "b", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEy 1100`() = assertTokenization(
        input = "<!DOCTYPEy",
        expectedTokens = listOf(
            doctype(name = "y", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEz 1101`() = assertTokenization(
        input = "<!DOCTYPEz",
        expectedTokens = listOf(
            doctype(name = "z", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPE 1102`() = assertTokenization(
        input = "<!DOCTYPE{",
        expectedTokens = listOf(
            doctype(name = "{", forceQuirks = true)
        )
    )

    @Test
    fun `DOCTYPEuDBC0uDC00 1103`() = assertTokenization(
        input = "<!DOCTYPE\uDBC0\uDC00",
        expectedTokens = listOf(
            doctype(name = "\uDBC0\uDC00", forceQuirks = true)
        )
    )

    @Test
    fun `Y 1104`() = assertTokenization(
        input = "<!Y",
        expectedTokens = listOf(
            comment("Y")
        )
    )

    @Test
    fun `Z 1105`() = assertTokenization(
        input = "<!Z",
        expectedTokens = listOf(
            comment("Z")
        )
    )

    @Test
    fun `test 1106`() = assertTokenization(
        input = "<!`",
        expectedTokens = listOf(
            comment("`")
        )
    )

    @Test
    fun `a 1107`() = assertTokenization(
        input = "<!a",
        expectedTokens = listOf(
            comment("a")
        )
    )

    @Test
    fun `b 1108`() = assertTokenization(
        input = "<!b",
        expectedTokens = listOf(
            comment("b")
        )
    )

    @Test
    fun `y 1109`() = assertTokenization(
        input = "<!y",
        expectedTokens = listOf(
            comment("y")
        )
    )

    @Test
    fun `z 1110`() = assertTokenization(
        input = "<!z",
        expectedTokens = listOf(
            comment("z")
        )
    )

    @Test
    fun `test 1111`() = assertTokenization(
        input = "<!{",
        expectedTokens = listOf(
            comment("{")
        )
    )

    @Test
    fun `uDBC0uDC00 1112`() = assertTokenization(
        input = "<!\uDBC0\uDC00",
        expectedTokens = listOf(
            comment("\uDBC0\uDC00")
        )
    )

    @Test
    fun `test 1113`() = assertTokenization(
        input = "<\"",
        expectedTokens = listOf(
            *chars("<\"").toTypedArray()
        )
    )

    @Test
    fun `test 1114`() = assertTokenization(
        input = "<&",
        expectedTokens = listOf(
            *chars("<&").toTypedArray()
        )
    )

    @Test
    fun `test 1115`() = assertTokenization(
        input = "<'",
        expectedTokens = listOf(
            *chars("<'").toTypedArray()
        )
    )

    @Test
    fun `test 1116`() = assertTokenization(
        input = "<-",
        expectedTokens = listOf(
            *chars("<-").toTypedArray()
        )
    )

    @Test
    fun `test 1117`() = assertTokenization(
        input = "<.",
        expectedTokens = listOf(
            *chars("<.").toTypedArray()
        )
    )

    @Test
    fun `test 1118`() = assertTokenization(
        input = "</",
        expectedTokens = listOf(
            *chars("</").toTypedArray()
        )
    )

    @Test
    fun `u0000 1119`() = assertTokenization(
        input = "</\u0000",
        expectedTokens = listOf(
            comment("�")
        )
    )

    @Test
    fun `u0009 1120`() = assertTokenization(
        input = "</\t",
        expectedTokens = listOf(
            comment("\t")
        )
    )

    @Test
    fun `u000A 1121`() = assertTokenization(
        input = "</\n",
        expectedTokens = listOf(
            comment("\n")
        )
    )

    @Test
    fun `u000B 1122`() = assertTokenization(
        input = "</\u000b",
        expectedTokens = listOf(
            comment("\u000b")
        )
    )

    @Test
    fun `u000C 1123`() = assertTokenization(
        input = "</\u000c",
        expectedTokens = listOf(
            comment("\u000c")
        )
    )

    @Test
    fun `test 1124`() = assertTokenization(
        input = "</ ",
        expectedTokens = listOf(
            comment(" ")
        )
    )

    @Test
    fun `u0000 1125`() = assertTokenization(
        input = "</ \u0000",
        expectedTokens = listOf(
            comment(" �")
        )
    )

    @Test
    fun `test 1126`() = assertTokenization(
        input = "</!",
        expectedTokens = listOf(
            comment("!")
        )
    )

    @Test
    fun `test 1127`() = assertTokenization(
        input = "</\"",
        expectedTokens = listOf(
            comment("\"")
        )
    )

    @Test
    fun `test 1128`() = assertTokenization(
        input = "</&",
        expectedTokens = listOf(
            comment("&")
        )
    )

    @Test
    fun `test 1129`() = assertTokenization(
        input = "</'",
        expectedTokens = listOf(
            comment("'")
        )
    )

    @Test
    fun `test 1130`() = assertTokenization(
        input = "</-",
        expectedTokens = listOf(
            comment("-")
        )
    )

    @Test
    fun `test 1131`() = assertTokenization(
        input = "<//",
        expectedTokens = listOf(
            comment("/")
        )
    )

    @Test
    fun `test 0 1132`() = assertTokenization(
        input = "</0",
        expectedTokens = listOf(
            comment("0")
        )
    )

    @Test
    fun `test 1 1133`() = assertTokenization(
        input = "</1",
        expectedTokens = listOf(
            comment("1")
        )
    )

    @Test
    fun `test 9 1134`() = assertTokenization(
        input = "</9",
        expectedTokens = listOf(
            comment("9")
        )
    )

    @Test
    fun `test 1135`() = assertTokenization(
        input = "</<",
        expectedTokens = listOf(
            comment("<")
        )
    )

    @Test
    fun `test 1136`() = assertTokenization(
        input = "</=",
        expectedTokens = listOf(
            comment("=")
        )
    )

    @Test
    fun `test 1137`() = assertTokenization(
        input = "</>",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `test 1138`() = assertTokenization(
        input = "</?",
        expectedTokens = listOf(
            comment("?")
        )
    )

    @Test
    fun `test 1139`() = assertTokenization(
        input = "</@",
        expectedTokens = listOf(
            comment("@")
        )
    )

    @Test
    fun `A 1140`() = assertTokenization(
        input = "</A>",
        expectedTokens = listOf(
            endTag("a")
        )
    )

    @Test
    fun `B 1141`() = assertTokenization(
        input = "</B>",
        expectedTokens = listOf(
            endTag("b")
        )
    )

    @Test
    fun `Y 1142`() = assertTokenization(
        input = "</Y>",
        expectedTokens = listOf(
            endTag("y")
        )
    )

    @Test
    fun `Z 1143`() = assertTokenization(
        input = "</Z>",
        expectedTokens = listOf(
            endTag("z")
        )
    )

    @Test
    fun `test 1144`() = assertTokenization(
        input = "</[",
        expectedTokens = listOf(
            comment("[")
        )
    )

    @Test
    fun `test 1145`() = assertTokenization(
        input = "</`",
        expectedTokens = listOf(
            comment("`")
        )
    )

    @Test
    fun `a 1146`() = assertTokenization(
        input = "</a>",
        expectedTokens = listOf(
            endTag("a")
        )
    )

    @Test
    fun `b 1147`() = assertTokenization(
        input = "</b>",
        expectedTokens = listOf(
            endTag("b")
        )
    )

    @Test
    fun `y 1148`() = assertTokenization(
        input = "</y>",
        expectedTokens = listOf(
            endTag("y")
        )
    )

    @Test
    fun `z 1149`() = assertTokenization(
        input = "</z>",
        expectedTokens = listOf(
            endTag("z")
        )
    )

    @Test
    fun `test 1150`() = assertTokenization(
        input = "</{",
        expectedTokens = listOf(
            comment("{")
        )
    )

    @Test
    fun `uDBC0uDC00 1151`() = assertTokenization(
        input = "</\uDBC0\uDC00",
        expectedTokens = listOf(
            comment("\uDBC0\uDC00")
        )
    )

    @Test
    fun `test 0 1152`() = assertTokenization(
        input = "<0",
        expectedTokens = listOf(
            *chars("<0").toTypedArray()
        )
    )

    @Test
    fun `test 1 1153`() = assertTokenization(
        input = "<1",
        expectedTokens = listOf(
            *chars("<1").toTypedArray()
        )
    )

    @Test
    fun `test 9 1154`() = assertTokenization(
        input = "<9",
        expectedTokens = listOf(
            *chars("<9").toTypedArray()
        )
    )

    @Test
    fun `test 1155`() = assertTokenization(
        input = "<<",
        expectedTokens = listOf(
            *chars("<<").toTypedArray()
        )
    )

    @Test
    fun `test 1156`() = assertTokenization(
        input = "<=",
        expectedTokens = listOf(
            *chars("<=").toTypedArray()
        )
    )

    @Test
    fun `test 1157`() = assertTokenization(
        input = "<>",
        expectedTokens = listOf(
            *chars("<>").toTypedArray()
        )
    )

    @Test
    fun `test 1158`() = assertTokenization(
        input = "<?",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `a 1159`() = assertTokenization(
        input = "<?a",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `b 1160`() = assertTokenization(
        input = "<?b",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `y 1161`() = assertTokenization(
        input = "<?y",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `z 1162`() = assertTokenization(
        input = "<?z",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `A 1163`() = assertTokenization(
        input = "<?A",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `B 1164`() = assertTokenization(
        input = "<?B",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `Y 1165`() = assertTokenization(
        input = "<?Y",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `Z 1166`() = assertTokenization(
        input = "<?Z",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `a a 1167`() = assertTokenization(
        input = "<?a a",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `b b 1168`() = assertTokenization(
        input = "<?b b",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `y y 1169`() = assertTokenization(
        input = "<?y y",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `z z 1170`() = assertTokenization(
        input = "<?z z",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `A A 1171`() = assertTokenization(
        input = "<?A A",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `B B 1172`() = assertTokenization(
        input = "<?B B",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `Y Y 1173`() = assertTokenization(
        input = "<?Y Y",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `Z Z 1174`() = assertTokenization(
        input = "<?Z Z?",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `a a 1175`() = assertTokenization(
        input = "<?a a",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `b b 1176`() = assertTokenization(
        input = "<?b b",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `y y 1177`() = assertTokenization(
        input = "<?y y",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `z z 1178`() = assertTokenization(
        input = "<?z z",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `A A 1179`() = assertTokenization(
        input = "<?A A",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `B B 1180`() = assertTokenization(
        input = "<?B B",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `Y Y 1181`() = assertTokenization(
        input = "<?Y Y",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `Z Z 1182`() = assertTokenization(
        input = "<?Z Z",
        expectedTokens = listOf(
        )
    )

    @Test
    fun `u0000 1183`() = assertTokenization(
        input = "<?\u0000",
        expectedTokens = listOf(
            comment("?�")
        )
    )

    @Test
    fun `u0009 1184`() = assertTokenization(
        input = "<?\t",
        expectedTokens = listOf(
            comment("?\t")
        )
    )

    @Test
    fun `u000A 1185`() = assertTokenization(
        input = "<?\n",
        expectedTokens = listOf(
            comment("?\n")
        )
    )

    @Test
    fun `u000B 1186`() = assertTokenization(
        input = "<?\u000b",
        expectedTokens = listOf(
            comment("?\u000b")
        )
    )

    @Test
    fun `u000C 1187`() = assertTokenization(
        input = "<?\u000c",
        expectedTokens = listOf(
            comment("?\u000c")
        )
    )

    @Test
    fun `test 1188`() = assertTokenization(
        input = "<? ",
        expectedTokens = listOf(
            comment("? ")
        )
    )

    @Test
    fun `u0000 1189`() = assertTokenization(
        input = "<? \u0000",
        expectedTokens = listOf(
            comment("? �")
        )
    )

    @Test
    fun `test 1190`() = assertTokenization(
        input = "<?!",
        expectedTokens = listOf(
            comment("?!")
        )
    )

    @Test
    fun `test 1191`() = assertTokenization(
        input = "<?\"",
        expectedTokens = listOf(
            comment("?\"")
        )
    )

    @Test
    fun `test 1192`() = assertTokenization(
        input = "<?&",
        expectedTokens = listOf(
            comment("?&")
        )
    )

    @Test
    fun `test 1193`() = assertTokenization(
        input = "<?'",
        expectedTokens = listOf(
            comment("?'")
        )
    )

    @Test
    fun `test 1194`() = assertTokenization(
        input = "<?-",
        expectedTokens = listOf(
            comment("?-")
        )
    )

    @Test
    fun `test 1195`() = assertTokenization(
        input = "<?/",
        expectedTokens = listOf(
            comment("?/")
        )
    )

    @Test
    fun `test 0 1196`() = assertTokenization(
        input = "<?0",
        expectedTokens = listOf(
            comment("?0")
        )
    )

    @Test
    fun `test 1 1197`() = assertTokenization(
        input = "<?1",
        expectedTokens = listOf(
            comment("?1")
        )
    )

    @Test
    fun `test 9 1198`() = assertTokenization(
        input = "<?9",
        expectedTokens = listOf(
            comment("?9")
        )
    )

    @Test
    fun `test 1199`() = assertTokenization(
        input = "<?<",
        expectedTokens = listOf(
            comment("?<")
        )
    )

    @Test
    fun `test 1200`() = assertTokenization(
        input = "<?=",
        expectedTokens = listOf(
            comment("?=")
        )
    )

    @Test
    fun `test 1201`() = assertTokenization(
        input = "<?>",
        expectedTokens = listOf(
            comment("?")
        )
    )

    @Test
    fun `test 1202`() = assertTokenization(
        input = "<??",
        expectedTokens = listOf(
            comment("??")
        )
    )

    @Test
    fun `test 1203`() = assertTokenization(
        input = "<?@",
        expectedTokens = listOf(
            comment("?@")
        )
    )

    @Test
    fun `test 1204`() = assertTokenization(
        input = "<?`",
        expectedTokens = listOf(
            comment("?`")
        )
    )

    @Test
    fun `test 1205`() = assertTokenization(
        input = "<?{",
        expectedTokens = listOf(
            comment("?{")
        )
    )

    @Test
    fun `uDBC0uDC00 1206`() = assertTokenization(
        input = "<?\uDBC0\uDC00",
        expectedTokens = listOf(
            comment("?\uDBC0\uDC00")
        )
    )

    @Test
    fun `test 1207`() = assertTokenization(
        input = "<@",
        expectedTokens = listOf(
            *chars("<@").toTypedArray()
        )
    )

    @Test
    fun `A 1208`() = assertTokenization(
        input = "<A>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `B 1209`() = assertTokenization(
        input = "<B>",
        expectedTokens = listOf(
            startTag(name = "b")
        )
    )

    @Test
    fun `Y 1210`() = assertTokenization(
        input = "<Y>",
        expectedTokens = listOf(
            startTag(name = "y")
        )
    )

    @Test
    fun `Z 1211`() = assertTokenization(
        input = "<Z>",
        expectedTokens = listOf(
            startTag(name = "z")
        )
    )

    @Test
    fun `test 1212`() = assertTokenization(
        input = "<[",
        expectedTokens = listOf(
            *chars("<[").toTypedArray()
        )
    )

    @Test
    fun `test 1213`() = assertTokenization(
        input = "<`",
        expectedTokens = listOf(
            *chars("<`").toTypedArray()
        )
    )

    @Test
    fun `a 1214`() = assertTokenization(
        input = "<a>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au0000 1215`() = assertTokenization(
        input = "<a\u0000>",
        expectedTokens = listOf(
            startTag(name = "a�")
        )
    )

    @Test
    fun `au0008 1216`() = assertTokenization(
        input = "<a\u0008>",
        expectedTokens = listOf(
            startTag(name = "a\u0008")
        )
    )

    @Test
    fun `au0009 1217`() = assertTokenization(
        input = "<a\t>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au000A 1218`() = assertTokenization(
        input = "<a\n>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au000B 1219`() = assertTokenization(
        input = "<a\u000b>",
        expectedTokens = listOf(
            startTag(name = "a\u000b")
        )
    )

    @Test
    fun `au000C 1220`() = assertTokenization(
        input = "<a\u000c>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au000D 1221`() = assertTokenization(
        input = "<a\r>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au001F 1222`() = assertTokenization(
        input = "<a\u001f>",
        expectedTokens = listOf(
            startTag(name = "a\u001f")
        )
    )

    @Test
    fun `a 1223`() = assertTokenization(
        input = "<a >",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a u0000 1224`() = assertTokenization(
        input = "<a \u0000>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("�" to ""))
        )
    )

    @Test
    fun `a u0008 1225`() = assertTokenization(
        input = "<a \u0008>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("\u0008" to ""))
        )
    )

    @Test
    fun `a u0009 1226`() = assertTokenization(
        input = "<a \t>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a u000A 1227`() = assertTokenization(
        input = "<a \n>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a u000B 1228`() = assertTokenization(
        input = "<a \u000b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("\u000b" to ""))
        )
    )

    @Test
    fun `a u000C 1229`() = assertTokenization(
        input = "<a \u000c>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a u000D 1230`() = assertTokenization(
        input = "<a \r>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a u001F 1231`() = assertTokenization(
        input = "<a \u001f>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("\u001f" to ""))
        )
    )

    @Test
    fun `a 1232`() = assertTokenization(
        input = "<a  >",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a 1233`() = assertTokenization(
        input = "<a !>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("!" to ""))
        )
    )

    @Test
    fun `a 1234`() = assertTokenization(
        input = "<a \">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("\"" to ""))
        )
    )

    @Test
    fun `a 1235`() = assertTokenization(
        input = "<a #>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("#" to ""))
        )
    )

    @Test
    fun `a 1236`() = assertTokenization(
        input = "<a &>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("&" to ""))
        )
    )

    @Test
    fun `a 1237`() = assertTokenization(
        input = "<a '>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("'" to ""))
        )
    )

    @Test
    fun `a 1238`() = assertTokenization(
        input = "<a (>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("(" to ""))
        )
    )

    @Test
    fun `a 1239`() = assertTokenization(
        input = "<a ->",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("-" to ""))
        )
    )

    @Test
    fun `a 1240`() = assertTokenization(
        input = "<a .>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("." to ""))
        )
    )

    @Test
    fun `a 1241`() = assertTokenization(
        input = "<a />",
        expectedTokens = listOf(
            startTag(name = "a", selfClosing = true)
        )
    )

    @Test
    fun `a 0 1242`() = assertTokenization(
        input = "<a 0>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("0" to ""))
        )
    )

    @Test
    fun `a 1 1243`() = assertTokenization(
        input = "<a 1>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("1" to ""))
        )
    )

    @Test
    fun `a 9 1244`() = assertTokenization(
        input = "<a 9>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("9" to ""))
        )
    )

    @Test
    fun `a 1245`() = assertTokenization(
        input = "<a <>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("<" to ""))
        )
    )

    @Test
    fun `a 1246`() = assertTokenization(
        input = "<a =>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("=" to ""))
        )
    )

    @Test
    fun `a 1247`() = assertTokenization(
        input = "<a >",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a 1248`() = assertTokenization(
        input = "<a ?>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("?" to ""))
        )
    )

    @Test
    fun `a 1249`() = assertTokenization(
        input = "<a @>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("@" to ""))
        )
    )

    @Test
    fun `a A 1250`() = assertTokenization(
        input = "<a A>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a B 1251`() = assertTokenization(
        input = "<a B>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("b" to ""))
        )
    )

    @Test
    fun `a Y 1252`() = assertTokenization(
        input = "<a Y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("y" to ""))
        )
    )

    @Test
    fun `a Z 1253`() = assertTokenization(
        input = "<a Z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("z" to ""))
        )
    )

    @Test
    fun `a 1254`() = assertTokenization(
        input = "<a [>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("[" to ""))
        )
    )

    @Test
    fun `a 1255`() = assertTokenization(
        input = "<a `>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("`" to ""))
        )
    )

    @Test
    fun `a a 1256`() = assertTokenization(
        input = "<a a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au0000 1257`() = assertTokenization(
        input = "<a a\u0000>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a�" to ""))
        )
    )

    @Test
    fun `a au0008 1258`() = assertTokenization(
        input = "<a a\u0008>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a\u0008" to ""))
        )
    )

    @Test
    fun `a au0009 1259`() = assertTokenization(
        input = "<a a\t>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000A 1260`() = assertTokenization(
        input = "<a a\n>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000B 1261`() = assertTokenization(
        input = "<a a\u000b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a\u000b" to ""))
        )
    )

    @Test
    fun `a au000C 1262`() = assertTokenization(
        input = "<a a\u000c>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000D 1263`() = assertTokenization(
        input = "<a a\r>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au001F 1264`() = assertTokenization(
        input = "<a a\u001f>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a\u001f" to ""))
        )
    )

    @Test
    fun `a a 1265`() = assertTokenization(
        input = "<a a >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a u0000 1266`() = assertTokenization(
        input = "<a a \u0000>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "�" to ""))
        )
    )

    @Test
    fun `a a u0008 1267`() = assertTokenization(
        input = "<a a \u0008>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "\u0008" to ""))
        )
    )

    @Test
    fun `a a u0009 1268`() = assertTokenization(
        input = "<a a \t>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a u000A 1269`() = assertTokenization(
        input = "<a a \n>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a u000B 1270`() = assertTokenization(
        input = "<a a \u000b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "\u000b" to ""))
        )
    )

    @Test
    fun `a a u000C 1271`() = assertTokenization(
        input = "<a a \u000c>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a u000D 1272`() = assertTokenization(
        input = "<a a \r>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a u001F 1273`() = assertTokenization(
        input = "<a a \u001f>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "\u001f" to ""))
        )
    )

    @Test
    fun `a a 1274`() = assertTokenization(
        input = "<a a  >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 1275`() = assertTokenization(
        input = "<a a !>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "!" to ""))
        )
    )

    @Test
    fun `a a 1276`() = assertTokenization(
        input = "<a a \">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "\"" to ""))
        )
    )

    @Test
    fun `a a 1277`() = assertTokenization(
        input = "<a a #>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "#" to ""))
        )
    )

    @Test
    fun `a a 1278`() = assertTokenization(
        input = "<a a &>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "&" to ""))
        )
    )

    @Test
    fun `a a 1279`() = assertTokenization(
        input = "<a a '>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "'" to ""))
        )
    )

    @Test
    fun `a a 1280`() = assertTokenization(
        input = "<a a (>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "(" to ""))
        )
    )

    @Test
    fun `a a 1281`() = assertTokenization(
        input = "<a a ->",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "-" to ""))
        )
    )

    @Test
    fun `a a 1282`() = assertTokenization(
        input = "<a a .>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "." to ""))
        )
    )

    @Test
    fun `a a 1283`() = assertTokenization(
        input = "<a a />",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""), selfClosing = true)
        )
    )

    @Test
    fun `a a 0 1284`() = assertTokenization(
        input = "<a a 0>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "0" to ""))
        )
    )

    @Test
    fun `a a 1 1285`() = assertTokenization(
        input = "<a a 1>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "1" to ""))
        )
    )

    @Test
    fun `a a 9 1286`() = assertTokenization(
        input = "<a a 9>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "9" to ""))
        )
    )

    @Test
    fun `a a 1287`() = assertTokenization(
        input = "<a a <>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "<" to ""))
        )
    )

    @Test
    fun `a a 1288`() = assertTokenization(
        input = "<a a =>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 1289`() = assertTokenization(
        input = "<a a >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 1290`() = assertTokenization(
        input = "<a a ?>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "?" to ""))
        )
    )

    @Test
    fun `a a 1291`() = assertTokenization(
        input = "<a a @>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "@" to ""))
        )
    )

    @Test
    fun `a a A 1292`() = assertTokenization(
        input = "<a a A>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a B 1293`() = assertTokenization(
        input = "<a a B>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "b" to ""))
        )
    )

    @Test
    fun `a a Y 1294`() = assertTokenization(
        input = "<a a Y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "y" to ""))
        )
    )

    @Test
    fun `a a Z 1295`() = assertTokenization(
        input = "<a a Z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "z" to ""))
        )
    )

    @Test
    fun `a a 1296`() = assertTokenization(
        input = "<a a [>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "[" to ""))
        )
    )

    @Test
    fun `a a 1297`() = assertTokenization(
        input = "<a a `>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "`" to ""))
        )
    )

    @Test
    fun `a a a 1298`() = assertTokenization(
        input = "<a a a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a b 1299`() = assertTokenization(
        input = "<a a b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "b" to ""))
        )
    )

    @Test
    fun `a a y 1300`() = assertTokenization(
        input = "<a a y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "y" to ""))
        )
    )

    @Test
    fun `a a z 1301`() = assertTokenization(
        input = "<a a z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "z" to ""))
        )
    )

    @Test
    fun `a a 1302`() = assertTokenization(
        input = "<a a {>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "{" to ""))
        )
    )

    @Test
    fun `a a uDBC0uDC00 1303`() = assertTokenization(
        input = "<a a \uDBC0\uDC00>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "\uDBC0\uDC00" to ""))
        )
    )

    @Test
    fun `a a 1304`() = assertTokenization(
        input = "<a a!>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a!" to ""))
        )
    )

    @Test
    fun `a a 1305`() = assertTokenization(
        input = "<a a\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a\"" to ""))
        )
    )

    @Test
    fun `a a 1306`() = assertTokenization(
        input = "<a a#>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a#" to ""))
        )
    )

    @Test
    fun `a a 1307`() = assertTokenization(
        input = "<a a&>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a&" to ""))
        )
    )

    @Test
    fun `a a 1308`() = assertTokenization(
        input = "<a a'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a'" to ""))
        )
    )

    @Test
    fun `a a 1309`() = assertTokenization(
        input = "<a a(>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a(" to ""))
        )
    )

    @Test
    fun `a a 1310`() = assertTokenization(
        input = "<a a->",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a-" to ""))
        )
    )

    @Test
    fun `a a 1311`() = assertTokenization(
        input = "<a a.>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a." to ""))
        )
    )

    @Test
    fun `a a 1312`() = assertTokenization(
        input = "<a a/>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""), selfClosing = true)
        )
    )

    @Test
    fun `a a0 1313`() = assertTokenization(
        input = "<a a0>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a0" to ""))
        )
    )

    @Test
    fun `a a1 1314`() = assertTokenization(
        input = "<a a1>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a1" to ""))
        )
    )

    @Test
    fun `a a9 1315`() = assertTokenization(
        input = "<a a9>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a9" to ""))
        )
    )

    @Test
    fun `a a 1316`() = assertTokenization(
        input = "<a a<>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a<" to ""))
        )
    )

    @Test
    fun `a a 1317`() = assertTokenization(
        input = "<a a=>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au0000 1318`() = assertTokenization(
        input = "<a a=\u0000>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "�"))
        )
    )

    @Test
    fun `a au0008 1319`() = assertTokenization(
        input = "<a a=\u0008>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\u0008"))
        )
    )

    @Test
    fun `a au0009 1320`() = assertTokenization(
        input = "<a a=\t>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000A 1321`() = assertTokenization(
        input = "<a a=\n>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000B 1322`() = assertTokenization(
        input = "<a a=\u000b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\u000b"))
        )
    )

    @Test
    fun `a au000C 1323`() = assertTokenization(
        input = "<a a=\u000c>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000D 1324`() = assertTokenization(
        input = "<a a=\r>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au001F 1325`() = assertTokenization(
        input = "<a a=\u001f>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\u001f"))
        )
    )

    @Test
    fun `a a 1326`() = assertTokenization(
        input = "<a a= >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 1327`() = assertTokenization(
        input = "<a a=!>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "!"))
        )
    )

    @Test
    fun `a a 1328`() = assertTokenization(
        input = "<a a=\"\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au0000 1329`() = assertTokenization(
        input = "<a a=\"\u0000\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "�"))
        )
    )

    @Test
    fun `a au0009 1330`() = assertTokenization(
        input = "<a a=\"\t\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\t"))
        )
    )

    @Test
    fun `a au000A 1331`() = assertTokenization(
        input = "<a a=\"\n\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\n"))
        )
    )

    @Test
    fun `a au000B 1332`() = assertTokenization(
        input = "<a a=\"\u000b\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\u000b"))
        )
    )

    @Test
    fun `a au000C 1333`() = assertTokenization(
        input = "<a a=\"\u000c\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\u000c"))
        )
    )

    @Test
    fun `a a 1334`() = assertTokenization(
        input = "<a a=\" \">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to " "))
        )
    )

    @Test
    fun `a a 1335`() = assertTokenization(
        input = "<a a=\"!\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "!"))
        )
    )

    @Test
    fun `a a 1336`() = assertTokenization(
        input = "<a a=\"\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 1337`() = assertTokenization(
        input = "<a a=\"#\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "#"))
        )
    )

    @Test
    fun `a a 1338`() = assertTokenization(
        input = "<a a=\"%\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "%"))
        )
    )

    @Test
    fun `a a 1339`() = assertTokenization(
        input = "<a a=\"&\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "&"))
        )
    )

    @Test
    fun `a a 1340`() = assertTokenization(
        input = "<a a=\"'\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "'"))
        )
    )

    @Test
    fun `a a 1341`() = assertTokenization(
        input = "<a a=\"-\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "-"))
        )
    )

    @Test
    fun `a a 1342`() = assertTokenization(
        input = "<a a=\"/\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "/"))
        )
    )

    @Test
    fun `a a0 1343`() = assertTokenization(
        input = "<a a=\"0\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "0"))
        )
    )

    @Test
    fun `a a1 1344`() = assertTokenization(
        input = "<a a=\"1\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "1"))
        )
    )

    @Test
    fun `a a9 1345`() = assertTokenization(
        input = "<a a=\"9\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "9"))
        )
    )

    @Test
    fun `a a 1346`() = assertTokenization(
        input = "<a a=\"<\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "<"))
        )
    )

    @Test
    fun `a a 1347`() = assertTokenization(
        input = "<a a=\"=\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "="))
        )
    )

    @Test
    fun `a a 1348`() = assertTokenization(
        input = "<a a=\">\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ">"))
        )
    )

    @Test
    fun `a a 1349`() = assertTokenization(
        input = "<a a=\"?\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "?"))
        )
    )

    @Test
    fun `a a 1350`() = assertTokenization(
        input = "<a a=\"@\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "@"))
        )
    )

    @Test
    fun `a aA 1351`() = assertTokenization(
        input = "<a a=\"A\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "A"))
        )
    )

    @Test
    fun `a aB 1352`() = assertTokenization(
        input = "<a a=\"B\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "B"))
        )
    )

    @Test
    fun `a aY 1353`() = assertTokenization(
        input = "<a a=\"Y\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "Y"))
        )
    )

    @Test
    fun `a aZ 1354`() = assertTokenization(
        input = "<a a=\"Z\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "Z"))
        )
    )

    @Test
    fun `a a 1355`() = assertTokenization(
        input = "<a a=\"`\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "`"))
        )
    )

    @Test
    fun `a aa 1356`() = assertTokenization(
        input = "<a a=\"a\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a ab 1357`() = assertTokenization(
        input = "<a a=\"b\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "b"))
        )
    )

    @Test
    fun `a ay 1358`() = assertTokenization(
        input = "<a a=\"y\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "y"))
        )
    )

    @Test
    fun `a az 1359`() = assertTokenization(
        input = "<a a=\"z\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "z"))
        )
    )

    @Test
    fun `a a 1360`() = assertTokenization(
        input = "<a a=\"{\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "{"))
        )
    )

    @Test
    fun `a auDBC0uDC00 1361`() = assertTokenization(
        input = "<a a=\"\uDBC0\uDC00\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\uDBC0\uDC00"))
        )
    )

    @Test
    fun `a a 1362`() = assertTokenization(
        input = "<a a=#>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "#"))
        )
    )

    @Test
    fun `a a 1363`() = assertTokenization(
        input = "<a a=%>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "%"))
        )
    )

    @Test
    fun `a a 1364`() = assertTokenization(
        input = "<a a=&>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "&"))
        )
    )

    @Test
    fun `a a 1365`() = assertTokenization(
        input = "<a a=''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au0000 1366`() = assertTokenization(
        input = "<a a='\u0000'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "�"))
        )
    )

    @Test
    fun `a au0009 1367`() = assertTokenization(
        input = "<a a='\t'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\t"))
        )
    )

    @Test
    fun `a au000A 1368`() = assertTokenization(
        input = "<a a='\n'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\n"))
        )
    )

    @Test
    fun `a au000B 1369`() = assertTokenization(
        input = "<a a='\u000b'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\u000b"))
        )
    )

    @Test
    fun `a au000C 1370`() = assertTokenization(
        input = "<a a='\u000c'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\u000c"))
        )
    )

    @Test
    fun `a a 1371`() = assertTokenization(
        input = "<a a=' '>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to " "))
        )
    )

    @Test
    fun `a a 1372`() = assertTokenization(
        input = "<a a='!'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "!"))
        )
    )

    @Test
    fun `a a 1373`() = assertTokenization(
        input = "<a a='\"'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\""))
        )
    )

    @Test
    fun `a a 1374`() = assertTokenization(
        input = "<a a='%'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "%"))
        )
    )

    @Test
    fun `a a 1375`() = assertTokenization(
        input = "<a a='&'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "&"))
        )
    )

    @Test
    fun `a a 1376`() = assertTokenization(
        input = "<a a=''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au0000 1377`() = assertTokenization(
        input = "<a a=''\u0000>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "�" to ""))
        )
    )

    @Test
    fun `a au0008 1378`() = assertTokenization(
        input = "<a a=''\u0008>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "\u0008" to ""))
        )
    )

    @Test
    fun `a au0009 1379`() = assertTokenization(
        input = "<a a=''\t>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000A 1380`() = assertTokenization(
        input = "<a a=''\n>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000B 1381`() = assertTokenization(
        input = "<a a=''\u000b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "\u000b" to ""))
        )
    )

    @Test
    fun `a au000C 1382`() = assertTokenization(
        input = "<a a=''\u000c>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000D 1383`() = assertTokenization(
        input = "<a a=''\r>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au001F 1384`() = assertTokenization(
        input = "<a a=''\u001f>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "\u001f" to ""))
        )
    )

    @Test
    fun `a a 1385`() = assertTokenization(
        input = "<a a='' >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 1386`() = assertTokenization(
        input = "<a a=''!>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "!" to ""))
        )
    )

    @Test
    fun `a a 1387`() = assertTokenization(
        input = "<a a=''\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "\"" to ""))
        )
    )

    @Test
    fun `a a 1388`() = assertTokenization(
        input = "<a a=''&>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "&" to ""))
        )
    )

    @Test
    fun `a a 1389`() = assertTokenization(
        input = "<a a='''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "'" to ""))
        )
    )

    @Test
    fun `a a 1390`() = assertTokenization(
        input = "<a a=''->",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "-" to ""))
        )
    )

    @Test
    fun `a a 1391`() = assertTokenization(
        input = "<a a=''.>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "." to ""))
        )
    )

    @Test
    fun `a a 1392`() = assertTokenization(
        input = "<a a=''/>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""), selfClosing = true)
        )
    )

    @Test
    fun `a a0 1393`() = assertTokenization(
        input = "<a a=''0>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "0" to ""))
        )
    )

    @Test
    fun `a a1 1394`() = assertTokenization(
        input = "<a a=''1>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "1" to ""))
        )
    )

    @Test
    fun `a a9 1395`() = assertTokenization(
        input = "<a a=''9>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "9" to ""))
        )
    )

    @Test
    fun `a a 1396`() = assertTokenization(
        input = "<a a=''<>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "<" to ""))
        )
    )

    @Test
    fun `a a 1397`() = assertTokenization(
        input = "<a a=''=>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "=" to ""))
        )
    )

    @Test
    fun `a a 1398`() = assertTokenization(
        input = "<a a=''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 1399`() = assertTokenization(
        input = "<a a=''?>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "?" to ""))
        )
    )

    @Test
    fun `a a 1400`() = assertTokenization(
        input = "<a a=''@>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "@" to ""))
        )
    )

    @Test
    fun `a aA 1401`() = assertTokenization(
        input = "<a a=''A>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a aB 1402`() = assertTokenization(
        input = "<a a=''B>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "b" to ""))
        )
    )

    @Test
    fun `a aY 1403`() = assertTokenization(
        input = "<a a=''Y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "y" to ""))
        )
    )

    @Test
    fun `a aZ 1404`() = assertTokenization(
        input = "<a a=''Z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "z" to ""))
        )
    )

    @Test
    fun `a a 1405`() = assertTokenization(
        input = "<a a=''`>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "`" to ""))
        )
    )

    @Test
    fun `a aa 1406`() = assertTokenization(
        input = "<a a=''a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a ab 1407`() = assertTokenization(
        input = "<a a=''b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "b" to ""))
        )
    )

    @Test
    fun `a ay 1408`() = assertTokenization(
        input = "<a a=''y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "y" to ""))
        )
    )

    @Test
    fun `a az 1409`() = assertTokenization(
        input = "<a a=''z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "z" to ""))
        )
    )

    @Test
    fun `a a 1410`() = assertTokenization(
        input = "<a a=''{>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "{" to ""))
        )
    )

    @Test
    fun `a auDBC0uDC00 1411`() = assertTokenization(
        input = "<a a=''\uDBC0\uDC00>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "\uDBC0\uDC00" to ""))
        )
    )

    @Test
    fun `a a 1412`() = assertTokenization(
        input = "<a a='('>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "("))
        )
    )

    @Test
    fun `a a 1413`() = assertTokenization(
        input = "<a a='-'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "-"))
        )
    )

    @Test
    fun `a a 1414`() = assertTokenization(
        input = "<a a='/'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "/"))
        )
    )

    @Test
    fun `a a0 1415`() = assertTokenization(
        input = "<a a='0'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "0"))
        )
    )

    @Test
    fun `a a1 1416`() = assertTokenization(
        input = "<a a='1'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "1"))
        )
    )

    @Test
    fun `a a9 1417`() = assertTokenization(
        input = "<a a='9'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "9"))
        )
    )

    @Test
    fun `a a 1418`() = assertTokenization(
        input = "<a a='<'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "<"))
        )
    )

    @Test
    fun `a a 1419`() = assertTokenization(
        input = "<a a='='>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "="))
        )
    )

    @Test
    fun `a a 1420`() = assertTokenization(
        input = "<a a='>'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ">"))
        )
    )

    @Test
    fun `a a 1421`() = assertTokenization(
        input = "<a a='?'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "?"))
        )
    )

    @Test
    fun `a a 1422`() = assertTokenization(
        input = "<a a='@'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "@"))
        )
    )

    @Test
    fun `a aA 1423`() = assertTokenization(
        input = "<a a='A'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "A"))
        )
    )

    @Test
    fun `a aB 1424`() = assertTokenization(
        input = "<a a='B'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "B"))
        )
    )

    @Test
    fun `a aY 1425`() = assertTokenization(
        input = "<a a='Y'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "Y"))
        )
    )

    @Test
    fun `a aZ 1426`() = assertTokenization(
        input = "<a a='Z'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "Z"))
        )
    )

    @Test
    fun `a a 1427`() = assertTokenization(
        input = "<a a='`'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "`"))
        )
    )

    @Test
    fun `a aa 1428`() = assertTokenization(
        input = "<a a='a'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a ab 1429`() = assertTokenization(
        input = "<a a='b'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "b"))
        )
    )

    @Test
    fun `a ay 1430`() = assertTokenization(
        input = "<a a='y'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "y"))
        )
    )

    @Test
    fun `a az 1431`() = assertTokenization(
        input = "<a a='z'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "z"))
        )
    )

    @Test
    fun `a a 1432`() = assertTokenization(
        input = "<a a='{'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "{"))
        )
    )

    @Test
    fun `a auDBC0uDC00 1433`() = assertTokenization(
        input = "<a a='\uDBC0\uDC00'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\uDBC0\uDC00"))
        )
    )

    @Test
    fun `a a 1434`() = assertTokenization(
        input = "<a a=(>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "("))
        )
    )

    @Test
    fun `a a 1435`() = assertTokenization(
        input = "<a a=->",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "-"))
        )
    )

    @Test
    fun `a a 1436`() = assertTokenization(
        input = "<a a=/>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "/"))
        )
    )

    @Test
    fun `a a0 1437`() = assertTokenization(
        input = "<a a=0>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "0"))
        )
    )

    @Test
    fun `a a1 1438`() = assertTokenization(
        input = "<a a=1>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "1"))
        )
    )

    @Test
    fun `a a9 1439`() = assertTokenization(
        input = "<a a=9>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "9"))
        )
    )

    @Test
    fun `a a 1440`() = assertTokenization(
        input = "<a a=<>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "<"))
        )
    )

    @Test
    fun `a a 1441`() = assertTokenization(
        input = "<a a==>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "="))
        )
    )

    @Test
    fun `a a 1442`() = assertTokenization(
        input = "<a a=>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 1443`() = assertTokenization(
        input = "<a a=?>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "?"))
        )
    )

    @Test
    fun `a a 1444`() = assertTokenization(
        input = "<a a=@>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "@"))
        )
    )

    @Test
    fun `a aA 1445`() = assertTokenization(
        input = "<a a=A>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "A"))
        )
    )

    @Test
    fun `a aB 1446`() = assertTokenization(
        input = "<a a=B>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "B"))
        )
    )

    @Test
    fun `a aY 1447`() = assertTokenization(
        input = "<a a=Y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "Y"))
        )
    )

    @Test
    fun `a aZ 1448`() = assertTokenization(
        input = "<a a=Z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "Z"))
        )
    )

    @Test
    fun `a a 1449`() = assertTokenization(
        input = "<a a=`>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "`"))
        )
    )

    @Test
    fun `a aa 1450`() = assertTokenization(
        input = "<a a=a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aau0000 1451`() = assertTokenization(
        input = "<a a=a\u0000>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a�"))
        )
    )

    @Test
    fun `a aau0008 1452`() = assertTokenization(
        input = "<a a=a\u0008>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a\u0008"))
        )
    )

    @Test
    fun `a aau0009 1453`() = assertTokenization(
        input = "<a a=a\t>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aau000A 1454`() = assertTokenization(
        input = "<a a=a\n>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aau000B 1455`() = assertTokenization(
        input = "<a a=a\u000b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a\u000b"))
        )
    )

    @Test
    fun `a aau000C 1456`() = assertTokenization(
        input = "<a a=a\u000c>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aau000D 1457`() = assertTokenization(
        input = "<a a=a\r>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aau001F 1458`() = assertTokenization(
        input = "<a a=a\u001f>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a\u001f"))
        )
    )

    @Test
    fun `a aa 1459`() = assertTokenization(
        input = "<a a=a >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aa 1460`() = assertTokenization(
        input = "<a a=a!>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a!"))
        )
    )

    @Test
    fun `a aa 1461`() = assertTokenization(
        input = "<a a=a\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a\""))
        )
    )

    @Test
    fun `a aa 1462`() = assertTokenization(
        input = "<a a=a#>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a#"))
        )
    )

    @Test
    fun `a aa 1463`() = assertTokenization(
        input = "<a a=a%>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a%"))
        )
    )

    @Test
    fun `a aa 1464`() = assertTokenization(
        input = "<a a=a&>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a&"))
        )
    )

    @Test
    fun `a aa 1465`() = assertTokenization(
        input = "<a a=a'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a'"))
        )
    )

    @Test
    fun `a aa 1466`() = assertTokenization(
        input = "<a a=a(>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a("))
        )
    )

    @Test
    fun `a aa 1467`() = assertTokenization(
        input = "<a a=a->",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a-"))
        )
    )

    @Test
    fun `a aa 1468`() = assertTokenization(
        input = "<a a=a/>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a/"))
        )
    )

    @Test
    fun `a aa0 1469`() = assertTokenization(
        input = "<a a=a0>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a0"))
        )
    )

    @Test
    fun `a aa1 1470`() = assertTokenization(
        input = "<a a=a1>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a1"))
        )
    )

    @Test
    fun `a aa9 1471`() = assertTokenization(
        input = "<a a=a9>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a9"))
        )
    )

    @Test
    fun `a aa 1472`() = assertTokenization(
        input = "<a a=a<>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a<"))
        )
    )

    @Test
    fun `a aa 1473`() = assertTokenization(
        input = "<a a=a=>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a="))
        )
    )

    @Test
    fun `a aa 1474`() = assertTokenization(
        input = "<a a=a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aa 1475`() = assertTokenization(
        input = "<a a=a?>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a?"))
        )
    )

    @Test
    fun `a aa 1476`() = assertTokenization(
        input = "<a a=a@>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a@"))
        )
    )

    @Test
    fun `a aaA 1477`() = assertTokenization(
        input = "<a a=aA>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "aA"))
        )
    )

    @Test
    fun `a aaB 1478`() = assertTokenization(
        input = "<a a=aB>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "aB"))
        )
    )

    @Test
    fun `a aaY 1479`() = assertTokenization(
        input = "<a a=aY>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "aY"))
        )
    )

    @Test
    fun `a aaZ 1480`() = assertTokenization(
        input = "<a a=aZ>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "aZ"))
        )
    )

    @Test
    fun `a aa 1481`() = assertTokenization(
        input = "<a a=a`>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a`"))
        )
    )

    @Test
    fun `a aaa 1482`() = assertTokenization(
        input = "<a a=aa>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "aa"))
        )
    )

    @Test
    fun `a aab 1483`() = assertTokenization(
        input = "<a a=ab>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "ab"))
        )
    )

    @Test
    fun `a aay 1484`() = assertTokenization(
        input = "<a a=ay>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "ay"))
        )
    )

    @Test
    fun `a aaz 1485`() = assertTokenization(
        input = "<a a=az>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "az"))
        )
    )

    @Test
    fun `a aa 1486`() = assertTokenization(
        input = "<a a=a{>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a{"))
        )
    )

    @Test
    fun `a aauDBC0uDC00 1487`() = assertTokenization(
        input = "<a a=a\uDBC0\uDC00>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a\uDBC0\uDC00"))
        )
    )

    @Test
    fun `a ab 1488`() = assertTokenization(
        input = "<a a=b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "b"))
        )
    )

    @Test
    fun `a ay 1489`() = assertTokenization(
        input = "<a a=y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "y"))
        )
    )

    @Test
    fun `a az 1490`() = assertTokenization(
        input = "<a a=z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "z"))
        )
    )

    @Test
    fun `a a 1491`() = assertTokenization(
        input = "<a a={>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "{"))
        )
    )

    @Test
    fun `a auDBC0uDC00 1492`() = assertTokenization(
        input = "<a a=\uDBC0\uDC00>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\uDBC0\uDC00"))
        )
    )

    @Test
    fun `a a 1493`() = assertTokenization(
        input = "<a a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 1494`() = assertTokenization(
        input = "<a a?>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a?" to ""))
        )
    )

    @Test
    fun `a a 1495`() = assertTokenization(
        input = "<a a@>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a@" to ""))
        )
    )

    @Test
    fun `a aA 1496`() = assertTokenization(
        input = "<a aA>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("aa" to ""))
        )
    )

    @Test
    fun `a aB 1497`() = assertTokenization(
        input = "<a aB>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("ab" to ""))
        )
    )

    @Test
    fun `a aY 1498`() = assertTokenization(
        input = "<a aY>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("ay" to ""))
        )
    )

    @Test
    fun `a aZ 1499`() = assertTokenization(
        input = "<a aZ>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("az" to ""))
        )
    )

    @Test
    fun `a a 1500`() = assertTokenization(
        input = "<a a[>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a[" to ""))
        )
    )

    @Test
    fun `a a 1501`() = assertTokenization(
        input = "<a a`>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a`" to ""))
        )
    )

    @Test
    fun `a aa 1502`() = assertTokenization(
        input = "<a aa>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("aa" to ""))
        )
    )

    @Test
    fun `a ab 1503`() = assertTokenization(
        input = "<a ab>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("ab" to ""))
        )
    )

    @Test
    fun `a ay 1504`() = assertTokenization(
        input = "<a ay>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("ay" to ""))
        )
    )

    @Test
    fun `a az 1505`() = assertTokenization(
        input = "<a az>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("az" to ""))
        )
    )

    @Test
    fun `a a 1506`() = assertTokenization(
        input = "<a a{>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a{" to ""))
        )
    )

    @Test
    fun `a auDBC0uDC00 1507`() = assertTokenization(
        input = "<a a\uDBC0\uDC00>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a\uDBC0\uDC00" to ""))
        )
    )

    @Test
    fun `a b 1508`() = assertTokenization(
        input = "<a b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("b" to ""))
        )
    )

    @Test
    fun `a y 1509`() = assertTokenization(
        input = "<a y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("y" to ""))
        )
    )

    @Test
    fun `a z 1510`() = assertTokenization(
        input = "<a z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("z" to ""))
        )
    )

    @Test
    fun `a 1511`() = assertTokenization(
        input = "<a {>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("{" to ""))
        )
    )

    @Test
    fun `a uDBC0uDC00 1512`() = assertTokenization(
        input = "<a \uDBC0\uDC00>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("\uDBC0\uDC00" to ""))
        )
    )

    @Test
    fun `a 1513`() = assertTokenization(
        input = "<a!>",
        expectedTokens = listOf(
            startTag(name = "a!")
        )
    )

    @Test
    fun `a 1514`() = assertTokenization(
        input = "<a\">",
        expectedTokens = listOf(
            startTag(name = "a\"")
        )
    )

    @Test
    fun `a 1515`() = assertTokenization(
        input = "<a&>",
        expectedTokens = listOf(
            startTag(name = "a&")
        )
    )

    @Test
    fun `a 1516`() = assertTokenization(
        input = "<a'>",
        expectedTokens = listOf(
            startTag(name = "a'")
        )
    )

    @Test
    fun `a 1517`() = assertTokenization(
        input = "<a->",
        expectedTokens = listOf(
            startTag(name = "a-")
        )
    )

    @Test
    fun `a 1518`() = assertTokenization(
        input = "<a.>",
        expectedTokens = listOf(
            startTag(name = "a.")
        )
    )

    @Test
    fun `a 1519`() = assertTokenization(
        input = "<a/>",
        expectedTokens = listOf(
            startTag(name = "a", selfClosing = true)
        )
    )

    @Test
    fun `au0000 1520`() = assertTokenization(
        input = "<a/\u0000>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("�" to ""))
        )
    )

    @Test
    fun `au0009 1521`() = assertTokenization(
        input = "<a/\t>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au000A 1522`() = assertTokenization(
        input = "<a/\n>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au000B 1523`() = assertTokenization(
        input = "<a/\u000b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("\u000b" to ""))
        )
    )

    @Test
    fun `au000C 1524`() = assertTokenization(
        input = "<a/\u000c>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a 1525`() = assertTokenization(
        input = "<a/ >",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a 1526`() = assertTokenization(
        input = "<a/!>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("!" to ""))
        )
    )

    @Test
    fun `a 1527`() = assertTokenization(
        input = "<a/\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("\"" to ""))
        )
    )

    @Test
    fun `a 1528`() = assertTokenization(
        input = "<a/&>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("&" to ""))
        )
    )

    @Test
    fun `a 1529`() = assertTokenization(
        input = "<a/'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("'" to ""))
        )
    )

    @Test
    fun `a 1530`() = assertTokenization(
        input = "<a/->",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("-" to ""))
        )
    )

    @Test
    fun `a 1531`() = assertTokenization(
        input = "<a//>",
        expectedTokens = listOf(
            startTag(name = "a", selfClosing = true)
        )
    )

    @Test
    fun `a0 1532`() = assertTokenization(
        input = "<a/0>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("0" to ""))
        )
    )

    @Test
    fun `a1 1533`() = assertTokenization(
        input = "<a/1>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("1" to ""))
        )
    )

    @Test
    fun `a9 1534`() = assertTokenization(
        input = "<a/9>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("9" to ""))
        )
    )

    @Test
    fun `a 1535`() = assertTokenization(
        input = "<a/<>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("<" to ""))
        )
    )

    @Test
    fun `a 1536`() = assertTokenization(
        input = "<a/=>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("=" to ""))
        )
    )

    @Test
    fun `a 1537`() = assertTokenization(
        input = "<a/>",
        expectedTokens = listOf(
            startTag(name = "a", selfClosing = true)
        )
    )

    @Test
    fun `a 1538`() = assertTokenization(
        input = "<a/?>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("?" to ""))
        )
    )

    @Test
    fun `a 1539`() = assertTokenization(
        input = "<a/@>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("@" to ""))
        )
    )

    @Test
    fun `aA 1540`() = assertTokenization(
        input = "<a/A>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `aB 1541`() = assertTokenization(
        input = "<a/B>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("b" to ""))
        )
    )

    @Test
    fun `aY 1542`() = assertTokenization(
        input = "<a/Y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("y" to ""))
        )
    )

    @Test
    fun `aZ 1543`() = assertTokenization(
        input = "<a/Z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("z" to ""))
        )
    )

    @Test
    fun `a 1544`() = assertTokenization(
        input = "<a/`>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("`" to ""))
        )
    )

    @Test
    fun `aa 1545`() = assertTokenization(
        input = "<a/a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `ab 1546`() = assertTokenization(
        input = "<a/b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("b" to ""))
        )
    )

    @Test
    fun `ay 1547`() = assertTokenization(
        input = "<a/y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("y" to ""))
        )
    )

    @Test
    fun `az 1548`() = assertTokenization(
        input = "<a/z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("z" to ""))
        )
    )

    @Test
    fun `a 1549`() = assertTokenization(
        input = "<a/{>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("{" to ""))
        )
    )

    @Test
    fun `auDBC0uDC00 1550`() = assertTokenization(
        input = "<a/\uDBC0\uDC00>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("\uDBC0\uDC00" to ""))
        )
    )

    @Test
    fun `a0 1551`() = assertTokenization(
        input = "<a0>",
        expectedTokens = listOf(
            startTag(name = "a0")
        )
    )

    @Test
    fun `a1 1552`() = assertTokenization(
        input = "<a1>",
        expectedTokens = listOf(
            startTag(name = "a1")
        )
    )

    @Test
    fun `a9 1553`() = assertTokenization(
        input = "<a9>",
        expectedTokens = listOf(
            startTag(name = "a9")
        )
    )

    @Test
    fun `a 1554`() = assertTokenization(
        input = "<a<>",
        expectedTokens = listOf(
            startTag(name = "a<")
        )
    )

    @Test
    fun `a 1555`() = assertTokenization(
        input = "<a=>",
        expectedTokens = listOf(
            startTag(name = "a=")
        )
    )

    @Test
    fun `a 1556`() = assertTokenization(
        input = "<a>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a 1557`() = assertTokenization(
        input = "<a?>",
        expectedTokens = listOf(
            startTag(name = "a?")
        )
    )

    @Test
    fun `a 1558`() = assertTokenization(
        input = "<a@>",
        expectedTokens = listOf(
            startTag(name = "a@")
        )
    )

    @Test
    fun `aA 1559`() = assertTokenization(
        input = "<aA>",
        expectedTokens = listOf(
            startTag(name = "aa")
        )
    )

    @Test
    fun `aB 1560`() = assertTokenization(
        input = "<aB>",
        expectedTokens = listOf(
            startTag(name = "ab")
        )
    )

    @Test
    fun `aY 1561`() = assertTokenization(
        input = "<aY>",
        expectedTokens = listOf(
            startTag(name = "ay")
        )
    )

    @Test
    fun `aZ 1562`() = assertTokenization(
        input = "<aZ>",
        expectedTokens = listOf(
            startTag(name = "az")
        )
    )

    @Test
    fun `a 1563`() = assertTokenization(
        input = "<a[>",
        expectedTokens = listOf(
            startTag(name = "a[")
        )
    )

    @Test
    fun `a 1564`() = assertTokenization(
        input = "<a`>",
        expectedTokens = listOf(
            startTag(name = "a`")
        )
    )

    @Test
    fun `aa 1565`() = assertTokenization(
        input = "<aa>",
        expectedTokens = listOf(
            startTag(name = "aa")
        )
    )

    @Test
    fun `ab 1566`() = assertTokenization(
        input = "<ab>",
        expectedTokens = listOf(
            startTag(name = "ab")
        )
    )

    @Test
    fun `ay 1567`() = assertTokenization(
        input = "<ay>",
        expectedTokens = listOf(
            startTag(name = "ay")
        )
    )

    @Test
    fun `az 1568`() = assertTokenization(
        input = "<az>",
        expectedTokens = listOf(
            startTag(name = "az")
        )
    )

    @Test
    fun `a 1569`() = assertTokenization(
        input = "<a{>",
        expectedTokens = listOf(
            startTag(name = "a{")
        )
    )

    @Test
    fun `auDBC0uDC00 1570`() = assertTokenization(
        input = "<a\uDBC0\uDC00>",
        expectedTokens = listOf(
            startTag(name = "a\uDBC0\uDC00")
        )
    )

    @Test
    fun `b 1571`() = assertTokenization(
        input = "<b>",
        expectedTokens = listOf(
            startTag(name = "b")
        )
    )

    @Test
    fun `y 1572`() = assertTokenization(
        input = "<y>",
        expectedTokens = listOf(
            startTag(name = "y")
        )
    )

    @Test
    fun `z 1573`() = assertTokenization(
        input = "<z>",
        expectedTokens = listOf(
            startTag(name = "z")
        )
    )

    @Test
    fun `test 1574`() = assertTokenization(
        input = "<{",
        expectedTokens = listOf(
            *chars("<{").toTypedArray()
        )
    )

    @Test
    fun `uDBC0uDC00 1575`() = assertTokenization(
        input = "<\uDBC0\uDC00",
        expectedTokens = listOf(
            *chars("<\uDBC0\uDC00").toTypedArray()
        )
    )

    @Test
    fun `test 1576`() = assertTokenization(
        input = "=",
        expectedTokens = listOf(
            char('=')
        )
    )

    @Test
    fun `test 1577`() = assertTokenization(
        input = "=",
        expectedTokens = listOf(
            char('=')
        )
    )

    @Test
    fun `test 1578`() = assertTokenization(
        input = ">",
        expectedTokens = listOf(
            char('>')
        )
    )

    @Test
    fun `test 1579`() = assertTokenization(
        input = ">",
        expectedTokens = listOf(
            char('>')
        )
    )

    @Test
    fun `test 1580`() = assertTokenization(
        input = "?",
        expectedTokens = listOf(
            char('?')
        )
    )

    @Test
    fun `test 1581`() = assertTokenization(
        input = "?",
        expectedTokens = listOf(
            char('?')
        )
    )

    @Test
    fun `test 1582`() = assertTokenization(
        input = "@",
        expectedTokens = listOf(
            char('@')
        )
    )

    @Test
    fun `test 1583`() = assertTokenization(
        input = "@",
        expectedTokens = listOf(
            char('@')
        )
    )

    @Test
    fun `A 1584`() = assertTokenization(
        input = "A",
        expectedTokens = listOf(
            char('A')
        )
    )

    @Test
    fun `A 1585`() = assertTokenization(
        input = "A",
        expectedTokens = listOf(
            char('A')
        )
    )

    @Test
    fun `B 1586`() = assertTokenization(
        input = "B",
        expectedTokens = listOf(
            char('B')
        )
    )

    @Test
    fun `B 1587`() = assertTokenization(
        input = "B",
        expectedTokens = listOf(
            char('B')
        )
    )

    @Test
    fun `Y 1588`() = assertTokenization(
        input = "Y",
        expectedTokens = listOf(
            char('Y')
        )
    )

    @Test
    fun `Y 1589`() = assertTokenization(
        input = "Y",
        expectedTokens = listOf(
            char('Y')
        )
    )

    @Test
    fun `Z 1590`() = assertTokenization(
        input = "Z",
        expectedTokens = listOf(
            char('Z')
        )
    )

    @Test
    fun `Z 1591`() = assertTokenization(
        input = "Z",
        expectedTokens = listOf(
            char('Z')
        )
    )

    @Test
    fun `test 1592`() = assertTokenization(
        input = "`",
        expectedTokens = listOf(
            char('\u0060')
        )
    )

    @Test
    fun `test 1593`() = assertTokenization(
        input = "`",
        expectedTokens = listOf(
            char('\u0060')
        )
    )

    @Test
    fun `a 1594`() = assertTokenization(
        input = "a",
        expectedTokens = listOf(
            char('a')
        )
    )

    @Test
    fun `a 1595`() = assertTokenization(
        input = "a",
        expectedTokens = listOf(
            char('a')
        )
    )

    @Test
    fun `b 1596`() = assertTokenization(
        input = "b",
        expectedTokens = listOf(
            char('b')
        )
    )

    @Test
    fun `b 1597`() = assertTokenization(
        input = "b",
        expectedTokens = listOf(
            char('b')
        )
    )

    @Test
    fun `y 1598`() = assertTokenization(
        input = "y",
        expectedTokens = listOf(
            char('y')
        )
    )

    @Test
    fun `y 1599`() = assertTokenization(
        input = "y",
        expectedTokens = listOf(
            char('y')
        )
    )

    @Test
    fun `z 1600`() = assertTokenization(
        input = "z",
        expectedTokens = listOf(
            char('z')
        )
    )

    @Test
    fun `z 1601`() = assertTokenization(
        input = "z",
        expectedTokens = listOf(
            char('z')
        )
    )

    @Test
    fun `test 1602`() = assertTokenization(
        input = "{",
        expectedTokens = listOf(
            char('{')
        )
    )

    @Test
    fun `test 1603`() = assertTokenization(
        input = "{",
        expectedTokens = listOf(
            char('{')
        )
    )

    @Test
    fun `uDBC0uDC00 1604`() = assertTokenization(
        input = "\uDBC0\uDC00",
        expectedTokens = listOf(
            *chars("\uDBC0\uDC00").toTypedArray()
        )
    )

    @Test
    fun `uDBC0uDC00 1605`() = assertTokenization(
        input = "\uDBC0\uDC00",
        expectedTokens = listOf(
            *chars("\uDBC0\uDC00").toTypedArray()
        )
    )
}
