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
 * Contains all 323 tests from here:
 * https://github.com/html5lib/html5lib-tests/blob/master/tokenizer/unicodeChars.test
 */
internal class UnicodeCharsTest : TokenizerTestBase() {

    @Test
    fun `Invalid Unicode character U+0001`() = assertTokenization(
        input = "\u0001",
        expectedTokens = listOf(char('\u0001'))
    )

    @Test
    fun `Invalid Unicode character U+0002`() = assertTokenization(
        input = "\u0002",
        expectedTokens = listOf(char('\u0002'))
    )

    @Test
    fun `Invalid Unicode character U+0003`() = assertTokenization(
        input = "\u0003",
        expectedTokens = listOf(char('\u0003'))
    )

    @Test
    fun `Invalid Unicode character U+0004`() = assertTokenization(
        input = "\u0004",
        expectedTokens = listOf(char('\u0004'))
    )

    @Test
    fun `Invalid Unicode character U+0005`() = assertTokenization(
        input = "\u0005",
        expectedTokens = listOf(char('\u0005'))
    )

    @Test
    fun `Invalid Unicode character U+0006`() = assertTokenization(
        input = "\u0006",
        expectedTokens = listOf(char('\u0006'))
    )

    @Test
    fun `Invalid Unicode character U+0007`() = assertTokenization(
        input = "\u0007",
        expectedTokens = listOf(char('\u0007'))
    )

    @Test
    fun `Invalid Unicode character U+0008`() = assertTokenization(
        input = "\u0008",
        expectedTokens = listOf(char('\u0008'))
    )

    @Test
    fun `Invalid Unicode character U+000B`() = assertTokenization(
        input = "\u000B",
        expectedTokens = listOf(char('\u000B'))
    )

    @Test
    fun `Invalid Unicode character U+000E`() = assertTokenization(
        input = "\u000E",
        expectedTokens = listOf(char('\u000E'))
    )

    @Test
    fun `Invalid Unicode character U+000F`() = assertTokenization(
        input = "\u000F",
        expectedTokens = listOf(char('\u000F'))
    )

    @Test
    fun `Invalid Unicode character U+0010`() = assertTokenization(
        input = "\u0010",
        expectedTokens = listOf(char('\u0010'))
    )

    @Test
    fun `Invalid Unicode character U+0011`() = assertTokenization(
        input = "\u0011",
        expectedTokens = listOf(char('\u0011'))
    )

    @Test
    fun `Invalid Unicode character U+0012`() = assertTokenization(
        input = "\u0012",
        expectedTokens = listOf(char('\u0012'))
    )

    @Test
    fun `Invalid Unicode character U+0013`() = assertTokenization(
        input = "\u0013",
        expectedTokens = listOf(char('\u0013'))
    )

    @Test
    fun `Invalid Unicode character U+0014`() = assertTokenization(
        input = "\u0014",
        expectedTokens = listOf(char('\u0014'))
    )

    @Test
    fun `Invalid Unicode character U+0015`() = assertTokenization(
        input = "\u0015",
        expectedTokens = listOf(char('\u0015'))
    )

    @Test
    fun `Invalid Unicode character U+0016`() = assertTokenization(
        input = "\u0016",
        expectedTokens = listOf(char('\u0016'))
    )

    @Test
    fun `Invalid Unicode character U+0017`() = assertTokenization(
        input = "\u0017",
        expectedTokens = listOf(char('\u0017'))
    )

    @Test
    fun `Invalid Unicode character U+0018`() = assertTokenization(
        input = "\u0018",
        expectedTokens = listOf(char('\u0018'))
    )

    @Test
    fun `Invalid Unicode character U+0019`() = assertTokenization(
        input = "\u0019",
        expectedTokens = listOf(char('\u0019'))
    )

    @Test
    fun `Invalid Unicode character U+001A`() = assertTokenization(
        input = "\u001A",
        expectedTokens = listOf(char('\u001A'))
    )

    @Test
    fun `Invalid Unicode character U+001B`() = assertTokenization(
        input = "\u001B",
        expectedTokens = listOf(char('\u001B'))
    )

    @Test
    fun `Invalid Unicode character U+001C`() = assertTokenization(
        input = "\u001C",
        expectedTokens = listOf(char('\u001C'))
    )

    @Test
    fun `Invalid Unicode character U+001D`() = assertTokenization(
        input = "\u001D",
        expectedTokens = listOf(char('\u001D'))
    )

    @Test
    fun `Invalid Unicode character U+001E`() = assertTokenization(
        input = "\u001E",
        expectedTokens = listOf(char('\u001E'))
    )

    @Test
    fun `Invalid Unicode character U+001F`() = assertTokenization(
        input = "\u001F",
        expectedTokens = listOf(char('\u001F'))
    )

    @Test
    fun `Invalid Unicode character U+007F`() = assertTokenization(
        input = "\u007F",
        expectedTokens = listOf(char('\u007F'))
    )

    @Test
    fun `Invalid Unicode character U+FDD0`() = assertTokenization(
        input = "\uFDD0",
        expectedTokens = listOf(char('\uFDD0'))
    )

    @Test
    fun `Invalid Unicode character U+FDD1`() = assertTokenization(
        input = "\uFDD1",
        expectedTokens = listOf(char('\uFDD1'))
    )

    @Test
    fun `Invalid Unicode character U+FDD2`() = assertTokenization(
        input = "\uFDD2",
        expectedTokens = listOf(char('\uFDD2'))
    )

    @Test
    fun `Invalid Unicode character U+FDD3`() = assertTokenization(
        input = "\uFDD3",
        expectedTokens = listOf(char('\uFDD3'))
    )

    @Test
    fun `Invalid Unicode character U+FDD4`() = assertTokenization(
        input = "\uFDD4",
        expectedTokens = listOf(char('\uFDD4'))
    )

    @Test
    fun `Invalid Unicode character U+FDD5`() = assertTokenization(
        input = "\uFDD5",
        expectedTokens = listOf(char('\uFDD5'))
    )

    @Test
    fun `Invalid Unicode character U+FDD6`() = assertTokenization(
        input = "\uFDD6",
        expectedTokens = listOf(char('\uFDD6'))
    )

    @Test
    fun `Invalid Unicode character U+FDD7`() = assertTokenization(
        input = "\uFDD7",
        expectedTokens = listOf(char('\uFDD7'))
    )

    @Test
    fun `Invalid Unicode character U+FDD8`() = assertTokenization(
        input = "\uFDD8",
        expectedTokens = listOf(char('\uFDD8'))
    )

    @Test
    fun `Invalid Unicode character U+FDD9`() = assertTokenization(
        input = "\uFDD9",
        expectedTokens = listOf(char('\uFDD9'))
    )

    @Test
    fun `Invalid Unicode character U+FDDA`() = assertTokenization(
        input = "\uFDDA",
        expectedTokens = listOf(char('\uFDDA'))
    )

    @Test
    fun `Invalid Unicode character U+FDDB`() = assertTokenization(
        input = "\uFDDB",
        expectedTokens = listOf(char('\uFDDB'))
    )

