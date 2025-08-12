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
 * Sealed class hierarchy for Node types providing better type safety.
 * This is a Kotlin idiom to replace numeric constants with type-safe alternatives.
 */
public sealed class NodeType(public val value: Short) {
    public object Element : NodeType(1)
    public object Attribute : NodeType(2)
    public object Text : NodeType(3)
    public object CDATASection : NodeType(4)
    public object ProcessingInstruction : NodeType(7)
    public object Comment : NodeType(8)
    public object Document : NodeType(9)
    public object DocumentType : NodeType(10)
    public object DocumentFragment : NodeType(11)
    
    public companion object {
        public fun fromValue(value: Short): NodeType? {
            return when (value) {
                1.toShort() -> Element
                2.toShort() -> Attribute
                3.toShort() -> Text
                4.toShort() -> CDATASection
                7.toShort() -> ProcessingInstruction
                8.toShort() -> Comment
                9.toShort() -> Document
                10.toShort() -> DocumentType
                11.toShort() -> DocumentFragment
                else -> null
            }
        }
    }
}

/**
 * Extension property for Node to get type-safe NodeType.
 */
public val Node.nodeTypeEnum: NodeType
    get() = NodeType.fromValue(nodeType) ?: throw IllegalStateException("Unknown node type: $nodeType")