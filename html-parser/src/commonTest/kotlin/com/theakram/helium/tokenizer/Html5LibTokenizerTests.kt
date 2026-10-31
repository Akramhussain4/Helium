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
package com.theakram.helium.tokenizer

import com.theakram.helium.TEST_RESOURCES_DIR
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.tokenization.states.CDATASection
import com.theakram.helium.tokenization.states.Data
import com.theakram.helium.tokenization.states.PlainText
import com.theakram.helium.tokenization.states.RCData
import com.theakram.helium.tokenization.states.RawText
import com.theakram.helium.tokenization.states.ScriptData
import com.theakram.helium.tokenizer.helpers.Json
import com.theakram.helium.tokenizer.helpers.KnownTokenizerFailures
import com.theakram.helium.util.appendUnicode
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

/**
 * Runs the html5lib tokenizer tests straight from their JSON files, comparing both the tokens and the parse error
 * codes, in order. Error positions are not compared: the spec doesn't define them. A test listed in
 * [KnownTokenizerFailures] must still produce the expected tokens, and exactly the errors recorded there.
 *
 * Source: https://github.com/html5lib/html5lib-tests/tree/master/tokenizer
 */
internal class Html5LibTokenizerTests {

    private val files = listOf(
        "contentModelFlags.test", "domjs.test", "entities.test", "escapeFlag.test", "namedEntities.test",
        "numericEntities.test", "pendingSpecChanges.test", "test1.test", "test2.test", "test3.test", "test4.test",
        "unicodeChars.test", "unicodeCharsProblematic.test"
    )

    // Expects the "coercing an HTML DOM into an infoset" tweaks, which Helium doesn't apply
    private val skippedFiles = listOf("xmlViolation.test")

    private class Result(val tokensMatch: String?, val expectedErrors: List<String>, val actualErrors: List<String>)

    @Test
    fun everyTestFileIsRun() {
        val onDisk = SystemFileSystem.list(Path(TEST_RESOURCES_DIR, TEST_DIR)).map { it.name }.filter { it.endsWith(".test") }
        assertEquals((files + skippedFiles).sorted(), onDisk.sorted())
    }

    @Test
    fun runAllTokenizerTests() {
        val failures = mutableListOf<String>()
        val changedKnownFailures = mutableListOf<String>()
        val fixedKnownFailures = mutableListOf<String>()
        val seenIds = mutableSetOf<String>()
        var passed = 0
        var known = 0

        for (file in files) {
            @Suppress("UNCHECKED_CAST")
            val tests = (Json.parse(load(file)) as Map<String, Any?>)["tests"] as List<Map<String, Any?>>
            val descriptionCounts = tests.groupingBy { it["description"] as String }.eachCount()
            val occurrences = mutableMapOf<String, Int>()
            for (test in tests) {
                val description = test["description"] as String
                val occurrence = occurrences.getOrElse(description) { 0 } + 1
                occurrences[description] = occurrence
                // Some descriptions repeat within a file, so those are numbered to keep every id unique
                val name = if (descriptionCounts.getValue(description) > 1) "$description [$occurrence]" else description
                @Suppress("UNCHECKED_CAST")
                val states = test["initialStates"] as List<String>? ?: listOf("Data state")
                for (state in states) {
                    val id = "$file: $name ($state)"
                    check(seenIds.add(id)) { "Duplicate test id $id" }
                    val result = run(test, state)
                    val knownFailure = KnownTokenizerFailures[id]
                    val errorsMatch = result.actualErrors == result.expectedErrors
                    when {
                        // A known failure excuses only the errors it records; the tokens must always match
                        result.tokensMatch != null -> failures += "$id\n${result.tokensMatch}"
                        knownFailure != null && errorsMatch -> fixedKnownFailures += id
                        knownFailure != null && result.actualErrors != knownFailure.actualErrors ->
                            changedKnownFailures += "$id\n  recorded errors: ${knownFailure.actualErrors}\n" +
                                "  actual errors:   ${result.actualErrors}"
                        knownFailure != null -> known++
                        errorsMatch -> passed++
                        else -> failures += "$id\n  expected errors: ${result.expectedErrors}\n" +
                            "  actual errors:   ${result.actualErrors}"
                    }
                }
            }
        }

        val staleKnownFailures = KnownTokenizerFailures.ids - seenIds
        println("Tokenizer tests: $passed passed, $known known failures, ${failures.size} failed")
        val problems = listOf(
            "tokenizer tests failed" to failures,
            "known failures now pass; remove them from KnownTokenizerFailures" to fixedKnownFailures,
            "known failures report different errors than KnownTokenizerFailures records" to changedKnownFailures,
            "KnownTokenizerFailures entries match no test" to staleKnownFailures.toList()
        ).filter { it.second.isNotEmpty() }
        if (problems.isNotEmpty()) {
            fail(problems.joinToString("\n\n") { (title, ids) -> "${ids.size} $title:\n\n" + ids.joinToString("\n\n") })
        }
    }

