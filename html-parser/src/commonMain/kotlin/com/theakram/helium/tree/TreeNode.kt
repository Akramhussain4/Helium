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

import com.theakram.helium.util.appendUnicode
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
    data: String? = null,
    public val attributes: MutableList<Attribute> = mutableListOf(),
    public val children: MutableList<TreeNode> = mutableListOf(),
    public var parent: TreeNode? = null,
    public var quirksMode: QuirksMode? = null,
    public var parserCannotChangeMode: Boolean = false,
    public var isIframeSrcdoc: Boolean = false,
    public var templateContent: TreeNode? = null
) {

    /**
     * The data while [appendData] appends to it, so that appending doesn't copy what is there each time. Only one of
     * [dataBuilder] and [dataString] holds the data: a builder is kept only until the data is read or
     * [finishAppendingData] is called, so a parsed tree holds no builders and their spare capacity.
     */
    private var dataBuilder: StringBuilder? = null

    /** The data as a string, or null while [dataBuilder] holds it. */
    private var dataString: String? = data

    /** The data of a text, comment or processing instruction node. */
    public var data: String?
        get() {
            finishAppendingData()
            return dataString
        }
        set(value) {
            dataString = value
            dataBuilder = null
        }

    /** Whether [appendData] has appended to the data since it was last read or set. */
    internal val isAppendingData: Boolean
        get() = dataBuilder != null

    /**
     * Appends [codePoint] to [data], in amortized constant time: the parser appends each character of a text run to
     * its Text node, and a new string each time would make that quadratic.
     */
    internal fun appendData(codePoint: Int) {
        val builder = dataBuilder ?: StringBuilder(dataString.orEmpty()).also {
            dataBuilder = it
            dataString = null
        }
        builder.appendUnicode(codePoint)
    }

    /** Turns the data that [appendData] appended into a string, letting go of the builder. */
    internal fun finishAppendingData() {
        val builder = dataBuilder ?: return
        dataString = builder.toString()
        dataBuilder = null
    }

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
        get() = nodeDocumentOfRoot(root)

    private fun nodeDocumentOfRoot(root: TreeNode): TreeNode? {
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
     * The form element this form-associated element is associated with, or null. The parser associates elements with
     * the form element pointer as it creates them, and inserting and removing nodes, changing a form attribute and
     * changing IDs reset it, as the HTML spec says, when made through TreeNode's methods rather than by changing
     * [children], [attributes] or [parent] directly or passing children to the constructor.
     *
     * Spec: https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#form-owner
     */
    public var formOwner: TreeNode? = null
        internal set

    /**
     * Spec: https://html.spec.whatwg.org/multipage/form-control-infrastructure.html#parser-inserted-flag
     */
    internal var parserInserted: Boolean = false

    // Set once this node or a descendant is a form-associated element, so form owner updates can skip the rest. It
    // isn't cleared when such an element is removed, which only costs a needless look.
    internal var mayContainFormAssociatedElement: Boolean = false

    // For a Document, what resetting form owners looks up, once it has a listed element with a form attribute
    internal var formOwnerIndex: FormOwnerIndex? = null

    /**
     * Whether this option element is selected. Helium keeps it up to date as the HTML spec says when nodes are
     * inserted and removed and attributes added through TreeNode's methods, as it does [formOwner].
     *
     * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#concept-option-selectedness
     */
    internal var selectedness: Boolean = false

    /**
     * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#concept-option-dirtiness
     */
    internal var dirtiness: Boolean = false

    /**
     * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#cached-nearest-ancestor-select-element
     */
    internal var cachedNearestAncestorSelect: TreeNode? = null

    /**
     * For a select element, whether the selectedness setting algorithm has run for it, and the option it left
     * selected, so that an option appended to it needn't go through its whole list of options.
     */
    internal var hasRunSelectednessSettingAlgorithm: Boolean = false
    internal var selectedOptionAfterLastRun: TreeNode? = null

    /**
     * Spec: https://html.spec.whatwg.org/multipage/form-elements.html#selectedcontent-disabled
     */
    internal var selectedcontentDisabled: Boolean = false

    // For a select element, its enabled selectedcontent descendants in tree order, or null if they need finding again
    internal var selectedcontentsInTreeOrder: List<TreeNode>? = null

    // Set once this node or a descendant is an option or selectedcontent element, so that inserting and removing nodes
    // can skip the subtrees without one. Like mayContainFormAssociatedElement, they aren't cleared.
    internal var mayContainOption: Boolean = false
    internal var mayContainSelectedcontent: Boolean = false

    /**
     * Spec: https://dom.spec.whatwg.org/#concept-tree-root
     */
    internal val root: TreeNode
        get() {
            var root = this
            while (true) root = root.parent ?: return root
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
     * Whether this is a template element in the HTML namespace.
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
        insertBefore(child, null)
    }

    /**
     * Inserts [newChild] before [referenceChild], or last if it is null, first removing it from its parent, as the
     * DOM's insert does, and running the insertion, post-connection and removing steps that Helium models: those that
     * keep form owners, the selectedness of option elements and selectedcontent elements up to date. Unlike the DOM, a
     * document fragment is inserted as a node, not by moving its children.
     *
     * Spec: https://dom.spec.whatwg.org/#concept-node-pre-insert
     */
    public fun insertBefore(newChild: TreeNode, referenceChild: TreeNode?) {
        // "If node is a host-including inclusive ancestor of parent, then throw a HierarchyRequestError". A node with
        // no children or template contents can only be an ancestor of itself, which spares the walk up for every new
        // element the parser inserts.
        if (newChild.children.isEmpty() && newChild.templateContent == null) {
            require(newChild !== this) { HIERARCHY_REQUEST_ERROR }
        } else {
            require(!newChild.isHostIncludingInclusiveAncestorOf(this)) { HIERARCHY_REQUEST_ERROR }
        }
        require(referenceChild == null || referenceChild.parent === this) {
            "Reference child is not a child of this node"
        }
        // "If referenceChild is node, then set referenceChild to node's next sibling"
        val reference = if (referenceChild === newChild) newChild.nextSibling else referenceChild
        // Moving a node, or inserting option or selectedcontent elements, can queue selectedcontent updates, which
        // wait for the insertion to finish. Other insertions skip the walk up to the root this needs.
        val scope = if (newChild.parent != null || newChild.mayContainSelectPart()) root else null
        scope?.let { it.mutationDepth++ }
        try {
            newChild.parent?.removeChild(newChild)
            val index = if (reference == null) children.size else children.indexOf(reference)
            children.add(index, newChild)
            newChild.parent = this
            formOwnerNodeInserted(newChild)
            selectNodeInserted(newChild)
        } finally {
            scope?.endMutation()
        }
    }

    public fun removeChild(child: TreeNode) {
        val oldRoot = root
        val document = nodeDocumentOfRoot(oldRoot)
        require(child.parent === this) { "Node is not a child of this node" }
        oldRoot.mutationDepth++
        try {
            children.remove(child)
            child.parent = null
            child.rootNodeDocument = document
            formOwnerNodeRemoved(child, oldRoot)
            selectNodeRemoved(child, oldRoot)
        } finally {
            oldRoot.endMutation()
        }
    }

    /**
     * For a tree's root, how many insertions and removals in the tree are under way, and the select elements whose
     * selectedcontent elements their removing steps queued updates for. The spec queues those updates as microtasks;
     * Helium runs them when the outermost insertion or removal finishes, so that they don't change the tree in the
     * middle of one.
     */
    internal var mutationDepth: Int = 0
    internal var pendingSelectedcontentUpdates: MutableList<TreeNode>? = null

    private fun endMutation() {
        if (--mutationDepth == 0 && pendingSelectedcontentUpdates != null) runPendingSelectedcontentUpdates()
    }

    /**
     * Runs [block], which changes the tree this node is in, with the selectedcontent updates it queues waiting for it
     * to finish, as an insertion or removal does.
     */
    internal fun withMutationScope(block: () -> Unit) {
        val scope = root
        scope.mutationDepth++
        try {
            block()
        } finally {
            scope.endMutation()
        }
    }

    /**
     * Clones this node, and if [subtree] is true its descendants and a template's contents, as the DOM's clone does,
     * appending the copy to [parent] if it isn't null. Only the state Helium models is copied.
     *
     * Spec: https://dom.spec.whatwg.org/#concept-node-clone
     */
    internal fun cloneNode(subtree: Boolean, parent: TreeNode? = null): TreeNode {
        // Each node to clone, with the copy its copy is appended to, so that deep trees don't recurse
        val pending = ArrayDeque<Pair<TreeNode, TreeNode?>>()
        pending.addLast(this to parent)
        var result: TreeNode? = null
        while (pending.isNotEmpty()) {
            val (node, copyParent) = pending.removeLast()
            // Step 1:
            val copy = node.cloneSingleNode()
            if (result == null) result = copy

            // Step 3:
            copyParent?.appendChild(copy)

            // Step 4:
            if (subtree) {
                for (index in node.children.indices.reversed()) pending.addLast(node.children[index] to copy)
            }

            // Step 2: the template element's cloning steps, "For each child of node's template contents's children, in
            // tree order: clone a node given child with document set to copy's template contents's node document,
            // subtree set to true, and parent set to copy's template contents." They go on the same stack, so that
            // nested templates don't recurse, and are cloned before the children; the contents being a tree of their
            // own, appending the copy first doesn't change the result.
            val contents = node.templateContent
            val copyContents = copy.templateContent
            if (subtree && contents != null && copyContents != null) {
                for (index in contents.children.indices.reversed()) {
                    pending.addLast(contents.children[index] to copyContents)
                }
            }
        }
        return result!!
    }

    /**
     * Spec: https://dom.spec.whatwg.org/#clone-a-single-node
     */
    private fun cloneSingleNode(): TreeNode = when (type) {
        NodeType.ELEMENT -> createElement(localName!!, namespace, prefix, attributes)
        NodeType.TEXT -> createText(data.orEmpty())
        NodeType.COMMENT -> createComment(data.orEmpty())
        NodeType.PROCESSING_INSTRUCTION -> createProcessingInstruction(localName!!, data.orEmpty())
        NodeType.CDATA_SECTION -> TreeNode(type = NodeType.CDATA_SECTION, data = data)
        NodeType.DOCUMENT_TYPE -> createDocumentType(localName!!, publicId, systemId)
        NodeType.DOCUMENT -> createDocument(quirksMode ?: QuirksMode.NO_QUIRKS)
        NodeType.DOCUMENT_FRAGMENT -> createDocumentFragment()
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
            val old = attributes[index]
            attributes[index] = old.copy(value = value)
            attributeChanged(old.namespace, old.localName, old.value, value)
            return
        }
        val localName = normalizedQualifiedName(qualifiedName)
        appendAttribute(Attribute(namespace = null, prefix = null, localName = localName, value = value))
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
            val old = attributes[index]
            attributes[index] = old.copy(value = value)
            attributeChanged(old.namespace, old.localName, old.value, value)
        } else {
            val attributeNamespace = namespace?.ifEmpty { null }
            appendAttribute(
                Attribute(namespace = attributeNamespace, prefix = prefix, localName = localName, value = value)
            )
        }
    }

    /**
     * Appends [attribute], as the DOM's "append an attribute" does, with the attribute change steps Helium models.
     *
     * Spec: https://dom.spec.whatwg.org/#concept-element-attributes-append
     */
    internal fun appendAttribute(attribute: Attribute) {
        attributes.add(attribute)
        attributeChanged(attribute.namespace, attribute.localName, null, attribute.value)
    }

    /** The attribute change steps Helium models. */
    private fun attributeChanged(namespace: String?, localName: String, oldValue: String?, newValue: String?) {
        formOwnerAttributeChanged(namespace, localName, oldValue, newValue)
        selectednessAttributeChanged(namespace, localName, oldValue, newValue)
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
        private const val HIERARCHY_REQUEST_ERROR =
            "HierarchyRequestError: a node can't be inserted into itself or its descendants"

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
            // "Except where otherwise specified, when the element is created, its selectedness must be set to true if
            // the element has a selected attribute."
            if (element.isHtmlElement("option") &&
                element.attributes.any { it.namespace == null && it.localName == "selected" }
            ) {
                element.selectedness = true
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