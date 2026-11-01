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

class SerializationTest {

    private fun element(localName: String, namespace: String? = Namespaces.HTML, vararg children: TreeNode) =
        TreeNode.createElement(localName, namespace).also { element -> children.forEach { element.appendChild(it) } }

    /** Parses [html] as a div's contents, so that nothing goes in a head element. */
    private fun body(html: String): TreeNode = HTMLParser().parseFragment(html, TreeNode.createElement("div"))

    @Test
    fun `text is escaped in text mode`() {
        val p = element("p", Namespaces.HTML, TreeNode.createText("a&b<c>d\"e'f g"))
        assertEquals("a&amp;b&lt;c&gt;d\"e'f&nbsp;g", p.innerHtml())
    }

    @Test
    fun `attribute values are escaped in attribute mode`() {
        val p = element("p")
        p.setAttribute("title", "a&b<c>d\"e'f g")
        assertEquals("<p title=\"a&amp;b&lt;c&gt;d&quot;e'f&nbsp;g\"></p>", p.outerHtml())
    }

    @Test
    fun `whitespace and other quotes in attribute values are written as is`() {
        val p = element("p")
        p.setAttribute("title", "a=b c\td\ne\rf\u000Cg\u000Bh'i")
        assertEquals("<p title=\"a=b c\td\ne\rf\u000Cg\u000Bh'i\"></p>", p.outerHtml())
    }

    @Test
    fun `a doctype keeps the case of its name`() {
        val document = TreeNode.createDocument()
        document.appendChild(TreeNode.createDocumentType("HTML"))
        assertEquals("<!DOCTYPE HTML>", document.innerHtml())
    }

    @Test
    fun `escaping keeps the text between the characters it replaces`() {
        assertEquals("&amp;&amp;x&lt;&lt;", element("p", Namespaces.HTML, TreeNode.createText("&&x<<")).innerHtml())
        assertEquals("", element("p", Namespaces.HTML, TreeNode.createText("")).innerHtml())
        assertEquals("plain 😀", element("p", Namespaces.HTML, TreeNode.createText("plain 😀")).innerHtml())
    }

    @Test
    fun `text in raw text parents is written literally`() {
        for (name in listOf("style", "script", "xmp", "iframe", "noembed", "noframes", "plaintext")) {
            val parent = element(name, Namespaces.HTML, TreeNode.createText("a<b>&amp; "))
            assertEquals("a<b>&amp; ", parent.innerHtml(), name)
            assertEquals("<$name>a<b>&amp; </$name>", parent.outerHtml(), name)
        }
    }

    @Test
    fun `text in a noscript element is literal only when scripting is enabled`() {
        val noscript = element("noscript", Namespaces.HTML, TreeNode.createText("<b>&"))
        assertEquals("<b>&", noscript.innerHtml())
        assertEquals("<b>&", noscript.innerHtml(scriptingEnabled = true))
        assertEquals("&lt;b&gt;&amp;", noscript.innerHtml(scriptingEnabled = false))
    }

    @Test
    fun `text in a noscript element in template contents is escaped since they have no browsing context`() {
        val body = body("<template><noscript>&lt;b&gt;</noscript></template><noscript>&lt;b&gt;</noscript>")
        assertEquals(
            "<template><noscript>&amp;lt;b&amp;gt;</noscript></template><noscript>&lt;b&gt;</noscript>",
            body.innerHtml()
        )
        val templateNoscript = body.children[0].templateContent!!.children[0]
        assertEquals("<noscript>&amp;lt;b&amp;gt;</noscript>", templateNoscript.outerHtml())
    }

    @Test
    fun `text in foreign elements named like raw text elements is escaped`() {
        val style = element("style", Namespaces.SVG, TreeNode.createText("a<b"))
        assertEquals("a&lt;b", style.innerHtml())
        assertEquals("<svg><style>a&lt;b</style></svg>", body("<svg><style>a&lt;b</style></svg>").innerHtml())
    }

    @Test
    fun `void elements have no end tag or contents`() {
        val voids = listOf(
            "area", "base", "br", "col", "embed", "hr", "img", "input", "link", "meta", "source", "track", "wbr",
            "basefont", "bgsound", "frame", "keygen", "param"
        )
        for (name in voids) {
            val void = element(name, Namespaces.HTML, TreeNode.createText("x"))
            assertEquals("<$name>", void.outerHtml(), name)
            assertEquals("", void.innerHtml(), name)
            assertEquals("<div><$name></div>", element("div", Namespaces.HTML, void).outerHtml(), name)
        }
        assertEquals("<br></br>", element("br", Namespaces.SVG).outerHtml())
        assertEquals("<p>a<br>b<img src=\"x\">c</p>", body("<p>a<br>b<img src=x>c").innerHtml())
    }

