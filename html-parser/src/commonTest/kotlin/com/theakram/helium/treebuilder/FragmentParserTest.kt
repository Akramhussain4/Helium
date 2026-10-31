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
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.QuirksMode
import com.theakram.helium.tree.TreeNode
import kotlin.test.Test
import kotlin.test.assertEquals

internal class FragmentParserTest {

    @Test
    fun `list items in a ul context are returned without html head or body`() {
        val context = TreeNode.createElement(localName = "ul")

        val nodes = FragmentParser.parseFragment(context, "<li>One<li>Two").children

        assertEquals(listOf("li", "li"), nodes.map { it.localName })
        assertEquals(listOf("One", "Two"), nodes.map { it.children.single().data })
    }

    @Test
    fun `table rows in a tbody context are parsed in table body mode`() {
        val context = TreeNode.createElement(localName = "tbody")

        val nodes = FragmentParser.parseFragment(context, "<tr><td>cell").children

        assertEquals("tr", nodes.single().localName)
        assertEquals("td", nodes.single().children.single().localName)
    }

    @Test
    fun `content of an svg context is parsed as svg`() {
        val context = TreeNode.createElement(localName = "svg", namespace = Namespaces.SVG)

        val nodes = FragmentParser.parseFragment(context, "<lineargradient/><![CDATA[text]]>").children

        assertEquals(Namespaces.SVG, nodes.first().namespace)
        assertEquals("linearGradient", nodes.first().localName)
        assertEquals("text", nodes.last().data)
    }

    @Test
    fun `a context in template contents takes the mode of the template contents owner document`() {
        // The template contents belong to a new inert document, which is in no-quirks mode even when the
        // template's document is in quirks mode, so the table closes the p
        val document = HTMLParser().parse("<template><div></div></template>")
        val template = document.findChildElement("html")!!.findChildElement("head")!!.findChildElement("template")!!
        val context = template.templateContent!!.findChildElement("div")!!

        val nodes = FragmentParser.parseFragment(context, "<p><table>").children

        assertEquals(QuirksMode.QUIRKS, document.quirksMode)
        assertEquals(listOf("p", "table"), nodes.map { it.localName })
    }

    @Test
    fun `a context in a quirks mode document parses in quirks mode`() {
        val document = HTMLParser().parse("<div></div>")
        val context = document.findChildElement("html")!!.findChildElement("body")!!.findChildElement("div")!!

        val nodes = FragmentParser.parseFragment(context, "<p><table>").children

        assertEquals("table", nodes.single().children.single().localName)
    }

    @Test
    fun `a context in nested template contents takes the mode of the template contents owner document`() {
        val document = HTMLParser().parse("<template><template><div></div></template></template>")
        val outer = document.findChildElement("html")!!.findChildElement("head")!!.findChildElement("template")!!
        val context = outer.templateContent!!.findChildElement("template")!!.templateContent!!.findChildElement("div")!!

        val nodes = FragmentParser.parseFragment(context, "<p><table>").children

        assertEquals(listOf("p", "table"), nodes.map { it.localName })
    }

    @Test
    fun `template contents as the target take the template's own document mode`() {
        // The context is the template element itself, which is in the quirks mode document
        val document = HTMLParser().parse("<template></template>")
        val template = document.findChildElement("html")!!.findChildElement("head")!!.findChildElement("template")!!

        val nodes = FragmentParser.parseFragment(template.templateContent!!, "<p><table>").children

        assertEquals("table", nodes.single().children.single().localName)
    }

    @Test
    fun `scripting is disabled for a context in template contents`() {
        // The inert template contents owner document has no browsing context, so noscript content is markup
        val document = HTMLParser(scriptingEnabled = true).parse("<template><div></div></template>")
        val template = document.findChildElement("html")!!.findChildElement("head")!!.findChildElement("template")!!
        val context = template.templateContent!!.findChildElement("div")!!

        val noscript = HTMLParser(scriptingEnabled = true).parseFragment("<noscript><p>x</p></noscript>", context)
            .children.single()

        assertEquals("p", noscript.children.single().localName)
    }

    @Test
    fun `nodes of a parsed fragment belong to the target's document`() {
        val document = HTMLParser().parse("<div></div>")
        val div = document.findChildElement("html")!!.findChildElement("body")!!.findChildElement("div")!!
        val span = FragmentParser.parseFragment(div, "<span></span>").children.single()

        val nodes = FragmentParser.parseFragment(span, "<p><table>").children

        assertEquals("table", nodes.single().children.single().localName)
    }

    @Test
    fun `a removed node keeps its node document`() {
        val document = HTMLParser().parse("<div></div>")
        val body = document.findChildElement("html")!!.findChildElement("body")!!
        val div = body.findChildElement("div")!!
        body.removeChild(div)

        val nodes = FragmentParser.parseFragment(div, "<p><table>").children

        assertEquals("table", nodes.single().children.single().localName)
    }
}
