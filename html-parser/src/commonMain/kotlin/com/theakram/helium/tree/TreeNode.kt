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

package com.theakram.helium.tree

/**
 * Tree node for HTML5 tree construction phase.
 *
 * Implements WHATWG HTML specification requirements for tree construction,
 * including namespace support for HTML, SVG, and MathML content.
 *
 * Note: This is not a data class to maintain object identity semantics -
 * nodes are compared by reference, not by structural equality.
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
    public var isIframeSrcdoc: Boolean = false
) {

    /**
     * The qualified name of the element.
     * Returns "prefix:localName" for namespaced elements, or just "localName" otherwise.
     * For non-element nodes, returns the appropriate name based on node type.
     */
    public val name: String?
        get() = when {
            localName == null -> null
            prefix != null -> "$prefix:$localName"
            else -> localName
        }

    /**
     * Get the previous sibling of this node.
     */
    public val previousSibling: TreeNode?
        get() = parent?.children?.let { siblings ->
            val index = siblings.indexOf(this)
            if (index > 0) siblings[index - 1] else null
        }

    /**
     * Get the next sibling of this node.
     */
    public val nextSibling: TreeNode?
        get() = parent?.children?.let { siblings ->
            val index = siblings.indexOf(this)
            if (index < siblings.size - 1) siblings[index + 1] else null
        }

    /**
     * Add a child node and set parent relationship.
     */
    public fun appendChild(child: TreeNode) {
        // Remove from previous parent if moving between parents
        child.parent?.children?.remove(child)

        children.add(child)
        child.parent = this
    }

    /**
     * Insert a child at specific position.
     */
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

    /**
     * Remove a child node.
     */
    public fun removeChild(child: TreeNode) {
        if (children.remove(child)) {
            child.parent = null
        } else {
            throw IllegalArgumentException("Node is not a child of this node")
        }
    }

    /**
     * Get the first child of this node.
     */
    public val firstChild: TreeNode?
        get() = children.firstOrNull()

    /**
     * Get the last child of this node.
     */
    public val lastChild: TreeNode?
        get() = children.lastOrNull()

    /**
     * Check if this node has children.
     */
    public fun hasChildren(): Boolean = children.isNotEmpty()

    /**
     * Find child element with specific tag name.
     */
    public fun findChildElement(tagName: String): TreeNode? {
        return children.find {
            it.type == NodeType.ELEMENT &&
            it.localName?.equals(tagName, ignoreCase = true) == true
        }
    }

    /**
     * Get all child elements (excluding text nodes, comments, etc.)
     */
    public val childElements: List<TreeNode>
        get() = children.filter { it.type == NodeType.ELEMENT }

    /**
     * Set a non-namespaced attribute on this element.
     */
    public fun setAttribute(name: String, value: String) {
        // Remove existing attribute with the same name
        attributes.removeAll { it.namespace == null && it.localName.equals(name, ignoreCase = true) }
        // Add new attribute
        attributes.add(Attribute(namespace = null, prefix = null, localName = name, value = value))
    }

    /**
     * Set a namespaced attribute on this element.
     */
    public fun setAttributeNS(namespace: String?, prefix: String?, localName: String, value: String) {
        // Remove existing attribute with the same namespace and localName
        attributes.removeAll {
            it.namespace == namespace && it.localName.equals(localName, ignoreCase = true)
        }
        // Add new attribute
        attributes.add(Attribute(namespace = namespace, prefix = prefix, localName = localName, value = value))
    }

    /**
     * Get a non-namespaced attribute value.
     */
    public fun getAttribute(name: String): String? {
        return attributes.find {
            it.namespace == null && it.localName.equals(name, ignoreCase = true)
        }?.value
    }

    /**
     * Get a namespaced attribute value.
     */
    public fun getAttributeNS(namespace: String?, localName: String): String? {
        return attributes.find {
            it.namespace == namespace && it.localName.equals(localName, ignoreCase = true)
        }?.value
    }

    /**
     * Check if element has a non-namespaced attribute.
     */
    public fun hasAttribute(name: String): Boolean {
        return attributes.any {
            it.namespace == null && it.localName.equals(name, ignoreCase = true)
        }
    }

    /**
     * Check if element has a namespaced attribute.
     */
    public fun hasAttributeNS(namespace: String?, localName: String): Boolean {
        return attributes.any {
            it.namespace == namespace && it.localName.equals(localName, ignoreCase = true)
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
            NodeType.DOCUMENT_TYPE -> "<!DOCTYPE $localName" +
                (if (!publicId.isNullOrEmpty()) " \"$publicId\"" else "") +
                (if (!systemId.isNullOrEmpty()) " \"$systemId\"" else "") + ">"
            NodeType.ELEMENT -> {
                if (attributes.isNotEmpty()) {
                    val sortedAttrs = attributes.sortedBy { it.name }
                    val attrString = sortedAttrs.joinToString(" ") { "${it.name}=\"${it.value}\"" }
                    "<$name $attrString>"
                } else {
                    "<$name>"
                }
            }
            NodeType.TEXT -> "\"$data\""
            NodeType.COMMENT -> "<!-- $data -->"
            NodeType.PROCESSING_INSTRUCTION -> "<?$name $data?>"
            NodeType.CDATA_SECTION -> "<![CDATA[$data]]>"
            NodeType.DOCUMENT_FRAGMENT -> "#document-fragment"
        }

        val result = StringBuilder()
        result.append(indent + nodeRepr)

        // Add children
        for (child in children) {
            result.append("\n")
            result.append(child.toTreeString(depth + 1).trimEnd())
        }

        return result.toString()
    }

    /**
     * Object identity-based equality.
     * Two TreeNode instances are equal only if they are the same object.
     */
    override fun equals(other: Any?): Boolean = this === other

    /**
     * Identity-based hash code.
     */
    override fun hashCode(): Int = System.identityHashCode(this)

    public companion object {
        /**
         * Create a document node.
         */
        public fun createDocument(quirksMode: QuirksMode = QuirksMode.NO_QUIRKS): TreeNode {
            return TreeNode(
                type = NodeType.DOCUMENT,
                quirksMode = quirksMode
            )
        }

        /**
         * Create an element node.
         */
        public fun createElement(
            localName: String,
            namespace: String = Namespaces.HTML,
            prefix: String? = null,
            attributes: List<Attribute> = emptyList()
        ): TreeNode {
            return TreeNode(
                type = NodeType.ELEMENT,
                namespace = namespace,
                localName = localName,
                prefix = prefix,
                attributes = attributes.toMutableList()
            )
        }

        /**
         * Create a text node.
         */
        public fun createText(data: String): TreeNode {
            return TreeNode(type = NodeType.TEXT, data = data)
        }

        /**
         * Create a comment node.
         */
        public fun createComment(data: String): TreeNode {
            return TreeNode(type = NodeType.COMMENT, data = data)
        }

        /**
         * Create a DOCTYPE node.
         */
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

        /**
         * Create a document fragment node.
         */
        public fun createDocumentFragment(): TreeNode {
            return TreeNode(type = NodeType.DOCUMENT_FRAGMENT)
        }
    }
}
