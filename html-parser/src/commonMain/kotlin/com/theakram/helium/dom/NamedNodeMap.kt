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
 * WHATWG-compliant NamedNodeMap interface for attribute collections.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-namednodemap
 */
public class NamedNodeMap(private val element: Element) {
    
    private val attributes = mutableMapOf<String, Attr>()
    
    public val length: Int get() = attributes.size
    
    public fun item(index: Int): Attr? {
        return attributes.values.elementAtOrNull(index)
    }
    
    public fun getNamedItem(name: String): Attr? {
        // WHATWG spec: For HTML elements, attribute names are case-insensitive
        val key = if (element.isHTMLElement()) name.lowercase() else name
        return attributes[key]
    }
    
    public fun getNamedItemNS(namespaceURI: String?, localName: String): Attr? {
        return attributes.values.find { 
            it.namespaceURI == namespaceURI && it.localName == localName 
        }
    }
    
    public fun setNamedItem(attr: Attr): Attr? {
        if (attr.ownerElement != null && attr.ownerElement !== element) {
            throw DOMException("Attribute is already in use", "InUseAttributeError")
        }
        
        val key = if (element.isHTMLElement()) attr.name.lowercase() else attr.name
        val old = attributes[key]
        
        attr.ownerElement = element
        attributes[key] = attr
        
        return old
    }
    
    public fun setNamedItemNS(attr: Attr): Attr? {
        if (attr.ownerElement != null && attr.ownerElement !== element) {
            throw DOMException("Attribute is already in use", "InUseAttributeError")
        }
        
        val old = getNamedItemNS(attr.namespaceURI, attr.localName)
        if (old != null) {
            removeNamedItemNS(attr.namespaceURI, attr.localName)
        }
        
        val key = if (element.isHTMLElement()) attr.name.lowercase() else attr.name
        attr.ownerElement = element
        attributes[key] = attr
        
        return old
    }
    
    public fun removeNamedItem(name: String): Attr {
        val key = if (element.isHTMLElement()) name.lowercase() else name
        val attr = attributes[key] ?: throw DOMException("Attribute not found", "NotFoundError")
        
        attributes.remove(key)
        attr.ownerElement = null
        
        return attr
    }
    
    public fun removeNamedItemNS(namespaceURI: String?, localName: String): Attr {
        val attr = getNamedItemNS(namespaceURI, localName) 
            ?: throw DOMException("Attribute not found", "NotFoundError")
        
        val key = if (element.isHTMLElement()) attr.name.lowercase() else attr.name
        attributes.remove(key)
        attr.ownerElement = null
        
        return attr
    }
    
    internal fun clear() {
        attributes.values.forEach { it.ownerElement = null }
        attributes.clear()
    }
    
    internal fun keys(): Set<String> = attributes.keys
    
    internal fun values(): Collection<Attr> = attributes.values
}