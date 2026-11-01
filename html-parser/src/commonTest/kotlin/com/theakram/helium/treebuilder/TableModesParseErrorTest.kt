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
import com.theakram.helium.tree.TreeNode
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Parse errors of the table insertion modes ("in table" through "in cell") and "in template".
 *
 * Every input starts with a doctype and closes what it opens, so the only tree construction errors are the ones
 * under test.
 */
internal class TableModesParseErrorTest {

    private class Result(val treeErrors: Int, val tokenizerErrors: Int, val body: String, val head: String)

    private fun parse(html: String): Result = HTMLParser().run {
        val document = parse("<!DOCTYPE html>$html")
        val html = document.findChildElement("html")!!
        Result(
            treeErrors = errors.count { it.code == null },
            tokenizerErrors = errors.count { it.code != null },
            body = html.findChildElement("body")!!.children.joinToString("\n") { it.toTreeString() },
            head = html.findChildElement("head")!!.children.joinToString("\n") { it.toTreeString() }
        )
    }

    private fun treeErrors(html: String): Int = parse(html).treeErrors

    // in table

    @Test
    fun `doctype in table is a parse error`() {
        assertEquals(1, treeErrors("<table><!DOCTYPE html></table>"))
    }

    @Test
    fun `table start tag in table is a parse error`() {
        val result = parse("<table><table></table>")

        assertEquals(1, result.treeErrors)
        assertEquals("| <table>\n| <table>", result.body)
    }

    @Test
    fun `table start tag in table is a parse error before the scope check`() {
        // Template contents have no table in table scope, so the table is ignored, after the parse error
        val result = parse("<template><tbody><table></template>")

        assertEquals(1, result.treeErrors)
        assertEquals("| <template>\n|   content\n|     <tbody>", result.head)
    }

    @Test
    fun `table end tag without a table in table scope is a parse error`() {
        // The caption end tag reprocesses </table> in "in table", where the template hides the outer stack
        val result = parse("<template><caption></table></template>")

        assertEquals(1, result.treeErrors)
    }

    @Test
    fun `ignored end tags in table are parse errors`() {
        assertEquals(
            11,
            treeErrors(
                "<table></body></caption></col></colgroup></html></tbody></td></tfoot></th></thead></tr></table>"
            )
        )
    }

    @Test
    fun `hidden input in table is a parse error and stays in the table`() {
        val result = parse("<table><input type=HIDDEN></table>")

        assertEquals(1, result.treeErrors)
        assertEquals("| <table>\n|   <input>\n|     type=\"HIDDEN\"", result.body)
    }

    @Test
    fun `other input in table is foster parented with one parse error`() {
        val result = parse("<table><input type=text></table>")

        assertEquals(1, result.treeErrors)
        assertEquals("| <input>\n|   type=\"text\"\n| <table>", result.body)
    }

    @Test
    fun `form in table is a parse error whether or not it is ignored`() {
        val inserted = parse("<table><form></table>")
        assertEquals(1, inserted.treeErrors)
        assertEquals("| <table>\n|   <form>", inserted.body)

        val ignored = parse("<form><table><form></table></form>")
        assertEquals(1, ignored.treeErrors)
        assertEquals("| <form>\n|   <table>", ignored.body)
    }

    @Test
    fun `anything else in table is a parse error per token`() {
        // <div> and </div> both go through the "anything else" entry
        val result = parse("<table><div></div></table>")

        assertEquals(2, result.treeErrors)
        assertEquals("| <div>\n| <table>", result.body)
    }

    // in table text

    @Test
    fun `non-whitespace table text is one parse error plus one per reprocessed token`() {
        // "in table text" reports one parse error and then reprocesses each of the three pending tokens with the
        // "anything else" entry of "in table", which reports one parse error each
        val result = parse("<table>a b</table>")

        assertEquals(4, result.treeErrors)
        assertEquals("| \"a b\"\n| <table>", result.body)
    }

    @Test
    fun `whitespace-only table text is not a parse error`() {
        val result = parse("<table> \n</table>")

        assertEquals(0, result.treeErrors)
        assertEquals("| <table>\n|   \" \n\"", result.body)
    }

    @Test
    fun `null character in table text is a parse error and is ignored`() {
        val result = parse("<table>\u0000</table>")

        // The tokenizer reports unexpected-null-character, and "in table text" ignores the token with its own error
        assertEquals(1, result.tokenizerErrors)
        assertEquals(1, result.treeErrors)
        assertEquals("| <table>", result.body)
    }

    @Test
    fun `characters in a foster parented element skip in table text`() {
        val result = parse("<table><b>x</b></table>")

        // The current node is the b element when "x" arrives, so all three tokens use the anything else entry
        assertEquals(3, result.treeErrors)
        assertEquals("| <b>\n|   \"x\"\n| <table>", result.body)
    }

    // in caption

    @Test
    fun `caption end tag with only implied end tags open is not a parse error`() {
        assertEquals(0, treeErrors("<table><caption><p>x</caption></table>"))
    }

    @Test
    fun `caption end tag with another element open is a parse error`() {
        val result = parse("<table><caption><div>x</caption></table>")

        assertEquals(1, result.treeErrors)
        assertEquals("| <table>\n|   <caption>\n|     <div>\n|       \"x\"", result.body)
    }

    @Test
    fun `table end tag in caption reports a misnested caption`() {
        assertEquals(1, treeErrors("<table><caption><div>x</table>"))
    }

    @Test
    fun `caption end tag without a caption in table scope is a parse error`() {
        // Only the fragment case reaches "in caption" without a caption element on the stack
        val parser = HTMLParser()

        parser.parseFragment("</caption>", TreeNode.createElement(localName = "caption"))

        assertEquals(1, parser.errors.count { it.code == null })
    }