    private fun run(test: Map<String, Any?>, state: String): Result {
        val doubleEscaped = test["doubleEscaped"] == true
        fun unescape(value: Any?): Any? = when {
            !doubleEscaped -> value
            value is String -> unescapeDoubleEscaped(value)
            value is Map<*, *> -> value.entries.associate { (k, v) -> unescape(k) to unescape(v) }
            else -> value
        }

        val tokens = mutableListOf<Token>()
        val errors = mutableListOf<String>()
        val tokenizer = Tokenizer(onTokenEmitted = tokens::add, onParseError = { code, _ -> errors += code })
        tokenizer.setState(
            when (state) {
                "Data state" -> Data
                "PLAINTEXT state" -> PlainText
                "RCDATA state" -> RCData
                "RAWTEXT state" -> RawText
                "Script data state" -> ScriptData
                "CDATA section state" -> CDATASection
                else -> error("Unknown initial state $state")
            }
        )
        (test["lastStartTag"] as String?)?.let { tokenizer.lastStartTagName = it }
        tokenizer.tokenize(unescape(test["input"]) as String)

        @Suppress("UNCHECKED_CAST")
        val expectedTokens = (test["output"] as List<List<Any?>>).map { token -> normalize(token.map(::unescape)) }
        @Suppress("UNCHECKED_CAST")
        val expectedErrors = (test["errors"] as List<Map<String, Any?>>? ?: emptyList()).map { it["code"] as String }
        val actualTokens = toHtml5LibTokens(tokens)

        val tokensMatch = if (actualTokens == expectedTokens) null else
            "  input:    ${test["input"]}\n  expected: $expectedTokens\n  actual:   $actualTokens"
        return Result(tokensMatch, expectedErrors, errors)
    }

    // Attribute values are compared as maps, and a StartTag's missing self-closing entry means false
    private fun normalize(token: List<Any?>): List<Any?> = when (token[0]) {
        "StartTag" -> listOf("StartTag", token[1], token[2], token.getOrNull(3) == true)
        else -> token
    }

    private fun toHtml5LibTokens(tokens: List<Token>): List<List<Any?>> {
        val result = mutableListOf<List<Any?>>()
        val characters = StringBuilder()
        fun flushCharacters() {
            if (characters.isNotEmpty()) result += listOf("Character", characters.toString())
            characters.clear()
        }
        for (token in tokens) {
            if (token is Token.Character) {
                characters.appendUnicode(token.codePoint)
                continue
            }
            flushCharacters()
            when (token) {
                is Token.DocType -> result += listOf("DOCTYPE", token.name, token.publicId, token.systemId, !token.forceQuirks)
                is Token.StartTag -> result += listOf("StartTag", token.name, token.attributes, token.selfClosing)
                is Token.EndTag -> result += listOf("EndTag", token.name)
                is Token.Comment -> result += listOf("Comment", token.data)
                is Token.ProcessingInstruction -> result += listOf("ProcessingInstruction", token.target, token.data)
                is Token.Character, Token.EOF -> Unit
            }
        }
        flushCharacters()
        return result
    }

    private fun unescapeDoubleEscaped(value: String): String =
        Regex("""\\u([0-9A-Fa-f]{4})""").replace(value) { it.groupValues[1].toInt(16).toChar().toString() }

    private fun load(file: String): String =
        SystemFileSystem.source(Path(TEST_RESOURCES_DIR, TEST_DIR, file)).buffered().use { it.readString() }

    private companion object {
        const val TEST_DIR = "html5lib-tests/tokenizer"
    }
}
