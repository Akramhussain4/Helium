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
import com.theakram.helium.tree.TreeNode
import kotlin.test.Test
import kotlin.test.assertEquals

internal class InBodyEndTagTest {

    private fun body(html: String): TreeNode =
        HTMLParser().parse(html).findChildElement("html")!!.findChildElement("body")!!

    @Test
    fun `select end tag closes the select like the other block end tags`() {
        // A div is not a scope boundary, so the select is in scope and everything up to it is popped
        val body = body("<select><div></select>x")

        assertEquals(listOf("select", null), body.children.map { it.localName })
        assertEquals("div", body.firstChild!!.children.single().localName)
        assertEquals("x", body.lastChild!!.data)
    }

    @Test
    fun `formatting elements closed by the select end tag are reconstructed after it`() {
        val body = body("<select><b><div></select>x")

        assertEquals(listOf("select", "b"), body.children.map { it.localName })
        assertEquals("x", body.lastChild!!.children.single().data)
    }

    @Test
    fun `select end tag in a table closes the select and the text is foster parented`() {
        val body = body("<table><select><div></select>x")

        assertEquals(listOf("select", null, "table"), body.children.map { it.localName })
        assertEquals("x", body.children[1].data)
    }

    @Test
    fun `select end tag is ignored when foreignObject is between it and the select`() {
        val foreignObject = body("<select><svg><foreignObject></select>x").firstChild!!.firstChild!!.firstChild!!

        assertEquals("foreignObject", foreignObject.localName)
        assertEquals("x", foreignObject.children.single().data)
    }

    @Test
    fun `select end tag is ignored in a select fragment context`() {
        val context = TreeNode.createElement(localName = "select")

        val nodes = FragmentParser.parseFragment(context, "<div></select>x").children

        assertEquals("div", nodes.single().localName)
        assertEquals("x", nodes.single().children.single().data)
    }

    @Test
    fun `form end tag is ignored when the form is not in scope`() {
        // The table is a scope boundary, so the form stays open and x goes into it
        val form = body("<form><table></form></table>x").children.single()

        assertEquals("form", form.localName)
        assertEquals(listOf("table", null), form.children.map { it.localName })
        assertEquals("x", form.lastChild!!.data)
    }

    @Test
    fun `form end tag clears the form element pointer even when it is ignored`() {
        // With the pointer cleared, the second form start tag is not ignored and nests in the first form
        val form = body("<form><table></form></table><form>y").children.single()

        assertEquals(listOf("table", "form"), form.children.map { it.localName })
        assertEquals("y", form.lastChild!!.children.single().data)
    }

    @Test
    fun `form end tag is ignored when a select or foreignObject is between it and the form`() {
        val form = body("<form><select></form></select>x").children.single()
        assertEquals(listOf("select", null), form.children.map { it.localName })

        // </svg> is handled by the foreign content rules, which close the svg but leave the form open
        val svgForm = body("<form><svg><foreignObject></form></svg>x").children.single()
        assertEquals(listOf("svg", null), svgForm.children.map { it.localName })
        assertEquals(Namespaces.SVG, svgForm.firstChild!!.namespace)
        assertEquals("x", svgForm.lastChild!!.data)
    }
}
