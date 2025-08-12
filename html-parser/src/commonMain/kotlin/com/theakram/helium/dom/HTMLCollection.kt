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
 * WHATWG-compliant HTMLCollection interface.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-htmlcollection
 */
public interface HTMLCollection : Iterable<Element> {
    public val length: Int
    
    public fun item(index: Int): Element?
    
    public fun namedItem(name: String): Element?
    
    override fun iterator(): Iterator<Element> = object : Iterator<Element> {
        private var index = 0
        
        override fun hasNext(): Boolean = index < length
        
        override fun next(): Element = item(index++) ?: throw NoSuchElementException()
    }
}

/**
 * Live HTMLCollection implementation that reflects changes to the DOM tree.
 */
public class LiveHTMLCollection(
    private val root: Node,
    private val filter: (Element) -> Boolean
) : HTMLCollection {
    
    private fun collectElements(): List<Element> {
        val result = mutableListOf<Element>()
        collectElementsRecursive(root, result)
        return result
    }
    
    private fun collectElementsRecursive(node: Node, result: MutableList<Element>) {
        if (node is Element && filter(node)) {
            result.add(node)
        }
        
        node.childNodes.forEach { child ->
            collectElementsRecursive(child, result)
        }
    }
    
    override val length: Int get() = collectElements().size
    
    override fun item(index: Int): Element? = collectElements().getOrNull(index)
    
    override fun namedItem(name: String): Element? {
        return collectElements().find { element ->
            element.getAttribute("id") == name || element.getAttribute("name") == name
        }
    }
}