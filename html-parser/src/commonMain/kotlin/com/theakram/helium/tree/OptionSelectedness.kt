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

import com.theakram.helium.util.parseNonNegativeInteger

internal fun TreeNode.isHtmlElement(localName: String): Boolean =
    type == NodeType.ELEMENT && namespace == Namespaces.HTML && this.localName == localName

private fun TreeNode.contentAttributeValue(localName: String): String? =
    attributes.firstOrNull { it.namespace == null && it.localName == localName }?.value

private fun TreeNode.hasContentAttribute(localName: String): Boolean =
    attributes.any { it.namespace == null && it.localName == localName }

/** Whether this is an HTML element whose descendants are left out of a select's list of options. */
private fun TreeNode.endsListOfOptionsSearch(): Boolean =
    type == NodeType.ELEMENT && namespace == Namespaces.HTML &&
        (localName == "select" || localName == "hr" || localName == "option" || localName == "datalist")

/**
 * Calls [action] with each option element in this select element's list of options, in tree order.
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#concept-select-option-list
 */
internal inline fun TreeNode.forEachInListOfOptions(action: (TreeNode) -> Unit) {
    // Each pending node with whether it is inside an optgroup, whose optgroup descendants' descendants are skipped
    val pending = ArrayDeque<TreeNode>()
    val pendingInOptgroup = ArrayDeque<Boolean>()
    for (index in children.indices.reversed()) {
        pending.addLast(children[index])
        pendingInOptgroup.addLast(false)
    }
    while (pending.isNotEmpty()) {
        val node = pending.removeLast()
        val inOptgroup = pendingInOptgroup.removeLast()
        if (node.isHtmlElement("option")) action(node)
        val isOptgroup = node.isHtmlElement("optgroup")
        if (node.endsListOfOptionsSearch() || (isOptgroup && inOptgroup)) continue
        for (index in node.children.indices.reversed()) {
            pending.addLast(node.children[index])
            pendingInOptgroup.addLast(inOptgroup || isOptgroup)
        }
    }
}

/**
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#concept-option-disabled
 */
private fun TreeNode.isDisabledOption(): Boolean {
    if (hasContentAttribute("disabled")) return true
    var ancestor = parent
    while (ancestor != null) {
        if (ancestor.isHtmlElement("optgroup")) return ancestor.hasContentAttribute("disabled")
        if (ancestor.endsListOfOptionsSearch()) return false
        ancestor = ancestor.parent
    }
    return false
}

/**
 * Whether this select element's display size is 1: its size attribute's value, if it parses as a non-negative
 * integer, and otherwise 4 if it has a multiple attribute and 1 if not.
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#concept-select-size
 */
private fun TreeNode.hasDisplaySizeOne(): Boolean {
    val size = contentAttributeValue("size")?.let(::parseNonNegativeInteger)
    return if (size != null) size == 1 else !hasContentAttribute("multiple")
}

/**
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#option-element-nearest-ancestor-select
 */
internal fun TreeNode.nearestAncestorSelect(): TreeNode? {
    // Step 1:
    var ancestorOptgroup: TreeNode? = null

    // Step 2:
    var ancestor = parent
    while (ancestor != null) {
        if (ancestor.isHtmlElement("datalist") || ancestor.isHtmlElement("hr") || ancestor.isHtmlElement("option")) {
            return null
        }
        if (ancestor.isHtmlElement("optgroup")) {
            if (ancestorOptgroup != null) return null
            ancestorOptgroup = ancestor
        }
        if (ancestor.isHtmlElement("select")) return ancestor
        ancestor = ancestor.parent
    }

    // Step 3:
    return null
}

/**
 * Runs the selectedness setting algorithm for this select element, returning whether it changed the selectedness of
 * any option.
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#selectedness-setting-algorithm
 */
internal fun TreeNode.runSelectednessSettingAlgorithm(): Boolean {
    // Step 1:
    if (hasContentAttribute("multiple")) return false

    // Steps 2-4:
    var hasChanged = false
    var firstEnabledOption: TreeNode? = null
    var lastSelectedOption: TreeNode? = null

    // Step 5:
    forEachInListOfOptions { option ->
        if (option.selectedness) {
            lastSelectedOption?.let {
                it.selectedness = false
                hasChanged = true
            }
            lastSelectedOption = option
        }
        if (firstEnabledOption == null && !option.isDisabledOption()) firstEnabledOption = option
    }

    // Step 6:
    val first = firstEnabledOption
    if (lastSelectedOption == null && first != null && hasDisplaySizeOne()) {
        first.selectedness = true
        lastSelectedOption = first
        hasChanged = true
    }

    selectedOptionAfterLastRun = lastSelectedOption
    hasRunSelectednessSettingAlgorithm = true

    // Step 7:
    return hasChanged
}

/**
 * Runs the selectedness setting algorithm for this select element after [option] joined its list of options, without
 * going through the list when [option] is the last option in it.
 *
 * After the algorithm runs, at most one option in the list is selected, and if none is and the display size is 1, none
 * is enabled. That still holds when the next run starts, since every change that could break it runs the algorithm,
 * so with [option] last in tree order the run only has to compare it with the option selected after the last run.
 */
private fun TreeNode.runSelectednessSettingAlgorithmFor(option: TreeNode): Boolean {
    if (hasContentAttribute("multiple")) return false
    if (!hasRunSelectednessSettingAlgorithm || !option.isLastInListOfOptionsOf(this)) {
        return runSelectednessSettingAlgorithm()
    }
    val selected = selectedOptionAfterLastRun
    return when {
        option.selectedness -> {
            selectedOptionAfterLastRun = option
            if (selected != null && selected !== option) {
                selected.selectedness = false
                true
            } else {
                false
            }
        }

        selected == null && hasDisplaySizeOne() && !option.isDisabledOption() -> {
            option.selectedness = true
            selectedOptionAfterLastRun = option
            true
        }

        else -> false
    }
}

