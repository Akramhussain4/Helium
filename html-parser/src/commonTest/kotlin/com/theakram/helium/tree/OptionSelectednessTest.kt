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
import kotlin.test.assertNull
import kotlin.test.assertSame

/**
 * Option elements' selectedness, which the HTML spec keeps up to date as options are inserted and removed and as the
 * selected, disabled, size and multiple attributes change.
 */
internal class OptionSelectednessTest {

    private fun body(html: String): TreeNode =
        HTMLParser().parse("<!DOCTYPE html>$html").findChildElement("html")!!.findChildElement("body")!!

    private fun TreeNode.descendants(): Sequence<TreeNode> =
        children.asSequence().flatMap { sequenceOf(it) + it.descendants() }

    private fun TreeNode.first(localName: String): TreeNode = descendants().first { it.localName == localName }

    /** The text of each option under this node, with an asterisk after those that are selected. */
    private fun TreeNode.options(): String =
        descendants().filter { it.isHtmlElement("option") }.joinToString(" ") { option ->
            val text = option.descendants().filter { it.type == NodeType.TEXT }.joinToString("") { it.data!! }
            if (option.selectedness) "$text*" else text
        }

    private fun option(text: String, vararg attributes: Pair<String, String>): TreeNode =
        TreeNode.createElement("option").apply {
            attributes.forEach { (name, value) -> setAttribute(name, value) }
            appendChild(TreeNode.createText(text))
        }

    @Test
    fun `the first option of a select is selected by default`() {
        assertEquals("a* b c", body("<select><option>a<option>b<option>c</select>").options())
    }

    @Test
    fun `an option with a selected attribute is selected instead of the first`() {
        assertEquals("a b* c", body("<select><option>a<option selected>b<option>c</select>").options())
    }

    @Test
    fun `the last of several options with a selected attribute is selected`() {
        assertEquals("a b* c", body("<select><option selected>a<option selected>b<option>c</select>").options())
    }

    @Test
    fun `a select with a multiple attribute keeps every selected option and selects none by default`() {
        assertEquals("a b* c*", body("<select multiple><option>a<option selected>b<option selected>c</select>").options())
        assertEquals("a b", body("<select multiple><option>a<option>b</select>").options())
    }

    @Test
    fun `a select whose display size is not 1 selects no option by default`() {
        assertEquals("a b", body("<select size=4><option>a<option>b</select>").options())
        assertEquals("a b", body("<select size=0><option>a<option>b</select>").options())
        assertEquals("a b", body("<select size=-0><option>a<option>b</select>").options())
    }

    @Test
    fun `a size attribute that is 1 or doesn't parse leaves the display size at 1`() {
        assertEquals("a* b", body("<select size=' +01x'><option>a<option>b</select>").options())
        assertEquals("a* b", body("<select size=-1><option>a<option>b</select>").options())
        assertEquals("a* b", body("<select size=one><option>a<option>b</select>").options())
        assertEquals("a b", body("<select size=99999999999><option>a<option>b</select>").options())
    }

    @Test
    fun `the first enabled option is selected by default`() {
        assertEquals("a b* c", body("<select><option disabled>a<option>b<option>c</select>").options())
        assertEquals(
            "a b c*",
            body("<select><optgroup disabled><option>a<option>b</optgroup><option>c</select>").options()
        )
    }

    @Test
    fun `an option outside a select is selected only by its selected attribute`() {
        assertEquals("a b*", body("<div><option>a<option selected>b</div>").options())
        assertNull(body("<option>a").first("option").cachedNearestAncestorSelect)
    }

    @Test
    fun `options in a nested optgroup or a datalist are not in the select's list of options`() {
        val select = TreeNode.createElement("select")
        val outer = TreeNode.createElement("optgroup")
        val inner = TreeNode.createElement("optgroup")
        val datalist = TreeNode.createElement("datalist")
        select.appendChild(outer)
        outer.appendChild(inner)
        inner.appendChild(option("nested"))
        select.appendChild(datalist)
        datalist.appendChild(option("listed"))
        outer.appendChild(option("grouped"))

        assertEquals("nested grouped* listed", select.options())
        assertSame(select, select.first("optgroup").children.last().cachedNearestAncestorSelect)
        assertNull(inner.children.single().cachedNearestAncestorSelect)
    }

