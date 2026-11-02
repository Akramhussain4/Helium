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
package com.theakram.helium.tree

/**
 * Spec: https://html.spec.whatwg.org/multipage/forms.html#form-associated-element
 */
private val FORM_ASSOCIATED_ELEMENTS =
    setOf("button", "fieldset", "input", "object", "output", "select", "textarea", "img")

/**
 * Spec: https://html.spec.whatwg.org/multipage/forms.html#category-listed
 */
private val LISTED_ELEMENTS = setOf("button", "fieldset", "input", "object", "output", "select", "textarea")

internal val TreeNode.isFormAssociated: Boolean
    get() = type == NodeType.ELEMENT && namespace == Namespaces.HTML && localName in FORM_ASSOCIATED_ELEMENTS

internal val TreeNode.isListed: Boolean
    get() = type == NodeType.ELEMENT && namespace == Namespaces.HTML && localName in LISTED_ELEMENTS

/** The value of the form content attribute, or null if it is not present. */
internal val TreeNode.formContentAttributeValue: String?
    get() = attributes.firstOrNull { it.namespace == null && it.localName == "form" }?.value

private fun TreeNode.isHtmlForm(): Boolean =
    type == NodeType.ELEMENT && namespace == Namespaces.HTML && localName == "form"

/**
 * The element's ID: the value of its id attribute, if it has one that is not empty.
 *
 * Spec: https://dom.spec.whatwg.org/#concept-id
 */
private val TreeNode.id: String?
    get() = if (type != NodeType.ELEMENT) null else
        attributes.firstOrNull { it.namespace == null && it.localName == "id" }?.value?.ifEmpty { null }

/**
 * Compares [a] and [b] by tree order: negative if [a] comes first. They must be in the same tree, and if [within] is
 * given, both inclusive descendants of it, which spares going up from it to the root.
 *
 * Spec: https://dom.spec.whatwg.org/#concept-tree-order
 */
internal fun compareTreeOrder(a: TreeNode, b: TreeNode, within: TreeNode? = null): Int {
    if (a === b) return 0
    val aChain = generateSequence(a) { if (it === within) null else it.parent }.toList().asReversed()
    val bChain = generateSequence(b) { if (it === within) null else it.parent }.toList().asReversed()
    var depth = 0
    while (depth < aChain.size && depth < bChain.size && aChain[depth] === bChain[depth]) depth++
    // An ancestor comes before its descendants
    if (depth == aChain.size) return -1
    if (depth == bChain.size) return 1
    // Look for the two siblings from both ends of their parent's children at once, which tells which comes first in
    // time proportional to how far the nearer of them is from an end, rather than to where they are
    val aChild = aChain[depth]
    val bChild = bChain[depth]
    val siblings = aChain[depth - 1].children
    var front = 0
    var back = siblings.lastIndex
    while (front <= back) {
        val first = siblings[front++]
        if (first === aChild) return -1
        if (first === bChild) return 1
        val last = siblings[back--]
        if (last === aChild) return 1
        if (last === bChild) return -1
    }
    throw IllegalStateException("Nodes are not siblings of their common ancestor's children")
}

/** Calls [action] with this node and each of its descendants, in tree order, without recursing. */
private inline fun TreeNode.forEachInclusiveDescendant(action: (TreeNode) -> Unit) {
    val pending = ArrayDeque<TreeNode>()
    pending.addLast(this)
    while (pending.isNotEmpty()) {
        val node = pending.removeLast()
        action(node)
        for (index in node.children.indices.reversed()) pending.addLast(node.children[index])
    }
}

/**
 * Calls [action] with each form-associated element among this node and its descendants, in tree order, skipping the
 * subtrees that have never held one.
 */
private inline fun TreeNode.forEachFormAssociatedInclusiveDescendant(action: (TreeNode) -> Unit) {
    if (!mayContainFormAssociatedElement) return
    val pending = ArrayDeque<TreeNode>()
    pending.addLast(this)
    while (pending.isNotEmpty()) {
        val node = pending.removeLast()
        if (node.isFormAssociated) action(node)
        for (index in node.children.indices.reversed()) {
            val child = node.children[index]
            if (child.mayContainFormAssociatedElement) pending.addLast(child)
        }
    }
}

