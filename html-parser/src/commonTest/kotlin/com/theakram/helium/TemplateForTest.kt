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

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Template elements with a for attribute, for declarative out-of-order streaming.
 *
 * Spec: https://html.spec.whatwg.org/multipage/scripting.html#prepare-content-patching
 */
internal class TemplateForTest {

    private fun body(html: String) = HTMLParser().parse(html).findChildElement("html")!!.findChildElement("body")!!

    @Test
    fun `template contents replace a marker processing instruction`() {
        val body = body("<div><?marker name=\"a\"?></div><template for=\"a\"><p>x</p></template>")
        val div = body.findChildElement("div")!!

        assertEquals(listOf("p"), div.children.map { it.localName })
        assertEquals("x", div.children.single().children.single().data)
        assertEquals(listOf("div"), body.children.map { it.localName })
    }

    @Test
    fun `template contents replace the range between start and end processing instructions`() {
        val body = body("<ul><?start name=\"l\"?><li>old</li><?end?></ul><template for=\"l\"><li>new</li></template>")
        val list = body.findChildElement("ul")!!

        assertEquals(listOf("li"), list.children.map { it.localName })
        assertEquals("new", list.children.single().children.single().data)
    }

    @Test
    fun `template without a matching marker is inserted as usual`() {
        val body = body("<div><?marker name=\"a\"?></div><template for=\"b\"><p>x</p></template>")

        assertEquals(listOf("div", "template"), body.children.map { it.localName })
        assertEquals(listOf("p"), body.children[1].templateContent!!.children.map { it.localName })
    }

    @Test
    fun `template in body can patch a marker in head`() {
        val html = HTMLParser().parse("<head><?marker name=\"t\"?></head><body><template for=\"t\"><meta charset=\"x\"></template>")
            .findChildElement("html")!!

        assertEquals(listOf("meta"), html.findChildElement("head")!!.children.map { it.localName })
    }
}
