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

package com.theakram.helium.util

internal enum class Chars(val char: Char) {
    Tab('\t'),
    LineFeed('\n'),
    FormFeed('\u000c'),
    CarriageReturn('\r'),
    Space(' '),
    ExclamationMark('!'),
    NumberSign('#'),
    Ampersand('&'),
    Apostrophe('\''),
    QuotationMark('"'),
    HyphenMinus('-'),
    Solidus('/'),
    Zero('0'),
    Nine('9'),
    SemiColon(';'),
    LessThan('<'),
    Equals('='),
    GreaterThan('>'),
    QuestionMark('?'),
    UpperA('A'),
    LowerA('a'),
    UpperF('F'),
    LowerF('f'),
    UpperZ('Z'),
    LowerZ('z'),
    LowerX('x'),
    UpperX('X'),
    GraveAccent('`'),
    LowLine('_'),
    OpeningSquareBracket('['),
    ClosingSquareBracket(']'),
    Null('\u0000'),
}