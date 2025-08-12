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

package com.theakram.helium.dom

/**
 * WHATWG-compliant Node interface.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-node
 */
public abstract class Node : EventTarget(), NodeWithChildren {
    
    public abstract val nodeType: Short
    public abstract val nodeName: String
    
    public var parentNode: Node? = null
        internal set
        
    public val parentElement: Element?
        get() = parentNode as? Element
    
    override val internalChildNodes: MutableList<Node> = mutableListOf()
    
    public val childNodes: NodeList = ChildNodeList(this)
    
    public val isConnected: Boolean
        get() = getRootNode() is Document
    
    public val firstChild: Node?
        get() = internalChildNodes.firstOrNull()
    
    public val lastChild: Node?
        get() = internalChildNodes.lastOrNull()
    
    public val previousSibling: Node?
        get() = parentNode?.internalChildNodes?.let { siblings ->
            val index = siblings.indexOf(this)
            if (index > 0) siblings[index - 1] else null
        }
    
    public val nextSibling: Node?
        get() = parentNode?.internalChildNodes?.let { siblings ->
            val index = siblings.indexOf(this)
            if (index != -1 && index < siblings.size - 1) siblings[index + 1] else null
        }
    
    public abstract val nodeValue: String?
    public abstract var textContent: String?
    public abstract val ownerDocument: Document?
    
    public val baseURI: String get() = ownerDocument?.documentURI ?: "about:blank"
    
    public fun getRootNode(options: GetRootNodeOptions? = null): Node {
        var root: Node = this
        while (root.parentNode != null) {
            root = root.parentNode!!
        }
        return root
    }
    
    public abstract fun cloneNode(deep: Boolean = false): Node
    
    public fun hasChildNodes(): Boolean = internalChildNodes.isNotEmpty()
    
    public fun contains(other: Node?): Boolean {
        if (other == null || other === this) return other === this
        return generateSequence(other) { it.parentNode }.any { it === this }
    }
    
    public fun appendChild(node: Node): Node {
        insertBefore(node, null)
        return node
    }
    
    public fun insertBefore(node: Node, child: Node?): Node {
        return preInsertionValidation(node, this, child)
    }
    
    // WHATWG-compliant pre-insertion validation algorithm
    private fun preInsertionValidation(node: Node, parent: Node, child: Node?): Node {
        // 1. Ensure viable parent
        when (parent.nodeType) {
            DOCUMENT_NODE, DOCUMENT_FRAGMENT_NODE, ELEMENT_NODE -> {
                // Valid parent types
            }
            else -> throw DOMException("Cannot insert into this node type", "HierarchyRequestError")
        }
        
        // 2. Ensure node is not an ancestor of parent
        if (node.contains(parent)) {
            throw DOMException("Cannot insert node into its descendant", "HierarchyRequestError")
        }
        
        // 3. Ensure child is a child of parent if specified
        if (child != null && child.parentNode !== parent) {
            throw DOMException("Reference child is not a child of parent", "NotFoundError")
        }
        
        // 4. Type-specific validation
        when (node.nodeType) {
            DOCUMENT_TYPE_NODE -> {
                if (parent.nodeType != DOCUMENT_NODE) {
                    throw DOMException("DocumentType can only be inserted into Document", "HierarchyRequestError")
                }
            }
            ELEMENT_NODE -> {
                if (parent.nodeType == DOCUMENT_NODE) {
                    // Document can have at most one element child
                    val existingElementChild = parent.internalChildNodes.find { it.nodeType == ELEMENT_NODE }
                    if (existingElementChild != null && existingElementChild !== node) {
                        throw DOMException("Document can have at most one element child", "HierarchyRequestError")
                    }
                }
            }
        }
        
        // 5. Perform insertion
        return insertIntoParent(node, parent, child)
    }
    
    // WHATWG-compliant insertion algorithm
    private fun insertIntoParent(node: Node, parent: Node, child: Node?): Node {
        // Remove from current parent if any
        node.parentNode?.let { currentParent ->
            val index = currentParent.internalChildNodes.indexOf(node)
            if (index != -1) {
                currentParent.internalChildNodes.removeAt(index)
                node.parentNode = null
            }
        }
        
        // Insert into new parent
        node.parentNode = parent
        if (child == null) {
            parent.internalChildNodes.add(node)
        } else {
            val index = parent.internalChildNodes.indexOf(child)
            parent.internalChildNodes.add(index, node)
        }
        
        return node
    }
    
    public fun removeChild(child: Node): Node {
        require(child.parentNode === this) {
            "Node is not a child of this node"
        }
        
        internalChildNodes.remove(child)
        child.parentNode = null
        return child
    }
    
