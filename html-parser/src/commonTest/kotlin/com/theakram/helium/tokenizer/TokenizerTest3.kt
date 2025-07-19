package com.theakram.helium.tokenizer

import kotlin.test.Test

/**
 * Contains all 1590 test from here:
 * https://github.com/html5lib/html5lib-tests/blob/master/tokenizer/test3.test
 *
 */
@Suppress("RemoveRedundantBackticks")
internal class TokenizerTest3 : TokenizerTestBase() {

    @Test
    fun `empty`() = assertTokenization(
        input = "",
        expectedTokens = listOf()
    )

    @Test
    fun `empty 2`() = assertTokenization(
        input = "",
        expectedTokens = listOf()
    )

    @Test
    fun `u0009`() = assertTokenization(
        input = "\t",
        expectedTokens = listOf(
            char('\t')
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
    fun u000A() = assertTokenization(
        input = "\n",
        expectedTokens = listOf(
            char('\n')
        )
    )

    @Test
    fun `u000A 2`() = assertTokenization(
        input = "\n",
        expectedTokens = listOf(
            char('\n')
        )
    )

    @Test
    fun u000B() = assertTokenization(
        input = "",
        expectedTokens = listOf(
            char('')
        )
    )

    @Test
    fun `u000B 2`() = assertTokenization(
        input = "",
        expectedTokens = listOf(
            char('')
        )
    )

    @Test
    fun `u000C`() = assertTokenization(
        input = "",
        expectedTokens = listOf(
            char('')
        )
    )

    @Test
    fun `u000C 2`() = assertTokenization(
        input = "",
        expectedTokens = listOf(
            char('')
        )
    )

    @Test
    fun `whitespace`() = assertTokenization(
        input = " ",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun ` 2`() = assertTokenization(
        input = " ",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun ` 3`() = assertTokenization(
        input = "!",
        expectedTokens = listOf(
            char('!')
        )
    )

    @Test
    fun ` 4`() = assertTokenization(
        input = "!",
        expectedTokens = listOf(
            char('!')
        )
    )

    @Test
    fun ` 5`() = assertTokenization(
        input = "\"",
        expectedTokens = listOf(
            char('\"')
        )
    )

    @Test
    fun ` 6`() = assertTokenization(
        input = "\"",
        expectedTokens = listOf(
            char('\"')
        )
    )

    @Test
    fun ` 7`() = assertTokenization(
        input = "%",
        expectedTokens = listOf(
            char('%')
        )
    )

    @Test
    fun ` 8`() = assertTokenization(
        input = "%",
        expectedTokens = listOf(
            char('%')
        )
    )

    @Test
    fun ` 9`() = assertTokenization(
        input = "&",
        expectedTokens = listOf(
            char('&')
        )
    )

    @Test
    fun ` 10`() = assertTokenization(
        input = "&",
        expectedTokens = listOf(
            char('&')
        )
    )

    @Test
    fun ` 11`() = assertTokenization(
        input = "'",
        expectedTokens = listOf(char('\''))
    )

    @Test
    fun ` 12`() = assertTokenization(
        input = "'",
        expectedTokens = listOf(char('\''))
    )

    @Test
    fun ` 13`() = assertTokenization(
        input = ",",
        expectedTokens = listOf(char(','))
    )

    @Test
    fun ` 14`() = assertTokenization(
        input = ",",
        expectedTokens = listOf(char(','))
    )

    @Test
    fun ` 15`() = assertTokenization(
        input = "-",
        expectedTokens = listOf(
            char('-')
        )
    )

    @Test
    fun ` 16`() = assertTokenization(
        input = "-",
        expectedTokens = listOf(
            char('-')
        )
    )

    @Test
    fun ` 17`() = assertTokenization(
        input = ".",
        expectedTokens = listOf(
            char('.')
        )
    )

    @Test
    fun ` 18`() = assertTokenization(
        input = ".",
        expectedTokens = listOf(
            char('.')
        )
    )

    @Test
    fun ` 19`() = assertTokenization(
        input = "/",
        expectedTokens = listOf(
            char('/')
        )
    )

    @Test
    fun ` 20`() = assertTokenization(
        input = "/",
        expectedTokens = listOf(
            char('/')
        )
    )

    @Test
    fun `0`() = assertTokenization(
        input = "0",
        expectedTokens = listOf(
            char('0')
        )
    )

    @Test
    fun `0 2`() = assertTokenization(
        input = "0",
        expectedTokens = listOf(
            char('0')
        )
    )

    @Test
    fun `1`() = assertTokenization(
        input = "1",
        expectedTokens = listOf(
            char('1')
        )
    )

    @Test
    fun `1 2`() = assertTokenization(
        input = "1",
        expectedTokens = listOf(
            char('1')
        )
    )

    @Test
    fun `9`() = assertTokenization(
        input = "9",
        expectedTokens = listOf(
            char('9')
        )
    )

    @Test
    fun `9 2`() = assertTokenization(
        input = "9",
        expectedTokens = listOf(
            char('9')
        )
    )

    @Test
    fun ` 21`() = assertTokenization(
        input = ";",
        expectedTokens = listOf(
            char(';')
        )
    )

    @Test
    fun ` 22`() = assertTokenization(
        input = ";",
        expectedTokens = listOf(
            char(';')
        )
    )

    @Test
    fun ` 23`() = assertTokenization(
        input = ";=",
        expectedTokens = listOf(
            *chars(";=").toTypedArray()
        )
    )

    @Test
    fun ` 24`() = assertTokenization(
        input = ";=",
        expectedTokens = listOf(
            *chars(";=").toTypedArray()
        )
    )

    @Test
    fun ` 25`() = assertTokenization(
        input = ";>",
        expectedTokens = listOf(
            *chars(";>").toTypedArray()
        )
    )

    @Test
    fun ` 26`() = assertTokenization(
        input = ";>",
        expectedTokens = listOf(
            *chars(";>").toTypedArray()
        )
    )

    @Test
    fun ` 27`() = assertTokenization(
        input = ";?",
        expectedTokens = listOf(
            *chars(";?").toTypedArray()
        )
    )

    @Test
    fun ` 28`() = assertTokenization(
        input = ";?",
        expectedTokens = listOf(
            *chars(";?").toTypedArray()
        )
    )

    @Test
    fun ` 29`() = assertTokenization(
        input = ";@",
        expectedTokens = listOf(
            *chars(";@").toTypedArray()
        )
    )

    @Test
    fun ` 30`() = assertTokenization(
        input = ";@",
        expectedTokens = listOf(
            *chars(";@").toTypedArray()
        )
    )

    @Test
    fun `A`() = assertTokenization(
        input = ";A",
        expectedTokens = listOf(
            *chars(";A").toTypedArray()
        )
    )

    @Test
    fun `A 2`() = assertTokenization(
        input = ";A",
        expectedTokens = listOf(
            *chars(";A").toTypedArray()
        )
    )

    @Test
    fun B() = assertTokenization(
        input = ";B",
        expectedTokens = listOf(
            *chars(";B").toTypedArray()
        )
    )

    @Test
    fun `B 2`() = assertTokenization(
        input = ";B",
        expectedTokens = listOf(
            *chars(";B").toTypedArray()
        )
    )

    @Test
    fun `Y`() = assertTokenization(
        input = ";Y",
        expectedTokens = listOf(
            *chars(";Y").toTypedArray()
        )
    )

    @Test
    fun `Y 2`() = assertTokenization(
        input = ";Y",
        expectedTokens = listOf(
            *chars(";Y").toTypedArray()
        )
    )

    @Test
    fun `Z`() = assertTokenization(
        input = ";Z",
        expectedTokens = listOf(
            *chars(";Z").toTypedArray()
        )
    )

    @Test
    fun `Z 2`() = assertTokenization(
        input = ";Z",
        expectedTokens = listOf(
            *chars(";Z").toTypedArray()
        )
    )

    @Test
    fun ` 31`() = assertTokenization(
        input = ";`",
        expectedTokens = listOf(
            *chars(";`").toTypedArray()
        )
    )

    @Test
    fun ` 32`() = assertTokenization(
        input = ";`",
        expectedTokens = listOf(
            *chars(";`").toTypedArray()
        )
    )

    @Test
    fun a() = assertTokenization(
        input = ";a",
        expectedTokens = listOf(
            *chars(";a").toTypedArray()
        )
    )

    @Test
    fun `a 2`() = assertTokenization(
        input = ";a",
        expectedTokens = listOf(
            *chars(";a").toTypedArray()
        )
    )

    @Test
    fun b() = assertTokenization(
        input = ";b",
        expectedTokens = listOf(
            *chars(";b").toTypedArray()
        )
    )

    @Test
    fun `b 2`() = assertTokenization(
        input = ";b",
        expectedTokens = listOf(
            *chars(";b").toTypedArray()
        )
    )

    @Test
    fun y() = assertTokenization(
        input = ";y",
        expectedTokens = listOf(
            *chars(";y").toTypedArray()
        )
    )

    @Test
    fun `y 2`() = assertTokenization(
        input = ";y",
        expectedTokens = listOf(
            *chars(";y").toTypedArray()
        )
    )

    @Test
    fun z() = assertTokenization(
        input = ";z",
        expectedTokens = listOf(
            *chars(";z").toTypedArray()
        )
    )

    @Test
    fun `z 2`() = assertTokenization(
        input = ";z",
        expectedTokens = listOf(
            *chars(";z").toTypedArray()
        )
    )

    @Test
    fun ` 33`() = assertTokenization(
        input = ";{",
        expectedTokens = listOf(
            *chars(";{").toTypedArray()
        )
    )

    @Test
    fun ` 34`() = assertTokenization(
        input = ";{",
        expectedTokens = listOf(
            *chars(";{").toTypedArray()
        )
    )

    @Test
    fun uDBC0uDC00() = assertTokenization(
        input = ";􀀀",
        expectedTokens = listOf(
            *chars(";􀀀").toTypedArray()
        )
    )

    @Test
    fun `uDBC0uDC00 2`() = assertTokenization(
        input = ";􀀀",
        expectedTokens = listOf(
            *chars(";􀀀").toTypedArray()
        )
    )

    @Test
    fun ` 35`() = assertTokenization(
        input = "<",
        expectedTokens = listOf(
            char('<')
        )
    )

    @Test
    fun `u0000`() = assertTokenization(
        input = "<\u0000",
        expectedTokens = listOf(
            *chars("<\u0000").toTypedArray()
        )
    )

    @Test
    fun `u0009 3`() = assertTokenization(
        input = "<\t",
        expectedTokens = listOf(
            *chars("<\t").toTypedArray()
        )
    )

    @Test
    fun `u000A 3`() = assertTokenization(
        input = "<\n",
        expectedTokens = listOf(
            *chars("<\n").toTypedArray()
        )
    )

    @Test
    fun `u000B 3`() = assertTokenization(
        input = "<",
        expectedTokens = listOf(
            *chars("<").toTypedArray()
        )
    )

    @Test
    fun `u000C 3`() = assertTokenization(
        input = "<",
        expectedTokens = listOf(
            *chars("<").toTypedArray()
        )
    )

    @Test
    fun ` 36`() = assertTokenization(
        input = "< ",
        expectedTokens = listOf(
            *chars("< ").toTypedArray()
        )
    )

    @Test
    fun ` 37`() = assertTokenization(
        input = "<!",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `u0000 2`() = assertTokenization(
        input = "<!\u0000",
        expectedTokens = listOf(
            comment("�")
        )
    )

    @Test
    fun `u0009 4`() = assertTokenization(
        input = "<!\t",
        expectedTokens = listOf(
            comment("	")
        )
    )

    @Test
    fun `u000A 4`() = assertTokenization(
        input = "<!\n",
        expectedTokens = listOf(comment("\n"))
    )

    @Test
    fun `u000B 4`() = assertTokenization(
        input = "<!",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `u000C 4`() = assertTokenization(
        input = "<!",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun ` 38`() = assertTokenization(
        input = "<! ",
        expectedTokens = listOf(
            comment(" ")
        )
    )

    @Test
    fun `u0000 3`() = assertTokenization(
        input = "<! \u0000",
        expectedTokens = listOf(
            comment(" �")
        )
    )

    @Test
    fun ` 39`() = assertTokenization(
        input = "<!!",
        expectedTokens = listOf(
            comment("!")
        )
    )

    @Test
    fun ` 40`() = assertTokenization(
        input = "<!\"",
        expectedTokens = listOf(comment("\""))
    )

    @Test
    fun ` 41`() = assertTokenization(
        input = "<!&",
        expectedTokens = listOf(
            comment("&")
        )
    )

    @Test
    fun ` 42`() = assertTokenization(
        input = "<!'",
        expectedTokens = listOf(
            comment("'")
        )
    )

    @Test
    fun ` 43`() = assertTokenization(
        input = "<!-",
        expectedTokens = listOf(
            comment("-")
        )
    )

    @Test
    fun ` 44`() = assertTokenization(
        input = "<!--",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `u0000 4`() = assertTokenization(
        input = "<!--\u0000",
        expectedTokens = listOf(
            comment("�")
        )
    )

    @Test
    fun `u0009 5`() = assertTokenization(
        input = "<!--\t",
        expectedTokens = listOf(
            comment("	")
        )
    )

    @Test
    fun `u000A 5`() = assertTokenization(
        input = "<!--\n",
        expectedTokens = listOf(comment("\n"))
    )

    @Test
    fun `u000B 5`() = assertTokenization(
        input = "<!--",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `u000C 5`() = assertTokenization(
        input = "<!--",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun ` 45`() = assertTokenization(
        input = "<!-- ",
        expectedTokens = listOf(
            comment(" ")
        )
    )

    @Test
    fun `u0000 5`() = assertTokenization(
        input = "<!-- \u0000",
        expectedTokens = listOf(
            comment(" �")
        )
    )

    @Test
    fun `u0009 6`() = assertTokenization(
        input = "<!-- \t",
        expectedTokens = listOf(
            comment(" 	")
        )
    )

    @Test
    fun `u000A 6`() = assertTokenization(
        input = "<!-- \n",
        expectedTokens = listOf(comment(" \n"))
    )

    @Test
    fun `u000B 6`() = assertTokenization(
        input = "<!-- ",
        expectedTokens = listOf(
            comment(" ")
        )
    )

    @Test
    fun `u000C 6`() = assertTokenization(
        input = "<!-- ",
        expectedTokens = listOf(
            comment(" ")
        )
    )

    @Test
    fun ` 46`() = assertTokenization(
        input = "<!--  ",
        expectedTokens = listOf(
            comment("  ")
        )
    )

    @Test
    fun ` 47`() = assertTokenization(
        input = "<!-- !",
        expectedTokens = listOf(
            comment(" !")
        )
    )

    @Test
    fun ` 48`() = assertTokenization(
        input = "<!-- \"",
        expectedTokens = listOf(comment(" \""))
    )

    @Test
    fun ` 49`() = assertTokenization(
        input = "<!-- &",
        expectedTokens = listOf(
            comment(" &")
        )
    )

    @Test
    fun ` 50`() = assertTokenization(
        input = "<!-- '",
        expectedTokens = listOf(
            comment(" '")
        )
    )

    @Test
    fun ` 51`() = assertTokenization(
        input = "<!-- ,",
        expectedTokens = listOf(
            comment(" ,")
        )
    )

    @Test
    fun ` 52`() = assertTokenization(
        input = "<!-- -",
        expectedTokens = listOf(
            comment(" ")
        )
    )

    @Test
    fun `u0000 6`() = assertTokenization(
        input = "<!-- -\u0000",
        expectedTokens = listOf(
            comment(" -�")
        )
    )

    @Test
    fun `u0009 7`() = assertTokenization(
        input = "<!-- -\t",
        expectedTokens = listOf(
            comment(" -	")
        )
    )

    @Test
    fun `u000A 7`() = assertTokenization(
        input = "<!-- -\n",
        expectedTokens = listOf(comment(" -\n"))
    )

    @Test
    fun `u000B 7`() = assertTokenization(
        input = "<!-- -",
        expectedTokens = listOf(
            comment(" -")
        )
    )

    @Test
    fun `u000C 7`() = assertTokenization(
        input = "<!-- -",
        expectedTokens = listOf(
            comment(" -")
        )
    )

    @Test
    fun ` 53`() = assertTokenization(
        input = "<!-- - ",
        expectedTokens = listOf(
            comment(" - ")
        )
    )

    @Test
    fun ` 54`() = assertTokenization(
        input = "<!-- -!",
        expectedTokens = listOf(
            comment(" -!")
        )
    )

    @Test
    fun ` 55`() = assertTokenization(
        input = "<!-- -\"",
        expectedTokens = listOf(comment(" -\""))
    )

    @Test
    fun ` 56`() = assertTokenization(
        input = "<!-- -&",
        expectedTokens = listOf(
            comment(" -&")
        )
    )

    @Test
    fun ` 57`() = assertTokenization(
        input = "<!-- -'",
        expectedTokens = listOf(
            comment(" -'")
        )
    )

    @Test
    fun ` 58`() = assertTokenization(
        input = "<!-- -,",
        expectedTokens = listOf(
            comment(" -,")
        )
    )

    @Test
    fun ` 59`() = assertTokenization(
        input = "<!-- --",
        expectedTokens = listOf(
            comment(" ")
        )
    )

    @Test
    fun ` 60`() = assertTokenization(
        input = "<!-- -.",
        expectedTokens = listOf(
            comment(" -.")
        )
    )

    @Test
    fun ` 61`() = assertTokenization(
        input = "<!-- -/",
        expectedTokens = listOf(
            comment(" -/")
        )
    )

    @Test
    fun `0 3`() = assertTokenization(
        input = "<!-- -0",
        expectedTokens = listOf(
            comment(" -0")
        )
    )

    @Test
    fun `1 3`() = assertTokenization(
        input = "<!-- -1",
        expectedTokens = listOf(
            comment(" -1")
        )
    )

    @Test
    fun `9 3`() = assertTokenization(
        input = "<!-- -9",
        expectedTokens = listOf(
            comment(" -9")
        )
    )

    @Test
    fun ` 62`() = assertTokenization(
        input = "<!-- -<",
        expectedTokens = listOf(
            comment(" -<")
        )
    )

    @Test
    fun ` 63`() = assertTokenization(
        input = "<!-- -=",
        expectedTokens = listOf(
            comment(" -=")
        )
    )

    @Test
    fun ` 64`() = assertTokenization(
        input = "<!-- ->",
        expectedTokens = listOf(
            comment(" ->")
        )
    )

    @Test
    fun ` 65`() = assertTokenization(
        input = "<!-- -?",
        expectedTokens = listOf(
            comment(" -?")
        )
    )

    @Test
    fun ` 66`() = assertTokenization(
        input = "<!-- -@",
        expectedTokens = listOf(
            comment(" -@")
        )
    )

    @Test
    fun `A 3`() = assertTokenization(
        input = "<!-- -A",
        expectedTokens = listOf(
            comment(" -A")
        )
    )

    @Test
    fun `B 3`() = assertTokenization(
        input = "<!-- -B",
        expectedTokens = listOf(
            comment(" -B")
        )
    )

    @Test
    fun `Y 3`() = assertTokenization(
        input = "<!-- -Y",
        expectedTokens = listOf(
            comment(" -Y")
        )
    )

    @Test
    fun `Z 3`() = assertTokenization(
        input = "<!-- -Z",
        expectedTokens = listOf(
            comment(" -Z")
        )
    )

    @Test
    fun ` 67`() = assertTokenization(
        input = "<!-- -`",
        expectedTokens = listOf(
            comment(" -`")
        )
    )

    @Test
    fun `a 3`() = assertTokenization(
        input = "<!-- -a",
        expectedTokens = listOf(
            comment(" -a")
        )
    )

    @Test
    fun `b 3`() = assertTokenization(
        input = "<!-- -b",
        expectedTokens = listOf(
            comment(" -b")
        )
    )

    @Test
    fun `y 3`() = assertTokenization(
        input = "<!-- -y",
        expectedTokens = listOf(
            comment(" -y")
        )
    )

    @Test
    fun `z 3`() = assertTokenization(
        input = "<!-- -z",
        expectedTokens = listOf(
            comment(" -z")
        )
    )

    @Test
    fun ` 68`() = assertTokenization(
        input = "<!-- -{",
        expectedTokens = listOf(
            comment(" -{")
        )
    )

    @Test
    fun `uDBC0uDC00 3`() = assertTokenization(
        input = "<!-- -􀀀",
        expectedTokens = listOf(
            comment(" -􀀀")
        )
    )

    @Test
    fun ` 69`() = assertTokenization(
        input = "<!-- .",
        expectedTokens = listOf(
            comment(" .")
        )
    )

    @Test
    fun ` 70`() = assertTokenization(
        input = "<!-- /",
        expectedTokens = listOf(
            comment(" /")
        )
    )

    @Test
    fun `0 4`() = assertTokenization(
        input = "<!-- 0",
        expectedTokens = listOf(
            comment(" 0")
        )
    )

    @Test
    fun `1 4`() = assertTokenization(
        input = "<!-- 1",
        expectedTokens = listOf(
            comment(" 1")
        )
    )

    @Test
    fun `9 4`() = assertTokenization(
        input = "<!-- 9",
        expectedTokens = listOf(
            comment(" 9")
        )
    )

    @Test
    fun ` 71`() = assertTokenization(
        input = "<!-- <",
        expectedTokens = listOf(
            comment(" <")
        )
    )

    @Test
    fun ` 72`() = assertTokenization(
        input = "<!-- =",
        expectedTokens = listOf(
            comment(" =")
        )
    )

    @Test
    fun ` 73`() = assertTokenization(
        input = "<!-- >",
        expectedTokens = listOf(
            comment(" >")
        )
    )

    @Test
    fun ` 74`() = assertTokenization(
        input = "<!-- ?",
        expectedTokens = listOf(
            comment(" ?")
        )
    )

    @Test
    fun ` 75`() = assertTokenization(
        input = "<!-- @",
        expectedTokens = listOf(
            comment(" @")
        )
    )

    @Test
    fun `A 4`() = assertTokenization(
        input = "<!-- A",
        expectedTokens = listOf(
            comment(" A")
        )
    )

    @Test
    fun `B 4`() = assertTokenization(
        input = "<!-- B",
        expectedTokens = listOf(
            comment(" B")
        )
    )

    @Test
    fun `Y 4`() = assertTokenization(
        input = "<!-- Y",
        expectedTokens = listOf(
            comment(" Y")
        )
    )

    @Test
    fun `Z 4`() = assertTokenization(
        input = "<!-- Z",
        expectedTokens = listOf(
            comment(" Z")
        )
    )

    @Test
    fun ` 76`() = assertTokenization(
        input = "<!-- `",
        expectedTokens = listOf(
            comment(" `")
        )
    )

    @Test
    fun `a 4`() = assertTokenization(
        input = "<!-- a",
        expectedTokens = listOf(
            comment(" a")
        )
    )

    @Test
    fun `b 4`() = assertTokenization(
        input = "<!-- b",
        expectedTokens = listOf(
            comment(" b")
        )
    )

    @Test
    fun `y 4`() = assertTokenization(
        input = "<!-- y",
        expectedTokens = listOf(
            comment(" y")
        )
    )

    @Test
    fun `z 4`() = assertTokenization(
        input = "<!-- z",
        expectedTokens = listOf(
            comment(" z")
        )
    )

    @Test
    fun ` 77`() = assertTokenization(
        input = "<!-- {",
        expectedTokens = listOf(
            comment(" {")
        )
    )

    @Test
    fun `uDBC0uDC00 4`() = assertTokenization(
        input = "<!-- 􀀀",
        expectedTokens = listOf(
            comment(" 􀀀")
        )
    )

    @Test
    fun ` 78`() = assertTokenization(
        input = "<!--!",
        expectedTokens = listOf(
            comment("!")
        )
    )

    @Test
    fun ` 79`() = assertTokenization(
        input = "<!--\"",
        expectedTokens = listOf(comment("\""))
    )

    @Test
    fun ` 80`() = assertTokenization(
        input = "<!--&",
        expectedTokens = listOf(
            comment("&")
        )
    )

    @Test
    fun ` 81`() = assertTokenization(
        input = "<!--'",
        expectedTokens = listOf(
            comment("'")
        )
    )

    @Test
    fun ` 82`() = assertTokenization(
        input = "<!--,",
        expectedTokens = listOf(
            comment(",")
        )
    )

    @Test
    fun ` 83`() = assertTokenization(
        input = "<!---",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `u0000 7`() = assertTokenization(
        input = "<!---\u0000",
        expectedTokens = listOf(
            comment("-�")
        )
    )

    @Test
    fun `u0009 8`() = assertTokenization(
        input = "<!---\t",
        expectedTokens = listOf(
            comment("-	")
        )
    )

    @Test
    fun `u000A 8`() = assertTokenization(
        input = "<!---\n",
        expectedTokens = listOf(comment("-\n"))
    )

    @Test
    fun `u000B 8`() = assertTokenization(
        input = "<!---",
        expectedTokens = listOf(
            comment("-")
        )
    )

    @Test
    fun `u000C 8`() = assertTokenization(
        input = "<!---",
        expectedTokens = listOf(
            comment("-")
        )
    )

    @Test
    fun ` 84`() = assertTokenization(
        input = "<!--- ",
        expectedTokens = listOf(
            comment("- ")
        )
    )

    @Test
    fun ` 85`() = assertTokenization(
        input = "<!---!",
        expectedTokens = listOf(
            comment("-!")
        )
    )

    @Test
    fun ` 86`() = assertTokenization(
        input = "<!---\"",
        expectedTokens = listOf(comment("-\""))
    )

    @Test
    fun ` 87`() = assertTokenization(
        input = "<!---&",
        expectedTokens = listOf(
            comment("-&")
        )
    )

    @Test
    fun ` 88`() = assertTokenization(
        input = "<!---'",
        expectedTokens = listOf(
            comment("-'")
        )
    )

    @Test
    fun ` 89`() = assertTokenization(
        input = "<!---,",
        expectedTokens = listOf(
            comment("-,")
        )
    )

    @Test
    fun ` 90`() = assertTokenization(
        input = "<!----",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `u0000 8`() = assertTokenization(
        input = "<!----\u0000",
        expectedTokens = listOf(
            comment("--�")
        )
    )

    @Test
    fun `u0009 9`() = assertTokenization(
        input = "<!----\t",
        expectedTokens = listOf(
            comment("--	")
        )
    )

    @Test
    fun `u000A 9`() = assertTokenization(
        input = "<!----\n",
        expectedTokens = listOf(comment("--\n"))
    )

    @Test
    fun `u000B 9`() = assertTokenization(
        input = "<!----",
        expectedTokens = listOf(
            comment("--")
        )
    )

    @Test
    fun `u000C 9`() = assertTokenization(
        input = "<!----",
        expectedTokens = listOf(
            comment("--")
        )
    )

    @Test
    fun ` 91`() = assertTokenization(
        input = "<!---- ",
        expectedTokens = listOf(
            comment("-- ")
        )
    )

    @Test
    fun ` 92`() = assertTokenization(
        input = "<!---- -",
        expectedTokens = listOf(
            comment("-- ")
        )
    )

    @Test
    fun ` 93`() = assertTokenization(
        input = "<!---- --",
        expectedTokens = listOf(
            comment("-- ")
        )
    )

    @Test
    fun ` 94`() = assertTokenization(
        input = "<!---- -->",
        expectedTokens = listOf(
            comment("-- ")
        )
    )

    @Test
    fun ` 95`() = assertTokenization(
        input = "<!----  -->",
        expectedTokens = listOf(
            comment("--  ")
        )
    )

    @Test
    fun `a 5`() = assertTokenization(
        input = "<!---- a-->",
        expectedTokens = listOf(
            comment("-- a")
        )
    )

    @Test
    fun ` 96`() = assertTokenization(
        input = "<!----!",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun ` 97`() = assertTokenization(
        input = "<!----!>",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun ` 98`() = assertTokenization(
        input = "<!----! >",
        expectedTokens = listOf(
            comment("--! >")
        )
    )

    @Test
    fun `LF`() = assertTokenization(
        input = "<!----!\n>",
        expectedTokens = listOf(comment("--! \n> "))
    )

    @Test
    fun `CR`() = assertTokenization(
        input = "<!----!\r>",
        expectedTokens = listOf(
            comment("--! \n> ")
        )
    )

    @Test
    fun `CRLF`() = assertTokenization(
        input = "<!----!\r\n>",
        expectedTokens = listOf(comment("--! \n> "))
    )

    @Test
    fun `a 6`() = assertTokenization(
        input = "<!----!a",
        expectedTokens = listOf(
            comment("--!a")
        )
    )

    @Test
    fun `a 7`() = assertTokenization(
        input = "<!----!a-",
        expectedTokens = listOf(
            comment("--!a")
        )
    )

    @Test
    fun `a 8`() = assertTokenization(
        input = "<!----!a--",
        expectedTokens = listOf(
            comment("--!a")
        )
    )

    @Test
    fun `a 9`() = assertTokenization(
        input = "<!----!a-->",
        expectedTokens = listOf(
            comment("--!a")
        )
    )

    @Test
    fun ` 99`() = assertTokenization(
        input = "<!----!-",
        expectedTokens = listOf(
            comment("--!")
        )
    )

    @Test
    fun ` 100`() = assertTokenization(
        input = "<!----!--",
        expectedTokens = listOf(
            comment("--!")
        )
    )

    @Test
    fun ` 101`() = assertTokenization(
        input = "<!----!-->",
        expectedTokens = listOf(
            comment("--!")
        )
    )

    @Test
    fun ` 102`() = assertTokenization(
        input = "<!----\"",
        expectedTokens = listOf(comment("--\""))
    )

    @Test
    fun ` 103`() = assertTokenization(
        input = "<!----&",
        expectedTokens = listOf(
            comment("--&")
        )
    )

    @Test
    fun ` 104`() = assertTokenization(
        input = "<!----'",
        expectedTokens = listOf(
            comment("--'")
        )
    )

    @Test
    fun ` 105`() = assertTokenization(
        input = "<!----,",
        expectedTokens = listOf(
            comment("--,")
        )
    )

    @Test
    fun ` 106`() = assertTokenization(
        input = "<!-----",
        expectedTokens = listOf(
            comment("-")
        )
    )

    @Test
    fun ` 107`() = assertTokenization(
        input = "<!----.",
        expectedTokens = listOf(
            comment("--.")
        )
    )

    @Test
    fun ` 108`() = assertTokenization(
        input = "<!----/",
        expectedTokens = listOf(
            comment("--/")
        )
    )

    @Test
    fun `0 5`() = assertTokenization(
        input = "<!----0",
        expectedTokens = listOf(
            comment("--0")
        )
    )

    @Test
    fun `1 5`() = assertTokenization(
        input = "<!----1",
        expectedTokens = listOf(
            comment("--1")
        )
    )

    @Test
    fun `9 5`() = assertTokenization(
        input = "<!----9",
        expectedTokens = listOf(
            comment("--9")
        )
    )

    @Test
    fun ` 109`() = assertTokenization(
        input = "<!----<",
        expectedTokens = listOf(
            comment("--<")
        )
    )

    @Test
    fun ` 110`() = assertTokenization(
        input = "<!----=",
        expectedTokens = listOf(
            comment("--=")
        )
    )

    @Test
    fun ` 111`() = assertTokenization(
        input = "<!---->",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun ` 112`() = assertTokenization(
        input = "<!----?",
        expectedTokens = listOf(
            comment("--?")
        )
    )

    @Test
    fun ` 113`() = assertTokenization(
        input = "<!----@",
        expectedTokens = listOf(
            comment("--@")
        )
    )

    @Test
    fun `A 5`() = assertTokenization(
        input = "<!----A",
        expectedTokens = listOf(
            comment("--A")
        )
    )

    @Test
    fun `B 5`() = assertTokenization(
        input = "<!----B",
        expectedTokens = listOf(
            comment("--B")
        )
    )

    @Test
    fun `Y 5`() = assertTokenization(
        input = "<!----Y",
        expectedTokens = listOf(
            comment("--Y")
        )
    )

    @Test
    fun `Z 5`() = assertTokenization(
        input = "<!----Z",
        expectedTokens = listOf(
            comment("--Z")
        )
    )

    @Test
    fun ` 114`() = assertTokenization(
        input = "<!----`",
        expectedTokens = listOf(
            comment("--`")
        )
    )

    @Test
    fun `a 10`() = assertTokenization(
        input = "<!----a",
        expectedTokens = listOf(
            comment("--a")
        )
    )

    @Test
    fun `b 5`() = assertTokenization(
        input = "<!----b",
        expectedTokens = listOf(
            comment("--b")
        )
    )

    @Test
    fun `y 5`() = assertTokenization(
        input = "<!----y",
        expectedTokens = listOf(
            comment("--y")
        )
    )

    @Test
    fun `z 5`() = assertTokenization(
        input = "<!----z",
        expectedTokens = listOf(
            comment("--z")
        )
    )

    @Test
    fun ` 115`() = assertTokenization(
        input = "<!----{",
        expectedTokens = listOf(
            comment("--{")
        )
    )

    @Test
    fun `uDBC0uDC00 5`() = assertTokenization(
        input = "<!----􀀀",
        expectedTokens = listOf(
            comment("--􀀀")
        )
    )

    @Test
    fun ` 116`() = assertTokenization(
        input = "<!---.",
        expectedTokens = listOf(
            comment("-.")
        )
    )

    @Test
    fun ` 117`() = assertTokenization(
        input = "<!---/",
        expectedTokens = listOf(
            comment("-/")
        )
    )

    @Test
    fun `0 6`() = assertTokenization(
        input = "<!---0",
        expectedTokens = listOf(
            comment("-0")
        )
    )

    @Test
    fun `1 6`() = assertTokenization(
        input = "<!---1",
        expectedTokens = listOf(
            comment("-1")
        )
    )

    @Test
    fun `9 6`() = assertTokenization(
        input = "<!---9",
        expectedTokens = listOf(
            comment("-9")
        )
    )

    @Test
    fun ` 118`() = assertTokenization(
        input = "<!---<",
        expectedTokens = listOf(
            comment("-<")
        )
    )

    @Test
    fun ` 119`() = assertTokenization(
        input = "<!---=",
        expectedTokens = listOf(
            comment("-=")
        )
    )

    @Test
    fun ` 120`() = assertTokenization(
        input = "<!---?",
        expectedTokens = listOf(
            comment("-?")
        )
    )

    @Test
    fun ` 121`() = assertTokenization(
        input = "<!---@",
        expectedTokens = listOf(
            comment("-@")
        )
    )

    @Test
    fun `A 6`() = assertTokenization(
        input = "<!---A",
        expectedTokens = listOf(
            comment("-A")
        )
    )

    @Test
    fun `B 6`() = assertTokenization(
        input = "<!---B",
        expectedTokens = listOf(
            comment("-B")
        )
    )

    @Test
    fun `Y 6`() = assertTokenization(
        input = "<!---Y",
        expectedTokens = listOf(
            comment("-Y")
        )
    )

    @Test
    fun `Z 6`() = assertTokenization(
        input = "<!---Z",
        expectedTokens = listOf(
            comment("-Z")
        )
    )

    @Test
    fun ` 122`() = assertTokenization(
        input = "<!---`",
        expectedTokens = listOf(
            comment("-`")
        )
    )

    @Test
    fun `a 11`() = assertTokenization(
        input = "<!---a",
        expectedTokens = listOf(
            comment("-a")
        )
    )

    @Test
    fun `b 6`() = assertTokenization(
        input = "<!---b",
        expectedTokens = listOf(
            comment("-b")
        )
    )

    @Test
    fun `y 6`() = assertTokenization(
        input = "<!---y",
        expectedTokens = listOf(
            comment("-y")
        )
    )

    @Test
    fun `z 6`() = assertTokenization(
        input = "<!---z",
        expectedTokens = listOf(
            comment("-z")
        )
    )

    @Test
    fun ` 123`() = assertTokenization(
        input = "<!---{",
        expectedTokens = listOf(
            comment("-{")
        )
    )

    @Test
    fun `uDBC0uDC00 6`() = assertTokenization(
        input = "<!---􀀀",
        expectedTokens = listOf(
            comment("-􀀀")
        )
    )

    @Test
    fun ` 124`() = assertTokenization(
        input = "<!--.",
        expectedTokens = listOf(
            comment(".")
        )
    )

    @Test
    fun ` 125`() = assertTokenization(
        input = "<!--/",
        expectedTokens = listOf(
            comment("/")
        )
    )

    @Test
    fun `0 7`() = assertTokenization(
        input = "<!--0",
        expectedTokens = listOf(
            comment("0")
        )
    )

    @Test
    fun `1 7`() = assertTokenization(
        input = "<!--1",
        expectedTokens = listOf(
            comment("1")
        )
    )

    @Test
    fun `9 7`() = assertTokenization(
        input = "<!--9",
        expectedTokens = listOf(
            comment("9")
        )
    )

    @Test
    fun ` 126`() = assertTokenization(
        input = "<!--<",
        expectedTokens = listOf(
            comment("<")
        )
    )

    @Test
    fun ` 127`() = assertTokenization(
        input = "<!--=",
        expectedTokens = listOf(
            comment("=")
        )
    )

    @Test
    fun ` 128`() = assertTokenization(
        input = "<!--?",
        expectedTokens = listOf(
            comment("?")
        )
    )

    @Test
    fun ` 129`() = assertTokenization(
        input = "<!--@",
        expectedTokens = listOf(
            comment("@")
        )
    )

    @Test
    fun `A 7`() = assertTokenization(
        input = "<!--A",
        expectedTokens = listOf(
            comment("A")
        )
    )

    @Test
    fun `B 7`() = assertTokenization(
        input = "<!--B",
        expectedTokens = listOf(
            comment("B")
        )
    )

    @Test
    fun `Y 7`() = assertTokenization(
        input = "<!--Y",
        expectedTokens = listOf(
            comment("Y")
        )
    )

    @Test
    fun `Z 7`() = assertTokenization(
        input = "<!--Z",
        expectedTokens = listOf(
            comment("Z")
        )
    )

    @Test
    fun ` 130`() = assertTokenization(
        input = "<!--`",
        expectedTokens = listOf(
            comment("`")
        )
    )

    @Test
    fun `a 12`() = assertTokenization(
        input = "<!--a",
        expectedTokens = listOf(
            comment("a")
        )
    )

    @Test
    fun `b 7`() = assertTokenization(
        input = "<!--b",
        expectedTokens = listOf(
            comment("b")
        )
    )

    @Test
    fun `y 7`() = assertTokenization(
        input = "<!--y",
        expectedTokens = listOf(
            comment("y")
        )
    )

    @Test
    fun `z 7`() = assertTokenization(
        input = "<!--z",
        expectedTokens = listOf(
            comment("z")
        )
    )

    @Test
    fun ` 131`() = assertTokenization(
        input = "<!--{",
        expectedTokens = listOf(
            comment("{")
        )
    )

    @Test
    fun `uDBC0uDC00 7`() = assertTokenization(
        input = "<!--􀀀",
        expectedTokens = listOf(
            comment("􀀀")
        )
    )

    @Test
    fun ` 132`() = assertTokenization(
        input = "<!/",
        expectedTokens = listOf(
            comment("/")
        )
    )

    @Test
    fun `0 8`() = assertTokenization(
        input = "<!0",
        expectedTokens = listOf(
            comment("0")
        )
    )

    @Test
    fun `1 8`() = assertTokenization(
        input = "<!1",
        expectedTokens = listOf(
            comment("1")
        )
    )

    @Test
    fun `9 8`() = assertTokenization(
        input = "<!9",
        expectedTokens = listOf(
            comment("9")
        )
    )

    @Test
    fun ` 133`() = assertTokenization(
        input = "<!<",
        expectedTokens = listOf(
            comment("<")
        )
    )

    @Test
    fun ` 134`() = assertTokenization(
        input = "<!=",
        expectedTokens = listOf(
            comment("=")
        )
    )

    @Test
    fun ` 135`() = assertTokenization(
        input = "<!>",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun ` 136`() = assertTokenization(
        input = "<!?",
        expectedTokens = listOf(
            comment("?")
        )
    )

    @Test
    fun ` 137`() = assertTokenization(
        input = "<!@",
        expectedTokens = listOf(
            comment("@")
        )
    )

    @Test
    fun `A 8`() = assertTokenization(
        input = "<!A",
        expectedTokens = listOf(
            comment("A")
        )
    )

    @Test
    fun `B 8`() = assertTokenization(
        input = "<!B",
        expectedTokens = listOf(
            comment("B")
        )
    )

    @Test
    fun `DOCTYPE`() = assertTokenization(
        input = "<!DOCTYPE",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEu0000`() = assertTokenization(
        input = "<!DOCTYPE\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEu0008`() = assertTokenization(
        input = "<!DOCTYPE",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEu0009`() = assertTokenization(
        input = "<!DOCTYPE\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEu000A`() = assertTokenization(
        input = "<!DOCTYPE\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEu000B`() = assertTokenization(
        input = "<!DOCTYPE",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEu000C`() = assertTokenization(
        input = "<!DOCTYPE",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEu000D`() = assertTokenization(
        input = "<!DOCTYPE\r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEu001F`() = assertTokenization(
        input = "<!DOCTYPE",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 2`() = assertTokenization(
        input = "<!DOCTYPE ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE u0000`() = assertTokenization(
        input = "<!DOCTYPE \u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE u0008`() = assertTokenization(
        input = "<!DOCTYPE ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE u0009`() = assertTokenization(
        input = "<!DOCTYPE \t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE u000A`() = assertTokenization(
        input = "<!DOCTYPE \n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE u000B`() = assertTokenization(
        input = "<!DOCTYPE ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE u000C`() = assertTokenization(
        input = "<!DOCTYPE ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE u000D`() = assertTokenization(
        input = "<!DOCTYPE \r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE u001F`() = assertTokenization(
        input = "<!DOCTYPE ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 3`() = assertTokenization(
        input = "<!DOCTYPE  ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 4`() = assertTokenization(
        input = "<!DOCTYPE !",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 5`() = assertTokenization(
        input = "<!DOCTYPE \"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 6`() = assertTokenization(
        input = "<!DOCTYPE &",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 7`() = assertTokenization(
        input = "<!DOCTYPE '",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 8`() = assertTokenization(
        input = "<!DOCTYPE -",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 9-1`() = assertTokenization(
        input = "<!DOCTYPE /",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 0`() = assertTokenization(
        input = "<!DOCTYPE 0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 1`() = assertTokenization(
        input = "<!DOCTYPE 1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 9`() = assertTokenization(
        input = "<!DOCTYPE 9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 10`() = assertTokenization(
        input = "<!DOCTYPE <",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 11`() = assertTokenization(
        input = "<!DOCTYPE =",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 12`() = assertTokenization(
        input = "<!DOCTYPE >",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 13`() = assertTokenization(
        input = "<!DOCTYPE ?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 14`() = assertTokenization(
        input = "<!DOCTYPE @",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE A`() = assertTokenization(
        input = "<!DOCTYPE A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE B`() = assertTokenization(
        input = "<!DOCTYPE B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE Y`() = assertTokenization(
        input = "<!DOCTYPE Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE Z`() = assertTokenization(
        input = "<!DOCTYPE Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 15`() = assertTokenization(
        input = "<!DOCTYPE [",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 16`() = assertTokenization(
        input = "<!DOCTYPE `",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a`() = assertTokenization(
        input = "<!DOCTYPE a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE au0000`() = assertTokenization(
        input = "<!DOCTYPE a\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE au0008`() = assertTokenization(
        input = "<!DOCTYPE a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE au0009`() = assertTokenization(
        input = "<!DOCTYPE a\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE au000A`() = assertTokenization(
        input = "<!DOCTYPE a\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE au000B`() = assertTokenization(
        input = "<!DOCTYPE a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE au000C`() = assertTokenization(
        input = "<!DOCTYPE a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE au000D`() = assertTokenization(
        input = "<!DOCTYPE a\r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE au001F`() = assertTokenization(
        input = "<!DOCTYPE a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 2`() = assertTokenization(
        input = "<!DOCTYPE a ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a u0000`() = assertTokenization(
        input = "<!DOCTYPE a \u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a u0008`() = assertTokenization(
        input = "<!DOCTYPE a ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a u0009`() = assertTokenization(
        input = "<!DOCTYPE a \t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a u000A`() = assertTokenization(
        input = "<!DOCTYPE a \n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a u000B`() = assertTokenization(
        input = "<!DOCTYPE a ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a u000C`() = assertTokenization(
        input = "<!DOCTYPE a ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a u000D`() = assertTokenization(
        input = "<!DOCTYPE a \r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a u001F`() = assertTokenization(
        input = "<!DOCTYPE a ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 3`() = assertTokenization(
        input = "<!DOCTYPE a  ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 4`() = assertTokenization(
        input = "<!DOCTYPE a !",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 5`() = assertTokenization(
        input = "<!DOCTYPE a \"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 6`() = assertTokenization(
        input = "<!DOCTYPE a &",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 7`() = assertTokenization(
        input = "<!DOCTYPE a '",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 8`() = assertTokenization(
        input = "<!DOCTYPE a -",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 9-1`() = assertTokenization(
        input = "<!DOCTYPE a /",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 0`() = assertTokenization(
        input = "<!DOCTYPE a 0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 1`() = assertTokenization(
        input = "<!DOCTYPE a 1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 9`() = assertTokenization(
        input = "<!DOCTYPE a 9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 10`() = assertTokenization(
        input = "<!DOCTYPE a <",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 11`() = assertTokenization(
        input = "<!DOCTYPE a =",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 12`() = assertTokenization(
        input = "<!DOCTYPE a >",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 13`() = assertTokenization(
        input = "<!DOCTYPE a ?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 14`() = assertTokenization(
        input = "<!DOCTYPE a @",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a A`() = assertTokenization(
        input = "<!DOCTYPE a A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a B`() = assertTokenization(
        input = "<!DOCTYPE a B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu0000`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu0008`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu0009`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000A`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000B`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000C`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000D`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu001F`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu0000 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu0009 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000A 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000B 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000C 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 5`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\" ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 6`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 7`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu0000 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\"\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC u0000`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"\" \u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 8`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"#",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 9`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 10`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 11`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 12`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC0`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC1`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC9`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 13`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 14`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 15`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\">",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 16`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 17`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICA`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICB`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICY`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICZ`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 18`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICa`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICb`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICy`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICz`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 19`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICuDBC0uDC00`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC\"􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 20`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC#",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 21`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 22`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu0000 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu0009 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000A 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000B 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000C 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 23`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC' ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 24`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 25`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 26`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 27`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu0000 5`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu0008 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu0009 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000A 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000B 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000C 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu000D 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu001F 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 28`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'' ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 29`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 30`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 31`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''#",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 32`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 33`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICu0000 6`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''''\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICxu0000`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''''x\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC u0000 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'''' \u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC xu0000`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'''' x\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 34`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''(",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 35`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 36`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC0 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC1 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC9 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 37`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 38`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 39`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 40`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 41`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICA 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICB 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICY 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICZ 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 42`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICa 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICb 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICy 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICz 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 43`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICuDBC0uDC00 2`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC''􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 44`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'(",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 45`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 46`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC0 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC1 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC9 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 47`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 48`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 49`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 50`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 51`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICA 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICB 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICY 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICZ 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 52`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICa 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICb 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICy 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICz 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 53`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICuDBC0uDC00 3`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC'􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 54`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC(",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 55`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 56`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC0 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC1 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC9 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 57`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 58`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 59`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 60`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 61`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICA 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICA",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICB 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICB",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICY 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICY",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICZ 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICZ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 62`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICa 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICa",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICb 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICb",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICy 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICy",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICz 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLICz",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLIC 63`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a PUBLICuDBC0uDC00 4`() = assertTokenization(
        input = "<!DOCTYPE a PUBLIC􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu0000`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM u0000`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM \u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM xu0000`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM \u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu0008`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu0009`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000A`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000B`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000C`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000D`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu001F`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu0000 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu0009 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000A 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000B 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000C 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 5`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\" ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 6`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 7`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 8`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"#",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 9`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 10`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 11`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 12`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM0`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM1`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM9`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 13`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 14`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 15`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\">",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 16`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 17`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMA`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMB`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMY`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMZ`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 18`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMa`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMb`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMy`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMz`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 19`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMuDBC0uDC00`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM\"􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 20`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM#",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 21`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 22`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu0000 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu0009 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000A 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000B 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000C 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 23`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM' ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 24`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 25`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 26`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 27`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu0000 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu0008 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu0009 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000A 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000B 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000C 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu000D 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMu001F 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 28`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'' ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM u0000 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'' \u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM xu0000 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'' x\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 29`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 30`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 31`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 32`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 33`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 34`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM0 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM1 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM9 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 35`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 36`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 37`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 38`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 39`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMA 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMB 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMY 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMZ 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 40`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMa 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMb 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMy 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMz 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 41`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMuDBC0uDC00 2`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM''􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 42`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'(",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 43`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 44`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM0 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM1 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM9 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 45`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 46`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 47`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 48`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 49`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMA 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMB 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMY 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMZ 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 50`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMa 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMb 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMy 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMz 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 51`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMuDBC0uDC00 3`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM'􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 52`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM(",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 53`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 54`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM0 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM1 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM9 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 55`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 56`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 57`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 58`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 59`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMA 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMA",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMB 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMB",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMY 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMY",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMZ 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMZ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 60`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMa 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMa",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMb 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMb",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMy 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMy",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMz 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEMz",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEM 61`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a SYSTEMuDBC0uDC00 4`() = assertTokenization(
        input = "<!DOCTYPE a SYSTEM􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a Y`() = assertTokenization(
        input = "<!DOCTYPE a Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a Z`() = assertTokenization(
        input = "<!DOCTYPE a Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 15`() = assertTokenization(
        input = "<!DOCTYPE a `",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a`() = assertTokenization(
        input = "<!DOCTYPE a a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a au0000`() = assertTokenization(
        input = "<!DOCTYPE a a\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a au0009`() = assertTokenization(
        input = "<!DOCTYPE a a\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a au000A`() = assertTokenization(
        input = "<!DOCTYPE a a\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a au000B`() = assertTokenization(
        input = "<!DOCTYPE a a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a au000C`() = assertTokenization(
        input = "<!DOCTYPE a a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 2`() = assertTokenization(
        input = "<!DOCTYPE a a ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 3`() = assertTokenization(
        input = "<!DOCTYPE a a!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 4`() = assertTokenization(
        input = "<!DOCTYPE a a\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 5`() = assertTokenization(
        input = "<!DOCTYPE a a&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 6`() = assertTokenization(
        input = "<!DOCTYPE a a'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 7`() = assertTokenization(
        input = "<!DOCTYPE a a-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 8`() = assertTokenization(
        input = "<!DOCTYPE a a/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a0`() = assertTokenization(
        input = "<!DOCTYPE a a0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a1`() = assertTokenization(
        input = "<!DOCTYPE a a1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a9`() = assertTokenization(
        input = "<!DOCTYPE a a9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 9`() = assertTokenization(
        input = "<!DOCTYPE a a<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 10`() = assertTokenization(
        input = "<!DOCTYPE a a=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 11`() = assertTokenization(
        input = "<!DOCTYPE a a>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 12`() = assertTokenization(
        input = "<!DOCTYPE a a?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 13`() = assertTokenization(
        input = "<!DOCTYPE a a@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a aA`() = assertTokenization(
        input = "<!DOCTYPE a aA",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a aB`() = assertTokenization(
        input = "<!DOCTYPE a aB",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a aY`() = assertTokenization(
        input = "<!DOCTYPE a aY",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a aZ`() = assertTokenization(
        input = "<!DOCTYPE a aZ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 14`() = assertTokenization(
        input = "<!DOCTYPE a a`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a aa`() = assertTokenization(
        input = "<!DOCTYPE a aa",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a ab`() = assertTokenization(
        input = "<!DOCTYPE a ab",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a ay`() = assertTokenization(
        input = "<!DOCTYPE a ay",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a az`() = assertTokenization(
        input = "<!DOCTYPE a az",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a a 15`() = assertTokenization(
        input = "<!DOCTYPE a a{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a auDBC0uDC00`() = assertTokenization(
        input = "<!DOCTYPE a a􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a b`() = assertTokenization(
        input = "<!DOCTYPE a b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a y`() = assertTokenization(
        input = "<!DOCTYPE a y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a z`() = assertTokenization(
        input = "<!DOCTYPE a z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 16`() = assertTokenization(
        input = "<!DOCTYPE a {",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a uDBC0uDC00`() = assertTokenization(
        input = "<!DOCTYPE a 􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 17`() = assertTokenization(
        input = "<!DOCTYPE a!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 18`() = assertTokenization(
        input = "<!DOCTYPE a\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 19`() = assertTokenization(
        input = "<!DOCTYPE a&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 20`() = assertTokenization(
        input = "<!DOCTYPE a'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 21`() = assertTokenization(
        input = "<!DOCTYPE a-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 22`() = assertTokenization(
        input = "<!DOCTYPE a/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a0`() = assertTokenization(
        input = "<!DOCTYPE a0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a1`() = assertTokenization(
        input = "<!DOCTYPE a1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a9`() = assertTokenization(
        input = "<!DOCTYPE a9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 23`() = assertTokenization(
        input = "<!DOCTYPE a<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 24`() = assertTokenization(
        input = "<!DOCTYPE a=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 25`() = assertTokenization(
        input = "<!DOCTYPE a>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 26`() = assertTokenization(
        input = "<!DOCTYPE a?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 27`() = assertTokenization(
        input = "<!DOCTYPE a@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE aA`() = assertTokenization(
        input = "<!DOCTYPE aA",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE aB`() = assertTokenization(
        input = "<!DOCTYPE aB",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE aY`() = assertTokenization(
        input = "<!DOCTYPE aY",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE aZ`() = assertTokenization(
        input = "<!DOCTYPE aZ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 28`() = assertTokenization(
        input = "<!DOCTYPE a[",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 29`() = assertTokenization(
        input = "<!DOCTYPE a`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE aa`() = assertTokenization(
        input = "<!DOCTYPE aa",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE ab`() = assertTokenization(
        input = "<!DOCTYPE ab",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE ay`() = assertTokenization(
        input = "<!DOCTYPE ay",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE az`() = assertTokenization(
        input = "<!DOCTYPE az",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE a 30`() = assertTokenization(
        input = "<!DOCTYPE a{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE auDBC0uDC00`() = assertTokenization(
        input = "<!DOCTYPE a􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE b`() = assertTokenization(
        input = "<!DOCTYPE b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE y`() = assertTokenization(
        input = "<!DOCTYPE y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE z`() = assertTokenization(
        input = "<!DOCTYPE z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 17`() = assertTokenization(
        input = "<!DOCTYPE {",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE uDBC0uDC00`() = assertTokenization(
        input = "<!DOCTYPE 􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 18`() = assertTokenization(
        input = "<!DOCTYPE!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 19`() = assertTokenization(
        input = "<!DOCTYPE\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 20`() = assertTokenization(
        input = "<!DOCTYPE&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 21`() = assertTokenization(
        input = "<!DOCTYPE'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 22`() = assertTokenization(
        input = "<!DOCTYPE-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 23`() = assertTokenization(
        input = "<!DOCTYPE/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE0`() = assertTokenization(
        input = "<!DOCTYPE0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE1`() = assertTokenization(
        input = "<!DOCTYPE1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE9`() = assertTokenization(
        input = "<!DOCTYPE9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 24`() = assertTokenization(
        input = "<!DOCTYPE<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 25`() = assertTokenization(
        input = "<!DOCTYPE=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 26`() = assertTokenization(
        input = "<!DOCTYPE>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 27`() = assertTokenization(
        input = "<!DOCTYPE?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 28`() = assertTokenization(
        input = "<!DOCTYPE@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEA`() = assertTokenization(
        input = "<!DOCTYPEA",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEB`() = assertTokenization(
        input = "<!DOCTYPEB",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEY`() = assertTokenization(
        input = "<!DOCTYPEY",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEZ`() = assertTokenization(
        input = "<!DOCTYPEZ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 29`() = assertTokenization(
        input = "<!DOCTYPE`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa`() = assertTokenization(
        input = "<!DOCTYPEa",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEau0000`() = assertTokenization(
        input = "<!DOCTYPEa\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEau0008`() = assertTokenization(
        input = "<!DOCTYPEa",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEau0009`() = assertTokenization(
        input = "<!DOCTYPEa\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEau000A`() = assertTokenization(
        input = "<!DOCTYPEa\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEau000B`() = assertTokenization(
        input = "<!DOCTYPEa",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEau000C`() = assertTokenization(
        input = "<!DOCTYPEa",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEau000D`() = assertTokenization(
        input = "<!DOCTYPEa\r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEau001F`() = assertTokenization(
        input = "<!DOCTYPEa",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 2`() = assertTokenization(
        input = "<!DOCTYPEa ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa u0000`() = assertTokenization(
        input = "<!DOCTYPEa \u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa u0008`() = assertTokenization(
        input = "<!DOCTYPEa ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa u0009`() = assertTokenization(
        input = "<!DOCTYPEa \t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa u000A`() = assertTokenization(
        input = "<!DOCTYPEa \n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa u000B`() = assertTokenization(
        input = "<!DOCTYPEa ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa u000C`() = assertTokenization(
        input = "<!DOCTYPEa ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa u000D`() = assertTokenization(
        input = "<!DOCTYPEa \r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa u001F`() = assertTokenization(
        input = "<!DOCTYPEa ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 3`() = assertTokenization(
        input = "<!DOCTYPEa  ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 4`() = assertTokenization(
        input = "<!DOCTYPEa !",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 5`() = assertTokenization(
        input = "<!DOCTYPEa \"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 6`() = assertTokenization(
        input = "<!DOCTYPEa &",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 7`() = assertTokenization(
        input = "<!DOCTYPEa '",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 8`() = assertTokenization(
        input = "<!DOCTYPEa -",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 9`() = assertTokenization(
        input = "<!DOCTYPEa /",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 0`() = assertTokenization(
        input = "<!DOCTYPEa 0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 1`() = assertTokenization(
        input = "<!DOCTYPEa 1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 9 - 1`() = assertTokenization(
        input = "<!DOCTYPEa 9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 10`() = assertTokenization(
        input = "<!DOCTYPEa <",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 11`() = assertTokenization(
        input = "<!DOCTYPEa =",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 12`() = assertTokenization(
        input = "<!DOCTYPEa >",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 13`() = assertTokenization(
        input = "<!DOCTYPEa ?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 14`() = assertTokenization(
        input = "<!DOCTYPEa @",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa A`() = assertTokenization(
        input = "<!DOCTYPEa A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa B`() = assertTokenization(
        input = "<!DOCTYPEa B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu0000`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu0008`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu0009`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000A`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000B`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000C`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000D`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu001F`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu0000 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu0009 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000A 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000B 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000C 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 5`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\" ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 6`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 7`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 8`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"#",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 9`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 10`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 11`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 12`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC0`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC1`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC9`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 13`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 14`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 15`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\">",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 16`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 17`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICA`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICB`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICY`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICZ`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 18`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICa`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICb`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICy`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICz`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 19`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICuDBC0uDC00`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC\"􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 20`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC#",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 21`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 22`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu0000 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu0009 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000A 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000B 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000C 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 23`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC' ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 24`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 25`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 26`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 27`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu0000 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu0008 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu0009 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000A 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000B 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000C 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu000D 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICu001F 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 28`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'' ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 29`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 30`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 31`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''#",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 32`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 33`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 34`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''(",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 35`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 36`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC0 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC1 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC9 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 37`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 38`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 39`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 40`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 41`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICA 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICB 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICY 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICZ 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 42`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICa 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICb 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICy 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICz 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 43`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICuDBC0uDC00 2`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC''􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 44`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'(",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 45`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 46`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC0 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC1 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC9 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 47`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 48`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 49`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 50`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 51`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICA 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICB 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICY 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICZ 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 52`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICa 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICb 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICy 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICz 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 53`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICuDBC0uDC00 3`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC'􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 54`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC(",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 55`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 56`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC0 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC1 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC9 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 57`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 58`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 59`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 60`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 61`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICA 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICA",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICB 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICB",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICY 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICY",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICZ 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICZ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 62`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICa 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICa",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICb 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICb",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICy 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICy",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICz 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLICz",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLIC 63`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa PUBLICuDBC0uDC00 4`() = assertTokenization(
        input = "<!DOCTYPEa PUBLIC􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu0000`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu0008`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu0009`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000A`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000B`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000C`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000D`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu001F`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu0000 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu0009 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000A 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000B 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000C 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 5`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\" ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 6`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 7`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 8`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"#",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 9`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 10`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 11`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 12`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM0`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM1`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM9`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 13`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 14`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 15`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\">",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 16`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 17`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMA`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMB`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMY`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMZ`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 18`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMa`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMb`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMy`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMz`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 19`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMuDBC0uDC00`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM\"􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 20`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM#",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 21`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 22`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu0000 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu0009 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000A 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000B 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000C 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 23`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM' ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 24`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 25`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 26`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 27`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu0000 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu0008 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu0009 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000A 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000B 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000C 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu000D 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\r",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMu001F 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 28`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'' ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 29`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 30`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 31`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 32`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'''",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 33`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 34`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM0 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM1 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM9 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 35`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 36`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 37`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 38`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 39`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMA 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMB 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMY 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMZ 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 40`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMa 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMb 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMy 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMz 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 41`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMuDBC0uDC00 2`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM''􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 42`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'(",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 43`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 44`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM0 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM1 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM9 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 45`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 46`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 47`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 48`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 49`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMA 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'A",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMB 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'B",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMY 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMZ 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 50`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMa 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMb 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMy 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMz 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 51`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMuDBC0uDC00 3`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM'􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 52`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM(",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 53`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 54`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM0 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM1 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM9 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 55`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 56`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 57`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 58`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 59`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMA 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMA",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMB 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMB",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMY 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMY",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMZ 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMZ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 60`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMa 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMa",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMb 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMb",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMy 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMy",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMz 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEMz",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEM 61`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa SYSTEMuDBC0uDC00 4`() = assertTokenization(
        input = "<!DOCTYPEa SYSTEM􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa Y`() = assertTokenization(
        input = "<!DOCTYPEa Y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa Z`() = assertTokenization(
        input = "<!DOCTYPEa Z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 15`() = assertTokenization(
        input = "<!DOCTYPEa `",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a`() = assertTokenization(
        input = "<!DOCTYPEa a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa au0000`() = assertTokenization(
        input = "<!DOCTYPEa a\u0000",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa au0009`() = assertTokenization(
        input = "<!DOCTYPEa a\t",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa au000A`() = assertTokenization(
        input = "<!DOCTYPEa a\n",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa au000B`() = assertTokenization(
        input = "<!DOCTYPEa a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa au000C`() = assertTokenization(
        input = "<!DOCTYPEa a",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 2`() = assertTokenization(
        input = "<!DOCTYPEa a ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 3`() = assertTokenization(
        input = "<!DOCTYPEa a!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 4`() = assertTokenization(
        input = "<!DOCTYPEa a\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 5`() = assertTokenization(
        input = "<!DOCTYPEa a&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 6`() = assertTokenization(
        input = "<!DOCTYPEa a'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 7`() = assertTokenization(
        input = "<!DOCTYPEa a-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 8`() = assertTokenization(
        input = "<!DOCTYPEa a/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a0`() = assertTokenization(
        input = "<!DOCTYPEa a0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a1`() = assertTokenization(
        input = "<!DOCTYPEa a1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a9`() = assertTokenization(
        input = "<!DOCTYPEa a9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 9`() = assertTokenization(
        input = "<!DOCTYPEa a<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 10`() = assertTokenization(
        input = "<!DOCTYPEa a=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 11`() = assertTokenization(
        input = "<!DOCTYPEa a>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 12`() = assertTokenization(
        input = "<!DOCTYPEa a?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 13`() = assertTokenization(
        input = "<!DOCTYPEa a@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa aA`() = assertTokenization(
        input = "<!DOCTYPEa aA",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa aB`() = assertTokenization(
        input = "<!DOCTYPEa aB",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa aY`() = assertTokenization(
        input = "<!DOCTYPEa aY",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa aZ`() = assertTokenization(
        input = "<!DOCTYPEa aZ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 14`() = assertTokenization(
        input = "<!DOCTYPEa a`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa aa`() = assertTokenization(
        input = "<!DOCTYPEa aa",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa ab`() = assertTokenization(
        input = "<!DOCTYPEa ab",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa ay`() = assertTokenization(
        input = "<!DOCTYPEa ay",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa az`() = assertTokenization(
        input = "<!DOCTYPEa az",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa a 15`() = assertTokenization(
        input = "<!DOCTYPEa a{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa auDBC0uDC00`() = assertTokenization(
        input = "<!DOCTYPEa a􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa b`() = assertTokenization(
        input = "<!DOCTYPEa b",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa y`() = assertTokenization(
        input = "<!DOCTYPEa y",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa z`() = assertTokenization(
        input = "<!DOCTYPEa z",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 16`() = assertTokenization(
        input = "<!DOCTYPEa {",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa uDBC0uDC00`() = assertTokenization(
        input = "<!DOCTYPEa 􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 17`() = assertTokenization(
        input = "<!DOCTYPEa!",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 18`() = assertTokenization(
        input = "<!DOCTYPEa\"",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 19`() = assertTokenization(
        input = "<!DOCTYPEa&",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 20`() = assertTokenization(
        input = "<!DOCTYPEa'",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 21`() = assertTokenization(
        input = "<!DOCTYPEa-",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 22`() = assertTokenization(
        input = "<!DOCTYPEa/",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa0`() = assertTokenization(
        input = "<!DOCTYPEa0",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa1`() = assertTokenization(
        input = "<!DOCTYPEa1",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa9`() = assertTokenization(
        input = "<!DOCTYPEa9",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 23`() = assertTokenization(
        input = "<!DOCTYPEa<",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 24`() = assertTokenization(
        input = "<!DOCTYPEa=",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 25`() = assertTokenization(
        input = "<!DOCTYPEa>",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 26`() = assertTokenization(
        input = "<!DOCTYPEa?",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 27`() = assertTokenization(
        input = "<!DOCTYPEa@",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEaA`() = assertTokenization(
        input = "<!DOCTYPEaA",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEaB`() = assertTokenization(
        input = "<!DOCTYPEaB",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEaY`() = assertTokenization(
        input = "<!DOCTYPEaY",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEaZ`() = assertTokenization(
        input = "<!DOCTYPEaZ",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 28`() = assertTokenization(
        input = "<!DOCTYPEa[",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 29`() = assertTokenization(
        input = "<!DOCTYPEa`",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEaa`() = assertTokenization(
        input = "<!DOCTYPEaa",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEab`() = assertTokenization(
        input = "<!DOCTYPEab",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEay`() = assertTokenization(
        input = "<!DOCTYPEay",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEaz`() = assertTokenization(
        input = "<!DOCTYPEaz",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEa 30`() = assertTokenization(
        input = "<!DOCTYPEa{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEauDBC0uDC00`() = assertTokenization(
        input = "<!DOCTYPEa􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEb`() = assertTokenization(
        input = "<!DOCTYPEb",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEy`() = assertTokenization(
        input = "<!DOCTYPEy",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEz`() = assertTokenization(
        input = "<!DOCTYPEz",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPE 30`() = assertTokenization(
        input = "<!DOCTYPE{",
        expectedTokens = listOf()
    )

    @Test
    fun `DOCTYPEuDBC0uDC00`() = assertTokenization(
        input = "<!DOCTYPE􀀀",
        expectedTokens = listOf()
    )

    @Test
    fun `Y 8`() = assertTokenization(
        input = "<!Y",
        expectedTokens = listOf(
            comment("Y")
        )
    )

    @Test
    fun `Z 8`() = assertTokenization(
        input = "<!Z",
        expectedTokens = listOf(
            comment("Z")
        )
    )

    @Test
    fun ` 138`() = assertTokenization(
        input = "<!`",
        expectedTokens = listOf(
            comment("`")
        )
    )

    @Test
    fun `a 13`() = assertTokenization(
        input = "<!a",
        expectedTokens = listOf(
            comment("a")
        )
    )

    @Test
    fun `b 8`() = assertTokenization(
        input = "<!b",
        expectedTokens = listOf(
            comment("b")
        )
    )

    @Test
    fun `y 8`() = assertTokenization(
        input = "<!y",
        expectedTokens = listOf(
            comment("y")
        )
    )

    @Test
    fun `z 8`() = assertTokenization(
        input = "<!z",
        expectedTokens = listOf(
            comment("z")
        )
    )

    @Test
    fun ` 139`() = assertTokenization(
        input = "<!{",
        expectedTokens = listOf(
            comment("{")
        )
    )

    @Test
    fun `uDBC0uDC00 8`() = assertTokenization(
        input = "<!􀀀",
        expectedTokens = listOf(
            comment("􀀀")
        )
    )

    @Test
    fun ` 140`() = assertTokenization(
        input = "<\"",
        expectedTokens = listOf(
            *chars("<\"").toTypedArray()
        )
    )

    @Test
    fun ` 141`() = assertTokenization(
        input = "<&",
        expectedTokens = listOf(
            *chars("<&").toTypedArray()
        )
    )

    @Test
    fun ` 142`() = assertTokenization(
        input = "<'",
        expectedTokens = listOf(
            *chars("<'").toTypedArray()
        )
    )

    @Test
    fun ` 143`() = assertTokenization(
        input = "<-",
        expectedTokens = listOf(
            *chars("<-").toTypedArray()
        )
    )

    @Test
    fun ` 144`() = assertTokenization(
        input = "<.",
        expectedTokens = listOf(
            *chars("<.").toTypedArray()
        )
    )

    @Test
    fun ` 145`() = assertTokenization(
        input = "</",
        expectedTokens = listOf(
            *chars("</").toTypedArray()
        )
    )

    @Test
    fun `u0000 9`() = assertTokenization(
        input = "</\u0000",
        expectedTokens = listOf(
            comment("�")
        )
    )

    @Test
    fun `u0009 10`() = assertTokenization(
        input = "</\t",
        expectedTokens = listOf(
            comment("	")
        )
    )

    @Test
    fun `u000A 10`() = assertTokenization(
        input = "</\n",
        expectedTokens = listOf(comment("\n"))
    )

    @Test
    fun `u000B 10`() = assertTokenization(
        input = "</",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun `u000C 10`() = assertTokenization(
        input = "</",
        expectedTokens = listOf(
            comment("")
        )
    )

    @Test
    fun ` 146`() = assertTokenization(
        input = "</ ",
        expectedTokens = listOf(
            comment(" ")
        )
    )

    @Test
    fun `u0000 10`() = assertTokenization(
        input = "</ \u0000",
        expectedTokens = listOf(
            comment(" �")
        )
    )

    @Test
    fun ` 147`() = assertTokenization(
        input = "</!",
        expectedTokens = listOf(
            comment("!")
        )
    )

    @Test
    fun ` 148`() = assertTokenization(
        input = "</\"",
        expectedTokens = listOf(comment("\""))
    )

    @Test
    fun ` 149`() = assertTokenization(
        input = "</&",
        expectedTokens = listOf(
            comment("&")
        )
    )

    @Test
    fun ` 150`() = assertTokenization(
        input = "</'",
        expectedTokens = listOf(
            comment("'")
        )
    )

    @Test
    fun ` 151`() = assertTokenization(
        input = "</-",
        expectedTokens = listOf(
            comment("-")
        )
    )

    @Test
    fun ` 152`() = assertTokenization(
        input = "<//",
        expectedTokens = listOf(
            comment("/")
        )
    )

    @Test
    fun `0 9`() = assertTokenization(
        input = "</0",
        expectedTokens = listOf(
            comment("0")
        )
    )

    @Test
    fun `1 9`() = assertTokenization(
        input = "</1",
        expectedTokens = listOf(
            comment("1")
        )
    )

    @Test
    fun `9 9`() = assertTokenization(
        input = "</9",
        expectedTokens = listOf(
            comment("9")
        )
    )

    @Test
    fun ` 153`() = assertTokenization(
        input = "</<",
        expectedTokens = listOf(
            comment("<")
        )
    )

    @Test
    fun ` 154`() = assertTokenization(
        input = "</=",
        expectedTokens = listOf(
            comment("=")
        )
    )

    @Test
    fun ` 155`() = assertTokenization(
        input = "</>",
        expectedTokens = listOf()
    )

    @Test
    fun ` 156`() = assertTokenization(
        input = "</?",
        expectedTokens = listOf(
            comment("?")
        )
    )

    @Test
    fun ` 157`() = assertTokenization(
        input = "</@",
        expectedTokens = listOf(
            comment("@")
        )
    )

    @Test
    fun `A 9`() = assertTokenization(
        input = "</A>",
        expectedTokens = listOf(
            endTag("a")
        )
    )

    @Test
    fun `B 9`() = assertTokenization(
        input = "</B>",
        expectedTokens = listOf(
            endTag("b")
        )
    )

    @Test
    fun `Y 9`() = assertTokenization(
        input = "</Y>",
        expectedTokens = listOf(
            endTag("y")
        )
    )

    @Test
    fun `Z 9`() = assertTokenization(
        input = "</Z>",
        expectedTokens = listOf(
            endTag("z")
        )
    )

    @Test
    fun ` 158`() = assertTokenization(
        input = "</[",
        expectedTokens = listOf(
            comment("[")
        )
    )

    @Test
    fun ` 159`() = assertTokenization(
        input = "</`",
        expectedTokens = listOf(
            comment("`")
        )
    )

    @Test
    fun `a 14`() = assertTokenization(
        input = "</a>",
        expectedTokens = listOf(
            endTag("a")
        )
    )

    @Test
    fun `b 9`() = assertTokenization(
        input = "</b>",
        expectedTokens = listOf(
            endTag("b")
        )
    )

    @Test
    fun `y 9`() = assertTokenization(
        input = "</y>",
        expectedTokens = listOf(
            endTag("y")
        )
    )

    @Test
    fun `z 9`() = assertTokenization(
        input = "</z>",
        expectedTokens = listOf(
            endTag("z")
        )
    )

    @Test
    fun ` 160`() = assertTokenization(
        input = "</{",
        expectedTokens = listOf(
            comment("{")
        )
    )

    @Test
    fun `uDBC0uDC00 9`() = assertTokenization(
        input = "</􀀀",
        expectedTokens = listOf(
            comment("􀀀")
        )
    )

    @Test
    fun `0 10`() = assertTokenization(
        input = "<0",
        expectedTokens = listOf(
            *chars("<0").toTypedArray()
        )
    )

    @Test
    fun `1 10`() = assertTokenization(
        input = "<1",
        expectedTokens = listOf(
            *chars("<1").toTypedArray()
        )
    )

    @Test
    fun `9 10`() = assertTokenization(
        input = "<9",
        expectedTokens = listOf(
            *chars("<9").toTypedArray()
        )
    )

    @Test
    fun ` 161`() = assertTokenization(
        input = "<<",
        expectedTokens = listOf(
            *chars("<<").toTypedArray()
        )
    )

    @Test
    fun ` 162`() = assertTokenization(
        input = "<=",
        expectedTokens = listOf(
            *chars("<=").toTypedArray()
        )
    )

    @Test
    fun ` 163`() = assertTokenization(
        input = "<>",
        expectedTokens = listOf(
            *chars("<>").toTypedArray()
        )
    )

    @Test
    fun ` 164`() = assertTokenization(
        input = "<?",
        expectedTokens = listOf(
            comment("?")
        )
    )

    @Test
    fun `u0000 11`() = assertTokenization(
        input = "<?\u0000",
        expectedTokens = listOf(
            comment("?�")
        )
    )

    @Test
    fun `u0009 11`() = assertTokenization(
        input = "<?\t",
        expectedTokens = listOf(
            comment("?	")
        )
    )

    @Test
    fun `u000A 11`() = assertTokenization(
        input = "<?\n",
        expectedTokens = listOf(comment("?\n"))
    )

    @Test
    fun `u000B 11`() = assertTokenization(
        input = "<?",
        expectedTokens = listOf(
            comment("?")
        )
    )

    @Test
    fun `u000C 11`() = assertTokenization(
        input = "<?",
        expectedTokens = listOf(
            comment("?")
        )
    )

    @Test
    fun ` 165`() = assertTokenization(
        input = "<? ",
        expectedTokens = listOf(
            comment("? ")
        )
    )

    @Test
    fun `u0000 12`() = assertTokenization(
        input = "<? \u0000",
        expectedTokens = listOf(
            comment("? �")
        )
    )

    @Test
    fun ` 166`() = assertTokenization(
        input = "<?!",
        expectedTokens = listOf(
            comment("?!")
        )
    )

    @Test
    fun ` 167`() = assertTokenization(
        input = "<?\"",
        expectedTokens = listOf(comment("?\""))
    )

    @Test
    fun ` 168`() = assertTokenization(
        input = "<?&",
        expectedTokens = listOf(
            comment("?&")
        )
    )

    @Test
    fun ` 169`() = assertTokenization(
        input = "<?'",
        expectedTokens = listOf(
            comment("?'")
        )
    )

    @Test
    fun ` 170`() = assertTokenization(
        input = "<?-",
        expectedTokens = listOf(
            comment("?-")
        )
    )

    @Test
    fun ` 171`() = assertTokenization(
        input = "<?/",
        expectedTokens = listOf(
            comment("?/")
        )
    )

    @Test
    fun `0 11`() = assertTokenization(
        input = "<?0",
        expectedTokens = listOf(
            comment("?0")
        )
    )

    @Test
    fun `1 11`() = assertTokenization(
        input = "<?1",
        expectedTokens = listOf(
            comment("?1")
        )
    )

    @Test
    fun `9 11`() = assertTokenization(
        input = "<?9",
        expectedTokens = listOf(
            comment("?9")
        )
    )

    @Test
    fun ` 172`() = assertTokenization(
        input = "<?<",
        expectedTokens = listOf(
            comment("?<")
        )
    )

    @Test
    fun ` 173`() = assertTokenization(
        input = "<?=",
        expectedTokens = listOf(
            comment("?=")
        )
    )

    @Test
    fun ` 174`() = assertTokenization(
        input = "<?>",
        expectedTokens = listOf(
            comment("?")
        )
    )

    @Test
    fun ` 175`() = assertTokenization(
        input = "<??",
        expectedTokens = listOf(
            comment("??")
        )
    )

    @Test
    fun ` 176`() = assertTokenization(
        input = "<?@",
        expectedTokens = listOf(
            comment("?@")
        )
    )

    @Test
    fun `A 10`() = assertTokenization(
        input = "<?A",
        expectedTokens = listOf(
            comment("?A")
        )
    )

    @Test
    fun `B 10`() = assertTokenization(
        input = "<?B",
        expectedTokens = listOf(
            comment("?B")
        )
    )

    @Test
    fun `Y 10`() = assertTokenization(
        input = "<?Y",
        expectedTokens = listOf(
            comment("?Y")
        )
    )

    @Test
    fun `Z 10`() = assertTokenization(
        input = "<?Z",
        expectedTokens = listOf(
            comment("?Z")
        )
    )

    @Test
    fun ` 177`() = assertTokenization(
        input = "<?`",
        expectedTokens = listOf(
            comment("?`")
        )
    )

    @Test
    fun `a 15`() = assertTokenization(
        input = "<?a",
        expectedTokens = listOf(
            comment("?a")
        )
    )

    @Test
    fun `b 10`() = assertTokenization(
        input = "<?b",
        expectedTokens = listOf(
            comment("?b")
        )
    )

    @Test
    fun `y 10`() = assertTokenization(
        input = "<?y",
        expectedTokens = listOf(
            comment("?y")
        )
    )

    @Test
    fun `z 10`() = assertTokenization(
        input = "<?z",
        expectedTokens = listOf(
            comment("?z")
        )
    )

    @Test
    fun ` 178`() = assertTokenization(
        input = "<?{",
        expectedTokens = listOf(
            comment("?{")
        )
    )

    @Test
    fun `uDBC0uDC00 10`() = assertTokenization(
        input = "<?􀀀",
        expectedTokens = listOf(
            comment("?􀀀")
        )
    )

    @Test
    fun ` 179`() = assertTokenization(
        input = "<@",
        expectedTokens = listOf(
            *chars("<@").toTypedArray()
        )
    )

    @Test
    fun `A 11`() = assertTokenization(
        input = "<A>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `B 11`() = assertTokenization(
        input = "<B>",
        expectedTokens = listOf(
            startTag(name = "b")
        )
    )

    @Test
    fun `Y 11`() = assertTokenization(
        input = "<Y>",
        expectedTokens = listOf(
            startTag(name = "y")
        )
    )

    @Test
    fun `Z 11`() = assertTokenization(
        input = "<Z>",
        expectedTokens = listOf(
            startTag(name = "z")
        )
    )

    @Test
    fun ` 180`() = assertTokenization(
        input = "<[",
        expectedTokens = listOf(
            *chars("<[").toTypedArray()
        )
    )

    @Test
    fun ` 181`() = assertTokenization(
        input = "<`",
        expectedTokens = listOf(
            *chars("<`").toTypedArray()
        )
    )

    @Test
    fun `a 16`() = assertTokenization(
        input = "<a>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au0000`() = assertTokenization(
        input = "<a\u0000>",
        expectedTokens = listOf(
            startTag(name = "a�")
        )
    )

    @Test
    fun `au0008`() = assertTokenization(
        input = "<a>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au0009`() = assertTokenization(
        input = "<a\t>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au000A`() = assertTokenization(
        input = "<a\n>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au000B`() = assertTokenization(
        input = "<a>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au000C`() = assertTokenization(
        input = "<a>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au000D`() = assertTokenization(
        input = "<a\r>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au001F`() = assertTokenization(
        input = "<a>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a 17`() = assertTokenization(
        input = "<a >",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a u0000`() = assertTokenization(
        input = "<a \u0000>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("�" to ""))
        )
    )

    @Test
    fun `a u0008`() = assertTokenization(
        input = "<a >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("" to ""))
        )
    )

    @Test
    fun `a u0009`() = assertTokenization(
        input = "<a \t>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a u000A`() = assertTokenization(
        input = "<a \n>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a u000B`() = assertTokenization(
        input = "<a >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("" to ""))
        )
    )

    @Test
    fun `a u000C`() = assertTokenization(
        input = "<a >",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a u000D`() = assertTokenization(
        input = "<a \r>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a u001F`() = assertTokenization(
        input = "<a >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("" to ""))
        )
    )

    @Test
    fun `a 18`() = assertTokenization(
        input = "<a  >",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a 19`() = assertTokenization(
        input = "<a !>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("!" to ""))
        )
    )

    @Test
    fun `a 20`() = assertTokenization(
        input = "<a \">",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("\"" to "")
            )
        )
    )

    @Test
    fun `a 21`() = assertTokenization(
        input = "<a #>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("#" to ""))
        )
    )

    @Test
    fun `a 22`() = assertTokenization(
        input = "<a &>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("&" to ""))
        )
    )

    @Test
    fun `a 23`() = assertTokenization(
        input = "<a '>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("'" to ""))
        )
    )

    @Test
    fun `a 24`() = assertTokenization(
        input = "<a (>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("(" to ""))
        )
    )

    @Test
    fun `a 25`() = assertTokenization(
        input = "<a ->",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("-" to ""))
        )
    )

    @Test
    fun `a 26`() = assertTokenization(
        input = "<a .>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("." to ""))
        )
    )

    @Test
    fun `a 27`() = assertTokenization(
        input = "<a />",
        expectedTokens = listOf(
            startTag(name = "a", selfClosing = true)
        )
    )

    @Test
    fun `a 0`() = assertTokenization(
        input = "<a 0>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("0" to ""))
        )
    )

    @Test
    fun `a 1`() = assertTokenization(
        input = "<a 1>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("1" to ""))
        )
    )

    @Test
    fun `a 9-1`() = assertTokenization(
        input = "<a 9>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("9" to ""))
        )
    )

    @Test
    fun `a 28`() = assertTokenization(
        input = "<a <>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("<" to ""))
        )
    )

    @Test
    fun `a 29`() = assertTokenization(
        input = "<a =>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("=" to ""))
        )
    )

    @Test
    fun `a 30`() = assertTokenization(
        input = "<a >",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a 31`() = assertTokenization(
        input = "<a ?>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("?" to ""))
        )
    )

    @Test
    fun `a 32`() = assertTokenization(
        input = "<a @>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("@" to ""))
        )
    )

    @Test
    fun `a A`() = assertTokenization(
        input = "<a A>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a B`() = assertTokenization(
        input = "<a B>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("b" to ""))
        )
    )

    @Test
    fun `a Y`() = assertTokenization(
        input = "<a Y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("y" to ""))
        )
    )

    @Test
    fun `a Z`() = assertTokenization(
        input = "<a Z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("z" to ""))
        )
    )

    @Test
    fun `a 33`() = assertTokenization(
        input = "<a [>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("[" to ""))
        )
    )

    @Test
    fun `a 34`() = assertTokenization(
        input = "<a `>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("`" to ""))
        )
    )

    @Test
    fun `a a`() = assertTokenization(
        input = "<a a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au0000`() = assertTokenization(
        input = "<a a\u0000>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a�" to ""))
        )
    )

    @Test
    fun `a au0008`() = assertTokenization(
        input = "<a a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au0009`() = assertTokenization(
        input = "<a a\t>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000A`() = assertTokenization(
        input = "<a a\n>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000B`() = assertTokenization(
        input = "<a a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000C`() = assertTokenization(
        input = "<a a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000D`() = assertTokenization(
        input = "<a a\r>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au001F`() = assertTokenization(
        input = "<a a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 2`() = assertTokenization(
        input = "<a a >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a u0000`() = assertTokenization(
        input = "<a a \u0000>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "�" to ""))
        )
    )

    @Test
    fun `a a u0008`() = assertTokenization(
        input = "<a a >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "" to ""))
        )
    )

    @Test
    fun `a a u0009`() = assertTokenization(
        input = "<a a \t>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a u000A`() = assertTokenization(
        input = "<a a \n>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a u000B`() = assertTokenization(
        input = "<a a >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "" to ""))
        )
    )

    @Test
    fun `a a u000C`() = assertTokenization(
        input = "<a a >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a u000D`() = assertTokenization(
        input = "<a a \r>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a u001F`() = assertTokenization(
        input = "<a a >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "" to ""))
        )
    )

    @Test
    fun `a a 3`() = assertTokenization(
        input = "<a a  >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 4`() = assertTokenization(
        input = "<a a !>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "!" to ""))
        )
    )

    @Test
    fun `a a 5`() = assertTokenization(
        input = "<a a \">",
        expectedTokens = listOf(
            startTag(
                name = "a", attributes = mapOf("a" to "", "\"" to "")
            )
        )
    )

    @Test
    fun `a a 6`() = assertTokenization(
        input = "<a a #>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "#" to ""))
        )
    )

    @Test
    fun `a a 7`() = assertTokenization(
        input = "<a a &>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "&" to ""))
        )
    )

    @Test
    fun `a a 8`() = assertTokenization(
        input = "<a a '>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "'" to ""))
        )
    )

    @Test
    fun `a a 9-1`() = assertTokenization(
        input = "<a a (>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "(" to ""))
        )
    )

    @Test
    fun `a a 10`() = assertTokenization(
        input = "<a a ->",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "-" to ""))
        )
    )

    @Test
    fun `a a 11`() = assertTokenization(
        input = "<a a .>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "." to ""))
        )
    )

    @Test
    fun `a a 12`() = assertTokenization(
        input = "<a a />",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""), selfClosing = true)
        )
    )

    @Test
    fun `a a 0`() = assertTokenization(
        input = "<a a 0>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "0" to ""))
        )
    )

    @Test
    fun `a a 1`() = assertTokenization(
        input = "<a a 1>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "1" to ""))
        )
    )

    @Test
    fun `a a 9`() = assertTokenization(
        input = "<a a 9>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "9" to ""))
        )
    )

    @Test
    fun `a a 13`() = assertTokenization(
        input = "<a a <>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "<" to ""))
        )
    )

    @Test
    fun `a a 14`() = assertTokenization(
        input = "<a a =>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 15`() = assertTokenization(
        input = "<a a >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 16`() = assertTokenization(
        input = "<a a ?>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "?" to ""))
        )
    )

    @Test
    fun `a a 17`() = assertTokenization(
        input = "<a a @>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "@" to ""))
        )
    )

    @Test
    fun `a a A`() = assertTokenization(
        input = "<a a A>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a B`() = assertTokenization(
        input = "<a a B>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "b" to ""))
        )
    )

    @Test
    fun `a a Y`() = assertTokenization(
        input = "<a a Y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "y" to ""))
        )
    )

    @Test
    fun `a a Z`() = assertTokenization(
        input = "<a a Z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "z" to ""))
        )
    )

    @Test
    fun `a a 18`() = assertTokenization(
        input = "<a a [>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "[" to ""))
        )
    )

    @Test
    fun `a a 19`() = assertTokenization(
        input = "<a a `>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "`" to ""))
        )
    )

    @Test
    fun `a a a`() = assertTokenization(
        input = "<a a a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a b`() = assertTokenization(
        input = "<a a b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "b" to ""))
        )
    )

    @Test
    fun `a a y`() = assertTokenization(
        input = "<a a y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "y" to ""))
        )
    )

    @Test
    fun `a a z`() = assertTokenization(
        input = "<a a z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "z" to ""))
        )
    )

    @Test
    fun `a a 20`() = assertTokenization(
        input = "<a a {>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "{" to ""))
        )
    )

    @Test
    fun `a a uDBC0uDC00`() = assertTokenization(
        input = "<a a 􀀀>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "􀀀" to ""))
        )
    )

    @Test
    fun `a a 21`() = assertTokenization(
        input = "<a a!>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a!" to ""))
        )
    )

    @Test
    fun `a a 22`() = assertTokenization(
        input = "<a a\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a\"" to ""))
        )
    )

    @Test
    fun `a a 23`() = assertTokenization(
        input = "<a a#>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a#" to ""))
        )
    )

    @Test
    fun `a a 24`() = assertTokenization(
        input = "<a a&>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a&" to ""))
        )
    )

    @Test
    fun `a a 25`() = assertTokenization(
        input = "<a a'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a'" to ""))
        )
    )

    @Test
    fun `a a 26`() = assertTokenization(
        input = "<a a(>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a(" to ""))
        )
    )

    @Test
    fun `a a 27`() = assertTokenization(
        input = "<a a->",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a-" to ""))
        )
    )

    @Test
    fun `a a 28`() = assertTokenization(
        input = "<a a.>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a." to ""))
        )
    )

    @Test
    fun `a a 29`() = assertTokenization(
        input = "<a a/>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""), selfClosing = true)
        )
    )

    @Test
    fun `a a0`() = assertTokenization(
        input = "<a a0>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a0" to ""))
        )
    )

    @Test
    fun `a a1`() = assertTokenization(
        input = "<a a1>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a1" to ""))
        )
    )

    @Test
    fun `a a9`() = assertTokenization(
        input = "<a a9>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a9" to ""))
        )
    )

    @Test
    fun `a a 30`() = assertTokenization(
        input = "<a a<>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a<" to ""))
        )
    )

    @Test
    fun `a a 31`() = assertTokenization(
        input = "<a a=>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au0000 2`() = assertTokenization(
        input = "<a a=\u0000>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "�"))
        )
    )

    @Test
    fun `a au0008 2`() = assertTokenization(
        input = "<a a=>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au0009 2`() = assertTokenization(
        input = "<a a=\t>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000A 2`() = assertTokenization(
        input = "<a a=\n>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000B 2`() = assertTokenization(
        input = "<a a=>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000C 2`() = assertTokenization(
        input = "<a a=>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000D 2`() = assertTokenization(
        input = "<a a=\r>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au001F 2`() = assertTokenization(
        input = "<a a=>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 32`() = assertTokenization(
        input = "<a a= >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 33`() = assertTokenization(
        input = "<a a=!>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "!"))
        )
    )

    @Test
    fun `a a 34`() = assertTokenization(
        input = "<a a=\"\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au0000 3`() = assertTokenization(
        input = "<a a=\"\u0000\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "�"))
        )
    )

    @Test
    fun `a au0009 3`() = assertTokenization(
        input = "<a a=\"\t\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "	"))
        )
    )

    @Test
    fun `a au000A 3`() = assertTokenization(
        input = "<a a=\"\n\">",
        expectedTokens = listOf(
            startTag(
                name = "a", attributes = mapOf("a" to "\n")
            )
        )
    )

    @Test
    fun `a au000B 3`() = assertTokenization(
        input = "<a a=\"\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000C 3`() = assertTokenization(
        input = "<a a=\"\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 35`() = assertTokenization(
        input = "<a a=\" \">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to " "))
        )
    )

    @Test
    fun `a a 36`() = assertTokenization(
        input = "<a a=\"!\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "!"))
        )
    )

    @Test
    fun `a a 37`() = assertTokenization(
        input = "<a a=\"\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 38`() = assertTokenization(
        input = "<a a=\"#\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "#"))
        )
    )

    @Test
    fun `a a 39`() = assertTokenization(
        input = "<a a=\"%\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "%"))
        )
    )

    @Test
    fun `a a 40`() = assertTokenization(
        input = "<a a=\"&\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "&"))
        )
    )

    @Test
    fun `a a 41`() = assertTokenization(
        input = "<a a=\"'\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "'"))
        )
    )

    @Test
    fun `a a 42`() = assertTokenization(
        input = "<a a=\"-\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "-"))
        )
    )

