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
import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.NodeType
import com.theakram.helium.tree.TreeNode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * Parse errors of the "after body", "in frameset", "after frameset", "after after body" and "after after frameset"
 * insertion modes, of the rules for parsing tokens in foreign content, and of creating an element for a token.
 */
internal class ForeignAndAfterModesParseErrorTest {

    private class Result(val document: TreeNode, val treeErrors: Int, val tokenizerErrors: List<String>)

    // Tree construction errors have no code
    private fun parse(html: String): Result {
        val parser = HTMLParser()
        val document = parser.parse(html)
        return Result(
            document = document,
            treeErrors = parser.errors.count { it.code == null },
            tokenizerErrors = parser.errors.mapNotNull { it.code }
        )
    }

    private fun parseFragment(html: String, context: TreeNode): Result {
        val parser = HTMLParser()
        val fragment = parser.parseFragment(html, context)
        return Result(
            document = fragment,
            treeErrors = parser.errors.count { it.code == null },
            tokenizerErrors = parser.errors.mapNotNull { it.code }
        )
    }

    private fun TreeNode.body(): TreeNode = findChildElement("html")!!.findChildElement("body")!!

    private fun TreeNode.frameset(): TreeNode = findChildElement("html")!!.findChildElement("frameset")!!

    // After body