/**
 * A Document's index of what resetting form owners looks up: its listed elements with a form attribute, by the
 * attribute's value, and, once a form attribute needs one, its elements by ID. A Document has one only after a listed
 * element with a form attribute was connected to it, so other documents pay nothing for it.
 */
internal class FormOwnerIndex(private val document: TreeNode) {
    private val formAttributeElements = HashMap<String, LinkedHashSet<TreeNode>>()
    private var elementsById: HashMap<String, MutableList<TreeNode>>? = null

    // The first element in tree order with each ID looked up, until an element with that ID is added or removed
    private val firstElementById = HashMap<String, TreeNode?>()

    fun addFormAttributeElement(element: TreeNode, value: String) {
        formAttributeElements.getOrPut(value) { LinkedHashSet() }.add(element)
    }

    fun removeFormAttributeElement(element: TreeNode, value: String) {
        formAttributeElements[value]?.remove(element)
    }

    fun formAttributeElementsNaming(id: String): List<TreeNode> = formAttributeElements[id]?.toList().orEmpty()

    fun addId(element: TreeNode, id: String) {
        elementsById?.getOrPut(id) { mutableListOf() }?.add(element)
        firstElementById.remove(id)
    }

    fun removeId(element: TreeNode, id: String) {
        elementsById?.get(id)?.remove(element)
        firstElementById.remove(id)
    }

    /** "The first element in element's tree, in tree order, to have an ID that is identical to" [id]. */
    fun firstElementWithId(id: String): TreeNode? {
        val byId = elementsById ?: HashMap<String, MutableList<TreeNode>>().also { built ->
            document.forEachInclusiveDescendant { node ->
                node.id?.let { built.getOrPut(it) { mutableListOf() }.add(node) }
            }
            elementsById = built
        }
        if (id in firstElementById) return firstElementById[id]
        return byId[id]?.minWithOrNull(::compareTreeOrder).also { firstElementById[id] = it }
    }
}

private fun TreeNode.formOwnerIndex(): FormOwnerIndex =
    formOwnerIndex ?: FormOwnerIndex(this).also { formOwnerIndex = it }

/**
 * Spec: https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#reset-the-form-owner
 */
internal fun TreeNode.resetFormOwner() {
    // Step 1:
    parserInserted = false

    // Step 2:
    val nearestFormAncestor = generateSequence(parent) { it.parent }.firstOrNull { it.isHtmlForm() }
    val owner = formOwner
    if (owner != null && (!isListed || formContentAttributeValue == null) && owner === nearestFormAncestor) return

    // Step 3:
    formOwner = null

    // Step 4:
    val formAttribute = formContentAttributeValue
    val root = root
    if (isListed && formAttribute != null && root.type == NodeType.DOCUMENT) {
        val first = root.formOwnerIndex().firstElementWithId(formAttribute)
        if (first != null && first.isHtmlForm()) formOwner = first
    } else if (nearestFormAncestor != null) {
        // Step 5:
        formOwner = nearestFormAncestor
    }
}

/**
 * The form owner parts of inserting [inserted] into a tree: the form-associated elements' insertion steps, run for it
 * and each of its descendants ("If the form-associated element's parser inserted flag is set, then return. Reset the
 * form owner of the form-associated element."), and, when it is inserted into a Document, the reset of the listed
 * elements with a form attribute when "an element with an ID is inserted into" it.
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#form-owner
 */
