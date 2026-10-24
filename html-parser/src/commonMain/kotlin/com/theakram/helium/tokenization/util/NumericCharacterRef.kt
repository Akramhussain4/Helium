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

package com.theakram.helium.tokenization.util

internal val NumCharRef by lazy {
    mapOf(
        0x80 to 0x20AC,
        0x82 to 0x201A,
        0x83 to 0x0192,
        0x84 to 0x201E,
        0x85 to 0x2026,
        0x86 to 0x2020,
        0x87 to 0x2021,
        0x88 to 0x02C6,
        0x89 to 0x2030,
        0x8A to 0x0160,
        0x8B to 0x2039,
        0x8C to 0x0152,
        0x8E to 0x017D,
        0x91 to 0x2018,
        0x92 to 0x2019,
        0x93 to 0x201C,
        0x94 to 0x201D,
        0x95 to 0x2022,
        0x96 to 0x2013,
        0x97 to 0x2014,
        0x98 to 0x02DC,
        0x99 to 0x2122,
        0x9A to 0x0161,
        0x9B to 0x203A,
        0x9C to 0x0153,
        0x9E to 0x017E,
        0x9F to 0x0178,
    )
}