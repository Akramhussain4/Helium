/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
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

import java.io.File

object TestFileLoader {
    private const val TEST_DIR = "html5lib-tests/tree-construction"

    private val searchPaths = listOf(
        "html-parser/src/commonTest/resources",
        "src/commonTest/resources",
        "../src/commonTest/resources"
    )

    fun loadTests(filename: String): List<Html5LibTest> =
        loadFileContent("$TEST_DIR/$filename")
            .let { DatFileParser.parse(it) }

    private fun loadFileContent(path: String): String {
        for (searchPath in searchPaths) {
            val file = File("$searchPath/$path")
            if (file.exists() && file.isFile) {
                return file.readText()
            }
        }
        throw IllegalArgumentException("Resource not found: $path")
    }

    val allTestFiles = listOf(
        // Core tests
        "tests1.dat", "tests2.dat", "tests3.dat", "tests4.dat", "tests5.dat",
        "tests6.dat", "tests7.dat", "tests8.dat", "tests9.dat", "tests10.dat",
        "tests11.dat", "tests12.dat", "tests14.dat", "tests15.dat", "tests16.dat",
        "tests17.dat", "tests18.dat", "tests19.dat", "tests20.dat", "tests21.dat",
        "tests22.dat", "tests23.dat", "tests24.dat", "tests25.dat", "tests26.dat",

        // Feature tests
        "adoption01.dat", "adoption02.dat", "blocks.dat", "comments01.dat",
        "doctype01.dat", "domjs-unsafe.dat", "entities01.dat", "entities02.dat",
        "foreign-fragment.dat", "html5test-com.dat", "inbody01.dat", "isindex.dat",
        "main-element.dat", "math.dat", "menuitem-element.dat",
        "namespace-sensitivity.dat", "noscript01.dat", "pending-spec-changes.dat",
        "pending-spec-changes-plain-text-unsafe.dat", "plain-text-unsafe.dat",
        "quirks01.dat", "ruby.dat", "scriptdata01.dat", "search-element.dat",
        "svg.dat", "tables01.dat", "template.dat", "tests_innerHTML_1.dat",
        "tricky01.dat", "webkit01.dat", "webkit02.dat",

        // Scripted tests
        "scripted/adoption01.dat", "scripted/ark.dat", "scripted/webkit01.dat"
    )
}