    @Test
    fun `a template serializes its contents and not its children`() {
        val template = element("template", Namespaces.HTML, element("i"))
        template.templateContent!!.appendChild(element("b", Namespaces.HTML, TreeNode.createText("x")))
        assertEquals("<b>x</b>", template.innerHtml())
        assertEquals("<template><b>x</b></template>", template.outerHtml())
        assertEquals(
            "<template><template>a</template><p>b</p></template>",
            body("<template><template>a</template><p>b</template>").innerHtml()
        )
        assertEquals("<template></template>", element("template", Namespaces.SVG).outerHtml())
    }

    @Test
    fun `foreign elements keep the case of their local name`() {
        assertEquals(
            "<svg viewBox=\"0 0 1 1\"><foreignObject><p>x</p></foreignObject><linearGradient></linearGradient></svg>" +
                "<math definitionURL=\"u\"><mi>y</mi></math>",
            body("<svg viewbox='0 0 1 1'><foreignobject><p>x</p></foreignobject><lineargradient/></svg>" +
                "<math definitionurl=u><mi>y</math>").innerHtml()
        )
    }

    @Test
    fun `elements in other namespaces use their qualified name`() {
        val element = TreeNode.createElement("item", namespace = "urn:x", prefix = "x")
        assertEquals("<x:item></x:item>", element.outerHtml())
        assertEquals("<item></item>", TreeNode.createElement("item", namespace = null).outerHtml())
        val svg = TreeNode.createElement("rect", namespace = Namespaces.SVG, prefix = "svg")
        assertEquals("<rect></rect>", svg.outerHtml())
    }

    @Test
    fun `attribute names depend on their namespace`() {
        val element = element("svg", Namespaces.SVG)
        element.setAttributeNS(null, "p", "plain", "1")
        element.setAttributeNS(Namespaces.XML, "foo", "lang", "2")
        element.setAttributeNS(Namespaces.XMLNS, null, "xmlns", "3")
        element.setAttributeNS(Namespaces.XMLNS, "foo", "xlink", "4")
        element.setAttributeNS(Namespaces.XLINK, "foo", "href", "5")
        element.setAttributeNS("urn:other", "o", "name", "6")
        element.setAttributeNS("urn:other", null, "bare", "7")
        assertEquals(
            "<svg plain=\"1\" xml:lang=\"2\" xmlns=\"3\" xmlns:xlink=\"4\" xlink:href=\"5\" o:name=\"6\" bare=\"7\">" +
                "</svg>",
            element.outerHtml()
        )
    }

    @Test
    fun `parsed foreign attributes serialize with their prefixes`() {
        val html = "<svg xlink:href=\"a\" xml:lang=\"b\" xmlns=\"http://www.w3.org/2000/svg\" " +
            "xmlns:xlink=\"http://www.w3.org/1999/xlink\"></svg>"
        assertEquals(html, body(html).innerHtml())
    }

    @Test
    fun `attributes keep their order`() {
        assertEquals("<p b=\"1\" a=\"2\" c=\"\"></p>", body("<p b=1 a=2 c>").innerHtml())
    }

    @Test
    fun `comments processing instructions and doctypes have their own forms`() {
        val div = element("div")
        div.appendChild(TreeNode.createComment(" a -- <b> & "))
        div.appendChild(TreeNode.createProcessingInstruction("target", "x=\"<&>\""))
        div.appendChild(TreeNode.createProcessingInstruction("empty", ""))
        assertEquals("<!-- a -- <b> & --><?target x=\"<&>\"?><?empty ?>", div.innerHtml())

        val document = HTMLParser().parse(
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\"><!--c-->"
        )
        assertEquals("<!DOCTYPE html><!--c--><html><head></head><body></body></html>", document.innerHtml())
        assertEquals("<!DOCTYPE html>", document.children[0].outerHtml())
        assertEquals("<!--c-->", document.children[1].outerHtml())
    }

    @Test
    fun `parsed processing instructions round trip`() {
        val html = "<?php echo 1 ?><p><?pi?></p>"
        val document = HTMLParser().parse(html)
        assertEquals("<?php echo 1 ?><html><head></head><body><p><?pi ?></p></body></html>", document.innerHtml())
    }

    @Test
    fun `a cdata section is serialized as the text node it is`() {
        val cdata = TreeNode(type = NodeType.CDATA_SECTION, data = "a<b]]>")
        assertEquals("<svg>a&lt;b]]&gt;</svg>", element("svg", Namespaces.SVG, cdata).outerHtml())
        assertEquals("a&lt;b]]&gt;", cdata.outerHtml())
        assertEquals("<svg>&lt;x&gt;</svg>", body("<svg><![CDATA[<x>]]></svg>").innerHtml())
    }

