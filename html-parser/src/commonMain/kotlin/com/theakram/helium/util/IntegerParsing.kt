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

/**
 * Parses [input] with the rules for parsing non-negative integers: optional leading ASCII whitespace, an optional
 * sign and a run of ASCII digits, ignoring whatever follows them. Returns null where the rules return an error. A
 * value too large for an Int is returned as [Int.MAX_VALUE].
 *
 * Spec: https://html.spec.whatwg.org/multipage/common-microsyntaxes.html#rules-for-parsing-non-negative-integers
 */
internal fun parseNonNegativeInteger(input: String): Int? {
    // The rules for parsing integers (https://html.spec.whatwg.org/multipage/common-microsyntaxes.html#rules-for-parsing-integers)
    var position = 0
    while (position < input.length && input[position].code.isAsciiWhitespace()) position++
    if (position == input.length) return null
    var negative = false
    when (input[position]) {
        '-' -> {
            negative = true
            position++
        }

        '+' -> position++
    }
    if (position == input.length || !input[position].code.isAsciiDigit()) return null
    var value = 0L
    while (position < input.length && input[position].code.isAsciiDigit()) {
        value = minOf(value * 10 + (input[position] - '0'), Int.MAX_VALUE.toLong())
        position++
    }

    // "If value is less than zero, return an error." Negative zero is zero.
    if (negative && value != 0L) return null
    return value.toInt()
}
