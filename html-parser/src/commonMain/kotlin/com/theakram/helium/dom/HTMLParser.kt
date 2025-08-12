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
 * HTML Parser interface for WHATWG-compliant parsing.
 * This interface defines the contract for HTML parsing operations.
 */
public interface HTMLParser {
    
    /**
     * Parse HTML fragment for innerHTML operation.
     * WHATWG spec: https://html.spec.whatwg.org/multipage/dynamic-markup-insertion.html#html-fragment-parsing-algorithm
     */
    public fun parseHTMLFragment(markup: String, contextElement: Element): DocumentFragment
    
    /**
     * Parse HTML for outerHTML operation.
     */
    public fun parseHTML(markup: String, document: Document): DocumentFragment
    
    /**
     * Serialize a node to HTML string.
     */
    public fun serializeHTML(node: Node): String
}

/**
 * Default HTML parser implementation that delegates to the actual HTML parser.
 * This is a placeholder until the full HTML parser is integrated.
 */
internal class DefaultHTMLParser : HTMLParser {
    
    override fun parseHTMLFragment(markup: String, contextElement: Element): DocumentFragment {
        // TODO: This should delegate to the actual HTML parser implementation
        // For now, we'll create a text node as a safe fallback
        val fragment = contextElement.ownerDocument?.createDocumentFragment() ?: DocumentFragment()
        
        if (markup.isNotEmpty()) {
            // This is a temporary implementation - the actual parser should be used
            val textNode = contextElement.ownerDocument?.createTextNode(markup) ?: Text(markup)
            fragment.appendChild(textNode)
        }
        
        return fragment
    }
    
    override fun parseHTML(markup: String, document: Document): DocumentFragment {
        val fragment = document.createDocumentFragment()
        
        if (markup.isNotEmpty()) {
            // This is a temporary implementation - the actual parser should be used
            val textNode = document.createTextNode(markup)
            fragment.appendChild(textNode)
        }
        
        return fragment
    }
    
    override fun serializeHTML(node: Node): String {
        return when (node) {
            is Element -> serializeElement(node)
            is Text -> serializeText(node)
            is Comment -> "<!--${node.data}-->"
            is DocumentType -> "<!DOCTYPE ${node.name}${if (node.publicId.isNotEmpty()) " PUBLIC \"${node.publicId}\"" else ""}${if (node.systemId.isNotEmpty()) " \"${node.systemId}\"" else ""}>"
            is ProcessingInstruction -> "<?${node.target} ${node.data}?>"
            is CDATASection -> "<![CDATA[${node.data}]]>"
            else -> ""
        }
    }
    
    private fun serializeElement(element: Element): String {
        val tagName = element.localName
        val attributes = buildString {
            for (attr in element.attributes.values()) {
                append(" ${attr.name}=\"${escapeAttributeValue(attr.value)}\"")
            }
        }
        
        val children = element.childNodes.joinToString("") { serializeHTML(it) }
        
        // Self-closing elements (void elements)
        val voidElements = setOf("area", "base", "br", "col", "embed", "hr", "img", "input", 
                                "link", "meta", "param", "source", "track", "wbr")
        
        return if (tagName.lowercase() in voidElements && children.isEmpty()) {
            "<$tagName$attributes>"
        } else {
            "<$tagName$attributes>$children</$tagName>"
        }
    }
    
    private fun serializeText(text: Text): String {
        return escapeTextContent(text.data)
    }
    
    private fun escapeTextContent(text: String): String {
        return text
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
    }
    
    private fun escapeAttributeValue(value: String): String {
        return value
            .replace("&", "&amp;")
            .replace("\"", "&quot;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
    }
}

/**
 * Global HTML parser instance.
 */
internal val htmlParser: HTMLParser = DefaultHTMLParser()