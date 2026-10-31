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
package com.theakram.helium.tokenizer

import com.theakram.helium.HTMLParser
import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import kotlin.test.Test
import kotlin.test.assertEquals

internal class TagTokenTest {

    private fun tokenize(html: String): Pair<List<Token>, List<String>> {
        val tokens = mutableListOf<Token>()
        val errors = mutableListOf<String>()
        Tokenizer(onTokenEmitted = tokens::add, onParseError = { code, _ -> errors += code }).tokenize(html)
        return tokens.filterNot { it is Token.EOF } to errors
    }

    @Test
    fun `a duplicate attribute is removed with its value and later attributes are kept`() {
        val (tokens, errors) = tokenize("<a x=1 x=2 y=3>")

        assertEquals(listOf(Token.StartTag("a", attributes = mapOf("x" to "1", "y" to "3"))), tokens)
        assertEquals(listOf("duplicate-attribute"), errors)
    }

    @Test
    fun `end tags keep their attributes and self-closing flag and report them`() {
        val (tokens, errors) = tokenize("</a x=1 x=\"2\"/>")

        assertEquals(listOf(Token.EndTag("a", selfClosing = true, attributes = mapOf("x" to "1"))), tokens)
        assertEquals(listOf("duplicate-attribute", "end-tag-with-attributes", "end-tag-with-trailing-solidus"), errors)
    }

    @Test
    fun `an appropriate end tag in RCDATA can have attributes`() {
        val (tokens, errors) = tokenize("<title>x</title a=b>")

        assertEquals(Token.EndTag("title", attributes = mapOf("a" to "b")), tokens.last())
        assertEquals(listOf("end-tag-with-attributes"), errors)
    }

    @Test
    fun `tree construction switches the tokenizer after the tag token is emitted`() {
        // The tokenizer switches to the data state before emitting <textarea>, so the tree builder's switch to
        // RCDATA is the one that holds
        val textarea = HTMLParser().parse("<textarea><b>x</b></textarea>")
            .findChildElement("html")!!.findChildElement("body")!!.children.single()

        assertEquals("<b>x</b>", textarea.children.single().data)
    }
}
