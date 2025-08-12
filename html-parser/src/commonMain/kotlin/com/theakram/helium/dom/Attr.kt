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
 * WHATWG-compliant Attr interface.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-attr
 */
public class Attr(
    public val name: String,
    public var value: String,
    public val namespaceURI: String? = null
) : Node() {
    
    override val nodeType: Short = ATTRIBUTE_NODE
    override val nodeName: String = name
    override var nodeValue: String?
        get() = value
        set(newValue) { value = newValue ?: "" }
    override var textContent: String?
        get() = value
        set(newValue) { value = newValue ?: "" }
    override val ownerDocument: Document? 
        get() = ownerElement?.ownerDocument
    
    public var ownerElement: Element? = null
        internal set
    
    public val localName: String = if (name.contains(':')) name.substringAfter(':') else name
    public val prefix: String? = if (name.contains(':')) name.substringBefore(':') else null
    
    public val specified: Boolean = true
    
    override fun cloneNode(deep: Boolean): Attr {
        return Attr(name, value, namespaceURI)
    }
}