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
 * Parser for html5lib .dat test files.
 *
 * Format: https://github.com/html5lib/html5lib-tests/blob/master/tree-construction/README.md
 */
object DatFileParser {
    private val ERROR_REGEX_OLD = """\((\d+),(\d+)\):\s*(.+)""".toRegex()
    private val ERROR_REGEX_NEW = """\((\d+):(\d+)\)\s+(.+)""".toRegex()

    // Split on LF only: lines() also splits on CR, which some test data contains literally
    fun parse(content: String): List<Html5LibTest> =
        content.split('\n')
            .let { lines -> LineIterator(lines).parseAllTests() }

    private class LineIterator(private val lines: List<String>) {
        private var index = 0
        private val currentLine get() = lines.getOrNull(index)

        fun parseAllTests(): List<Html5LibTest> = buildList {
            while (index < lines.size) {
                skipBlankLines()
                if (currentLine?.trim() == "#data") {
                    addAll(parseTest())
                } else {
                    index++
                }
            }
        }

        // A test without #script-on or #script-off runs once with scripting enabled and once with it disabled
        private fun parseTest(): List<Html5LibTest> {
            val lineNumber = index + 1
            expectSection("#data")

            val data = parseContent()
            val errors = parseErrorSection("#errors", ERROR_REGEX_OLD)
            val newErrors = parseErrorSection("#new-errors", ERROR_REGEX_NEW)
            val documentFragment = parseDocumentFragment()
            val scriptingEnabled = parseScriptingDirective()
            val document = parseDocument()
            skipBlankLines()

            return (scriptingEnabled?.let { listOf(it) } ?: listOf(true, false)).map {
                Html5LibTest(
                    lineNumber = lineNumber,
                    data = data,
                    errors = errors,
                    newErrors = newErrors,
                    documentFragment = documentFragment,
                    scriptingEnabled = it,
                    document = document
                )
            }
        }

        private fun parseContent(): String = buildString {
            while (currentLine?.let { !it.startsWith("#") } == true) {
                if (isNotEmpty()) append("\n")
                append(lines[index])
                index++
            }
        }

        private fun parseErrorSection(header: String, regex: Regex): List<ParseError> {
            if (currentLine?.trim() != header) return emptyList()
            index++
            return parseErrorLines(regex)
        }

        private fun parseErrorLines(regex: Regex): List<ParseError> = buildList {
            while (currentLine?.let { !it.startsWith("#") && it.isNotBlank() } == true) {
                parseErrorLine(lines[index], regex)?.let { add(it) }
                index++
            }
        }

        private fun parseDocumentFragment(): String? =
            if (currentLine?.trim() == "#document-fragment") {
                index++
                lines[index].trim().also { index++ }
            } else null

        private fun parseScriptingDirective(): Boolean? =
            when (currentLine?.trim()) {
                "#script-on" -> true.also { index++ }
                "#script-off" -> false.also { index++ }
                else -> null
            }

        private fun parseDocument(): String {
            expectSection("#document")
            return buildString {
                while (!isEndOfTest()) {
                    if (isNotEmpty()) append("\n")
                    append(lines[index])
                    index++
                }
            }
        }

        // The document is the last section, and its text nodes can contain blank lines, so it only ends at
        // the blank line before the next test, which starts with #description or #data, or at the end of the file
        private fun isEndOfTest(): Boolean {
            if (currentLine?.isNotBlank() == true) return false
            val nextContentLine = lines.asSequence().drop(index).firstOrNull { it.isNotBlank() }
            return nextContentLine == null || nextContentLine.trim() in setOf("#description", "#data")
        }

        private fun expectSection(section: String) {
            require(currentLine?.trim() == section) {
                "Expected $section at line ${index + 1}, got: $currentLine"
            }
            index++
        }

        private fun skipBlankLines() {
            while (index < lines.size && lines[index].isBlank()) index++
        }
    }

    // Each line is one parse error. Some aren't in the "(line,col): description" form, such as validator.nu messages
    // or ranges; those still count, with no position.
    private fun parseErrorLine(line: String, regex: Regex): ParseError? {
        val trimmed = line.trim()
        if (trimmed.isEmpty()) return null

        val match = regex.find(trimmed) ?: return ParseError(line = 0, column = 0, code = trimmed)
        return ParseError(
            line = match.groupValues[1].toInt(),
            column = match.groupValues[2].toInt(),
            code = match.groupValues[3].trim()
        )
    }
}