    @Test
    fun `inserting a selected option deselects the option that was selected`() {
        val select = body("<select><option>a<option>b</select>").first("select")
        select.appendChild(option("c", "selected" to ""))

        assertEquals("a b c*", select.options())
    }

    @Test
    fun `removing the selected option selects the first enabled option`() {
        val select = body("<select><option disabled>a<option>b<option selected>c</select>").first("select")
        select.removeChild(select.children.last())

        assertEquals("a b*", select.options())
    }

    @Test
    fun `inserting a subtree runs the insertion steps for each option in it`() {
        val select = body("<select><option>a</select>").first("select")
        val group = TreeNode.createElement("optgroup")
        group.appendChild(option("b"))
        group.appendChild(option("c", "selected" to ""))
        select.appendChild(group)

        assertEquals("a b c*", select.options())
        assertSame(select, group.children.last().cachedNearestAncestorSelect)
    }

    @Test
    fun `removing a subtree clears the cached select of each option in it`() {
        val body = body("<select><optgroup><option>a<option>b</optgroup><option>c</select>")
        val select = body.first("select")
        val group = select.first("optgroup")
        select.removeChild(group)

        assertEquals("c*", select.options())
        assertNull(group.children.first().cachedNearestAncestorSelect)
        assertNull(group.children.last().cachedNearestAncestorSelect)
    }

    @Test
    fun `adding a selected attribute selects the option`() {
        val select = body("<select><option>a<option>b</select>").first("select")
        select.children.last().setAttribute("selected", "")

        assertEquals("a b*", select.options())
    }

    @Test
    fun `changing the value of a selected attribute changes nothing`() {
        val select = body("<select><option selected>a<option>b</select>").first("select")
        select.children.first().selectedness = false
        select.children.first().setAttribute("selected", "yes")

        assertEquals("a b", select.options())
    }

    @Test
    fun `adding a selected attribute to a dirty option leaves its selectedness`() {
        val select = body("<select><option>a<option>b</select>").first("select")
        select.children.last().dirtiness = true
        select.children.last().setAttribute("selected", "")

        assertEquals("a* b", select.options())
    }

    @Test
    fun `disabling the selected option keeps it selected`() {
        // The selectedness setting algorithm keeps a selected option even if it is disabled, so disabling it alone
        // changes nothing until no option is selected
        val select = body("<select><option>a<option>b</select>").first("select")
        select.children.first().setAttribute("disabled", "")

        assertEquals("a* b", select.options())
    }

    @Test
    fun `changing the size attribute to 1 selects the first enabled option`() {
        val select = body("<select size=2><option>a<option>b</select>").first("select")
        select.setAttribute("size", "1")

        assertEquals("a* b", select.options())
    }

    @Test
    fun `adding a multiple attribute keeps the selected option`() {
        val select = body("<select><option>a<option selected>b</select>").first("select")
        select.setAttribute("multiple", "")
        select.appendChild(option("c", "selected" to ""))

        assertEquals("a b* c*", select.options())
    }

    @Test
    fun `appending options gives the same selectedness as running the whole algorithm`() {
        val random = Random(42)
        repeat(500) { round ->
            // The same appends to two selects, the second made to go through its whole list each time
            val selects = List(2) {
                TreeNode.createElement("select").apply {
                    if (round % 3 == 0) setAttribute("size", "${round % 4}")
                }
            }
            repeat(random.nextInt(1, 12)) {
                val selected = random.nextInt(4) == 0
                val disabled = random.nextInt(3) == 0
                val inGroup = random.nextInt(3) == 0
                val groupDisabled = random.nextBoolean()
                for (select in selects) {
                    val option = TreeNode.createElement("option")
                    if (selected) option.setAttribute("selected", "")
                    if (disabled) option.setAttribute("disabled", "")
                    val group = select.children.lastOrNull()?.takeIf { inGroup && it.localName == "optgroup" }
                    if (inGroup && group == null) {
                        val newGroup = TreeNode.createElement("optgroup")
                        if (groupDisabled) newGroup.setAttribute("disabled", "")
                        select.appendChild(newGroup)
                    }
                    if (select === selects[1]) select.hasRunSelectednessSettingAlgorithm = false
                    (if (inGroup) select.children.last() else select).appendChild(option)
                }
                val (fast, full) = selects.map { select ->
                    select.descendants().filter { it.isHtmlElement("option") }.map { it.selectedness }.toList()
                }
                assertEquals(full, fast, "round $round")
            }
        }
    }
}
