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
            is Token.Character if token.data == '\u0000' -> {
                // Parse error: unexpected null character
                treeBuilder.insertCharacter('�')
                continueInCurrentMode()
            }

            is Token.Character if token.data.code.isAsciiWhitespace() -> {
                treeBuilder.insertCharacter(token.data)
                continueInCurrentMode()
            }

            is Token.Character -> {
                treeBuilder.insertCharacter(token.data)
                treeBuilder.setFramesetNotOk()
                continueInCurrentMode()
            }

            is Token.Comment -> {
                treeBuilder.insertComment(token.data)
                continueInCurrentMode()
            }

            is Token.DocType -> {
                // Parse error
                continueInCurrentMode()
            }

            is Token.StartTag if token.name in BREAKOUT_START_TAGS ||
                    (token.name == "font" && token.attributes.keys.any { it in FONT_BREAKOUT_ATTRIBUTES }) -> {
                breakOutOfForeignContent(token, treeBuilder)
            }

            is Token.EndTag if token.name == "br" || token.name == "p" -> {
                breakOutOfForeignContent(token, treeBuilder)
            }

            is Token.StartTag -> insertForeignElement(token, treeBuilder)

            is Token.EndTag if token.name == "script" &&
                    treeBuilder.currentNode.namespace == Namespaces.SVG &&
                    treeBuilder.currentNode.localName == "script" -> {
                // Scripts aren't executed, so this only pops the element
                treeBuilder.popCurrentElement()
                continueInCurrentMode()
            }

            is Token.EndTag -> processAnyOtherEndTag(token, treeBuilder)

            // The dispatcher sends end-of-file to the current insertion mode
            is Token.EOF -> treeBuilder.currentMode.process(token, treeBuilder)
        }
    }

    private fun breakOutOfForeignContent(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        // Parse error
        while (true) {
            val node = treeBuilder.currentNode
            if (node.namespace == Namespaces.HTML ||
                IntegrationPoints.isMathMLTextIntegrationPoint(node) ||
                IntegrationPoints.isHTMLIntegrationPoint(node)
            ) {
                break
            }
            treeBuilder.popCurrentElement()
        }
        return treeBuilder.currentMode.process(token, treeBuilder)
    }

    private fun insertForeignElement(token: Token.StartTag, treeBuilder: TreeBuilder): InsertionModeTransition {
        val namespace = treeBuilder.adjustedCurrentNode.namespace ?: Namespaces.HTML
        val (tagName, attributes) = when (namespace) {
            Namespaces.MATHML -> token.name to ForeignAttributeAdjustments.adjustMathMLAttributesFull(token.attributes)
            Namespaces.SVG -> SVGTagNameAdjustments.adjustSVGTagName(token.name) to
                    ForeignAttributeAdjustments.adjustSVGAttributesFull(token.attributes)
            else -> token.name to ForeignAttributeAdjustments.adjustForeignAttributes(token.attributes)
        }
        treeBuilder.insertElement(tagName, namespace, attributes)

        // A self-closing SVG script is handled like its end tag, which also just pops it
        if (token.selfClosing) {
            treeBuilder.popCurrentElement()
        }
        return continueInCurrentMode()
    }

    private fun processAnyOtherEndTag(token: Token.EndTag, treeBuilder: TreeBuilder): InsertionModeTransition {
        val stack = treeBuilder.elementStack

        // Step 1:
        var index = stack.lastIndex
        var node = stack[index]

        // Step 2:
        if (node.localName?.asciiLowercase() != token.name) {
            // Parse error
        }

        while (true) {
            // Step 3:
            if (index == 0) return continueInCurrentMode()

            // Step 4:
            if (node.localName?.asciiLowercase() == token.name) {
                treeBuilder.popUntilElement(node)
                return continueInCurrentMode()
            }

            // Step 5:
            index--
            node = stack[index]

            // Step 6-7:
            if (node.namespace == Namespaces.HTML) {
                return treeBuilder.currentMode.process(token, treeBuilder)
            }
        }
    }
}