    @Test
    fun `text appended by the parser is read through its data`() {
        val text = TreeNode.createText("a")
        text.appendData('<'.code)
        assertEquals("<p>a&lt;</p>", element("p", Namespaces.HTML, text).outerHtml())
    }

    @Test
    fun `outer html of other nodes`() {
        assertEquals("a&lt;b", TreeNode.createText("a<b").outerHtml())
        val script = body("<script>if (a < b) {}</script>").children[0]
        assertEquals("if (a < b) {}", script.children[0].outerHtml())
        assertEquals("<script>if (a < b) {}</script>", script.outerHtml())

        val document = HTMLParser().parse("<p>x")
        assertEquals("<html><head></head><body><p>x</p></body></html>", document.outerHtml())
        assertEquals(document.innerHtml(), document.outerHtml())

        val fragment = HTMLParser().parseFragment("<td>a<td>b", TreeNode.createElement("tr"))
        assertEquals("<td>a</td><td>b</td>", fragment.innerHtml())
        assertEquals("<td>a</td><td>b</td>", fragment.outerHtml())
    }

    @Test
    fun `a document fragment inserted as a node is serialized as its children`() {
        val fragment = TreeNode.createDocumentFragment()
        fragment.appendChild(TreeNode.createText("a<b"))
        fragment.appendChild(element("i"))
        val script = element("script", Namespaces.HTML, fragment)
        assertEquals("a<b<i></i>", script.innerHtml())
        assertEquals("a<b<i></i>", fragment.innerHtml())
        assertEquals("a<b<i></i>", fragment.outerHtml())
        assertEquals("a<b", fragment.children[0].outerHtml())
        assertEquals("<div>a&lt;b<i></i></div>", element("div", Namespaces.HTML, fragment).outerHtml())
        assertEquals("a&lt;b", fragment.children[0].outerHtml())
    }

    @Test
    fun `noscript in template contents set by hand is escaped from every node`() {
        val template = TreeNode.createElement("template")
        template.templateContent = TreeNode.createDocumentFragment()
        val noscript = element("noscript", Namespaces.HTML, TreeNode.createText("<b>"))
        template.templateContent!!.appendChild(noscript)
        assertEquals("<noscript>&lt;b&gt;</noscript>", template.innerHtml())
        assertEquals("&lt;b&gt;", noscript.innerHtml())
        assertEquals("<noscript>&lt;b&gt;</noscript>", noscript.outerHtml())
    }

    @Test
    fun `an attribute in the empty namespace has no namespace`() {
        val p = element("p")
        p.attributes.add(Attribute(namespace = "", prefix = "q", localName = "x", value = "1"))
        assertEquals("<p x=\"1\"></p>", p.outerHtml())
    }

    @Test
    fun `a whole document serializes as the parser built it`() {
        val document = HTMLParser().parse("<!doctype html><title>T &amp; t</title><p class=x>Hi <b>there</b>")
        assertEquals(
            "<!DOCTYPE html><html><head><title>T &amp; t</title></head>" +
                "<body><p class=\"x\">Hi <b>there</b></p></body></html>",
            document.innerHtml()
        )
    }

    @Test
    fun `the readme examples give what the readme says`() {
        val parser = HTMLParser()
        val div = TreeNode.createElement("div")
        val paragraph = parser.parseFragment("<p class=greeting>Fish &amp; chips<br>", div).firstChild!!
        assertEquals("<p class=\"greeting\">Fish &amp; chips<br></p>", paragraph.outerHtml())
        assertEquals("Fish &amp; chips<br>", paragraph.innerHtml())
        assertEquals("<html><head></head><body><p>Hi</p></body></html>", parser.parse("<p>Hi").innerHtml())
    }

    @Test
    fun `deep trees serialize without overflowing the stack`() {
        val depth = 100_000
        val root = element("div")
        var current = root
        repeat(depth) { current = element("span").also { current.appendChild(it) } }
        current.appendChild(TreeNode.createText("x"))
        assertEquals("<span>".repeat(depth) + "x" + "</span>".repeat(depth), root.innerHtml())
        assertEquals("<div>" + "<span>".repeat(depth) + "x" + "</span>".repeat(depth) + "</div>", root.outerHtml())
    }

    @Test
    fun `deeply nested templates serialize without overflowing the stack`() {
        val depth = 20_000
        val root = element("template")
        var current = root
        repeat(depth) {
            current = element("template").also { current.templateContent!!.appendChild(it) }
        }
        assertEquals("<template>".repeat(depth) + "</template>".repeat(depth), root.innerHtml())
    }

    @Test
    fun `wide trees serialize every child`() {
        val width = 100_000
        val root = element("div")
        repeat(width) { root.appendChild(element("br")) }
        assertEquals("<br>".repeat(width), root.innerHtml())
    }
}
