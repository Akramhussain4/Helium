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

data class FileResult(
    val filename: String,
    val passed: Int = 0,
    val failed: Int = 0,
    val skipped: Int = 0,
    val total: Int = 0,
    val error: String? = null
) {
    val passRate: Int
        get() = if (passed + failed > 0) (passed * 100) / (passed + failed) else 0

    val hasFailures: Boolean get() = failed > 0
    val isPerfect: Boolean get() = failed == 0 && passed > 0

    val status: String
        get() = when {
            isPerfect -> "✓"
            hasFailures -> "✗"
            else -> "⊘"
        }

    fun formatSummary(): String = buildString {
        append(status)
        append(" ")
        append(filename.padEnd(50))
        append(" (")

        val parts = mutableListOf<String>()
        if (passed > 0) parts.add("$passed passed")
        if (failed > 0) parts.add("$failed failed")
        if (skipped > 0) parts.add("$skipped skipped")
        if (error != null) parts.add("error: $error")

        append(parts.joinToString(", "))
        append(")")
    }
}

data class TestSummary(
    val results: List<FileResult>
) {
    val totalTests = results.sumOf { it.total }
    val totalPassed = results.sumOf { it.passed }
    val totalFailed = results.sumOf { it.failed }
    val totalSkipped = results.sumOf { it.skipped }

    val filesWithFailures = results.filter { it.hasFailures }
    val perfectFiles = results.filter { it.isPerfect }

    fun print() {
        println("\n${"=".repeat(80)}")
        println("SUMMARY")
        println("=".repeat(80))
        println("Files processed: ${results.size}")
        println("Total tests: $totalTests")
        println("  Passed:  $totalPassed (${passRate(totalPassed, totalTests)}%)")
        println("  Failed:  $totalFailed (${passRate(totalFailed, totalTests)}%)")
        println("  Skipped: $totalSkipped (${passRate(totalSkipped, totalTests)}%)")
        println("=".repeat(80))

        if (filesWithFailures.isNotEmpty()) {
            println("\nFiles with failures (${filesWithFailures.size}):")
            filesWithFailures
                .sortedByDescending { it.passRate }
                .forEach { println("  ${it.filename.padEnd(50)} (${it.passRate}% passing)") }
        }

        if (perfectFiles.isNotEmpty()) {
            println("\nFully passing files (${perfectFiles.size}):")
            perfectFiles.forEach { println("  ${it.formatSummary()}") }
        }

        println("\nTo debug a specific file:")
        println("  ./gradlew :html-parser:jvmTest --tests \"*runTests1\"")
        println("\nTo debug a specific test:")
        println("  Add: @Test fun debugTests1_5() = runSingleTest(\"tests1.dat\", 5)")
        println("  Run: ./gradlew :html-parser:jvmTest --tests \"*debugTests1_5\"")
        println()
    }

    private fun passRate(count: Int, total: Int) =
        if (total > 0) (count * 100) / total else 0
}
