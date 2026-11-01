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

private const val IN_TABLE_TEXT =
    "In table text's \"Anything else\" entry is a parse error of its own (\"then this is a parse error: reprocess " +
        "the character tokens in the pending table character tokens list using the rules given in the \"anything " +
        "else\" entry in the \"in table\" insertion mode\") besides the \"Parse error.\" that entry reports for each " +
        "character, and html5lib lists only the per-character errors."

private const val PROCESSING_INSTRUCTION =
    "The tokenizer's processing instruction states report their own parse error (\"This is an " +
        "eof-in-processing-instruction parse error.\", \"This is an " +
        "invalid-first-character-of-processing-instruction-target parse error.\", \"This is a " +
        "disallowed-processing-instruction-target parse error.\"), which html5lib lists only in #new-errors while " +
        "its #errors lists just the missing DOCTYPE."

private const val NULL_CHARACTER =
    "In body's and foreign content's \"A character token that is U+0000 NULL\" entries are tree construction parse " +
        "errors (\"Parse error. Ignore the token.\" and \"Parse error. Insert a U+FFFD REPLACEMENT CHARACTER " +
        "character.\") after the tokenizer's unexpected-null-character, and html5lib's #new-errors lists only the " +
        "tokenizer's."

private const val FOREIGN_END_TAG =
    "Foreign content's \"Any other end tag\" steps end with \"Otherwise, process the token according to the rules " +
        "given in the section corresponding to the current insertion mode in HTML content\", and html5lib leaves out " +
        "the parse error that rule then reports (in body's \"Otherwise, if node is in the special category, then " +
        "this is a parse error; ignore the token, and return.\" or in table body's \"If the stack of open elements " +
        "does not have a tbody, thead, or tfoot element in table scope, this is a parse error; ignore the token.\")."

private const val ADOPTION_AGENCY_PASSES =
    "The adoption agency algorithm's \"If formattingElement is not the current node, this is a parse error. (But " +
        "do not return.)\" is a step of its \"While true:\" outer loop, so it is reported on each of the two passes " +
        "here, and html5lib lists neither."

private const val NO_SUCH_ERROR =
    "html5lib lists an end-tag-too-early-named error the spec doesn't have: in table's end tag \"table\" rule, with " +
        "a table element in table scope, only says \"Pop elements from this stack until a table element has been " +
        "popped from the stack. Reset the insertion mode appropriately.\", even though that pops the open marquee."

private const val TEMPLATE_END_TAG =
    "The end tag \"template\" rule says \"If the current node is not a template element, then this is a parse " +
        "error.\" after it generates all implied end tags thoroughly, which leaves an open form element the current " +
        "node, and html5lib leaves that error out."

private const val TEMPLATE_END_TAG_AND_EOF =
    "html5lib leaves out both the end tag \"template\" rule's \"If the current node is not a template element, then " +
        "this is a parse error.\" for the inner form still open and in body's end-of-file \"If there is a node in " +
        "the stack of open elements that is not either a dd element, [...] then this is a parse error.\" for the " +
        "outer form still open."

private const val NO_ERRORS_LISTED =
    "The test's #errors section is empty and it has no #new-errors, yet its input has parse errors, starting with " +
        "the initial insertion mode's \"If the document is not an iframe srcdoc document, then this is a parse " +
        "error\" for the missing DOCTYPE."

/**
 * Tree construction tests whose error count differs from the number their #errors section lists, because html5lib's
 * list differs from the spec, keyed by "file:line (script on|off)", with the reason and the count Helium reports.
 */
internal object KnownErrorCountDifferences {

    class KnownDifference(val reason: String, val actualCount: Int)

    /** Files whose tests list no errors at all, so their counts say nothing. */
    val filesWithoutErrorLists = setOf("processing-instructions.dat")

