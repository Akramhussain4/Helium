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
import kotlin.test.assertNull

class TreeNodeDataTest {

    @Test
    fun `appending to data keeps what was there whether it was read or not`() {
        val text = TreeNode.createText("a")
        text.appendData('b'.code)
        assertEquals("ab", text.data)
        text.appendData(0x1F600)
        text.appendData('c'.code)
        assertEquals("ab😀c", text.data)
        assertEquals("ab😀c", text.data)
    }

    @Test
    fun `setting data replaces what was appended`() {
        val text = TreeNode.createText("a")
        text.appendData('b'.code)
        text.data = "x"
        assertEquals("x", text.data)
        text.appendData('y'.code)
        assertEquals("xy", text.data)
        text.data = null
        assertNull(text.data)
        text.appendData('z'.code)
        assertEquals("z", text.data)
    }

    @Test
    fun `a text run parses into one text node whether it is foster parented or not`() {
        val run = "a😀".repeat(10_000)
        val body = HTMLParser().parse(run).findChildElement("html")!!.findChildElement("body")!!
        assertEquals(listOf(run), body.children.map { it.data })

        val fostered = HTMLParser().parse("<table>$run").findChildElement("html")!!.findChildElement("body")!!
        assertEquals(listOf(run, null), fostered.children.map { it.data })
        assertEquals("table", fostered.children[1].localName)
    }

    @Test
    fun `foster parented runs go before the table as elements are foster parented between them`() {
        val body = HTMLParser().parse("<table>a<br>bc<br>d").findChildElement("html")!!.findChildElement("body")!!
        assertEquals(listOf("a", null, "bc", null, "d", null), body.children.map { it.data })
        assertEquals(listOf(null, "br", null, "br", null, "table"), body.children.map { it.localName })
    }

    @Test
    fun `a parsed tree holds no text it is still appending to`() {
        val body = HTMLParser().parse("ab<table>cd<tr><td>ef").findChildElement("html")!!.findChildElement("body")!!
        val texts = mutableListOf<TreeNode>()
        fun collect(node: TreeNode) {
            if (node.type == NodeType.TEXT) texts += node
            node.children.forEach(::collect)
        }
        collect(body)
        // Checked before reading data, which turns it into a string too
        assertEquals(listOf(false, false), texts.map { it.isAppendingData })
        // The foster parented "cd" is appended to the "ab" before the table
        assertEquals(listOf("abcd", "ef"), texts.map { it.data })
    }
}
