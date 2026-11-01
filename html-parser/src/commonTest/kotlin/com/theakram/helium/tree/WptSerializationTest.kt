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

/**
 * The cases of web-platform-tests' serialization tests, ported by hand since they are JavaScript, as of WPT commit
 * 41ce24f042d9f8ee1a8ebb3d869d13dd941ea6fa. A browser's main document has scripting enabled, which is
 * [HTMLParser]'s default.
 *
 * Source: https://github.com/web-platform-tests/wpt/tree/master/html/syntax/serializing-html-fragments
 */
class WptSerializationTest {

    private fun div(html: String): TreeNode = HTMLParser().parseFragment(html, TreeNode.createElement("div"))

    private fun element(localName: String, vararg children: TreeNode): TreeNode =
        TreeNode.createElement(localName).also { element -> children.forEach { element.appendChild(it) } }

    /** serializing.html: the children of div#test, each with its innerHTML and outerHTML. */
    @Test
    fun `serializing html parsed elements`() {
        val cases = listOf(
            "<span></span>" to ("" to "<span></span>"),
            "<span><a></a></span>" to ("<a></a>" to "<span><a></a></span>"),
            "<span><a b=c></a></span>" to ("<a b=\"c\"></a>" to "<span><a b=\"c\"></a></span>"),
            "<span><a b='c'></a></span>" to ("<a b=\"c\"></a>" to "<span><a b=\"c\"></a></span>"),
            "<span><a b='&'></a></span>" to ("<a b=\"&amp;\"></a>" to "<span><a b=\"&amp;\"></a></span>"),
            "<span><a b='&nbsp;'></a></span>" to ("<a b=\"&nbsp;\"></a>" to "<span><a b=\"&nbsp;\"></a></span>"),
            "<span><a b='\"'></a></span>" to ("<a b=\"&quot;\"></a>" to "<span><a b=\"&quot;\"></a></span>"),
            "<span><a b=\"<\"></a></span>" to ("<a b=\"&lt;\"></a>" to "<span><a b=\"&lt;\"></a></span>"),
            "<span><a b=\">\"></a></span>" to ("<a b=\"&gt;\"></a>" to "<span><a b=\"&gt;\"></a></span>"),
            "<span><a href=\"javascript:&quot;&lt;>&quot;\"></a></span>" to (
                "<a href=\"javascript:&quot;&lt;&gt;&quot;\"></a>" to
                    "<span><a href=\"javascript:&quot;&lt;&gt;&quot;\"></a></span>"
                ),
            "<span><svg xlink:href=\"a\"></svg></span>" to
                ("<svg xlink:href=\"a\"></svg>" to "<span><svg xlink:href=\"a\"></svg></span>"),
            "<span><svg xmlns:svg=\"test\"></svg></span>" to
                ("<svg xmlns:svg=\"test\"></svg>" to "<span><svg xmlns:svg=\"test\"></svg></span>"),
            "<span>a</span>" to ("a" to "<span>a</span>"),
            "<span>&amp;</span>" to ("&amp;" to "<span>&amp;</span>"),
            "<span>&nbsp;</span>" to ("&nbsp;" to "<span>&nbsp;</span>"),
            "<span>&lt;</span>" to ("&lt;" to "<span>&lt;</span>"),
            "<span>&gt;</span>" to ("&gt;" to "<span>&gt;</span>"),
            "<span>&quot;</span>" to ("\"" to "<span>\"</span>"),
            "<span><style><&></style></span>" to ("<style><&></style>" to "<span><style><&></style></span>"),
            "<span><script type=\"test\"><&></script></span>" to
                ("<script type=\"test\"><&></script>" to "<span><script type=\"test\"><&></script></span>"),
            "<script type=\"test\"><&></script>" to ("<&>" to "<script type=\"test\"><&></script>"),
            "<span><xmp><&></xmp></span>" to ("<xmp><&></xmp>" to "<span><xmp><&></xmp></span>"),
            "<span><iframe><&></iframe></span>" to ("<iframe><&></iframe>" to "<span><iframe><&></iframe></span>"),
            "<span><noembed><&></noembed></span>" to
                ("<noembed><&></noembed>" to "<span><noembed><&></noembed></span>"),
            "<span><noframes><&></noframes></span>" to
                ("<noframes><&></noframes>" to "<span><noframes><&></noframes></span>"),
            "<span><noscript><&></noscript></span>" to
                ("<noscript><&></noscript>" to "<span><noscript><&></noscript></span>"),
            "<span><!--data--></span>" to ("<!--data-->" to "<span><!--data--></span>"),
            "<span><a><b><c></c></b><d>e</d><f><g>h</g></f></a></span>" to (
                "<a><b><c></c></b><d>e</d><f><g>h</g></f></a>" to
                    "<span><a><b><c></c></b><d>e</d><f><g>h</g></f></a></span>"
                ),
            "<span b=c></span>" to ("" to "<span b=\"c\"></span>")
        )
        for ((html, expected) in cases) {
            val node = div(html).firstChild!!
            assertEquals(expected.first, node.innerHtml(), "innerHTML of $html")
            assertEquals(expected.second, node.outerHtml(), "outerHTML of $html")
        }
    }

