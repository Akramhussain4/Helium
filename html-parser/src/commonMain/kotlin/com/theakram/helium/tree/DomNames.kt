/*
 * MIT License
 *
 * Copyright (c) 2023-2026 Mohammed Akram Hussain
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
package com.theakram.helium.tree

import com.theakram.helium.util.isAsciiWhitespace

/**
 * Spec: https://dom.spec.whatwg.org/#valid-attribute-local-name
 */
internal fun isValidAttributeLocalName(name: String): Boolean =
    name.isNotEmpty() &&
        name.none { it.code.isAsciiWhitespace() || it == '\u0000' || it == '/' || it == '=' || it == '>' }

/**
 * Spec: https://dom.spec.whatwg.org/#valid-namespace-prefix
 */
internal fun isValidNamespacePrefix(prefix: String): Boolean =
    prefix.isNotEmpty() && prefix.none { it.code.isAsciiWhitespace() || it == '\u0000' || it == '/' || it == '>' }

internal data class ExtractedName(val namespace: String?, val prefix: String?, val localName: String)

/**
 * "Validate and extract" a namespace and qualified name, given the "attribute" context. The DOM's
 * "InvalidCharacterError" and "NamespaceError" exceptions are thrown as [IllegalArgumentException]s.
 *
 * Spec: https://dom.spec.whatwg.org/#validate-and-extract
 */
internal fun validateAndExtract(namespace: String?, qualifiedName: String): ExtractedName {
    // Step 1:
    val validatedNamespace = namespace?.ifEmpty { null }

    // Steps 2-4:
    val colon = qualifiedName.indexOf(':')
    val prefix = if (colon == -1) null else qualifiedName.substring(0, colon)
    val localName = if (colon == -1) qualifiedName else qualifiedName.substring(colon + 1)
    require(prefix == null || isValidNamespacePrefix(prefix)) {
        "InvalidCharacterError: \"$prefix\" is not a valid namespace prefix"
    }

    // Step 6: The context is "attribute"
    require(isValidAttributeLocalName(localName)) {
        "InvalidCharacterError: \"$localName\" is not a valid attribute local name"
    }

    // Step 8:
    require(prefix == null || validatedNamespace != null) { "NamespaceError: a prefix needs a namespace" }

    // Step 9:
    require(prefix != "xml" || validatedNamespace == Namespaces.XML) {
        "NamespaceError: the xml prefix needs the XML namespace"
    }

    // Step 10:
    require((qualifiedName != "xmlns" && prefix != "xmlns") || validatedNamespace == Namespaces.XMLNS) {
        "NamespaceError: xmlns needs the XMLNS namespace"
    }

    // Step 11:
    require(validatedNamespace != Namespaces.XMLNS || qualifiedName == "xmlns" || prefix == "xmlns") {
        "NamespaceError: the XMLNS namespace needs xmlns"
    }

    // Step 12:
    return ExtractedName(validatedNamespace, prefix, localName)
}
