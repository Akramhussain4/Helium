/*
 * MIT License
 *
 * Copyright (c) 2023-2026 Mohammed Akram Hussain
 * Website: https://theakram.com
 */

package com.theakram.helium.treebuilder.modes.helpers

import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.TreeConstructionConstants

internal object InBodyHelpers {

    private val ELEMENTS_ALLOWED_AT_END_OF_BODY = setOf(
        "dd", "dt", "li", "optgroup", "option", "p", "rb", "rp", "rt", "rtc",
        "tbody", "td", "tfoot", "th", "thead", "tr", "body", "html"
    )

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#close-a-p-element
     */
    fun closePElement(treeBuilder: TreeBuilder) {
        // Step 1:
        generateImpliedEndTags(treeBuilder, except = "p")

        // Step 2:
        if (!isHtmlElement(treeBuilder.currentNode, "p")) {
            treeBuilder.parseError()
        }

        // Step 3:
        treeBuilder.popUntil("p")
    }

    fun isHtmlElement(node: TreeNode, tagName: String): Boolean =
        node.namespace == Namespaces.HTML && node.localName == tagName

    /**
     * Whether there is a node in the stack of open elements that is not either a dd element, a dt element, an li
     * element, an optgroup element, an option element, a p element, an rb element, an rp element, an rt element, an
     * rtc element, a tbody element, a td element, a tfoot element, a th element, a thead element, a tr element, the
     * body element, or the html element, which is a parse error at the end of the body.
     */
    fun hasNodeNotAllowedAtEndOfBody(treeBuilder: TreeBuilder): Boolean =
        treeBuilder.elementStack.any { node ->
            node.namespace != Namespaces.HTML || node.localName !in ELEMENTS_ALLOWED_AT_END_OF_BODY
        }

    /** Whether this is the fragment case with a select context element, where select and input are ignored. */
    fun isSelectContext(treeBuilder: TreeBuilder): Boolean {
        val context = treeBuilder.contextElement ?: return false
        return context.namespace == Namespaces.HTML && context.localName == "select"
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#generate-all-implied-end-tags-thoroughly
     */
    fun generateAllImpliedEndTagsThoroughly(treeBuilder: TreeBuilder) {
        while (treeBuilder.currentNode.namespace == Namespaces.HTML &&
            treeBuilder.currentNode.localName in TreeConstructionConstants.THOROUGH_IMPLIED_END_TAG_ELEMENTS
        ) {
            treeBuilder.popCurrentElement()
        }
    }

    /**
     * Generates implied end tags, except for HTML elements with the tag name [except], if given.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#generate-implied-end-tags
     */
    fun generateImpliedEndTags(treeBuilder: TreeBuilder, except: String? = null) {
        // The current node is the Document, which is not an HTML element, once the stack is empty
        while (treeBuilder.currentNode.namespace == Namespaces.HTML &&
            treeBuilder.currentNode.localName in TreeConstructionConstants.IMPLIED_END_TAG_ELEMENTS &&
            treeBuilder.currentNode.localName != except
        ) {
            treeBuilder.popCurrentElement()
        }
    }
}
