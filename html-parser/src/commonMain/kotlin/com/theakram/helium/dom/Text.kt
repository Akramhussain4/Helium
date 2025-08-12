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
 * WHATWG-compliant Text node.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-text
 */
public open class Text(data: String = "") : CharacterData(data) {
    
    override val nodeType: Short = TEXT_NODE
    override val nodeName: String = "#text"
    override val ownerDocument: Document? get() = generateSequence(parentNode) { it.parentNode }.filterIsInstance<Document>().firstOrNull()
    
    override fun cloneNode(deep: Boolean): Text {
        return Text(data)
    }
    
    public fun splitText(offset: Int): Text {
        require(offset in 0..length) { "Offset out of range: $offset" }
        
        val count = length - offset
        val newData = substringData(offset, count)
        val newNode = Text(newData)
        
        parentNode?.insertBefore(newNode, nextSibling)
        deleteData(offset, count)
        
        return newNode
    }
    
    public val wholeText: String
        get() {
            val result = StringBuilder()
            
            // Collect preceding text nodes
            var current = previousSibling
            val preceding = mutableListOf<String>()
            while (current is Text) {
                preceding.add(0, current.data)
                current = current.previousSibling
            }
            
            preceding.forEach { result.append(it) }
            result.append(data)
            
            // Collect following text nodes
            current = nextSibling
            while (current is Text) {
                result.append(current.data)
                current = current.nextSibling
            }
            
            return result.toString()
        }
}