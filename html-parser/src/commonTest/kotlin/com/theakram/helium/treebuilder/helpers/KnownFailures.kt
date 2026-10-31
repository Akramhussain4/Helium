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

package com.theakram.helium.treebuilder.helpers

/**
 * Tree construction tests that are skipped because the parser doesn't match them yet, with the reason for each.
 * A change that makes a test pass removes its entry, so this list is also the list of remaining work.
 */
internal object KnownFailures {

    private const val SELECTEDCONTENT =
        "selectedcontent is kept up to date by DOM insertion steps, which a parser without a DOM doesn't run"

    private val failures = mapOf(
        "webkit02.dat:692" to SELECTEDCONTENT,
        "webkit02.dat:706" to SELECTEDCONTENT,
        "webkit02.dat:732" to SELECTEDCONTENT,
        "webkit02.dat:748" to SELECTEDCONTENT
    )

    fun reasonFor(filename: String, lineNumber: Int): String? = failures["$filename:$lineNumber"]
}
