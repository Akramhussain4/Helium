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
 * CSS Selector engine for WHATWG-compliant selector matching.
 * This is a basic implementation that handles common selectors.
 * For production use, a full CSS selector parser would be implemented.
 */
public interface CSSSelector {
    
    /**
     * Test if an element matches the given selector.
     */
    public fun matches(element: Element, selector: String): Boolean
    
    /**
     * Find the first matching element within the given root.
     */
    public fun querySelector(root: Node, selector: String): Element?
    
    /**
     * Find all matching elements within the given root.
     */
    public fun querySelectorAll(root: Node, selector: String): List<Element>
}

/**
 * Basic CSS selector implementation.
 * This handles the most common selector types but is not a complete implementation.
 */
internal class BasicCSSSelector : CSSSelector {
    
    override fun matches(element: Element, selector: String): Boolean {
        val trimmedSelector = selector.trim()
        
        return when {
            // Universal selector
            trimmedSelector == "*" -> true
            
            // ID selector
            trimmedSelector.startsWith("#") -> {
                val id = trimmedSelector.substring(1)
                element.id == id
            }
            
            // Class selector
            trimmedSelector.startsWith(".") -> {
                val className = trimmedSelector.substring(1)
                element.classList.contains(className)
            }
            
            // Attribute selector [attr] or [attr=value]
            trimmedSelector.startsWith("[") && trimmedSelector.endsWith("]") -> {
                matchesAttributeSelector(element, trimmedSelector)
            }
            
            // Type selector (element name)
            else -> {
                element.tagName.equals(trimmedSelector, ignoreCase = true)
            }
        }
    }
    
    private fun matchesAttributeSelector(element: Element, selector: String): Boolean {
        val attrContent = selector.substring(1, selector.length - 1).trim()
        
        return when {
            "=" in attrContent -> {
                val (attrName, expectedValue) = attrContent.split("=", limit = 2)
                val actualValue = element.getAttribute(attrName.trim())
                actualValue == expectedValue.trim().removeSurrounding("\"", "'")
            }
            else -> {
                // Just check for attribute existence
                element.hasAttribute(attrContent)
            }
        }
    }
    
    override fun querySelector(root: Node, selector: String): Element? {
        return traverseDepthFirst(root) { node ->
            node is Element && matches(node, selector)
        } as Element?
    }
    
    override fun querySelectorAll(root: Node, selector: String): List<Element> {
        val results = mutableListOf<Element>()
        traverseDepthFirstAll(root) { node ->
            if (node is Element && matches(node, selector)) {
                results.add(node)
            }
        }
        return results
    }
    
    private fun traverseDepthFirst(node: Node, predicate: (Node) -> Boolean): Node? {
        // Check current node first (document order)
        if (predicate(node)) {
            return node
        }
        
        // Then check children
        for (child in node.internalChildNodes) {
            val result = traverseDepthFirst(child, predicate)
            if (result != null) {
                return result
            }
        }
        
        return null
    }
    
    private fun traverseDepthFirstAll(node: Node, action: (Node) -> Unit) {
        // Process current node first (document order)
        action(node)
        
        // Then process children
        for (child in node.internalChildNodes) {
            traverseDepthFirstAll(child, action)
        }
    }
}

/**
 * Global CSS selector engine instance.
 */
internal val cssSelector: CSSSelector = BasicCSSSelector()