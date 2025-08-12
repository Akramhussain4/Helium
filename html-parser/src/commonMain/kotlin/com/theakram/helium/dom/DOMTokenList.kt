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
 * WHATWG-compliant DOMTokenList interface.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-domtokenlist
 */
public class DOMTokenList(
    private val element: Element,
    private val attributeName: String
) : Iterable<String> {
    
    private fun getTokens(): List<String> {
        return element.getAttribute(attributeName)?.trim()?.split("\\s+".toRegex())?.filter { it.isNotEmpty() } ?: emptyList()
    }
    
    private fun setTokens(tokens: List<String>) {
        if (tokens.isEmpty()) {
            element.removeAttribute(attributeName)
        } else {
            element.setAttribute(attributeName, tokens.joinToString(" "))
        }
    }
    
    public val length: Int get() = getTokens().size
    
    public fun item(index: Int): String? = getTokens().getOrNull(index)
    
    public fun contains(token: String): Boolean {
        validateToken(token)
        return getTokens().contains(token)
    }
    
    public fun add(vararg tokens: String) {
        tokens.forEach { validateToken(it) }
        val current = getTokens().toMutableSet()
        current.addAll(tokens)
        setTokens(current.toList())
    }
    
    public fun remove(vararg tokens: String) {
        tokens.forEach { validateToken(it) }
        val current = getTokens().toMutableSet()
        current.removeAll(tokens.toSet())
        setTokens(current.toList())
    }
    
    public fun toggle(token: String, force: Boolean? = null): Boolean {
        validateToken(token)
        val current = getTokens().toMutableSet()
        
        return if (force == null) {
            if (current.contains(token)) {
                current.remove(token)
                setTokens(current.toList())
                false
            } else {
                current.add(token)
                setTokens(current.toList())
                true
            }
        } else {
            if (force) {
                current.add(token)
                setTokens(current.toList())
                true
            } else {
                current.remove(token)
                setTokens(current.toList())
                false
            }
        }
    }
    
    public fun replace(oldToken: String, newToken: String): Boolean {
        validateToken(oldToken)
        validateToken(newToken)
        
        val tokens = getTokens().toMutableList()
        val index = tokens.indexOf(oldToken)
        
        return if (index != -1) {
            tokens[index] = newToken
            setTokens(tokens)
            true
        } else {
            false
        }
    }
    
    public fun supports(token: String): Boolean {
        // This would typically check if the token is supported for this attribute
        // For now, return true for all tokens
        return true
    }
    
    override fun iterator(): Iterator<String> = getTokens().iterator()
    
    override fun toString(): String = getTokens().joinToString(" ")
    
    private fun validateToken(token: String) {
        if (token.isEmpty()) {
            throw DOMException("Token cannot be empty", "SyntaxError")
        }
        if (token.contains("\\s".toRegex())) {
            throw DOMException("Token cannot contain whitespace", "InvalidCharacterError")
        }
    }
}