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
 * Namespace URIs as defined by the WHATWG HTML specification.
 *
 * These are used to distinguish elements and attributes from different XML vocabularies
 * (HTML, SVG, MathML, XLink, XML, XMLNS).
 *
 * Spec references:
 * - Namespaces: https://infra.spec.whatwg.org/#namespaces
 */
public object Namespaces {
    /**
     * HTML namespace.
     * Used for all standard HTML elements.
     */
    public const val HTML: String = "http://www.w3.org/1999/xhtml"

    /**
     * SVG namespace.
     * Used for Scalable Vector Graphics elements.
     */
    public const val SVG: String = "http://www.w3.org/2000/svg"

    /**
     * MathML namespace.
     * Used for Mathematical Markup Language elements.
     */
    public const val MATHML: String = "http://www.w3.org/1998/Math/MathML"

    /**
     * XLink namespace.
     * Used for XLink attributes (commonly in SVG).
     */
    public const val XLINK: String = "http://www.w3.org/1999/xlink"

    /**
     * XML namespace.
     * Used for XML-specific attributes like xml:lang.
     */
    public const val XML: String = "http://www.w3.org/XML/1998/namespace"

    /**
     * XMLNS namespace.
     * Used for xmlns namespace declarations.
     */
    public const val XMLNS: String = "http://www.w3.org/2000/xmlns/"
}