    public fun replaceChild(newChild: Node, oldChild: Node): Node {
        // WHATWG-compliant replacement algorithm
        
        // 1. First do hierarchy validation (before checking if oldChild exists)
        // This ensures HierarchyRequestError is thrown before NotFoundError per spec
        if (newChild.contains(this)) {
            throw DOMException("Cannot replace with an ancestor", "HierarchyRequestError")
        }
        
        // 2. Validate that oldChild is a child of this node
        if (oldChild.parentNode !== this) {
            throw DOMException("Old child is not a child of this node", "NotFoundError")
        }
        
        // 3. Use pre-insertion validation with oldChild's next sibling as reference
        val referenceChild = oldChild.nextSibling
        
        // 4. Remove the old child first
        val index = internalChildNodes.indexOf(oldChild)
        internalChildNodes.removeAt(index)
        oldChild.parentNode = null
        
        // 5. Insert new child at the same position
        preInsertionValidation(newChild, this, referenceChild)
        
        return oldChild
    }
    
    public fun compareDocumentPosition(other: Node): Short {
        if (this === other) return 0
        
        val thisRoot = generateSequence(this) { it.parentNode }.last()
        val otherRoot = generateSequence(other) { it.parentNode }.last()
        
        if (thisRoot !== otherRoot) {
            return (DOCUMENT_POSITION_DISCONNECTED.toInt() or DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC.toInt()).toShort()
        }
        
        if (contains(other)) {
            return (DOCUMENT_POSITION_CONTAINED_BY.toInt() or DOCUMENT_POSITION_FOLLOWING.toInt()).toShort()
        }
        
        if (other.contains(this)) {
            return (DOCUMENT_POSITION_CONTAINS.toInt() or DOCUMENT_POSITION_PRECEDING.toInt()).toShort()
        }
        
        val thisAncestors = generateSequence(this) { it.parentNode }.toList().reversed()
        val otherAncestors = generateSequence(other) { it.parentNode }.toList().reversed()
        
        var commonAncestor: Node? = null
        for (i in 0 until minOf(thisAncestors.size, otherAncestors.size)) {
            if (thisAncestors[i] === otherAncestors[i]) {
                commonAncestor = thisAncestors[i]
            } else {
                break
            }
        }
        
        if (commonAncestor != null) {
            val thisChild = thisAncestors.getOrNull(thisAncestors.indexOf(commonAncestor) + 1)
            val otherChild = otherAncestors.getOrNull(otherAncestors.indexOf(commonAncestor) + 1)
            
            if (thisChild != null && otherChild != null) {
                val thisIndex = commonAncestor.internalChildNodes.indexOf(thisChild)
                val otherIndex = commonAncestor.internalChildNodes.indexOf(otherChild)
                
                return if (thisIndex < otherIndex) {
                    DOCUMENT_POSITION_FOLLOWING
                } else {
                    DOCUMENT_POSITION_PRECEDING
                }
            }
        }
        
        return (DOCUMENT_POSITION_DISCONNECTED.toInt() or DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC.toInt()).toShort()
    }
    
    public fun isEqualNode(other: Node?): Boolean {
        if (other == null) return false
        if (this === other) return true
        
        if (nodeType != other.nodeType || 
            nodeName != other.nodeName ||
            nodeValue != other.nodeValue) {
            return false
        }
        
        if (internalChildNodes.size != other.internalChildNodes.size) {
            return false
        }
        
        return internalChildNodes.zip(other.internalChildNodes).all { (child, otherChild) ->
            child.isEqualNode(otherChild)
        }
    }
    
    public fun isSameNode(other: Node?): Boolean = this === other
    
    public fun normalize() {
        var i = 0
        while (i < internalChildNodes.size) {
            val child = internalChildNodes[i]
            
            if (child is Text) {
                if (child.data.isEmpty()) {
                    internalChildNodes.removeAt(i)
                    child.parentNode = null
                    continue
                }
                
                while (i + 1 < internalChildNodes.size && internalChildNodes[i + 1] is Text) {
                    val nextText = internalChildNodes[i + 1] as Text
                    child.data += nextText.data
                    internalChildNodes.removeAt(i + 1)
                    nextText.parentNode = null
                }
            } else {
                child.normalize()
            }
            
            i++
        }
    }
    
    public open fun lookupPrefix(namespaceURI: String?): String? {
        if (namespaceURI == null || namespaceURI.isEmpty()) return null
        
        var current: Node? = this
        while (current != null) {
            if (current is Element) {
                return current.lookupPrefix(namespaceURI)
            }
            current = current.parentNode
        }
        return null
    }
    
    public open fun lookupNamespaceURI(prefix: String?): String? {
        var current: Node? = this
        while (current != null) {
            if (current is Element) {
                return current.lookupNamespaceURI(prefix)
            }
            current = current.parentNode
        }
        return null
    }
    
    public fun isDefaultNamespace(namespaceURI: String?): Boolean {
        return lookupNamespaceURI(null) == namespaceURI
    }
    
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

        // DocumentPosition constants
        public const val DOCUMENT_POSITION_DISCONNECTED: Short = 1
        public const val DOCUMENT_POSITION_PRECEDING: Short = 2
        public const val DOCUMENT_POSITION_FOLLOWING: Short = 4
        public const val DOCUMENT_POSITION_CONTAINS: Short = 8
        public const val DOCUMENT_POSITION_CONTAINED_BY: Short = 16
        public const val DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC: Short = 32
    }
}

/**
 * Options for getRootNode method.
 */
public data class GetRootNodeOptions(
    val composed: Boolean = false
)