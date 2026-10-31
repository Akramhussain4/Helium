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

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class ProcessingInstructionAttributesTest {

    @Test
    fun `parses pseudo-attributes with either quote and surrounding whitespace`() {
        assertEquals(
            mapOf("name" to "a", "type" to "text/css"),
            ProcessingInstructionAttributes.parsePseudoAttributes(" name = \"a\"\ttype='text/css' ")
        )
    }

    @Test
    fun `replaces predefined entity and character references`() {
        assertEquals(
            mapOf("v" to "<&>\"'AB"),
            ProcessingInstructionAttributes.parsePseudoAttributes("v=\"&lt;&amp;&gt;&quot;&apos;&#65;&#x42;\"")
        )
    }

    @Test
    fun `empty data has no pseudo-attributes`() {
        assertEquals(emptyMap(), ProcessingInstructionAttributes.parsePseudoAttributes(""))
    }

    @Test
    fun `malformed data is an error`() {
        for (data in listOf("a=b", "a=\"b\"c=\"d\"", "a=\"<\"", "a=\"&x;\"", "a=\"&#0;\"", "a=\"1\" a=\"2\"", "1a=\"b\"", "a=\"b")) {
            assertNull(ProcessingInstructionAttributes.parsePseudoAttributes(data), data)
        }
    }
}
