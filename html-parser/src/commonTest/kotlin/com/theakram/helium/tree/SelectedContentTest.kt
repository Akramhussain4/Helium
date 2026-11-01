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
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotSame
import kotlin.test.assertTrue

/**
 * selectedcontent elements, which reflect the contents of their select element's selected option.
 */
internal class SelectedContentTest {

    private fun body(html: String): TreeNode =
        HTMLParser().parse("<!DOCTYPE html>$html").findChildElement("html")!!.findChildElement("body")!!

    private fun TreeNode.descendants(): Sequence<TreeNode> =
        children.asSequence().flatMap { sequenceOf(it) + it.descendants() }

    private fun TreeNode.first(localName: String): TreeNode = descendants().first { it.localName == localName }

    /** The node's descendants as markup, without attributes. */
    private fun TreeNode.contents(): String = children.joinToString("") { child ->
        when (child.type) {
            NodeType.TEXT -> child.data!!
            NodeType.ELEMENT -> "<${child.localName}>${child.contents()}</${child.localName}>"
            else -> ""
        }
    }

    private fun option(text: String, selected: Boolean = false): TreeNode =
        TreeNode.createElement("option").apply {
            if (selected) setAttribute("selected", "")
            appendChild(TreeNode.createText(text))
        }

    @Test
    fun `a selectedcontent element gets a copy of the contents of the first option by default`() {
        val body = body("<select><button><selectedcontent></button><option>a<b>b</b><option>c</select>")

        assertEquals("a<b>b</b>", body.first("selectedcontent").contents())
    }

    @Test
    fun `a selectedcontent element reflects the option with a selected attribute`() {
        val body = body("<select><button><selectedcontent></button><option>a<option selected>b<option>c</select>")

        assertEquals("b", body.first("selectedcontent").contents())
    }

    @Test
    fun `the copy is a clone and not the option's own nodes`() {
        val body = body("<select><button><selectedcontent></button><option><span id=x>a</span></select>")
        val original = body.first("option").first("span")
        val copy = body.first("selectedcontent").first("span")

        assertNotSame(original, copy)
        assertEquals("x", copy.getAttribute("id"))
    }

    @Test
    fun `a select with a multiple attribute leaves its selectedcontent elements alone`() {
        val body = body("<select multiple><button><selectedcontent></button><option selected>a</select>")

        assertEquals("", body.first("selectedcontent").contents())
    }

    @Test
    fun `every selectedcontent element of the select is updated`() {
        val body = body(
            "<select><button><selectedcontent></selectedcontent><span><selectedcontent></span></button>" +
                "<option>a</select>"
        )

        val selectedcontents = body.descendants().filter { it.localName == "selectedcontent" }
        assertEquals(listOf("a", "a"), selectedcontents.map { it.contents() }.toList())
    }

    @Test
    fun `a selectedcontent element in an option or in a nested select is disabled`() {
        val select = body("<select><option>a</select>").first("select")
        val inOption = TreeNode.createElement("selectedcontent")
        select.first("option").appendChild(inOption)
        val inner = TreeNode.createElement("select")
        val inNestedSelect = TreeNode.createElement("selectedcontent")
        inner.appendChild(inNestedSelect)
        select.appendChild(inner)

        assertTrue(inOption.selectedcontentDisabled)
        assertTrue(inNestedSelect.selectedcontentDisabled)
        assertEquals("", inOption.contents())
        assertEquals("", inNestedSelect.contents())
    }

    @Test
    fun `inserting a selectedcontent element into a document copies the selected option`() {
        val select = body("<select><option>a<option selected>b</select>").first("select")
        val selectedcontent = TreeNode.createElement("selectedcontent")
        select.insertBefore(selectedcontent, select.firstChild)

        assertFalse(selectedcontent.selectedcontentDisabled)
        assertEquals("b", selectedcontent.contents())
    }

    @Test
    fun `a select that is not connected doesn't update when a selectedcontent element is inserted`() {
        // Post-connection steps run only for connected nodes
        val select = TreeNode.createElement("select")
        select.appendChild(option("a"))
        val selectedcontent = TreeNode.createElement("selectedcontent")
        select.appendChild(selectedcontent)

        assertEquals("", selectedcontent.contents())
    }

    @Test
    fun `selecting another option updates the selectedcontent element`() {
        val body = body("<select><button><selectedcontent></button><option>a<option>b</select>")
        body.first("select").children.last().setAttribute("selected", "")

        assertEquals("b", body.first("selectedcontent").contents())
    }

    @Test
    fun `appending a selected option updates the selectedcontent element`() {
        val body = body("<select><button><selectedcontent></button><option>a</select>")
        body.first("select").appendChild(option("b", selected = true))

        assertEquals("b", body.first("selectedcontent").contents())
    }

    @Test
    fun `removing the selected option copies the next selected one`() {
        val body = body("<select><button><selectedcontent></button><option>a<option selected>b</select>")
        val select = body.first("select")
        select.removeChild(select.children.last())

        assertEquals("a", body.first("selectedcontent").contents())
    }

    @Test
    fun `removing the last option clears the selectedcontent element`() {
        val body = body("<select><button><selectedcontent></button><option>a</select>")
        val select = body.first("select")
        select.removeChild(select.children.last())

        assertEquals("", body.first("selectedcontent").contents())
    }

