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

import com.theakram.helium.util.asciiLowercase
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
     * The Document a tree root other than a Document still belongs to: a node removed from its parent keeps its
     * node document, and a fragment returned by fragment parsing belongs to its target's. Null for a node created on
     * its own.
     */
    internal var rootNodeDocument: TreeNode? = null

    /**
     * Whether this Document was created by the appropriate template contents owner document algorithm, whose
     * documents have no browsing context.
     */
    internal var isInertTemplateDocument: Boolean = false

    /**
     * Spec: https://html.spec.whatwg.org/multipage/scripting.html#associated-inert-template-document
     */
    private var associatedInertTemplateDocument: TreeNode? = null

    /**
     * The Document this node belongs to, found from the node's root since nodes don't link to it: the root itself if
     * it is a Document, the template's appropriate template contents owner document for template contents, or the
     * Document a removed node or parsed fragment was left with. Null for a node that was never in a document.
     *
     * Spec: https://dom.spec.whatwg.org/#concept-node-document
     */
    internal val nodeDocument: TreeNode?
        get() {
            var root = this
            while (true) {
                root = root.parent ?: break
            }
            val host = root.host
            return when {
                root.type == NodeType.DOCUMENT -> root
                root.rootNodeDocument != null -> root.rootNodeDocument
                // Template contents keep the document they were created for, even if the template is given new ones
                host != null -> appropriateTemplateContentsOwnerDocument(host.nodeDocument)
                else -> null
            }
        }

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
        val document = nodeDocument
        if (children.remove(child)) {
            child.parent = null
            child.rootNodeDocument = document
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

    // Helium's documents are all HTML documents, so the DOM's "in the HTML namespace and its node document is an HTML
    // document" condition is just the namespace
    private fun normalizedQualifiedName(qualifiedName: String): String =
        if (namespace == Namespaces.HTML) qualifiedName.asciiLowercase() else qualifiedName

    /**
     * Spec: https://dom.spec.whatwg.org/#concept-element-attributes-get-by-name
     */
    private fun attributeIndexByName(qualifiedName: String): Int {
        val name = normalizedQualifiedName(qualifiedName)
        return attributes.indexOfFirst { it.name == name }
    }

    private fun attributeByName(qualifiedName: String): Attribute? =
        attributes.getOrNull(attributeIndexByName(qualifiedName))

    /**
     * Spec: https://dom.spec.whatwg.org/#concept-element-attributes-get-by-namespace
     */
    private fun attributeIndexByNamespace(namespace: String?, localName: String): Int {
        val attributeNamespace = namespace?.ifEmpty { null }
        return attributes.indexOfFirst { it.namespace == attributeNamespace && it.localName == localName }
    }

    /**
     * Sets the value of the attribute whose qualified name is [qualifiedName], or adds an attribute with no namespace
     * and that local name. On an HTML element the name is ASCII lowercased first.
     *
     * @throws IllegalArgumentException if [qualifiedName] is not a valid attribute local name (the DOM's
     * "InvalidCharacterError")
     *
     * Spec: https://dom.spec.whatwg.org/#dom-element-setattribute
     */
    public fun setAttribute(qualifiedName: String, value: String) {
        require(isValidAttributeLocalName(qualifiedName)) {
            "InvalidCharacterError: \"$qualifiedName\" is not a valid attribute local name"
        }
        val index = attributeIndexByName(qualifiedName)
        if (index != -1) {
            attributes[index] = attributes[index].copy(value = value)
            return
        }
        val localName = normalizedQualifiedName(qualifiedName)
        attributes.add(Attribute(namespace = null, prefix = null, localName = localName, value = value))
    }

    /**
     * Sets the value of the attribute in [namespace] whose local name is the one in [qualifiedName], or adds the
     * attribute with the prefix and local name [qualifiedName] gives. An existing attribute keeps its prefix.
     *
     * @throws IllegalArgumentException if the name is not valid or doesn't fit the namespace (the DOM's
     * "InvalidCharacterError" and "NamespaceError")
     *
     * Spec: https://dom.spec.whatwg.org/#dom-element-setattributens
     */
    public fun setAttributeNS(namespace: String?, qualifiedName: String, value: String) {
        val (attributeNamespace, prefix, localName) = validateAndExtract(namespace, qualifiedName)
        setAttributeNS(attributeNamespace, prefix, localName, value)
    }

    /**
     * Sets the value of the attribute in [namespace] with [localName], or adds the attribute with [prefix] if there
     * is none. An existing attribute keeps its prefix. Unlike the qualified name overload, this doesn't validate the
     * names, as the DOM's own algorithm doesn't.
     *
     * Spec: https://dom.spec.whatwg.org/#concept-element-attributes-set-value
     */
    public fun setAttributeNS(namespace: String?, prefix: String?, localName: String, value: String) {
        val index = attributeIndexByNamespace(namespace, localName)
        if (index != -1) {
            attributes[index] = attributes[index].copy(value = value)
            return
        }
        val attributeNamespace = namespace?.ifEmpty { null }
        attributes.add(Attribute(namespace = attributeNamespace, prefix = prefix, localName = localName, value = value))
    }

    /**
     * The value of the first attribute whose qualified name is [qualifiedName], or null. On an HTML element the name
     * is ASCII lowercased first.
     *
     * Spec: https://dom.spec.whatwg.org/#dom-element-getattribute
     */
    public fun getAttribute(qualifiedName: String): String? = attributeByName(qualifiedName)?.value

    /**
     * Spec: https://dom.spec.whatwg.org/#dom-element-getattributens
     */
    public fun getAttributeNS(namespace: String?, localName: String): String? =
        attributes.getOrNull(attributeIndexByNamespace(namespace, localName))?.value

    /**
     * Spec: https://dom.spec.whatwg.org/#dom-element-hasattribute
     */
    public fun hasAttribute(qualifiedName: String): Boolean = attributeByName(qualifiedName) != null

    /**
     * Spec: https://dom.spec.whatwg.org/#dom-element-hasattributens
     */
    public fun hasAttributeNS(namespace: String?, localName: String): Boolean =
        attributeIndexByNamespace(namespace, localName) != -1

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
        /**
         * Spec: https://html.spec.whatwg.org/multipage/scripting.html#appropriate-template-contents-owner-document
         *
         * A template created on its own has no node document, so its contents get a new inert document.
         */
        internal fun appropriateTemplateContentsOwnerDocument(document: TreeNode?): TreeNode {
            if (document != null && document.isInertTemplateDocument) return document
            document?.associatedInertTemplateDocument?.let { return it }
            val newDocument = createDocument().also { it.isInertTemplateDocument = true }
            document?.associatedInertTemplateDocument = newDocument
            return newDocument
        }

        public fun createDocument(quirksMode: QuirksMode = QuirksMode.NO_QUIRKS): TreeNode {
            return TreeNode(
                type = NodeType.DOCUMENT,
                quirksMode = quirksMode
            )
        }

        public fun createElement(
            localName: String,
            namespace: String? = Namespaces.HTML,
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