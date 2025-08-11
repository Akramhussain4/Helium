package com.theakram.helium.dom

/**
 * The Document interface represents the entire HTML or XML document. Conceptually, it is the root of the document tree,
 * and provides the primary access to the document's data.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-document
 */

public class Document : Node {

    private val _childNodes = mutableListOf<Node>()

    override val nodeType: Short = Node.DOCUMENT_NODE
    override val nodeName: String = "#document"
    override val baseURI: String = ""
    override val isConnected: Boolean
        get() = true
    override val ownerDocument: Document = this
    override val parentNode: Node? = null
    override val parentElement: Element? = null
    override val childNodes: List<Node>
        get() = _childNodes
    override val previousSibling: Node? = null
    override val nextSibling: Node? = null
    override var nodeValue: String?
        get() = null
        set(_) {}
    override var textContent: String?
        get() = null
        set(_) {}

    public val URL: String = ""
    public val documentURI: String = ""
    public val compatMode: String = ""
    public val characterSet: String = ""
    public val charset: String = ""
    public val inputEncoding: String = ""
    public val contentType: String = ""
    public val doctype: DocumentType? = null
    public val documentElement: Element?
        get() = childNodes.filterIsInstance<Element>().firstOrNull()
    public var title: String? = null
    public var dir: String? = null
    public var body: Element? = null
    public val head: Element?
        get() = documentElement?.childNodes?.filterIsInstance<Element>()
            ?.firstOrNull { it.tagName.equals("head", ignoreCase = true) }
    public val images: List<Element> = emptyList()
    public val embeds: List<Element> = emptyList()
    public val plugins: List<Element> = emptyList()
    public val links: List<Element> = emptyList()
    public val forms: List<Element> = emptyList()
    public val scripts: List<Element> = emptyList()
    public val readyState: String = ""
    public val hidden: Boolean = false
    public val visibilityState: String = ""

    override fun getRootNode(options: GetRootNodeOptions?): Node = this

    override fun hasChildNodes(): Boolean = _childNodes.isNotEmpty()

    override fun normalize() {
        // TODO: Implement this
    }

    override fun cloneNode(deep: Boolean): Node {
        TODO("Not yet implemented")
    }

    override fun isEqualNode(other: Node?): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSameNode(other: Node?): Boolean = other === this

    override fun compareDocumentPosition(other: Node): UShort {
        TODO("Not yet implemented")
    }

    override fun contains(other: Node?): Boolean {
        TODO("Not yet implemented")
    }

    override fun lookupPrefix(namespace: String?): String? {
        TODO("Not yet implemented")
    }

    override fun lookupNamespaceURI(prefix: String?): String? {
        TODO("Not yet implemented")
    }

    override fun isDefaultNamespace(namespace: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun insertBefore(newNode: Node, referenceNode: Node?): Node {
        TODO("Not yet implemented")
    }

    override fun appendChild(node: Node): Node {
        _childNodes.add(node)
        return node
    }

    override fun replaceChild(node: Node, child: Node): Node {
        TODO("Not yet implemented")
    }

    override fun removeChild(node: Node): Node {
        TODO("Not yet implemented")
    }

    public fun getElementsByTagName(qualifiedName: String): List<Element> {
        TODO("Not yet implemented")
    }

    public fun getElementsByTagNameNS(namespace: String?, localName: String): List<Element> {
        TODO("Not yet implemented")
    }

    public fun getElementsByClassName(classNames: String): List<Element> {
        TODO("Not yet implemented")
    }

    public fun createElement(localName: String, options: ElementCreationOptions? = null): Element {
        return Element(localName, this)
    }

    public fun createElementNS(
        namespace: String?,
        qualifiedName: String,
        options: ElementCreationOptions? = null
    ): Element {
        TODO("Not yet implemented")
    }

    public fun createDocumentFragment(): DocumentFragment {
        TODO("Not yet implemented")
    }

    public fun createTextNode(data: String): Text {
        return Text(data).also { it.ownerDocument = this }
    }

    public fun createCDATASection(data: String): CDATASection {
        TODO("Not yet implemented")
    }

    public fun createComment(data: String): Comment {
        return Comment(data).also { it.ownerDocument = this }
    }

    public fun createProcessingInstruction(target: String, data: String): ProcessingInstruction {
        TODO("Not yet implemented")
    }

    public fun importNode(node: Node, deep: Boolean = false): Node {
        TODO("Not yet implemented")
    }

    public fun adoptNode(node: Node): Node {
        TODO("Not yet implemented")
    }

    public fun createAttribute(localName: String): Attr {
        TODO("Not yet implemented")
    }

    public fun createAttributeNS(namespace: String?, qualifiedName: String): Attr {
        TODO("Not yet implemented")
    }

    public fun createEvent(eventInterface: String): Event {
        TODO("Not yet implemented")
    }

    public fun createRange(): Range {
        TODO("Not yet implemented")
    }

    public fun createNodeIterator(root: Node, whatToShow: Long = 0xFFFFFFFF, filter: NodeFilter? = null): NodeIterator {
        TODO("Not yet implemented")
    }

    public fun createTreeWalker(root: Node, whatToShow: Long = 0xFFFFFFFF, filter: NodeFilter? = null): TreeWalker {
        TODO("Not yet implemented")
    }

    public fun getElementsByName(elementName: String): List<Element> {
        TODO("Not yet implemented")
    }

    public fun open(url: String = "about:blank", name: String = "", features: String = "") {
        TODO("Not yet implemented")
    }

    public fun close() {
        TODO("Not yet implemented")
    }

    public fun write(vararg text: String) {
        TODO("Not yet implemented")
    }

    public fun writeln(vararg text: String) {
        TODO("Not yet implemented")
    }

    public fun hasFocus(): Boolean {
        TODO("Not yet implemented")
    }

    public fun execCommand(commandId: String, showUI: Boolean = false, value: String = ""): Boolean {
        TODO("Not yet implemented")
    }

    public fun queryCommandEnabled(commandId: String): Boolean {
        TODO("Not yet implemented")
    }

    public fun queryCommandIndeterm(commandId: String): Boolean {
        TODO("Not yet implemented")
    }

    public fun queryCommandState(commandId: String): Boolean {
        TODO("Not yet implemented")
    }

    public fun queryCommandSupported(commandId: String): Boolean {
        TODO("Not yet implemented")
    }
}

public class DocumentType
public class ElementCreationOptions
public class DocumentFragment
public class CDATASection
public class ProcessingInstruction
public class Attr
public class Event
public class Range
public class NodeFilter
public class NodeIterator
public class TreeWalker

