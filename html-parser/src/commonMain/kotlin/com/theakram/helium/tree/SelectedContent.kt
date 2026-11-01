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
 * The first option in this select element's list of options whose selectedness is true, or null. Without a multiple
 * attribute, at most one option is selected after the selectedness setting algorithm runs, and every change to which
 * one runs it, so the option it left selected is that option without going through the list.
 */
internal fun TreeNode.firstSelectedOption(): TreeNode? {
    if (hasRunSelectednessSettingAlgorithm && !hasContentAttribute("multiple")) return selectedOptionAfterLastRun
    forEachInListOfOptions { if (it.selectedness) return it }
    return null
}

/**
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#update-a-select's-descendant-selectedcontent-elements
 */
internal fun TreeNode.updateDescendantSelectedcontents() {
    // Step 1:
    if (hasContentAttribute("multiple")) return

    // Step 2: the selectedcontent elements that registered with this select when their disabledness was last
    // recalculated, and are still enabled descendants of it. A node's ancestors change only when it or one of them is
    // inserted, which recalculates its disabledness, and an enabled one has no other select ancestor, so these are all
    // of them, without going through the select's descendants each time its selected option changes.
    val registered = registeredSelectedcontents ?: return
    registered.removeAll { it.selectedcontentDisabled || !isAncestorOf(it) }
    val descendantSelectedcontents =
        if (registered.size > 1) registered.sortedWith(::compareTreeOrder) else registered.toList()

    // Step 3:
    for (selectedcontent in descendantSelectedcontents) updateSelectedcontent(selectedcontent)
}

/**
 * Updates [selectedcontent] to reflect this select element's selected option.
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#update-a-selectedcontent
 */
internal fun TreeNode.updateSelectedcontent(selectedcontent: TreeNode) {
    // Step 1:
    if (selectedcontent.selectedcontentDisabled) return

    // Step 2:
    val option = firstSelectedOption()

    // Steps 3-4:
    if (option == null) clearSelectedcontent(selectedcontent) else cloneOptionIntoSelectedcontent(option, selectedcontent)
}

/**
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#clone-an-option-into-a-selectedcontent
 */
private fun cloneOptionIntoSelectedcontent(option: TreeNode, selectedcontent: TreeNode) {
    // Steps 1-2:
    val clones = option.children.map { it.cloneNode(subtree = true) }

    // Step 3:
    selectedcontent.replaceAllChildren(clones)
}

/**
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#clear-a-selectedcontent
 */
private fun clearSelectedcontent(selectedcontent: TreeNode) {
    selectedcontent.replaceAllChildren(emptyList())
}

/**
 * Removes this node's children and appends [nodes] in their place. Unlike the DOM's "replace all", which inserts a
 * document fragment's children all at once, each node is inserted on its own.
 *
 * Spec: https://dom.spec.whatwg.org/#concept-node-replace-all
 */
private fun TreeNode.replaceAllChildren(nodes: List<TreeNode>) {
    while (children.isNotEmpty()) removeChild(children.last())
    for (node in nodes) appendChild(node)
}

/**
 * Sets this selectedcontent element's disabled to whether it is in an option, in another selectedcontent element or in
 * more than one select element, and returns its nearest select ancestor.
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#recalculate-a-selectedcontent-element's-disabledness
 */
internal fun TreeNode.recalculateSelectedcontentDisabledness(): TreeNode? {
    // Steps 1-2:
    var nearestSelectAncestor: TreeNode? = null
    selectedcontentDisabled = false

    // Step 3:
    var ancestor = parent
    while (ancestor != null) {
        if (ancestor.isHtmlElement("select")) {
            if (nearestSelectAncestor != null) {
                selectedcontentDisabled = true
                break
            }
            nearestSelectAncestor = ancestor
        } else if (ancestor.isHtmlElement("option") || ancestor.isHtmlElement("selectedcontent")) {
            selectedcontentDisabled = true
            break
        }
        ancestor = ancestor.parent
    }

    if (!selectedcontentDisabled && nearestSelectAncestor != null) {
        val registered = nearestSelectAncestor.registeredSelectedcontents
            ?: mutableListOf<TreeNode>().also { nearestSelectAncestor.registeredSelectedcontents = it }
        if (registered.none { it === this }) registered += this
    }

    // Step 4:
    return nearestSelectAncestor
}


/**
 * The selectedcontent element's post-connection steps.
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#the-selectedcontent-element
 */
internal fun TreeNode.selectedcontentPostConnectionSteps() {
    // Step 1:
    val select = recalculateSelectedcontentDisabledness()

    // Step 2:
    if (selectedcontentDisabled || select == null || select.hasContentAttribute("multiple")) return

    // Step 3:
    select.updateSelectedcontent(this)
}

/**
 * Updates the selectedcontent elements of this option element's select if this option is selected. Besides being the
 * option element's post-connection steps, it runs when the parser pops the option off the stack of open elements, by
 * which time the parser has inserted the option's contents.
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#update-descendant-selectedcontent-elements-for-an-option
 */
internal fun TreeNode.updateDescendantSelectedcontentsForOption() {
    // Step 1:
    val select = cachedNearestAncestorSelect

    // Step 2:
    if (select == null || !selectedness) return

    // Step 3:
    select.updateDescendantSelectedcontents()
}
