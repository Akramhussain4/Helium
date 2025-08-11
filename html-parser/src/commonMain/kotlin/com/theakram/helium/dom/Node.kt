package com.theakram.helium.dom

/**
 * The Node interface is the primary datatype for the entire Document Object Model.
 * It represents a single node in the document tree.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-node
 */
public sealed interface Node {
    public val nodeType: Short
    public val nodeName: String
    public val baseURI: String
    public val isConnected: Boolean
    public val ownerDocument: Document?
    public val parentNode: Node?
    public val parentElement: Element?
    public val childNodes: List<Node>
    public val firstChild: Node?
        get() = childNodes.firstOrNull()
    public val lastChild: Node?
        get() = childNodes.lastOrNull()
    public val previousSibling: Node?
    public val nextSibling: Node?
    public var nodeValue: String?
    public var textContent: String?
    public fun getRootNode(options: GetRootNodeOptions? = null): Node
    public fun hasChildNodes(): Boolean
    public fun normalize()
    public fun cloneNode(deep: Boolean = false): Node
    public fun isEqualNode(other: Node?): Boolean
    public fun isSameNode(other: Node?): Boolean
    public fun compareDocumentPosition(other: Node): UShort
    public fun contains(other: Node?): Boolean
    public fun lookupPrefix(namespace: String?): String?
    public fun lookupNamespaceURI(prefix: String?): String?
    public fun isDefaultNamespace(namespace: String?): Boolean
    public fun insertBefore(newNode: Node, referenceNode: Node?): Node
    public fun appendChild(node: Node): Node
    public fun replaceChild(node: Node, child: Node): Node
    public fun removeChild(node: Node): Node

    public companion object {
        public const val ELEMENT_NODE: Short = 1
        public const val ATTRIBUTE_NODE: Short = 2
        public const val TEXT_NODE: Short = 3
        public const val CDATA_SECTION_NODE: Short = 4
        public const val ENTITY_REFERENCE_NODE: Short = 5
        public const val ENTITY_NODE: Short = 6
        public const val PROCESSING_INSTRUCTION_NODE: Short = 7
        public const val COMMENT_NODE: Short = 8
        public const val DOCUMENT_NODE: Short = 9
        public const val DOCUMENT_TYPE_NODE: Short = 10
        public const val DOCUMENT_FRAGMENT_NODE: Short = 11
        public const val NOTATION_NODE: Short = 12

        public const val DOCUMENT_POSITION_DISCONNECTED: Short = 0x01
        public const val DOCUMENT_POSITION_PRECEDING: Short = 0x02
        public const val DOCUMENT_POSITION_FOLLOWING: Short = 0x04
        public const val DOCUMENT_POSITION_CONTAINS: Short = 0x08
        public const val DOCUMENT_POSITION_CONTAINED_BY: Short = 0x10
        public const val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: Short = 0x20
    }
}

public data class GetRootNodeOptions(
    val composed: Boolean = false
)

/**
 * The CharacterData abstract interface represents a Node object that contains characters.
 * It is implemented by Text and Comment nodes.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-characterdata
 */
public interface CharacterData : Node {
    public override val parentElement: Element?
        get() = parentNode as? Element
    public var data: String
    public val length: Int

    public fun substringData(offset: Int, count: Int): String
    public fun appendData(data: String)
    public fun insertData(offset: Int, data: String)
    public fun deleteData(offset: Int, count: Int)
    public fun replaceData(offset: Int, count: Int, data: String)
}
