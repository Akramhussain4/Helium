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
 * Serializes the tree Helium parses for each tree construction test, parses the result again in the same way, with
 * the test's fragment context and scripting mode, and checks that it gives the same tree.
 *
 * The spec says that this doesn't always hold ("It is possible that the output of this algorithm, if parsed with an
 * HTML parser, will not return the original tree structure"), so the runs whose trees differ are recorded in
 * [KnownRoundTripDifferences] with the reason. Every other run must give the same tree, and a recorded run must give
 * a different one, so that an entry that no longer applies fails the build too.
 */
internal class SerializationRoundTripTests {

    @Test
    fun parsedTreesRoundTrip() {
        var roundTripped = 0
        val unexpected = mutableListOf<String>()
        val seenIds = mutableSetOf<String>()
        for (file in TestFileLoader.allTestFiles) {
            for (test in TestFileLoader.loadTests(file)) {
                val id = "$file:${test.lineNumber} (script ${if (test.scriptingEnabled) "on" else "off"})"
                seenIds += id
                val first = parse(test, test.data)
                val serialized = first.innerHtml(test.scriptingEnabled)
                val second = parse(test, serialized)
                val firstTree = first.treeString()
                val secondTree = second.treeString()
                val known = KnownRoundTripDifferences[id]
                when {
                    known == null && firstTree == secondTree -> roundTripped++
                    known != null && firstTree != secondTree -> Unit
                    else -> unexpected += "$id: " +
                        (if (known == null) "the tree differs after a round trip" else "recorded, but round trips") +
                        "\n  input:      ${test.data.escaped()}" +
                        "\n  serialized: ${serialized.escaped()}" +
                        "\n  parsed:\n$firstTree" +
                        "\n  reparsed:\n$secondTree"
                }
            }
        }
        val stale = KnownRoundTripDifferences.ids - seenIds
        println("Serialization round trips: $roundTripped same tree, ${KnownRoundTripDifferences.ids.size} known differences")
        check(unexpected.isEmpty() && stale.isEmpty()) {
            buildString {
                if (unexpected.isNotEmpty()) {
                    append("${unexpected.size} round trips differ from KnownRoundTripDifferences:\n\n")
                    append(unexpected.joinToString("\n\n"))
                }
                if (stale.isNotEmpty()) append("\n\nKnownRoundTripDifferences entries match no test:\n" + stale.joinToString("\n"))
            }
        }
    }

    /**
     * Parses [html] as [test] parses its input: as a document, or as the contents of its fragment context. A
     * fragment's nodes are then moved into the context element, so that serializing them is what the element's
     * innerHTML getter gives after its innerHTML setter parsed [html]: the parent of a text node decides whether it is
     * escaped, and a style context's text isn't.
     */
    private fun parse(test: Html5LibTest, html: String): TreeNode {
        val parser = HTMLParser(scriptingEnabled = test.scriptingEnabled)
        val context = test.documentFragment ?: return parser.parse(html)
        val (namespace, localName) = when {
            context.startsWith("svg ") -> Namespaces.SVG to context.removePrefix("svg ")
            context.startsWith("math ") -> Namespaces.MATHML to context.removePrefix("math ")
            else -> Namespaces.HTML to context
        }
        val element = TreeNode.createElement(localName = localName, namespace = namespace)
        val fragment = parser.parseFragment(html, element)
        // The innerHTML setter replaces a template's contents rather than its children
        val target = element.templateContent ?: element
        while (fragment.hasChildren()) target.appendChild(fragment.firstChild!!)
        return element
    }

    /** The tree that was parsed: a document, or a fragment context element's contents. */
    private fun TreeNode.treeString(): String = toTreeString()

    private fun String.escaped(): String = replace("\n", "\\n")
}
