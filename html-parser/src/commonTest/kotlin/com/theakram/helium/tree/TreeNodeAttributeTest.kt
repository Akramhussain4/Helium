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
package com.theakram.helium.tree

import com.theakram.helium.HTMLParser
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

internal class TreeNodeAttributeTest {

    private fun svg() = HTMLParser().parse("<svg viewBox='0 0 1 1' xlink:href=x>")
        .findChildElement("html")!!.findChildElement("body")!!.children.single()

    @Test
    fun `getAttribute lowercases the name only on HTML elements`() {
        val div = TreeNode.createElement("div", attributes = listOf(Attribute(localName = "id", value = "a")))
        assertEquals("a", div.getAttribute("ID"))

        val svg = svg()
        assertEquals("0 0 1 1", svg.getAttribute("viewBox"))
        assertNull(svg.getAttribute("viewbox"))
    }

    @Test
    fun `getAttribute matches the qualified name in any namespace`() {
        assertEquals("x", svg().getAttribute("xlink:href"))
    }

    @Test
    fun `getAttributeNS matches namespace and local name exactly`() {
        val svg = svg()
        assertEquals("x", svg.getAttributeNS(Namespaces.XLINK, "href"))
        assertNull(svg.getAttributeNS(null, "viewbox"))
        assertEquals("0 0 1 1", svg.getAttributeNS("", "viewBox"))
        assertTrue(svg.hasAttributeNS(null, "viewBox"))
        assertFalse(svg.hasAttribute("viewbox"))
    }

    @Test
    fun `setAttribute changes an existing attribute in place`() {
        val div = TreeNode.createElement(
            "div",
            attributes = listOf(Attribute(localName = "a", value = "1"), Attribute(localName = "b", value = "2"))
        )
        div.setAttribute("A", "3")

        assertEquals(listOf("a" to "3", "b" to "2"), div.attributes.map { it.localName to it.value })
    }

    @Test
    fun `setAttribute rejects names that are not valid attribute local names`() {
        val div = TreeNode.createElement("div")
        assertFailsWith<IllegalArgumentException> { div.setAttribute("a=b", "x") }
        assertFailsWith<IllegalArgumentException> { div.setAttribute("", "x") }
    }

    @Test
    fun `setAttributeNS validates and extracts the qualified name`() {
        val svg = svg()
        svg.setAttributeNS(Namespaces.XLINK, "xl:href", "y")
        // The existing attribute keeps its prefix
        assertEquals("xlink:href", svg.attributes.single { it.namespace == Namespaces.XLINK }.name)
        assertEquals("y", svg.getAttributeNS(Namespaces.XLINK, "href"))

        assertFailsWith<IllegalArgumentException> { svg.setAttributeNS(null, "a:b", "x") }
        assertFailsWith<IllegalArgumentException> { svg.setAttributeNS(Namespaces.SVG, "xml:lang", "x") }
        assertFailsWith<IllegalArgumentException> { svg.setAttributeNS(null, "xmlns", "x") }
        assertFailsWith<IllegalArgumentException> { svg.setAttributeNS(Namespaces.XMLNS, "a", "x") }
    }

    @Test
    fun `the parser adds attributes of a second html start tag without validating them`() {
        val html = HTMLParser().parse("<html a=1><html =b a=2 c=3>").findChildElement("html")!!

        assertEquals(listOf("a" to "1", "=b" to "", "c" to "3"), html.attributes.map { it.localName to it.value })
    }

    @Test
    fun `the prefix and local name overload sets an attribute value without validating`() {
        val div = TreeNode.createElement("div")
        div.setAttributeNS(null, null, "a:b", "1")
        div.setAttributeNS("urn:x", "a:b", "c", "2")
        div.setAttributeNS("urn:x", "other", "c", "3")

        assertEquals(listOf("a:b" to "1", "a:b:c" to "3"), div.attributes.map { it.name to it.value })
    }
}
