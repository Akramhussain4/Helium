/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
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

/**
 * Represents an attribute in the HTML tree.
 *
 * Attributes can be namespaced (e.g., xlink:href in SVG) or non-namespaced.
 * This follows the WHATWG specification for attribute handling.
 *
 * Spec references:
 * - Attributes: https://dom.spec.whatwg.org/#interface-attr
 */
public data class Attribute(
    /**
     * The namespace URI of the attribute (e.g., "http://www.w3.org/1999/xlink").
     * Null for non-namespaced attributes.
     */
    val namespace: String? = null,

    /**
     * The namespace prefix (e.g., "xlink" in "xlink:href").
     * Null for non-namespaced attributes.
     */
    val prefix: String? = null,

    /**
     * The local name of the attribute (e.g., "href" in "xlink:href", or "class" in "class").
     */
    val localName: String,

    /**
     * The value of the attribute.
     */
    val value: String
) {
    /**
     * The qualified name of the attribute.
     * Returns "prefix:localName" for namespaced attributes, or just "localName" otherwise.
     */
    val name: String
        get() = if (prefix != null) "$prefix:$localName" else localName
}
