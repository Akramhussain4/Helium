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
import com.theakram.helium.tree.NodeType
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.treebuilder.ContentPatching
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.ScriptingMode
import com.theakram.helium.treebuilder.TokenizerStateType
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.modes.helpers.InBodyHelpers
import com.theakram.helium.treebuilder.modes.helpers.InHeadHelpers
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo
import com.theakram.helium.util.asciiLowercase
import com.theakram.helium.util.isAsciiWhitespace

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-main-inhead
 */
internal object InHead : InsertionMode {

    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character if token.codePoint.isAsciiWhitespace() -> {
                treeBuilder.insertCharacter(token.codePoint)
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
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            is Token.StartTag if token.name == "html" -> InBody.process(token, treeBuilder)

            is Token.StartTag if token.name in VOID_HEAD_ELEMENTS -> {
                val attributes = InHeadHelpers.toAttributeList(token.attributes)
                treeBuilder.insertElement(token.name, attributes = attributes)
                treeBuilder.popCurrentElement()
                treeBuilder.acknowledgeSelfClosingFlag()
                continueInCurrentMode()
            }

            is Token.StartTag if token.name == "meta" -> {
                val attributes = InHeadHelpers.toAttributeList(token.attributes)
                treeBuilder.insertElement(token.name, attributes = attributes)
                treeBuilder.popCurrentElement()
                treeBuilder.acknowledgeSelfClosingFlag()
                // TODO: Handle character encoding detection (if active speculative HTML parser is null)
                continueInCurrentMode()
            }

            is Token.StartTag if token.name == "title" -> {
                InHeadHelpers.followGenericRCDATAElementParsingAlgorithm(token, treeBuilder)
            }

            is Token.StartTag if (token.name == "noscript" && treeBuilder.scriptingMode != ScriptingMode.DISABLED) ||
                    token.name in RAW_TEXT_HEAD_ELEMENTS -> {
                InHeadHelpers.followGenericRawTextElementParsingAlgorithm(token, treeBuilder)
            }

            is Token.StartTag if token.name == "noscript" -> {
                // The scripting mode is Disabled
                treeBuilder.insertElement(token.name, attributes = InHeadHelpers.toAttributeList(token.attributes))
                switchTo(InHeadNoScript)
            }

            is Token.StartTag if token.name == "script" -> processScriptStartTag(token, treeBuilder)

            is Token.EndTag if token.name == "head" -> {
                treeBuilder.popCurrentElement()
                switchTo(AfterHead)
            }

            is Token.EndTag if token.name in ANYTHING_ELSE_END_TAGS -> {
                treeBuilder.popCurrentElement()
                reprocessIn(AfterHead)
            }

            is Token.StartTag if token.name == "template" -> processTemplateStartTag(token, treeBuilder)

            is Token.EndTag if token.name == "template" -> processTemplateEndTag(treeBuilder)

            is Token.StartTag if token.name == "head" -> {
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            is Token.EndTag -> {
                treeBuilder.parseError()
                continueInCurrentMode()
            }

            else -> {
                treeBuilder.popCurrentElement()
                reprocessIn(AfterHead)
            }
        }
    }

    private fun processScriptStartTag(token: Token.StartTag, treeBuilder: TreeBuilder): InsertionModeTransition {
        // Step 1:
        val scriptElement = TreeNode.createElement(
            localName = token.name,
            attributes = InHeadHelpers.toAttributeList(token.attributes)
        )
        // Step 2: N/A. Helium doesn't execute scripts, so script elements have no parser document or force async.
        // Step 3: N/A. Likewise, script elements have no already started to set when the scripting mode is Inert.
        // Step 4: N/A. Helium has no document.write() or document.writeln().
        // Step 5:
        treeBuilder.insertElementAtAdjustedInsertionLocation(scriptElement)
        // Step 6:
        treeBuilder.pushElement(scriptElement)
        // Step 7:
        treeBuilder.switchTokenizerState(TokenizerStateType.SCRIPT_DATA)
        // Step 8:
        treeBuilder.saveOriginalInsertionMode()
        // Step 9:
        return switchTo(Text)
    }

