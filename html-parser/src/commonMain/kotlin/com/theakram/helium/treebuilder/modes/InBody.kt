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
import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.QuirksMode
import com.theakram.helium.treebuilder.ForeignAttributeAdjustments
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.ScriptingMode
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.TreeConstructionConstants
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.modes.helpers.*
import com.theakram.helium.treebuilder.stopParsing
import com.theakram.helium.treebuilder.switchTo
import com.theakram.helium.util.isAsciiWhitespace

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-inbody
 */
internal object InBody : InsertionMode {

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character -> {
                when {
                    token.codePoint == 0x0000 -> {
                        // Parse error: unexpected null character
                        continueInCurrentMode()
                    }

                    token.codePoint.isAsciiWhitespace() -> {
                        treeBuilder.reconstructActiveFormattingElements()
                        treeBuilder.insertCharacter(token.codePoint)
                        continueInCurrentMode()
                    }

                    else -> {
                        treeBuilder.reconstructActiveFormattingElements()
                        treeBuilder.insertCharacter(token.codePoint)
                        treeBuilder.setFramesetNotOk()
                        continueInCurrentMode()
                    }
                }
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
                // Parse error: unexpected DOCTYPE in body
                continueInCurrentMode()
            }

            is Token.StartTag -> processStartTag(token, treeBuilder)
            is Token.EndTag -> processEndTag(token, treeBuilder)

