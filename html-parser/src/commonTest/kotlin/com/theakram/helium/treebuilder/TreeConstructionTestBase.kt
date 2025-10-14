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

package com.theakram.helium.treebuilder

import com.theakram.helium.tokenization.Tokenizer

/**
 * Base class for tree construction tests.
 * Follows the exact pattern established by TokenizerTestBase.
 */
internal abstract class TreeConstructionTestBase {

    private val treeBuilder = TreeBuilder()
    private val tokenizer = Tokenizer { token ->
        treeBuilder.process(token)
    }
    
    /**
     * Parse HTML input and return the resulting tree structure.
     */
    protected fun parseTree(input: String): String {
        treeBuilder.reset()
        tokenizer.tokenize(input)
        treeBuilder.finish()
        return treeBuilder.toTreeString().trim()
    }
    
    /**
     * Assert that HTML input produces the expected tree structure.
     */
    protected fun assertTreeConstruction(input: String, expectedTree: String) {
        val actualTree = parseTree(input)
        if (actualTree != expectedTree.trim()) {
            throw AssertionError(
                "Tree structure mismatch.\n" +
                "Input: $input\n" +
                "Expected:\n${expectedTree.trim()}\n" +
                "Actual:\n$actualTree"
            )
        }
    }
}