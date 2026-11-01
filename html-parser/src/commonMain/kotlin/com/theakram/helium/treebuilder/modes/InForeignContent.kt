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

package com.theakram.helium.treebuilder.modes

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.treebuilder.ForeignAttributeAdjustments
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.IntegrationPoints
import com.theakram.helium.treebuilder.SVGTagNameAdjustments
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.util.asciiLowercase
import com.theakram.helium.util.isAsciiWhitespace

/**
 * The rules for tokens inside SVG and MathML. Not an insertion mode in the spec: the tree builder
 * applies these rules instead of the current insertion mode when the adjusted current node is foreign.
 *
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-inforeign
 */
internal object InForeignContent : InsertionMode {

    private val BREAKOUT_START_TAGS = setOf(
        "b", "big", "blockquote", "body", "br", "center", "code", "dd", "div", "dl", "dt", "em",
        "embed", "h1", "h2", "h3", "h4", "h5", "h6", "head", "hr", "i", "img", "li", "listing",
        "menu", "meta", "nobr", "ol", "p", "pre", "ruby", "s", "small", "span", "strong",
        "strike", "sub", "sup", "table", "tt", "u", "ul", "var"
    )

    private val FONT_BREAKOUT_ATTRIBUTES = setOf("color", "face", "size")

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character if token.codePoint == 0x0000 -> {
                // Parse error. Insert a U+FFFD REPLACEMENT CHARACTER character.
                treeBuilder.parseError()
                treeBuilder.insertCharacter(0xFFFD)
                continueInCurrentMode()
            }

            is Token.Character if token.codePoint.isAsciiWhitespace() -> {
                treeBuilder.insertCharacter(token.codePoint)
                continueInCurrentMode()
            }

            is Token.Character -> {
                treeBuilder.insertCharacter(token.codePoint)
                treeBuilder.setFramesetNotOk()
                continueInCurrentMode()
            }

            is Token.Comment -> {
                treeBuilder.insertComment(token.data)
                continueInCurrentMode()
            }

            is Token.ProcessingInstruction -> {
                treeBuilder.insertProcessingInstruction(token.target, token.data)
                continueInCurrentMode()
            }

            is Token.DocType -> {
                // Parse error. Ignore the token.
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            is Token.StartTag if token.name in BREAKOUT_START_TAGS ||
                    (token.name == "font" && token.attributes.keys.any { it in FONT_BREAKOUT_ATTRIBUTES }) -> {
                breakOutOfForeignContent(token, treeBuilder)
            }

            is Token.EndTag if token.name == "br" || token.name == "p" -> {
                breakOutOfForeignContent(token, treeBuilder)
            }

            is Token.StartTag -> processAnyOtherStartTag(token, treeBuilder)

            is Token.EndTag if token.name == "script" && treeBuilder.currentNode.isSVGScript() -> {
                processSVGScriptEndTag(treeBuilder)
                continueInCurrentMode()
            }

            is Token.EndTag -> processAnyOtherEndTag(token, treeBuilder)

            is Token.EOF -> error("The tree construction dispatcher processes end-of-file tokens in HTML content")
        }
    }

    private fun breakOutOfForeignContent(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        // Parse error.
        treeBuilder.parseError()

        // While the current node is not a MathML text integration point, an HTML integration point, or an element
        // in the HTML namespace, pop elements from the stack of open elements.
        while (true) {
            val node = treeBuilder.currentNode
            if (node.namespace == Namespaces.HTML ||
                IntegrationPoints.isMathMLTextIntegrationPoint(node) ||
                treeBuilder.isHTMLIntegrationPoint(node)
            ) {
                break
            }
            treeBuilder.popCurrentElement()
        }

        // Reprocess the token according to the rules given in the section corresponding to the current insertion
        // mode in HTML content.
        return treeBuilder.currentMode.process(token, treeBuilder)
    }

    private fun processAnyOtherStartTag(token: Token.StartTag, treeBuilder: TreeBuilder): InsertionModeTransition {
        // An element created through the DOM can be in no namespace; its elements then are too
        val namespace = treeBuilder.adjustedCurrentNode.namespace

        // Adjust MathML attributes (MathML), change the tag name and adjust SVG attributes (SVG), then adjust
        // foreign attributes for the token.
        val (tagName, attributes) = when (namespace) {
            Namespaces.MATHML -> token.name to ForeignAttributeAdjustments.adjustMathMLAttributesFull(token.attributes)
            Namespaces.SVG -> SVGTagNameAdjustments.adjustSVGTagName(token.name) to
                    ForeignAttributeAdjustments.adjustSVGAttributesFull(token.attributes)
            else -> token.name to ForeignAttributeAdjustments.adjustForeignAttributes(token.attributes)
        }

        // Insert a foreign element for the token, with the adjusted current node's namespace.
        treeBuilder.insertElement(tagName, namespace, attributes)

        // If the token has its self-closing flag set, then run the appropriate steps from the following list:
        if (token.selfClosing) {
            if (token.name == "script" && treeBuilder.currentNode.namespace == Namespaces.SVG) {
                // Acknowledge the token's self-closing flag, and then act as described in the steps for a "script"
                // end tag below.
                treeBuilder.acknowledgeSelfClosingFlag()
                processSVGScriptEndTag(treeBuilder)
            } else {
                // Pop the current node off the stack of open elements and acknowledge the token's self-closing flag.
                treeBuilder.popCurrentElement()
                treeBuilder.acknowledgeSelfClosingFlag()
            }
        }
        return continueInCurrentMode()
    }

    /**
     * The steps for an end tag whose tag name is "script", if the current node is an SVG script element. Helium
     * doesn't support scripting, so only the first step, which pops the element, affects the tree.
     */
    private fun processSVGScriptEndTag(treeBuilder: TreeBuilder) {
        // Pop the current node off the stack of open elements.
        treeBuilder.popCurrentElement()

        // Not applicable without scripting: let the old insertion point have the same value as the current insertion
        // point, and let the insertion point be just before the next input character.

        // Not applicable without scripting: increment the parser's script nesting level by one and set the parser
        // pause flag to true.

        // Not applicable without scripting: if the active speculative HTML parser is null and the user agent supports
        // SVG, then process the SVG script element according to the SVG rules.

        // Not applicable without scripting: decrement the parser's script nesting level by one, and if it is zero,
        // set the parser pause flag to false.

        // Not applicable without scripting: let the insertion point have the value of the old insertion point.
    }

    private fun processAnyOtherEndTag(token: Token.EndTag, treeBuilder: TreeBuilder): InsertionModeTransition {
        val stack = treeBuilder.elementStack

        // Step 1:
        var index = stack.lastIndex
        var node = stack[index]

        // Step 2:
        if (!node.hasTagNameOf(token)) {
            treeBuilder.parseError()
        }

        while (true) {
            // Step 3:
            if (index == 0) return continueInCurrentMode()

            // Step 4:
            if (node.hasTagNameOf(token)) {
                treeBuilder.popUntilElement(node)
                return continueInCurrentMode()
            }

            // Step 5:
            index--
            node = stack[index]

            // Steps 6-7:
            if (node.namespace == Namespaces.HTML) {
                return treeBuilder.currentMode.process(token, treeBuilder)
            }
        }
    }

    private fun TreeNode.isSVGScript(): Boolean = namespace == Namespaces.SVG && localName == "script"

    // Whether the element's tag name, converted to ASCII lowercase, is the same as the tag name of the token
    private fun TreeNode.hasTagNameOf(token: Token.EndTag): Boolean =
        checkNotNull(localName) { "The stack of open elements only holds elements" }.asciiLowercase() == token.name
}