/** Whether nothing follows this node in [select] in tree order but its own descendants. */
private fun TreeNode.isLastInListOfOptionsOf(select: TreeNode): Boolean {
    var node = this
    while (node !== select) {
        val parent = node.parent ?: return false
        if (parent.children.last() !== node) return false
        node = parent
    }
    return true
}

/**
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#reset-a-select's-selectedness
 */
internal fun TreeNode.resetSelectedness(hasChanged: Boolean) {
    // Step 1:
    runSelectednessSettingAlgorithm()

    // Steps 2-3, "If hasChanged is false, then return. Update a select's descendant selectedcontent elements given
    // select.", wait for Helium to model selectedcontent elements
}

/**
 * Updates this option element's cached nearest ancestor select element, and returns the select elements whose
 * selectedcontent elements need updating.
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#update-an-option's-nearest-ancestor-select
 */
internal fun TreeNode.updateNearestAncestorSelect(): List<TreeNode> {
    // Steps 1-3:
    val oldSelect = cachedNearestAncestorSelect
    val newSelect = nearestAncestorSelect()
    cachedNearestAncestorSelect = newSelect

    // Step 4:
    if (oldSelect === newSelect) return emptyList()

    // Steps 5-6:
    val result = mutableListOf<TreeNode>()
    for (select in listOf(oldSelect, newSelect)) {
        if (select == null) continue
        val hasChanged =
            if (select === newSelect) select.runSelectednessSettingAlgorithmFor(this)
            else select.runSelectednessSettingAlgorithm()
        if (selectedness || hasChanged) result += select
    }

    // Step 7:
    return result
}

/** Marks [inserted] and its ancestors as possibly containing an option element if it is one or contains one. */
private fun markMayContainOption(inserted: TreeNode) {
    if (inserted.isHtmlElement("option")) inserted.mayContainOption = true
    if (!inserted.mayContainOption) return
    var ancestor = inserted.parent
    while (ancestor != null && !ancestor.mayContainOption) {
        ancestor.mayContainOption = true
        ancestor = ancestor.parent
    }
}

/** Calls [action] with each option element among this node and its descendants, in tree order. */
private inline fun TreeNode.forEachOptionInclusiveDescendant(action: (TreeNode) -> Unit) {
    if (!mayContainOption) return
    val pending = ArrayDeque<TreeNode>()
    pending.addLast(this)
    while (pending.isNotEmpty()) {
        val node = pending.removeLast()
        if (node.isHtmlElement("option")) action(node)
        for (index in node.children.indices.reversed()) {
            val child = node.children[index]
            if (child.mayContainOption) pending.addLast(child)
        }
    }
}

/**
 * The option element insertion steps, run for [inserted] and each of its descendants: "update an option's nearest
 * ancestor select given insertedOption".
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#the-option-element
 */
internal fun optionNodeInserted(inserted: TreeNode) {
    markMayContainOption(inserted)
    inserted.forEachOptionInclusiveDescendant { it.updateNearestAncestorSelect() }
}

/**
 * The option element removing steps, run for [removed] and each of its descendants: "Let selects be the result of
 * update an option's nearest ancestor select given removedNode."
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#the-option-element
 */
internal fun optionNodeRemoved(removed: TreeNode) {
    removed.forEachOptionInclusiveDescendant { it.updateNearestAncestorSelect() }
}

/**
 * The steps the option, optgroup and select elements run when one of their attributes is added, changed or removed.
 * [oldValue] and [newValue] are null when the attribute is absent.
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#the-option-element
 */
internal fun TreeNode.selectednessAttributeChanged(
    namespace: String?,
    localName: String,
    oldValue: String?,
    newValue: String?
) {
    if (namespace != null || type != NodeType.ELEMENT || this.namespace != Namespaces.HTML) return
    val addedOrRemoved = (oldValue == null) != (newValue == null)
    when (this.localName) {
        "option" -> when (localName) {
            // "Whenever an option element option's selected attribute is added or removed"
            "selected" -> if (addedOrRemoved) {
                var hasChanged = false
                if (!dirtiness) {
                    if (newValue != null && !selectedness) {
                        selectedness = true
                        hasChanged = true
                    }
                    if (newValue == null && selectedness) {
                        selectedness = false
                        hasChanged = true
                    }
                }
                cachedNearestAncestorSelect?.resetSelectedness(hasChanged)
            }

            // "Whenever an option element's disabled attribute is added or removed"
            "disabled" -> if (addedOrRemoved) cachedNearestAncestorSelect?.resetSelectedness(false)
        }

        // "Whenever an optgroup element's disabled attribute is added or removed"
        "optgroup" -> if (localName == "disabled" && addedOrRemoved) {
            nearestAncestorSelect()?.resetSelectedness(false)
        }

        "select" -> when (localName) {
            // "Whenever a select element's size attribute is set, changed, or removed"
            "size" -> resetSelectedness(false)

            // "Whenever a select element select's multiple attribute is added or removed"
            "multiple" -> if (addedOrRemoved) {
                if (newValue == null) {
                    var foundSelected = false
                    forEachInListOfOptions { option ->
                        if (option.selectedness) {
                            if (foundSelected) option.selectedness = false
                            foundSelected = true
                        }
                    }
                }
                resetSelectedness(true)
            }
        }
    }
}
