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

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class IntegerParsingTest {

    @Test
    fun `digits parse as their value`() {
        assertEquals(0, parseNonNegativeInteger("0"))
        assertEquals(42, parseNonNegativeInteger("042"))
    }

    @Test
    fun `leading whitespace and a plus sign are skipped and trailing characters ignored`() {
        assertEquals(7, parseNonNegativeInteger(" \t\n\u000C\r+7px"))
    }

    @Test
    fun `negative zero is zero`() {
        assertEquals(0, parseNonNegativeInteger("-0"))
    }

    @Test
    fun `negative numbers and strings without leading digits are errors`() {
        assertNull(parseNonNegativeInteger("-1"))
        assertNull(parseNonNegativeInteger(""))
        assertNull(parseNonNegativeInteger("   "))
        assertNull(parseNonNegativeInteger("+"))
        assertNull(parseNonNegativeInteger("x1"))
        assertNull(parseNonNegativeInteger(" 1"))
    }

    @Test
    fun `values too large for an Int saturate`() {
        assertEquals(Int.MAX_VALUE, parseNonNegativeInteger("99999999999999999999999"))
    }
}
