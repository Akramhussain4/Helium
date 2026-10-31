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
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tree.QuirksMode
import com.theakram.helium.tree.TreeNode
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * The parse errors of the "initial", "before html", "before head", "in head", "in head noscript", "after head" and
 * "text" insertion modes.
 */
internal class HeadModesParseErrorTest {

    private class Result(val document: TreeNode, val treeErrors: Int) {
        val html: TreeNode get() = document.findChildElement("html")!!
        val head: TreeNode get() = html.findChildElement("head")!!
        val body: TreeNode get() = html.findChildElement("body")!!
    }

    // Tree construction errors are the ones without a code
    private fun parse(html: String, scriptingEnabled: Boolean = true): Result {
        val parser = HTMLParser(scriptingEnabled)
        val document = parser.parse(html)
        return Result(document, parser.errors.count { it.code == null })
    }

    // "initial"

    @Test
    fun `html doctype is not a parse error`() {
        val result = parse("<!DOCTYPE html>")

        assertEquals(0, result.treeErrors)
        assertEquals(QuirksMode.NO_QUIRKS, result.document.quirksMode)
    }

    @Test
    fun `about legacy-compat system identifier is not a parse error`() {
        assertEquals(0, parse("<!DOCTYPE html SYSTEM \"about:legacy-compat\">").treeErrors)
    }

    @Test
    fun `doctype with another name is a parse error and sets quirks mode`() {
        val result = parse("<!DOCTYPE potato>")

        assertEquals(1, result.treeErrors)
        assertEquals(QuirksMode.QUIRKS, result.document.quirksMode)
    }

    @Test
    fun `doctype with a public identifier is a parse error`() {
        val result = parse("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">")

        assertEquals(1, result.treeErrors)
        assertEquals(QuirksMode.NO_QUIRKS, result.document.quirksMode)
    }

    @Test
    fun `doctype with an empty public identifier is a parse error`() {
        // An empty identifier is not missing
        assertEquals(1, parse("<!DOCTYPE html PUBLIC \"\">").treeErrors)
    }

    @Test
    fun `doctype with another system identifier is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html SYSTEM \"http://example.com/html.dtd\">").treeErrors)
        assertEquals(1, parse("<!DOCTYPE html SYSTEM \"ABOUT:LEGACY-COMPAT\">").treeErrors)
    }

    @Test
    fun `doctype sets limited-quirks mode only when a limited-quirks condition matches`() {
        val xhtml = parse("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\">")
        assertEquals(1, xhtml.treeErrors)
        assertEquals(QuirksMode.LIMITED_QUIRKS, xhtml.document.quirksMode)

        val html401WithSystemId = parse(
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" " +
                    "\"http://www.w3.org/TR/html4/loose.dtd\">"
        )
        // One parse error, though both identifiers are not allowed
        assertEquals(1, html401WithSystemId.treeErrors)
        assertEquals(QuirksMode.LIMITED_QUIRKS, html401WithSystemId.document.quirksMode)

        val html401WithoutSystemId = parse("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">")
        assertEquals(QuirksMode.QUIRKS, html401WithoutSystemId.document.quirksMode)
    }

    @Test
    fun `missing doctype is a parse error and sets quirks mode`() {
        val result = parse("<p>")

        assertEquals(1, result.treeErrors)
        assertEquals(QuirksMode.QUIRKS, result.document.quirksMode)
    }

    @Test
    fun `end of file in the initial insertion mode is a parse error like anything else`() {
        val result = parse("")

        assertEquals(1, result.treeErrors)
        assertEquals(QuirksMode.QUIRKS, result.document.quirksMode)
        assertEquals(listOf("head", "body"), result.html.children.map { it.localName })
    }

    @Test
    fun `missing doctype in an iframe srcdoc document is not a parse error`() {
        var treeErrors = 0
        val treeBuilder = TreeBuilder(onSwitchTokenizerState = {}, onParseError = { if (it == null) treeErrors++ })
        treeBuilder.document.isIframeSrcdoc = true

        treeBuilder.process(Token.EOF)

        assertEquals(0, treeErrors)
        assertEquals(QuirksMode.NO_QUIRKS, treeBuilder.document.quirksMode)
    }

    @Test
    fun `missing doctype doesn't change the mode when the parser cannot change the mode`() {
        var treeErrors = 0
        val treeBuilder = TreeBuilder(onSwitchTokenizerState = {}, onParseError = { if (it == null) treeErrors++ })
        treeBuilder.document.parserCannotChangeMode = true

        treeBuilder.process(Token.EOF)

        assertEquals(1, treeErrors)
        assertEquals(QuirksMode.NO_QUIRKS, treeBuilder.document.quirksMode)
    }

