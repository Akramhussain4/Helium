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
        Namespaces.MATHML -> node.localName == "annotation-xml" &&
                node.getAttribute("encoding")?.lowercase() in HTML_ENCODINGS

        Namespaces.SVG -> node.localName in SVG_HTML_INTEGRATION_POINTS

        else -> false
    }
}