    @Test
    fun `Invalid Unicode character U+FDDC`() = assertTokenization(
        input = "\uFDDC",
        expectedTokens = listOf(char('\uFDDC'))
    )

    @Test
    fun `Invalid Unicode character U+FDDD`() = assertTokenization(
        input = "\uFDDD",
        expectedTokens = listOf(char('\uFDDD'))
    )

    @Test
    fun `Invalid Unicode character U+FDDE`() = assertTokenization(
        input = "\uFDDE",
        expectedTokens = listOf(char('\uFDDE'))
    )

    @Test
    fun `Invalid Unicode character U+FDDF`() = assertTokenization(
        input = "\uFDDF",
        expectedTokens = listOf(char('\uFDDF'))
    )

    @Test
    fun `Invalid Unicode character U+FDE0`() = assertTokenization(
        input = "\uFDE0",
        expectedTokens = listOf(char('\uFDE0'))
    )

    @Test
    fun `Invalid Unicode character U+FDE1`() = assertTokenization(
        input = "\uFDE1",
        expectedTokens = listOf(char('\uFDE1'))
    )

    @Test
    fun `Invalid Unicode character U+FDE2`() = assertTokenization(
        input = "\uFDE2",
        expectedTokens = listOf(char('\uFDE2'))
    )

    @Test
    fun `Invalid Unicode character U+FDE3`() = assertTokenization(
        input = "\uFDE3",
        expectedTokens = listOf(char('\uFDE3'))
    )

    @Test
    fun `Invalid Unicode character U+FDE4`() = assertTokenization(
        input = "\uFDE4",
        expectedTokens = listOf(char('\uFDE4'))
    )

    @Test
    fun `Invalid Unicode character U+FDE5`() = assertTokenization(
        input = "\uFDE5",
        expectedTokens = listOf(char('\uFDE5'))
    )

    @Test
    fun `Invalid Unicode character U+FDE6`() = assertTokenization(
        input = "\uFDE6",
        expectedTokens = listOf(char('\uFDE6'))
    )

    @Test
    fun `Invalid Unicode character U+FDE7`() = assertTokenization(
        input = "\uFDE7",
        expectedTokens = listOf(char('\uFDE7'))
    )

    @Test
    fun `Invalid Unicode character U+FDE8`() = assertTokenization(
        input = "\uFDE8",
        expectedTokens = listOf(char('\uFDE8'))
    )

    @Test
    fun `Invalid Unicode character U+FDE9`() = assertTokenization(
        input = "\uFDE9",
        expectedTokens = listOf(char('\uFDE9'))
    )

    @Test
    fun `Invalid Unicode character U+FDEA`() = assertTokenization(
        input = "\uFDEA",
        expectedTokens = listOf(char('\uFDEA'))
    )

    @Test
    fun `Invalid Unicode character U+FDEB`() = assertTokenization(
        input = "\uFDEB",
        expectedTokens = listOf(char('\uFDEB'))
    )

    @Test
    fun `Invalid Unicode character U+FDEC`() = assertTokenization(
        input = "\uFDEC",
        expectedTokens = listOf(char('\uFDEC'))
    )

    @Test
    fun `Invalid Unicode character U+FDED`() = assertTokenization(
        input = "\uFDED",
        expectedTokens = listOf(char('\uFDED'))
    )

    @Test
    fun `Invalid Unicode character U+FDEE`() = assertTokenization(
        input = "\uFDEE",
        expectedTokens = listOf(char('\uFDEE'))
    )

    @Test
    fun `Invalid Unicode character U+FDEF`() = assertTokenization(
        input = "\uFDEF",
        expectedTokens = listOf(char('\uFDEF'))
    )

    @Test
    fun `Invalid Unicode character U+FFFE`() = assertTokenization(
        input = "\uFFFE",
        expectedTokens = listOf(char('\uFFFE'))
    )

    @Test
    fun `Invalid Unicode character U+FFFF`() = assertTokenization(
        input = "\uFFFF",
        expectedTokens = listOf(char('\uFFFF'))
    )

