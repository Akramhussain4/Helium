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

package com.theakram.helium.treebuilder

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tree.Attribute
import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.NodeType
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.treebuilder.modes.Initial

/**
 * HTML5 Tree Builder implementing the WHATWG tree construction algorithm.
 *
 * Takes tokens from the tokenizer and builds a tree structure following
 * the HTML5 parsing rules with insertion modes and stack management.
 *
 * Spec references:
 * - Tree construction: https://html.spec.whatwg.org/multipage/parsing.html#tree-construction
 * - Stack of open elements: https://html.spec.whatwg.org/multipage/parsing.html#the-stack-of-open-elements
 * - Insertion modes: https://html.spec.whatwg.org/multipage/parsing.html#the-insertion-mode
 */
public class TreeBuilder {
    private var insertionMode: InsertionMode = Initial
    
    // Tree construction state
    public val document: TreeNode = TreeNode.createDocument()
    private val openElements = mutableListOf<TreeNode>()
    private var currentNode: TreeNode = document
    
    // Track head and form elements for special handling
    public var headElement: TreeNode? = null
    public var formElement: TreeNode? = null
    
    /**
     * Process a token according to the current insertion mode.
     */
    internal fun process(token: Token) {
        insertionMode.process(token, this)
    }
    
    /**
     * Switch to a new insertion mode.
     */
    internal fun switch(to: InsertionMode) {
        insertionMode = to
    }
    
    /**
     * Get the current insertion mode.
     */
    internal val currentMode: InsertionMode get() = insertionMode
    
    /**
     * Insert an element into the tree and push it onto the stack.
     */
    public fun insertElement(
        tagName: String,
        namespace: String = Namespaces.HTML,
        attributes: List<Attribute> = emptyList()
    ): TreeNode {
        val element = TreeNode.createElement(
            localName = tagName,
            namespace = namespace,
            attributes = attributes
        )
        currentNode.appendChild(element)
        openElements.add(element)
        currentNode = element
        return element
    }
    
    /**
     * Insert a text node into the current node.
     */
    public fun insertText(data: String) {
        // Merge with previous text node if it exists
        val lastChild = currentNode.lastChild
        if (lastChild?.type == NodeType.TEXT) {
            lastChild.data = (lastChild.data ?: "") + data
        } else {
            val textNode = TreeNode.createText(data)
            currentNode.appendChild(textNode)
        }
    }
    
    /**
     * Insert a comment node into the current node.
     */
    public fun insertComment(data: String) {
        val comment = TreeNode.createComment(data)
        currentNode.appendChild(comment)
    }
    
    /**
     * Insert a DOCTYPE node into the document.
     */
    public fun insertDoctype(name: String, publicId: String? = null, systemId: String? = null) {
        val doctype = TreeNode.createDocumentType(name, publicId, systemId)
        document.appendChild(doctype)
    }
    
    /**
     * Pop the current element from the stack.
     */
    public fun popCurrentElement(): TreeNode? {
        if (openElements.isEmpty()) return null
        
        val popped = openElements.removeLastOrNull()
        currentNode = openElements.lastOrNull() ?: document
        return popped
    }
    
    /**
     * Pop elements until we find the specified tag name.
     */
    public fun popUntil(tagName: String) {
        while (openElements.isNotEmpty()) {
            val element = popCurrentElement()
            if (element?.localName?.equals(tagName, ignoreCase = true) == true) {
                break
            }
        }
    }
    
    /**
     * Check if we have an element with the given tag name in scope.
     */
    public fun hasElementInScope(tagName: String): Boolean {
        return openElements.any { it.localName?.equals(tagName, ignoreCase = true) == true }
    }
    
    /**
     * Get the current element on the stack.
     */
    public val currentElement: TreeNode get() = currentNode
    
    /**
     * Get the stack of open elements.
     */
    public val elementStack: List<TreeNode> get() = openElements.toList()
    
    /**
     * Reset the tree builder to initial state (for testing).
     */
    public fun reset() {
        insertionMode = Initial
        openElements.clear()
        currentNode = document
        document.children.clear()
        headElement = null
        formElement = null
    }
    
    /**
     * Get the tree as html5lib format string for testing.
     */
    public fun toTreeString(): String {
        return document.toTreeString()
    }
}