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
 * WHATWG-compliant DOMImplementation interface.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-domimplementation
 */
public class DOMImplementation(private val document: Document) {
    
    public fun createDocumentType(qualifiedName: String, publicId: String = "", systemId: String = ""): DocumentType {
        // Validate qualified name
        require(qualifiedName.isNotEmpty()) { "Qualified name cannot be empty" }
        require(isValidQualifiedName(qualifiedName)) { "Invalid qualified name: $qualifiedName" }
        
        return DocumentType(qualifiedName, publicId, systemId)
    }
    
    public fun createDocument(
        namespace: String?, 
        qualifiedName: String?, 
        doctype: DocumentType? = null
    ): Document {
        val document = Document()
        
        // Add doctype if provided
        doctype?.let { document.appendChild(it) }
        
        // Create document element if qualified name is provided
        qualifiedName?.let { qName ->
            require(qName.isNotEmpty()) { "Qualified name cannot be empty" }
            require(isValidQualifiedName(qName)) { "Invalid qualified name: $qName" }
            
            val documentElement = if (namespace != null) {
                document.createElementNS(namespace, qName)
            } else {
                document.createElement(qName)
            }
            
            document.appendChild(documentElement)
        }
        
        return document
    }
    
    public fun createHTMLDocument(title: String? = null): Document {
        val document = Document()
        document.contentType = "text/html"
        
        // Create DOCTYPE html
        val doctype = createDocumentType("html")
        document.appendChild(doctype)
        
        // Create html element
        val htmlElement = document.createElement("html")
        document.appendChild(htmlElement)
        
        // Create head element
        val headElement = document.createElement("head")
        htmlElement.appendChild(headElement)
        
        // Create title element if title is provided
        title?.let { titleText ->
            val titleElement = document.createElement("title")
            titleElement.textContent = titleText
            headElement.appendChild(titleElement)
        }
        
        // Create body element
        val bodyElement = document.createElement("body")
        htmlElement.appendChild(bodyElement)
        
        return document
    }
    
    public fun hasFeature(feature: String, version: String): Boolean {
        // Per WHATWG spec, this method is deprecated and should always return true
        return true
    }
    
    private fun isValidQualifiedName(name: String): Boolean {
        if (name.isEmpty()) return false
        
        // Basic validation - more complex validation would be needed for full compliance
        return name.all { char ->
            char.isLetterOrDigit() || char in setOf('-', '_', '.', ':')
        } && !name.startsWith(":") && !name.endsWith(":")
    }
}