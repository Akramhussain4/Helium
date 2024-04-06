package com.hussain.csoup.util


internal fun Int.isAsciiDigit() = this in CharCodes.Zero.code..CharCodes.Nine.code

internal fun Int.isAsciiUpperHexDigit() = this in CharCodes.UpperA.code..CharCodes.UpperF.code

internal fun Int.isAsciiLowerHexDigit() = this in CharCodes.LowerA.code..CharCodes.LowerF.code

internal fun Int.isAsciiHexDigit() = isAsciiUpperHexDigit() || isAsciiLowerHexDigit()

internal fun Int.isAsciiUpperAlpha() = this in CharCodes.UpperA.code..CharCodes.UpperZ.code

internal fun Int.isAsciiLowerAlpha() = this in CharCodes.LowerA.code..CharCodes.LowerZ.code

internal fun Int.isAsciiAlpha() = isAsciiUpperAlpha() || isAsciiLowerAlpha()

internal fun Int.isAsciiAlphaNumeric() = isAsciiAlpha() || isAsciiDigit()

internal fun Int.leadingSurrogate() = this in 0xD800..0xDBFF

internal fun Int.trailingSurrogate() = this in 0xDC00..0xDFFF

internal fun Int.isSurrogate() = leadingSurrogate() || trailingSurrogate()

internal fun Int.isScalarValue() = isSurrogate().not()

internal fun Int.isNonCharacter() = this in 0xFDD0..0xFDEF
        || this and 0xFFFE == 0xFFFE
        || this and 0xFFFF == 0xFFFF