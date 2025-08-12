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
 * WHATWG-compliant DocumentType.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-documenttype
 */
public data class DocumentType(
    public val name: String,
    public val publicId: String = "",
    public val systemId: String = ""
) : Node() {
    
    override val nodeType: Short = DOCUMENT_TYPE_NODE
    override val nodeName: String = name
    override val nodeValue: String? = null
    override var textContent: String?
        get() = null
        set(_) { /* Do nothing - DocumentType textContent is not settable per WHATWG spec */ }
    override val ownerDocument: Document? get() = generateSequence(parentNode) { it.parentNode }.filterIsInstance<Document>().firstOrNull()
    
    override fun cloneNode(deep: Boolean): DocumentType {
        return DocumentType(name, publicId, systemId)
    }
}