    private fun processTemplateStartTag(token: Token.StartTag, treeBuilder: TreeBuilder): InsertionModeTransition {
        // Step 1:
        val templateStartTag = token
        // Step 2:
        treeBuilder.activeFormattingElements.insertMarker()
        // Step 3:
        treeBuilder.setFramesetNotOk()
        // Step 4: The switch to "in template" takes effect when the dispatcher applies the returned transition. This is
        // equivalent: every path below returns this transition, and neither the steps below nor "after head" removing
        // the head element once it has processed this token using these rules read the insertion mode.
        val transition = switchTo(InTemplate)
        // Step 5:
        treeBuilder.pushTemplateInsertionMode(InTemplate)
        // Step 6:
        val declarativeShadowHostElement = treeBuilder.currentNode
        // Step 7:
        val hostIsRoot = treeBuilder.elementStack.firstOrNull() === declarativeShadowHostElement
        // Step 8:
        val template = TreeNode.createElement(
            localName = templateStartTag.name,
            attributes = InHeadHelpers.toAttributeList(templateStartTag.attributes)
        )
        // Step 9:
        val insertionLocation = treeBuilder.appropriatePlaceForInsertingNode()
        // Step 10:
        treeBuilder.pushElement(template)
        // Steps 11-12: Helium has no parser sanitizer configuration, so sanitizerAction stays "Keep"

        // Step 13 "Keep":
        when {
            // Step 13 "Keep", 1:
            template.getAttribute("shadowrootmode")?.asciiLowercase() in SHADOW_ROOT_MODE_KEYWORDS -> {
                // Step 13 "Keep", 1.1:
                if (!treeBuilder.allowDeclarativeShadowRoots ||
                    hostIsRoot ||
                    treeBuilder.insertionTargetRedirectionMap.containsKey(declarativeShadowHostElement)
                ) {
                    treeBuilder.insertElementAtAdjustedInsertionLocation(template, insertionLocation)
                    return transition
                }
                // Steps 13 "Keep", 1.2-1.9: These attach a declarative shadow root, which Helium's tree doesn't
                // support. None of Helium's parsing entry points allow declarative shadow roots, so step 1.1
                // always returns.
                error("Declarative shadow roots are never allowed, so step 1.1 always inserts the template")
            }

            // Step 13 "Keep", 2:
            template.hasAttribute("for") -> {
                // Step 13 "Keep", 2.1:
                var scope = treeBuilder.adjustedInsertionLocation(insertionLocation).targetParent
                // Step 13 "Keep", 2.2:
                if (isBodyElementOfItsNodeDocument(scope)) {
                    scope = checkNotNull(scope.parent) { "The body element of its node document has a parent" }
                }
                // Step 13 "Keep", 2.3:
                val success = ContentPatching.prepareContentPatching(scope, template)
                // Step 13 "Keep", 2.4:
                if (!success) {
                    treeBuilder.insertElementAtAdjustedInsertionLocation(template, insertionLocation)
                }
            }

            // Step 13 "Keep", 3:
            else -> treeBuilder.insertElementAtAdjustedInsertionLocation(template, insertionLocation)
        }
        return transition
    }

    private fun processTemplateEndTag(treeBuilder: TreeBuilder): InsertionModeTransition {
        val templates = treeBuilder.elementStack.filter { it.namespace == Namespaces.HTML && it.localName == "template" }
        if (templates.isEmpty()) {
            treeBuilder.parseError()
            return continueInCurrentMode()
        }

        // Step 1:
        InBodyHelpers.generateAllImpliedEndTagsThoroughly(treeBuilder)

        // Step 2:
        if (treeBuilder.currentNode.namespace != Namespaces.HTML || treeBuilder.currentNode.localName != "template") {
            treeBuilder.parseError()
        }

        // Step 3:
        val template = templates.last()

        // Step 4:
        if (template.insertionTarget != null) {
            // Steps 4.1-4.3:
            val start = checkNotNull(template.insertionStartMarker) { "A template with an insertion target has a start marker" }
            val end = template.insertionEndMarker
            // Step 4.4:
            start.parent?.removeChild(start)
            // Step 4.5:
            if (end != null) {
                end.parent?.removeChild(end)
            }
            // Steps 4.6-4.8:
            template.insertionTarget = null
            template.insertionStartMarker = null
            template.insertionEndMarker = null
        }

        // Step 5:
        treeBuilder.popUntil("template")
        // Step 6:
        treeBuilder.activeFormattingElements.clearToLastMarker()
        // Step 7:
        treeBuilder.popTemplateInsertionMode()
        // Step 8:
        treeBuilder.resetInsertionModeAppropriately()
        return switchTo(treeBuilder.currentMode)
    }

    /**
     * The first of the html element's children that is a body or frameset element, if [node] is that body element.
     *
     * Spec: https://html.spec.whatwg.org/multipage/dom.html#the-body-element-2
     */
    private fun isBodyElementOfItsNodeDocument(node: TreeNode): Boolean {
        if (node.namespace != Namespaces.HTML || node.localName != "body") return false
        val htmlElement = node.parent ?: return false
        if (htmlElement.namespace != Namespaces.HTML || htmlElement.localName != "html") return false
        val document = htmlElement.parent ?: return false
        if (document.type != NodeType.DOCUMENT || document.children.firstOrNull { it.type == NodeType.ELEMENT } !== htmlElement) {
            return false
        }
        return htmlElement.children.firstOrNull {
            it.namespace == Namespaces.HTML && (it.localName == "body" || it.localName == "frameset")
        } === node
    }

    private val VOID_HEAD_ELEMENTS = setOf("base", "basefont", "bgsound", "link")

    private val RAW_TEXT_HEAD_ELEMENTS = setOf("noframes", "style")

    private val ANYTHING_ELSE_END_TAGS = setOf("body", "html", "br")

    // The keywords of the shadowrootmode attribute. A missing or invalid value is in the None state.
    private val SHADOW_ROOT_MODE_KEYWORDS = setOf("open", "closed")
}