    /** serializing.html's dom_tests: svg elements made with createElement, so in the HTML namespace. */
    @Test
    fun `serializing html attribute namespaces`() {
        val cases = listOf(
            Triple(Namespaces.XML, "xml:foo", "<svg xml:foo=\"test\"></svg>"),
            Triple(Namespaces.XML, "abc:foo", "<svg xml:foo=\"test\"></svg>"),
            Triple(Namespaces.XMLNS, "xmlns:foo", "<svg xmlns:foo=\"test\"></svg>"),
            Triple(Namespaces.XMLNS, "xmlns", "<svg xmlns=\"test\"></svg>"),
            Triple("fake_ns", "abc:def", "<svg abc:def=\"test\"></svg>")
        )
        for ((namespace, qualifiedName, expected) in cases) {
            val svg = element("svg")
            val prefix = qualifiedName.substringBefore(':', "").ifEmpty { null }
            svg.setAttributeNS(namespace, prefix, qualifiedName.substringAfter(':'), "test")
            val span = element("span", svg)
            assertEquals(expected, span.innerHtml(), qualifiedName)
            assertEquals("<span>$expected</span>", span.outerHtml(), qualifiedName)
        }
    }

    /** serializing.html's dom_tests and text_tests: the serializer doesn't add a newline to keep a leading one. */
    @Test
    fun `serializing html leading newlines`() {
        val span = element("span", TreeNode.createText("\n"))
        assertEquals("\n", span.innerHtml())
        assertEquals("<span>\n</span>", span.outerHtml())
        for (name in listOf("pre", "textarea", "listing")) {
            for (text in listOf("\n", "a\n")) {
                val context = element(name, TreeNode.createText(text))
                assertEquals(text, context.innerHtml(), name)
                assertEquals("<$name>$text</$name>", context.outerHtml(), name)
                val parent = element("span", element(name, TreeNode.createText(text)))
                assertEquals("<$name>$text</$name>", parent.innerHtml(), name)
                assertEquals("<span><$name>$text</$name></span>", parent.outerHtml(), name)
            }
        }
    }

