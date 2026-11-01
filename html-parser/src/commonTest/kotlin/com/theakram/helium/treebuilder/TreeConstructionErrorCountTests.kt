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
package com.theakram.helium.treebuilder

import com.theakram.helium.HTMLParser
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.treebuilder.helpers.Html5LibTest
import com.theakram.helium.treebuilder.helpers.TestFileLoader
import kotlin.test.Test

/**
 * Compares how many parse errors Helium reports for each tree construction test with the number its #errors and
 * #new-errors sections list. The descriptions and positions in those sections are html5lib's own, so only the count
 * is compared.
 */
internal class TreeConstructionErrorCountTests {

    @Test
    fun errorCounts() {
        var matched = 0
        val mismatches = mutableListOf<String>()
        for (file in TestFileLoader.allTestFiles) {
            for (test in TestFileLoader.loadTests(file)) {
                if (test.shouldSkip()) continue
                val actual = errorsOf(test)
                if (actual.size == test.errors.size) {
                    matched++
                } else {
                    mismatches += "$file:${test.lineNumber} (script ${if (test.scriptingEnabled) "on" else "off"}): " +
                        "expected ${test.errors.size}, got ${actual.size}\n  input: ${test.data.replace("\n", "\\n")}\n" +
                        "  expected: ${test.errors.map { it.code }}\n  actual:   ${actual}"
                }
            }
        }
        println("Tree construction error counts: $matched match, ${mismatches.size} differ")
        println(mismatches.take(MAX_SHOWN).joinToString("\n"))
        if (mismatches.size > MAX_SHOWN) println("... and ${mismatches.size - MAX_SHOWN} more")
    }

    private companion object {
        const val MAX_SHOWN = 100
    }

    private fun errorsOf(test: Html5LibTest): List<String?> {
        val parser = HTMLParser(scriptingEnabled = test.scriptingEnabled)
        val context = test.documentFragment
        if (context == null) {
            parser.parse(test.data)
        } else {
            val (namespace, localName) = when {
                context.startsWith("svg ") -> Namespaces.SVG to context.removePrefix("svg ")
                context.startsWith("math ") -> Namespaces.MATHML to context.removePrefix("math ")
                else -> Namespaces.HTML to context
            }
            parser.parseFragment(test.data, TreeNode.createElement(localName = localName, namespace = namespace))
        }
        return parser.errors.map { it.code }
    }
}