            is Token.EOF -> {
                if (treeBuilder.currentTemplateInsertionMode() != null) {
                    return InTemplate.process(token, treeBuilder)
                }
                // Otherwise, stop parsing.
                stopParsing()
            }
        }
    }

    private fun processStartTag(
        token: Token.StartTag,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        val tagName = token.name
        val attributes = InBodyHelpers.toAttributeList(token.attributes)

        return when (tagName) {
            "html" -> processHtmlStartTag(token, treeBuilder)

            in setOf(
                "base", "basefont", "bgsound", "link", "meta", "noframes",
                "script", "style", "template", "title"
            ) -> InHead.process(token, treeBuilder)

            "body" -> processBodyStartTag(token, treeBuilder)

            "frameset" -> processFramesetStartTag(token, treeBuilder)

            in setOf(
                "address", "article", "aside", "blockquote", "center", "details",
                "dialog", "dir", "div", "dl", "fieldset", "figcaption", "figure",
                "footer", "header", "hgroup", "main", "menu", "nav", "ol", "p",
                "search", "section", "summary", "ul"
            ) -> {
                BlockElementHandler.handleBlockElement(treeBuilder, tagName, attributes)
            }

            in TreeConstructionConstants.HEADING_ELEMENTS -> {
                HeadingHandler.handleHeadingStartTag(treeBuilder, tagName, attributes)
            }

            in setOf("pre", "listing") -> {
                BlockElementHandler.handlePreElement(treeBuilder, tagName, attributes)
            }

            "form" -> FormElementHandler.handleFormStartTag(treeBuilder, attributes)

            "li" -> ListElementHandler.handleLiStartTag(treeBuilder, attributes)

            in setOf("dd", "dt") -> {
                ListElementHandler.handleDdDtStartTag(treeBuilder, tagName, attributes)
            }

            "plaintext" -> BlockElementHandler.handlePlaintextElement(treeBuilder, attributes)

            "button" -> SpecialElementHandler.handleButtonStartTag(treeBuilder, attributes)

            "a" -> FormattingElementHandler.handleAStartTag(treeBuilder, attributes)

            in TreeConstructionConstants.FORMATTING_ELEMENTS - setOf("a", "nobr") -> {
                FormattingElementHandler.handleFormattingElementStartTag(treeBuilder, tagName, attributes)
            }

            "nobr" -> FormattingElementHandler.handleNobrStartTag(treeBuilder, attributes)

            in setOf("applet", "marquee", "object") -> {
                SpecialElementHandler.handleAppletStartTag(treeBuilder, tagName, attributes)
            }

            "table" -> {
                if (treeBuilder.document.quirksMode != QuirksMode.QUIRKS &&
                    treeBuilder.hasElementInButtonScope("p")
                ) {
                    InBodyHelpers.closePElement(treeBuilder)
                }
                treeBuilder.insertElement("table", attributes = attributes)
                treeBuilder.setFramesetNotOk()
                switchTo(InTable)
            }

            in setOf("area", "br", "embed", "img", "keygen", "wbr") -> {
                MediaElementHandler.handleAreaStartTag(treeBuilder, tagName, attributes)
            }

            "input" -> MediaElementHandler.handleInputStartTag(treeBuilder, attributes)

            in setOf("param", "source", "track") -> {
                MediaElementHandler.handleParamStartTag(treeBuilder, tagName, attributes)
            }

            "hr" -> BlockElementHandler.handleHrElement(treeBuilder, attributes)

            "image" -> MediaElementHandler.handleImageStartTag(treeBuilder, attributes)

            "textarea" -> SpecialElementHandler.handleTextareaStartTag(treeBuilder, attributes)

            "xmp" -> SpecialElementHandler.handleXmpStartTag(treeBuilder, attributes)

            "iframe" -> SpecialElementHandler.handleIframeStartTag(treeBuilder, attributes)

            "noembed" -> SpecialElementHandler.handleNoembedStartTag(treeBuilder, tagName, attributes)

            // With scripting disabled, noscript is parsed like any other element
            "noscript" if treeBuilder.scriptingMode != ScriptingMode.DISABLED -> {
                SpecialElementHandler.handleNoembedStartTag(treeBuilder, tagName, attributes)
            }

            "select" -> SpecialElementHandler.handleSelectStartTag(treeBuilder, attributes)

            "option" -> SpecialElementHandler.handleOptionStartTag(treeBuilder, attributes)

            "optgroup" -> SpecialElementHandler.handleOptgroupStartTag(treeBuilder, attributes)

            in setOf("rb", "rtc") -> {
                if (treeBuilder.hasElementInScope("ruby")) {
                    InBodyHelpers.generateImpliedEndTags(treeBuilder)
                    if (treeBuilder.currentNode.localName != "ruby" ||
                        treeBuilder.currentNode.namespace != Namespaces.HTML
                    ) {
                        // Parse error
                    }
                }
                treeBuilder.insertElement(tagName, attributes = attributes)
                continueInCurrentMode()
            }

            in setOf("rp", "rt") -> {
                if (treeBuilder.hasElementInScope("ruby")) {
                    InBodyHelpers.generateImpliedEndTags(treeBuilder, except = "rtc")
                    val currentNode = treeBuilder.currentNode
                    if (currentNode.namespace == Namespaces.HTML &&
                        currentNode.localName !in setOf("rtc", "ruby")
                    ) {
                        // Parse error
                    }
                }
                treeBuilder.insertElement(tagName, attributes = attributes)
                continueInCurrentMode()
            }

            "math" -> {
                treeBuilder.reconstructActiveFormattingElements()
                val adjustedAttributes = ForeignAttributeAdjustments.adjustMathMLAttributesFull(token.attributes)
                treeBuilder.insertElement("math", Namespaces.MATHML, adjustedAttributes)
                if (token.selfClosing) {
                    treeBuilder.popCurrentElement()
                }
                continueInCurrentMode()
            }

            "svg" -> {
                treeBuilder.reconstructActiveFormattingElements()
                val adjustedAttributes = ForeignAttributeAdjustments.adjustSVGAttributesFull(token.attributes)
                treeBuilder.insertElement("svg", Namespaces.SVG, adjustedAttributes)
                if (token.selfClosing) {
                    treeBuilder.popCurrentElement()
                }
                continueInCurrentMode()
            }

            in setOf(
                "caption", "col", "colgroup", "frame", "head", "tbody",
                "td", "tfoot", "th", "thead", "tr"
            ) -> continueInCurrentMode()

            else -> processAnyOtherStartTag(tagName, attributes, treeBuilder)
        }
    }

    private fun processEndTag(
        token: Token.EndTag,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        val tagName = token.name

        return when (tagName) {
            "body" -> EndTagHandler.handleBodyEndTag(treeBuilder)

            "html" -> EndTagHandler.handleHtmlEndTag(treeBuilder)

            in setOf(
                "address", "article", "aside", "blockquote", "button", "center",
                "details", "dialog", "dir", "div", "dl", "fieldset", "figcaption",
                "figure", "footer", "header", "hgroup", "listing", "main", "menu",
                "nav", "ol", "pre", "search", "section", "select", "summary", "ul"
            ) -> BlockElementHandler.handleBlockEndTag(treeBuilder, tagName)

            "form" -> FormElementHandler.handleFormEndTag(treeBuilder)

            "p" -> BlockElementHandler.handlePEndTag(treeBuilder)

            "li" -> ListElementHandler.handleLiEndTag(treeBuilder)

            in setOf("dd", "dt") -> ListElementHandler.handleDdDtEndTag(treeBuilder, tagName)

            in TreeConstructionConstants.HEADING_ELEMENTS -> HeadingHandler.handleHeadingEndTag(treeBuilder)

            in TreeConstructionConstants.FORMATTING_ELEMENTS -> {
                FormattingElementHandler.handleFormattingElementEndTag(treeBuilder, tagName)
            }

            in setOf("applet", "marquee", "object") -> {
                SpecialElementHandler.handleAppletEndTag(treeBuilder, tagName)
            }

            "br" -> MediaElementHandler.handleBrEndTag(treeBuilder)

            "template" -> InHead.process(token, treeBuilder)

            else -> EndTagHandler.handleAnyOtherEndTag(treeBuilder, tagName)
        }
    }

    private fun processHtmlStartTag(
        token: Token.StartTag,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        // Parse error

        if (treeBuilder.elementStack.any { it.localName == "template" && it.namespace == Namespaces.HTML }) {
            return continueInCurrentMode()
        }

        val htmlElement = treeBuilder.elementStack.firstOrNull()
        if (htmlElement != null && htmlElement.localName == "html") {
            token.attributes.forEach { (name, value) ->
                if (!htmlElement.hasAttribute(name)) {
                    htmlElement.setAttribute(name, value)
                }
            }
        }

        return continueInCurrentMode()
    }

    private fun processBodyStartTag(
        token: Token.StartTag,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        // Parse error

        val stack = treeBuilder.elementStack

        if (stack.size <= 1) return continueInCurrentMode()

        val secondElement = stack[1]
        if (secondElement.localName != "body" || secondElement.namespace != Namespaces.HTML) {
            return continueInCurrentMode()
        }

        if (stack.any { it.localName == "template" && it.namespace == Namespaces.HTML }) {
            return continueInCurrentMode()
        }

        treeBuilder.setFramesetNotOk()
        token.attributes.forEach { (name, value) ->
            if (!secondElement.hasAttribute(name)) {
                secondElement.setAttribute(name, value)
            }
        }
        return continueInCurrentMode()
    }

    private fun processFramesetStartTag(
        token: Token.StartTag,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        // Parse error

        val stack = treeBuilder.elementStack

        if (stack.size <= 1) return continueInCurrentMode()

        val secondElement = stack[1]
        if (secondElement.localName != "body" || secondElement.namespace != Namespaces.HTML) {
            return continueInCurrentMode()
        }

        if (!treeBuilder.isFramesetOk()) {
            return continueInCurrentMode()
        }
        secondElement.parent?.removeChild(secondElement)
        while (treeBuilder.elementStack.size > 1) {
            treeBuilder.popCurrentElement()
        }

        treeBuilder.insertElement("frameset", attributes = InBodyHelpers.toAttributeList(token.attributes))
        return switchTo(InFrameSet)
    }

    private fun processAnyOtherStartTag(
        tagName: String,
        attributes: List<Attribute>,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        treeBuilder.reconstructActiveFormattingElements()
        treeBuilder.insertElement(tagName, attributes = attributes)
        return continueInCurrentMode()
    }
}