    /** serializing.html's void_tests: void elements are serialized without their children. */
    @Test
    fun `serializing html void elements`() {
        val voids = listOf(
            "area", "base", "basefont", "bgsound", "br", "col", "embed", "frame", "hr", "img", "input", "keygen",
            "link", "meta", "param", "source", "track", "wbr"
        )
        fun makeVoid(name: String) = element(
            name,
            element("a", TreeNode.createComment("abc")),
            element("b", element("c", TreeNode.createText("abc")))
        )
        fun a() = element("a", TreeNode.createText("test"))
        for (name in voids) {
            assertEquals("", makeVoid(name).innerHtml(), name)
            assertEquals("<$name>", makeVoid(name).outerHtml(), name)

            val first = element("span", makeVoid(name), a(), element("b"))
            assertEquals("<$name><a>test</a><b></b>", first.innerHtml(), name)
            assertEquals("<span><$name><a>test</a><b></b></span>", first.outerHtml(), name)

            val second = element("span", a(), makeVoid(name), element("b"))
            assertEquals("<a>test</a><$name><b></b>", second.innerHtml(), name)
            assertEquals("<span><a>test</a><$name><b></b></span>", second.outerHtml(), name)

            val last = element("span", a(), element("b"), makeVoid(name))
            assertEquals("<a>test</a><b></b><$name>", last.innerHtml(), name)
            assertEquals("<span><a>test</a><b></b><$name></span>", last.outerHtml(), name)
        }
    }

    /** outerHTML.html: an element made with createElement has an end tag unless it is void. */
    @Test
    fun `outer html of created elements`() {
        val voids = listOf(
            "area", "base", "br", "col", "embed", "hr", "img", "input", "link", "meta", "param", "source", "track",
            "wbr"
        )
        val nonVoids = listOf(
            "a", "abbr", "address", "article", "aside", "audio", "b", "bdi", "bdo", "blockquote", "body", "button",
            "canvas", "caption", "cite", "code", "colgroup", "data", "datalist", "dd", "del", "details", "dfn",
            "dialog", "div", "dl", "dt", "em", "fieldset", "figcaption", "figure", "footer", "form", "h1", "head",
            "header", "html", "i", "iframe", "ins", "kbd", "label", "legend", "li", "main", "map", "mark", "menu",
            "meter", "nav", "noscript", "object", "ol", "optgroup", "option", "output", "p", "picture", "pre",
            "progress", "q", "rp", "rt", "ruby", "s", "samp", "script", "search", "section", "select", "slot",
            "small", "span", "strong", "style", "sub", "summary", "sup", "table", "tbody", "td", "template",
            "textarea", "tfoot", "th", "thead", "time", "title", "tr", "u", "ul", "var", "video"
        )
        for (name in voids) assertEquals("<$name>", element(name).outerHtml(), name)
        for (name in nonVoids) assertEquals("<$name></$name>", element(name).outerHtml(), name)
    }

    /** serializing-lt-gt.html: attribute values escape < and >. */
    @Test
    fun `less than and greater than signs in attribute values are escaped`() {
        val span = div("<span id=\"test\"><a b=\"<>\"></a></span>").firstChild!!
        assertEquals("<a b=\"&lt;&gt;\"></a>", span.innerHtml())
        assertEquals("<span id=\"test\"><a b=\"&lt;&gt;\"></a></span>", span.outerHtml())
    }

    /** processing-instructions.html */
    @Test
    fun `processing instructions`() {
        assertEquals("<?target ?>", element("div", TreeNode.createProcessingInstruction("target", "")).innerHtml())
        assertEquals(
            "<?target data?>",
            element("div", TreeNode.createProcessingInstruction("target", "data")).innerHtml()
        )
    }

    /** serializing-cdata-in-html-document.html: a CDATA section from an XML document is escaped as text. */
    @Test
    fun `cdata sections in an html document`() {
        val svg = TreeNode.createElement("svg", Namespaces.SVG)
        svg.setAttributeNS(Namespaces.XMLNS, null, "xmlns", Namespaces.SVG)
        svg.appendChild(TreeNode(type = NodeType.CDATA_SECTION, data = "<img>"))
        assertEquals("<svg xmlns=\"http://www.w3.org/2000/svg\">&lt;img&gt;</svg>", svg.outerHtml())
    }

