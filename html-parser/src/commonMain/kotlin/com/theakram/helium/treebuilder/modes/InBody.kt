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

    private val IN_HEAD_START_TAGS = setOf(
        "base", "basefont", "bgsound", "link", "meta", "noframes", "script", "style", "template", "title"
    )

    private val BLOCK_START_TAGS = setOf(
        "address", "article", "aside", "blockquote", "center", "details", "dialog", "dir", "div", "dl", "fieldset",
        "figcaption", "figure", "footer", "header", "hgroup", "main", "menu", "nav", "ol", "p", "search", "section",
        "summary", "ul"
    )

    private val PRE_LISTING = setOf("pre", "listing")

    private val DD_DT = setOf("dd", "dt")

    private val FORMATTING_START_TAGS = setOf(
        "b", "big", "code", "em", "font", "i", "s", "small", "strike", "strong", "tt", "u"
    )

    private val APPLET_MARQUEE_OBJECT = setOf("applet", "marquee", "object")

    private val AREA_BR_EMBED_IMG_KEYGEN_WBR = setOf("area", "br", "embed", "img", "keygen", "wbr")

    private val PARAM_SOURCE_TRACK = setOf("param", "source", "track")

    private val RB_RTC = setOf("rb", "rtc")

    private val RP_RT = setOf("rp", "rt")

    private val RTC_RUBY = setOf("rtc", "ruby")

    private val IGNORED_START_TAGS = setOf(
        "caption", "col", "colgroup", "frame", "head", "tbody", "td", "tfoot", "th", "thead", "tr"
    )

    private val BLOCK_END_TAGS = setOf(
        "address", "article", "aside", "blockquote", "button", "center", "details", "dialog", "dir", "div", "dl",
        "fieldset", "figcaption", "figure", "footer", "header", "hgroup", "listing", "main", "menu", "nav", "ol",
        "pre", "search", "section", "select", "summary", "ul"
    )

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character -> {
                when {
                    token.codePoint == 0x0000 -> {
                        treeBuilder.parseError()
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
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            is Token.StartTag -> processStartTag(token, treeBuilder)
            is Token.EndTag -> processEndTag(token, treeBuilder)

            is Token.EOF -> {
                if (treeBuilder.currentTemplateInsertionMode() != null) {
                    return InTemplate.process(token, treeBuilder)
                }

                // Step 1:
                if (InBodyHelpers.hasNodeNotAllowedAtEndOfBody(treeBuilder)) {
                    treeBuilder.parseError()
                }

                // Step 2:
                stopParsing()
            }
        }
    }

    private fun processStartTag(
        token: Token.StartTag,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        val tagName = token.name

        return when (tagName) {
            "html" -> processHtmlStartTag(token, treeBuilder)

            in IN_HEAD_START_TAGS -> InHead.process(token, treeBuilder)

            "body" -> processBodyStartTag(token, treeBuilder)

            "frameset" -> processFramesetStartTag(token, treeBuilder)

            in BLOCK_START_TAGS -> {
                BlockElementHandler.handleBlockStartTag(treeBuilder, tagName, token.htmlAttributes())
            }

            in TreeConstructionConstants.HEADING_ELEMENTS -> {
                HeadingHandler.handleHeadingStartTag(treeBuilder, tagName, token.htmlAttributes())
            }

            in PRE_LISTING -> {
                BlockElementHandler.handlePreListingStartTag(treeBuilder, tagName, token.htmlAttributes())
            }

            "form" -> FormElementHandler.handleFormStartTag(treeBuilder, token.htmlAttributes())

            "li" -> ListElementHandler.handleLiStartTag(treeBuilder, token.htmlAttributes())

            in DD_DT -> ListElementHandler.handleDdDtStartTag(treeBuilder, tagName, token.htmlAttributes())

            "plaintext" -> BlockElementHandler.handlePlaintextStartTag(treeBuilder, token.htmlAttributes())

            "button" -> SpecialElementHandler.handleButtonStartTag(treeBuilder, token.htmlAttributes())

            "a" -> FormattingElementHandler.handleAStartTag(treeBuilder, token.htmlAttributes())

            in FORMATTING_START_TAGS -> {
                FormattingElementHandler.handleFormattingElementStartTag(treeBuilder, tagName, token.htmlAttributes())
            }

            "nobr" -> FormattingElementHandler.handleNobrStartTag(treeBuilder, token.htmlAttributes())

            in APPLET_MARQUEE_OBJECT -> {
                SpecialElementHandler.handleAppletMarqueeObjectStartTag(treeBuilder, tagName, token.htmlAttributes())
            }

            "table" -> {
                if (treeBuilder.document.quirksMode != QuirksMode.QUIRKS &&
                    treeBuilder.hasElementInButtonScope("p")
                ) {
                    InBodyHelpers.closePElement(treeBuilder)
                }
                treeBuilder.insertElement("table", attributes = token.htmlAttributes())
                treeBuilder.setFramesetNotOk()
                switchTo(InTable)
            }

            in AREA_BR_EMBED_IMG_KEYGEN_WBR -> {
                MediaElementHandler.handleAreaBrEmbedImgKeygenWbrStartTag(treeBuilder, tagName, token.htmlAttributes())
            }

            "input" -> MediaElementHandler.handleInputStartTag(treeBuilder, token.htmlAttributes())

            in PARAM_SOURCE_TRACK -> {
                MediaElementHandler.handleParamSourceTrackStartTag(treeBuilder, tagName, token.htmlAttributes())
            }

            "hr" -> BlockElementHandler.handleHrStartTag(treeBuilder, token.htmlAttributes())

            "image" -> {
                treeBuilder.parseError()
                // "Change the token's tag name to "img" and reprocess it", in the current insertion mode, which is not
                // "in body" when a table mode processes the token using the "in body" rules
                treeBuilder.currentMode.process(token.copy(name = "img"), treeBuilder)
            }

            "textarea" -> SpecialElementHandler.handleTextareaStartTag(treeBuilder, token.htmlAttributes())

            "xmp" -> SpecialElementHandler.handleXmpStartTag(treeBuilder, token)

            "iframe" -> SpecialElementHandler.handleIframeStartTag(treeBuilder, token)

            "noembed" -> {
                SpecialElementHandler.handleNoembedNoscriptStartTag(treeBuilder, token)
            }

            // With scripting disabled, noscript is parsed like any other element
            "noscript" if treeBuilder.scriptingMode != ScriptingMode.DISABLED -> {
                SpecialElementHandler.handleNoembedNoscriptStartTag(treeBuilder, token)
            }

            "select" -> SpecialElementHandler.handleSelectStartTag(treeBuilder, token.htmlAttributes())

            "option" -> SpecialElementHandler.handleOptionStartTag(treeBuilder, token.htmlAttributes())

            "optgroup" -> SpecialElementHandler.handleOptgroupStartTag(treeBuilder, token.htmlAttributes())

            in RB_RTC -> {
                if (treeBuilder.hasElementInScope("ruby")) {
                    InBodyHelpers.generateImpliedEndTags(treeBuilder)
                    if (!InBodyHelpers.isHtmlElement(treeBuilder.currentNode, "ruby")) {
                        treeBuilder.parseError()
                    }
                }
                treeBuilder.insertElement(tagName, attributes = token.htmlAttributes())
                continueInCurrentMode()
            }

            in RP_RT -> {
                if (treeBuilder.hasElementInScope("ruby")) {
                    InBodyHelpers.generateImpliedEndTags(treeBuilder, except = "rtc")
                    val currentNode = treeBuilder.currentNode
                    if (currentNode.namespace != Namespaces.HTML || currentNode.localName !in RTC_RUBY) {
                        treeBuilder.parseError()
                    }
                }
                treeBuilder.insertElement(tagName, attributes = token.htmlAttributes())
                continueInCurrentMode()
            }

            "math" -> {
                treeBuilder.reconstructActiveFormattingElements()
                val adjustedAttributes = ForeignAttributeAdjustments.adjustMathMLAttributesFull(token.attributes)
                treeBuilder.insertElement("math", Namespaces.MATHML, adjustedAttributes)
                if (token.selfClosing) {
                    treeBuilder.popCurrentElement()
                    treeBuilder.acknowledgeSelfClosingFlag()
                }
                continueInCurrentMode()
            }

            "svg" -> {
                treeBuilder.reconstructActiveFormattingElements()
                val adjustedAttributes = ForeignAttributeAdjustments.adjustSVGAttributesFull(token.attributes)
                treeBuilder.insertElement("svg", Namespaces.SVG, adjustedAttributes)
                if (token.selfClosing) {
                    treeBuilder.popCurrentElement()
                    treeBuilder.acknowledgeSelfClosingFlag()
                }
                continueInCurrentMode()
            }

            in IGNORED_START_TAGS -> {
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            else -> {
                treeBuilder.reconstructActiveFormattingElements()
                treeBuilder.insertElement(tagName, attributes = token.htmlAttributes())
                continueInCurrentMode()
            }
        }
    }

    private fun processEndTag(
        token: Token.EndTag,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        val tagName = token.name

        return when (tagName) {
            "template" -> InHead.process(token, treeBuilder)

            "body" -> EndTagHandler.handleBodyEndTag(treeBuilder)

            "html" -> EndTagHandler.handleHtmlEndTag(treeBuilder)

            in BLOCK_END_TAGS -> BlockElementHandler.handleBlockEndTag(treeBuilder, tagName)

            "form" -> FormElementHandler.handleFormEndTag(treeBuilder)

            "p" -> BlockElementHandler.handlePEndTag(treeBuilder)

            "li" -> ListElementHandler.handleLiEndTag(treeBuilder)

            in DD_DT -> ListElementHandler.handleDdDtEndTag(treeBuilder, tagName)

            in TreeConstructionConstants.HEADING_ELEMENTS -> HeadingHandler.handleHeadingEndTag(treeBuilder, tagName)

            in TreeConstructionConstants.FORMATTING_ELEMENTS -> {
                FormattingElementHandler.handleFormattingElementEndTag(treeBuilder, tagName)
            }

            in APPLET_MARQUEE_OBJECT -> SpecialElementHandler.handleAppletMarqueeObjectEndTag(treeBuilder, tagName)

            "br" -> MediaElementHandler.handleBrEndTag(treeBuilder)

            else -> EndTagHandler.handleAnyOtherEndTag(treeBuilder, tagName)
        }
    }

    private fun processHtmlStartTag(
        token: Token.StartTag,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        treeBuilder.parseError()

        val stack = treeBuilder.elementStack
        if (stack.any { InBodyHelpers.isHtmlElement(it, "template") }) {
            return continueInCurrentMode()
        }

        val htmlElement = stack.first()
        // "Check to see if the attribute is already present on" it; "if it is not, add the attribute and its
        // corresponding value". Token attributes have no namespace, and their names are lowercase already.
        token.attributes.forEach { (name, value) ->
            if (htmlElement.attributes.none { it.namespace == null && it.localName == name }) {
                htmlElement.attributes.add(Attribute(localName = name, value = value))
            }
        }
        return continueInCurrentMode()
    }

    private fun processBodyStartTag(
        token: Token.StartTag,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        treeBuilder.parseError()

        // The fragment case, or there is a template element on the stack
        val stack = treeBuilder.elementStack
        if (stack.size == 1 ||
            !InBodyHelpers.isHtmlElement(stack[1], "body") ||
            stack.any { InBodyHelpers.isHtmlElement(it, "template") }
        ) {
            return continueInCurrentMode()
        }

        treeBuilder.setFramesetNotOk()
        val bodyElement = stack[1]
        // "Check to see if the attribute is already present on" it; "if it is not, add the attribute and its
        // corresponding value". Token attributes have no namespace, and their names are lowercase already.
        token.attributes.forEach { (name, value) ->
            if (bodyElement.attributes.none { it.namespace == null && it.localName == name }) {
                bodyElement.attributes.add(Attribute(localName = name, value = value))
            }
        }
        return continueInCurrentMode()
    }

    private fun processFramesetStartTag(
        token: Token.StartTag,
        treeBuilder: TreeBuilder
    ): InsertionModeTransition {
        treeBuilder.parseError()

        // The fragment case, or there is a template element on the stack
        val stack = treeBuilder.elementStack
        if (stack.size == 1 || !InBodyHelpers.isHtmlElement(stack[1], "body")) {
            return continueInCurrentMode()
        }

        if (!treeBuilder.isFramesetOk()) {
            return continueInCurrentMode()
        }

        // Step 1:
        val secondElement = stack[1]
        secondElement.parent?.removeChild(secondElement)

        // Step 2:
        val htmlElement = stack.first()
        while (treeBuilder.currentNode !== htmlElement) {
            treeBuilder.popCurrentElement()
        }

        // Step 3:
        treeBuilder.insertElement("frameset", attributes = token.htmlAttributes())

        // Step 4:
        return switchTo(InFrameSet)
    }

    private fun Token.StartTag.htmlAttributes(): List<Attribute> = InHeadHelpers.toAttributeList(attributes)
}
