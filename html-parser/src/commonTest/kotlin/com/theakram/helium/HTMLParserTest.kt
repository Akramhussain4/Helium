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

package com.theakram.helium

import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.NodeType
import com.theakram.helium.tree.QuirksMode
import com.theakram.helium.tree.TreeNode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame

internal class HTMLParserTest {

    @Test
    fun `parsing again leaves the previous document intact`() {
        val parser = HTMLParser()

        val first = parser.parse("<!DOCTYPE html><p>first</p>")
        val firstTree = first.toTreeString()
        val firstQuirksMode = first.quirksMode

        val second = parser.parse("<p>second</p>")

        assertNotSame(first, second)
        assertEquals(firstTree, first.toTreeString())
        assertEquals(firstQuirksMode, first.quirksMode)
        assertEquals(QuirksMode.NO_QUIRKS, first.quirksMode)
        assertEquals(QuirksMode.QUIRKS, second.quirksMode)
    }

    @Test
    fun `document property returns the latest parse result`() {
        val parser = HTMLParser()

        parser.parse("<p>first</p>")
        val second = parser.parse("<p>second</p>")

        assertEquals(second, parser.document)
    }

    @Test
    fun `newline at the start of pre listing and textarea is dropped`() {
        val body = HTMLParser()
            .parse("<pre>\nA</pre><listing>\nB</listing><textarea>\nC</textarea><pre>\n\nD</pre>")
            .findChildElement("html")!!
            .findChildElement("body")!!

        assertEquals(listOf("A", "B", "C", "\nD"), body.children.map { it.children.single().data })
    }

    @Test
    fun `svg and mathml content is parsed in its own namespace`() {
        val body = HTMLParser()
            .parse("<svg><lineargradient/><foreignObject><b>x</b></foreignObject></svg><math><mi><b>y</b></mi></math>")
            .findChildElement("html")!!
            .findChildElement("body")!!
        val (svg, math) = body.children

        assertEquals(Namespaces.SVG, svg.namespace)
        assertEquals(listOf("linearGradient", "foreignObject"), svg.children.map { it.localName })
        assertEquals(Namespaces.HTML, svg.children[1].children.single().namespace)
        assertEquals(Namespaces.MATHML, math.children.single().namespace)
        assertEquals(Namespaces.HTML, math.children.single().children.single().namespace)
    }

    @Test
    fun `html start tag breaks out of svg`() {
        val body = HTMLParser()
            .parse("<svg><circle><p>text")
            .findChildElement("html")!!
            .findChildElement("body")!!

        assertEquals(listOf("svg", "p"), body.children.map { it.localName })
        assertEquals(Namespaces.HTML, body.children[1].namespace)
    }

    @Test
    fun `cdata is text in svg and a bogus comment in html`() {
        val body = HTMLParser()
            .parse("<svg><![CDATA[a<b]]></svg><![CDATA[c]]>")
            .findChildElement("html")!!
            .findChildElement("body")!!
        val (svg, comment) = body.children

        assertEquals("a<b", svg.children.single().data)
        assertEquals(NodeType.COMMENT, comment.type)
        assertEquals("[CDATA[c]]", comment.data)
    }

    @Test
    fun `noscript content is text with scripting enabled and markup with scripting disabled`() {
        val html = "<noscript><b>off</b></noscript>"

        val enabled = HTMLParser().parse(html).findChildElement("html")!!.findChildElement("head")!!
        val disabled = HTMLParser(scriptingEnabled = false).parse(html)
            .findChildElement("html")!!.findChildElement("head")!!

        assertEquals("<b>off</b>", enabled.findChildElement("noscript")!!.children.single().data)
        assertEquals("noscript", disabled.children.single().localName)
        assertEquals(emptyList(), disabled.findChildElement("noscript")!!.children)
    }

    @Test
    fun `parseFragment parses markup as the contents of the context element`() {
        val parser = HTMLParser()
        val context = TreeNode.createElement(localName = "tr")

        val nodes = parser.parseFragment("<td>a<td>b&notit;", context)

        assertEquals(listOf("td", "td"), nodes.map { it.localName })
        assertEquals(listOf(null, null), nodes.map { it.parent })
        assertEquals(emptyList(), context.children)
        assertEquals(listOf("missing-semicolon-after-character-reference"), parser.errors.map { it.code })
    }

    @Test
    fun `quirks mode public identifiers match case-insensitively`() {
        val document = HTMLParser().parse("<!DOCTYPE html PUBLIC \"html\"><p>")

        assertEquals(QuirksMode.QUIRKS, document.quirksMode)
    }
}
