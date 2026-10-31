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

import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.TreeNode

/**
 * Base class for tree construction tests.
 * Follows the exact pattern established by TokenizerTestBase.
 */
internal abstract class TreeConstructionTestBase {

    protected fun parseTree(input: String, scriptingEnabled: Boolean = true): String {
        lateinit var tokenizer: Tokenizer
        val treeBuilder = TreeBuilder(
            onSwitchTokenizerState = { stateType ->
                tokenizer.switchState(stateType)
            },
            scriptingMode = if (scriptingEnabled) ScriptingMode.NORMAL else ScriptingMode.DISABLED
        )
        tokenizer = Tokenizer(
            onTokenEmitted = { token ->
                treeBuilder.process(token)
            },
            onParseError = { _, _ -> },
            hasForeignAdjustedCurrentNode = treeBuilder::hasForeignAdjustedCurrentNode
        )

        tokenizer.tokenize(input)
        return treeBuilder.toTreeString().trim()
    }

    /**
     * Parses [input] as the contents of [context], given in html5lib's format: a tag name, optionally
     * prefixed with "svg " or "math " for foreign elements (e.g. "td", "svg path").
     */
    protected fun parseFragmentTree(input: String, context: String, scriptingEnabled: Boolean = true): String {
        val (namespace, localName) = when {
            context.startsWith("svg ") -> Namespaces.SVG to context.removePrefix("svg ")
            context.startsWith("math ") -> Namespaces.MATHML to context.removePrefix("math ")
            else -> Namespaces.HTML to context
        }
        val contextElement = TreeNode.createElement(localName = localName, namespace = namespace)
        return FragmentParser.parseFragment(
            contextElement,
            input,
            if (scriptingEnabled) ScriptingMode.INERT else ScriptingMode.DISABLED
        )
            .joinToString("\n") { it.toTreeString() }
            .trim()
    }

    /**
     * Assert that HTML input produces the expected tree structure, parsed as a fragment when [context] is given.
     */
    protected fun assertTreeConstruction(
        input: String,
        expectedTree: String,
        scriptingEnabled: Boolean = true,
        context: String? = null
    ) {
        val actualTree = if (context != null) {
            parseFragmentTree(input, context, scriptingEnabled)
        } else {
            parseTree(input, scriptingEnabled)
        }
        if (actualTree != expectedTree.trim()) {
            throw AssertionError(formatTreeMismatchError(input, expectedTree.trim(), actualTree))
        }
    }

    private fun formatTreeMismatchError(input: String, expected: String, actual: String): String {
        val expectedLines = expected.lines()
        val actualLines = actual.lines()

        // Find first differing line
        var firstDiffLine = -1
        val maxLines = maxOf(expectedLines.size, actualLines.size)
        for (i in 0 until maxLines) {
            val expLine = expectedLines.getOrNull(i) ?: ""
            val actLine = actualLines.getOrNull(i) ?: ""
            if (expLine != actLine) {
                firstDiffLine = i
                break
            }
        }

        return buildString {
            appendLine("Tree structure mismatch")
            appendLine()

            // Input (truncated if too long)
            append("Input: ")
            if (input.length <= 60) {
                appendLine(input.replace("\n", "\\n"))
            } else {
                appendLine(input.take(60).replace("\n", "\\n") + "...")
            }
            appendLine()

            // Show difference location
            if (firstDiffLine >= 0) {
                appendLine("First difference at line ${firstDiffLine + 1}:")
                appendLine()

                // Show context (2 lines before)
                val contextStart = maxOf(0, firstDiffLine - 2)
                for (i in contextStart until firstDiffLine) {
                    appendLine("  ${expectedLines[i]}")
                }

                // Show the differing line
                appendLine("- ${expectedLines.getOrNull(firstDiffLine) ?: "(missing)"}")
                appendLine("+ ${actualLines.getOrNull(firstDiffLine) ?: "(missing)"}")

                // Show context (2 lines after)
                val contextEnd = minOf(expectedLines.size, firstDiffLine + 3)
                for (i in (firstDiffLine + 1) until contextEnd) {
                    appendLine("  ${expectedLines[i]}")
                }
                appendLine()
            }

            // Full diff for small trees (< 10 lines)
            if (expectedLines.size <= 10 || actualLines.size <= 10) {
                appendLine("Expected tree:")
                expectedLines.forEach { appendLine("  $it") }
                appendLine()
                appendLine("Actual tree:")
                actualLines.forEach { appendLine("  $it") }
            } else {
                appendLine("Expected ${expectedLines.size} lines, got ${actualLines.size} lines")
                appendLine("(Use -v for full tree output)")
            }
        }
    }
}