    @Test
    fun `adding a multiple attribute stops updates`() {
        val body = body("<select><button><selectedcontent></button><option>a<option>b</select>")
        val select = body.first("select")
        select.setAttribute("multiple", "")
        select.children.last().setAttribute("selected", "")

        assertEquals("a", body.first("selectedcontent").contents())
    }

    @Test
    fun `the parser updates selectedcontent in template contents when it pops the option`() {
        // The option's insertion steps and the parser's pop run in template contents too, though the post-connection
        // steps don't
        val head = body("<template><select><button><selectedcontent></button><option>a</select></template>")
            .parent!!.findChildElement("head")!!
        val contents = head.findChildElement("template")!!.templateContent!!

        assertEquals("a", contents.first("selectedcontent").contents())
    }

    @Test
    fun `cloning copies attributes and descendants and template contents`() {
        val body = body("<div id=a>x<template><p>t</p></template><!--c--></div>")
        val copy = body.first("div").cloneNode(subtree = true)

        assertEquals("a", copy.getAttribute("id"))
        assertEquals(body.first("div").toTreeString(), copy.toTreeString())
        assertNotSame(body.first("template").templateContent, copy.first("template").templateContent)
    }

    @Test
    fun `cloning without the subtree copies only the node`() {
        val copy = body("<div id=a>x</div>").first("div").cloneNode(subtree = false)

        assertEquals("a", copy.getAttribute("id"))
        assertTrue(copy.children.isEmpty())
    }

    /** The select element that a selectedcontent element reflects, found from its ancestors as the spec does. */
    private fun TreeNode.reflectedSelect(): TreeNode? {
        var select: TreeNode? = null
        var ancestor = parent
        while (ancestor != null) {
            if (ancestor.isHtmlElement("select")) {
                if (select != null) return null
                select = ancestor
            } else if (ancestor.isHtmlElement("option") || ancestor.isHtmlElement("selectedcontent")) {
                return null
            }
            ancestor = ancestor.parent
        }
        return select
    }

    @Test
    fun `selectedcontent elements reflect the selected option through random changes`() {
        val random = Random(7)
        repeat(300) { round ->
            val body = body("<select><button><selectedcontent></button><option>o0</select><select></select>")
            var counter = 0
            fun elements(localName: String) = body.descendants().filter { it.isHtmlElement(localName) }.toList()
            repeat(40) { step ->
                val selects = elements("select")
                val options = elements("option")
                when (random.nextInt(11)) {
                    0, 1 -> {
                        val containers = selects + elements("optgroup") + elements("button")
                        val parent = containers.random(random)
                        val option = TreeNode.createElement("option")
                        if (random.nextInt(4) == 0) option.setAttribute("selected", "")
                        if (random.nextInt(5) == 0) option.setAttribute("disabled", "")
                        option.appendChild(TreeNode.createText("o${++counter}"))
                        parent.insertBefore(option, parent.children.randomOrNull(random))
                    }

                    2 -> options.randomOrNull(random)?.let { it.parent!!.removeChild(it) }
                    3 -> options.randomOrNull(random)?.setAttribute("selected", "")
                    4 -> (options + elements("optgroup")).randomOrNull(random)?.setAttribute("disabled", "")
                    5 -> selects.random(random).let { it.appendChild(TreeNode.createElement("optgroup")) }
                    6 -> {
                        // Not into an option: changing an option's children doesn't update selectedcontent
                        val parent = (selects + elements("button")).random(random)
                        val selectedcontent = TreeNode.createElement("selectedcontent")
                        parent.insertBefore(selectedcontent, parent.children.randomOrNull(random))
                    }

                    7 -> selects.random(random).setAttribute("size", "${random.nextInt(3)}")
                    8 -> options.randomOrNull(random)?.let { option ->
                        val parent = (selects + elements("optgroup")).random(random)
                        if (!option.isHostIncludingInclusiveAncestorOf(parent)) {
                            parent.insertBefore(option, parent.children.randomOrNull(random))
                        }
                    }

                    9 -> selects.random(random).let { select ->
                        val button = TreeNode.createElement("button")
                        select.insertBefore(button, select.firstChild)
                    }

                    10 -> elements("selectedcontent").randomOrNull(random)?.let { selectedcontent ->
                        val parent = (selects + elements("button")).random(random)
                        parent.insertBefore(selectedcontent, parent.children.randomOrNull(random))
                    }
                }

                val where = "round $round step $step"
                for (select in elements("select")) {
                    val listed = mutableListOf<TreeNode>()
                    select.forEachInListOfOptions { listed += it }
                    val selected = listed.filter { it.selectedness }
                    assertTrue(selected.size <= 1, "$where: ${selected.size} selected")
                    assertEquals(selected.firstOrNull(), select.firstSelectedOption(), where)
                }
                for (selectedcontent in elements("selectedcontent")) {
                    val select = selectedcontent.reflectedSelect() ?: continue
                    assertFalse(selectedcontent.selectedcontentDisabled, where)
                    val expected = select.firstSelectedOption()?.contents().orEmpty()
                    assertEquals(expected, selectedcontent.contents(), where)
                }
            }
        }
    }
}