internal fun formOwnerNodeInserted(inserted: TreeNode) {
    if (inserted.isFormAssociated) inserted.mayContainFormAssociatedElement = true
    if (inserted.mayContainFormAssociatedElement) {
        var ancestor = inserted.parent
        while (ancestor != null && !ancestor.mayContainFormAssociatedElement) {
            ancestor.mayContainFormAssociatedElement = true
            ancestor = ancestor.parent
        }
    }

    // Only form-associated elements and elements with an ID need anything, so a new element with neither, which is
    // what the parser mostly inserts, is done without walking up to the root
    if (!inserted.mayContainFormAssociatedElement && inserted.children.isEmpty() && inserted.id == null) return

    val root = inserted.root
    if (root.type == NodeType.DOCUMENT) {
        inserted.forEachFormAssociatedInclusiveDescendant { element ->
            val value = element.formContentAttributeValue
            if (element.isListed && value != null) root.formOwnerIndex().addFormAttributeElement(element, value)
        }
        root.formOwnerIndex?.let { index ->
            val insertedIds = mutableSetOf<String>()
            inserted.forEachInclusiveDescendant { node ->
                node.id?.let {
                    index.addId(node, it)
                    insertedIds += it
                }
            }
            for (id in insertedIds) index.formAttributeElementsNaming(id).forEach { it.resetFormOwner() }
        }
    }

    inserted.forEachFormAssociatedInclusiveDescendant { if (!it.parserInserted) it.resetFormOwner() }
}

/**
 * The form owner parts of removing [removed] from a tree whose root was [oldRoot]: the form-associated elements'
 * removing steps, run for it and each of its descendants (an element whose form owner is no longer in its tree resets
 * it), and, when it is removed from a Document, the reset of the listed elements with a form attribute when "an
 * element with an ID is ... removed from" it.
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#form-owner
 */
internal fun formOwnerNodeRemoved(removed: TreeNode, oldRoot: TreeNode) {
    if (oldRoot.type == NodeType.DOCUMENT) {
        oldRoot.formOwnerIndex?.let { index ->
            val removedFormAttributeElements = mutableListOf<TreeNode>()
            removed.forEachFormAssociatedInclusiveDescendant { element ->
                element.formContentAttributeValue?.let {
                    index.removeFormAttributeElement(element, it)
                    if (element.isListed) removedFormAttributeElements += element
                }
            }
            val removedIds = mutableSetOf<String>()
            removed.forEachInclusiveDescendant { node ->
                node.id?.let {
                    index.removeId(node, it)
                    removedIds += it
                }
            }
            // The listed elements with a form attribute that were in the Document when the element with an ID left
            // it: those still in it, and those that left with it
            if (removedIds.isNotEmpty()) {
                for (id in removedIds) index.formAttributeElementsNaming(id).forEach { it.resetFormOwner() }
                removedFormAttributeElements.forEach { it.resetFormOwner() }
            }
        }
    }

    removed.forEachFormAssociatedInclusiveDescendant { element ->
        val owner = element.formOwner
        if (owner != null && element.root !== owner.root) element.resetFormOwner()
    }
}

/**
 * The form owner parts of an attribute change: "When a listed form-associated element's form attribute is set,
 * changed, or removed", and when "the ID of any of the elements in the tree changes", the affected elements reset
 * their form owner. [oldValue] and [newValue] are null when the attribute is absent.
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#form-owner
 */
internal fun TreeNode.formOwnerAttributeChanged(
    namespace: String?,
    localName: String,
    oldValue: String?,
    newValue: String?
) {
    if (namespace != null) return
    val root = root
    val index = if (root.type == NodeType.DOCUMENT) root.formOwnerIndex else null
    when (localName) {
        "form" -> if (isListed) {
            if (root.type == NodeType.DOCUMENT) {
                oldValue?.let { root.formOwnerIndex?.removeFormAttributeElement(this, it) }
                newValue?.let { root.formOwnerIndex().addFormAttributeElement(this, it) }
            }
            resetFormOwner()
        }

        // An ID set to the value it has doesn't change
        "id" -> if (index != null && oldValue != newValue) {
            val oldId = oldValue?.ifEmpty { null }
            val newId = newValue?.ifEmpty { null }
            oldId?.let { index.removeId(this, it) }
            newId?.let { index.addId(this, it) }
            for (id in listOfNotNull(oldId, newId)) {
                index.formAttributeElementsNaming(id).forEach { it.resetFormOwner() }
            }
        }
    }
}
