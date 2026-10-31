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

import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.NodeType
import com.theakram.helium.tree.TreeNode

/**
 * Keeps a customizable select's selectedcontent element showing a copy of its selected option.
 * The parser runs this whenever an option element is popped off the stack of open elements.
 *
 * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#maybe-clone-an-option-into-selectedcontent
 */
internal object SelectedContent {

    fun onElementPopped(element: TreeNode) {
        if (element.isHTML("option")) {
            maybeCloneOptionIntoSelectedContent(element)
        }
    }

    private fun maybeCloneOptionIntoSelectedContent(option: TreeNode) {
        val select = generateSequence(option.parent) { it.parent }.firstOrNull { it.isHTML("select") } ?: return
        if (selectedOption(select) !== option) return

        val selectedContent = descendants(select).firstOrNull { it.isHTML("selectedcontent") } ?: return
        selectedContent.children.toList().forEach { selectedContent.removeChild(it) }
        option.children.forEach { selectedContent.appendChild(cloneNode(it)) }
    }

    /**
     * The last option with a selected attribute, or else the first option that isn't disabled.
     *
     * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#selectedness-setting-algorithm
     */
    private fun selectedOption(select: TreeNode): TreeNode? {
        val options = descendants(select).filter { it.isHTML("option") }.toList()
        return options.lastOrNull { it.hasAttribute("selected") }
            ?: options.firstOrNull { !it.hasAttribute("disabled") }
    }

    private fun descendants(node: TreeNode): Sequence<TreeNode> = sequence {
        for (child in node.children) {
            yield(child)
            yieldAll(descendants(child))
        }
    }

    private fun cloneNode(node: TreeNode): TreeNode {
        val clone = when (node.type) {
            NodeType.ELEMENT -> TreeNode.createElement(
                localName = node.localName.orEmpty(),
                namespace = node.namespace ?: Namespaces.HTML,
                prefix = node.prefix,
                attributes = node.attributes
            )

            NodeType.TEXT -> TreeNode.createText(node.data.orEmpty())

            else -> TreeNode.createComment(node.data.orEmpty())
        }
        node.children.forEach { clone.appendChild(cloneNode(it)) }
        return clone
    }

    private fun TreeNode.isHTML(localName: String): Boolean =
        type == NodeType.ELEMENT && namespace == Namespaces.HTML && this.localName == localName
}