    // "before html"

    @Test
    fun `doctype before html is a parse error and is ignored`() {
        val result = parse("<!DOCTYPE html><!DOCTYPE html>")

        assertEquals(1, result.treeErrors)
        assertEquals(2, result.document.children.size)
    }

    @Test
    fun `other end tag before html is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html></p>").treeErrors)
    }

    @Test
    fun `head body html and br end tags before html are not parse errors`() {
        assertEquals(0, parse("<!DOCTYPE html></head>").treeErrors)
        assertEquals(0, parse("<!DOCTYPE html></html>").treeErrors)
    }

    @Test
    fun `html start tag keeps its attributes`() {
        assertEquals("ltr", parse("<!DOCTYPE html><html dir=ltr>").html.getAttribute("dir"))
    }

    // "before head"

    @Test
    fun `doctype before head is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><html><!DOCTYPE html>").treeErrors)
    }

    @Test
    fun `other end tag before head is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><html></p>").treeErrors)
    }

    @Test
    fun `head start tag keeps its attributes`() {
        assertEquals("x", parse("<!DOCTYPE html><head id=x>").head.getAttribute("id"))
    }

    // "in head"

    @Test
    fun `doctype in head is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><head><!DOCTYPE html>").treeErrors)
    }

    @Test
    fun `head start tag in head is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><head><head>").treeErrors)
    }

    @Test
    fun `other end tag in head is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><head></p>").treeErrors)
    }

    @Test
    fun `template end tag without a template is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><head></template>").treeErrors)
    }

    @Test
    fun `template end tag when the current node is not a template is a parse error`() {
        val result = parse("<!DOCTYPE html><head><template><div></template>")

        assertEquals(1, result.treeErrors)
        assertEquals("template", result.head.children.single().localName)
    }

    @Test
    fun `template with a shadowrootmode is inserted as a regular template`() {
        val result = parse("<!DOCTYPE html><body><div><template shadowrootmode=open>x</template></div>")

        val template = result.body.firstChild!!.children.single()
        assertEquals("template", template.localName)
        assertEquals(0, result.treeErrors)
    }

    @Test
    fun `script start tag inserts the script and its contents are script data`() {
        val result = parse("<!DOCTYPE html><head><script type=module><p></script>")

        val script = result.head.children.single()
        assertEquals("script", script.localName)
        assertEquals("module", script.getAttribute("type"))
        assertEquals("<p>", script.children.single().data)
        assertEquals(0, result.treeErrors)
    }

    @Test
    fun `noscript is raw text in head when scripting is enabled`() {
        val result = parse("<!DOCTYPE html><head><noscript><p></noscript>")

        assertEquals("<p>", result.head.children.single().children.single().data)
        assertEquals(0, result.treeErrors)
    }

    // "in head noscript"

    @Test
    fun `doctype in head noscript is a parse error`() {
        val result = parse("<!DOCTYPE html><head><noscript><!DOCTYPE html></noscript>", scriptingEnabled = false)

        assertEquals(1, result.treeErrors)
    }

    @Test
    fun `head and noscript start tags in head noscript are parse errors`() {
        assertEquals(1, parse("<!DOCTYPE html><noscript><head></noscript>", scriptingEnabled = false).treeErrors)
        assertEquals(1, parse("<!DOCTYPE html><noscript><noscript></noscript>", scriptingEnabled = false).treeErrors)
    }

    @Test
    fun `other end tag in head noscript is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><noscript></p></noscript>", scriptingEnabled = false).treeErrors)
    }

    @Test
    fun `anything else in head noscript is a parse error and closes the noscript`() {
        val result = parse("<!DOCTYPE html><noscript>x", scriptingEnabled = false)

        assertEquals(1, result.treeErrors)
        assertEquals("noscript", result.head.children.single().localName)
        assertEquals("x", result.body.children.single().data)
    }

    @Test
    fun `end of file in head noscript is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><noscript>", scriptingEnabled = false).treeErrors)
    }

    @Test
    fun `head elements in head noscript are not parse errors`() {
        val result = parse("<!DOCTYPE html><noscript><link><style></style> <!--c--></noscript>", scriptingEnabled = false)

        assertEquals(0, result.treeErrors)
        assertEquals(listOf("link", "style", null, null), result.head.children.single().children.map { it.localName })
    }

    // "after head"

    @Test
    fun `doctype after head is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><head></head><!DOCTYPE html>").treeErrors)
    }

    @Test
    fun `head element start tag after head is a parse error and goes in the head`() {
        val result = parse("<!DOCTYPE html><head></head><meta charset=utf-8>")

        assertEquals(1, result.treeErrors)
        assertEquals("meta", result.head.children.single().localName)
    }

    @Test
    fun `script after head is a parse error and the text mode returns to after head`() {
        val result = parse("<!DOCTYPE html><head></head><script>x</script><body>")

        assertEquals(1, result.treeErrors)
        assertEquals("script", result.head.children.single().localName)
        assertEquals(listOf("head", "body"), result.html.children.map { it.localName })
    }

    @Test
    fun `template after head is a parse error only for its start tag`() {
        val result = parse("<!DOCTYPE html><head></head><template></template>")

        assertEquals(1, result.treeErrors)
        assertEquals("template", result.head.children.single().localName)
    }

    @Test
    fun `head start tag after head is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><head></head><head>").treeErrors)
    }

    @Test
    fun `other end tag after head is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><head></head></p>").treeErrors)
    }

    @Test
    fun `body and frameset start tags keep their attributes`() {
        assertEquals("a", parse("<!DOCTYPE html><body class=a>").body.getAttribute("class"))
        assertEquals(
            "1,2",
            parse("<!DOCTYPE html><frameset rows=1,2></frameset>").html.findChildElement("frameset")!!.getAttribute("rows")
        )
    }

    // "text"

    @Test
    fun `end of file in RCDATA text is a parse error`() {
        val result = parse("<!DOCTYPE html><title>x")

        assertEquals(1, result.treeErrors)
        assertEquals("x", result.head.children.single().children.single().data)
    }

    @Test
    fun `end of file in a script is a parse error`() {
        assertEquals(1, parse("<!DOCTYPE html><script>x").treeErrors)
    }

    @Test
    fun `end tags in text return to the original insertion mode`() {
        val result = parse("<!DOCTYPE html><body><textarea>x</textarea><style>y</style>z")

        assertEquals(0, result.treeErrors)
        assertEquals(listOf("textarea", "style", null), result.body.children.map { it.localName })
    }

    @Test
    fun `body and html end tags before the html element are not parse errors but other end tags are`() {
        assertEquals(0, parse("<!DOCTYPE html></body>").treeErrors)
        assertEquals(0, parse("<!DOCTYPE html></html>").treeErrors)
        assertEquals(1, parse("<!DOCTYPE html></p>").treeErrors)
    }

    @Test
    fun `a br end tag in head noscript is handled as anything else`() {
        // The noscript's own error, then "in body"'s for the br end tag it reaches
        assertEquals(2, parse("<!DOCTYPE html><head><noscript></br>", scriptingEnabled = false).treeErrors)
    }

    @Test
    fun `a template end tag after head is processed with the in head rules only`() {
        // "in head" reports the missing template; "after head" adds no error of its own
        assertEquals(1, parse("<!DOCTYPE html><head></head></template>").treeErrors)
    }

    @Test
    fun `raw text in head noscript returns to head noscript`() {
        // If the text mode returned to "in head", the noscript end tag would be a parse error there
        val result = parse("<!DOCTYPE html><head><noscript><style>x</style></noscript>", scriptingEnabled = false)

        assertEquals(0, result.treeErrors)
        assertEquals("style", result.head.children.single().children.single().localName)
    }
}
