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
import com.theakram.helium.treebuilder.modes.InForeignContent
import com.theakram.helium.treebuilder.modes.Initial

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#tree-construction
 */
internal class TreeBuilder(
    private val onSwitchTokenizerState: (TokenizerStateType) -> Unit,
    /** Spec: https://html.spec.whatwg.org/multipage/parsing.html#scripting-mode */
    internal val scriptingMode: ScriptingMode = ScriptingMode.NORMAL
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
    internal val elementStack: List<TreeNode> get() = stack.toList()
    internal val currentMode: InsertionMode get() = insertionMode

    internal fun process(token: Token) {
        if (ignoreNextLineFeed) {
            ignoreNextLineFeed = false
            if (token is Token.Character && token.data == '\n') return
        }

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
                    while (stack.size > 0) {
                        popCurrentElement()
                    }
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
        val isHTMLIntegrationPoint = IntegrationPoints.isHTMLIntegrationPoint(node)
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

    internal fun insertElement(
        tagName: String,
        namespace: String = Namespaces.HTML,
        attributes: List<Attribute> = emptyList()
    ): TreeNode {
        // Spec: https://html.spec.whatwg.org/multipage/parsing.html#insert-a-foreign-element
        val element = TreeNode.createElement(
            localName = tagName,
            namespace = namespace,
            attributes = attributes
        )

        insertElementAtAdjustedInsertionLocation(element)
        stack.push(element)
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

    internal fun hasElementInListItemScope(tagName: String): Boolean =
        scopeChecker.hasElementInListItemScope(tagName)

    internal fun hasElementInButtonScope(tagName: String): Boolean =
        scopeChecker.hasElementInButtonScope(tagName)

    internal fun hasElementInTableScope(tagName: String): Boolean =
        scopeChecker.hasElementInTableScope(tagName)

    internal fun hasElementInSelectScope(tagName: String): Boolean =
        scopeChecker.hasElementInSelectScope(tagName)

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

    internal fun resetInsertionModeAppropriately() {
        insertionMode = insertionModeResetter.reset()
    }

    internal fun createImplicitElement(tagName: String): TreeNode =
        insertElement(tagName = tagName, namespace = Namespaces.HTML, attributes = emptyList())

    internal fun insertCharacter(char: Char) {
        val data = char.toString()
        nodeInsertion.insertCharacter(data)
    }

    internal fun reset() {
        insertionMode = Initial
        stack.clear()
        // A new document per parse, so trees returned by earlier parses stay intact
        document = TreeNode.createDocument()
        contextElement = null
        headElement = null
        formElement = null
        activeFormattingElements.clear()
        originalInsertionMode = null
        templateInsertionModes.clear()
        framesetOk = true
        ignoreNextLineFeed = false
        fosterParenting.disable()
        nodeInsertion.insertionTargetRedirectionMap.clear()
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
