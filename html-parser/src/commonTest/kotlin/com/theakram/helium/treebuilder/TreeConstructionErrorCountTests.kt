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
 * Compares how many parse errors Helium reports for each tree construction test with the number its #errors section
 * lists. The descriptions and positions in those sections are html5lib's own, so only the count is compared.
 * #new-errors lists the tokenizer's errors again by their current codes, so it counts only when #errors is empty.
 *
 * html5lib's lists are not always the spec's, so the runs whose count differs are recorded in
 * [KnownErrorCountDifferences] with the reason and the count Helium reports. Every other run must match, and a
 * recorded run must report exactly its recorded count, so a lost or extra parse error fails the build either way.
 */
internal class TreeConstructionErrorCountTests {

    @Test
    fun errorCounts() {
        var matched = 0
        val unexpected = mutableListOf<String>()
        val seenIds = mutableSetOf<String>()
        for (file in TestFileLoader.allTestFiles) {
            if (file in KnownErrorCountDifferences.filesWithoutErrorLists) continue
            for (test in TestFileLoader.loadTests(file)) {
                if (test.shouldSkip()) continue
                val id = "$file:${test.lineNumber} (script ${if (test.scriptingEnabled) "on" else "off"})"
                seenIds += id
                val expected = (test.errors.ifEmpty { test.newErrors }).size
                val actual = errorsOf(test)
                val known = KnownErrorCountDifferences[id]
                when {
                    known == null && actual.size == expected -> matched++
                    known != null && actual.size == known.actualCount && actual.size != expected -> Unit
                    else -> unexpected += "$id: html5lib lists $expected, Helium reports ${actual.size}" +
                        (known?.let { ", recorded ${it.actualCount}" } ?: "") +
                        "\n  input:  ${test.data.replace("\n", "\\n")}" +
                        "\n  html5lib: ${test.errors.ifEmpty { test.newErrors }.map { it.code }}" +
                        "\n  Helium:   $actual"
                }
            }
        }
        val stale = KnownErrorCountDifferences.ids - seenIds
        println("Tree construction error counts: $matched match, ${KnownErrorCountDifferences.ids.size} known differences")
        check(unexpected.isEmpty() && stale.isEmpty()) {
            buildString {
                if (unexpected.isNotEmpty()) {
                    append("${unexpected.size} error counts differ from html5lib's or from KnownErrorCountDifferences:\n\n")
                    append(unexpected.joinToString("\n\n"))
                }
                if (stale.isNotEmpty()) append("\n\nKnownErrorCountDifferences entries match no test:\n" + stale.joinToString("\n"))
            }
        }
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
