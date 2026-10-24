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

import com.theakram.helium.tree.Attribute

internal object ForeignAttributeAdjustments {

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#adjust-svg-attributes
     */
    private val svgAttributeAdjustments = mapOf(
        "attributename" to "attributeName",
        "attributetype" to "attributeType",
        "basefrequency" to "baseFrequency",
        "baseprofile" to "baseProfile",
        "calcmode" to "calcMode",
        "clippathunits" to "clipPathUnits",
        "diffuseconstant" to "diffuseConstant",
        "edgemode" to "edgeMode",
        "filterunits" to "filterUnits",
        "glyphref" to "glyphRef",
        "gradienttransform" to "gradientTransform",
        "gradientunits" to "gradientUnits",
        "kernelmatrix" to "kernelMatrix",
        "kernelunitlength" to "kernelUnitLength",
        "keypoints" to "keyPoints",
        "keysplines" to "keySplines",
        "keytimes" to "keyTimes",
        "lengthadjust" to "lengthAdjust",
        "limitingconeangle" to "limitingConeAngle",
        "markerheight" to "markerHeight",
        "markerunits" to "markerUnits",
        "markerwidth" to "markerWidth",
        "maskcontentunits" to "maskContentUnits",
        "maskunits" to "maskUnits",
        "numoctaves" to "numOctaves",
        "pathlength" to "pathLength",
        "patterncontentunits" to "patternContentUnits",
        "patterntransform" to "patternTransform",
        "patternunits" to "patternUnits",
        "pointsatx" to "pointsAtX",
        "pointsaty" to "pointsAtY",
        "pointsatz" to "pointsAtZ",
        "preservealpha" to "preserveAlpha",
        "preserveaspectratio" to "preserveAspectRatio",
        "primitiveunits" to "primitiveUnits",
        "refx" to "refX",
        "refy" to "refY",
        "repeatcount" to "repeatCount",
        "repeatdur" to "repeatDur",
        "requiredextensions" to "requiredExtensions",
        "requiredfeatures" to "requiredFeatures",
        "specularconstant" to "specularConstant",
        "specularexponent" to "specularExponent",
        "spreadmethod" to "spreadMethod",
        "startoffset" to "startOffset",
        "stddeviation" to "stdDeviation",
        "stitchtiles" to "stitchTiles",
        "surfacescale" to "surfaceScale",
        "systemlanguage" to "systemLanguage",
        "tablevalues" to "tableValues",
        "targetx" to "targetX",
        "targety" to "targetY",
        "textlength" to "textLength",
        "viewbox" to "viewBox",
        "viewtarget" to "viewTarget",
        "xchannelselector" to "xChannelSelector",
        "ychannelselector" to "yChannelSelector",
        "zoomandpan" to "zoomAndPan"
    )

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#adjust-mathml-attributes
     */
    private val mathMLAttributeAdjustments = mapOf(
        "definitionurl" to "definitionURL"
    )

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#adjust-foreign-attributes
     */
    private data class ForeignAttrSpec(
        val namespace: String,
        val prefix: String?,
        val localName: String
    )

    private val foreignAttributeAdjustments = mapOf(
        "xlink:actuate" to ForeignAttrSpec("http://www.w3.org/1999/xlink", "xlink", "actuate"),
        "xlink:arcrole" to ForeignAttrSpec("http://www.w3.org/1999/xlink", "xlink", "arcrole"),
        "xlink:href" to ForeignAttrSpec("http://www.w3.org/1999/xlink", "xlink", "href"),
        "xlink:role" to ForeignAttrSpec("http://www.w3.org/1999/xlink", "xlink", "role"),
        "xlink:show" to ForeignAttrSpec("http://www.w3.org/1999/xlink", "xlink", "show"),
        "xlink:title" to ForeignAttrSpec("http://www.w3.org/1999/xlink", "xlink", "title"),
        "xlink:type" to ForeignAttrSpec("http://www.w3.org/1999/xlink", "xlink", "type"),
        "xml:base" to ForeignAttrSpec("http://www.w3.org/XML/1998/namespace", "xml", "base"),
        "xml:lang" to ForeignAttrSpec("http://www.w3.org/XML/1998/namespace", "xml", "lang"),
        "xml:space" to ForeignAttrSpec("http://www.w3.org/XML/1998/namespace", "xml", "space"),
        "xmlns" to ForeignAttrSpec("http://www.w3.org/2000/xmlns/", null, "xmlns"),
        "xmlns:xlink" to ForeignAttrSpec("http://www.w3.org/2000/xmlns/", "xmlns", "xlink")
    )

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#adjust-svg-attributes
     */
    fun adjustSVGAttributes(attributes: Map<String, String>): Map<String, String> {
        return attributes.mapKeys { (name, _) ->
            svgAttributeAdjustments[name] ?: name
        }
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#adjust-mathml-attributes
     */
    fun adjustMathMLAttributes(attributes: Map<String, String>): Map<String, String> {
        return attributes.mapKeys { (name, _) ->
            mathMLAttributeAdjustments[name] ?: name
        }
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#adjust-foreign-attributes
     */
    fun adjustForeignAttributes(attributes: Map<String, String>): List<Attribute> {
        return attributes.map { (name, value) ->
            val foreignAttr = foreignAttributeAdjustments[name]
            if (foreignAttr != null) {
                Attribute(
                    namespace = foreignAttr.namespace,
                    prefix = foreignAttr.prefix,
                    localName = foreignAttr.localName,
                    value = value
                )
            } else {
                // Not a foreign attribute, create with null namespace
                Attribute(
                    namespace = null,
                    prefix = null,
                    localName = name,
                    value = value
                )
            }
        }
    }

    /**
     * Combined adjustment for SVG: adjust SVG attributes, then adjust foreign attributes.
     */
    fun adjustSVGAttributesFull(attributes: Map<String, String>): List<Attribute> {
        val svgAdjusted = adjustSVGAttributes(attributes)
        return adjustForeignAttributes(svgAdjusted)
    }

    /**
     * Combined adjustment for MathML: adjust MathML attributes, then adjust foreign attributes.
     */
    fun adjustMathMLAttributesFull(attributes: Map<String, String>): List<Attribute> {
        val mathMLAdjusted = adjustMathMLAttributes(attributes)
        return adjustForeignAttributes(mathMLAdjusted)
    }
}