    @Test
    fun `a a 43`() = assertTokenization(
        input = "<a a=\"/\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "/"))
        )
    )

    @Test
    fun `a a0 2`() = assertTokenization(
        input = "<a a=\"0\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "0"))
        )
    )

    @Test
    fun `a a1 2`() = assertTokenization(
        input = "<a a=\"1\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "1"))
        )
    )

    @Test
    fun `a a9 2`() = assertTokenization(
        input = "<a a=\"9\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "9"))
        )
    )

    @Test
    fun `a a 44`() = assertTokenization(
        input = "<a a=\"<\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "<"))
        )
    )

    @Test
    fun `a a 45`() = assertTokenization(
        input = "<a a=\"=\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "="))
        )
    )

    @Test
    fun `a a 46`() = assertTokenization(
        input = "<a a=\">\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ">"))
        )
    )

    @Test
    fun `a a 47`() = assertTokenization(
        input = "<a a=\"?\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "?"))
        )
    )

    @Test
    fun `a a 48`() = assertTokenization(
        input = "<a a=\"@\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "@"))
        )
    )

    @Test
    fun `a aA`() = assertTokenization(
        input = "<a a=\"A\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "A"))
        )
    )

    @Test
    fun `a aB`() = assertTokenization(
        input = "<a a=\"B\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "B"))
        )
    )

    @Test
    fun `a aY`() = assertTokenization(
        input = "<a a=\"Y\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "Y"))
        )
    )

    @Test
    fun `a aZ`() = assertTokenization(
        input = "<a a=\"Z\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "Z"))
        )
    )

    @Test
    fun `a a 49`() = assertTokenization(
        input = "<a a=\"`\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "`"))
        )
    )

    @Test
    fun `a aa`() = assertTokenization(
        input = "<a a=\"a\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a ab`() = assertTokenization(
        input = "<a a=\"b\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "b"))
        )
    )

    @Test
    fun `a ay`() = assertTokenization(
        input = "<a a=\"y\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "y"))
        )
    )

    @Test
    fun `a az`() = assertTokenization(
        input = "<a a=\"z\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "z"))
        )
    )

    @Test
    fun `a a 50`() = assertTokenization(
        input = "<a a=\"{\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "{"))
        )
    )

    @Test
    fun `a auDBC0uDC00`() = assertTokenization(
        input = "<a a=\"􀀀\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "􀀀"))
        )
    )

    @Test
    fun `a a 51`() = assertTokenization(
        input = "<a a=#>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "#"))
        )
    )

    @Test
    fun `a a 52`() = assertTokenization(
        input = "<a a=%>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "%"))
        )
    )

    @Test
    fun `a a 53`() = assertTokenization(
        input = "<a a=&>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "&"))
        )
    )

    @Test
    fun `a a 54`() = assertTokenization(
        input = "<a a=''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au0000 4`() = assertTokenization(
        input = "<a a='\u0000'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "�"))
        )
    )

    @Test
    fun `a au0009 4`() = assertTokenization(
        input = "<a a='\t'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "	"))
        )
    )

    @Test
    fun `a au000A 4`() = assertTokenization(
        input = "<a a='\n'>",
        expectedTokens = listOf(
            startTag(
                name = "a", attributes = mapOf("a" to "\n")
            )
        )
    )

    @Test
    fun `a au000B 4`() = assertTokenization(
        input = "<a a=''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000C 4`() = assertTokenization(
        input = "<a a=''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 55`() = assertTokenization(
        input = "<a a=' '>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to " "))
        )
    )

    @Test
    fun `a a 56`() = assertTokenization(
        input = "<a a='!'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "!"))
        )
    )

    @Test
    fun `a a 57`() = assertTokenization(
        input = "<a a='\"'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "\""))
        )
    )

    @Test
    fun `a a 58`() = assertTokenization(
        input = "<a a='%'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "%"))
        )
    )

    @Test
    fun `a a 59`() = assertTokenization(
        input = "<a a='&'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "&"))
        )
    )

    @Test
    fun `a a 60`() = assertTokenization(
        input = "<a a=''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au0000 5`() = assertTokenization(
        input = "<a a=''\u0000>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "�" to ""))
        )
    )

    @Test
    fun `a au0008 3`() = assertTokenization(
        input = "<a a=''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "" to ""))
        )
    )

    @Test
    fun `a au0009 5`() = assertTokenization(
        input = "<a a=''\t>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000A 5`() = assertTokenization(
        input = "<a a=''\n>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000B 5`() = assertTokenization(
        input = "<a a=''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "" to ""))
        )
    )

    @Test
    fun `a au000C 5`() = assertTokenization(
        input = "<a a=''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au000D 3`() = assertTokenization(
        input = "<a a=''\r>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a au001F 3`() = assertTokenization(
        input = "<a a=''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "" to ""))
        )
    )

    @Test
    fun `a a 61`() = assertTokenization(
        input = "<a a='' >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 62`() = assertTokenization(
        input = "<a a=''!>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "!" to ""))
        )
    )

    @Test
    fun `a a 63`() = assertTokenization(
        input = "<a a=''\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "", "\"" to ""))
        )
    )

    @Test
    fun `a a 64`() = assertTokenization(
        input = "<a a=''&>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "&" to "")
            )
        )
    )

    @Test
    fun `a a 65`() = assertTokenization(
        input = "<a a='''>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "'" to "")
            )
        )
    )

    @Test
    fun `a a 66`() = assertTokenization(
        input = "<a a=''->",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "-" to "")
            )
        )
    )

    @Test
    fun `a a 67`() = assertTokenization(
        input = "<a a=''.>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "." to "")
            )
        )
    )

    @Test
    fun `a a 68`() = assertTokenization(
        input = "<a a=''/>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to ""),
                selfClosing = true
            )
        )
    )

    @Test
    fun `a a0 3`() = assertTokenization(
        input = "<a a=''0>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "0" to "")
            )
        )
    )

    @Test
    fun `a a1 3`() = assertTokenization(
        input = "<a a=''1>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "1" to "")
            )
        )
    )

    @Test
    fun `a a9 3`() = assertTokenization(
        input = "<a a=''9>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "9" to "")
            )
        )
    )

    @Test
    fun `a a 69`() = assertTokenization(
        input = "<a a=''<>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "<" to "")
            )
        )
    )

    @Test
    fun `a a 70`() = assertTokenization(
        input = "<a a=''=>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "=" to "")
            )
        )
    )

    @Test
    fun `a a 71`() = assertTokenization(
        input = "<a a=''>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 72`() = assertTokenization(
        input = "<a a=''?>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "?" to "")
            )
        )
    )

    @Test
    fun `a a 73`() = assertTokenization(
        input = "<a a=''@>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "@" to "")
            )
        )
    )

    @Test
    fun `a aA 2`() = assertTokenization(
        input = "<a a=''A>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a aB 2`() = assertTokenization(
        input = "<a a=''B>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "b" to "")
            )
        )
    )

    @Test
    fun `a aY 2`() = assertTokenization(
        input = "<a a=''Y>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "y" to "")
            )
        )
    )

    @Test
    fun `a aZ 2`() = assertTokenization(
        input = "<a a=''Z>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "z" to "")
            )
        )
    )

    @Test
    fun `a a 74`() = assertTokenization(
        input = "<a a=''`>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "`" to "")
            )
        )
    )

    @Test
    fun `a aa 2`() = assertTokenization(
        input = "<a a=''a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a ab 2`() = assertTokenization(
        input = "<a a=''b>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "b" to "")
            )
        )
    )

    @Test
    fun `a ay 2`() = assertTokenization(
        input = "<a a=''y>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "y" to "")
            )
        )
    )

    @Test
    fun `a az 2`() = assertTokenization(
        input = "<a a=''z>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "z" to "")
            )
        )
    )

    @Test
    fun `a a 75`() = assertTokenization(
        input = "<a a=''{>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "{" to "")
            )
        )
    )

    @Test
    fun `a auDBC0uDC00 2`() = assertTokenization(
        input = "<a a=''􀀀>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "", "􀀀" to "")
            )
        )
    )

    @Test
    fun `a a 76`() = assertTokenization(
        input = "<a a='('>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "("))
        )
    )

    @Test
    fun `a a 77`() = assertTokenization(
        input = "<a a='-'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "-"))
        )
    )

    @Test
    fun `a a 78`() = assertTokenization(
        input = "<a a='/'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "/"))
        )
    )

    @Test
    fun `a a0 4`() = assertTokenization(
        input = "<a a='0'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "0"))
        )
    )

    @Test
    fun `a a1 4`() = assertTokenization(
        input = "<a a='1'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "1"))
        )
    )

    @Test
    fun `a a9 4`() = assertTokenization(
        input = "<a a='9'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "9"))
        )
    )

    @Test
    fun `a a 79`() = assertTokenization(
        input = "<a a='<'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "<"))
        )
    )

    @Test
    fun `a a 80`() = assertTokenization(
        input = "<a a='='>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "="))
        )
    )

    @Test
    fun `a a 81`() = assertTokenization(
        input = "<a a='>'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ">"))
        )
    )

    @Test
    fun `a a 82`() = assertTokenization(
        input = "<a a='?'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "?"))
        )
    )

    @Test
    fun `a a 83`() = assertTokenization(
        input = "<a a='@'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "@"))
        )
    )

    @Test
    fun `a aA 3`() = assertTokenization(
        input = "<a a='A'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "A"))
        )
    )

    @Test
    fun `a aB 3`() = assertTokenization(
        input = "<a a='B'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "B"))
        )
    )

    @Test
    fun `a aY 3`() = assertTokenization(
        input = "<a a='Y'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "Y"))
        )
    )

    @Test
    fun `a aZ 3`() = assertTokenization(
        input = "<a a='Z'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "Z"))
        )
    )

    @Test
    fun `a a 84`() = assertTokenization(
        input = "<a a='`'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "`"))
        )
    )

    @Test
    fun `a aa 3`() = assertTokenization(
        input = "<a a='a'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a ab 3`() = assertTokenization(
        input = "<a a='b'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "b"))
        )
    )

    @Test
    fun `a ay 3`() = assertTokenization(
        input = "<a a='y'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "y"))
        )
    )

    @Test
    fun `a az 3`() = assertTokenization(
        input = "<a a='z'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "z"))
        )
    )

    @Test
    fun `a a 85`() = assertTokenization(
        input = "<a a='{'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "{"))
        )
    )

    @Test
    fun `a auDBC0uDC00 3`() = assertTokenization(
        input = "<a a='􀀀'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "􀀀"))
        )
    )

    @Test
    fun `a a 86`() = assertTokenization(
        input = "<a a=(>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "("))
        )
    )

    @Test
    fun `a a 87`() = assertTokenization(
        input = "<a a=->",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "-"))
        )
    )

    @Test
    fun `a a 88`() = assertTokenization(
        input = "<a a=/>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "/"))
        )
    )

    @Test
    fun `a a0 5`() = assertTokenization(
        input = "<a a=0>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "0"))
        )
    )

    @Test
    fun `a a1 5`() = assertTokenization(
        input = "<a a=1>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "1"))
        )
    )

    @Test
    fun `a a9 5`() = assertTokenization(
        input = "<a a=9>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "9"))
        )
    )

    @Test
    fun `a a 89`() = assertTokenization(
        input = "<a a=<>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "<"))
        )
    )

    @Test
    fun `a a 90`() = assertTokenization(
        input = "<a a==>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "="))
        )
    )

    @Test
    fun `a a 91`() = assertTokenization(
        input = "<a a=>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `a a 92`() = assertTokenization(
        input = "<a a=?>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "?"))
        )
    )

    @Test
    fun `a a 93`() = assertTokenization(
        input = "<a a=@>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "@"))
        )
    )

    @Test
    fun `a aA 4`() = assertTokenization(
        input = "<a a=A>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "A"))
        )
    )

    @Test
    fun `a aB 4`() = assertTokenization(
        input = "<a a=B>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "B"))
        )
    )

    @Test
    fun `a aY 4`() = assertTokenization(
        input = "<a a=Y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "Y"))
        )
    )

    @Test
    fun `a aZ 4`() = assertTokenization(
        input = "<a a=Z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "Z"))
        )
    )

    @Test
    fun `a a 94`() = assertTokenization(
        input = "<a a=`>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "`"))
        )
    )

    @Test
    fun `a aa 4`() = assertTokenization(
        input = "<a a=a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aau0000`() = assertTokenization(
        input = "<a a=a\u0000>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a�"))
        )
    )

    @Test
    fun `a aau0008`() = assertTokenization(
        input = "<a a=a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aau0009`() = assertTokenization(
        input = "<a a=a\t>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aau000A`() = assertTokenization(
        input = "<a a=a\n>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aau000B`() = assertTokenization(
        input = "<a a=a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aau000C`() = assertTokenization(
        input = "<a a=a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aau000D`() = assertTokenization(
        input = "<a a=a\r>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aau001F`() = assertTokenization(
        input = "<a a=a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aa 5`() = assertTokenization(
        input = "<a a=a >",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a"))
        )
    )

    @Test
    fun `a aa 6`() = assertTokenization(
        input = "<a a=a!>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a!"))
        )
    )

    @Test
    fun `a aa 7`() = assertTokenization(
        input = "<a a=a\">",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a\""))
        )
    )

    @Test
    fun `a aa 8`() = assertTokenization(
        input = "<a a=a#>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a#")
            )
        )
    )

    @Test
    fun `a aa 9`() = assertTokenization(
        input = "<a a=a%>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a%")
            )
        )
    )

    @Test
    fun `a aa 10`() = assertTokenization(
        input = "<a a=a&>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a&")
            )
        )
    )

    @Test
    fun `a aa 11`() = assertTokenization(
        input = "<a a=a'>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a'")
            )
        )
    )

    @Test
    fun `a aa 12`() = assertTokenization(
        input = "<a a=a(>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a(")
            )
        )
    )

    @Test
    fun `a aa 13`() = assertTokenization(
        input = "<a a=a->",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a-")
            )
        )
    )

    @Test
    fun `a aa 14`() = assertTokenization(
        input = "<a a=a/>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a/")
            )
        )
    )

    @Test
    fun `a aa0`() = assertTokenization(
        input = "<a a=a0>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a0")
            )
        )
    )

    @Test
    fun `a aa1`() = assertTokenization(
        input = "<a a=a1>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a1")
            )
        )
    )

    @Test
    fun `a aa9`() = assertTokenization(
        input = "<a a=a9>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a9")
            )
        )
    )

    @Test
    fun `a aa 15`() = assertTokenization(
        input = "<a a=a<>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a<")
            )
        )
    )

    @Test
    fun `a aa 16`() = assertTokenization(
        input = "<a a=a=>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a=")
            )
        )
    )

    @Test
    fun `a aa 17`() = assertTokenization(
        input = "<a a=a>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a")
            )
        )
    )

    @Test
    fun `a aa 18`() = assertTokenization(
        input = "<a a=a?>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a?")
            )
        )
    )

    @Test
    fun `a aa 19`() = assertTokenization(
        input = "<a a=a@>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a@")
            )
        )
    )

    @Test
    fun `a aaA`() = assertTokenization(
        input = "<a a=aA>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "aA")
            )
        )
    )

    @Test
    fun `a aaB`() = assertTokenization(
        input = "<a a=aB>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "aB")
            )
        )
    )

    @Test
    fun `a aaY`() = assertTokenization(
        input = "<a a=aY>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "aY")
            )
        )
    )

    @Test
    fun `a aaZ`() = assertTokenization(
        input = "<a a=aZ>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "aZ")
            )
        )
    )

    @Test
    fun `a aa 20`() = assertTokenization(
        input = "<a a=a`>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a`")
            )
        )
    )

    @Test
    fun `a aaa`() = assertTokenization(
        input = "<a a=aa>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "aa")
            )
        )
    )

    @Test
    fun `a aab`() = assertTokenization(
        input = "<a a=ab>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "ab")
            )
        )
    )

    @Test
    fun `a aay`() = assertTokenization(
        input = "<a a=ay>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "ay")
            )
        )
    )

    @Test
    fun `a aaz`() = assertTokenization(
        input = "<a a=az>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "az")
            )
        )
    )

    @Test
    fun `a aa 21`() = assertTokenization(
        input = "<a a=a{>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a{")
            )
        )
    )

    @Test
    fun `a aauDBC0uDC00`() = assertTokenization(
        input = "<a a=a􀀀>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "a􀀀")
            )
        )
    )

    @Test
    fun `a ab 4`() = assertTokenization(
        input = "<a a=b>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "b")
            )
        )
    )

    @Test
    fun `a ay 4`() = assertTokenization(
        input = "<a a=y>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "y")
            )
        )
    )

    @Test
    fun `a az 4`() = assertTokenization(
        input = "<a a=z>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "z")
            )
        )
    )

    @Test
    fun `a a 95`() = assertTokenization(
        input = "<a a={>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "{")
            )
        )
    )

    @Test
    fun `a auDBC0uDC00 4`() = assertTokenization(
        input = "<a a=􀀀>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "􀀀")
            )
        )
    )

    @Test
    fun `a a 96`() = assertTokenization(
        input = "<a a>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a" to "")
            )
        )
    )

    @Test
    fun `a a 97`() = assertTokenization(
        input = "<a a?>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a?" to "")
            )
        )
    )

    @Test
    fun `a a 98`() = assertTokenization(
        input = "<a a@>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a@" to "")
            )
        )
    )

    @Test
    fun `a aA 5`() = assertTokenization(
        input = "<a aA>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("aa" to "")
            )
        )
    )

    @Test
    fun `a aB 5`() = assertTokenization(
        input = "<a aB>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("ab" to "")
            )
        )
    )

    @Test
    fun `a aY 5`() = assertTokenization(
        input = "<a aY>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("ay" to "")
            )
        )
    )

    @Test
    fun `a aZ 5`() = assertTokenization(
        input = "<a aZ>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("az" to "")
            )
        )
    )

    @Test
    fun `a a 99`() = assertTokenization(
        input = "<a a[>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a[" to "")
            )
        )
    )

    @Test
    fun `a a 100`() = assertTokenization(
        input = "<a a`>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a`" to "")
            )
        )
    )

    @Test
    fun `a aa 22`() = assertTokenization(
        input = "<a aa>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("aa" to "")
            )
        )
    )

    @Test
    fun `a ab 5`() = assertTokenization(
        input = "<a ab>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("ab" to "")
            )
        )
    )

    @Test
    fun `a ay 5`() = assertTokenization(
        input = "<a ay>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("ay" to "")
            )
        )
    )

    @Test
    fun `a az 5`() = assertTokenization(
        input = "<a az>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("az" to "")
            )
        )
    )

    @Test
    fun `a a 101`() = assertTokenization(
        input = "<a a{>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a{" to "")
            )
        )
    )

    @Test
    fun `a auDBC0uDC00 5`() = assertTokenization(
        input = "<a a􀀀>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("a􀀀" to "")
            )
        )
    )

    @Test
    fun `a b`() = assertTokenization(
        input = "<a b>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("b" to "")
            )
        )
    )

    @Test
    fun `a y`() = assertTokenization(
        input = "<a y>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("y" to "")
            )
        )
    )

    @Test
    fun `a z`() = assertTokenization(
        input = "<a z>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("z" to "")
            )
        )
    )

    @Test
    fun `a 35`() = assertTokenization(
        input = "<a {>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("{" to "")
            )
        )
    )

    @Test
    fun `a uDBC0uDC00`() = assertTokenization(
        input = "<a 􀀀>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("􀀀" to "")
            )
        )
    )

    @Test
    fun `a 36`() = assertTokenization(
        input = "<a!>",
        expectedTokens = listOf(
            startTag(name = "a!")
        )
    )

    @Test
    fun `a 37`() = assertTokenization(
        input = "<a\">",
        expectedTokens = listOf(
            startTag(name = "a\"")
        )
    )

    @Test
    fun `a 38`() = assertTokenization(
        input = "<a&>",
        expectedTokens = listOf(
            startTag(name = "a&")
        )
    )

    @Test
    fun `a 39`() = assertTokenization(
        input = "<a'>",
        expectedTokens = listOf(
            startTag(name = "a'")
        )
    )

    @Test
    fun `a 40`() = assertTokenization(
        input = "<a->",
        expectedTokens = listOf(
            startTag(name = "a-")
        )
    )

    @Test
    fun `a 41`() = assertTokenization(
        input = "<a.>",
        expectedTokens = listOf(
            startTag(name = "a.")
        )
    )

    @Test
    fun `a 42`() = assertTokenization(
        input = "<a/>",
        expectedTokens = listOf(
            startTag(name = "a", selfClosing = true)
        )
    )

    @Test
    fun `au0000 2`() = assertTokenization(
        input = "<a/\u0000>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("�" to "")
            )
        )
    )

    @Test
    fun `au0009 2`() = assertTokenization(
        input = "<a/\t>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au000A 2`() = assertTokenization(
        input = "<a/\n>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `au000B 2`() = assertTokenization(
        input = "<a/>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("" to "")
            )
        )
    )

    @Test
    fun `au000C 2`() = assertTokenization(
        input = "<a/>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a 43`() = assertTokenization(
        input = "<a/ >",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a 44`() = assertTokenization(
        input = "<a/!>",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf("!" to "")
            )
        )
    )

    @Test
    fun `a 45`() = assertTokenization(
        input = "<a/\">",
        expectedTokens = listOf(
            startTag(
                name = "a",
                attributes = mapOf(
                    "\"" to ""
                )
            )
        )
    )

    @Test
    fun `a 46`() = assertTokenization(
        input = "<a/&>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("&" to ""))
        )
    )

    @Test
    fun `a 47`() = assertTokenization(
        input = "<a/'>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("'" to ""))
        )
    )

    @Test
    fun `a 48`() = assertTokenization(
        input = "<a/->",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("-" to ""))
        )
    )

    @Test
    fun `a 49`() = assertTokenization(
        input = "<a//>",
        expectedTokens = listOf(
            startTag(name = "a", selfClosing = true)
        )
    )

    @Test
    fun `a0`() = assertTokenization(
        input = "<a/0>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("0" to ""))
        )
    )

    @Test
    fun `a1`() = assertTokenization(
        input = "<a/1>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("1" to ""))
        )
    )

    @Test
    fun `a9`() = assertTokenization(
        input = "<a/9>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("9" to ""))
        )
    )

    @Test
    fun `a 50`() = assertTokenization(
        input = "<a/<>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("<" to ""))
        )
    )

    @Test
    fun `a 51`() = assertTokenization(
        input = "<a/=>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("=" to ""))
        )
    )

    @Test
    fun `a 52`() = assertTokenization(
        input = "<a/>",
        expectedTokens = listOf(
            startTag(name = "a", selfClosing = true)
        )
    )

    @Test
    fun `a 53`() = assertTokenization(
        input = "<a/?>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("?" to ""))
        )
    )

    @Test
    fun `a 54`() = assertTokenization(
        input = "<a/@>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("@" to ""))
        )
    )

    @Test
    fun `aA`() = assertTokenization(
        input = "<a/A>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `aB`() = assertTokenization(
        input = "<a/B>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("b" to ""))
        )
    )

    @Test
    fun `aY`() = assertTokenization(
        input = "<a/Y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("y" to ""))
        )
    )

    @Test
    fun `aZ`() = assertTokenization(
        input = "<a/Z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("z" to ""))
        )
    )

    @Test
    fun `a 55`() = assertTokenization(
        input = "<a/`>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("`" to ""))
        )
    )

    @Test
    fun `aa`() = assertTokenization(
        input = "<a/a>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to ""))
        )
    )

    @Test
    fun `ab`() = assertTokenization(
        input = "<a/b>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("b" to ""))
        )
    )

    @Test
    fun `ay`() = assertTokenization(
        input = "<a/y>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("y" to ""))
        )
    )

    @Test
    fun `az`() = assertTokenization(
        input = "<a/z>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("z" to ""))
        )
    )

    @Test
    fun `a 56`() = assertTokenization(
        input = "<a/{>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("{" to ""))
        )
    )

    @Test
    fun `auDBC0uDC00`() = assertTokenization(
        input = "<a/􀀀>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("􀀀" to ""))
        )
    )

    @Test
    fun `a0 2`() = assertTokenization(
        input = "<a0>",
        expectedTokens = listOf(
            startTag(name = "a0")
        )
    )

    @Test
    fun `a1 2`() = assertTokenization(
        input = "<a1>",
        expectedTokens = listOf(
            startTag(name = "a1")
        )
    )

    @Test
    fun `a9 2`() = assertTokenization(
        input = "<a9>",
        expectedTokens = listOf(
            startTag(name = "a9")
        )
    )

    @Test
    fun `a 57`() = assertTokenization(
        input = "<a<>",
        expectedTokens = listOf(
            startTag(name = "a<")
        )
    )

    @Test
    fun `a 58`() = assertTokenization(
        input = "<a=>",
        expectedTokens = listOf(
            startTag(name = "a=")
        )
    )

    @Test
    fun `a 59`() = assertTokenization(
        input = "<a>",
        expectedTokens = listOf(
            startTag(name = "a")
        )
    )

    @Test
    fun `a 60`() = assertTokenization(
        input = "<a?>",
        expectedTokens = listOf(
            startTag(name = "a?")
        )
    )

    @Test
    fun `a 61`() = assertTokenization(
        input = "<a@>",
        expectedTokens = listOf(
            startTag(name = "a@")
        )
    )

    @Test
    fun `aA 2`() = assertTokenization(
        input = "<aA>",
        expectedTokens = listOf(
            startTag(name = "aa")
        )
    )

    @Test
    fun `aB 2`() = assertTokenization(
        input = "<aB>",
        expectedTokens = listOf(
            startTag(name = "ab")
        )
    )

    @Test
    fun `aY 2`() = assertTokenization(
        input = "<aY>",
        expectedTokens = listOf(
            startTag(name = "ay")
        )
    )

    @Test
    fun `aZ 2`() = assertTokenization(
        input = "<aZ>",
        expectedTokens = listOf(
            startTag(name = "az")
        )
    )

    @Test
    fun `a 62`() = assertTokenization(
        input = "<a[>",
        expectedTokens = listOf(
            startTag(name = "a[")
        )
    )

    @Test
    fun `a 63`() = assertTokenization(
        input = "<a`>",
        expectedTokens = listOf(
            startTag(name = "a`")
        )
    )

    @Test
    fun `aa 2`() = assertTokenization(
        input = "<aa>",
        expectedTokens = listOf(
            startTag(name = "aa")
        )
    )

    @Test
    fun `ab 2`() = assertTokenization(
        input = "<ab>",
        expectedTokens = listOf(
            startTag(name = "ab")
        )
    )

    @Test
    fun `ay 2`() = assertTokenization(
        input = "<ay>",
        expectedTokens = listOf(
            startTag(name = "ay")
        )
    )

    @Test
    fun `az 2`() = assertTokenization(
        input = "<az>",
        expectedTokens = listOf(
            startTag(name = "az")
        )
    )

    @Test
    fun `a 64`() = assertTokenization(
        input = "<a{>",
        expectedTokens = listOf(
            startTag(name = "a{")
        )
    )

    @Test
    fun `auDBC0uDC00 2`() = assertTokenization(
        input = "<a􀀀>",
        expectedTokens = listOf(
            startTag(name = "a􀀀")
        )
    )

    @Test
    fun `b 11`() = assertTokenization(
        input = "<b>",
        expectedTokens = listOf(
            startTag(name = "b")
        )
    )

    @Test
    fun `y 11`() = assertTokenization(
        input = "<y>",
        expectedTokens = listOf(
            startTag(name = "y")
        )
    )

    @Test
    fun `z 11`() = assertTokenization(
        input = "<z>",
        expectedTokens = listOf(
            startTag(name = "z")
        )
    )

    @Test
    fun ` 182`() = assertTokenization(
        input = "<{",
        expectedTokens = listOf(
            *chars("<{").toTypedArray()
        )
    )

    @Test
    fun `uDBC0uDC00 11`() = assertTokenization(
        input = "<􀀀",
        expectedTokens = listOf(
            *chars("<􀀀").toTypedArray()
        )
    )

    @Test
    fun ` 183`() = assertTokenization(
        input = "=",
        expectedTokens = listOf(
            char('=')
        )
    )

    @Test
    fun ` 184`() = assertTokenization(
        input = "=",
        expectedTokens = listOf(
            char('=')
        )
    )

    @Test
    fun ` 185`() = assertTokenization(
        input = ">",
        expectedTokens = listOf(
            char('>')
        )
    )

    @Test
    fun ` 186`() = assertTokenization(
        input = ">",
        expectedTokens = listOf(
            char('>')
        )
    )

    @Test
    fun ` 187`() = assertTokenization(
        input = "?",
        expectedTokens = listOf(
            char('?')
        )
    )

    @Test
    fun ` 188`() = assertTokenization(
        input = "?",
        expectedTokens = listOf(
            char('?')
        )
    )

    @Test
    fun ` 189`() = assertTokenization(
        input = "@",
        expectedTokens = listOf(
            char('@')
        )
    )

    @Test
    fun ` 190`() = assertTokenization(
        input = "@",
        expectedTokens = listOf(
            char('@')
        )
    )

    @Test
    fun `A 12`() = assertTokenization(
        input = "A",
        expectedTokens = listOf(
            char('A')
        )
    )

    @Test
    fun `A 13`() = assertTokenization(
        input = "A",
        expectedTokens = listOf(
            char('A')
        )
    )

    @Test
    fun `B 12`() = assertTokenization(
        input = "B",
        expectedTokens = listOf(
            char('B')
        )
    )

    @Test
    fun `B 13`() = assertTokenization(
        input = "B",
        expectedTokens = listOf(
            char('B')
        )
    )

    @Test
    fun `Y 12`() = assertTokenization(
        input = "Y",
        expectedTokens = listOf(
            char('Y')
        )
    )

    @Test
    fun `Y 13`() = assertTokenization(
        input = "Y",
        expectedTokens = listOf(
            char('Y')
        )
    )

    @Test
    fun `Z 12`() = assertTokenization(
        input = "Z",
        expectedTokens = listOf(
            char('Z')
        )
    )

    @Test
    fun `Z 13`() = assertTokenization(
        input = "Z",
        expectedTokens = listOf(
            char('Z')
        )
    )

    @Test
    fun ` 191`() = assertTokenization(
        input = "`",
        expectedTokens = listOf(
            char('`')
        )
    )

    @Test
    fun ` 192`() = assertTokenization(
        input = "`",
        expectedTokens = listOf(
            char('`')
        )
    )

    @Test
    fun `a 65`() = assertTokenization(
        input = "a",
        expectedTokens = listOf(
            char('a')
        )
    )

    @Test
    fun `a 66`() = assertTokenization(
        input = "a",
        expectedTokens = listOf(
            char('a')
        )
    )

    @Test
    fun `b 12`() = assertTokenization(
        input = "b",
        expectedTokens = listOf(
            char('b')
        )
    )

    @Test
    fun `b 13`() = assertTokenization(
        input = "b",
        expectedTokens = listOf(
            char('b')
        )
    )

    @Test
    fun `y 12`() = assertTokenization(
        input = "y",
        expectedTokens = listOf(
            char('y')
        )
    )

    @Test
    fun `y 13`() = assertTokenization(
        input = "y",
        expectedTokens = listOf(
            char('y')
        )
    )

    @Test
    fun `z 12`() = assertTokenization(
        input = "z",
        expectedTokens = listOf(
            char('z')
        )
    )

    @Test
    fun `z 13`() = assertTokenization(
        input = "z",
        expectedTokens = listOf(
            char('z')
        )
    )

    @Test
    fun ` 193`() = assertTokenization(
        input = "{",
        expectedTokens = listOf(
            char('{')
        )
    )

    @Test
    fun ` 194`() = assertTokenization(
        input = "{",
        expectedTokens = listOf(
            char('{')
        )
    )

    @Test
    fun `uDBC0uDC00 12`() = assertTokenization(
        input = "􀀀",
        expectedTokens = listOf(
            *chars("􀀀").toTypedArray()
        )
    )

    @Test
    fun `uDBC0uDC00 13`() = assertTokenization(
        input = "􀀀",
        expectedTokens = listOf(
            *chars("􀀀").toTypedArray()
        )
    )
}
