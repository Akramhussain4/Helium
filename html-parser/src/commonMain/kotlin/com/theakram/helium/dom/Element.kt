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
 * WHATWG-compliant Element.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-element
 */
public class Element(
    public val localName: String,
    public val namespaceURI: String? = null
) : Node() {
    
    override val nodeType: Short = ELEMENT_NODE
    override val nodeName: String = if (namespaceURI == HTML_NAMESPACE) localName.uppercase() else localName
    override val nodeValue: String? = null
    override var textContent: String?
        get() = getTextContentRecursive(this)
        set(value) {
            // WHATWG textContent setter algorithm
            internalChildNodes.toList().forEach { removeChild(it) }
            if (!value.isNullOrEmpty()) {
                appendChild(ownerDocument?.createTextNode(value) ?: Text(value))
            }
        }
    
    private fun getTextContentRecursive(node: Node): String {
        return when (node.nodeType) {
            DOCUMENT_FRAGMENT_NODE, ELEMENT_NODE -> {
                node.internalChildNodes.joinToString("") { getTextContentRecursive(it) }
            }
            TEXT_NODE, CDATA_SECTION_NODE -> {
                (node as? CharacterData)?.data ?: ""
            }
            PROCESSING_INSTRUCTION_NODE, COMMENT_NODE -> {
                (node as? CharacterData)?.data ?: ""
            }
            else -> ""
        }
    }
    override val ownerDocument: Document? get() = generateSequence(parentNode) { it.parentNode }.filterIsInstance<Document>().firstOrNull()
    
    public val tagName: String = nodeName
    
    private val _attributes = NamedNodeMap(this)
    public val attributes: NamedNodeMap get() = _attributes
    
    public var id: String
        get() = getAttribute("id") ?: ""
        set(value) = setAttribute("id", value)
    
    public var className: String
        get() = getAttribute("class") ?: ""
        set(value) = setAttribute("class", value)
    
    public val classList: DOMTokenList by lazy { DOMTokenList(this, "class") }
    
    public var innerHTML: String
        get() = internalChildNodes.joinToString("") { htmlParser.serializeHTML(it) }
        set(value) {
            // WHATWG-compliant innerHTML setter algorithm
            
            // 1. Remove all children
            internalChildNodes.toList().forEach { removeChild(it) }
            
            // 2. Parse HTML fragment
            val fragment = htmlParser.parseHTMLFragment(value, this)
            
            // 3. Append parsed nodes
            fragment.childNodes.forEach { child ->
                appendChild(child)
            }
        }
    
    public var outerHTML: String
        get() = htmlParser.serializeHTML(this)
        set(value) {
            // WHATWG-compliant outerHTML setter algorithm
            
            parentNode?.let { parent ->
                val fragment = htmlParser.parseHTML(value, ownerDocument ?: Document())
                
                // Replace this element with the parsed content
                if (fragment.hasChildNodes()) {
                    // Insert all nodes from the fragment before this element
                    fragment.childNodes.toList().forEach { node ->
                        parent.insertBefore(node, this)
                    }
                }
                
                // Remove this element
                parent.removeChild(this)
            } ?: throw DOMException("Cannot set outerHTML on element without parent", "NoModificationAllowedError")
        }
    
    public val prefix: String? = if (nodeName.contains(':')) nodeName.substringBefore(':') else null
    
    // Scroll properties - would be implemented by layout engine
    public var scrollTop: Double = 0.0
    public var scrollLeft: Double = 0.0
    public val scrollWidth: Double get() = 0.0  // Would be computed by layout
    public val scrollHeight: Double get() = 0.0  // Would be computed by layout
    
    // Client dimensions - would be implemented by layout engine  
    public val clientTop: Double get() = 0.0  // Border top width
    public val clientLeft: Double get() = 0.0  // Border left width
    public val clientWidth: Double get() = 0.0  // Inner width
    public val clientHeight: Double get() = 0.0  // Inner height
    
    // Shadow DOM support
    public var slot: String
        get() = getAttribute("slot") ?: ""
        set(value) = setAttribute("slot", value)
    
    public fun getAttribute(name: String): String? {
        return _attributes.getNamedItem(name)?.value
    }
    
    public fun getAttributeNS(namespaceURI: String?, localName: String): String? {
        return _attributes.getNamedItemNS(namespaceURI, localName)?.value
    }
    
    public fun setAttribute(name: String, value: String) {
        val attr = Attr(name, value)
        _attributes.setNamedItem(attr)
    }
    
    public fun setAttributeNS(namespaceURI: String?, qualifiedName: String, value: String) {
        val attr = Attr(qualifiedName, value, namespaceURI)
        _attributes.setNamedItemNS(attr)
    }
    
    public fun removeAttribute(name: String) {
        try {
            _attributes.removeNamedItem(name)
        } catch (e: DOMException) {
            // Ignore NotFoundError when removing non-existent attributes
        }
    }
    
    public fun removeAttributeNS(namespaceURI: String?, localName: String) {
        try {
            _attributes.removeNamedItemNS(namespaceURI, localName)
        } catch (e: DOMException) {
            // Ignore NotFoundError when removing non-existent attributes
        }
    }
    
    public fun hasAttribute(name: String): Boolean {
        return _attributes.getNamedItem(name) != null
    }
    
    public fun hasAttributeNS(namespaceURI: String?, localName: String): Boolean {
        return _attributes.getNamedItemNS(namespaceURI, localName) != null
    }
    
    public fun hasAttributes(): Boolean {
        return _attributes.length > 0
    }
    
    public fun getAttributeNames(): List<String> {
        return _attributes.keys().toList()
    }
    
    public fun getAttributeNode(name: String): Attr? {
        return _attributes.getNamedItem(name)
    }
    
    public fun getAttributeNodeNS(namespaceURI: String?, localName: String): Attr? {
        return _attributes.getNamedItemNS(namespaceURI, localName)
    }
    
    public fun setAttributeNode(attr: Attr): Attr? {
        return _attributes.setNamedItem(attr)
    }
    
    public fun setAttributeNodeNS(attr: Attr): Attr? {
        return _attributes.setNamedItemNS(attr)
    }
    
    public fun removeAttributeNode(attr: Attr): Attr {
        if (attr.ownerElement !== this) {
            throw DOMException("Attribute is not owned by this element", "NotFoundError")
        }
        return _attributes.removeNamedItem(attr.name)
    }
    
    public fun closest(selectors: String): Element? {
        // WHATWG-compliant closest algorithm
        var current: Element? = this
        while (current != null) {
            if (cssSelector.matches(current, selectors)) {
                return current
            }
            current = current.parentElement
        }
        return null
    }
    
    public fun matches(selectors: String): Boolean {
        return try {
            cssSelector.matches(this, selectors)
        } catch (e: Exception) {
            throw DOMException("Invalid selector: $selectors", "SyntaxError")
        }
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
    
    public fun insertAdjacentElement(where: String, element: Element): Element? {
        return when (where.lowercase()) {
            "beforebegin" -> {
                parentNode?.insertBefore(element, this)
                element
            }
            "afterbegin" -> {
                insertBefore(element, firstChild)
                element
            }
            "beforeend" -> {
                appendChild(element)
                element
            }
            "afterend" -> {
                parentNode?.insertBefore(element, nextSibling)
                element
            }
            else -> throw DOMException("Invalid position: $where", "SyntaxError")
        }
    }
    
    public fun insertAdjacentHTML(where: String, html: String) {
        val fragment = htmlParser.parseHTMLFragment(html, this)
        
        when (where.lowercase()) {
            "beforebegin" -> {
                parentNode?.let { parent ->
                    fragment.childNodes.toList().forEach { node ->
                        parent.insertBefore(node, this)
                    }
                }
            }
            "afterbegin" -> {
                fragment.childNodes.toList().reversed().forEach { node ->
                    insertBefore(node, firstChild)
                }
            }
            "beforeend" -> {
                fragment.childNodes.toList().forEach { node ->
                    appendChild(node)
                }
            }
            "afterend" -> {
                parentNode?.let { parent ->
                    var referenceNode = nextSibling
                    fragment.childNodes.toList().forEach { node ->
                        parent.insertBefore(node, referenceNode)
                        referenceNode = node.nextSibling
                    }
                }
            }
            else -> throw DOMException("Invalid position: $where", "SyntaxError")
        }
    }
    
    public fun insertAdjacentText(where: String, text: String) {
        val textNode = ownerDocument?.createTextNode(text) ?: Text(text)
        when (where.lowercase()) {
            "beforebegin" -> parentNode?.insertBefore(textNode, this)
            "afterbegin" -> insertBefore(textNode, firstChild)
            "beforeend" -> appendChild(textNode)
            "afterend" -> parentNode?.insertBefore(textNode, nextSibling)
            else -> throw DOMException("Invalid position: $where", "SyntaxError")
        }
    }
    
    public fun remove() {
        parentNode?.removeChild(this)
    }
    
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
    
    public override fun lookupPrefix(namespaceURI: String?): String? {
        if (namespaceURI == null || namespaceURI.isEmpty()) return null
        
        // Check this element's namespace
        if (this.namespaceURI == namespaceURI && prefix != null) {
            return prefix
        }
        
        // Check attributes for namespace declarations
        for (attr in _attributes.values()) {
            if (attr.name.startsWith("xmlns:") && attr.value == namespaceURI) {
                return attr.name.substring(6) // Remove "xmlns:" prefix
            }
        }
        
        // Check parent
        return parentNode?.lookupPrefix(namespaceURI)
    }
    
    public override fun lookupNamespaceURI(prefix: String?): String? {
        if (prefix == null || prefix.isEmpty()) {
            // Default namespace
            return getAttribute("xmlns")
        }
        
        // Look for xmlns:prefix attribute
        val attr = getAttribute("xmlns:$prefix")
        if (attr != null) return attr
        
        // Check this element's namespace
        if (this.prefix == prefix) {
            return namespaceURI
        }
        
        // Check parent
        return parentNode?.lookupNamespaceURI(prefix)
    }
    
    
    override fun cloneNode(deep: Boolean): Element {
        val clone = Element(localName, namespaceURI)
        
        // Copy attributes
        _attributes.values().forEach { attr ->
            clone.setAttributeNode(Attr(attr.name, attr.value, attr.namespaceURI))
        }
        
        if (deep) {
            childNodes.forEach { child ->
                clone.appendChild(child.cloneNode(true))
            }
        }
        
        return clone
    }
    
    public companion object {
        public const val HTML_NAMESPACE: String = "http://www.w3.org/1999/xhtml"
    }
}

/**
 * Extension function to check if an element is an HTML element.
 * Per WHATWG spec, an HTML element is an element with HTML namespace.
 */
internal fun Element.isHTMLElement(): Boolean {
    return namespaceURI == Element.HTML_NAMESPACE
}