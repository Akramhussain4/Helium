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
import kotlin.test.assertNull
import kotlin.test.assertSame

/**
 * "Create an element for a token" associates form-associated elements with the form element pointer.
 */
internal class FormOwnerTest {

    private fun body(html: String): TreeNode =
        HTMLParser().parse("<!DOCTYPE html>$html").findChildElement("html")!!.findChildElement("body")!!

    private fun TreeNode.descendants(): Sequence<TreeNode> =
        children.asSequence().flatMap { sequenceOf(it) + it.descendants() }

    private fun TreeNode.first(localName: String): TreeNode = descendants().first { it.localName == localName }

    @Test
    fun `form-associated elements inside a form get it as their form owner`() {
        val body = body("<form><div><input><img><button></button></div></form>")
        val form = body.first("form")

        assertSame(form, body.first("input").formOwner)
        assertSame(form, body.first("img").formOwner)
        assertSame(form, body.first("button").formOwner)
        assertNull(body.first("div").formOwner)
    }

    @Test
    fun `elements after the form end tag have no form owner`() {
        assertNull(body("<form></form><input>").first("input").formOwner)
    }

    @Test
    fun `a foster parented input is associated with the form element pointer`() {
        // In a table, the form is inserted and popped at once but stays the form element pointer, and the input,
        // foster parented before the table, is in the same tree
        val body = body("<table><form><input type=text></table>")
        val form = body.first("form")

        assertSame(form, body.first("input").formOwner)
    }

    @Test
    fun `a listed element with a form attribute is not associated`() {
        val body = body("<form><input form=other><img form=other>")
        val form = body.first("form")

        assertNull(body.first("input").formOwner)
        // img is form-associated but not listed, so its form attribute doesn't matter
        assertSame(form, body.first("img").formOwner)
    }

    @Test
    fun `elements in template contents are not associated`() {
        val body = body("<form><template><input></template>")
        val input = body.first("form").first("template").templateContent!!.first("input")

        assertNull(input.formOwner)
    }

    @Test
    fun `elements parsed as a fragment are not associated`() {
        val context = TreeNode.createElement("form")

        val input = HTMLParser().parseFragment("<input>", context).children.single()

        assertNull(input.formOwner)
    }

    @Test
    fun `an element still inside a form after its end tag is reset to that form when inserted`() {
        // The form end tag clears the form element pointer, but the div keeps the input inside the form
        val body = body("<form id=f><div></form><input>")

        assertSame(body.first("form"), body.first("input").formOwner)
    }

    @Test
    fun `elements in template contents get their nearest ancestor form`() {
        val template = body("<form><template><form id=t><input></template>").first("template")
        val contents = template.templateContent!!

        assertSame(contents.first("form"), contents.first("input").formOwner)
    }

    @Test
    fun `elements parsed as a fragment get their nearest ancestor form`() {
        val nodes = HTMLParser().parseFragment("<form id=f><input></form>", TreeNode.createElement("div"))

        assertSame(nodes.first("form"), nodes.first("input").formOwner)
    }

    @Test
    fun `a form attribute names a form already in the document`() {
        val body = body("<form id=other></form><form id=f><input form=other>")

        assertSame(body.descendants().first { it.getAttribute("id") == "other" }, body.first("input").formOwner)
    }

    @Test
    fun `an element moved out of its form by the adoption agency loses it`() {
        // The adoption agency removes the div holding the input from the form and appends it elsewhere
        assertNull(body("<div><form id=f></div><a><div><input></a>").first("input").formOwner)
        assertNull(body("<div><form id=f></div><b><p><input></b>").first("input").formOwner)
    }

    @Test
    fun `changing the form attribute resets the form owner`() {
        val body = body("<form id=a></form><form id=b><input>")
        val input = body.first("input")

        input.setAttribute("form", "a")

        assertSame(body.descendants().first { it.getAttribute("id") == "a" }, input.formOwner)
    }

    @Test
    fun `a form attribute naming a form inserted later is reset when the form is inserted`() {
        val body = body("<input form=later><form id=later></form>")

        assertSame(body.first("form"), body.first("input").formOwner)
    }

    @Test
    fun `changing an ID resets the elements whose form attribute names it`() {
        val body = body("<form id=a></form><form id=b></form><input form=b>")
        val (a, b) = body.children.filter { it.localName == "form" }
        val input = body.first("input")

        b.setAttribute("id", "c")
        assertNull(input.formOwner)

        a.setAttribute("id", "b")
        assertSame(a, input.formOwner)
    }

    @Test
    fun `a node can't be inserted into its own descendant`() {
        val outer = TreeNode.createElement("div")
        val inner = TreeNode.createElement("div")
        outer.appendChild(inner)

        kotlin.test.assertFailsWith<IllegalArgumentException> { inner.appendChild(outer) }
    }

    @Test
    fun `deep trees and many form attributes don't overflow or slow down`() {
        // A 50,000 deep chain built leaf by leaf and moved into a document, then a form attribute changed at its bottom
        val document = HTMLParser().parse("<form id=x></form>")
        var top = TreeNode.createElement("div")
        val chainTop = top
        repeat(50_000) {
            val child = TreeNode.createElement("div")
            top.appendChild(child)
            top = child
        }
        val input = TreeNode.createElement("input")
        top.appendChild(input)
        document.findChildElement("html")!!.findChildElement("body")!!.appendChild(chainTop)
        input.setAttribute("form", "x")
        assertSame(document.descendants().first { it.localName == "form" }, input.formOwner)

        // Many listed elements with a form attribute among many elements
        val html = "<form id=f></form>" + "<span></span>".repeat(20_000) + "<input form=f>".repeat(2_000)
        val body = body(html)
        assertSame(body.first("form"), body.descendants().last().formOwner)
    }

    @Test
    fun `removing an element with an ID resets the form attribute elements that leave with it`() {
        // The div is the first element with ID a, so the input has no owner until the form, holding it, is removed:
        // then, no longer connected, it takes its nearest ancestor form
        val body = body("<div id=a></div><form id=a><input form=a></form>")
        val form = body.first("form")
        val input = body.first("input")
        assertNull(input.formOwner)

        body.removeChild(form)

        assertSame(form, input.formOwner)
    }

    @Test
    fun `an element leaving the document with the form its form attribute named loses it`() {
        val body = body("<div><form id=x></form><input form=x></div>")
        val input = body.first("input")
        assertSame(body.first("form"), input.formOwner)

        body.removeChild(body.first("div"))

        assertNull(input.formOwner)
    }

    @Test
    fun `setting the form attribute to its value resets the form owner`() {
        val body = body("<div><form id=x></form><input form=x></div>")
        val input = body.first("input")
        body.removeChild(body.first("div"))
        input.setAttribute("form", "x")

        assertNull(input.formOwner)
    }

    @Test
    fun `a moved element takes its form owner from its new document`() {
        val first = body("<form id=f></form><input form=f>")
        val second = body("<form id=f></form>")
        val input = first.first("input")

        second.appendChild(input)

        assertSame(second.first("form"), input.formOwner)
    }

    @Test
    fun `template contents can't take an ancestor of their template`() {
        val body = body("<body><template></template>")
        val contents = body.first("template").templateContent!!

        kotlin.test.assertFailsWith<IllegalArgumentException> { contents.appendChild(body) }
    }

    @Test
    fun `many form attributes naming a repeated ID stay fast`() {
        // Each p with ID x resets every input naming x; the first element with that ID is looked up once per change
        val body = body("<input form=x>".repeat(400) + "<p id=x>".repeat(400))

        assertNull(body.first("input").formOwner)
    }
}
