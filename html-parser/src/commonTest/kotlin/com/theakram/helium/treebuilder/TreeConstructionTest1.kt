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

import kotlin.test.Test

/**
 * Contains tree construction tests from:
 * https://github.com/html5lib/html5lib-tests/blob/master/tree-construction/tests1.dat
 * 
 * These tests verify HTML5 tree construction algorithm compliance.
 */
internal class TreeConstructionTest1 : TreeConstructionTestBase() {

    @Test
    fun `Simple text content`() = assertTreeConstruction(
        input = "Test",
        expectedTree = """
            | <html>
            |   <head>
            |   <body>
            |     "Test"
        """.trimIndent()
    )

    @Test
    fun `Two paragraph elements`() = assertTreeConstruction(
        input = "<p>One<p>Two",
        expectedTree = """
            | <html>
            |   <head>
            |   <body>
            |     <p>
            |       "One"
            |     <p>
            |       "Two"
        """.trimIndent()
    )

    @Test
    fun `Mixed text and br elements`() = assertTreeConstruction(
        input = "Line1<br>Line2<br>Line3<br>Line4",
        expectedTree = """
            | <html>
            |   <head>
            |   <body>
            |     "Line1"
            |     <br>
            |     "Line2"
            |     <br>
            |     "Line3"
            |     <br>
            |     "Line4"
        """.trimIndent()
    )

    @Test
    fun `Basic html element`() = assertTreeConstruction(
        input = "<html>",
        expectedTree = """
            | <html>
            |   <head>
            |   <body>
        """.trimIndent()
    )

    @Test
    fun `Basic head element`() = assertTreeConstruction(
        input = "<head>",
        expectedTree = """
            | <html>
            |   <head>
            |   <body>
        """.trimIndent()
    )

    @Test
    fun `Title element`() = assertTreeConstruction(
        input = "<title>",
        expectedTree = """
            | <html>
            |   <head>
            |     <title>
            |   <body>
        """.trimIndent()
    )

    @Test
    fun `End head element`() = assertTreeConstruction(
        input = "</head>",
        expectedTree = """
            | <html>
            |   <head>
            |   <body>
        """.trimIndent()
    )

    @Test
    fun `Basic body element`() = assertTreeConstruction(
        input = "<body>",
        expectedTree = """
            | <html>
            |   <head>
            |   <body>
        """.trimIndent()
    )

    @Test
    fun `End body element`() = assertTreeConstruction(
        input = "</body>",
        expectedTree = """
            | <html>
            |   <head>
            |   <body>
        """.trimIndent()
    )

    @Test
    fun `End html element`() = assertTreeConstruction(
        input = "</html>",
        expectedTree = """
            | <html>
            |   <head>
            |   <body>
        """.trimIndent()
    )
}