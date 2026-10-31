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

/**
 * Restores the camel case of SVG element names, which the tokenizer lowercases.
 *
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-inforeign
 */
internal object SVGTagNameAdjustments {

    private val svgTagNameAdjustments = mapOf(
        "altglyph" to "altGlyph",
        "altglyphdef" to "altGlyphDef",
        "altglyphitem" to "altGlyphItem",
        "animatecolor" to "animateColor",
        "animatemotion" to "animateMotion",
        "animatetransform" to "animateTransform",
        "clippath" to "clipPath",
        "feblend" to "feBlend",
        "fecolormatrix" to "feColorMatrix",
        "fecomponenttransfer" to "feComponentTransfer",
        "fecomposite" to "feComposite",
        "feconvolvematrix" to "feConvolveMatrix",
        "fediffuselighting" to "feDiffuseLighting",
        "fedisplacementmap" to "feDisplacementMap",
        "fedistantlight" to "feDistantLight",
        "fedropshadow" to "feDropShadow",
        "feflood" to "feFlood",
        "fefunca" to "feFuncA",
        "fefuncb" to "feFuncB",
        "fefuncg" to "feFuncG",
        "fefuncr" to "feFuncR",
        "fegaussianblur" to "feGaussianBlur",
        "feimage" to "feImage",
        "femerge" to "feMerge",
        "femergenode" to "feMergeNode",
        "femorphology" to "feMorphology",
        "feoffset" to "feOffset",
        "fepointlight" to "fePointLight",
        "fespecularlighting" to "feSpecularLighting",
        "fespotlight" to "feSpotLight",
        "fetile" to "feTile",
        "feturbulence" to "feTurbulence",
        "foreignobject" to "foreignObject",
        "glyphref" to "glyphRef",
        "lineargradient" to "linearGradient",
        "radialgradient" to "radialGradient",
        "textpath" to "textPath"
    )

    fun adjustSVGTagName(tagName: String): String = svgTagNameAdjustments[tagName] ?: tagName
}
