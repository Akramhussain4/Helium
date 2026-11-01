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

internal object TreeConstructionConstants {

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#special
     */
    val HTML_SPECIAL_ELEMENTS = setOf(
        "address", "applet", "area", "article", "aside", "base", "basefont",
        "bgsound", "blockquote", "body", "br", "button", "caption", "center",
        "col", "colgroup", "dd", "details", "dir", "div", "dl", "dt", "embed",
        "fieldset", "figcaption", "figure", "footer", "form", "frame", "frameset",
        "h1", "h2", "h3", "h4", "h5", "h6", "head", "header", "hgroup", "hr",
        "html", "iframe", "img", "input", "keygen", "li", "link", "listing",
        "main", "marquee", "menu", "meta", "nav", "noembed", "noframes",
        "noscript", "object", "ol", "p", "param", "plaintext", "pre", "script",
        "search", "section", "select", "source", "style", "summary", "table",
        "tbody", "td", "template", "textarea", "tfoot", "th", "thead", "title",
        "tr", "track", "ul", "wbr", "xmp"
    )

    val MATHML_SPECIAL_ELEMENTS = setOf(
        "mi", "mo", "mn", "ms", "mtext", "annotation-xml"
    )

    val SVG_SPECIAL_ELEMENTS = setOf(
        "foreignObject", "desc", "title"
    )

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#formatting
     */
    val FORMATTING_ELEMENTS = setOf(
        "a", "b", "big", "code", "em", "font", "i", "nobr", "s", "small",
        "strike", "strong", "tt", "u"
    )

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#generate-implied-end-tags
     */
    val IMPLIED_END_TAG_ELEMENTS = setOf(
        "dd", "dt", "li", "optgroup", "option", "p", "rb", "rp", "rt", "rtc"
    )

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#generate-implied-end-tags
     */
    val THOROUGH_IMPLIED_END_TAG_ELEMENTS = IMPLIED_END_TAG_ELEMENTS + setOf(
        "caption", "colgroup", "tbody", "td", "tfoot", "th", "thead", "tr"
    )

    /**
     * Spec: https://html.spec.whatwg.org/multipage/syntax.html#void-elements
     */
    val VOID_ELEMENTS = setOf(
        "area", "base", "br", "col", "embed", "hr", "img", "input",
        "link", "meta", "param", "source", "track", "wbr"
    )

    /**
     * The heading elements, h1 to h6. A heading start tag closes a heading that is the current node, but headings can
     * still nest, as in `<h1><div><h2>`.
     */
    val HEADING_ELEMENTS = setOf("h1", "h2", "h3", "h4", "h5", "h6")

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#foster-parent
     */
    val FOSTER_PARENTING_ELEMENTS = setOf(
        "table", "tbody", "tfoot", "thead", "tr"
    )

    fun isSpecialElement(element: TreeNode): Boolean {
        val localName = element.localName ?: return false
        return when (element.namespace) {
            Namespaces.HTML -> localName in HTML_SPECIAL_ELEMENTS
            Namespaces.MATHML -> localName in MATHML_SPECIAL_ELEMENTS
            Namespaces.SVG -> localName in SVG_SPECIAL_ELEMENTS
            else -> false
        }
    }

    fun isFormattingElement(element: TreeNode): Boolean {
        return element.namespace == Namespaces.HTML &&
                element.localName in FORMATTING_ELEMENTS
    }

    fun requiresFosterParenting(element: TreeNode): Boolean {
        return element.namespace == Namespaces.HTML &&
                element.localName in FOSTER_PARENTING_ELEMENTS
    }
}
