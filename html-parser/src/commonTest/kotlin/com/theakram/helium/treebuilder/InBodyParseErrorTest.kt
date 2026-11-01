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

// Each input follows a doctype and a body start tag, so that only the "in body" insertion mode can report tree
// construction errors
internal class InBodyParseErrorTest {

    private companion object {
        const val PREFIX = "<!DOCTYPE html><body>"
    }

    // The codes of the parse errors, in order: null for those of tree construction
    private fun errors(html: String): List<String?> = HTMLParser().run {
        parse("$PREFIX$html")
        errors.map { it.code }
    }

    private fun body(html: String): TreeNode =
        HTMLParser().parse("$PREFIX$html").findChildElement("html")!!.findChildElement("body")!!

    @Test
    fun `well-formed markup has no parse errors`() {
        assertEquals(emptyList(), errors("<div><p>a<b>b</b><ul><li>c</ul><h1>d</h1></div><p>e"))
    }

    @Test
    fun `a null character is a parse error of the tokenizer and of tree construction`() {
        assertEquals(listOf("unexpected-null-character", null), errors("<p>a\u0000b"))
    }

    @Test
    fun `doctype and html and body and frameset start tags in the body are parse errors`() {
        assertEquals(listOf(null), errors("<p><!DOCTYPE html>"))
        assertEquals(listOf(null), errors("<p><html lang=en>"))
        assertEquals(listOf(null), errors("<p><body class=x>"))
        assertEquals(listOf(null), errors("<p>x<frameset>"))
    }

    @Test
    fun `end of file with an element that is not implied closed open is a parse error`() {
        assertEquals(emptyList(), errors("<p>x"))
        assertEquals(emptyList(), errors("<li>x"))
        assertEquals(listOf(null), errors("<div>x"))
    }

    @Test
    fun `end of file with a foreign element open is a parse error`() {
        assertEquals(listOf(null), errors("<svg>"))
        assertEquals(listOf(null), errors("<math><mi>"))
    }

    @Test
    fun `body and html end tags report elements left open and a body that is not in scope`() {
        assertEquals(emptyList(), errors("<p>x</body>"))
        assertEquals(listOf(null), errors("<div>x</body>"))
        assertEquals(listOf(null), errors("<div>x</html>"))
        // The object is a scope boundary, so the end tag is ignored; it is still open at the end of the file
        assertEquals(listOf(null, null), errors("<object></body>"))
    }

    @Test
    fun `end tags without a matching element in scope are parse errors`() {
        assertEquals(listOf(null), errors("</div>"))
        assertEquals(listOf(null), errors("</form>"))
        assertEquals(listOf(null), errors("</li>"))
        assertEquals(listOf(null), errors("</dd>"))
        assertEquals(listOf(null), errors("</h3>"))
        assertEquals(listOf(null), errors("</object>"))
    }

    @Test
    fun `end tags that close other elements are parse errors`() {
        assertEquals(listOf(null), errors("<div><span></div>"))
        assertEquals(listOf(null), errors("<form><span></form></span>"))
        assertEquals(listOf(null), errors("<ul><li><span></li></ul>"))
        assertEquals(listOf(null), errors("<dl><dd><span></dd></dl>"))
        assertEquals(listOf(null), errors("<object><span></object>"))
    }

    @Test
    fun `a p end tag without a p element in button scope inserts one and is a parse error`() {
        assertEquals(listOf(null), errors("</p>"))
        assertEquals(listOf("p"), body("</p>").children.map { it.localName })
    }

    @Test
    fun `closing a p element that has other elements open is a parse error`() {
        assertEquals(listOf(null), errors("<p><span><div></div>"))
    }

    @Test
    fun `a heading end tag must match the current heading's tag name`() {
        assertEquals(emptyList(), errors("<h1>x</h1>"))
        assertEquals(listOf(null), errors("<h1>x</h2>"))
        assertEquals(listOf("h1", null), body("<h1>x</h2>y").children.map { it.localName })
    }

    @Test
    fun `a heading start tag closes a heading that is the current node`() {
        assertEquals(listOf(null), errors("<h1><h2></h2>"))
        assertEquals(listOf("h1", "h2"), body("<h1><h2>").children.map { it.localName })
    }

    @Test
    fun `li and dd and dt start tags that close an item with other elements open are parse errors`() {
        assertEquals(listOf(null), errors("<li><span><li>"))
        assertEquals(listOf(null), errors("<dd><span><dt>"))
    }

    @Test
    fun `a button start tag with a button in scope is a parse error`() {
        // The second error is the end of the file with the second button open
        assertEquals(listOf(null, null), errors("<button><button>"))
        assertEquals(listOf("button", "button"), body("<button><button>").children.map { it.localName })
    }

    @Test
    fun `a form start tag with an open form is a parse error`() {
        assertEquals(listOf(null), errors("<form><form></form>"))
    }

    @Test
    fun `an a start tag with an a element in the list of active formatting elements is a parse error`() {
        assertEquals(listOf(null), errors("<a>x<a>y</a>"))
        assertEquals(listOf("a", "a"), body("<a>x<a>y</a>").children.map { it.localName })
    }

    @Test
    fun `a nobr start tag with a nobr in scope is a parse error`() {
        assertEquals(listOf(null), errors("<nobr>x<nobr>y</nobr>"))
    }

    @Test
    fun `a br end tag is a parse error and inserts a br element`() {
        assertEquals(listOf(null), errors("</br>"))
        assertEquals(listOf("br"), body("</br>").children.map { it.localName })
    }

