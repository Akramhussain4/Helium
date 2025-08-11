package com.theakram.helium.dom

/**
 * Element is the most common kind of node. It corresponds to an HTML tag.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-element
 */
public class Element(
    public val tagName: String,
    public override val ownerDocument: Document
) : Node {

    public override val nodeType: Short = Node.ELEMENT_NODE
    public override val nodeName: String
        get() = tagName
    public override val baseURI: String = ""
    public override val isConnected: Boolean
        get() = parentNode != null
    public override var parentNode: Node? = null
        internal set
    public override val parentElement: Element?
        get() = parentNode as? Element
    private val _childNodes = mutableListOf<Node>()
    public override val childNodes: List<Node> get() = _childNodes
    public override val previousSibling: Node?
        get() = parentNode?.childNodes?.let {
            val index = it.indexOf(this)
            if (index > 0) it[index - 1] else null
        }

    public override val nextSibling: Node?
        get() = parentNode?.childNodes?.let {
            val index = it.indexOf(this)
            if (index != -1 && index < it.size - 1) it[index + 1] else null
        }
    public override var nodeValue: String?
        get() = null
        set(_) {}
    public override var textContent: String?
        get() = childNodes.joinToString("") { it.textContent ?: "" }
        set(value) {
            // TODO: Set text content
        }

    public val namespaceURI: String? = null
    public val prefix: String? = null
    public val localName: String
        get() = tagName
    public var id: String
        get() = getAttribute("id") ?: ""
        set(value) {
            setAttribute("id", value)
        }
    public var className: String
        get() = getAttribute("class") ?: ""
        set(value) {
            setAttribute("class", value)
        }
    public val classList: DOMTokenList = DOMTokenList(this)
    public var slot: String
        get() = getAttribute("slot") ?: ""
        set(value) {
            setAttribute("slot", value)
        }

    public val attributes: MutableMap<String, String> = mutableMapOf()

    override fun getRootNode(options: GetRootNodeOptions?): Node {
        TODO("Not yet implemented")
    }

    override fun hasChildNodes(): Boolean = _childNodes.isNotEmpty()

    override fun normalize() {
        // TODO: Implement this
    }

    override fun cloneNode(deep: Boolean): Node {
        val newElement = Element(tagName, ownerDocument)
        // Copy attributes
        this.attributes.forEach { (key, value) ->
            newElement.setAttribute(key, value)
        }

        if (deep) {
            // Recursively clone children
            this.childNodes.forEach { child ->
                newElement.appendChild(child.cloneNode(true))
            }
        }

        return newElement
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

    public fun hasAttributes(): Boolean = attributes.isNotEmpty()

    public fun getAttributeNames(): List<String> = attributes.keys.toList()

    public fun getAttribute(qualifiedName: String): String? = attributes[qualifiedName.lowercase()]

    public fun getAttributeNS(namespace: String?, localName: String): String? {
        TODO("Not yet implemented")
    }

    public fun setAttribute(qualifiedName: String, value: String) {
        attributes[qualifiedName.lowercase()] = value
    }

    public fun setAttributeNS(namespace: String?, qualifiedName: String, value: String) {
        TODO("Not yet implemented")
    }

    public fun removeAttribute(qualifiedName: String) {
        attributes.remove(qualifiedName.lowercase())
    }

    public fun removeAttributeNS(namespace: String?, localName: String) {
        TODO("Not yet implemented")
    }

    public fun toggleAttribute(qualifiedName: String, force: Boolean? = null): Boolean {
        TODO("Not yet implemented")
    }

    public fun hasAttribute(qualifiedName: String): Boolean = attributes.containsKey(qualifiedName.lowercase())

    public fun hasAttributeNS(namespace: String?, localName: String): Boolean {
        TODO("Not yet implemented")
    }

    public fun getAttributeNode(qualifiedName: String): Attr? {
        TODO("Not yet implemented")
    }

    public fun getAttributeNodeNS(namespace: String?, localName: String): Attr? {
        TODO("Not yet implemented")
    }

    public fun setAttributeNode(attr: Attr): Attr? {
        TODO("Not yet implemented")
    }

    public fun setAttributeNodeNS(attr: Attr): Attr? {
        TODO("Not yet implemented")
    }

    public fun removeAttributeNode(attr: Attr): Attr {
        TODO("Not yet implemented")
    }

    public fun attachShadow(init: ShadowRootInit): ShadowRoot {
        TODO("Not yet implemented")
    }

    public val shadowRoot: ShadowRoot? = null

    public fun closest(selectors: String): Element? {
        TODO("Not yet implemented")
    }

    public fun matches(selectors: String): Boolean {
        TODO("Not yet implemented")
    }

    public fun webkitMatchesSelector(selectors: String): Boolean = matches(selectors)

    public fun getElementsByTagName(qualifiedName: String): List<Element> {
        TODO("Not yet implemented")
    }

    public fun getElementsByTagNameNS(namespace: String?, localName: String): List<Element> {
        TODO("Not yet implemented")
    }

    public fun getElementsByClassName(classNames: String): List<Element> {
        TODO("Not yet implemented")
    }

    public fun insertAdjacentElement(where: String, element: Element): Element? {
        TODO("Not yet implemented")
    }

    public fun insertAdjacentText(where: String, data: String) {
        TODO("Not yet implemented")
    }

    override fun insertBefore(newNode: Node, referenceNode: Node?): Node {
        // If referenceNode is null, append to the end.
        if (referenceNode == null) {
            return appendChild(newNode)
        }

        val index = _childNodes.indexOf(referenceNode)
        if (index == -1) {
            throw DOMException(
                message = "Reference node not found in child list.",
                name = "NotFoundError"
            )
        }

        // If the node already has a parent, it must be removed first.
        (newNode as? Element)?.parentNode?.removeChild(newNode)

        _childNodes.add(index, newNode)
        when (newNode) {
            is Element -> newNode.parentNode = this
            is Text -> newNode.parentNode = this
            is Comment -> newNode.parentNode = this
            else -> {}
        }
        return newNode
    }

    override fun appendChild(node: Node): Node {
        // If the node already has a parent, it must be removed first.
        (node as? Element)?.parentNode?.removeChild(node)

        _childNodes.add(node)
        when (node) {
            is Element -> node.parentNode = this
            is Text -> node.parentNode = this
            is Comment -> node.parentNode = this
            else -> {}
        }
        return node
    }

    override fun replaceChild(node: Node, child: Node): Node {
        TODO("Not yet implemented")
    }

    override fun removeChild(node: Node): Node {
        if (_childNodes.remove(node)) {
            when (node) {
                is Element -> node.parentNode = null
                is Text -> node.parentNode = null
                is Comment -> node.parentNode = null
                else -> {}
            }
            return node
        } else {
            throw DOMException(
                message = "Node not found in child list.",
                name = "NotFoundError"
            )
        }
    }
}

public class DOMTokenList(private val element: Element) {
    public fun item(index: Int): String? {
        TODO("Not yet implemented")
    }

    public fun contains(token: String): Boolean {
        TODO("Not yet implemented")
    }

    public fun add(vararg tokens: String) {
        TODO("Not yet implemented")
    }

    public fun remove(vararg tokens: String) {
        TODO("Not yet implemented")
    }

    public fun toggle(token: String, force: Boolean? = null): Boolean {
        TODO("Not yet implemented")
    }

    public fun replace(oldToken: String, newToken: String): Boolean {
        TODO("Not yet implemented")
    }

    public fun supports(token: String): Boolean {
        TODO("Not yet implemented")
    }
}

public class ShadowRootInit
public class ShadowRoot