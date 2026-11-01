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

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.NodeType
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.tree.formContentAttributeValue
import com.theakram.helium.tree.isFormAssociated
import com.theakram.helium.tree.isListed
import com.theakram.helium.treebuilder.modes.InForeignContent
import com.theakram.helium.treebuilder.modes.Initial

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#tree-construction
 */
internal class TreeBuilder(
    private val onSwitchTokenizerState: (TokenizerStateType) -> Unit,
    /** Spec: https://html.spec.whatwg.org/multipage/parsing.html#scripting-mode */
    internal val scriptingMode: ScriptingMode = ScriptingMode.NORMAL,
    // Called with the error's code, or null for a parse error the spec gives no dedicated code
    private val onParseError: (code: String?) -> Unit = {}
) {
    private var insertionMode: InsertionMode = Initial
    var document: TreeNode = TreeNode.createDocument()
        private set

    private val stack = StackOfOpenElements()
    private val scopeChecker = ScopeChecker(stack)
    private val fosterParenting = FosterParenting()
    internal val activeFormattingElements = ActiveFormattingElements()
    private val nodeInsertion = NodeInsertion(
        stack = stack,
        fosterParenting = fosterParenting,
        getCurrentNode = { currentNode }
    )
    private val formattingElementReconstructor = FormattingElementReconstructor(
        stack = stack,
        activeFormattingElements = activeFormattingElements,
        treeBuilder = this
    )
    private val adoptionAgency = AdoptionAgencyAlgorithm(
        treeBuilder = this,
        stack = stack,
        scopeChecker = scopeChecker,
        activeFormattingElements = activeFormattingElements,
    )
    private val insertionModeResetter = InsertionModeResetter(
        stack = stack,
        getContextElement = { contextElement },
        getHeadElement = { headElement },
        getCurrentTemplateInsertionMode = { currentTemplateInsertionMode() }
    )

    /**
     * The element whose contents are being parsed in the fragment case, or null when parsing a document.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#concept-frag-parse-context
     */
    internal var contextElement: TreeNode? = null
    internal val isFragmentCase: Boolean get() = contextElement != null

    /**
     * "The start tag token of context": the start tag token the fragment parsing algorithm creates, whose name is
     * the context element's local name and whose attributes are its attributes.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#html-fragment-parsing-algorithm
     */
    internal var contextElementStartTag: Token.StartTag? = null

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#html-integration-point
     */
    internal fun isHTMLIntegrationPoint(node: TreeNode): Boolean =
        IntegrationPoints.isHTMLIntegrationPoint(node, contextElementStartTag.takeIf { node === contextElement })

    /**
     * Initially false, and none of Helium's parsing entry points allow declarative shadow roots.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#allow-declarative-shadow-roots
     */
    internal val allowDeclarativeShadowRoots: Boolean = false

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parsing-template-contents
     */
    internal val isParsingTemplateContents: Boolean
        get() = stack.any { it.namespace == Namespaces.HTML && it.localName == "template" } ||
                (contextElement?.namespace == Namespaces.HTML && contextElement?.localName == "template")

    internal var headElement: TreeNode? = null
    internal var formElement: TreeNode? = null
    private var originalInsertionMode: InsertionMode? = null
    private val templateInsertionModes = mutableListOf<InsertionMode>()
    private var framesetOk: Boolean = true
    private var ignoreNextLineFeed: Boolean = false

    internal val pendingTableCharacterTokens = mutableListOf<Token.Character>()
    internal val currentNode: TreeNode get() = stack.currentNode ?: document

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#adjusted-current-node
     */
    internal val adjustedCurrentNode: TreeNode
        get() = contextElement?.takeIf { stack.size == 1 } ?: currentNode

    internal fun hasForeignAdjustedCurrentNode(): Boolean =
        stack.isNotEmpty() && adjustedCurrentNode.namespace != Namespaces.HTML
    /**
     * The stack of open elements, from the topmost element to the current node. It is a view, not a copy: a loop over
     * it that pushes or pops must stop iterating right after.
     */
    internal val elementStack: List<TreeNode> get() = stack.asList()

    /** The number of elements on the stack of open elements. */
    internal val openElementCount: Int get() = stack.size
    internal val currentMode: InsertionMode get() = insertionMode

    // Whether tree construction has acknowledged the self-closing flag of the start tag token being processed
    private var selfClosingFlagAcknowledged = false

    /**
     * Reports a parse error of tree construction that the spec gives no dedicated code.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#parse-errors
     */
    internal fun parseError() = onParseError(null)

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#acknowledge-self-closing-flag
     */
    internal fun acknowledgeSelfClosingFlag() {
        selfClosingFlagAcknowledged = true
    }

    internal fun process(token: Token) {
        if (ignoreNextLineFeed) {
            ignoreNextLineFeed = false
            if (token is Token.Character && token.codePoint == '\n'.code) return
        }

        selfClosingFlagAcknowledged = false
        dispatch(token)

        // "When a start tag token is emitted with its self-closing flag set, if the flag is not acknowledged when it
        // is processed by the tree construction stage, that is a non-void-html-element-start-tag-with-trailing-solidus
        // parse error."
        if (token is Token.StartTag && token.selfClosing && !selfClosingFlagAcknowledged) {
            onParseError("non-void-html-element-start-tag-with-trailing-solidus")
        }
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#tree-construction-dispatcher
     */
    private fun dispatch(token: Token) {
        while (true) {
            val transition = if (isProcessedAsHTMLContent(token)) {
                insertionMode.process(token, this)
            } else {
                InForeignContent.process(token, this)
            }

            when (transition) {
                is InsertionModeTransition.Continue -> break
                is InsertionModeTransition.SwitchMode -> {
                    insertionMode = transition.newMode
                    break
                }

                is InsertionModeTransition.Reprocess -> {
                    insertionMode = transition.newMode
                }

                is InsertionModeTransition.StopParsing -> {
                    // The end (https://html.spec.whatwg.org/multipage/parsing.html#stop-parsing), step 4: "Pop all the
                    // nodes off the stack of open elements." Its other steps are about speculative parsing, the
                    // insertion point, document readiness, scripts and events, which Helium doesn't have.
                    while (stack.size > 0) {
                        popCurrentElement()
                    }
                    nodeInsertion.finishAppendingText()
                    break
                }
            }
        }
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#tree-construction-dispatcher
     */
    private fun isProcessedAsHTMLContent(token: Token): Boolean {
        if (stack.isEmpty()) return true
        val node = adjustedCurrentNode
        val isMathMLTextIntegrationPoint = IntegrationPoints.isMathMLTextIntegrationPoint(node)
        val isHTMLIntegrationPoint = isHTMLIntegrationPoint(node)
        return node.namespace == Namespaces.HTML ||
                (isMathMLTextIntegrationPoint && token is Token.StartTag &&
                        token.name != "mglyph" && token.name != "malignmark") ||
                (isMathMLTextIntegrationPoint && token is Token.Character) ||
                (node.namespace == Namespaces.MATHML && node.localName == "annotation-xml" &&
                        token is Token.StartTag && token.name == "svg") ||
                (isHTMLIntegrationPoint && token is Token.StartTag) ||
                (isHTMLIntegrationPoint && token is Token.Character) ||
                token is Token.EOF
    }

    /**
     * Inserts a foreign element for a token with [tagName] and [attributes] (already adjusted), in [namespace]: the
     * HTML namespace for "insert an HTML element".
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#insert-a-foreign-element
     */
    internal fun insertElement(
        tagName: String,
        namespace: String? = Namespaces.HTML,
        attributes: List<Attribute> = emptyList()
    ): TreeNode {
        // Step 1:
        val element = createElementForToken(
            tagName,
            namespace,
            attributes,
            intendedParent = appropriatePlaceForInsertingNode().targetParent
        )
        // Step 2:
        insertElementAtAdjustedInsertionLocation(element)
        // Step 3:
        stack.push(element)
        // Step 4:
        return element
    }

    /**
     * Without speculative parsing, a sanitizer, custom elements or scripts, only the element's creation, its
     * attributes, the xmlns parse errors and the form owner association apply.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#create-an-element-for-the-token
     */
    internal fun createElementForToken(
        tagName: String,
        namespace: String?,
        attributes: List<Attribute>,
        intendedParent: TreeNode
    ): TreeNode {
        val element = TreeNode.createElement(localName = tagName, namespace = namespace, attributes = attributes)

        // "If element has an xmlns attribute in the XMLNS namespace whose value is not exactly the same as the
        // element's namespace, that is a parse error. Similarly, if element has an xmlns:xlink attribute in the XMLNS
        // namespace whose value is not the XLink namespace, that is a parse error."
        for (attribute in element.attributes) {
            if (attribute.namespace != Namespaces.XMLNS) continue
            if ((attribute.prefix == null && attribute.localName == "xmlns" && attribute.value != namespace) ||
                (attribute.prefix == "xmlns" && attribute.localName == "xlink" && attribute.value != Namespaces.XLINK)
            ) {
                parseError()
            }
        }

        // Resettable elements' reset algorithm initializes their value and checkedness, which Helium doesn't model

        // "If element is a form-associated element and not a form-associated custom element, the form element
        // pointer is not null, the parser is not parsing template contents, the parser's fragment context element is
        // null, element is either not listed or doesn't have a form attribute, the intendedParent is in the same tree
        // as the element pointed to by the form element pointer, and the parser's insertion target redirection
        // map[the element pointed to by the form element pointer] does not exist, then associate element with the
        // form element pointed to by the form element pointer and set element's parser inserted flag."
        val form = formElement
        if (form != null &&
            element.isFormAssociated &&
            !isParsingTemplateContents &&
            contextElement == null &&
            (!element.isListed || element.formContentAttributeValue == null) &&
            intendedParent.root === form.root &&
            form !in insertionTargetRedirectionMap
        ) {
            element.formOwner = form
            element.parserInserted = true
        }

        return element
    }

    internal fun insertElementAtAdjustedInsertionLocation(element: TreeNode, overrideLocation: InsertionLocation? = null) {
        nodeInsertion.insertElementAtAdjustedInsertionLocation(element, overrideLocation)
    }

    internal fun appropriatePlaceForInsertingNode(overrideTarget: TreeNode? = null): InsertionLocation =
        nodeInsertion.appropriatePlaceForInsertingNode(overrideTarget)

    internal fun adjustedInsertionLocation(insertionLocation: InsertionLocation? = null): InsertionLocation =
        nodeInsertion.adjustedInsertionLocation(insertionLocation)

    internal val insertionTargetRedirectionMap: MutableMap<TreeNode, InsertionLocation>
        get() = nodeInsertion.insertionTargetRedirectionMap

    internal fun insertComment(data: String, insertionLocation: InsertionLocation? = null) {
        nodeInsertion.insertComment(data, insertionLocation)
    }

    internal fun insertProcessingInstruction(target: String, data: String, insertionLocation: InsertionLocation? = null) {
        nodeInsertion.insertProcessingInstruction(target, data, insertionLocation)
    }

    /**
     * Appends a doctype to the Document unless it already has a doctype or element child, as the
     * DOCTYPE token's entry in the "initial" insertion mode says.
     */
    internal fun insertDoctype(name: String, publicId: String, systemId: String) {
        if (document.children.none { it.type == NodeType.DOCUMENT_TYPE || it.type == NodeType.ELEMENT }) {
            document.appendChild(TreeNode.createDocumentType(name, publicId, systemId))
        }
    }

    internal fun popCurrentElement(): TreeNode? = stack.pop()

    internal fun popUntil(tagName: String): Unit = stack.popUntil(tagName)

    internal fun popUntilElement(element: TreeNode): Unit = stack.popUntilElement(element)

    internal fun pushElement(element: TreeNode) = stack.push(element)

    internal fun removeElement(element: TreeNode): Boolean = stack.remove(element)

    internal fun hasElementInScope(tagName: String): Boolean =
        scopeChecker.hasElementInScope(tagName)

    internal fun hasElementInScope(element: TreeNode): Boolean =
        scopeChecker.isElementInScope(element)

    internal fun hasElementInListItemScope(tagName: String): Boolean =
        scopeChecker.hasElementInListItemScope(tagName)

    internal fun hasElementInButtonScope(tagName: String): Boolean =
        scopeChecker.hasElementInButtonScope(tagName)

    internal fun hasElementInTableScope(tagName: String): Boolean =
        scopeChecker.hasElementInTableScope(tagName)

    internal fun hasElementInScopeMatching(matches: (TreeNode) -> Boolean): Boolean =
        scopeChecker.hasElementInScopeMatching(matches)

    internal fun enableFosterParenting() = fosterParenting.enable()

    internal fun disableFosterParenting() = fosterParenting.disable()

    internal fun runAdoptionAgency(tagName: String) = adoptionAgency.run(tagName)

    internal fun reconstructActiveFormattingElements() {
        formattingElementReconstructor.reconstruct()
    }

    internal fun setFramesetNotOk() {
        framesetOk = false
    }

    internal fun setFramesetOk() {
        framesetOk = true
    }

    internal fun isFramesetOk(): Boolean = framesetOk

    /**
     * Drops the next token if it is a line feed, as pre, listing and textarea require.
     * Newlines at the start of those elements are ignored as an authoring convenience.
     */
    internal fun ignoreNextLineFeed() {
        ignoreNextLineFeed = true
    }

    internal fun saveOriginalInsertionMode() {
        originalInsertionMode = insertionMode
    }

    internal fun getOriginalInsertionMode(): InsertionMode? = originalInsertionMode

    internal fun pushTemplateInsertionMode(mode: InsertionMode) {
        templateInsertionModes.add(mode)
    }

    internal fun popTemplateInsertionMode(): InsertionMode? =
        templateInsertionModes.removeLastOrNull()

    internal fun currentTemplateInsertionMode(): InsertionMode? =
        templateInsertionModes.lastOrNull()

    internal fun switchTokenizerState(stateType: TokenizerStateType) {
        onSwitchTokenizerState(stateType)
    }

    /**
     * The insertion mode that "reset the insertion mode appropriately" switches to. An insertion mode that resets it
     * returns [switchTo] or [reprocessIn] with this mode, so that every change of insertion mode while processing a
     * token goes through the transition that the dispatcher applies.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#reset-the-insertion-mode-appropriately
     */
    internal fun appropriateInsertionMode(): InsertionMode = insertionModeResetter.reset()

    /**
     * "Reset the insertion mode appropriately" outside of processing a token, as the fragment parsing algorithm does
     * before it starts tokenizing. Insertion modes return a transition to [appropriateInsertionMode] instead.
     */
    internal fun resetInsertionModeAppropriately() {
        insertionMode = appropriateInsertionMode()
    }

    internal fun createImplicitElement(tagName: String): TreeNode =
        insertElement(tagName = tagName, namespace = Namespaces.HTML, attributes = emptyList())

    internal fun insertCharacter(codePoint: Int) {
        nodeInsertion.insertCharacter(codePoint)
    }

    internal fun reset() {
        insertionMode = Initial
        stack.clear()
        // A new document per parse, so trees returned by earlier parses stay intact
        document = TreeNode.createDocument()
        contextElement = null
        contextElementStartTag = null
        headElement = null
        formElement = null
        activeFormattingElements.clear()
        originalInsertionMode = null
        templateInsertionModes.clear()
        framesetOk = true
        ignoreNextLineFeed = false
        fosterParenting.disable()
        nodeInsertion.insertionTargetRedirectionMap.clear()
        nodeInsertion.finishAppendingText()
        pendingTableCharacterTokens.clear()
    }

    internal fun toTreeString(): String {
        val result = StringBuilder()
        document.children.forEachIndexed { index, child ->
            result.append(child.toTreeString())
            if (index < document.children.size - 1) {
                result.append("\n")
            }
        }
        return result.toString()
    }
}