    private val differences = mapOf(
        "tests1.dat:426 (script on)" to KnownDifference(IN_TABLE_TEXT, 16),
        "tests1.dat:426 (script off)" to KnownDifference(IN_TABLE_TEXT, 16),
        "tests1.dat:1141 (script on)" to KnownDifference(IN_TABLE_TEXT, 9),
        "tests1.dat:1141 (script off)" to KnownDifference(IN_TABLE_TEXT, 9),
        "tests1.dat:1173 (script on)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tests1.dat:1173 (script off)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tests1.dat:1196 (script on)" to KnownDifference(IN_TABLE_TEXT, 9),
        "tests1.dat:1196 (script off)" to KnownDifference(IN_TABLE_TEXT, 9),
        "tests2.dat:189 (script on)" to KnownDifference(IN_TABLE_TEXT, 6),
        "tests2.dat:189 (script off)" to KnownDifference(IN_TABLE_TEXT, 6),
        "tests6.dat:336 (script on)" to KnownDifference(IN_TABLE_TEXT, 6),
        "tests6.dat:336 (script off)" to KnownDifference(IN_TABLE_TEXT, 6),
        "tests7.dat:97 (script on)" to KnownDifference(IN_TABLE_TEXT, 3),
        "tests7.dat:97 (script off)" to KnownDifference(IN_TABLE_TEXT, 3),
        "tests7.dat:238 (script on)" to KnownDifference(IN_TABLE_TEXT, 3),
        "tests7.dat:238 (script off)" to KnownDifference(IN_TABLE_TEXT, 3),
        "tests7.dat:383 (script on)" to KnownDifference(IN_TABLE_TEXT, 7),
        "tests7.dat:383 (script off)" to KnownDifference(IN_TABLE_TEXT, 7),
        "tests7.dat:407 (script on)" to KnownDifference(IN_TABLE_TEXT, 7),
        "tests7.dat:407 (script off)" to KnownDifference(IN_TABLE_TEXT, 7),
        "tests7.dat:424 (script on)" to KnownDifference(IN_TABLE_TEXT, 8),
        "tests7.dat:424 (script off)" to KnownDifference(IN_TABLE_TEXT, 8),
        "tests8.dat:104 (script on)" to KnownDifference(IN_TABLE_TEXT, 4),
        "tests8.dat:104 (script off)" to KnownDifference(IN_TABLE_TEXT, 4),
        "tests8.dat:117 (script on)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tests8.dat:117 (script off)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tests15.dat:90 (script on)" to KnownDifference(IN_TABLE_TEXT, 4),
        "tests15.dat:90 (script off)" to KnownDifference(IN_TABLE_TEXT, 4),
        "tests15.dat:105 (script on)" to KnownDifference(IN_TABLE_TEXT, 3),
        "tests15.dat:105 (script off)" to KnownDifference(IN_TABLE_TEXT, 3),
        "tests15.dat:118 (script on)" to KnownDifference(IN_TABLE_TEXT, 4),
        "tests15.dat:118 (script off)" to KnownDifference(IN_TABLE_TEXT, 4),
        "tests15.dat:132 (script on)" to KnownDifference(IN_TABLE_TEXT, 3),
        "tests15.dat:132 (script off)" to KnownDifference(IN_TABLE_TEXT, 3),
        "tests15.dat:147 (script on)" to KnownDifference(IN_TABLE_TEXT, 2),
        "tests15.dat:147 (script off)" to KnownDifference(IN_TABLE_TEXT, 2),
        "tests18.dat:369 (script on)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tests18.dat:369 (script off)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tests18.dat:388 (script on)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tests18.dat:388 (script off)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tests19.dat:319 (script on)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tests19.dat:319 (script off)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tests19.dat:348 (script on)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tests19.dat:348 (script off)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tests19.dat:1160 (script on)" to KnownDifference(IN_TABLE_TEXT, 11),
        "tests19.dat:1160 (script off)" to KnownDifference(IN_TABLE_TEXT, 11),
        "tests19.dat:1283 (script on)" to KnownDifference(IN_TABLE_TEXT, 13),
        "tests19.dat:1283 (script off)" to KnownDifference(IN_TABLE_TEXT, 13),
        "tests26.dat:71 (script on)" to KnownDifference(IN_TABLE_TEXT, 15),
        "tests26.dat:71 (script off)" to KnownDifference(IN_TABLE_TEXT, 15),
        "adoption01.dat:186 (script on)" to KnownDifference(IN_TABLE_TEXT, 6),
        "adoption01.dat:186 (script off)" to KnownDifference(IN_TABLE_TEXT, 6),
        "adoption01.dat:208 (script on)" to KnownDifference(IN_TABLE_TEXT, 6),
        "adoption01.dat:208 (script off)" to KnownDifference(IN_TABLE_TEXT, 6),
        "domjs-unsafe.dat:457 (script on)" to KnownDifference(IN_TABLE_TEXT, 6),
        "domjs-unsafe.dat:457 (script off)" to KnownDifference(IN_TABLE_TEXT, 6),
        "namespace-sensitivity.dat:1 (script on)" to KnownDifference(IN_TABLE_TEXT, 7),
        "namespace-sensitivity.dat:1 (script off)" to KnownDifference(IN_TABLE_TEXT, 7),
        "pending-spec-changes-plain-text-unsafe.dat:1 (script on)" to KnownDifference(IN_TABLE_TEXT, 19),
        "pending-spec-changes-plain-text-unsafe.dat:1 (script off)" to KnownDifference(IN_TABLE_TEXT, 19),
        "tables01.dat:45 (script on)" to KnownDifference(IN_TABLE_TEXT, 7),
        "tables01.dat:45 (script off)" to KnownDifference(IN_TABLE_TEXT, 7),
        "tables01.dat:311 (script on)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tables01.dat:311 (script off)" to KnownDifference(IN_TABLE_TEXT, 5),
        "template.dat:1430 (script on)" to KnownDifference(IN_TABLE_TEXT, 7),
        "template.dat:1430 (script off)" to KnownDifference(IN_TABLE_TEXT, 7),
        "template.dat:1865 (script on)" to KnownDifference(IN_TABLE_TEXT, 2),
        "template.dat:1865 (script off)" to KnownDifference(IN_TABLE_TEXT, 2),
        "webkit02.dat:70 (script on)" to KnownDifference(IN_TABLE_TEXT, 5),
        "webkit02.dat:70 (script off)" to KnownDifference(IN_TABLE_TEXT, 5),
        "tests1.dat:551 (script on)" to KnownDifference(PROCESSING_INSTRUCTION, 2),
        "tests1.dat:551 (script off)" to KnownDifference(PROCESSING_INSTRUCTION, 2),
        "tests1.dat:562 (script on)" to KnownDifference(PROCESSING_INSTRUCTION, 2),
        "tests1.dat:562 (script off)" to KnownDifference(PROCESSING_INSTRUCTION, 2),
        "comments01.dat:155 (script on)" to KnownDifference(PROCESSING_INSTRUCTION, 2),
        "comments01.dat:155 (script off)" to KnownDifference(PROCESSING_INSTRUCTION, 2),
        "comments01.dat:168 (script on)" to KnownDifference(PROCESSING_INSTRUCTION, 2),
        "comments01.dat:168 (script off)" to KnownDifference(PROCESSING_INSTRUCTION, 2),
        "comments01.dat:180 (script on)" to KnownDifference(PROCESSING_INSTRUCTION, 2),
        "comments01.dat:180 (script off)" to KnownDifference(PROCESSING_INSTRUCTION, 2),
        "plain-text-unsafe.dat:669 (script on)" to KnownDifference(NULL_CHARACTER, 4),
        "plain-text-unsafe.dat:669 (script off)" to KnownDifference(NULL_CHARACTER, 4),
        "plain-text-unsafe.dat:680 (script on)" to KnownDifference(NULL_CHARACTER, 4),
        "plain-text-unsafe.dat:680 (script off)" to KnownDifference(NULL_CHARACTER, 4),
        "plain-text-unsafe.dat:691 (script on)" to KnownDifference(NULL_CHARACTER, 4),
        "plain-text-unsafe.dat:691 (script off)" to KnownDifference(NULL_CHARACTER, 4),
        "plain-text-unsafe.dat:702 (script on)" to KnownDifference(NULL_CHARACTER, 2),
        "plain-text-unsafe.dat:702 (script off)" to KnownDifference(NULL_CHARACTER, 2),
        "plain-text-unsafe.dat:778 (script on)" to KnownDifference(NULL_CHARACTER, 4),
        "plain-text-unsafe.dat:778 (script off)" to KnownDifference(NULL_CHARACTER, 4),
        "foreign-fragment.dat:32 (script on)" to KnownDifference(FOREIGN_END_TAG, 3),
        "foreign-fragment.dat:32 (script off)" to KnownDifference(FOREIGN_END_TAG, 3),
        "math.dat:67 (script on)" to KnownDifference(FOREIGN_END_TAG, 4),
        "math.dat:67 (script off)" to KnownDifference(FOREIGN_END_TAG, 4),
        "math.dat:80 (script on)" to KnownDifference(FOREIGN_END_TAG, 4),
        "math.dat:80 (script off)" to KnownDifference(FOREIGN_END_TAG, 4),
        "math.dat:93 (script on)" to KnownDifference(FOREIGN_END_TAG, 4),
        "math.dat:93 (script off)" to KnownDifference(FOREIGN_END_TAG, 4),
        "svg.dat:67 (script on)" to KnownDifference(FOREIGN_END_TAG, 4),
        "svg.dat:67 (script off)" to KnownDifference(FOREIGN_END_TAG, 4),
        "svg.dat:80 (script on)" to KnownDifference(FOREIGN_END_TAG, 4),
        "svg.dat:80 (script off)" to KnownDifference(FOREIGN_END_TAG, 4),
        "svg.dat:93 (script on)" to KnownDifference(FOREIGN_END_TAG, 4),
        "svg.dat:93 (script off)" to KnownDifference(FOREIGN_END_TAG, 4),
        "search-element.dat:66 (script on)" to KnownDifference(ADOPTION_AGENCY_PASSES, 4),
        "search-element.dat:66 (script off)" to KnownDifference(ADOPTION_AGENCY_PASSES, 4),
        "adoption02.dat:41 (script on)" to KnownDifference(NO_SUCH_ERROR, 4),
        "adoption02.dat:41 (script off)" to KnownDifference(NO_SUCH_ERROR, 4),
        "adoption02.dat:58 (script on)" to KnownDifference(NO_SUCH_ERROR, 3),
        "adoption02.dat:58 (script off)" to KnownDifference(NO_SUCH_ERROR, 3),
        "template.dat:1750 (script on)" to KnownDifference(TEMPLATE_END_TAG, 2),
        "template.dat:1750 (script off)" to KnownDifference(TEMPLATE_END_TAG, 2),
        "template.dat:1773 (script on)" to KnownDifference(TEMPLATE_END_TAG, 2),
        "template.dat:1773 (script off)" to KnownDifference(TEMPLATE_END_TAG, 2),
        "template.dat:1788 (script on)" to KnownDifference(TEMPLATE_END_TAG, 3),
        "template.dat:1788 (script off)" to KnownDifference(TEMPLATE_END_TAG, 3),
        "template.dat:1803 (script on)" to KnownDifference(TEMPLATE_END_TAG_AND_EOF, 4),
        "template.dat:1803 (script off)" to KnownDifference(TEMPLATE_END_TAG_AND_EOF, 4),
        "html5test-com.dat:149 (script on)" to KnownDifference(NO_ERRORS_LISTED, 2),
        "html5test-com.dat:149 (script off)" to KnownDifference(NO_ERRORS_LISTED, 2),
        "html5test-com.dat:160 (script on)" to KnownDifference(NO_ERRORS_LISTED, 2),
        "html5test-com.dat:160 (script off)" to KnownDifference(NO_ERRORS_LISTED, 2),
        "html5test-com.dat:171 (script on)" to KnownDifference(NO_ERRORS_LISTED, 3),
        "html5test-com.dat:171 (script off)" to KnownDifference(NO_ERRORS_LISTED, 3),
        "html5test-com.dat:183 (script on)" to KnownDifference(NO_ERRORS_LISTED, 2),
        "html5test-com.dat:183 (script off)" to KnownDifference(NO_ERRORS_LISTED, 2),
        "html5test-com.dat:193 (script on)" to KnownDifference(NO_ERRORS_LISTED, 2),
        "html5test-com.dat:193 (script off)" to KnownDifference(NO_ERRORS_LISTED, 2),
        "html5test-com.dat:204 (script on)" to KnownDifference(NO_ERRORS_LISTED, 3),
        "html5test-com.dat:204 (script off)" to KnownDifference(NO_ERRORS_LISTED, 3),
        "webkit02.dat:765 (script on)" to KnownDifference(NO_ERRORS_LISTED, 3),
        "webkit02.dat:765 (script off)" to KnownDifference(NO_ERRORS_LISTED, 3)
    )

    val ids: Set<String> get() = differences.keys

    operator fun get(id: String): KnownDifference? = differences[id]
}
