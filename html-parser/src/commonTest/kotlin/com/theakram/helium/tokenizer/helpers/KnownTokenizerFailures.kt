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

    private const val CONTROL_CHARACTER =
        "control-character-in-input-stream is not reported"

    private const val SURROGATE_PAIR =
        "The input stream is checked one UTF-16 unit at a time, so a valid surrogate pair is reported as two " +
            "surrogate-in-input-stream errors"

    private const val ASTRAL_NONCHARACTER =
        "The input stream is checked one UTF-16 unit at a time, so a noncharacter above U+FFFF is reported " +
            "as two surrogate-in-input-stream errors instead of noncharacter-in-input-stream"

    private const val ERROR_ORDER =
        "Input stream errors are all reported before tokenizing, not as each character is consumed"

    private const val DUPLICATE_ATTRIBUTE =
        "duplicate-attribute is not reported"

    private const val END_TAG_WITH_ATTRIBUTES =
        "End tag tokens have no attributes, so end-tag-with-attributes is not reported"

    private const val END_TAG_WITH_TRAILING_SOLIDUS =
        "End tag tokens have no self-closing flag, so end-tag-with-trailing-solidus is not reported"

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
        "test1.test: End Tag w/attribute (Data state)" to KnownFailure(
            END_TAG_WITH_ATTRIBUTES,
            listOf()
        ),
        "test1.test: Repeated attr (Data state)" to KnownFailure(
            DUPLICATE_ATTRIBUTE,
            listOf()
        ),
        "test3.test: \\u000B [1] (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: \\u000B [1] (PLAINTEXT state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: \\u000B [1] (RCDATA state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: \\u000B [1] (RAWTEXT state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: \\u000B [1] (Script data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: \\u000B [2] (CDATA section state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("eof-in-cdata")
        ),
        "test3.test: ;\\uDBC0\\uDC00 [1] (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: ;\\uDBC0\\uDC00 [1] (PLAINTEXT state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: ;\\uDBC0\\uDC00 [1] (RCDATA state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: ;\\uDBC0\\uDC00 [1] (RAWTEXT state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: ;\\uDBC0\\uDC00 [1] (Script data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: ;\\uDBC0\\uDC00 [2] (CDATA section state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "eof-in-cdata")
        ),
        "test3.test: <\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("invalid-first-character-of-tag-name")
        ),
        "test3.test: <!\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("incorrectly-opened-comment")
        ),
        "test3.test: <!--\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("eof-in-comment")
        ),
        "test3.test: <!-- \\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("eof-in-comment")
        ),
        "test3.test: <!-- -\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("eof-in-comment")
        ),
        "test3.test: <!-- -\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "eof-in-comment")
        ),
        "test3.test: <!-- \\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "eof-in-comment")
        ),
        "test3.test: <!---\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("eof-in-comment")
        ),
        "test3.test: <!----\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("eof-in-comment")
        ),
        "test3.test: <!----\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "eof-in-comment")
        ),
        "test3.test: <!---\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "eof-in-comment")
        ),
        "test3.test: <!--\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "eof-in-comment")
        ),
        "test3.test: <!DOCTYPE\\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("missing-whitespace-before-doctype-name", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("missing-whitespace-before-doctype-name", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE\\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("missing-whitespace-before-doctype-name", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE \\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE \\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE \\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE a\\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE a\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE a\\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE a \\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("invalid-character-sequence-after-doctype-name")
        ),
        "test3.test: <!DOCTYPE a \\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("invalid-character-sequence-after-doctype-name")
        ),
        "test3.test: <!DOCTYPE a \\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("invalid-character-sequence-after-doctype-name")
        ),
        "test3.test: <!DOCTYPE a PUBLIC\\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("missing-quote-before-doctype-public-identifier")
        ),
        "test3.test: <!DOCTYPE a PUBLIC\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("missing-quote-before-doctype-public-identifier")
        ),
        "test3.test: <!DOCTYPE a PUBLIC\\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("missing-quote-before-doctype-public-identifier")
        ),
        "test3.test: <!DOCTYPE a PUBLIC\"\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-after-doctype-public-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE a PUBLIC\"\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-after-doctype-public-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE a PUBLIC'\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-after-doctype-public-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE a PUBLIC''\\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-after-doctype-public-keyword", "missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPE a PUBLIC''\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-after-doctype-public-keyword", "missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPE a PUBLIC''\\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-after-doctype-public-keyword", "missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPE a PUBLIC''\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-after-doctype-public-keyword", "missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPE a PUBLIC'\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-after-doctype-public-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE a PUBLIC\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-quote-before-doctype-public-identifier")
        ),
        "test3.test: <!DOCTYPE a SYSTEM\\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPE a SYSTEM\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPE a SYSTEM\\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPE a SYSTEM\"\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-after-doctype-system-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE a SYSTEM\"\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-after-doctype-system-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE a SYSTEM'\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-after-doctype-system-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE a SYSTEM''\\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-after-doctype-system-keyword", "unexpected-character-after-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPE a SYSTEM''\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-after-doctype-system-keyword", "unexpected-character-after-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPE a SYSTEM''\\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-after-doctype-system-keyword", "unexpected-character-after-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPE a SYSTEM''\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-after-doctype-system-keyword", "unexpected-character-after-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPE a SYSTEM'\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-after-doctype-system-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE a SYSTEM\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPE a a\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("invalid-character-sequence-after-doctype-name")
        ),
        "test3.test: <!DOCTYPE a a\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "invalid-character-sequence-after-doctype-name")
        ),
        "test3.test: <!DOCTYPE a \\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "invalid-character-sequence-after-doctype-name")
        ),
        "test3.test: <!DOCTYPE a\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE \\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPEa\\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPEa\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPEa\\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPEa \\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "invalid-character-sequence-after-doctype-name")
        ),
        "test3.test: <!DOCTYPEa \\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "invalid-character-sequence-after-doctype-name")
        ),
        "test3.test: <!DOCTYPEa \\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "invalid-character-sequence-after-doctype-name")
        ),
        "test3.test: <!DOCTYPEa PUBLIC\\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-quote-before-doctype-public-identifier")
        ),
        "test3.test: <!DOCTYPEa PUBLIC\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-quote-before-doctype-public-identifier")
        ),
        "test3.test: <!DOCTYPEa PUBLIC\\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-quote-before-doctype-public-identifier")
        ),
        "test3.test: <!DOCTYPEa PUBLIC\"\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-public-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPEa PUBLIC\"\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-public-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPEa PUBLIC'\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-public-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPEa PUBLIC''\\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-public-keyword", "missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPEa PUBLIC''\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-public-keyword", "missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPEa PUBLIC''\\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-public-keyword", "missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPEa PUBLIC''\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-public-keyword", "missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPEa PUBLIC'\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-public-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPEa PUBLIC\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-before-doctype-name", "missing-quote-before-doctype-public-identifier")
        ),
        "test3.test: <!DOCTYPEa SYSTEM\\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPEa SYSTEM\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPEa SYSTEM\\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPEa SYSTEM\"\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-system-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPEa SYSTEM\"\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-system-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPEa SYSTEM'\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-system-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPEa SYSTEM''\\u0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-system-keyword", "unexpected-character-after-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPEa SYSTEM''\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-system-keyword", "unexpected-character-after-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPEa SYSTEM''\\u001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-system-keyword", "unexpected-character-after-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPEa SYSTEM''\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-system-keyword", "unexpected-character-after-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPEa SYSTEM'\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-before-doctype-name", "missing-whitespace-after-doctype-system-keyword", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPEa SYSTEM\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-before-doctype-name", "missing-quote-before-doctype-system-identifier")
        ),
        "test3.test: <!DOCTYPEa a\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("missing-whitespace-before-doctype-name", "invalid-character-sequence-after-doctype-name")
        ),
        "test3.test: <!DOCTYPEa a\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-before-doctype-name", "invalid-character-sequence-after-doctype-name")
        ),
        "test3.test: <!DOCTYPEa \\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-before-doctype-name", "invalid-character-sequence-after-doctype-name")
        ),
        "test3.test: <!DOCTYPEa\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-before-doctype-name", "eof-in-doctype")
        ),
        "test3.test: <!DOCTYPE\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-before-doctype-name", "eof-in-doctype")
        ),
        "test3.test: <!\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "incorrectly-opened-comment")
        ),
        "test3.test: </\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("invalid-first-character-of-tag-name")
        ),
        "test3.test: </\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "invalid-first-character-of-tag-name")
        ),
        "test3.test: <?\\u000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("invalid-first-character-of-processing-instruction-target")
        ),
        "test3.test: <?\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "invalid-first-character-of-processing-instruction-target")
        ),
        "test3.test: <a\\u0008> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a\\u000B> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a\\u001F> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a \\u0008> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a \\u000B> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a \\u001F> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a\\u0008> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a\\u000B> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a\\u001F> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a \\u0008> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a \\u000B> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a \\u001F> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a A> (Data state)" to KnownFailure(
            DUPLICATE_ATTRIBUTE,
            listOf()
        ),
        "test3.test: <a a a> (Data state)" to KnownFailure(
            DUPLICATE_ATTRIBUTE,
            listOf()
        ),
        "test3.test: <a a \\uDBC0\\uDC00> (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: <a a=\\u0008> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a=\\u000B> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a=\\u001F> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a=\"\\u000B\"> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a=\"\\uDBC0\\uDC00\"> (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: <a a='\\u000B'> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a=''\\u0008> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("missing-whitespace-between-attributes")
        ),
        "test3.test: <a a=''\\u000B> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("missing-whitespace-between-attributes")
        ),
        "test3.test: <a a=''\\u001F> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("missing-whitespace-between-attributes")
        ),
        "test3.test: <a a=''A> (Data state)" to KnownFailure(
            DUPLICATE_ATTRIBUTE,
            listOf("missing-whitespace-between-attributes")
        ),
        "test3.test: <a a=''a> (Data state)" to KnownFailure(
            DUPLICATE_ATTRIBUTE,
            listOf("missing-whitespace-between-attributes")
        ),
        "test3.test: <a a=''\\uDBC0\\uDC00> (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "missing-whitespace-between-attributes")
        ),
        "test3.test: <a a='\\uDBC0\\uDC00'> (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: <a a=a\\u0008> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a=a\\u000B> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a=a\\u001F> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "test3.test: <a a=a\\uDBC0\\uDC00> (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: <a a=\\uDBC0\\uDC00> (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: <a a\\uDBC0\\uDC00> (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: <a \\uDBC0\\uDC00> (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: <a/\\u000B> (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf("unexpected-solidus-in-tag")
        ),
        "test3.test: <a/\\uDBC0\\uDC00> (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "unexpected-solidus-in-tag")
        ),
        "test3.test: <a\\uDBC0\\uDC00> (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: <\\uDBC0\\uDC00 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "invalid-first-character-of-tag-name")
        ),
        "test3.test: \\uDBC0\\uDC00 [1] (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: \\uDBC0\\uDC00 [1] (PLAINTEXT state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: \\uDBC0\\uDC00 [1] (RCDATA state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: \\uDBC0\\uDC00 [1] (RAWTEXT state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: \\uDBC0\\uDC00 [1] (Script data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "test3.test: \\uDBC0\\uDC00 [2] (CDATA section state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "eof-in-cdata")
        ),
        "test4.test: Duplicate different-case attributes (Data state)" to KnownFailure(
            DUPLICATE_ATTRIBUTE,
            listOf()
        ),
        "test4.test: Uppercase close tag attributes (Data state)" to KnownFailure(
            END_TAG_WITH_ATTRIBUTES,
            listOf()
        ),
        "test4.test: Duplicate close tag attributes (Data state)" to KnownFailure(
            DUPLICATE_ATTRIBUTE + "; " + END_TAG_WITH_ATTRIBUTES,
            listOf()
        ),
        "test4.test: Permitted slash but in close tag (Data state)" to KnownFailure(
            END_TAG_WITH_TRAILING_SOLIDUS,
            listOf()
        ),
        "test4.test: U+0080 in lookahead region (Data state)" to KnownFailure(
            CONTROL_CHARACTER + "; " + ERROR_ORDER,
            listOf("incorrectly-opened-comment")
        ),
        "test4.test: U+FDD1 in lookahead region (Data state)" to KnownFailure(
            ERROR_ORDER,
            listOf("noncharacter-in-input-stream", "incorrectly-opened-comment")
        ),
        "test4.test: U+1FFFF in lookahead region (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER + "; " + ERROR_ORDER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream", "incorrectly-opened-comment")
        ),
        "unicodeChars.test: Invalid Unicode character U+0001 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0002 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0003 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0004 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0005 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0006 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0007 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0008 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+000B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+000E (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+000F (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0010 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0011 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0012 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0013 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0014 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0015 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0016 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0017 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0018 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+0019 (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+001A (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+001B (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+001C (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+001D (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+001E (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+001F (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+007F (Data state)" to KnownFailure(
            CONTROL_CHARACTER,
            listOf()
        ),
        "unicodeChars.test: Invalid Unicode character U+1FFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+1FFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+2FFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+2FFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+3FFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+3FFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+4FFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+4FFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+5FFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+5FFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+6FFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+6FFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+7FFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+7FFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+8FFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+8FFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+9FFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+9FFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+AFFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+AFFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+BFFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+BFFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+CFFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+CFFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+DFFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+DFFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+EFFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+EFFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+FFFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+FFFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+10FFFE (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Invalid Unicode character U+10FFFF (Data state)" to KnownFailure(
            ASTRAL_NONCHARACTER,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+10000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+1FFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+20000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+2FFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+30000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+3FFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+40000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+4FFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+50000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+5FFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+60000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+6FFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+70000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+7FFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+80000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+8FFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+90000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+9FFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+A0000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+AFFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+B0000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+BFFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+C0000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+CFFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+D0000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+DFFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+E0000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+EFFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+F0000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+FFFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+100000 (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
        "unicodeChars.test: Valid Unicode character U+10FFFD (Data state)" to KnownFailure(
            SURROGATE_PAIR,
            listOf("surrogate-in-input-stream", "surrogate-in-input-stream")
        ),
    )

    val ids: Set<String> get() = failures.keys

    operator fun get(id: String): KnownFailure? = failures[id]
}
