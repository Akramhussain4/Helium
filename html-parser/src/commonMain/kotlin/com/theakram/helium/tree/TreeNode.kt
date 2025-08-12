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
 * Simple tree node for HTML5 tree construction phase.
 * 
 * This is a minimal tree structure without DOM API methods - just the data
 * needed for tree construction and serialization. The DOM API layer can be
 * built on top of this later.
 */
public data class TreeNode(
    val type: NodeType,
    val name: String? = null,                    // Tag name for elements, target for PI
    val publicId: String? = null,                // For DOCTYPE nodes
    val systemId: String? = null,                // For DOCTYPE nodes
    var data: String? = null,                    // Text content, comment data, etc.
    val attributes: MutableMap<String, String> = mutableMapOf(),
    val children: MutableList<TreeNode> = mutableListOf(),
    var parent: TreeNode? = null
) {
    
    /**
     * Add a child node and set parent relationship.
     */
    public fun appendChild(child: TreeNode) {
        children.add(child)
        child.parent = this
    }
    
    /**
     * Insert a child at specific position.
     */
    public fun insertBefore(newChild: TreeNode, referenceChild: TreeNode?) {
        if (referenceChild == null) {
            appendChild(newChild)
        } else {
            val index = children.indexOf(referenceChild)
            if (index != -1) {
                children.add(index, newChild)
                newChild.parent = this
            }
        }
    }
    
    /**
     * Remove a child node.
     */
    public fun removeChild(child: TreeNode) {
        if (children.remove(child)) {
            child.parent = null
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
        return children.find { it.type == NodeType.ELEMENT && it.name?.equals(tagName, ignoreCase = true) == true }
    }
    
    /**
     * Get all child elements (excluding text nodes, comments, etc.)
     */
    public val childElements: List<TreeNode>
        get() = children.filter { it.type == NodeType.ELEMENT }
    
    /**
     * Serialize to html5lib tree format for testing.
     * Format: "| " + "  " per nesting level + node representation
     */
    public fun toTreeString(depth: Int = 0): String {
        val indent = "| " + "  ".repeat(depth)
        val nodeRepr = when (type) {
            NodeType.DOCUMENT -> "#document"
            NodeType.DOCUMENT_TYPE -> "<!DOCTYPE $name" +
                (if (!publicId.isNullOrEmpty()) " \"$publicId\"" else "") +
                (if (!systemId.isNullOrEmpty()) " \"$systemId\"" else "") + ">"
            NodeType.ELEMENT -> {
                val tag = "<$name>"
                val attrs = if (attributes.isNotEmpty()) {
                    "\n" + attributes.map { (key, value) ->
                        "$indent  $key=\"$value\""
                    }.joinToString("\n")
                } else ""
                tag + attrs
            }
            NodeType.TEXT -> "\"$data\""
            NodeType.COMMENT -> "<!-- $data -->"
            NodeType.PROCESSING_INSTRUCTION -> "<?$name $data?>"
            NodeType.CDATA_SECTION -> "<![CDATA[$data]]>"
            NodeType.DOCUMENT_FRAGMENT -> "#document-fragment"
        }
        
        val result = StringBuilder()
        result.appendLine(indent + nodeRepr)
        
        // Add children
        for (child in children) {
            result.append(child.toTreeString(depth + 1))
        }
        
        return result.toString()
    }
    
    public companion object {
        /**
         * Create a document node.
         */
        public fun createDocument(): TreeNode {
            return TreeNode(type = NodeType.DOCUMENT)
        }
        
        /**
         * Create an element node.
         */
        public fun createElement(name: String, attributes: Map<String, String> = emptyMap()): TreeNode {
            return TreeNode(
                type = NodeType.ELEMENT,
                name = name,
                attributes = attributes.toMutableMap()
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
        public fun createDoctype(name: String, publicId: String? = null, systemId: String? = null): TreeNode {
            return TreeNode(
                type = NodeType.DOCUMENT_TYPE,
                name = name,
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