    @Test
    fun `ignored end tags in caption are parse errors`() {
        assertEquals(10, treeErrors(
            "<table><caption></body></col></colgroup></html></tbody></td></tfoot></th></thead></tr></caption></table>"
        ))
    }

    // in column group

    @Test
    fun `doctype and col end tag in column group are parse errors`() {
        assertEquals(2, treeErrors("<table><colgroup><!DOCTYPE html></col></colgroup></table>"))
    }

    @Test
    fun `colgroup end tag when the current node is not a colgroup is a parse error`() {
        // In a template, <col> switches to "in column group" with the template as the current node
        assertEquals(1, treeErrors("<template><col></colgroup></template>"))
    }

    @Test
    fun `anything else when the current node is not a colgroup is a parse error and ignored`() {
        val result = parse("<body><template><col>ab</template>")

        assertEquals(2, result.treeErrors)
        assertEquals("| <template>\n|   content\n|     <col>", result.body)
    }

    // in table body

    @Test
    fun `cell start tag in table body is a parse error`() {
        val result = parse("<table><tbody><td></td></tbody></table>")

        assertEquals(1, result.treeErrors)
        assertEquals("| <table>\n|   <tbody>\n|     <tr>\n|       <td>", result.body)
    }

    @Test
    fun `table section end tag not in table scope is a parse error in table body`() {
        assertEquals(1, treeErrors("<table><tbody></thead></table>"))
    }

    @Test
    fun `table body closing start tag without a table section in scope is a parse error`() {
        // The caption start tag closes the row and is reprocessed in "in table body", where the template hides
        // everything outside it
        assertEquals(1, treeErrors("<template><tr><caption></template>"))
    }

    @Test
    fun `ignored end tags in table body are parse errors`() {
        assertEquals(
            8,
            treeErrors("<table><tbody></body></caption></col></colgroup></html></td></th></tr></table>")
        )
    }

    // in row

    @Test
    fun `tr end tag without a tr in table scope is a parse error`() {
        assertEquals(1, treeErrors("<template><td></td></tr></template>"))
    }

    @Test
    fun `table section end tag in row is a parse error only when the section is not in scope`() {
        assertEquals(0, treeErrors("<table><tr></tbody></table>"))
        assertEquals(1, treeErrors("<table><tr></thead></table>"))
    }

    @Test
    fun `row closing start tag without a tr in table scope is a parse error`() {
        assertEquals(1, treeErrors("<template><td></td><caption></template>"))
    }

    @Test
    fun `ignored end tags in row are parse errors`() {
        assertEquals(7, treeErrors("<table><tr></body></caption></col></colgroup></html></td></th></table>"))
    }

    // in cell

    @Test
    fun `cell end tag with another element open is a parse error`() {
        val result = parse("<table><tr><td><div>x</td></table>")

        assertEquals(1, result.treeErrors)
        assertEquals("| <table>\n|   <tbody>\n|     <tr>\n|       <td>\n|         <div>\n|           \"x\"", result.body)
    }

    @Test
    fun `cell end tag not in table scope is a parse error`() {
        assertEquals(1, treeErrors("<table><tr><td></th></td></table>"))
    }

    @Test
    fun `closing the cell with another element open is a parse error`() {
        // <tr> closes the cell while the div is the current node
        val result = parse("<table><tr><td><div><tr></table>")

        assertEquals(1, result.treeErrors)
        assertEquals(
            "| <table>\n|   <tbody>\n|     <tr>\n|       <td>\n|         <div>\n|     <tr>",
            result.body
        )
    }

    @Test
    fun `cell closing end tag not in table scope is a parse error`() {
        assertEquals(1, treeErrors("<template><td></table></td></template>"))
    }

    @Test
    fun `ignored end tags in cell are parse errors`() {
        assertEquals(5, treeErrors("<table><tr><td></body></caption></col></colgroup></html></td></table>"))
    }

    // in template

    @Test
    fun `other end tags in template are parse errors`() {
        assertEquals(1, treeErrors("<template></div></template>"))
    }

    @Test
    fun `end of file in template is a parse error`() {
        assertEquals(1, treeErrors("<template>"))
    }

    @Test
    fun `end of file in a template fragment context stops parsing without a parse error`() {
        val parser = HTMLParser()
        val template = TreeNode.createElement(localName = "template")

        val fragment = parser.parseFragment("<td>", template)

        assertEquals(0, parser.errors.count { it.code == null })
        assertEquals(listOf("td"), fragment.children.map { it.localName })
    }

    @Test
    fun `characters after a colgroup are reprocessed in table, without an error of their own`() {
        // The colgroup's "anything else" pops it without an error; the pending x then gives in table text's error
        // and in table's "anything else" error
        val result = parse("<table><colgroup>x</table>")

        assertEquals(2, result.treeErrors)
        assertEquals("| \"x\"\n| <table>\n|   <colgroup>", result.body)
    }

    @Test
    fun `a table closed in a cell returns to in cell`() {
        val result = parse("<table><tr><td><table></table>x</td></tr></table>")

        assertEquals(0, result.treeErrors)
        assertEquals(
            "| <table>\n|   <tbody>\n|     <tr>\n|       <td>\n|         <table>\n|         \"x\"",
            result.body
        )
    }

    @Test
    fun `col, tr and cell start tags in template contents switch to their table modes`() {
        assertEquals("| <template>\n|   content\n|     <col>", parse("<template><col></template>").head)
        assertEquals("| <template>\n|   content\n|     <tr>", parse("<template><tr></template>").head)
        assertEquals("| <template>\n|   content\n|     <td>", parse("<template><td></template>").head)
        assertEquals(0, treeErrors("<template><col></template><template><tr></template><template><td></template>"))
    }
}
