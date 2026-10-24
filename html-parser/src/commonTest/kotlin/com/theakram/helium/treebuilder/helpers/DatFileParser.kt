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

    fun parse(content: String): List<Html5LibTest> =
        content.lines()
            .let { lines -> LineIterator(lines).parseAllTests() }

    private class LineIterator(private val lines: List<String>) {
        private var index = 0
        private val currentLine get() = lines.getOrNull(index)

        fun parseAllTests(): List<Html5LibTest> = buildList {
            while (index < lines.size) {
                skipBlankLines()
                if (currentLine?.trim() == "#data") {
                    add(parseTest())
                } else {
                    index++
                }
            }
        }

        private fun parseTest(): Html5LibTest {
            val lineNumber = index + 1
            expectSection("#data")

            return Html5LibTest(
                lineNumber = lineNumber,
                data = parseContent(),
                errors = parseErrors(),
                documentFragment = parseDocumentFragment(),
                scriptingEnabled = parseScriptingDirective(),
                document = parseDocument()
            ).also { skipBlankLines() }
        }

        private fun parseContent(): String = buildString {
            while (currentLine?.let { !it.startsWith("#") } == true) {
                if (isNotEmpty()) append("\n")
                append(lines[index])
                index++
            }
        }

        private fun parseErrors(): List<ParseError> = buildList {
            if (currentLine?.trim() == "#errors") {
                index++
                addAll(parseErrorLines(ERROR_REGEX_OLD))
            }
            if (currentLine?.trim() == "#new-errors") {
                index++
                addAll(parseErrorLines(ERROR_REGEX_NEW))
            }
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

        private fun parseScriptingDirective(): Boolean =
            when (currentLine?.trim()) {
                "#script-on" -> true.also { index++ }
                "#script-off" -> false.also { index++ }
                else -> true
            }

        private fun parseDocument(): String {
            expectSection("#document")
            return buildString {
                while (currentLine?.let { !it.startsWith("#") && it.isNotBlank() } == true) {
                    if (isNotEmpty()) append("\n")
                    append(lines[index])
                    index++
                }
            }
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

    private fun parseErrorLine(line: String, regex: Regex): ParseError? {
        val trimmed = line.trim()
        if (trimmed.isEmpty()) return null

        val match = regex.find(trimmed) ?: return null
        return ParseError(
            line = match.groupValues[1].toInt(),
            column = match.groupValues[2].toInt(),
            code = match.groupValues[3].trim()
        )
    }
}
