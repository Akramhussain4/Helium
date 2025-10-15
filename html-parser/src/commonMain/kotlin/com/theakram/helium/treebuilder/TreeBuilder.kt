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

package com.theakram.helium.treebuilder

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.NodeType
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.treebuilder.modes.Initial
import com.theakram.helium.treebuilder.modes.BeforeHTML
import com.theakram.helium.treebuilder.modes.BeforeHead
import com.theakram.helium.treebuilder.modes.InBody

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#tree-construction
 */
public class TreeBuilder(
    private val onSwitchTokenizerState: (TokenizerStateType) -> Unit
) {
    private var insertionMode: InsertionMode = Initial
    public val document: TreeNode = TreeNode.createDocument()

    private val stack = StackOfOpenElements()
    private val scopeChecker = ScopeChecker(stack)
    private val fosterParenting = FosterParenting(stack)
    internal val activeFormattingElements = ActiveFormattingElements()
    private val insertionLocation = InsertionLocation(
        stack = stack,
        fosterParenting = fosterParenting,
        getCurrentNode = { currentNode },
        getDocument = { document }
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

    public var headElement: TreeNode? = null
    public var formElement: TreeNode? = null
    private var originalInsertionMode: InsertionMode? = null
    private val templateInsertionModes = mutableListOf<InsertionMode>()
    private var framesetOk: Boolean = true

    internal val pendingTableCharacterTokens = mutableListOf<Token.Character>()
    public val currentNode: TreeNode get() = stack.currentNode ?: document
    public val currentElement: TreeNode get() = currentNode
    public val elementStack: List<TreeNode> get() = stack.toList()
    internal val currentMode: InsertionMode get() = insertionMode

    internal fun process(token: Token) {
        var currentToken = token

        while (true) {
            val transition = insertionMode.process(currentToken, this)

            when (transition) {
                is InsertionModeTransition.Continue -> break
                is InsertionModeTransition.SwitchMode -> {
                    insertionMode = transition.newMode
                    break
                }

                is InsertionModeTransition.Reprocess -> {
                    insertionMode = transition.newMode
                }
            }
        }
    }

    public fun insertElement(
        tagName: String,
        namespace: String = Namespaces.HTML,
        attributes: List<Attribute> = emptyList()
    ): TreeNode {
        val element = TreeNode.createElement(
            localName = tagName,
            namespace = namespace,
            attributes = attributes
        )

        insertAtAppropriatePlace(element)
        stack.push(element)
        return element
    }

    public fun insertText(data: String) {
        if (fosterParenting.isEnabled) {
            fosterParenting.fosterParentText(data)
            return
        }

        val lastChild = currentNode.lastChild
        if (lastChild?.type == NodeType.TEXT) {
            lastChild.data = (lastChild.data ?: "") + data
        } else {
            currentNode.appendChild(TreeNode.createText(data))
        }
    }

    public fun insertComment(data: String) {
        currentNode.appendChild(TreeNode.createComment(data))
    }

    public fun insertDoctype(name: String, publicId: String? = null, systemId: String? = null) {
        document.appendChild(TreeNode.createDocumentType(name, publicId, systemId))
    }

    public fun popCurrentElement(): TreeNode? = stack.pop()

    public fun popUntil(tagName: String): Unit = stack.popUntil(tagName)

    internal fun pushElement(element: TreeNode) = stack.push(element)

    internal fun removeElement(element: TreeNode): Boolean = stack.remove(element)

    public fun hasElementInScope(tagName: String): Boolean =
        scopeChecker.hasElementInScope(tagName)

    public fun hasElementInListItemScope(tagName: String): Boolean =
        scopeChecker.hasElementInListItemScope(tagName)

    public fun hasElementInButtonScope(tagName: String): Boolean =
        scopeChecker.hasElementInButtonScope(tagName)

    public fun hasElementInTableScope(tagName: String): Boolean =
        scopeChecker.hasElementInTableScope(tagName)

    public fun hasElementInSelectScope(tagName: String): Boolean =
        scopeChecker.hasElementInSelectScope(tagName)

    internal fun enableFosterParenting() = fosterParenting.enable()

    internal fun disableFosterParenting() = fosterParenting.disable()

    internal fun runAdoptionAgency(tagName: String) = adoptionAgency.run(tagName)

    internal fun insertAtAppropriatePlace(node: TreeNode, overrideTarget: TreeNode? = null) {
        insertionLocation.insertAtAppropriatePlace(node, overrideTarget)
    }

    internal fun reconstructActiveFormattingElements() {
        formattingElementReconstructor.reconstruct()
    }

    internal fun setFramesetNotOk() {
        framesetOk = false
    }

    internal fun isFramesetOk(): Boolean = framesetOk

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

    internal fun createImplicitElement(tagName: String): TreeNode =
        insertElement(tagName = tagName, namespace = Namespaces.HTML, attributes = emptyList())

    internal fun insertCharacter(char: Char) {
        insertText(char.toString())
    }

    internal fun finish() {
        val htmlElement = document.findChildElement("html") ?: return

        if (htmlElement.findChildElement("head") == null) {
            if (currentMode != InBody) {
                when (currentMode) {
                    Initial, BeforeHTML -> {}
                    BeforeHead -> {
                        val head = TreeNode.createElement("head")
                        htmlElement.appendChild(head)
                        headElement = head
                    }

                    else -> {
                        if (headElement == null) {
                            val head = TreeNode.createElement("head")
                            htmlElement.insertBefore(head, htmlElement.firstChild)
                            headElement = head
                        }
                    }
                }
            }
        }

        if (htmlElement.findChildElement("body") == null) {
            htmlElement.appendChild(TreeNode.createElement("body"))
        }
    }

    public fun reset() {
        insertionMode = Initial
        stack.clear()
        document.children.clear()
        headElement = null
        formElement = null
        activeFormattingElements.clear()
        originalInsertionMode = null
        templateInsertionModes.clear()
        framesetOk = true
        fosterParenting.disable()
    }

    public fun toTreeString(): String {
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