    @Test
    fun `a DOCTYPE after the body is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><body></body><!DOCTYPE html>").treeErrors)
    }

    @Test
    fun `content after the body is a parse error and goes back into the body`() {
        val result = parse("<!DOCTYPE html><body></body><p>x")

        assertEquals(1, result.treeErrors)
        assertEquals("p", result.document.body().children.single().localName)
    }

    @Test
    fun `whitespace and comments after the body are not parse errors`() {
        assertEquals(0, parse("<!DOCTYPE html><body></body> <!--c-->").treeErrors)
    }

    @Test
    fun `an html end tag after the body is a parse error only in the fragment case`() {
        assertEquals(0, parse("<!DOCTYPE html><body></body></html>").treeErrors)

        val context = TreeNode.createElement(localName = "html")
        assertEquals(1, parseFragment("<body></body></html>", context).treeErrors)
    }

    // In frameset

    @Test
    fun `a DOCTYPE in a frameset is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><frameset><!DOCTYPE html></frameset>").treeErrors)
    }

    @Test
    fun `other tokens in a frameset are parse errors and are ignored`() {
        val result = parse("<!DOCTYPE html><frameset><div>ab</div></frameset>")

        // The div start tag, both characters and the div end tag
        assertEquals(4, result.treeErrors)
        assertEquals(0, result.document.frameset().children.size)
    }

    @Test
    fun `frames and whitespace in a frameset are not parse errors`() {
        assertEquals(0, parse("<!DOCTYPE html><frameset> <frame><frame/></frameset>").treeErrors)
    }

    @Test
    fun `end of file with a frameset open is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><frameset>").treeErrors)
    }

    @Test
    fun `a frameset end tag with only the root html element open is a parse error`() {
        val context = TreeNode.createElement(localName = "frameset")

        // End of file with only the root html element open is not
        assertEquals(0, parseFragment("", context).treeErrors)
        assertEquals(1, parseFragment("</frameset>", context).treeErrors)
    }

    // After frameset

    @Test
    fun `a DOCTYPE after the frameset is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><frameset></frameset><!DOCTYPE html>").treeErrors)
    }

    @Test
    fun `other tokens after the frameset are parse errors and are ignored`() {
        val result = parse("<!DOCTYPE html><frameset></frameset><div>")

        assertEquals(1, result.treeErrors)
        assertNull(result.document.findChildElement("html")!!.findChildElement("div"))
    }

    // After after body

    @Test
    fun `content after the html end tag is a parse error and goes back into the body`() {
        val result = parse("<!DOCTYPE html><body></body></html><p>x")

        assertEquals(1, result.treeErrors)
        assertEquals("p", result.document.body().children.single().localName)
    }

    @Test
    fun `comments and whitespace after the html end tag are not parse errors`() {
        assertEquals(0, parse("<!DOCTYPE html><body></body></html> <!--c-->").treeErrors)
    }

    // After after frameset

    @Test
    fun `other tokens after the frameset and html end tags are parse errors and are ignored`() {
        val result = parse("<!DOCTYPE html><frameset></frameset></html><div>x")

        assertEquals(2, result.treeErrors)
        assertEquals(listOf("head", "frameset"), result.document.findChildElement("html")!!.children.map { it.localName })
    }

    @Test
    fun `noframes after the frameset and html end tags is handled by the in head rules`() {
        assertEquals(0, parse("<!DOCTYPE html><frameset></frameset></html><noframes></noframes>").treeErrors)
    }

    @Test
    fun `a tag name that only matches noframes under Unicode case folding is not noframes`() {
        // U+017F LATIN SMALL LETTER LONG S uppercases to S, but tag names are only lowercased in ASCII
        val result = parse("<!DOCTYPE html><frameset></frameset></html><noframeſ>")

        assertEquals(1, result.treeErrors)
        assertEquals(listOf("head", "frameset"), result.document.findChildElement("html")!!.children.map { it.localName })
    }

    // Foreign content

    @Test
    fun `a NULL character in foreign content is a parse error and is replaced`() {
        val result = parse("<!DOCTYPE html><svg>\u0000</svg>")

        assertEquals(listOf("unexpected-null-character"), result.tokenizerErrors)
        assertEquals(1, result.treeErrors)
        assertEquals("�", result.document.body().firstChild!!.firstChild!!.data)
    }

    @Test
    fun `a DOCTYPE in foreign content is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><svg><!DOCTYPE html></svg>").treeErrors)
    }

    @Test
    fun `an HTML start tag breaking out of foreign content is a parse error`() {
        val result = parse("<!DOCTYPE html><svg><g><p>")

        assertEquals(1, result.treeErrors)
        assertEquals(listOf("svg", "p"), result.document.body().children.map { it.localName })
    }

    @Test
    fun `a font start tag breaks out of foreign content only with color or face or size`() {
        // The input closes the font in body and leaves nothing open, so the breakout is its only error
        assertEquals(1, parse("<!DOCTYPE html><svg><font color=red></font>").treeErrors)
        assertEquals(0, parse("<!DOCTYPE html><svg><font id=x></font></svg>").treeErrors)
    }

    @Test
    fun `br and p end tags breaking out of foreign content are parse errors`() {
        // The p end tag then closes the open p in body without an error of its own
        assertEquals(1, parse("<!DOCTYPE html><p><math></p>").treeErrors)
    }

    @Test
    fun `an end tag that does not match the current node in foreign content is a parse error`() {
        val result = parse("<!DOCTYPE html><svg><g><circle></g></svg>")

        assertEquals(1, result.treeErrors)
        val g = result.document.body().firstChild!!.firstChild!!
        assertEquals("circle", g.children.single().localName)
    }

    @Test
    fun `end tags match foreign tag names converted to ASCII lowercase`() {
        val result = parse("<!DOCTYPE html><svg><foreignObject></foreignobject><G></g></svg>")

        assertEquals(0, result.treeErrors)
        assertEquals(listOf("foreignObject", "g"), result.document.body().firstChild!!.children.map { it.localName })
    }

    @Test
    fun `a self-closing SVG script is popped and its self-closing flag acknowledged`() {
        val result = parse("<!DOCTYPE html><svg><script/>x</svg>")

        assertEquals(0, result.treeErrors)
        assertEquals(emptyList(), result.tokenizerErrors)
        val svg = result.document.body().firstChild!!
        assertEquals(listOf("script", null), svg.children.map { it.localName })
        assertEquals(0, svg.firstChild!!.children.size)
    }

    @Test
    fun `other self-closing foreign elements are popped and their self-closing flag acknowledged`() {
        val result = parse("<!DOCTYPE html><math><script/><mi/>x</math>")

        assertEquals(0, result.treeErrors)
        assertEquals(emptyList(), result.tokenizerErrors)
        assertEquals(listOf("script", "mi", null), result.document.body().firstChild!!.children.map { it.localName })
    }

    @Test
    fun `an SVG script end tag pops the script`() {
        val svg = parse("<!DOCTYPE html><svg><script>a</script>b</svg>").document.body().firstChild!!

        assertEquals(listOf("script", null), svg.children.map { it.localName })
        assertEquals("b", svg.lastChild!!.data)
    }

    @Test
    fun `end of file in foreign content is processed in HTML content`() {
        assertEquals(0, parse("<!DOCTYPE html><svg><g></g></svg>").treeErrors)
    }

    // HTML integration points

    @Test
    fun `annotation-xml is an HTML integration point when its encoding matches ASCII case-insensitively`() {
        val result = parse("<!DOCTYPE html><math><annotation-xml encoding=\"Text/HTML\"><div></div></annotation-xml></math>")

        assertEquals(0, result.treeErrors)
        val div = result.document.body().firstChild!!.firstChild!!.firstChild!!
        assertEquals(Namespaces.HTML, div.namespace)
    }

    @Test
    fun `the encoding attribute of an annotation-xml fragment context is matched by its exact name`() {
        val encoding = TreeNode.createElement(
            localName = "annotation-xml",
            namespace = Namespaces.MATHML,
            attributes = listOf(Attribute(localName = "encoding", value = "text/html"))
        )
        assertEquals(0, parseFragment("<div></div>", encoding).treeErrors)

        // The fake start tag token has an attribute named "ENCODING", not "encoding", so the div breaks out
        val upperCaseEncoding = TreeNode.createElement(
            localName = "annotation-xml",
            namespace = Namespaces.MATHML,
            attributes = listOf(Attribute(localName = "ENCODING", value = "text/html"))
        )
        assertEquals(1, parseFragment("<div></div>", upperCaseEncoding).treeErrors)
    }

    // Creating an element for a token

    @Test
    fun `an xmlns attribute that is not the element's namespace is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><svg xmlns=\"http://www.w3.org/1998/Math/MathML\"></svg>").treeErrors)
        assertEquals(1, parse("<!DOCTYPE html><math xmlns=foo></math>").treeErrors)
        assertEquals(1, parse("<!DOCTYPE html><svg><g xmlns=\"\"></g></svg>").treeErrors)
    }

    @Test
    fun `an xmlns attribute that is the element's namespace is not a parse error`() {
        assertEquals(0, parse("<!DOCTYPE html><svg xmlns=\"http://www.w3.org/2000/svg\"></svg>").treeErrors)
        assertEquals(0, parse("<!DOCTYPE html><math xmlns=\"http://www.w3.org/1998/Math/MathML\"></math>").treeErrors)
    }

    @Test
    fun `an xmlns xlink attribute that is not the XLink namespace is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><svg xmlns:xlink=foo></svg>").treeErrors)
        assertEquals(0, parse("<!DOCTYPE html><svg xmlns:xlink=\"http://www.w3.org/1999/xlink\"></svg>").treeErrors)
    }

    @Test
    fun `xmlns attributes of HTML elements are in no namespace and are not parse errors`() {
        assertEquals(0, parse("<!DOCTYPE html><div xmlns=foo xmlns:xlink=bar></div>").treeErrors)
    }

    @Test
    fun `a fragment context element in no namespace gives its children no namespace`() {
        val context = TreeNode(type = NodeType.ELEMENT, namespace = null, localName = "x")

        val element = parseFragment("<a></a>", context).document.children.single()

        assertEquals(null, element.namespace)
        assertEquals("a", element.localName)
    }
}
