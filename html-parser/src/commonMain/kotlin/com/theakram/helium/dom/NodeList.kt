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
 * WHATWG-compliant NodeList interface.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-nodelist
 */
public interface NodeList : Iterable<Node> {
    public val length: Int
    
    public fun item(index: Int): Node?
    
    override fun iterator(): Iterator<Node> = object : Iterator<Node> {
        private var index = 0
        
        override fun hasNext(): Boolean = index < length
        
        override fun next(): Node = item(index++) ?: throw NoSuchElementException()
    }
}

/**
 * Static NodeList implementation backed by a list.
 */
public class StaticNodeList(private val nodes: List<Node>) : NodeList {
    override val length: Int get() = nodes.size
    
    override fun item(index: Int): Node? = nodes.getOrNull(index)
}

/**
 * Live NodeList implementation that reflects changes to the DOM tree.
 */
public class LiveNodeList(
    private val root: Node,
    private val filter: (Node) -> Boolean
) : NodeList {
    
    private fun collectNodes(): List<Node> {
        val result = mutableListOf<Node>()
        collectNodesRecursive(root, result)
        return result
    }
    
    private fun collectNodesRecursive(node: Node, result: MutableList<Node>) {
        if (filter(node)) {
            result.add(node)
        }
        
        node.childNodes.forEach { child ->
            collectNodesRecursive(child, result)
        }
    }
    
    override val length: Int get() = collectNodes().size
    
    override fun item(index: Int): Node? = collectNodes().getOrNull(index)
}