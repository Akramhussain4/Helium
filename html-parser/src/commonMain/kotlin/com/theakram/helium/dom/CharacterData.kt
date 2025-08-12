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
 * WHATWG-compliant CharacterData.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-characterdata
 */
public abstract class CharacterData(initialData: String) : Node() {
    
    public var data: String = initialData
    
    public val length: Int get() = data.length
    
    override val nodeValue: String?
        get() = data
    override var textContent: String?
        get() = data
        set(value) { data = value ?: "" }
    
    public fun substringData(offset: Int, count: Int): String {
        require(offset >= 0 && offset <= length) { "Offset out of range: $offset" }
        require(count >= 0) { "Count cannot be negative: $count" }
        return data.substring(offset, minOf(offset + count, length))
    }
    
    public fun appendData(data: String) {
        replaceData(length, 0, data)
    }
    
    public fun insertData(offset: Int, data: String) {
        replaceData(offset, 0, data)
    }
    
    public fun deleteData(offset: Int, count: Int) {
        replaceData(offset, count, "")
    }
    
    public fun replaceData(offset: Int, count: Int, data: String) {
        require(offset >= 0 && offset <= length) { "Offset out of range: $offset" }
        require(count >= 0) { "Count cannot be negative: $count" }
        
        val actualCount = minOf(count, length - offset)
        val newData = StringBuilder(this.data)
        newData.replace(offset, offset + actualCount, data)
        this.data = newData.toString()
    }
}