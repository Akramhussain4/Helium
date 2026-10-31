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

import com.theakram.helium.tree.Namespaces
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
}
