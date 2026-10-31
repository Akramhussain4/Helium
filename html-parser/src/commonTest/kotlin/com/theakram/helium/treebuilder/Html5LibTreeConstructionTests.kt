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

import com.theakram.helium.treebuilder.helpers.FileResult
import com.theakram.helium.treebuilder.helpers.TestFileLoader
import com.theakram.helium.treebuilder.helpers.TestSummary
import kotlin.test.Test

/**
 * Tree construction tests from html5lib-tests.
 *
 * Source: https://github.com/html5lib/html5lib-tests
 * Total available: 59 files, 1,782 tests
 */
internal class Html5LibTreeConstructionTests : TreeConstructionTestBase() {

    @Test
    fun runAllTreeConstructionTests() {
        println("\n${"=".repeat(80)}")
        println("Running all ${TestFileLoader.allTestFiles.size} tree-construction test files")
        println("This may take a while...")
        println("${"=".repeat(80)}\n")

        val results = TestFileLoader.allTestFiles.map { runFileSilently(it) }

        results.forEach { println(it.formatSummary()) }

        val summary = TestSummary(results)
        summary.print()

        val loadErrors = results.filter { it.error != null }
        if (loadErrors.isNotEmpty()) {
            throw AssertionError(
                "Could not load ${loadErrors.size} test files (is the html5lib-tests submodule checked out?): " +
                    loadErrors.joinToString { "${it.filename}: ${it.error}" }
            )
        }

        // Raise this as conformance improves so regressions fail the build
        if (summary.totalPassed < MIN_PASSING_TESTS) {
            throw AssertionError(
                "Tree construction regressed: ${summary.totalPassed} tests passed, expected at least $MIN_PASSING_TESTS"
            )
        }
    }

    @Test
    fun runTests1() = runFile("tests1.dat")

    @Test
    fun runTests2() = runFile("tests2.dat")

    private fun runFile(filename: String) {
        val tests = TestFileLoader.loadTests(filename)
        val failures = mutableListOf<TestFailure>()
        var passed = 0
        var skipped = 0

        tests.forEachIndexed { index, test ->
            when {
                test.shouldSkip() -> skipped++
                else -> try {
                    assertTreeConstruction(test.data, test.document)
                    passed++
                } catch (e: Throwable) {
                    failures.add(TestFailure(filename, index + 1, test.lineNumber, test.data, e))
                }
            }
        }

        println("\n$filename: $passed passed, ${failures.size} failed, $skipped skipped (${tests.size} total)")

        if (failures.isNotEmpty()) {
            printFailures(failures, filename)
            throw AssertionError("${failures.size} of ${tests.size} tests failed in $filename")
        }
    }

    private fun runFileSilently(filename: String): FileResult = try {
        val tests = TestFileLoader.loadTests(filename)
        var passed = 0
        var failed = 0
        var skipped = 0

        tests.forEach { test ->
            when {
                test.shouldSkip() -> skipped++
                else -> try {
                    assertTreeConstruction(test.data, test.document)
                    passed++
                } catch (e: Throwable) {
                    failed++
                }
            }
        }

        FileResult(filename, passed, failed, skipped, tests.size)
    } catch (e: Exception) {
        FileResult(filename, error = e.message)
    }

    private fun runSingleTest(filename: String, testNumber: Int) {
        val tests = TestFileLoader.loadTests(filename)
        val test = tests.getOrNull(testNumber - 1)
            ?: error("Test #$testNumber not found in $filename (only ${tests.size} tests)")

        println("\nRunning test #$testNumber from $filename (line ${test.lineNumber}):")
        println("Input: ${test.data.replace("\n", "\\n")}\n")

        assertTreeConstruction(test.data, test.document)

        println("✓ Test #$testNumber passed")
    }

    private fun runTestRange(filename: String, startTest: Int, endTest: Int) {
        val tests = TestFileLoader.loadTests(filename)
        require(startTest in 1..tests.size && endTest in 1..tests.size) {
            "Invalid range $startTest-$endTest for $filename (has ${tests.size} tests)"
        }

        println("\nRunning tests $startTest-$endTest from $filename")
        println("=".repeat(80))

        var passed = 0
        var failed = 0

        for (i in (startTest - 1) until endTest) {
            val test = tests[i]
            try {
                assertTreeConstruction(test.data, test.document)
                passed++
                println("✓ Test #${i + 1}")
            } catch (e: AssertionError) {
                failed++
                println("\n✗ Test #${i + 1} (line ${test.lineNumber}) FAILED:")
                println("Input: ${test.data.replace("\n", "\\n")}")
                println(e.message)
                println()
            }
        }

        println("=".repeat(80))
        println("Results: $passed passed, $failed failed")

        if (failed > 0) {
            throw AssertionError("$failed of ${endTest - startTest + 1} tests failed")
        }
    }

    private fun printFailures(failures: List<TestFailure>, filename: String) {
        val maxShow = 3
        println("\nShowing first ${failures.size.coerceAtMost(maxShow)} of ${failures.size} failure(s):")
        println("=".repeat(80))

        failures.take(maxShow).forEachIndexed { idx, failure ->
            println("\nFailure ${idx + 1}:")
            println("Test #${failure.testNumber} (line ${failure.lineNumber} in $filename)")
            println("-".repeat(80))
            println(failure.error.message ?: "Unknown error")
            if (idx < maxShow - 1 && idx < failures.size - 1) println()
        }

        println("=".repeat(80))

        if (failures.size > maxShow) {
            println("\n... and ${failures.size - maxShow} more failure(s) not shown")
            println("Re-run with individual test to see full details")
        }
    }

    private companion object {
        const val MIN_PASSING_TESTS = 1512
    }

    private data class TestFailure(
        val filename: String,
        val testNumber: Int,
        val lineNumber: Int,
        val input: String,
        val error: Throwable
    )
}
