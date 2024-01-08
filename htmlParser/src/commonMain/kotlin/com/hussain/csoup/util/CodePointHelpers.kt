package com.hussain.csoup.util


internal fun Int.isAsciiDigit(): Boolean {
    return this in CharCodes.Zero.code..CharCodes.Nine.code
}

internal fun Int.isAsciiUpperHexDigit(): Boolean {
    return this in CharCodes.UpperA.code..CharCodes.UpperF.code
}

internal fun Int.isAsciiLowerHexDigit(): Boolean {
    return this in CharCodes.LowerA.code..CharCodes.LowerF.code
}

internal fun Int.isAsciiHexDigit(): Boolean {
    return isAsciiUpperHexDigit() || isAsciiLowerHexDigit()
}

internal fun Int.isAsciiUpperAlpha(): Boolean {
    return this in CharCodes.UpperA.code..CharCodes.UpperZ.code
}

internal fun Int.isAsciiLowerAlpha(): Boolean {
    return this in CharCodes.LowerA.code..CharCodes.LowerZ.code
}

internal fun Int.isAsciiAlpha(): Boolean {
    return isAsciiUpperAlpha() || isAsciiLowerAlpha()
}

internal fun Int.isAsciiAlphaNumeric(): Boolean {
    return isAsciiAlpha() || isAsciiDigit()
}

internal fun Int.leadingSurrogate(): Boolean {
    return this in 0xD800..0xDBFF
}

internal fun Int.trailingSurrogate(): Boolean {
    return this in 0xDC00..0xDFFF
}

internal fun Int.isSurrogate(): Boolean {
    return leadingSurrogate() || trailingSurrogate()
}

internal fun Int.isScalarValue(): Boolean {
    return isSurrogate().not()
}

internal fun Int.isNonCharacter(): Boolean {
    return this in 0xFDD0..0xFDEF || this and 0xFFFE == 0xFFFE || this and 0xFFFF == 0xFFFF
}