    @Test
    fun `Invalid Unicode character U+1FFFE`() = assertTokenization(
        input = "\uD83F\uDFFE",
        expectedTokens = listOf(
            char(0x1FFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+1FFFF`() = assertTokenization(
        input = "\uD83F\uDFFF",
        expectedTokens = listOf(
            char(0x1FFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+2FFFE`() = assertTokenization(
        input = "\uD87F\uDFFE",
        expectedTokens = listOf(
            char(0x2FFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+2FFFF`() = assertTokenization(
        input = "\uD87F\uDFFF",
        expectedTokens = listOf(
            char(0x2FFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+3FFFE`() = assertTokenization(
        input = "\uD8BF\uDFFE",
        expectedTokens = listOf(
            char(0x3FFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+3FFFF`() = assertTokenization(
        input = "\uD8BF\uDFFF",
        expectedTokens = listOf(
            char(0x3FFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+4FFFE`() = assertTokenization(
        input = "\uD8FF\uDFFE",
        expectedTokens = listOf(
            char(0x4FFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+4FFFF`() = assertTokenization(
        input = "\uD8FF\uDFFF",
        expectedTokens = listOf(
            char(0x4FFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+5FFFE`() = assertTokenization(
        input = "\uD93F\uDFFE",
        expectedTokens = listOf(
            char(0x5FFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+5FFFF`() = assertTokenization(
        input = "\uD93F\uDFFF",
        expectedTokens = listOf(
            char(0x5FFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+6FFFE`() = assertTokenization(
        input = "\uD97F\uDFFE",
        expectedTokens = listOf(
            char(0x6FFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+6FFFF`() = assertTokenization(
        input = "\uD97F\uDFFF",
        expectedTokens = listOf(
            char(0x6FFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+7FFFE`() = assertTokenization(
        input = "\uD9BF\uDFFE",
        expectedTokens = listOf(
            char(0x7FFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+7FFFF`() = assertTokenization(
        input = "\uD9BF\uDFFF",
        expectedTokens = listOf(
            char(0x7FFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+8FFFE`() = assertTokenization(
        input = "\uD9FF\uDFFE",
        expectedTokens = listOf(
            char(0x8FFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+8FFFF`() = assertTokenization(
        input = "\uD9FF\uDFFF",
        expectedTokens = listOf(
            char(0x8FFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+9FFFE`() = assertTokenization(
        input = "\uDA3F\uDFFE",
        expectedTokens = listOf(
            char(0x9FFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+9FFFF`() = assertTokenization(
        input = "\uDA3F\uDFFF",
        expectedTokens = listOf(
            char(0x9FFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+AFFFE`() = assertTokenization(
        input = "\uDA7F\uDFFE",
        expectedTokens = listOf(
            char(0xAFFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+AFFFF`() = assertTokenization(
        input = "\uDA7F\uDFFF",
        expectedTokens = listOf(
            char(0xAFFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+BFFFE`() = assertTokenization(
        input = "\uDABF\uDFFE",
        expectedTokens = listOf(
            char(0xBFFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+BFFFF`() = assertTokenization(
        input = "\uDABF\uDFFF",
        expectedTokens = listOf(
            char(0xBFFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+CFFFE`() = assertTokenization(
        input = "\uDAFF\uDFFE",
        expectedTokens = listOf(
            char(0xCFFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+CFFFF`() = assertTokenization(
        input = "\uDAFF\uDFFF",
        expectedTokens = listOf(
            char(0xCFFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+DFFFE`() = assertTokenization(
        input = "\uDB3F\uDFFE",
        expectedTokens = listOf(
            char(0xDFFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+DFFFF`() = assertTokenization(
        input = "\uDB3F\uDFFF",
        expectedTokens = listOf(
            char(0xDFFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+EFFFE`() = assertTokenization(
        input = "\uDB7F\uDFFE",
        expectedTokens = listOf(
            char(0xEFFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+EFFFF`() = assertTokenization(
        input = "\uDB7F\uDFFF",
        expectedTokens = listOf(
            char(0xEFFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+FFFFE`() = assertTokenization(
        input = "\uDBBF\uDFFE",
        expectedTokens = listOf(
            char(0xFFFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+FFFFF`() = assertTokenization(
        input = "\uDBBF\uDFFF",
        expectedTokens = listOf(
            char(0xFFFFF)
        )
    )

    @Test
    fun `Invalid Unicode character U+10FFFE`() = assertTokenization(
        input = "\uDBFF\uDFFE",
        expectedTokens = listOf(
            char(0x10FFFE)
        )
    )

    @Test
    fun `Invalid Unicode character U+10FFFF`() = assertTokenization(
        input = "\uDBFF\uDFFF",
        expectedTokens = listOf(
            char(0x10FFFF)
        )
    )

    @Test
    fun `Valid Unicode character U+0009`() = assertTokenization(
        input = "\u0009",
        expectedTokens = listOf(char('\u0009'))
    )

    @Test
    fun `Valid Unicode character U+000A`() = assertTokenization(
        input = "\u000A",
        expectedTokens = listOf(char('\u000A'))
    )

    @Test
    fun `Valid Unicode character U+0020`() = assertTokenization(
        input = "\u0020",
        expectedTokens = listOf(char('\u0020'))
    )

    @Test
    fun `Valid Unicode character U+0021`() = assertTokenization(
        input = "\u0021",
        expectedTokens = listOf(char('\u0021'))
    )

    @Test
    fun `Valid Unicode character U+0022`() = assertTokenization(
        input = "\u0022",
        expectedTokens = listOf(char('\u0022'))
    )

    @Test
    fun `Valid Unicode character U+0023`() = assertTokenization(
        input = "\u0023",
        expectedTokens = listOf(char('\u0023'))
    )

    @Test
    fun `Valid Unicode character U+0024`() = assertTokenization(
        input = "\u0024",
        expectedTokens = listOf(char('\u0024'))
    )

    @Test
    fun `Valid Unicode character U+0025`() = assertTokenization(
        input = "\u0025",
        expectedTokens = listOf(char('\u0025'))
    )

    @Test
    fun `Valid Unicode character U+0026`() = assertTokenization(
        input = "\u0026",
        expectedTokens = listOf(char('\u0026'))
    )

    @Test
    fun `Valid Unicode character U+0027`() = assertTokenization(
        input = "\u0027",
        expectedTokens = listOf(char('\u0027'))
    )

    @Test
    fun `Valid Unicode character U+0028`() = assertTokenization(
        input = "\u0028",
        expectedTokens = listOf(char('\u0028'))
    )

    @Test
    fun `Valid Unicode character U+0029`() = assertTokenization(
        input = "\u0029",
        expectedTokens = listOf(char('\u0029'))
    )

    @Test
    fun `Valid Unicode character U+002A`() = assertTokenization(
        input = "\u002A",
        expectedTokens = listOf(char('\u002A'))
    )

    @Test
    fun `Valid Unicode character U+002B`() = assertTokenization(
        input = "\u002B",
        expectedTokens = listOf(char('\u002B'))
    )

    @Test
    fun `Valid Unicode character U+002C`() = assertTokenization(
        input = "\u002C",
        expectedTokens = listOf(char('\u002C'))
    )

    @Test
    fun `Valid Unicode character U+002D`() = assertTokenization(
        input = "\u002D",
        expectedTokens = listOf(char('\u002D'))
    )

    @Test
    fun `Valid Unicode character U+002E`() = assertTokenization(
        input = "\u002E",
        expectedTokens = listOf(char('\u002E'))
    )

    @Test
    fun `Valid Unicode character U+002F`() = assertTokenization(
        input = "\u002F",
        expectedTokens = listOf(char('\u002F'))
    )

    @Test
    fun `Valid Unicode character U+0030`() = assertTokenization(
        input = "\u0030",
        expectedTokens = listOf(char('\u0030'))
    )

    @Test
    fun `Valid Unicode character U+0031`() = assertTokenization(
        input = "\u0031",
        expectedTokens = listOf(char('\u0031'))
    )

    @Test
    fun `Valid Unicode character U+0032`() = assertTokenization(
        input = "\u0032",
        expectedTokens = listOf(char('\u0032'))
    )

    @Test
    fun `Valid Unicode character U+0033`() = assertTokenization(
        input = "\u0033",
        expectedTokens = listOf(char('\u0033'))
    )

    @Test
    fun `Valid Unicode character U+0034`() = assertTokenization(
        input = "\u0034",
        expectedTokens = listOf(char('\u0034'))
    )

    @Test
    fun `Valid Unicode character U+0035`() = assertTokenization(
        input = "\u0035",
        expectedTokens = listOf(char('\u0035'))
    )

    @Test
    fun `Valid Unicode character U+0036`() = assertTokenization(
        input = "\u0036",
        expectedTokens = listOf(char('\u0036'))
    )

    @Test
    fun `Valid Unicode character U+0037`() = assertTokenization(
        input = "\u0037",
        expectedTokens = listOf(char('\u0037'))
    )

    @Test
    fun `Valid Unicode character U+0038`() = assertTokenization(
        input = "\u0038",
        expectedTokens = listOf(char('\u0038'))
    )

    @Test
    fun `Valid Unicode character U+0039`() = assertTokenization(
        input = "\u0039",
        expectedTokens = listOf(char('\u0039'))
    )

    @Test
    fun `Valid Unicode character U+003A`() = assertTokenization(
        input = "\u003A",
        expectedTokens = listOf(char('\u003A'))
    )

    @Test
    fun `Valid Unicode character U+003B`() = assertTokenization(
        input = "\u003B",
        expectedTokens = listOf(char('\u003B'))
    )

    @Test
    fun `Valid Unicode character U+003D`() = assertTokenization(
        input = "\u003D",
        expectedTokens = listOf(char('\u003D'))
    )

    @Test
    fun `Valid Unicode character U+003E`() = assertTokenization(
        input = "\u003E",
        expectedTokens = listOf(char('\u003E'))
    )

    @Test
    fun `Valid Unicode character U+003F`() = assertTokenization(
        input = "\u003F",
        expectedTokens = listOf(char('\u003F'))
    )

    @Test
    fun `Valid Unicode character U+0040`() = assertTokenization(
        input = "\u0040",
        expectedTokens = listOf(char('\u0040'))
    )

    @Test
    fun `Valid Unicode character U+0041`() = assertTokenization(
        input = "\u0041",
        expectedTokens = listOf(char('\u0041'))
    )

    @Test
    fun `Valid Unicode character U+0042`() = assertTokenization(
        input = "\u0042",
        expectedTokens = listOf(char('\u0042'))
    )

    @Test
    fun `Valid Unicode character U+0043`() = assertTokenization(
        input = "\u0043",
        expectedTokens = listOf(
            char('\u0043')
        )
    )

    @Test
    fun `Valid Unicode character U+0044`() = assertTokenization(
        input = "\u0044",
        expectedTokens = listOf(
            char('\u0044')
        )
    )

    @Test
    fun `Valid Unicode character U+0045`() = assertTokenization(
        input = "\u0045",
        expectedTokens = listOf(
            char('\u0045')
        )
    )

    @Test
    fun `Valid Unicode character U+0046`() = assertTokenization(
        input = "\u0046",
        expectedTokens = listOf(
            char('\u0046')
        )
    )

    @Test
    fun `Valid Unicode character U+0047`() = assertTokenization(
        input = "\u0047",
        expectedTokens = listOf(
            char('\u0047')
        )
    )

    @Test
    fun `Valid Unicode character U+0048`() = assertTokenization(
        input = "\u0048",
        expectedTokens = listOf(
            char('\u0048')
        )
    )

    @Test
    fun `Valid Unicode character U+0049`() = assertTokenization(
        input = "\u0049",
        expectedTokens = listOf(
            char('\u0049')
        )
    )

    @Test
    fun `Valid Unicode character U+004A`() = assertTokenization(
        input = "\u004A",
        expectedTokens = listOf(
            char('\u004A')
        )
    )

    @Test
    fun `Valid Unicode character U+004B`() = assertTokenization(
        input = "\u004B",
        expectedTokens = listOf(
            char('\u004B')
        )
    )

    @Test
    fun `Valid Unicode character U+004C`() = assertTokenization(
        input = "\u004C",
        expectedTokens = listOf(
            char('\u004C')
        )
    )

    @Test
    fun `Valid Unicode character U+004D`() = assertTokenization(
        input = "\u004D",
        expectedTokens = listOf(
            char('\u004D')
        )
    )

    @Test
    fun `Valid Unicode character U+004E`() = assertTokenization(
        input = "\u004E",
        expectedTokens = listOf(
            char('\u004E')
        )
    )

    @Test
    fun `Valid Unicode character U+004F`() = assertTokenization(
        input = "\u004F",
        expectedTokens = listOf(
            char('\u004F')
        )
    )

    @Test
    fun `Valid Unicode character U+0050`() = assertTokenization(
        input = "\u0050",
        expectedTokens = listOf(
            char('\u0050')
        )
    )

    @Test
    fun `Valid Unicode character U+0051`() = assertTokenization(
        input = "\u0051",
        expectedTokens = listOf(
            char('\u0051')
        )
    )

    @Test
    fun `Valid Unicode character U+0052`() = assertTokenization(
        input = "\u0052",
        expectedTokens = listOf(
            char('\u0052')
        )
    )

    @Test
    fun `Valid Unicode character U+0053`() = assertTokenization(
        input = "\u0053",
        expectedTokens = listOf(
            char('\u0053')
        )
    )

    @Test
    fun `Valid Unicode character U+0054`() = assertTokenization(
        input = "\u0054",
        expectedTokens = listOf(
            char('\u0054')
        )
    )

    @Test
    fun `Valid Unicode character U+0055`() = assertTokenization(
        input = "\u0055",
        expectedTokens = listOf(
            char('\u0055')
        )
    )

    @Test
    fun `Valid Unicode character U+0056`() = assertTokenization(
        input = "\u0056",
        expectedTokens = listOf(
            char('\u0056')
        )
    )

    @Test
    fun `Valid Unicode character U+0057`() = assertTokenization(
        input = "\u0057",
        expectedTokens = listOf(
            char('\u0057')
        )
    )

    @Test
    fun `Valid Unicode character U+0058`() = assertTokenization(
        input = "\u0058",
        expectedTokens = listOf(
            char('\u0058')
        )
    )

    @Test
    fun `Valid Unicode character U+0059`() = assertTokenization(
        input = "\u0059",
        expectedTokens = listOf(
            char('\u0059')
        )
    )

    @Test
    fun `Valid Unicode character U+005A`() = assertTokenization(
        input = "\u005A",
        expectedTokens = listOf(
            char('\u005A')
        )
    )

    @Test
    fun `Valid Unicode character U+005B`() = assertTokenization(
        input = "\u005B",
        expectedTokens = listOf(
            char('\u005B')
        )
    )

    @Test
    fun `Valid Unicode character U+005C`() = assertTokenization(
        input = "\u005C",
        expectedTokens = listOf(
            char('\u005C')
        )
    )

    @Test
    fun `Valid Unicode character U+005D`() = assertTokenization(
        input = "\u005D",
        expectedTokens = listOf(
            char('\u005D')
        )
    )

    @Test
    fun `Valid Unicode character U+005E`() = assertTokenization(
        input = "\u005E",
        expectedTokens = listOf(
            char('\u005E')
        )
    )

    @Test
    fun `Valid Unicode character U+005F`() = assertTokenization(
        input = "\u005F",
        expectedTokens = listOf(
            char('\u005F')
        )
    )

    @Test
    fun `Valid Unicode character U+0060`() = assertTokenization(
        input = "\u0060",
        expectedTokens = listOf(
            char('\u0060')
        )
    )

    @Test
    fun `Valid Unicode character U+0061`() = assertTokenization(
        input = "\u0061",
        expectedTokens = listOf(
            char('\u0061')
        )
    )

    @Test
    fun `Valid Unicode character U+0062`() = assertTokenization(
        input = "\u0062",
        expectedTokens = listOf(
            char('\u0062')
        )
    )

    @Test
    fun `Valid Unicode character U+0063`() = assertTokenization(
        input = "\u0063",
        expectedTokens = listOf(
            char('\u0063')
        )
    )

    @Test
    fun `Valid Unicode character U+0064`() = assertTokenization(
        input = "\u0064",
        expectedTokens = listOf(
            char('\u0064')
        )
    )

    @Test
    fun `Valid Unicode character U+0065`() = assertTokenization(
        input = "\u0065",
        expectedTokens = listOf(
            char('\u0065')
        )
    )

    @Test
    fun `Valid Unicode character U+0066`() = assertTokenization(
        input = "\u0066",
        expectedTokens = listOf(
            char('\u0066')
        )
    )

    @Test
    fun `Valid Unicode character U+0067`() = assertTokenization(
        input = "\u0067",
        expectedTokens = listOf(
            char('\u0067')
        )
    )

    @Test
    fun `Valid Unicode character U+0068`() = assertTokenization(
        input = "\u0068",
        expectedTokens = listOf(
            char('\u0068')
        )
    )

    @Test
    fun `Valid Unicode character U+0069`() = assertTokenization(
        input = "\u0069",
        expectedTokens = listOf(
            char('\u0069')
        )
    )

    @Test
    fun `Valid Unicode character U+006A`() = assertTokenization(
        input = "\u006A",
        expectedTokens = listOf(
            char('\u006A')
        )
    )

    @Test
    fun `Valid Unicode character U+006B`() = assertTokenization(
        input = "\u006B",
        expectedTokens = listOf(
            char('\u006B')
        )
    )

    @Test
    fun `Valid Unicode character U+006C`() = assertTokenization(
        input = "\u006C",
        expectedTokens = listOf(
            char('\u006C')
        )
    )

    @Test
    fun `Valid Unicode character U+006D`() = assertTokenization(
        input = "\u006D",
        expectedTokens = listOf(
            char('\u006D')
        )
    )

    @Test
    fun `Valid Unicode character U+006E`() = assertTokenization(
        input = "\u006E",
        expectedTokens = listOf(
            char('\u006E')
        )
    )

    @Test
    fun `Valid Unicode character U+006F`() = assertTokenization(
        input = "\u006F",
        expectedTokens = listOf(
            char('\u006F')
        )
    )

    @Test
    fun `Valid Unicode character U+0070`() = assertTokenization(
        input = "\u0070",
        expectedTokens = listOf(
            char('\u0070')
        )
    )

    @Test
    fun `Valid Unicode character U+0071`() = assertTokenization(
        input = "\u0071",
        expectedTokens = listOf(
            char('\u0071')
        )
    )

    @Test
    fun `Valid Unicode character U+0072`() = assertTokenization(
        input = "\u0072",
        expectedTokens = listOf(
            char('\u0072')
        )
    )

    @Test
    fun `Valid Unicode character U+0073`() = assertTokenization(
        input = "\u0073",
        expectedTokens = listOf(
            char('\u0073')
        )
    )

    @Test
    fun `Valid Unicode character U+0074`() = assertTokenization(
        input = "\u0074",
        expectedTokens = listOf(
            char('\u0074')
        )
    )

    @Test
    fun `Valid Unicode character U+0075`() = assertTokenization(
        input = "\u0075",
        expectedTokens = listOf(
            char('\u0075')
        )
    )

    @Test
    fun `Valid Unicode character U+0076`() = assertTokenization(
        input = "\u0076",
        expectedTokens = listOf(
            char('\u0076')
        )
    )

    @Test
    fun `Valid Unicode character U+0077`() = assertTokenization(
        input = "\u0077",
        expectedTokens = listOf(
            char('\u0077')
        )
    )

    @Test
    fun `Valid Unicode character U+0078`() = assertTokenization(
        input = "\u0078",
        expectedTokens = listOf(
            char('\u0078')
        )
    )

    @Test
    fun `Valid Unicode character U+0079`() = assertTokenization(
        input = "\u0079",
        expectedTokens = listOf(
            char('\u0079')
        )
    )

    @Test
    fun `Valid Unicode character U+007A`() = assertTokenization(
        input = "\u007A",
        expectedTokens = listOf(
            char('\u007A')
        )
    )

    @Test
    fun `Valid Unicode character U+007B`() = assertTokenization(
        input = "\u007B",
        expectedTokens = listOf(
            char('\u007B')
        )
    )

    @Test
    fun `Valid Unicode character U+007C`() = assertTokenization(
        input = "\u007C",
        expectedTokens = listOf(
            char('\u007C')
        )
    )

    @Test
    fun `Valid Unicode character U+007D`() = assertTokenization(
        input = "\u007D",
        expectedTokens = listOf(
            char('\u007D')
        )
    )

    @Test
    fun `Valid Unicode character U+007E`() = assertTokenization(
        input = "\u007E",
        expectedTokens = listOf(
            char('\u007E')
        )
    )

    @Test
    fun `Valid Unicode character U+00A0`() = assertTokenization(
        input = "\u00A0",
        expectedTokens = listOf(
            char('\u00A0')
        )
    )

    @Test
    fun `Valid Unicode character U+00A1`() = assertTokenization(
        input = "\u00A1",
        expectedTokens = listOf(
            char('\u00A1')
        )
    )

    @Test
    fun `Valid Unicode character U+00A2`() = assertTokenization(
        input = "\u00A2",
        expectedTokens = listOf(
            char('\u00A2')
        )
    )

    @Test
    fun `Valid Unicode character U+00A3`() = assertTokenization(
        input = "\u00A3",
        expectedTokens = listOf(
            char('\u00A3')
        )
    )

    @Test
    fun `Valid Unicode character U+00A4`() = assertTokenization(
        input = "\u00A4",
        expectedTokens = listOf(
            char('\u00A4')
        )
    )

    @Test
    fun `Valid Unicode character U+00A5`() = assertTokenization(
        input = "\u00A5",
        expectedTokens = listOf(
            char('\u00A5')
        )
    )

    @Test
    fun `Valid Unicode character U+00A6`() = assertTokenization(
        input = "\u00A6",
        expectedTokens = listOf(
            char('\u00A6')
        )
    )

    @Test
    fun `Valid Unicode character U+00A7`() = assertTokenization(
        input = "\u00A7",
        expectedTokens = listOf(
            char('\u00A7')
        )
    )

    @Test
    fun `Valid Unicode character U+00A8`() = assertTokenization(
        input = "\u00A8",
        expectedTokens = listOf(
            char('\u00A8')
        )
    )

    @Test
    fun `Valid Unicode character U+00A9`() = assertTokenization(
        input = "\u00A9",
        expectedTokens = listOf(
            char('\u00A9')
        )
    )

    @Test
    fun `Valid Unicode character U+00AA`() = assertTokenization(
        input = "\u00AA",
        expectedTokens = listOf(
            char('\u00AA')
        )
    )

    @Test
    fun `Valid Unicode character U+00AB`() = assertTokenization(
        input = "\u00AB",
        expectedTokens = listOf(
            char('\u00AB')
        )
    )

    @Test
    fun `Valid Unicode character U+00AC`() = assertTokenization(
        input = "\u00AC",
        expectedTokens = listOf(
            char('\u00AC')
        )
    )

    @Test
    fun `Valid Unicode character U+00AD`() = assertTokenization(
        input = "\u00AD",
        expectedTokens = listOf(
            char('\u00AD')
        )
    )

    @Test
    fun `Valid Unicode character U+00AE`() = assertTokenization(
        input = "\u00AE",
        expectedTokens = listOf(
            char('\u00AE')
        )
    )

    @Test
    fun `Valid Unicode character U+00AF`() = assertTokenization(
        input = "\u00AF",
        expectedTokens = listOf(
            char('\u00AF')
        )
    )

    @Test
    fun `Valid Unicode character U+00B0`() = assertTokenization(
        input = "\u00B0",
        expectedTokens = listOf(
            char('\u00B0')
        )
    )

    @Test
    fun `Valid Unicode character U+00B1`() = assertTokenization(
        input = "\u00B1",
        expectedTokens = listOf(
            char('\u00B1')
        )
    )

    @Test
    fun `Valid Unicode character U+00B2`() = assertTokenization(
        input = "\u00B2",
        expectedTokens = listOf(
            char('\u00B2')
        )
    )

    @Test
    fun `Valid Unicode character U+00B3`() = assertTokenization(
        input = "\u00B3",
        expectedTokens = listOf(
            char('\u00B3')
        )
    )

    @Test
    fun `Valid Unicode character U+00B4`() = assertTokenization(
        input = "\u00B4",
        expectedTokens = listOf(
            char('\u00B4')
        )
    )

    @Test
    fun `Valid Unicode character U+00B5`() = assertTokenization(
        input = "\u00B5",
        expectedTokens = listOf(
            char('\u00B5')
        )
    )

    @Test
    fun `Valid Unicode character U+00B6`() = assertTokenization(
        input = "\u00B6",
        expectedTokens = listOf(
            char('\u00B6')
        )
    )

    @Test
    fun `Valid Unicode character U+00B7`() = assertTokenization(
        input = "\u00B7",
        expectedTokens = listOf(
            char('\u00B7')
        )
    )

    @Test
    fun `Valid Unicode character U+00B8`() = assertTokenization(
        input = "\u00B8",
        expectedTokens = listOf(
            char('\u00B8')
        )
    )

    @Test
    fun `Valid Unicode character U+00B9`() = assertTokenization(
        input = "\u00B9",
        expectedTokens = listOf(
            char('\u00B9')
        )
    )

    @Test
    fun `Valid Unicode character U+00BA`() = assertTokenization(
        input = "\u00BA",
        expectedTokens = listOf(
            char('\u00BA')
        )
    )

    @Test
    fun `Valid Unicode character U+00BB`() = assertTokenization(
        input = "\u00BB",
        expectedTokens = listOf(
            char('\u00BB')
        )
    )

    @Test
    fun `Valid Unicode character U+00BC`() = assertTokenization(
        input = "\u00BC",
        expectedTokens = listOf(
            char('\u00BC')
        )
    )

    @Test
    fun `Valid Unicode character U+00BD`() = assertTokenization(
        input = "\u00BD",
        expectedTokens = listOf(
            char('\u00BD')
        )
    )

    @Test
    fun `Valid Unicode character U+00BE`() = assertTokenization(
        input = "\u00BE",
        expectedTokens = listOf(
            char('\u00BE')
        )
    )

    @Test
    fun `Valid Unicode character U+00BF`() = assertTokenization(
        input = "\u00BF",
        expectedTokens = listOf(
            char('\u00BF')
        )
    )

    @Test
    fun `Valid Unicode character U+00C0`() = assertTokenization(
        input = "\u00C0",
        expectedTokens = listOf(
            char('\u00C0')
        )
    )

    @Test
    fun `Valid Unicode character U+00C1`() = assertTokenization(
        input = "\u00C1",
        expectedTokens = listOf(
            char('\u00C1')
        )
    )

    @Test
    fun `Valid Unicode character U+00C2`() = assertTokenization(
        input = "\u00C2",
        expectedTokens = listOf(
            char('\u00C2')
        )
    )

    @Test
    fun `Valid Unicode character U+00C3`() = assertTokenization(
        input = "\u00C3",
        expectedTokens = listOf(
            char('\u00C3')
        )
    )

    @Test
    fun `Valid Unicode character U+00C4`() = assertTokenization(
        input = "\u00C4",
        expectedTokens = listOf(
            char('\u00C4')
        )
    )

    @Test
    fun `Valid Unicode character U+00C5`() = assertTokenization(
        input = "\u00C5",
        expectedTokens = listOf(
            char('\u00C5')
        )
    )

    @Test
    fun `Valid Unicode character U+00C6`() = assertTokenization(
        input = "\u00C6",
        expectedTokens = listOf(
            char('\u00C6')
        )
    )

    @Test
    fun `Valid Unicode character U+00C7`() = assertTokenization(
        input = "\u00C7",
        expectedTokens = listOf(
            char('\u00C7')
        )
    )

    @Test
    fun `Valid Unicode character U+00C8`() = assertTokenization(
        input = "\u00C8",
        expectedTokens = listOf(
            char('\u00C8')
        )
    )

    @Test
    fun `Valid Unicode character U+00C9`() = assertTokenization(
        input = "\u00C9",
        expectedTokens = listOf(
            char('\u00C9')
        )
    )

    @Test
    fun `Valid Unicode character U+00CA`() = assertTokenization(
        input = "\u00CA",
        expectedTokens = listOf(
            char('\u00CA')
        )
    )

    @Test
    fun `Valid Unicode character U+00CB`() = assertTokenization(
        input = "\u00CB",
        expectedTokens = listOf(
            char('\u00CB')
        )
    )

    @Test
    fun `Valid Unicode character U+00CC`() = assertTokenization(
        input = "\u00CC",
        expectedTokens = listOf(
            char('\u00CC')
        )
    )

    @Test
    fun `Valid Unicode character U+00CD`() = assertTokenization(
        input = "\u00CD",
        expectedTokens = listOf(
            char('\u00CD')
        )
    )

    @Test
    fun `Valid Unicode character U+00CE`() = assertTokenization(
        input = "\u00CE",
        expectedTokens = listOf(
            char('\u00CE')
        )
    )

    @Test
    fun `Valid Unicode character U+00CF`() = assertTokenization(
        input = "\u00CF",
        expectedTokens = listOf(
            char('\u00CF')
        )
    )

    @Test
    fun `Valid Unicode character U+00D0`() = assertTokenization(
        input = "\u00D0",
        expectedTokens = listOf(
            char('\u00D0')
        )
    )

    @Test
    fun `Valid Unicode character U+00D1`() = assertTokenization(
        input = "\u00D1",
        expectedTokens = listOf(
            char('\u00D1')
        )
    )

    @Test
    fun `Valid Unicode character U+00D2`() = assertTokenization(
        input = "\u00D2",
        expectedTokens = listOf(
            char('\u00D2')
        )
    )

    @Test
    fun `Valid Unicode character U+00D3`() = assertTokenization(
        input = "\u00D3",
        expectedTokens = listOf(
            char('\u00D3')
        )
    )

    @Test
    fun `Valid Unicode character U+00D4`() = assertTokenization(
        input = "\u00D4",
        expectedTokens = listOf(
            char('\u00D4')
        )
    )

    @Test
    fun `Valid Unicode character U+00D5`() = assertTokenization(
        input = "\u00D5",
        expectedTokens = listOf(
            char('\u00D5')
        )
    )

    @Test
    fun `Valid Unicode character U+00D6`() = assertTokenization(
        input = "\u00D6",
        expectedTokens = listOf(
            char('\u00D6')
        )
    )

    @Test
    fun `Valid Unicode character U+00D7`() = assertTokenization(
        input = "\u00D7",
        expectedTokens = listOf(
            char('\u00D7')
        )
    )

    @Test
    fun `Valid Unicode character U+00D8`() = assertTokenization(
        input = "\u00D8",
        expectedTokens = listOf(
            char('\u00D8')
        )
    )

    @Test
    fun `Valid Unicode character U+00D9`() = assertTokenization(
        input = "\u00D9",
        expectedTokens = listOf(
            char('\u00D9')
        )
    )

    @Test
    fun `Valid Unicode character U+00DA`() = assertTokenization(
        input = "\u00DA",
        expectedTokens = listOf(
            char('\u00DA')
        )
    )

    @Test
    fun `Valid Unicode character U+00DB`() = assertTokenization(
        input = "\u00DB",
        expectedTokens = listOf(
            char('\u00DB')
        )
    )

    @Test
    fun `Valid Unicode character U+00DC`() = assertTokenization(
        input = "\u00DC",
        expectedTokens = listOf(
            char('\u00DC')
        )
    )

    @Test
    fun `Valid Unicode character U+00DD`() = assertTokenization(
        input = "\u00DD",
        expectedTokens = listOf(
            char('\u00DD')
        )
    )

    @Test
    fun `Valid Unicode character U+00DE`() = assertTokenization(
        input = "\u00DE",
        expectedTokens = listOf(
            char('\u00DE')
        )
    )

    @Test
    fun `Valid Unicode character U+00DF`() = assertTokenization(
        input = "\u00DF",
        expectedTokens = listOf(
            char('\u00DF')
        )
    )

    @Test
    fun `Valid Unicode character U+00E0`() = assertTokenization(
        input = "\u00E0",
        expectedTokens = listOf(
            char('\u00E0')
        )
    )

    @Test
    fun `Valid Unicode character U+00E1`() = assertTokenization(
        input = "\u00E1",
        expectedTokens = listOf(
            char('\u00E1')
        )
    )

    @Test
    fun `Valid Unicode character U+00E2`() = assertTokenization(
        input = "\u00E2",
        expectedTokens = listOf(
            char('\u00E2')
        )
    )

    @Test
    fun `Valid Unicode character U+00E3`() = assertTokenization(
        input = "\u00E3",
        expectedTokens = listOf(
            char('\u00E3')
        )
    )

    @Test
    fun `Valid Unicode character U+00E4`() = assertTokenization(
        input = "\u00E4",
        expectedTokens = listOf(
            char('\u00E4')
        )
    )

    @Test
    fun `Valid Unicode character U+00E5`() = assertTokenization(
        input = "\u00E5",
        expectedTokens = listOf(
            char('\u00E5')
        )
    )

    @Test
    fun `Valid Unicode character U+00E6`() = assertTokenization(
        input = "\u00E6",
        expectedTokens = listOf(
            char('\u00E6')
        )
    )

    @Test
    fun `Valid Unicode character U+00E7`() = assertTokenization(
        input = "\u00E7",
        expectedTokens = listOf(
            char('\u00E7')
        )
    )

    @Test
    fun `Valid Unicode character U+00E8`() = assertTokenization(
        input = "\u00E8",
        expectedTokens = listOf(
            char('\u00E8')
        )
    )

    @Test
    fun `Valid Unicode character U+00E9`() = assertTokenization(
        input = "\u00E9",
        expectedTokens = listOf(
            char('\u00E9')
        )
    )

    @Test
    fun `Valid Unicode character U+00EA`() = assertTokenization(
        input = "\u00EA",
        expectedTokens = listOf(
            char('\u00EA')
        )
    )

    @Test
    fun `Valid Unicode character U+00EB`() = assertTokenization(
        input = "\u00EB",
        expectedTokens = listOf(
            char('\u00EB')
        )
    )

    @Test
    fun `Valid Unicode character U+00EC`() = assertTokenization(
        input = "\u00EC",
        expectedTokens = listOf(
            char('\u00EC')
        )
    )

    @Test
    fun `Valid Unicode character U+00ED`() = assertTokenization(
        input = "\u00ED",
        expectedTokens = listOf(
            char('\u00ED'),
        ),
    )

    @Test
    fun `Valid Unicode character U+00EE`() = assertTokenization(
        input = "\u00EE",
        expectedTokens = listOf(
            char('\u00EE'),
        ),
    )

    @Test
    fun `Valid Unicode character U+00EF`() = assertTokenization(
        input = "\u00EF",
        expectedTokens = listOf(
            char('\u00EF'),
        ),
    )

    @Test
    fun `Valid Unicode character U+00F0`() = assertTokenization(
        input = "\u00F0",
        expectedTokens = listOf(
            char('\u00F0'),
        ),
    )

    @Test
    fun `Valid Unicode character U+00F1`() = assertTokenization(
        input = "\u00F1",
        expectedTokens = listOf(
            char('\u00F1'),
        ),
    )

    @Test
    fun `Valid Unicode character U+00F2`() = assertTokenization(
        input = "\u00F2",
        expectedTokens = listOf(
            char('\u00F2'),
        ),
    )

    @Test
    fun `Valid Unicode character U+00F3`() = assertTokenization(
        input = "\u00F3",
        expectedTokens = listOf(
            char('\u00F3'),
        ),
    )

    @Test
    fun `Valid Unicode character U+00F4`() = assertTokenization(
        input = "\u00F4",
        expectedTokens = listOf(
            char('\u00F4'),
        ),
    )

    @Test
    fun `Valid Unicode character U+00F5`() = assertTokenization(
        input = "\u00F5",
        expectedTokens = listOf(
            char('\u00F5'),
        ),
    )

    @Test
    fun `Valid Unicode character U+00F6`() = assertTokenization(
        input = "\u00F6",
        expectedTokens = listOf(
            char('\u00F6'),
        ),
    )

    @Test
    fun `Valid Unicode character U+00F7`() = assertTokenization(
        input = "\u00F7",
        expectedTokens = listOf(
            char('\u00F7'),
        ),
    )

    @Test
    fun `Valid Unicode character U+00F8`() = assertTokenization(
        input = "\u00F8",
        expectedTokens = listOf(
            char('\u00F8'),
        ),
    )

    @Test
    fun `Valid Unicode character U+00F9`() = assertTokenization(
        input = "\u00F9",
        expectedTokens = listOf(
            char('\u00F9'),
        ),
    )

    @Test
    fun `Valid Unicode character U+00FA`() = assertTokenization(
        input = "\u00FA",
        expectedTokens = listOf(char('\u00FA')),
    )

    @Test
    fun `Valid Unicode character U+00FB`() = assertTokenization(
        input = "\u00FB",
        expectedTokens = listOf(char('\u00FB')),
    )

    @Test
    fun `Valid Unicode character U+00FC`() = assertTokenization(
        input = "\u00FC",
        expectedTokens = listOf(char('\u00FC')),
    )

    @Test
    fun `Valid Unicode character U+00FD`() = assertTokenization(
        input = "\u00FD",
        expectedTokens = listOf(char('\u00FD')),
    )

    @Test
    fun `Valid Unicode character U+00FE`() = assertTokenization(
        input = "\u00FE",
        expectedTokens = listOf(char('\u00FE')),
    )

    @Test
    fun `Valid Unicode character U+00FF`() = assertTokenization(
        input = "\u00FF",
        expectedTokens = listOf(char('\u00FF')),
    )

    @Test
    fun `Valid Unicode character U+D7FF`() = assertTokenization(
        input = "\uD7FF",
        expectedTokens = listOf(char('\uD7FF')),
    )

    @Test
    fun `Valid Unicode character U+E000`() = assertTokenization(
        input = "\uE000",
        expectedTokens = listOf(char('\uE000')),
    )

    @Test
    fun `Valid Unicode character U+FDCF`() = assertTokenization(
        input = "\uFDCF",
        expectedTokens = listOf(char('\uFDCF')),
    )

    @Test
    fun `Valid Unicode character U+FDF0`() = assertTokenization(
        input = "\uFDF0",
        expectedTokens = listOf(char('\uFDF0')),
    )

    @Test
    fun `Valid Unicode character U+FFFD`() = assertTokenization(
        input = "\uFFFD",
        expectedTokens = listOf(char('\uFFFD'))
    )

    @Test
    fun `Valid Unicode character U+10000`() = assertTokenization(
        input = "\uD800\uDC00",
        expectedTokens = listOf(
            char(0x10000),
        ),
    )

    @Test
    fun `Valid Unicode character U+1FFFD`() = assertTokenization(
        input = "\uD83F\uDFFD",
        expectedTokens = listOf(
            char(0x1FFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+20000`() = assertTokenization(
        input = "\uD840\uDC00",
        expectedTokens = listOf(
            char(0x20000),
        ),
    )

    @Test
    fun `Valid Unicode character U+2FFFD`() = assertTokenization(
        input = "\uD87F\uDFFD",
        expectedTokens = listOf(
            char(0x2FFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+30000`() = assertTokenization(
        input = "\uD880\uDC00",
        expectedTokens = listOf(
            char(0x30000),
        ),
    )

    @Test
    fun `Valid Unicode character U+3FFFD`() = assertTokenization(
        input = "\uD8BF\uDFFD",
        expectedTokens = listOf(
            char(0x3FFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+40000`() = assertTokenization(
        input = "\uD8C0\uDC00",
        expectedTokens = listOf(
            char(0x40000),
        ),
    )

    @Test
    fun `Valid Unicode character U+4FFFD`() = assertTokenization(
        input = "\uD8FF\uDFFD",
        expectedTokens = listOf(
            char(0x4FFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+50000`() = assertTokenization(
        input = "\uD900\uDC00",
        expectedTokens = listOf(
            char(0x50000),
        ),
    )

    @Test
    fun `Valid Unicode character U+5FFFD`() = assertTokenization(
        input = "\uD93F\uDFFD",
        expectedTokens = listOf(
            char(0x5FFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+60000`() = assertTokenization(
        input = "\uD940\uDC00",
        expectedTokens = listOf(
            char(0x60000),
        ),
    )

    @Test
    fun `Valid Unicode character U+6FFFD`() = assertTokenization(
        input = "\uD97F\uDFFD",
        expectedTokens = listOf(
            char(0x6FFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+70000`() = assertTokenization(
        input = "\uD980\uDC00",
        expectedTokens = listOf(
            char(0x70000),
        ),
    )

    @Test
    fun `Valid Unicode character U+7FFFD`() = assertTokenization(
        input = "\uD9BF\uDFFD",
        expectedTokens = listOf(
            char(0x7FFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+80000`() = assertTokenization(
        input = "\uD9C0\uDC00",
        expectedTokens = listOf(
            char(0x80000),
        ),
    )

    @Test
    fun `Valid Unicode character U+8FFFD`() = assertTokenization(
        input = "\uD9FF\uDFFD",
        expectedTokens = listOf(
            char(0x8FFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+90000`() = assertTokenization(
        input = "\uDA00\uDC00",
        expectedTokens = listOf(
            char(0x90000),
        ),
    )

    @Test
    fun `Valid Unicode character U+9FFFD`() = assertTokenization(
        input = "\uDA3F\uDFFD",
        expectedTokens = listOf(
            char(0x9FFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+A0000`() = assertTokenization(
        input = "\uDA40\uDC00",
        expectedTokens = listOf(
            char(0xA0000),
        ),
    )

    @Test
    fun `Valid Unicode character U+AFFFD`() = assertTokenization(
        input = "\uDA7F\uDFFD",
        expectedTokens = listOf(
            char(0xAFFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+B0000`() = assertTokenization(
        input = "\uDA80\uDC00",
        expectedTokens = listOf(
            char(0xB0000),
        ),
    )

    @Test
    fun `Valid Unicode character U+BFFFD`() = assertTokenization(
        input = "\uDABF\uDFFD",
        expectedTokens = listOf(
            char(0xBFFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+C0000`() = assertTokenization(
        input = "\uDAC0\uDC00",
        expectedTokens = listOf(
            char(0xC0000),
        ),
    )

    @Test
    fun `Valid Unicode character U+CFFFD`() = assertTokenization(
        input = "\uDAFF\uDFFD",
        expectedTokens = listOf(
            char(0xCFFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+D0000`() = assertTokenization(
        input = "\uDB00\uDC00",
        expectedTokens = listOf(
            char(0xD0000),
        ),
    )

    @Test
    fun `Valid Unicode character U+DFFFD`() = assertTokenization(
        input = "\uDB3F\uDFFD",
        expectedTokens = listOf(
            char(0xDFFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+E0000`() = assertTokenization(
        input = "\uDB40\uDC00",
        expectedTokens = listOf(
            char(0xE0000),
        ),
    )

    @Test
    fun `Valid Unicode character U+EFFFD`() = assertTokenization(
        input = "\uDB7F\uDFFD",
        expectedTokens = listOf(
            char(0xEFFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+F0000`() = assertTokenization(
        input = "\uDB80\uDC00",
        expectedTokens = listOf(
            char(0xF0000),
        ),
    )

    @Test
    fun `Valid Unicode character U+FFFFD`() = assertTokenization(
        input = "\uDBBF\uDFFD",
        expectedTokens = listOf(
            char(0xFFFFD),
        ),
    )

    @Test
    fun `Valid Unicode character U+100000`() = assertTokenization(
        input = "\uDBC0\uDC00",
        expectedTokens = listOf(
            char(0x100000),
        ),
    )

    @Test
    fun `Valid Unicode character U+10FFFD`() = assertTokenization(
        input = "\uDBFF\uDFFD",
        expectedTokens = listOf(
            char(0x10FFFD),
        ),
    )
}