    @Test
    fun `start tags of table parts are parse errors and ignored`() {
        assertEquals(listOf(null), errors("<caption>"))
        assertEquals(listOf(null, null), errors("<tr><td>"))
    }

    @Test
    fun `select and input start tags in a select are parse errors`() {
        assertEquals(listOf(null), errors("<select><select>"))
        assertEquals(listOf(null), errors("<select><input>"))
    }

    // In these, the second error is the end of the file with the select and the span open
    @Test
    fun `option and optgroup and hr start tags with an option still in scope in a select are parse errors`() {
        assertEquals(emptyList(), errors("<select><option>a<option>b<optgroup><option>c<hr></select>"))
        assertEquals(listOf(null, null), errors("<select><option><span><option>"))
        assertEquals(listOf(null, null), errors("<select><option><span><optgroup>"))
        assertEquals(listOf(null, null), errors("<select><option><span><hr>"))
    }

    @Test
    fun `an image start tag is a parse error and is reprocessed as an img start tag`() {
        assertEquals(listOf(null), errors("<image src=a>"))
        val img = body("<image src=a>").children.single()
        assertEquals("img", img.localName)
        assertEquals("a", img.getAttribute("src"))
    }

    @Test
    fun `an image start tag in a table is reprocessed as img in the table insertion mode`() {
        assertEquals(listOf("img", "table"), body("<table><image></table>").children.map { it.localName })
        // in table's "anything else", image's own error, then in table's "anything else" again for the img
        assertEquals(listOf(null, null, null), errors("<table><image></table>"))
    }

    @Test
    fun `the adoption agency reports a formatting element that is not the current node once per outer loop pass`() {
        // Two passes of the outer loop each find the b not current; the third error is the div left open at EOF
        assertEquals(listOf(null, null, null), errors("<b><div><div></b>"))
    }

    @Test
    fun `noah's ark compares attributes regardless of their order`() {
        // The fourth b matches the first three, so the earliest is removed; three are reconstructed after the p
        val body = body("<p><b class=x id=y><b class=x id=y><b class=x id=y><b id=y class=x>a</p>b")
        var depth = 0
        var node = body.children[1]
        while (node.localName == "b") {
            depth++
            node = node.firstChild!!
        }
        assertEquals(3, depth)
    }

    @Test
    fun `rb and rtc start tags require the current node to be a ruby element`() {
        assertEquals(emptyList(), errors("<ruby>a<rb>b</ruby>"))
        assertEquals(listOf(null), errors("<ruby><span><rtc></span></ruby>"))
    }

    @Test
    fun `rp and rt start tags require the current node to be an rtc or ruby element`() {
        assertEquals(emptyList(), errors("<ruby><rtc><rt>a</ruby>"))
        assertEquals(emptyList(), errors("<ruby><rt>a<rp>b</ruby>"))
        assertEquals(listOf(null), errors("<ruby><span><rt></span></ruby>"))
    }

    @Test
    fun `ruby annotation start tags without a ruby element in scope are parse errors`() {
        // The current node is the body, which is neither a ruby nor an rtc element
        assertEquals(listOf(null), errors("<rb>"))
        assertEquals(listOf(null), errors("<rtc>"))
        assertEquals(listOf(null), errors("<rp>"))
        assertEquals(listOf(null), errors("<rt>"))
        // A ruby element outside the default scope doesn't count, but an rtc current node still allows rt
        assertEquals(listOf(null), errors("<ruby><table><tr><td><rt></table></ruby>"))
        assertEquals(listOf(null), errors("<rtc><rt>"))
    }

    @Test
    fun `any other end tag reports closing other elements and a special element in the way`() {
        assertEquals(emptyList(), errors("<span><em></em></span>"))
        assertEquals(listOf(null), errors("<div><span><em></span></div>"))
        // The div is special, so the span end tag is ignored
        assertEquals(listOf(null), errors("<span><div></span></div></span>"))
        assertEquals(listOf(null), errors("</sarcasm>"))
    }

    @Test
    fun `the adoption agency algorithm reports a formatting element that is not the current node`() {
        assertEquals(listOf(null), errors("<p><b><i>x</b>y</i></p>"))
    }

    @Test
    fun `the adoption agency algorithm reports a formatting element that is not open`() {
        // The div end tag closes the b, which stays in the list of active formatting elements until the b end tag
        assertEquals(listOf(null, null), errors("<div><b></div></b>"))
    }

    @Test
    fun `the adoption agency algorithm acts as any other end tag without a formatting element`() {
        // The body is special, so the b end tag is ignored
        assertEquals(listOf(null), errors("<span></b></span>"))
        assertEquals(listOf("span"), body("<span></b></span>").children.map { it.localName })
    }

    @Test
    fun `reconstructed and adopted formatting elements have the attributes of their tokens`() {
        val reconstructed = body("<p><b class=x>a</p>b").children.last()
        assertEquals("b", reconstructed.localName)
        assertEquals("x", reconstructed.getAttribute("class"))

        val div = body("<b class=x>a<div>b</b>c").children.last()
        val adopted = div.firstChild!!
        assertEquals("b", adopted.localName)
        assertEquals("x", adopted.getAttribute("class"))
    }

    @Test
    fun `the Noah's Ark clause keeps three formatting elements with the same tag name and attributes`() {
        val p = body("<p><b class=x><b class=x><b class=x><b class=x><b class=y><p>z").children[1]

        var node: TreeNode? = p.firstChild
        val reconstructed = buildList {
            while (node?.localName == "b") {
                add(node!!.getAttribute("class"))
                node = node!!.firstChild
            }
        }
        assertEquals(listOf("x", "x", "x", "y"), reconstructed)
    }
}
