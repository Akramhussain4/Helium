package com.hussain.csoup.util

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