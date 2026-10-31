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

import com.theakram.helium.util.equalsAsciiCaseInsensitive

/**
 * Tree node for HTML5 tree construction phase.
 *
 * Spec references:
 * - Tree construction: https://html.spec.whatwg.org/multipage/parsing.html#tree-construction
 * - DOM Node interface: https://dom.spec.whatwg.org/#interface-node
 * - Element interface: https://dom.spec.whatwg.org/#interface-element
 */
public class TreeNode(
    public val type: NodeType,
    public val namespace: String? = null,
    public val localName: String? = null,
    public val prefix: String? = null,
    public val publicId: String? = null,
    public val systemId: String? = null,
    public var data: String? = null,
    public val attributes: MutableList<Attribute> = mutableListOf(),
    public val children: MutableList<TreeNode> = mutableListOf(),
    public var parent: TreeNode? = null,
    public var quirksMode: QuirksMode? = null,
    public var parserCannotChangeMode: Boolean = false,
    public var isIframeSrcdoc: Boolean = false,
    public var templateContent: TreeNode? = null
) {

    /**
     * The template element whose template contents this document fragment is.
     *
     * Spec: https://dom.spec.whatwg.org/#concept-documentfragment-host
     */
    internal var host: TreeNode? = null

    /**
     * Where a template element with a for attribute inserts its contents, and the markers around them.
     *
     * Spec: https://html.spec.whatwg.org/multipage/scripting.html#insertion-target
     */
    internal var insertionTarget: TreeNode? = null
    internal var insertionStartMarker: TreeNode? = null
    internal var insertionEndMarker: TreeNode? = null

    /**
     * Spec: https://dom.spec.whatwg.org/#concept-tree-host-including-inclusive-ancestor
     */
    internal fun isHostIncludingInclusiveAncestorOf(node: TreeNode): Boolean {
        var current: TreeNode? = node
        while (current != null) {
            if (current === this) return true
            current = current.parent ?: current.host
        }
        return false
    }

    /**
     * Returns true if this is a template element with content.
     */
    public val isTemplate: Boolean
        get() = type == NodeType.ELEMENT &&
                namespace == Namespaces.HTML &&
                localName == "template"

    public val name: String?
        get() = when {
            localName == null -> null
            prefix != null -> "$prefix:$localName"
            else -> localName
        }

    public val previousSibling: TreeNode?
        get() = parent?.children?.let { siblings ->
            val index = siblings.indexOf(this)
            if (index > 0) siblings[index - 1] else null
        }


    public val nextSibling: TreeNode?
        get() = parent?.children?.let { siblings ->
            val index = siblings.indexOf(this)
            if (index < siblings.size - 1) siblings[index + 1] else null
        }


    public fun appendChild(child: TreeNode) {
        // Remove from previous parent if moving between parents
        child.parent?.children?.remove(child)
        children.add(child)
        child.parent = this
    }

    public fun insertBefore(newChild: TreeNode, referenceChild: TreeNode?) {
        // Remove from previous parent if moving between parents
        newChild.parent?.children?.remove(newChild)
        if (referenceChild == null) {
            appendChild(newChild)
        } else {
            val index = children.indexOf(referenceChild)
            if (index != -1) {
                children.add(index, newChild)
                newChild.parent = this
            } else {
                throw IllegalArgumentException("Reference child is not a child of this node")
            }
        }
    }

    public fun removeChild(child: TreeNode) {
        if (children.remove(child)) {
            child.parent = null
        } else {
            throw IllegalArgumentException("Node is not a child of this node")
        }
    }

    public val firstChild: TreeNode?
        get() = children.firstOrNull()

    public val lastChild: TreeNode?
        get() = children.lastOrNull()

    public fun hasChildren(): Boolean = children.isNotEmpty()

    public fun findChildElement(tagName: String): TreeNode? {
        return children.find {
            it.type == NodeType.ELEMENT &&
            it.localName?.equalsAsciiCaseInsensitive(tagName) == true
        }
    }

    public val childElements: List<TreeNode>
        get() = children.filter { it.type == NodeType.ELEMENT }

    public fun setAttribute(name: String, value: String) {
        attributes.removeAll { it.namespace == null && it.localName.equalsAsciiCaseInsensitive(name) }
        attributes.add(Attribute(namespace = null, prefix = null, localName = name, value = value))
    }

    public fun setAttributeNS(namespace: String?, prefix: String?, localName: String, value: String) {
        attributes.removeAll {
            it.namespace == namespace && it.localName.equalsAsciiCaseInsensitive(localName)
        }
        attributes.add(Attribute(namespace = namespace, prefix = prefix, localName = localName, value = value))
    }

    public fun getAttribute(name: String): String? {
        return attributes.find {
            it.namespace == null && it.localName.equalsAsciiCaseInsensitive(name)
        }?.value
    }

    public fun getAttributeNS(namespace: String?, localName: String): String? {
        return attributes.find {
            it.namespace == namespace && it.localName.equalsAsciiCaseInsensitive(localName)
        }?.value
    }

    public fun hasAttribute(name: String): Boolean {
        return attributes.any {
            it.namespace == null && it.localName.equalsAsciiCaseInsensitive(name)
        }
    }

    public fun hasAttributeNS(namespace: String?, localName: String): Boolean {
        return attributes.any {
            it.namespace == namespace && it.localName.equalsAsciiCaseInsensitive(localName)
        }
    }

    /**
     * Serialize to html5lib tree format for testing.
     * Format: "| " + "  " per nesting level + node representation
     */
    public fun toTreeString(depth: Int = 0): String {
        val indent = "| " + "  ".repeat(depth)
        val nodeRepr = when (type) {
            NodeType.DOCUMENT -> "#document"
            NodeType.DOCUMENT_TYPE -> {
                val hasPublic = !publicId.isNullOrEmpty()
                val hasSystem = !systemId.isNullOrEmpty()

                when {
                    !hasPublic && !hasSystem -> "<!DOCTYPE $localName>"
                    else -> {
                        val pub = publicId ?: ""
                        val sys = systemId ?: ""
                        "<!DOCTYPE $localName \"$pub\" \"$sys\">"
                    }
                }
            }
            NodeType.ELEMENT -> {
                val namespacePrefix = when (namespace) {
                    Namespaces.SVG -> "svg "
                    Namespaces.MATHML -> "math "
                    else -> ""
                }
                "<$namespacePrefix$name>"
            }
            NodeType.TEXT -> "\"$data\""
            NodeType.COMMENT -> "<!-- $data -->"
            NodeType.PROCESSING_INSTRUCTION -> "<?$name $data?>"
            NodeType.CDATA_SECTION -> "<![CDATA[$data]]>"
            NodeType.DOCUMENT_FRAGMENT -> "#document-fragment"
        }

        val result = StringBuilder()
        result.append(indent + nodeRepr)

        if (type == NodeType.ELEMENT && attributes.isNotEmpty()) {
            // Sorted by the printed name, as html5lib-tests expects
            val namedAttrs = attributes.map { attr ->
                val attrName = if (attr.prefix != null) {
                    "${attr.prefix} ${attr.localName}"
                } else {
                    attr.localName
                }
                attrName to attr
            }.sortedBy { it.first }
            for ((attrName, attr) in namedAttrs) {
                result.append("\n")
                val attrIndent = "| " + "  ".repeat(depth + 1)
                result.append("$attrIndent$attrName=\"${attr.value}\"")
            }
        }

        // For template elements, render the content document fragment
        if (isTemplate && templateContent != null) {
            result.append("\n")
            val contentIndent = "| " + "  ".repeat(depth + 1)
            result.append("${contentIndent}content")
            for (child in templateContent!!.children) {
                result.append("\n")
                result.append(child.toTreeString(depth + 2).trimEnd())
            }
        }

        for (child in children) {
            result.append("\n")
            result.append(child.toTreeString(depth + 1).trimEnd())
        }
        return result.toString()
    }

    public companion object {
        public fun createDocument(quirksMode: QuirksMode = QuirksMode.NO_QUIRKS): TreeNode {
            return TreeNode(
                type = NodeType.DOCUMENT,
                quirksMode = quirksMode
            )
        }

        public fun createElement(
            localName: String,
            namespace: String = Namespaces.HTML,
            prefix: String? = null,
            attributes: List<Attribute> = emptyList()
        ): TreeNode {
            val element = TreeNode(
                type = NodeType.ELEMENT,
                namespace = namespace,
                localName = localName,
                prefix = prefix,
                attributes = attributes.toMutableList()
            )
            // Per spec, template elements have a content property that is a DocumentFragment
            if (namespace == Namespaces.HTML && localName == "template") {
                element.templateContent = createDocumentFragment().also { it.host = element }
            }
            return element
        }

        public fun createText(data: String): TreeNode {
            return TreeNode(type = NodeType.TEXT, data = data)
        }

        public fun createComment(data: String): TreeNode {
            return TreeNode(type = NodeType.COMMENT, data = data)
        }

        /** Creates a processing instruction; [target] is stored as its [localName], as [name] reports it. */
        public fun createProcessingInstruction(target: String, data: String): TreeNode {
            return TreeNode(type = NodeType.PROCESSING_INSTRUCTION, localName = target, data = data)
        }

        public fun createDocumentType(
            name: String,
            publicId: String? = null,
            systemId: String? = null
        ): TreeNode {
            return TreeNode(
                type = NodeType.DOCUMENT_TYPE,
                localName = name,
                publicId = publicId,
                systemId = systemId
            )
        }

        public fun createDocumentFragment(): TreeNode {
            return TreeNode(type = NodeType.DOCUMENT_FRAGMENT)
        }
    }
}