    /** template.html */
    @Test
    fun `template contents are serialized`() {
        val template = div("<div><template><table><td></table></template></div>").firstChild!!.firstChild!!
        assertEquals("<table><tbody><tr><td></td></tr></tbody></table>", template.innerHtml())
        assertEquals("<template><table><tbody><tr><td></td></tr></tbody></table></template>", template.parent!!.innerHtml())

        val created = TreeNode.createElement("template")
        val content = TreeNode.createElement("div", namespace = "xx")
        created.templateContent!!.appendChild(content)
        content.setAttributeNS(Namespaces.XML, "xml:lang", "en-us")
        content.setAttributeNS("uri2", "p:attr", "v")
        assertEquals("<div xml:lang=\"en-us\" p:attr=\"v\"></div>", created.innerHtml())
    }

    /** initial-linefeed-pre.html */
    @Test
    fun `initial line feeds in pre textarea and listing`() {
        val html = "\n<div id=\"inner\">\n<pre id=\"pre1\">\nx</pre>\n<pre id=\"pre2\">\n\nx</pre>\n" +
            "<textarea id=\"textarea1\">\nx</textarea>\n<textarea id=\"textarea2\">\n\nx</textarea>\n" +
            "<listing id=\"listing1\">\nx</listing>\n<listing id=\"listing2\">\n\nx</listing>\n</div>\n"
        val expectedOuter = "\n<div id=\"inner\">\n<pre id=\"pre1\">x</pre>\n<pre id=\"pre2\">\nx</pre>\n" +
            "<textarea id=\"textarea1\">x</textarea>\n<textarea id=\"textarea2\">\nx</textarea>\n" +
            "<listing id=\"listing1\">x</listing>\n<listing id=\"listing2\">\nx</listing>\n</div>\n"
        val outer = div(html)
        assertEquals(expectedOuter, outer.innerHtml())
        val inner = outer.childElements.single()
        assertEquals(expectedOuter.replace("\n<div id=\"inner\">", "").replace("</div>\n", ""), inner.innerHtml())
        for (element in inner.childElements) {
            val expected = if (element.getAttribute("id")!!.endsWith("1")) "x" else "\nx"
            assertEquals(expected, element.innerHtml(), element.getAttribute("id"))
        }
    }

    /**
     * escaping.html: noscript's text is escaped unless scripting is enabled for it, which needs a document with a
     * browsing context. A browser's main document has one, and a template's contents, a DOMParser document and a
     * createHTMLDocument document don't, which [HTMLParser] models with scriptingEnabled set to false.
     */
    @Test
    fun `noscript text is escaped where scripting is disabled`() {
        val escaped = "&amp;&nbsp;&lt;&gt;"
        val unescaped = "&\u00A0<>"

        // div.innerHTML in the main document
        val div = div("<noscript>$unescaped</noscript>")
        assertEquals(unescaped, div.firstChild!!.firstChild!!.data)
        assertEquals(unescaped, div.firstChild!!.innerHtml())

        // template.innerHTML. The test expects the markup to be parsed with scripting disabled, as browsers parse
        // into a template's contents, while Helium's fragment parser, like the spec's, takes the scripting mode from
        // the template's own document. That is the parser's business, so the markup is parsed the way the test
        // expects, and what is checked is that serializing escapes the text even with scripting enabled.
        val template = TreeNode.createElement("template")
        val parsed = HTMLParser(scriptingEnabled = false).parseFragment("<noscript>$escaped</noscript>", template)
        while (parsed.hasChildren()) template.templateContent!!.appendChild(parsed.firstChild!!)
        val noscript = template.templateContent!!.firstChild!!
        assertEquals(unescaped, noscript.firstChild!!.data)
        assertEquals(escaped, noscript.innerHtml())

        // DOMParser.parseFromString, whose document has no browsing context
        val document = HTMLParser(scriptingEnabled = false).parse("<body><noscript>$escaped</noscript></body>")
        val body = document.findChildElement("html")!!.findChildElement("body")!!
        assertEquals(unescaped, body.firstChild!!.firstChild!!.data)
        assertEquals(escaped, body.firstChild!!.innerHtml(scriptingEnabled = false))
    }
}
