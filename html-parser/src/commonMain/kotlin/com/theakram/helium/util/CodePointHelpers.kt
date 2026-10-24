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

package com.theakram.helium.util

internal fun Int.isAsciiDigit() = this in Chars.Zero.char.code..Chars.Nine.char.code

internal fun Int.isAsciiUpperHexDigit() =
    isAsciiDigit() || this in Chars.UpperA.char.code..Chars.UpperF.char.code

internal fun Int.isAsciiLowerHexDigit() =
    isAsciiDigit() || this in Chars.LowerA.char.code..Chars.LowerF.char.code

internal fun Int.isAsciiHexDigit() = isAsciiUpperHexDigit() || isAsciiLowerHexDigit()

internal fun Int.isAsciiUpperAlpha() = this in Chars.UpperA.char.code..Chars.UpperZ.char.code

internal fun Int.isAsciiLowerAlpha() = this in Chars.LowerA.char.code..Chars.LowerZ.char.code

internal fun Int.isAsciiAlpha() = isAsciiUpperAlpha() || isAsciiLowerAlpha()

internal fun Int.isAsciiAlphaNumeric() = isAsciiAlpha() || isAsciiDigit()

internal fun Int.leadingSurrogate() = this in 0xD800..0xDBFF

internal fun Int.trailingSurrogate() = this in 0xDC00..0xDFFF

internal fun Int.isSurrogate() = leadingSurrogate() || trailingSurrogate()

internal fun Int.isScalarValue() = isSurrogate().not()

internal fun Int.isNonCharacter() = this in 0xFDD0..0xFDEF
        || this and 0xFFFE == 0xFFFE
        || this and 0xFFFF == 0xFFFF

internal fun Int.isAsciiWhitespace() = this == Chars.Space.char.code
        || isWhiteSpace()
        || this == Chars.CarriageReturn.char.code

internal fun Int.isWhiteSpace(): Boolean {
    return this == Chars.Space.char.code
            || this == Chars.Tab.char.code
            || this == Chars.LineFeed.char.code
            || this == Chars.FormFeed.char.code
}

internal fun Int.isNull() = this == Chars.Null.char.code