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
 * WHATWG-compliant Document.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-document
 */
public class Document : Node() {
    
    override val nodeType: Short = DOCUMENT_NODE
    override val nodeName: String = "#document"
    override val nodeValue: String? = null
    override var textContent: String?
        get() = null
        set(_) { /* Do nothing - Document textContent is not settable per WHATWG spec */ }
    override val ownerDocument: Document? = null
    
    public var documentURI: String = "about:blank"
    public val URL: String get() = documentURI
    public var contentType: String = "text/html"
    public var characterSet: String = "UTF-8"
    public var domain: String = ""
    public var referrer: String = ""
    public var cookie: String = ""
    public var readyState: String = "loading"
    
    public val doctype: DocumentType?
        get() = internalChildNodes.filterIsInstance<DocumentType>().firstOrNull()
    
    public val documentElement: Element?
        get() = internalChildNodes.filterIsInstance<Element>().firstOrNull()
        
    public val implementation: DOMImplementation by lazy { DOMImplementation(this) }
    
    public val head: Element?
        get() = documentElement?.childNodes?.filterIsInstance<Element>()?.find { it.localName == "head" }
    
    public val body: Element?
        get() = documentElement?.childNodes?.filterIsInstance<Element>()?.find { it.localName == "body" }
    
    public var title: String
        get() = head?.childNodes?.filterIsInstance<Element>()?.find { it.localName == "title" }?.textContent ?: ""
        set(value) {
            val titleElement = head?.childNodes?.filterIsInstance<Element>()?.find { it.localName == "title" }
            if (titleElement != null) {
                titleElement.childNodes.toList().forEach { titleElement.removeChild(it) }
                if (value.isNotEmpty()) {
                    titleElement.appendChild(createTextNode(value))
                }
            }
        }
    
    // Factory methods
    
    public fun createElement(localName: String): Element {
        validateLocalName(localName)
        return if (contentType == "text/html") {
            Element(localName.lowercase(), Element.HTML_NAMESPACE)
        } else {
            Element(localName)
        }
    }
    
    public fun createElementNS(namespace: String?, qualifiedName: String): Element {
        validateQualifiedName(qualifiedName)
        val colonIndex = qualifiedName.indexOf(':')
        val localName = if (colonIndex != -1) qualifiedName.substring(colonIndex + 1) else qualifiedName
        return Element(localName, namespace)
    }
    
    public fun createTextNode(data: String): Text {
        return Text(data)
    }
    
    public fun createComment(data: String): Comment {
        return Comment(data)
    }
    
    public fun createProcessingInstruction(target: String, data: String): ProcessingInstruction {
        return ProcessingInstruction(target, data)
    }
    
    public fun createCDATASection(data: String): CDATASection {
        return CDATASection(data)
    }
    
    public fun createDocumentFragment(): DocumentFragment {
        return DocumentFragment()
    }
    
    public fun createDocumentType(qualifiedName: String, publicId: String = "", systemId: String = ""): DocumentType {
        return DocumentType(qualifiedName, publicId, systemId)
    }
    
    public fun createAttribute(name: String): Attr {
        validateLocalName(name)
        return Attr(name, "")
    }
    
    public fun createAttributeNS(namespaceURI: String?, qualifiedName: String): Attr {
        validateQualifiedName(qualifiedName)
        return Attr(qualifiedName, "", namespaceURI)
    }
    
    public fun adoptNode(node: Node): Node {
        if (node.ownerDocument === this) return node
        
        // Remove from current document
        node.parentNode?.removeChild(node)
        
        // Recursively adopt all descendants
        if (node is Element) {
            node.attributes.values().forEach { attr ->
                attr.ownerElement = node
            }
        }
        
        node.childNodes.forEach { child ->
            adoptNode(child)
        }
        
        return node
    }
    
    public fun importNode(node: Node, deep: Boolean = false): Node {
        return node.cloneNode(deep)
    }
    
    // Query methods
    
    public fun getElementById(elementId: String): Element? {
        return findElementById(this, elementId)
    }
    
    private fun findElementById(node: Node, id: String): Element? {
        if (node is Element && node.id == id) {
            return node
        }
        
        for (child in node.childNodes) {
            val result = findElementById(child, id)
            if (result != null) return result
        }
        
        return null
    }
    
    public fun querySelector(selectors: String): Element? {
        return try {
            cssSelector.querySelector(this, selectors)
        } catch (e: Exception) {
            throw DOMException("Invalid selector: $selectors", "SyntaxError")
        }
    }
    
    public fun querySelectorAll(selectors: String): NodeList {
        return try {
            val results = cssSelector.querySelectorAll(this, selectors)
            StaticNodeList(results)
        } catch (e: Exception) {
            throw DOMException("Invalid selector: $selectors", "SyntaxError")
        }
    }
    
    // Query methods
    
    public fun getElementsByTagName(tagName: String): HTMLCollection {
        return LiveHTMLCollection(this) { element ->
            tagName == "*" || element.tagName.equals(tagName, ignoreCase = true)
        }
    }
    
    public fun getElementsByClassName(className: String): HTMLCollection {
        return LiveHTMLCollection(this) { element ->
            element.classList.contains(className)
        }
    }
    
    override fun cloneNode(deep: Boolean): Document {
        val clone = Document()
        clone.documentURI = this.documentURI
        clone.contentType = this.contentType
        clone.characterSet = this.characterSet
        
        if (deep) {
            childNodes.forEach { child ->
                clone.appendChild(child.cloneNode(true))
            }
        }
        
        return clone
    }
    
    private fun validateLocalName(localName: String) {
        require(localName.isNotEmpty() && isValidName(localName)) {
            "Invalid local name: $localName"
        }
    }
    
    private fun validateQualifiedName(qualifiedName: String) {
        require(qualifiedName.isNotEmpty() && isValidName(qualifiedName)) {
            "Invalid qualified name: $qualifiedName"
        }
    }
    
    private fun isValidName(name: String): Boolean {
        return name.isNotEmpty() && name.all { it.isLetterOrDigit() || it in "-_:." }
    }
}