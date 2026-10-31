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
package com.theakram.helium.tokenizer.helpers

/**
 * html5lib tokenizer tests that don't pass yet, keyed by "file: description (initial state)", with the reason for
 * each and the errors Helium reports for it. A known failure must still produce the expected tokens, and exactly the
 * recorded errors, so a change in either fails the test. A change that makes a test pass removes its entry, so this
 * list is also the list of remaining work.
 */
internal object KnownTokenizerFailures {

    class KnownFailure(val reason: String, val actualErrors: List<String>)

    private const val STALE_QUESTION_MARK =
        "The test expects unexpected-question-mark-instead-of-tag-name, which the spec dropped when <? " +
            "started a processing instruction; the processing instruction open state reports " +
            "invalid-first-character-of-processing-instruction-target instead"

    private const val ERROR_ORDER =
        "Both errors are for the same character. The input stream error is reported as the processing " +
            "instruction open state consumes the character, before that state reports its own error, as in the " +
            "40 other html5lib tests with two errors at one position; this one test lists them the other way " +
            "round"

    private val failures = mapOf(
        "domjs.test: CR in bogus comment state (Data state)" to KnownFailure(
            STALE_QUESTION_MARK,
            listOf("invalid-first-character-of-processing-instruction-target")
        ),
        "domjs.test: CRLF in bogus comment state (Data state)" to KnownFailure(
            STALE_QUESTION_MARK,
            listOf("invalid-first-character-of-processing-instruction-target")
        ),
        "domjs.test: CRLFLF in bogus comment state (Data state)" to KnownFailure(
            STALE_QUESTION_MARK,
            listOf("invalid-first-character-of-processing-instruction-target")
        ),
        "test3.test: <?\\u000B (Data state)" to KnownFailure(
            ERROR_ORDER,
            listOf("control-character-in-input-stream", "invalid-first-character-of-processing-instruction-target")
        ),
    )

    val ids: Set<String> get() = failures.keys

    operator fun get(id: String): KnownFailure? = failures[id]
}
