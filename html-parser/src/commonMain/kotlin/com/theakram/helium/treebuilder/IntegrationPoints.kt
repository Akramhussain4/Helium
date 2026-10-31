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
import com.theakram.helium.util.asciiLowercase

internal object IntegrationPoints {

    private val MATHML_TEXT_INTEGRATION_POINTS = setOf("mi", "mo", "mn", "ms", "mtext")

    private val SVG_HTML_INTEGRATION_POINTS = setOf("foreignObject", "desc", "title")

    private val HTML_ENCODINGS = setOf("text/html", "application/xhtml+xml")

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#mathml-text-integration-point
     */
    fun isMathMLTextIntegrationPoint(node: TreeNode): Boolean =
        node.namespace == Namespaces.MATHML && node.localName in MATHML_TEXT_INTEGRATION_POINTS

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#html-integration-point
     */
    fun isHTMLIntegrationPoint(node: TreeNode): Boolean = when (node.namespace) {
        // "A MathML annotation-xml element whose start tag token had an attribute with the name "encoding" whose value
        // was an ASCII case-insensitive match for "text/html" [or] "application/xhtml+xml""
        Namespaces.MATHML -> node.localName == "annotation-xml" &&
                startTagEncodingAttributeValue(node)?.asciiLowercase() in HTML_ENCODINGS

        Namespaces.SVG -> node.localName in SVG_HTML_INTEGRATION_POINTS

        else -> false
    }

    /**
     * The value of the attribute named exactly "encoding" on the start tag token the element was created for. The
     * element's attributes are the token's: attribute adjustments never rename "encoding", so the token's attribute
     * is the element's attribute whose qualified name is "encoding". For the fragment context element, the token is
     * the "fake" token, whose attributes are the context element's.
     */
    private fun startTagEncodingAttributeValue(element: TreeNode): String? =
        element.attributes.firstOrNull { it.prefix == null && it.localName == "encoding" }?.value
}
