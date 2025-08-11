package com.theakram.helium.dom

/**
 * A Text node in the DOM, representing the textual content of an element.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-text
 */
public class Text(public override var data: String) : CharacterData {

    public override val nodeType: Short = Node.TEXT_NODE
    public override val nodeName: String = "#text"
    public override val baseURI: String = ""
    public override val isConnected: Boolean
        get() = parentNode != null
    public override var parentNode: Node? = null
        internal set
    public override val parentElement: Element?
        get() = parentNode as? Element
    public override var ownerDocument: Document? = null
        internal set
    public override val childNodes: List<Node> = emptyList()
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
        get() = data
        set(value) {
            data = value ?: ""
        }
    public override var textContent: String?
        get() = data
        set(value) {
            data = value ?: ""
        }
    public override val length: Int
        get() = data.length

    override fun getRootNode(options: GetRootNodeOptions?): Node {
        TODO("Not yet implemented")
    }

    override fun hasChildNodes(): Boolean = false

    override fun normalize() {}

    override fun cloneNode(deep: Boolean): Node {
        return Text(data).also { it.ownerDocument = this.ownerDocument }
    }

    override fun isEqualNode(other: Node?): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSameNode(other: Node?): Boolean = other === this

    override fun compareDocumentPosition(other: Node): UShort {
        TODO("Not yet implemented")
    }

    override fun contains(other: Node?): Boolean = false

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
        throw DOMException("Nodes of type 'Text' can't have children.", "HierarchyRequestError")
    }

    override fun appendChild(node: Node): Node {
        throw DOMException("Nodes of type 'Text' can't have children.", "HierarchyRequestError")
    }

    override fun replaceChild(node: Node, child: Node): Node {
        throw DOMException("Nodes of type 'Text' don't have children.", "NotFoundError")
    }

    override fun removeChild(node: Node): Node {
        throw DOMException("Nodes of type 'Text' don't have children.", "NotFoundError")
    }

    override fun substringData(offset: Int, count: Int): String {
        val length = this.length
        if (offset !in 0..length) {
            throw DOMException("The offset must be between 0 and the length of the string.", "IndexSizeError")
        }
        val end = minOf(length, offset + count)
        return data.substring(offset, end)
    }

    override fun appendData(data: String) {
        replaceData(this.length, 0, data)
    }

    override fun insertData(offset: Int, data: String) {
        replaceData(offset, 0, data)
    }

    override fun deleteData(offset: Int, count: Int) {
        replaceData(offset, count, "")
    }

    override fun replaceData(offset: Int, count: Int, data: String) {
        val length = this.length
        if (offset !in 0..length) {
            throw DOMException("The offset must be between 0 and the length of the string.", "IndexSizeError")
        }
        val effectiveCount = minOf(count, length - offset)

        val builder = StringBuilder(this.data)
        builder.replace(offset, offset + effectiveCount, data)
        this.data = builder.toString()
        // TODO: Queue mutation record
    }

    public fun splitText(offset: Int): Text {
        val length = data.length
        if (offset !in 0..length) {
            throw DOMException("The offset must be between 0 and the length of the string.", "IndexSizeError")
        }
        val newData = substringData(offset, length - offset)
        val newNode = Text(newData).also { it.ownerDocument = this.ownerDocument }
        this.parentNode?.insertBefore(newNode, this.nextSibling)
        deleteData(offset, length - offset)
        return newNode
    }

    public val wholeText: String
        get() {
            val buffer = StringBuilder()
            var current: Node? = this
            while (current is Text) {
                buffer.insert(0, current.data)
                current = current.previousSibling
            }
            current = this.nextSibling
            while (current is Text) {
                buffer.append(current.data)
                current = current.nextSibling
            }
            return buffer.toString()
        }
}
