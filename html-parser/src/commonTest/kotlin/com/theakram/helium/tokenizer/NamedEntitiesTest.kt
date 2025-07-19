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

package com.theakram.helium.tokenizer

import kotlin.test.Test

/**
 * Contains all 4210 test from here:
 * https://github.com/html5lib/html5lib-tests/blob/master/tokenizer/namedEntities.test
 *
 */
internal class NamedEntitiesTest : TokenizerTestBase() {

    @Test
    fun `Named entity AElig without a semicolon`() = assertTokenization(
        input = "&AElig",
        expectedTokens = listOf(
            char('Æ')
        )
    )

    @Test
    fun `Named entity AElig with a semicolon`() = assertTokenization(
        input = "&AElig;",
        expectedTokens = listOf(
            char('Æ')
        )
    )

    @Test
    fun `Named entity AMP without a semicolon`() = assertTokenization(
        input = "&AMP",
        expectedTokens = listOf(
            char('&')
        )
    )

    @Test
    fun `Named entity AMP with a semicolon`() = assertTokenization(
        input = "&AMP;",
        expectedTokens = listOf(
            char('&')
        )
    )

    @Test
    fun `Named entity Aacute without a semicolon`() = assertTokenization(
        input = "&Aacute",
        expectedTokens = listOf(
            char('Á')
        )
    )

    @Test
    fun `Named entity Aacute with a semicolon`() = assertTokenization(
        input = "&Aacute;",
        expectedTokens = listOf(
            char('Á')
        )
    )

    @Test
    fun `Bad named entity Abreve without a semicolon`() = assertTokenization(
        input = "&Abreve",
        expectedTokens = listOf(
            *chars("&Abreve").toTypedArray()
        )
    )

    @Test
    fun `Named entity Abreve with a semicolon`() = assertTokenization(
        input = "&Abreve;",
        expectedTokens = listOf(
            char('Ă')
        )
    )

    @Test
    fun `Named entity Acirc without a semicolon`() = assertTokenization(
        input = "&Acirc",
        expectedTokens = listOf(
            char('Â')
        )
    )

    @Test
    fun `Named entity Acirc with a semicolon`() = assertTokenization(
        input = "&Acirc;",
        expectedTokens = listOf(
            char('Â')
        )
    )

    @Test
    fun `Bad named entity Acy without a semicolon`() = assertTokenization(
        input = "&Acy",
        expectedTokens = listOf(
            *chars("&Acy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Acy with a semicolon`() = assertTokenization(
        input = "&Acy;",
        expectedTokens = listOf(
            char('А')
        )
    )

    @Test
    fun `Bad named entity Afr without a semicolon`() = assertTokenization(
        input = "&Afr",
        expectedTokens = listOf(
            *chars("&Afr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Afr with a semicolon`() = assertTokenization(
        input = "&Afr;",
        expectedTokens = listOf(
            *chars("𝔄").toTypedArray()
        )
    )

    @Test
    fun `Named entity Agrave without a semicolon`() = assertTokenization(
        input = "&Agrave",
        expectedTokens = listOf(
            char('À')
        )
    )

    @Test
    fun `Named entity Agrave with a semicolon`() = assertTokenization(
        input = "&Agrave;",
        expectedTokens = listOf(
            char('À')
        )
    )

    @Test
    fun `Bad named entity Alpha without a semicolon`() = assertTokenization(
        input = "&Alpha",
        expectedTokens = listOf(
            *chars("&Alpha").toTypedArray()
        )
    )

    @Test
    fun `Named entity Alpha with a semicolon`() = assertTokenization(
        input = "&Alpha;",
        expectedTokens = listOf(
            char('Α')
        )
    )

    @Test
    fun `Bad named entity Amacr without a semicolon`() = assertTokenization(
        input = "&Amacr",
        expectedTokens = listOf(
            *chars("&Amacr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Amacr with a semicolon`() = assertTokenization(
        input = "&Amacr;",
        expectedTokens = listOf(
            char('Ā')
        )
    )

    @Test
    fun `Bad named entity And without a semicolon`() = assertTokenization(
        input = "&And",
        expectedTokens = listOf(
            *chars("&And").toTypedArray()
        )
    )

    @Test
    fun `Named entity And with a semicolon`() = assertTokenization(
        input = "&And;",
        expectedTokens = listOf(
            char('⩓')
        )
    )

    @Test
    fun `Bad named entity Aogon without a semicolon`() = assertTokenization(
        input = "&Aogon",
        expectedTokens = listOf(
            *chars("&Aogon").toTypedArray()
        )
    )

    @Test
    fun `Named entity Aogon with a semicolon`() = assertTokenization(
        input = "&Aogon;",
        expectedTokens = listOf(
            char('Ą')
        )
    )

    @Test
    fun `Bad named entity Aopf without a semicolon`() = assertTokenization(
        input = "&Aopf",
        expectedTokens = listOf(
            *chars("&Aopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Aopf with a semicolon`() = assertTokenization(
        input = "&Aopf;",
        expectedTokens = listOf(
            *chars("𝔸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ApplyFunction without a semicolon`() = assertTokenization(
        input = "&ApplyFunction",
        expectedTokens = listOf(
            *chars("&ApplyFunction").toTypedArray()
        )
    )

    @Test
    fun `Named entity ApplyFunction with a semicolon`() = assertTokenization(
        input = "&ApplyFunction;",
        expectedTokens = listOf(
            char('⁡')
        )
    )

    @Test
    fun `Named entity Aring without a semicolon`() = assertTokenization(
        input = "&Aring",
        expectedTokens = listOf(
            char('Å')
        )
    )

    @Test
    fun `Named entity Aring with a semicolon`() = assertTokenization(
        input = "&Aring;",
        expectedTokens = listOf(
            char('Å')
        )
    )

    @Test
    fun `Bad named entity Ascr without a semicolon`() = assertTokenization(
        input = "&Ascr",
        expectedTokens = listOf(
            *chars("&Ascr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ascr with a semicolon`() = assertTokenization(
        input = "&Ascr;",
        expectedTokens = listOf(
            *chars("𝒜").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Assign without a semicolon`() = assertTokenization(
        input = "&Assign",
        expectedTokens = listOf(
            *chars("&Assign").toTypedArray()
        )
    )

    @Test
    fun `Named entity Assign with a semicolon`() = assertTokenization(
        input = "&Assign;",
        expectedTokens = listOf(
            char('≔')
        )
    )

    @Test
    fun `Named entity Atilde without a semicolon`() = assertTokenization(
        input = "&Atilde",
        expectedTokens = listOf(
            char('Ã')
        )
    )

    @Test
    fun `Named entity Atilde with a semicolon`() = assertTokenization(
        input = "&Atilde;",
        expectedTokens = listOf(
            char('Ã')
        )
    )

    @Test
    fun `Named entity Auml without a semicolon`() = assertTokenization(
        input = "&Auml",
        expectedTokens = listOf(
            char('Ä')
        )
    )

    @Test
    fun `Named entity Auml with a semicolon`() = assertTokenization(
        input = "&Auml;",
        expectedTokens = listOf(
            char('Ä')
        )
    )

    @Test
    fun `Bad named entity Backslash without a semicolon`() = assertTokenization(
        input = "&Backslash",
        expectedTokens = listOf(
            *chars("&Backslash").toTypedArray()
        )
    )

    @Test
    fun `Named entity Backslash with a semicolon`() = assertTokenization(
        input = "&Backslash;",
        expectedTokens = listOf(
            char('∖')
        )
    )

    @Test
    fun `Bad named entity Barv without a semicolon`() = assertTokenization(
        input = "&Barv",
        expectedTokens = listOf(
            *chars("&Barv").toTypedArray()
        )
    )

    @Test
    fun `Named entity Barv with a semicolon`() = assertTokenization(
        input = "&Barv;",
        expectedTokens = listOf(
            char('⫧')
        )
    )

    @Test
    fun `Bad named entity Barwed without a semicolon`() = assertTokenization(
        input = "&Barwed",
        expectedTokens = listOf(
            *chars("&Barwed").toTypedArray()
        )
    )

    @Test
    fun `Named entity Barwed with a semicolon`() = assertTokenization(
        input = "&Barwed;",
        expectedTokens = listOf(
            char('⌆')
        )
    )

    @Test
    fun `Bad named entity Bcy without a semicolon`() = assertTokenization(
        input = "&Bcy",
        expectedTokens = listOf(
            *chars("&Bcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Bcy with a semicolon`() = assertTokenization(
        input = "&Bcy;",
        expectedTokens = listOf(
            char('Б')
        )
    )

    @Test
    fun `Bad named entity Because without a semicolon`() = assertTokenization(
        input = "&Because",
        expectedTokens = listOf(
            *chars("&Because").toTypedArray()
        )
    )

    @Test
    fun `Named entity Because with a semicolon`() = assertTokenization(
        input = "&Because;",
        expectedTokens = listOf(
            char('∵')
        )
    )

    @Test
    fun `Bad named entity Bernoullis without a semicolon`() = assertTokenization(
        input = "&Bernoullis",
        expectedTokens = listOf(
            *chars("&Bernoullis").toTypedArray()
        )
    )

    @Test
    fun `Named entity Bernoullis with a semicolon`() = assertTokenization(
        input = "&Bernoullis;",
        expectedTokens = listOf(
            char('ℬ')
        )
    )

    @Test
    fun `Bad named entity Beta without a semicolon`() = assertTokenization(
        input = "&Beta",
        expectedTokens = listOf(
            *chars("&Beta").toTypedArray()
        )
    )

    @Test
    fun `Named entity Beta with a semicolon`() = assertTokenization(
        input = "&Beta;",
        expectedTokens = listOf(
            char('Β')
        )
    )

    @Test
    fun `Bad named entity Bfr without a semicolon`() = assertTokenization(
        input = "&Bfr",
        expectedTokens = listOf(
            *chars("&Bfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Bfr with a semicolon`() = assertTokenization(
        input = "&Bfr;",
        expectedTokens = listOf(
            *chars("𝔅").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Bopf without a semicolon`() = assertTokenization(
        input = "&Bopf",
        expectedTokens = listOf(
            *chars("&Bopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Bopf with a semicolon`() = assertTokenization(
        input = "&Bopf;",
        expectedTokens = listOf(
            *chars("𝔹").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Breve without a semicolon`() = assertTokenization(
        input = "&Breve",
        expectedTokens = listOf(
            *chars("&Breve").toTypedArray()
        )
    )

    @Test
    fun `Named entity Breve with a semicolon`() = assertTokenization(
        input = "&Breve;",
        expectedTokens = listOf(
            char('˘')
        )
    )

    @Test
    fun `Bad named entity Bscr without a semicolon`() = assertTokenization(
        input = "&Bscr",
        expectedTokens = listOf(
            *chars("&Bscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Bscr with a semicolon`() = assertTokenization(
        input = "&Bscr;",
        expectedTokens = listOf(
            char('ℬ')
        )
    )

    @Test
    fun `Bad named entity Bumpeq without a semicolon`() = assertTokenization(
        input = "&Bumpeq",
        expectedTokens = listOf(
            *chars("&Bumpeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity Bumpeq with a semicolon`() = assertTokenization(
        input = "&Bumpeq;",
        expectedTokens = listOf(
            char('≎')
        )
    )

    @Test
    fun `Bad named entity CHcy without a semicolon`() = assertTokenization(
        input = "&CHcy",
        expectedTokens = listOf(
            *chars("&CHcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity CHcy with a semicolon`() = assertTokenization(
        input = "&CHcy;",
        expectedTokens = listOf(
            char('Ч')
        )
    )

    @Test
    fun `Named entity COPY without a semicolon`() = assertTokenization(
        input = "&COPY",
        expectedTokens = listOf(
            char('©')
        )
    )

    @Test
    fun `Named entity COPY with a semicolon`() = assertTokenization(
        input = "&COPY;",
        expectedTokens = listOf(
            char('©')
        )
    )

    @Test
    fun `Bad named entity Cacute without a semicolon`() = assertTokenization(
        input = "&Cacute",
        expectedTokens = listOf(
            *chars("&Cacute").toTypedArray()
        )
    )

    @Test
    fun `Named entity Cacute with a semicolon`() = assertTokenization(
        input = "&Cacute;",
        expectedTokens = listOf(
            char('Ć')
        )
    )

    @Test
    fun `Bad named entity Cap without a semicolon`() = assertTokenization(
        input = "&Cap",
        expectedTokens = listOf(
            *chars("&Cap").toTypedArray()
        )
    )

    @Test
    fun `Named entity Cap with a semicolon`() = assertTokenization(
        input = "&Cap;",
        expectedTokens = listOf(
            char('⋒')
        )
    )

    @Test
    fun `Bad named entity CapitalDifferentialD without a semicolon`() = assertTokenization(
        input = "&CapitalDifferentialD",
        expectedTokens = listOf(
            *chars("&CapitalDifferentialD").toTypedArray()
        )
    )

    @Test
    fun `Named entity CapitalDifferentialD with a semicolon`() = assertTokenization(
        input = "&CapitalDifferentialD;",
        expectedTokens = listOf(
            char('ⅅ')
        )
    )

    @Test
    fun `Bad named entity Cayleys without a semicolon`() = assertTokenization(
        input = "&Cayleys",
        expectedTokens = listOf(
            *chars("&Cayleys").toTypedArray()
        )
    )

    @Test
    fun `Named entity Cayleys with a semicolon`() = assertTokenization(
        input = "&Cayleys;",
        expectedTokens = listOf(
            char('ℭ')
        )
    )

    @Test
    fun `Bad named entity Ccaron without a semicolon`() = assertTokenization(
        input = "&Ccaron",
        expectedTokens = listOf(
            *chars("&Ccaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ccaron with a semicolon`() = assertTokenization(
        input = "&Ccaron;",
        expectedTokens = listOf(
            char('Č')
        )
    )

    @Test
    fun `Named entity Ccedil without a semicolon`() = assertTokenization(
        input = "&Ccedil",
        expectedTokens = listOf(
            char('Ç')
        )
    )

    @Test
    fun `Named entity Ccedil with a semicolon`() = assertTokenization(
        input = "&Ccedil;",
        expectedTokens = listOf(
            char('Ç')
        )
    )

    @Test
    fun `Bad named entity Ccirc without a semicolon`() = assertTokenization(
        input = "&Ccirc",
        expectedTokens = listOf(
            *chars("&Ccirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ccirc with a semicolon`() = assertTokenization(
        input = "&Ccirc;",
        expectedTokens = listOf(
            char('Ĉ')
        )
    )

    @Test
    fun `Bad named entity Cconint without a semicolon`() = assertTokenization(
        input = "&Cconint",
        expectedTokens = listOf(
            *chars("&Cconint").toTypedArray()
        )
    )

    @Test
    fun `Named entity Cconint with a semicolon`() = assertTokenization(
        input = "&Cconint;",
        expectedTokens = listOf(
            char('∰')
        )
    )

    @Test
    fun `Bad named entity Cdot without a semicolon`() = assertTokenization(
        input = "&Cdot",
        expectedTokens = listOf(
            *chars("&Cdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity Cdot with a semicolon`() = assertTokenization(
        input = "&Cdot;",
        expectedTokens = listOf(
            char('Ċ')
        )
    )

    @Test
    fun `Bad named entity Cedilla without a semicolon`() = assertTokenization(
        input = "&Cedilla",
        expectedTokens = listOf(
            *chars("&Cedilla").toTypedArray()
        )
    )

    @Test
    fun `Named entity Cedilla with a semicolon`() = assertTokenization(
        input = "&Cedilla;",
        expectedTokens = listOf(
            char('¸')
        )
    )

    @Test
    fun `Bad named entity CenterDot without a semicolon`() = assertTokenization(
        input = "&CenterDot",
        expectedTokens = listOf(
            *chars("&CenterDot").toTypedArray()
        )
    )

    @Test
    fun `Named entity CenterDot with a semicolon`() = assertTokenization(
        input = "&CenterDot;",
        expectedTokens = listOf(
            char('·')
        )
    )

    @Test
    fun `Bad named entity Cfr without a semicolon`() = assertTokenization(
        input = "&Cfr",
        expectedTokens = listOf(
            *chars("&Cfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Cfr with a semicolon`() = assertTokenization(
        input = "&Cfr;",
        expectedTokens = listOf(
            char('ℭ')
        )
    )

    @Test
    fun `Bad named entity Chi without a semicolon`() = assertTokenization(
        input = "&Chi",
        expectedTokens = listOf(
            *chars("&Chi").toTypedArray()
        )
    )

    @Test
    fun `Named entity Chi with a semicolon`() = assertTokenization(
        input = "&Chi;",
        expectedTokens = listOf(
            char('Χ')
        )
    )

    @Test
    fun `Bad named entity CircleDot without a semicolon`() = assertTokenization(
        input = "&CircleDot",
        expectedTokens = listOf(
            *chars("&CircleDot").toTypedArray()
        )
    )

    @Test
    fun `Named entity CircleDot with a semicolon`() = assertTokenization(
        input = "&CircleDot;",
        expectedTokens = listOf(
            char('⊙')
        )
    )

    @Test
    fun `Bad named entity CircleMinus without a semicolon`() = assertTokenization(
        input = "&CircleMinus",
        expectedTokens = listOf(
            *chars("&CircleMinus").toTypedArray()
        )
    )

    @Test
    fun `Named entity CircleMinus with a semicolon`() = assertTokenization(
        input = "&CircleMinus;",
        expectedTokens = listOf(
            char('⊖')
        )
    )

    @Test
    fun `Bad named entity CirclePlus without a semicolon`() = assertTokenization(
        input = "&CirclePlus",
        expectedTokens = listOf(
            *chars("&CirclePlus").toTypedArray()
        )
    )

    @Test
    fun `Named entity CirclePlus with a semicolon`() = assertTokenization(
        input = "&CirclePlus;",
        expectedTokens = listOf(
            char('⊕')
        )
    )

    @Test
    fun `Bad named entity CircleTimes without a semicolon`() = assertTokenization(
        input = "&CircleTimes",
        expectedTokens = listOf(
            *chars("&CircleTimes").toTypedArray()
        )
    )

    @Test
    fun `Named entity CircleTimes with a semicolon`() = assertTokenization(
        input = "&CircleTimes;",
        expectedTokens = listOf(
            char('⊗')
        )
    )

    @Test
    fun `Bad named entity ClockwiseContourIntegral without a semicolon`() = assertTokenization(
        input = "&ClockwiseContourIntegral",
        expectedTokens = listOf(
            *chars("&ClockwiseContourIntegral").toTypedArray()
        )
    )

    @Test
    fun `Named entity ClockwiseContourIntegral with a semicolon`() = assertTokenization(
        input = "&ClockwiseContourIntegral;",
        expectedTokens = listOf(
            char('∲')
        )
    )

    @Test
    fun `Bad named entity CloseCurlyDoubleQuote without a semicolon`() = assertTokenization(
        input = "&CloseCurlyDoubleQuote",
        expectedTokens = listOf(
            *chars("&CloseCurlyDoubleQuote").toTypedArray()
        )
    )

    @Test
    fun `Named entity CloseCurlyDoubleQuote with a semicolon`() = assertTokenization(
        input = "&CloseCurlyDoubleQuote;",
        expectedTokens = listOf(
            char('”')
        )
    )

    @Test
    fun `Bad named entity CloseCurlyQuote without a semicolon`() = assertTokenization(
        input = "&CloseCurlyQuote",
        expectedTokens = listOf(
            *chars("&CloseCurlyQuote").toTypedArray()
        )
    )

    @Test
    fun `Named entity CloseCurlyQuote with a semicolon`() = assertTokenization(
        input = "&CloseCurlyQuote;",
        expectedTokens = listOf(
            char('’')
        )
    )

    @Test
    fun `Bad named entity Colon without a semicolon`() = assertTokenization(
        input = "&Colon",
        expectedTokens = listOf(
            *chars("&Colon").toTypedArray()
        )
    )

    @Test
    fun `Named entity Colon with a semicolon`() = assertTokenization(
        input = "&Colon;",
        expectedTokens = listOf(
            char('∷')
        )
    )

    @Test
    fun `Bad named entity Colone without a semicolon`() = assertTokenization(
        input = "&Colone",
        expectedTokens = listOf(
            *chars("&Colone").toTypedArray()
        )
    )

    @Test
    fun `Named entity Colone with a semicolon`() = assertTokenization(
        input = "&Colone;",
        expectedTokens = listOf(
            char('⩴')
        )
    )

    @Test
    fun `Bad named entity Congruent without a semicolon`() = assertTokenization(
        input = "&Congruent",
        expectedTokens = listOf(
            *chars("&Congruent").toTypedArray()
        )
    )

    @Test
    fun `Named entity Congruent with a semicolon`() = assertTokenization(
        input = "&Congruent;",
        expectedTokens = listOf(
            char('≡')
        )
    )

    @Test
    fun `Bad named entity Conint without a semicolon`() = assertTokenization(
        input = "&Conint",
        expectedTokens = listOf(
            *chars("&Conint").toTypedArray()
        )
    )

    @Test
    fun `Named entity Conint with a semicolon`() = assertTokenization(
        input = "&Conint;",
        expectedTokens = listOf(
            char('∯')
        )
    )

    @Test
    fun `Bad named entity ContourIntegral without a semicolon`() = assertTokenization(
        input = "&ContourIntegral",
        expectedTokens = listOf(
            *chars("&ContourIntegral").toTypedArray()
        )
    )

    @Test
    fun `Named entity ContourIntegral with a semicolon`() = assertTokenization(
        input = "&ContourIntegral;",
        expectedTokens = listOf(
            char('∮')
        )
    )

    @Test
    fun `Bad named entity Copf without a semicolon`() = assertTokenization(
        input = "&Copf",
        expectedTokens = listOf(
            *chars("&Copf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Copf with a semicolon`() = assertTokenization(
        input = "&Copf;",
        expectedTokens = listOf(
            char('ℂ')
        )
    )

    @Test
    fun `Bad named entity Coproduct without a semicolon`() = assertTokenization(
        input = "&Coproduct",
        expectedTokens = listOf(
            *chars("&Coproduct").toTypedArray()
        )
    )

    @Test
    fun `Named entity Coproduct with a semicolon`() = assertTokenization(
        input = "&Coproduct;",
        expectedTokens = listOf(
            char('∐')
        )
    )

    @Test
    fun `Bad named entity CounterClockwiseContourIntegral without a semicolon`() = assertTokenization(
        input = "&CounterClockwiseContourIntegral",
        expectedTokens = listOf(
            *chars("&CounterClockwiseContourIntegral").toTypedArray()
        )
    )

    @Test
    fun `Named entity CounterClockwiseContourIntegral with a semicolon`() = assertTokenization(
        input = "&CounterClockwiseContourIntegral;",
        expectedTokens = listOf(
            char('∳')
        )
    )

    @Test
    fun `Bad named entity Cross without a semicolon`() = assertTokenization(
        input = "&Cross",
        expectedTokens = listOf(
            *chars("&Cross").toTypedArray()
        )
    )

    @Test
    fun `Named entity Cross with a semicolon`() = assertTokenization(
        input = "&Cross;",
        expectedTokens = listOf(
            char('⨯')
        )
    )

    @Test
    fun `Bad named entity Cscr without a semicolon`() = assertTokenization(
        input = "&Cscr",
        expectedTokens = listOf(
            *chars("&Cscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Cscr with a semicolon`() = assertTokenization(
        input = "&Cscr;",
        expectedTokens = listOf(
            *chars("𝒞").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Cup without a semicolon`() = assertTokenization(
        input = "&Cup",
        expectedTokens = listOf(
            *chars("&Cup").toTypedArray()
        )
    )

    @Test
    fun `Named entity Cup with a semicolon`() = assertTokenization(
        input = "&Cup;",
        expectedTokens = listOf(
            char('⋓')
        )
    )

    @Test
    fun `Bad named entity CupCap without a semicolon`() = assertTokenization(
        input = "&CupCap",
        expectedTokens = listOf(
            *chars("&CupCap").toTypedArray()
        )
    )

    @Test
    fun `Named entity CupCap with a semicolon`() = assertTokenization(
        input = "&CupCap;",
        expectedTokens = listOf(
            char('≍')
        )
    )

    @Test
    fun `Bad named entity DD without a semicolon`() = assertTokenization(
        input = "&DD",
        expectedTokens = listOf(
            *chars("&DD").toTypedArray()
        )
    )

    @Test
    fun `Named entity DD with a semicolon`() = assertTokenization(
        input = "&DD;",
        expectedTokens = listOf(
            char('ⅅ')
        )
    )

    @Test
    fun `Bad named entity DDotrahd without a semicolon`() = assertTokenization(
        input = "&DDotrahd",
        expectedTokens = listOf(
            *chars("&DDotrahd").toTypedArray()
        )
    )

    @Test
    fun `Named entity DDotrahd with a semicolon`() = assertTokenization(
        input = "&DDotrahd;",
        expectedTokens = listOf(
            char('⤑')
        )
    )

    @Test
    fun `Bad named entity DJcy without a semicolon`() = assertTokenization(
        input = "&DJcy",
        expectedTokens = listOf(
            *chars("&DJcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity DJcy with a semicolon`() = assertTokenization(
        input = "&DJcy;",
        expectedTokens = listOf(
            char('Ђ')
        )
    )

    @Test
    fun `Bad named entity DScy without a semicolon`() = assertTokenization(
        input = "&DScy",
        expectedTokens = listOf(
            *chars("&DScy").toTypedArray()
        )
    )

    @Test
    fun `Named entity DScy with a semicolon`() = assertTokenization(
        input = "&DScy;",
        expectedTokens = listOf(
            char('Ѕ')
        )
    )

    @Test
    fun `Bad named entity DZcy without a semicolon`() = assertTokenization(
        input = "&DZcy",
        expectedTokens = listOf(
            *chars("&DZcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity DZcy with a semicolon`() = assertTokenization(
        input = "&DZcy;",
        expectedTokens = listOf(
            char('Џ')
        )
    )

    @Test
    fun `Bad named entity Dagger without a semicolon`() = assertTokenization(
        input = "&Dagger",
        expectedTokens = listOf(
            *chars("&Dagger").toTypedArray()
        )
    )

    @Test
    fun `Named entity Dagger with a semicolon`() = assertTokenization(
        input = "&Dagger;",
        expectedTokens = listOf(
            char('‡')
        )
    )

    @Test
    fun `Bad named entity Darr without a semicolon`() = assertTokenization(
        input = "&Darr",
        expectedTokens = listOf(
            *chars("&Darr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Darr with a semicolon`() = assertTokenization(
        input = "&Darr;",
        expectedTokens = listOf(
            char('↡')
        )
    )

    @Test
    fun `Bad named entity Dashv without a semicolon`() = assertTokenization(
        input = "&Dashv",
        expectedTokens = listOf(
            *chars("&Dashv").toTypedArray()
        )
    )

    @Test
    fun `Named entity Dashv with a semicolon`() = assertTokenization(
        input = "&Dashv;",
        expectedTokens = listOf(
            char('⫤')
        )
    )

    @Test
    fun `Bad named entity Dcaron without a semicolon`() = assertTokenization(
        input = "&Dcaron",
        expectedTokens = listOf(
            *chars("&Dcaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity Dcaron with a semicolon`() = assertTokenization(
        input = "&Dcaron;",
        expectedTokens = listOf(
            char('Ď')
        )
    )

    @Test
    fun `Bad named entity Dcy without a semicolon`() = assertTokenization(
        input = "&Dcy",
        expectedTokens = listOf(
            *chars("&Dcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Dcy with a semicolon`() = assertTokenization(
        input = "&Dcy;",
        expectedTokens = listOf(
            char('Д')
        )
    )

    @Test
    fun `Bad named entity Del without a semicolon`() = assertTokenization(
        input = "&Del",
        expectedTokens = listOf(
            *chars("&Del").toTypedArray()
        )
    )

    @Test
    fun `Named entity Del with a semicolon`() = assertTokenization(
        input = "&Del;",
        expectedTokens = listOf(
            char('∇')
        )
    )

    @Test
    fun `Bad named entity Delta without a semicolon`() = assertTokenization(
        input = "&Delta",
        expectedTokens = listOf(
            *chars("&Delta").toTypedArray()
        )
    )

    @Test
    fun `Named entity Delta with a semicolon`() = assertTokenization(
        input = "&Delta;",
        expectedTokens = listOf(
            char('Δ')
        )
    )

    @Test
    fun `Bad named entity Dfr without a semicolon`() = assertTokenization(
        input = "&Dfr",
        expectedTokens = listOf(
            *chars("&Dfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Dfr with a semicolon`() = assertTokenization(
        input = "&Dfr;",
        expectedTokens = listOf(
            *chars("𝔇").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity DiacriticalAcute without a semicolon`() = assertTokenization(
        input = "&DiacriticalAcute",
        expectedTokens = listOf(
            *chars("&DiacriticalAcute").toTypedArray()
        )
    )

    @Test
    fun `Named entity DiacriticalAcute with a semicolon`() = assertTokenization(
        input = "&DiacriticalAcute;",
        expectedTokens = listOf(
            char('´')
        )
    )

    @Test
    fun `Bad named entity DiacriticalDot without a semicolon`() = assertTokenization(
        input = "&DiacriticalDot",
        expectedTokens = listOf(
            *chars("&DiacriticalDot").toTypedArray()
        )
    )

    @Test
    fun `Named entity DiacriticalDot with a semicolon`() = assertTokenization(
        input = "&DiacriticalDot;",
        expectedTokens = listOf(
            char('˙')
        )
    )

    @Test
    fun `Bad named entity DiacriticalDoubleAcute without a semicolon`() = assertTokenization(
        input = "&DiacriticalDoubleAcute",
        expectedTokens = listOf(
            *chars("&DiacriticalDoubleAcute").toTypedArray()
        )
    )

    @Test
    fun `Named entity DiacriticalDoubleAcute with a semicolon`() = assertTokenization(
        input = "&DiacriticalDoubleAcute;",
        expectedTokens = listOf(
            char('˝')
        )
    )

    @Test
    fun `Bad named entity DiacriticalGrave without a semicolon`() = assertTokenization(
        input = "&DiacriticalGrave",
        expectedTokens = listOf(
            *chars("&DiacriticalGrave").toTypedArray()
        )
    )

    @Test
    fun `Named entity DiacriticalGrave with a semicolon`() = assertTokenization(
        input = "&DiacriticalGrave;",
        expectedTokens = listOf(
            char('`')
        )
    )

    @Test
    fun `Bad named entity DiacriticalTilde without a semicolon`() = assertTokenization(
        input = "&DiacriticalTilde",
        expectedTokens = listOf(
            *chars("&DiacriticalTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity DiacriticalTilde with a semicolon`() = assertTokenization(
        input = "&DiacriticalTilde;",
        expectedTokens = listOf(
            char('˜')
        )
    )

    @Test
    fun `Bad named entity Diamond without a semicolon`() = assertTokenization(
        input = "&Diamond",
        expectedTokens = listOf(
            *chars("&Diamond").toTypedArray()
        )
    )

    @Test
    fun `Named entity Diamond with a semicolon`() = assertTokenization(
        input = "&Diamond;",
        expectedTokens = listOf(
            char('⋄')
        )
    )

    @Test
    fun `Bad named entity DifferentialD without a semicolon`() = assertTokenization(
        input = "&DifferentialD",
        expectedTokens = listOf(
            *chars("&DifferentialD").toTypedArray()
        )
    )

    @Test
    fun `Named entity DifferentialD with a semicolon`() = assertTokenization(
        input = "&DifferentialD;",
        expectedTokens = listOf(
            char('ⅆ')
        )
    )

    @Test
    fun `Bad named entity Dopf without a semicolon`() = assertTokenization(
        input = "&Dopf",
        expectedTokens = listOf(
            *chars("&Dopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Dopf with a semicolon`() = assertTokenization(
        input = "&Dopf;",
        expectedTokens = listOf(
            *chars("𝔻").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Dot without a semicolon`() = assertTokenization(
        input = "&Dot",
        expectedTokens = listOf(
            *chars("&Dot").toTypedArray()
        )
    )

    @Test
    fun `Named entity Dot with a semicolon`() = assertTokenization(
        input = "&Dot;",
        expectedTokens = listOf(
            char('¨')
        )
    )

    @Test
    fun `Bad named entity DotDot without a semicolon`() = assertTokenization(
        input = "&DotDot",
        expectedTokens = listOf(
            *chars("&DotDot").toTypedArray()
        )
    )

    @Test
    fun `Named entity DotDot with a semicolon`() = assertTokenization(
        input = "&DotDot;",
        expectedTokens = listOf(
            char('⃜')
        )
    )

    @Test
    fun `Bad named entity DotEqual without a semicolon`() = assertTokenization(
        input = "&DotEqual",
        expectedTokens = listOf(
            *chars("&DotEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity DotEqual with a semicolon`() = assertTokenization(
        input = "&DotEqual;",
        expectedTokens = listOf(
            char('≐')
        )
    )

    @Test
    fun `Bad named entity DoubleContourIntegral without a semicolon`() = assertTokenization(
        input = "&DoubleContourIntegral",
        expectedTokens = listOf(
            *chars("&DoubleContourIntegral").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleContourIntegral with a semicolon`() = assertTokenization(
        input = "&DoubleContourIntegral;",
        expectedTokens = listOf(
            char('∯')
        )
    )

    @Test
    fun `Bad named entity DoubleDot without a semicolon`() = assertTokenization(
        input = "&DoubleDot",
        expectedTokens = listOf(
            *chars("&DoubleDot").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleDot with a semicolon`() = assertTokenization(
        input = "&DoubleDot;",
        expectedTokens = listOf(
            char('¨')
        )
    )

    @Test
    fun `Bad named entity DoubleDownArrow without a semicolon`() = assertTokenization(
        input = "&DoubleDownArrow",
        expectedTokens = listOf(
            *chars("&DoubleDownArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleDownArrow with a semicolon`() = assertTokenization(
        input = "&DoubleDownArrow;",
        expectedTokens = listOf(
            char('⇓')
        )
    )

    @Test
    fun `Bad named entity DoubleLeftArrow without a semicolon`() = assertTokenization(
        input = "&DoubleLeftArrow",
        expectedTokens = listOf(
            *chars("&DoubleLeftArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleLeftArrow with a semicolon`() = assertTokenization(
        input = "&DoubleLeftArrow;",
        expectedTokens = listOf(
            char('⇐')
        )
    )

    @Test
    fun `Bad named entity DoubleLeftRightArrow without a semicolon`() = assertTokenization(
        input = "&DoubleLeftRightArrow",
        expectedTokens = listOf(
            *chars("&DoubleLeftRightArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleLeftRightArrow with a semicolon`() = assertTokenization(
        input = "&DoubleLeftRightArrow;",
        expectedTokens = listOf(
            char('⇔')
        )
    )

    @Test
    fun `Bad named entity DoubleLeftTee without a semicolon`() = assertTokenization(
        input = "&DoubleLeftTee",
        expectedTokens = listOf(
            *chars("&DoubleLeftTee").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleLeftTee with a semicolon`() = assertTokenization(
        input = "&DoubleLeftTee;",
        expectedTokens = listOf(
            char('⫤')
        )
    )

    @Test
    fun `Bad named entity DoubleLongLeftArrow without a semicolon`() = assertTokenization(
        input = "&DoubleLongLeftArrow",
        expectedTokens = listOf(
            *chars("&DoubleLongLeftArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleLongLeftArrow with a semicolon`() = assertTokenization(
        input = "&DoubleLongLeftArrow;",
        expectedTokens = listOf(
            char('⟸')
        )
    )

    @Test
    fun `Bad named entity DoubleLongLeftRightArrow without a semicolon`() = assertTokenization(
        input = "&DoubleLongLeftRightArrow",
        expectedTokens = listOf(
            *chars("&DoubleLongLeftRightArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleLongLeftRightArrow with a semicolon`() = assertTokenization(
        input = "&DoubleLongLeftRightArrow;",
        expectedTokens = listOf(
            char('⟺')
        )
    )

    @Test
    fun `Bad named entity DoubleLongRightArrow without a semicolon`() = assertTokenization(
        input = "&DoubleLongRightArrow",
        expectedTokens = listOf(
            *chars("&DoubleLongRightArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleLongRightArrow with a semicolon`() = assertTokenization(
        input = "&DoubleLongRightArrow;",
        expectedTokens = listOf(
            char('⟹')
        )
    )

    @Test
    fun `Bad named entity DoubleRightArrow without a semicolon`() = assertTokenization(
        input = "&DoubleRightArrow",
        expectedTokens = listOf(
            *chars("&DoubleRightArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleRightArrow with a semicolon`() = assertTokenization(
        input = "&DoubleRightArrow;",
        expectedTokens = listOf(
            char('⇒')
        )
    )

    @Test
    fun `Bad named entity DoubleRightTee without a semicolon`() = assertTokenization(
        input = "&DoubleRightTee",
        expectedTokens = listOf(
            *chars("&DoubleRightTee").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleRightTee with a semicolon`() = assertTokenization(
        input = "&DoubleRightTee;",
        expectedTokens = listOf(
            char('⊨')
        )
    )

    @Test
    fun `Bad named entity DoubleUpArrow without a semicolon`() = assertTokenization(
        input = "&DoubleUpArrow",
        expectedTokens = listOf(
            *chars("&DoubleUpArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleUpArrow with a semicolon`() = assertTokenization(
        input = "&DoubleUpArrow;",
        expectedTokens = listOf(
            char('⇑')
        )
    )

    @Test
    fun `Bad named entity DoubleUpDownArrow without a semicolon`() = assertTokenization(
        input = "&DoubleUpDownArrow",
        expectedTokens = listOf(
            *chars("&DoubleUpDownArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleUpDownArrow with a semicolon`() = assertTokenization(
        input = "&DoubleUpDownArrow;",
        expectedTokens = listOf(
            char('⇕')
        )
    )

    @Test
    fun `Bad named entity DoubleVerticalBar without a semicolon`() = assertTokenization(
        input = "&DoubleVerticalBar",
        expectedTokens = listOf(
            *chars("&DoubleVerticalBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity DoubleVerticalBar with a semicolon`() = assertTokenization(
        input = "&DoubleVerticalBar;",
        expectedTokens = listOf(
            char('∥')
        )
    )

    @Test
    fun `Bad named entity DownArrow without a semicolon`() = assertTokenization(
        input = "&DownArrow",
        expectedTokens = listOf(
            *chars("&DownArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity DownArrow with a semicolon`() = assertTokenization(
        input = "&DownArrow;",
        expectedTokens = listOf(
            char('↓')
        )
    )

    @Test
    fun `Bad named entity DownArrowBar without a semicolon`() = assertTokenization(
        input = "&DownArrowBar",
        expectedTokens = listOf(
            *chars("&DownArrowBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity DownArrowBar with a semicolon`() = assertTokenization(
        input = "&DownArrowBar;",
        expectedTokens = listOf(
            char('⤓')
        )
    )

    @Test
    fun `Bad named entity DownArrowUpArrow without a semicolon`() = assertTokenization(
        input = "&DownArrowUpArrow",
        expectedTokens = listOf(
            *chars("&DownArrowUpArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity DownArrowUpArrow with a semicolon`() = assertTokenization(
        input = "&DownArrowUpArrow;",
        expectedTokens = listOf(
            char('⇵')
        )
    )

    @Test
    fun `Bad named entity DownBreve without a semicolon`() = assertTokenization(
        input = "&DownBreve",
        expectedTokens = listOf(
            *chars("&DownBreve").toTypedArray()
        )
    )

    @Test
    fun `Named entity DownBreve with a semicolon`() = assertTokenization(
        input = "&DownBreve;",
        expectedTokens = listOf(
            char('̑')
        )
    )

    @Test
    fun `Bad named entity DownLeftRightVector without a semicolon`() = assertTokenization(
        input = "&DownLeftRightVector",
        expectedTokens = listOf(
            *chars("&DownLeftRightVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity DownLeftRightVector with a semicolon`() = assertTokenization(
        input = "&DownLeftRightVector;",
        expectedTokens = listOf(
            char('⥐')
        )
    )

    @Test
    fun `Bad named entity DownLeftTeeVector without a semicolon`() = assertTokenization(
        input = "&DownLeftTeeVector",
        expectedTokens = listOf(
            *chars("&DownLeftTeeVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity DownLeftTeeVector with a semicolon`() = assertTokenization(
        input = "&DownLeftTeeVector;",
        expectedTokens = listOf(
            char('⥞')
        )
    )

    @Test
    fun `Bad named entity DownLeftVector without a semicolon`() = assertTokenization(
        input = "&DownLeftVector",
        expectedTokens = listOf(
            *chars("&DownLeftVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity DownLeftVector with a semicolon`() = assertTokenization(
        input = "&DownLeftVector;",
        expectedTokens = listOf(
            char('↽')
        )
    )

    @Test
    fun `Bad named entity DownLeftVectorBar without a semicolon`() = assertTokenization(
        input = "&DownLeftVectorBar",
        expectedTokens = listOf(
            *chars("&DownLeftVectorBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity DownLeftVectorBar with a semicolon`() = assertTokenization(
        input = "&DownLeftVectorBar;",
        expectedTokens = listOf(
            char('⥖')
        )
    )

    @Test
    fun `Bad named entity DownRightTeeVector without a semicolon`() = assertTokenization(
        input = "&DownRightTeeVector",
        expectedTokens = listOf(
            *chars("&DownRightTeeVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity DownRightTeeVector with a semicolon`() = assertTokenization(
        input = "&DownRightTeeVector;",
        expectedTokens = listOf(
            char('⥟')
        )
    )

    @Test
    fun `Bad named entity DownRightVector without a semicolon`() = assertTokenization(
        input = "&DownRightVector",
        expectedTokens = listOf(
            *chars("&DownRightVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity DownRightVector with a semicolon`() = assertTokenization(
        input = "&DownRightVector;",
        expectedTokens = listOf(
            char('⇁')
        )
    )

    @Test
    fun `Bad named entity DownRightVectorBar without a semicolon`() = assertTokenization(
        input = "&DownRightVectorBar",
        expectedTokens = listOf(
            *chars("&DownRightVectorBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity DownRightVectorBar with a semicolon`() = assertTokenization(
        input = "&DownRightVectorBar;",
        expectedTokens = listOf(
            char('⥗')
        )
    )

    @Test
    fun `Bad named entity DownTee without a semicolon`() = assertTokenization(
        input = "&DownTee",
        expectedTokens = listOf(
            *chars("&DownTee").toTypedArray()
        )
    )

    @Test
    fun `Named entity DownTee with a semicolon`() = assertTokenization(
        input = "&DownTee;",
        expectedTokens = listOf(
            char('⊤')
        )
    )

    @Test
    fun `Bad named entity DownTeeArrow without a semicolon`() = assertTokenization(
        input = "&DownTeeArrow",
        expectedTokens = listOf(
            *chars("&DownTeeArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity DownTeeArrow with a semicolon`() = assertTokenization(
        input = "&DownTeeArrow;",
        expectedTokens = listOf(
            char('↧')
        )
    )

    @Test
    fun `Bad named entity Downarrow without a semicolon`() = assertTokenization(
        input = "&Downarrow",
        expectedTokens = listOf(
            *chars("&Downarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity Downarrow with a semicolon`() = assertTokenization(
        input = "&Downarrow;",
        expectedTokens = listOf(
            char('⇓')
        )
    )

    @Test
    fun `Bad named entity Dscr without a semicolon`() = assertTokenization(
        input = "&Dscr",
        expectedTokens = listOf(
            *chars("&Dscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Dscr with a semicolon`() = assertTokenization(
        input = "&Dscr;",
        expectedTokens = listOf(
            *chars("𝒟").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Dstrok without a semicolon`() = assertTokenization(
        input = "&Dstrok",
        expectedTokens = listOf(
            *chars("&Dstrok").toTypedArray()
        )
    )

    @Test
    fun `Named entity Dstrok with a semicolon`() = assertTokenization(
        input = "&Dstrok;",
        expectedTokens = listOf(
            char('Đ')
        )
    )

    @Test
    fun `Bad named entity ENG without a semicolon`() = assertTokenization(
        input = "&ENG",
        expectedTokens = listOf(
            *chars("&ENG").toTypedArray()
        )
    )

    @Test
    fun `Named entity ENG with a semicolon`() = assertTokenization(
        input = "&ENG;",
        expectedTokens = listOf(
            char('Ŋ')
        )
    )

    @Test
    fun `Named entity ETH without a semicolon`() = assertTokenization(
        input = "&ETH",
        expectedTokens = listOf(
            char('Ð')
        )
    )

    @Test
    fun `Named entity ETH with a semicolon`() = assertTokenization(
        input = "&ETH;",
        expectedTokens = listOf(
            char('Ð')
        )
    )

    @Test
    fun `Named entity Eacute without a semicolon`() = assertTokenization(
        input = "&Eacute",
        expectedTokens = listOf(
            char('É')
        )
    )

    @Test
    fun `Named entity Eacute with a semicolon`() = assertTokenization(
        input = "&Eacute;",
        expectedTokens = listOf(
            char('É')
        )
    )

    @Test
    fun `Bad named entity Ecaron without a semicolon`() = assertTokenization(
        input = "&Ecaron",
        expectedTokens = listOf(
            *chars("&Ecaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ecaron with a semicolon`() = assertTokenization(
        input = "&Ecaron;",
        expectedTokens = listOf(
            char('Ě')
        )
    )

    @Test
    fun `Named entity Ecirc without a semicolon`() = assertTokenization(
        input = "&Ecirc",
        expectedTokens = listOf(
            char('Ê')
        )
    )

    @Test
    fun `Named entity Ecirc with a semicolon`() = assertTokenization(
        input = "&Ecirc;",
        expectedTokens = listOf(
            char('Ê')
        )
    )

    @Test
    fun `Bad named entity Ecy without a semicolon`() = assertTokenization(
        input = "&Ecy",
        expectedTokens = listOf(
            *chars("&Ecy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ecy with a semicolon`() = assertTokenization(
        input = "&Ecy;",
        expectedTokens = listOf(
            char('Э')
        )
    )

    @Test
    fun `Bad named entity Edot without a semicolon`() = assertTokenization(
        input = "&Edot",
        expectedTokens = listOf(
            *chars("&Edot").toTypedArray()
        )
    )

    @Test
    fun `Named entity Edot with a semicolon`() = assertTokenization(
        input = "&Edot;",
        expectedTokens = listOf(
            char('Ė')
        )
    )

    @Test
    fun `Bad named entity Efr without a semicolon`() = assertTokenization(
        input = "&Efr",
        expectedTokens = listOf(
            *chars("&Efr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Efr with a semicolon`() = assertTokenization(
        input = "&Efr;",
        expectedTokens = listOf(
            *chars("𝔈").toTypedArray()
        )
    )

    @Test
    fun `Named entity Egrave without a semicolon`() = assertTokenization(
        input = "&Egrave",
        expectedTokens = listOf(
            char('È')
        )
    )

    @Test
    fun `Named entity Egrave with a semicolon`() = assertTokenization(
        input = "&Egrave;",
        expectedTokens = listOf(
            char('È')
        )
    )

    @Test
    fun `Bad named entity Element without a semicolon`() = assertTokenization(
        input = "&Element",
        expectedTokens = listOf(
            *chars("&Element").toTypedArray()
        )
    )

    @Test
    fun `Named entity Element with a semicolon`() = assertTokenization(
        input = "&Element;",
        expectedTokens = listOf(
            char('∈')
        )
    )

    @Test
    fun `Bad named entity Emacr without a semicolon`() = assertTokenization(
        input = "&Emacr",
        expectedTokens = listOf(
            *chars("&Emacr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Emacr with a semicolon`() = assertTokenization(
        input = "&Emacr;",
        expectedTokens = listOf(
            char('Ē')
        )
    )

    @Test
    fun `Bad named entity EmptySmallSquare without a semicolon`() = assertTokenization(
        input = "&EmptySmallSquare",
        expectedTokens = listOf(
            *chars("&EmptySmallSquare").toTypedArray()
        )
    )

    @Test
    fun `Named entity EmptySmallSquare with a semicolon`() = assertTokenization(
        input = "&EmptySmallSquare;",
        expectedTokens = listOf(
            char('◻')
        )
    )

    @Test
    fun `Bad named entity EmptyVerySmallSquare without a semicolon`() = assertTokenization(
        input = "&EmptyVerySmallSquare",
        expectedTokens = listOf(
            *chars("&EmptyVerySmallSquare").toTypedArray()
        )
    )

    @Test
    fun `Named entity EmptyVerySmallSquare with a semicolon`() = assertTokenization(
        input = "&EmptyVerySmallSquare;",
        expectedTokens = listOf(
            char('▫')
        )
    )

    @Test
    fun `Bad named entity Eogon without a semicolon`() = assertTokenization(
        input = "&Eogon",
        expectedTokens = listOf(
            *chars("&Eogon").toTypedArray()
        )
    )

    @Test
    fun `Named entity Eogon with a semicolon`() = assertTokenization(
        input = "&Eogon;",
        expectedTokens = listOf(
            char('Ę')
        )
    )

    @Test
    fun `Bad named entity Eopf without a semicolon`() = assertTokenization(
        input = "&Eopf",
        expectedTokens = listOf(
            *chars("&Eopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Eopf with a semicolon`() = assertTokenization(
        input = "&Eopf;",
        expectedTokens = listOf(
            *chars("𝔼").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Epsilon without a semicolon`() = assertTokenization(
        input = "&Epsilon",
        expectedTokens = listOf(
            *chars("&Epsilon").toTypedArray()
        )
    )

    @Test
    fun `Named entity Epsilon with a semicolon`() = assertTokenization(
        input = "&Epsilon;",
        expectedTokens = listOf(
            char('Ε')
        )
    )

    @Test
    fun `Bad named entity Equal without a semicolon`() = assertTokenization(
        input = "&Equal",
        expectedTokens = listOf(
            *chars("&Equal").toTypedArray()
        )
    )

    @Test
    fun `Named entity Equal with a semicolon`() = assertTokenization(
        input = "&Equal;",
        expectedTokens = listOf(
            char('⩵')
        )
    )

    @Test
    fun `Bad named entity EqualTilde without a semicolon`() = assertTokenization(
        input = "&EqualTilde",
        expectedTokens = listOf(
            *chars("&EqualTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity EqualTilde with a semicolon`() = assertTokenization(
        input = "&EqualTilde;",
        expectedTokens = listOf(
            char('≂')
        )
    )

    @Test
    fun `Bad named entity Equilibrium without a semicolon`() = assertTokenization(
        input = "&Equilibrium",
        expectedTokens = listOf(
            *chars("&Equilibrium").toTypedArray()
        )
    )

    @Test
    fun `Named entity Equilibrium with a semicolon`() = assertTokenization(
        input = "&Equilibrium;",
        expectedTokens = listOf(
            char('⇌')
        )
    )

    @Test
    fun `Bad named entity Escr without a semicolon`() = assertTokenization(
        input = "&Escr",
        expectedTokens = listOf(
            *chars("&Escr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Escr with a semicolon`() = assertTokenization(
        input = "&Escr;",
        expectedTokens = listOf(
            char('ℰ')
        )
    )

    @Test
    fun `Bad named entity Esim without a semicolon`() = assertTokenization(
        input = "&Esim",
        expectedTokens = listOf(
            *chars("&Esim").toTypedArray()
        )
    )

    @Test
    fun `Named entity Esim with a semicolon`() = assertTokenization(
        input = "&Esim;",
        expectedTokens = listOf(
            char('⩳')
        )
    )

    @Test
    fun `Bad named entity Eta without a semicolon`() = assertTokenization(
        input = "&Eta",
        expectedTokens = listOf(
            *chars("&Eta").toTypedArray()
        )
    )

    @Test
    fun `Named entity Eta with a semicolon`() = assertTokenization(
        input = "&Eta;",
        expectedTokens = listOf(
            char('Η')
        )
    )

    @Test
    fun `Named entity Euml without a semicolon`() = assertTokenization(
        input = "&Euml",
        expectedTokens = listOf(
            char('Ë')
        )
    )

    @Test
    fun `Named entity Euml with a semicolon`() = assertTokenization(
        input = "&Euml;",
        expectedTokens = listOf(
            char('Ë')
        )
    )

    @Test
    fun `Bad named entity Exists without a semicolon`() = assertTokenization(
        input = "&Exists",
        expectedTokens = listOf(
            *chars("&Exists").toTypedArray()
        )
    )

    @Test
    fun `Named entity Exists with a semicolon`() = assertTokenization(
        input = "&Exists;",
        expectedTokens = listOf(
            char('∃')
        )
    )

    @Test
    fun `Bad named entity ExponentialE without a semicolon`() = assertTokenization(
        input = "&ExponentialE",
        expectedTokens = listOf(
            *chars("&ExponentialE").toTypedArray()
        )
    )

    @Test
    fun `Named entity ExponentialE with a semicolon`() = assertTokenization(
        input = "&ExponentialE;",
        expectedTokens = listOf(
            char('ⅇ')
        )
    )

    @Test
    fun `Bad named entity Fcy without a semicolon`() = assertTokenization(
        input = "&Fcy",
        expectedTokens = listOf(
            *chars("&Fcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Fcy with a semicolon`() = assertTokenization(
        input = "&Fcy;",
        expectedTokens = listOf(
            char('Ф')
        )
    )

    @Test
    fun `Bad named entity Ffr without a semicolon`() = assertTokenization(
        input = "&Ffr",
        expectedTokens = listOf(
            *chars("&Ffr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ffr with a semicolon`() = assertTokenization(
        input = "&Ffr;",
        expectedTokens = listOf(
            *chars("𝔉").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity FilledSmallSquare without a semicolon`() = assertTokenization(
        input = "&FilledSmallSquare",
        expectedTokens = listOf(
            *chars("&FilledSmallSquare").toTypedArray()
        )
    )

    @Test
    fun `Named entity FilledSmallSquare with a semicolon`() = assertTokenization(
        input = "&FilledSmallSquare;",
        expectedTokens = listOf(
            char('◼')
        )
    )

    @Test
    fun `Bad named entity FilledVerySmallSquare without a semicolon`() = assertTokenization(
        input = "&FilledVerySmallSquare",
        expectedTokens = listOf(
            *chars("&FilledVerySmallSquare").toTypedArray()
        )
    )

    @Test
    fun `Named entity FilledVerySmallSquare with a semicolon`() = assertTokenization(
        input = "&FilledVerySmallSquare;",
        expectedTokens = listOf(
            char('▪')
        )
    )

    @Test
    fun `Bad named entity Fopf without a semicolon`() = assertTokenization(
        input = "&Fopf",
        expectedTokens = listOf(
            *chars("&Fopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Fopf with a semicolon`() = assertTokenization(
        input = "&Fopf;",
        expectedTokens = listOf(
            *chars("𝔽").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ForAll without a semicolon`() = assertTokenization(
        input = "&ForAll",
        expectedTokens = listOf(
            *chars("&ForAll").toTypedArray()
        )
    )

    @Test
    fun `Named entity ForAll with a semicolon`() = assertTokenization(
        input = "&ForAll;",
        expectedTokens = listOf(
            char('∀')
        )
    )

    @Test
    fun `Bad named entity Fouriertrf without a semicolon`() = assertTokenization(
        input = "&Fouriertrf",
        expectedTokens = listOf(
            *chars("&Fouriertrf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Fouriertrf with a semicolon`() = assertTokenization(
        input = "&Fouriertrf;",
        expectedTokens = listOf(
            char('ℱ')
        )
    )

    @Test
    fun `Bad named entity Fscr without a semicolon`() = assertTokenization(
        input = "&Fscr",
        expectedTokens = listOf(
            *chars("&Fscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Fscr with a semicolon`() = assertTokenization(
        input = "&Fscr;",
        expectedTokens = listOf(
            char('ℱ')
        )
    )

    @Test
    fun `Bad named entity GJcy without a semicolon`() = assertTokenization(
        input = "&GJcy",
        expectedTokens = listOf(
            *chars("&GJcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity GJcy with a semicolon`() = assertTokenization(
        input = "&GJcy;",
        expectedTokens = listOf(
            char('Ѓ')
        )
    )

    @Test
    fun `Named entity GT without a semicolon`() = assertTokenization(
        input = "&GT",
        expectedTokens = listOf(
            char('>')
        )
    )

    @Test
    fun `Named entity GT with a semicolon`() = assertTokenization(
        input = "&GT;",
        expectedTokens = listOf(
            char('>')
        )
    )

    @Test
    fun `Bad named entity Gamma without a semicolon`() = assertTokenization(
        input = "&Gamma",
        expectedTokens = listOf(
            *chars("&Gamma").toTypedArray()
        )
    )

    @Test
    fun `Named entity Gamma with a semicolon`() = assertTokenization(
        input = "&Gamma;",
        expectedTokens = listOf(
            char('Γ')
        )
    )

    @Test
    fun `Bad named entity Gammad without a semicolon`() = assertTokenization(
        input = "&Gammad",
        expectedTokens = listOf(
            *chars("&Gammad").toTypedArray()
        )
    )

    @Test
    fun `Named entity Gammad with a semicolon`() = assertTokenization(
        input = "&Gammad;",
        expectedTokens = listOf(
            char('Ϝ')
        )
    )

    @Test
    fun `Bad named entity Gbreve without a semicolon`() = assertTokenization(
        input = "&Gbreve",
        expectedTokens = listOf(
            *chars("&Gbreve").toTypedArray()
        )
    )

    @Test
    fun `Named entity Gbreve with a semicolon`() = assertTokenization(
        input = "&Gbreve;",
        expectedTokens = listOf(
            char('Ğ')
        )
    )

    @Test
    fun `Bad named entity Gcedil without a semicolon`() = assertTokenization(
        input = "&Gcedil",
        expectedTokens = listOf(
            *chars("&Gcedil").toTypedArray()
        )
    )

    @Test
    fun `Named entity Gcedil with a semicolon`() = assertTokenization(
        input = "&Gcedil;",
        expectedTokens = listOf(
            char('Ģ')
        )
    )

    @Test
    fun `Bad named entity Gcirc without a semicolon`() = assertTokenization(
        input = "&Gcirc",
        expectedTokens = listOf(
            *chars("&Gcirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity Gcirc with a semicolon`() = assertTokenization(
        input = "&Gcirc;",
        expectedTokens = listOf(
            char('Ĝ')
        )
    )

    @Test
    fun `Bad named entity Gcy without a semicolon`() = assertTokenization(
        input = "&Gcy",
        expectedTokens = listOf(
            *chars("&Gcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Gcy with a semicolon`() = assertTokenization(
        input = "&Gcy;",
        expectedTokens = listOf(
            char('Г')
        )
    )

    @Test
    fun `Bad named entity Gdot without a semicolon`() = assertTokenization(
        input = "&Gdot",
        expectedTokens = listOf(
            *chars("&Gdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity Gdot with a semicolon`() = assertTokenization(
        input = "&Gdot;",
        expectedTokens = listOf(
            char('Ġ')
        )
    )

    @Test
    fun `Bad named entity Gfr without a semicolon`() = assertTokenization(
        input = "&Gfr",
        expectedTokens = listOf(
            *chars("&Gfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Gfr with a semicolon`() = assertTokenization(
        input = "&Gfr;",
        expectedTokens = listOf(
            *chars("𝔊").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Gg without a semicolon`() = assertTokenization(
        input = "&Gg",
        expectedTokens = listOf(
            *chars("&Gg").toTypedArray()
        )
    )

    @Test
    fun `Named entity Gg with a semicolon`() = assertTokenization(
        input = "&Gg;",
        expectedTokens = listOf(
            char('⋙')
        )
    )

    @Test
    fun `Bad named entity Gopf without a semicolon`() = assertTokenization(
        input = "&Gopf",
        expectedTokens = listOf(
            *chars("&Gopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Gopf with a semicolon`() = assertTokenization(
        input = "&Gopf;",
        expectedTokens = listOf(
            *chars("𝔾").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity GreaterEqual without a semicolon`() = assertTokenization(
        input = "&GreaterEqual",
        expectedTokens = listOf(
            *chars("&GreaterEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity GreaterEqual with a semicolon`() = assertTokenization(
        input = "&GreaterEqual;",
        expectedTokens = listOf(
            char('≥')
        )
    )

    @Test
    fun `Bad named entity GreaterEqualLess without a semicolon`() = assertTokenization(
        input = "&GreaterEqualLess",
        expectedTokens = listOf(
            *chars("&GreaterEqualLess").toTypedArray()
        )
    )

    @Test
    fun `Named entity GreaterEqualLess with a semicolon`() = assertTokenization(
        input = "&GreaterEqualLess;",
        expectedTokens = listOf(
            char('⋛')
        )
    )

    @Test
    fun `Bad named entity GreaterFullEqual without a semicolon`() = assertTokenization(
        input = "&GreaterFullEqual",
        expectedTokens = listOf(
            *chars("&GreaterFullEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity GreaterFullEqual with a semicolon`() = assertTokenization(
        input = "&GreaterFullEqual;",
        expectedTokens = listOf(
            char('≧')
        )
    )

    @Test
    fun `Bad named entity GreaterGreater without a semicolon`() = assertTokenization(
        input = "&GreaterGreater",
        expectedTokens = listOf(
            *chars("&GreaterGreater").toTypedArray()
        )
    )

    @Test
    fun `Named entity GreaterGreater with a semicolon`() = assertTokenization(
        input = "&GreaterGreater;",
        expectedTokens = listOf(
            char('⪢')
        )
    )

    @Test
    fun `Bad named entity GreaterLess without a semicolon`() = assertTokenization(
        input = "&GreaterLess",
        expectedTokens = listOf(
            *chars("&GreaterLess").toTypedArray()
        )
    )

    @Test
    fun `Named entity GreaterLess with a semicolon`() = assertTokenization(
        input = "&GreaterLess;",
        expectedTokens = listOf(
            char('≷')
        )
    )

    @Test
    fun `Bad named entity GreaterSlantEqual without a semicolon`() = assertTokenization(
        input = "&GreaterSlantEqual",
        expectedTokens = listOf(
            *chars("&GreaterSlantEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity GreaterSlantEqual with a semicolon`() = assertTokenization(
        input = "&GreaterSlantEqual;",
        expectedTokens = listOf(
            char('⩾')
        )
    )

    @Test
    fun `Bad named entity GreaterTilde without a semicolon`() = assertTokenization(
        input = "&GreaterTilde",
        expectedTokens = listOf(
            *chars("&GreaterTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity GreaterTilde with a semicolon`() = assertTokenization(
        input = "&GreaterTilde;",
        expectedTokens = listOf(
            char('≳')
        )
    )

    @Test
    fun `Bad named entity Gscr without a semicolon`() = assertTokenization(
        input = "&Gscr",
        expectedTokens = listOf(
            *chars("&Gscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Gscr with a semicolon`() = assertTokenization(
        input = "&Gscr;",
        expectedTokens = listOf(
            *chars("𝒢").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Gt without a semicolon`() = assertTokenization(
        input = "&Gt",
        expectedTokens = listOf(
            *chars("&Gt").toTypedArray()
        )
    )

    @Test
    fun `Named entity Gt with a semicolon`() = assertTokenization(
        input = "&Gt;",
        expectedTokens = listOf(
            char('≫')
        )
    )

    @Test
    fun `Bad named entity HARDcy without a semicolon`() = assertTokenization(
        input = "&HARDcy",
        expectedTokens = listOf(
            *chars("&HARDcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity HARDcy with a semicolon`() = assertTokenization(
        input = "&HARDcy;",
        expectedTokens = listOf(
            char('Ъ')
        )
    )

    @Test
    fun `Bad named entity Hacek without a semicolon`() = assertTokenization(
        input = "&Hacek",
        expectedTokens = listOf(
            *chars("&Hacek").toTypedArray()
        )
    )

    @Test
    fun `Named entity Hacek with a semicolon`() = assertTokenization(
        input = "&Hacek;",
        expectedTokens = listOf(
            char('ˇ')
        )
    )

    @Test
    fun `Bad named entity Hat without a semicolon`() = assertTokenization(
        input = "&Hat",
        expectedTokens = listOf(
            *chars("&Hat").toTypedArray()
        )
    )

    @Test
    fun `Named entity Hat with a semicolon`() = assertTokenization(
        input = "&Hat;",
        expectedTokens = listOf(
            char('^')
        )
    )

    @Test
    fun `Bad named entity Hcirc without a semicolon`() = assertTokenization(
        input = "&Hcirc",
        expectedTokens = listOf(
            *chars("&Hcirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity Hcirc with a semicolon`() = assertTokenization(
        input = "&Hcirc;",
        expectedTokens = listOf(
            char('Ĥ')
        )
    )

    @Test
    fun `Bad named entity Hfr without a semicolon`() = assertTokenization(
        input = "&Hfr",
        expectedTokens = listOf(
            *chars("&Hfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Hfr with a semicolon`() = assertTokenization(
        input = "&Hfr;",
        expectedTokens = listOf(
            char('ℌ')
        )
    )

    @Test
    fun `Bad named entity HilbertSpace without a semicolon`() = assertTokenization(
        input = "&HilbertSpace",
        expectedTokens = listOf(
            *chars("&HilbertSpace").toTypedArray()
        )
    )

    @Test
    fun `Named entity HilbertSpace with a semicolon`() = assertTokenization(
        input = "&HilbertSpace;",
        expectedTokens = listOf(
            char('ℋ')
        )
    )

    @Test
    fun `Bad named entity Hopf without a semicolon`() = assertTokenization(
        input = "&Hopf",
        expectedTokens = listOf(
            *chars("&Hopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Hopf with a semicolon`() = assertTokenization(
        input = "&Hopf;",
        expectedTokens = listOf(
            char('ℍ')
        )
    )

    @Test
    fun `Bad named entity HorizontalLine without a semicolon`() = assertTokenization(
        input = "&HorizontalLine",
        expectedTokens = listOf(
            *chars("&HorizontalLine").toTypedArray()
        )
    )

    @Test
    fun `Named entity HorizontalLine with a semicolon`() = assertTokenization(
        input = "&HorizontalLine;",
        expectedTokens = listOf(
            char('─')
        )
    )

    @Test
    fun `Bad named entity Hscr without a semicolon`() = assertTokenization(
        input = "&Hscr",
        expectedTokens = listOf(
            *chars("&Hscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Hscr with a semicolon`() = assertTokenization(
        input = "&Hscr;",
        expectedTokens = listOf(
            char('ℋ')
        )
    )

    @Test
    fun `Bad named entity Hstrok without a semicolon`() = assertTokenization(
        input = "&Hstrok",
        expectedTokens = listOf(
            *chars("&Hstrok").toTypedArray()
        )
    )

    @Test
    fun `Named entity Hstrok with a semicolon`() = assertTokenization(
        input = "&Hstrok;",
        expectedTokens = listOf(
            char('Ħ')
        )
    )

    @Test
    fun `Bad named entity HumpDownHump without a semicolon`() = assertTokenization(
        input = "&HumpDownHump",
        expectedTokens = listOf(
            *chars("&HumpDownHump").toTypedArray()
        )
    )

    @Test
    fun `Named entity HumpDownHump with a semicolon`() = assertTokenization(
        input = "&HumpDownHump;",
        expectedTokens = listOf(
            char('≎')
        )
    )

    @Test
    fun `Bad named entity HumpEqual without a semicolon`() = assertTokenization(
        input = "&HumpEqual",
        expectedTokens = listOf(
            *chars("&HumpEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity HumpEqual with a semicolon`() = assertTokenization(
        input = "&HumpEqual;",
        expectedTokens = listOf(
            char('≏')
        )
    )

    @Test
    fun `Bad named entity IEcy without a semicolon`() = assertTokenization(
        input = "&IEcy",
        expectedTokens = listOf(
            *chars("&IEcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity IEcy with a semicolon`() = assertTokenization(
        input = "&IEcy;",
        expectedTokens = listOf(
            char('Е')
        )
    )

    @Test
    fun `Bad named entity IJlig without a semicolon`() = assertTokenization(
        input = "&IJlig",
        expectedTokens = listOf(
            *chars("&IJlig").toTypedArray()
        )
    )

    @Test
    fun `Named entity IJlig with a semicolon`() = assertTokenization(
        input = "&IJlig;",
        expectedTokens = listOf(
            char('Ĳ')
        )
    )

    @Test
    fun `Bad named entity IOcy without a semicolon`() = assertTokenization(
        input = "&IOcy",
        expectedTokens = listOf(
            *chars("&IOcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity IOcy with a semicolon`() = assertTokenization(
        input = "&IOcy;",
        expectedTokens = listOf(
            char('Ё')
        )
    )

    @Test
    fun `Named entity Iacute without a semicolon`() = assertTokenization(
        input = "&Iacute",
        expectedTokens = listOf(
            char('Í')
        )
    )

    @Test
    fun `Named entity Iacute with a semicolon`() = assertTokenization(
        input = "&Iacute;",
        expectedTokens = listOf(
            char('Í')
        )
    )

    @Test
    fun `Named entity Icirc without a semicolon`() = assertTokenization(
        input = "&Icirc",
        expectedTokens = listOf(
            char('Î')
        )
    )

    @Test
    fun `Named entity Icirc with a semicolon`() = assertTokenization(
        input = "&Icirc;",
        expectedTokens = listOf(
            char('Î')
        )
    )

    @Test
    fun `Bad named entity Icy without a semicolon`() = assertTokenization(
        input = "&Icy",
        expectedTokens = listOf(
            *chars("&Icy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Icy with a semicolon`() = assertTokenization(
        input = "&Icy;",
        expectedTokens = listOf(
            char('И')
        )
    )

    @Test
    fun `Bad named entity Idot without a semicolon`() = assertTokenization(
        input = "&Idot",
        expectedTokens = listOf(
            *chars("&Idot").toTypedArray()
        )
    )

    @Test
    fun `Named entity Idot with a semicolon`() = assertTokenization(
        input = "&Idot;",
        expectedTokens = listOf(
            char('İ')
        )
    )

    @Test
    fun `Bad named entity Ifr without a semicolon`() = assertTokenization(
        input = "&Ifr",
        expectedTokens = listOf(
            *chars("&Ifr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ifr with a semicolon`() = assertTokenization(
        input = "&Ifr;",
        expectedTokens = listOf(
            char('ℑ')
        )
    )

    @Test
    fun `Named entity Igrave without a semicolon`() = assertTokenization(
        input = "&Igrave",
        expectedTokens = listOf(
            char('Ì')
        )
    )

    @Test
    fun `Named entity Igrave with a semicolon`() = assertTokenization(
        input = "&Igrave;",
        expectedTokens = listOf(
            char('Ì')
        )
    )

    @Test
    fun `Bad named entity Im without a semicolon`() = assertTokenization(
        input = "&Im",
        expectedTokens = listOf(
            *chars("&Im").toTypedArray()
        )
    )

    @Test
    fun `Named entity Im with a semicolon`() = assertTokenization(
        input = "&Im;",
        expectedTokens = listOf(
            char('ℑ')
        )
    )

    @Test
    fun `Bad named entity Imacr without a semicolon`() = assertTokenization(
        input = "&Imacr",
        expectedTokens = listOf(
            *chars("&Imacr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Imacr with a semicolon`() = assertTokenization(
        input = "&Imacr;",
        expectedTokens = listOf(
            char('Ī')
        )
    )

    @Test
    fun `Bad named entity ImaginaryI without a semicolon`() = assertTokenization(
        input = "&ImaginaryI",
        expectedTokens = listOf(
            *chars("&ImaginaryI").toTypedArray()
        )
    )

    @Test
    fun `Named entity ImaginaryI with a semicolon`() = assertTokenization(
        input = "&ImaginaryI;",
        expectedTokens = listOf(
            char('ⅈ')
        )
    )

    @Test
    fun `Bad named entity Implies without a semicolon`() = assertTokenization(
        input = "&Implies",
        expectedTokens = listOf(
            *chars("&Implies").toTypedArray()
        )
    )

    @Test
    fun `Named entity Implies with a semicolon`() = assertTokenization(
        input = "&Implies;",
        expectedTokens = listOf(
            char('⇒')
        )
    )

    @Test
    fun `Bad named entity Int without a semicolon`() = assertTokenization(
        input = "&Int",
        expectedTokens = listOf(
            *chars("&Int").toTypedArray()
        )
    )

    @Test
    fun `Named entity Int with a semicolon`() = assertTokenization(
        input = "&Int;",
        expectedTokens = listOf(
            char('∬')
        )
    )

    @Test
    fun `Bad named entity Integral without a semicolon`() = assertTokenization(
        input = "&Integral",
        expectedTokens = listOf(
            *chars("&Integral").toTypedArray()
        )
    )

    @Test
    fun `Named entity Integral with a semicolon`() = assertTokenization(
        input = "&Integral;",
        expectedTokens = listOf(
            char('∫')
        )
    )

    @Test
    fun `Bad named entity Intersection without a semicolon`() = assertTokenization(
        input = "&Intersection",
        expectedTokens = listOf(
            *chars("&Intersection").toTypedArray()
        )
    )

    @Test
    fun `Named entity Intersection with a semicolon`() = assertTokenization(
        input = "&Intersection;",
        expectedTokens = listOf(
            char('⋂')
        )
    )

    @Test
    fun `Bad named entity InvisibleComma without a semicolon`() = assertTokenization(
        input = "&InvisibleComma",
        expectedTokens = listOf(
            *chars("&InvisibleComma").toTypedArray()
        )
    )

    @Test
    fun `Named entity InvisibleComma with a semicolon`() = assertTokenization(
        input = "&InvisibleComma;",
        expectedTokens = listOf(
            char('⁣')
        )
    )

    @Test
    fun `Bad named entity InvisibleTimes without a semicolon`() = assertTokenization(
        input = "&InvisibleTimes",
        expectedTokens = listOf(
            *chars("&InvisibleTimes").toTypedArray()
        )
    )

    @Test
    fun `Named entity InvisibleTimes with a semicolon`() = assertTokenization(
        input = "&InvisibleTimes;",
        expectedTokens = listOf(
            char('⁢')
        )
    )

    @Test
    fun `Bad named entity Iogon without a semicolon`() = assertTokenization(
        input = "&Iogon",
        expectedTokens = listOf(
            *chars("&Iogon").toTypedArray()
        )
    )

    @Test
    fun `Named entity Iogon with a semicolon`() = assertTokenization(
        input = "&Iogon;",
        expectedTokens = listOf(
            char('Į')
        )
    )

    @Test
    fun `Bad named entity Iopf without a semicolon`() = assertTokenization(
        input = "&Iopf",
        expectedTokens = listOf(
            *chars("&Iopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Iopf with a semicolon`() = assertTokenization(
        input = "&Iopf;",
        expectedTokens = listOf(
            *chars("𝕀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Iota without a semicolon`() = assertTokenization(
        input = "&Iota",
        expectedTokens = listOf(
            *chars("&Iota").toTypedArray()
        )
    )

    @Test
    fun `Named entity Iota with a semicolon`() = assertTokenization(
        input = "&Iota;",
        expectedTokens = listOf(
            char('Ι')
        )
    )

    @Test
    fun `Bad named entity Iscr without a semicolon`() = assertTokenization(
        input = "&Iscr",
        expectedTokens = listOf(
            *chars("&Iscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Iscr with a semicolon`() = assertTokenization(
        input = "&Iscr;",
        expectedTokens = listOf(
            char('ℐ')
        )
    )

    @Test
    fun `Bad named entity Itilde without a semicolon`() = assertTokenization(
        input = "&Itilde",
        expectedTokens = listOf(
            *chars("&Itilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity Itilde with a semicolon`() = assertTokenization(
        input = "&Itilde;",
        expectedTokens = listOf(
            char('Ĩ')
        )
    )

    @Test
    fun `Bad named entity Iukcy without a semicolon`() = assertTokenization(
        input = "&Iukcy",
        expectedTokens = listOf(
            *chars("&Iukcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Iukcy with a semicolon`() = assertTokenization(
        input = "&Iukcy;",
        expectedTokens = listOf(
            char('І')
        )
    )

    @Test
    fun `Named entity Iuml without a semicolon`() = assertTokenization(
        input = "&Iuml",
        expectedTokens = listOf(
            char('Ï')
        )
    )

    @Test
    fun `Named entity Iuml with a semicolon`() = assertTokenization(
        input = "&Iuml;",
        expectedTokens = listOf(
            char('Ï')
        )
    )

    @Test
    fun `Bad named entity Jcirc without a semicolon`() = assertTokenization(
        input = "&Jcirc",
        expectedTokens = listOf(
            *chars("&Jcirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity Jcirc with a semicolon`() = assertTokenization(
        input = "&Jcirc;",
        expectedTokens = listOf(
            char('Ĵ')
        )
    )

    @Test
    fun `Bad named entity Jcy without a semicolon`() = assertTokenization(
        input = "&Jcy",
        expectedTokens = listOf(
            *chars("&Jcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Jcy with a semicolon`() = assertTokenization(
        input = "&Jcy;",
        expectedTokens = listOf(
            char('Й')
        )
    )

    @Test
    fun `Bad named entity Jfr without a semicolon`() = assertTokenization(
        input = "&Jfr",
        expectedTokens = listOf(
            *chars("&Jfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Jfr with a semicolon`() = assertTokenization(
        input = "&Jfr;",
        expectedTokens = listOf(
            *chars("𝔍").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Jopf without a semicolon`() = assertTokenization(
        input = "&Jopf",
        expectedTokens = listOf(
            *chars("&Jopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Jopf with a semicolon`() = assertTokenization(
        input = "&Jopf;",
        expectedTokens = listOf(
            *chars("𝕁").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Jscr without a semicolon`() = assertTokenization(
        input = "&Jscr",
        expectedTokens = listOf(
            *chars("&Jscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Jscr with a semicolon`() = assertTokenization(
        input = "&Jscr;",
        expectedTokens = listOf(
            *chars("𝒥").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Jsercy without a semicolon`() = assertTokenization(
        input = "&Jsercy",
        expectedTokens = listOf(
            *chars("&Jsercy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Jsercy with a semicolon`() = assertTokenization(
        input = "&Jsercy;",
        expectedTokens = listOf(
            char('Ј')
        )
    )

    @Test
    fun `Bad named entity Jukcy without a semicolon`() = assertTokenization(
        input = "&Jukcy",
        expectedTokens = listOf(
            *chars("&Jukcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Jukcy with a semicolon`() = assertTokenization(
        input = "&Jukcy;",
        expectedTokens = listOf(
            char('Є')
        )
    )

    @Test
    fun `Bad named entity KHcy without a semicolon`() = assertTokenization(
        input = "&KHcy",
        expectedTokens = listOf(
            *chars("&KHcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity KHcy with a semicolon`() = assertTokenization(
        input = "&KHcy;",
        expectedTokens = listOf(
            char('Х')
        )
    )

    @Test
    fun `Bad named entity KJcy without a semicolon`() = assertTokenization(
        input = "&KJcy",
        expectedTokens = listOf(
            *chars("&KJcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity KJcy with a semicolon`() = assertTokenization(
        input = "&KJcy;",
        expectedTokens = listOf(
            char('Ќ')
        )
    )

    @Test
    fun `Bad named entity Kappa without a semicolon`() = assertTokenization(
        input = "&Kappa",
        expectedTokens = listOf(
            *chars("&Kappa").toTypedArray()
        )
    )

    @Test
    fun `Named entity Kappa with a semicolon`() = assertTokenization(
        input = "&Kappa;",
        expectedTokens = listOf(
            char('Κ')
        )
    )

    @Test
    fun `Bad named entity Kcedil without a semicolon`() = assertTokenization(
        input = "&Kcedil",
        expectedTokens = listOf(
            *chars("&Kcedil").toTypedArray()
        )
    )

    @Test
    fun `Named entity Kcedil with a semicolon`() = assertTokenization(
        input = "&Kcedil;",
        expectedTokens = listOf(
            char('Ķ')
        )
    )

    @Test
    fun `Bad named entity Kcy without a semicolon`() = assertTokenization(
        input = "&Kcy",
        expectedTokens = listOf(
            *chars("&Kcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Kcy with a semicolon`() = assertTokenization(
        input = "&Kcy;",
        expectedTokens = listOf(
            char('К')
        )
    )

    @Test
    fun `Bad named entity Kfr without a semicolon`() = assertTokenization(
        input = "&Kfr",
        expectedTokens = listOf(
            *chars("&Kfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Kfr with a semicolon`() = assertTokenization(
        input = "&Kfr;",
        expectedTokens = listOf(
            *chars("𝔎").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Kopf without a semicolon`() = assertTokenization(
        input = "&Kopf",
        expectedTokens = listOf(
            *chars("&Kopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Kopf with a semicolon`() = assertTokenization(
        input = "&Kopf;",
        expectedTokens = listOf(
            *chars("𝕂").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Kscr without a semicolon`() = assertTokenization(
        input = "&Kscr",
        expectedTokens = listOf(
            *chars("&Kscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Kscr with a semicolon`() = assertTokenization(
        input = "&Kscr;",
        expectedTokens = listOf(
            *chars("𝒦").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity LJcy without a semicolon`() = assertTokenization(
        input = "&LJcy",
        expectedTokens = listOf(
            *chars("&LJcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity LJcy with a semicolon`() = assertTokenization(
        input = "&LJcy;",
        expectedTokens = listOf(
            char('Љ')
        )
    )

    @Test
    fun `Named entity LT without a semicolon`() = assertTokenization(
        input = "&LT",
        expectedTokens = listOf(
            char('<')
        )
    )

    @Test
    fun `Named entity LT with a semicolon`() = assertTokenization(
        input = "&LT;",
        expectedTokens = listOf(
            char('<')
        )
    )

    @Test
    fun `Bad named entity Lacute without a semicolon`() = assertTokenization(
        input = "&Lacute",
        expectedTokens = listOf(
            *chars("&Lacute").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lacute with a semicolon`() = assertTokenization(
        input = "&Lacute;",
        expectedTokens = listOf(
            char('Ĺ')
        )
    )

    @Test
    fun `Bad named entity Lambda without a semicolon`() = assertTokenization(
        input = "&Lambda",
        expectedTokens = listOf(
            *chars("&Lambda").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lambda with a semicolon`() = assertTokenization(
        input = "&Lambda;",
        expectedTokens = listOf(
            char('Λ')
        )
    )

    @Test
    fun `Bad named entity Lang without a semicolon`() = assertTokenization(
        input = "&Lang",
        expectedTokens = listOf(
            *chars("&Lang").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lang with a semicolon`() = assertTokenization(
        input = "&Lang;",
        expectedTokens = listOf(
            char('⟪')
        )
    )

    @Test
    fun `Bad named entity Laplacetrf without a semicolon`() = assertTokenization(
        input = "&Laplacetrf",
        expectedTokens = listOf(
            *chars("&Laplacetrf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Laplacetrf with a semicolon`() = assertTokenization(
        input = "&Laplacetrf;",
        expectedTokens = listOf(
            char('ℒ')
        )
    )

    @Test
    fun `Bad named entity Larr without a semicolon`() = assertTokenization(
        input = "&Larr",
        expectedTokens = listOf(
            *chars("&Larr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Larr with a semicolon`() = assertTokenization(
        input = "&Larr;",
        expectedTokens = listOf(
            char('↞')
        )
    )

    @Test
    fun `Bad named entity Lcaron without a semicolon`() = assertTokenization(
        input = "&Lcaron",
        expectedTokens = listOf(
            *chars("&Lcaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lcaron with a semicolon`() = assertTokenization(
        input = "&Lcaron;",
        expectedTokens = listOf(
            char('Ľ')
        )
    )

    @Test
    fun `Bad named entity Lcedil without a semicolon`() = assertTokenization(
        input = "&Lcedil",
        expectedTokens = listOf(
            *chars("&Lcedil").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lcedil with a semicolon`() = assertTokenization(
        input = "&Lcedil;",
        expectedTokens = listOf(
            char('Ļ')
        )
    )

    @Test
    fun `Bad named entity Lcy without a semicolon`() = assertTokenization(
        input = "&Lcy",
        expectedTokens = listOf(
            *chars("&Lcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lcy with a semicolon`() = assertTokenization(
        input = "&Lcy;",
        expectedTokens = listOf(
            char('Л')
        )
    )

    @Test
    fun `Bad named entity LeftAngleBracket without a semicolon`() = assertTokenization(
        input = "&LeftAngleBracket",
        expectedTokens = listOf(
            *chars("&LeftAngleBracket").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftAngleBracket with a semicolon`() = assertTokenization(
        input = "&LeftAngleBracket;",
        expectedTokens = listOf(
            char('⟨')
        )
    )

    @Test
    fun `Bad named entity LeftArrow without a semicolon`() = assertTokenization(
        input = "&LeftArrow",
        expectedTokens = listOf(
            *chars("&LeftArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftArrow with a semicolon`() = assertTokenization(
        input = "&LeftArrow;",
        expectedTokens = listOf(
            char('←')
        )
    )

    @Test
    fun `Bad named entity LeftArrowBar without a semicolon`() = assertTokenization(
        input = "&LeftArrowBar",
        expectedTokens = listOf(
            *chars("&LeftArrowBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftArrowBar with a semicolon`() = assertTokenization(
        input = "&LeftArrowBar;",
        expectedTokens = listOf(
            char('⇤')
        )
    )

    @Test
    fun `Bad named entity LeftArrowRightArrow without a semicolon`() = assertTokenization(
        input = "&LeftArrowRightArrow",
        expectedTokens = listOf(
            *chars("&LeftArrowRightArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftArrowRightArrow with a semicolon`() = assertTokenization(
        input = "&LeftArrowRightArrow;",
        expectedTokens = listOf(
            char('⇆')
        )
    )

    @Test
    fun `Bad named entity LeftCeiling without a semicolon`() = assertTokenization(
        input = "&LeftCeiling",
        expectedTokens = listOf(
            *chars("&LeftCeiling").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftCeiling with a semicolon`() = assertTokenization(
        input = "&LeftCeiling;",
        expectedTokens = listOf(
            char('⌈')
        )
    )

    @Test
    fun `Bad named entity LeftDoubleBracket without a semicolon`() = assertTokenization(
        input = "&LeftDoubleBracket",
        expectedTokens = listOf(
            *chars("&LeftDoubleBracket").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftDoubleBracket with a semicolon`() = assertTokenization(
        input = "&LeftDoubleBracket;",
        expectedTokens = listOf(
            char('⟦')
        )
    )

    @Test
    fun `Bad named entity LeftDownTeeVector without a semicolon`() = assertTokenization(
        input = "&LeftDownTeeVector",
        expectedTokens = listOf(
            *chars("&LeftDownTeeVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftDownTeeVector with a semicolon`() = assertTokenization(
        input = "&LeftDownTeeVector;",
        expectedTokens = listOf(
            char('⥡')
        )
    )

    @Test
    fun `Bad named entity LeftDownVector without a semicolon`() = assertTokenization(
        input = "&LeftDownVector",
        expectedTokens = listOf(
            *chars("&LeftDownVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftDownVector with a semicolon`() = assertTokenization(
        input = "&LeftDownVector;",
        expectedTokens = listOf(
            char('⇃')
        )
    )

    @Test
    fun `Bad named entity LeftDownVectorBar without a semicolon`() = assertTokenization(
        input = "&LeftDownVectorBar",
        expectedTokens = listOf(
            *chars("&LeftDownVectorBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftDownVectorBar with a semicolon`() = assertTokenization(
        input = "&LeftDownVectorBar;",
        expectedTokens = listOf(
            char('⥙')
        )
    )

    @Test
    fun `Bad named entity LeftFloor without a semicolon`() = assertTokenization(
        input = "&LeftFloor",
        expectedTokens = listOf(
            *chars("&LeftFloor").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftFloor with a semicolon`() = assertTokenization(
        input = "&LeftFloor;",
        expectedTokens = listOf(
            char('⌊')
        )
    )

    @Test
    fun `Bad named entity LeftRightArrow without a semicolon`() = assertTokenization(
        input = "&LeftRightArrow",
        expectedTokens = listOf(
            *chars("&LeftRightArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftRightArrow with a semicolon`() = assertTokenization(
        input = "&LeftRightArrow;",
        expectedTokens = listOf(
            char('↔')
        )
    )

    @Test
    fun `Bad named entity LeftRightVector without a semicolon`() = assertTokenization(
        input = "&LeftRightVector",
        expectedTokens = listOf(
            *chars("&LeftRightVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftRightVector with a semicolon`() = assertTokenization(
        input = "&LeftRightVector;",
        expectedTokens = listOf(
            char('⥎')
        )
    )

    @Test
    fun `Bad named entity LeftTee without a semicolon`() = assertTokenization(
        input = "&LeftTee",
        expectedTokens = listOf(
            *chars("&LeftTee").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftTee with a semicolon`() = assertTokenization(
        input = "&LeftTee;",
        expectedTokens = listOf(
            char('⊣')
        )
    )

    @Test
    fun `Bad named entity LeftTeeArrow without a semicolon`() = assertTokenization(
        input = "&LeftTeeArrow",
        expectedTokens = listOf(
            *chars("&LeftTeeArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftTeeArrow with a semicolon`() = assertTokenization(
        input = "&LeftTeeArrow;",
        expectedTokens = listOf(
            char('↤')
        )
    )

    @Test
    fun `Bad named entity LeftTeeVector without a semicolon`() = assertTokenization(
        input = "&LeftTeeVector",
        expectedTokens = listOf(
            *chars("&LeftTeeVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftTeeVector with a semicolon`() = assertTokenization(
        input = "&LeftTeeVector;",
        expectedTokens = listOf(
            char('⥚')
        )
    )

    @Test
    fun `Bad named entity LeftTriangle without a semicolon`() = assertTokenization(
        input = "&LeftTriangle",
        expectedTokens = listOf(
            *chars("&LeftTriangle").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftTriangle with a semicolon`() = assertTokenization(
        input = "&LeftTriangle;",
        expectedTokens = listOf(
            char('⊲')
        )
    )

    @Test
    fun `Bad named entity LeftTriangleBar without a semicolon`() = assertTokenization(
        input = "&LeftTriangleBar",
        expectedTokens = listOf(
            *chars("&LeftTriangleBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftTriangleBar with a semicolon`() = assertTokenization(
        input = "&LeftTriangleBar;",
        expectedTokens = listOf(
            char('⧏')
        )
    )

    @Test
    fun `Bad named entity LeftTriangleEqual without a semicolon`() = assertTokenization(
        input = "&LeftTriangleEqual",
        expectedTokens = listOf(
            *chars("&LeftTriangleEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftTriangleEqual with a semicolon`() = assertTokenization(
        input = "&LeftTriangleEqual;",
        expectedTokens = listOf(
            char('⊴')
        )
    )

    @Test
    fun `Bad named entity LeftUpDownVector without a semicolon`() = assertTokenization(
        input = "&LeftUpDownVector",
        expectedTokens = listOf(
            *chars("&LeftUpDownVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftUpDownVector with a semicolon`() = assertTokenization(
        input = "&LeftUpDownVector;",
        expectedTokens = listOf(
            char('⥑')
        )
    )

    @Test
    fun `Bad named entity LeftUpTeeVector without a semicolon`() = assertTokenization(
        input = "&LeftUpTeeVector",
        expectedTokens = listOf(
            *chars("&LeftUpTeeVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftUpTeeVector with a semicolon`() = assertTokenization(
        input = "&LeftUpTeeVector;",
        expectedTokens = listOf(
            char('⥠')
        )
    )

    @Test
    fun `Bad named entity LeftUpVector without a semicolon`() = assertTokenization(
        input = "&LeftUpVector",
        expectedTokens = listOf(
            *chars("&LeftUpVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftUpVector with a semicolon`() = assertTokenization(
        input = "&LeftUpVector;",
        expectedTokens = listOf(
            char('↿')
        )
    )

    @Test
    fun `Bad named entity LeftUpVectorBar without a semicolon`() = assertTokenization(
        input = "&LeftUpVectorBar",
        expectedTokens = listOf(
            *chars("&LeftUpVectorBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftUpVectorBar with a semicolon`() = assertTokenization(
        input = "&LeftUpVectorBar;",
        expectedTokens = listOf(
            char('⥘')
        )
    )

    @Test
    fun `Bad named entity LeftVector without a semicolon`() = assertTokenization(
        input = "&LeftVector",
        expectedTokens = listOf(
            *chars("&LeftVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftVector with a semicolon`() = assertTokenization(
        input = "&LeftVector;",
        expectedTokens = listOf(
            char('↼')
        )
    )

    @Test
    fun `Bad named entity LeftVectorBar without a semicolon`() = assertTokenization(
        input = "&LeftVectorBar",
        expectedTokens = listOf(
            *chars("&LeftVectorBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity LeftVectorBar with a semicolon`() = assertTokenization(
        input = "&LeftVectorBar;",
        expectedTokens = listOf(
            char('⥒')
        )
    )

    @Test
    fun `Bad named entity Leftarrow without a semicolon`() = assertTokenization(
        input = "&Leftarrow",
        expectedTokens = listOf(
            *chars("&Leftarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity Leftarrow with a semicolon`() = assertTokenization(
        input = "&Leftarrow;",
        expectedTokens = listOf(
            char('⇐')
        )
    )

    @Test
    fun `Bad named entity Leftrightarrow without a semicolon`() = assertTokenization(
        input = "&Leftrightarrow",
        expectedTokens = listOf(
            *chars("&Leftrightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity Leftrightarrow with a semicolon`() = assertTokenization(
        input = "&Leftrightarrow;",
        expectedTokens = listOf(
            char('⇔')
        )
    )

    @Test
    fun `Bad named entity LessEqualGreater without a semicolon`() = assertTokenization(
        input = "&LessEqualGreater",
        expectedTokens = listOf(
            *chars("&LessEqualGreater").toTypedArray()
        )
    )

    @Test
    fun `Named entity LessEqualGreater with a semicolon`() = assertTokenization(
        input = "&LessEqualGreater;",
        expectedTokens = listOf(
            char('⋚')
        )
    )

    @Test
    fun `Bad named entity LessFullEqual without a semicolon`() = assertTokenization(
        input = "&LessFullEqual",
        expectedTokens = listOf(
            *chars("&LessFullEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity LessFullEqual with a semicolon`() = assertTokenization(
        input = "&LessFullEqual;",
        expectedTokens = listOf(
            char('≦')
        )
    )

    @Test
    fun `Bad named entity LessGreater without a semicolon`() = assertTokenization(
        input = "&LessGreater",
        expectedTokens = listOf(
            *chars("&LessGreater").toTypedArray()
        )
    )

    @Test
    fun `Named entity LessGreater with a semicolon`() = assertTokenization(
        input = "&LessGreater;",
        expectedTokens = listOf(
            char('≶')
        )
    )

    @Test
    fun `Bad named entity LessLess without a semicolon`() = assertTokenization(
        input = "&LessLess",
        expectedTokens = listOf(
            *chars("&LessLess").toTypedArray()
        )
    )

    @Test
    fun `Named entity LessLess with a semicolon`() = assertTokenization(
        input = "&LessLess;",
        expectedTokens = listOf(
            char('⪡')
        )
    )

    @Test
    fun `Bad named entity LessSlantEqual without a semicolon`() = assertTokenization(
        input = "&LessSlantEqual",
        expectedTokens = listOf(
            *chars("&LessSlantEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity LessSlantEqual with a semicolon`() = assertTokenization(
        input = "&LessSlantEqual;",
        expectedTokens = listOf(
            char('⩽')
        )
    )

    @Test
    fun `Bad named entity LessTilde without a semicolon`() = assertTokenization(
        input = "&LessTilde",
        expectedTokens = listOf(
            *chars("&LessTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity LessTilde with a semicolon`() = assertTokenization(
        input = "&LessTilde;",
        expectedTokens = listOf(
            char('≲')
        )
    )

    @Test
    fun `Bad named entity Lfr without a semicolon`() = assertTokenization(
        input = "&Lfr",
        expectedTokens = listOf(
            *chars("&Lfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lfr with a semicolon`() = assertTokenization(
        input = "&Lfr;",
        expectedTokens = listOf(
            *chars("𝔏").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Ll without a semicolon`() = assertTokenization(
        input = "&Ll",
        expectedTokens = listOf(
            *chars("&Ll").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ll with a semicolon`() = assertTokenization(
        input = "&Ll;",
        expectedTokens = listOf(
            char('⋘')
        )
    )

    @Test
    fun `Bad named entity Lleftarrow without a semicolon`() = assertTokenization(
        input = "&Lleftarrow",
        expectedTokens = listOf(
            *chars("&Lleftarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lleftarrow with a semicolon`() = assertTokenization(
        input = "&Lleftarrow;",
        expectedTokens = listOf(
            char('⇚')
        )
    )

    @Test
    fun `Bad named entity Lmidot without a semicolon`() = assertTokenization(
        input = "&Lmidot",
        expectedTokens = listOf(
            *chars("&Lmidot").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lmidot with a semicolon`() = assertTokenization(
        input = "&Lmidot;",
        expectedTokens = listOf(
            char('Ŀ')
        )
    )

    @Test
    fun `Bad named entity LongLeftArrow without a semicolon`() = assertTokenization(
        input = "&LongLeftArrow",
        expectedTokens = listOf(
            *chars("&LongLeftArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity LongLeftArrow with a semicolon`() = assertTokenization(
        input = "&LongLeftArrow;",
        expectedTokens = listOf(
            char('⟵')
        )
    )

    @Test
    fun `Bad named entity LongLeftRightArrow without a semicolon`() = assertTokenization(
        input = "&LongLeftRightArrow",
        expectedTokens = listOf(
            *chars("&LongLeftRightArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity LongLeftRightArrow with a semicolon`() = assertTokenization(
        input = "&LongLeftRightArrow;",
        expectedTokens = listOf(
            char('⟷')
        )
    )

    @Test
    fun `Bad named entity LongRightArrow without a semicolon`() = assertTokenization(
        input = "&LongRightArrow",
        expectedTokens = listOf(
            *chars("&LongRightArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity LongRightArrow with a semicolon`() = assertTokenization(
        input = "&LongRightArrow;",
        expectedTokens = listOf(
            char('⟶')
        )
    )

    @Test
    fun `Bad named entity Longleftarrow without a semicolon`() = assertTokenization(
        input = "&Longleftarrow",
        expectedTokens = listOf(
            *chars("&Longleftarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity Longleftarrow with a semicolon`() = assertTokenization(
        input = "&Longleftarrow;",
        expectedTokens = listOf(
            char('⟸')
        )
    )

    @Test
    fun `Bad named entity Longleftrightarrow without a semicolon`() = assertTokenization(
        input = "&Longleftrightarrow",
        expectedTokens = listOf(
            *chars("&Longleftrightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity Longleftrightarrow with a semicolon`() = assertTokenization(
        input = "&Longleftrightarrow;",
        expectedTokens = listOf(
            char('⟺')
        )
    )

    @Test
    fun `Bad named entity Longrightarrow without a semicolon`() = assertTokenization(
        input = "&Longrightarrow",
        expectedTokens = listOf(
            *chars("&Longrightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity Longrightarrow with a semicolon`() = assertTokenization(
        input = "&Longrightarrow;",
        expectedTokens = listOf(
            char('⟹')
        )
    )

    @Test
    fun `Bad named entity Lopf without a semicolon`() = assertTokenization(
        input = "&Lopf",
        expectedTokens = listOf(
            *chars("&Lopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lopf with a semicolon`() = assertTokenization(
        input = "&Lopf;",
        expectedTokens = listOf(
            *chars("𝕃").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity LowerLeftArrow without a semicolon`() = assertTokenization(
        input = "&LowerLeftArrow",
        expectedTokens = listOf(
            *chars("&LowerLeftArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity LowerLeftArrow with a semicolon`() = assertTokenization(
        input = "&LowerLeftArrow;",
        expectedTokens = listOf(
            char('↙')
        )
    )

    @Test
    fun `Bad named entity LowerRightArrow without a semicolon`() = assertTokenization(
        input = "&LowerRightArrow",
        expectedTokens = listOf(
            *chars("&LowerRightArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity LowerRightArrow with a semicolon`() = assertTokenization(
        input = "&LowerRightArrow;",
        expectedTokens = listOf(
            char('↘')
        )
    )

    @Test
    fun `Bad named entity Lscr without a semicolon`() = assertTokenization(
        input = "&Lscr",
        expectedTokens = listOf(
            *chars("&Lscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lscr with a semicolon`() = assertTokenization(
        input = "&Lscr;",
        expectedTokens = listOf(
            char('ℒ')
        )
    )

    @Test
    fun `Bad named entity Lsh without a semicolon`() = assertTokenization(
        input = "&Lsh",
        expectedTokens = listOf(
            *chars("&Lsh").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lsh with a semicolon`() = assertTokenization(
        input = "&Lsh;",
        expectedTokens = listOf(
            char('↰')
        )
    )

    @Test
    fun `Bad named entity Lstrok without a semicolon`() = assertTokenization(
        input = "&Lstrok",
        expectedTokens = listOf(
            *chars("&Lstrok").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lstrok with a semicolon`() = assertTokenization(
        input = "&Lstrok;",
        expectedTokens = listOf(
            char('Ł')
        )
    )

    @Test
    fun `Bad named entity Lt without a semicolon`() = assertTokenization(
        input = "&Lt",
        expectedTokens = listOf(
            *chars("&Lt").toTypedArray()
        )
    )

    @Test
    fun `Named entity Lt with a semicolon`() = assertTokenization(
        input = "&Lt;",
        expectedTokens = listOf(
            char('≪')
        )
    )

    @Test
    fun `Bad named entity Map without a semicolon`() = assertTokenization(
        input = "&Map",
        expectedTokens = listOf(
            *chars("&Map").toTypedArray()
        )
    )

    @Test
    fun `Named entity Map with a semicolon`() = assertTokenization(
        input = "&Map;",
        expectedTokens = listOf(
            char('⤅')
        )
    )

    @Test
    fun `Bad named entity Mcy without a semicolon`() = assertTokenization(
        input = "&Mcy",
        expectedTokens = listOf(
            *chars("&Mcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Mcy with a semicolon`() = assertTokenization(
        input = "&Mcy;",
        expectedTokens = listOf(
            char('М')
        )
    )

    @Test
    fun `Bad named entity MediumSpace without a semicolon`() = assertTokenization(
        input = "&MediumSpace",
        expectedTokens = listOf(
            *chars("&MediumSpace").toTypedArray()
        )
    )

    @Test
    fun `Named entity MediumSpace with a semicolon`() = assertTokenization(
        input = "&MediumSpace;",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Bad named entity Mellintrf without a semicolon`() = assertTokenization(
        input = "&Mellintrf",
        expectedTokens = listOf(
            *chars("&Mellintrf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Mellintrf with a semicolon`() = assertTokenization(
        input = "&Mellintrf;",
        expectedTokens = listOf(
            char('ℳ')
        )
    )

    @Test
    fun `Bad named entity Mfr without a semicolon`() = assertTokenization(
        input = "&Mfr",
        expectedTokens = listOf(
            *chars("&Mfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Mfr with a semicolon`() = assertTokenization(
        input = "&Mfr;",
        expectedTokens = listOf(
            *chars("𝔐").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity MinusPlus without a semicolon`() = assertTokenization(
        input = "&MinusPlus",
        expectedTokens = listOf(
            *chars("&MinusPlus").toTypedArray()
        )
    )

    @Test
    fun `Named entity MinusPlus with a semicolon`() = assertTokenization(
        input = "&MinusPlus;",
        expectedTokens = listOf(
            char('∓')
        )
    )

    @Test
    fun `Bad named entity Mopf without a semicolon`() = assertTokenization(
        input = "&Mopf",
        expectedTokens = listOf(
            *chars("&Mopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Mopf with a semicolon`() = assertTokenization(
        input = "&Mopf;",
        expectedTokens = listOf(
            *chars("𝕄").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Mscr without a semicolon`() = assertTokenization(
        input = "&Mscr",
        expectedTokens = listOf(
            *chars("&Mscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Mscr with a semicolon`() = assertTokenization(
        input = "&Mscr;",
        expectedTokens = listOf(
            char('ℳ')
        )
    )

    @Test
    fun `Bad named entity Mu without a semicolon`() = assertTokenization(
        input = "&Mu",
        expectedTokens = listOf(
            *chars("&Mu").toTypedArray()
        )
    )

    @Test
    fun `Named entity Mu with a semicolon`() = assertTokenization(
        input = "&Mu;",
        expectedTokens = listOf(
            char('Μ')
        )
    )

    @Test
    fun `Bad named entity NJcy without a semicolon`() = assertTokenization(
        input = "&NJcy",
        expectedTokens = listOf(
            *chars("&NJcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity NJcy with a semicolon`() = assertTokenization(
        input = "&NJcy;",
        expectedTokens = listOf(
            char('Њ')
        )
    )

    @Test
    fun `Bad named entity Nacute without a semicolon`() = assertTokenization(
        input = "&Nacute",
        expectedTokens = listOf(
            *chars("&Nacute").toTypedArray()
        )
    )

    @Test
    fun `Named entity Nacute with a semicolon`() = assertTokenization(
        input = "&Nacute;",
        expectedTokens = listOf(
            char('Ń')
        )
    )

    @Test
    fun `Bad named entity Ncaron without a semicolon`() = assertTokenization(
        input = "&Ncaron",
        expectedTokens = listOf(
            *chars("&Ncaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ncaron with a semicolon`() = assertTokenization(
        input = "&Ncaron;",
        expectedTokens = listOf(
            char('Ň')
        )
    )

    @Test
    fun `Bad named entity Ncedil without a semicolon`() = assertTokenization(
        input = "&Ncedil",
        expectedTokens = listOf(
            *chars("&Ncedil").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ncedil with a semicolon`() = assertTokenization(
        input = "&Ncedil;",
        expectedTokens = listOf(
            char('Ņ')
        )
    )

    @Test
    fun `Bad named entity Ncy without a semicolon`() = assertTokenization(
        input = "&Ncy",
        expectedTokens = listOf(
            *chars("&Ncy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ncy with a semicolon`() = assertTokenization(
        input = "&Ncy;",
        expectedTokens = listOf(
            char('Н')
        )
    )

    @Test
    fun `Bad named entity NegativeMediumSpace without a semicolon`() = assertTokenization(
        input = "&NegativeMediumSpace",
        expectedTokens = listOf(
            *chars("&NegativeMediumSpace").toTypedArray()
        )
    )

    @Test
    fun `Named entity NegativeMediumSpace with a semicolon`() = assertTokenization(
        input = "&NegativeMediumSpace;",
        expectedTokens = listOf(
            char('​')
        )
    )

    @Test
    fun `Bad named entity NegativeThickSpace without a semicolon`() = assertTokenization(
        input = "&NegativeThickSpace",
        expectedTokens = listOf(
            *chars("&NegativeThickSpace").toTypedArray()
        )
    )

    @Test
    fun `Named entity NegativeThickSpace with a semicolon`() = assertTokenization(
        input = "&NegativeThickSpace;",
        expectedTokens = listOf(
            char('​')
        )
    )

    @Test
    fun `Bad named entity NegativeThinSpace without a semicolon`() = assertTokenization(
        input = "&NegativeThinSpace",
        expectedTokens = listOf(
            *chars("&NegativeThinSpace").toTypedArray()
        )
    )

    @Test
    fun `Named entity NegativeThinSpace with a semicolon`() = assertTokenization(
        input = "&NegativeThinSpace;",
        expectedTokens = listOf(
            char('​')
        )
    )

    @Test
    fun `Bad named entity NegativeVeryThinSpace without a semicolon`() = assertTokenization(
        input = "&NegativeVeryThinSpace",
        expectedTokens = listOf(
            *chars("&NegativeVeryThinSpace").toTypedArray()
        )
    )

    @Test
    fun `Named entity NegativeVeryThinSpace with a semicolon`() = assertTokenization(
        input = "&NegativeVeryThinSpace;",
        expectedTokens = listOf(
            char('​')
        )
    )

    @Test
    fun `Bad named entity NestedGreaterGreater without a semicolon`() = assertTokenization(
        input = "&NestedGreaterGreater",
        expectedTokens = listOf(
            *chars("&NestedGreaterGreater").toTypedArray()
        )
    )

    @Test
    fun `Named entity NestedGreaterGreater with a semicolon`() = assertTokenization(
        input = "&NestedGreaterGreater;",
        expectedTokens = listOf(
            char('≫')
        )
    )

    @Test
    fun `Bad named entity NestedLessLess without a semicolon`() = assertTokenization(
        input = "&NestedLessLess",
        expectedTokens = listOf(
            *chars("&NestedLessLess").toTypedArray()
        )
    )

    @Test
    fun `Named entity NestedLessLess with a semicolon`() = assertTokenization(
        input = "&NestedLessLess;",
        expectedTokens = listOf(
            char('≪')
        )
    )

    @Test
    fun `Bad named entity NewLine without a semicolon`() = assertTokenization(
        input = "&NewLine",
        expectedTokens = listOf(
            *chars("&NewLine").toTypedArray()
        )
    )

    @Test
    fun `Named entity NewLine with a semicolon`() = assertTokenization(
        input = "&NewLine;",
        expectedTokens = listOf(
            char('\n')
        )
    )

    @Test
    fun `Bad named entity Nfr without a semicolon`() = assertTokenization(
        input = "&Nfr",
        expectedTokens = listOf(
            *chars("&Nfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Nfr with a semicolon`() = assertTokenization(
        input = "&Nfr;",
        expectedTokens = listOf(
            *chars("𝔑").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NoBreak without a semicolon`() = assertTokenization(
        input = "&NoBreak",
        expectedTokens = listOf(
            *chars("&NoBreak").toTypedArray()
        )
    )

    @Test
    fun `Named entity NoBreak with a semicolon`() = assertTokenization(
        input = "&NoBreak;",
        expectedTokens = listOf(
            char('⁠')
        )
    )

    @Test
    fun `Bad named entity NonBreakingSpace without a semicolon`() = assertTokenization(
        input = "&NonBreakingSpace",
        expectedTokens = listOf(
            *chars("&NonBreakingSpace").toTypedArray()
        )
    )

    @Test
    fun `Named entity NonBreakingSpace with a semicolon`() = assertTokenization(
        input = "&NonBreakingSpace;",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Bad named entity Nopf without a semicolon`() = assertTokenization(
        input = "&Nopf",
        expectedTokens = listOf(
            *chars("&Nopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Nopf with a semicolon`() = assertTokenization(
        input = "&Nopf;",
        expectedTokens = listOf(
            char('ℕ')
        )
    )

    @Test
    fun `Bad named entity Not without a semicolon`() = assertTokenization(
        input = "&Not",
        expectedTokens = listOf(
            *chars("&Not").toTypedArray()
        )
    )

    @Test
    fun `Named entity Not with a semicolon`() = assertTokenization(
        input = "&Not;",
        expectedTokens = listOf(
            char('⫬')
        )
    )

    @Test
    fun `Bad named entity NotCongruent without a semicolon`() = assertTokenization(
        input = "&NotCongruent",
        expectedTokens = listOf(
            *chars("&NotCongruent").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotCongruent with a semicolon`() = assertTokenization(
        input = "&NotCongruent;",
        expectedTokens = listOf(
            char('≢')
        )
    )

    @Test
    fun `Bad named entity NotCupCap without a semicolon`() = assertTokenization(
        input = "&NotCupCap",
        expectedTokens = listOf(
            *chars("&NotCupCap").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotCupCap with a semicolon`() = assertTokenization(
        input = "&NotCupCap;",
        expectedTokens = listOf(
            char('≭')
        )
    )

    @Test
    fun `Bad named entity NotDoubleVerticalBar without a semicolon`() = assertTokenization(
        input = "&NotDoubleVerticalBar",
        expectedTokens = listOf(
            *chars("&NotDoubleVerticalBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotDoubleVerticalBar with a semicolon`() = assertTokenization(
        input = "&NotDoubleVerticalBar;",
        expectedTokens = listOf(
            char('∦')
        )
    )

    @Test
    fun `Bad named entity NotElement without a semicolon`() = assertTokenization(
        input = "&NotElement",
        expectedTokens = listOf(
            *chars("&NotElement").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotElement with a semicolon`() = assertTokenization(
        input = "&NotElement;",
        expectedTokens = listOf(
            char('∉')
        )
    )

    @Test
    fun `Bad named entity NotEqual without a semicolon`() = assertTokenization(
        input = "&NotEqual",
        expectedTokens = listOf(
            *chars("&NotEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotEqual with a semicolon`() = assertTokenization(
        input = "&NotEqual;",
        expectedTokens = listOf(
            char('≠')
        )
    )

    @Test
    fun `Bad named entity NotEqualTilde without a semicolon`() = assertTokenization(
        input = "&NotEqualTilde",
        expectedTokens = listOf(
            *chars("&NotEqualTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotEqualTilde with a semicolon`() = assertTokenization(
        input = "&NotEqualTilde;",
        expectedTokens = listOf(
            *chars("≂̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotExists without a semicolon`() = assertTokenization(
        input = "&NotExists",
        expectedTokens = listOf(
            *chars("&NotExists").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotExists with a semicolon`() = assertTokenization(
        input = "&NotExists;",
        expectedTokens = listOf(
            char('∄')
        )
    )

    @Test
    fun `Bad named entity NotGreater without a semicolon`() = assertTokenization(
        input = "&NotGreater",
        expectedTokens = listOf(
            *chars("&NotGreater").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotGreater with a semicolon`() = assertTokenization(
        input = "&NotGreater;",
        expectedTokens = listOf(
            char('≯')
        )
    )

    @Test
    fun `Bad named entity NotGreaterEqual without a semicolon`() = assertTokenization(
        input = "&NotGreaterEqual",
        expectedTokens = listOf(
            *chars("&NotGreaterEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotGreaterEqual with a semicolon`() = assertTokenization(
        input = "&NotGreaterEqual;",
        expectedTokens = listOf(
            char('≱')
        )
    )

    @Test
    fun `Bad named entity NotGreaterFullEqual without a semicolon`() = assertTokenization(
        input = "&NotGreaterFullEqual",
        expectedTokens = listOf(
            *chars("&NotGreaterFullEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotGreaterFullEqual with a semicolon`() = assertTokenization(
        input = "&NotGreaterFullEqual;",
        expectedTokens = listOf(
            *chars("≧̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotGreaterGreater without a semicolon`() = assertTokenization(
        input = "&NotGreaterGreater",
        expectedTokens = listOf(
            *chars("&NotGreaterGreater").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotGreaterGreater with a semicolon`() = assertTokenization(
        input = "&NotGreaterGreater;",
        expectedTokens = listOf(
            *chars("≫̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotGreaterLess without a semicolon`() = assertTokenization(
        input = "&NotGreaterLess",
        expectedTokens = listOf(
            *chars("&NotGreaterLess").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotGreaterLess with a semicolon`() = assertTokenization(
        input = "&NotGreaterLess;",
        expectedTokens = listOf(
            char('≹')
        )
    )

    @Test
    fun `Bad named entity NotGreaterSlantEqual without a semicolon`() = assertTokenization(
        input = "&NotGreaterSlantEqual",
        expectedTokens = listOf(
            *chars("&NotGreaterSlantEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotGreaterSlantEqual with a semicolon`() = assertTokenization(
        input = "&NotGreaterSlantEqual;",
        expectedTokens = listOf(
            *chars("⩾̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotGreaterTilde without a semicolon`() = assertTokenization(
        input = "&NotGreaterTilde",
        expectedTokens = listOf(
            *chars("&NotGreaterTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotGreaterTilde with a semicolon`() = assertTokenization(
        input = "&NotGreaterTilde;",
        expectedTokens = listOf(
            char('≵')
        )
    )

    @Test
    fun `Bad named entity NotHumpDownHump without a semicolon`() = assertTokenization(
        input = "&NotHumpDownHump",
        expectedTokens = listOf(
            *chars("&NotHumpDownHump").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotHumpDownHump with a semicolon`() = assertTokenization(
        input = "&NotHumpDownHump;",
        expectedTokens = listOf(
            *chars("≎̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotHumpEqual without a semicolon`() = assertTokenization(
        input = "&NotHumpEqual",
        expectedTokens = listOf(
            *chars("&NotHumpEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotHumpEqual with a semicolon`() = assertTokenization(
        input = "&NotHumpEqual;",
        expectedTokens = listOf(
            *chars("≏̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotLeftTriangle without a semicolon`() = assertTokenization(
        input = "&NotLeftTriangle",
        expectedTokens = listOf(
            *chars("&NotLeftTriangle").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotLeftTriangle with a semicolon`() = assertTokenization(
        input = "&NotLeftTriangle;",
        expectedTokens = listOf(
            char('⋪')
        )
    )

    @Test
    fun `Bad named entity NotLeftTriangleBar without a semicolon`() = assertTokenization(
        input = "&NotLeftTriangleBar",
        expectedTokens = listOf(
            *chars("&NotLeftTriangleBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotLeftTriangleBar with a semicolon`() = assertTokenization(
        input = "&NotLeftTriangleBar;",
        expectedTokens = listOf(
            *chars("⧏̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotLeftTriangleEqual without a semicolon`() = assertTokenization(
        input = "&NotLeftTriangleEqual",
        expectedTokens = listOf(
            *chars("&NotLeftTriangleEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotLeftTriangleEqual with a semicolon`() = assertTokenization(
        input = "&NotLeftTriangleEqual;",
        expectedTokens = listOf(
            char('⋬')
        )
    )

    @Test
    fun `Bad named entity NotLess without a semicolon`() = assertTokenization(
        input = "&NotLess",
        expectedTokens = listOf(
            *chars("&NotLess").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotLess with a semicolon`() = assertTokenization(
        input = "&NotLess;",
        expectedTokens = listOf(
            char('≮')
        )
    )

    @Test
    fun `Bad named entity NotLessEqual without a semicolon`() = assertTokenization(
        input = "&NotLessEqual",
        expectedTokens = listOf(
            *chars("&NotLessEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotLessEqual with a semicolon`() = assertTokenization(
        input = "&NotLessEqual;",
        expectedTokens = listOf(
            char('≰')
        )
    )

    @Test
    fun `Bad named entity NotLessGreater without a semicolon`() = assertTokenization(
        input = "&NotLessGreater",
        expectedTokens = listOf(
            *chars("&NotLessGreater").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotLessGreater with a semicolon`() = assertTokenization(
        input = "&NotLessGreater;",
        expectedTokens = listOf(
            char('≸')
        )
    )

    @Test
    fun `Bad named entity NotLessLess without a semicolon`() = assertTokenization(
        input = "&NotLessLess",
        expectedTokens = listOf(
            *chars("&NotLessLess").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotLessLess with a semicolon`() = assertTokenization(
        input = "&NotLessLess;",
        expectedTokens = listOf(
            *chars("≪̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotLessSlantEqual without a semicolon`() = assertTokenization(
        input = "&NotLessSlantEqual",
        expectedTokens = listOf(
            *chars("&NotLessSlantEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotLessSlantEqual with a semicolon`() = assertTokenization(
        input = "&NotLessSlantEqual;",
        expectedTokens = listOf(
            *chars("⩽̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotLessTilde without a semicolon`() = assertTokenization(
        input = "&NotLessTilde",
        expectedTokens = listOf(
            *chars("&NotLessTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotLessTilde with a semicolon`() = assertTokenization(
        input = "&NotLessTilde;",
        expectedTokens = listOf(
            char('≴')
        )
    )

    @Test
    fun `Bad named entity NotNestedGreaterGreater without a semicolon`() = assertTokenization(
        input = "&NotNestedGreaterGreater",
        expectedTokens = listOf(
            *chars("&NotNestedGreaterGreater").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotNestedGreaterGreater with a semicolon`() = assertTokenization(
        input = "&NotNestedGreaterGreater;",
        expectedTokens = listOf(
            *chars("⪢̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotNestedLessLess without a semicolon`() = assertTokenization(
        input = "&NotNestedLessLess",
        expectedTokens = listOf(
            *chars("&NotNestedLessLess").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotNestedLessLess with a semicolon`() = assertTokenization(
        input = "&NotNestedLessLess;",
        expectedTokens = listOf(
            *chars("⪡̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotPrecedes without a semicolon`() = assertTokenization(
        input = "&NotPrecedes",
        expectedTokens = listOf(
            *chars("&NotPrecedes").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotPrecedes with a semicolon`() = assertTokenization(
        input = "&NotPrecedes;",
        expectedTokens = listOf(
            char('⊀')
        )
    )

    @Test
    fun `Bad named entity NotPrecedesEqual without a semicolon`() = assertTokenization(
        input = "&NotPrecedesEqual",
        expectedTokens = listOf(
            *chars("&NotPrecedesEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotPrecedesEqual with a semicolon`() = assertTokenization(
        input = "&NotPrecedesEqual;",
        expectedTokens = listOf(
            *chars("⪯̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotPrecedesSlantEqual without a semicolon`() = assertTokenization(
        input = "&NotPrecedesSlantEqual",
        expectedTokens = listOf(
            *chars("&NotPrecedesSlantEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotPrecedesSlantEqual with a semicolon`() = assertTokenization(
        input = "&NotPrecedesSlantEqual;",
        expectedTokens = listOf(
            char('⋠')
        )
    )

    @Test
    fun `Bad named entity NotReverseElement without a semicolon`() = assertTokenization(
        input = "&NotReverseElement",
        expectedTokens = listOf(
            *chars("&NotReverseElement").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotReverseElement with a semicolon`() = assertTokenization(
        input = "&NotReverseElement;",
        expectedTokens = listOf(
            char('∌')
        )
    )

    @Test
    fun `Bad named entity NotRightTriangle without a semicolon`() = assertTokenization(
        input = "&NotRightTriangle",
        expectedTokens = listOf(
            *chars("&NotRightTriangle").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotRightTriangle with a semicolon`() = assertTokenization(
        input = "&NotRightTriangle;",
        expectedTokens = listOf(
            char('⋫')
        )
    )

    @Test
    fun `Bad named entity NotRightTriangleBar without a semicolon`() = assertTokenization(
        input = "&NotRightTriangleBar",
        expectedTokens = listOf(
            *chars("&NotRightTriangleBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotRightTriangleBar with a semicolon`() = assertTokenization(
        input = "&NotRightTriangleBar;",
        expectedTokens = listOf(
            *chars("⧐̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotRightTriangleEqual without a semicolon`() = assertTokenization(
        input = "&NotRightTriangleEqual",
        expectedTokens = listOf(
            *chars("&NotRightTriangleEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotRightTriangleEqual with a semicolon`() = assertTokenization(
        input = "&NotRightTriangleEqual;",
        expectedTokens = listOf(
            char('⋭')
        )
    )

    @Test
    fun `Bad named entity NotSquareSubset without a semicolon`() = assertTokenization(
        input = "&NotSquareSubset",
        expectedTokens = listOf(
            *chars("&NotSquareSubset").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotSquareSubset with a semicolon`() = assertTokenization(
        input = "&NotSquareSubset;",
        expectedTokens = listOf(
            *chars("⊏̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotSquareSubsetEqual without a semicolon`() = assertTokenization(
        input = "&NotSquareSubsetEqual",
        expectedTokens = listOf(
            *chars("&NotSquareSubsetEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotSquareSubsetEqual with a semicolon`() = assertTokenization(
        input = "&NotSquareSubsetEqual;",
        expectedTokens = listOf(
            char('⋢')
        )
    )

    @Test
    fun `Bad named entity NotSquareSuperset without a semicolon`() = assertTokenization(
        input = "&NotSquareSuperset",
        expectedTokens = listOf(
            *chars("&NotSquareSuperset").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotSquareSuperset with a semicolon`() = assertTokenization(
        input = "&NotSquareSuperset;",
        expectedTokens = listOf(
            *chars("⊐̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotSquareSupersetEqual without a semicolon`() = assertTokenization(
        input = "&NotSquareSupersetEqual",
        expectedTokens = listOf(
            *chars("&NotSquareSupersetEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotSquareSupersetEqual with a semicolon`() = assertTokenization(
        input = "&NotSquareSupersetEqual;",
        expectedTokens = listOf(
            char('⋣')
        )
    )

    @Test
    fun `Bad named entity NotSubset without a semicolon`() = assertTokenization(
        input = "&NotSubset",
        expectedTokens = listOf(
            *chars("&NotSubset").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotSubset with a semicolon`() = assertTokenization(
        input = "&NotSubset;",
        expectedTokens = listOf(
            *chars("⊂⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotSubsetEqual without a semicolon`() = assertTokenization(
        input = "&NotSubsetEqual",
        expectedTokens = listOf(
            *chars("&NotSubsetEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotSubsetEqual with a semicolon`() = assertTokenization(
        input = "&NotSubsetEqual;",
        expectedTokens = listOf(
            char('⊈')
        )
    )

    @Test
    fun `Bad named entity NotSucceeds without a semicolon`() = assertTokenization(
        input = "&NotSucceeds",
        expectedTokens = listOf(
            *chars("&NotSucceeds").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotSucceeds with a semicolon`() = assertTokenization(
        input = "&NotSucceeds;",
        expectedTokens = listOf(
            char('⊁')
        )
    )

    @Test
    fun `Bad named entity NotSucceedsEqual without a semicolon`() = assertTokenization(
        input = "&NotSucceedsEqual",
        expectedTokens = listOf(
            *chars("&NotSucceedsEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotSucceedsEqual with a semicolon`() = assertTokenization(
        input = "&NotSucceedsEqual;",
        expectedTokens = listOf(
            *chars("⪰̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotSucceedsSlantEqual without a semicolon`() = assertTokenization(
        input = "&NotSucceedsSlantEqual",
        expectedTokens = listOf(
            *chars("&NotSucceedsSlantEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotSucceedsSlantEqual with a semicolon`() = assertTokenization(
        input = "&NotSucceedsSlantEqual;",
        expectedTokens = listOf(
            char('⋡')
        )
    )

    @Test
    fun `Bad named entity NotSucceedsTilde without a semicolon`() = assertTokenization(
        input = "&NotSucceedsTilde",
        expectedTokens = listOf(
            *chars("&NotSucceedsTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotSucceedsTilde with a semicolon`() = assertTokenization(
        input = "&NotSucceedsTilde;",
        expectedTokens = listOf(
            *chars("≿̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotSuperset without a semicolon`() = assertTokenization(
        input = "&NotSuperset",
        expectedTokens = listOf(
            *chars("&NotSuperset").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotSuperset with a semicolon`() = assertTokenization(
        input = "&NotSuperset;",
        expectedTokens = listOf(
            *chars("⊃⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity NotSupersetEqual without a semicolon`() = assertTokenization(
        input = "&NotSupersetEqual",
        expectedTokens = listOf(
            *chars("&NotSupersetEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotSupersetEqual with a semicolon`() = assertTokenization(
        input = "&NotSupersetEqual;",
        expectedTokens = listOf(
            char('⊉')
        )
    )

    @Test
    fun `Bad named entity NotTilde without a semicolon`() = assertTokenization(
        input = "&NotTilde",
        expectedTokens = listOf(
            *chars("&NotTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotTilde with a semicolon`() = assertTokenization(
        input = "&NotTilde;",
        expectedTokens = listOf(
            char('≁')
        )
    )

    @Test
    fun `Bad named entity NotTildeEqual without a semicolon`() = assertTokenization(
        input = "&NotTildeEqual",
        expectedTokens = listOf(
            *chars("&NotTildeEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotTildeEqual with a semicolon`() = assertTokenization(
        input = "&NotTildeEqual;",
        expectedTokens = listOf(
            char('≄')
        )
    )

    @Test
    fun `Bad named entity NotTildeFullEqual without a semicolon`() = assertTokenization(
        input = "&NotTildeFullEqual",
        expectedTokens = listOf(
            *chars("&NotTildeFullEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotTildeFullEqual with a semicolon`() = assertTokenization(
        input = "&NotTildeFullEqual;",
        expectedTokens = listOf(
            char('≇')
        )
    )

    @Test
    fun `Bad named entity NotTildeTilde without a semicolon`() = assertTokenization(
        input = "&NotTildeTilde",
        expectedTokens = listOf(
            *chars("&NotTildeTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotTildeTilde with a semicolon`() = assertTokenization(
        input = "&NotTildeTilde;",
        expectedTokens = listOf(
            char('≉')
        )
    )

    @Test
    fun `Bad named entity NotVerticalBar without a semicolon`() = assertTokenization(
        input = "&NotVerticalBar",
        expectedTokens = listOf(
            *chars("&NotVerticalBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity NotVerticalBar with a semicolon`() = assertTokenization(
        input = "&NotVerticalBar;",
        expectedTokens = listOf(
            char('∤')
        )
    )

    @Test
    fun `Bad named entity Nscr without a semicolon`() = assertTokenization(
        input = "&Nscr",
        expectedTokens = listOf(
            *chars("&Nscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Nscr with a semicolon`() = assertTokenization(
        input = "&Nscr;",
        expectedTokens = listOf(
            *chars("𝒩").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ntilde without a semicolon`() = assertTokenization(
        input = "&Ntilde",
        expectedTokens = listOf(
            char('Ñ')
        )
    )

    @Test
    fun `Named entity Ntilde with a semicolon`() = assertTokenization(
        input = "&Ntilde;",
        expectedTokens = listOf(
            char('Ñ')
        )
    )

    @Test
    fun `Bad named entity Nu without a semicolon`() = assertTokenization(
        input = "&Nu",
        expectedTokens = listOf(
            *chars("&Nu").toTypedArray()
        )
    )

    @Test
    fun `Named entity Nu with a semicolon`() = assertTokenization(
        input = "&Nu;",
        expectedTokens = listOf(
            char('Ν')
        )
    )

    @Test
    fun `Bad named entity OElig without a semicolon`() = assertTokenization(
        input = "&OElig",
        expectedTokens = listOf(
            *chars("&OElig").toTypedArray()
        )
    )

    @Test
    fun `Named entity OElig with a semicolon`() = assertTokenization(
        input = "&OElig;",
        expectedTokens = listOf(
            char('Œ')
        )
    )

    @Test
    fun `Named entity Oacute without a semicolon`() = assertTokenization(
        input = "&Oacute",
        expectedTokens = listOf(
            char('Ó')
        )
    )

    @Test
    fun `Named entity Oacute with a semicolon`() = assertTokenization(
        input = "&Oacute;",
        expectedTokens = listOf(
            char('Ó')
        )
    )

    @Test
    fun `Named entity Ocirc without a semicolon`() = assertTokenization(
        input = "&Ocirc",
        expectedTokens = listOf(
            char('Ô')
        )
    )

    @Test
    fun `Named entity Ocirc with a semicolon`() = assertTokenization(
        input = "&Ocirc;",
        expectedTokens = listOf(
            char('Ô')
        )
    )

    @Test
    fun `Bad named entity Ocy without a semicolon`() = assertTokenization(
        input = "&Ocy",
        expectedTokens = listOf(
            *chars("&Ocy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ocy with a semicolon`() = assertTokenization(
        input = "&Ocy;",
        expectedTokens = listOf(
            char('О')
        )
    )

    @Test
    fun `Bad named entity Odblac without a semicolon`() = assertTokenization(
        input = "&Odblac",
        expectedTokens = listOf(
            *chars("&Odblac").toTypedArray()
        )
    )

    @Test
    fun `Named entity Odblac with a semicolon`() = assertTokenization(
        input = "&Odblac;",
        expectedTokens = listOf(
            char('Ő')
        )
    )

    @Test
    fun `Bad named entity Ofr without a semicolon`() = assertTokenization(
        input = "&Ofr",
        expectedTokens = listOf(
            *chars("&Ofr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ofr with a semicolon`() = assertTokenization(
        input = "&Ofr;",
        expectedTokens = listOf(
            *chars("𝔒").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ograve without a semicolon`() = assertTokenization(
        input = "&Ograve",
        expectedTokens = listOf(
            char('Ò')
        )
    )

    @Test
    fun `Named entity Ograve with a semicolon`() = assertTokenization(
        input = "&Ograve;",
        expectedTokens = listOf(
            char('Ò')
        )
    )

    @Test
    fun `Bad named entity Omacr without a semicolon`() = assertTokenization(
        input = "&Omacr",
        expectedTokens = listOf(
            *chars("&Omacr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Omacr with a semicolon`() = assertTokenization(
        input = "&Omacr;",
        expectedTokens = listOf(
            char('Ō')
        )
    )

    @Test
    fun `Bad named entity Omega without a semicolon`() = assertTokenization(
        input = "&Omega",
        expectedTokens = listOf(
            *chars("&Omega").toTypedArray()
        )
    )

    @Test
    fun `Named entity Omega with a semicolon`() = assertTokenization(
        input = "&Omega;",
        expectedTokens = listOf(
            char('Ω')
        )
    )

    @Test
    fun `Bad named entity Omicron without a semicolon`() = assertTokenization(
        input = "&Omicron",
        expectedTokens = listOf(
            *chars("&Omicron").toTypedArray()
        )
    )

    @Test
    fun `Named entity Omicron with a semicolon`() = assertTokenization(
        input = "&Omicron;",
        expectedTokens = listOf(
            char('Ο')
        )
    )

    @Test
    fun `Bad named entity Oopf without a semicolon`() = assertTokenization(
        input = "&Oopf",
        expectedTokens = listOf(
            *chars("&Oopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Oopf with a semicolon`() = assertTokenization(
        input = "&Oopf;",
        expectedTokens = listOf(
            *chars("𝕆").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity OpenCurlyDoubleQuote without a semicolon`() = assertTokenization(
        input = "&OpenCurlyDoubleQuote",
        expectedTokens = listOf(
            *chars("&OpenCurlyDoubleQuote").toTypedArray()
        )
    )

    @Test
    fun `Named entity OpenCurlyDoubleQuote with a semicolon`() = assertTokenization(
        input = "&OpenCurlyDoubleQuote;",
        expectedTokens = listOf(
            char('“')
        )
    )

    @Test
    fun `Bad named entity OpenCurlyQuote without a semicolon`() = assertTokenization(
        input = "&OpenCurlyQuote",
        expectedTokens = listOf(
            *chars("&OpenCurlyQuote").toTypedArray()
        )
    )

    @Test
    fun `Named entity OpenCurlyQuote with a semicolon`() = assertTokenization(
        input = "&OpenCurlyQuote;",
        expectedTokens = listOf(
            char('‘')
        )
    )

    @Test
    fun `Bad named entity Or without a semicolon`() = assertTokenization(
        input = "&Or",
        expectedTokens = listOf(
            *chars("&Or").toTypedArray()
        )
    )

    @Test
    fun `Named entity Or with a semicolon`() = assertTokenization(
        input = "&Or;",
        expectedTokens = listOf(
            char('⩔')
        )
    )

    @Test
    fun `Bad named entity Oscr without a semicolon`() = assertTokenization(
        input = "&Oscr",
        expectedTokens = listOf(
            *chars("&Oscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Oscr with a semicolon`() = assertTokenization(
        input = "&Oscr;",
        expectedTokens = listOf(
            *chars("𝒪").toTypedArray()
        )
    )

    @Test
    fun `Named entity Oslash without a semicolon`() = assertTokenization(
        input = "&Oslash",
        expectedTokens = listOf(
            char('Ø')
        )
    )

    @Test
    fun `Named entity Oslash with a semicolon`() = assertTokenization(
        input = "&Oslash;",
        expectedTokens = listOf(
            char('Ø')
        )
    )

    @Test
    fun `Named entity Otilde without a semicolon`() = assertTokenization(
        input = "&Otilde",
        expectedTokens = listOf(
            char('Õ')
        )
    )

    @Test
    fun `Named entity Otilde with a semicolon`() = assertTokenization(
        input = "&Otilde;",
        expectedTokens = listOf(
            char('Õ')
        )
    )

    @Test
    fun `Bad named entity Otimes without a semicolon`() = assertTokenization(
        input = "&Otimes",
        expectedTokens = listOf(
            *chars("&Otimes").toTypedArray()
        )
    )

    @Test
    fun `Named entity Otimes with a semicolon`() = assertTokenization(
        input = "&Otimes;",
        expectedTokens = listOf(
            char('⨷')
        )
    )

    @Test
    fun `Named entity Ouml without a semicolon`() = assertTokenization(
        input = "&Ouml",
        expectedTokens = listOf(
            char('Ö')
        )
    )

    @Test
    fun `Named entity Ouml with a semicolon`() = assertTokenization(
        input = "&Ouml;",
        expectedTokens = listOf(
            char('Ö')
        )
    )

    @Test
    fun `Bad named entity OverBar without a semicolon`() = assertTokenization(
        input = "&OverBar",
        expectedTokens = listOf(
            *chars("&OverBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity OverBar with a semicolon`() = assertTokenization(
        input = "&OverBar;",
        expectedTokens = listOf(
            char('‾')
        )
    )

    @Test
    fun `Bad named entity OverBrace without a semicolon`() = assertTokenization(
        input = "&OverBrace",
        expectedTokens = listOf(
            *chars("&OverBrace").toTypedArray()
        )
    )

    @Test
    fun `Named entity OverBrace with a semicolon`() = assertTokenization(
        input = "&OverBrace;",
        expectedTokens = listOf(
            char('⏞')
        )
    )

    @Test
    fun `Bad named entity OverBracket without a semicolon`() = assertTokenization(
        input = "&OverBracket",
        expectedTokens = listOf(
            *chars("&OverBracket").toTypedArray()
        )
    )

    @Test
    fun `Named entity OverBracket with a semicolon`() = assertTokenization(
        input = "&OverBracket;",
        expectedTokens = listOf(
            char('⎴')
        )
    )

    @Test
    fun `Bad named entity OverParenthesis without a semicolon`() = assertTokenization(
        input = "&OverParenthesis",
        expectedTokens = listOf(
            *chars("&OverParenthesis").toTypedArray()
        )
    )

    @Test
    fun `Named entity OverParenthesis with a semicolon`() = assertTokenization(
        input = "&OverParenthesis;",
        expectedTokens = listOf(
            char('⏜')
        )
    )

    @Test
    fun `Bad named entity PartialD without a semicolon`() = assertTokenization(
        input = "&PartialD",
        expectedTokens = listOf(
            *chars("&PartialD").toTypedArray()
        )
    )

    @Test
    fun `Named entity PartialD with a semicolon`() = assertTokenization(
        input = "&PartialD;",
        expectedTokens = listOf(
            char('∂')
        )
    )

    @Test
    fun `Bad named entity Pcy without a semicolon`() = assertTokenization(
        input = "&Pcy",
        expectedTokens = listOf(
            *chars("&Pcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Pcy with a semicolon`() = assertTokenization(
        input = "&Pcy;",
        expectedTokens = listOf(
            char('П')
        )
    )

    @Test
    fun `Bad named entity Pfr without a semicolon`() = assertTokenization(
        input = "&Pfr",
        expectedTokens = listOf(
            *chars("&Pfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Pfr with a semicolon`() = assertTokenization(
        input = "&Pfr;",
        expectedTokens = listOf(
            *chars("𝔓").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Phi without a semicolon`() = assertTokenization(
        input = "&Phi",
        expectedTokens = listOf(
            *chars("&Phi").toTypedArray()
        )
    )

    @Test
    fun `Named entity Phi with a semicolon`() = assertTokenization(
        input = "&Phi;",
        expectedTokens = listOf(
            char('Φ')
        )
    )

    @Test
    fun `Bad named entity Pi without a semicolon`() = assertTokenization(
        input = "&Pi",
        expectedTokens = listOf(
            *chars("&Pi").toTypedArray()
        )
    )

    @Test
    fun `Named entity Pi with a semicolon`() = assertTokenization(
        input = "&Pi;",
        expectedTokens = listOf(
            char('Π')
        )
    )

    @Test
    fun `Bad named entity PlusMinus without a semicolon`() = assertTokenization(
        input = "&PlusMinus",
        expectedTokens = listOf(
            *chars("&PlusMinus").toTypedArray()
        )
    )

    @Test
    fun `Named entity PlusMinus with a semicolon`() = assertTokenization(
        input = "&PlusMinus;",
        expectedTokens = listOf(
            char('±')
        )
    )

    @Test
    fun `Bad named entity Poincareplane without a semicolon`() = assertTokenization(
        input = "&Poincareplane",
        expectedTokens = listOf(
            *chars("&Poincareplane").toTypedArray()
        )
    )

    @Test
    fun `Named entity Poincareplane with a semicolon`() = assertTokenization(
        input = "&Poincareplane;",
        expectedTokens = listOf(
            char('ℌ')
        )
    )

    @Test
    fun `Bad named entity Popf without a semicolon`() = assertTokenization(
        input = "&Popf",
        expectedTokens = listOf(
            *chars("&Popf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Popf with a semicolon`() = assertTokenization(
        input = "&Popf;",
        expectedTokens = listOf(
            char('ℙ')
        )
    )

    @Test
    fun `Bad named entity Pr without a semicolon`() = assertTokenization(
        input = "&Pr",
        expectedTokens = listOf(
            *chars("&Pr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Pr with a semicolon`() = assertTokenization(
        input = "&Pr;",
        expectedTokens = listOf(
            char('⪻')
        )
    )

    @Test
    fun `Bad named entity Precedes without a semicolon`() = assertTokenization(
        input = "&Precedes",
        expectedTokens = listOf(
            *chars("&Precedes").toTypedArray()
        )
    )

    @Test
    fun `Named entity Precedes with a semicolon`() = assertTokenization(
        input = "&Precedes;",
        expectedTokens = listOf(
            char('≺')
        )
    )

    @Test
    fun `Bad named entity PrecedesEqual without a semicolon`() = assertTokenization(
        input = "&PrecedesEqual",
        expectedTokens = listOf(
            *chars("&PrecedesEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity PrecedesEqual with a semicolon`() = assertTokenization(
        input = "&PrecedesEqual;",
        expectedTokens = listOf(
            char('⪯')
        )
    )

    @Test
    fun `Bad named entity PrecedesSlantEqual without a semicolon`() = assertTokenization(
        input = "&PrecedesSlantEqual",
        expectedTokens = listOf(
            *chars("&PrecedesSlantEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity PrecedesSlantEqual with a semicolon`() = assertTokenization(
        input = "&PrecedesSlantEqual;",
        expectedTokens = listOf(
            char('≼')
        )
    )

    @Test
    fun `Bad named entity PrecedesTilde without a semicolon`() = assertTokenization(
        input = "&PrecedesTilde",
        expectedTokens = listOf(
            *chars("&PrecedesTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity PrecedesTilde with a semicolon`() = assertTokenization(
        input = "&PrecedesTilde;",
        expectedTokens = listOf(
            char('≾')
        )
    )

    @Test
    fun `Bad named entity Prime without a semicolon`() = assertTokenization(
        input = "&Prime",
        expectedTokens = listOf(
            *chars("&Prime").toTypedArray()
        )
    )

    @Test
    fun `Named entity Prime with a semicolon`() = assertTokenization(
        input = "&Prime;",
        expectedTokens = listOf(
            char('″')
        )
    )

    @Test
    fun `Bad named entity Product without a semicolon`() = assertTokenization(
        input = "&Product",
        expectedTokens = listOf(
            *chars("&Product").toTypedArray()
        )
    )

    @Test
    fun `Named entity Product with a semicolon`() = assertTokenization(
        input = "&Product;",
        expectedTokens = listOf(
            char('∏')
        )
    )

    @Test
    fun `Bad named entity Proportion without a semicolon`() = assertTokenization(
        input = "&Proportion",
        expectedTokens = listOf(
            *chars("&Proportion").toTypedArray()
        )
    )

    @Test
    fun `Named entity Proportion with a semicolon`() = assertTokenization(
        input = "&Proportion;",
        expectedTokens = listOf(
            char('∷')
        )
    )

    @Test
    fun `Bad named entity Proportional without a semicolon`() = assertTokenization(
        input = "&Proportional",
        expectedTokens = listOf(
            *chars("&Proportional").toTypedArray()
        )
    )

    @Test
    fun `Named entity Proportional with a semicolon`() = assertTokenization(
        input = "&Proportional;",
        expectedTokens = listOf(
            char('∝')
        )
    )

    @Test
    fun `Bad named entity Pscr without a semicolon`() = assertTokenization(
        input = "&Pscr",
        expectedTokens = listOf(
            *chars("&Pscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Pscr with a semicolon`() = assertTokenization(
        input = "&Pscr;",
        expectedTokens = listOf(
            *chars("𝒫").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Psi without a semicolon`() = assertTokenization(
        input = "&Psi",
        expectedTokens = listOf(
            *chars("&Psi").toTypedArray()
        )
    )

    @Test
    fun `Named entity Psi with a semicolon`() = assertTokenization(
        input = "&Psi;",
        expectedTokens = listOf(
            char('Ψ')
        )
    )

    @Test
    fun `Named entity QUOT without a semicolon`() = assertTokenization(
        input = "&QUOT",
        expectedTokens = listOf(
            char('\"')
        )
    )

    @Test
    fun `Named entity QUOT with a semicolon`() = assertTokenization(
        input = "&QUOT;",
        expectedTokens = listOf(
            char('\"')
        )
    )

    @Test
    fun `Bad named entity Qfr without a semicolon`() = assertTokenization(
        input = "&Qfr",
        expectedTokens = listOf(
            *chars("&Qfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Qfr with a semicolon`() = assertTokenization(
        input = "&Qfr;",
        expectedTokens = listOf(
            *chars("𝔔").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Qopf without a semicolon`() = assertTokenization(
        input = "&Qopf",
        expectedTokens = listOf(
            *chars("&Qopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Qopf with a semicolon`() = assertTokenization(
        input = "&Qopf;",
        expectedTokens = listOf(
            char('ℚ')
        )
    )

    @Test
    fun `Bad named entity Qscr without a semicolon`() = assertTokenization(
        input = "&Qscr",
        expectedTokens = listOf(
            *chars("&Qscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Qscr with a semicolon`() = assertTokenization(
        input = "&Qscr;",
        expectedTokens = listOf(
            *chars("𝒬").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity RBarr without a semicolon`() = assertTokenization(
        input = "&RBarr",
        expectedTokens = listOf(
            *chars("&RBarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity RBarr with a semicolon`() = assertTokenization(
        input = "&RBarr;",
        expectedTokens = listOf(
            char('⤐')
        )
    )

    @Test
    fun `Named entity REG without a semicolon`() = assertTokenization(
        input = "&REG",
        expectedTokens = listOf(
            char('®')
        )
    )

    @Test
    fun `Named entity REG with a semicolon`() = assertTokenization(
        input = "&REG;",
        expectedTokens = listOf(
            char('®')
        )
    )

    @Test
    fun `Bad named entity Racute without a semicolon`() = assertTokenization(
        input = "&Racute",
        expectedTokens = listOf(
            *chars("&Racute").toTypedArray()
        )
    )

    @Test
    fun `Named entity Racute with a semicolon`() = assertTokenization(
        input = "&Racute;",
        expectedTokens = listOf(
            char('Ŕ')
        )
    )

    @Test
    fun `Bad named entity Rang without a semicolon`() = assertTokenization(
        input = "&Rang",
        expectedTokens = listOf(
            *chars("&Rang").toTypedArray()
        )
    )

    @Test
    fun `Named entity Rang with a semicolon`() = assertTokenization(
        input = "&Rang;",
        expectedTokens = listOf(
            char('⟫')
        )
    )

    @Test
    fun `Bad named entity Rarr without a semicolon`() = assertTokenization(
        input = "&Rarr",
        expectedTokens = listOf(
            *chars("&Rarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Rarr with a semicolon`() = assertTokenization(
        input = "&Rarr;",
        expectedTokens = listOf(
            char('↠')
        )
    )

    @Test
    fun `Bad named entity Rarrtl without a semicolon`() = assertTokenization(
        input = "&Rarrtl",
        expectedTokens = listOf(
            *chars("&Rarrtl").toTypedArray()
        )
    )

    @Test
    fun `Named entity Rarrtl with a semicolon`() = assertTokenization(
        input = "&Rarrtl;",
        expectedTokens = listOf(
            char('⤖')
        )
    )

    @Test
    fun `Bad named entity Rcaron without a semicolon`() = assertTokenization(
        input = "&Rcaron",
        expectedTokens = listOf(
            *chars("&Rcaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity Rcaron with a semicolon`() = assertTokenization(
        input = "&Rcaron;",
        expectedTokens = listOf(
            char('Ř')
        )
    )

    @Test
    fun `Bad named entity Rcedil without a semicolon`() = assertTokenization(
        input = "&Rcedil",
        expectedTokens = listOf(
            *chars("&Rcedil").toTypedArray()
        )
    )

    @Test
    fun `Named entity Rcedil with a semicolon`() = assertTokenization(
        input = "&Rcedil;",
        expectedTokens = listOf(
            char('Ŗ')
        )
    )

    @Test
    fun `Bad named entity Rcy without a semicolon`() = assertTokenization(
        input = "&Rcy",
        expectedTokens = listOf(
            *chars("&Rcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Rcy with a semicolon`() = assertTokenization(
        input = "&Rcy;",
        expectedTokens = listOf(
            char('Р')
        )
    )

    @Test
    fun `Bad named entity Re without a semicolon`() = assertTokenization(
        input = "&Re",
        expectedTokens = listOf(
            *chars("&Re").toTypedArray()
        )
    )

    @Test
    fun `Named entity Re with a semicolon`() = assertTokenization(
        input = "&Re;",
        expectedTokens = listOf(
            char('ℜ')
        )
    )

    @Test
    fun `Bad named entity ReverseElement without a semicolon`() = assertTokenization(
        input = "&ReverseElement",
        expectedTokens = listOf(
            *chars("&ReverseElement").toTypedArray()
        )
    )

    @Test
    fun `Named entity ReverseElement with a semicolon`() = assertTokenization(
        input = "&ReverseElement;",
        expectedTokens = listOf(
            char('∋')
        )
    )

    @Test
    fun `Bad named entity ReverseEquilibrium without a semicolon`() = assertTokenization(
        input = "&ReverseEquilibrium",
        expectedTokens = listOf(
            *chars("&ReverseEquilibrium").toTypedArray()
        )
    )

    @Test
    fun `Named entity ReverseEquilibrium with a semicolon`() = assertTokenization(
        input = "&ReverseEquilibrium;",
        expectedTokens = listOf(
            char('⇋')
        )
    )

    @Test
    fun `Bad named entity ReverseUpEquilibrium without a semicolon`() = assertTokenization(
        input = "&ReverseUpEquilibrium",
        expectedTokens = listOf(
            *chars("&ReverseUpEquilibrium").toTypedArray()
        )
    )

    @Test
    fun `Named entity ReverseUpEquilibrium with a semicolon`() = assertTokenization(
        input = "&ReverseUpEquilibrium;",
        expectedTokens = listOf(
            char('⥯')
        )
    )

    @Test
    fun `Bad named entity Rfr without a semicolon`() = assertTokenization(
        input = "&Rfr",
        expectedTokens = listOf(
            *chars("&Rfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Rfr with a semicolon`() = assertTokenization(
        input = "&Rfr;",
        expectedTokens = listOf(
            char('ℜ')
        )
    )

    @Test
    fun `Bad named entity Rho without a semicolon`() = assertTokenization(
        input = "&Rho",
        expectedTokens = listOf(
            *chars("&Rho").toTypedArray()
        )
    )

    @Test
    fun `Named entity Rho with a semicolon`() = assertTokenization(
        input = "&Rho;",
        expectedTokens = listOf(
            char('Ρ')
        )
    )

    @Test
    fun `Bad named entity RightAngleBracket without a semicolon`() = assertTokenization(
        input = "&RightAngleBracket",
        expectedTokens = listOf(
            *chars("&RightAngleBracket").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightAngleBracket with a semicolon`() = assertTokenization(
        input = "&RightAngleBracket;",
        expectedTokens = listOf(
            char('⟩')
        )
    )

    @Test
    fun `Bad named entity RightArrow without a semicolon`() = assertTokenization(
        input = "&RightArrow",
        expectedTokens = listOf(
            *chars("&RightArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightArrow with a semicolon`() = assertTokenization(
        input = "&RightArrow;",
        expectedTokens = listOf(
            char('→')
        )
    )

    @Test
    fun `Bad named entity RightArrowBar without a semicolon`() = assertTokenization(
        input = "&RightArrowBar",
        expectedTokens = listOf(
            *chars("&RightArrowBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightArrowBar with a semicolon`() = assertTokenization(
        input = "&RightArrowBar;",
        expectedTokens = listOf(
            char('⇥')
        )
    )

    @Test
    fun `Bad named entity RightArrowLeftArrow without a semicolon`() = assertTokenization(
        input = "&RightArrowLeftArrow",
        expectedTokens = listOf(
            *chars("&RightArrowLeftArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightArrowLeftArrow with a semicolon`() = assertTokenization(
        input = "&RightArrowLeftArrow;",
        expectedTokens = listOf(
            char('⇄')
        )
    )

    @Test
    fun `Bad named entity RightCeiling without a semicolon`() = assertTokenization(
        input = "&RightCeiling",
        expectedTokens = listOf(
            *chars("&RightCeiling").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightCeiling with a semicolon`() = assertTokenization(
        input = "&RightCeiling;",
        expectedTokens = listOf(
            char('⌉')
        )
    )

    @Test
    fun `Bad named entity RightDoubleBracket without a semicolon`() = assertTokenization(
        input = "&RightDoubleBracket",
        expectedTokens = listOf(
            *chars("&RightDoubleBracket").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightDoubleBracket with a semicolon`() = assertTokenization(
        input = "&RightDoubleBracket;",
        expectedTokens = listOf(
            char('⟧')
        )
    )

    @Test
    fun `Bad named entity RightDownTeeVector without a semicolon`() = assertTokenization(
        input = "&RightDownTeeVector",
        expectedTokens = listOf(
            *chars("&RightDownTeeVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightDownTeeVector with a semicolon`() = assertTokenization(
        input = "&RightDownTeeVector;",
        expectedTokens = listOf(
            char('⥝')
        )
    )

    @Test
    fun `Bad named entity RightDownVector without a semicolon`() = assertTokenization(
        input = "&RightDownVector",
        expectedTokens = listOf(
            *chars("&RightDownVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightDownVector with a semicolon`() = assertTokenization(
        input = "&RightDownVector;",
        expectedTokens = listOf(
            char('⇂')
        )
    )

    @Test
    fun `Bad named entity RightDownVectorBar without a semicolon`() = assertTokenization(
        input = "&RightDownVectorBar",
        expectedTokens = listOf(
            *chars("&RightDownVectorBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightDownVectorBar with a semicolon`() = assertTokenization(
        input = "&RightDownVectorBar;",
        expectedTokens = listOf(
            char('⥕')
        )
    )

    @Test
    fun `Bad named entity RightFloor without a semicolon`() = assertTokenization(
        input = "&RightFloor",
        expectedTokens = listOf(
            *chars("&RightFloor").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightFloor with a semicolon`() = assertTokenization(
        input = "&RightFloor;",
        expectedTokens = listOf(
            char('⌋')
        )
    )

    @Test
    fun `Bad named entity RightTee without a semicolon`() = assertTokenization(
        input = "&RightTee",
        expectedTokens = listOf(
            *chars("&RightTee").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightTee with a semicolon`() = assertTokenization(
        input = "&RightTee;",
        expectedTokens = listOf(
            char('⊢')
        )
    )

    @Test
    fun `Bad named entity RightTeeArrow without a semicolon`() = assertTokenization(
        input = "&RightTeeArrow",
        expectedTokens = listOf(
            *chars("&RightTeeArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightTeeArrow with a semicolon`() = assertTokenization(
        input = "&RightTeeArrow;",
        expectedTokens = listOf(
            char('↦')
        )
    )

    @Test
    fun `Bad named entity RightTeeVector without a semicolon`() = assertTokenization(
        input = "&RightTeeVector",
        expectedTokens = listOf(
            *chars("&RightTeeVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightTeeVector with a semicolon`() = assertTokenization(
        input = "&RightTeeVector;",
        expectedTokens = listOf(
            char('⥛')
        )
    )

    @Test
    fun `Bad named entity RightTriangle without a semicolon`() = assertTokenization(
        input = "&RightTriangle",
        expectedTokens = listOf(
            *chars("&RightTriangle").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightTriangle with a semicolon`() = assertTokenization(
        input = "&RightTriangle;",
        expectedTokens = listOf(
            char('⊳')
        )
    )

    @Test
    fun `Bad named entity RightTriangleBar without a semicolon`() = assertTokenization(
        input = "&RightTriangleBar",
        expectedTokens = listOf(
            *chars("&RightTriangleBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightTriangleBar with a semicolon`() = assertTokenization(
        input = "&RightTriangleBar;",
        expectedTokens = listOf(
            char('⧐')
        )
    )

    @Test
    fun `Bad named entity RightTriangleEqual without a semicolon`() = assertTokenization(
        input = "&RightTriangleEqual",
        expectedTokens = listOf(
            *chars("&RightTriangleEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightTriangleEqual with a semicolon`() = assertTokenization(
        input = "&RightTriangleEqual;",
        expectedTokens = listOf(
            char('⊵')
        )
    )

    @Test
    fun `Bad named entity RightUpDownVector without a semicolon`() = assertTokenization(
        input = "&RightUpDownVector",
        expectedTokens = listOf(
            *chars("&RightUpDownVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightUpDownVector with a semicolon`() = assertTokenization(
        input = "&RightUpDownVector;",
        expectedTokens = listOf(
            char('⥏')
        )
    )

    @Test
    fun `Bad named entity RightUpTeeVector without a semicolon`() = assertTokenization(
        input = "&RightUpTeeVector",
        expectedTokens = listOf(
            *chars("&RightUpTeeVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightUpTeeVector with a semicolon`() = assertTokenization(
        input = "&RightUpTeeVector;",
        expectedTokens = listOf(
            char('⥜')
        )
    )

    @Test
    fun `Bad named entity RightUpVector without a semicolon`() = assertTokenization(
        input = "&RightUpVector",
        expectedTokens = listOf(
            *chars("&RightUpVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightUpVector with a semicolon`() = assertTokenization(
        input = "&RightUpVector;",
        expectedTokens = listOf(
            char('↾')
        )
    )

    @Test
    fun `Bad named entity RightUpVectorBar without a semicolon`() = assertTokenization(
        input = "&RightUpVectorBar",
        expectedTokens = listOf(
            *chars("&RightUpVectorBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightUpVectorBar with a semicolon`() = assertTokenization(
        input = "&RightUpVectorBar;",
        expectedTokens = listOf(
            char('⥔')
        )
    )

    @Test
    fun `Bad named entity RightVector without a semicolon`() = assertTokenization(
        input = "&RightVector",
        expectedTokens = listOf(
            *chars("&RightVector").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightVector with a semicolon`() = assertTokenization(
        input = "&RightVector;",
        expectedTokens = listOf(
            char('⇀')
        )
    )

    @Test
    fun `Bad named entity RightVectorBar without a semicolon`() = assertTokenization(
        input = "&RightVectorBar",
        expectedTokens = listOf(
            *chars("&RightVectorBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity RightVectorBar with a semicolon`() = assertTokenization(
        input = "&RightVectorBar;",
        expectedTokens = listOf(
            char('⥓')
        )
    )

    @Test
    fun `Bad named entity Rightarrow without a semicolon`() = assertTokenization(
        input = "&Rightarrow",
        expectedTokens = listOf(
            *chars("&Rightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity Rightarrow with a semicolon`() = assertTokenization(
        input = "&Rightarrow;",
        expectedTokens = listOf(
            char('⇒')
        )
    )

    @Test
    fun `Bad named entity Ropf without a semicolon`() = assertTokenization(
        input = "&Ropf",
        expectedTokens = listOf(
            *chars("&Ropf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ropf with a semicolon`() = assertTokenization(
        input = "&Ropf;",
        expectedTokens = listOf(
            char('ℝ')
        )
    )

    @Test
    fun `Bad named entity RoundImplies without a semicolon`() = assertTokenization(
        input = "&RoundImplies",
        expectedTokens = listOf(
            *chars("&RoundImplies").toTypedArray()
        )
    )

    @Test
    fun `Named entity RoundImplies with a semicolon`() = assertTokenization(
        input = "&RoundImplies;",
        expectedTokens = listOf(
            char('⥰')
        )
    )

    @Test
    fun `Bad named entity Rrightarrow without a semicolon`() = assertTokenization(
        input = "&Rrightarrow",
        expectedTokens = listOf(
            *chars("&Rrightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity Rrightarrow with a semicolon`() = assertTokenization(
        input = "&Rrightarrow;",
        expectedTokens = listOf(
            char('⇛')
        )
    )

    @Test
    fun `Bad named entity Rscr without a semicolon`() = assertTokenization(
        input = "&Rscr",
        expectedTokens = listOf(
            *chars("&Rscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Rscr with a semicolon`() = assertTokenization(
        input = "&Rscr;",
        expectedTokens = listOf(
            char('ℛ')
        )
    )

    @Test
    fun `Bad named entity Rsh without a semicolon`() = assertTokenization(
        input = "&Rsh",
        expectedTokens = listOf(
            *chars("&Rsh").toTypedArray()
        )
    )

    @Test
    fun `Named entity Rsh with a semicolon`() = assertTokenization(
        input = "&Rsh;",
        expectedTokens = listOf(
            char('↱')
        )
    )

    @Test
    fun `Bad named entity RuleDelayed without a semicolon`() = assertTokenization(
        input = "&RuleDelayed",
        expectedTokens = listOf(
            *chars("&RuleDelayed").toTypedArray()
        )
    )

    @Test
    fun `Named entity RuleDelayed with a semicolon`() = assertTokenization(
        input = "&RuleDelayed;",
        expectedTokens = listOf(
            char('⧴')
        )
    )

    @Test
    fun `Bad named entity SHCHcy without a semicolon`() = assertTokenization(
        input = "&SHCHcy",
        expectedTokens = listOf(
            *chars("&SHCHcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity SHCHcy with a semicolon`() = assertTokenization(
        input = "&SHCHcy;",
        expectedTokens = listOf(
            char('Щ')
        )
    )

    @Test
    fun `Bad named entity SHcy without a semicolon`() = assertTokenization(
        input = "&SHcy",
        expectedTokens = listOf(
            *chars("&SHcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity SHcy with a semicolon`() = assertTokenization(
        input = "&SHcy;",
        expectedTokens = listOf(
            char('Ш')
        )
    )

    @Test
    fun `Bad named entity SOFTcy without a semicolon`() = assertTokenization(
        input = "&SOFTcy",
        expectedTokens = listOf(
            *chars("&SOFTcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity SOFTcy with a semicolon`() = assertTokenization(
        input = "&SOFTcy;",
        expectedTokens = listOf(
            char('Ь')
        )
    )

    @Test
    fun `Bad named entity Sacute without a semicolon`() = assertTokenization(
        input = "&Sacute",
        expectedTokens = listOf(
            *chars("&Sacute").toTypedArray()
        )
    )

    @Test
    fun `Named entity Sacute with a semicolon`() = assertTokenization(
        input = "&Sacute;",
        expectedTokens = listOf(
            char('Ś')
        )
    )

    @Test
    fun `Bad named entity Sc without a semicolon`() = assertTokenization(
        input = "&Sc",
        expectedTokens = listOf(
            *chars("&Sc").toTypedArray()
        )
    )

    @Test
    fun `Named entity Sc with a semicolon`() = assertTokenization(
        input = "&Sc;",
        expectedTokens = listOf(
            char('⪼')
        )
    )

    @Test
    fun `Bad named entity Scaron without a semicolon`() = assertTokenization(
        input = "&Scaron",
        expectedTokens = listOf(
            *chars("&Scaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity Scaron with a semicolon`() = assertTokenization(
        input = "&Scaron;",
        expectedTokens = listOf(
            char('Š')
        )
    )

    @Test
    fun `Bad named entity Scedil without a semicolon`() = assertTokenization(
        input = "&Scedil",
        expectedTokens = listOf(
            *chars("&Scedil").toTypedArray()
        )
    )

    @Test
    fun `Named entity Scedil with a semicolon`() = assertTokenization(
        input = "&Scedil;",
        expectedTokens = listOf(
            char('Ş')
        )
    )

    @Test
    fun `Bad named entity Scirc without a semicolon`() = assertTokenization(
        input = "&Scirc",
        expectedTokens = listOf(
            *chars("&Scirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity Scirc with a semicolon`() = assertTokenization(
        input = "&Scirc;",
        expectedTokens = listOf(
            char('Ŝ')
        )
    )

    @Test
    fun `Bad named entity Scy without a semicolon`() = assertTokenization(
        input = "&Scy",
        expectedTokens = listOf(
            *chars("&Scy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Scy with a semicolon`() = assertTokenization(
        input = "&Scy;",
        expectedTokens = listOf(
            char('С')
        )
    )

    @Test
    fun `Bad named entity Sfr without a semicolon`() = assertTokenization(
        input = "&Sfr",
        expectedTokens = listOf(
            *chars("&Sfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Sfr with a semicolon`() = assertTokenization(
        input = "&Sfr;",
        expectedTokens = listOf(
            *chars("𝔖").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ShortDownArrow without a semicolon`() = assertTokenization(
        input = "&ShortDownArrow",
        expectedTokens = listOf(
            *chars("&ShortDownArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity ShortDownArrow with a semicolon`() = assertTokenization(
        input = "&ShortDownArrow;",
        expectedTokens = listOf(
            char('↓')
        )
    )

    @Test
    fun `Bad named entity ShortLeftArrow without a semicolon`() = assertTokenization(
        input = "&ShortLeftArrow",
        expectedTokens = listOf(
            *chars("&ShortLeftArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity ShortLeftArrow with a semicolon`() = assertTokenization(
        input = "&ShortLeftArrow;",
        expectedTokens = listOf(
            char('←')
        )
    )

    @Test
    fun `Bad named entity ShortRightArrow without a semicolon`() = assertTokenization(
        input = "&ShortRightArrow",
        expectedTokens = listOf(
            *chars("&ShortRightArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity ShortRightArrow with a semicolon`() = assertTokenization(
        input = "&ShortRightArrow;",
        expectedTokens = listOf(
            char('→')
        )
    )

    @Test
    fun `Bad named entity ShortUpArrow without a semicolon`() = assertTokenization(
        input = "&ShortUpArrow",
        expectedTokens = listOf(
            *chars("&ShortUpArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity ShortUpArrow with a semicolon`() = assertTokenization(
        input = "&ShortUpArrow;",
        expectedTokens = listOf(
            char('↑')
        )
    )

    @Test
    fun `Bad named entity Sigma without a semicolon`() = assertTokenization(
        input = "&Sigma",
        expectedTokens = listOf(
            *chars("&Sigma").toTypedArray()
        )
    )

    @Test
    fun `Named entity Sigma with a semicolon`() = assertTokenization(
        input = "&Sigma;",
        expectedTokens = listOf(
            char('Σ')
        )
    )

    @Test
    fun `Bad named entity SmallCircle without a semicolon`() = assertTokenization(
        input = "&SmallCircle",
        expectedTokens = listOf(
            *chars("&SmallCircle").toTypedArray()
        )
    )

    @Test
    fun `Named entity SmallCircle with a semicolon`() = assertTokenization(
        input = "&SmallCircle;",
        expectedTokens = listOf(
            char('∘')
        )
    )

    @Test
    fun `Bad named entity Sopf without a semicolon`() = assertTokenization(
        input = "&Sopf",
        expectedTokens = listOf(
            *chars("&Sopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Sopf with a semicolon`() = assertTokenization(
        input = "&Sopf;",
        expectedTokens = listOf(
            *chars("𝕊").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Sqrt without a semicolon`() = assertTokenization(
        input = "&Sqrt",
        expectedTokens = listOf(
            *chars("&Sqrt").toTypedArray()
        )
    )

    @Test
    fun `Named entity Sqrt with a semicolon`() = assertTokenization(
        input = "&Sqrt;",
        expectedTokens = listOf(
            char('√')
        )
    )

    @Test
    fun `Bad named entity Square without a semicolon`() = assertTokenization(
        input = "&Square",
        expectedTokens = listOf(
            *chars("&Square").toTypedArray()
        )
    )

    @Test
    fun `Named entity Square with a semicolon`() = assertTokenization(
        input = "&Square;",
        expectedTokens = listOf(
            char('□')
        )
    )

    @Test
    fun `Bad named entity SquareIntersection without a semicolon`() = assertTokenization(
        input = "&SquareIntersection",
        expectedTokens = listOf(
            *chars("&SquareIntersection").toTypedArray()
        )
    )

    @Test
    fun `Named entity SquareIntersection with a semicolon`() = assertTokenization(
        input = "&SquareIntersection;",
        expectedTokens = listOf(
            char('⊓')
        )
    )

    @Test
    fun `Bad named entity SquareSubset without a semicolon`() = assertTokenization(
        input = "&SquareSubset",
        expectedTokens = listOf(
            *chars("&SquareSubset").toTypedArray()
        )
    )

    @Test
    fun `Named entity SquareSubset with a semicolon`() = assertTokenization(
        input = "&SquareSubset;",
        expectedTokens = listOf(
            char('⊏')
        )
    )

    @Test
    fun `Bad named entity SquareSubsetEqual without a semicolon`() = assertTokenization(
        input = "&SquareSubsetEqual",
        expectedTokens = listOf(
            *chars("&SquareSubsetEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity SquareSubsetEqual with a semicolon`() = assertTokenization(
        input = "&SquareSubsetEqual;",
        expectedTokens = listOf(
            char('⊑')
        )
    )

    @Test
    fun `Bad named entity SquareSuperset without a semicolon`() = assertTokenization(
        input = "&SquareSuperset",
        expectedTokens = listOf(
            *chars("&SquareSuperset").toTypedArray()
        )
    )

    @Test
    fun `Named entity SquareSuperset with a semicolon`() = assertTokenization(
        input = "&SquareSuperset;",
        expectedTokens = listOf(
            char('⊐')
        )
    )

    @Test
    fun `Bad named entity SquareSupersetEqual without a semicolon`() = assertTokenization(
        input = "&SquareSupersetEqual",
        expectedTokens = listOf(
            *chars("&SquareSupersetEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity SquareSupersetEqual with a semicolon`() = assertTokenization(
        input = "&SquareSupersetEqual;",
        expectedTokens = listOf(
            char('⊒')
        )
    )

    @Test
    fun `Bad named entity SquareUnion without a semicolon`() = assertTokenization(
        input = "&SquareUnion",
        expectedTokens = listOf(
            *chars("&SquareUnion").toTypedArray()
        )
    )

    @Test
    fun `Named entity SquareUnion with a semicolon`() = assertTokenization(
        input = "&SquareUnion;",
        expectedTokens = listOf(
            char('⊔')
        )
    )

    @Test
    fun `Bad named entity Sscr without a semicolon`() = assertTokenization(
        input = "&Sscr",
        expectedTokens = listOf(
            *chars("&Sscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Sscr with a semicolon`() = assertTokenization(
        input = "&Sscr;",
        expectedTokens = listOf(
            *chars("𝒮").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Star without a semicolon`() = assertTokenization(
        input = "&Star",
        expectedTokens = listOf(
            *chars("&Star").toTypedArray()
        )
    )

    @Test
    fun `Named entity Star with a semicolon`() = assertTokenization(
        input = "&Star;",
        expectedTokens = listOf(
            char('⋆')
        )
    )

    @Test
    fun `Bad named entity Sub without a semicolon`() = assertTokenization(
        input = "&Sub",
        expectedTokens = listOf(
            *chars("&Sub").toTypedArray()
        )
    )

    @Test
    fun `Named entity Sub with a semicolon`() = assertTokenization(
        input = "&Sub;",
        expectedTokens = listOf(
            char('⋐')
        )
    )

    @Test
    fun `Bad named entity Subset without a semicolon`() = assertTokenization(
        input = "&Subset",
        expectedTokens = listOf(
            *chars("&Subset").toTypedArray()
        )
    )

    @Test
    fun `Named entity Subset with a semicolon`() = assertTokenization(
        input = "&Subset;",
        expectedTokens = listOf(
            char('⋐')
        )
    )

    @Test
    fun `Bad named entity SubsetEqual without a semicolon`() = assertTokenization(
        input = "&SubsetEqual",
        expectedTokens = listOf(
            *chars("&SubsetEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity SubsetEqual with a semicolon`() = assertTokenization(
        input = "&SubsetEqual;",
        expectedTokens = listOf(
            char('⊆')
        )
    )

    @Test
    fun `Bad named entity Succeeds without a semicolon`() = assertTokenization(
        input = "&Succeeds",
        expectedTokens = listOf(
            *chars("&Succeeds").toTypedArray()
        )
    )

    @Test
    fun `Named entity Succeeds with a semicolon`() = assertTokenization(
        input = "&Succeeds;",
        expectedTokens = listOf(
            char('≻')
        )
    )

    @Test
    fun `Bad named entity SucceedsEqual without a semicolon`() = assertTokenization(
        input = "&SucceedsEqual",
        expectedTokens = listOf(
            *chars("&SucceedsEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity SucceedsEqual with a semicolon`() = assertTokenization(
        input = "&SucceedsEqual;",
        expectedTokens = listOf(
            char('⪰')
        )
    )

    @Test
    fun `Bad named entity SucceedsSlantEqual without a semicolon`() = assertTokenization(
        input = "&SucceedsSlantEqual",
        expectedTokens = listOf(
            *chars("&SucceedsSlantEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity SucceedsSlantEqual with a semicolon`() = assertTokenization(
        input = "&SucceedsSlantEqual;",
        expectedTokens = listOf(
            char('≽')
        )
    )

    @Test
    fun `Bad named entity SucceedsTilde without a semicolon`() = assertTokenization(
        input = "&SucceedsTilde",
        expectedTokens = listOf(
            *chars("&SucceedsTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity SucceedsTilde with a semicolon`() = assertTokenization(
        input = "&SucceedsTilde;",
        expectedTokens = listOf(
            char('≿')
        )
    )

    @Test
    fun `Bad named entity SuchThat without a semicolon`() = assertTokenization(
        input = "&SuchThat",
        expectedTokens = listOf(
            *chars("&SuchThat").toTypedArray()
        )
    )

    @Test
    fun `Named entity SuchThat with a semicolon`() = assertTokenization(
        input = "&SuchThat;",
        expectedTokens = listOf(
            char('∋')
        )
    )

    @Test
    fun `Bad named entity Sum without a semicolon`() = assertTokenization(
        input = "&Sum",
        expectedTokens = listOf(
            *chars("&Sum").toTypedArray()
        )
    )

    @Test
    fun `Named entity Sum with a semicolon`() = assertTokenization(
        input = "&Sum;",
        expectedTokens = listOf(
            char('∑')
        )
    )

    @Test
    fun `Bad named entity Sup without a semicolon`() = assertTokenization(
        input = "&Sup",
        expectedTokens = listOf(
            *chars("&Sup").toTypedArray()
        )
    )

    @Test
    fun `Named entity Sup with a semicolon`() = assertTokenization(
        input = "&Sup;",
        expectedTokens = listOf(
            char('⋑')
        )
    )

    @Test
    fun `Bad named entity Superset without a semicolon`() = assertTokenization(
        input = "&Superset",
        expectedTokens = listOf(
            *chars("&Superset").toTypedArray()
        )
    )

    @Test
    fun `Named entity Superset with a semicolon`() = assertTokenization(
        input = "&Superset;",
        expectedTokens = listOf(
            char('⊃')
        )
    )

    @Test
    fun `Bad named entity SupersetEqual without a semicolon`() = assertTokenization(
        input = "&SupersetEqual",
        expectedTokens = listOf(
            *chars("&SupersetEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity SupersetEqual with a semicolon`() = assertTokenization(
        input = "&SupersetEqual;",
        expectedTokens = listOf(
            char('⊇')
        )
    )

    @Test
    fun `Bad named entity Supset without a semicolon`() = assertTokenization(
        input = "&Supset",
        expectedTokens = listOf(
            *chars("&Supset").toTypedArray()
        )
    )

    @Test
    fun `Named entity Supset with a semicolon`() = assertTokenization(
        input = "&Supset;",
        expectedTokens = listOf(
            char('⋑')
        )
    )

    @Test
    fun `Named entity THORN without a semicolon`() = assertTokenization(
        input = "&THORN",
        expectedTokens = listOf(
            char('Þ')
        )
    )

    @Test
    fun `Named entity THORN with a semicolon`() = assertTokenization(
        input = "&THORN;",
        expectedTokens = listOf(
            char('Þ')
        )
    )

    @Test
    fun `Bad named entity TRADE without a semicolon`() = assertTokenization(
        input = "&TRADE",
        expectedTokens = listOf(
            *chars("&TRADE").toTypedArray()
        )
    )

    @Test
    fun `Named entity TRADE with a semicolon`() = assertTokenization(
        input = "&TRADE;",
        expectedTokens = listOf(
            char('™')
        )
    )

    @Test
    fun `Bad named entity TSHcy without a semicolon`() = assertTokenization(
        input = "&TSHcy",
        expectedTokens = listOf(
            *chars("&TSHcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity TSHcy with a semicolon`() = assertTokenization(
        input = "&TSHcy;",
        expectedTokens = listOf(
            char('Ћ')
        )
    )

    @Test
    fun `Bad named entity TScy without a semicolon`() = assertTokenization(
        input = "&TScy",
        expectedTokens = listOf(
            *chars("&TScy").toTypedArray()
        )
    )

    @Test
    fun `Named entity TScy with a semicolon`() = assertTokenization(
        input = "&TScy;",
        expectedTokens = listOf(
            char('Ц')
        )
    )

    @Test
    fun `Bad named entity Tab without a semicolon`() = assertTokenization(
        input = "&Tab",
        expectedTokens = listOf(
            *chars("&Tab").toTypedArray()
        )
    )

    @Test
    fun `Named entity Tab with a semicolon`() = assertTokenization(
        input = "&Tab;",
        expectedTokens = listOf(
            char('\t')
        )
    )

    @Test
    fun `Bad named entity Tau without a semicolon`() = assertTokenization(
        input = "&Tau",
        expectedTokens = listOf(
            *chars("&Tau").toTypedArray()
        )
    )

    @Test
    fun `Named entity Tau with a semicolon`() = assertTokenization(
        input = "&Tau;",
        expectedTokens = listOf(
            char('Τ')
        )
    )

    @Test
    fun `Bad named entity Tcaron without a semicolon`() = assertTokenization(
        input = "&Tcaron",
        expectedTokens = listOf(
            *chars("&Tcaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity Tcaron with a semicolon`() = assertTokenization(
        input = "&Tcaron;",
        expectedTokens = listOf(
            char('Ť')
        )
    )

    @Test
    fun `Bad named entity Tcedil without a semicolon`() = assertTokenization(
        input = "&Tcedil",
        expectedTokens = listOf(
            *chars("&Tcedil").toTypedArray()
        )
    )

    @Test
    fun `Named entity Tcedil with a semicolon`() = assertTokenization(
        input = "&Tcedil;",
        expectedTokens = listOf(
            char('Ţ')
        )
    )

    @Test
    fun `Bad named entity Tcy without a semicolon`() = assertTokenization(
        input = "&Tcy",
        expectedTokens = listOf(
            *chars("&Tcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Tcy with a semicolon`() = assertTokenization(
        input = "&Tcy;",
        expectedTokens = listOf(
            char('Т')
        )
    )

    @Test
    fun `Bad named entity Tfr without a semicolon`() = assertTokenization(
        input = "&Tfr",
        expectedTokens = listOf(
            *chars("&Tfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Tfr with a semicolon`() = assertTokenization(
        input = "&Tfr;",
        expectedTokens = listOf(
            *chars("𝔗").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Therefore without a semicolon`() = assertTokenization(
        input = "&Therefore",
        expectedTokens = listOf(
            *chars("&Therefore").toTypedArray()
        )
    )

    @Test
    fun `Named entity Therefore with a semicolon`() = assertTokenization(
        input = "&Therefore;",
        expectedTokens = listOf(
            char('∴')
        )
    )

    @Test
    fun `Bad named entity Theta without a semicolon`() = assertTokenization(
        input = "&Theta",
        expectedTokens = listOf(
            *chars("&Theta").toTypedArray()
        )
    )

    @Test
    fun `Named entity Theta with a semicolon`() = assertTokenization(
        input = "&Theta;",
        expectedTokens = listOf(
            char('Θ')
        )
    )

    @Test
    fun `Bad named entity ThickSpace without a semicolon`() = assertTokenization(
        input = "&ThickSpace",
        expectedTokens = listOf(
            *chars("&ThickSpace").toTypedArray()
        )
    )

    @Test
    fun `Named entity ThickSpace with a semicolon`() = assertTokenization(
        input = "&ThickSpace;",
        expectedTokens = listOf(
            *chars("  ").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ThinSpace without a semicolon`() = assertTokenization(
        input = "&ThinSpace",
        expectedTokens = listOf(
            *chars("&ThinSpace").toTypedArray()
        )
    )

    @Test
    fun `Named entity ThinSpace with a semicolon`() = assertTokenization(
        input = "&ThinSpace;",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Bad named entity Tilde without a semicolon`() = assertTokenization(
        input = "&Tilde",
        expectedTokens = listOf(
            *chars("&Tilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity Tilde with a semicolon`() = assertTokenization(
        input = "&Tilde;",
        expectedTokens = listOf(
            char('∼')
        )
    )

    @Test
    fun `Bad named entity TildeEqual without a semicolon`() = assertTokenization(
        input = "&TildeEqual",
        expectedTokens = listOf(
            *chars("&TildeEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity TildeEqual with a semicolon`() = assertTokenization(
        input = "&TildeEqual;",
        expectedTokens = listOf(
            char('≃')
        )
    )

    @Test
    fun `Bad named entity TildeFullEqual without a semicolon`() = assertTokenization(
        input = "&TildeFullEqual",
        expectedTokens = listOf(
            *chars("&TildeFullEqual").toTypedArray()
        )
    )

    @Test
    fun `Named entity TildeFullEqual with a semicolon`() = assertTokenization(
        input = "&TildeFullEqual;",
        expectedTokens = listOf(
            char('≅')
        )
    )

    @Test
    fun `Bad named entity TildeTilde without a semicolon`() = assertTokenization(
        input = "&TildeTilde",
        expectedTokens = listOf(
            *chars("&TildeTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity TildeTilde with a semicolon`() = assertTokenization(
        input = "&TildeTilde;",
        expectedTokens = listOf(
            char('≈')
        )
    )

    @Test
    fun `Bad named entity Topf without a semicolon`() = assertTokenization(
        input = "&Topf",
        expectedTokens = listOf(
            *chars("&Topf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Topf with a semicolon`() = assertTokenization(
        input = "&Topf;",
        expectedTokens = listOf(
            *chars("𝕋").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity TripleDot without a semicolon`() = assertTokenization(
        input = "&TripleDot",
        expectedTokens = listOf(
            *chars("&TripleDot").toTypedArray()
        )
    )

    @Test
    fun `Named entity TripleDot with a semicolon`() = assertTokenization(
        input = "&TripleDot;",
        expectedTokens = listOf(
            char('⃛')
        )
    )

    @Test
    fun `Bad named entity Tscr without a semicolon`() = assertTokenization(
        input = "&Tscr",
        expectedTokens = listOf(
            *chars("&Tscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Tscr with a semicolon`() = assertTokenization(
        input = "&Tscr;",
        expectedTokens = listOf(
            *chars("𝒯").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Tstrok without a semicolon`() = assertTokenization(
        input = "&Tstrok",
        expectedTokens = listOf(
            *chars("&Tstrok").toTypedArray()
        )
    )

    @Test
    fun `Named entity Tstrok with a semicolon`() = assertTokenization(
        input = "&Tstrok;",
        expectedTokens = listOf(
            char('Ŧ')
        )
    )

    @Test
    fun `Named entity Uacute without a semicolon`() = assertTokenization(
        input = "&Uacute",
        expectedTokens = listOf(
            char('Ú')
        )
    )

    @Test
    fun `Named entity Uacute with a semicolon`() = assertTokenization(
        input = "&Uacute;",
        expectedTokens = listOf(
            char('Ú')
        )
    )

    @Test
    fun `Bad named entity Uarr without a semicolon`() = assertTokenization(
        input = "&Uarr",
        expectedTokens = listOf(
            *chars("&Uarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Uarr with a semicolon`() = assertTokenization(
        input = "&Uarr;",
        expectedTokens = listOf(
            char('↟')
        )
    )

    @Test
    fun `Bad named entity Uarrocir without a semicolon`() = assertTokenization(
        input = "&Uarrocir",
        expectedTokens = listOf(
            *chars("&Uarrocir").toTypedArray()
        )
    )

    @Test
    fun `Named entity Uarrocir with a semicolon`() = assertTokenization(
        input = "&Uarrocir;",
        expectedTokens = listOf(
            char('⥉')
        )
    )

    @Test
    fun `Bad named entity Ubrcy without a semicolon`() = assertTokenization(
        input = "&Ubrcy",
        expectedTokens = listOf(
            *chars("&Ubrcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ubrcy with a semicolon`() = assertTokenization(
        input = "&Ubrcy;",
        expectedTokens = listOf(
            char('Ў')
        )
    )

    @Test
    fun `Bad named entity Ubreve without a semicolon`() = assertTokenization(
        input = "&Ubreve",
        expectedTokens = listOf(
            *chars("&Ubreve").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ubreve with a semicolon`() = assertTokenization(
        input = "&Ubreve;",
        expectedTokens = listOf(
            char('Ŭ')
        )
    )

    @Test
    fun `Named entity Ucirc without a semicolon`() = assertTokenization(
        input = "&Ucirc",
        expectedTokens = listOf(
            char('Û')
        )
    )

    @Test
    fun `Named entity Ucirc with a semicolon`() = assertTokenization(
        input = "&Ucirc;",
        expectedTokens = listOf(
            char('Û')
        )
    )

    @Test
    fun `Bad named entity Ucy without a semicolon`() = assertTokenization(
        input = "&Ucy",
        expectedTokens = listOf(
            *chars("&Ucy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ucy with a semicolon`() = assertTokenization(
        input = "&Ucy;",
        expectedTokens = listOf(
            char('У')
        )
    )

    @Test
    fun `Bad named entity Udblac without a semicolon`() = assertTokenization(
        input = "&Udblac",
        expectedTokens = listOf(
            *chars("&Udblac").toTypedArray()
        )
    )

    @Test
    fun `Named entity Udblac with a semicolon`() = assertTokenization(
        input = "&Udblac;",
        expectedTokens = listOf(
            char('Ű')
        )
    )

    @Test
    fun `Bad named entity Ufr without a semicolon`() = assertTokenization(
        input = "&Ufr",
        expectedTokens = listOf(
            *chars("&Ufr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ufr with a semicolon`() = assertTokenization(
        input = "&Ufr;",
        expectedTokens = listOf(
            *chars("𝔘").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ugrave without a semicolon`() = assertTokenization(
        input = "&Ugrave",
        expectedTokens = listOf(
            char('Ù')
        )
    )

    @Test
    fun `Named entity Ugrave with a semicolon`() = assertTokenization(
        input = "&Ugrave;",
        expectedTokens = listOf(
            char('Ù')
        )
    )

    @Test
    fun `Bad named entity Umacr without a semicolon`() = assertTokenization(
        input = "&Umacr",
        expectedTokens = listOf(
            *chars("&Umacr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Umacr with a semicolon`() = assertTokenization(
        input = "&Umacr;",
        expectedTokens = listOf(
            char('Ū')
        )
    )

    @Test
    fun `Bad named entity UnderBar without a semicolon`() = assertTokenization(
        input = "&UnderBar",
        expectedTokens = listOf(
            *chars("&UnderBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity UnderBar with a semicolon`() = assertTokenization(
        input = "&UnderBar;",
        expectedTokens = listOf(
            char('_')
        )
    )

    @Test
    fun `Bad named entity UnderBrace without a semicolon`() = assertTokenization(
        input = "&UnderBrace",
        expectedTokens = listOf(
            *chars("&UnderBrace").toTypedArray()
        )
    )

    @Test
    fun `Named entity UnderBrace with a semicolon`() = assertTokenization(
        input = "&UnderBrace;",
        expectedTokens = listOf(
            char('⏟')
        )
    )

    @Test
    fun `Bad named entity UnderBracket without a semicolon`() = assertTokenization(
        input = "&UnderBracket",
        expectedTokens = listOf(
            *chars("&UnderBracket").toTypedArray()
        )
    )

    @Test
    fun `Named entity UnderBracket with a semicolon`() = assertTokenization(
        input = "&UnderBracket;",
        expectedTokens = listOf(
            char('⎵')
        )
    )

    @Test
    fun `Bad named entity UnderParenthesis without a semicolon`() = assertTokenization(
        input = "&UnderParenthesis",
        expectedTokens = listOf(
            *chars("&UnderParenthesis").toTypedArray()
        )
    )

    @Test
    fun `Named entity UnderParenthesis with a semicolon`() = assertTokenization(
        input = "&UnderParenthesis;",
        expectedTokens = listOf(
            char('⏝')
        )
    )

    @Test
    fun `Bad named entity Union without a semicolon`() = assertTokenization(
        input = "&Union",
        expectedTokens = listOf(
            *chars("&Union").toTypedArray()
        )
    )

    @Test
    fun `Named entity Union with a semicolon`() = assertTokenization(
        input = "&Union;",
        expectedTokens = listOf(
            char('⋃')
        )
    )

    @Test
    fun `Bad named entity UnionPlus without a semicolon`() = assertTokenization(
        input = "&UnionPlus",
        expectedTokens = listOf(
            *chars("&UnionPlus").toTypedArray()
        )
    )

    @Test
    fun `Named entity UnionPlus with a semicolon`() = assertTokenization(
        input = "&UnionPlus;",
        expectedTokens = listOf(
            char('⊎')
        )
    )

    @Test
    fun `Bad named entity Uogon without a semicolon`() = assertTokenization(
        input = "&Uogon",
        expectedTokens = listOf(
            *chars("&Uogon").toTypedArray()
        )
    )

    @Test
    fun `Named entity Uogon with a semicolon`() = assertTokenization(
        input = "&Uogon;",
        expectedTokens = listOf(
            char('Ų')
        )
    )

    @Test
    fun `Bad named entity Uopf without a semicolon`() = assertTokenization(
        input = "&Uopf",
        expectedTokens = listOf(
            *chars("&Uopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Uopf with a semicolon`() = assertTokenization(
        input = "&Uopf;",
        expectedTokens = listOf(
            *chars("𝕌").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity UpArrow without a semicolon`() = assertTokenization(
        input = "&UpArrow",
        expectedTokens = listOf(
            *chars("&UpArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity UpArrow with a semicolon`() = assertTokenization(
        input = "&UpArrow;",
        expectedTokens = listOf(
            char('↑')
        )
    )

    @Test
    fun `Bad named entity UpArrowBar without a semicolon`() = assertTokenization(
        input = "&UpArrowBar",
        expectedTokens = listOf(
            *chars("&UpArrowBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity UpArrowBar with a semicolon`() = assertTokenization(
        input = "&UpArrowBar;",
        expectedTokens = listOf(
            char('⤒')
        )
    )

    @Test
    fun `Bad named entity UpArrowDownArrow without a semicolon`() = assertTokenization(
        input = "&UpArrowDownArrow",
        expectedTokens = listOf(
            *chars("&UpArrowDownArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity UpArrowDownArrow with a semicolon`() = assertTokenization(
        input = "&UpArrowDownArrow;",
        expectedTokens = listOf(
            char('⇅')
        )
    )

    @Test
    fun `Bad named entity UpDownArrow without a semicolon`() = assertTokenization(
        input = "&UpDownArrow",
        expectedTokens = listOf(
            *chars("&UpDownArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity UpDownArrow with a semicolon`() = assertTokenization(
        input = "&UpDownArrow;",
        expectedTokens = listOf(
            char('↕')
        )
    )

    @Test
    fun `Bad named entity UpEquilibrium without a semicolon`() = assertTokenization(
        input = "&UpEquilibrium",
        expectedTokens = listOf(
            *chars("&UpEquilibrium").toTypedArray()
        )
    )

    @Test
    fun `Named entity UpEquilibrium with a semicolon`() = assertTokenization(
        input = "&UpEquilibrium;",
        expectedTokens = listOf(
            char('⥮')
        )
    )

    @Test
    fun `Bad named entity UpTee without a semicolon`() = assertTokenization(
        input = "&UpTee",
        expectedTokens = listOf(
            *chars("&UpTee").toTypedArray()
        )
    )

    @Test
    fun `Named entity UpTee with a semicolon`() = assertTokenization(
        input = "&UpTee;",
        expectedTokens = listOf(
            char('⊥')
        )
    )

    @Test
    fun `Bad named entity UpTeeArrow without a semicolon`() = assertTokenization(
        input = "&UpTeeArrow",
        expectedTokens = listOf(
            *chars("&UpTeeArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity UpTeeArrow with a semicolon`() = assertTokenization(
        input = "&UpTeeArrow;",
        expectedTokens = listOf(
            char('↥')
        )
    )

    @Test
    fun `Bad named entity Uparrow without a semicolon`() = assertTokenization(
        input = "&Uparrow",
        expectedTokens = listOf(
            *chars("&Uparrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity Uparrow with a semicolon`() = assertTokenization(
        input = "&Uparrow;",
        expectedTokens = listOf(
            char('⇑')
        )
    )

    @Test
    fun `Bad named entity Updownarrow without a semicolon`() = assertTokenization(
        input = "&Updownarrow",
        expectedTokens = listOf(
            *chars("&Updownarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity Updownarrow with a semicolon`() = assertTokenization(
        input = "&Updownarrow;",
        expectedTokens = listOf(
            char('⇕')
        )
    )

    @Test
    fun `Bad named entity UpperLeftArrow without a semicolon`() = assertTokenization(
        input = "&UpperLeftArrow",
        expectedTokens = listOf(
            *chars("&UpperLeftArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity UpperLeftArrow with a semicolon`() = assertTokenization(
        input = "&UpperLeftArrow;",
        expectedTokens = listOf(
            char('↖')
        )
    )

    @Test
    fun `Bad named entity UpperRightArrow without a semicolon`() = assertTokenization(
        input = "&UpperRightArrow",
        expectedTokens = listOf(
            *chars("&UpperRightArrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity UpperRightArrow with a semicolon`() = assertTokenization(
        input = "&UpperRightArrow;",
        expectedTokens = listOf(
            char('↗')
        )
    )

    @Test
    fun `Bad named entity Upsi without a semicolon`() = assertTokenization(
        input = "&Upsi",
        expectedTokens = listOf(
            *chars("&Upsi").toTypedArray()
        )
    )

    @Test
    fun `Named entity Upsi with a semicolon`() = assertTokenization(
        input = "&Upsi;",
        expectedTokens = listOf(
            char('ϒ')
        )
    )

    @Test
    fun `Bad named entity Upsilon without a semicolon`() = assertTokenization(
        input = "&Upsilon",
        expectedTokens = listOf(
            *chars("&Upsilon").toTypedArray()
        )
    )

    @Test
    fun `Named entity Upsilon with a semicolon`() = assertTokenization(
        input = "&Upsilon;",
        expectedTokens = listOf(
            char('Υ')
        )
    )

    @Test
    fun `Bad named entity Uring without a semicolon`() = assertTokenization(
        input = "&Uring",
        expectedTokens = listOf(
            *chars("&Uring").toTypedArray()
        )
    )

    @Test
    fun `Named entity Uring with a semicolon`() = assertTokenization(
        input = "&Uring;",
        expectedTokens = listOf(
            char('Ů')
        )
    )

    @Test
    fun `Bad named entity Uscr without a semicolon`() = assertTokenization(
        input = "&Uscr",
        expectedTokens = listOf(
            *chars("&Uscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Uscr with a semicolon`() = assertTokenization(
        input = "&Uscr;",
        expectedTokens = listOf(
            *chars("𝒰").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Utilde without a semicolon`() = assertTokenization(
        input = "&Utilde",
        expectedTokens = listOf(
            *chars("&Utilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity Utilde with a semicolon`() = assertTokenization(
        input = "&Utilde;",
        expectedTokens = listOf(
            char('Ũ')
        )
    )

    @Test
    fun `Named entity Uuml without a semicolon`() = assertTokenization(
        input = "&Uuml",
        expectedTokens = listOf(
            char('Ü')
        )
    )

    @Test
    fun `Named entity Uuml with a semicolon`() = assertTokenization(
        input = "&Uuml;",
        expectedTokens = listOf(
            char('Ü')
        )
    )

    @Test
    fun `Bad named entity VDash without a semicolon`() = assertTokenization(
        input = "&VDash",
        expectedTokens = listOf(
            *chars("&VDash").toTypedArray()
        )
    )

    @Test
    fun `Named entity VDash with a semicolon`() = assertTokenization(
        input = "&VDash;",
        expectedTokens = listOf(
            char('⊫')
        )
    )

    @Test
    fun `Bad named entity Vbar without a semicolon`() = assertTokenization(
        input = "&Vbar",
        expectedTokens = listOf(
            *chars("&Vbar").toTypedArray()
        )
    )

    @Test
    fun `Named entity Vbar with a semicolon`() = assertTokenization(
        input = "&Vbar;",
        expectedTokens = listOf(
            char('⫫')
        )
    )

    @Test
    fun `Bad named entity Vcy without a semicolon`() = assertTokenization(
        input = "&Vcy",
        expectedTokens = listOf(
            *chars("&Vcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Vcy with a semicolon`() = assertTokenization(
        input = "&Vcy;",
        expectedTokens = listOf(
            char('В')
        )
    )

    @Test
    fun `Bad named entity Vdash without a semicolon`() = assertTokenization(
        input = "&Vdash",
        expectedTokens = listOf(
            *chars("&Vdash").toTypedArray()
        )
    )

    @Test
    fun `Named entity Vdash with a semicolon`() = assertTokenization(
        input = "&Vdash;",
        expectedTokens = listOf(
            char('⊩')
        )
    )

    @Test
    fun `Bad named entity Vdashl without a semicolon`() = assertTokenization(
        input = "&Vdashl",
        expectedTokens = listOf(
            *chars("&Vdashl").toTypedArray()
        )
    )

    @Test
    fun `Named entity Vdashl with a semicolon`() = assertTokenization(
        input = "&Vdashl;",
        expectedTokens = listOf(
            char('⫦')
        )
    )

    @Test
    fun `Bad named entity Vee without a semicolon`() = assertTokenization(
        input = "&Vee",
        expectedTokens = listOf(
            *chars("&Vee").toTypedArray()
        )
    )

    @Test
    fun `Named entity Vee with a semicolon`() = assertTokenization(
        input = "&Vee;",
        expectedTokens = listOf(
            char('⋁')
        )
    )

    @Test
    fun `Bad named entity Verbar without a semicolon`() = assertTokenization(
        input = "&Verbar",
        expectedTokens = listOf(
            *chars("&Verbar").toTypedArray()
        )
    )

    @Test
    fun `Named entity Verbar with a semicolon`() = assertTokenization(
        input = "&Verbar;",
        expectedTokens = listOf(
            char('‖')
        )
    )

    @Test
    fun `Bad named entity Vert without a semicolon`() = assertTokenization(
        input = "&Vert",
        expectedTokens = listOf(
            *chars("&Vert").toTypedArray()
        )
    )

    @Test
    fun `Named entity Vert with a semicolon`() = assertTokenization(
        input = "&Vert;",
        expectedTokens = listOf(
            char('‖')
        )
    )

    @Test
    fun `Bad named entity VerticalBar without a semicolon`() = assertTokenization(
        input = "&VerticalBar",
        expectedTokens = listOf(
            *chars("&VerticalBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity VerticalBar with a semicolon`() = assertTokenization(
        input = "&VerticalBar;",
        expectedTokens = listOf(
            char('∣')
        )
    )

    @Test
    fun `Bad named entity VerticalLine without a semicolon`() = assertTokenization(
        input = "&VerticalLine",
        expectedTokens = listOf(
            *chars("&VerticalLine").toTypedArray()
        )
    )

    @Test
    fun `Named entity VerticalLine with a semicolon`() = assertTokenization(
        input = "&VerticalLine;",
        expectedTokens = listOf(
            char('|')
        )
    )

    @Test
    fun `Bad named entity VerticalSeparator without a semicolon`() = assertTokenization(
        input = "&VerticalSeparator",
        expectedTokens = listOf(
            *chars("&VerticalSeparator").toTypedArray()
        )
    )

    @Test
    fun `Named entity VerticalSeparator with a semicolon`() = assertTokenization(
        input = "&VerticalSeparator;",
        expectedTokens = listOf(
            char('❘')
        )
    )

    @Test
    fun `Bad named entity VerticalTilde without a semicolon`() = assertTokenization(
        input = "&VerticalTilde",
        expectedTokens = listOf(
            *chars("&VerticalTilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity VerticalTilde with a semicolon`() = assertTokenization(
        input = "&VerticalTilde;",
        expectedTokens = listOf(
            char('≀')
        )
    )

    @Test
    fun `Bad named entity VeryThinSpace without a semicolon`() = assertTokenization(
        input = "&VeryThinSpace",
        expectedTokens = listOf(
            *chars("&VeryThinSpace").toTypedArray()
        )
    )

    @Test
    fun `Named entity VeryThinSpace with a semicolon`() = assertTokenization(
        input = "&VeryThinSpace;",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Bad named entity Vfr without a semicolon`() = assertTokenization(
        input = "&Vfr",
        expectedTokens = listOf(
            *chars("&Vfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Vfr with a semicolon`() = assertTokenization(
        input = "&Vfr;",
        expectedTokens = listOf(
            *chars("𝔙").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Vopf without a semicolon`() = assertTokenization(
        input = "&Vopf",
        expectedTokens = listOf(
            *chars("&Vopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Vopf with a semicolon`() = assertTokenization(
        input = "&Vopf;",
        expectedTokens = listOf(
            *chars("𝕍").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Vscr without a semicolon`() = assertTokenization(
        input = "&Vscr",
        expectedTokens = listOf(
            *chars("&Vscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Vscr with a semicolon`() = assertTokenization(
        input = "&Vscr;",
        expectedTokens = listOf(
            *chars("𝒱").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Vvdash without a semicolon`() = assertTokenization(
        input = "&Vvdash",
        expectedTokens = listOf(
            *chars("&Vvdash").toTypedArray()
        )
    )

    @Test
    fun `Named entity Vvdash with a semicolon`() = assertTokenization(
        input = "&Vvdash;",
        expectedTokens = listOf(
            char('⊪')
        )
    )

    @Test
    fun `Bad named entity Wcirc without a semicolon`() = assertTokenization(
        input = "&Wcirc",
        expectedTokens = listOf(
            *chars("&Wcirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity Wcirc with a semicolon`() = assertTokenization(
        input = "&Wcirc;",
        expectedTokens = listOf(
            char('Ŵ')
        )
    )

    @Test
    fun `Bad named entity Wedge without a semicolon`() = assertTokenization(
        input = "&Wedge",
        expectedTokens = listOf(
            *chars("&Wedge").toTypedArray()
        )
    )

    @Test
    fun `Named entity Wedge with a semicolon`() = assertTokenization(
        input = "&Wedge;",
        expectedTokens = listOf(
            char('⋀')
        )
    )

    @Test
    fun `Bad named entity Wfr without a semicolon`() = assertTokenization(
        input = "&Wfr",
        expectedTokens = listOf(
            *chars("&Wfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Wfr with a semicolon`() = assertTokenization(
        input = "&Wfr;",
        expectedTokens = listOf(
            *chars("𝔚").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Wopf without a semicolon`() = assertTokenization(
        input = "&Wopf",
        expectedTokens = listOf(
            *chars("&Wopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Wopf with a semicolon`() = assertTokenization(
        input = "&Wopf;",
        expectedTokens = listOf(
            *chars("𝕎").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Wscr without a semicolon`() = assertTokenization(
        input = "&Wscr",
        expectedTokens = listOf(
            *chars("&Wscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Wscr with a semicolon`() = assertTokenization(
        input = "&Wscr;",
        expectedTokens = listOf(
            *chars("𝒲").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Xfr without a semicolon`() = assertTokenization(
        input = "&Xfr",
        expectedTokens = listOf(
            *chars("&Xfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Xfr with a semicolon`() = assertTokenization(
        input = "&Xfr;",
        expectedTokens = listOf(
            *chars("𝔛").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Xi without a semicolon`() = assertTokenization(
        input = "&Xi",
        expectedTokens = listOf(
            *chars("&Xi").toTypedArray()
        )
    )

    @Test
    fun `Named entity Xi with a semicolon`() = assertTokenization(
        input = "&Xi;",
        expectedTokens = listOf(
            char('Ξ')
        )
    )

    @Test
    fun `Bad named entity Xopf without a semicolon`() = assertTokenization(
        input = "&Xopf",
        expectedTokens = listOf(
            *chars("&Xopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Xopf with a semicolon`() = assertTokenization(
        input = "&Xopf;",
        expectedTokens = listOf(
            *chars("𝕏").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Xscr without a semicolon`() = assertTokenization(
        input = "&Xscr",
        expectedTokens = listOf(
            *chars("&Xscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Xscr with a semicolon`() = assertTokenization(
        input = "&Xscr;",
        expectedTokens = listOf(
            *chars("𝒳").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity YAcy without a semicolon`() = assertTokenization(
        input = "&YAcy",
        expectedTokens = listOf(
            *chars("&YAcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity YAcy with a semicolon`() = assertTokenization(
        input = "&YAcy;",
        expectedTokens = listOf(
            char('Я')
        )
    )

    @Test
    fun `Bad named entity YIcy without a semicolon`() = assertTokenization(
        input = "&YIcy",
        expectedTokens = listOf(
            *chars("&YIcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity YIcy with a semicolon`() = assertTokenization(
        input = "&YIcy;",
        expectedTokens = listOf(
            char('Ї')
        )
    )

    @Test
    fun `Bad named entity YUcy without a semicolon`() = assertTokenization(
        input = "&YUcy",
        expectedTokens = listOf(
            *chars("&YUcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity YUcy with a semicolon`() = assertTokenization(
        input = "&YUcy;",
        expectedTokens = listOf(
            char('Ю')
        )
    )

    @Test
    fun `Named entity Yacute without a semicolon`() = assertTokenization(
        input = "&Yacute",
        expectedTokens = listOf(
            char('Ý')
        )
    )

    @Test
    fun `Named entity Yacute with a semicolon`() = assertTokenization(
        input = "&Yacute;",
        expectedTokens = listOf(
            char('Ý')
        )
    )

    @Test
    fun `Bad named entity Ycirc without a semicolon`() = assertTokenization(
        input = "&Ycirc",
        expectedTokens = listOf(
            *chars("&Ycirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ycirc with a semicolon`() = assertTokenization(
        input = "&Ycirc;",
        expectedTokens = listOf(
            char('Ŷ')
        )
    )

    @Test
    fun `Bad named entity Ycy without a semicolon`() = assertTokenization(
        input = "&Ycy",
        expectedTokens = listOf(
            *chars("&Ycy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Ycy with a semicolon`() = assertTokenization(
        input = "&Ycy;",
        expectedTokens = listOf(
            char('Ы')
        )
    )

    @Test
    fun `Bad named entity Yfr without a semicolon`() = assertTokenization(
        input = "&Yfr",
        expectedTokens = listOf(
            *chars("&Yfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Yfr with a semicolon`() = assertTokenization(
        input = "&Yfr;",
        expectedTokens = listOf(
            *chars("𝔜").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Yopf without a semicolon`() = assertTokenization(
        input = "&Yopf",
        expectedTokens = listOf(
            *chars("&Yopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Yopf with a semicolon`() = assertTokenization(
        input = "&Yopf;",
        expectedTokens = listOf(
            *chars("𝕐").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Yscr without a semicolon`() = assertTokenization(
        input = "&Yscr",
        expectedTokens = listOf(
            *chars("&Yscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Yscr with a semicolon`() = assertTokenization(
        input = "&Yscr;",
        expectedTokens = listOf(
            *chars("𝒴").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity Yuml without a semicolon`() = assertTokenization(
        input = "&Yuml",
        expectedTokens = listOf(
            *chars("&Yuml").toTypedArray()
        )
    )

    @Test
    fun `Named entity Yuml with a semicolon`() = assertTokenization(
        input = "&Yuml;",
        expectedTokens = listOf(
            char('Ÿ')
        )
    )

    @Test
    fun `Bad named entity ZHcy without a semicolon`() = assertTokenization(
        input = "&ZHcy",
        expectedTokens = listOf(
            *chars("&ZHcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity ZHcy with a semicolon`() = assertTokenization(
        input = "&ZHcy;",
        expectedTokens = listOf(
            char('Ж')
        )
    )

    @Test
    fun `Bad named entity Zacute without a semicolon`() = assertTokenization(
        input = "&Zacute",
        expectedTokens = listOf(
            *chars("&Zacute").toTypedArray()
        )
    )

    @Test
    fun `Named entity Zacute with a semicolon`() = assertTokenization(
        input = "&Zacute;",
        expectedTokens = listOf(
            char('Ź')
        )
    )

    @Test
    fun `Bad named entity Zcaron without a semicolon`() = assertTokenization(
        input = "&Zcaron",
        expectedTokens = listOf(
            *chars("&Zcaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity Zcaron with a semicolon`() = assertTokenization(
        input = "&Zcaron;",
        expectedTokens = listOf(
            char('Ž')
        )
    )

    @Test
    fun `Bad named entity Zcy without a semicolon`() = assertTokenization(
        input = "&Zcy",
        expectedTokens = listOf(
            *chars("&Zcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity Zcy with a semicolon`() = assertTokenization(
        input = "&Zcy;",
        expectedTokens = listOf(
            char('З')
        )
    )

    @Test
    fun `Bad named entity Zdot without a semicolon`() = assertTokenization(
        input = "&Zdot",
        expectedTokens = listOf(
            *chars("&Zdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity Zdot with a semicolon`() = assertTokenization(
        input = "&Zdot;",
        expectedTokens = listOf(
            char('Ż')
        )
    )

    @Test
    fun `Bad named entity ZeroWidthSpace without a semicolon`() = assertTokenization(
        input = "&ZeroWidthSpace",
        expectedTokens = listOf(
            *chars("&ZeroWidthSpace").toTypedArray()
        )
    )

    @Test
    fun `Named entity ZeroWidthSpace with a semicolon`() = assertTokenization(
        input = "&ZeroWidthSpace;",
        expectedTokens = listOf(
            char('​')
        )
    )

    @Test
    fun `Bad named entity Zeta without a semicolon`() = assertTokenization(
        input = "&Zeta",
        expectedTokens = listOf(
            *chars("&Zeta").toTypedArray()
        )
    )

    @Test
    fun `Named entity Zeta with a semicolon`() = assertTokenization(
        input = "&Zeta;",
        expectedTokens = listOf(
            char('Ζ')
        )
    )

    @Test
    fun `Bad named entity Zfr without a semicolon`() = assertTokenization(
        input = "&Zfr",
        expectedTokens = listOf(
            *chars("&Zfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Zfr with a semicolon`() = assertTokenization(
        input = "&Zfr;",
        expectedTokens = listOf(
            char('ℨ')
        )
    )

    @Test
    fun `Bad named entity Zopf without a semicolon`() = assertTokenization(
        input = "&Zopf",
        expectedTokens = listOf(
            *chars("&Zopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity Zopf with a semicolon`() = assertTokenization(
        input = "&Zopf;",
        expectedTokens = listOf(
            char('ℤ')
        )
    )

    @Test
    fun `Bad named entity Zscr without a semicolon`() = assertTokenization(
        input = "&Zscr",
        expectedTokens = listOf(
            *chars("&Zscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity Zscr with a semicolon`() = assertTokenization(
        input = "&Zscr;",
        expectedTokens = listOf(
            *chars("𝒵").toTypedArray()
        )
    )

    @Test
    fun `Named entity aacute without a semicolon`() = assertTokenization(
        input = "&aacute",
        expectedTokens = listOf(
            char('á')
        )
    )

    @Test
    fun `Named entity aacute with a semicolon`() = assertTokenization(
        input = "&aacute;",
        expectedTokens = listOf(
            char('á')
        )
    )

    @Test
    fun `Bad named entity abreve without a semicolon`() = assertTokenization(
        input = "&abreve",
        expectedTokens = listOf(
            *chars("&abreve").toTypedArray()
        )
    )

    @Test
    fun `Named entity abreve with a semicolon`() = assertTokenization(
        input = "&abreve;",
        expectedTokens = listOf(
            char('ă')
        )
    )

    @Test
    fun `Bad named entity ac without a semicolon`() = assertTokenization(
        input = "&ac",
        expectedTokens = listOf(
            *chars("&ac").toTypedArray()
        )
    )

    @Test
    fun `Named entity ac with a semicolon`() = assertTokenization(
        input = "&ac;",
        expectedTokens = listOf(
            char('∾')
        )
    )

    @Test
    fun `Bad named entity acE without a semicolon`() = assertTokenization(
        input = "&acE",
        expectedTokens = listOf(
            *chars("&acE").toTypedArray()
        )
    )

    @Test
    fun `Named entity acE with a semicolon`() = assertTokenization(
        input = "&acE;",
        expectedTokens = listOf(
            *chars("∾̳").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity acd without a semicolon`() = assertTokenization(
        input = "&acd",
        expectedTokens = listOf(
            *chars("&acd").toTypedArray()
        )
    )

    @Test
    fun `Named entity acd with a semicolon`() = assertTokenization(
        input = "&acd;",
        expectedTokens = listOf(
            char('∿')
        )
    )

    @Test
    fun `Named entity acirc without a semicolon`() = assertTokenization(
        input = "&acirc",
        expectedTokens = listOf(
            char('â')
        )
    )

    @Test
    fun `Named entity acirc with a semicolon`() = assertTokenization(
        input = "&acirc;",
        expectedTokens = listOf(
            char('â')
        )
    )

    @Test
    fun `Named entity acute without a semicolon`() = assertTokenization(
        input = "&acute",
        expectedTokens = listOf(
            char('´')
        )
    )

    @Test
    fun `Named entity acute with a semicolon`() = assertTokenization(
        input = "&acute;",
        expectedTokens = listOf(
            char('´')
        )
    )

    @Test
    fun `Bad named entity acy without a semicolon`() = assertTokenization(
        input = "&acy",
        expectedTokens = listOf(
            *chars("&acy").toTypedArray()
        )
    )

    @Test
    fun `Named entity acy with a semicolon`() = assertTokenization(
        input = "&acy;",
        expectedTokens = listOf(
            char('а')
        )
    )

    @Test
    fun `Named entity aelig without a semicolon`() = assertTokenization(
        input = "&aelig",
        expectedTokens = listOf(
            char('æ')
        )
    )

    @Test
    fun `Named entity aelig with a semicolon`() = assertTokenization(
        input = "&aelig;",
        expectedTokens = listOf(
            char('æ')
        )
    )

    @Test
    fun `Bad named entity af without a semicolon`() = assertTokenization(
        input = "&af",
        expectedTokens = listOf(
            *chars("&af").toTypedArray()
        )
    )

    @Test
    fun `Named entity af with a semicolon`() = assertTokenization(
        input = "&af;",
        expectedTokens = listOf(
            char('⁡')
        )
    )

    @Test
    fun `Bad named entity afr without a semicolon`() = assertTokenization(
        input = "&afr",
        expectedTokens = listOf(
            *chars("&afr").toTypedArray()
        )
    )

    @Test
    fun `Named entity afr with a semicolon`() = assertTokenization(
        input = "&afr;",
        expectedTokens = listOf(
            *chars("𝔞").toTypedArray()
        )
    )

    @Test
    fun `Named entity agrave without a semicolon`() = assertTokenization(
        input = "&agrave",
        expectedTokens = listOf(
            char('à')
        )
    )

    @Test
    fun `Named entity agrave with a semicolon`() = assertTokenization(
        input = "&agrave;",
        expectedTokens = listOf(
            char('à')
        )
    )

    @Test
    fun `Bad named entity alefsym without a semicolon`() = assertTokenization(
        input = "&alefsym",
        expectedTokens = listOf(
            *chars("&alefsym").toTypedArray()
        )
    )

    @Test
    fun `Named entity alefsym with a semicolon`() = assertTokenization(
        input = "&alefsym;",
        expectedTokens = listOf(
            char('ℵ')
        )
    )

    @Test
    fun `Bad named entity aleph without a semicolon`() = assertTokenization(
        input = "&aleph",
        expectedTokens = listOf(
            *chars("&aleph").toTypedArray()
        )
    )

    @Test
    fun `Named entity aleph with a semicolon`() = assertTokenization(
        input = "&aleph;",
        expectedTokens = listOf(
            char('ℵ')
        )
    )

    @Test
    fun `Bad named entity alpha without a semicolon`() = assertTokenization(
        input = "&alpha",
        expectedTokens = listOf(
            *chars("&alpha").toTypedArray()
        )
    )

    @Test
    fun `Named entity alpha with a semicolon`() = assertTokenization(
        input = "&alpha;",
        expectedTokens = listOf(
            char('α')
        )
    )

    @Test
    fun `Bad named entity amacr without a semicolon`() = assertTokenization(
        input = "&amacr",
        expectedTokens = listOf(
            *chars("&amacr").toTypedArray()
        )
    )

    @Test
    fun `Named entity amacr with a semicolon`() = assertTokenization(
        input = "&amacr;",
        expectedTokens = listOf(
            char('ā')
        )
    )

    @Test
    fun `Bad named entity amalg without a semicolon`() = assertTokenization(
        input = "&amalg",
        expectedTokens = listOf(
            *chars("&amalg").toTypedArray()
        )
    )

    @Test
    fun `Named entity amalg with a semicolon`() = assertTokenization(
        input = "&amalg;",
        expectedTokens = listOf(
            char('⨿')
        )
    )

    @Test
    fun `Named entity amp without a semicolon`() = assertTokenization(
        input = "&amp",
        expectedTokens = listOf(
            char('&')
        )
    )

    @Test
    fun `Named entity amp with a semicolon`() = assertTokenization(
        input = "&amp;",
        expectedTokens = listOf(
            char('&')
        )
    )

    @Test
    fun `Bad named entity and without a semicolon`() = assertTokenization(
        input = "&and",
        expectedTokens = listOf(
            *chars("&and").toTypedArray()
        )
    )

    @Test
    fun `Named entity and with a semicolon`() = assertTokenization(
        input = "&and;",
        expectedTokens = listOf(
            char('∧')
        )
    )

    @Test
    fun `Bad named entity andand without a semicolon`() = assertTokenization(
        input = "&andand",
        expectedTokens = listOf(
            *chars("&andand").toTypedArray()
        )
    )

    @Test
    fun `Named entity andand with a semicolon`() = assertTokenization(
        input = "&andand;",
        expectedTokens = listOf(
            char('⩕')
        )
    )

    @Test
    fun `Bad named entity andd without a semicolon`() = assertTokenization(
        input = "&andd",
        expectedTokens = listOf(
            *chars("&andd").toTypedArray()
        )
    )

    @Test
    fun `Named entity andd with a semicolon`() = assertTokenization(
        input = "&andd;",
        expectedTokens = listOf(
            char('⩜')
        )
    )

    @Test
    fun `Bad named entity andslope without a semicolon`() = assertTokenization(
        input = "&andslope",
        expectedTokens = listOf(
            *chars("&andslope").toTypedArray()
        )
    )

    @Test
    fun `Named entity andslope with a semicolon`() = assertTokenization(
        input = "&andslope;",
        expectedTokens = listOf(
            char('⩘')
        )
    )

    @Test
    fun `Bad named entity andv without a semicolon`() = assertTokenization(
        input = "&andv",
        expectedTokens = listOf(
            *chars("&andv").toTypedArray()
        )
    )

    @Test
    fun `Named entity andv with a semicolon`() = assertTokenization(
        input = "&andv;",
        expectedTokens = listOf(
            char('⩚')
        )
    )

    @Test
    fun `Bad named entity ang without a semicolon`() = assertTokenization(
        input = "&ang",
        expectedTokens = listOf(
            *chars("&ang").toTypedArray()
        )
    )

    @Test
    fun `Named entity ang with a semicolon`() = assertTokenization(
        input = "&ang;",
        expectedTokens = listOf(
            char('∠')
        )
    )

    @Test
    fun `Bad named entity ange without a semicolon`() = assertTokenization(
        input = "&ange",
        expectedTokens = listOf(
            *chars("&ange").toTypedArray()
        )
    )

    @Test
    fun `Named entity ange with a semicolon`() = assertTokenization(
        input = "&ange;",
        expectedTokens = listOf(
            char('⦤')
        )
    )

    @Test
    fun `Bad named entity angle without a semicolon`() = assertTokenization(
        input = "&angle",
        expectedTokens = listOf(
            *chars("&angle").toTypedArray()
        )
    )

    @Test
    fun `Named entity angle with a semicolon`() = assertTokenization(
        input = "&angle;",
        expectedTokens = listOf(
            char('∠')
        )
    )

    @Test
    fun `Bad named entity angmsd without a semicolon`() = assertTokenization(
        input = "&angmsd",
        expectedTokens = listOf(
            *chars("&angmsd").toTypedArray()
        )
    )

    @Test
    fun `Named entity angmsd with a semicolon`() = assertTokenization(
        input = "&angmsd;",
        expectedTokens = listOf(
            char('∡')
        )
    )

    @Test
    fun `Bad named entity angmsdaa without a semicolon`() = assertTokenization(
        input = "&angmsdaa",
        expectedTokens = listOf(
            *chars("&angmsdaa").toTypedArray()
        )
    )

    @Test
    fun `Named entity angmsdaa with a semicolon`() = assertTokenization(
        input = "&angmsdaa;",
        expectedTokens = listOf(
            char('⦨')
        )
    )

    @Test
    fun `Bad named entity angmsdab without a semicolon`() = assertTokenization(
        input = "&angmsdab",
        expectedTokens = listOf(
            *chars("&angmsdab").toTypedArray()
        )
    )

    @Test
    fun `Named entity angmsdab with a semicolon`() = assertTokenization(
        input = "&angmsdab;",
        expectedTokens = listOf(
            char('⦩')
        )
    )

    @Test
    fun `Bad named entity angmsdac without a semicolon`() = assertTokenization(
        input = "&angmsdac",
        expectedTokens = listOf(
            *chars("&angmsdac").toTypedArray()
        )
    )

    @Test
    fun `Named entity angmsdac with a semicolon`() = assertTokenization(
        input = "&angmsdac;",
        expectedTokens = listOf(
            char('⦪')
        )
    )

    @Test
    fun `Bad named entity angmsdad without a semicolon`() = assertTokenization(
        input = "&angmsdad",
        expectedTokens = listOf(
            *chars("&angmsdad").toTypedArray()
        )
    )

    @Test
    fun `Named entity angmsdad with a semicolon`() = assertTokenization(
        input = "&angmsdad;",
        expectedTokens = listOf(
            char('⦫')
        )
    )

    @Test
    fun `Bad named entity angmsdae without a semicolon`() = assertTokenization(
        input = "&angmsdae",
        expectedTokens = listOf(
            *chars("&angmsdae").toTypedArray()
        )
    )

    @Test
    fun `Named entity angmsdae with a semicolon`() = assertTokenization(
        input = "&angmsdae;",
        expectedTokens = listOf(
            char('⦬')
        )
    )

    @Test
    fun `Bad named entity angmsdaf without a semicolon`() = assertTokenization(
        input = "&angmsdaf",
        expectedTokens = listOf(
            *chars("&angmsdaf").toTypedArray()
        )
    )

    @Test
    fun `Named entity angmsdaf with a semicolon`() = assertTokenization(
        input = "&angmsdaf;",
        expectedTokens = listOf(
            char('⦭')
        )
    )

    @Test
    fun `Bad named entity angmsdag without a semicolon`() = assertTokenization(
        input = "&angmsdag",
        expectedTokens = listOf(
            *chars("&angmsdag").toTypedArray()
        )
    )

    @Test
    fun `Named entity angmsdag with a semicolon`() = assertTokenization(
        input = "&angmsdag;",
        expectedTokens = listOf(
            char('⦮')
        )
    )

    @Test
    fun `Bad named entity angmsdah without a semicolon`() = assertTokenization(
        input = "&angmsdah",
        expectedTokens = listOf(
            *chars("&angmsdah").toTypedArray()
        )
    )

    @Test
    fun `Named entity angmsdah with a semicolon`() = assertTokenization(
        input = "&angmsdah;",
        expectedTokens = listOf(
            char('⦯')
        )
    )

    @Test
    fun `Bad named entity angrt without a semicolon`() = assertTokenization(
        input = "&angrt",
        expectedTokens = listOf(
            *chars("&angrt").toTypedArray()
        )
    )

    @Test
    fun `Named entity angrt with a semicolon`() = assertTokenization(
        input = "&angrt;",
        expectedTokens = listOf(
            char('∟')
        )
    )

    @Test
    fun `Bad named entity angrtvb without a semicolon`() = assertTokenization(
        input = "&angrtvb",
        expectedTokens = listOf(
            *chars("&angrtvb").toTypedArray()
        )
    )

    @Test
    fun `Named entity angrtvb with a semicolon`() = assertTokenization(
        input = "&angrtvb;",
        expectedTokens = listOf(
            char('⊾')
        )
    )

    @Test
    fun `Bad named entity angrtvbd without a semicolon`() = assertTokenization(
        input = "&angrtvbd",
        expectedTokens = listOf(
            *chars("&angrtvbd").toTypedArray()
        )
    )

    @Test
    fun `Named entity angrtvbd with a semicolon`() = assertTokenization(
        input = "&angrtvbd;",
        expectedTokens = listOf(
            char('⦝')
        )
    )

    @Test
    fun `Bad named entity angsph without a semicolon`() = assertTokenization(
        input = "&angsph",
        expectedTokens = listOf(
            *chars("&angsph").toTypedArray()
        )
    )

    @Test
    fun `Named entity angsph with a semicolon`() = assertTokenization(
        input = "&angsph;",
        expectedTokens = listOf(
            char('∢')
        )
    )

    @Test
    fun `Bad named entity angst without a semicolon`() = assertTokenization(
        input = "&angst",
        expectedTokens = listOf(
            *chars("&angst").toTypedArray()
        )
    )

    @Test
    fun `Named entity angst with a semicolon`() = assertTokenization(
        input = "&angst;",
        expectedTokens = listOf(
            char('Å')
        )
    )

    @Test
    fun `Bad named entity angzarr without a semicolon`() = assertTokenization(
        input = "&angzarr",
        expectedTokens = listOf(
            *chars("&angzarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity angzarr with a semicolon`() = assertTokenization(
        input = "&angzarr;",
        expectedTokens = listOf(
            char('⍼')
        )
    )

    @Test
    fun `Bad named entity aogon without a semicolon`() = assertTokenization(
        input = "&aogon",
        expectedTokens = listOf(
            *chars("&aogon").toTypedArray()
        )
    )

    @Test
    fun `Named entity aogon with a semicolon`() = assertTokenization(
        input = "&aogon;",
        expectedTokens = listOf(
            char('ą')
        )
    )

    @Test
    fun `Bad named entity aopf without a semicolon`() = assertTokenization(
        input = "&aopf",
        expectedTokens = listOf(
            *chars("&aopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity aopf with a semicolon`() = assertTokenization(
        input = "&aopf;",
        expectedTokens = listOf(
            *chars("𝕒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ap without a semicolon`() = assertTokenization(
        input = "&ap",
        expectedTokens = listOf(
            *chars("&ap").toTypedArray()
        )
    )

    @Test
    fun `Named entity ap with a semicolon`() = assertTokenization(
        input = "&ap;",
        expectedTokens = listOf(
            char('≈')
        )
    )

    @Test
    fun `Bad named entity apE without a semicolon`() = assertTokenization(
        input = "&apE",
        expectedTokens = listOf(
            *chars("&apE").toTypedArray()
        )
    )

    @Test
    fun `Named entity apE with a semicolon`() = assertTokenization(
        input = "&apE;",
        expectedTokens = listOf(
            char('⩰')
        )
    )

    @Test
    fun `Bad named entity apacir without a semicolon`() = assertTokenization(
        input = "&apacir",
        expectedTokens = listOf(
            *chars("&apacir").toTypedArray()
        )
    )

    @Test
    fun `Named entity apacir with a semicolon`() = assertTokenization(
        input = "&apacir;",
        expectedTokens = listOf(
            char('⩯')
        )
    )

    @Test
    fun `Bad named entity ape without a semicolon`() = assertTokenization(
        input = "&ape",
        expectedTokens = listOf(
            *chars("&ape").toTypedArray()
        )
    )

    @Test
    fun `Named entity ape with a semicolon`() = assertTokenization(
        input = "&ape;",
        expectedTokens = listOf(
            char('≊')
        )
    )

    @Test
    fun `Bad named entity apid without a semicolon`() = assertTokenization(
        input = "&apid",
        expectedTokens = listOf(
            *chars("&apid").toTypedArray()
        )
    )

    @Test
    fun `Named entity apid with a semicolon`() = assertTokenization(
        input = "&apid;",
        expectedTokens = listOf(
            char('≋')
        )
    )

    @Test
    fun `Bad named entity apos without a semicolon`() = assertTokenization(
        input = "&apos",
        expectedTokens = listOf(
            *chars("&apos").toTypedArray()
        )
    )

    @Test
    fun `Named entity apos with a semicolon`() = assertTokenization(
        input = "&apos;",
        expectedTokens = listOf(char('\''))
    )

    @Test
    fun `Bad named entity approx without a semicolon`() = assertTokenization(
        input = "&approx",
        expectedTokens = listOf(
            *chars("&approx").toTypedArray()
        )
    )

    @Test
    fun `Named entity approx with a semicolon`() = assertTokenization(
        input = "&approx;",
        expectedTokens = listOf(
            char('≈')
        )
    )

    @Test
    fun `Bad named entity approxeq without a semicolon`() = assertTokenization(
        input = "&approxeq",
        expectedTokens = listOf(
            *chars("&approxeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity approxeq with a semicolon`() = assertTokenization(
        input = "&approxeq;",
        expectedTokens = listOf(
            char('≊')
        )
    )

    @Test
    fun `Named entity aring without a semicolon`() = assertTokenization(
        input = "&aring",
        expectedTokens = listOf(
            char('å')
        )
    )

    @Test
    fun `Named entity aring with a semicolon`() = assertTokenization(
        input = "&aring;",
        expectedTokens = listOf(
            char('å')
        )
    )

    @Test
    fun `Bad named entity ascr without a semicolon`() = assertTokenization(
        input = "&ascr",
        expectedTokens = listOf(
            *chars("&ascr").toTypedArray()
        )
    )

    @Test
    fun `Named entity ascr with a semicolon`() = assertTokenization(
        input = "&ascr;",
        expectedTokens = listOf(
            *chars("𝒶").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ast without a semicolon`() = assertTokenization(
        input = "&ast",
        expectedTokens = listOf(
            *chars("&ast").toTypedArray()
        )
    )

    @Test
    fun `Named entity ast with a semicolon`() = assertTokenization(
        input = "&ast;",
        expectedTokens = listOf(
            char('*')
        )
    )

    @Test
    fun `Bad named entity asymp without a semicolon`() = assertTokenization(
        input = "&asymp",
        expectedTokens = listOf(
            *chars("&asymp").toTypedArray()
        )
    )

    @Test
    fun `Named entity asymp with a semicolon`() = assertTokenization(
        input = "&asymp;",
        expectedTokens = listOf(
            char('≈')
        )
    )

    @Test
    fun `Bad named entity asympeq without a semicolon`() = assertTokenization(
        input = "&asympeq",
        expectedTokens = listOf(
            *chars("&asympeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity asympeq with a semicolon`() = assertTokenization(
        input = "&asympeq;",
        expectedTokens = listOf(
            char('≍')
        )
    )

    @Test
    fun `Named entity atilde without a semicolon`() = assertTokenization(
        input = "&atilde",
        expectedTokens = listOf(
            char('ã')
        )
    )

    @Test
    fun `Named entity atilde with a semicolon`() = assertTokenization(
        input = "&atilde;",
        expectedTokens = listOf(
            char('ã')
        )
    )

    @Test
    fun `Named entity auml without a semicolon`() = assertTokenization(
        input = "&auml",
        expectedTokens = listOf(
            char('ä')
        )
    )

    @Test
    fun `Named entity auml with a semicolon`() = assertTokenization(
        input = "&auml;",
        expectedTokens = listOf(
            char('ä')
        )
    )

    @Test
    fun `Bad named entity awconint without a semicolon`() = assertTokenization(
        input = "&awconint",
        expectedTokens = listOf(
            *chars("&awconint").toTypedArray()
        )
    )

    @Test
    fun `Named entity awconint with a semicolon`() = assertTokenization(
        input = "&awconint;",
        expectedTokens = listOf(
            char('∳')
        )
    )

    @Test
    fun `Bad named entity awint without a semicolon`() = assertTokenization(
        input = "&awint",
        expectedTokens = listOf(
            *chars("&awint").toTypedArray()
        )
    )

    @Test
    fun `Named entity awint with a semicolon`() = assertTokenization(
        input = "&awint;",
        expectedTokens = listOf(
            char('⨑')
        )
    )

    @Test
    fun `Bad named entity bNot without a semicolon`() = assertTokenization(
        input = "&bNot",
        expectedTokens = listOf(
            *chars("&bNot").toTypedArray()
        )
    )

    @Test
    fun `Named entity bNot with a semicolon`() = assertTokenization(
        input = "&bNot;",
        expectedTokens = listOf(
            char('⫭')
        )
    )

    @Test
    fun `Bad named entity backcong without a semicolon`() = assertTokenization(
        input = "&backcong",
        expectedTokens = listOf(
            *chars("&backcong").toTypedArray()
        )
    )

    @Test
    fun `Named entity backcong with a semicolon`() = assertTokenization(
        input = "&backcong;",
        expectedTokens = listOf(
            char('≌')
        )
    )

    @Test
    fun `Bad named entity backepsilon without a semicolon`() = assertTokenization(
        input = "&backepsilon",
        expectedTokens = listOf(
            *chars("&backepsilon").toTypedArray()
        )
    )

    @Test
    fun `Named entity backepsilon with a semicolon`() = assertTokenization(
        input = "&backepsilon;",
        expectedTokens = listOf(
            char('϶')
        )
    )

    @Test
    fun `Bad named entity backprime without a semicolon`() = assertTokenization(
        input = "&backprime",
        expectedTokens = listOf(
            *chars("&backprime").toTypedArray()
        )
    )

    @Test
    fun `Named entity backprime with a semicolon`() = assertTokenization(
        input = "&backprime;",
        expectedTokens = listOf(
            char('‵')
        )
    )

    @Test
    fun `Bad named entity backsim without a semicolon`() = assertTokenization(
        input = "&backsim",
        expectedTokens = listOf(
            *chars("&backsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity backsim with a semicolon`() = assertTokenization(
        input = "&backsim;",
        expectedTokens = listOf(
            char('∽')
        )
    )

    @Test
    fun `Bad named entity backsimeq without a semicolon`() = assertTokenization(
        input = "&backsimeq",
        expectedTokens = listOf(
            *chars("&backsimeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity backsimeq with a semicolon`() = assertTokenization(
        input = "&backsimeq;",
        expectedTokens = listOf(
            char('⋍')
        )
    )

    @Test
    fun `Bad named entity barvee without a semicolon`() = assertTokenization(
        input = "&barvee",
        expectedTokens = listOf(
            *chars("&barvee").toTypedArray()
        )
    )

    @Test
    fun `Named entity barvee with a semicolon`() = assertTokenization(
        input = "&barvee;",
        expectedTokens = listOf(
            char('⊽')
        )
    )

    @Test
    fun `Bad named entity barwed without a semicolon`() = assertTokenization(
        input = "&barwed",
        expectedTokens = listOf(
            *chars("&barwed").toTypedArray()
        )
    )

    @Test
    fun `Named entity barwed with a semicolon`() = assertTokenization(
        input = "&barwed;",
        expectedTokens = listOf(
            char('⌅')
        )
    )

    @Test
    fun `Bad named entity barwedge without a semicolon`() = assertTokenization(
        input = "&barwedge",
        expectedTokens = listOf(
            *chars("&barwedge").toTypedArray()
        )
    )

    @Test
    fun `Named entity barwedge with a semicolon`() = assertTokenization(
        input = "&barwedge;",
        expectedTokens = listOf(
            char('⌅')
        )
    )

    @Test
    fun `Bad named entity bbrk without a semicolon`() = assertTokenization(
        input = "&bbrk",
        expectedTokens = listOf(
            *chars("&bbrk").toTypedArray()
        )
    )

    @Test
    fun `Named entity bbrk with a semicolon`() = assertTokenization(
        input = "&bbrk;",
        expectedTokens = listOf(
            char('⎵')
        )
    )

    @Test
    fun `Bad named entity bbrktbrk without a semicolon`() = assertTokenization(
        input = "&bbrktbrk",
        expectedTokens = listOf(
            *chars("&bbrktbrk").toTypedArray()
        )
    )

    @Test
    fun `Named entity bbrktbrk with a semicolon`() = assertTokenization(
        input = "&bbrktbrk;",
        expectedTokens = listOf(
            char('⎶')
        )
    )

    @Test
    fun `Bad named entity bcong without a semicolon`() = assertTokenization(
        input = "&bcong",
        expectedTokens = listOf(
            *chars("&bcong").toTypedArray()
        )
    )

    @Test
    fun `Named entity bcong with a semicolon`() = assertTokenization(
        input = "&bcong;",
        expectedTokens = listOf(
            char('≌')
        )
    )

    @Test
    fun `Bad named entity bcy without a semicolon`() = assertTokenization(
        input = "&bcy",
        expectedTokens = listOf(
            *chars("&bcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity bcy with a semicolon`() = assertTokenization(
        input = "&bcy;",
        expectedTokens = listOf(
            char('б')
        )
    )

    @Test
    fun `Bad named entity bdquo without a semicolon`() = assertTokenization(
        input = "&bdquo",
        expectedTokens = listOf(
            *chars("&bdquo").toTypedArray()
        )
    )

    @Test
    fun `Named entity bdquo with a semicolon`() = assertTokenization(
        input = "&bdquo;",
        expectedTokens = listOf(
            char('„')
        )
    )

    @Test
    fun `Bad named entity becaus without a semicolon`() = assertTokenization(
        input = "&becaus",
        expectedTokens = listOf(
            *chars("&becaus").toTypedArray()
        )
    )

    @Test
    fun `Named entity becaus with a semicolon`() = assertTokenization(
        input = "&becaus;",
        expectedTokens = listOf(
            char('∵')
        )
    )

    @Test
    fun `Bad named entity because without a semicolon`() = assertTokenization(
        input = "&because",
        expectedTokens = listOf(
            *chars("&because").toTypedArray()
        )
    )

    @Test
    fun `Named entity because with a semicolon`() = assertTokenization(
        input = "&because;",
        expectedTokens = listOf(
            char('∵')
        )
    )

    @Test
    fun `Bad named entity bemptyv without a semicolon`() = assertTokenization(
        input = "&bemptyv",
        expectedTokens = listOf(
            *chars("&bemptyv").toTypedArray()
        )
    )

    @Test
    fun `Named entity bemptyv with a semicolon`() = assertTokenization(
        input = "&bemptyv;",
        expectedTokens = listOf(
            char('⦰')
        )
    )

    @Test
    fun `Bad named entity bepsi without a semicolon`() = assertTokenization(
        input = "&bepsi",
        expectedTokens = listOf(
            *chars("&bepsi").toTypedArray()
        )
    )

    @Test
    fun `Named entity bepsi with a semicolon`() = assertTokenization(
        input = "&bepsi;",
        expectedTokens = listOf(
            char('϶')
        )
    )

    @Test
    fun `Bad named entity bernou without a semicolon`() = assertTokenization(
        input = "&bernou",
        expectedTokens = listOf(
            *chars("&bernou").toTypedArray()
        )
    )

    @Test
    fun `Named entity bernou with a semicolon`() = assertTokenization(
        input = "&bernou;",
        expectedTokens = listOf(
            char('ℬ')
        )
    )

    @Test
    fun `Bad named entity beta without a semicolon`() = assertTokenization(
        input = "&beta",
        expectedTokens = listOf(
            *chars("&beta").toTypedArray()
        )
    )

    @Test
    fun `Named entity beta with a semicolon`() = assertTokenization(
        input = "&beta;",
        expectedTokens = listOf(
            char('β')
        )
    )

    @Test
    fun `Bad named entity beth without a semicolon`() = assertTokenization(
        input = "&beth",
        expectedTokens = listOf(
            *chars("&beth").toTypedArray()
        )
    )

    @Test
    fun `Named entity beth with a semicolon`() = assertTokenization(
        input = "&beth;",
        expectedTokens = listOf(
            char('ℶ')
        )
    )

    @Test
    fun `Bad named entity between without a semicolon`() = assertTokenization(
        input = "&between",
        expectedTokens = listOf(
            *chars("&between").toTypedArray()
        )
    )

    @Test
    fun `Named entity between with a semicolon`() = assertTokenization(
        input = "&between;",
        expectedTokens = listOf(
            char('≬')
        )
    )

    @Test
    fun `Bad named entity bfr without a semicolon`() = assertTokenization(
        input = "&bfr",
        expectedTokens = listOf(
            *chars("&bfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity bfr with a semicolon`() = assertTokenization(
        input = "&bfr;",
        expectedTokens = listOf(
            *chars("𝔟").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity bigcap without a semicolon`() = assertTokenization(
        input = "&bigcap",
        expectedTokens = listOf(
            *chars("&bigcap").toTypedArray()
        )
    )

    @Test
    fun `Named entity bigcap with a semicolon`() = assertTokenization(
        input = "&bigcap;",
        expectedTokens = listOf(
            char('⋂')
        )
    )

    @Test
    fun `Bad named entity bigcirc without a semicolon`() = assertTokenization(
        input = "&bigcirc",
        expectedTokens = listOf(
            *chars("&bigcirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity bigcirc with a semicolon`() = assertTokenization(
        input = "&bigcirc;",
        expectedTokens = listOf(
            char('◯')
        )
    )

    @Test
    fun `Bad named entity bigcup without a semicolon`() = assertTokenization(
        input = "&bigcup",
        expectedTokens = listOf(
            *chars("&bigcup").toTypedArray()
        )
    )

    @Test
    fun `Named entity bigcup with a semicolon`() = assertTokenization(
        input = "&bigcup;",
        expectedTokens = listOf(
            char('⋃')
        )
    )

    @Test
    fun `Bad named entity bigodot without a semicolon`() = assertTokenization(
        input = "&bigodot",
        expectedTokens = listOf(
            *chars("&bigodot").toTypedArray()
        )
    )

    @Test
    fun `Named entity bigodot with a semicolon`() = assertTokenization(
        input = "&bigodot;",
        expectedTokens = listOf(
            char('⨀')
        )
    )

    @Test
    fun `Bad named entity bigoplus without a semicolon`() = assertTokenization(
        input = "&bigoplus",
        expectedTokens = listOf(
            *chars("&bigoplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity bigoplus with a semicolon`() = assertTokenization(
        input = "&bigoplus;",
        expectedTokens = listOf(
            char('⨁')
        )
    )

    @Test
    fun `Bad named entity bigotimes without a semicolon`() = assertTokenization(
        input = "&bigotimes",
        expectedTokens = listOf(
            *chars("&bigotimes").toTypedArray()
        )
    )

    @Test
    fun `Named entity bigotimes with a semicolon`() = assertTokenization(
        input = "&bigotimes;",
        expectedTokens = listOf(
            char('⨂')
        )
    )

    @Test
    fun `Bad named entity bigsqcup without a semicolon`() = assertTokenization(
        input = "&bigsqcup",
        expectedTokens = listOf(
            *chars("&bigsqcup").toTypedArray()
        )
    )

    @Test
    fun `Named entity bigsqcup with a semicolon`() = assertTokenization(
        input = "&bigsqcup;",
        expectedTokens = listOf(
            char('⨆')
        )
    )

    @Test
    fun `Bad named entity bigstar without a semicolon`() = assertTokenization(
        input = "&bigstar",
        expectedTokens = listOf(
            *chars("&bigstar").toTypedArray()
        )
    )

    @Test
    fun `Named entity bigstar with a semicolon`() = assertTokenization(
        input = "&bigstar;",
        expectedTokens = listOf(
            char('★')
        )
    )

    @Test
    fun `Bad named entity bigtriangledown without a semicolon`() = assertTokenization(
        input = "&bigtriangledown",
        expectedTokens = listOf(
            *chars("&bigtriangledown").toTypedArray()
        )
    )

    @Test
    fun `Named entity bigtriangledown with a semicolon`() = assertTokenization(
        input = "&bigtriangledown;",
        expectedTokens = listOf(
            char('▽')
        )
    )

    @Test
    fun `Bad named entity bigtriangleup without a semicolon`() = assertTokenization(
        input = "&bigtriangleup",
        expectedTokens = listOf(
            *chars("&bigtriangleup").toTypedArray()
        )
    )

    @Test
    fun `Named entity bigtriangleup with a semicolon`() = assertTokenization(
        input = "&bigtriangleup;",
        expectedTokens = listOf(
            char('△')
        )
    )

    @Test
    fun `Bad named entity biguplus without a semicolon`() = assertTokenization(
        input = "&biguplus",
        expectedTokens = listOf(
            *chars("&biguplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity biguplus with a semicolon`() = assertTokenization(
        input = "&biguplus;",
        expectedTokens = listOf(
            char('⨄')
        )
    )

    @Test
    fun `Bad named entity bigvee without a semicolon`() = assertTokenization(
        input = "&bigvee",
        expectedTokens = listOf(
            *chars("&bigvee").toTypedArray()
        )
    )

    @Test
    fun `Named entity bigvee with a semicolon`() = assertTokenization(
        input = "&bigvee;",
        expectedTokens = listOf(
            char('⋁')
        )
    )

    @Test
    fun `Bad named entity bigwedge without a semicolon`() = assertTokenization(
        input = "&bigwedge",
        expectedTokens = listOf(
            *chars("&bigwedge").toTypedArray()
        )
    )

    @Test
    fun `Named entity bigwedge with a semicolon`() = assertTokenization(
        input = "&bigwedge;",
        expectedTokens = listOf(
            char('⋀')
        )
    )

    @Test
    fun `Bad named entity bkarow without a semicolon`() = assertTokenization(
        input = "&bkarow",
        expectedTokens = listOf(
            *chars("&bkarow").toTypedArray()
        )
    )

    @Test
    fun `Named entity bkarow with a semicolon`() = assertTokenization(
        input = "&bkarow;",
        expectedTokens = listOf(
            char('⤍')
        )
    )

    @Test
    fun `Bad named entity blacklozenge without a semicolon`() = assertTokenization(
        input = "&blacklozenge",
        expectedTokens = listOf(
            *chars("&blacklozenge").toTypedArray()
        )
    )

    @Test
    fun `Named entity blacklozenge with a semicolon`() = assertTokenization(
        input = "&blacklozenge;",
        expectedTokens = listOf(
            char('⧫')
        )
    )

    @Test
    fun `Bad named entity blacksquare without a semicolon`() = assertTokenization(
        input = "&blacksquare",
        expectedTokens = listOf(
            *chars("&blacksquare").toTypedArray()
        )
    )

    @Test
    fun `Named entity blacksquare with a semicolon`() = assertTokenization(
        input = "&blacksquare;",
        expectedTokens = listOf(
            char('▪')
        )
    )

    @Test
    fun `Bad named entity blacktriangle without a semicolon`() = assertTokenization(
        input = "&blacktriangle",
        expectedTokens = listOf(
            *chars("&blacktriangle").toTypedArray()
        )
    )

    @Test
    fun `Named entity blacktriangle with a semicolon`() = assertTokenization(
        input = "&blacktriangle;",
        expectedTokens = listOf(
            char('▴')
        )
    )

    @Test
    fun `Bad named entity blacktriangledown without a semicolon`() = assertTokenization(
        input = "&blacktriangledown",
        expectedTokens = listOf(
            *chars("&blacktriangledown").toTypedArray()
        )
    )

    @Test
    fun `Named entity blacktriangledown with a semicolon`() = assertTokenization(
        input = "&blacktriangledown;",
        expectedTokens = listOf(
            char('▾')
        )
    )

    @Test
    fun `Bad named entity blacktriangleleft without a semicolon`() = assertTokenization(
        input = "&blacktriangleleft",
        expectedTokens = listOf(
            *chars("&blacktriangleleft").toTypedArray()
        )
    )

    @Test
    fun `Named entity blacktriangleleft with a semicolon`() = assertTokenization(
        input = "&blacktriangleleft;",
        expectedTokens = listOf(
            char('◂')
        )
    )

    @Test
    fun `Bad named entity blacktriangleright without a semicolon`() = assertTokenization(
        input = "&blacktriangleright",
        expectedTokens = listOf(
            *chars("&blacktriangleright").toTypedArray()
        )
    )

    @Test
    fun `Named entity blacktriangleright with a semicolon`() = assertTokenization(
        input = "&blacktriangleright;",
        expectedTokens = listOf(
            char('▸')
        )
    )

    @Test
    fun `Bad named entity blank without a semicolon`() = assertTokenization(
        input = "&blank",
        expectedTokens = listOf(
            *chars("&blank").toTypedArray()
        )
    )

    @Test
    fun `Named entity blank with a semicolon`() = assertTokenization(
        input = "&blank;",
        expectedTokens = listOf(
            char('␣')
        )
    )

    @Test
    fun `Bad named entity blk12 without a semicolon`() = assertTokenization(
        input = "&blk12",
        expectedTokens = listOf(
            *chars("&blk12").toTypedArray()
        )
    )

    @Test
    fun `Named entity blk12 with a semicolon`() = assertTokenization(
        input = "&blk12;",
        expectedTokens = listOf(
            char('▒')
        )
    )

    @Test
    fun `Bad named entity blk14 without a semicolon`() = assertTokenization(
        input = "&blk14",
        expectedTokens = listOf(
            *chars("&blk14").toTypedArray()
        )
    )

    @Test
    fun `Named entity blk14 with a semicolon`() = assertTokenization(
        input = "&blk14;",
        expectedTokens = listOf(
            char('░')
        )
    )

    @Test
    fun `Bad named entity blk34 without a semicolon`() = assertTokenization(
        input = "&blk34",
        expectedTokens = listOf(
            *chars("&blk34").toTypedArray()
        )
    )

    @Test
    fun `Named entity blk34 with a semicolon`() = assertTokenization(
        input = "&blk34;",
        expectedTokens = listOf(
            char('▓')
        )
    )

    @Test
    fun `Bad named entity block without a semicolon`() = assertTokenization(
        input = "&block",
        expectedTokens = listOf(
            *chars("&block").toTypedArray()
        )
    )

    @Test
    fun `Named entity block with a semicolon`() = assertTokenization(
        input = "&block;",
        expectedTokens = listOf(
            char('█')
        )
    )

    @Test
    fun `Bad named entity bne without a semicolon`() = assertTokenization(
        input = "&bne",
        expectedTokens = listOf(
            *chars("&bne").toTypedArray()
        )
    )

    @Test
    fun `Named entity bne with a semicolon`() = assertTokenization(
        input = "&bne;",
        expectedTokens = listOf(
            *chars("=⃥").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity bnequiv without a semicolon`() = assertTokenization(
        input = "&bnequiv",
        expectedTokens = listOf(
            *chars("&bnequiv").toTypedArray()
        )
    )

    @Test
    fun `Named entity bnequiv with a semicolon`() = assertTokenization(
        input = "&bnequiv;",
        expectedTokens = listOf(
            *chars("≡⃥").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity bnot without a semicolon`() = assertTokenization(
        input = "&bnot",
        expectedTokens = listOf(
            *chars("&bnot").toTypedArray()
        )
    )

    @Test
    fun `Named entity bnot with a semicolon`() = assertTokenization(
        input = "&bnot;",
        expectedTokens = listOf(
            char('⌐')
        )
    )

    @Test
    fun `Bad named entity bopf without a semicolon`() = assertTokenization(
        input = "&bopf",
        expectedTokens = listOf(
            *chars("&bopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity bopf with a semicolon`() = assertTokenization(
        input = "&bopf;",
        expectedTokens = listOf(
            *chars("𝕓").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity bot without a semicolon`() = assertTokenization(
        input = "&bot",
        expectedTokens = listOf(
            *chars("&bot").toTypedArray()
        )
    )

    @Test
    fun `Named entity bot with a semicolon`() = assertTokenization(
        input = "&bot;",
        expectedTokens = listOf(
            char('⊥')
        )
    )

    @Test
    fun `Bad named entity bottom without a semicolon`() = assertTokenization(
        input = "&bottom",
        expectedTokens = listOf(
            *chars("&bottom").toTypedArray()
        )
    )

    @Test
    fun `Named entity bottom with a semicolon`() = assertTokenization(
        input = "&bottom;",
        expectedTokens = listOf(
            char('⊥')
        )
    )

    @Test
    fun `Bad named entity bowtie without a semicolon`() = assertTokenization(
        input = "&bowtie",
        expectedTokens = listOf(
            *chars("&bowtie").toTypedArray()
        )
    )

    @Test
    fun `Named entity bowtie with a semicolon`() = assertTokenization(
        input = "&bowtie;",
        expectedTokens = listOf(
            char('⋈')
        )
    )

    @Test
    fun `Bad named entity boxDL without a semicolon`() = assertTokenization(
        input = "&boxDL",
        expectedTokens = listOf(
            *chars("&boxDL").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxDL with a semicolon`() = assertTokenization(
        input = "&boxDL;",
        expectedTokens = listOf(
            char('╗')
        )
    )

    @Test
    fun `Bad named entity boxDR without a semicolon`() = assertTokenization(
        input = "&boxDR",
        expectedTokens = listOf(
            *chars("&boxDR").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxDR with a semicolon`() = assertTokenization(
        input = "&boxDR;",
        expectedTokens = listOf(
            char('╔')
        )
    )

    @Test
    fun `Bad named entity boxDl without a semicolon`() = assertTokenization(
        input = "&boxDl",
        expectedTokens = listOf(
            *chars("&boxDl").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxDl with a semicolon`() = assertTokenization(
        input = "&boxDl;",
        expectedTokens = listOf(
            char('╖')
        )
    )

    @Test
    fun `Bad named entity boxDr without a semicolon`() = assertTokenization(
        input = "&boxDr",
        expectedTokens = listOf(
            *chars("&boxDr").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxDr with a semicolon`() = assertTokenization(
        input = "&boxDr;",
        expectedTokens = listOf(
            char('╓')
        )
    )

    @Test
    fun `Bad named entity boxH without a semicolon`() = assertTokenization(
        input = "&boxH",
        expectedTokens = listOf(
            *chars("&boxH").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxH with a semicolon`() = assertTokenization(
        input = "&boxH;",
        expectedTokens = listOf(
            char('═')
        )
    )

    @Test
    fun `Bad named entity boxHD without a semicolon`() = assertTokenization(
        input = "&boxHD",
        expectedTokens = listOf(
            *chars("&boxHD").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxHD with a semicolon`() = assertTokenization(
        input = "&boxHD;",
        expectedTokens = listOf(
            char('╦')
        )
    )

    @Test
    fun `Bad named entity boxHU without a semicolon`() = assertTokenization(
        input = "&boxHU",
        expectedTokens = listOf(
            *chars("&boxHU").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxHU with a semicolon`() = assertTokenization(
        input = "&boxHU;",
        expectedTokens = listOf(
            char('╩')
        )
    )

    @Test
    fun `Bad named entity boxHd without a semicolon`() = assertTokenization(
        input = "&boxHd",
        expectedTokens = listOf(
            *chars("&boxHd").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxHd with a semicolon`() = assertTokenization(
        input = "&boxHd;",
        expectedTokens = listOf(
            char('╤')
        )
    )

    @Test
    fun `Bad named entity boxHu without a semicolon`() = assertTokenization(
        input = "&boxHu",
        expectedTokens = listOf(
            *chars("&boxHu").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxHu with a semicolon`() = assertTokenization(
        input = "&boxHu;",
        expectedTokens = listOf(
            char('╧')
        )
    )

    @Test
    fun `Bad named entity boxUL without a semicolon`() = assertTokenization(
        input = "&boxUL",
        expectedTokens = listOf(
            *chars("&boxUL").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxUL with a semicolon`() = assertTokenization(
        input = "&boxUL;",
        expectedTokens = listOf(
            char('╝')
        )
    )

    @Test
    fun `Bad named entity boxUR without a semicolon`() = assertTokenization(
        input = "&boxUR",
        expectedTokens = listOf(
            *chars("&boxUR").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxUR with a semicolon`() = assertTokenization(
        input = "&boxUR;",
        expectedTokens = listOf(
            char('╚')
        )
    )

    @Test
    fun `Bad named entity boxUl without a semicolon`() = assertTokenization(
        input = "&boxUl",
        expectedTokens = listOf(
            *chars("&boxUl").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxUl with a semicolon`() = assertTokenization(
        input = "&boxUl;",
        expectedTokens = listOf(
            char('╜')
        )
    )

    @Test
    fun `Bad named entity boxUr without a semicolon`() = assertTokenization(
        input = "&boxUr",
        expectedTokens = listOf(
            *chars("&boxUr").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxUr with a semicolon`() = assertTokenization(
        input = "&boxUr;",
        expectedTokens = listOf(
            char('╙')
        )
    )

    @Test
    fun `Bad named entity boxV without a semicolon`() = assertTokenization(
        input = "&boxV",
        expectedTokens = listOf(
            *chars("&boxV").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxV with a semicolon`() = assertTokenization(
        input = "&boxV;",
        expectedTokens = listOf(
            char('║')
        )
    )

    @Test
    fun `Bad named entity boxVH without a semicolon`() = assertTokenization(
        input = "&boxVH",
        expectedTokens = listOf(
            *chars("&boxVH").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxVH with a semicolon`() = assertTokenization(
        input = "&boxVH;",
        expectedTokens = listOf(
            char('╬')
        )
    )

    @Test
    fun `Bad named entity boxVL without a semicolon`() = assertTokenization(
        input = "&boxVL",
        expectedTokens = listOf(
            *chars("&boxVL").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxVL with a semicolon`() = assertTokenization(
        input = "&boxVL;",
        expectedTokens = listOf(
            char('╣')
        )
    )

    @Test
    fun `Bad named entity boxVR without a semicolon`() = assertTokenization(
        input = "&boxVR",
        expectedTokens = listOf(
            *chars("&boxVR").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxVR with a semicolon`() = assertTokenization(
        input = "&boxVR;",
        expectedTokens = listOf(
            char('╠')
        )
    )

    @Test
    fun `Bad named entity boxVh without a semicolon`() = assertTokenization(
        input = "&boxVh",
        expectedTokens = listOf(
            *chars("&boxVh").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxVh with a semicolon`() = assertTokenization(
        input = "&boxVh;",
        expectedTokens = listOf(
            char('╫')
        )
    )

    @Test
    fun `Bad named entity boxVl without a semicolon`() = assertTokenization(
        input = "&boxVl",
        expectedTokens = listOf(
            *chars("&boxVl").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxVl with a semicolon`() = assertTokenization(
        input = "&boxVl;",
        expectedTokens = listOf(
            char('╢')
        )
    )

    @Test
    fun `Bad named entity boxVr without a semicolon`() = assertTokenization(
        input = "&boxVr",
        expectedTokens = listOf(
            *chars("&boxVr").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxVr with a semicolon`() = assertTokenization(
        input = "&boxVr;",
        expectedTokens = listOf(
            char('╟')
        )
    )

    @Test
    fun `Bad named entity boxbox without a semicolon`() = assertTokenization(
        input = "&boxbox",
        expectedTokens = listOf(
            *chars("&boxbox").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxbox with a semicolon`() = assertTokenization(
        input = "&boxbox;",
        expectedTokens = listOf(
            char('⧉')
        )
    )

    @Test
    fun `Bad named entity boxdL without a semicolon`() = assertTokenization(
        input = "&boxdL",
        expectedTokens = listOf(
            *chars("&boxdL").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxdL with a semicolon`() = assertTokenization(
        input = "&boxdL;",
        expectedTokens = listOf(
            char('╕')
        )
    )

    @Test
    fun `Bad named entity boxdR without a semicolon`() = assertTokenization(
        input = "&boxdR",
        expectedTokens = listOf(
            *chars("&boxdR").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxdR with a semicolon`() = assertTokenization(
        input = "&boxdR;",
        expectedTokens = listOf(
            char('╒')
        )
    )

    @Test
    fun `Bad named entity boxdl without a semicolon`() = assertTokenization(
        input = "&boxdl",
        expectedTokens = listOf(
            *chars("&boxdl").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxdl with a semicolon`() = assertTokenization(
        input = "&boxdl;",
        expectedTokens = listOf(
            char('┐')
        )
    )

    @Test
    fun `Bad named entity boxdr without a semicolon`() = assertTokenization(
        input = "&boxdr",
        expectedTokens = listOf(
            *chars("&boxdr").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxdr with a semicolon`() = assertTokenization(
        input = "&boxdr;",
        expectedTokens = listOf(
            char('┌')
        )
    )

    @Test
    fun `Bad named entity boxh without a semicolon`() = assertTokenization(
        input = "&boxh",
        expectedTokens = listOf(
            *chars("&boxh").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxh with a semicolon`() = assertTokenization(
        input = "&boxh;",
        expectedTokens = listOf(
            char('─')
        )
    )

    @Test
    fun `Bad named entity boxhD without a semicolon`() = assertTokenization(
        input = "&boxhD",
        expectedTokens = listOf(
            *chars("&boxhD").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxhD with a semicolon`() = assertTokenization(
        input = "&boxhD;",
        expectedTokens = listOf(
            char('╥')
        )
    )

    @Test
    fun `Bad named entity boxhU without a semicolon`() = assertTokenization(
        input = "&boxhU",
        expectedTokens = listOf(
            *chars("&boxhU").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxhU with a semicolon`() = assertTokenization(
        input = "&boxhU;",
        expectedTokens = listOf(
            char('╨')
        )
    )

    @Test
    fun `Bad named entity boxhd without a semicolon`() = assertTokenization(
        input = "&boxhd",
        expectedTokens = listOf(
            *chars("&boxhd").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxhd with a semicolon`() = assertTokenization(
        input = "&boxhd;",
        expectedTokens = listOf(
            char('┬')
        )
    )

    @Test
    fun `Bad named entity boxhu without a semicolon`() = assertTokenization(
        input = "&boxhu",
        expectedTokens = listOf(
            *chars("&boxhu").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxhu with a semicolon`() = assertTokenization(
        input = "&boxhu;",
        expectedTokens = listOf(
            char('┴')
        )
    )

    @Test
    fun `Bad named entity boxminus without a semicolon`() = assertTokenization(
        input = "&boxminus",
        expectedTokens = listOf(
            *chars("&boxminus").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxminus with a semicolon`() = assertTokenization(
        input = "&boxminus;",
        expectedTokens = listOf(
            char('⊟')
        )
    )

    @Test
    fun `Bad named entity boxplus without a semicolon`() = assertTokenization(
        input = "&boxplus",
        expectedTokens = listOf(
            *chars("&boxplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxplus with a semicolon`() = assertTokenization(
        input = "&boxplus;",
        expectedTokens = listOf(
            char('⊞')
        )
    )

    @Test
    fun `Bad named entity boxtimes without a semicolon`() = assertTokenization(
        input = "&boxtimes",
        expectedTokens = listOf(
            *chars("&boxtimes").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxtimes with a semicolon`() = assertTokenization(
        input = "&boxtimes;",
        expectedTokens = listOf(
            char('⊠')
        )
    )

    @Test
    fun `Bad named entity boxuL without a semicolon`() = assertTokenization(
        input = "&boxuL",
        expectedTokens = listOf(
            *chars("&boxuL").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxuL with a semicolon`() = assertTokenization(
        input = "&boxuL;",
        expectedTokens = listOf(
            char('╛')
        )
    )

    @Test
    fun `Bad named entity boxuR without a semicolon`() = assertTokenization(
        input = "&boxuR",
        expectedTokens = listOf(
            *chars("&boxuR").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxuR with a semicolon`() = assertTokenization(
        input = "&boxuR;",
        expectedTokens = listOf(
            char('╘')
        )
    )

    @Test
    fun `Bad named entity boxul without a semicolon`() = assertTokenization(
        input = "&boxul",
        expectedTokens = listOf(
            *chars("&boxul").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxul with a semicolon`() = assertTokenization(
        input = "&boxul;",
        expectedTokens = listOf(
            char('┘')
        )
    )

    @Test
    fun `Bad named entity boxur without a semicolon`() = assertTokenization(
        input = "&boxur",
        expectedTokens = listOf(
            *chars("&boxur").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxur with a semicolon`() = assertTokenization(
        input = "&boxur;",
        expectedTokens = listOf(
            char('└')
        )
    )

    @Test
    fun `Bad named entity boxv without a semicolon`() = assertTokenization(
        input = "&boxv",
        expectedTokens = listOf(
            *chars("&boxv").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxv with a semicolon`() = assertTokenization(
        input = "&boxv;",
        expectedTokens = listOf(
            char('│')
        )
    )

    @Test
    fun `Bad named entity boxvH without a semicolon`() = assertTokenization(
        input = "&boxvH",
        expectedTokens = listOf(
            *chars("&boxvH").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxvH with a semicolon`() = assertTokenization(
        input = "&boxvH;",
        expectedTokens = listOf(
            char('╪')
        )
    )

    @Test
    fun `Bad named entity boxvL without a semicolon`() = assertTokenization(
        input = "&boxvL",
        expectedTokens = listOf(
            *chars("&boxvL").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxvL with a semicolon`() = assertTokenization(
        input = "&boxvL;",
        expectedTokens = listOf(
            char('╡')
        )
    )

    @Test
    fun `Bad named entity boxvR without a semicolon`() = assertTokenization(
        input = "&boxvR",
        expectedTokens = listOf(
            *chars("&boxvR").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxvR with a semicolon`() = assertTokenization(
        input = "&boxvR;",
        expectedTokens = listOf(
            char('╞')
        )
    )

    @Test
    fun `Bad named entity boxvh without a semicolon`() = assertTokenization(
        input = "&boxvh",
        expectedTokens = listOf(
            *chars("&boxvh").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxvh with a semicolon`() = assertTokenization(
        input = "&boxvh;",
        expectedTokens = listOf(
            char('┼')
        )
    )

    @Test
    fun `Bad named entity boxvl without a semicolon`() = assertTokenization(
        input = "&boxvl",
        expectedTokens = listOf(
            *chars("&boxvl").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxvl with a semicolon`() = assertTokenization(
        input = "&boxvl;",
        expectedTokens = listOf(
            char('┤')
        )
    )

    @Test
    fun `Bad named entity boxvr without a semicolon`() = assertTokenization(
        input = "&boxvr",
        expectedTokens = listOf(
            *chars("&boxvr").toTypedArray()
        )
    )

    @Test
    fun `Named entity boxvr with a semicolon`() = assertTokenization(
        input = "&boxvr;",
        expectedTokens = listOf(
            char('├')
        )
    )

    @Test
    fun `Bad named entity bprime without a semicolon`() = assertTokenization(
        input = "&bprime",
        expectedTokens = listOf(
            *chars("&bprime").toTypedArray()
        )
    )

    @Test
    fun `Named entity bprime with a semicolon`() = assertTokenization(
        input = "&bprime;",
        expectedTokens = listOf(
            char('‵')
        )
    )

    @Test
    fun `Bad named entity breve without a semicolon`() = assertTokenization(
        input = "&breve",
        expectedTokens = listOf(
            *chars("&breve").toTypedArray()
        )
    )

    @Test
    fun `Named entity breve with a semicolon`() = assertTokenization(
        input = "&breve;",
        expectedTokens = listOf(
            char('˘')
        )
    )

    @Test
    fun `Named entity brvbar without a semicolon`() = assertTokenization(
        input = "&brvbar",
        expectedTokens = listOf(
            char('¦')
        )
    )

    @Test
    fun `Named entity brvbar with a semicolon`() = assertTokenization(
        input = "&brvbar;",
        expectedTokens = listOf(
            char('¦')
        )
    )

    @Test
    fun `Bad named entity bscr without a semicolon`() = assertTokenization(
        input = "&bscr",
        expectedTokens = listOf(
            *chars("&bscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity bscr with a semicolon`() = assertTokenization(
        input = "&bscr;",
        expectedTokens = listOf(
            *chars("𝒷").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity bsemi without a semicolon`() = assertTokenization(
        input = "&bsemi",
        expectedTokens = listOf(
            *chars("&bsemi").toTypedArray()
        )
    )

    @Test
    fun `Named entity bsemi with a semicolon`() = assertTokenization(
        input = "&bsemi;",
        expectedTokens = listOf(
            char('⁏')
        )
    )

    @Test
    fun `Bad named entity bsim without a semicolon`() = assertTokenization(
        input = "&bsim",
        expectedTokens = listOf(
            *chars("&bsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity bsim with a semicolon`() = assertTokenization(
        input = "&bsim;",
        expectedTokens = listOf(
            char('∽')
        )
    )

    @Test
    fun `Bad named entity bsime without a semicolon`() = assertTokenization(
        input = "&bsime",
        expectedTokens = listOf(
            *chars("&bsime").toTypedArray()
        )
    )

    @Test
    fun `Named entity bsime with a semicolon`() = assertTokenization(
        input = "&bsime;",
        expectedTokens = listOf(
            char('⋍')
        )
    )

    @Test
    fun `Bad named entity bsol without a semicolon`() = assertTokenization(
        input = "&bsol",
        expectedTokens = listOf(
            *chars("&bsol").toTypedArray()
        )
    )

    @Test
    fun `Named entity bsol with a semicolon`() = assertTokenization(
        input = "&bsol;",
        expectedTokens = listOf(
            char('\\')
        )
    )

    @Test
    fun `Bad named entity bsolb without a semicolon`() = assertTokenization(
        input = "&bsolb",
        expectedTokens = listOf(
            *chars("&bsolb").toTypedArray()
        )
    )

    @Test
    fun `Named entity bsolb with a semicolon`() = assertTokenization(
        input = "&bsolb;",
        expectedTokens = listOf(
            char('⧅')
        )
    )

    @Test
    fun `Bad named entity bsolhsub without a semicolon`() = assertTokenization(
        input = "&bsolhsub",
        expectedTokens = listOf(
            *chars("&bsolhsub").toTypedArray()
        )
    )

    @Test
    fun `Named entity bsolhsub with a semicolon`() = assertTokenization(
        input = "&bsolhsub;",
        expectedTokens = listOf(
            char('⟈')
        )
    )

    @Test
    fun `Bad named entity bull without a semicolon`() = assertTokenization(
        input = "&bull",
        expectedTokens = listOf(
            *chars("&bull").toTypedArray()
        )
    )

    @Test
    fun `Named entity bull with a semicolon`() = assertTokenization(
        input = "&bull;",
        expectedTokens = listOf(
            char('•')
        )
    )

    @Test
    fun `Bad named entity bullet without a semicolon`() = assertTokenization(
        input = "&bullet",
        expectedTokens = listOf(
            *chars("&bullet").toTypedArray()
        )
    )

    @Test
    fun `Named entity bullet with a semicolon`() = assertTokenization(
        input = "&bullet;",
        expectedTokens = listOf(
            char('•')
        )
    )

    @Test
    fun `Bad named entity bump without a semicolon`() = assertTokenization(
        input = "&bump",
        expectedTokens = listOf(
            *chars("&bump").toTypedArray()
        )
    )

    @Test
    fun `Named entity bump with a semicolon`() = assertTokenization(
        input = "&bump;",
        expectedTokens = listOf(
            char('≎')
        )
    )

    @Test
    fun `Bad named entity bumpE without a semicolon`() = assertTokenization(
        input = "&bumpE",
        expectedTokens = listOf(
            *chars("&bumpE").toTypedArray()
        )
    )

    @Test
    fun `Named entity bumpE with a semicolon`() = assertTokenization(
        input = "&bumpE;",
        expectedTokens = listOf(
            char('⪮')
        )
    )

    @Test
    fun `Bad named entity bumpe without a semicolon`() = assertTokenization(
        input = "&bumpe",
        expectedTokens = listOf(
            *chars("&bumpe").toTypedArray()
        )
    )

    @Test
    fun `Named entity bumpe with a semicolon`() = assertTokenization(
        input = "&bumpe;",
        expectedTokens = listOf(
            char('≏')
        )
    )

    @Test
    fun `Bad named entity bumpeq without a semicolon`() = assertTokenization(
        input = "&bumpeq",
        expectedTokens = listOf(
            *chars("&bumpeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity bumpeq with a semicolon`() = assertTokenization(
        input = "&bumpeq;",
        expectedTokens = listOf(
            char('≏')
        )
    )

    @Test
    fun `Bad named entity cacute without a semicolon`() = assertTokenization(
        input = "&cacute",
        expectedTokens = listOf(
            *chars("&cacute").toTypedArray()
        )
    )

    @Test
    fun `Named entity cacute with a semicolon`() = assertTokenization(
        input = "&cacute;",
        expectedTokens = listOf(
            char('ć')
        )
    )

    @Test
    fun `Bad named entity cap without a semicolon`() = assertTokenization(
        input = "&cap",
        expectedTokens = listOf(
            *chars("&cap").toTypedArray()
        )
    )

    @Test
    fun `Named entity cap with a semicolon`() = assertTokenization(
        input = "&cap;",
        expectedTokens = listOf(
            char('∩')
        )
    )

    @Test
    fun `Bad named entity capand without a semicolon`() = assertTokenization(
        input = "&capand",
        expectedTokens = listOf(
            *chars("&capand").toTypedArray()
        )
    )

    @Test
    fun `Named entity capand with a semicolon`() = assertTokenization(
        input = "&capand;",
        expectedTokens = listOf(
            char('⩄')
        )
    )

    @Test
    fun `Bad named entity capbrcup without a semicolon`() = assertTokenization(
        input = "&capbrcup",
        expectedTokens = listOf(
            *chars("&capbrcup").toTypedArray()
        )
    )

    @Test
    fun `Named entity capbrcup with a semicolon`() = assertTokenization(
        input = "&capbrcup;",
        expectedTokens = listOf(
            char('⩉')
        )
    )

    @Test
    fun `Bad named entity capcap without a semicolon`() = assertTokenization(
        input = "&capcap",
        expectedTokens = listOf(
            *chars("&capcap").toTypedArray()
        )
    )

    @Test
    fun `Named entity capcap with a semicolon`() = assertTokenization(
        input = "&capcap;",
        expectedTokens = listOf(
            char('⩋')
        )
    )

    @Test
    fun `Bad named entity capcup without a semicolon`() = assertTokenization(
        input = "&capcup",
        expectedTokens = listOf(
            *chars("&capcup").toTypedArray()
        )
    )

    @Test
    fun `Named entity capcup with a semicolon`() = assertTokenization(
        input = "&capcup;",
        expectedTokens = listOf(
            char('⩇')
        )
    )

    @Test
    fun `Bad named entity capdot without a semicolon`() = assertTokenization(
        input = "&capdot",
        expectedTokens = listOf(
            *chars("&capdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity capdot with a semicolon`() = assertTokenization(
        input = "&capdot;",
        expectedTokens = listOf(
            char('⩀')
        )
    )

    @Test
    fun `Bad named entity caps without a semicolon`() = assertTokenization(
        input = "&caps",
        expectedTokens = listOf(
            *chars("&caps").toTypedArray()
        )
    )

    @Test
    fun `Named entity caps with a semicolon`() = assertTokenization(
        input = "&caps;",
        expectedTokens = listOf(
            *chars("∩︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity caret without a semicolon`() = assertTokenization(
        input = "&caret",
        expectedTokens = listOf(
            *chars("&caret").toTypedArray()
        )
    )

    @Test
    fun `Named entity caret with a semicolon`() = assertTokenization(
        input = "&caret;",
        expectedTokens = listOf(
            char('⁁')
        )
    )

    @Test
    fun `Bad named entity caron without a semicolon`() = assertTokenization(
        input = "&caron",
        expectedTokens = listOf(
            *chars("&caron").toTypedArray()
        )
    )

    @Test
    fun `Named entity caron with a semicolon`() = assertTokenization(
        input = "&caron;",
        expectedTokens = listOf(
            char('ˇ')
        )
    )

    @Test
    fun `Bad named entity ccaps without a semicolon`() = assertTokenization(
        input = "&ccaps",
        expectedTokens = listOf(
            *chars("&ccaps").toTypedArray()
        )
    )

    @Test
    fun `Named entity ccaps with a semicolon`() = assertTokenization(
        input = "&ccaps;",
        expectedTokens = listOf(
            char('⩍')
        )
    )

    @Test
    fun `Bad named entity ccaron without a semicolon`() = assertTokenization(
        input = "&ccaron",
        expectedTokens = listOf(
            *chars("&ccaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity ccaron with a semicolon`() = assertTokenization(
        input = "&ccaron;",
        expectedTokens = listOf(
            char('č')
        )
    )

    @Test
    fun `Named entity ccedil without a semicolon`() = assertTokenization(
        input = "&ccedil",
        expectedTokens = listOf(
            char('ç')
        )
    )

    @Test
    fun `Named entity ccedil with a semicolon`() = assertTokenization(
        input = "&ccedil;",
        expectedTokens = listOf(
            char('ç')
        )
    )

    @Test
    fun `Bad named entity ccirc without a semicolon`() = assertTokenization(
        input = "&ccirc",
        expectedTokens = listOf(
            *chars("&ccirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity ccirc with a semicolon`() = assertTokenization(
        input = "&ccirc;",
        expectedTokens = listOf(
            char('ĉ')
        )
    )

    @Test
    fun `Bad named entity ccups without a semicolon`() = assertTokenization(
        input = "&ccups",
        expectedTokens = listOf(
            *chars("&ccups").toTypedArray()
        )
    )

    @Test
    fun `Named entity ccups with a semicolon`() = assertTokenization(
        input = "&ccups;",
        expectedTokens = listOf(
            char('⩌')
        )
    )

    @Test
    fun `Bad named entity ccupssm without a semicolon`() = assertTokenization(
        input = "&ccupssm",
        expectedTokens = listOf(
            *chars("&ccupssm").toTypedArray()
        )
    )

    @Test
    fun `Named entity ccupssm with a semicolon`() = assertTokenization(
        input = "&ccupssm;",
        expectedTokens = listOf(
            char('⩐')
        )
    )

    @Test
    fun `Bad named entity cdot without a semicolon`() = assertTokenization(
        input = "&cdot",
        expectedTokens = listOf(
            *chars("&cdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity cdot with a semicolon`() = assertTokenization(
        input = "&cdot;",
        expectedTokens = listOf(
            char('ċ')
        )
    )

    @Test
    fun `Named entity cedil without a semicolon`() = assertTokenization(
        input = "&cedil",
        expectedTokens = listOf(
            char('¸')
        )
    )

    @Test
    fun `Named entity cedil with a semicolon`() = assertTokenization(
        input = "&cedil;",
        expectedTokens = listOf(
            char('¸')
        )
    )

    @Test
    fun `Bad named entity cemptyv without a semicolon`() = assertTokenization(
        input = "&cemptyv",
        expectedTokens = listOf(
            *chars("&cemptyv").toTypedArray()
        )
    )

    @Test
    fun `Named entity cemptyv with a semicolon`() = assertTokenization(
        input = "&cemptyv;",
        expectedTokens = listOf(
            char('⦲')
        )
    )

    @Test
    fun `Named entity cent without a semicolon`() = assertTokenization(
        input = "&cent",
        expectedTokens = listOf(
            char('¢')
        )
    )

    @Test
    fun `Named entity cent with a semicolon`() = assertTokenization(
        input = "&cent;",
        expectedTokens = listOf(
            char('¢')
        )
    )

    @Test
    fun `Named entity centerdot with a semicolon`() = assertTokenization(
        input = "&centerdot;",
        expectedTokens = listOf(
            char('·')
        )
    )

    @Test
    fun `Bad named entity cfr without a semicolon`() = assertTokenization(
        input = "&cfr",
        expectedTokens = listOf(
            *chars("&cfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity cfr with a semicolon`() = assertTokenization(
        input = "&cfr;",
        expectedTokens = listOf(
            *chars("𝔠").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity chcy without a semicolon`() = assertTokenization(
        input = "&chcy",
        expectedTokens = listOf(
            *chars("&chcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity chcy with a semicolon`() = assertTokenization(
        input = "&chcy;",
        expectedTokens = listOf(
            char('ч')
        )
    )

    @Test
    fun `Bad named entity check without a semicolon`() = assertTokenization(
        input = "&check",
        expectedTokens = listOf(
            *chars("&check").toTypedArray()
        )
    )

    @Test
    fun `Named entity check with a semicolon`() = assertTokenization(
        input = "&check;",
        expectedTokens = listOf(
            char('✓')
        )
    )

    @Test
    fun `Bad named entity checkmark without a semicolon`() = assertTokenization(
        input = "&checkmark",
        expectedTokens = listOf(
            *chars("&checkmark").toTypedArray()
        )
    )

    @Test
    fun `Named entity checkmark with a semicolon`() = assertTokenization(
        input = "&checkmark;",
        expectedTokens = listOf(
            char('✓')
        )
    )

    @Test
    fun `Bad named entity chi without a semicolon`() = assertTokenization(
        input = "&chi",
        expectedTokens = listOf(
            *chars("&chi").toTypedArray()
        )
    )

    @Test
    fun `Named entity chi with a semicolon`() = assertTokenization(
        input = "&chi;",
        expectedTokens = listOf(
            char('χ')
        )
    )

    @Test
    fun `Bad named entity cir without a semicolon`() = assertTokenization(
        input = "&cir",
        expectedTokens = listOf(
            *chars("&cir").toTypedArray()
        )
    )

    @Test
    fun `Named entity cir with a semicolon`() = assertTokenization(
        input = "&cir;",
        expectedTokens = listOf(
            char('○')
        )
    )

    @Test
    fun `Bad named entity cirE without a semicolon`() = assertTokenization(
        input = "&cirE",
        expectedTokens = listOf(
            *chars("&cirE").toTypedArray()
        )
    )

    @Test
    fun `Named entity cirE with a semicolon`() = assertTokenization(
        input = "&cirE;",
        expectedTokens = listOf(
            char('⧃')
        )
    )

    @Test
    fun `Bad named entity circ without a semicolon`() = assertTokenization(
        input = "&circ",
        expectedTokens = listOf(
            *chars("&circ").toTypedArray()
        )
    )

    @Test
    fun `Named entity circ with a semicolon`() = assertTokenization(
        input = "&circ;",
        expectedTokens = listOf(
            char('ˆ')
        )
    )

    @Test
    fun `Bad named entity circeq without a semicolon`() = assertTokenization(
        input = "&circeq",
        expectedTokens = listOf(
            *chars("&circeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity circeq with a semicolon`() = assertTokenization(
        input = "&circeq;",
        expectedTokens = listOf(
            char('≗')
        )
    )

    @Test
    fun `Bad named entity circlearrowleft without a semicolon`() = assertTokenization(
        input = "&circlearrowleft",
        expectedTokens = listOf(
            *chars("&circlearrowleft").toTypedArray()
        )
    )

    @Test
    fun `Named entity circlearrowleft with a semicolon`() = assertTokenization(
        input = "&circlearrowleft;",
        expectedTokens = listOf(
            char('↺')
        )
    )

    @Test
    fun `Bad named entity circlearrowright without a semicolon`() = assertTokenization(
        input = "&circlearrowright",
        expectedTokens = listOf(
            *chars("&circlearrowright").toTypedArray()
        )
    )

    @Test
    fun `Named entity circlearrowright with a semicolon`() = assertTokenization(
        input = "&circlearrowright;",
        expectedTokens = listOf(
            char('↻')
        )
    )

    @Test
    fun `Bad named entity circledR without a semicolon`() = assertTokenization(
        input = "&circledR",
        expectedTokens = listOf(
            *chars("&circledR").toTypedArray()
        )
    )

    @Test
    fun `Named entity circledR with a semicolon`() = assertTokenization(
        input = "&circledR;",
        expectedTokens = listOf(
            char('®')
        )
    )

    @Test
    fun `Bad named entity circledS without a semicolon`() = assertTokenization(
        input = "&circledS",
        expectedTokens = listOf(
            *chars("&circledS").toTypedArray()
        )
    )

    @Test
    fun `Named entity circledS with a semicolon`() = assertTokenization(
        input = "&circledS;",
        expectedTokens = listOf(
            char('Ⓢ')
        )
    )

    @Test
    fun `Bad named entity circledast without a semicolon`() = assertTokenization(
        input = "&circledast",
        expectedTokens = listOf(
            *chars("&circledast").toTypedArray()
        )
    )

    @Test
    fun `Named entity circledast with a semicolon`() = assertTokenization(
        input = "&circledast;",
        expectedTokens = listOf(
            char('⊛')
        )
    )

    @Test
    fun `Bad named entity circledcirc without a semicolon`() = assertTokenization(
        input = "&circledcirc",
        expectedTokens = listOf(
            *chars("&circledcirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity circledcirc with a semicolon`() = assertTokenization(
        input = "&circledcirc;",
        expectedTokens = listOf(
            char('⊚')
        )
    )

    @Test
    fun `Bad named entity circleddash without a semicolon`() = assertTokenization(
        input = "&circleddash",
        expectedTokens = listOf(
            *chars("&circleddash").toTypedArray()
        )
    )

    @Test
    fun `Named entity circleddash with a semicolon`() = assertTokenization(
        input = "&circleddash;",
        expectedTokens = listOf(
            char('⊝')
        )
    )

    @Test
    fun `Bad named entity cire without a semicolon`() = assertTokenization(
        input = "&cire",
        expectedTokens = listOf(
            *chars("&cire").toTypedArray()
        )
    )

    @Test
    fun `Named entity cire with a semicolon`() = assertTokenization(
        input = "&cire;",
        expectedTokens = listOf(
            char('≗')
        )
    )

    @Test
    fun `Bad named entity cirfnint without a semicolon`() = assertTokenization(
        input = "&cirfnint",
        expectedTokens = listOf(
            *chars("&cirfnint").toTypedArray()
        )
    )

    @Test
    fun `Named entity cirfnint with a semicolon`() = assertTokenization(
        input = "&cirfnint;",
        expectedTokens = listOf(
            char('⨐')
        )
    )

    @Test
    fun `Bad named entity cirmid without a semicolon`() = assertTokenization(
        input = "&cirmid",
        expectedTokens = listOf(
            *chars("&cirmid").toTypedArray()
        )
    )

    @Test
    fun `Named entity cirmid with a semicolon`() = assertTokenization(
        input = "&cirmid;",
        expectedTokens = listOf(
            char('⫯')
        )
    )

    @Test
    fun `Bad named entity cirscir without a semicolon`() = assertTokenization(
        input = "&cirscir",
        expectedTokens = listOf(
            *chars("&cirscir").toTypedArray()
        )
    )

    @Test
    fun `Named entity cirscir with a semicolon`() = assertTokenization(
        input = "&cirscir;",
        expectedTokens = listOf(
            char('⧂')
        )
    )

    @Test
    fun `Bad named entity clubs without a semicolon`() = assertTokenization(
        input = "&clubs",
        expectedTokens = listOf(
            *chars("&clubs").toTypedArray()
        )
    )

    @Test
    fun `Named entity clubs with a semicolon`() = assertTokenization(
        input = "&clubs;",
        expectedTokens = listOf(
            char('♣')
        )
    )

    @Test
    fun `Bad named entity clubsuit without a semicolon`() = assertTokenization(
        input = "&clubsuit",
        expectedTokens = listOf(
            *chars("&clubsuit").toTypedArray()
        )
    )

    @Test
    fun `Named entity clubsuit with a semicolon`() = assertTokenization(
        input = "&clubsuit;",
        expectedTokens = listOf(
            char('♣')
        )
    )

    @Test
    fun `Bad named entity colon without a semicolon`() = assertTokenization(
        input = "&colon",
        expectedTokens = listOf(
            *chars("&colon").toTypedArray()
        )
    )

    @Test
    fun `Named entity colon with a semicolon`() = assertTokenization(
        input = "&colon;",
        expectedTokens = listOf(
            char(':')
        )
    )

    @Test
    fun `Bad named entity colone without a semicolon`() = assertTokenization(
        input = "&colone",
        expectedTokens = listOf(
            *chars("&colone").toTypedArray()
        )
    )

    @Test
    fun `Named entity colone with a semicolon`() = assertTokenization(
        input = "&colone;",
        expectedTokens = listOf(
            char('≔')
        )
    )

    @Test
    fun `Bad named entity coloneq without a semicolon`() = assertTokenization(
        input = "&coloneq",
        expectedTokens = listOf(
            *chars("&coloneq").toTypedArray()
        )
    )

    @Test
    fun `Named entity coloneq with a semicolon`() = assertTokenization(
        input = "&coloneq;",
        expectedTokens = listOf(
            char('≔')
        )
    )

    @Test
    fun `Bad named entity comma without a semicolon`() = assertTokenization(
        input = "&comma",
        expectedTokens = listOf(
            *chars("&comma").toTypedArray()
        )
    )

    @Test
    fun `Named entity comma with a semicolon`() = assertTokenization(
        input = "&comma;",
        expectedTokens = listOf(
            char(',')
        )
    )

    @Test
    fun `Bad named entity commat without a semicolon`() = assertTokenization(
        input = "&commat",
        expectedTokens = listOf(
            *chars("&commat").toTypedArray()
        )
    )

    @Test
    fun `Named entity commat with a semicolon`() = assertTokenization(
        input = "&commat;",
        expectedTokens = listOf(
            char('@')
        )
    )

    @Test
    fun `Bad named entity comp without a semicolon`() = assertTokenization(
        input = "&comp",
        expectedTokens = listOf(
            *chars("&comp").toTypedArray()
        )
    )

    @Test
    fun `Named entity comp with a semicolon`() = assertTokenization(
        input = "&comp;",
        expectedTokens = listOf(
            char('∁')
        )
    )

    @Test
    fun `Bad named entity compfn without a semicolon`() = assertTokenization(
        input = "&compfn",
        expectedTokens = listOf(
            *chars("&compfn").toTypedArray()
        )
    )

    @Test
    fun `Named entity compfn with a semicolon`() = assertTokenization(
        input = "&compfn;",
        expectedTokens = listOf(
            char('∘')
        )
    )

    @Test
    fun `Bad named entity complement without a semicolon`() = assertTokenization(
        input = "&complement",
        expectedTokens = listOf(
            *chars("&complement").toTypedArray()
        )
    )

    @Test
    fun `Named entity complement with a semicolon`() = assertTokenization(
        input = "&complement;",
        expectedTokens = listOf(
            char('∁')
        )
    )

    @Test
    fun `Bad named entity complexes without a semicolon`() = assertTokenization(
        input = "&complexes",
        expectedTokens = listOf(
            *chars("&complexes").toTypedArray()
        )
    )

    @Test
    fun `Named entity complexes with a semicolon`() = assertTokenization(
        input = "&complexes;",
        expectedTokens = listOf(
            char('ℂ')
        )
    )

    @Test
    fun `Bad named entity cong without a semicolon`() = assertTokenization(
        input = "&cong",
        expectedTokens = listOf(
            *chars("&cong").toTypedArray()
        )
    )

    @Test
    fun `Named entity cong with a semicolon`() = assertTokenization(
        input = "&cong;",
        expectedTokens = listOf(
            char('≅')
        )
    )

    @Test
    fun `Bad named entity congdot without a semicolon`() = assertTokenization(
        input = "&congdot",
        expectedTokens = listOf(
            *chars("&congdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity congdot with a semicolon`() = assertTokenization(
        input = "&congdot;",
        expectedTokens = listOf(
            char('⩭')
        )
    )

    @Test
    fun `Bad named entity conint without a semicolon`() = assertTokenization(
        input = "&conint",
        expectedTokens = listOf(
            *chars("&conint").toTypedArray()
        )
    )

    @Test
    fun `Named entity conint with a semicolon`() = assertTokenization(
        input = "&conint;",
        expectedTokens = listOf(
            char('∮')
        )
    )

    @Test
    fun `Bad named entity copf without a semicolon`() = assertTokenization(
        input = "&copf",
        expectedTokens = listOf(
            *chars("&copf").toTypedArray()
        )
    )

    @Test
    fun `Named entity copf with a semicolon`() = assertTokenization(
        input = "&copf;",
        expectedTokens = listOf(
            *chars("𝕔").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity coprod without a semicolon`() = assertTokenization(
        input = "&coprod",
        expectedTokens = listOf(
            *chars("&coprod").toTypedArray()
        )
    )

    @Test
    fun `Named entity coprod with a semicolon`() = assertTokenization(
        input = "&coprod;",
        expectedTokens = listOf(
            char('∐')
        )
    )

    @Test
    fun `Named entity copy without a semicolon`() = assertTokenization(
        input = "&copy",
        expectedTokens = listOf(
            char('©')
        )
    )

    @Test
    fun `Named entity copy with a semicolon`() = assertTokenization(
        input = "&copy;",
        expectedTokens = listOf(
            char('©')
        )
    )

    @Test
    fun `Named entity copysr with a semicolon`() = assertTokenization(
        input = "&copysr;",
        expectedTokens = listOf(
            char('℗')
        )
    )

    @Test
    fun `Bad named entity crarr without a semicolon`() = assertTokenization(
        input = "&crarr",
        expectedTokens = listOf(
            *chars("&crarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity crarr with a semicolon`() = assertTokenization(
        input = "&crarr;",
        expectedTokens = listOf(
            char('↵')
        )
    )

    @Test
    fun `Bad named entity cross without a semicolon`() = assertTokenization(
        input = "&cross",
        expectedTokens = listOf(
            *chars("&cross").toTypedArray()
        )
    )

    @Test
    fun `Named entity cross with a semicolon`() = assertTokenization(
        input = "&cross;",
        expectedTokens = listOf(
            char('✗')
        )
    )

    @Test
    fun `Bad named entity cscr without a semicolon`() = assertTokenization(
        input = "&cscr",
        expectedTokens = listOf(
            *chars("&cscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity cscr with a semicolon`() = assertTokenization(
        input = "&cscr;",
        expectedTokens = listOf(
            *chars("𝒸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity csub without a semicolon`() = assertTokenization(
        input = "&csub",
        expectedTokens = listOf(
            *chars("&csub").toTypedArray()
        )
    )

    @Test
    fun `Named entity csub with a semicolon`() = assertTokenization(
        input = "&csub;",
        expectedTokens = listOf(
            char('⫏')
        )
    )

    @Test
    fun `Bad named entity csube without a semicolon`() = assertTokenization(
        input = "&csube",
        expectedTokens = listOf(
            *chars("&csube").toTypedArray()
        )
    )

    @Test
    fun `Named entity csube with a semicolon`() = assertTokenization(
        input = "&csube;",
        expectedTokens = listOf(
            char('⫑')
        )
    )

    @Test
    fun `Bad named entity csup without a semicolon`() = assertTokenization(
        input = "&csup",
        expectedTokens = listOf(
            *chars("&csup").toTypedArray()
        )
    )

    @Test
    fun `Named entity csup with a semicolon`() = assertTokenization(
        input = "&csup;",
        expectedTokens = listOf(
            char('⫐')
        )
    )

    @Test
    fun `Bad named entity csupe without a semicolon`() = assertTokenization(
        input = "&csupe",
        expectedTokens = listOf(
            *chars("&csupe").toTypedArray()
        )
    )

    @Test
    fun `Named entity csupe with a semicolon`() = assertTokenization(
        input = "&csupe;",
        expectedTokens = listOf(
            char('⫒')
        )
    )

    @Test
    fun `Bad named entity ctdot without a semicolon`() = assertTokenization(
        input = "&ctdot",
        expectedTokens = listOf(
            *chars("&ctdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity ctdot with a semicolon`() = assertTokenization(
        input = "&ctdot;",
        expectedTokens = listOf(
            char('⋯')
        )
    )

    @Test
    fun `Bad named entity cudarrl without a semicolon`() = assertTokenization(
        input = "&cudarrl",
        expectedTokens = listOf(
            *chars("&cudarrl").toTypedArray()
        )
    )

    @Test
    fun `Named entity cudarrl with a semicolon`() = assertTokenization(
        input = "&cudarrl;",
        expectedTokens = listOf(
            char('⤸')
        )
    )

    @Test
    fun `Bad named entity cudarrr without a semicolon`() = assertTokenization(
        input = "&cudarrr",
        expectedTokens = listOf(
            *chars("&cudarrr").toTypedArray()
        )
    )

    @Test
    fun `Named entity cudarrr with a semicolon`() = assertTokenization(
        input = "&cudarrr;",
        expectedTokens = listOf(
            char('⤵')
        )
    )

    @Test
    fun `Bad named entity cuepr without a semicolon`() = assertTokenization(
        input = "&cuepr",
        expectedTokens = listOf(
            *chars("&cuepr").toTypedArray()
        )
    )

    @Test
    fun `Named entity cuepr with a semicolon`() = assertTokenization(
        input = "&cuepr;",
        expectedTokens = listOf(
            char('⋞')
        )
    )

    @Test
    fun `Bad named entity cuesc without a semicolon`() = assertTokenization(
        input = "&cuesc",
        expectedTokens = listOf(
            *chars("&cuesc").toTypedArray()
        )
    )

    @Test
    fun `Named entity cuesc with a semicolon`() = assertTokenization(
        input = "&cuesc;",
        expectedTokens = listOf(
            char('⋟')
        )
    )

    @Test
    fun `Bad named entity cularr without a semicolon`() = assertTokenization(
        input = "&cularr",
        expectedTokens = listOf(
            *chars("&cularr").toTypedArray()
        )
    )

    @Test
    fun `Named entity cularr with a semicolon`() = assertTokenization(
        input = "&cularr;",
        expectedTokens = listOf(
            char('↶')
        )
    )

    @Test
    fun `Bad named entity cularrp without a semicolon`() = assertTokenization(
        input = "&cularrp",
        expectedTokens = listOf(
            *chars("&cularrp").toTypedArray()
        )
    )

    @Test
    fun `Named entity cularrp with a semicolon`() = assertTokenization(
        input = "&cularrp;",
        expectedTokens = listOf(
            char('⤽')
        )
    )

    @Test
    fun `Bad named entity cup without a semicolon`() = assertTokenization(
        input = "&cup",
        expectedTokens = listOf(
            *chars("&cup").toTypedArray()
        )
    )

    @Test
    fun `Named entity cup with a semicolon`() = assertTokenization(
        input = "&cup;",
        expectedTokens = listOf(
            char('∪')
        )
    )

    @Test
    fun `Bad named entity cupbrcap without a semicolon`() = assertTokenization(
        input = "&cupbrcap",
        expectedTokens = listOf(
            *chars("&cupbrcap").toTypedArray()
        )
    )

    @Test
    fun `Named entity cupbrcap with a semicolon`() = assertTokenization(
        input = "&cupbrcap;",
        expectedTokens = listOf(
            char('⩈')
        )
    )

    @Test
    fun `Bad named entity cupcap without a semicolon`() = assertTokenization(
        input = "&cupcap",
        expectedTokens = listOf(
            *chars("&cupcap").toTypedArray()
        )
    )

    @Test
    fun `Named entity cupcap with a semicolon`() = assertTokenization(
        input = "&cupcap;",
        expectedTokens = listOf(
            char('⩆')
        )
    )

    @Test
    fun `Bad named entity cupcup without a semicolon`() = assertTokenization(
        input = "&cupcup",
        expectedTokens = listOf(
            *chars("&cupcup").toTypedArray()
        )
    )

    @Test
    fun `Named entity cupcup with a semicolon`() = assertTokenization(
        input = "&cupcup;",
        expectedTokens = listOf(
            char('⩊')
        )
    )

    @Test
    fun `Bad named entity cupdot without a semicolon`() = assertTokenization(
        input = "&cupdot",
        expectedTokens = listOf(
            *chars("&cupdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity cupdot with a semicolon`() = assertTokenization(
        input = "&cupdot;",
        expectedTokens = listOf(
            char('⊍')
        )
    )

    @Test
    fun `Bad named entity cupor without a semicolon`() = assertTokenization(
        input = "&cupor",
        expectedTokens = listOf(
            *chars("&cupor").toTypedArray()
        )
    )

    @Test
    fun `Named entity cupor with a semicolon`() = assertTokenization(
        input = "&cupor;",
        expectedTokens = listOf(
            char('⩅')
        )
    )

    @Test
    fun `Bad named entity cups without a semicolon`() = assertTokenization(
        input = "&cups",
        expectedTokens = listOf(
            *chars("&cups").toTypedArray()
        )
    )

    @Test
    fun `Named entity cups with a semicolon`() = assertTokenization(
        input = "&cups;",
        expectedTokens = listOf(
            *chars("∪︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity curarr without a semicolon`() = assertTokenization(
        input = "&curarr",
        expectedTokens = listOf(
            *chars("&curarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity curarr with a semicolon`() = assertTokenization(
        input = "&curarr;",
        expectedTokens = listOf(
            char('↷')
        )
    )

    @Test
    fun `Bad named entity curarrm without a semicolon`() = assertTokenization(
        input = "&curarrm",
        expectedTokens = listOf(
            *chars("&curarrm").toTypedArray()
        )
    )

    @Test
    fun `Named entity curarrm with a semicolon`() = assertTokenization(
        input = "&curarrm;",
        expectedTokens = listOf(
            char('⤼')
        )
    )

    @Test
    fun `Bad named entity curlyeqprec without a semicolon`() = assertTokenization(
        input = "&curlyeqprec",
        expectedTokens = listOf(
            *chars("&curlyeqprec").toTypedArray()
        )
    )

    @Test
    fun `Named entity curlyeqprec with a semicolon`() = assertTokenization(
        input = "&curlyeqprec;",
        expectedTokens = listOf(
            char('⋞')
        )
    )

    @Test
    fun `Bad named entity curlyeqsucc without a semicolon`() = assertTokenization(
        input = "&curlyeqsucc",
        expectedTokens = listOf(
            *chars("&curlyeqsucc").toTypedArray()
        )
    )

    @Test
    fun `Named entity curlyeqsucc with a semicolon`() = assertTokenization(
        input = "&curlyeqsucc;",
        expectedTokens = listOf(
            char('⋟')
        )
    )

    @Test
    fun `Bad named entity curlyvee without a semicolon`() = assertTokenization(
        input = "&curlyvee",
        expectedTokens = listOf(
            *chars("&curlyvee").toTypedArray()
        )
    )

    @Test
    fun `Named entity curlyvee with a semicolon`() = assertTokenization(
        input = "&curlyvee;",
        expectedTokens = listOf(
            char('⋎')
        )
    )

    @Test
    fun `Bad named entity curlywedge without a semicolon`() = assertTokenization(
        input = "&curlywedge",
        expectedTokens = listOf(
            *chars("&curlywedge").toTypedArray()
        )
    )

    @Test
    fun `Named entity curlywedge with a semicolon`() = assertTokenization(
        input = "&curlywedge;",
        expectedTokens = listOf(
            char('⋏')
        )
    )

    @Test
    fun `Named entity curren without a semicolon`() = assertTokenization(
        input = "&curren",
        expectedTokens = listOf(
            char('¤')
        )
    )

    @Test
    fun `Named entity curren with a semicolon`() = assertTokenization(
        input = "&curren;",
        expectedTokens = listOf(
            char('¤')
        )
    )

    @Test
    fun `Bad named entity curvearrowleft without a semicolon`() = assertTokenization(
        input = "&curvearrowleft",
        expectedTokens = listOf(
            *chars("&curvearrowleft").toTypedArray()
        )
    )

    @Test
    fun `Named entity curvearrowleft with a semicolon`() = assertTokenization(
        input = "&curvearrowleft;",
        expectedTokens = listOf(
            char('↶')
        )
    )

    @Test
    fun `Bad named entity curvearrowright without a semicolon`() = assertTokenization(
        input = "&curvearrowright",
        expectedTokens = listOf(
            *chars("&curvearrowright").toTypedArray()
        )
    )

    @Test
    fun `Named entity curvearrowright with a semicolon`() = assertTokenization(
        input = "&curvearrowright;",
        expectedTokens = listOf(
            char('↷')
        )
    )

    @Test
    fun `Bad named entity cuvee without a semicolon`() = assertTokenization(
        input = "&cuvee",
        expectedTokens = listOf(
            *chars("&cuvee").toTypedArray()
        )
    )

    @Test
    fun `Named entity cuvee with a semicolon`() = assertTokenization(
        input = "&cuvee;",
        expectedTokens = listOf(
            char('⋎')
        )
    )

    @Test
    fun `Bad named entity cuwed without a semicolon`() = assertTokenization(
        input = "&cuwed",
        expectedTokens = listOf(
            *chars("&cuwed").toTypedArray()
        )
    )

    @Test
    fun `Named entity cuwed with a semicolon`() = assertTokenization(
        input = "&cuwed;",
        expectedTokens = listOf(
            char('⋏')
        )
    )

    @Test
    fun `Bad named entity cwconint without a semicolon`() = assertTokenization(
        input = "&cwconint",
        expectedTokens = listOf(
            *chars("&cwconint").toTypedArray()
        )
    )

    @Test
    fun `Named entity cwconint with a semicolon`() = assertTokenization(
        input = "&cwconint;",
        expectedTokens = listOf(
            char('∲')
        )
    )

    @Test
    fun `Bad named entity cwint without a semicolon`() = assertTokenization(
        input = "&cwint",
        expectedTokens = listOf(
            *chars("&cwint").toTypedArray()
        )
    )

    @Test
    fun `Named entity cwint with a semicolon`() = assertTokenization(
        input = "&cwint;",
        expectedTokens = listOf(
            char('∱')
        )
    )

    @Test
    fun `Bad named entity cylcty without a semicolon`() = assertTokenization(
        input = "&cylcty",
        expectedTokens = listOf(
            *chars("&cylcty").toTypedArray()
        )
    )

    @Test
    fun `Named entity cylcty with a semicolon`() = assertTokenization(
        input = "&cylcty;",
        expectedTokens = listOf(
            char('⌭')
        )
    )

    @Test
    fun `Bad named entity dArr without a semicolon`() = assertTokenization(
        input = "&dArr",
        expectedTokens = listOf(
            *chars("&dArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity dArr with a semicolon`() = assertTokenization(
        input = "&dArr;",
        expectedTokens = listOf(
            char('⇓')
        )
    )

    @Test
    fun `Bad named entity dHar without a semicolon`() = assertTokenization(
        input = "&dHar",
        expectedTokens = listOf(
            *chars("&dHar").toTypedArray()
        )
    )

    @Test
    fun `Named entity dHar with a semicolon`() = assertTokenization(
        input = "&dHar;",
        expectedTokens = listOf(
            char('⥥')
        )
    )

    @Test
    fun `Bad named entity dagger without a semicolon`() = assertTokenization(
        input = "&dagger",
        expectedTokens = listOf(
            *chars("&dagger").toTypedArray()
        )
    )

    @Test
    fun `Named entity dagger with a semicolon`() = assertTokenization(
        input = "&dagger;",
        expectedTokens = listOf(
            char('†')
        )
    )

    @Test
    fun `Bad named entity daleth without a semicolon`() = assertTokenization(
        input = "&daleth",
        expectedTokens = listOf(
            *chars("&daleth").toTypedArray()
        )
    )

    @Test
    fun `Named entity daleth with a semicolon`() = assertTokenization(
        input = "&daleth;",
        expectedTokens = listOf(
            char('ℸ')
        )
    )

    @Test
    fun `Bad named entity darr without a semicolon`() = assertTokenization(
        input = "&darr",
        expectedTokens = listOf(
            *chars("&darr").toTypedArray()
        )
    )

    @Test
    fun `Named entity darr with a semicolon`() = assertTokenization(
        input = "&darr;",
        expectedTokens = listOf(
            char('↓')
        )
    )

    @Test
    fun `Bad named entity dash without a semicolon`() = assertTokenization(
        input = "&dash",
        expectedTokens = listOf(
            *chars("&dash").toTypedArray()
        )
    )

    @Test
    fun `Named entity dash with a semicolon`() = assertTokenization(
        input = "&dash;",
        expectedTokens = listOf(
            char('‐')
        )
    )

    @Test
    fun `Bad named entity dashv without a semicolon`() = assertTokenization(
        input = "&dashv",
        expectedTokens = listOf(
            *chars("&dashv").toTypedArray()
        )
    )

    @Test
    fun `Named entity dashv with a semicolon`() = assertTokenization(
        input = "&dashv;",
        expectedTokens = listOf(
            char('⊣')
        )
    )

    @Test
    fun `Bad named entity dbkarow without a semicolon`() = assertTokenization(
        input = "&dbkarow",
        expectedTokens = listOf(
            *chars("&dbkarow").toTypedArray()
        )
    )

    @Test
    fun `Named entity dbkarow with a semicolon`() = assertTokenization(
        input = "&dbkarow;",
        expectedTokens = listOf(
            char('⤏')
        )
    )

    @Test
    fun `Bad named entity dblac without a semicolon`() = assertTokenization(
        input = "&dblac",
        expectedTokens = listOf(
            *chars("&dblac").toTypedArray()
        )
    )

    @Test
    fun `Named entity dblac with a semicolon`() = assertTokenization(
        input = "&dblac;",
        expectedTokens = listOf(
            char('˝')
        )
    )

    @Test
    fun `Bad named entity dcaron without a semicolon`() = assertTokenization(
        input = "&dcaron",
        expectedTokens = listOf(
            *chars("&dcaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity dcaron with a semicolon`() = assertTokenization(
        input = "&dcaron;",
        expectedTokens = listOf(
            char('ď')
        )
    )

    @Test
    fun `Bad named entity dcy without a semicolon`() = assertTokenization(
        input = "&dcy",
        expectedTokens = listOf(
            *chars("&dcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity dcy with a semicolon`() = assertTokenization(
        input = "&dcy;",
        expectedTokens = listOf(
            char('д')
        )
    )

    @Test
    fun `Bad named entity dd without a semicolon`() = assertTokenization(
        input = "&dd",
        expectedTokens = listOf(
            *chars("&dd").toTypedArray()
        )
    )

    @Test
    fun `Named entity dd with a semicolon`() = assertTokenization(
        input = "&dd;",
        expectedTokens = listOf(
            char('ⅆ')
        )
    )

    @Test
    fun `Bad named entity ddagger without a semicolon`() = assertTokenization(
        input = "&ddagger",
        expectedTokens = listOf(
            *chars("&ddagger").toTypedArray()
        )
    )

    @Test
    fun `Named entity ddagger with a semicolon`() = assertTokenization(
        input = "&ddagger;",
        expectedTokens = listOf(
            char('‡')
        )
    )

    @Test
    fun `Bad named entity ddarr without a semicolon`() = assertTokenization(
        input = "&ddarr",
        expectedTokens = listOf(
            *chars("&ddarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity ddarr with a semicolon`() = assertTokenization(
        input = "&ddarr;",
        expectedTokens = listOf(
            char('⇊')
        )
    )

    @Test
    fun `Bad named entity ddotseq without a semicolon`() = assertTokenization(
        input = "&ddotseq",
        expectedTokens = listOf(
            *chars("&ddotseq").toTypedArray()
        )
    )

    @Test
    fun `Named entity ddotseq with a semicolon`() = assertTokenization(
        input = "&ddotseq;",
        expectedTokens = listOf(
            char('⩷')
        )
    )

    @Test
    fun `Named entity deg without a semicolon`() = assertTokenization(
        input = "&deg",
        expectedTokens = listOf(
            char('°')
        )
    )

    @Test
    fun `Named entity deg with a semicolon`() = assertTokenization(
        input = "&deg;",
        expectedTokens = listOf(
            char('°')
        )
    )

    @Test
    fun `Bad named entity delta without a semicolon`() = assertTokenization(
        input = "&delta",
        expectedTokens = listOf(
            *chars("&delta").toTypedArray()
        )
    )

    @Test
    fun `Named entity delta with a semicolon`() = assertTokenization(
        input = "&delta;",
        expectedTokens = listOf(
            char('δ')
        )
    )

    @Test
    fun `Bad named entity demptyv without a semicolon`() = assertTokenization(
        input = "&demptyv",
        expectedTokens = listOf(
            *chars("&demptyv").toTypedArray()
        )
    )

    @Test
    fun `Named entity demptyv with a semicolon`() = assertTokenization(
        input = "&demptyv;",
        expectedTokens = listOf(
            char('⦱')
        )
    )

    @Test
    fun `Bad named entity dfisht without a semicolon`() = assertTokenization(
        input = "&dfisht",
        expectedTokens = listOf(
            *chars("&dfisht").toTypedArray()
        )
    )

    @Test
    fun `Named entity dfisht with a semicolon`() = assertTokenization(
        input = "&dfisht;",
        expectedTokens = listOf(
            char('⥿')
        )
    )

    @Test
    fun `Bad named entity dfr without a semicolon`() = assertTokenization(
        input = "&dfr",
        expectedTokens = listOf(
            *chars("&dfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity dfr with a semicolon`() = assertTokenization(
        input = "&dfr;",
        expectedTokens = listOf(
            *chars("𝔡").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity dharl without a semicolon`() = assertTokenization(
        input = "&dharl",
        expectedTokens = listOf(
            *chars("&dharl").toTypedArray()
        )
    )

    @Test
    fun `Named entity dharl with a semicolon`() = assertTokenization(
        input = "&dharl;",
        expectedTokens = listOf(
            char('⇃')
        )
    )

    @Test
    fun `Bad named entity dharr without a semicolon`() = assertTokenization(
        input = "&dharr",
        expectedTokens = listOf(
            *chars("&dharr").toTypedArray()
        )
    )

    @Test
    fun `Named entity dharr with a semicolon`() = assertTokenization(
        input = "&dharr;",
        expectedTokens = listOf(
            char('⇂')
        )
    )

    @Test
    fun `Bad named entity diam without a semicolon`() = assertTokenization(
        input = "&diam",
        expectedTokens = listOf(
            *chars("&diam").toTypedArray()
        )
    )

    @Test
    fun `Named entity diam with a semicolon`() = assertTokenization(
        input = "&diam;",
        expectedTokens = listOf(
            char('⋄')
        )
    )

    @Test
    fun `Bad named entity diamond without a semicolon`() = assertTokenization(
        input = "&diamond",
        expectedTokens = listOf(
            *chars("&diamond").toTypedArray()
        )
    )

    @Test
    fun `Named entity diamond with a semicolon`() = assertTokenization(
        input = "&diamond;",
        expectedTokens = listOf(
            char('⋄')
        )
    )

    @Test
    fun `Bad named entity diamondsuit without a semicolon`() = assertTokenization(
        input = "&diamondsuit",
        expectedTokens = listOf(
            *chars("&diamondsuit").toTypedArray()
        )
    )

    @Test
    fun `Named entity diamondsuit with a semicolon`() = assertTokenization(
        input = "&diamondsuit;",
        expectedTokens = listOf(
            char('♦')
        )
    )

    @Test
    fun `Bad named entity diams without a semicolon`() = assertTokenization(
        input = "&diams",
        expectedTokens = listOf(
            *chars("&diams").toTypedArray()
        )
    )

    @Test
    fun `Named entity diams with a semicolon`() = assertTokenization(
        input = "&diams;",
        expectedTokens = listOf(
            char('♦')
        )
    )

    @Test
    fun `Bad named entity die without a semicolon`() = assertTokenization(
        input = "&die",
        expectedTokens = listOf(
            *chars("&die").toTypedArray()
        )
    )

    @Test
    fun `Named entity die with a semicolon`() = assertTokenization(
        input = "&die;",
        expectedTokens = listOf(
            char('¨')
        )
    )

    @Test
    fun `Bad named entity digamma without a semicolon`() = assertTokenization(
        input = "&digamma",
        expectedTokens = listOf(
            *chars("&digamma").toTypedArray()
        )
    )

    @Test
    fun `Named entity digamma with a semicolon`() = assertTokenization(
        input = "&digamma;",
        expectedTokens = listOf(
            char('ϝ')
        )
    )

    @Test
    fun `Bad named entity disin without a semicolon`() = assertTokenization(
        input = "&disin",
        expectedTokens = listOf(
            *chars("&disin").toTypedArray()
        )
    )

    @Test
    fun `Named entity disin with a semicolon`() = assertTokenization(
        input = "&disin;",
        expectedTokens = listOf(
            char('⋲')
        )
    )

    @Test
    fun `Bad named entity div without a semicolon`() = assertTokenization(
        input = "&div",
        expectedTokens = listOf(
            *chars("&div").toTypedArray()
        )
    )

    @Test
    fun `Named entity div with a semicolon`() = assertTokenization(
        input = "&div;",
        expectedTokens = listOf(
            char('÷')
        )
    )

    @Test
    fun `Named entity divide without a semicolon`() = assertTokenization(
        input = "&divide",
        expectedTokens = listOf(
            char('÷')
        )
    )

    @Test
    fun `Named entity divide with a semicolon`() = assertTokenization(
        input = "&divide;",
        expectedTokens = listOf(
            char('÷')
        )
    )

    @Test
    fun `Named entity divideontimes with a semicolon`() = assertTokenization(
        input = "&divideontimes;",
        expectedTokens = listOf(
            char('⋇')
        )
    )

    @Test
    fun `Bad named entity divonx without a semicolon`() = assertTokenization(
        input = "&divonx",
        expectedTokens = listOf(
            *chars("&divonx").toTypedArray()
        )
    )

    @Test
    fun `Named entity divonx with a semicolon`() = assertTokenization(
        input = "&divonx;",
        expectedTokens = listOf(
            char('⋇')
        )
    )

    @Test
    fun `Bad named entity djcy without a semicolon`() = assertTokenization(
        input = "&djcy",
        expectedTokens = listOf(
            *chars("&djcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity djcy with a semicolon`() = assertTokenization(
        input = "&djcy;",
        expectedTokens = listOf(
            char('ђ')
        )
    )

    @Test
    fun `Bad named entity dlcorn without a semicolon`() = assertTokenization(
        input = "&dlcorn",
        expectedTokens = listOf(
            *chars("&dlcorn").toTypedArray()
        )
    )

    @Test
    fun `Named entity dlcorn with a semicolon`() = assertTokenization(
        input = "&dlcorn;",
        expectedTokens = listOf(
            char('⌞')
        )
    )

    @Test
    fun `Bad named entity dlcrop without a semicolon`() = assertTokenization(
        input = "&dlcrop",
        expectedTokens = listOf(
            *chars("&dlcrop").toTypedArray()
        )
    )

    @Test
    fun `Named entity dlcrop with a semicolon`() = assertTokenization(
        input = "&dlcrop;",
        expectedTokens = listOf(
            char('⌍')
        )
    )

    @Test
    fun `Bad named entity dollar without a semicolon`() = assertTokenization(
        input = "&dollar",
        expectedTokens = listOf(
            *chars("&dollar").toTypedArray()
        )
    )

    @Test
    fun `Named entity dollar with a semicolon`() = assertTokenization(
        input = "&dollar;",
        expectedTokens = listOf(
            char('$')
        )
    )

    @Test
    fun `Bad named entity dopf without a semicolon`() = assertTokenization(
        input = "&dopf",
        expectedTokens = listOf(
            *chars("&dopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity dopf with a semicolon`() = assertTokenization(
        input = "&dopf;",
        expectedTokens = listOf(
            *chars("𝕕").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity dot without a semicolon`() = assertTokenization(
        input = "&dot",
        expectedTokens = listOf(
            *chars("&dot").toTypedArray()
        )
    )

    @Test
    fun `Named entity dot with a semicolon`() = assertTokenization(
        input = "&dot;",
        expectedTokens = listOf(
            char('˙')
        )
    )

    @Test
    fun `Bad named entity doteq without a semicolon`() = assertTokenization(
        input = "&doteq",
        expectedTokens = listOf(
            *chars("&doteq").toTypedArray()
        )
    )

    @Test
    fun `Named entity doteq with a semicolon`() = assertTokenization(
        input = "&doteq;",
        expectedTokens = listOf(
            char('≐')
        )
    )

    @Test
    fun `Bad named entity doteqdot without a semicolon`() = assertTokenization(
        input = "&doteqdot",
        expectedTokens = listOf(
            *chars("&doteqdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity doteqdot with a semicolon`() = assertTokenization(
        input = "&doteqdot;",
        expectedTokens = listOf(
            char('≑')
        )
    )

    @Test
    fun `Bad named entity dotminus without a semicolon`() = assertTokenization(
        input = "&dotminus",
        expectedTokens = listOf(
            *chars("&dotminus").toTypedArray()
        )
    )

    @Test
    fun `Named entity dotminus with a semicolon`() = assertTokenization(
        input = "&dotminus;",
        expectedTokens = listOf(
            char('∸')
        )
    )

    @Test
    fun `Bad named entity dotplus without a semicolon`() = assertTokenization(
        input = "&dotplus",
        expectedTokens = listOf(
            *chars("&dotplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity dotplus with a semicolon`() = assertTokenization(
        input = "&dotplus;",
        expectedTokens = listOf(
            char('∔')
        )
    )

    @Test
    fun `Bad named entity dotsquare without a semicolon`() = assertTokenization(
        input = "&dotsquare",
        expectedTokens = listOf(
            *chars("&dotsquare").toTypedArray()
        )
    )

    @Test
    fun `Named entity dotsquare with a semicolon`() = assertTokenization(
        input = "&dotsquare;",
        expectedTokens = listOf(
            char('⊡')
        )
    )

    @Test
    fun `Bad named entity doublebarwedge without a semicolon`() = assertTokenization(
        input = "&doublebarwedge",
        expectedTokens = listOf(
            *chars("&doublebarwedge").toTypedArray()
        )
    )

    @Test
    fun `Named entity doublebarwedge with a semicolon`() = assertTokenization(
        input = "&doublebarwedge;",
        expectedTokens = listOf(
            char('⌆')
        )
    )

    @Test
    fun `Bad named entity downarrow without a semicolon`() = assertTokenization(
        input = "&downarrow",
        expectedTokens = listOf(
            *chars("&downarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity downarrow with a semicolon`() = assertTokenization(
        input = "&downarrow;",
        expectedTokens = listOf(
            char('↓')
        )
    )

    @Test
    fun `Bad named entity downdownarrows without a semicolon`() = assertTokenization(
        input = "&downdownarrows",
        expectedTokens = listOf(
            *chars("&downdownarrows").toTypedArray()
        )
    )

    @Test
    fun `Named entity downdownarrows with a semicolon`() = assertTokenization(
        input = "&downdownarrows;",
        expectedTokens = listOf(
            char('⇊')
        )
    )

    @Test
    fun `Bad named entity downharpoonleft without a semicolon`() = assertTokenization(
        input = "&downharpoonleft",
        expectedTokens = listOf(
            *chars("&downharpoonleft").toTypedArray()
        )
    )

    @Test
    fun `Named entity downharpoonleft with a semicolon`() = assertTokenization(
        input = "&downharpoonleft;",
        expectedTokens = listOf(
            char('⇃')
        )
    )

    @Test
    fun `Bad named entity downharpoonright without a semicolon`() = assertTokenization(
        input = "&downharpoonright",
        expectedTokens = listOf(
            *chars("&downharpoonright").toTypedArray()
        )
    )

    @Test
    fun `Named entity downharpoonright with a semicolon`() = assertTokenization(
        input = "&downharpoonright;",
        expectedTokens = listOf(
            char('⇂')
        )
    )

    @Test
    fun `Bad named entity drbkarow without a semicolon`() = assertTokenization(
        input = "&drbkarow",
        expectedTokens = listOf(
            *chars("&drbkarow").toTypedArray()
        )
    )

    @Test
    fun `Named entity drbkarow with a semicolon`() = assertTokenization(
        input = "&drbkarow;",
        expectedTokens = listOf(
            char('⤐')
        )
    )

    @Test
    fun `Bad named entity drcorn without a semicolon`() = assertTokenization(
        input = "&drcorn",
        expectedTokens = listOf(
            *chars("&drcorn").toTypedArray()
        )
    )

    @Test
    fun `Named entity drcorn with a semicolon`() = assertTokenization(
        input = "&drcorn;",
        expectedTokens = listOf(
            char('⌟')
        )
    )

    @Test
    fun `Bad named entity drcrop without a semicolon`() = assertTokenization(
        input = "&drcrop",
        expectedTokens = listOf(
            *chars("&drcrop").toTypedArray()
        )
    )

    @Test
    fun `Named entity drcrop with a semicolon`() = assertTokenization(
        input = "&drcrop;",
        expectedTokens = listOf(
            char('⌌')
        )
    )

    @Test
    fun `Bad named entity dscr without a semicolon`() = assertTokenization(
        input = "&dscr",
        expectedTokens = listOf(
            *chars("&dscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity dscr with a semicolon`() = assertTokenization(
        input = "&dscr;",
        expectedTokens = listOf(
            *chars("𝒹").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity dscy without a semicolon`() = assertTokenization(
        input = "&dscy",
        expectedTokens = listOf(
            *chars("&dscy").toTypedArray()
        )
    )

    @Test
    fun `Named entity dscy with a semicolon`() = assertTokenization(
        input = "&dscy;",
        expectedTokens = listOf(
            char('ѕ')
        )
    )

    @Test
    fun `Bad named entity dsol without a semicolon`() = assertTokenization(
        input = "&dsol",
        expectedTokens = listOf(
            *chars("&dsol").toTypedArray()
        )
    )

    @Test
    fun `Named entity dsol with a semicolon`() = assertTokenization(
        input = "&dsol;",
        expectedTokens = listOf(
            char('⧶')
        )
    )

    @Test
    fun `Bad named entity dstrok without a semicolon`() = assertTokenization(
        input = "&dstrok",
        expectedTokens = listOf(
            *chars("&dstrok").toTypedArray()
        )
    )

    @Test
    fun `Named entity dstrok with a semicolon`() = assertTokenization(
        input = "&dstrok;",
        expectedTokens = listOf(
            char('đ')
        )
    )

    @Test
    fun `Bad named entity dtdot without a semicolon`() = assertTokenization(
        input = "&dtdot",
        expectedTokens = listOf(
            *chars("&dtdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity dtdot with a semicolon`() = assertTokenization(
        input = "&dtdot;",
        expectedTokens = listOf(
            char('⋱')
        )
    )

    @Test
    fun `Bad named entity dtri without a semicolon`() = assertTokenization(
        input = "&dtri",
        expectedTokens = listOf(
            *chars("&dtri").toTypedArray()
        )
    )

    @Test
    fun `Named entity dtri with a semicolon`() = assertTokenization(
        input = "&dtri;",
        expectedTokens = listOf(
            char('▿')
        )
    )

    @Test
    fun `Bad named entity dtrif without a semicolon`() = assertTokenization(
        input = "&dtrif",
        expectedTokens = listOf(
            *chars("&dtrif").toTypedArray()
        )
    )

    @Test
    fun `Named entity dtrif with a semicolon`() = assertTokenization(
        input = "&dtrif;",
        expectedTokens = listOf(
            char('▾')
        )
    )

    @Test
    fun `Bad named entity duarr without a semicolon`() = assertTokenization(
        input = "&duarr",
        expectedTokens = listOf(
            *chars("&duarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity duarr with a semicolon`() = assertTokenization(
        input = "&duarr;",
        expectedTokens = listOf(
            char('⇵')
        )
    )

    @Test
    fun `Bad named entity duhar without a semicolon`() = assertTokenization(
        input = "&duhar",
        expectedTokens = listOf(
            *chars("&duhar").toTypedArray()
        )
    )

    @Test
    fun `Named entity duhar with a semicolon`() = assertTokenization(
        input = "&duhar;",
        expectedTokens = listOf(
            char('⥯')
        )
    )

    @Test
    fun `Bad named entity dwangle without a semicolon`() = assertTokenization(
        input = "&dwangle",
        expectedTokens = listOf(
            *chars("&dwangle").toTypedArray()
        )
    )

    @Test
    fun `Named entity dwangle with a semicolon`() = assertTokenization(
        input = "&dwangle;",
        expectedTokens = listOf(
            char('⦦')
        )
    )

    @Test
    fun `Bad named entity dzcy without a semicolon`() = assertTokenization(
        input = "&dzcy",
        expectedTokens = listOf(
            *chars("&dzcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity dzcy with a semicolon`() = assertTokenization(
        input = "&dzcy;",
        expectedTokens = listOf(
            char('џ')
        )
    )

    @Test
    fun `Bad named entity dzigrarr without a semicolon`() = assertTokenization(
        input = "&dzigrarr",
        expectedTokens = listOf(
            *chars("&dzigrarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity dzigrarr with a semicolon`() = assertTokenization(
        input = "&dzigrarr;",
        expectedTokens = listOf(
            char('⟿')
        )
    )

    @Test
    fun `Bad named entity eDDot without a semicolon`() = assertTokenization(
        input = "&eDDot",
        expectedTokens = listOf(
            *chars("&eDDot").toTypedArray()
        )
    )

    @Test
    fun `Named entity eDDot with a semicolon`() = assertTokenization(
        input = "&eDDot;",
        expectedTokens = listOf(
            char('⩷')
        )
    )

    @Test
    fun `Bad named entity eDot without a semicolon`() = assertTokenization(
        input = "&eDot",
        expectedTokens = listOf(
            *chars("&eDot").toTypedArray()
        )
    )

    @Test
    fun `Named entity eDot with a semicolon`() = assertTokenization(
        input = "&eDot;",
        expectedTokens = listOf(
            char('≑')
        )
    )

    @Test
    fun `Named entity eacute without a semicolon`() = assertTokenization(
        input = "&eacute",
        expectedTokens = listOf(
            char('é')
        )
    )

    @Test
    fun `Named entity eacute with a semicolon`() = assertTokenization(
        input = "&eacute;",
        expectedTokens = listOf(
            char('é')
        )
    )

    @Test
    fun `Bad named entity easter without a semicolon`() = assertTokenization(
        input = "&easter",
        expectedTokens = listOf(
            *chars("&easter").toTypedArray()
        )
    )

    @Test
    fun `Named entity easter with a semicolon`() = assertTokenization(
        input = "&easter;",
        expectedTokens = listOf(
            char('⩮')
        )
    )

    @Test
    fun `Bad named entity ecaron without a semicolon`() = assertTokenization(
        input = "&ecaron",
        expectedTokens = listOf(
            *chars("&ecaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity ecaron with a semicolon`() = assertTokenization(
        input = "&ecaron;",
        expectedTokens = listOf(
            char('ě')
        )
    )

    @Test
    fun `Bad named entity ecir without a semicolon`() = assertTokenization(
        input = "&ecir",
        expectedTokens = listOf(
            *chars("&ecir").toTypedArray()
        )
    )

    @Test
    fun `Named entity ecir with a semicolon`() = assertTokenization(
        input = "&ecir;",
        expectedTokens = listOf(
            char('≖')
        )
    )

    @Test
    fun `Named entity ecirc without a semicolon`() = assertTokenization(
        input = "&ecirc",
        expectedTokens = listOf(
            char('ê')
        )
    )

    @Test
    fun `Named entity ecirc with a semicolon`() = assertTokenization(
        input = "&ecirc;",
        expectedTokens = listOf(
            char('ê')
        )
    )

    @Test
    fun `Bad named entity ecolon without a semicolon`() = assertTokenization(
        input = "&ecolon",
        expectedTokens = listOf(
            *chars("&ecolon").toTypedArray()
        )
    )

    @Test
    fun `Named entity ecolon with a semicolon`() = assertTokenization(
        input = "&ecolon;",
        expectedTokens = listOf(
            char('≕')
        )
    )

    @Test
    fun `Bad named entity ecy without a semicolon`() = assertTokenization(
        input = "&ecy",
        expectedTokens = listOf(
            *chars("&ecy").toTypedArray()
        )
    )

    @Test
    fun `Named entity ecy with a semicolon`() = assertTokenization(
        input = "&ecy;",
        expectedTokens = listOf(
            char('э')
        )
    )

    @Test
    fun `Bad named entity edot without a semicolon`() = assertTokenization(
        input = "&edot",
        expectedTokens = listOf(
            *chars("&edot").toTypedArray()
        )
    )

    @Test
    fun `Named entity edot with a semicolon`() = assertTokenization(
        input = "&edot;",
        expectedTokens = listOf(
            char('ė')
        )
    )

    @Test
    fun `Bad named entity ee without a semicolon`() = assertTokenization(
        input = "&ee",
        expectedTokens = listOf(
            *chars("&ee").toTypedArray()
        )
    )

    @Test
    fun `Named entity ee with a semicolon`() = assertTokenization(
        input = "&ee;",
        expectedTokens = listOf(
            char('ⅇ')
        )
    )

    @Test
    fun `Bad named entity efDot without a semicolon`() = assertTokenization(
        input = "&efDot",
        expectedTokens = listOf(
            *chars("&efDot").toTypedArray()
        )
    )

    @Test
    fun `Named entity efDot with a semicolon`() = assertTokenization(
        input = "&efDot;",
        expectedTokens = listOf(
            char('≒')
        )
    )

    @Test
    fun `Bad named entity efr without a semicolon`() = assertTokenization(
        input = "&efr",
        expectedTokens = listOf(
            *chars("&efr").toTypedArray()
        )
    )

    @Test
    fun `Named entity efr with a semicolon`() = assertTokenization(
        input = "&efr;",
        expectedTokens = listOf(
            *chars("𝔢").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity eg without a semicolon`() = assertTokenization(
        input = "&eg",
        expectedTokens = listOf(
            *chars("&eg").toTypedArray()
        )
    )

    @Test
    fun `Named entity eg with a semicolon`() = assertTokenization(
        input = "&eg;",
        expectedTokens = listOf(
            char('⪚')
        )
    )

    @Test
    fun `Named entity egrave without a semicolon`() = assertTokenization(
        input = "&egrave",
        expectedTokens = listOf(
            char('è')
        )
    )

    @Test
    fun `Named entity egrave with a semicolon`() = assertTokenization(
        input = "&egrave;",
        expectedTokens = listOf(
            char('è')
        )
    )

    @Test
    fun `Bad named entity egs without a semicolon`() = assertTokenization(
        input = "&egs",
        expectedTokens = listOf(
            *chars("&egs").toTypedArray()
        )
    )

    @Test
    fun `Named entity egs with a semicolon`() = assertTokenization(
        input = "&egs;",
        expectedTokens = listOf(
            char('⪖')
        )
    )

    @Test
    fun `Bad named entity egsdot without a semicolon`() = assertTokenization(
        input = "&egsdot",
        expectedTokens = listOf(
            *chars("&egsdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity egsdot with a semicolon`() = assertTokenization(
        input = "&egsdot;",
        expectedTokens = listOf(
            char('⪘')
        )
    )

    @Test
    fun `Bad named entity el without a semicolon`() = assertTokenization(
        input = "&el",
        expectedTokens = listOf(
            *chars("&el").toTypedArray()
        )
    )

    @Test
    fun `Named entity el with a semicolon`() = assertTokenization(
        input = "&el;",
        expectedTokens = listOf(
            char('⪙')
        )
    )

    @Test
    fun `Bad named entity elinters without a semicolon`() = assertTokenization(
        input = "&elinters",
        expectedTokens = listOf(
            *chars("&elinters").toTypedArray()
        )
    )

    @Test
    fun `Named entity elinters with a semicolon`() = assertTokenization(
        input = "&elinters;",
        expectedTokens = listOf(
            char('⏧')
        )
    )

    @Test
    fun `Bad named entity ell without a semicolon`() = assertTokenization(
        input = "&ell",
        expectedTokens = listOf(
            *chars("&ell").toTypedArray()
        )
    )

    @Test
    fun `Named entity ell with a semicolon`() = assertTokenization(
        input = "&ell;",
        expectedTokens = listOf(
            char('ℓ')
        )
    )

    @Test
    fun `Bad named entity els without a semicolon`() = assertTokenization(
        input = "&els",
        expectedTokens = listOf(
            *chars("&els").toTypedArray()
        )
    )

    @Test
    fun `Named entity els with a semicolon`() = assertTokenization(
        input = "&els;",
        expectedTokens = listOf(
            char('⪕')
        )
    )

    @Test
    fun `Bad named entity elsdot without a semicolon`() = assertTokenization(
        input = "&elsdot",
        expectedTokens = listOf(
            *chars("&elsdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity elsdot with a semicolon`() = assertTokenization(
        input = "&elsdot;",
        expectedTokens = listOf(
            char('⪗')
        )
    )

    @Test
    fun `Bad named entity emacr without a semicolon`() = assertTokenization(
        input = "&emacr",
        expectedTokens = listOf(
            *chars("&emacr").toTypedArray()
        )
    )

    @Test
    fun `Named entity emacr with a semicolon`() = assertTokenization(
        input = "&emacr;",
        expectedTokens = listOf(
            char('ē')
        )
    )

    @Test
    fun `Bad named entity empty without a semicolon`() = assertTokenization(
        input = "&empty",
        expectedTokens = listOf(
            *chars("&empty").toTypedArray()
        )
    )

    @Test
    fun `Named entity empty with a semicolon`() = assertTokenization(
        input = "&empty;",
        expectedTokens = listOf(
            char('∅')
        )
    )

    @Test
    fun `Bad named entity emptyset without a semicolon`() = assertTokenization(
        input = "&emptyset",
        expectedTokens = listOf(
            *chars("&emptyset").toTypedArray()
        )
    )

    @Test
    fun `Named entity emptyset with a semicolon`() = assertTokenization(
        input = "&emptyset;",
        expectedTokens = listOf(
            char('∅')
        )
    )

    @Test
    fun `Bad named entity emptyv without a semicolon`() = assertTokenization(
        input = "&emptyv",
        expectedTokens = listOf(
            *chars("&emptyv").toTypedArray()
        )
    )

    @Test
    fun `Named entity emptyv with a semicolon`() = assertTokenization(
        input = "&emptyv;",
        expectedTokens = listOf(
            char('∅')
        )
    )

    @Test
    fun `Bad named entity emsp without a semicolon`() = assertTokenization(
        input = "&emsp",
        expectedTokens = listOf(
            *chars("&emsp").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity emsp13 without a semicolon`() = assertTokenization(
        input = "&emsp13",
        expectedTokens = listOf(
            *chars("&emsp13").toTypedArray()
        )
    )

    @Test
    fun `Named entity emsp13 with a semicolon`() = assertTokenization(
        input = "&emsp13;",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Bad named entity emsp14 without a semicolon`() = assertTokenization(
        input = "&emsp14",
        expectedTokens = listOf(
            *chars("&emsp14").toTypedArray()
        )
    )

    @Test
    fun `Named entity emsp14 with a semicolon`() = assertTokenization(
        input = "&emsp14;",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Named entity emsp with a semicolon`() = assertTokenization(
        input = "&emsp;",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Bad named entity eng without a semicolon`() = assertTokenization(
        input = "&eng",
        expectedTokens = listOf(
            *chars("&eng").toTypedArray()
        )
    )

    @Test
    fun `Named entity eng with a semicolon`() = assertTokenization(
        input = "&eng;",
        expectedTokens = listOf(
            char('ŋ')
        )
    )

    @Test
    fun `Bad named entity ensp without a semicolon`() = assertTokenization(
        input = "&ensp",
        expectedTokens = listOf(
            *chars("&ensp").toTypedArray()
        )
    )

    @Test
    fun `Named entity ensp with a semicolon`() = assertTokenization(
        input = "&ensp;",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Bad named entity eogon without a semicolon`() = assertTokenization(
        input = "&eogon",
        expectedTokens = listOf(
            *chars("&eogon").toTypedArray()
        )
    )

    @Test
    fun `Named entity eogon with a semicolon`() = assertTokenization(
        input = "&eogon;",
        expectedTokens = listOf(
            char('ę')
        )
    )

    @Test
    fun `Bad named entity eopf without a semicolon`() = assertTokenization(
        input = "&eopf",
        expectedTokens = listOf(
            *chars("&eopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity eopf with a semicolon`() = assertTokenization(
        input = "&eopf;",
        expectedTokens = listOf(
            *chars("𝕖").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity epar without a semicolon`() = assertTokenization(
        input = "&epar",
        expectedTokens = listOf(
            *chars("&epar").toTypedArray()
        )
    )

    @Test
    fun `Named entity epar with a semicolon`() = assertTokenization(
        input = "&epar;",
        expectedTokens = listOf(
            char('⋕')
        )
    )

    @Test
    fun `Bad named entity eparsl without a semicolon`() = assertTokenization(
        input = "&eparsl",
        expectedTokens = listOf(
            *chars("&eparsl").toTypedArray()
        )
    )

    @Test
    fun `Named entity eparsl with a semicolon`() = assertTokenization(
        input = "&eparsl;",
        expectedTokens = listOf(
            char('⧣')
        )
    )

    @Test
    fun `Bad named entity eplus without a semicolon`() = assertTokenization(
        input = "&eplus",
        expectedTokens = listOf(
            *chars("&eplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity eplus with a semicolon`() = assertTokenization(
        input = "&eplus;",
        expectedTokens = listOf(
            char('⩱')
        )
    )

    @Test
    fun `Bad named entity epsi without a semicolon`() = assertTokenization(
        input = "&epsi",
        expectedTokens = listOf(
            *chars("&epsi").toTypedArray()
        )
    )

    @Test
    fun `Named entity epsi with a semicolon`() = assertTokenization(
        input = "&epsi;",
        expectedTokens = listOf(
            char('ε')
        )
    )

    @Test
    fun `Bad named entity epsilon without a semicolon`() = assertTokenization(
        input = "&epsilon",
        expectedTokens = listOf(
            *chars("&epsilon").toTypedArray()
        )
    )

    @Test
    fun `Named entity epsilon with a semicolon`() = assertTokenization(
        input = "&epsilon;",
        expectedTokens = listOf(
            char('ε')
        )
    )

    @Test
    fun `Bad named entity epsiv without a semicolon`() = assertTokenization(
        input = "&epsiv",
        expectedTokens = listOf(
            *chars("&epsiv").toTypedArray()
        )
    )

    @Test
    fun `Named entity epsiv with a semicolon`() = assertTokenization(
        input = "&epsiv;",
        expectedTokens = listOf(
            char('ϵ')
        )
    )

    @Test
    fun `Bad named entity eqcirc without a semicolon`() = assertTokenization(
        input = "&eqcirc",
        expectedTokens = listOf(
            *chars("&eqcirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity eqcirc with a semicolon`() = assertTokenization(
        input = "&eqcirc;",
        expectedTokens = listOf(
            char('≖')
        )
    )

    @Test
    fun `Bad named entity eqcolon without a semicolon`() = assertTokenization(
        input = "&eqcolon",
        expectedTokens = listOf(
            *chars("&eqcolon").toTypedArray()
        )
    )

    @Test
    fun `Named entity eqcolon with a semicolon`() = assertTokenization(
        input = "&eqcolon;",
        expectedTokens = listOf(
            char('≕')
        )
    )

    @Test
    fun `Bad named entity eqsim without a semicolon`() = assertTokenization(
        input = "&eqsim",
        expectedTokens = listOf(
            *chars("&eqsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity eqsim with a semicolon`() = assertTokenization(
        input = "&eqsim;",
        expectedTokens = listOf(
            char('≂')
        )
    )

    @Test
    fun `Bad named entity eqslantgtr without a semicolon`() = assertTokenization(
        input = "&eqslantgtr",
        expectedTokens = listOf(
            *chars("&eqslantgtr").toTypedArray()
        )
    )

    @Test
    fun `Named entity eqslantgtr with a semicolon`() = assertTokenization(
        input = "&eqslantgtr;",
        expectedTokens = listOf(
            char('⪖')
        )
    )

    @Test
    fun `Bad named entity eqslantless without a semicolon`() = assertTokenization(
        input = "&eqslantless",
        expectedTokens = listOf(
            *chars("&eqslantless").toTypedArray()
        )
    )

    @Test
    fun `Named entity eqslantless with a semicolon`() = assertTokenization(
        input = "&eqslantless;",
        expectedTokens = listOf(
            char('⪕')
        )
    )

    @Test
    fun `Bad named entity equals without a semicolon`() = assertTokenization(
        input = "&equals",
        expectedTokens = listOf(
            *chars("&equals").toTypedArray()
        )
    )

    @Test
    fun `Named entity equals with a semicolon`() = assertTokenization(
        input = "&equals;",
        expectedTokens = listOf(
            char('=')
        )
    )

    @Test
    fun `Bad named entity equest without a semicolon`() = assertTokenization(
        input = "&equest",
        expectedTokens = listOf(
            *chars("&equest").toTypedArray()
        )
    )

    @Test
    fun `Named entity equest with a semicolon`() = assertTokenization(
        input = "&equest;",
        expectedTokens = listOf(
            char('≟')
        )
    )

    @Test
    fun `Bad named entity equiv without a semicolon`() = assertTokenization(
        input = "&equiv",
        expectedTokens = listOf(
            *chars("&equiv").toTypedArray()
        )
    )

    @Test
    fun `Named entity equiv with a semicolon`() = assertTokenization(
        input = "&equiv;",
        expectedTokens = listOf(
            char('≡')
        )
    )

    @Test
    fun `Bad named entity equivDD without a semicolon`() = assertTokenization(
        input = "&equivDD",
        expectedTokens = listOf(
            *chars("&equivDD").toTypedArray()
        )
    )

    @Test
    fun `Named entity equivDD with a semicolon`() = assertTokenization(
        input = "&equivDD;",
        expectedTokens = listOf(
            char('⩸')
        )
    )

    @Test
    fun `Bad named entity eqvparsl without a semicolon`() = assertTokenization(
        input = "&eqvparsl",
        expectedTokens = listOf(
            *chars("&eqvparsl").toTypedArray()
        )
    )

    @Test
    fun `Named entity eqvparsl with a semicolon`() = assertTokenization(
        input = "&eqvparsl;",
        expectedTokens = listOf(
            char('⧥')
        )
    )

    @Test
    fun `Bad named entity erDot without a semicolon`() = assertTokenization(
        input = "&erDot",
        expectedTokens = listOf(
            *chars("&erDot").toTypedArray()
        )
    )

    @Test
    fun `Named entity erDot with a semicolon`() = assertTokenization(
        input = "&erDot;",
        expectedTokens = listOf(
            char('≓')
        )
    )

    @Test
    fun `Bad named entity erarr without a semicolon`() = assertTokenization(
        input = "&erarr",
        expectedTokens = listOf(
            *chars("&erarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity erarr with a semicolon`() = assertTokenization(
        input = "&erarr;",
        expectedTokens = listOf(
            char('⥱')
        )
    )

    @Test
    fun `Bad named entity escr without a semicolon`() = assertTokenization(
        input = "&escr",
        expectedTokens = listOf(
            *chars("&escr").toTypedArray()
        )
    )

    @Test
    fun `Named entity escr with a semicolon`() = assertTokenization(
        input = "&escr;",
        expectedTokens = listOf(
            char('ℯ')
        )
    )

    @Test
    fun `Bad named entity esdot without a semicolon`() = assertTokenization(
        input = "&esdot",
        expectedTokens = listOf(
            *chars("&esdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity esdot with a semicolon`() = assertTokenization(
        input = "&esdot;",
        expectedTokens = listOf(
            char('≐')
        )
    )

    @Test
    fun `Bad named entity esim without a semicolon`() = assertTokenization(
        input = "&esim",
        expectedTokens = listOf(
            *chars("&esim").toTypedArray()
        )
    )

    @Test
    fun `Named entity esim with a semicolon`() = assertTokenization(
        input = "&esim;",
        expectedTokens = listOf(
            char('≂')
        )
    )

    @Test
    fun `Bad named entity eta without a semicolon`() = assertTokenization(
        input = "&eta",
        expectedTokens = listOf(
            *chars("&eta").toTypedArray()
        )
    )

    @Test
    fun `Named entity eta with a semicolon`() = assertTokenization(
        input = "&eta;",
        expectedTokens = listOf(
            char('η')
        )
    )

    @Test
    fun `Named entity eth without a semicolon`() = assertTokenization(
        input = "&eth",
        expectedTokens = listOf(
            char('ð')
        )
    )

    @Test
    fun `Named entity eth with a semicolon`() = assertTokenization(
        input = "&eth;",
        expectedTokens = listOf(
            char('ð')
        )
    )

    @Test
    fun `Named entity euml without a semicolon`() = assertTokenization(
        input = "&euml",
        expectedTokens = listOf(
            char('ë')
        )
    )

    @Test
    fun `Named entity euml with a semicolon`() = assertTokenization(
        input = "&euml;",
        expectedTokens = listOf(
            char('ë')
        )
    )

    @Test
    fun `Bad named entity euro without a semicolon`() = assertTokenization(
        input = "&euro",
        expectedTokens = listOf(
            *chars("&euro").toTypedArray()
        )
    )

    @Test
    fun `Named entity euro with a semicolon`() = assertTokenization(
        input = "&euro;",
        expectedTokens = listOf(
            char('€')
        )
    )

    @Test
    fun `Bad named entity excl without a semicolon`() = assertTokenization(
        input = "&excl",
        expectedTokens = listOf(
            *chars("&excl").toTypedArray()
        )
    )

    @Test
    fun `Named entity excl with a semicolon`() = assertTokenization(
        input = "&excl;",
        expectedTokens = listOf(
            char('!')
        )
    )

    @Test
    fun `Bad named entity exist without a semicolon`() = assertTokenization(
        input = "&exist",
        expectedTokens = listOf(
            *chars("&exist").toTypedArray()
        )
    )

    @Test
    fun `Named entity exist with a semicolon`() = assertTokenization(
        input = "&exist;",
        expectedTokens = listOf(
            char('∃')
        )
    )

    @Test
    fun `Bad named entity expectation without a semicolon`() = assertTokenization(
        input = "&expectation",
        expectedTokens = listOf(
            *chars("&expectation").toTypedArray()
        )
    )

    @Test
    fun `Named entity expectation with a semicolon`() = assertTokenization(
        input = "&expectation;",
        expectedTokens = listOf(
            char('ℰ')
        )
    )

    @Test
    fun `Bad named entity exponentiale without a semicolon`() = assertTokenization(
        input = "&exponentiale",
        expectedTokens = listOf(
            *chars("&exponentiale").toTypedArray()
        )
    )

    @Test
    fun `Named entity exponentiale with a semicolon`() = assertTokenization(
        input = "&exponentiale;",
        expectedTokens = listOf(
            char('ⅇ')
        )
    )

    @Test
    fun `Bad named entity fallingdotseq without a semicolon`() = assertTokenization(
        input = "&fallingdotseq",
        expectedTokens = listOf(
            *chars("&fallingdotseq").toTypedArray()
        )
    )

    @Test
    fun `Named entity fallingdotseq with a semicolon`() = assertTokenization(
        input = "&fallingdotseq;",
        expectedTokens = listOf(
            char('≒')
        )
    )

    @Test
    fun `Bad named entity fcy without a semicolon`() = assertTokenization(
        input = "&fcy",
        expectedTokens = listOf(
            *chars("&fcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity fcy with a semicolon`() = assertTokenization(
        input = "&fcy;",
        expectedTokens = listOf(
            char('ф')
        )
    )

    @Test
    fun `Bad named entity female without a semicolon`() = assertTokenization(
        input = "&female",
        expectedTokens = listOf(
            *chars("&female").toTypedArray()
        )
    )

    @Test
    fun `Named entity female with a semicolon`() = assertTokenization(
        input = "&female;",
        expectedTokens = listOf(
            char('♀')
        )
    )

    @Test
    fun `Bad named entity ffilig without a semicolon`() = assertTokenization(
        input = "&ffilig",
        expectedTokens = listOf(
            *chars("&ffilig").toTypedArray()
        )
    )

    @Test
    fun `Named entity ffilig with a semicolon`() = assertTokenization(
        input = "&ffilig;",
        expectedTokens = listOf(
            char('ﬃ')
        )
    )

    @Test
    fun `Bad named entity fflig without a semicolon`() = assertTokenization(
        input = "&fflig",
        expectedTokens = listOf(
            *chars("&fflig").toTypedArray()
        )
    )

    @Test
    fun `Named entity fflig with a semicolon`() = assertTokenization(
        input = "&fflig;",
        expectedTokens = listOf(
            char('ﬀ')
        )
    )

    @Test
    fun `Bad named entity ffllig without a semicolon`() = assertTokenization(
        input = "&ffllig",
        expectedTokens = listOf(
            *chars("&ffllig").toTypedArray()
        )
    )

    @Test
    fun `Named entity ffllig with a semicolon`() = assertTokenization(
        input = "&ffllig;",
        expectedTokens = listOf(
            char('ﬄ')
        )
    )

    @Test
    fun `Bad named entity ffr without a semicolon`() = assertTokenization(
        input = "&ffr",
        expectedTokens = listOf(
            *chars("&ffr").toTypedArray()
        )
    )

    @Test
    fun `Named entity ffr with a semicolon`() = assertTokenization(
        input = "&ffr;",
        expectedTokens = listOf(
            *chars("𝔣").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity filig without a semicolon`() = assertTokenization(
        input = "&filig",
        expectedTokens = listOf(
            *chars("&filig").toTypedArray()
        )
    )

    @Test
    fun `Named entity filig with a semicolon`() = assertTokenization(
        input = "&filig;",
        expectedTokens = listOf(
            char('ﬁ')
        )
    )

    @Test
    fun `Bad named entity fjlig without a semicolon`() = assertTokenization(
        input = "&fjlig",
        expectedTokens = listOf(
            *chars("&fjlig").toTypedArray()
        )
    )

    @Test
    fun `Named entity fjlig with a semicolon`() = assertTokenization(
        input = "&fjlig;",
        expectedTokens = listOf(
            *chars("fj").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity flat without a semicolon`() = assertTokenization(
        input = "&flat",
        expectedTokens = listOf(
            *chars("&flat").toTypedArray()
        )
    )

    @Test
    fun `Named entity flat with a semicolon`() = assertTokenization(
        input = "&flat;",
        expectedTokens = listOf(
            char('♭')
        )
    )

    @Test
    fun `Bad named entity fllig without a semicolon`() = assertTokenization(
        input = "&fllig",
        expectedTokens = listOf(
            *chars("&fllig").toTypedArray()
        )
    )

    @Test
    fun `Named entity fllig with a semicolon`() = assertTokenization(
        input = "&fllig;",
        expectedTokens = listOf(
            char('ﬂ')
        )
    )

    @Test
    fun `Bad named entity fltns without a semicolon`() = assertTokenization(
        input = "&fltns",
        expectedTokens = listOf(
            *chars("&fltns").toTypedArray()
        )
    )

    @Test
    fun `Named entity fltns with a semicolon`() = assertTokenization(
        input = "&fltns;",
        expectedTokens = listOf(
            char('▱')
        )
    )

    @Test
    fun `Bad named entity fnof without a semicolon`() = assertTokenization(
        input = "&fnof",
        expectedTokens = listOf(
            *chars("&fnof").toTypedArray()
        )
    )

    @Test
    fun `Named entity fnof with a semicolon`() = assertTokenization(
        input = "&fnof;",
        expectedTokens = listOf(
            char('ƒ')
        )
    )

    @Test
    fun `Bad named entity fopf without a semicolon`() = assertTokenization(
        input = "&fopf",
        expectedTokens = listOf(
            *chars("&fopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity fopf with a semicolon`() = assertTokenization(
        input = "&fopf;",
        expectedTokens = listOf(
            *chars("𝕗").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity forall without a semicolon`() = assertTokenization(
        input = "&forall",
        expectedTokens = listOf(
            *chars("&forall").toTypedArray()
        )
    )

    @Test
    fun `Named entity forall with a semicolon`() = assertTokenization(
        input = "&forall;",
        expectedTokens = listOf(
            char('∀')
        )
    )

    @Test
    fun `Bad named entity fork without a semicolon`() = assertTokenization(
        input = "&fork",
        expectedTokens = listOf(
            *chars("&fork").toTypedArray()
        )
    )

    @Test
    fun `Named entity fork with a semicolon`() = assertTokenization(
        input = "&fork;",
        expectedTokens = listOf(
            char('⋔')
        )
    )

    @Test
    fun `Bad named entity forkv without a semicolon`() = assertTokenization(
        input = "&forkv",
        expectedTokens = listOf(
            *chars("&forkv").toTypedArray()
        )
    )

    @Test
    fun `Named entity forkv with a semicolon`() = assertTokenization(
        input = "&forkv;",
        expectedTokens = listOf(
            char('⫙')
        )
    )

    @Test
    fun `Bad named entity fpartint without a semicolon`() = assertTokenization(
        input = "&fpartint",
        expectedTokens = listOf(
            *chars("&fpartint").toTypedArray()
        )
    )

    @Test
    fun `Named entity fpartint with a semicolon`() = assertTokenization(
        input = "&fpartint;",
        expectedTokens = listOf(
            char('⨍')
        )
    )

    @Test
    fun `Named entity frac12 without a semicolon`() = assertTokenization(
        input = "&frac12",
        expectedTokens = listOf(
            char('½')
        )
    )

    @Test
    fun `Named entity frac12 with a semicolon`() = assertTokenization(
        input = "&frac12;",
        expectedTokens = listOf(
            char('½')
        )
    )

    @Test
    fun `Bad named entity frac13 without a semicolon`() = assertTokenization(
        input = "&frac13",
        expectedTokens = listOf(
            *chars("&frac13").toTypedArray()
        )
    )

    @Test
    fun `Named entity frac13 with a semicolon`() = assertTokenization(
        input = "&frac13;",
        expectedTokens = listOf(
            char('⅓')
        )
    )

    @Test
    fun `Named entity frac14 without a semicolon`() = assertTokenization(
        input = "&frac14",
        expectedTokens = listOf(
            char('¼')
        )
    )

    @Test
    fun `Named entity frac14 with a semicolon`() = assertTokenization(
        input = "&frac14;",
        expectedTokens = listOf(
            char('¼')
        )
    )

    @Test
    fun `Bad named entity frac15 without a semicolon`() = assertTokenization(
        input = "&frac15",
        expectedTokens = listOf(
            *chars("&frac15").toTypedArray()
        )
    )

    @Test
    fun `Named entity frac15 with a semicolon`() = assertTokenization(
        input = "&frac15;",
        expectedTokens = listOf(
            char('⅕')
        )
    )

    @Test
    fun `Bad named entity frac16 without a semicolon`() = assertTokenization(
        input = "&frac16",
        expectedTokens = listOf(
            *chars("&frac16").toTypedArray()
        )
    )

    @Test
    fun `Named entity frac16 with a semicolon`() = assertTokenization(
        input = "&frac16;",
        expectedTokens = listOf(
            char('⅙')
        )
    )

    @Test
    fun `Bad named entity frac18 without a semicolon`() = assertTokenization(
        input = "&frac18",
        expectedTokens = listOf(
            *chars("&frac18").toTypedArray()
        )
    )

    @Test
    fun `Named entity frac18 with a semicolon`() = assertTokenization(
        input = "&frac18;",
        expectedTokens = listOf(
            char('⅛')
        )
    )

    @Test
    fun `Bad named entity frac23 without a semicolon`() = assertTokenization(
        input = "&frac23",
        expectedTokens = listOf(
            *chars("&frac23").toTypedArray()
        )
    )

    @Test
    fun `Named entity frac23 with a semicolon`() = assertTokenization(
        input = "&frac23;",
        expectedTokens = listOf(
            char('⅔')
        )
    )

    @Test
    fun `Bad named entity frac25 without a semicolon`() = assertTokenization(
        input = "&frac25",
        expectedTokens = listOf(
            *chars("&frac25").toTypedArray()
        )
    )

    @Test
    fun `Named entity frac25 with a semicolon`() = assertTokenization(
        input = "&frac25;",
        expectedTokens = listOf(
            char('⅖')
        )
    )

    @Test
    fun `Named entity frac34 without a semicolon`() = assertTokenization(
        input = "&frac34",
        expectedTokens = listOf(
            char('¾')
        )
    )

    @Test
    fun `Named entity frac34 with a semicolon`() = assertTokenization(
        input = "&frac34;",
        expectedTokens = listOf(
            char('¾')
        )
    )

    @Test
    fun `Bad named entity frac35 without a semicolon`() = assertTokenization(
        input = "&frac35",
        expectedTokens = listOf(
            *chars("&frac35").toTypedArray()
        )
    )

    @Test
    fun `Named entity frac35 with a semicolon`() = assertTokenization(
        input = "&frac35;",
        expectedTokens = listOf(
            char('⅗')
        )
    )

    @Test
    fun `Bad named entity frac38 without a semicolon`() = assertTokenization(
        input = "&frac38",
        expectedTokens = listOf(
            *chars("&frac38").toTypedArray()
        )
    )

    @Test
    fun `Named entity frac38 with a semicolon`() = assertTokenization(
        input = "&frac38;",
        expectedTokens = listOf(
            char('⅜')
        )
    )

    @Test
    fun `Bad named entity frac45 without a semicolon`() = assertTokenization(
        input = "&frac45",
        expectedTokens = listOf(
            *chars("&frac45").toTypedArray()
        )
    )

    @Test
    fun `Named entity frac45 with a semicolon`() = assertTokenization(
        input = "&frac45;",
        expectedTokens = listOf(
            char('⅘')
        )
    )

    @Test
    fun `Bad named entity frac56 without a semicolon`() = assertTokenization(
        input = "&frac56",
        expectedTokens = listOf(
            *chars("&frac56").toTypedArray()
        )
    )

    @Test
    fun `Named entity frac56 with a semicolon`() = assertTokenization(
        input = "&frac56;",
        expectedTokens = listOf(
            char('⅚')
        )
    )

    @Test
    fun `Bad named entity frac58 without a semicolon`() = assertTokenization(
        input = "&frac58",
        expectedTokens = listOf(
            *chars("&frac58").toTypedArray()
        )
    )

    @Test
    fun `Named entity frac58 with a semicolon`() = assertTokenization(
        input = "&frac58;",
        expectedTokens = listOf(
            char('⅝')
        )
    )

    @Test
    fun `Bad named entity frac78 without a semicolon`() = assertTokenization(
        input = "&frac78",
        expectedTokens = listOf(
            *chars("&frac78").toTypedArray()
        )
    )

    @Test
    fun `Named entity frac78 with a semicolon`() = assertTokenization(
        input = "&frac78;",
        expectedTokens = listOf(
            char('⅞')
        )
    )

    @Test
    fun `Bad named entity frasl without a semicolon`() = assertTokenization(
        input = "&frasl",
        expectedTokens = listOf(
            *chars("&frasl").toTypedArray()
        )
    )

    @Test
    fun `Named entity frasl with a semicolon`() = assertTokenization(
        input = "&frasl;",
        expectedTokens = listOf(
            char('⁄')
        )
    )

    @Test
    fun `Bad named entity frown without a semicolon`() = assertTokenization(
        input = "&frown",
        expectedTokens = listOf(
            *chars("&frown").toTypedArray()
        )
    )

    @Test
    fun `Named entity frown with a semicolon`() = assertTokenization(
        input = "&frown;",
        expectedTokens = listOf(
            char('⌢')
        )
    )

    @Test
    fun `Bad named entity fscr without a semicolon`() = assertTokenization(
        input = "&fscr",
        expectedTokens = listOf(
            *chars("&fscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity fscr with a semicolon`() = assertTokenization(
        input = "&fscr;",
        expectedTokens = listOf(
            *chars("𝒻").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity gE without a semicolon`() = assertTokenization(
        input = "&gE",
        expectedTokens = listOf(
            *chars("&gE").toTypedArray()
        )
    )

    @Test
    fun `Named entity gE with a semicolon`() = assertTokenization(
        input = "&gE;",
        expectedTokens = listOf(
            char('≧')
        )
    )

    @Test
    fun `Bad named entity gEl without a semicolon`() = assertTokenization(
        input = "&gEl",
        expectedTokens = listOf(
            *chars("&gEl").toTypedArray()
        )
    )

    @Test
    fun `Named entity gEl with a semicolon`() = assertTokenization(
        input = "&gEl;",
        expectedTokens = listOf(
            char('⪌')
        )
    )

    @Test
    fun `Bad named entity gacute without a semicolon`() = assertTokenization(
        input = "&gacute",
        expectedTokens = listOf(
            *chars("&gacute").toTypedArray()
        )
    )

    @Test
    fun `Named entity gacute with a semicolon`() = assertTokenization(
        input = "&gacute;",
        expectedTokens = listOf(
            char('ǵ')
        )
    )

    @Test
    fun `Bad named entity gamma without a semicolon`() = assertTokenization(
        input = "&gamma",
        expectedTokens = listOf(
            *chars("&gamma").toTypedArray()
        )
    )

    @Test
    fun `Named entity gamma with a semicolon`() = assertTokenization(
        input = "&gamma;",
        expectedTokens = listOf(
            char('γ')
        )
    )

    @Test
    fun `Bad named entity gammad without a semicolon`() = assertTokenization(
        input = "&gammad",
        expectedTokens = listOf(
            *chars("&gammad").toTypedArray()
        )
    )

    @Test
    fun `Named entity gammad with a semicolon`() = assertTokenization(
        input = "&gammad;",
        expectedTokens = listOf(
            char('ϝ')
        )
    )

    @Test
    fun `Bad named entity gap without a semicolon`() = assertTokenization(
        input = "&gap",
        expectedTokens = listOf(
            *chars("&gap").toTypedArray()
        )
    )

    @Test
    fun `Named entity gap with a semicolon`() = assertTokenization(
        input = "&gap;",
        expectedTokens = listOf(
            char('⪆')
        )
    )

    @Test
    fun `Bad named entity gbreve without a semicolon`() = assertTokenization(
        input = "&gbreve",
        expectedTokens = listOf(
            *chars("&gbreve").toTypedArray()
        )
    )

    @Test
    fun `Named entity gbreve with a semicolon`() = assertTokenization(
        input = "&gbreve;",
        expectedTokens = listOf(
            char('ğ')
        )
    )

    @Test
    fun `Bad named entity gcirc without a semicolon`() = assertTokenization(
        input = "&gcirc",
        expectedTokens = listOf(
            *chars("&gcirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity gcirc with a semicolon`() = assertTokenization(
        input = "&gcirc;",
        expectedTokens = listOf(
            char('ĝ')
        )
    )

    @Test
    fun `Bad named entity gcy without a semicolon`() = assertTokenization(
        input = "&gcy",
        expectedTokens = listOf(
            *chars("&gcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity gcy with a semicolon`() = assertTokenization(
        input = "&gcy;",
        expectedTokens = listOf(
            char('г')
        )
    )

    @Test
    fun `Bad named entity gdot without a semicolon`() = assertTokenization(
        input = "&gdot",
        expectedTokens = listOf(
            *chars("&gdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity gdot with a semicolon`() = assertTokenization(
        input = "&gdot;",
        expectedTokens = listOf(
            char('ġ')
        )
    )

    @Test
    fun `Bad named entity ge without a semicolon`() = assertTokenization(
        input = "&ge",
        expectedTokens = listOf(
            *chars("&ge").toTypedArray()
        )
    )

    @Test
    fun `Named entity ge with a semicolon`() = assertTokenization(
        input = "&ge;",
        expectedTokens = listOf(
            char('≥')
        )
    )

    @Test
    fun `Bad named entity gel without a semicolon`() = assertTokenization(
        input = "&gel",
        expectedTokens = listOf(
            *chars("&gel").toTypedArray()
        )
    )

    @Test
    fun `Named entity gel with a semicolon`() = assertTokenization(
        input = "&gel;",
        expectedTokens = listOf(
            char('⋛')
        )
    )

    @Test
    fun `Bad named entity geq without a semicolon`() = assertTokenization(
        input = "&geq",
        expectedTokens = listOf(
            *chars("&geq").toTypedArray()
        )
    )

    @Test
    fun `Named entity geq with a semicolon`() = assertTokenization(
        input = "&geq;",
        expectedTokens = listOf(
            char('≥')
        )
    )

    @Test
    fun `Bad named entity geqq without a semicolon`() = assertTokenization(
        input = "&geqq",
        expectedTokens = listOf(
            *chars("&geqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity geqq with a semicolon`() = assertTokenization(
        input = "&geqq;",
        expectedTokens = listOf(
            char('≧')
        )
    )

    @Test
    fun `Bad named entity geqslant without a semicolon`() = assertTokenization(
        input = "&geqslant",
        expectedTokens = listOf(
            *chars("&geqslant").toTypedArray()
        )
    )

    @Test
    fun `Named entity geqslant with a semicolon`() = assertTokenization(
        input = "&geqslant;",
        expectedTokens = listOf(
            char('⩾')
        )
    )

    @Test
    fun `Bad named entity ges without a semicolon`() = assertTokenization(
        input = "&ges",
        expectedTokens = listOf(
            *chars("&ges").toTypedArray()
        )
    )

    @Test
    fun `Named entity ges with a semicolon`() = assertTokenization(
        input = "&ges;",
        expectedTokens = listOf(
            char('⩾')
        )
    )

    @Test
    fun `Bad named entity gescc without a semicolon`() = assertTokenization(
        input = "&gescc",
        expectedTokens = listOf(
            *chars("&gescc").toTypedArray()
        )
    )

    @Test
    fun `Named entity gescc with a semicolon`() = assertTokenization(
        input = "&gescc;",
        expectedTokens = listOf(
            char('⪩')
        )
    )

    @Test
    fun `Bad named entity gesdot without a semicolon`() = assertTokenization(
        input = "&gesdot",
        expectedTokens = listOf(
            *chars("&gesdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity gesdot with a semicolon`() = assertTokenization(
        input = "&gesdot;",
        expectedTokens = listOf(
            char('⪀')
        )
    )

    @Test
    fun `Bad named entity gesdoto without a semicolon`() = assertTokenization(
        input = "&gesdoto",
        expectedTokens = listOf(
            *chars("&gesdoto").toTypedArray()
        )
    )

    @Test
    fun `Named entity gesdoto with a semicolon`() = assertTokenization(
        input = "&gesdoto;",
        expectedTokens = listOf(
            char('⪂')
        )
    )

    @Test
    fun `Bad named entity gesdotol without a semicolon`() = assertTokenization(
        input = "&gesdotol",
        expectedTokens = listOf(
            *chars("&gesdotol").toTypedArray()
        )
    )

    @Test
    fun `Named entity gesdotol with a semicolon`() = assertTokenization(
        input = "&gesdotol;",
        expectedTokens = listOf(
            char('⪄')
        )
    )

    @Test
    fun `Bad named entity gesl without a semicolon`() = assertTokenization(
        input = "&gesl",
        expectedTokens = listOf(
            *chars("&gesl").toTypedArray()
        )
    )

    @Test
    fun `Named entity gesl with a semicolon`() = assertTokenization(
        input = "&gesl;",
        expectedTokens = listOf(
            *chars("⋛︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity gesles without a semicolon`() = assertTokenization(
        input = "&gesles",
        expectedTokens = listOf(
            *chars("&gesles").toTypedArray()
        )
    )

    @Test
    fun `Named entity gesles with a semicolon`() = assertTokenization(
        input = "&gesles;",
        expectedTokens = listOf(
            char('⪔')
        )
    )

    @Test
    fun `Bad named entity gfr without a semicolon`() = assertTokenization(
        input = "&gfr",
        expectedTokens = listOf(
            *chars("&gfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity gfr with a semicolon`() = assertTokenization(
        input = "&gfr;",
        expectedTokens = listOf(
            *chars("𝔤").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity gg without a semicolon`() = assertTokenization(
        input = "&gg",
        expectedTokens = listOf(
            *chars("&gg").toTypedArray()
        )
    )

    @Test
    fun `Named entity gg with a semicolon`() = assertTokenization(
        input = "&gg;",
        expectedTokens = listOf(
            char('≫')
        )
    )

    @Test
    fun `Bad named entity ggg without a semicolon`() = assertTokenization(
        input = "&ggg",
        expectedTokens = listOf(
            *chars("&ggg").toTypedArray()
        )
    )

    @Test
    fun `Named entity ggg with a semicolon`() = assertTokenization(
        input = "&ggg;",
        expectedTokens = listOf(
            char('⋙')
        )
    )

    @Test
    fun `Bad named entity gimel without a semicolon`() = assertTokenization(
        input = "&gimel",
        expectedTokens = listOf(
            *chars("&gimel").toTypedArray()
        )
    )

    @Test
    fun `Named entity gimel with a semicolon`() = assertTokenization(
        input = "&gimel;",
        expectedTokens = listOf(
            char('ℷ')
        )
    )

    @Test
    fun `Bad named entity gjcy without a semicolon`() = assertTokenization(
        input = "&gjcy",
        expectedTokens = listOf(
            *chars("&gjcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity gjcy with a semicolon`() = assertTokenization(
        input = "&gjcy;",
        expectedTokens = listOf(
            char('ѓ')
        )
    )

    @Test
    fun `Bad named entity gl without a semicolon`() = assertTokenization(
        input = "&gl",
        expectedTokens = listOf(
            *chars("&gl").toTypedArray()
        )
    )

    @Test
    fun `Named entity gl with a semicolon`() = assertTokenization(
        input = "&gl;",
        expectedTokens = listOf(
            char('≷')
        )
    )

    @Test
    fun `Bad named entity glE without a semicolon`() = assertTokenization(
        input = "&glE",
        expectedTokens = listOf(
            *chars("&glE").toTypedArray()
        )
    )

    @Test
    fun `Named entity glE with a semicolon`() = assertTokenization(
        input = "&glE;",
        expectedTokens = listOf(
            char('⪒')
        )
    )

    @Test
    fun `Bad named entity gla without a semicolon`() = assertTokenization(
        input = "&gla",
        expectedTokens = listOf(
            *chars("&gla").toTypedArray()
        )
    )

    @Test
    fun `Named entity gla with a semicolon`() = assertTokenization(
        input = "&gla;",
        expectedTokens = listOf(
            char('⪥')
        )
    )

    @Test
    fun `Bad named entity glj without a semicolon`() = assertTokenization(
        input = "&glj",
        expectedTokens = listOf(
            *chars("&glj").toTypedArray()
        )
    )

    @Test
    fun `Named entity glj with a semicolon`() = assertTokenization(
        input = "&glj;",
        expectedTokens = listOf(
            char('⪤')
        )
    )

    @Test
    fun `Bad named entity gnE without a semicolon`() = assertTokenization(
        input = "&gnE",
        expectedTokens = listOf(
            *chars("&gnE").toTypedArray()
        )
    )

    @Test
    fun `Named entity gnE with a semicolon`() = assertTokenization(
        input = "&gnE;",
        expectedTokens = listOf(
            char('≩')
        )
    )

    @Test
    fun `Bad named entity gnap without a semicolon`() = assertTokenization(
        input = "&gnap",
        expectedTokens = listOf(
            *chars("&gnap").toTypedArray()
        )
    )

    @Test
    fun `Named entity gnap with a semicolon`() = assertTokenization(
        input = "&gnap;",
        expectedTokens = listOf(
            char('⪊')
        )
    )

    @Test
    fun `Bad named entity gnapprox without a semicolon`() = assertTokenization(
        input = "&gnapprox",
        expectedTokens = listOf(
            *chars("&gnapprox").toTypedArray()
        )
    )

    @Test
    fun `Named entity gnapprox with a semicolon`() = assertTokenization(
        input = "&gnapprox;",
        expectedTokens = listOf(
            char('⪊')
        )
    )

    @Test
    fun `Bad named entity gne without a semicolon`() = assertTokenization(
        input = "&gne",
        expectedTokens = listOf(
            *chars("&gne").toTypedArray()
        )
    )

    @Test
    fun `Named entity gne with a semicolon`() = assertTokenization(
        input = "&gne;",
        expectedTokens = listOf(
            char('⪈')
        )
    )

    @Test
    fun `Bad named entity gneq without a semicolon`() = assertTokenization(
        input = "&gneq",
        expectedTokens = listOf(
            *chars("&gneq").toTypedArray()
        )
    )

    @Test
    fun `Named entity gneq with a semicolon`() = assertTokenization(
        input = "&gneq;",
        expectedTokens = listOf(
            char('⪈')
        )
    )

    @Test
    fun `Bad named entity gneqq without a semicolon`() = assertTokenization(
        input = "&gneqq",
        expectedTokens = listOf(
            *chars("&gneqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity gneqq with a semicolon`() = assertTokenization(
        input = "&gneqq;",
        expectedTokens = listOf(
            char('≩')
        )
    )

    @Test
    fun `Bad named entity gnsim without a semicolon`() = assertTokenization(
        input = "&gnsim",
        expectedTokens = listOf(
            *chars("&gnsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity gnsim with a semicolon`() = assertTokenization(
        input = "&gnsim;",
        expectedTokens = listOf(
            char('⋧')
        )
    )

    @Test
    fun `Bad named entity gopf without a semicolon`() = assertTokenization(
        input = "&gopf",
        expectedTokens = listOf(
            *chars("&gopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity gopf with a semicolon`() = assertTokenization(
        input = "&gopf;",
        expectedTokens = listOf(
            *chars("𝕘").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity grave without a semicolon`() = assertTokenization(
        input = "&grave",
        expectedTokens = listOf(
            *chars("&grave").toTypedArray()
        )
    )

    @Test
    fun `Named entity grave with a semicolon`() = assertTokenization(
        input = "&grave;",
        expectedTokens = listOf(
            char('`')
        )
    )

    @Test
    fun `Bad named entity gscr without a semicolon`() = assertTokenization(
        input = "&gscr",
        expectedTokens = listOf(
            *chars("&gscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity gscr with a semicolon`() = assertTokenization(
        input = "&gscr;",
        expectedTokens = listOf(
            char('ℊ')
        )
    )

    @Test
    fun `Bad named entity gsim without a semicolon`() = assertTokenization(
        input = "&gsim",
        expectedTokens = listOf(
            *chars("&gsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity gsim with a semicolon`() = assertTokenization(
        input = "&gsim;",
        expectedTokens = listOf(
            char('≳')
        )
    )

    @Test
    fun `Bad named entity gsime without a semicolon`() = assertTokenization(
        input = "&gsime",
        expectedTokens = listOf(
            *chars("&gsime").toTypedArray()
        )
    )

    @Test
    fun `Named entity gsime with a semicolon`() = assertTokenization(
        input = "&gsime;",
        expectedTokens = listOf(
            char('⪎')
        )
    )

    @Test
    fun `Bad named entity gsiml without a semicolon`() = assertTokenization(
        input = "&gsiml",
        expectedTokens = listOf(
            *chars("&gsiml").toTypedArray()
        )
    )

    @Test
    fun `Named entity gsiml with a semicolon`() = assertTokenization(
        input = "&gsiml;",
        expectedTokens = listOf(
            char('⪐')
        )
    )

    @Test
    fun `Named entity gt without a semicolon`() = assertTokenization(
        input = "&gt",
        expectedTokens = listOf(
            char('>')
        )
    )

    @Test
    fun `Named entity gt with a semicolon`() = assertTokenization(
        input = "&gt;",
        expectedTokens = listOf(
            char('>')
        )
    )

    @Test
    fun `Named entity gtcc with a semicolon`() = assertTokenization(
        input = "&gtcc;",
        expectedTokens = listOf(
            char('⪧')
        )
    )

    @Test
    fun `Named entity gtcir with a semicolon`() = assertTokenization(
        input = "&gtcir;",
        expectedTokens = listOf(
            char('⩺')
        )
    )

    @Test
    fun `Named entity gtdot with a semicolon`() = assertTokenization(
        input = "&gtdot;",
        expectedTokens = listOf(
            char('⋗')
        )
    )

    @Test
    fun `Named entity gtlPar with a semicolon`() = assertTokenization(
        input = "&gtlPar;",
        expectedTokens = listOf(
            char('⦕')
        )
    )

    @Test
    fun `Named entity gtquest with a semicolon`() = assertTokenization(
        input = "&gtquest;",
        expectedTokens = listOf(
            char('⩼')
        )
    )

    @Test
    fun `Named entity gtrapprox with a semicolon`() = assertTokenization(
        input = "&gtrapprox;",
        expectedTokens = listOf(
            char('⪆')
        )
    )

    @Test
    fun `Named entity gtrarr with a semicolon`() = assertTokenization(
        input = "&gtrarr;",
        expectedTokens = listOf(
            char('⥸')
        )
    )

    @Test
    fun `Named entity gtrdot with a semicolon`() = assertTokenization(
        input = "&gtrdot;",
        expectedTokens = listOf(
            char('⋗')
        )
    )

    @Test
    fun `Named entity gtreqless with a semicolon`() = assertTokenization(
        input = "&gtreqless;",
        expectedTokens = listOf(
            char('⋛')
        )
    )

    @Test
    fun `Named entity gtreqqless with a semicolon`() = assertTokenization(
        input = "&gtreqqless;",
        expectedTokens = listOf(
            char('⪌')
        )
    )

    @Test
    fun `Named entity gtrless with a semicolon`() = assertTokenization(
        input = "&gtrless;",
        expectedTokens = listOf(
            char('≷')
        )
    )

    @Test
    fun `Named entity gtrsim with a semicolon`() = assertTokenization(
        input = "&gtrsim;",
        expectedTokens = listOf(
            char('≳')
        )
    )

    @Test
    fun `Bad named entity gvertneqq without a semicolon`() = assertTokenization(
        input = "&gvertneqq",
        expectedTokens = listOf(
            *chars("&gvertneqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity gvertneqq with a semicolon`() = assertTokenization(
        input = "&gvertneqq;",
        expectedTokens = listOf(
            *chars("≩︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity gvnE without a semicolon`() = assertTokenization(
        input = "&gvnE",
        expectedTokens = listOf(
            *chars("&gvnE").toTypedArray()
        )
    )

    @Test
    fun `Named entity gvnE with a semicolon`() = assertTokenization(
        input = "&gvnE;",
        expectedTokens = listOf(
            *chars("≩︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity hArr without a semicolon`() = assertTokenization(
        input = "&hArr",
        expectedTokens = listOf(
            *chars("&hArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity hArr with a semicolon`() = assertTokenization(
        input = "&hArr;",
        expectedTokens = listOf(
            char('⇔')
        )
    )

    @Test
    fun `Bad named entity hairsp without a semicolon`() = assertTokenization(
        input = "&hairsp",
        expectedTokens = listOf(
            *chars("&hairsp").toTypedArray()
        )
    )

    @Test
    fun `Named entity hairsp with a semicolon`() = assertTokenization(
        input = "&hairsp;",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Bad named entity half without a semicolon`() = assertTokenization(
        input = "&half",
        expectedTokens = listOf(
            *chars("&half").toTypedArray()
        )
    )

    @Test
    fun `Named entity half with a semicolon`() = assertTokenization(
        input = "&half;",
        expectedTokens = listOf(
            char('½')
        )
    )

    @Test
    fun `Bad named entity hamilt without a semicolon`() = assertTokenization(
        input = "&hamilt",
        expectedTokens = listOf(
            *chars("&hamilt").toTypedArray()
        )
    )

    @Test
    fun `Named entity hamilt with a semicolon`() = assertTokenization(
        input = "&hamilt;",
        expectedTokens = listOf(
            char('ℋ')
        )
    )

    @Test
    fun `Bad named entity hardcy without a semicolon`() = assertTokenization(
        input = "&hardcy",
        expectedTokens = listOf(
            *chars("&hardcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity hardcy with a semicolon`() = assertTokenization(
        input = "&hardcy;",
        expectedTokens = listOf(
            char('ъ')
        )
    )

    @Test
    fun `Bad named entity harr without a semicolon`() = assertTokenization(
        input = "&harr",
        expectedTokens = listOf(
            *chars("&harr").toTypedArray()
        )
    )

    @Test
    fun `Named entity harr with a semicolon`() = assertTokenization(
        input = "&harr;",
        expectedTokens = listOf(
            char('↔')
        )
    )

    @Test
    fun `Bad named entity harrcir without a semicolon`() = assertTokenization(
        input = "&harrcir",
        expectedTokens = listOf(
            *chars("&harrcir").toTypedArray()
        )
    )

    @Test
    fun `Named entity harrcir with a semicolon`() = assertTokenization(
        input = "&harrcir;",
        expectedTokens = listOf(
            char('⥈')
        )
    )

    @Test
    fun `Bad named entity harrw without a semicolon`() = assertTokenization(
        input = "&harrw",
        expectedTokens = listOf(
            *chars("&harrw").toTypedArray()
        )
    )

    @Test
    fun `Named entity harrw with a semicolon`() = assertTokenization(
        input = "&harrw;",
        expectedTokens = listOf(
            char('↭')
        )
    )

    @Test
    fun `Bad named entity hbar without a semicolon`() = assertTokenization(
        input = "&hbar",
        expectedTokens = listOf(
            *chars("&hbar").toTypedArray()
        )
    )

    @Test
    fun `Named entity hbar with a semicolon`() = assertTokenization(
        input = "&hbar;",
        expectedTokens = listOf(
            char('ℏ')
        )
    )

    @Test
    fun `Bad named entity hcirc without a semicolon`() = assertTokenization(
        input = "&hcirc",
        expectedTokens = listOf(
            *chars("&hcirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity hcirc with a semicolon`() = assertTokenization(
        input = "&hcirc;",
        expectedTokens = listOf(
            char('ĥ')
        )
    )

    @Test
    fun `Bad named entity hearts without a semicolon`() = assertTokenization(
        input = "&hearts",
        expectedTokens = listOf(
            *chars("&hearts").toTypedArray()
        )
    )

    @Test
    fun `Named entity hearts with a semicolon`() = assertTokenization(
        input = "&hearts;",
        expectedTokens = listOf(
            char('♥')
        )
    )

    @Test
    fun `Bad named entity heartsuit without a semicolon`() = assertTokenization(
        input = "&heartsuit",
        expectedTokens = listOf(
            *chars("&heartsuit").toTypedArray()
        )
    )

    @Test
    fun `Named entity heartsuit with a semicolon`() = assertTokenization(
        input = "&heartsuit;",
        expectedTokens = listOf(
            char('♥')
        )
    )

    @Test
    fun `Bad named entity hellip without a semicolon`() = assertTokenization(
        input = "&hellip",
        expectedTokens = listOf(
            *chars("&hellip").toTypedArray()
        )
    )

    @Test
    fun `Named entity hellip with a semicolon`() = assertTokenization(
        input = "&hellip;",
        expectedTokens = listOf(
            char('…')
        )
    )

    @Test
    fun `Bad named entity hercon without a semicolon`() = assertTokenization(
        input = "&hercon",
        expectedTokens = listOf(
            *chars("&hercon").toTypedArray()
        )
    )

    @Test
    fun `Named entity hercon with a semicolon`() = assertTokenization(
        input = "&hercon;",
        expectedTokens = listOf(
            char('⊹')
        )
    )

    @Test
    fun `Bad named entity hfr without a semicolon`() = assertTokenization(
        input = "&hfr",
        expectedTokens = listOf(
            *chars("&hfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity hfr with a semicolon`() = assertTokenization(
        input = "&hfr;",
        expectedTokens = listOf(
            *chars("𝔥").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity hksearow without a semicolon`() = assertTokenization(
        input = "&hksearow",
        expectedTokens = listOf(
            *chars("&hksearow").toTypedArray()
        )
    )

    @Test
    fun `Named entity hksearow with a semicolon`() = assertTokenization(
        input = "&hksearow;",
        expectedTokens = listOf(
            char('⤥')
        )
    )

    @Test
    fun `Bad named entity hkswarow without a semicolon`() = assertTokenization(
        input = "&hkswarow",
        expectedTokens = listOf(
            *chars("&hkswarow").toTypedArray()
        )
    )

    @Test
    fun `Named entity hkswarow with a semicolon`() = assertTokenization(
        input = "&hkswarow;",
        expectedTokens = listOf(
            char('⤦')
        )
    )

    @Test
    fun `Bad named entity hoarr without a semicolon`() = assertTokenization(
        input = "&hoarr",
        expectedTokens = listOf(
            *chars("&hoarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity hoarr with a semicolon`() = assertTokenization(
        input = "&hoarr;",
        expectedTokens = listOf(
            char('⇿')
        )
    )

    @Test
    fun `Bad named entity homtht without a semicolon`() = assertTokenization(
        input = "&homtht",
        expectedTokens = listOf(
            *chars("&homtht").toTypedArray()
        )
    )

    @Test
    fun `Named entity homtht with a semicolon`() = assertTokenization(
        input = "&homtht;",
        expectedTokens = listOf(
            char('∻')
        )
    )

    @Test
    fun `Bad named entity hookleftarrow without a semicolon`() = assertTokenization(
        input = "&hookleftarrow",
        expectedTokens = listOf(
            *chars("&hookleftarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity hookleftarrow with a semicolon`() = assertTokenization(
        input = "&hookleftarrow;",
        expectedTokens = listOf(
            char('↩')
        )
    )

    @Test
    fun `Bad named entity hookrightarrow without a semicolon`() = assertTokenization(
        input = "&hookrightarrow",
        expectedTokens = listOf(
            *chars("&hookrightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity hookrightarrow with a semicolon`() = assertTokenization(
        input = "&hookrightarrow;",
        expectedTokens = listOf(
            char('↪')
        )
    )

    @Test
    fun `Bad named entity hopf without a semicolon`() = assertTokenization(
        input = "&hopf",
        expectedTokens = listOf(
            *chars("&hopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity hopf with a semicolon`() = assertTokenization(
        input = "&hopf;",
        expectedTokens = listOf(
            *chars("𝕙").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity horbar without a semicolon`() = assertTokenization(
        input = "&horbar",
        expectedTokens = listOf(
            *chars("&horbar").toTypedArray()
        )
    )

    @Test
    fun `Named entity horbar with a semicolon`() = assertTokenization(
        input = "&horbar;",
        expectedTokens = listOf(
            char('―')
        )
    )

    @Test
    fun `Bad named entity hscr without a semicolon`() = assertTokenization(
        input = "&hscr",
        expectedTokens = listOf(
            *chars("&hscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity hscr with a semicolon`() = assertTokenization(
        input = "&hscr;",
        expectedTokens = listOf(
            *chars("𝒽").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity hslash without a semicolon`() = assertTokenization(
        input = "&hslash",
        expectedTokens = listOf(
            *chars("&hslash").toTypedArray()
        )
    )

    @Test
    fun `Named entity hslash with a semicolon`() = assertTokenization(
        input = "&hslash;",
        expectedTokens = listOf(
            char('ℏ')
        )
    )

    @Test
    fun `Bad named entity hstrok without a semicolon`() = assertTokenization(
        input = "&hstrok",
        expectedTokens = listOf(
            *chars("&hstrok").toTypedArray()
        )
    )

    @Test
    fun `Named entity hstrok with a semicolon`() = assertTokenization(
        input = "&hstrok;",
        expectedTokens = listOf(
            char('ħ')
        )
    )

    @Test
    fun `Bad named entity hybull without a semicolon`() = assertTokenization(
        input = "&hybull",
        expectedTokens = listOf(
            *chars("&hybull").toTypedArray()
        )
    )

    @Test
    fun `Named entity hybull with a semicolon`() = assertTokenization(
        input = "&hybull;",
        expectedTokens = listOf(
            char('⁃')
        )
    )

    @Test
    fun `Bad named entity hyphen without a semicolon`() = assertTokenization(
        input = "&hyphen",
        expectedTokens = listOf(
            *chars("&hyphen").toTypedArray()
        )
    )

    @Test
    fun `Named entity hyphen with a semicolon`() = assertTokenization(
        input = "&hyphen;",
        expectedTokens = listOf(
            char('‐')
        )
    )

    @Test
    fun `Named entity iacute without a semicolon`() = assertTokenization(
        input = "&iacute",
        expectedTokens = listOf(
            char('í')
        )
    )

    @Test
    fun `Named entity iacute with a semicolon`() = assertTokenization(
        input = "&iacute;",
        expectedTokens = listOf(
            char('í')
        )
    )

    @Test
    fun `Bad named entity ic without a semicolon`() = assertTokenization(
        input = "&ic",
        expectedTokens = listOf(
            *chars("&ic").toTypedArray()
        )
    )

    @Test
    fun `Named entity ic with a semicolon`() = assertTokenization(
        input = "&ic;",
        expectedTokens = listOf(
            char('⁣')
        )
    )

    @Test
    fun `Named entity icirc without a semicolon`() = assertTokenization(
        input = "&icirc",
        expectedTokens = listOf(
            char('î')
        )
    )

    @Test
    fun `Named entity icirc with a semicolon`() = assertTokenization(
        input = "&icirc;",
        expectedTokens = listOf(
            char('î')
        )
    )

    @Test
    fun `Bad named entity icy without a semicolon`() = assertTokenization(
        input = "&icy",
        expectedTokens = listOf(
            *chars("&icy").toTypedArray()
        )
    )

    @Test
    fun `Named entity icy with a semicolon`() = assertTokenization(
        input = "&icy;",
        expectedTokens = listOf(
            char('и')
        )
    )

    @Test
    fun `Bad named entity iecy without a semicolon`() = assertTokenization(
        input = "&iecy",
        expectedTokens = listOf(
            *chars("&iecy").toTypedArray()
        )
    )

    @Test
    fun `Named entity iecy with a semicolon`() = assertTokenization(
        input = "&iecy;",
        expectedTokens = listOf(
            char('е')
        )
    )

    @Test
    fun `Named entity iexcl without a semicolon`() = assertTokenization(
        input = "&iexcl",
        expectedTokens = listOf(
            char('¡')
        )
    )

    @Test
    fun `Named entity iexcl with a semicolon`() = assertTokenization(
        input = "&iexcl;",
        expectedTokens = listOf(
            char('¡')
        )
    )

    @Test
    fun `Bad named entity iff without a semicolon`() = assertTokenization(
        input = "&iff",
        expectedTokens = listOf(
            *chars("&iff").toTypedArray()
        )
    )

    @Test
    fun `Named entity iff with a semicolon`() = assertTokenization(
        input = "&iff;",
        expectedTokens = listOf(
            char('⇔')
        )
    )

    @Test
    fun `Bad named entity ifr without a semicolon`() = assertTokenization(
        input = "&ifr",
        expectedTokens = listOf(
            *chars("&ifr").toTypedArray()
        )
    )

    @Test
    fun `Named entity ifr with a semicolon`() = assertTokenization(
        input = "&ifr;",
        expectedTokens = listOf(
            *chars("𝔦").toTypedArray()
        )
    )

    @Test
    fun `Named entity igrave without a semicolon`() = assertTokenization(
        input = "&igrave",
        expectedTokens = listOf(
            char('ì')
        )
    )

    @Test
    fun `Named entity igrave with a semicolon`() = assertTokenization(
        input = "&igrave;",
        expectedTokens = listOf(
            char('ì')
        )
    )

    @Test
    fun `Bad named entity ii without a semicolon`() = assertTokenization(
        input = "&ii",
        expectedTokens = listOf(
            *chars("&ii").toTypedArray()
        )
    )

    @Test
    fun `Named entity ii with a semicolon`() = assertTokenization(
        input = "&ii;",
        expectedTokens = listOf(
            char('ⅈ')
        )
    )

    @Test
    fun `Bad named entity iiiint without a semicolon`() = assertTokenization(
        input = "&iiiint",
        expectedTokens = listOf(
            *chars("&iiiint").toTypedArray()
        )
    )

    @Test
    fun `Named entity iiiint with a semicolon`() = assertTokenization(
        input = "&iiiint;",
        expectedTokens = listOf(
            char('⨌')
        )
    )

    @Test
    fun `Bad named entity iiint without a semicolon`() = assertTokenization(
        input = "&iiint",
        expectedTokens = listOf(
            *chars("&iiint").toTypedArray()
        )
    )

    @Test
    fun `Named entity iiint with a semicolon`() = assertTokenization(
        input = "&iiint;",
        expectedTokens = listOf(
            char('∭')
        )
    )

    @Test
    fun `Bad named entity iinfin without a semicolon`() = assertTokenization(
        input = "&iinfin",
        expectedTokens = listOf(
            *chars("&iinfin").toTypedArray()
        )
    )

    @Test
    fun `Named entity iinfin with a semicolon`() = assertTokenization(
        input = "&iinfin;",
        expectedTokens = listOf(
            char('⧜')
        )
    )

    @Test
    fun `Bad named entity iiota without a semicolon`() = assertTokenization(
        input = "&iiota",
        expectedTokens = listOf(
            *chars("&iiota").toTypedArray()
        )
    )

    @Test
    fun `Named entity iiota with a semicolon`() = assertTokenization(
        input = "&iiota;",
        expectedTokens = listOf(
            char('℩')
        )
    )

    @Test
    fun `Bad named entity ijlig without a semicolon`() = assertTokenization(
        input = "&ijlig",
        expectedTokens = listOf(
            *chars("&ijlig").toTypedArray()
        )
    )

    @Test
    fun `Named entity ijlig with a semicolon`() = assertTokenization(
        input = "&ijlig;",
        expectedTokens = listOf(
            char('ĳ')
        )
    )

    @Test
    fun `Bad named entity imacr without a semicolon`() = assertTokenization(
        input = "&imacr",
        expectedTokens = listOf(
            *chars("&imacr").toTypedArray()
        )
    )

    @Test
    fun `Named entity imacr with a semicolon`() = assertTokenization(
        input = "&imacr;",
        expectedTokens = listOf(
            char('ī')
        )
    )

    @Test
    fun `Bad named entity image without a semicolon`() = assertTokenization(
        input = "&image",
        expectedTokens = listOf(
            *chars("&image").toTypedArray()
        )
    )

    @Test
    fun `Named entity image with a semicolon`() = assertTokenization(
        input = "&image;",
        expectedTokens = listOf(
            char('ℑ')
        )
    )

    @Test
    fun `Bad named entity imagline without a semicolon`() = assertTokenization(
        input = "&imagline",
        expectedTokens = listOf(
            *chars("&imagline").toTypedArray()
        )
    )

    @Test
    fun `Named entity imagline with a semicolon`() = assertTokenization(
        input = "&imagline;",
        expectedTokens = listOf(
            char('ℐ')
        )
    )

    @Test
    fun `Bad named entity imagpart without a semicolon`() = assertTokenization(
        input = "&imagpart",
        expectedTokens = listOf(
            *chars("&imagpart").toTypedArray()
        )
    )

    @Test
    fun `Named entity imagpart with a semicolon`() = assertTokenization(
        input = "&imagpart;",
        expectedTokens = listOf(
            char('ℑ')
        )
    )

    @Test
    fun `Bad named entity imath without a semicolon`() = assertTokenization(
        input = "&imath",
        expectedTokens = listOf(
            *chars("&imath").toTypedArray()
        )
    )

    @Test
    fun `Named entity imath with a semicolon`() = assertTokenization(
        input = "&imath;",
        expectedTokens = listOf(
            char('ı')
        )
    )

    @Test
    fun `Bad named entity imof without a semicolon`() = assertTokenization(
        input = "&imof",
        expectedTokens = listOf(
            *chars("&imof").toTypedArray()
        )
    )

    @Test
    fun `Named entity imof with a semicolon`() = assertTokenization(
        input = "&imof;",
        expectedTokens = listOf(
            char('⊷')
        )
    )

    @Test
    fun `Bad named entity imped without a semicolon`() = assertTokenization(
        input = "&imped",
        expectedTokens = listOf(
            *chars("&imped").toTypedArray()
        )
    )

    @Test
    fun `Named entity imped with a semicolon`() = assertTokenization(
        input = "&imped;",
        expectedTokens = listOf(
            char('Ƶ')
        )
    )

    @Test
    fun `Bad named entity in without a semicolon`() = assertTokenization(
        input = "&in",
        expectedTokens = listOf(
            *chars("&in").toTypedArray()
        )
    )

    @Test
    fun `Named entity in with a semicolon`() = assertTokenization(
        input = "&in;",
        expectedTokens = listOf(
            char('∈')
        )
    )

    @Test
    fun `Bad named entity incare without a semicolon`() = assertTokenization(
        input = "&incare",
        expectedTokens = listOf(
            *chars("&incare").toTypedArray()
        )
    )

    @Test
    fun `Named entity incare with a semicolon`() = assertTokenization(
        input = "&incare;",
        expectedTokens = listOf(
            char('℅')
        )
    )

    @Test
    fun `Bad named entity infin without a semicolon`() = assertTokenization(
        input = "&infin",
        expectedTokens = listOf(
            *chars("&infin").toTypedArray()
        )
    )

    @Test
    fun `Named entity infin with a semicolon`() = assertTokenization(
        input = "&infin;",
        expectedTokens = listOf(
            char('∞')
        )
    )

    @Test
    fun `Bad named entity infintie without a semicolon`() = assertTokenization(
        input = "&infintie",
        expectedTokens = listOf(
            *chars("&infintie").toTypedArray()
        )
    )

    @Test
    fun `Named entity infintie with a semicolon`() = assertTokenization(
        input = "&infintie;",
        expectedTokens = listOf(
            char('⧝')
        )
    )

    @Test
    fun `Bad named entity inodot without a semicolon`() = assertTokenization(
        input = "&inodot",
        expectedTokens = listOf(
            *chars("&inodot").toTypedArray()
        )
    )

    @Test
    fun `Named entity inodot with a semicolon`() = assertTokenization(
        input = "&inodot;",
        expectedTokens = listOf(
            char('ı')
        )
    )

    @Test
    fun `Bad named entity int without a semicolon`() = assertTokenization(
        input = "&int",
        expectedTokens = listOf(
            *chars("&int").toTypedArray()
        )
    )

    @Test
    fun `Named entity int with a semicolon`() = assertTokenization(
        input = "&int;",
        expectedTokens = listOf(
            char('∫')
        )
    )

    @Test
    fun `Bad named entity intcal without a semicolon`() = assertTokenization(
        input = "&intcal",
        expectedTokens = listOf(
            *chars("&intcal").toTypedArray()
        )
    )

    @Test
    fun `Named entity intcal with a semicolon`() = assertTokenization(
        input = "&intcal;",
        expectedTokens = listOf(
            char('⊺')
        )
    )

    @Test
    fun `Bad named entity integers without a semicolon`() = assertTokenization(
        input = "&integers",
        expectedTokens = listOf(
            *chars("&integers").toTypedArray()
        )
    )

    @Test
    fun `Named entity integers with a semicolon`() = assertTokenization(
        input = "&integers;",
        expectedTokens = listOf(
            char('ℤ')
        )
    )

    @Test
    fun `Bad named entity intercal without a semicolon`() = assertTokenization(
        input = "&intercal",
        expectedTokens = listOf(
            *chars("&intercal").toTypedArray()
        )
    )

    @Test
    fun `Named entity intercal with a semicolon`() = assertTokenization(
        input = "&intercal;",
        expectedTokens = listOf(
            char('⊺')
        )
    )

    @Test
    fun `Bad named entity intlarhk without a semicolon`() = assertTokenization(
        input = "&intlarhk",
        expectedTokens = listOf(
            *chars("&intlarhk").toTypedArray()
        )
    )

    @Test
    fun `Named entity intlarhk with a semicolon`() = assertTokenization(
        input = "&intlarhk;",
        expectedTokens = listOf(
            char('⨗')
        )
    )

    @Test
    fun `Bad named entity intprod without a semicolon`() = assertTokenization(
        input = "&intprod",
        expectedTokens = listOf(
            *chars("&intprod").toTypedArray()
        )
    )

    @Test
    fun `Named entity intprod with a semicolon`() = assertTokenization(
        input = "&intprod;",
        expectedTokens = listOf(
            char('⨼')
        )
    )

    @Test
    fun `Bad named entity iocy without a semicolon`() = assertTokenization(
        input = "&iocy",
        expectedTokens = listOf(
            *chars("&iocy").toTypedArray()
        )
    )

    @Test
    fun `Named entity iocy with a semicolon`() = assertTokenization(
        input = "&iocy;",
        expectedTokens = listOf(
            char('ё')
        )
    )

    @Test
    fun `Bad named entity iogon without a semicolon`() = assertTokenization(
        input = "&iogon",
        expectedTokens = listOf(
            *chars("&iogon").toTypedArray()
        )
    )

    @Test
    fun `Named entity iogon with a semicolon`() = assertTokenization(
        input = "&iogon;",
        expectedTokens = listOf(
            char('į')
        )
    )

    @Test
    fun `Bad named entity iopf without a semicolon`() = assertTokenization(
        input = "&iopf",
        expectedTokens = listOf(
            *chars("&iopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity iopf with a semicolon`() = assertTokenization(
        input = "&iopf;",
        expectedTokens = listOf(
            *chars("𝕚").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity iota without a semicolon`() = assertTokenization(
        input = "&iota",
        expectedTokens = listOf(
            *chars("&iota").toTypedArray()
        )
    )

    @Test
    fun `Named entity iota with a semicolon`() = assertTokenization(
        input = "&iota;",
        expectedTokens = listOf(
            char('ι')
        )
    )

    @Test
    fun `Bad named entity iprod without a semicolon`() = assertTokenization(
        input = "&iprod",
        expectedTokens = listOf(
            *chars("&iprod").toTypedArray()
        )
    )

    @Test
    fun `Named entity iprod with a semicolon`() = assertTokenization(
        input = "&iprod;",
        expectedTokens = listOf(
            char('⨼')
        )
    )

    @Test
    fun `Named entity iquest without a semicolon`() = assertTokenization(
        input = "&iquest",
        expectedTokens = listOf(
            char('¿')
        )
    )

    @Test
    fun `Named entity iquest with a semicolon`() = assertTokenization(
        input = "&iquest;",
        expectedTokens = listOf(
            char('¿')
        )
    )

    @Test
    fun `Bad named entity iscr without a semicolon`() = assertTokenization(
        input = "&iscr",
        expectedTokens = listOf(
            *chars("&iscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity iscr with a semicolon`() = assertTokenization(
        input = "&iscr;",
        expectedTokens = listOf(
            *chars("𝒾").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity isin without a semicolon`() = assertTokenization(
        input = "&isin",
        expectedTokens = listOf(
            *chars("&isin").toTypedArray()
        )
    )

    @Test
    fun `Named entity isin with a semicolon`() = assertTokenization(
        input = "&isin;",
        expectedTokens = listOf(
            char('∈')
        )
    )

    @Test
    fun `Bad named entity isinE without a semicolon`() = assertTokenization(
        input = "&isinE",
        expectedTokens = listOf(
            *chars("&isinE").toTypedArray()
        )
    )

    @Test
    fun `Named entity isinE with a semicolon`() = assertTokenization(
        input = "&isinE;",
        expectedTokens = listOf(
            char('⋹')
        )
    )

    @Test
    fun `Bad named entity isindot without a semicolon`() = assertTokenization(
        input = "&isindot",
        expectedTokens = listOf(
            *chars("&isindot").toTypedArray()
        )
    )

    @Test
    fun `Named entity isindot with a semicolon`() = assertTokenization(
        input = "&isindot;",
        expectedTokens = listOf(
            char('⋵')
        )
    )

    @Test
    fun `Bad named entity isins without a semicolon`() = assertTokenization(
        input = "&isins",
        expectedTokens = listOf(
            *chars("&isins").toTypedArray()
        )
    )

    @Test
    fun `Named entity isins with a semicolon`() = assertTokenization(
        input = "&isins;",
        expectedTokens = listOf(
            char('⋴')
        )
    )

    @Test
    fun `Bad named entity isinsv without a semicolon`() = assertTokenization(
        input = "&isinsv",
        expectedTokens = listOf(
            *chars("&isinsv").toTypedArray()
        )
    )

    @Test
    fun `Named entity isinsv with a semicolon`() = assertTokenization(
        input = "&isinsv;",
        expectedTokens = listOf(
            char('⋳')
        )
    )

    @Test
    fun `Bad named entity isinv without a semicolon`() = assertTokenization(
        input = "&isinv",
        expectedTokens = listOf(
            *chars("&isinv").toTypedArray()
        )
    )

    @Test
    fun `Named entity isinv with a semicolon`() = assertTokenization(
        input = "&isinv;",
        expectedTokens = listOf(
            char('∈')
        )
    )

    @Test
    fun `Bad named entity it without a semicolon`() = assertTokenization(
        input = "&it",
        expectedTokens = listOf(
            *chars("&it").toTypedArray()
        )
    )

    @Test
    fun `Named entity it with a semicolon`() = assertTokenization(
        input = "&it;",
        expectedTokens = listOf(
            char('⁢')
        )
    )

    @Test
    fun `Bad named entity itilde without a semicolon`() = assertTokenization(
        input = "&itilde",
        expectedTokens = listOf(
            *chars("&itilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity itilde with a semicolon`() = assertTokenization(
        input = "&itilde;",
        expectedTokens = listOf(
            char('ĩ')
        )
    )

    @Test
    fun `Bad named entity iukcy without a semicolon`() = assertTokenization(
        input = "&iukcy",
        expectedTokens = listOf(
            *chars("&iukcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity iukcy with a semicolon`() = assertTokenization(
        input = "&iukcy;",
        expectedTokens = listOf(
            char('і')
        )
    )

    @Test
    fun `Named entity iuml without a semicolon`() = assertTokenization(
        input = "&iuml",
        expectedTokens = listOf(
            char('ï')
        )
    )

    @Test
    fun `Named entity iuml with a semicolon`() = assertTokenization(
        input = "&iuml;",
        expectedTokens = listOf(
            char('ï')
        )
    )

    @Test
    fun `Bad named entity jcirc without a semicolon`() = assertTokenization(
        input = "&jcirc",
        expectedTokens = listOf(
            *chars("&jcirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity jcirc with a semicolon`() = assertTokenization(
        input = "&jcirc;",
        expectedTokens = listOf(
            char('ĵ')
        )
    )

    @Test
    fun `Bad named entity jcy without a semicolon`() = assertTokenization(
        input = "&jcy",
        expectedTokens = listOf(
            *chars("&jcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity jcy with a semicolon`() = assertTokenization(
        input = "&jcy;",
        expectedTokens = listOf(
            char('й')
        )
    )

    @Test
    fun `Bad named entity jfr without a semicolon`() = assertTokenization(
        input = "&jfr",
        expectedTokens = listOf(
            *chars("&jfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity jfr with a semicolon`() = assertTokenization(
        input = "&jfr;",
        expectedTokens = listOf(
            *chars("𝔧").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity jmath without a semicolon`() = assertTokenization(
        input = "&jmath",
        expectedTokens = listOf(
            *chars("&jmath").toTypedArray()
        )
    )

    @Test
    fun `Named entity jmath with a semicolon`() = assertTokenization(
        input = "&jmath;",
        expectedTokens = listOf(
            char('ȷ')
        )
    )

    @Test
    fun `Bad named entity jopf without a semicolon`() = assertTokenization(
        input = "&jopf",
        expectedTokens = listOf(
            *chars("&jopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity jopf with a semicolon`() = assertTokenization(
        input = "&jopf;",
        expectedTokens = listOf(
            *chars("𝕛").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity jscr without a semicolon`() = assertTokenization(
        input = "&jscr",
        expectedTokens = listOf(
            *chars("&jscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity jscr with a semicolon`() = assertTokenization(
        input = "&jscr;",
        expectedTokens = listOf(
            *chars("𝒿").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity jsercy without a semicolon`() = assertTokenization(
        input = "&jsercy",
        expectedTokens = listOf(
            *chars("&jsercy").toTypedArray()
        )
    )

    @Test
    fun `Named entity jsercy with a semicolon`() = assertTokenization(
        input = "&jsercy;",
        expectedTokens = listOf(
            char('ј')
        )
    )

    @Test
    fun `Bad named entity jukcy without a semicolon`() = assertTokenization(
        input = "&jukcy",
        expectedTokens = listOf(
            *chars("&jukcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity jukcy with a semicolon`() = assertTokenization(
        input = "&jukcy;",
        expectedTokens = listOf(
            char('є')
        )
    )

    @Test
    fun `Bad named entity kappa without a semicolon`() = assertTokenization(
        input = "&kappa",
        expectedTokens = listOf(
            *chars("&kappa").toTypedArray()
        )
    )

    @Test
    fun `Named entity kappa with a semicolon`() = assertTokenization(
        input = "&kappa;",
        expectedTokens = listOf(
            char('κ')
        )
    )

    @Test
    fun `Bad named entity kappav without a semicolon`() = assertTokenization(
        input = "&kappav",
        expectedTokens = listOf(
            *chars("&kappav").toTypedArray()
        )
    )

    @Test
    fun `Named entity kappav with a semicolon`() = assertTokenization(
        input = "&kappav;",
        expectedTokens = listOf(
            char('ϰ')
        )
    )

    @Test
    fun `Bad named entity kcedil without a semicolon`() = assertTokenization(
        input = "&kcedil",
        expectedTokens = listOf(
            *chars("&kcedil").toTypedArray()
        )
    )

    @Test
    fun `Named entity kcedil with a semicolon`() = assertTokenization(
        input = "&kcedil;",
        expectedTokens = listOf(
            char('ķ')
        )
    )

    @Test
    fun `Bad named entity kcy without a semicolon`() = assertTokenization(
        input = "&kcy",
        expectedTokens = listOf(
            *chars("&kcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity kcy with a semicolon`() = assertTokenization(
        input = "&kcy;",
        expectedTokens = listOf(
            char('к')
        )
    )

    @Test
    fun `Bad named entity kfr without a semicolon`() = assertTokenization(
        input = "&kfr",
        expectedTokens = listOf(
            *chars("&kfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity kfr with a semicolon`() = assertTokenization(
        input = "&kfr;",
        expectedTokens = listOf(
            *chars("𝔨").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity kgreen without a semicolon`() = assertTokenization(
        input = "&kgreen",
        expectedTokens = listOf(
            *chars("&kgreen").toTypedArray()
        )
    )

    @Test
    fun `Named entity kgreen with a semicolon`() = assertTokenization(
        input = "&kgreen;",
        expectedTokens = listOf(
            char('ĸ')
        )
    )

    @Test
    fun `Bad named entity khcy without a semicolon`() = assertTokenization(
        input = "&khcy",
        expectedTokens = listOf(
            *chars("&khcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity khcy with a semicolon`() = assertTokenization(
        input = "&khcy;",
        expectedTokens = listOf(
            char('х')
        )
    )

    @Test
    fun `Bad named entity kjcy without a semicolon`() = assertTokenization(
        input = "&kjcy",
        expectedTokens = listOf(
            *chars("&kjcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity kjcy with a semicolon`() = assertTokenization(
        input = "&kjcy;",
        expectedTokens = listOf(
            char('ќ')
        )
    )

    @Test
    fun `Bad named entity kopf without a semicolon`() = assertTokenization(
        input = "&kopf",
        expectedTokens = listOf(
            *chars("&kopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity kopf with a semicolon`() = assertTokenization(
        input = "&kopf;",
        expectedTokens = listOf(
            *chars("𝕜").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity kscr without a semicolon`() = assertTokenization(
        input = "&kscr",
        expectedTokens = listOf(
            *chars("&kscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity kscr with a semicolon`() = assertTokenization(
        input = "&kscr;",
        expectedTokens = listOf(
            *chars("𝓀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity lAarr without a semicolon`() = assertTokenization(
        input = "&lAarr",
        expectedTokens = listOf(
            *chars("&lAarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity lAarr with a semicolon`() = assertTokenization(
        input = "&lAarr;",
        expectedTokens = listOf(
            char('⇚')
        )
    )

    @Test
    fun `Bad named entity lArr without a semicolon`() = assertTokenization(
        input = "&lArr",
        expectedTokens = listOf(
            *chars("&lArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity lArr with a semicolon`() = assertTokenization(
        input = "&lArr;",
        expectedTokens = listOf(
            char('⇐')
        )
    )

    @Test
    fun `Bad named entity lAtail without a semicolon`() = assertTokenization(
        input = "&lAtail",
        expectedTokens = listOf(
            *chars("&lAtail").toTypedArray()
        )
    )

    @Test
    fun `Named entity lAtail with a semicolon`() = assertTokenization(
        input = "&lAtail;",
        expectedTokens = listOf(
            char('⤛')
        )
    )

    @Test
    fun `Bad named entity lBarr without a semicolon`() = assertTokenization(
        input = "&lBarr",
        expectedTokens = listOf(
            *chars("&lBarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity lBarr with a semicolon`() = assertTokenization(
        input = "&lBarr;",
        expectedTokens = listOf(
            char('⤎')
        )
    )

    @Test
    fun `Bad named entity lE without a semicolon`() = assertTokenization(
        input = "&lE",
        expectedTokens = listOf(
            *chars("&lE").toTypedArray()
        )
    )

    @Test
    fun `Named entity lE with a semicolon`() = assertTokenization(
        input = "&lE;",
        expectedTokens = listOf(
            char('≦')
        )
    )

    @Test
    fun `Bad named entity lEg without a semicolon`() = assertTokenization(
        input = "&lEg",
        expectedTokens = listOf(
            *chars("&lEg").toTypedArray()
        )
    )

    @Test
    fun `Named entity lEg with a semicolon`() = assertTokenization(
        input = "&lEg;",
        expectedTokens = listOf(
            char('⪋')
        )
    )

    @Test
    fun `Bad named entity lHar without a semicolon`() = assertTokenization(
        input = "&lHar",
        expectedTokens = listOf(
            *chars("&lHar").toTypedArray()
        )
    )

    @Test
    fun `Named entity lHar with a semicolon`() = assertTokenization(
        input = "&lHar;",
        expectedTokens = listOf(
            char('⥢')
        )
    )

    @Test
    fun `Bad named entity lacute without a semicolon`() = assertTokenization(
        input = "&lacute",
        expectedTokens = listOf(
            *chars("&lacute").toTypedArray()
        )
    )

    @Test
    fun `Named entity lacute with a semicolon`() = assertTokenization(
        input = "&lacute;",
        expectedTokens = listOf(
            char('ĺ')
        )
    )

    @Test
    fun `Bad named entity laemptyv without a semicolon`() = assertTokenization(
        input = "&laemptyv",
        expectedTokens = listOf(
            *chars("&laemptyv").toTypedArray()
        )
    )

    @Test
    fun `Named entity laemptyv with a semicolon`() = assertTokenization(
        input = "&laemptyv;",
        expectedTokens = listOf(
            char('⦴')
        )
    )

    @Test
    fun `Bad named entity lagran without a semicolon`() = assertTokenization(
        input = "&lagran",
        expectedTokens = listOf(
            *chars("&lagran").toTypedArray()
        )
    )

    @Test
    fun `Named entity lagran with a semicolon`() = assertTokenization(
        input = "&lagran;",
        expectedTokens = listOf(
            char('ℒ')
        )
    )

    @Test
    fun `Bad named entity lambda without a semicolon`() = assertTokenization(
        input = "&lambda",
        expectedTokens = listOf(
            *chars("&lambda").toTypedArray()
        )
    )

    @Test
    fun `Named entity lambda with a semicolon`() = assertTokenization(
        input = "&lambda;",
        expectedTokens = listOf(
            char('λ')
        )
    )

    @Test
    fun `Bad named entity lang without a semicolon`() = assertTokenization(
        input = "&lang",
        expectedTokens = listOf(
            *chars("&lang").toTypedArray()
        )
    )

    @Test
    fun `Named entity lang with a semicolon`() = assertTokenization(
        input = "&lang;",
        expectedTokens = listOf(
            char('⟨')
        )
    )

    @Test
    fun `Bad named entity langd without a semicolon`() = assertTokenization(
        input = "&langd",
        expectedTokens = listOf(
            *chars("&langd").toTypedArray()
        )
    )

    @Test
    fun `Named entity langd with a semicolon`() = assertTokenization(
        input = "&langd;",
        expectedTokens = listOf(
            char('⦑')
        )
    )

    @Test
    fun `Bad named entity langle without a semicolon`() = assertTokenization(
        input = "&langle",
        expectedTokens = listOf(
            *chars("&langle").toTypedArray()
        )
    )

    @Test
    fun `Named entity langle with a semicolon`() = assertTokenization(
        input = "&langle;",
        expectedTokens = listOf(
            char('⟨')
        )
    )

    @Test
    fun `Bad named entity lap without a semicolon`() = assertTokenization(
        input = "&lap",
        expectedTokens = listOf(
            *chars("&lap").toTypedArray()
        )
    )

    @Test
    fun `Named entity lap with a semicolon`() = assertTokenization(
        input = "&lap;",
        expectedTokens = listOf(
            char('⪅')
        )
    )

    @Test
    fun `Named entity laquo without a semicolon`() = assertTokenization(
        input = "&laquo",
        expectedTokens = listOf(
            char('«')
        )
    )

    @Test
    fun `Named entity laquo with a semicolon`() = assertTokenization(
        input = "&laquo;",
        expectedTokens = listOf(
            char('«')
        )
    )

    @Test
    fun `Bad named entity larr without a semicolon`() = assertTokenization(
        input = "&larr",
        expectedTokens = listOf(
            *chars("&larr").toTypedArray()
        )
    )

    @Test
    fun `Named entity larr with a semicolon`() = assertTokenization(
        input = "&larr;",
        expectedTokens = listOf(
            char('←')
        )
    )

    @Test
    fun `Bad named entity larrb without a semicolon`() = assertTokenization(
        input = "&larrb",
        expectedTokens = listOf(
            *chars("&larrb").toTypedArray()
        )
    )

    @Test
    fun `Named entity larrb with a semicolon`() = assertTokenization(
        input = "&larrb;",
        expectedTokens = listOf(
            char('⇤')
        )
    )

    @Test
    fun `Bad named entity larrbfs without a semicolon`() = assertTokenization(
        input = "&larrbfs",
        expectedTokens = listOf(
            *chars("&larrbfs").toTypedArray()
        )
    )

    @Test
    fun `Named entity larrbfs with a semicolon`() = assertTokenization(
        input = "&larrbfs;",
        expectedTokens = listOf(
            char('⤟')
        )
    )

    @Test
    fun `Bad named entity larrfs without a semicolon`() = assertTokenization(
        input = "&larrfs",
        expectedTokens = listOf(
            *chars("&larrfs").toTypedArray()
        )
    )

    @Test
    fun `Named entity larrfs with a semicolon`() = assertTokenization(
        input = "&larrfs;",
        expectedTokens = listOf(
            char('⤝')
        )
    )

    @Test
    fun `Bad named entity larrhk without a semicolon`() = assertTokenization(
        input = "&larrhk",
        expectedTokens = listOf(
            *chars("&larrhk").toTypedArray()
        )
    )

    @Test
    fun `Named entity larrhk with a semicolon`() = assertTokenization(
        input = "&larrhk;",
        expectedTokens = listOf(
            char('↩')
        )
    )

    @Test
    fun `Bad named entity larrlp without a semicolon`() = assertTokenization(
        input = "&larrlp",
        expectedTokens = listOf(
            *chars("&larrlp").toTypedArray()
        )
    )

    @Test
    fun `Named entity larrlp with a semicolon`() = assertTokenization(
        input = "&larrlp;",
        expectedTokens = listOf(
            char('↫')
        )
    )

    @Test
    fun `Bad named entity larrpl without a semicolon`() = assertTokenization(
        input = "&larrpl",
        expectedTokens = listOf(
            *chars("&larrpl").toTypedArray()
        )
    )

    @Test
    fun `Named entity larrpl with a semicolon`() = assertTokenization(
        input = "&larrpl;",
        expectedTokens = listOf(
            char('⤹')
        )
    )

    @Test
    fun `Bad named entity larrsim without a semicolon`() = assertTokenization(
        input = "&larrsim",
        expectedTokens = listOf(
            *chars("&larrsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity larrsim with a semicolon`() = assertTokenization(
        input = "&larrsim;",
        expectedTokens = listOf(
            char('⥳')
        )
    )

    @Test
    fun `Bad named entity larrtl without a semicolon`() = assertTokenization(
        input = "&larrtl",
        expectedTokens = listOf(
            *chars("&larrtl").toTypedArray()
        )
    )

    @Test
    fun `Named entity larrtl with a semicolon`() = assertTokenization(
        input = "&larrtl;",
        expectedTokens = listOf(
            char('↢')
        )
    )

    @Test
    fun `Bad named entity lat without a semicolon`() = assertTokenization(
        input = "&lat",
        expectedTokens = listOf(
            *chars("&lat").toTypedArray()
        )
    )

    @Test
    fun `Named entity lat with a semicolon`() = assertTokenization(
        input = "&lat;",
        expectedTokens = listOf(
            char('⪫')
        )
    )

    @Test
    fun `Bad named entity latail without a semicolon`() = assertTokenization(
        input = "&latail",
        expectedTokens = listOf(
            *chars("&latail").toTypedArray()
        )
    )

    @Test
    fun `Named entity latail with a semicolon`() = assertTokenization(
        input = "&latail;",
        expectedTokens = listOf(
            char('⤙')
        )
    )

    @Test
    fun `Bad named entity late without a semicolon`() = assertTokenization(
        input = "&late",
        expectedTokens = listOf(
            *chars("&late").toTypedArray()
        )
    )

    @Test
    fun `Named entity late with a semicolon`() = assertTokenization(
        input = "&late;",
        expectedTokens = listOf(
            char('⪭')
        )
    )

    @Test
    fun `Bad named entity lates without a semicolon`() = assertTokenization(
        input = "&lates",
        expectedTokens = listOf(
            *chars("&lates").toTypedArray()
        )
    )

    @Test
    fun `Named entity lates with a semicolon`() = assertTokenization(
        input = "&lates;",
        expectedTokens = listOf(
            *chars("⪭︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity lbarr without a semicolon`() = assertTokenization(
        input = "&lbarr",
        expectedTokens = listOf(
            *chars("&lbarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity lbarr with a semicolon`() = assertTokenization(
        input = "&lbarr;",
        expectedTokens = listOf(
            char('⤌')
        )
    )

    @Test
    fun `Bad named entity lbbrk without a semicolon`() = assertTokenization(
        input = "&lbbrk",
        expectedTokens = listOf(
            *chars("&lbbrk").toTypedArray()
        )
    )

    @Test
    fun `Named entity lbbrk with a semicolon`() = assertTokenization(
        input = "&lbbrk;",
        expectedTokens = listOf(
            char('❲')
        )
    )

    @Test
    fun `Bad named entity lbrace without a semicolon`() = assertTokenization(
        input = "&lbrace",
        expectedTokens = listOf(
            *chars("&lbrace").toTypedArray()
        )
    )

    @Test
    fun `Named entity lbrace with a semicolon`() = assertTokenization(
        input = "&lbrace;",
        expectedTokens = listOf(
            char('{')
        )
    )

    @Test
    fun `Bad named entity lbrack without a semicolon`() = assertTokenization(
        input = "&lbrack",
        expectedTokens = listOf(
            *chars("&lbrack").toTypedArray()
        )
    )

    @Test
    fun `Named entity lbrack with a semicolon`() = assertTokenization(
        input = "&lbrack;",
        expectedTokens = listOf(
            char('[')
        )
    )

    @Test
    fun `Bad named entity lbrke without a semicolon`() = assertTokenization(
        input = "&lbrke",
        expectedTokens = listOf(
            *chars("&lbrke").toTypedArray()
        )
    )

    @Test
    fun `Named entity lbrke with a semicolon`() = assertTokenization(
        input = "&lbrke;",
        expectedTokens = listOf(
            char('⦋')
        )
    )

    @Test
    fun `Bad named entity lbrksld without a semicolon`() = assertTokenization(
        input = "&lbrksld",
        expectedTokens = listOf(
            *chars("&lbrksld").toTypedArray()
        )
    )

    @Test
    fun `Named entity lbrksld with a semicolon`() = assertTokenization(
        input = "&lbrksld;",
        expectedTokens = listOf(
            char('⦏')
        )
    )

    @Test
    fun `Bad named entity lbrkslu without a semicolon`() = assertTokenization(
        input = "&lbrkslu",
        expectedTokens = listOf(
            *chars("&lbrkslu").toTypedArray()
        )
    )

    @Test
    fun `Named entity lbrkslu with a semicolon`() = assertTokenization(
        input = "&lbrkslu;",
        expectedTokens = listOf(
            char('⦍')
        )
    )

    @Test
    fun `Bad named entity lcaron without a semicolon`() = assertTokenization(
        input = "&lcaron",
        expectedTokens = listOf(
            *chars("&lcaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity lcaron with a semicolon`() = assertTokenization(
        input = "&lcaron;",
        expectedTokens = listOf(
            char('ľ')
        )
    )

    @Test
    fun `Bad named entity lcedil without a semicolon`() = assertTokenization(
        input = "&lcedil",
        expectedTokens = listOf(
            *chars("&lcedil").toTypedArray()
        )
    )

    @Test
    fun `Named entity lcedil with a semicolon`() = assertTokenization(
        input = "&lcedil;",
        expectedTokens = listOf(
            char('ļ')
        )
    )

    @Test
    fun `Bad named entity lceil without a semicolon`() = assertTokenization(
        input = "&lceil",
        expectedTokens = listOf(
            *chars("&lceil").toTypedArray()
        )
    )

    @Test
    fun `Named entity lceil with a semicolon`() = assertTokenization(
        input = "&lceil;",
        expectedTokens = listOf(
            char('⌈')
        )
    )

    @Test
    fun `Bad named entity lcub without a semicolon`() = assertTokenization(
        input = "&lcub",
        expectedTokens = listOf(
            *chars("&lcub").toTypedArray()
        )
    )

    @Test
    fun `Named entity lcub with a semicolon`() = assertTokenization(
        input = "&lcub;",
        expectedTokens = listOf(
            char('{')
        )
    )

    @Test
    fun `Bad named entity lcy without a semicolon`() = assertTokenization(
        input = "&lcy",
        expectedTokens = listOf(
            *chars("&lcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity lcy with a semicolon`() = assertTokenization(
        input = "&lcy;",
        expectedTokens = listOf(
            char('л')
        )
    )

    @Test
    fun `Bad named entity ldca without a semicolon`() = assertTokenization(
        input = "&ldca",
        expectedTokens = listOf(
            *chars("&ldca").toTypedArray()
        )
    )

    @Test
    fun `Named entity ldca with a semicolon`() = assertTokenization(
        input = "&ldca;",
        expectedTokens = listOf(
            char('⤶')
        )
    )

    @Test
    fun `Bad named entity ldquo without a semicolon`() = assertTokenization(
        input = "&ldquo",
        expectedTokens = listOf(
            *chars("&ldquo").toTypedArray()
        )
    )

    @Test
    fun `Named entity ldquo with a semicolon`() = assertTokenization(
        input = "&ldquo;",
        expectedTokens = listOf(
            char('“')
        )
    )

    @Test
    fun `Bad named entity ldquor without a semicolon`() = assertTokenization(
        input = "&ldquor",
        expectedTokens = listOf(
            *chars("&ldquor").toTypedArray()
        )
    )

    @Test
    fun `Named entity ldquor with a semicolon`() = assertTokenization(
        input = "&ldquor;",
        expectedTokens = listOf(
            char('„')
        )
    )

    @Test
    fun `Bad named entity ldrdhar without a semicolon`() = assertTokenization(
        input = "&ldrdhar",
        expectedTokens = listOf(
            *chars("&ldrdhar").toTypedArray()
        )
    )

    @Test
    fun `Named entity ldrdhar with a semicolon`() = assertTokenization(
        input = "&ldrdhar;",
        expectedTokens = listOf(
            char('⥧')
        )
    )

    @Test
    fun `Bad named entity ldrushar without a semicolon`() = assertTokenization(
        input = "&ldrushar",
        expectedTokens = listOf(
            *chars("&ldrushar").toTypedArray()
        )
    )

    @Test
    fun `Named entity ldrushar with a semicolon`() = assertTokenization(
        input = "&ldrushar;",
        expectedTokens = listOf(
            char('⥋')
        )
    )

    @Test
    fun `Bad named entity ldsh without a semicolon`() = assertTokenization(
        input = "&ldsh",
        expectedTokens = listOf(
            *chars("&ldsh").toTypedArray()
        )
    )

    @Test
    fun `Named entity ldsh with a semicolon`() = assertTokenization(
        input = "&ldsh;",
        expectedTokens = listOf(
            char('↲')
        )
    )

    @Test
    fun `Bad named entity le without a semicolon`() = assertTokenization(
        input = "&le",
        expectedTokens = listOf(
            *chars("&le").toTypedArray()
        )
    )

    @Test
    fun `Named entity le with a semicolon`() = assertTokenization(
        input = "&le;",
        expectedTokens = listOf(
            char('≤')
        )
    )

    @Test
    fun `Bad named entity leftarrow without a semicolon`() = assertTokenization(
        input = "&leftarrow",
        expectedTokens = listOf(
            *chars("&leftarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity leftarrow with a semicolon`() = assertTokenization(
        input = "&leftarrow;",
        expectedTokens = listOf(
            char('←')
        )
    )

    @Test
    fun `Bad named entity leftarrowtail without a semicolon`() = assertTokenization(
        input = "&leftarrowtail",
        expectedTokens = listOf(
            *chars("&leftarrowtail").toTypedArray()
        )
    )

    @Test
    fun `Named entity leftarrowtail with a semicolon`() = assertTokenization(
        input = "&leftarrowtail;",
        expectedTokens = listOf(
            char('↢')
        )
    )

    @Test
    fun `Bad named entity leftharpoondown without a semicolon`() = assertTokenization(
        input = "&leftharpoondown",
        expectedTokens = listOf(
            *chars("&leftharpoondown").toTypedArray()
        )
    )

    @Test
    fun `Named entity leftharpoondown with a semicolon`() = assertTokenization(
        input = "&leftharpoondown;",
        expectedTokens = listOf(
            char('↽')
        )
    )

    @Test
    fun `Bad named entity leftharpoonup without a semicolon`() = assertTokenization(
        input = "&leftharpoonup",
        expectedTokens = listOf(
            *chars("&leftharpoonup").toTypedArray()
        )
    )

    @Test
    fun `Named entity leftharpoonup with a semicolon`() = assertTokenization(
        input = "&leftharpoonup;",
        expectedTokens = listOf(
            char('↼')
        )
    )

    @Test
    fun `Bad named entity leftleftarrows without a semicolon`() = assertTokenization(
        input = "&leftleftarrows",
        expectedTokens = listOf(
            *chars("&leftleftarrows").toTypedArray()
        )
    )

    @Test
    fun `Named entity leftleftarrows with a semicolon`() = assertTokenization(
        input = "&leftleftarrows;",
        expectedTokens = listOf(
            char('⇇')
        )
    )

    @Test
    fun `Bad named entity leftrightarrow without a semicolon`() = assertTokenization(
        input = "&leftrightarrow",
        expectedTokens = listOf(
            *chars("&leftrightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity leftrightarrow with a semicolon`() = assertTokenization(
        input = "&leftrightarrow;",
        expectedTokens = listOf(
            char('↔')
        )
    )

    @Test
    fun `Bad named entity leftrightarrows without a semicolon`() = assertTokenization(
        input = "&leftrightarrows",
        expectedTokens = listOf(
            *chars("&leftrightarrows").toTypedArray()
        )
    )

    @Test
    fun `Named entity leftrightarrows with a semicolon`() = assertTokenization(
        input = "&leftrightarrows;",
        expectedTokens = listOf(
            char('⇆')
        )
    )

    @Test
    fun `Bad named entity leftrightharpoons without a semicolon`() = assertTokenization(
        input = "&leftrightharpoons",
        expectedTokens = listOf(
            *chars("&leftrightharpoons").toTypedArray()
        )
    )

    @Test
    fun `Named entity leftrightharpoons with a semicolon`() = assertTokenization(
        input = "&leftrightharpoons;",
        expectedTokens = listOf(
            char('⇋')
        )
    )

    @Test
    fun `Bad named entity leftrightsquigarrow without a semicolon`() = assertTokenization(
        input = "&leftrightsquigarrow",
        expectedTokens = listOf(
            *chars("&leftrightsquigarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity leftrightsquigarrow with a semicolon`() = assertTokenization(
        input = "&leftrightsquigarrow;",
        expectedTokens = listOf(
            char('↭')
        )
    )

    @Test
    fun `Bad named entity leftthreetimes without a semicolon`() = assertTokenization(
        input = "&leftthreetimes",
        expectedTokens = listOf(
            *chars("&leftthreetimes").toTypedArray()
        )
    )

    @Test
    fun `Named entity leftthreetimes with a semicolon`() = assertTokenization(
        input = "&leftthreetimes;",
        expectedTokens = listOf(
            char('⋋')
        )
    )

    @Test
    fun `Bad named entity leg without a semicolon`() = assertTokenization(
        input = "&leg",
        expectedTokens = listOf(
            *chars("&leg").toTypedArray()
        )
    )

    @Test
    fun `Named entity leg with a semicolon`() = assertTokenization(
        input = "&leg;",
        expectedTokens = listOf(
            char('⋚')
        )
    )

    @Test
    fun `Bad named entity leq without a semicolon`() = assertTokenization(
        input = "&leq",
        expectedTokens = listOf(
            *chars("&leq").toTypedArray()
        )
    )

    @Test
    fun `Named entity leq with a semicolon`() = assertTokenization(
        input = "&leq;",
        expectedTokens = listOf(
            char('≤')
        )
    )

    @Test
    fun `Bad named entity leqq without a semicolon`() = assertTokenization(
        input = "&leqq",
        expectedTokens = listOf(
            *chars("&leqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity leqq with a semicolon`() = assertTokenization(
        input = "&leqq;",
        expectedTokens = listOf(
            char('≦')
        )
    )

    @Test
    fun `Bad named entity leqslant without a semicolon`() = assertTokenization(
        input = "&leqslant",
        expectedTokens = listOf(
            *chars("&leqslant").toTypedArray()
        )
    )

    @Test
    fun `Named entity leqslant with a semicolon`() = assertTokenization(
        input = "&leqslant;",
        expectedTokens = listOf(
            char('⩽')
        )
    )

    @Test
    fun `Bad named entity les without a semicolon`() = assertTokenization(
        input = "&les",
        expectedTokens = listOf(
            *chars("&les").toTypedArray()
        )
    )

    @Test
    fun `Named entity les with a semicolon`() = assertTokenization(
        input = "&les;",
        expectedTokens = listOf(
            char('⩽')
        )
    )

    @Test
    fun `Bad named entity lescc without a semicolon`() = assertTokenization(
        input = "&lescc",
        expectedTokens = listOf(
            *chars("&lescc").toTypedArray()
        )
    )

    @Test
    fun `Named entity lescc with a semicolon`() = assertTokenization(
        input = "&lescc;",
        expectedTokens = listOf(
            char('⪨')
        )
    )

    @Test
    fun `Bad named entity lesdot without a semicolon`() = assertTokenization(
        input = "&lesdot",
        expectedTokens = listOf(
            *chars("&lesdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity lesdot with a semicolon`() = assertTokenization(
        input = "&lesdot;",
        expectedTokens = listOf(
            char('⩿')
        )
    )

    @Test
    fun `Bad named entity lesdoto without a semicolon`() = assertTokenization(
        input = "&lesdoto",
        expectedTokens = listOf(
            *chars("&lesdoto").toTypedArray()
        )
    )

    @Test
    fun `Named entity lesdoto with a semicolon`() = assertTokenization(
        input = "&lesdoto;",
        expectedTokens = listOf(
            char('⪁')
        )
    )

    @Test
    fun `Bad named entity lesdotor without a semicolon`() = assertTokenization(
        input = "&lesdotor",
        expectedTokens = listOf(
            *chars("&lesdotor").toTypedArray()
        )
    )

    @Test
    fun `Named entity lesdotor with a semicolon`() = assertTokenization(
        input = "&lesdotor;",
        expectedTokens = listOf(
            char('⪃')
        )
    )

    @Test
    fun `Bad named entity lesg without a semicolon`() = assertTokenization(
        input = "&lesg",
        expectedTokens = listOf(
            *chars("&lesg").toTypedArray()
        )
    )

    @Test
    fun `Named entity lesg with a semicolon`() = assertTokenization(
        input = "&lesg;",
        expectedTokens = listOf(
            *chars("⋚︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity lesges without a semicolon`() = assertTokenization(
        input = "&lesges",
        expectedTokens = listOf(
            *chars("&lesges").toTypedArray()
        )
    )

    @Test
    fun `Named entity lesges with a semicolon`() = assertTokenization(
        input = "&lesges;",
        expectedTokens = listOf(
            char('⪓')
        )
    )

    @Test
    fun `Bad named entity lessapprox without a semicolon`() = assertTokenization(
        input = "&lessapprox",
        expectedTokens = listOf(
            *chars("&lessapprox").toTypedArray()
        )
    )

    @Test
    fun `Named entity lessapprox with a semicolon`() = assertTokenization(
        input = "&lessapprox;",
        expectedTokens = listOf(
            char('⪅')
        )
    )

    @Test
    fun `Bad named entity lessdot without a semicolon`() = assertTokenization(
        input = "&lessdot",
        expectedTokens = listOf(
            *chars("&lessdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity lessdot with a semicolon`() = assertTokenization(
        input = "&lessdot;",
        expectedTokens = listOf(
            char('⋖')
        )
    )

    @Test
    fun `Bad named entity lesseqgtr without a semicolon`() = assertTokenization(
        input = "&lesseqgtr",
        expectedTokens = listOf(
            *chars("&lesseqgtr").toTypedArray()
        )
    )

    @Test
    fun `Named entity lesseqgtr with a semicolon`() = assertTokenization(
        input = "&lesseqgtr;",
        expectedTokens = listOf(
            char('⋚')
        )
    )

    @Test
    fun `Bad named entity lesseqqgtr without a semicolon`() = assertTokenization(
        input = "&lesseqqgtr",
        expectedTokens = listOf(
            *chars("&lesseqqgtr").toTypedArray()
        )
    )

    @Test
    fun `Named entity lesseqqgtr with a semicolon`() = assertTokenization(
        input = "&lesseqqgtr;",
        expectedTokens = listOf(
            char('⪋')
        )
    )

    @Test
    fun `Bad named entity lessgtr without a semicolon`() = assertTokenization(
        input = "&lessgtr",
        expectedTokens = listOf(
            *chars("&lessgtr").toTypedArray()
        )
    )

    @Test
    fun `Named entity lessgtr with a semicolon`() = assertTokenization(
        input = "&lessgtr;",
        expectedTokens = listOf(
            char('≶')
        )
    )

    @Test
    fun `Bad named entity lesssim without a semicolon`() = assertTokenization(
        input = "&lesssim",
        expectedTokens = listOf(
            *chars("&lesssim").toTypedArray()
        )
    )

    @Test
    fun `Named entity lesssim with a semicolon`() = assertTokenization(
        input = "&lesssim;",
        expectedTokens = listOf(
            char('≲')
        )
    )

    @Test
    fun `Bad named entity lfisht without a semicolon`() = assertTokenization(
        input = "&lfisht",
        expectedTokens = listOf(
            *chars("&lfisht").toTypedArray()
        )
    )

    @Test
    fun `Named entity lfisht with a semicolon`() = assertTokenization(
        input = "&lfisht;",
        expectedTokens = listOf(
            char('⥼')
        )
    )

    @Test
    fun `Bad named entity lfloor without a semicolon`() = assertTokenization(
        input = "&lfloor",
        expectedTokens = listOf(
            *chars("&lfloor").toTypedArray()
        )
    )

    @Test
    fun `Named entity lfloor with a semicolon`() = assertTokenization(
        input = "&lfloor;",
        expectedTokens = listOf(
            char('⌊')
        )
    )

    @Test
    fun `Bad named entity lfr without a semicolon`() = assertTokenization(
        input = "&lfr",
        expectedTokens = listOf(
            *chars("&lfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity lfr with a semicolon`() = assertTokenization(
        input = "&lfr;",
        expectedTokens = listOf(
            *chars("𝔩").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity lg without a semicolon`() = assertTokenization(
        input = "&lg",
        expectedTokens = listOf(
            *chars("&lg").toTypedArray()
        )
    )

    @Test
    fun `Named entity lg with a semicolon`() = assertTokenization(
        input = "&lg;",
        expectedTokens = listOf(
            char('≶')
        )
    )

    @Test
    fun `Bad named entity lgE without a semicolon`() = assertTokenization(
        input = "&lgE",
        expectedTokens = listOf(
            *chars("&lgE").toTypedArray()
        )
    )

    @Test
    fun `Named entity lgE with a semicolon`() = assertTokenization(
        input = "&lgE;",
        expectedTokens = listOf(
            char('⪑')
        )
    )

    @Test
    fun `Bad named entity lhard without a semicolon`() = assertTokenization(
        input = "&lhard",
        expectedTokens = listOf(
            *chars("&lhard").toTypedArray()
        )
    )

    @Test
    fun `Named entity lhard with a semicolon`() = assertTokenization(
        input = "&lhard;",
        expectedTokens = listOf(
            char('↽')
        )
    )

    @Test
    fun `Bad named entity lharu without a semicolon`() = assertTokenization(
        input = "&lharu",
        expectedTokens = listOf(
            *chars("&lharu").toTypedArray()
        )
    )

    @Test
    fun `Named entity lharu with a semicolon`() = assertTokenization(
        input = "&lharu;",
        expectedTokens = listOf(
            char('↼')
        )
    )

    @Test
    fun `Bad named entity lharul without a semicolon`() = assertTokenization(
        input = "&lharul",
        expectedTokens = listOf(
            *chars("&lharul").toTypedArray()
        )
    )

    @Test
    fun `Named entity lharul with a semicolon`() = assertTokenization(
        input = "&lharul;",
        expectedTokens = listOf(
            char('⥪')
        )
    )

    @Test
    fun `Bad named entity lhblk without a semicolon`() = assertTokenization(
        input = "&lhblk",
        expectedTokens = listOf(
            *chars("&lhblk").toTypedArray()
        )
    )

    @Test
    fun `Named entity lhblk with a semicolon`() = assertTokenization(
        input = "&lhblk;",
        expectedTokens = listOf(
            char('▄')
        )
    )

    @Test
    fun `Bad named entity ljcy without a semicolon`() = assertTokenization(
        input = "&ljcy",
        expectedTokens = listOf(
            *chars("&ljcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity ljcy with a semicolon`() = assertTokenization(
        input = "&ljcy;",
        expectedTokens = listOf(
            char('љ')
        )
    )

    @Test
    fun `Bad named entity ll without a semicolon`() = assertTokenization(
        input = "&ll",
        expectedTokens = listOf(
            *chars("&ll").toTypedArray()
        )
    )

    @Test
    fun `Named entity ll with a semicolon`() = assertTokenization(
        input = "&ll;",
        expectedTokens = listOf(
            char('≪')
        )
    )

    @Test
    fun `Bad named entity llarr without a semicolon`() = assertTokenization(
        input = "&llarr",
        expectedTokens = listOf(
            *chars("&llarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity llarr with a semicolon`() = assertTokenization(
        input = "&llarr;",
        expectedTokens = listOf(
            char('⇇')
        )
    )

    @Test
    fun `Bad named entity llcorner without a semicolon`() = assertTokenization(
        input = "&llcorner",
        expectedTokens = listOf(
            *chars("&llcorner").toTypedArray()
        )
    )

    @Test
    fun `Named entity llcorner with a semicolon`() = assertTokenization(
        input = "&llcorner;",
        expectedTokens = listOf(
            char('⌞')
        )
    )

    @Test
    fun `Bad named entity llhard without a semicolon`() = assertTokenization(
        input = "&llhard",
        expectedTokens = listOf(
            *chars("&llhard").toTypedArray()
        )
    )

    @Test
    fun `Named entity llhard with a semicolon`() = assertTokenization(
        input = "&llhard;",
        expectedTokens = listOf(
            char('⥫')
        )
    )

    @Test
    fun `Bad named entity lltri without a semicolon`() = assertTokenization(
        input = "&lltri",
        expectedTokens = listOf(
            *chars("&lltri").toTypedArray()
        )
    )

    @Test
    fun `Named entity lltri with a semicolon`() = assertTokenization(
        input = "&lltri;",
        expectedTokens = listOf(
            char('◺')
        )
    )

    @Test
    fun `Bad named entity lmidot without a semicolon`() = assertTokenization(
        input = "&lmidot",
        expectedTokens = listOf(
            *chars("&lmidot").toTypedArray()
        )
    )

    @Test
    fun `Named entity lmidot with a semicolon`() = assertTokenization(
        input = "&lmidot;",
        expectedTokens = listOf(
            char('ŀ')
        )
    )

    @Test
    fun `Bad named entity lmoust without a semicolon`() = assertTokenization(
        input = "&lmoust",
        expectedTokens = listOf(
            *chars("&lmoust").toTypedArray()
        )
    )

    @Test
    fun `Named entity lmoust with a semicolon`() = assertTokenization(
        input = "&lmoust;",
        expectedTokens = listOf(
            char('⎰')
        )
    )

    @Test
    fun `Bad named entity lmoustache without a semicolon`() = assertTokenization(
        input = "&lmoustache",
        expectedTokens = listOf(
            *chars("&lmoustache").toTypedArray()
        )
    )

    @Test
    fun `Named entity lmoustache with a semicolon`() = assertTokenization(
        input = "&lmoustache;",
        expectedTokens = listOf(
            char('⎰')
        )
    )

    @Test
    fun `Bad named entity lnE without a semicolon`() = assertTokenization(
        input = "&lnE",
        expectedTokens = listOf(
            *chars("&lnE").toTypedArray()
        )
    )

    @Test
    fun `Named entity lnE with a semicolon`() = assertTokenization(
        input = "&lnE;",
        expectedTokens = listOf(
            char('≨')
        )
    )

    @Test
    fun `Bad named entity lnap without a semicolon`() = assertTokenization(
        input = "&lnap",
        expectedTokens = listOf(
            *chars("&lnap").toTypedArray()
        )
    )

    @Test
    fun `Named entity lnap with a semicolon`() = assertTokenization(
        input = "&lnap;",
        expectedTokens = listOf(
            char('⪉')
        )
    )

    @Test
    fun `Bad named entity lnapprox without a semicolon`() = assertTokenization(
        input = "&lnapprox",
        expectedTokens = listOf(
            *chars("&lnapprox").toTypedArray()
        )
    )

    @Test
    fun `Named entity lnapprox with a semicolon`() = assertTokenization(
        input = "&lnapprox;",
        expectedTokens = listOf(
            char('⪉')
        )
    )

    @Test
    fun `Bad named entity lne without a semicolon`() = assertTokenization(
        input = "&lne",
        expectedTokens = listOf(
            *chars("&lne").toTypedArray()
        )
    )

    @Test
    fun `Named entity lne with a semicolon`() = assertTokenization(
        input = "&lne;",
        expectedTokens = listOf(
            char('⪇')
        )
    )

    @Test
    fun `Bad named entity lneq without a semicolon`() = assertTokenization(
        input = "&lneq",
        expectedTokens = listOf(
            *chars("&lneq").toTypedArray()
        )
    )

    @Test
    fun `Named entity lneq with a semicolon`() = assertTokenization(
        input = "&lneq;",
        expectedTokens = listOf(
            char('⪇')
        )
    )

    @Test
    fun `Bad named entity lneqq without a semicolon`() = assertTokenization(
        input = "&lneqq",
        expectedTokens = listOf(
            *chars("&lneqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity lneqq with a semicolon`() = assertTokenization(
        input = "&lneqq;",
        expectedTokens = listOf(
            char('≨')
        )
    )

    @Test
    fun `Bad named entity lnsim without a semicolon`() = assertTokenization(
        input = "&lnsim",
        expectedTokens = listOf(
            *chars("&lnsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity lnsim with a semicolon`() = assertTokenization(
        input = "&lnsim;",
        expectedTokens = listOf(
            char('⋦')
        )
    )

    @Test
    fun `Bad named entity loang without a semicolon`() = assertTokenization(
        input = "&loang",
        expectedTokens = listOf(
            *chars("&loang").toTypedArray()
        )
    )

    @Test
    fun `Named entity loang with a semicolon`() = assertTokenization(
        input = "&loang;",
        expectedTokens = listOf(
            char('⟬')
        )
    )

    @Test
    fun `Bad named entity loarr without a semicolon`() = assertTokenization(
        input = "&loarr",
        expectedTokens = listOf(
            *chars("&loarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity loarr with a semicolon`() = assertTokenization(
        input = "&loarr;",
        expectedTokens = listOf(
            char('⇽')
        )
    )

    @Test
    fun `Bad named entity lobrk without a semicolon`() = assertTokenization(
        input = "&lobrk",
        expectedTokens = listOf(
            *chars("&lobrk").toTypedArray()
        )
    )

    @Test
    fun `Named entity lobrk with a semicolon`() = assertTokenization(
        input = "&lobrk;",
        expectedTokens = listOf(
            char('⟦')
        )
    )

    @Test
    fun `Bad named entity longleftarrow without a semicolon`() = assertTokenization(
        input = "&longleftarrow",
        expectedTokens = listOf(
            *chars("&longleftarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity longleftarrow with a semicolon`() = assertTokenization(
        input = "&longleftarrow;",
        expectedTokens = listOf(
            char('⟵')
        )
    )

    @Test
    fun `Bad named entity longleftrightarrow without a semicolon`() = assertTokenization(
        input = "&longleftrightarrow",
        expectedTokens = listOf(
            *chars("&longleftrightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity longleftrightarrow with a semicolon`() = assertTokenization(
        input = "&longleftrightarrow;",
        expectedTokens = listOf(
            char('⟷')
        )
    )

    @Test
    fun `Bad named entity longmapsto without a semicolon`() = assertTokenization(
        input = "&longmapsto",
        expectedTokens = listOf(
            *chars("&longmapsto").toTypedArray()
        )
    )

    @Test
    fun `Named entity longmapsto with a semicolon`() = assertTokenization(
        input = "&longmapsto;",
        expectedTokens = listOf(
            char('⟼')
        )
    )

    @Test
    fun `Bad named entity longrightarrow without a semicolon`() = assertTokenization(
        input = "&longrightarrow",
        expectedTokens = listOf(
            *chars("&longrightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity longrightarrow with a semicolon`() = assertTokenization(
        input = "&longrightarrow;",
        expectedTokens = listOf(
            char('⟶')
        )
    )

    @Test
    fun `Bad named entity looparrowleft without a semicolon`() = assertTokenization(
        input = "&looparrowleft",
        expectedTokens = listOf(
            *chars("&looparrowleft").toTypedArray()
        )
    )

    @Test
    fun `Named entity looparrowleft with a semicolon`() = assertTokenization(
        input = "&looparrowleft;",
        expectedTokens = listOf(
            char('↫')
        )
    )

    @Test
    fun `Bad named entity looparrowright without a semicolon`() = assertTokenization(
        input = "&looparrowright",
        expectedTokens = listOf(
            *chars("&looparrowright").toTypedArray()
        )
    )

    @Test
    fun `Named entity looparrowright with a semicolon`() = assertTokenization(
        input = "&looparrowright;",
        expectedTokens = listOf(
            char('↬')
        )
    )

    @Test
    fun `Bad named entity lopar without a semicolon`() = assertTokenization(
        input = "&lopar",
        expectedTokens = listOf(
            *chars("&lopar").toTypedArray()
        )
    )

    @Test
    fun `Named entity lopar with a semicolon`() = assertTokenization(
        input = "&lopar;",
        expectedTokens = listOf(
            char('⦅')
        )
    )

    @Test
    fun `Bad named entity lopf without a semicolon`() = assertTokenization(
        input = "&lopf",
        expectedTokens = listOf(
            *chars("&lopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity lopf with a semicolon`() = assertTokenization(
        input = "&lopf;",
        expectedTokens = listOf(
            *chars("𝕝").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity loplus without a semicolon`() = assertTokenization(
        input = "&loplus",
        expectedTokens = listOf(
            *chars("&loplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity loplus with a semicolon`() = assertTokenization(
        input = "&loplus;",
        expectedTokens = listOf(
            char('⨭')
        )
    )

    @Test
    fun `Bad named entity lotimes without a semicolon`() = assertTokenization(
        input = "&lotimes",
        expectedTokens = listOf(
            *chars("&lotimes").toTypedArray()
        )
    )

    @Test
    fun `Named entity lotimes with a semicolon`() = assertTokenization(
        input = "&lotimes;",
        expectedTokens = listOf(
            char('⨴')
        )
    )

    @Test
    fun `Bad named entity lowast without a semicolon`() = assertTokenization(
        input = "&lowast",
        expectedTokens = listOf(
            *chars("&lowast").toTypedArray()
        )
    )

    @Test
    fun `Named entity lowast with a semicolon`() = assertTokenization(
        input = "&lowast;",
        expectedTokens = listOf(
            char('∗')
        )
    )

    @Test
    fun `Bad named entity lowbar without a semicolon`() = assertTokenization(
        input = "&lowbar",
        expectedTokens = listOf(
            *chars("&lowbar").toTypedArray()
        )
    )

    @Test
    fun `Named entity lowbar with a semicolon`() = assertTokenization(
        input = "&lowbar;",
        expectedTokens = listOf(
            char('_')
        )
    )

    @Test
    fun `Bad named entity loz without a semicolon`() = assertTokenization(
        input = "&loz",
        expectedTokens = listOf(
            *chars("&loz").toTypedArray()
        )
    )

    @Test
    fun `Named entity loz with a semicolon`() = assertTokenization(
        input = "&loz;",
        expectedTokens = listOf(
            char('◊')
        )
    )

    @Test
    fun `Bad named entity lozenge without a semicolon`() = assertTokenization(
        input = "&lozenge",
        expectedTokens = listOf(
            *chars("&lozenge").toTypedArray()
        )
    )

    @Test
    fun `Named entity lozenge with a semicolon`() = assertTokenization(
        input = "&lozenge;",
        expectedTokens = listOf(
            char('◊')
        )
    )

    @Test
    fun `Bad named entity lozf without a semicolon`() = assertTokenization(
        input = "&lozf",
        expectedTokens = listOf(
            *chars("&lozf").toTypedArray()
        )
    )

    @Test
    fun `Named entity lozf with a semicolon`() = assertTokenization(
        input = "&lozf;",
        expectedTokens = listOf(
            char('⧫')
        )
    )

    @Test
    fun `Bad named entity lpar without a semicolon`() = assertTokenization(
        input = "&lpar",
        expectedTokens = listOf(
            *chars("&lpar").toTypedArray()
        )
    )

    @Test
    fun `Named entity lpar with a semicolon`() = assertTokenization(
        input = "&lpar;",
        expectedTokens = listOf(
            char('(')
        )
    )

    @Test
    fun `Bad named entity lparlt without a semicolon`() = assertTokenization(
        input = "&lparlt",
        expectedTokens = listOf(
            *chars("&lparlt").toTypedArray()
        )
    )

    @Test
    fun `Named entity lparlt with a semicolon`() = assertTokenization(
        input = "&lparlt;",
        expectedTokens = listOf(
            char('⦓')
        )
    )

    @Test
    fun `Bad named entity lrarr without a semicolon`() = assertTokenization(
        input = "&lrarr",
        expectedTokens = listOf(
            *chars("&lrarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity lrarr with a semicolon`() = assertTokenization(
        input = "&lrarr;",
        expectedTokens = listOf(
            char('⇆')
        )
    )

    @Test
    fun `Bad named entity lrcorner without a semicolon`() = assertTokenization(
        input = "&lrcorner",
        expectedTokens = listOf(
            *chars("&lrcorner").toTypedArray()
        )
    )

    @Test
    fun `Named entity lrcorner with a semicolon`() = assertTokenization(
        input = "&lrcorner;",
        expectedTokens = listOf(
            char('⌟')
        )
    )

    @Test
    fun `Bad named entity lrhar without a semicolon`() = assertTokenization(
        input = "&lrhar",
        expectedTokens = listOf(
            *chars("&lrhar").toTypedArray()
        )
    )

    @Test
    fun `Named entity lrhar with a semicolon`() = assertTokenization(
        input = "&lrhar;",
        expectedTokens = listOf(
            char('⇋')
        )
    )

    @Test
    fun `Bad named entity lrhard without a semicolon`() = assertTokenization(
        input = "&lrhard",
        expectedTokens = listOf(
            *chars("&lrhard").toTypedArray()
        )
    )

    @Test
    fun `Named entity lrhard with a semicolon`() = assertTokenization(
        input = "&lrhard;",
        expectedTokens = listOf(
            char('⥭')
        )
    )

    @Test
    fun `Bad named entity lrm without a semicolon`() = assertTokenization(
        input = "&lrm",
        expectedTokens = listOf(
            *chars("&lrm").toTypedArray()
        )
    )

    @Test
    fun `Named entity lrm with a semicolon`() = assertTokenization(
        input = "&lrm;",
        expectedTokens = listOf(
            char('‎')
        )
    )

    @Test
    fun `Bad named entity lrtri without a semicolon`() = assertTokenization(
        input = "&lrtri",
        expectedTokens = listOf(
            *chars("&lrtri").toTypedArray()
        )
    )

    @Test
    fun `Named entity lrtri with a semicolon`() = assertTokenization(
        input = "&lrtri;",
        expectedTokens = listOf(
            char('⊿')
        )
    )

    @Test
    fun `Bad named entity lsaquo without a semicolon`() = assertTokenization(
        input = "&lsaquo",
        expectedTokens = listOf(
            *chars("&lsaquo").toTypedArray()
        )
    )

    @Test
    fun `Named entity lsaquo with a semicolon`() = assertTokenization(
        input = "&lsaquo;",
        expectedTokens = listOf(
            char('‹')
        )
    )

    @Test
    fun `Bad named entity lscr without a semicolon`() = assertTokenization(
        input = "&lscr",
        expectedTokens = listOf(
            *chars("&lscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity lscr with a semicolon`() = assertTokenization(
        input = "&lscr;",
        expectedTokens = listOf(
            *chars("𝓁").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity lsh without a semicolon`() = assertTokenization(
        input = "&lsh",
        expectedTokens = listOf(
            *chars("&lsh").toTypedArray()
        )
    )

    @Test
    fun `Named entity lsh with a semicolon`() = assertTokenization(
        input = "&lsh;",
        expectedTokens = listOf(
            char('↰')
        )
    )

    @Test
    fun `Bad named entity lsim without a semicolon`() = assertTokenization(
        input = "&lsim",
        expectedTokens = listOf(
            *chars("&lsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity lsim with a semicolon`() = assertTokenization(
        input = "&lsim;",
        expectedTokens = listOf(
            char('≲')
        )
    )

    @Test
    fun `Bad named entity lsime without a semicolon`() = assertTokenization(
        input = "&lsime",
        expectedTokens = listOf(
            *chars("&lsime").toTypedArray()
        )
    )

    @Test
    fun `Named entity lsime with a semicolon`() = assertTokenization(
        input = "&lsime;",
        expectedTokens = listOf(
            char('⪍')
        )
    )

    @Test
    fun `Bad named entity lsimg without a semicolon`() = assertTokenization(
        input = "&lsimg",
        expectedTokens = listOf(
            *chars("&lsimg").toTypedArray()
        )
    )

    @Test
    fun `Named entity lsimg with a semicolon`() = assertTokenization(
        input = "&lsimg;",
        expectedTokens = listOf(
            char('⪏')
        )
    )

    @Test
    fun `Bad named entity lsqb without a semicolon`() = assertTokenization(
        input = "&lsqb",
        expectedTokens = listOf(
            *chars("&lsqb").toTypedArray()
        )
    )

    @Test
    fun `Named entity lsqb with a semicolon`() = assertTokenization(
        input = "&lsqb;",
        expectedTokens = listOf(
            char('[')
        )
    )

    @Test
    fun `Bad named entity lsquo without a semicolon`() = assertTokenization(
        input = "&lsquo",
        expectedTokens = listOf(
            *chars("&lsquo").toTypedArray()
        )
    )

    @Test
    fun `Named entity lsquo with a semicolon`() = assertTokenization(
        input = "&lsquo;",
        expectedTokens = listOf(
            char('‘')
        )
    )

    @Test
    fun `Bad named entity lsquor without a semicolon`() = assertTokenization(
        input = "&lsquor",
        expectedTokens = listOf(
            *chars("&lsquor").toTypedArray()
        )
    )

    @Test
    fun `Named entity lsquor with a semicolon`() = assertTokenization(
        input = "&lsquor;",
        expectedTokens = listOf(
            char('‚')
        )
    )

    @Test
    fun `Bad named entity lstrok without a semicolon`() = assertTokenization(
        input = "&lstrok",
        expectedTokens = listOf(
            *chars("&lstrok").toTypedArray()
        )
    )

    @Test
    fun `Named entity lstrok with a semicolon`() = assertTokenization(
        input = "&lstrok;",
        expectedTokens = listOf(
            char('ł')
        )
    )

    @Test
    fun `Named entity lt without a semicolon`() = assertTokenization(
        input = "&lt",
        expectedTokens = listOf(
            char('<')
        )
    )

    @Test
    fun `Named entity lt with a semicolon`() = assertTokenization(
        input = "&lt;",
        expectedTokens = listOf(
            char('<')
        )
    )

    @Test
    fun `Named entity ltcc with a semicolon`() = assertTokenization(
        input = "&ltcc;",
        expectedTokens = listOf(
            char('⪦')
        )
    )

    @Test
    fun `Named entity ltcir with a semicolon`() = assertTokenization(
        input = "&ltcir;",
        expectedTokens = listOf(
            char('⩹')
        )
    )

    @Test
    fun `Named entity ltdot with a semicolon`() = assertTokenization(
        input = "&ltdot;",
        expectedTokens = listOf(
            char('⋖')
        )
    )

    @Test
    fun `Named entity lthree with a semicolon`() = assertTokenization(
        input = "&lthree;",
        expectedTokens = listOf(
            char('⋋')
        )
    )

    @Test
    fun `Named entity ltimes with a semicolon`() = assertTokenization(
        input = "&ltimes;",
        expectedTokens = listOf(
            char('⋉')
        )
    )

    @Test
    fun `Named entity ltlarr with a semicolon`() = assertTokenization(
        input = "&ltlarr;",
        expectedTokens = listOf(
            char('⥶')
        )
    )

    @Test
    fun `Named entity ltquest with a semicolon`() = assertTokenization(
        input = "&ltquest;",
        expectedTokens = listOf(
            char('⩻')
        )
    )

    @Test
    fun `Named entity ltrPar with a semicolon`() = assertTokenization(
        input = "&ltrPar;",
        expectedTokens = listOf(
            char('⦖')
        )
    )

    @Test
    fun `Named entity ltri with a semicolon`() = assertTokenization(
        input = "&ltri;",
        expectedTokens = listOf(
            char('◃')
        )
    )

    @Test
    fun `Named entity ltrie with a semicolon`() = assertTokenization(
        input = "&ltrie;",
        expectedTokens = listOf(
            char('⊴')
        )
    )

    @Test
    fun `Named entity ltrif with a semicolon`() = assertTokenization(
        input = "&ltrif;",
        expectedTokens = listOf(
            char('◂')
        )
    )

    @Test
    fun `Bad named entity lurdshar without a semicolon`() = assertTokenization(
        input = "&lurdshar",
        expectedTokens = listOf(
            *chars("&lurdshar").toTypedArray()
        )
    )

    @Test
    fun `Named entity lurdshar with a semicolon`() = assertTokenization(
        input = "&lurdshar;",
        expectedTokens = listOf(
            char('⥊')
        )
    )

    @Test
    fun `Bad named entity luruhar without a semicolon`() = assertTokenization(
        input = "&luruhar",
        expectedTokens = listOf(
            *chars("&luruhar").toTypedArray()
        )
    )

    @Test
    fun `Named entity luruhar with a semicolon`() = assertTokenization(
        input = "&luruhar;",
        expectedTokens = listOf(
            char('⥦')
        )
    )

    @Test
    fun `Bad named entity lvertneqq without a semicolon`() = assertTokenization(
        input = "&lvertneqq",
        expectedTokens = listOf(
            *chars("&lvertneqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity lvertneqq with a semicolon`() = assertTokenization(
        input = "&lvertneqq;",
        expectedTokens = listOf(
            *chars("≨︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity lvnE without a semicolon`() = assertTokenization(
        input = "&lvnE",
        expectedTokens = listOf(
            *chars("&lvnE").toTypedArray()
        )
    )

    @Test
    fun `Named entity lvnE with a semicolon`() = assertTokenization(
        input = "&lvnE;",
        expectedTokens = listOf(
            *chars("≨︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity mDDot without a semicolon`() = assertTokenization(
        input = "&mDDot",
        expectedTokens = listOf(
            *chars("&mDDot").toTypedArray()
        )
    )

    @Test
    fun `Named entity mDDot with a semicolon`() = assertTokenization(
        input = "&mDDot;",
        expectedTokens = listOf(
            char('∺')
        )
    )

    @Test
    fun `Named entity macr without a semicolon`() = assertTokenization(
        input = "&macr",
        expectedTokens = listOf(
            char('¯')
        )
    )

    @Test
    fun `Named entity macr with a semicolon`() = assertTokenization(
        input = "&macr;",
        expectedTokens = listOf(
            char('¯')
        )
    )

    @Test
    fun `Bad named entity male without a semicolon`() = assertTokenization(
        input = "&male",
        expectedTokens = listOf(
            *chars("&male").toTypedArray()
        )
    )

    @Test
    fun `Named entity male with a semicolon`() = assertTokenization(
        input = "&male;",
        expectedTokens = listOf(
            char('♂')
        )
    )

    @Test
    fun `Bad named entity malt without a semicolon`() = assertTokenization(
        input = "&malt",
        expectedTokens = listOf(
            *chars("&malt").toTypedArray()
        )
    )

    @Test
    fun `Named entity malt with a semicolon`() = assertTokenization(
        input = "&malt;",
        expectedTokens = listOf(
            char('✠')
        )
    )

    @Test
    fun `Bad named entity maltese without a semicolon`() = assertTokenization(
        input = "&maltese",
        expectedTokens = listOf(
            *chars("&maltese").toTypedArray()
        )
    )

    @Test
    fun `Named entity maltese with a semicolon`() = assertTokenization(
        input = "&maltese;",
        expectedTokens = listOf(
            char('✠')
        )
    )

    @Test
    fun `Bad named entity map without a semicolon`() = assertTokenization(
        input = "&map",
        expectedTokens = listOf(
            *chars("&map").toTypedArray()
        )
    )

    @Test
    fun `Named entity map with a semicolon`() = assertTokenization(
        input = "&map;",
        expectedTokens = listOf(
            char('↦')
        )
    )

    @Test
    fun `Bad named entity mapsto without a semicolon`() = assertTokenization(
        input = "&mapsto",
        expectedTokens = listOf(
            *chars("&mapsto").toTypedArray()
        )
    )

    @Test
    fun `Named entity mapsto with a semicolon`() = assertTokenization(
        input = "&mapsto;",
        expectedTokens = listOf(
            char('↦')
        )
    )

    @Test
    fun `Bad named entity mapstodown without a semicolon`() = assertTokenization(
        input = "&mapstodown",
        expectedTokens = listOf(
            *chars("&mapstodown").toTypedArray()
        )
    )

    @Test
    fun `Named entity mapstodown with a semicolon`() = assertTokenization(
        input = "&mapstodown;",
        expectedTokens = listOf(
            char('↧')
        )
    )

    @Test
    fun `Bad named entity mapstoleft without a semicolon`() = assertTokenization(
        input = "&mapstoleft",
        expectedTokens = listOf(
            *chars("&mapstoleft").toTypedArray()
        )
    )

    @Test
    fun `Named entity mapstoleft with a semicolon`() = assertTokenization(
        input = "&mapstoleft;",
        expectedTokens = listOf(
            char('↤')
        )
    )

    @Test
    fun `Bad named entity mapstoup without a semicolon`() = assertTokenization(
        input = "&mapstoup",
        expectedTokens = listOf(
            *chars("&mapstoup").toTypedArray()
        )
    )

    @Test
    fun `Named entity mapstoup with a semicolon`() = assertTokenization(
        input = "&mapstoup;",
        expectedTokens = listOf(
            char('↥')
        )
    )

    @Test
    fun `Bad named entity marker without a semicolon`() = assertTokenization(
        input = "&marker",
        expectedTokens = listOf(
            *chars("&marker").toTypedArray()
        )
    )

    @Test
    fun `Named entity marker with a semicolon`() = assertTokenization(
        input = "&marker;",
        expectedTokens = listOf(
            char('▮')
        )
    )

    @Test
    fun `Bad named entity mcomma without a semicolon`() = assertTokenization(
        input = "&mcomma",
        expectedTokens = listOf(
            *chars("&mcomma").toTypedArray()
        )
    )

    @Test
    fun `Named entity mcomma with a semicolon`() = assertTokenization(
        input = "&mcomma;",
        expectedTokens = listOf(
            char('⨩')
        )
    )

    @Test
    fun `Bad named entity mcy without a semicolon`() = assertTokenization(
        input = "&mcy",
        expectedTokens = listOf(
            *chars("&mcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity mcy with a semicolon`() = assertTokenization(
        input = "&mcy;",
        expectedTokens = listOf(
            char('м')
        )
    )

    @Test
    fun `Bad named entity mdash without a semicolon`() = assertTokenization(
        input = "&mdash",
        expectedTokens = listOf(
            *chars("&mdash").toTypedArray()
        )
    )

    @Test
    fun `Named entity mdash with a semicolon`() = assertTokenization(
        input = "&mdash;",
        expectedTokens = listOf(
            char('—')
        )
    )

    @Test
    fun `Bad named entity measuredangle without a semicolon`() = assertTokenization(
        input = "&measuredangle",
        expectedTokens = listOf(
            *chars("&measuredangle").toTypedArray()
        )
    )

    @Test
    fun `Named entity measuredangle with a semicolon`() = assertTokenization(
        input = "&measuredangle;",
        expectedTokens = listOf(
            char('∡')
        )
    )

    @Test
    fun `Bad named entity mfr without a semicolon`() = assertTokenization(
        input = "&mfr",
        expectedTokens = listOf(
            *chars("&mfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity mfr with a semicolon`() = assertTokenization(
        input = "&mfr;",
        expectedTokens = listOf(
            *chars("𝔪").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity mho without a semicolon`() = assertTokenization(
        input = "&mho",
        expectedTokens = listOf(
            *chars("&mho").toTypedArray()
        )
    )

    @Test
    fun `Named entity mho with a semicolon`() = assertTokenization(
        input = "&mho;",
        expectedTokens = listOf(
            char('℧')
        )
    )

    @Test
    fun `Named entity micro without a semicolon`() = assertTokenization(
        input = "&micro",
        expectedTokens = listOf(
            char('µ')
        )
    )

    @Test
    fun `Named entity micro with a semicolon`() = assertTokenization(
        input = "&micro;",
        expectedTokens = listOf(
            char('µ')
        )
    )

    @Test
    fun `Bad named entity mid without a semicolon`() = assertTokenization(
        input = "&mid",
        expectedTokens = listOf(
            *chars("&mid").toTypedArray()
        )
    )

    @Test
    fun `Named entity mid with a semicolon`() = assertTokenization(
        input = "&mid;",
        expectedTokens = listOf(
            char('∣')
        )
    )

    @Test
    fun `Bad named entity midast without a semicolon`() = assertTokenization(
        input = "&midast",
        expectedTokens = listOf(
            *chars("&midast").toTypedArray()
        )
    )

    @Test
    fun `Named entity midast with a semicolon`() = assertTokenization(
        input = "&midast;",
        expectedTokens = listOf(
            char('*')
        )
    )

    @Test
    fun `Bad named entity midcir without a semicolon`() = assertTokenization(
        input = "&midcir",
        expectedTokens = listOf(
            *chars("&midcir").toTypedArray()
        )
    )

    @Test
    fun `Named entity midcir with a semicolon`() = assertTokenization(
        input = "&midcir;",
        expectedTokens = listOf(
            char('⫰')
        )
    )

    @Test
    fun `Named entity middot without a semicolon`() = assertTokenization(
        input = "&middot",
        expectedTokens = listOf(
            char('·')
        )
    )

    @Test
    fun `Named entity middot with a semicolon`() = assertTokenization(
        input = "&middot;",
        expectedTokens = listOf(
            char('·')
        )
    )

    @Test
    fun `Bad named entity minus without a semicolon`() = assertTokenization(
        input = "&minus",
        expectedTokens = listOf(
            *chars("&minus").toTypedArray()
        )
    )

    @Test
    fun `Named entity minus with a semicolon`() = assertTokenization(
        input = "&minus;",
        expectedTokens = listOf(
            char('−')
        )
    )

    @Test
    fun `Bad named entity minusb without a semicolon`() = assertTokenization(
        input = "&minusb",
        expectedTokens = listOf(
            *chars("&minusb").toTypedArray()
        )
    )

    @Test
    fun `Named entity minusb with a semicolon`() = assertTokenization(
        input = "&minusb;",
        expectedTokens = listOf(
            char('⊟')
        )
    )

    @Test
    fun `Bad named entity minusd without a semicolon`() = assertTokenization(
        input = "&minusd",
        expectedTokens = listOf(
            *chars("&minusd").toTypedArray()
        )
    )

    @Test
    fun `Named entity minusd with a semicolon`() = assertTokenization(
        input = "&minusd;",
        expectedTokens = listOf(
            char('∸')
        )
    )

    @Test
    fun `Bad named entity minusdu without a semicolon`() = assertTokenization(
        input = "&minusdu",
        expectedTokens = listOf(
            *chars("&minusdu").toTypedArray()
        )
    )

    @Test
    fun `Named entity minusdu with a semicolon`() = assertTokenization(
        input = "&minusdu;",
        expectedTokens = listOf(
            char('⨪')
        )
    )

    @Test
    fun `Bad named entity mlcp without a semicolon`() = assertTokenization(
        input = "&mlcp",
        expectedTokens = listOf(
            *chars("&mlcp").toTypedArray()
        )
    )

    @Test
    fun `Named entity mlcp with a semicolon`() = assertTokenization(
        input = "&mlcp;",
        expectedTokens = listOf(
            char('⫛')
        )
    )

    @Test
    fun `Bad named entity mldr without a semicolon`() = assertTokenization(
        input = "&mldr",
        expectedTokens = listOf(
            *chars("&mldr").toTypedArray()
        )
    )

    @Test
    fun `Named entity mldr with a semicolon`() = assertTokenization(
        input = "&mldr;",
        expectedTokens = listOf(
            char('…')
        )
    )

    @Test
    fun `Bad named entity mnplus without a semicolon`() = assertTokenization(
        input = "&mnplus",
        expectedTokens = listOf(
            *chars("&mnplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity mnplus with a semicolon`() = assertTokenization(
        input = "&mnplus;",
        expectedTokens = listOf(
            char('∓')
        )
    )

    @Test
    fun `Bad named entity models without a semicolon`() = assertTokenization(
        input = "&models",
        expectedTokens = listOf(
            *chars("&models").toTypedArray()
        )
    )

    @Test
    fun `Named entity models with a semicolon`() = assertTokenization(
        input = "&models;",
        expectedTokens = listOf(
            char('⊧')
        )
    )

    @Test
    fun `Bad named entity mopf without a semicolon`() = assertTokenization(
        input = "&mopf",
        expectedTokens = listOf(
            *chars("&mopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity mopf with a semicolon`() = assertTokenization(
        input = "&mopf;",
        expectedTokens = listOf(
            *chars("𝕞").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity mp without a semicolon`() = assertTokenization(
        input = "&mp",
        expectedTokens = listOf(
            *chars("&mp").toTypedArray()
        )
    )

    @Test
    fun `Named entity mp with a semicolon`() = assertTokenization(
        input = "&mp;",
        expectedTokens = listOf(
            char('∓')
        )
    )

    @Test
    fun `Bad named entity mscr without a semicolon`() = assertTokenization(
        input = "&mscr",
        expectedTokens = listOf(
            *chars("&mscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity mscr with a semicolon`() = assertTokenization(
        input = "&mscr;",
        expectedTokens = listOf(
            *chars("𝓂").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity mstpos without a semicolon`() = assertTokenization(
        input = "&mstpos",
        expectedTokens = listOf(
            *chars("&mstpos").toTypedArray()
        )
    )

    @Test
    fun `Named entity mstpos with a semicolon`() = assertTokenization(
        input = "&mstpos;",
        expectedTokens = listOf(
            char('∾')
        )
    )

    @Test
    fun `Bad named entity mu without a semicolon`() = assertTokenization(
        input = "&mu",
        expectedTokens = listOf(
            *chars("&mu").toTypedArray()
        )
    )

    @Test
    fun `Named entity mu with a semicolon`() = assertTokenization(
        input = "&mu;",
        expectedTokens = listOf(
            char('μ')
        )
    )

    @Test
    fun `Bad named entity multimap without a semicolon`() = assertTokenization(
        input = "&multimap",
        expectedTokens = listOf(
            *chars("&multimap").toTypedArray()
        )
    )

    @Test
    fun `Named entity multimap with a semicolon`() = assertTokenization(
        input = "&multimap;",
        expectedTokens = listOf(
            char('⊸')
        )
    )

    @Test
    fun `Bad named entity mumap without a semicolon`() = assertTokenization(
        input = "&mumap",
        expectedTokens = listOf(
            *chars("&mumap").toTypedArray()
        )
    )

    @Test
    fun `Named entity mumap with a semicolon`() = assertTokenization(
        input = "&mumap;",
        expectedTokens = listOf(
            char('⊸')
        )
    )

    @Test
    fun `Bad named entity nGg without a semicolon`() = assertTokenization(
        input = "&nGg",
        expectedTokens = listOf(
            *chars("&nGg").toTypedArray()
        )
    )

    @Test
    fun `Named entity nGg with a semicolon`() = assertTokenization(
        input = "&nGg;",
        expectedTokens = listOf(
            *chars("⋙̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nGt without a semicolon`() = assertTokenization(
        input = "&nGt",
        expectedTokens = listOf(
            *chars("&nGt").toTypedArray()
        )
    )

    @Test
    fun `Named entity nGt with a semicolon`() = assertTokenization(
        input = "&nGt;",
        expectedTokens = listOf(
            *chars("≫⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nGtv without a semicolon`() = assertTokenization(
        input = "&nGtv",
        expectedTokens = listOf(
            *chars("&nGtv").toTypedArray()
        )
    )

    @Test
    fun `Named entity nGtv with a semicolon`() = assertTokenization(
        input = "&nGtv;",
        expectedTokens = listOf(
            *chars("≫̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nLeftarrow without a semicolon`() = assertTokenization(
        input = "&nLeftarrow",
        expectedTokens = listOf(
            *chars("&nLeftarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity nLeftarrow with a semicolon`() = assertTokenization(
        input = "&nLeftarrow;",
        expectedTokens = listOf(
            char('⇍')
        )
    )

    @Test
    fun `Bad named entity nLeftrightarrow without a semicolon`() = assertTokenization(
        input = "&nLeftrightarrow",
        expectedTokens = listOf(
            *chars("&nLeftrightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity nLeftrightarrow with a semicolon`() = assertTokenization(
        input = "&nLeftrightarrow;",
        expectedTokens = listOf(
            char('⇎')
        )
    )

    @Test
    fun `Bad named entity nLl without a semicolon`() = assertTokenization(
        input = "&nLl",
        expectedTokens = listOf(
            *chars("&nLl").toTypedArray()
        )
    )

    @Test
    fun `Named entity nLl with a semicolon`() = assertTokenization(
        input = "&nLl;",
        expectedTokens = listOf(
            *chars("⋘̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nLt without a semicolon`() = assertTokenization(
        input = "&nLt",
        expectedTokens = listOf(
            *chars("&nLt").toTypedArray()
        )
    )

    @Test
    fun `Named entity nLt with a semicolon`() = assertTokenization(
        input = "&nLt;",
        expectedTokens = listOf(
            *chars("≪⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nLtv without a semicolon`() = assertTokenization(
        input = "&nLtv",
        expectedTokens = listOf(
            *chars("&nLtv").toTypedArray()
        )
    )

    @Test
    fun `Named entity nLtv with a semicolon`() = assertTokenization(
        input = "&nLtv;",
        expectedTokens = listOf(
            *chars("≪̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nRightarrow without a semicolon`() = assertTokenization(
        input = "&nRightarrow",
        expectedTokens = listOf(
            *chars("&nRightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity nRightarrow with a semicolon`() = assertTokenization(
        input = "&nRightarrow;",
        expectedTokens = listOf(
            char('⇏')
        )
    )

    @Test
    fun `Bad named entity nVDash without a semicolon`() = assertTokenization(
        input = "&nVDash",
        expectedTokens = listOf(
            *chars("&nVDash").toTypedArray()
        )
    )

    @Test
    fun `Named entity nVDash with a semicolon`() = assertTokenization(
        input = "&nVDash;",
        expectedTokens = listOf(
            char('⊯')
        )
    )

    @Test
    fun `Bad named entity nVdash without a semicolon`() = assertTokenization(
        input = "&nVdash",
        expectedTokens = listOf(
            *chars("&nVdash").toTypedArray()
        )
    )

    @Test
    fun `Named entity nVdash with a semicolon`() = assertTokenization(
        input = "&nVdash;",
        expectedTokens = listOf(
            char('⊮')
        )
    )

    @Test
    fun `Bad named entity nabla without a semicolon`() = assertTokenization(
        input = "&nabla",
        expectedTokens = listOf(
            *chars("&nabla").toTypedArray()
        )
    )

    @Test
    fun `Named entity nabla with a semicolon`() = assertTokenization(
        input = "&nabla;",
        expectedTokens = listOf(
            char('∇')
        )
    )

    @Test
    fun `Bad named entity nacute without a semicolon`() = assertTokenization(
        input = "&nacute",
        expectedTokens = listOf(
            *chars("&nacute").toTypedArray()
        )
    )

    @Test
    fun `Named entity nacute with a semicolon`() = assertTokenization(
        input = "&nacute;",
        expectedTokens = listOf(
            char('ń')
        )
    )

    @Test
    fun `Bad named entity nang without a semicolon`() = assertTokenization(
        input = "&nang",
        expectedTokens = listOf(
            *chars("&nang").toTypedArray()
        )
    )

    @Test
    fun `Named entity nang with a semicolon`() = assertTokenization(
        input = "&nang;",
        expectedTokens = listOf(
            *chars("∠⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nap without a semicolon`() = assertTokenization(
        input = "&nap",
        expectedTokens = listOf(
            *chars("&nap").toTypedArray()
        )
    )

    @Test
    fun `Named entity nap with a semicolon`() = assertTokenization(
        input = "&nap;",
        expectedTokens = listOf(
            char('≉')
        )
    )

    @Test
    fun `Bad named entity napE without a semicolon`() = assertTokenization(
        input = "&napE",
        expectedTokens = listOf(
            *chars("&napE").toTypedArray()
        )
    )

    @Test
    fun `Named entity napE with a semicolon`() = assertTokenization(
        input = "&napE;",
        expectedTokens = listOf(
            *chars("⩰̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity napid without a semicolon`() = assertTokenization(
        input = "&napid",
        expectedTokens = listOf(
            *chars("&napid").toTypedArray()
        )
    )

    @Test
    fun `Named entity napid with a semicolon`() = assertTokenization(
        input = "&napid;",
        expectedTokens = listOf(
            *chars("≋̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity napos without a semicolon`() = assertTokenization(
        input = "&napos",
        expectedTokens = listOf(
            *chars("&napos").toTypedArray()
        )
    )

    @Test
    fun `Named entity napos with a semicolon`() = assertTokenization(
        input = "&napos;",
        expectedTokens = listOf(
            char('ŉ')
        )
    )

    @Test
    fun `Bad named entity napprox without a semicolon`() = assertTokenization(
        input = "&napprox",
        expectedTokens = listOf(
            *chars("&napprox").toTypedArray()
        )
    )

    @Test
    fun `Named entity napprox with a semicolon`() = assertTokenization(
        input = "&napprox;",
        expectedTokens = listOf(
            char('≉')
        )
    )

    @Test
    fun `Bad named entity natur without a semicolon`() = assertTokenization(
        input = "&natur",
        expectedTokens = listOf(
            *chars("&natur").toTypedArray()
        )
    )

    @Test
    fun `Named entity natur with a semicolon`() = assertTokenization(
        input = "&natur;",
        expectedTokens = listOf(
            char('♮')
        )
    )

    @Test
    fun `Bad named entity natural without a semicolon`() = assertTokenization(
        input = "&natural",
        expectedTokens = listOf(
            *chars("&natural").toTypedArray()
        )
    )

    @Test
    fun `Named entity natural with a semicolon`() = assertTokenization(
        input = "&natural;",
        expectedTokens = listOf(
            char('♮')
        )
    )

    @Test
    fun `Bad named entity naturals without a semicolon`() = assertTokenization(
        input = "&naturals",
        expectedTokens = listOf(
            *chars("&naturals").toTypedArray()
        )
    )

    @Test
    fun `Named entity naturals with a semicolon`() = assertTokenization(
        input = "&naturals;",
        expectedTokens = listOf(
            char('ℕ')
        )
    )

    @Test
    fun `Named entity nbsp without a semicolon`() = assertTokenization(
        input = "&nbsp",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Named entity nbsp with a semicolon`() = assertTokenization(
        input = "&nbsp;",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Bad named entity nbump without a semicolon`() = assertTokenization(
        input = "&nbump",
        expectedTokens = listOf(
            *chars("&nbump").toTypedArray()
        )
    )

    @Test
    fun `Named entity nbump with a semicolon`() = assertTokenization(
        input = "&nbump;",
        expectedTokens = listOf(
            *chars("≎̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nbumpe without a semicolon`() = assertTokenization(
        input = "&nbumpe",
        expectedTokens = listOf(
            *chars("&nbumpe").toTypedArray()
        )
    )

    @Test
    fun `Named entity nbumpe with a semicolon`() = assertTokenization(
        input = "&nbumpe;",
        expectedTokens = listOf(
            *chars("≏̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ncap without a semicolon`() = assertTokenization(
        input = "&ncap",
        expectedTokens = listOf(
            *chars("&ncap").toTypedArray()
        )
    )

    @Test
    fun `Named entity ncap with a semicolon`() = assertTokenization(
        input = "&ncap;",
        expectedTokens = listOf(
            char('⩃')
        )
    )

    @Test
    fun `Bad named entity ncaron without a semicolon`() = assertTokenization(
        input = "&ncaron",
        expectedTokens = listOf(
            *chars("&ncaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity ncaron with a semicolon`() = assertTokenization(
        input = "&ncaron;",
        expectedTokens = listOf(
            char('ň')
        )
    )

    @Test
    fun `Bad named entity ncedil without a semicolon`() = assertTokenization(
        input = "&ncedil",
        expectedTokens = listOf(
            *chars("&ncedil").toTypedArray()
        )
    )

    @Test
    fun `Named entity ncedil with a semicolon`() = assertTokenization(
        input = "&ncedil;",
        expectedTokens = listOf(
            char('ņ')
        )
    )

    @Test
    fun `Bad named entity ncong without a semicolon`() = assertTokenization(
        input = "&ncong",
        expectedTokens = listOf(
            *chars("&ncong").toTypedArray()
        )
    )

    @Test
    fun `Named entity ncong with a semicolon`() = assertTokenization(
        input = "&ncong;",
        expectedTokens = listOf(
            char('≇')
        )
    )

    @Test
    fun `Bad named entity ncongdot without a semicolon`() = assertTokenization(
        input = "&ncongdot",
        expectedTokens = listOf(
            *chars("&ncongdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity ncongdot with a semicolon`() = assertTokenization(
        input = "&ncongdot;",
        expectedTokens = listOf(
            *chars("⩭̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ncup without a semicolon`() = assertTokenization(
        input = "&ncup",
        expectedTokens = listOf(
            *chars("&ncup").toTypedArray()
        )
    )

    @Test
    fun `Named entity ncup with a semicolon`() = assertTokenization(
        input = "&ncup;",
        expectedTokens = listOf(
            char('⩂')
        )
    )

    @Test
    fun `Bad named entity ncy without a semicolon`() = assertTokenization(
        input = "&ncy",
        expectedTokens = listOf(
            *chars("&ncy").toTypedArray()
        )
    )

    @Test
    fun `Named entity ncy with a semicolon`() = assertTokenization(
        input = "&ncy;",
        expectedTokens = listOf(
            char('н')
        )
    )

    @Test
    fun `Bad named entity ndash without a semicolon`() = assertTokenization(
        input = "&ndash",
        expectedTokens = listOf(
            *chars("&ndash").toTypedArray()
        )
    )

    @Test
    fun `Named entity ndash with a semicolon`() = assertTokenization(
        input = "&ndash;",
        expectedTokens = listOf(
            char('–')
        )
    )

    @Test
    fun `Bad named entity ne without a semicolon`() = assertTokenization(
        input = "&ne",
        expectedTokens = listOf(
            *chars("&ne").toTypedArray()
        )
    )

    @Test
    fun `Named entity ne with a semicolon`() = assertTokenization(
        input = "&ne;",
        expectedTokens = listOf(
            char('≠')
        )
    )

    @Test
    fun `Bad named entity neArr without a semicolon`() = assertTokenization(
        input = "&neArr",
        expectedTokens = listOf(
            *chars("&neArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity neArr with a semicolon`() = assertTokenization(
        input = "&neArr;",
        expectedTokens = listOf(
            char('⇗')
        )
    )

    @Test
    fun `Bad named entity nearhk without a semicolon`() = assertTokenization(
        input = "&nearhk",
        expectedTokens = listOf(
            *chars("&nearhk").toTypedArray()
        )
    )

    @Test
    fun `Named entity nearhk with a semicolon`() = assertTokenization(
        input = "&nearhk;",
        expectedTokens = listOf(
            char('⤤')
        )
    )

    @Test
    fun `Bad named entity nearr without a semicolon`() = assertTokenization(
        input = "&nearr",
        expectedTokens = listOf(
            *chars("&nearr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nearr with a semicolon`() = assertTokenization(
        input = "&nearr;",
        expectedTokens = listOf(
            char('↗')
        )
    )

    @Test
    fun `Bad named entity nearrow without a semicolon`() = assertTokenization(
        input = "&nearrow",
        expectedTokens = listOf(
            *chars("&nearrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity nearrow with a semicolon`() = assertTokenization(
        input = "&nearrow;",
        expectedTokens = listOf(
            char('↗')
        )
    )

    @Test
    fun `Bad named entity nedot without a semicolon`() = assertTokenization(
        input = "&nedot",
        expectedTokens = listOf(
            *chars("&nedot").toTypedArray()
        )
    )

    @Test
    fun `Named entity nedot with a semicolon`() = assertTokenization(
        input = "&nedot;",
        expectedTokens = listOf(
            *chars("≐̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nequiv without a semicolon`() = assertTokenization(
        input = "&nequiv",
        expectedTokens = listOf(
            *chars("&nequiv").toTypedArray()
        )
    )

    @Test
    fun `Named entity nequiv with a semicolon`() = assertTokenization(
        input = "&nequiv;",
        expectedTokens = listOf(
            char('≢')
        )
    )

    @Test
    fun `Bad named entity nesear without a semicolon`() = assertTokenization(
        input = "&nesear",
        expectedTokens = listOf(
            *chars("&nesear").toTypedArray()
        )
    )

    @Test
    fun `Named entity nesear with a semicolon`() = assertTokenization(
        input = "&nesear;",
        expectedTokens = listOf(
            char('⤨')
        )
    )

    @Test
    fun `Bad named entity nesim without a semicolon`() = assertTokenization(
        input = "&nesim",
        expectedTokens = listOf(
            *chars("&nesim").toTypedArray()
        )
    )

    @Test
    fun `Named entity nesim with a semicolon`() = assertTokenization(
        input = "&nesim;",
        expectedTokens = listOf(
            *chars("≂̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nexist without a semicolon`() = assertTokenization(
        input = "&nexist",
        expectedTokens = listOf(
            *chars("&nexist").toTypedArray()
        )
    )

    @Test
    fun `Named entity nexist with a semicolon`() = assertTokenization(
        input = "&nexist;",
        expectedTokens = listOf(
            char('∄')
        )
    )

    @Test
    fun `Bad named entity nexists without a semicolon`() = assertTokenization(
        input = "&nexists",
        expectedTokens = listOf(
            *chars("&nexists").toTypedArray()
        )
    )

    @Test
    fun `Named entity nexists with a semicolon`() = assertTokenization(
        input = "&nexists;",
        expectedTokens = listOf(
            char('∄')
        )
    )

    @Test
    fun `Bad named entity nfr without a semicolon`() = assertTokenization(
        input = "&nfr",
        expectedTokens = listOf(
            *chars("&nfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nfr with a semicolon`() = assertTokenization(
        input = "&nfr;",
        expectedTokens = listOf(
            *chars("𝔫").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ngE without a semicolon`() = assertTokenization(
        input = "&ngE",
        expectedTokens = listOf(
            *chars("&ngE").toTypedArray()
        )
    )

    @Test
    fun `Named entity ngE with a semicolon`() = assertTokenization(
        input = "&ngE;",
        expectedTokens = listOf(
            *chars("≧̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nge without a semicolon`() = assertTokenization(
        input = "&nge",
        expectedTokens = listOf(
            *chars("&nge").toTypedArray()
        )
    )

    @Test
    fun `Named entity nge with a semicolon`() = assertTokenization(
        input = "&nge;",
        expectedTokens = listOf(
            char('≱')
        )
    )

    @Test
    fun `Bad named entity ngeq without a semicolon`() = assertTokenization(
        input = "&ngeq",
        expectedTokens = listOf(
            *chars("&ngeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity ngeq with a semicolon`() = assertTokenization(
        input = "&ngeq;",
        expectedTokens = listOf(
            char('≱')
        )
    )

    @Test
    fun `Bad named entity ngeqq without a semicolon`() = assertTokenization(
        input = "&ngeqq",
        expectedTokens = listOf(
            *chars("&ngeqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity ngeqq with a semicolon`() = assertTokenization(
        input = "&ngeqq;",
        expectedTokens = listOf(
            *chars("≧̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ngeqslant without a semicolon`() = assertTokenization(
        input = "&ngeqslant",
        expectedTokens = listOf(
            *chars("&ngeqslant").toTypedArray()
        )
    )

    @Test
    fun `Named entity ngeqslant with a semicolon`() = assertTokenization(
        input = "&ngeqslant;",
        expectedTokens = listOf(
            *chars("⩾̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nges without a semicolon`() = assertTokenization(
        input = "&nges",
        expectedTokens = listOf(
            *chars("&nges").toTypedArray()
        )
    )

    @Test
    fun `Named entity nges with a semicolon`() = assertTokenization(
        input = "&nges;",
        expectedTokens = listOf(
            *chars("⩾̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ngsim without a semicolon`() = assertTokenization(
        input = "&ngsim",
        expectedTokens = listOf(
            *chars("&ngsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity ngsim with a semicolon`() = assertTokenization(
        input = "&ngsim;",
        expectedTokens = listOf(
            char('≵')
        )
    )

    @Test
    fun `Bad named entity ngt without a semicolon`() = assertTokenization(
        input = "&ngt",
        expectedTokens = listOf(
            *chars("&ngt").toTypedArray()
        )
    )

    @Test
    fun `Named entity ngt with a semicolon`() = assertTokenization(
        input = "&ngt;",
        expectedTokens = listOf(
            char('≯')
        )
    )

    @Test
    fun `Bad named entity ngtr without a semicolon`() = assertTokenization(
        input = "&ngtr",
        expectedTokens = listOf(
            *chars("&ngtr").toTypedArray()
        )
    )

    @Test
    fun `Named entity ngtr with a semicolon`() = assertTokenization(
        input = "&ngtr;",
        expectedTokens = listOf(
            char('≯')
        )
    )

    @Test
    fun `Bad named entity nhArr without a semicolon`() = assertTokenization(
        input = "&nhArr",
        expectedTokens = listOf(
            *chars("&nhArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nhArr with a semicolon`() = assertTokenization(
        input = "&nhArr;",
        expectedTokens = listOf(
            char('⇎')
        )
    )

    @Test
    fun `Bad named entity nharr without a semicolon`() = assertTokenization(
        input = "&nharr",
        expectedTokens = listOf(
            *chars("&nharr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nharr with a semicolon`() = assertTokenization(
        input = "&nharr;",
        expectedTokens = listOf(
            char('↮')
        )
    )

    @Test
    fun `Bad named entity nhpar without a semicolon`() = assertTokenization(
        input = "&nhpar",
        expectedTokens = listOf(
            *chars("&nhpar").toTypedArray()
        )
    )

    @Test
    fun `Named entity nhpar with a semicolon`() = assertTokenization(
        input = "&nhpar;",
        expectedTokens = listOf(
            char('⫲')
        )
    )

    @Test
    fun `Bad named entity ni without a semicolon`() = assertTokenization(
        input = "&ni",
        expectedTokens = listOf(
            *chars("&ni").toTypedArray()
        )
    )

    @Test
    fun `Named entity ni with a semicolon`() = assertTokenization(
        input = "&ni;",
        expectedTokens = listOf(
            char('∋')
        )
    )

    @Test
    fun `Bad named entity nis without a semicolon`() = assertTokenization(
        input = "&nis",
        expectedTokens = listOf(
            *chars("&nis").toTypedArray()
        )
    )

    @Test
    fun `Named entity nis with a semicolon`() = assertTokenization(
        input = "&nis;",
        expectedTokens = listOf(
            char('⋼')
        )
    )

    @Test
    fun `Bad named entity nisd without a semicolon`() = assertTokenization(
        input = "&nisd",
        expectedTokens = listOf(
            *chars("&nisd").toTypedArray()
        )
    )

    @Test
    fun `Named entity nisd with a semicolon`() = assertTokenization(
        input = "&nisd;",
        expectedTokens = listOf(
            char('⋺')
        )
    )

    @Test
    fun `Bad named entity niv without a semicolon`() = assertTokenization(
        input = "&niv",
        expectedTokens = listOf(
            *chars("&niv").toTypedArray()
        )
    )

    @Test
    fun `Named entity niv with a semicolon`() = assertTokenization(
        input = "&niv;",
        expectedTokens = listOf(
            char('∋')
        )
    )

    @Test
    fun `Bad named entity njcy without a semicolon`() = assertTokenization(
        input = "&njcy",
        expectedTokens = listOf(
            *chars("&njcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity njcy with a semicolon`() = assertTokenization(
        input = "&njcy;",
        expectedTokens = listOf(
            char('њ')
        )
    )

    @Test
    fun `Bad named entity nlArr without a semicolon`() = assertTokenization(
        input = "&nlArr",
        expectedTokens = listOf(
            *chars("&nlArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nlArr with a semicolon`() = assertTokenization(
        input = "&nlArr;",
        expectedTokens = listOf(
            char('⇍')
        )
    )

    @Test
    fun `Bad named entity nlE without a semicolon`() = assertTokenization(
        input = "&nlE",
        expectedTokens = listOf(
            *chars("&nlE").toTypedArray()
        )
    )

    @Test
    fun `Named entity nlE with a semicolon`() = assertTokenization(
        input = "&nlE;",
        expectedTokens = listOf(
            *chars("≦̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nlarr without a semicolon`() = assertTokenization(
        input = "&nlarr",
        expectedTokens = listOf(
            *chars("&nlarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nlarr with a semicolon`() = assertTokenization(
        input = "&nlarr;",
        expectedTokens = listOf(
            char('↚')
        )
    )

    @Test
    fun `Bad named entity nldr without a semicolon`() = assertTokenization(
        input = "&nldr",
        expectedTokens = listOf(
            *chars("&nldr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nldr with a semicolon`() = assertTokenization(
        input = "&nldr;",
        expectedTokens = listOf(
            char('‥')
        )
    )

    @Test
    fun `Bad named entity nle without a semicolon`() = assertTokenization(
        input = "&nle",
        expectedTokens = listOf(
            *chars("&nle").toTypedArray()
        )
    )

    @Test
    fun `Named entity nle with a semicolon`() = assertTokenization(
        input = "&nle;",
        expectedTokens = listOf(
            char('≰')
        )
    )

    @Test
    fun `Bad named entity nleftarrow without a semicolon`() = assertTokenization(
        input = "&nleftarrow",
        expectedTokens = listOf(
            *chars("&nleftarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity nleftarrow with a semicolon`() = assertTokenization(
        input = "&nleftarrow;",
        expectedTokens = listOf(
            char('↚')
        )
    )

    @Test
    fun `Bad named entity nleftrightarrow without a semicolon`() = assertTokenization(
        input = "&nleftrightarrow",
        expectedTokens = listOf(
            *chars("&nleftrightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity nleftrightarrow with a semicolon`() = assertTokenization(
        input = "&nleftrightarrow;",
        expectedTokens = listOf(
            char('↮')
        )
    )

    @Test
    fun `Bad named entity nleq without a semicolon`() = assertTokenization(
        input = "&nleq",
        expectedTokens = listOf(
            *chars("&nleq").toTypedArray()
        )
    )

    @Test
    fun `Named entity nleq with a semicolon`() = assertTokenization(
        input = "&nleq;",
        expectedTokens = listOf(
            char('≰')
        )
    )

    @Test
    fun `Bad named entity nleqq without a semicolon`() = assertTokenization(
        input = "&nleqq",
        expectedTokens = listOf(
            *chars("&nleqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity nleqq with a semicolon`() = assertTokenization(
        input = "&nleqq;",
        expectedTokens = listOf(
            *chars("≦̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nleqslant without a semicolon`() = assertTokenization(
        input = "&nleqslant",
        expectedTokens = listOf(
            *chars("&nleqslant").toTypedArray()
        )
    )

    @Test
    fun `Named entity nleqslant with a semicolon`() = assertTokenization(
        input = "&nleqslant;",
        expectedTokens = listOf(
            *chars("⩽̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nles without a semicolon`() = assertTokenization(
        input = "&nles",
        expectedTokens = listOf(
            *chars("&nles").toTypedArray()
        )
    )

    @Test
    fun `Named entity nles with a semicolon`() = assertTokenization(
        input = "&nles;",
        expectedTokens = listOf(
            *chars("⩽̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nless without a semicolon`() = assertTokenization(
        input = "&nless",
        expectedTokens = listOf(
            *chars("&nless").toTypedArray()
        )
    )

    @Test
    fun `Named entity nless with a semicolon`() = assertTokenization(
        input = "&nless;",
        expectedTokens = listOf(
            char('≮')
        )
    )

    @Test
    fun `Bad named entity nlsim without a semicolon`() = assertTokenization(
        input = "&nlsim",
        expectedTokens = listOf(
            *chars("&nlsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity nlsim with a semicolon`() = assertTokenization(
        input = "&nlsim;",
        expectedTokens = listOf(
            char('≴')
        )
    )

    @Test
    fun `Bad named entity nlt without a semicolon`() = assertTokenization(
        input = "&nlt",
        expectedTokens = listOf(
            *chars("&nlt").toTypedArray()
        )
    )

    @Test
    fun `Named entity nlt with a semicolon`() = assertTokenization(
        input = "&nlt;",
        expectedTokens = listOf(
            char('≮')
        )
    )

    @Test
    fun `Bad named entity nltri without a semicolon`() = assertTokenization(
        input = "&nltri",
        expectedTokens = listOf(
            *chars("&nltri").toTypedArray()
        )
    )

    @Test
    fun `Named entity nltri with a semicolon`() = assertTokenization(
        input = "&nltri;",
        expectedTokens = listOf(
            char('⋪')
        )
    )

    @Test
    fun `Bad named entity nltrie without a semicolon`() = assertTokenization(
        input = "&nltrie",
        expectedTokens = listOf(
            *chars("&nltrie").toTypedArray()
        )
    )

    @Test
    fun `Named entity nltrie with a semicolon`() = assertTokenization(
        input = "&nltrie;",
        expectedTokens = listOf(
            char('⋬')
        )
    )

    @Test
    fun `Bad named entity nmid without a semicolon`() = assertTokenization(
        input = "&nmid",
        expectedTokens = listOf(
            *chars("&nmid").toTypedArray()
        )
    )

    @Test
    fun `Named entity nmid with a semicolon`() = assertTokenization(
        input = "&nmid;",
        expectedTokens = listOf(
            char('∤')
        )
    )

    @Test
    fun `Bad named entity nopf without a semicolon`() = assertTokenization(
        input = "&nopf",
        expectedTokens = listOf(
            *chars("&nopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity nopf with a semicolon`() = assertTokenization(
        input = "&nopf;",
        expectedTokens = listOf(
            *chars("𝕟").toTypedArray()
        )
    )

    @Test
    fun `Named entity not without a semicolon`() = assertTokenization(
        input = "&not",
        expectedTokens = listOf(
            char('¬')
        )
    )

    @Test
    fun `Named entity not with a semicolon`() = assertTokenization(
        input = "&not;",
        expectedTokens = listOf(
            char('¬')
        )
    )

    @Test
    fun `Named entity notin with a semicolon`() = assertTokenization(
        input = "&notin;",
        expectedTokens = listOf(
            char('∉')
        )
    )

    @Test
    fun `Named entity notinE with a semicolon`() = assertTokenization(
        input = "&notinE;",
        expectedTokens = listOf(
            *chars("⋹̸").toTypedArray()
        )
    )

    @Test
    fun `Named entity notindot with a semicolon`() = assertTokenization(
        input = "&notindot;",
        expectedTokens = listOf(
            *chars("⋵̸").toTypedArray()
        )
    )

    @Test
    fun `Named entity notinva with a semicolon`() = assertTokenization(
        input = "&notinva;",
        expectedTokens = listOf(
            char('∉')
        )
    )

    @Test
    fun `Named entity notinvb with a semicolon`() = assertTokenization(
        input = "&notinvb;",
        expectedTokens = listOf(
            char('⋷')
        )
    )

    @Test
    fun `Named entity notinvc with a semicolon`() = assertTokenization(
        input = "&notinvc;",
        expectedTokens = listOf(
            char('⋶')
        )
    )

    @Test
    fun `Named entity notni with a semicolon`() = assertTokenization(
        input = "&notni;",
        expectedTokens = listOf(
            char('∌')
        )
    )

    @Test
    fun `Named entity notniva with a semicolon`() = assertTokenization(
        input = "&notniva;",
        expectedTokens = listOf(
            char('∌')
        )
    )

    @Test
    fun `Named entity notnivb with a semicolon`() = assertTokenization(
        input = "&notnivb;",
        expectedTokens = listOf(
            char('⋾')
        )
    )

    @Test
    fun `Named entity notnivc with a semicolon`() = assertTokenization(
        input = "&notnivc;",
        expectedTokens = listOf(
            char('⋽')
        )
    )

    @Test
    fun `Bad named entity npar without a semicolon`() = assertTokenization(
        input = "&npar",
        expectedTokens = listOf(
            *chars("&npar").toTypedArray()
        )
    )

    @Test
    fun `Named entity npar with a semicolon`() = assertTokenization(
        input = "&npar;",
        expectedTokens = listOf(
            char('∦')
        )
    )

    @Test
    fun `Bad named entity nparallel without a semicolon`() = assertTokenization(
        input = "&nparallel",
        expectedTokens = listOf(
            *chars("&nparallel").toTypedArray()
        )
    )

    @Test
    fun `Named entity nparallel with a semicolon`() = assertTokenization(
        input = "&nparallel;",
        expectedTokens = listOf(
            char('∦')
        )
    )

    @Test
    fun `Bad named entity nparsl without a semicolon`() = assertTokenization(
        input = "&nparsl",
        expectedTokens = listOf(
            *chars("&nparsl").toTypedArray()
        )
    )

    @Test
    fun `Named entity nparsl with a semicolon`() = assertTokenization(
        input = "&nparsl;",
        expectedTokens = listOf(
            *chars("⫽⃥").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity npart without a semicolon`() = assertTokenization(
        input = "&npart",
        expectedTokens = listOf(
            *chars("&npart").toTypedArray()
        )
    )

    @Test
    fun `Named entity npart with a semicolon`() = assertTokenization(
        input = "&npart;",
        expectedTokens = listOf(
            *chars("∂̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity npolint without a semicolon`() = assertTokenization(
        input = "&npolint",
        expectedTokens = listOf(
            *chars("&npolint").toTypedArray()
        )
    )

    @Test
    fun `Named entity npolint with a semicolon`() = assertTokenization(
        input = "&npolint;",
        expectedTokens = listOf(
            char('⨔')
        )
    )

    @Test
    fun `Bad named entity npr without a semicolon`() = assertTokenization(
        input = "&npr",
        expectedTokens = listOf(
            *chars("&npr").toTypedArray()
        )
    )

    @Test
    fun `Named entity npr with a semicolon`() = assertTokenization(
        input = "&npr;",
        expectedTokens = listOf(
            char('⊀')
        )
    )

    @Test
    fun `Bad named entity nprcue without a semicolon`() = assertTokenization(
        input = "&nprcue",
        expectedTokens = listOf(
            *chars("&nprcue").toTypedArray()
        )
    )

    @Test
    fun `Named entity nprcue with a semicolon`() = assertTokenization(
        input = "&nprcue;",
        expectedTokens = listOf(
            char('⋠')
        )
    )

    @Test
    fun `Bad named entity npre without a semicolon`() = assertTokenization(
        input = "&npre",
        expectedTokens = listOf(
            *chars("&npre").toTypedArray()
        )
    )

    @Test
    fun `Named entity npre with a semicolon`() = assertTokenization(
        input = "&npre;",
        expectedTokens = listOf(
            *chars("⪯̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nprec without a semicolon`() = assertTokenization(
        input = "&nprec",
        expectedTokens = listOf(
            *chars("&nprec").toTypedArray()
        )
    )

    @Test
    fun `Named entity nprec with a semicolon`() = assertTokenization(
        input = "&nprec;",
        expectedTokens = listOf(
            char('⊀')
        )
    )

    @Test
    fun `Bad named entity npreceq without a semicolon`() = assertTokenization(
        input = "&npreceq",
        expectedTokens = listOf(
            *chars("&npreceq").toTypedArray()
        )
    )

    @Test
    fun `Named entity npreceq with a semicolon`() = assertTokenization(
        input = "&npreceq;",
        expectedTokens = listOf(
            *chars("⪯̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nrArr without a semicolon`() = assertTokenization(
        input = "&nrArr",
        expectedTokens = listOf(
            *chars("&nrArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nrArr with a semicolon`() = assertTokenization(
        input = "&nrArr;",
        expectedTokens = listOf(
            char('⇏')
        )
    )

    @Test
    fun `Bad named entity nrarr without a semicolon`() = assertTokenization(
        input = "&nrarr",
        expectedTokens = listOf(
            *chars("&nrarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nrarr with a semicolon`() = assertTokenization(
        input = "&nrarr;",
        expectedTokens = listOf(
            char('↛')
        )
    )

    @Test
    fun `Bad named entity nrarrc without a semicolon`() = assertTokenization(
        input = "&nrarrc",
        expectedTokens = listOf(
            *chars("&nrarrc").toTypedArray()
        )
    )

    @Test
    fun `Named entity nrarrc with a semicolon`() = assertTokenization(
        input = "&nrarrc;",
        expectedTokens = listOf(
            *chars("⤳̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nrarrw without a semicolon`() = assertTokenization(
        input = "&nrarrw",
        expectedTokens = listOf(
            *chars("&nrarrw").toTypedArray()
        )
    )

    @Test
    fun `Named entity nrarrw with a semicolon`() = assertTokenization(
        input = "&nrarrw;",
        expectedTokens = listOf(
            *chars("↝̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nrightarrow without a semicolon`() = assertTokenization(
        input = "&nrightarrow",
        expectedTokens = listOf(
            *chars("&nrightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity nrightarrow with a semicolon`() = assertTokenization(
        input = "&nrightarrow;",
        expectedTokens = listOf(
            char('↛')
        )
    )

    @Test
    fun `Bad named entity nrtri without a semicolon`() = assertTokenization(
        input = "&nrtri",
        expectedTokens = listOf(
            *chars("&nrtri").toTypedArray()
        )
    )

    @Test
    fun `Named entity nrtri with a semicolon`() = assertTokenization(
        input = "&nrtri;",
        expectedTokens = listOf(
            char('⋫')
        )
    )

    @Test
    fun `Bad named entity nrtrie without a semicolon`() = assertTokenization(
        input = "&nrtrie",
        expectedTokens = listOf(
            *chars("&nrtrie").toTypedArray()
        )
    )

    @Test
    fun `Named entity nrtrie with a semicolon`() = assertTokenization(
        input = "&nrtrie;",
        expectedTokens = listOf(
            char('⋭')
        )
    )

    @Test
    fun `Bad named entity nsc without a semicolon`() = assertTokenization(
        input = "&nsc",
        expectedTokens = listOf(
            *chars("&nsc").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsc with a semicolon`() = assertTokenization(
        input = "&nsc;",
        expectedTokens = listOf(
            char('⊁')
        )
    )

    @Test
    fun `Bad named entity nsccue without a semicolon`() = assertTokenization(
        input = "&nsccue",
        expectedTokens = listOf(
            *chars("&nsccue").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsccue with a semicolon`() = assertTokenization(
        input = "&nsccue;",
        expectedTokens = listOf(
            char('⋡')
        )
    )

    @Test
    fun `Bad named entity nsce without a semicolon`() = assertTokenization(
        input = "&nsce",
        expectedTokens = listOf(
            *chars("&nsce").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsce with a semicolon`() = assertTokenization(
        input = "&nsce;",
        expectedTokens = listOf(
            *chars("⪰̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nscr without a semicolon`() = assertTokenization(
        input = "&nscr",
        expectedTokens = listOf(
            *chars("&nscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nscr with a semicolon`() = assertTokenization(
        input = "&nscr;",
        expectedTokens = listOf(
            *chars("𝓃").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nshortmid without a semicolon`() = assertTokenization(
        input = "&nshortmid",
        expectedTokens = listOf(
            *chars("&nshortmid").toTypedArray()
        )
    )

    @Test
    fun `Named entity nshortmid with a semicolon`() = assertTokenization(
        input = "&nshortmid;",
        expectedTokens = listOf(
            char('∤')
        )
    )

    @Test
    fun `Bad named entity nshortparallel without a semicolon`() = assertTokenization(
        input = "&nshortparallel",
        expectedTokens = listOf(
            *chars("&nshortparallel").toTypedArray()
        )
    )

    @Test
    fun `Named entity nshortparallel with a semicolon`() = assertTokenization(
        input = "&nshortparallel;",
        expectedTokens = listOf(
            char('∦')
        )
    )

    @Test
    fun `Bad named entity nsim without a semicolon`() = assertTokenization(
        input = "&nsim",
        expectedTokens = listOf(
            *chars("&nsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsim with a semicolon`() = assertTokenization(
        input = "&nsim;",
        expectedTokens = listOf(
            char('≁')
        )
    )

    @Test
    fun `Bad named entity nsime without a semicolon`() = assertTokenization(
        input = "&nsime",
        expectedTokens = listOf(
            *chars("&nsime").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsime with a semicolon`() = assertTokenization(
        input = "&nsime;",
        expectedTokens = listOf(
            char('≄')
        )
    )

    @Test
    fun `Bad named entity nsimeq without a semicolon`() = assertTokenization(
        input = "&nsimeq",
        expectedTokens = listOf(
            *chars("&nsimeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsimeq with a semicolon`() = assertTokenization(
        input = "&nsimeq;",
        expectedTokens = listOf(
            char('≄')
        )
    )

    @Test
    fun `Bad named entity nsmid without a semicolon`() = assertTokenization(
        input = "&nsmid",
        expectedTokens = listOf(
            *chars("&nsmid").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsmid with a semicolon`() = assertTokenization(
        input = "&nsmid;",
        expectedTokens = listOf(
            char('∤')
        )
    )

    @Test
    fun `Bad named entity nspar without a semicolon`() = assertTokenization(
        input = "&nspar",
        expectedTokens = listOf(
            *chars("&nspar").toTypedArray()
        )
    )

    @Test
    fun `Named entity nspar with a semicolon`() = assertTokenization(
        input = "&nspar;",
        expectedTokens = listOf(
            char('∦')
        )
    )

    @Test
    fun `Bad named entity nsqsube without a semicolon`() = assertTokenization(
        input = "&nsqsube",
        expectedTokens = listOf(
            *chars("&nsqsube").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsqsube with a semicolon`() = assertTokenization(
        input = "&nsqsube;",
        expectedTokens = listOf(
            char('⋢')
        )
    )

    @Test
    fun `Bad named entity nsqsupe without a semicolon`() = assertTokenization(
        input = "&nsqsupe",
        expectedTokens = listOf(
            *chars("&nsqsupe").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsqsupe with a semicolon`() = assertTokenization(
        input = "&nsqsupe;",
        expectedTokens = listOf(
            char('⋣')
        )
    )

    @Test
    fun `Bad named entity nsub without a semicolon`() = assertTokenization(
        input = "&nsub",
        expectedTokens = listOf(
            *chars("&nsub").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsub with a semicolon`() = assertTokenization(
        input = "&nsub;",
        expectedTokens = listOf(
            char('⊄')
        )
    )

    @Test
    fun `Bad named entity nsubE without a semicolon`() = assertTokenization(
        input = "&nsubE",
        expectedTokens = listOf(
            *chars("&nsubE").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsubE with a semicolon`() = assertTokenization(
        input = "&nsubE;",
        expectedTokens = listOf(
            *chars("⫅̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nsube without a semicolon`() = assertTokenization(
        input = "&nsube",
        expectedTokens = listOf(
            *chars("&nsube").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsube with a semicolon`() = assertTokenization(
        input = "&nsube;",
        expectedTokens = listOf(
            char('⊈')
        )
    )

    @Test
    fun `Bad named entity nsubset without a semicolon`() = assertTokenization(
        input = "&nsubset",
        expectedTokens = listOf(
            *chars("&nsubset").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsubset with a semicolon`() = assertTokenization(
        input = "&nsubset;",
        expectedTokens = listOf(
            *chars("⊂⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nsubseteq without a semicolon`() = assertTokenization(
        input = "&nsubseteq",
        expectedTokens = listOf(
            *chars("&nsubseteq").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsubseteq with a semicolon`() = assertTokenization(
        input = "&nsubseteq;",
        expectedTokens = listOf(
            char('⊈')
        )
    )

    @Test
    fun `Bad named entity nsubseteqq without a semicolon`() = assertTokenization(
        input = "&nsubseteqq",
        expectedTokens = listOf(
            *chars("&nsubseteqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsubseteqq with a semicolon`() = assertTokenization(
        input = "&nsubseteqq;",
        expectedTokens = listOf(
            *chars("⫅̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nsucc without a semicolon`() = assertTokenization(
        input = "&nsucc",
        expectedTokens = listOf(
            *chars("&nsucc").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsucc with a semicolon`() = assertTokenization(
        input = "&nsucc;",
        expectedTokens = listOf(
            char('⊁')
        )
    )

    @Test
    fun `Bad named entity nsucceq without a semicolon`() = assertTokenization(
        input = "&nsucceq",
        expectedTokens = listOf(
            *chars("&nsucceq").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsucceq with a semicolon`() = assertTokenization(
        input = "&nsucceq;",
        expectedTokens = listOf(
            *chars("⪰̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nsup without a semicolon`() = assertTokenization(
        input = "&nsup",
        expectedTokens = listOf(
            *chars("&nsup").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsup with a semicolon`() = assertTokenization(
        input = "&nsup;",
        expectedTokens = listOf(
            char('⊅')
        )
    )

    @Test
    fun `Bad named entity nsupE without a semicolon`() = assertTokenization(
        input = "&nsupE",
        expectedTokens = listOf(
            *chars("&nsupE").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsupE with a semicolon`() = assertTokenization(
        input = "&nsupE;",
        expectedTokens = listOf(
            *chars("⫆̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nsupe without a semicolon`() = assertTokenization(
        input = "&nsupe",
        expectedTokens = listOf(
            *chars("&nsupe").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsupe with a semicolon`() = assertTokenization(
        input = "&nsupe;",
        expectedTokens = listOf(
            char('⊉')
        )
    )

    @Test
    fun `Bad named entity nsupset without a semicolon`() = assertTokenization(
        input = "&nsupset",
        expectedTokens = listOf(
            *chars("&nsupset").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsupset with a semicolon`() = assertTokenization(
        input = "&nsupset;",
        expectedTokens = listOf(
            *chars("⊃⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nsupseteq without a semicolon`() = assertTokenization(
        input = "&nsupseteq",
        expectedTokens = listOf(
            *chars("&nsupseteq").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsupseteq with a semicolon`() = assertTokenization(
        input = "&nsupseteq;",
        expectedTokens = listOf(
            char('⊉')
        )
    )

    @Test
    fun `Bad named entity nsupseteqq without a semicolon`() = assertTokenization(
        input = "&nsupseteqq",
        expectedTokens = listOf(
            *chars("&nsupseteqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity nsupseteqq with a semicolon`() = assertTokenization(
        input = "&nsupseteqq;",
        expectedTokens = listOf(
            *chars("⫆̸").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ntgl without a semicolon`() = assertTokenization(
        input = "&ntgl",
        expectedTokens = listOf(
            *chars("&ntgl").toTypedArray()
        )
    )

    @Test
    fun `Named entity ntgl with a semicolon`() = assertTokenization(
        input = "&ntgl;",
        expectedTokens = listOf(
            char('≹')
        )
    )

    @Test
    fun `Named entity ntilde without a semicolon`() = assertTokenization(
        input = "&ntilde",
        expectedTokens = listOf(
            char('ñ')
        )
    )

    @Test
    fun `Named entity ntilde with a semicolon`() = assertTokenization(
        input = "&ntilde;",
        expectedTokens = listOf(
            char('ñ')
        )
    )

    @Test
    fun `Bad named entity ntlg without a semicolon`() = assertTokenization(
        input = "&ntlg",
        expectedTokens = listOf(
            *chars("&ntlg").toTypedArray()
        )
    )

    @Test
    fun `Named entity ntlg with a semicolon`() = assertTokenization(
        input = "&ntlg;",
        expectedTokens = listOf(
            char('≸')
        )
    )

    @Test
    fun `Bad named entity ntriangleleft without a semicolon`() = assertTokenization(
        input = "&ntriangleleft",
        expectedTokens = listOf(
            *chars("&ntriangleleft").toTypedArray()
        )
    )

    @Test
    fun `Named entity ntriangleleft with a semicolon`() = assertTokenization(
        input = "&ntriangleleft;",
        expectedTokens = listOf(
            char('⋪')
        )
    )

    @Test
    fun `Bad named entity ntrianglelefteq without a semicolon`() = assertTokenization(
        input = "&ntrianglelefteq",
        expectedTokens = listOf(
            *chars("&ntrianglelefteq").toTypedArray()
        )
    )

    @Test
    fun `Named entity ntrianglelefteq with a semicolon`() = assertTokenization(
        input = "&ntrianglelefteq;",
        expectedTokens = listOf(
            char('⋬')
        )
    )

    @Test
    fun `Bad named entity ntriangleright without a semicolon`() = assertTokenization(
        input = "&ntriangleright",
        expectedTokens = listOf(
            *chars("&ntriangleright").toTypedArray()
        )
    )

    @Test
    fun `Named entity ntriangleright with a semicolon`() = assertTokenization(
        input = "&ntriangleright;",
        expectedTokens = listOf(
            char('⋫')
        )
    )

    @Test
    fun `Bad named entity ntrianglerighteq without a semicolon`() = assertTokenization(
        input = "&ntrianglerighteq",
        expectedTokens = listOf(
            *chars("&ntrianglerighteq").toTypedArray()
        )
    )

    @Test
    fun `Named entity ntrianglerighteq with a semicolon`() = assertTokenization(
        input = "&ntrianglerighteq;",
        expectedTokens = listOf(
            char('⋭')
        )
    )

    @Test
    fun `Bad named entity nu without a semicolon`() = assertTokenization(
        input = "&nu",
        expectedTokens = listOf(
            *chars("&nu").toTypedArray()
        )
    )

    @Test
    fun `Named entity nu with a semicolon`() = assertTokenization(
        input = "&nu;",
        expectedTokens = listOf(
            char('ν')
        )
    )

    @Test
    fun `Bad named entity num without a semicolon`() = assertTokenization(
        input = "&num",
        expectedTokens = listOf(
            *chars("&num").toTypedArray()
        )
    )

    @Test
    fun `Named entity num with a semicolon`() = assertTokenization(
        input = "&num;",
        expectedTokens = listOf(
            char('#')
        )
    )

    @Test
    fun `Bad named entity numero without a semicolon`() = assertTokenization(
        input = "&numero",
        expectedTokens = listOf(
            *chars("&numero").toTypedArray()
        )
    )

    @Test
    fun `Named entity numero with a semicolon`() = assertTokenization(
        input = "&numero;",
        expectedTokens = listOf(
            char('№')
        )
    )

    @Test
    fun `Bad named entity numsp without a semicolon`() = assertTokenization(
        input = "&numsp",
        expectedTokens = listOf(
            *chars("&numsp").toTypedArray()
        )
    )

    @Test
    fun `Named entity numsp with a semicolon`() = assertTokenization(
        input = "&numsp;",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Bad named entity nvDash without a semicolon`() = assertTokenization(
        input = "&nvDash",
        expectedTokens = listOf(
            *chars("&nvDash").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvDash with a semicolon`() = assertTokenization(
        input = "&nvDash;",
        expectedTokens = listOf(
            char('⊭')
        )
    )

    @Test
    fun `Bad named entity nvHarr without a semicolon`() = assertTokenization(
        input = "&nvHarr",
        expectedTokens = listOf(
            *chars("&nvHarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvHarr with a semicolon`() = assertTokenization(
        input = "&nvHarr;",
        expectedTokens = listOf(
            char('⤄')
        )
    )

    @Test
    fun `Bad named entity nvap without a semicolon`() = assertTokenization(
        input = "&nvap",
        expectedTokens = listOf(
            *chars("&nvap").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvap with a semicolon`() = assertTokenization(
        input = "&nvap;",
        expectedTokens = listOf(
            *chars("≍⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nvdash without a semicolon`() = assertTokenization(
        input = "&nvdash",
        expectedTokens = listOf(
            *chars("&nvdash").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvdash with a semicolon`() = assertTokenization(
        input = "&nvdash;",
        expectedTokens = listOf(
            char('⊬')
        )
    )

    @Test
    fun `Bad named entity nvge without a semicolon`() = assertTokenization(
        input = "&nvge",
        expectedTokens = listOf(
            *chars("&nvge").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvge with a semicolon`() = assertTokenization(
        input = "&nvge;",
        expectedTokens = listOf(
            *chars("≥⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nvgt without a semicolon`() = assertTokenization(
        input = "&nvgt",
        expectedTokens = listOf(
            *chars("&nvgt").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvgt with a semicolon`() = assertTokenization(
        input = "&nvgt;",
        expectedTokens = listOf(
            *chars(">⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nvinfin without a semicolon`() = assertTokenization(
        input = "&nvinfin",
        expectedTokens = listOf(
            *chars("&nvinfin").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvinfin with a semicolon`() = assertTokenization(
        input = "&nvinfin;",
        expectedTokens = listOf(
            char('⧞')
        )
    )

    @Test
    fun `Bad named entity nvlArr without a semicolon`() = assertTokenization(
        input = "&nvlArr",
        expectedTokens = listOf(
            *chars("&nvlArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvlArr with a semicolon`() = assertTokenization(
        input = "&nvlArr;",
        expectedTokens = listOf(
            char('⤂')
        )
    )

    @Test
    fun `Bad named entity nvle without a semicolon`() = assertTokenization(
        input = "&nvle",
        expectedTokens = listOf(
            *chars("&nvle").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvle with a semicolon`() = assertTokenization(
        input = "&nvle;",
        expectedTokens = listOf(
            *chars("≤⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nvlt without a semicolon`() = assertTokenization(
        input = "&nvlt",
        expectedTokens = listOf(
            *chars("&nvlt").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvlt with a semicolon`() = assertTokenization(
        input = "&nvlt;",
        expectedTokens = listOf(
            *chars("<⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nvltrie without a semicolon`() = assertTokenization(
        input = "&nvltrie",
        expectedTokens = listOf(
            *chars("&nvltrie").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvltrie with a semicolon`() = assertTokenization(
        input = "&nvltrie;",
        expectedTokens = listOf(
            *chars("⊴⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nvrArr without a semicolon`() = assertTokenization(
        input = "&nvrArr",
        expectedTokens = listOf(
            *chars("&nvrArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvrArr with a semicolon`() = assertTokenization(
        input = "&nvrArr;",
        expectedTokens = listOf(
            char('⤃')
        )
    )

    @Test
    fun `Bad named entity nvrtrie without a semicolon`() = assertTokenization(
        input = "&nvrtrie",
        expectedTokens = listOf(
            *chars("&nvrtrie").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvrtrie with a semicolon`() = assertTokenization(
        input = "&nvrtrie;",
        expectedTokens = listOf(
            *chars("⊵⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nvsim without a semicolon`() = assertTokenization(
        input = "&nvsim",
        expectedTokens = listOf(
            *chars("&nvsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity nvsim with a semicolon`() = assertTokenization(
        input = "&nvsim;",
        expectedTokens = listOf(
            *chars("∼⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity nwArr without a semicolon`() = assertTokenization(
        input = "&nwArr",
        expectedTokens = listOf(
            *chars("&nwArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nwArr with a semicolon`() = assertTokenization(
        input = "&nwArr;",
        expectedTokens = listOf(
            char('⇖')
        )
    )

    @Test
    fun `Bad named entity nwarhk without a semicolon`() = assertTokenization(
        input = "&nwarhk",
        expectedTokens = listOf(
            *chars("&nwarhk").toTypedArray()
        )
    )

    @Test
    fun `Named entity nwarhk with a semicolon`() = assertTokenization(
        input = "&nwarhk;",
        expectedTokens = listOf(
            char('⤣')
        )
    )

    @Test
    fun `Bad named entity nwarr without a semicolon`() = assertTokenization(
        input = "&nwarr",
        expectedTokens = listOf(
            *chars("&nwarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity nwarr with a semicolon`() = assertTokenization(
        input = "&nwarr;",
        expectedTokens = listOf(
            char('↖')
        )
    )

    @Test
    fun `Bad named entity nwarrow without a semicolon`() = assertTokenization(
        input = "&nwarrow",
        expectedTokens = listOf(
            *chars("&nwarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity nwarrow with a semicolon`() = assertTokenization(
        input = "&nwarrow;",
        expectedTokens = listOf(
            char('↖')
        )
    )

    @Test
    fun `Bad named entity nwnear without a semicolon`() = assertTokenization(
        input = "&nwnear",
        expectedTokens = listOf(
            *chars("&nwnear").toTypedArray()
        )
    )

    @Test
    fun `Named entity nwnear with a semicolon`() = assertTokenization(
        input = "&nwnear;",
        expectedTokens = listOf(
            char('⤧')
        )
    )

    @Test
    fun `Bad named entity oS without a semicolon`() = assertTokenization(
        input = "&oS",
        expectedTokens = listOf(
            *chars("&oS").toTypedArray()
        )
    )

    @Test
    fun `Named entity oS with a semicolon`() = assertTokenization(
        input = "&oS;",
        expectedTokens = listOf(
            char('Ⓢ')
        )
    )

    @Test
    fun `Named entity oacute without a semicolon`() = assertTokenization(
        input = "&oacute",
        expectedTokens = listOf(
            char('ó')
        )
    )

    @Test
    fun `Named entity oacute with a semicolon`() = assertTokenization(
        input = "&oacute;",
        expectedTokens = listOf(
            char('ó')
        )
    )

    @Test
    fun `Bad named entity oast without a semicolon`() = assertTokenization(
        input = "&oast",
        expectedTokens = listOf(
            *chars("&oast").toTypedArray()
        )
    )

    @Test
    fun `Named entity oast with a semicolon`() = assertTokenization(
        input = "&oast;",
        expectedTokens = listOf(
            char('⊛')
        )
    )

    @Test
    fun `Bad named entity ocir without a semicolon`() = assertTokenization(
        input = "&ocir",
        expectedTokens = listOf(
            *chars("&ocir").toTypedArray()
        )
    )

    @Test
    fun `Named entity ocir with a semicolon`() = assertTokenization(
        input = "&ocir;",
        expectedTokens = listOf(
            char('⊚')
        )
    )

    @Test
    fun `Named entity ocirc without a semicolon`() = assertTokenization(
        input = "&ocirc",
        expectedTokens = listOf(
            char('ô')
        )
    )

    @Test
    fun `Named entity ocirc with a semicolon`() = assertTokenization(
        input = "&ocirc;",
        expectedTokens = listOf(
            char('ô')
        )
    )

    @Test
    fun `Bad named entity ocy without a semicolon`() = assertTokenization(
        input = "&ocy",
        expectedTokens = listOf(
            *chars("&ocy").toTypedArray()
        )
    )

    @Test
    fun `Named entity ocy with a semicolon`() = assertTokenization(
        input = "&ocy;",
        expectedTokens = listOf(
            char('о')
        )
    )

    @Test
    fun `Bad named entity odash without a semicolon`() = assertTokenization(
        input = "&odash",
        expectedTokens = listOf(
            *chars("&odash").toTypedArray()
        )
    )

    @Test
    fun `Named entity odash with a semicolon`() = assertTokenization(
        input = "&odash;",
        expectedTokens = listOf(
            char('⊝')
        )
    )

    @Test
    fun `Bad named entity odblac without a semicolon`() = assertTokenization(
        input = "&odblac",
        expectedTokens = listOf(
            *chars("&odblac").toTypedArray()
        )
    )

    @Test
    fun `Named entity odblac with a semicolon`() = assertTokenization(
        input = "&odblac;",
        expectedTokens = listOf(
            char('ő')
        )
    )

    @Test
    fun `Bad named entity odiv without a semicolon`() = assertTokenization(
        input = "&odiv",
        expectedTokens = listOf(
            *chars("&odiv").toTypedArray()
        )
    )

    @Test
    fun `Named entity odiv with a semicolon`() = assertTokenization(
        input = "&odiv;",
        expectedTokens = listOf(
            char('⨸')
        )
    )

    @Test
    fun `Bad named entity odot without a semicolon`() = assertTokenization(
        input = "&odot",
        expectedTokens = listOf(
            *chars("&odot").toTypedArray()
        )
    )

    @Test
    fun `Named entity odot with a semicolon`() = assertTokenization(
        input = "&odot;",
        expectedTokens = listOf(
            char('⊙')
        )
    )

    @Test
    fun `Bad named entity odsold without a semicolon`() = assertTokenization(
        input = "&odsold",
        expectedTokens = listOf(
            *chars("&odsold").toTypedArray()
        )
    )

    @Test
    fun `Named entity odsold with a semicolon`() = assertTokenization(
        input = "&odsold;",
        expectedTokens = listOf(
            char('⦼')
        )
    )

    @Test
    fun `Bad named entity oelig without a semicolon`() = assertTokenization(
        input = "&oelig",
        expectedTokens = listOf(
            *chars("&oelig").toTypedArray()
        )
    )

    @Test
    fun `Named entity oelig with a semicolon`() = assertTokenization(
        input = "&oelig;",
        expectedTokens = listOf(
            char('œ')
        )
    )

    @Test
    fun `Bad named entity ofcir without a semicolon`() = assertTokenization(
        input = "&ofcir",
        expectedTokens = listOf(
            *chars("&ofcir").toTypedArray()
        )
    )

    @Test
    fun `Named entity ofcir with a semicolon`() = assertTokenization(
        input = "&ofcir;",
        expectedTokens = listOf(
            char('⦿')
        )
    )

    @Test
    fun `Bad named entity ofr without a semicolon`() = assertTokenization(
        input = "&ofr",
        expectedTokens = listOf(
            *chars("&ofr").toTypedArray()
        )
    )

    @Test
    fun `Named entity ofr with a semicolon`() = assertTokenization(
        input = "&ofr;",
        expectedTokens = listOf(
            *chars("𝔬").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ogon without a semicolon`() = assertTokenization(
        input = "&ogon",
        expectedTokens = listOf(
            *chars("&ogon").toTypedArray()
        )
    )

    @Test
    fun `Named entity ogon with a semicolon`() = assertTokenization(
        input = "&ogon;",
        expectedTokens = listOf(
            char('˛')
        )
    )

    @Test
    fun `Named entity ograve without a semicolon`() = assertTokenization(
        input = "&ograve",
        expectedTokens = listOf(
            char('ò')
        )
    )

    @Test
    fun `Named entity ograve with a semicolon`() = assertTokenization(
        input = "&ograve;",
        expectedTokens = listOf(
            char('ò')
        )
    )

    @Test
    fun `Bad named entity ogt without a semicolon`() = assertTokenization(
        input = "&ogt",
        expectedTokens = listOf(
            *chars("&ogt").toTypedArray()
        )
    )

    @Test
    fun `Named entity ogt with a semicolon`() = assertTokenization(
        input = "&ogt;",
        expectedTokens = listOf(
            char('⧁')
        )
    )

    @Test
    fun `Bad named entity ohbar without a semicolon`() = assertTokenization(
        input = "&ohbar",
        expectedTokens = listOf(
            *chars("&ohbar").toTypedArray()
        )
    )

    @Test
    fun `Named entity ohbar with a semicolon`() = assertTokenization(
        input = "&ohbar;",
        expectedTokens = listOf(
            char('⦵')
        )
    )

    @Test
    fun `Bad named entity ohm without a semicolon`() = assertTokenization(
        input = "&ohm",
        expectedTokens = listOf(
            *chars("&ohm").toTypedArray()
        )
    )

    @Test
    fun `Named entity ohm with a semicolon`() = assertTokenization(
        input = "&ohm;",
        expectedTokens = listOf(
            char('Ω')
        )
    )

    @Test
    fun `Bad named entity oint without a semicolon`() = assertTokenization(
        input = "&oint",
        expectedTokens = listOf(
            *chars("&oint").toTypedArray()
        )
    )

    @Test
    fun `Named entity oint with a semicolon`() = assertTokenization(
        input = "&oint;",
        expectedTokens = listOf(
            char('∮')
        )
    )

    @Test
    fun `Bad named entity olarr without a semicolon`() = assertTokenization(
        input = "&olarr",
        expectedTokens = listOf(
            *chars("&olarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity olarr with a semicolon`() = assertTokenization(
        input = "&olarr;",
        expectedTokens = listOf(
            char('↺')
        )
    )

    @Test
    fun `Bad named entity olcir without a semicolon`() = assertTokenization(
        input = "&olcir",
        expectedTokens = listOf(
            *chars("&olcir").toTypedArray()
        )
    )

    @Test
    fun `Named entity olcir with a semicolon`() = assertTokenization(
        input = "&olcir;",
        expectedTokens = listOf(
            char('⦾')
        )
    )

    @Test
    fun `Bad named entity olcross without a semicolon`() = assertTokenization(
        input = "&olcross",
        expectedTokens = listOf(
            *chars("&olcross").toTypedArray()
        )
    )

    @Test
    fun `Named entity olcross with a semicolon`() = assertTokenization(
        input = "&olcross;",
        expectedTokens = listOf(
            char('⦻')
        )
    )

    @Test
    fun `Bad named entity oline without a semicolon`() = assertTokenization(
        input = "&oline",
        expectedTokens = listOf(
            *chars("&oline").toTypedArray()
        )
    )

    @Test
    fun `Named entity oline with a semicolon`() = assertTokenization(
        input = "&oline;",
        expectedTokens = listOf(
            char('‾')
        )
    )

    @Test
    fun `Bad named entity olt without a semicolon`() = assertTokenization(
        input = "&olt",
        expectedTokens = listOf(
            *chars("&olt").toTypedArray()
        )
    )

    @Test
    fun `Named entity olt with a semicolon`() = assertTokenization(
        input = "&olt;",
        expectedTokens = listOf(
            char('⧀')
        )
    )

    @Test
    fun `Bad named entity omacr without a semicolon`() = assertTokenization(
        input = "&omacr",
        expectedTokens = listOf(
            *chars("&omacr").toTypedArray()
        )
    )

    @Test
    fun `Named entity omacr with a semicolon`() = assertTokenization(
        input = "&omacr;",
        expectedTokens = listOf(
            char('ō')
        )
    )

    @Test
    fun `Bad named entity omega without a semicolon`() = assertTokenization(
        input = "&omega",
        expectedTokens = listOf(
            *chars("&omega").toTypedArray()
        )
    )

    @Test
    fun `Named entity omega with a semicolon`() = assertTokenization(
        input = "&omega;",
        expectedTokens = listOf(
            char('ω')
        )
    )

    @Test
    fun `Bad named entity omicron without a semicolon`() = assertTokenization(
        input = "&omicron",
        expectedTokens = listOf(
            *chars("&omicron").toTypedArray()
        )
    )

    @Test
    fun `Named entity omicron with a semicolon`() = assertTokenization(
        input = "&omicron;",
        expectedTokens = listOf(
            char('ο')
        )
    )

    @Test
    fun `Bad named entity omid without a semicolon`() = assertTokenization(
        input = "&omid",
        expectedTokens = listOf(
            *chars("&omid").toTypedArray()
        )
    )

    @Test
    fun `Named entity omid with a semicolon`() = assertTokenization(
        input = "&omid;",
        expectedTokens = listOf(
            char('⦶')
        )
    )

    @Test
    fun `Bad named entity ominus without a semicolon`() = assertTokenization(
        input = "&ominus",
        expectedTokens = listOf(
            *chars("&ominus").toTypedArray()
        )
    )

    @Test
    fun `Named entity ominus with a semicolon`() = assertTokenization(
        input = "&ominus;",
        expectedTokens = listOf(
            char('⊖')
        )
    )

    @Test
    fun `Bad named entity oopf without a semicolon`() = assertTokenization(
        input = "&oopf",
        expectedTokens = listOf(
            *chars("&oopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity oopf with a semicolon`() = assertTokenization(
        input = "&oopf;",
        expectedTokens = listOf(
            *chars("𝕠").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity opar without a semicolon`() = assertTokenization(
        input = "&opar",
        expectedTokens = listOf(
            *chars("&opar").toTypedArray()
        )
    )

    @Test
    fun `Named entity opar with a semicolon`() = assertTokenization(
        input = "&opar;",
        expectedTokens = listOf(
            char('⦷')
        )
    )

    @Test
    fun `Bad named entity operp without a semicolon`() = assertTokenization(
        input = "&operp",
        expectedTokens = listOf(
            *chars("&operp").toTypedArray()
        )
    )

    @Test
    fun `Named entity operp with a semicolon`() = assertTokenization(
        input = "&operp;",
        expectedTokens = listOf(
            char('⦹')
        )
    )

    @Test
    fun `Bad named entity oplus without a semicolon`() = assertTokenization(
        input = "&oplus",
        expectedTokens = listOf(
            *chars("&oplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity oplus with a semicolon`() = assertTokenization(
        input = "&oplus;",
        expectedTokens = listOf(
            char('⊕')
        )
    )

    @Test
    fun `Bad named entity or without a semicolon`() = assertTokenization(
        input = "&or",
        expectedTokens = listOf(
            *chars("&or").toTypedArray()
        )
    )

    @Test
    fun `Named entity or with a semicolon`() = assertTokenization(
        input = "&or;",
        expectedTokens = listOf(
            char('∨')
        )
    )

    @Test
    fun `Bad named entity orarr without a semicolon`() = assertTokenization(
        input = "&orarr",
        expectedTokens = listOf(
            *chars("&orarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity orarr with a semicolon`() = assertTokenization(
        input = "&orarr;",
        expectedTokens = listOf(
            char('↻')
        )
    )

    @Test
    fun `Bad named entity ord without a semicolon`() = assertTokenization(
        input = "&ord",
        expectedTokens = listOf(
            *chars("&ord").toTypedArray()
        )
    )

    @Test
    fun `Named entity ord with a semicolon`() = assertTokenization(
        input = "&ord;",
        expectedTokens = listOf(
            char('⩝')
        )
    )

    @Test
    fun `Bad named entity order without a semicolon`() = assertTokenization(
        input = "&order",
        expectedTokens = listOf(
            *chars("&order").toTypedArray()
        )
    )

    @Test
    fun `Named entity order with a semicolon`() = assertTokenization(
        input = "&order;",
        expectedTokens = listOf(
            char('ℴ')
        )
    )

    @Test
    fun `Bad named entity orderof without a semicolon`() = assertTokenization(
        input = "&orderof",
        expectedTokens = listOf(
            *chars("&orderof").toTypedArray()
        )
    )

    @Test
    fun `Named entity orderof with a semicolon`() = assertTokenization(
        input = "&orderof;",
        expectedTokens = listOf(
            char('ℴ')
        )
    )

    @Test
    fun `Named entity ordf without a semicolon`() = assertTokenization(
        input = "&ordf",
        expectedTokens = listOf(
            char('ª')
        )
    )

    @Test
    fun `Named entity ordf with a semicolon`() = assertTokenization(
        input = "&ordf;",
        expectedTokens = listOf(
            char('ª')
        )
    )

    @Test
    fun `Named entity ordm without a semicolon`() = assertTokenization(
        input = "&ordm",
        expectedTokens = listOf(
            char('º')
        )
    )

    @Test
    fun `Named entity ordm with a semicolon`() = assertTokenization(
        input = "&ordm;",
        expectedTokens = listOf(
            char('º')
        )
    )

    @Test
    fun `Bad named entity origof without a semicolon`() = assertTokenization(
        input = "&origof",
        expectedTokens = listOf(
            *chars("&origof").toTypedArray()
        )
    )

    @Test
    fun `Named entity origof with a semicolon`() = assertTokenization(
        input = "&origof;",
        expectedTokens = listOf(
            char('⊶')
        )
    )

    @Test
    fun `Bad named entity oror without a semicolon`() = assertTokenization(
        input = "&oror",
        expectedTokens = listOf(
            *chars("&oror").toTypedArray()
        )
    )

    @Test
    fun `Named entity oror with a semicolon`() = assertTokenization(
        input = "&oror;",
        expectedTokens = listOf(
            char('⩖')
        )
    )

    @Test
    fun `Bad named entity orslope without a semicolon`() = assertTokenization(
        input = "&orslope",
        expectedTokens = listOf(
            *chars("&orslope").toTypedArray()
        )
    )

    @Test
    fun `Named entity orslope with a semicolon`() = assertTokenization(
        input = "&orslope;",
        expectedTokens = listOf(
            char('⩗')
        )
    )

    @Test
    fun `Bad named entity orv without a semicolon`() = assertTokenization(
        input = "&orv",
        expectedTokens = listOf(
            *chars("&orv").toTypedArray()
        )
    )

    @Test
    fun `Named entity orv with a semicolon`() = assertTokenization(
        input = "&orv;",
        expectedTokens = listOf(
            char('⩛')
        )
    )

    @Test
    fun `Bad named entity oscr without a semicolon`() = assertTokenization(
        input = "&oscr",
        expectedTokens = listOf(
            *chars("&oscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity oscr with a semicolon`() = assertTokenization(
        input = "&oscr;",
        expectedTokens = listOf(
            char('ℴ')
        )
    )

    @Test
    fun `Named entity oslash without a semicolon`() = assertTokenization(
        input = "&oslash",
        expectedTokens = listOf(
            char('ø')
        )
    )

    @Test
    fun `Named entity oslash with a semicolon`() = assertTokenization(
        input = "&oslash;",
        expectedTokens = listOf(
            char('ø')
        )
    )

    @Test
    fun `Bad named entity osol without a semicolon`() = assertTokenization(
        input = "&osol",
        expectedTokens = listOf(
            *chars("&osol").toTypedArray()
        )
    )

    @Test
    fun `Named entity osol with a semicolon`() = assertTokenization(
        input = "&osol;",
        expectedTokens = listOf(
            char('⊘')
        )
    )

    @Test
    fun `Named entity otilde without a semicolon`() = assertTokenization(
        input = "&otilde",
        expectedTokens = listOf(
            char('õ')
        )
    )

    @Test
    fun `Named entity otilde with a semicolon`() = assertTokenization(
        input = "&otilde;",
        expectedTokens = listOf(
            char('õ')
        )
    )

    @Test
    fun `Bad named entity otimes without a semicolon`() = assertTokenization(
        input = "&otimes",
        expectedTokens = listOf(
            *chars("&otimes").toTypedArray()
        )
    )

    @Test
    fun `Named entity otimes with a semicolon`() = assertTokenization(
        input = "&otimes;",
        expectedTokens = listOf(
            char('⊗')
        )
    )

    @Test
    fun `Bad named entity otimesas without a semicolon`() = assertTokenization(
        input = "&otimesas",
        expectedTokens = listOf(
            *chars("&otimesas").toTypedArray()
        )
    )

    @Test
    fun `Named entity otimesas with a semicolon`() = assertTokenization(
        input = "&otimesas;",
        expectedTokens = listOf(
            char('⨶')
        )
    )

    @Test
    fun `Named entity ouml without a semicolon`() = assertTokenization(
        input = "&ouml",
        expectedTokens = listOf(
            char('ö')
        )
    )

    @Test
    fun `Named entity ouml with a semicolon`() = assertTokenization(
        input = "&ouml;",
        expectedTokens = listOf(
            char('ö')
        )
    )

    @Test
    fun `Bad named entity ovbar without a semicolon`() = assertTokenization(
        input = "&ovbar",
        expectedTokens = listOf(
            *chars("&ovbar").toTypedArray()
        )
    )

    @Test
    fun `Named entity ovbar with a semicolon`() = assertTokenization(
        input = "&ovbar;",
        expectedTokens = listOf(
            char('⌽')
        )
    )

    @Test
    fun `Bad named entity par without a semicolon`() = assertTokenization(
        input = "&par",
        expectedTokens = listOf(
            *chars("&par").toTypedArray()
        )
    )

    @Test
    fun `Named entity par with a semicolon`() = assertTokenization(
        input = "&par;",
        expectedTokens = listOf(
            char('∥')
        )
    )

    @Test
    fun `Named entity para without a semicolon`() = assertTokenization(
        input = "&para",
        expectedTokens = listOf(
            char('¶')
        )
    )

    @Test
    fun `Named entity para with a semicolon`() = assertTokenization(
        input = "&para;",
        expectedTokens = listOf(
            char('¶')
        )
    )

    @Test
    fun `Named entity parallel with a semicolon`() = assertTokenization(
        input = "&parallel;",
        expectedTokens = listOf(
            char('∥')
        )
    )

    @Test
    fun `Bad named entity parsim without a semicolon`() = assertTokenization(
        input = "&parsim",
        expectedTokens = listOf(
            *chars("&parsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity parsim with a semicolon`() = assertTokenization(
        input = "&parsim;",
        expectedTokens = listOf(
            char('⫳')
        )
    )

    @Test
    fun `Bad named entity parsl without a semicolon`() = assertTokenization(
        input = "&parsl",
        expectedTokens = listOf(
            *chars("&parsl").toTypedArray()
        )
    )

    @Test
    fun `Named entity parsl with a semicolon`() = assertTokenization(
        input = "&parsl;",
        expectedTokens = listOf(
            char('⫽')
        )
    )

    @Test
    fun `Bad named entity part without a semicolon`() = assertTokenization(
        input = "&part",
        expectedTokens = listOf(
            *chars("&part").toTypedArray()
        )
    )

    @Test
    fun `Named entity part with a semicolon`() = assertTokenization(
        input = "&part;",
        expectedTokens = listOf(
            char('∂')
        )
    )

    @Test
    fun `Bad named entity pcy without a semicolon`() = assertTokenization(
        input = "&pcy",
        expectedTokens = listOf(
            *chars("&pcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity pcy with a semicolon`() = assertTokenization(
        input = "&pcy;",
        expectedTokens = listOf(
            char('п')
        )
    )

    @Test
    fun `Bad named entity percnt without a semicolon`() = assertTokenization(
        input = "&percnt",
        expectedTokens = listOf(
            *chars("&percnt").toTypedArray()
        )
    )

    @Test
    fun `Named entity percnt with a semicolon`() = assertTokenization(
        input = "&percnt;",
        expectedTokens = listOf(
            char('%')
        )
    )

    @Test
    fun `Bad named entity period without a semicolon`() = assertTokenization(
        input = "&period",
        expectedTokens = listOf(
            *chars("&period").toTypedArray()
        )
    )

    @Test
    fun `Named entity period with a semicolon`() = assertTokenization(
        input = "&period;",
        expectedTokens = listOf(
            char('.')
        )
    )

    @Test
    fun `Bad named entity permil without a semicolon`() = assertTokenization(
        input = "&permil",
        expectedTokens = listOf(
            *chars("&permil").toTypedArray()
        )
    )

    @Test
    fun `Named entity permil with a semicolon`() = assertTokenization(
        input = "&permil;",
        expectedTokens = listOf(
            char('‰')
        )
    )

    @Test
    fun `Bad named entity perp without a semicolon`() = assertTokenization(
        input = "&perp",
        expectedTokens = listOf(
            *chars("&perp").toTypedArray()
        )
    )

    @Test
    fun `Named entity perp with a semicolon`() = assertTokenization(
        input = "&perp;",
        expectedTokens = listOf(
            char('⊥')
        )
    )

    @Test
    fun `Bad named entity pertenk without a semicolon`() = assertTokenization(
        input = "&pertenk",
        expectedTokens = listOf(
            *chars("&pertenk").toTypedArray()
        )
    )

    @Test
    fun `Named entity pertenk with a semicolon`() = assertTokenization(
        input = "&pertenk;",
        expectedTokens = listOf(
            char('‱')
        )
    )

    @Test
    fun `Bad named entity pfr without a semicolon`() = assertTokenization(
        input = "&pfr",
        expectedTokens = listOf(
            *chars("&pfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity pfr with a semicolon`() = assertTokenization(
        input = "&pfr;",
        expectedTokens = listOf(
            *chars("𝔭").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity phi without a semicolon`() = assertTokenization(
        input = "&phi",
        expectedTokens = listOf(
            *chars("&phi").toTypedArray()
        )
    )

    @Test
    fun `Named entity phi with a semicolon`() = assertTokenization(
        input = "&phi;",
        expectedTokens = listOf(
            char('φ')
        )
    )

    @Test
    fun `Bad named entity phiv without a semicolon`() = assertTokenization(
        input = "&phiv",
        expectedTokens = listOf(
            *chars("&phiv").toTypedArray()
        )
    )

    @Test
    fun `Named entity phiv with a semicolon`() = assertTokenization(
        input = "&phiv;",
        expectedTokens = listOf(
            char('ϕ')
        )
    )

    @Test
    fun `Bad named entity phmmat without a semicolon`() = assertTokenization(
        input = "&phmmat",
        expectedTokens = listOf(
            *chars("&phmmat").toTypedArray()
        )
    )

    @Test
    fun `Named entity phmmat with a semicolon`() = assertTokenization(
        input = "&phmmat;",
        expectedTokens = listOf(
            char('ℳ')
        )
    )

    @Test
    fun `Bad named entity phone without a semicolon`() = assertTokenization(
        input = "&phone",
        expectedTokens = listOf(
            *chars("&phone").toTypedArray()
        )
    )

    @Test
    fun `Named entity phone with a semicolon`() = assertTokenization(
        input = "&phone;",
        expectedTokens = listOf(
            char('☎')
        )
    )

    @Test
    fun `Bad named entity pi without a semicolon`() = assertTokenization(
        input = "&pi",
        expectedTokens = listOf(
            *chars("&pi").toTypedArray()
        )
    )

    @Test
    fun `Named entity pi with a semicolon`() = assertTokenization(
        input = "&pi;",
        expectedTokens = listOf(
            char('π')
        )
    )

    @Test
    fun `Bad named entity pitchfork without a semicolon`() = assertTokenization(
        input = "&pitchfork",
        expectedTokens = listOf(
            *chars("&pitchfork").toTypedArray()
        )
    )

    @Test
    fun `Named entity pitchfork with a semicolon`() = assertTokenization(
        input = "&pitchfork;",
        expectedTokens = listOf(
            char('⋔')
        )
    )

    @Test
    fun `Bad named entity piv without a semicolon`() = assertTokenization(
        input = "&piv",
        expectedTokens = listOf(
            *chars("&piv").toTypedArray()
        )
    )

    @Test
    fun `Named entity piv with a semicolon`() = assertTokenization(
        input = "&piv;",
        expectedTokens = listOf(
            char('ϖ')
        )
    )

    @Test
    fun `Bad named entity planck without a semicolon`() = assertTokenization(
        input = "&planck",
        expectedTokens = listOf(
            *chars("&planck").toTypedArray()
        )
    )

    @Test
    fun `Named entity planck with a semicolon`() = assertTokenization(
        input = "&planck;",
        expectedTokens = listOf(
            char('ℏ')
        )
    )

    @Test
    fun `Bad named entity planckh without a semicolon`() = assertTokenization(
        input = "&planckh",
        expectedTokens = listOf(
            *chars("&planckh").toTypedArray()
        )
    )

    @Test
    fun `Named entity planckh with a semicolon`() = assertTokenization(
        input = "&planckh;",
        expectedTokens = listOf(
            char('ℎ')
        )
    )

    @Test
    fun `Bad named entity plankv without a semicolon`() = assertTokenization(
        input = "&plankv",
        expectedTokens = listOf(
            *chars("&plankv").toTypedArray()
        )
    )

    @Test
    fun `Named entity plankv with a semicolon`() = assertTokenization(
        input = "&plankv;",
        expectedTokens = listOf(
            char('ℏ')
        )
    )

    @Test
    fun `Bad named entity plus without a semicolon`() = assertTokenization(
        input = "&plus",
        expectedTokens = listOf(
            *chars("&plus").toTypedArray()
        )
    )

    @Test
    fun `Named entity plus with a semicolon`() = assertTokenization(
        input = "&plus;",
        expectedTokens = listOf(
            char('+')
        )
    )

    @Test
    fun `Bad named entity plusacir without a semicolon`() = assertTokenization(
        input = "&plusacir",
        expectedTokens = listOf(
            *chars("&plusacir").toTypedArray()
        )
    )

    @Test
    fun `Named entity plusacir with a semicolon`() = assertTokenization(
        input = "&plusacir;",
        expectedTokens = listOf(
            char('⨣')
        )
    )

    @Test
    fun `Bad named entity plusb without a semicolon`() = assertTokenization(
        input = "&plusb",
        expectedTokens = listOf(
            *chars("&plusb").toTypedArray()
        )
    )

    @Test
    fun `Named entity plusb with a semicolon`() = assertTokenization(
        input = "&plusb;",
        expectedTokens = listOf(
            char('⊞')
        )
    )

    @Test
    fun `Bad named entity pluscir without a semicolon`() = assertTokenization(
        input = "&pluscir",
        expectedTokens = listOf(
            *chars("&pluscir").toTypedArray()
        )
    )

    @Test
    fun `Named entity pluscir with a semicolon`() = assertTokenization(
        input = "&pluscir;",
        expectedTokens = listOf(
            char('⨢')
        )
    )

    @Test
    fun `Bad named entity plusdo without a semicolon`() = assertTokenization(
        input = "&plusdo",
        expectedTokens = listOf(
            *chars("&plusdo").toTypedArray()
        )
    )

    @Test
    fun `Named entity plusdo with a semicolon`() = assertTokenization(
        input = "&plusdo;",
        expectedTokens = listOf(
            char('∔')
        )
    )

    @Test
    fun `Bad named entity plusdu without a semicolon`() = assertTokenization(
        input = "&plusdu",
        expectedTokens = listOf(
            *chars("&plusdu").toTypedArray()
        )
    )

    @Test
    fun `Named entity plusdu with a semicolon`() = assertTokenization(
        input = "&plusdu;",
        expectedTokens = listOf(
            char('⨥')
        )
    )

    @Test
    fun `Bad named entity pluse without a semicolon`() = assertTokenization(
        input = "&pluse",
        expectedTokens = listOf(
            *chars("&pluse").toTypedArray()
        )
    )

    @Test
    fun `Named entity pluse with a semicolon`() = assertTokenization(
        input = "&pluse;",
        expectedTokens = listOf(
            char('⩲')
        )
    )

    @Test
    fun `Named entity plusmn without a semicolon`() = assertTokenization(
        input = "&plusmn",
        expectedTokens = listOf(
            char('±')
        )
    )

    @Test
    fun `Named entity plusmn with a semicolon`() = assertTokenization(
        input = "&plusmn;",
        expectedTokens = listOf(
            char('±')
        )
    )

    @Test
    fun `Bad named entity plussim without a semicolon`() = assertTokenization(
        input = "&plussim",
        expectedTokens = listOf(
            *chars("&plussim").toTypedArray()
        )
    )

    @Test
    fun `Named entity plussim with a semicolon`() = assertTokenization(
        input = "&plussim;",
        expectedTokens = listOf(
            char('⨦')
        )
    )

    @Test
    fun `Bad named entity plustwo without a semicolon`() = assertTokenization(
        input = "&plustwo",
        expectedTokens = listOf(
            *chars("&plustwo").toTypedArray()
        )
    )

    @Test
    fun `Named entity plustwo with a semicolon`() = assertTokenization(
        input = "&plustwo;",
        expectedTokens = listOf(
            char('⨧')
        )
    )

    @Test
    fun `Bad named entity pm without a semicolon`() = assertTokenization(
        input = "&pm",
        expectedTokens = listOf(
            *chars("&pm").toTypedArray()
        )
    )

    @Test
    fun `Named entity pm with a semicolon`() = assertTokenization(
        input = "&pm;",
        expectedTokens = listOf(
            char('±')
        )
    )

    @Test
    fun `Bad named entity pointint without a semicolon`() = assertTokenization(
        input = "&pointint",
        expectedTokens = listOf(
            *chars("&pointint").toTypedArray()
        )
    )

    @Test
    fun `Named entity pointint with a semicolon`() = assertTokenization(
        input = "&pointint;",
        expectedTokens = listOf(
            char('⨕')
        )
    )

    @Test
    fun `Bad named entity popf without a semicolon`() = assertTokenization(
        input = "&popf",
        expectedTokens = listOf(
            *chars("&popf").toTypedArray()
        )
    )

    @Test
    fun `Named entity popf with a semicolon`() = assertTokenization(
        input = "&popf;",
        expectedTokens = listOf(
            *chars("𝕡").toTypedArray()
        )
    )

    @Test
    fun `Named entity pound without a semicolon`() = assertTokenization(
        input = "&pound",
        expectedTokens = listOf(
            char('£')
        )
    )

    @Test
    fun `Named entity pound with a semicolon`() = assertTokenization(
        input = "&pound;",
        expectedTokens = listOf(
            char('£')
        )
    )

    @Test
    fun `Bad named entity pr without a semicolon`() = assertTokenization(
        input = "&pr",
        expectedTokens = listOf(
            *chars("&pr").toTypedArray()
        )
    )

    @Test
    fun `Named entity pr with a semicolon`() = assertTokenization(
        input = "&pr;",
        expectedTokens = listOf(
            char('≺')
        )
    )

    @Test
    fun `Bad named entity prE without a semicolon`() = assertTokenization(
        input = "&prE",
        expectedTokens = listOf(
            *chars("&prE").toTypedArray()
        )
    )

    @Test
    fun `Named entity prE with a semicolon`() = assertTokenization(
        input = "&prE;",
        expectedTokens = listOf(
            char('⪳')
        )
    )

    @Test
    fun `Bad named entity prap without a semicolon`() = assertTokenization(
        input = "&prap",
        expectedTokens = listOf(
            *chars("&prap").toTypedArray()
        )
    )

    @Test
    fun `Named entity prap with a semicolon`() = assertTokenization(
        input = "&prap;",
        expectedTokens = listOf(
            char('⪷')
        )
    )

    @Test
    fun `Bad named entity prcue without a semicolon`() = assertTokenization(
        input = "&prcue",
        expectedTokens = listOf(
            *chars("&prcue").toTypedArray()
        )
    )

    @Test
    fun `Named entity prcue with a semicolon`() = assertTokenization(
        input = "&prcue;",
        expectedTokens = listOf(
            char('≼')
        )
    )

    @Test
    fun `Bad named entity pre without a semicolon`() = assertTokenization(
        input = "&pre",
        expectedTokens = listOf(
            *chars("&pre").toTypedArray()
        )
    )

    @Test
    fun `Named entity pre with a semicolon`() = assertTokenization(
        input = "&pre;",
        expectedTokens = listOf(
            char('⪯')
        )
    )

    @Test
    fun `Bad named entity prec without a semicolon`() = assertTokenization(
        input = "&prec",
        expectedTokens = listOf(
            *chars("&prec").toTypedArray()
        )
    )

    @Test
    fun `Named entity prec with a semicolon`() = assertTokenization(
        input = "&prec;",
        expectedTokens = listOf(
            char('≺')
        )
    )

    @Test
    fun `Bad named entity precapprox without a semicolon`() = assertTokenization(
        input = "&precapprox",
        expectedTokens = listOf(
            *chars("&precapprox").toTypedArray()
        )
    )

    @Test
    fun `Named entity precapprox with a semicolon`() = assertTokenization(
        input = "&precapprox;",
        expectedTokens = listOf(
            char('⪷')
        )
    )

    @Test
    fun `Bad named entity preccurlyeq without a semicolon`() = assertTokenization(
        input = "&preccurlyeq",
        expectedTokens = listOf(
            *chars("&preccurlyeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity preccurlyeq with a semicolon`() = assertTokenization(
        input = "&preccurlyeq;",
        expectedTokens = listOf(
            char('≼')
        )
    )

    @Test
    fun `Bad named entity preceq without a semicolon`() = assertTokenization(
        input = "&preceq",
        expectedTokens = listOf(
            *chars("&preceq").toTypedArray()
        )
    )

    @Test
    fun `Named entity preceq with a semicolon`() = assertTokenization(
        input = "&preceq;",
        expectedTokens = listOf(
            char('⪯')
        )
    )

    @Test
    fun `Bad named entity precnapprox without a semicolon`() = assertTokenization(
        input = "&precnapprox",
        expectedTokens = listOf(
            *chars("&precnapprox").toTypedArray()
        )
    )

    @Test
    fun `Named entity precnapprox with a semicolon`() = assertTokenization(
        input = "&precnapprox;",
        expectedTokens = listOf(
            char('⪹')
        )
    )

    @Test
    fun `Bad named entity precneqq without a semicolon`() = assertTokenization(
        input = "&precneqq",
        expectedTokens = listOf(
            *chars("&precneqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity precneqq with a semicolon`() = assertTokenization(
        input = "&precneqq;",
        expectedTokens = listOf(
            char('⪵')
        )
    )

    @Test
    fun `Bad named entity precnsim without a semicolon`() = assertTokenization(
        input = "&precnsim",
        expectedTokens = listOf(
            *chars("&precnsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity precnsim with a semicolon`() = assertTokenization(
        input = "&precnsim;",
        expectedTokens = listOf(
            char('⋨')
        )
    )

    @Test
    fun `Bad named entity precsim without a semicolon`() = assertTokenization(
        input = "&precsim",
        expectedTokens = listOf(
            *chars("&precsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity precsim with a semicolon`() = assertTokenization(
        input = "&precsim;",
        expectedTokens = listOf(
            char('≾')
        )
    )

    @Test
    fun `Bad named entity prime without a semicolon`() = assertTokenization(
        input = "&prime",
        expectedTokens = listOf(
            *chars("&prime").toTypedArray()
        )
    )

    @Test
    fun `Named entity prime with a semicolon`() = assertTokenization(
        input = "&prime;",
        expectedTokens = listOf(
            char('′')
        )
    )

    @Test
    fun `Bad named entity primes without a semicolon`() = assertTokenization(
        input = "&primes",
        expectedTokens = listOf(
            *chars("&primes").toTypedArray()
        )
    )

    @Test
    fun `Named entity primes with a semicolon`() = assertTokenization(
        input = "&primes;",
        expectedTokens = listOf(
            char('ℙ')
        )
    )

    @Test
    fun `Bad named entity prnE without a semicolon`() = assertTokenization(
        input = "&prnE",
        expectedTokens = listOf(
            *chars("&prnE").toTypedArray()
        )
    )

    @Test
    fun `Named entity prnE with a semicolon`() = assertTokenization(
        input = "&prnE;",
        expectedTokens = listOf(
            char('⪵')
        )
    )

    @Test
    fun `Bad named entity prnap without a semicolon`() = assertTokenization(
        input = "&prnap",
        expectedTokens = listOf(
            *chars("&prnap").toTypedArray()
        )
    )

    @Test
    fun `Named entity prnap with a semicolon`() = assertTokenization(
        input = "&prnap;",
        expectedTokens = listOf(
            char('⪹')
        )
    )

    @Test
    fun `Bad named entity prnsim without a semicolon`() = assertTokenization(
        input = "&prnsim",
        expectedTokens = listOf(
            *chars("&prnsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity prnsim with a semicolon`() = assertTokenization(
        input = "&prnsim;",
        expectedTokens = listOf(
            char('⋨')
        )
    )

    @Test
    fun `Bad named entity prod without a semicolon`() = assertTokenization(
        input = "&prod",
        expectedTokens = listOf(
            *chars("&prod").toTypedArray()
        )
    )

    @Test
    fun `Named entity prod with a semicolon`() = assertTokenization(
        input = "&prod;",
        expectedTokens = listOf(
            char('∏')
        )
    )

    @Test
    fun `Bad named entity profalar without a semicolon`() = assertTokenization(
        input = "&profalar",
        expectedTokens = listOf(
            *chars("&profalar").toTypedArray()
        )
    )

    @Test
    fun `Named entity profalar with a semicolon`() = assertTokenization(
        input = "&profalar;",
        expectedTokens = listOf(
            char('⌮')
        )
    )

    @Test
    fun `Bad named entity profline without a semicolon`() = assertTokenization(
        input = "&profline",
        expectedTokens = listOf(
            *chars("&profline").toTypedArray()
        )
    )

    @Test
    fun `Named entity profline with a semicolon`() = assertTokenization(
        input = "&profline;",
        expectedTokens = listOf(
            char('⌒')
        )
    )

    @Test
    fun `Bad named entity profsurf without a semicolon`() = assertTokenization(
        input = "&profsurf",
        expectedTokens = listOf(
            *chars("&profsurf").toTypedArray()
        )
    )

    @Test
    fun `Named entity profsurf with a semicolon`() = assertTokenization(
        input = "&profsurf;",
        expectedTokens = listOf(
            char('⌓')
        )
    )

    @Test
    fun `Bad named entity prop without a semicolon`() = assertTokenization(
        input = "&prop",
        expectedTokens = listOf(
            *chars("&prop").toTypedArray()
        )
    )

    @Test
    fun `Named entity prop with a semicolon`() = assertTokenization(
        input = "&prop;",
        expectedTokens = listOf(
            char('∝')
        )
    )

    @Test
    fun `Bad named entity propto without a semicolon`() = assertTokenization(
        input = "&propto",
        expectedTokens = listOf(
            *chars("&propto").toTypedArray()
        )
    )

    @Test
    fun `Named entity propto with a semicolon`() = assertTokenization(
        input = "&propto;",
        expectedTokens = listOf(
            char('∝')
        )
    )

    @Test
    fun `Bad named entity prsim without a semicolon`() = assertTokenization(
        input = "&prsim",
        expectedTokens = listOf(
            *chars("&prsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity prsim with a semicolon`() = assertTokenization(
        input = "&prsim;",
        expectedTokens = listOf(
            char('≾')
        )
    )

    @Test
    fun `Bad named entity prurel without a semicolon`() = assertTokenization(
        input = "&prurel",
        expectedTokens = listOf(
            *chars("&prurel").toTypedArray()
        )
    )

    @Test
    fun `Named entity prurel with a semicolon`() = assertTokenization(
        input = "&prurel;",
        expectedTokens = listOf(
            char('⊰')
        )
    )

    @Test
    fun `Bad named entity pscr without a semicolon`() = assertTokenization(
        input = "&pscr",
        expectedTokens = listOf(
            *chars("&pscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity pscr with a semicolon`() = assertTokenization(
        input = "&pscr;",
        expectedTokens = listOf(
            *chars("𝓅").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity psi without a semicolon`() = assertTokenization(
        input = "&psi",
        expectedTokens = listOf(
            *chars("&psi").toTypedArray()
        )
    )

    @Test
    fun `Named entity psi with a semicolon`() = assertTokenization(
        input = "&psi;",
        expectedTokens = listOf(
            char('ψ')
        )
    )

    @Test
    fun `Bad named entity puncsp without a semicolon`() = assertTokenization(
        input = "&puncsp",
        expectedTokens = listOf(
            *chars("&puncsp").toTypedArray()
        )
    )

    @Test
    fun `Named entity puncsp with a semicolon`() = assertTokenization(
        input = "&puncsp;",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Bad named entity qfr without a semicolon`() = assertTokenization(
        input = "&qfr",
        expectedTokens = listOf(
            *chars("&qfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity qfr with a semicolon`() = assertTokenization(
        input = "&qfr;",
        expectedTokens = listOf(
            *chars("𝔮").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity qint without a semicolon`() = assertTokenization(
        input = "&qint",
        expectedTokens = listOf(
            *chars("&qint").toTypedArray()
        )
    )

    @Test
    fun `Named entity qint with a semicolon`() = assertTokenization(
        input = "&qint;",
        expectedTokens = listOf(
            char('⨌')
        )
    )

    @Test
    fun `Bad named entity qopf without a semicolon`() = assertTokenization(
        input = "&qopf",
        expectedTokens = listOf(
            *chars("&qopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity qopf with a semicolon`() = assertTokenization(
        input = "&qopf;",
        expectedTokens = listOf(
            *chars("𝕢").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity qprime without a semicolon`() = assertTokenization(
        input = "&qprime",
        expectedTokens = listOf(
            *chars("&qprime").toTypedArray()
        )
    )

    @Test
    fun `Named entity qprime with a semicolon`() = assertTokenization(
        input = "&qprime;",
        expectedTokens = listOf(
            char('⁗')
        )
    )

    @Test
    fun `Bad named entity qscr without a semicolon`() = assertTokenization(
        input = "&qscr",
        expectedTokens = listOf(
            *chars("&qscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity qscr with a semicolon`() = assertTokenization(
        input = "&qscr;",
        expectedTokens = listOf(
            *chars("𝓆").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity quaternions without a semicolon`() = assertTokenization(
        input = "&quaternions",
        expectedTokens = listOf(
            *chars("&quaternions").toTypedArray()
        )
    )

    @Test
    fun `Named entity quaternions with a semicolon`() = assertTokenization(
        input = "&quaternions;",
        expectedTokens = listOf(
            char('ℍ')
        )
    )

    @Test
    fun `Bad named entity quatint without a semicolon`() = assertTokenization(
        input = "&quatint",
        expectedTokens = listOf(
            *chars("&quatint").toTypedArray()
        )
    )

    @Test
    fun `Named entity quatint with a semicolon`() = assertTokenization(
        input = "&quatint;",
        expectedTokens = listOf(
            char('⨖')
        )
    )

    @Test
    fun `Bad named entity quest without a semicolon`() = assertTokenization(
        input = "&quest",
        expectedTokens = listOf(
            *chars("&quest").toTypedArray()
        )
    )

    @Test
    fun `Named entity quest with a semicolon`() = assertTokenization(
        input = "&quest;",
        expectedTokens = listOf(
            char('?')
        )
    )

    @Test
    fun `Bad named entity questeq without a semicolon`() = assertTokenization(
        input = "&questeq",
        expectedTokens = listOf(
            *chars("&questeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity questeq with a semicolon`() = assertTokenization(
        input = "&questeq;",
        expectedTokens = listOf(
            char('≟')
        )
    )

    @Test
    fun `Named entity quot without a semicolon`() = assertTokenization(
        input = "&quot",
        expectedTokens = listOf(
            char('\"')
        )
    )

    @Test
    fun `Named entity quot with a semicolon`() = assertTokenization(
        input = "&quot;",
        expectedTokens = listOf(
            char('\"')
        )
    )

    @Test
    fun `Bad named entity rAarr without a semicolon`() = assertTokenization(
        input = "&rAarr",
        expectedTokens = listOf(
            *chars("&rAarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity rAarr with a semicolon`() = assertTokenization(
        input = "&rAarr;",
        expectedTokens = listOf(
            char('⇛')
        )
    )

    @Test
    fun `Bad named entity rArr without a semicolon`() = assertTokenization(
        input = "&rArr",
        expectedTokens = listOf(
            *chars("&rArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity rArr with a semicolon`() = assertTokenization(
        input = "&rArr;",
        expectedTokens = listOf(
            char('⇒')
        )
    )

    @Test
    fun `Bad named entity rAtail without a semicolon`() = assertTokenization(
        input = "&rAtail",
        expectedTokens = listOf(
            *chars("&rAtail").toTypedArray()
        )
    )

    @Test
    fun `Named entity rAtail with a semicolon`() = assertTokenization(
        input = "&rAtail;",
        expectedTokens = listOf(
            char('⤜')
        )
    )

    @Test
    fun `Bad named entity rBarr without a semicolon`() = assertTokenization(
        input = "&rBarr",
        expectedTokens = listOf(
            *chars("&rBarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity rBarr with a semicolon`() = assertTokenization(
        input = "&rBarr;",
        expectedTokens = listOf(
            char('⤏')
        )
    )

    @Test
    fun `Bad named entity rHar without a semicolon`() = assertTokenization(
        input = "&rHar",
        expectedTokens = listOf(
            *chars("&rHar").toTypedArray()
        )
    )

    @Test
    fun `Named entity rHar with a semicolon`() = assertTokenization(
        input = "&rHar;",
        expectedTokens = listOf(
            char('⥤')
        )
    )

    @Test
    fun `Bad named entity race without a semicolon`() = assertTokenization(
        input = "&race",
        expectedTokens = listOf(
            *chars("&race").toTypedArray()
        )
    )

    @Test
    fun `Named entity race with a semicolon`() = assertTokenization(
        input = "&race;",
        expectedTokens = listOf(
            *chars("∽̱").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity racute without a semicolon`() = assertTokenization(
        input = "&racute",
        expectedTokens = listOf(
            *chars("&racute").toTypedArray()
        )
    )

    @Test
    fun `Named entity racute with a semicolon`() = assertTokenization(
        input = "&racute;",
        expectedTokens = listOf(
            char('ŕ')
        )
    )

    @Test
    fun `Bad named entity radic without a semicolon`() = assertTokenization(
        input = "&radic",
        expectedTokens = listOf(
            *chars("&radic").toTypedArray()
        )
    )

    @Test
    fun `Named entity radic with a semicolon`() = assertTokenization(
        input = "&radic;",
        expectedTokens = listOf(
            char('√')
        )
    )

    @Test
    fun `Bad named entity raemptyv without a semicolon`() = assertTokenization(
        input = "&raemptyv",
        expectedTokens = listOf(
            *chars("&raemptyv").toTypedArray()
        )
    )

    @Test
    fun `Named entity raemptyv with a semicolon`() = assertTokenization(
        input = "&raemptyv;",
        expectedTokens = listOf(
            char('⦳')
        )
    )

    @Test
    fun `Bad named entity rang without a semicolon`() = assertTokenization(
        input = "&rang",
        expectedTokens = listOf(
            *chars("&rang").toTypedArray()
        )
    )

    @Test
    fun `Named entity rang with a semicolon`() = assertTokenization(
        input = "&rang;",
        expectedTokens = listOf(
            char('⟩')
        )
    )

    @Test
    fun `Bad named entity rangd without a semicolon`() = assertTokenization(
        input = "&rangd",
        expectedTokens = listOf(
            *chars("&rangd").toTypedArray()
        )
    )

    @Test
    fun `Named entity rangd with a semicolon`() = assertTokenization(
        input = "&rangd;",
        expectedTokens = listOf(
            char('⦒')
        )
    )

    @Test
    fun `Bad named entity range without a semicolon`() = assertTokenization(
        input = "&range",
        expectedTokens = listOf(
            *chars("&range").toTypedArray()
        )
    )

    @Test
    fun `Named entity range with a semicolon`() = assertTokenization(
        input = "&range;",
        expectedTokens = listOf(
            char('⦥')
        )
    )

    @Test
    fun `Bad named entity rangle without a semicolon`() = assertTokenization(
        input = "&rangle",
        expectedTokens = listOf(
            *chars("&rangle").toTypedArray()
        )
    )

    @Test
    fun `Named entity rangle with a semicolon`() = assertTokenization(
        input = "&rangle;",
        expectedTokens = listOf(
            char('⟩')
        )
    )

    @Test
    fun `Named entity raquo without a semicolon`() = assertTokenization(
        input = "&raquo",
        expectedTokens = listOf(
            char('»')
        )
    )

    @Test
    fun `Named entity raquo with a semicolon`() = assertTokenization(
        input = "&raquo;",
        expectedTokens = listOf(
            char('»')
        )
    )

    @Test
    fun `Bad named entity rarr without a semicolon`() = assertTokenization(
        input = "&rarr",
        expectedTokens = listOf(
            *chars("&rarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity rarr with a semicolon`() = assertTokenization(
        input = "&rarr;",
        expectedTokens = listOf(
            char('→')
        )
    )

    @Test
    fun `Bad named entity rarrap without a semicolon`() = assertTokenization(
        input = "&rarrap",
        expectedTokens = listOf(
            *chars("&rarrap").toTypedArray()
        )
    )

    @Test
    fun `Named entity rarrap with a semicolon`() = assertTokenization(
        input = "&rarrap;",
        expectedTokens = listOf(
            char('⥵')
        )
    )

    @Test
    fun `Bad named entity rarrb without a semicolon`() = assertTokenization(
        input = "&rarrb",
        expectedTokens = listOf(
            *chars("&rarrb").toTypedArray()
        )
    )

    @Test
    fun `Named entity rarrb with a semicolon`() = assertTokenization(
        input = "&rarrb;",
        expectedTokens = listOf(
            char('⇥')
        )
    )

    @Test
    fun `Bad named entity rarrbfs without a semicolon`() = assertTokenization(
        input = "&rarrbfs",
        expectedTokens = listOf(
            *chars("&rarrbfs").toTypedArray()
        )
    )

    @Test
    fun `Named entity rarrbfs with a semicolon`() = assertTokenization(
        input = "&rarrbfs;",
        expectedTokens = listOf(
            char('⤠')
        )
    )

    @Test
    fun `Bad named entity rarrc without a semicolon`() = assertTokenization(
        input = "&rarrc",
        expectedTokens = listOf(
            *chars("&rarrc").toTypedArray()
        )
    )

    @Test
    fun `Named entity rarrc with a semicolon`() = assertTokenization(
        input = "&rarrc;",
        expectedTokens = listOf(
            char('⤳')
        )
    )

    @Test
    fun `Bad named entity rarrfs without a semicolon`() = assertTokenization(
        input = "&rarrfs",
        expectedTokens = listOf(
            *chars("&rarrfs").toTypedArray()
        )
    )

    @Test
    fun `Named entity rarrfs with a semicolon`() = assertTokenization(
        input = "&rarrfs;",
        expectedTokens = listOf(
            char('⤞')
        )
    )

    @Test
    fun `Bad named entity rarrhk without a semicolon`() = assertTokenization(
        input = "&rarrhk",
        expectedTokens = listOf(
            *chars("&rarrhk").toTypedArray()
        )
    )

    @Test
    fun `Named entity rarrhk with a semicolon`() = assertTokenization(
        input = "&rarrhk;",
        expectedTokens = listOf(
            char('↪')
        )
    )

    @Test
    fun `Bad named entity rarrlp without a semicolon`() = assertTokenization(
        input = "&rarrlp",
        expectedTokens = listOf(
            *chars("&rarrlp").toTypedArray()
        )
    )

    @Test
    fun `Named entity rarrlp with a semicolon`() = assertTokenization(
        input = "&rarrlp;",
        expectedTokens = listOf(
            char('↬')
        )
    )

    @Test
    fun `Bad named entity rarrpl without a semicolon`() = assertTokenization(
        input = "&rarrpl",
        expectedTokens = listOf(
            *chars("&rarrpl").toTypedArray()
        )
    )

    @Test
    fun `Named entity rarrpl with a semicolon`() = assertTokenization(
        input = "&rarrpl;",
        expectedTokens = listOf(
            char('⥅')
        )
    )

    @Test
    fun `Bad named entity rarrsim without a semicolon`() = assertTokenization(
        input = "&rarrsim",
        expectedTokens = listOf(
            *chars("&rarrsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity rarrsim with a semicolon`() = assertTokenization(
        input = "&rarrsim;",
        expectedTokens = listOf(
            char('⥴')
        )
    )

    @Test
    fun `Bad named entity rarrtl without a semicolon`() = assertTokenization(
        input = "&rarrtl",
        expectedTokens = listOf(
            *chars("&rarrtl").toTypedArray()
        )
    )

    @Test
    fun `Named entity rarrtl with a semicolon`() = assertTokenization(
        input = "&rarrtl;",
        expectedTokens = listOf(
            char('↣')
        )
    )

    @Test
    fun `Bad named entity rarrw without a semicolon`() = assertTokenization(
        input = "&rarrw",
        expectedTokens = listOf(
            *chars("&rarrw").toTypedArray()
        )
    )

    @Test
    fun `Named entity rarrw with a semicolon`() = assertTokenization(
        input = "&rarrw;",
        expectedTokens = listOf(
            char('↝')
        )
    )

    @Test
    fun `Bad named entity ratail without a semicolon`() = assertTokenization(
        input = "&ratail",
        expectedTokens = listOf(
            *chars("&ratail").toTypedArray()
        )
    )

    @Test
    fun `Named entity ratail with a semicolon`() = assertTokenization(
        input = "&ratail;",
        expectedTokens = listOf(
            char('⤚')
        )
    )

    @Test
    fun `Bad named entity ratio without a semicolon`() = assertTokenization(
        input = "&ratio",
        expectedTokens = listOf(
            *chars("&ratio").toTypedArray()
        )
    )

    @Test
    fun `Named entity ratio with a semicolon`() = assertTokenization(
        input = "&ratio;",
        expectedTokens = listOf(
            char('∶')
        )
    )

    @Test
    fun `Bad named entity rationals without a semicolon`() = assertTokenization(
        input = "&rationals",
        expectedTokens = listOf(
            *chars("&rationals").toTypedArray()
        )
    )

    @Test
    fun `Named entity rationals with a semicolon`() = assertTokenization(
        input = "&rationals;",
        expectedTokens = listOf(
            char('ℚ')
        )
    )

    @Test
    fun `Bad named entity rbarr without a semicolon`() = assertTokenization(
        input = "&rbarr",
        expectedTokens = listOf(
            *chars("&rbarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity rbarr with a semicolon`() = assertTokenization(
        input = "&rbarr;",
        expectedTokens = listOf(
            char('⤍')
        )
    )

    @Test
    fun `Bad named entity rbbrk without a semicolon`() = assertTokenization(
        input = "&rbbrk",
        expectedTokens = listOf(
            *chars("&rbbrk").toTypedArray()
        )
    )

    @Test
    fun `Named entity rbbrk with a semicolon`() = assertTokenization(
        input = "&rbbrk;",
        expectedTokens = listOf(
            char('❳')
        )
    )

    @Test
    fun `Bad named entity rbrace without a semicolon`() = assertTokenization(
        input = "&rbrace",
        expectedTokens = listOf(
            *chars("&rbrace").toTypedArray()
        )
    )

    @Test
    fun `Named entity rbrace with a semicolon`() = assertTokenization(
        input = "&rbrace;",
        expectedTokens = listOf(
            char('}')
        )
    )

    @Test
    fun `Bad named entity rbrack without a semicolon`() = assertTokenization(
        input = "&rbrack",
        expectedTokens = listOf(
            *chars("&rbrack").toTypedArray()
        )
    )

    @Test
    fun `Named entity rbrack with a semicolon`() = assertTokenization(
        input = "&rbrack;",
        expectedTokens = listOf(
            char(']')
        )
    )

    @Test
    fun `Bad named entity rbrke without a semicolon`() = assertTokenization(
        input = "&rbrke",
        expectedTokens = listOf(
            *chars("&rbrke").toTypedArray()
        )
    )

    @Test
    fun `Named entity rbrke with a semicolon`() = assertTokenization(
        input = "&rbrke;",
        expectedTokens = listOf(
            char('⦌')
        )
    )

    @Test
    fun `Bad named entity rbrksld without a semicolon`() = assertTokenization(
        input = "&rbrksld",
        expectedTokens = listOf(
            *chars("&rbrksld").toTypedArray()
        )
    )

    @Test
    fun `Named entity rbrksld with a semicolon`() = assertTokenization(
        input = "&rbrksld;",
        expectedTokens = listOf(
            char('⦎')
        )
    )

    @Test
    fun `Bad named entity rbrkslu without a semicolon`() = assertTokenization(
        input = "&rbrkslu",
        expectedTokens = listOf(
            *chars("&rbrkslu").toTypedArray()
        )
    )

    @Test
    fun `Named entity rbrkslu with a semicolon`() = assertTokenization(
        input = "&rbrkslu;",
        expectedTokens = listOf(
            char('⦐')
        )
    )

    @Test
    fun `Bad named entity rcaron without a semicolon`() = assertTokenization(
        input = "&rcaron",
        expectedTokens = listOf(
            *chars("&rcaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity rcaron with a semicolon`() = assertTokenization(
        input = "&rcaron;",
        expectedTokens = listOf(
            char('ř')
        )
    )

    @Test
    fun `Bad named entity rcedil without a semicolon`() = assertTokenization(
        input = "&rcedil",
        expectedTokens = listOf(
            *chars("&rcedil").toTypedArray()
        )
    )

    @Test
    fun `Named entity rcedil with a semicolon`() = assertTokenization(
        input = "&rcedil;",
        expectedTokens = listOf(
            char('ŗ')
        )
    )

    @Test
    fun `Bad named entity rceil without a semicolon`() = assertTokenization(
        input = "&rceil",
        expectedTokens = listOf(
            *chars("&rceil").toTypedArray()
        )
    )

    @Test
    fun `Named entity rceil with a semicolon`() = assertTokenization(
        input = "&rceil;",
        expectedTokens = listOf(
            char('⌉')
        )
    )

    @Test
    fun `Bad named entity rcub without a semicolon`() = assertTokenization(
        input = "&rcub",
        expectedTokens = listOf(
            *chars("&rcub").toTypedArray()
        )
    )

    @Test
    fun `Named entity rcub with a semicolon`() = assertTokenization(
        input = "&rcub;",
        expectedTokens = listOf(
            char('}')
        )
    )

    @Test
    fun `Bad named entity rcy without a semicolon`() = assertTokenization(
        input = "&rcy",
        expectedTokens = listOf(
            *chars("&rcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity rcy with a semicolon`() = assertTokenization(
        input = "&rcy;",
        expectedTokens = listOf(
            char('р')
        )
    )

    @Test
    fun `Bad named entity rdca without a semicolon`() = assertTokenization(
        input = "&rdca",
        expectedTokens = listOf(
            *chars("&rdca").toTypedArray()
        )
    )

    @Test
    fun `Named entity rdca with a semicolon`() = assertTokenization(
        input = "&rdca;",
        expectedTokens = listOf(
            char('⤷')
        )
    )

    @Test
    fun `Bad named entity rdldhar without a semicolon`() = assertTokenization(
        input = "&rdldhar",
        expectedTokens = listOf(
            *chars("&rdldhar").toTypedArray()
        )
    )

    @Test
    fun `Named entity rdldhar with a semicolon`() = assertTokenization(
        input = "&rdldhar;",
        expectedTokens = listOf(
            char('⥩')
        )
    )

    @Test
    fun `Bad named entity rdquo without a semicolon`() = assertTokenization(
        input = "&rdquo",
        expectedTokens = listOf(
            *chars("&rdquo").toTypedArray()
        )
    )

    @Test
    fun `Named entity rdquo with a semicolon`() = assertTokenization(
        input = "&rdquo;",
        expectedTokens = listOf(
            char('”')
        )
    )

    @Test
    fun `Bad named entity rdquor without a semicolon`() = assertTokenization(
        input = "&rdquor",
        expectedTokens = listOf(
            *chars("&rdquor").toTypedArray()
        )
    )

    @Test
    fun `Named entity rdquor with a semicolon`() = assertTokenization(
        input = "&rdquor;",
        expectedTokens = listOf(
            char('”')
        )
    )

    @Test
    fun `Bad named entity rdsh without a semicolon`() = assertTokenization(
        input = "&rdsh",
        expectedTokens = listOf(
            *chars("&rdsh").toTypedArray()
        )
    )

    @Test
    fun `Named entity rdsh with a semicolon`() = assertTokenization(
        input = "&rdsh;",
        expectedTokens = listOf(
            char('↳')
        )
    )

    @Test
    fun `Bad named entity real without a semicolon`() = assertTokenization(
        input = "&real",
        expectedTokens = listOf(
            *chars("&real").toTypedArray()
        )
    )

    @Test
    fun `Named entity real with a semicolon`() = assertTokenization(
        input = "&real;",
        expectedTokens = listOf(
            char('ℜ')
        )
    )

    @Test
    fun `Bad named entity realine without a semicolon`() = assertTokenization(
        input = "&realine",
        expectedTokens = listOf(
            *chars("&realine").toTypedArray()
        )
    )

    @Test
    fun `Named entity realine with a semicolon`() = assertTokenization(
        input = "&realine;",
        expectedTokens = listOf(
            char('ℛ')
        )
    )

    @Test
    fun `Bad named entity realpart without a semicolon`() = assertTokenization(
        input = "&realpart",
        expectedTokens = listOf(
            *chars("&realpart").toTypedArray()
        )
    )

    @Test
    fun `Named entity realpart with a semicolon`() = assertTokenization(
        input = "&realpart;",
        expectedTokens = listOf(
            char('ℜ')
        )
    )

    @Test
    fun `Bad named entity reals without a semicolon`() = assertTokenization(
        input = "&reals",
        expectedTokens = listOf(
            *chars("&reals").toTypedArray()
        )
    )

    @Test
    fun `Named entity reals with a semicolon`() = assertTokenization(
        input = "&reals;",
        expectedTokens = listOf(
            char('ℝ')
        )
    )

    @Test
    fun `Bad named entity rect without a semicolon`() = assertTokenization(
        input = "&rect",
        expectedTokens = listOf(
            *chars("&rect").toTypedArray()
        )
    )

    @Test
    fun `Named entity rect with a semicolon`() = assertTokenization(
        input = "&rect;",
        expectedTokens = listOf(
            char('▭')
        )
    )

    @Test
    fun `Named entity reg without a semicolon`() = assertTokenization(
        input = "&reg",
        expectedTokens = listOf(
            char('®')
        )
    )

    @Test
    fun `Named entity reg with a semicolon`() = assertTokenization(
        input = "&reg;",
        expectedTokens = listOf(
            char('®')
        )
    )

    @Test
    fun `Bad named entity rfisht without a semicolon`() = assertTokenization(
        input = "&rfisht",
        expectedTokens = listOf(
            *chars("&rfisht").toTypedArray()
        )
    )

    @Test
    fun `Named entity rfisht with a semicolon`() = assertTokenization(
        input = "&rfisht;",
        expectedTokens = listOf(
            char('⥽')
        )
    )

    @Test
    fun `Bad named entity rfloor without a semicolon`() = assertTokenization(
        input = "&rfloor",
        expectedTokens = listOf(
            *chars("&rfloor").toTypedArray()
        )
    )

    @Test
    fun `Named entity rfloor with a semicolon`() = assertTokenization(
        input = "&rfloor;",
        expectedTokens = listOf(
            char('⌋')
        )
    )

    @Test
    fun `Bad named entity rfr without a semicolon`() = assertTokenization(
        input = "&rfr",
        expectedTokens = listOf(
            *chars("&rfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity rfr with a semicolon`() = assertTokenization(
        input = "&rfr;",
        expectedTokens = listOf(
            *chars("𝔯").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity rhard without a semicolon`() = assertTokenization(
        input = "&rhard",
        expectedTokens = listOf(
            *chars("&rhard").toTypedArray()
        )
    )

    @Test
    fun `Named entity rhard with a semicolon`() = assertTokenization(
        input = "&rhard;",
        expectedTokens = listOf(
            char('⇁')
        )
    )

    @Test
    fun `Bad named entity rharu without a semicolon`() = assertTokenization(
        input = "&rharu",
        expectedTokens = listOf(
            *chars("&rharu").toTypedArray()
        )
    )

    @Test
    fun `Named entity rharu with a semicolon`() = assertTokenization(
        input = "&rharu;",
        expectedTokens = listOf(
            char('⇀')
        )
    )

    @Test
    fun `Bad named entity rharul without a semicolon`() = assertTokenization(
        input = "&rharul",
        expectedTokens = listOf(
            *chars("&rharul").toTypedArray()
        )
    )

    @Test
    fun `Named entity rharul with a semicolon`() = assertTokenization(
        input = "&rharul;",
        expectedTokens = listOf(
            char('⥬')
        )
    )

    @Test
    fun `Bad named entity rho without a semicolon`() = assertTokenization(
        input = "&rho",
        expectedTokens = listOf(
            *chars("&rho").toTypedArray()
        )
    )

    @Test
    fun `Named entity rho with a semicolon`() = assertTokenization(
        input = "&rho;",
        expectedTokens = listOf(
            char('ρ')
        )
    )

    @Test
    fun `Bad named entity rhov without a semicolon`() = assertTokenization(
        input = "&rhov",
        expectedTokens = listOf(
            *chars("&rhov").toTypedArray()
        )
    )

    @Test
    fun `Named entity rhov with a semicolon`() = assertTokenization(
        input = "&rhov;",
        expectedTokens = listOf(
            char('ϱ')
        )
    )

    @Test
    fun `Bad named entity rightarrow without a semicolon`() = assertTokenization(
        input = "&rightarrow",
        expectedTokens = listOf(
            *chars("&rightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity rightarrow with a semicolon`() = assertTokenization(
        input = "&rightarrow;",
        expectedTokens = listOf(
            char('→')
        )
    )

    @Test
    fun `Bad named entity rightarrowtail without a semicolon`() = assertTokenization(
        input = "&rightarrowtail",
        expectedTokens = listOf(
            *chars("&rightarrowtail").toTypedArray()
        )
    )

    @Test
    fun `Named entity rightarrowtail with a semicolon`() = assertTokenization(
        input = "&rightarrowtail;",
        expectedTokens = listOf(
            char('↣')
        )
    )

    @Test
    fun `Bad named entity rightharpoondown without a semicolon`() = assertTokenization(
        input = "&rightharpoondown",
        expectedTokens = listOf(
            *chars("&rightharpoondown").toTypedArray()
        )
    )

    @Test
    fun `Named entity rightharpoondown with a semicolon`() = assertTokenization(
        input = "&rightharpoondown;",
        expectedTokens = listOf(
            char('⇁')
        )
    )

    @Test
    fun `Bad named entity rightharpoonup without a semicolon`() = assertTokenization(
        input = "&rightharpoonup",
        expectedTokens = listOf(
            *chars("&rightharpoonup").toTypedArray()
        )
    )

    @Test
    fun `Named entity rightharpoonup with a semicolon`() = assertTokenization(
        input = "&rightharpoonup;",
        expectedTokens = listOf(
            char('⇀')
        )
    )

    @Test
    fun `Bad named entity rightleftarrows without a semicolon`() = assertTokenization(
        input = "&rightleftarrows",
        expectedTokens = listOf(
            *chars("&rightleftarrows").toTypedArray()
        )
    )

    @Test
    fun `Named entity rightleftarrows with a semicolon`() = assertTokenization(
        input = "&rightleftarrows;",
        expectedTokens = listOf(
            char('⇄')
        )
    )

    @Test
    fun `Bad named entity rightleftharpoons without a semicolon`() = assertTokenization(
        input = "&rightleftharpoons",
        expectedTokens = listOf(
            *chars("&rightleftharpoons").toTypedArray()
        )
    )

    @Test
    fun `Named entity rightleftharpoons with a semicolon`() = assertTokenization(
        input = "&rightleftharpoons;",
        expectedTokens = listOf(
            char('⇌')
        )
    )

    @Test
    fun `Bad named entity rightrightarrows without a semicolon`() = assertTokenization(
        input = "&rightrightarrows",
        expectedTokens = listOf(
            *chars("&rightrightarrows").toTypedArray()
        )
    )

    @Test
    fun `Named entity rightrightarrows with a semicolon`() = assertTokenization(
        input = "&rightrightarrows;",
        expectedTokens = listOf(
            char('⇉')
        )
    )

    @Test
    fun `Bad named entity rightsquigarrow without a semicolon`() = assertTokenization(
        input = "&rightsquigarrow",
        expectedTokens = listOf(
            *chars("&rightsquigarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity rightsquigarrow with a semicolon`() = assertTokenization(
        input = "&rightsquigarrow;",
        expectedTokens = listOf(
            char('↝')
        )
    )

    @Test
    fun `Bad named entity rightthreetimes without a semicolon`() = assertTokenization(
        input = "&rightthreetimes",
        expectedTokens = listOf(
            *chars("&rightthreetimes").toTypedArray()
        )
    )

    @Test
    fun `Named entity rightthreetimes with a semicolon`() = assertTokenization(
        input = "&rightthreetimes;",
        expectedTokens = listOf(
            char('⋌')
        )
    )

    @Test
    fun `Bad named entity ring without a semicolon`() = assertTokenization(
        input = "&ring",
        expectedTokens = listOf(
            *chars("&ring").toTypedArray()
        )
    )

    @Test
    fun `Named entity ring with a semicolon`() = assertTokenization(
        input = "&ring;",
        expectedTokens = listOf(
            char('˚')
        )
    )

    @Test
    fun `Bad named entity risingdotseq without a semicolon`() = assertTokenization(
        input = "&risingdotseq",
        expectedTokens = listOf(
            *chars("&risingdotseq").toTypedArray()
        )
    )

    @Test
    fun `Named entity risingdotseq with a semicolon`() = assertTokenization(
        input = "&risingdotseq;",
        expectedTokens = listOf(
            char('≓')
        )
    )

    @Test
    fun `Bad named entity rlarr without a semicolon`() = assertTokenization(
        input = "&rlarr",
        expectedTokens = listOf(
            *chars("&rlarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity rlarr with a semicolon`() = assertTokenization(
        input = "&rlarr;",
        expectedTokens = listOf(
            char('⇄')
        )
    )

    @Test
    fun `Bad named entity rlhar without a semicolon`() = assertTokenization(
        input = "&rlhar",
        expectedTokens = listOf(
            *chars("&rlhar").toTypedArray()
        )
    )

    @Test
    fun `Named entity rlhar with a semicolon`() = assertTokenization(
        input = "&rlhar;",
        expectedTokens = listOf(
            char('⇌')
        )
    )

    @Test
    fun `Bad named entity rlm without a semicolon`() = assertTokenization(
        input = "&rlm",
        expectedTokens = listOf(
            *chars("&rlm").toTypedArray()
        )
    )

    @Test
    fun `Named entity rlm with a semicolon`() = assertTokenization(
        input = "&rlm;",
        expectedTokens = listOf(
            char('‏')
        )
    )

    @Test
    fun `Bad named entity rmoust without a semicolon`() = assertTokenization(
        input = "&rmoust",
        expectedTokens = listOf(
            *chars("&rmoust").toTypedArray()
        )
    )

    @Test
    fun `Named entity rmoust with a semicolon`() = assertTokenization(
        input = "&rmoust;",
        expectedTokens = listOf(
            char('⎱')
        )
    )

    @Test
    fun `Bad named entity rmoustache without a semicolon`() = assertTokenization(
        input = "&rmoustache",
        expectedTokens = listOf(
            *chars("&rmoustache").toTypedArray()
        )
    )

    @Test
    fun `Named entity rmoustache with a semicolon`() = assertTokenization(
        input = "&rmoustache;",
        expectedTokens = listOf(
            char('⎱')
        )
    )

    @Test
    fun `Bad named entity rnmid without a semicolon`() = assertTokenization(
        input = "&rnmid",
        expectedTokens = listOf(
            *chars("&rnmid").toTypedArray()
        )
    )

    @Test
    fun `Named entity rnmid with a semicolon`() = assertTokenization(
        input = "&rnmid;",
        expectedTokens = listOf(
            char('⫮')
        )
    )

    @Test
    fun `Bad named entity roang without a semicolon`() = assertTokenization(
        input = "&roang",
        expectedTokens = listOf(
            *chars("&roang").toTypedArray()
        )
    )

    @Test
    fun `Named entity roang with a semicolon`() = assertTokenization(
        input = "&roang;",
        expectedTokens = listOf(
            char('⟭')
        )
    )

    @Test
    fun `Bad named entity roarr without a semicolon`() = assertTokenization(
        input = "&roarr",
        expectedTokens = listOf(
            *chars("&roarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity roarr with a semicolon`() = assertTokenization(
        input = "&roarr;",
        expectedTokens = listOf(
            char('⇾')
        )
    )

    @Test
    fun `Bad named entity robrk without a semicolon`() = assertTokenization(
        input = "&robrk",
        expectedTokens = listOf(
            *chars("&robrk").toTypedArray()
        )
    )

    @Test
    fun `Named entity robrk with a semicolon`() = assertTokenization(
        input = "&robrk;",
        expectedTokens = listOf(
            char('⟧')
        )
    )

    @Test
    fun `Bad named entity ropar without a semicolon`() = assertTokenization(
        input = "&ropar",
        expectedTokens = listOf(
            *chars("&ropar").toTypedArray()
        )
    )

    @Test
    fun `Named entity ropar with a semicolon`() = assertTokenization(
        input = "&ropar;",
        expectedTokens = listOf(
            char('⦆')
        )
    )

    @Test
    fun `Bad named entity ropf without a semicolon`() = assertTokenization(
        input = "&ropf",
        expectedTokens = listOf(
            *chars("&ropf").toTypedArray()
        )
    )

    @Test
    fun `Named entity ropf with a semicolon`() = assertTokenization(
        input = "&ropf;",
        expectedTokens = listOf(
            *chars("𝕣").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity roplus without a semicolon`() = assertTokenization(
        input = "&roplus",
        expectedTokens = listOf(
            *chars("&roplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity roplus with a semicolon`() = assertTokenization(
        input = "&roplus;",
        expectedTokens = listOf(
            char('⨮')
        )
    )

    @Test
    fun `Bad named entity rotimes without a semicolon`() = assertTokenization(
        input = "&rotimes",
        expectedTokens = listOf(
            *chars("&rotimes").toTypedArray()
        )
    )

    @Test
    fun `Named entity rotimes with a semicolon`() = assertTokenization(
        input = "&rotimes;",
        expectedTokens = listOf(
            char('⨵')
        )
    )

    @Test
    fun `Bad named entity rpar without a semicolon`() = assertTokenization(
        input = "&rpar",
        expectedTokens = listOf(
            *chars("&rpar").toTypedArray()
        )
    )

    @Test
    fun `Named entity rpar with a semicolon`() = assertTokenization(
        input = "&rpar;",
        expectedTokens = listOf(
            char(')')
        )
    )

    @Test
    fun `Bad named entity rpargt without a semicolon`() = assertTokenization(
        input = "&rpargt",
        expectedTokens = listOf(
            *chars("&rpargt").toTypedArray()
        )
    )

    @Test
    fun `Named entity rpargt with a semicolon`() = assertTokenization(
        input = "&rpargt;",
        expectedTokens = listOf(
            char('⦔')
        )
    )

    @Test
    fun `Bad named entity rppolint without a semicolon`() = assertTokenization(
        input = "&rppolint",
        expectedTokens = listOf(
            *chars("&rppolint").toTypedArray()
        )
    )

    @Test
    fun `Named entity rppolint with a semicolon`() = assertTokenization(
        input = "&rppolint;",
        expectedTokens = listOf(
            char('⨒')
        )
    )

    @Test
    fun `Bad named entity rrarr without a semicolon`() = assertTokenization(
        input = "&rrarr",
        expectedTokens = listOf(
            *chars("&rrarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity rrarr with a semicolon`() = assertTokenization(
        input = "&rrarr;",
        expectedTokens = listOf(
            char('⇉')
        )
    )

    @Test
    fun `Bad named entity rsaquo without a semicolon`() = assertTokenization(
        input = "&rsaquo",
        expectedTokens = listOf(
            *chars("&rsaquo").toTypedArray()
        )
    )

    @Test
    fun `Named entity rsaquo with a semicolon`() = assertTokenization(
        input = "&rsaquo;",
        expectedTokens = listOf(
            char('›')
        )
    )

    @Test
    fun `Bad named entity rscr without a semicolon`() = assertTokenization(
        input = "&rscr",
        expectedTokens = listOf(
            *chars("&rscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity rscr with a semicolon`() = assertTokenization(
        input = "&rscr;",
        expectedTokens = listOf(
            *chars("𝓇").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity rsh without a semicolon`() = assertTokenization(
        input = "&rsh",
        expectedTokens = listOf(
            *chars("&rsh").toTypedArray()
        )
    )

    @Test
    fun `Named entity rsh with a semicolon`() = assertTokenization(
        input = "&rsh;",
        expectedTokens = listOf(
            char('↱')
        )
    )

    @Test
    fun `Bad named entity rsqb without a semicolon`() = assertTokenization(
        input = "&rsqb",
        expectedTokens = listOf(
            *chars("&rsqb").toTypedArray()
        )
    )

    @Test
    fun `Named entity rsqb with a semicolon`() = assertTokenization(
        input = "&rsqb;",
        expectedTokens = listOf(
            char(']')
        )
    )

    @Test
    fun `Bad named entity rsquo without a semicolon`() = assertTokenization(
        input = "&rsquo",
        expectedTokens = listOf(
            *chars("&rsquo").toTypedArray()
        )
    )

    @Test
    fun `Named entity rsquo with a semicolon`() = assertTokenization(
        input = "&rsquo;",
        expectedTokens = listOf(
            char('’')
        )
    )

    @Test
    fun `Bad named entity rsquor without a semicolon`() = assertTokenization(
        input = "&rsquor",
        expectedTokens = listOf(
            *chars("&rsquor").toTypedArray()
        )
    )

    @Test
    fun `Named entity rsquor with a semicolon`() = assertTokenization(
        input = "&rsquor;",
        expectedTokens = listOf(
            char('’')
        )
    )

    @Test
    fun `Bad named entity rthree without a semicolon`() = assertTokenization(
        input = "&rthree",
        expectedTokens = listOf(
            *chars("&rthree").toTypedArray()
        )
    )

    @Test
    fun `Named entity rthree with a semicolon`() = assertTokenization(
        input = "&rthree;",
        expectedTokens = listOf(
            char('⋌')
        )
    )

    @Test
    fun `Bad named entity rtimes without a semicolon`() = assertTokenization(
        input = "&rtimes",
        expectedTokens = listOf(
            *chars("&rtimes").toTypedArray()
        )
    )

    @Test
    fun `Named entity rtimes with a semicolon`() = assertTokenization(
        input = "&rtimes;",
        expectedTokens = listOf(
            char('⋊')
        )
    )

    @Test
    fun `Bad named entity rtri without a semicolon`() = assertTokenization(
        input = "&rtri",
        expectedTokens = listOf(
            *chars("&rtri").toTypedArray()
        )
    )

    @Test
    fun `Named entity rtri with a semicolon`() = assertTokenization(
        input = "&rtri;",
        expectedTokens = listOf(
            char('▹')
        )
    )

    @Test
    fun `Bad named entity rtrie without a semicolon`() = assertTokenization(
        input = "&rtrie",
        expectedTokens = listOf(
            *chars("&rtrie").toTypedArray()
        )
    )

    @Test
    fun `Named entity rtrie with a semicolon`() = assertTokenization(
        input = "&rtrie;",
        expectedTokens = listOf(
            char('⊵')
        )
    )

    @Test
    fun `Bad named entity rtrif without a semicolon`() = assertTokenization(
        input = "&rtrif",
        expectedTokens = listOf(
            *chars("&rtrif").toTypedArray()
        )
    )

    @Test
    fun `Named entity rtrif with a semicolon`() = assertTokenization(
        input = "&rtrif;",
        expectedTokens = listOf(
            char('▸')
        )
    )

    @Test
    fun `Bad named entity rtriltri without a semicolon`() = assertTokenization(
        input = "&rtriltri",
        expectedTokens = listOf(
            *chars("&rtriltri").toTypedArray()
        )
    )

    @Test
    fun `Named entity rtriltri with a semicolon`() = assertTokenization(
        input = "&rtriltri;",
        expectedTokens = listOf(
            char('⧎')
        )
    )

    @Test
    fun `Bad named entity ruluhar without a semicolon`() = assertTokenization(
        input = "&ruluhar",
        expectedTokens = listOf(
            *chars("&ruluhar").toTypedArray()
        )
    )

    @Test
    fun `Named entity ruluhar with a semicolon`() = assertTokenization(
        input = "&ruluhar;",
        expectedTokens = listOf(
            char('⥨')
        )
    )

    @Test
    fun `Bad named entity rx without a semicolon`() = assertTokenization(
        input = "&rx",
        expectedTokens = listOf(
            *chars("&rx").toTypedArray()
        )
    )

    @Test
    fun `Named entity rx with a semicolon`() = assertTokenization(
        input = "&rx;",
        expectedTokens = listOf(
            char('℞')
        )
    )

    @Test
    fun `Bad named entity sacute without a semicolon`() = assertTokenization(
        input = "&sacute",
        expectedTokens = listOf(
            *chars("&sacute").toTypedArray()
        )
    )

    @Test
    fun `Named entity sacute with a semicolon`() = assertTokenization(
        input = "&sacute;",
        expectedTokens = listOf(
            char('ś')
        )
    )

    @Test
    fun `Bad named entity sbquo without a semicolon`() = assertTokenization(
        input = "&sbquo",
        expectedTokens = listOf(
            *chars("&sbquo").toTypedArray()
        )
    )

    @Test
    fun `Named entity sbquo with a semicolon`() = assertTokenization(
        input = "&sbquo;",
        expectedTokens = listOf(
            char('‚')
        )
    )

    @Test
    fun `Bad named entity sc without a semicolon`() = assertTokenization(
        input = "&sc",
        expectedTokens = listOf(
            *chars("&sc").toTypedArray()
        )
    )

    @Test
    fun `Named entity sc with a semicolon`() = assertTokenization(
        input = "&sc;",
        expectedTokens = listOf(
            char('≻')
        )
    )

    @Test
    fun `Bad named entity scE without a semicolon`() = assertTokenization(
        input = "&scE",
        expectedTokens = listOf(
            *chars("&scE").toTypedArray()
        )
    )

    @Test
    fun `Named entity scE with a semicolon`() = assertTokenization(
        input = "&scE;",
        expectedTokens = listOf(
            char('⪴')
        )
    )

    @Test
    fun `Bad named entity scap without a semicolon`() = assertTokenization(
        input = "&scap",
        expectedTokens = listOf(
            *chars("&scap").toTypedArray()
        )
    )

    @Test
    fun `Named entity scap with a semicolon`() = assertTokenization(
        input = "&scap;",
        expectedTokens = listOf(
            char('⪸')
        )
    )

    @Test
    fun `Bad named entity scaron without a semicolon`() = assertTokenization(
        input = "&scaron",
        expectedTokens = listOf(
            *chars("&scaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity scaron with a semicolon`() = assertTokenization(
        input = "&scaron;",
        expectedTokens = listOf(
            char('š')
        )
    )

    @Test
    fun `Bad named entity sccue without a semicolon`() = assertTokenization(
        input = "&sccue",
        expectedTokens = listOf(
            *chars("&sccue").toTypedArray()
        )
    )

    @Test
    fun `Named entity sccue with a semicolon`() = assertTokenization(
        input = "&sccue;",
        expectedTokens = listOf(
            char('≽')
        )
    )

    @Test
    fun `Bad named entity sce without a semicolon`() = assertTokenization(
        input = "&sce",
        expectedTokens = listOf(
            *chars("&sce").toTypedArray()
        )
    )

    @Test
    fun `Named entity sce with a semicolon`() = assertTokenization(
        input = "&sce;",
        expectedTokens = listOf(
            char('⪰')
        )
    )

    @Test
    fun `Bad named entity scedil without a semicolon`() = assertTokenization(
        input = "&scedil",
        expectedTokens = listOf(
            *chars("&scedil").toTypedArray()
        )
    )

    @Test
    fun `Named entity scedil with a semicolon`() = assertTokenization(
        input = "&scedil;",
        expectedTokens = listOf(
            char('ş')
        )
    )

    @Test
    fun `Bad named entity scirc without a semicolon`() = assertTokenization(
        input = "&scirc",
        expectedTokens = listOf(
            *chars("&scirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity scirc with a semicolon`() = assertTokenization(
        input = "&scirc;",
        expectedTokens = listOf(
            char('ŝ')
        )
    )

    @Test
    fun `Bad named entity scnE without a semicolon`() = assertTokenization(
        input = "&scnE",
        expectedTokens = listOf(
            *chars("&scnE").toTypedArray()
        )
    )

    @Test
    fun `Named entity scnE with a semicolon`() = assertTokenization(
        input = "&scnE;",
        expectedTokens = listOf(
            char('⪶')
        )
    )

    @Test
    fun `Bad named entity scnap without a semicolon`() = assertTokenization(
        input = "&scnap",
        expectedTokens = listOf(
            *chars("&scnap").toTypedArray()
        )
    )

    @Test
    fun `Named entity scnap with a semicolon`() = assertTokenization(
        input = "&scnap;",
        expectedTokens = listOf(
            char('⪺')
        )
    )

    @Test
    fun `Bad named entity scnsim without a semicolon`() = assertTokenization(
        input = "&scnsim",
        expectedTokens = listOf(
            *chars("&scnsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity scnsim with a semicolon`() = assertTokenization(
        input = "&scnsim;",
        expectedTokens = listOf(
            char('⋩')
        )
    )

    @Test
    fun `Bad named entity scpolint without a semicolon`() = assertTokenization(
        input = "&scpolint",
        expectedTokens = listOf(
            *chars("&scpolint").toTypedArray()
        )
    )

    @Test
    fun `Named entity scpolint with a semicolon`() = assertTokenization(
        input = "&scpolint;",
        expectedTokens = listOf(
            char('⨓')
        )
    )

    @Test
    fun `Bad named entity scsim without a semicolon`() = assertTokenization(
        input = "&scsim",
        expectedTokens = listOf(
            *chars("&scsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity scsim with a semicolon`() = assertTokenization(
        input = "&scsim;",
        expectedTokens = listOf(
            char('≿')
        )
    )

    @Test
    fun `Bad named entity scy without a semicolon`() = assertTokenization(
        input = "&scy",
        expectedTokens = listOf(
            *chars("&scy").toTypedArray()
        )
    )

    @Test
    fun `Named entity scy with a semicolon`() = assertTokenization(
        input = "&scy;",
        expectedTokens = listOf(
            char('с')
        )
    )

    @Test
    fun `Bad named entity sdot without a semicolon`() = assertTokenization(
        input = "&sdot",
        expectedTokens = listOf(
            *chars("&sdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity sdot with a semicolon`() = assertTokenization(
        input = "&sdot;",
        expectedTokens = listOf(
            char('⋅')
        )
    )

    @Test
    fun `Bad named entity sdotb without a semicolon`() = assertTokenization(
        input = "&sdotb",
        expectedTokens = listOf(
            *chars("&sdotb").toTypedArray()
        )
    )

    @Test
    fun `Named entity sdotb with a semicolon`() = assertTokenization(
        input = "&sdotb;",
        expectedTokens = listOf(
            char('⊡')
        )
    )

    @Test
    fun `Bad named entity sdote without a semicolon`() = assertTokenization(
        input = "&sdote",
        expectedTokens = listOf(
            *chars("&sdote").toTypedArray()
        )
    )

    @Test
    fun `Named entity sdote with a semicolon`() = assertTokenization(
        input = "&sdote;",
        expectedTokens = listOf(
            char('⩦')
        )
    )

    @Test
    fun `Bad named entity seArr without a semicolon`() = assertTokenization(
        input = "&seArr",
        expectedTokens = listOf(
            *chars("&seArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity seArr with a semicolon`() = assertTokenization(
        input = "&seArr;",
        expectedTokens = listOf(
            char('⇘')
        )
    )

    @Test
    fun `Bad named entity searhk without a semicolon`() = assertTokenization(
        input = "&searhk",
        expectedTokens = listOf(
            *chars("&searhk").toTypedArray()
        )
    )

    @Test
    fun `Named entity searhk with a semicolon`() = assertTokenization(
        input = "&searhk;",
        expectedTokens = listOf(
            char('⤥')
        )
    )

    @Test
    fun `Bad named entity searr without a semicolon`() = assertTokenization(
        input = "&searr",
        expectedTokens = listOf(
            *chars("&searr").toTypedArray()
        )
    )

    @Test
    fun `Named entity searr with a semicolon`() = assertTokenization(
        input = "&searr;",
        expectedTokens = listOf(
            char('↘')
        )
    )

    @Test
    fun `Bad named entity searrow without a semicolon`() = assertTokenization(
        input = "&searrow",
        expectedTokens = listOf(
            *chars("&searrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity searrow with a semicolon`() = assertTokenization(
        input = "&searrow;",
        expectedTokens = listOf(
            char('↘')
        )
    )

    @Test
    fun `Named entity sect without a semicolon`() = assertTokenization(
        input = "&sect",
        expectedTokens = listOf(
            char('§')
        )
    )

    @Test
    fun `Named entity sect with a semicolon`() = assertTokenization(
        input = "&sect;",
        expectedTokens = listOf(
            char('§')
        )
    )

    @Test
    fun `Bad named entity semi without a semicolon`() = assertTokenization(
        input = "&semi",
        expectedTokens = listOf(
            *chars("&semi").toTypedArray()
        )
    )

    @Test
    fun `Named entity semi with a semicolon`() = assertTokenization(
        input = "&semi;",
        expectedTokens = listOf(
            char(';')
        )
    )

    @Test
    fun `Bad named entity seswar without a semicolon`() = assertTokenization(
        input = "&seswar",
        expectedTokens = listOf(
            *chars("&seswar").toTypedArray()
        )
    )

    @Test
    fun `Named entity seswar with a semicolon`() = assertTokenization(
        input = "&seswar;",
        expectedTokens = listOf(
            char('⤩')
        )
    )

    @Test
    fun `Bad named entity setminus without a semicolon`() = assertTokenization(
        input = "&setminus",
        expectedTokens = listOf(
            *chars("&setminus").toTypedArray()
        )
    )

    @Test
    fun `Named entity setminus with a semicolon`() = assertTokenization(
        input = "&setminus;",
        expectedTokens = listOf(
            char('∖')
        )
    )

    @Test
    fun `Bad named entity setmn without a semicolon`() = assertTokenization(
        input = "&setmn",
        expectedTokens = listOf(
            *chars("&setmn").toTypedArray()
        )
    )

    @Test
    fun `Named entity setmn with a semicolon`() = assertTokenization(
        input = "&setmn;",
        expectedTokens = listOf(
            char('∖')
        )
    )

    @Test
    fun `Bad named entity sext without a semicolon`() = assertTokenization(
        input = "&sext",
        expectedTokens = listOf(
            *chars("&sext").toTypedArray()
        )
    )

    @Test
    fun `Named entity sext with a semicolon`() = assertTokenization(
        input = "&sext;",
        expectedTokens = listOf(
            char('✶')
        )
    )

    @Test
    fun `Bad named entity sfr without a semicolon`() = assertTokenization(
        input = "&sfr",
        expectedTokens = listOf(
            *chars("&sfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity sfr with a semicolon`() = assertTokenization(
        input = "&sfr;",
        expectedTokens = listOf(
            *chars("𝔰").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity sfrown without a semicolon`() = assertTokenization(
        input = "&sfrown",
        expectedTokens = listOf(
            *chars("&sfrown").toTypedArray()
        )
    )

    @Test
    fun `Named entity sfrown with a semicolon`() = assertTokenization(
        input = "&sfrown;",
        expectedTokens = listOf(
            char('⌢')
        )
    )

    @Test
    fun `Bad named entity sharp without a semicolon`() = assertTokenization(
        input = "&sharp",
        expectedTokens = listOf(
            *chars("&sharp").toTypedArray()
        )
    )

    @Test
    fun `Named entity sharp with a semicolon`() = assertTokenization(
        input = "&sharp;",
        expectedTokens = listOf(
            char('♯')
        )
    )

    @Test
    fun `Bad named entity shchcy without a semicolon`() = assertTokenization(
        input = "&shchcy",
        expectedTokens = listOf(
            *chars("&shchcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity shchcy with a semicolon`() = assertTokenization(
        input = "&shchcy;",
        expectedTokens = listOf(
            char('щ')
        )
    )

    @Test
    fun `Bad named entity shcy without a semicolon`() = assertTokenization(
        input = "&shcy",
        expectedTokens = listOf(
            *chars("&shcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity shcy with a semicolon`() = assertTokenization(
        input = "&shcy;",
        expectedTokens = listOf(
            char('ш')
        )
    )

    @Test
    fun `Bad named entity shortmid without a semicolon`() = assertTokenization(
        input = "&shortmid",
        expectedTokens = listOf(
            *chars("&shortmid").toTypedArray()
        )
    )

    @Test
    fun `Named entity shortmid with a semicolon`() = assertTokenization(
        input = "&shortmid;",
        expectedTokens = listOf(
            char('∣')
        )
    )

    @Test
    fun `Bad named entity shortparallel without a semicolon`() = assertTokenization(
        input = "&shortparallel",
        expectedTokens = listOf(
            *chars("&shortparallel").toTypedArray()
        )
    )

    @Test
    fun `Named entity shortparallel with a semicolon`() = assertTokenization(
        input = "&shortparallel;",
        expectedTokens = listOf(
            char('∥')
        )
    )

    @Test
    fun `Named entity shy without a semicolon`() = assertTokenization(
        input = "&shy",
        expectedTokens = listOf(
            char('­')
        )
    )

    @Test
    fun `Named entity shy with a semicolon`() = assertTokenization(
        input = "&shy;",
        expectedTokens = listOf(
            char('­')
        )
    )

    @Test
    fun `Bad named entity sigma without a semicolon`() = assertTokenization(
        input = "&sigma",
        expectedTokens = listOf(
            *chars("&sigma").toTypedArray()
        )
    )

    @Test
    fun `Named entity sigma with a semicolon`() = assertTokenization(
        input = "&sigma;",
        expectedTokens = listOf(
            char('σ')
        )
    )

    @Test
    fun `Bad named entity sigmaf without a semicolon`() = assertTokenization(
        input = "&sigmaf",
        expectedTokens = listOf(
            *chars("&sigmaf").toTypedArray()
        )
    )

    @Test
    fun `Named entity sigmaf with a semicolon`() = assertTokenization(
        input = "&sigmaf;",
        expectedTokens = listOf(
            char('ς')
        )
    )

    @Test
    fun `Bad named entity sigmav without a semicolon`() = assertTokenization(
        input = "&sigmav",
        expectedTokens = listOf(
            *chars("&sigmav").toTypedArray()
        )
    )

    @Test
    fun `Named entity sigmav with a semicolon`() = assertTokenization(
        input = "&sigmav;",
        expectedTokens = listOf(
            char('ς')
        )
    )

    @Test
    fun `Bad named entity sim without a semicolon`() = assertTokenization(
        input = "&sim",
        expectedTokens = listOf(
            *chars("&sim").toTypedArray()
        )
    )

    @Test
    fun `Named entity sim with a semicolon`() = assertTokenization(
        input = "&sim;",
        expectedTokens = listOf(
            char('∼')
        )
    )

    @Test
    fun `Bad named entity simdot without a semicolon`() = assertTokenization(
        input = "&simdot",
        expectedTokens = listOf(
            *chars("&simdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity simdot with a semicolon`() = assertTokenization(
        input = "&simdot;",
        expectedTokens = listOf(
            char('⩪')
        )
    )

    @Test
    fun `Bad named entity sime without a semicolon`() = assertTokenization(
        input = "&sime",
        expectedTokens = listOf(
            *chars("&sime").toTypedArray()
        )
    )

    @Test
    fun `Named entity sime with a semicolon`() = assertTokenization(
        input = "&sime;",
        expectedTokens = listOf(
            char('≃')
        )
    )

    @Test
    fun `Bad named entity simeq without a semicolon`() = assertTokenization(
        input = "&simeq",
        expectedTokens = listOf(
            *chars("&simeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity simeq with a semicolon`() = assertTokenization(
        input = "&simeq;",
        expectedTokens = listOf(
            char('≃')
        )
    )

    @Test
    fun `Bad named entity simg without a semicolon`() = assertTokenization(
        input = "&simg",
        expectedTokens = listOf(
            *chars("&simg").toTypedArray()
        )
    )

    @Test
    fun `Named entity simg with a semicolon`() = assertTokenization(
        input = "&simg;",
        expectedTokens = listOf(
            char('⪞')
        )
    )

    @Test
    fun `Bad named entity simgE without a semicolon`() = assertTokenization(
        input = "&simgE",
        expectedTokens = listOf(
            *chars("&simgE").toTypedArray()
        )
    )

    @Test
    fun `Named entity simgE with a semicolon`() = assertTokenization(
        input = "&simgE;",
        expectedTokens = listOf(
            char('⪠')
        )
    )

    @Test
    fun `Bad named entity siml without a semicolon`() = assertTokenization(
        input = "&siml",
        expectedTokens = listOf(
            *chars("&siml").toTypedArray()
        )
    )

    @Test
    fun `Named entity siml with a semicolon`() = assertTokenization(
        input = "&siml;",
        expectedTokens = listOf(
            char('⪝')
        )
    )

    @Test
    fun `Bad named entity simlE without a semicolon`() = assertTokenization(
        input = "&simlE",
        expectedTokens = listOf(
            *chars("&simlE").toTypedArray()
        )
    )

    @Test
    fun `Named entity simlE with a semicolon`() = assertTokenization(
        input = "&simlE;",
        expectedTokens = listOf(
            char('⪟')
        )
    )

    @Test
    fun `Bad named entity simne without a semicolon`() = assertTokenization(
        input = "&simne",
        expectedTokens = listOf(
            *chars("&simne").toTypedArray()
        )
    )

    @Test
    fun `Named entity simne with a semicolon`() = assertTokenization(
        input = "&simne;",
        expectedTokens = listOf(
            char('≆')
        )
    )

    @Test
    fun `Bad named entity simplus without a semicolon`() = assertTokenization(
        input = "&simplus",
        expectedTokens = listOf(
            *chars("&simplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity simplus with a semicolon`() = assertTokenization(
        input = "&simplus;",
        expectedTokens = listOf(
            char('⨤')
        )
    )

    @Test
    fun `Bad named entity simrarr without a semicolon`() = assertTokenization(
        input = "&simrarr",
        expectedTokens = listOf(
            *chars("&simrarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity simrarr with a semicolon`() = assertTokenization(
        input = "&simrarr;",
        expectedTokens = listOf(
            char('⥲')
        )
    )

    @Test
    fun `Bad named entity slarr without a semicolon`() = assertTokenization(
        input = "&slarr",
        expectedTokens = listOf(
            *chars("&slarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity slarr with a semicolon`() = assertTokenization(
        input = "&slarr;",
        expectedTokens = listOf(
            char('←')
        )
    )

    @Test
    fun `Bad named entity smallsetminus without a semicolon`() = assertTokenization(
        input = "&smallsetminus",
        expectedTokens = listOf(
            *chars("&smallsetminus").toTypedArray()
        )
    )

    @Test
    fun `Named entity smallsetminus with a semicolon`() = assertTokenization(
        input = "&smallsetminus;",
        expectedTokens = listOf(
            char('∖')
        )
    )

    @Test
    fun `Bad named entity smashp without a semicolon`() = assertTokenization(
        input = "&smashp",
        expectedTokens = listOf(
            *chars("&smashp").toTypedArray()
        )
    )

    @Test
    fun `Named entity smashp with a semicolon`() = assertTokenization(
        input = "&smashp;",
        expectedTokens = listOf(
            char('⨳')
        )
    )

    @Test
    fun `Bad named entity smeparsl without a semicolon`() = assertTokenization(
        input = "&smeparsl",
        expectedTokens = listOf(
            *chars("&smeparsl").toTypedArray()
        )
    )

    @Test
    fun `Named entity smeparsl with a semicolon`() = assertTokenization(
        input = "&smeparsl;",
        expectedTokens = listOf(
            char('⧤')
        )
    )

    @Test
    fun `Bad named entity smid without a semicolon`() = assertTokenization(
        input = "&smid",
        expectedTokens = listOf(
            *chars("&smid").toTypedArray()
        )
    )

    @Test
    fun `Named entity smid with a semicolon`() = assertTokenization(
        input = "&smid;",
        expectedTokens = listOf(
            char('∣')
        )
    )

    @Test
    fun `Bad named entity smile without a semicolon`() = assertTokenization(
        input = "&smile",
        expectedTokens = listOf(
            *chars("&smile").toTypedArray()
        )
    )

    @Test
    fun `Named entity smile with a semicolon`() = assertTokenization(
        input = "&smile;",
        expectedTokens = listOf(
            char('⌣')
        )
    )

    @Test
    fun `Bad named entity smt without a semicolon`() = assertTokenization(
        input = "&smt",
        expectedTokens = listOf(
            *chars("&smt").toTypedArray()
        )
    )

    @Test
    fun `Named entity smt with a semicolon`() = assertTokenization(
        input = "&smt;",
        expectedTokens = listOf(
            char('⪪')
        )
    )

    @Test
    fun `Bad named entity smte without a semicolon`() = assertTokenization(
        input = "&smte",
        expectedTokens = listOf(
            *chars("&smte").toTypedArray()
        )
    )

    @Test
    fun `Named entity smte with a semicolon`() = assertTokenization(
        input = "&smte;",
        expectedTokens = listOf(
            char('⪬')
        )
    )

    @Test
    fun `Bad named entity smtes without a semicolon`() = assertTokenization(
        input = "&smtes",
        expectedTokens = listOf(
            *chars("&smtes").toTypedArray()
        )
    )

    @Test
    fun `Named entity smtes with a semicolon`() = assertTokenization(
        input = "&smtes;",
        expectedTokens = listOf(
            *chars("⪬︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity softcy without a semicolon`() = assertTokenization(
        input = "&softcy",
        expectedTokens = listOf(
            *chars("&softcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity softcy with a semicolon`() = assertTokenization(
        input = "&softcy;",
        expectedTokens = listOf(
            char('ь')
        )
    )

    @Test
    fun `Bad named entity sol without a semicolon`() = assertTokenization(
        input = "&sol",
        expectedTokens = listOf(
            *chars("&sol").toTypedArray()
        )
    )

    @Test
    fun `Named entity sol with a semicolon`() = assertTokenization(
        input = "&sol;",
        expectedTokens = listOf(
            char('/')
        )
    )

    @Test
    fun `Bad named entity solb without a semicolon`() = assertTokenization(
        input = "&solb",
        expectedTokens = listOf(
            *chars("&solb").toTypedArray()
        )
    )

    @Test
    fun `Named entity solb with a semicolon`() = assertTokenization(
        input = "&solb;",
        expectedTokens = listOf(
            char('⧄')
        )
    )

    @Test
    fun `Bad named entity solbar without a semicolon`() = assertTokenization(
        input = "&solbar",
        expectedTokens = listOf(
            *chars("&solbar").toTypedArray()
        )
    )

    @Test
    fun `Named entity solbar with a semicolon`() = assertTokenization(
        input = "&solbar;",
        expectedTokens = listOf(
            char('⌿')
        )
    )

    @Test
    fun `Bad named entity sopf without a semicolon`() = assertTokenization(
        input = "&sopf",
        expectedTokens = listOf(
            *chars("&sopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity sopf with a semicolon`() = assertTokenization(
        input = "&sopf;",
        expectedTokens = listOf(
            *chars("𝕤").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity spades without a semicolon`() = assertTokenization(
        input = "&spades",
        expectedTokens = listOf(
            *chars("&spades").toTypedArray()
        )
    )

    @Test
    fun `Named entity spades with a semicolon`() = assertTokenization(
        input = "&spades;",
        expectedTokens = listOf(
            char('♠')
        )
    )

    @Test
    fun `Bad named entity spadesuit without a semicolon`() = assertTokenization(
        input = "&spadesuit",
        expectedTokens = listOf(
            *chars("&spadesuit").toTypedArray()
        )
    )

    @Test
    fun `Named entity spadesuit with a semicolon`() = assertTokenization(
        input = "&spadesuit;",
        expectedTokens = listOf(
            char('♠')
        )
    )

    @Test
    fun `Bad named entity spar without a semicolon`() = assertTokenization(
        input = "&spar",
        expectedTokens = listOf(
            *chars("&spar").toTypedArray()
        )
    )

    @Test
    fun `Named entity spar with a semicolon`() = assertTokenization(
        input = "&spar;",
        expectedTokens = listOf(
            char('∥')
        )
    )

    @Test
    fun `Bad named entity sqcap without a semicolon`() = assertTokenization(
        input = "&sqcap",
        expectedTokens = listOf(
            *chars("&sqcap").toTypedArray()
        )
    )

    @Test
    fun `Named entity sqcap with a semicolon`() = assertTokenization(
        input = "&sqcap;",
        expectedTokens = listOf(
            char('⊓')
        )
    )

    @Test
    fun `Bad named entity sqcaps without a semicolon`() = assertTokenization(
        input = "&sqcaps",
        expectedTokens = listOf(
            *chars("&sqcaps").toTypedArray()
        )
    )

    @Test
    fun `Named entity sqcaps with a semicolon`() = assertTokenization(
        input = "&sqcaps;",
        expectedTokens = listOf(
            *chars("⊓︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity sqcup without a semicolon`() = assertTokenization(
        input = "&sqcup",
        expectedTokens = listOf(
            *chars("&sqcup").toTypedArray()
        )
    )

    @Test
    fun `Named entity sqcup with a semicolon`() = assertTokenization(
        input = "&sqcup;",
        expectedTokens = listOf(
            char('⊔')
        )
    )

    @Test
    fun `Bad named entity sqcups without a semicolon`() = assertTokenization(
        input = "&sqcups",
        expectedTokens = listOf(
            *chars("&sqcups").toTypedArray()
        )
    )

    @Test
    fun `Named entity sqcups with a semicolon`() = assertTokenization(
        input = "&sqcups;",
        expectedTokens = listOf(
            *chars("⊔︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity sqsub without a semicolon`() = assertTokenization(
        input = "&sqsub",
        expectedTokens = listOf(
            *chars("&sqsub").toTypedArray()
        )
    )

    @Test
    fun `Named entity sqsub with a semicolon`() = assertTokenization(
        input = "&sqsub;",
        expectedTokens = listOf(
            char('⊏')
        )
    )

    @Test
    fun `Bad named entity sqsube without a semicolon`() = assertTokenization(
        input = "&sqsube",
        expectedTokens = listOf(
            *chars("&sqsube").toTypedArray()
        )
    )

    @Test
    fun `Named entity sqsube with a semicolon`() = assertTokenization(
        input = "&sqsube;",
        expectedTokens = listOf(
            char('⊑')
        )
    )

    @Test
    fun `Bad named entity sqsubset without a semicolon`() = assertTokenization(
        input = "&sqsubset",
        expectedTokens = listOf(
            *chars("&sqsubset").toTypedArray()
        )
    )

    @Test
    fun `Named entity sqsubset with a semicolon`() = assertTokenization(
        input = "&sqsubset;",
        expectedTokens = listOf(
            char('⊏')
        )
    )

    @Test
    fun `Bad named entity sqsubseteq without a semicolon`() = assertTokenization(
        input = "&sqsubseteq",
        expectedTokens = listOf(
            *chars("&sqsubseteq").toTypedArray()
        )
    )

    @Test
    fun `Named entity sqsubseteq with a semicolon`() = assertTokenization(
        input = "&sqsubseteq;",
        expectedTokens = listOf(
            char('⊑')
        )
    )

    @Test
    fun `Bad named entity sqsup without a semicolon`() = assertTokenization(
        input = "&sqsup",
        expectedTokens = listOf(
            *chars("&sqsup").toTypedArray()
        )
    )

    @Test
    fun `Named entity sqsup with a semicolon`() = assertTokenization(
        input = "&sqsup;",
        expectedTokens = listOf(
            char('⊐')
        )
    )

    @Test
    fun `Bad named entity sqsupe without a semicolon`() = assertTokenization(
        input = "&sqsupe",
        expectedTokens = listOf(
            *chars("&sqsupe").toTypedArray()
        )
    )

    @Test
    fun `Named entity sqsupe with a semicolon`() = assertTokenization(
        input = "&sqsupe;",
        expectedTokens = listOf(
            char('⊒')
        )
    )

    @Test
    fun `Bad named entity sqsupset without a semicolon`() = assertTokenization(
        input = "&sqsupset",
        expectedTokens = listOf(
            *chars("&sqsupset").toTypedArray()
        )
    )

    @Test
    fun `Named entity sqsupset with a semicolon`() = assertTokenization(
        input = "&sqsupset;",
        expectedTokens = listOf(
            char('⊐')
        )
    )

    @Test
    fun `Bad named entity sqsupseteq without a semicolon`() = assertTokenization(
        input = "&sqsupseteq",
        expectedTokens = listOf(
            *chars("&sqsupseteq").toTypedArray()
        )
    )

    @Test
    fun `Named entity sqsupseteq with a semicolon`() = assertTokenization(
        input = "&sqsupseteq;",
        expectedTokens = listOf(
            char('⊒')
        )
    )

    @Test
    fun `Bad named entity squ without a semicolon`() = assertTokenization(
        input = "&squ",
        expectedTokens = listOf(
            *chars("&squ").toTypedArray()
        )
    )

    @Test
    fun `Named entity squ with a semicolon`() = assertTokenization(
        input = "&squ;",
        expectedTokens = listOf(
            char('□')
        )
    )

    @Test
    fun `Bad named entity square without a semicolon`() = assertTokenization(
        input = "&square",
        expectedTokens = listOf(
            *chars("&square").toTypedArray()
        )
    )

    @Test
    fun `Named entity square with a semicolon`() = assertTokenization(
        input = "&square;",
        expectedTokens = listOf(
            char('□')
        )
    )

    @Test
    fun `Bad named entity squarf without a semicolon`() = assertTokenization(
        input = "&squarf",
        expectedTokens = listOf(
            *chars("&squarf").toTypedArray()
        )
    )

    @Test
    fun `Named entity squarf with a semicolon`() = assertTokenization(
        input = "&squarf;",
        expectedTokens = listOf(
            char('▪')
        )
    )

    @Test
    fun `Bad named entity squf without a semicolon`() = assertTokenization(
        input = "&squf",
        expectedTokens = listOf(
            *chars("&squf").toTypedArray()
        )
    )

    @Test
    fun `Named entity squf with a semicolon`() = assertTokenization(
        input = "&squf;",
        expectedTokens = listOf(
            char('▪')
        )
    )

    @Test
    fun `Bad named entity srarr without a semicolon`() = assertTokenization(
        input = "&srarr",
        expectedTokens = listOf(
            *chars("&srarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity srarr with a semicolon`() = assertTokenization(
        input = "&srarr;",
        expectedTokens = listOf(
            char('→')
        )
    )

    @Test
    fun `Bad named entity sscr without a semicolon`() = assertTokenization(
        input = "&sscr",
        expectedTokens = listOf(
            *chars("&sscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity sscr with a semicolon`() = assertTokenization(
        input = "&sscr;",
        expectedTokens = listOf(
            *chars("𝓈").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity ssetmn without a semicolon`() = assertTokenization(
        input = "&ssetmn",
        expectedTokens = listOf(
            *chars("&ssetmn").toTypedArray()
        )
    )

    @Test
    fun `Named entity ssetmn with a semicolon`() = assertTokenization(
        input = "&ssetmn;",
        expectedTokens = listOf(
            char('∖')
        )
    )

    @Test
    fun `Bad named entity ssmile without a semicolon`() = assertTokenization(
        input = "&ssmile",
        expectedTokens = listOf(
            *chars("&ssmile").toTypedArray()
        )
    )

    @Test
    fun `Named entity ssmile with a semicolon`() = assertTokenization(
        input = "&ssmile;",
        expectedTokens = listOf(
            char('⌣')
        )
    )

    @Test
    fun `Bad named entity sstarf without a semicolon`() = assertTokenization(
        input = "&sstarf",
        expectedTokens = listOf(
            *chars("&sstarf").toTypedArray()
        )
    )

    @Test
    fun `Named entity sstarf with a semicolon`() = assertTokenization(
        input = "&sstarf;",
        expectedTokens = listOf(
            char('⋆')
        )
    )

    @Test
    fun `Bad named entity star without a semicolon`() = assertTokenization(
        input = "&star",
        expectedTokens = listOf(
            *chars("&star").toTypedArray()
        )
    )

    @Test
    fun `Named entity star with a semicolon`() = assertTokenization(
        input = "&star;",
        expectedTokens = listOf(
            char('☆')
        )
    )

    @Test
    fun `Bad named entity starf without a semicolon`() = assertTokenization(
        input = "&starf",
        expectedTokens = listOf(
            *chars("&starf").toTypedArray()
        )
    )

    @Test
    fun `Named entity starf with a semicolon`() = assertTokenization(
        input = "&starf;",
        expectedTokens = listOf(
            char('★')
        )
    )

    @Test
    fun `Bad named entity straightepsilon without a semicolon`() = assertTokenization(
        input = "&straightepsilon",
        expectedTokens = listOf(
            *chars("&straightepsilon").toTypedArray()
        )
    )

    @Test
    fun `Named entity straightepsilon with a semicolon`() = assertTokenization(
        input = "&straightepsilon;",
        expectedTokens = listOf(
            char('ϵ')
        )
    )

    @Test
    fun `Bad named entity straightphi without a semicolon`() = assertTokenization(
        input = "&straightphi",
        expectedTokens = listOf(
            *chars("&straightphi").toTypedArray()
        )
    )

    @Test
    fun `Named entity straightphi with a semicolon`() = assertTokenization(
        input = "&straightphi;",
        expectedTokens = listOf(
            char('ϕ')
        )
    )

    @Test
    fun `Bad named entity strns without a semicolon`() = assertTokenization(
        input = "&strns",
        expectedTokens = listOf(
            *chars("&strns").toTypedArray()
        )
    )

    @Test
    fun `Named entity strns with a semicolon`() = assertTokenization(
        input = "&strns;",
        expectedTokens = listOf(
            char('¯')
        )
    )

    @Test
    fun `Bad named entity sub without a semicolon`() = assertTokenization(
        input = "&sub",
        expectedTokens = listOf(
            *chars("&sub").toTypedArray()
        )
    )

    @Test
    fun `Named entity sub with a semicolon`() = assertTokenization(
        input = "&sub;",
        expectedTokens = listOf(
            char('⊂')
        )
    )

    @Test
    fun `Bad named entity subE without a semicolon`() = assertTokenization(
        input = "&subE",
        expectedTokens = listOf(
            *chars("&subE").toTypedArray()
        )
    )

    @Test
    fun `Named entity subE with a semicolon`() = assertTokenization(
        input = "&subE;",
        expectedTokens = listOf(
            char('⫅')
        )
    )

    @Test
    fun `Bad named entity subdot without a semicolon`() = assertTokenization(
        input = "&subdot",
        expectedTokens = listOf(
            *chars("&subdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity subdot with a semicolon`() = assertTokenization(
        input = "&subdot;",
        expectedTokens = listOf(
            char('⪽')
        )
    )

    @Test
    fun `Bad named entity sube without a semicolon`() = assertTokenization(
        input = "&sube",
        expectedTokens = listOf(
            *chars("&sube").toTypedArray()
        )
    )

    @Test
    fun `Named entity sube with a semicolon`() = assertTokenization(
        input = "&sube;",
        expectedTokens = listOf(
            char('⊆')
        )
    )

    @Test
    fun `Bad named entity subedot without a semicolon`() = assertTokenization(
        input = "&subedot",
        expectedTokens = listOf(
            *chars("&subedot").toTypedArray()
        )
    )

    @Test
    fun `Named entity subedot with a semicolon`() = assertTokenization(
        input = "&subedot;",
        expectedTokens = listOf(
            char('⫃')
        )
    )

    @Test
    fun `Bad named entity submult without a semicolon`() = assertTokenization(
        input = "&submult",
        expectedTokens = listOf(
            *chars("&submult").toTypedArray()
        )
    )

    @Test
    fun `Named entity submult with a semicolon`() = assertTokenization(
        input = "&submult;",
        expectedTokens = listOf(
            char('⫁')
        )
    )

    @Test
    fun `Bad named entity subnE without a semicolon`() = assertTokenization(
        input = "&subnE",
        expectedTokens = listOf(
            *chars("&subnE").toTypedArray()
        )
    )

    @Test
    fun `Named entity subnE with a semicolon`() = assertTokenization(
        input = "&subnE;",
        expectedTokens = listOf(
            char('⫋')
        )
    )

    @Test
    fun `Bad named entity subne without a semicolon`() = assertTokenization(
        input = "&subne",
        expectedTokens = listOf(
            *chars("&subne").toTypedArray()
        )
    )

    @Test
    fun `Named entity subne with a semicolon`() = assertTokenization(
        input = "&subne;",
        expectedTokens = listOf(
            char('⊊')
        )
    )

    @Test
    fun `Bad named entity subplus without a semicolon`() = assertTokenization(
        input = "&subplus",
        expectedTokens = listOf(
            *chars("&subplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity subplus with a semicolon`() = assertTokenization(
        input = "&subplus;",
        expectedTokens = listOf(
            char('⪿')
        )
    )

    @Test
    fun `Bad named entity subrarr without a semicolon`() = assertTokenization(
        input = "&subrarr",
        expectedTokens = listOf(
            *chars("&subrarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity subrarr with a semicolon`() = assertTokenization(
        input = "&subrarr;",
        expectedTokens = listOf(
            char('⥹')
        )
    )

    @Test
    fun `Bad named entity subset without a semicolon`() = assertTokenization(
        input = "&subset",
        expectedTokens = listOf(
            *chars("&subset").toTypedArray()
        )
    )

    @Test
    fun `Named entity subset with a semicolon`() = assertTokenization(
        input = "&subset;",
        expectedTokens = listOf(
            char('⊂')
        )
    )

    @Test
    fun `Bad named entity subseteq without a semicolon`() = assertTokenization(
        input = "&subseteq",
        expectedTokens = listOf(
            *chars("&subseteq").toTypedArray()
        )
    )

    @Test
    fun `Named entity subseteq with a semicolon`() = assertTokenization(
        input = "&subseteq;",
        expectedTokens = listOf(
            char('⊆')
        )
    )

    @Test
    fun `Bad named entity subseteqq without a semicolon`() = assertTokenization(
        input = "&subseteqq",
        expectedTokens = listOf(
            *chars("&subseteqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity subseteqq with a semicolon`() = assertTokenization(
        input = "&subseteqq;",
        expectedTokens = listOf(
            char('⫅')
        )
    )

    @Test
    fun `Bad named entity subsetneq without a semicolon`() = assertTokenization(
        input = "&subsetneq",
        expectedTokens = listOf(
            *chars("&subsetneq").toTypedArray()
        )
    )

    @Test
    fun `Named entity subsetneq with a semicolon`() = assertTokenization(
        input = "&subsetneq;",
        expectedTokens = listOf(
            char('⊊')
        )
    )

    @Test
    fun `Bad named entity subsetneqq without a semicolon`() = assertTokenization(
        input = "&subsetneqq",
        expectedTokens = listOf(
            *chars("&subsetneqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity subsetneqq with a semicolon`() = assertTokenization(
        input = "&subsetneqq;",
        expectedTokens = listOf(
            char('⫋')
        )
    )

    @Test
    fun `Bad named entity subsim without a semicolon`() = assertTokenization(
        input = "&subsim",
        expectedTokens = listOf(
            *chars("&subsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity subsim with a semicolon`() = assertTokenization(
        input = "&subsim;",
        expectedTokens = listOf(
            char('⫇')
        )
    )

    @Test
    fun `Bad named entity subsub without a semicolon`() = assertTokenization(
        input = "&subsub",
        expectedTokens = listOf(
            *chars("&subsub").toTypedArray()
        )
    )

    @Test
    fun `Named entity subsub with a semicolon`() = assertTokenization(
        input = "&subsub;",
        expectedTokens = listOf(
            char('⫕')
        )
    )

    @Test
    fun `Bad named entity subsup without a semicolon`() = assertTokenization(
        input = "&subsup",
        expectedTokens = listOf(
            *chars("&subsup").toTypedArray()
        )
    )

    @Test
    fun `Named entity subsup with a semicolon`() = assertTokenization(
        input = "&subsup;",
        expectedTokens = listOf(
            char('⫓')
        )
    )

    @Test
    fun `Bad named entity succ without a semicolon`() = assertTokenization(
        input = "&succ",
        expectedTokens = listOf(
            *chars("&succ").toTypedArray()
        )
    )

    @Test
    fun `Named entity succ with a semicolon`() = assertTokenization(
        input = "&succ;",
        expectedTokens = listOf(
            char('≻')
        )
    )

    @Test
    fun `Bad named entity succapprox without a semicolon`() = assertTokenization(
        input = "&succapprox",
        expectedTokens = listOf(
            *chars("&succapprox").toTypedArray()
        )
    )

    @Test
    fun `Named entity succapprox with a semicolon`() = assertTokenization(
        input = "&succapprox;",
        expectedTokens = listOf(
            char('⪸')
        )
    )

    @Test
    fun `Bad named entity succcurlyeq without a semicolon`() = assertTokenization(
        input = "&succcurlyeq",
        expectedTokens = listOf(
            *chars("&succcurlyeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity succcurlyeq with a semicolon`() = assertTokenization(
        input = "&succcurlyeq;",
        expectedTokens = listOf(
            char('≽')
        )
    )

    @Test
    fun `Bad named entity succeq without a semicolon`() = assertTokenization(
        input = "&succeq",
        expectedTokens = listOf(
            *chars("&succeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity succeq with a semicolon`() = assertTokenization(
        input = "&succeq;",
        expectedTokens = listOf(
            char('⪰')
        )
    )

    @Test
    fun `Bad named entity succnapprox without a semicolon`() = assertTokenization(
        input = "&succnapprox",
        expectedTokens = listOf(
            *chars("&succnapprox").toTypedArray()
        )
    )

    @Test
    fun `Named entity succnapprox with a semicolon`() = assertTokenization(
        input = "&succnapprox;",
        expectedTokens = listOf(
            char('⪺')
        )
    )

    @Test
    fun `Bad named entity succneqq without a semicolon`() = assertTokenization(
        input = "&succneqq",
        expectedTokens = listOf(
            *chars("&succneqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity succneqq with a semicolon`() = assertTokenization(
        input = "&succneqq;",
        expectedTokens = listOf(
            char('⪶')
        )
    )

    @Test
    fun `Bad named entity succnsim without a semicolon`() = assertTokenization(
        input = "&succnsim",
        expectedTokens = listOf(
            *chars("&succnsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity succnsim with a semicolon`() = assertTokenization(
        input = "&succnsim;",
        expectedTokens = listOf(
            char('⋩')
        )
    )

    @Test
    fun `Bad named entity succsim without a semicolon`() = assertTokenization(
        input = "&succsim",
        expectedTokens = listOf(
            *chars("&succsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity succsim with a semicolon`() = assertTokenization(
        input = "&succsim;",
        expectedTokens = listOf(
            char('≿')
        )
    )

    @Test
    fun `Bad named entity sum without a semicolon`() = assertTokenization(
        input = "&sum",
        expectedTokens = listOf(
            *chars("&sum").toTypedArray()
        )
    )

    @Test
    fun `Named entity sum with a semicolon`() = assertTokenization(
        input = "&sum;",
        expectedTokens = listOf(
            char('∑')
        )
    )

    @Test
    fun `Bad named entity sung without a semicolon`() = assertTokenization(
        input = "&sung",
        expectedTokens = listOf(
            *chars("&sung").toTypedArray()
        )
    )

    @Test
    fun `Named entity sung with a semicolon`() = assertTokenization(
        input = "&sung;",
        expectedTokens = listOf(
            char('♪')
        )
    )

    @Test
    fun `Bad named entity sup without a semicolon`() = assertTokenization(
        input = "&sup",
        expectedTokens = listOf(
            *chars("&sup").toTypedArray()
        )
    )

    @Test
    fun `Named entity sup1 without a semicolon`() = assertTokenization(
        input = "&sup1",
        expectedTokens = listOf(
            char('¹')
        )
    )

    @Test
    fun `Named entity sup1 with a semicolon`() = assertTokenization(
        input = "&sup1;",
        expectedTokens = listOf(
            char('¹')
        )
    )

    @Test
    fun `Named entity sup2 without a semicolon`() = assertTokenization(
        input = "&sup2",
        expectedTokens = listOf(
            char('²')
        )
    )

    @Test
    fun `Named entity sup2 with a semicolon`() = assertTokenization(
        input = "&sup2;",
        expectedTokens = listOf(
            char('²')
        )
    )

    @Test
    fun `Named entity sup3 without a semicolon`() = assertTokenization(
        input = "&sup3",
        expectedTokens = listOf(
            char('³')
        )
    )

    @Test
    fun `Named entity sup3 with a semicolon`() = assertTokenization(
        input = "&sup3;",
        expectedTokens = listOf(
            char('³')
        )
    )

    @Test
    fun `Named entity sup with a semicolon`() = assertTokenization(
        input = "&sup;",
        expectedTokens = listOf(
            char('⊃')
        )
    )

    @Test
    fun `Bad named entity supE without a semicolon`() = assertTokenization(
        input = "&supE",
        expectedTokens = listOf(
            *chars("&supE").toTypedArray()
        )
    )

    @Test
    fun `Named entity supE with a semicolon`() = assertTokenization(
        input = "&supE;",
        expectedTokens = listOf(
            char('⫆')
        )
    )

    @Test
    fun `Bad named entity supdot without a semicolon`() = assertTokenization(
        input = "&supdot",
        expectedTokens = listOf(
            *chars("&supdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity supdot with a semicolon`() = assertTokenization(
        input = "&supdot;",
        expectedTokens = listOf(
            char('⪾')
        )
    )

    @Test
    fun `Bad named entity supdsub without a semicolon`() = assertTokenization(
        input = "&supdsub",
        expectedTokens = listOf(
            *chars("&supdsub").toTypedArray()
        )
    )

    @Test
    fun `Named entity supdsub with a semicolon`() = assertTokenization(
        input = "&supdsub;",
        expectedTokens = listOf(
            char('⫘')
        )
    )

    @Test
    fun `Bad named entity supe without a semicolon`() = assertTokenization(
        input = "&supe",
        expectedTokens = listOf(
            *chars("&supe").toTypedArray()
        )
    )

    @Test
    fun `Named entity supe with a semicolon`() = assertTokenization(
        input = "&supe;",
        expectedTokens = listOf(
            char('⊇')
        )
    )

    @Test
    fun `Bad named entity supedot without a semicolon`() = assertTokenization(
        input = "&supedot",
        expectedTokens = listOf(
            *chars("&supedot").toTypedArray()
        )
    )

    @Test
    fun `Named entity supedot with a semicolon`() = assertTokenization(
        input = "&supedot;",
        expectedTokens = listOf(
            char('⫄')
        )
    )

    @Test
    fun `Bad named entity suphsol without a semicolon`() = assertTokenization(
        input = "&suphsol",
        expectedTokens = listOf(
            *chars("&suphsol").toTypedArray()
        )
    )

    @Test
    fun `Named entity suphsol with a semicolon`() = assertTokenization(
        input = "&suphsol;",
        expectedTokens = listOf(
            char('⟉')
        )
    )

    @Test
    fun `Bad named entity suphsub without a semicolon`() = assertTokenization(
        input = "&suphsub",
        expectedTokens = listOf(
            *chars("&suphsub").toTypedArray()
        )
    )

    @Test
    fun `Named entity suphsub with a semicolon`() = assertTokenization(
        input = "&suphsub;",
        expectedTokens = listOf(
            char('⫗')
        )
    )

    @Test
    fun `Bad named entity suplarr without a semicolon`() = assertTokenization(
        input = "&suplarr",
        expectedTokens = listOf(
            *chars("&suplarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity suplarr with a semicolon`() = assertTokenization(
        input = "&suplarr;",
        expectedTokens = listOf(
            char('⥻')
        )
    )

    @Test
    fun `Bad named entity supmult without a semicolon`() = assertTokenization(
        input = "&supmult",
        expectedTokens = listOf(
            *chars("&supmult").toTypedArray()
        )
    )

    @Test
    fun `Named entity supmult with a semicolon`() = assertTokenization(
        input = "&supmult;",
        expectedTokens = listOf(
            char('⫂')
        )
    )

    @Test
    fun `Bad named entity supnE without a semicolon`() = assertTokenization(
        input = "&supnE",
        expectedTokens = listOf(
            *chars("&supnE").toTypedArray()
        )
    )

    @Test
    fun `Named entity supnE with a semicolon`() = assertTokenization(
        input = "&supnE;",
        expectedTokens = listOf(
            char('⫌')
        )
    )

    @Test
    fun `Bad named entity supne without a semicolon`() = assertTokenization(
        input = "&supne",
        expectedTokens = listOf(
            *chars("&supne").toTypedArray()
        )
    )

    @Test
    fun `Named entity supne with a semicolon`() = assertTokenization(
        input = "&supne;",
        expectedTokens = listOf(
            char('⊋')
        )
    )

    @Test
    fun `Bad named entity supplus without a semicolon`() = assertTokenization(
        input = "&supplus",
        expectedTokens = listOf(
            *chars("&supplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity supplus with a semicolon`() = assertTokenization(
        input = "&supplus;",
        expectedTokens = listOf(
            char('⫀')
        )
    )

    @Test
    fun `Bad named entity supset without a semicolon`() = assertTokenization(
        input = "&supset",
        expectedTokens = listOf(
            *chars("&supset").toTypedArray()
        )
    )

    @Test
    fun `Named entity supset with a semicolon`() = assertTokenization(
        input = "&supset;",
        expectedTokens = listOf(
            char('⊃')
        )
    )

    @Test
    fun `Bad named entity supseteq without a semicolon`() = assertTokenization(
        input = "&supseteq",
        expectedTokens = listOf(
            *chars("&supseteq").toTypedArray()
        )
    )

    @Test
    fun `Named entity supseteq with a semicolon`() = assertTokenization(
        input = "&supseteq;",
        expectedTokens = listOf(
            char('⊇')
        )
    )

    @Test
    fun `Bad named entity supseteqq without a semicolon`() = assertTokenization(
        input = "&supseteqq",
        expectedTokens = listOf(
            *chars("&supseteqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity supseteqq with a semicolon`() = assertTokenization(
        input = "&supseteqq;",
        expectedTokens = listOf(
            char('⫆')
        )
    )

    @Test
    fun `Bad named entity supsetneq without a semicolon`() = assertTokenization(
        input = "&supsetneq",
        expectedTokens = listOf(
            *chars("&supsetneq").toTypedArray()
        )
    )

    @Test
    fun `Named entity supsetneq with a semicolon`() = assertTokenization(
        input = "&supsetneq;",
        expectedTokens = listOf(
            char('⊋')
        )
    )

    @Test
    fun `Bad named entity supsetneqq without a semicolon`() = assertTokenization(
        input = "&supsetneqq",
        expectedTokens = listOf(
            *chars("&supsetneqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity supsetneqq with a semicolon`() = assertTokenization(
        input = "&supsetneqq;",
        expectedTokens = listOf(
            char('⫌')
        )
    )

    @Test
    fun `Bad named entity supsim without a semicolon`() = assertTokenization(
        input = "&supsim",
        expectedTokens = listOf(
            *chars("&supsim").toTypedArray()
        )
    )

    @Test
    fun `Named entity supsim with a semicolon`() = assertTokenization(
        input = "&supsim;",
        expectedTokens = listOf(
            char('⫈')
        )
    )

    @Test
    fun `Bad named entity supsub without a semicolon`() = assertTokenization(
        input = "&supsub",
        expectedTokens = listOf(
            *chars("&supsub").toTypedArray()
        )
    )

    @Test
    fun `Named entity supsub with a semicolon`() = assertTokenization(
        input = "&supsub;",
        expectedTokens = listOf(
            char('⫔')
        )
    )

    @Test
    fun `Bad named entity supsup without a semicolon`() = assertTokenization(
        input = "&supsup",
        expectedTokens = listOf(
            *chars("&supsup").toTypedArray()
        )
    )

    @Test
    fun `Named entity supsup with a semicolon`() = assertTokenization(
        input = "&supsup;",
        expectedTokens = listOf(
            char('⫖')
        )
    )

    @Test
    fun `Bad named entity swArr without a semicolon`() = assertTokenization(
        input = "&swArr",
        expectedTokens = listOf(
            *chars("&swArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity swArr with a semicolon`() = assertTokenization(
        input = "&swArr;",
        expectedTokens = listOf(
            char('⇙')
        )
    )

    @Test
    fun `Bad named entity swarhk without a semicolon`() = assertTokenization(
        input = "&swarhk",
        expectedTokens = listOf(
            *chars("&swarhk").toTypedArray()
        )
    )

    @Test
    fun `Named entity swarhk with a semicolon`() = assertTokenization(
        input = "&swarhk;",
        expectedTokens = listOf(
            char('⤦')
        )
    )

    @Test
    fun `Bad named entity swarr without a semicolon`() = assertTokenization(
        input = "&swarr",
        expectedTokens = listOf(
            *chars("&swarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity swarr with a semicolon`() = assertTokenization(
        input = "&swarr;",
        expectedTokens = listOf(
            char('↙')
        )
    )

    @Test
    fun `Bad named entity swarrow without a semicolon`() = assertTokenization(
        input = "&swarrow",
        expectedTokens = listOf(
            *chars("&swarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity swarrow with a semicolon`() = assertTokenization(
        input = "&swarrow;",
        expectedTokens = listOf(
            char('↙')
        )
    )

    @Test
    fun `Bad named entity swnwar without a semicolon`() = assertTokenization(
        input = "&swnwar",
        expectedTokens = listOf(
            *chars("&swnwar").toTypedArray()
        )
    )

    @Test
    fun `Named entity swnwar with a semicolon`() = assertTokenization(
        input = "&swnwar;",
        expectedTokens = listOf(
            char('⤪')
        )
    )

    @Test
    fun `Named entity szlig without a semicolon`() = assertTokenization(
        input = "&szlig",
        expectedTokens = listOf(
            char('ß')
        )
    )

    @Test
    fun `Named entity szlig with a semicolon`() = assertTokenization(
        input = "&szlig;",
        expectedTokens = listOf(
            char('ß')
        )
    )

    @Test
    fun `Bad named entity target without a semicolon`() = assertTokenization(
        input = "&target",
        expectedTokens = listOf(
            *chars("&target").toTypedArray()
        )
    )

    @Test
    fun `Named entity target with a semicolon`() = assertTokenization(
        input = "&target;",
        expectedTokens = listOf(
            char('⌖')
        )
    )

    @Test
    fun `Bad named entity tau without a semicolon`() = assertTokenization(
        input = "&tau",
        expectedTokens = listOf(
            *chars("&tau").toTypedArray()
        )
    )

    @Test
    fun `Named entity tau with a semicolon`() = assertTokenization(
        input = "&tau;",
        expectedTokens = listOf(
            char('τ')
        )
    )

    @Test
    fun `Bad named entity tbrk without a semicolon`() = assertTokenization(
        input = "&tbrk",
        expectedTokens = listOf(
            *chars("&tbrk").toTypedArray()
        )
    )

    @Test
    fun `Named entity tbrk with a semicolon`() = assertTokenization(
        input = "&tbrk;",
        expectedTokens = listOf(
            char('⎴')
        )
    )

    @Test
    fun `Bad named entity tcaron without a semicolon`() = assertTokenization(
        input = "&tcaron",
        expectedTokens = listOf(
            *chars("&tcaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity tcaron with a semicolon`() = assertTokenization(
        input = "&tcaron;",
        expectedTokens = listOf(
            char('ť')
        )
    )

    @Test
    fun `Bad named entity tcedil without a semicolon`() = assertTokenization(
        input = "&tcedil",
        expectedTokens = listOf(
            *chars("&tcedil").toTypedArray()
        )
    )

    @Test
    fun `Named entity tcedil with a semicolon`() = assertTokenization(
        input = "&tcedil;",
        expectedTokens = listOf(
            char('ţ')
        )
    )

    @Test
    fun `Bad named entity tcy without a semicolon`() = assertTokenization(
        input = "&tcy",
        expectedTokens = listOf(
            *chars("&tcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity tcy with a semicolon`() = assertTokenization(
        input = "&tcy;",
        expectedTokens = listOf(
            char('т')
        )
    )

    @Test
    fun `Bad named entity tdot without a semicolon`() = assertTokenization(
        input = "&tdot",
        expectedTokens = listOf(
            *chars("&tdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity tdot with a semicolon`() = assertTokenization(
        input = "&tdot;",
        expectedTokens = listOf(
            char('⃛')
        )
    )

    @Test
    fun `Bad named entity telrec without a semicolon`() = assertTokenization(
        input = "&telrec",
        expectedTokens = listOf(
            *chars("&telrec").toTypedArray()
        )
    )

    @Test
    fun `Named entity telrec with a semicolon`() = assertTokenization(
        input = "&telrec;",
        expectedTokens = listOf(
            char('⌕')
        )
    )

    @Test
    fun `Bad named entity tfr without a semicolon`() = assertTokenization(
        input = "&tfr",
        expectedTokens = listOf(
            *chars("&tfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity tfr with a semicolon`() = assertTokenization(
        input = "&tfr;",
        expectedTokens = listOf(
            *chars("𝔱").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity there4 without a semicolon`() = assertTokenization(
        input = "&there4",
        expectedTokens = listOf(
            *chars("&there4").toTypedArray()
        )
    )

    @Test
    fun `Named entity there4 with a semicolon`() = assertTokenization(
        input = "&there4;",
        expectedTokens = listOf(
            char('∴')
        )
    )

    @Test
    fun `Bad named entity therefore without a semicolon`() = assertTokenization(
        input = "&therefore",
        expectedTokens = listOf(
            *chars("&therefore").toTypedArray()
        )
    )

    @Test
    fun `Named entity therefore with a semicolon`() = assertTokenization(
        input = "&therefore;",
        expectedTokens = listOf(
            char('∴')
        )
    )

    @Test
    fun `Bad named entity theta without a semicolon`() = assertTokenization(
        input = "&theta",
        expectedTokens = listOf(
            *chars("&theta").toTypedArray()
        )
    )

    @Test
    fun `Named entity theta with a semicolon`() = assertTokenization(
        input = "&theta;",
        expectedTokens = listOf(
            char('θ')
        )
    )

    @Test
    fun `Bad named entity thetasym without a semicolon`() = assertTokenization(
        input = "&thetasym",
        expectedTokens = listOf(
            *chars("&thetasym").toTypedArray()
        )
    )

    @Test
    fun `Named entity thetasym with a semicolon`() = assertTokenization(
        input = "&thetasym;",
        expectedTokens = listOf(
            char('ϑ')
        )
    )

    @Test
    fun `Bad named entity thetav without a semicolon`() = assertTokenization(
        input = "&thetav",
        expectedTokens = listOf(
            *chars("&thetav").toTypedArray()
        )
    )

    @Test
    fun `Named entity thetav with a semicolon`() = assertTokenization(
        input = "&thetav;",
        expectedTokens = listOf(
            char('ϑ')
        )
    )

    @Test
    fun `Bad named entity thickapprox without a semicolon`() = assertTokenization(
        input = "&thickapprox",
        expectedTokens = listOf(
            *chars("&thickapprox").toTypedArray()
        )
    )

    @Test
    fun `Named entity thickapprox with a semicolon`() = assertTokenization(
        input = "&thickapprox;",
        expectedTokens = listOf(
            char('≈')
        )
    )

    @Test
    fun `Bad named entity thicksim without a semicolon`() = assertTokenization(
        input = "&thicksim",
        expectedTokens = listOf(
            *chars("&thicksim").toTypedArray()
        )
    )

    @Test
    fun `Named entity thicksim with a semicolon`() = assertTokenization(
        input = "&thicksim;",
        expectedTokens = listOf(
            char('∼')
        )
    )

    @Test
    fun `Bad named entity thinsp without a semicolon`() = assertTokenization(
        input = "&thinsp",
        expectedTokens = listOf(
            *chars("&thinsp").toTypedArray()
        )
    )

    @Test
    fun `Named entity thinsp with a semicolon`() = assertTokenization(
        input = "&thinsp;",
        expectedTokens = listOf(
            char(' ')
        )
    )

    @Test
    fun `Bad named entity thkap without a semicolon`() = assertTokenization(
        input = "&thkap",
        expectedTokens = listOf(
            *chars("&thkap").toTypedArray()
        )
    )

    @Test
    fun `Named entity thkap with a semicolon`() = assertTokenization(
        input = "&thkap;",
        expectedTokens = listOf(
            char('≈')
        )
    )

    @Test
    fun `Bad named entity thksim without a semicolon`() = assertTokenization(
        input = "&thksim",
        expectedTokens = listOf(
            *chars("&thksim").toTypedArray()
        )
    )

    @Test
    fun `Named entity thksim with a semicolon`() = assertTokenization(
        input = "&thksim;",
        expectedTokens = listOf(
            char('∼')
        )
    )

    @Test
    fun `Named entity thorn without a semicolon`() = assertTokenization(
        input = "&thorn",
        expectedTokens = listOf(
            char('þ')
        )
    )

    @Test
    fun `Named entity thorn with a semicolon`() = assertTokenization(
        input = "&thorn;",
        expectedTokens = listOf(
            char('þ')
        )
    )

    @Test
    fun `Bad named entity tilde without a semicolon`() = assertTokenization(
        input = "&tilde",
        expectedTokens = listOf(
            *chars("&tilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity tilde with a semicolon`() = assertTokenization(
        input = "&tilde;",
        expectedTokens = listOf(
            char('˜')
        )
    )

    @Test
    fun `Named entity times without a semicolon`() = assertTokenization(
        input = "&times",
        expectedTokens = listOf(
            char('×')
        )
    )

    @Test
    fun `Named entity times with a semicolon`() = assertTokenization(
        input = "&times;",
        expectedTokens = listOf(
            char('×')
        )
    )

    @Test
    fun `Named entity timesb with a semicolon`() = assertTokenization(
        input = "&timesb;",
        expectedTokens = listOf(
            char('⊠')
        )
    )

    @Test
    fun `Named entity timesbar with a semicolon`() = assertTokenization(
        input = "&timesbar;",
        expectedTokens = listOf(
            char('⨱')
        )
    )

    @Test
    fun `Named entity timesd with a semicolon`() = assertTokenization(
        input = "&timesd;",
        expectedTokens = listOf(
            char('⨰')
        )
    )

    @Test
    fun `Bad named entity tint without a semicolon`() = assertTokenization(
        input = "&tint",
        expectedTokens = listOf(
            *chars("&tint").toTypedArray()
        )
    )

    @Test
    fun `Named entity tint with a semicolon`() = assertTokenization(
        input = "&tint;",
        expectedTokens = listOf(
            char('∭')
        )
    )

    @Test
    fun `Bad named entity toea without a semicolon`() = assertTokenization(
        input = "&toea",
        expectedTokens = listOf(
            *chars("&toea").toTypedArray()
        )
    )

    @Test
    fun `Named entity toea with a semicolon`() = assertTokenization(
        input = "&toea;",
        expectedTokens = listOf(
            char('⤨')
        )
    )

    @Test
    fun `Bad named entity top without a semicolon`() = assertTokenization(
        input = "&top",
        expectedTokens = listOf(
            *chars("&top").toTypedArray()
        )
    )

    @Test
    fun `Named entity top with a semicolon`() = assertTokenization(
        input = "&top;",
        expectedTokens = listOf(
            char('⊤')
        )
    )

    @Test
    fun `Bad named entity topbot without a semicolon`() = assertTokenization(
        input = "&topbot",
        expectedTokens = listOf(
            *chars("&topbot").toTypedArray()
        )
    )

    @Test
    fun `Named entity topbot with a semicolon`() = assertTokenization(
        input = "&topbot;",
        expectedTokens = listOf(
            char('⌶')
        )
    )

    @Test
    fun `Bad named entity topcir without a semicolon`() = assertTokenization(
        input = "&topcir",
        expectedTokens = listOf(
            *chars("&topcir").toTypedArray()
        )
    )

    @Test
    fun `Named entity topcir with a semicolon`() = assertTokenization(
        input = "&topcir;",
        expectedTokens = listOf(
            char('⫱')
        )
    )

    @Test
    fun `Bad named entity topf without a semicolon`() = assertTokenization(
        input = "&topf",
        expectedTokens = listOf(
            *chars("&topf").toTypedArray()
        )
    )

    @Test
    fun `Named entity topf with a semicolon`() = assertTokenization(
        input = "&topf;",
        expectedTokens = listOf(
            *chars("𝕥").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity topfork without a semicolon`() = assertTokenization(
        input = "&topfork",
        expectedTokens = listOf(
            *chars("&topfork").toTypedArray()
        )
    )

    @Test
    fun `Named entity topfork with a semicolon`() = assertTokenization(
        input = "&topfork;",
        expectedTokens = listOf(
            char('⫚')
        )
    )

    @Test
    fun `Bad named entity tosa without a semicolon`() = assertTokenization(
        input = "&tosa",
        expectedTokens = listOf(
            *chars("&tosa").toTypedArray()
        )
    )

    @Test
    fun `Named entity tosa with a semicolon`() = assertTokenization(
        input = "&tosa;",
        expectedTokens = listOf(
            char('⤩')
        )
    )

    @Test
    fun `Bad named entity tprime without a semicolon`() = assertTokenization(
        input = "&tprime",
        expectedTokens = listOf(
            *chars("&tprime").toTypedArray()
        )
    )

    @Test
    fun `Named entity tprime with a semicolon`() = assertTokenization(
        input = "&tprime;",
        expectedTokens = listOf(
            char('‴')
        )
    )

    @Test
    fun `Bad named entity trade without a semicolon`() = assertTokenization(
        input = "&trade",
        expectedTokens = listOf(
            *chars("&trade").toTypedArray()
        )
    )

    @Test
    fun `Named entity trade with a semicolon`() = assertTokenization(
        input = "&trade;",
        expectedTokens = listOf(
            char('™')
        )
    )

    @Test
    fun `Bad named entity triangle without a semicolon`() = assertTokenization(
        input = "&triangle",
        expectedTokens = listOf(
            *chars("&triangle").toTypedArray()
        )
    )

    @Test
    fun `Named entity triangle with a semicolon`() = assertTokenization(
        input = "&triangle;",
        expectedTokens = listOf(
            char('▵')
        )
    )

    @Test
    fun `Bad named entity triangledown without a semicolon`() = assertTokenization(
        input = "&triangledown",
        expectedTokens = listOf(
            *chars("&triangledown").toTypedArray()
        )
    )

    @Test
    fun `Named entity triangledown with a semicolon`() = assertTokenization(
        input = "&triangledown;",
        expectedTokens = listOf(
            char('▿')
        )
    )

    @Test
    fun `Bad named entity triangleleft without a semicolon`() = assertTokenization(
        input = "&triangleleft",
        expectedTokens = listOf(
            *chars("&triangleleft").toTypedArray()
        )
    )

    @Test
    fun `Named entity triangleleft with a semicolon`() = assertTokenization(
        input = "&triangleleft;",
        expectedTokens = listOf(
            char('◃')
        )
    )

    @Test
    fun `Bad named entity trianglelefteq without a semicolon`() = assertTokenization(
        input = "&trianglelefteq",
        expectedTokens = listOf(
            *chars("&trianglelefteq").toTypedArray()
        )
    )

    @Test
    fun `Named entity trianglelefteq with a semicolon`() = assertTokenization(
        input = "&trianglelefteq;",
        expectedTokens = listOf(
            char('⊴')
        )
    )

    @Test
    fun `Bad named entity triangleq without a semicolon`() = assertTokenization(
        input = "&triangleq",
        expectedTokens = listOf(
            *chars("&triangleq").toTypedArray()
        )
    )

    @Test
    fun `Named entity triangleq with a semicolon`() = assertTokenization(
        input = "&triangleq;",
        expectedTokens = listOf(
            char('≜')
        )
    )

    @Test
    fun `Bad named entity triangleright without a semicolon`() = assertTokenization(
        input = "&triangleright",
        expectedTokens = listOf(
            *chars("&triangleright").toTypedArray()
        )
    )

    @Test
    fun `Named entity triangleright with a semicolon`() = assertTokenization(
        input = "&triangleright;",
        expectedTokens = listOf(
            char('▹')
        )
    )

    @Test
    fun `Bad named entity trianglerighteq without a semicolon`() = assertTokenization(
        input = "&trianglerighteq",
        expectedTokens = listOf(
            *chars("&trianglerighteq").toTypedArray()
        )
    )

    @Test
    fun `Named entity trianglerighteq with a semicolon`() = assertTokenization(
        input = "&trianglerighteq;",
        expectedTokens = listOf(
            char('⊵')
        )
    )

    @Test
    fun `Bad named entity tridot without a semicolon`() = assertTokenization(
        input = "&tridot",
        expectedTokens = listOf(
            *chars("&tridot").toTypedArray()
        )
    )

    @Test
    fun `Named entity tridot with a semicolon`() = assertTokenization(
        input = "&tridot;",
        expectedTokens = listOf(
            char('◬')
        )
    )

    @Test
    fun `Bad named entity trie without a semicolon`() = assertTokenization(
        input = "&trie",
        expectedTokens = listOf(
            *chars("&trie").toTypedArray()
        )
    )

    @Test
    fun `Named entity trie with a semicolon`() = assertTokenization(
        input = "&trie;",
        expectedTokens = listOf(
            char('≜')
        )
    )

    @Test
    fun `Bad named entity triminus without a semicolon`() = assertTokenization(
        input = "&triminus",
        expectedTokens = listOf(
            *chars("&triminus").toTypedArray()
        )
    )

    @Test
    fun `Named entity triminus with a semicolon`() = assertTokenization(
        input = "&triminus;",
        expectedTokens = listOf(
            char('⨺')
        )
    )

    @Test
    fun `Bad named entity triplus without a semicolon`() = assertTokenization(
        input = "&triplus",
        expectedTokens = listOf(
            *chars("&triplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity triplus with a semicolon`() = assertTokenization(
        input = "&triplus;",
        expectedTokens = listOf(
            char('⨹')
        )
    )

    @Test
    fun `Bad named entity trisb without a semicolon`() = assertTokenization(
        input = "&trisb",
        expectedTokens = listOf(
            *chars("&trisb").toTypedArray()
        )
    )

    @Test
    fun `Named entity trisb with a semicolon`() = assertTokenization(
        input = "&trisb;",
        expectedTokens = listOf(
            char('⧍')
        )
    )

    @Test
    fun `Bad named entity tritime without a semicolon`() = assertTokenization(
        input = "&tritime",
        expectedTokens = listOf(
            *chars("&tritime").toTypedArray()
        )
    )

    @Test
    fun `Named entity tritime with a semicolon`() = assertTokenization(
        input = "&tritime;",
        expectedTokens = listOf(
            char('⨻')
        )
    )

    @Test
    fun `Bad named entity trpezium without a semicolon`() = assertTokenization(
        input = "&trpezium",
        expectedTokens = listOf(
            *chars("&trpezium").toTypedArray()
        )
    )

    @Test
    fun `Named entity trpezium with a semicolon`() = assertTokenization(
        input = "&trpezium;",
        expectedTokens = listOf(
            char('⏢')
        )
    )

    @Test
    fun `Bad named entity tscr without a semicolon`() = assertTokenization(
        input = "&tscr",
        expectedTokens = listOf(
            *chars("&tscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity tscr with a semicolon`() = assertTokenization(
        input = "&tscr;",
        expectedTokens = listOf(
            *chars("𝓉").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity tscy without a semicolon`() = assertTokenization(
        input = "&tscy",
        expectedTokens = listOf(
            *chars("&tscy").toTypedArray()
        )
    )

    @Test
    fun `Named entity tscy with a semicolon`() = assertTokenization(
        input = "&tscy;",
        expectedTokens = listOf(
            char('ц')
        )
    )

    @Test
    fun `Bad named entity tshcy without a semicolon`() = assertTokenization(
        input = "&tshcy",
        expectedTokens = listOf(
            *chars("&tshcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity tshcy with a semicolon`() = assertTokenization(
        input = "&tshcy;",
        expectedTokens = listOf(
            char('ћ')
        )
    )

    @Test
    fun `Bad named entity tstrok without a semicolon`() = assertTokenization(
        input = "&tstrok",
        expectedTokens = listOf(
            *chars("&tstrok").toTypedArray()
        )
    )

    @Test
    fun `Named entity tstrok with a semicolon`() = assertTokenization(
        input = "&tstrok;",
        expectedTokens = listOf(
            char('ŧ')
        )
    )

    @Test
    fun `Bad named entity twixt without a semicolon`() = assertTokenization(
        input = "&twixt",
        expectedTokens = listOf(
            *chars("&twixt").toTypedArray()
        )
    )

    @Test
    fun `Named entity twixt with a semicolon`() = assertTokenization(
        input = "&twixt;",
        expectedTokens = listOf(
            char('≬')
        )
    )

    @Test
    fun `Bad named entity twoheadleftarrow without a semicolon`() = assertTokenization(
        input = "&twoheadleftarrow",
        expectedTokens = listOf(
            *chars("&twoheadleftarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity twoheadleftarrow with a semicolon`() = assertTokenization(
        input = "&twoheadleftarrow;",
        expectedTokens = listOf(
            char('↞')
        )
    )

    @Test
    fun `Bad named entity twoheadrightarrow without a semicolon`() = assertTokenization(
        input = "&twoheadrightarrow",
        expectedTokens = listOf(
            *chars("&twoheadrightarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity twoheadrightarrow with a semicolon`() = assertTokenization(
        input = "&twoheadrightarrow;",
        expectedTokens = listOf(
            char('↠')
        )
    )

    @Test
    fun `Bad named entity uArr without a semicolon`() = assertTokenization(
        input = "&uArr",
        expectedTokens = listOf(
            *chars("&uArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity uArr with a semicolon`() = assertTokenization(
        input = "&uArr;",
        expectedTokens = listOf(
            char('⇑')
        )
    )

    @Test
    fun `Bad named entity uHar without a semicolon`() = assertTokenization(
        input = "&uHar",
        expectedTokens = listOf(
            *chars("&uHar").toTypedArray()
        )
    )

    @Test
    fun `Named entity uHar with a semicolon`() = assertTokenization(
        input = "&uHar;",
        expectedTokens = listOf(
            char('⥣')
        )
    )

    @Test
    fun `Named entity uacute without a semicolon`() = assertTokenization(
        input = "&uacute",
        expectedTokens = listOf(
            char('ú')
        )
    )

    @Test
    fun `Named entity uacute with a semicolon`() = assertTokenization(
        input = "&uacute;",
        expectedTokens = listOf(
            char('ú')
        )
    )

    @Test
    fun `Bad named entity uarr without a semicolon`() = assertTokenization(
        input = "&uarr",
        expectedTokens = listOf(
            *chars("&uarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity uarr with a semicolon`() = assertTokenization(
        input = "&uarr;",
        expectedTokens = listOf(
            char('↑')
        )
    )

    @Test
    fun `Bad named entity ubrcy without a semicolon`() = assertTokenization(
        input = "&ubrcy",
        expectedTokens = listOf(
            *chars("&ubrcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity ubrcy with a semicolon`() = assertTokenization(
        input = "&ubrcy;",
        expectedTokens = listOf(
            char('ў')
        )
    )

    @Test
    fun `Bad named entity ubreve without a semicolon`() = assertTokenization(
        input = "&ubreve",
        expectedTokens = listOf(
            *chars("&ubreve").toTypedArray()
        )
    )

    @Test
    fun `Named entity ubreve with a semicolon`() = assertTokenization(
        input = "&ubreve;",
        expectedTokens = listOf(
            char('ŭ')
        )
    )

    @Test
    fun `Named entity ucirc without a semicolon`() = assertTokenization(
        input = "&ucirc",
        expectedTokens = listOf(
            char('û')
        )
    )

    @Test
    fun `Named entity ucirc with a semicolon`() = assertTokenization(
        input = "&ucirc;",
        expectedTokens = listOf(
            char('û')
        )
    )

    @Test
    fun `Bad named entity ucy without a semicolon`() = assertTokenization(
        input = "&ucy",
        expectedTokens = listOf(
            *chars("&ucy").toTypedArray()
        )
    )

    @Test
    fun `Named entity ucy with a semicolon`() = assertTokenization(
        input = "&ucy;",
        expectedTokens = listOf(
            char('у')
        )
    )

    @Test
    fun `Bad named entity udarr without a semicolon`() = assertTokenization(
        input = "&udarr",
        expectedTokens = listOf(
            *chars("&udarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity udarr with a semicolon`() = assertTokenization(
        input = "&udarr;",
        expectedTokens = listOf(
            char('⇅')
        )
    )

    @Test
    fun `Bad named entity udblac without a semicolon`() = assertTokenization(
        input = "&udblac",
        expectedTokens = listOf(
            *chars("&udblac").toTypedArray()
        )
    )

    @Test
    fun `Named entity udblac with a semicolon`() = assertTokenization(
        input = "&udblac;",
        expectedTokens = listOf(
            char('ű')
        )
    )

    @Test
    fun `Bad named entity udhar without a semicolon`() = assertTokenization(
        input = "&udhar",
        expectedTokens = listOf(
            *chars("&udhar").toTypedArray()
        )
    )

    @Test
    fun `Named entity udhar with a semicolon`() = assertTokenization(
        input = "&udhar;",
        expectedTokens = listOf(
            char('⥮')
        )
    )

    @Test
    fun `Bad named entity ufisht without a semicolon`() = assertTokenization(
        input = "&ufisht",
        expectedTokens = listOf(
            *chars("&ufisht").toTypedArray()
        )
    )

    @Test
    fun `Named entity ufisht with a semicolon`() = assertTokenization(
        input = "&ufisht;",
        expectedTokens = listOf(
            char('⥾')
        )
    )

    @Test
    fun `Bad named entity ufr without a semicolon`() = assertTokenization(
        input = "&ufr",
        expectedTokens = listOf(
            *chars("&ufr").toTypedArray()
        )
    )

    @Test
    fun `Named entity ufr with a semicolon`() = assertTokenization(
        input = "&ufr;",
        expectedTokens = listOf(
            *chars("𝔲").toTypedArray()
        )
    )

    @Test
    fun `Named entity ugrave without a semicolon`() = assertTokenization(
        input = "&ugrave",
        expectedTokens = listOf(
            char('ù')
        )
    )

    @Test
    fun `Named entity ugrave with a semicolon`() = assertTokenization(
        input = "&ugrave;",
        expectedTokens = listOf(
            char('ù')
        )
    )

    @Test
    fun `Bad named entity uharl without a semicolon`() = assertTokenization(
        input = "&uharl",
        expectedTokens = listOf(
            *chars("&uharl").toTypedArray()
        )
    )

    @Test
    fun `Named entity uharl with a semicolon`() = assertTokenization(
        input = "&uharl;",
        expectedTokens = listOf(
            char('↿')
        )
    )

    @Test
    fun `Bad named entity uharr without a semicolon`() = assertTokenization(
        input = "&uharr",
        expectedTokens = listOf(
            *chars("&uharr").toTypedArray()
        )
    )

    @Test
    fun `Named entity uharr with a semicolon`() = assertTokenization(
        input = "&uharr;",
        expectedTokens = listOf(
            char('↾')
        )
    )

    @Test
    fun `Bad named entity uhblk without a semicolon`() = assertTokenization(
        input = "&uhblk",
        expectedTokens = listOf(
            *chars("&uhblk").toTypedArray()
        )
    )

    @Test
    fun `Named entity uhblk with a semicolon`() = assertTokenization(
        input = "&uhblk;",
        expectedTokens = listOf(
            char('▀')
        )
    )

    @Test
    fun `Bad named entity ulcorn without a semicolon`() = assertTokenization(
        input = "&ulcorn",
        expectedTokens = listOf(
            *chars("&ulcorn").toTypedArray()
        )
    )

    @Test
    fun `Named entity ulcorn with a semicolon`() = assertTokenization(
        input = "&ulcorn;",
        expectedTokens = listOf(
            char('⌜')
        )
    )

    @Test
    fun `Bad named entity ulcorner without a semicolon`() = assertTokenization(
        input = "&ulcorner",
        expectedTokens = listOf(
            *chars("&ulcorner").toTypedArray()
        )
    )

    @Test
    fun `Named entity ulcorner with a semicolon`() = assertTokenization(
        input = "&ulcorner;",
        expectedTokens = listOf(
            char('⌜')
        )
    )

    @Test
    fun `Bad named entity ulcrop without a semicolon`() = assertTokenization(
        input = "&ulcrop",
        expectedTokens = listOf(
            *chars("&ulcrop").toTypedArray()
        )
    )

    @Test
    fun `Named entity ulcrop with a semicolon`() = assertTokenization(
        input = "&ulcrop;",
        expectedTokens = listOf(
            char('⌏')
        )
    )

    @Test
    fun `Bad named entity ultri without a semicolon`() = assertTokenization(
        input = "&ultri",
        expectedTokens = listOf(
            *chars("&ultri").toTypedArray()
        )
    )

    @Test
    fun `Named entity ultri with a semicolon`() = assertTokenization(
        input = "&ultri;",
        expectedTokens = listOf(
            char('◸')
        )
    )

    @Test
    fun `Bad named entity umacr without a semicolon`() = assertTokenization(
        input = "&umacr",
        expectedTokens = listOf(
            *chars("&umacr").toTypedArray()
        )
    )

    @Test
    fun `Named entity umacr with a semicolon`() = assertTokenization(
        input = "&umacr;",
        expectedTokens = listOf(
            char('ū')
        )
    )

    @Test
    fun `Named entity uml without a semicolon`() = assertTokenization(
        input = "&uml",
        expectedTokens = listOf(
            char('¨')
        )
    )

    @Test
    fun `Named entity uml with a semicolon`() = assertTokenization(
        input = "&uml;",
        expectedTokens = listOf(
            char('¨')
        )
    )

    @Test
    fun `Bad named entity uogon without a semicolon`() = assertTokenization(
        input = "&uogon",
        expectedTokens = listOf(
            *chars("&uogon").toTypedArray()
        )
    )

    @Test
    fun `Named entity uogon with a semicolon`() = assertTokenization(
        input = "&uogon;",
        expectedTokens = listOf(
            char('ų')
        )
    )

    @Test
    fun `Bad named entity uopf without a semicolon`() = assertTokenization(
        input = "&uopf",
        expectedTokens = listOf(
            *chars("&uopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity uopf with a semicolon`() = assertTokenization(
        input = "&uopf;",
        expectedTokens = listOf(
            *chars("𝕦").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity uparrow without a semicolon`() = assertTokenization(
        input = "&uparrow",
        expectedTokens = listOf(
            *chars("&uparrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity uparrow with a semicolon`() = assertTokenization(
        input = "&uparrow;",
        expectedTokens = listOf(
            char('↑')
        )
    )

    @Test
    fun `Bad named entity updownarrow without a semicolon`() = assertTokenization(
        input = "&updownarrow",
        expectedTokens = listOf(
            *chars("&updownarrow").toTypedArray()
        )
    )

    @Test
    fun `Named entity updownarrow with a semicolon`() = assertTokenization(
        input = "&updownarrow;",
        expectedTokens = listOf(
            char('↕')
        )
    )

    @Test
    fun `Bad named entity upharpoonleft without a semicolon`() = assertTokenization(
        input = "&upharpoonleft",
        expectedTokens = listOf(
            *chars("&upharpoonleft").toTypedArray()
        )
    )

    @Test
    fun `Named entity upharpoonleft with a semicolon`() = assertTokenization(
        input = "&upharpoonleft;",
        expectedTokens = listOf(
            char('↿')
        )
    )

    @Test
    fun `Bad named entity upharpoonright without a semicolon`() = assertTokenization(
        input = "&upharpoonright",
        expectedTokens = listOf(
            *chars("&upharpoonright").toTypedArray()
        )
    )

    @Test
    fun `Named entity upharpoonright with a semicolon`() = assertTokenization(
        input = "&upharpoonright;",
        expectedTokens = listOf(
            char('↾')
        )
    )

    @Test
    fun `Bad named entity uplus without a semicolon`() = assertTokenization(
        input = "&uplus",
        expectedTokens = listOf(
            *chars("&uplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity uplus with a semicolon`() = assertTokenization(
        input = "&uplus;",
        expectedTokens = listOf(
            char('⊎')
        )
    )

    @Test
    fun `Bad named entity upsi without a semicolon`() = assertTokenization(
        input = "&upsi",
        expectedTokens = listOf(
            *chars("&upsi").toTypedArray()
        )
    )

    @Test
    fun `Named entity upsi with a semicolon`() = assertTokenization(
        input = "&upsi;",
        expectedTokens = listOf(
            char('υ')
        )
    )

    @Test
    fun `Bad named entity upsih without a semicolon`() = assertTokenization(
        input = "&upsih",
        expectedTokens = listOf(
            *chars("&upsih").toTypedArray()
        )
    )

    @Test
    fun `Named entity upsih with a semicolon`() = assertTokenization(
        input = "&upsih;",
        expectedTokens = listOf(
            char('ϒ')
        )
    )

    @Test
    fun `Bad named entity upsilon without a semicolon`() = assertTokenization(
        input = "&upsilon",
        expectedTokens = listOf(
            *chars("&upsilon").toTypedArray()
        )
    )

    @Test
    fun `Named entity upsilon with a semicolon`() = assertTokenization(
        input = "&upsilon;",
        expectedTokens = listOf(
            char('υ')
        )
    )

    @Test
    fun `Bad named entity upuparrows without a semicolon`() = assertTokenization(
        input = "&upuparrows",
        expectedTokens = listOf(
            *chars("&upuparrows").toTypedArray()
        )
    )

    @Test
    fun `Named entity upuparrows with a semicolon`() = assertTokenization(
        input = "&upuparrows;",
        expectedTokens = listOf(
            char('⇈')
        )
    )

    @Test
    fun `Bad named entity urcorn without a semicolon`() = assertTokenization(
        input = "&urcorn",
        expectedTokens = listOf(
            *chars("&urcorn").toTypedArray()
        )
    )

    @Test
    fun `Named entity urcorn with a semicolon`() = assertTokenization(
        input = "&urcorn;",
        expectedTokens = listOf(
            char('⌝')
        )
    )

    @Test
    fun `Bad named entity urcorner without a semicolon`() = assertTokenization(
        input = "&urcorner",
        expectedTokens = listOf(
            *chars("&urcorner").toTypedArray()
        )
    )

    @Test
    fun `Named entity urcorner with a semicolon`() = assertTokenization(
        input = "&urcorner;",
        expectedTokens = listOf(
            char('⌝')
        )
    )

    @Test
    fun `Bad named entity urcrop without a semicolon`() = assertTokenization(
        input = "&urcrop",
        expectedTokens = listOf(
            *chars("&urcrop").toTypedArray()
        )
    )

    @Test
    fun `Named entity urcrop with a semicolon`() = assertTokenization(
        input = "&urcrop;",
        expectedTokens = listOf(
            char('⌎')
        )
    )

    @Test
    fun `Bad named entity uring without a semicolon`() = assertTokenization(
        input = "&uring",
        expectedTokens = listOf(
            *chars("&uring").toTypedArray()
        )
    )

    @Test
    fun `Named entity uring with a semicolon`() = assertTokenization(
        input = "&uring;",
        expectedTokens = listOf(
            char('ů')
        )
    )

    @Test
    fun `Bad named entity urtri without a semicolon`() = assertTokenization(
        input = "&urtri",
        expectedTokens = listOf(
            *chars("&urtri").toTypedArray()
        )
    )

    @Test
    fun `Named entity urtri with a semicolon`() = assertTokenization(
        input = "&urtri;",
        expectedTokens = listOf(
            char('◹')
        )
    )

    @Test
    fun `Bad named entity uscr without a semicolon`() = assertTokenization(
        input = "&uscr",
        expectedTokens = listOf(
            *chars("&uscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity uscr with a semicolon`() = assertTokenization(
        input = "&uscr;",
        expectedTokens = listOf(
            *chars("𝓊").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity utdot without a semicolon`() = assertTokenization(
        input = "&utdot",
        expectedTokens = listOf(
            *chars("&utdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity utdot with a semicolon`() = assertTokenization(
        input = "&utdot;",
        expectedTokens = listOf(
            char('⋰')
        )
    )

    @Test
    fun `Bad named entity utilde without a semicolon`() = assertTokenization(
        input = "&utilde",
        expectedTokens = listOf(
            *chars("&utilde").toTypedArray()
        )
    )

    @Test
    fun `Named entity utilde with a semicolon`() = assertTokenization(
        input = "&utilde;",
        expectedTokens = listOf(
            char('ũ')
        )
    )

    @Test
    fun `Bad named entity utri without a semicolon`() = assertTokenization(
        input = "&utri",
        expectedTokens = listOf(
            *chars("&utri").toTypedArray()
        )
    )

    @Test
    fun `Named entity utri with a semicolon`() = assertTokenization(
        input = "&utri;",
        expectedTokens = listOf(
            char('▵')
        )
    )

    @Test
    fun `Bad named entity utrif without a semicolon`() = assertTokenization(
        input = "&utrif",
        expectedTokens = listOf(
            *chars("&utrif").toTypedArray()
        )
    )

    @Test
    fun `Named entity utrif with a semicolon`() = assertTokenization(
        input = "&utrif;",
        expectedTokens = listOf(
            char('▴')
        )
    )

    @Test
    fun `Bad named entity uuarr without a semicolon`() = assertTokenization(
        input = "&uuarr",
        expectedTokens = listOf(
            *chars("&uuarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity uuarr with a semicolon`() = assertTokenization(
        input = "&uuarr;",
        expectedTokens = listOf(
            char('⇈')
        )
    )

    @Test
    fun `Named entity uuml without a semicolon`() = assertTokenization(
        input = "&uuml",
        expectedTokens = listOf(
            char('ü')
        )
    )

    @Test
    fun `Named entity uuml with a semicolon`() = assertTokenization(
        input = "&uuml;",
        expectedTokens = listOf(
            char('ü')
        )
    )

    @Test
    fun `Bad named entity uwangle without a semicolon`() = assertTokenization(
        input = "&uwangle",
        expectedTokens = listOf(
            *chars("&uwangle").toTypedArray()
        )
    )

    @Test
    fun `Named entity uwangle with a semicolon`() = assertTokenization(
        input = "&uwangle;",
        expectedTokens = listOf(
            char('⦧')
        )
    )

    @Test
    fun `Bad named entity vArr without a semicolon`() = assertTokenization(
        input = "&vArr",
        expectedTokens = listOf(
            *chars("&vArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity vArr with a semicolon`() = assertTokenization(
        input = "&vArr;",
        expectedTokens = listOf(
            char('⇕')
        )
    )

    @Test
    fun `Bad named entity vBar without a semicolon`() = assertTokenization(
        input = "&vBar",
        expectedTokens = listOf(
            *chars("&vBar").toTypedArray()
        )
    )

    @Test
    fun `Named entity vBar with a semicolon`() = assertTokenization(
        input = "&vBar;",
        expectedTokens = listOf(
            char('⫨')
        )
    )

    @Test
    fun `Bad named entity vBarv without a semicolon`() = assertTokenization(
        input = "&vBarv",
        expectedTokens = listOf(
            *chars("&vBarv").toTypedArray()
        )
    )

    @Test
    fun `Named entity vBarv with a semicolon`() = assertTokenization(
        input = "&vBarv;",
        expectedTokens = listOf(
            char('⫩')
        )
    )

    @Test
    fun `Bad named entity vDash without a semicolon`() = assertTokenization(
        input = "&vDash",
        expectedTokens = listOf(
            *chars("&vDash").toTypedArray()
        )
    )

    @Test
    fun `Named entity vDash with a semicolon`() = assertTokenization(
        input = "&vDash;",
        expectedTokens = listOf(
            char('⊨')
        )
    )

    @Test
    fun `Bad named entity vangrt without a semicolon`() = assertTokenization(
        input = "&vangrt",
        expectedTokens = listOf(
            *chars("&vangrt").toTypedArray()
        )
    )

    @Test
    fun `Named entity vangrt with a semicolon`() = assertTokenization(
        input = "&vangrt;",
        expectedTokens = listOf(
            char('⦜')
        )
    )

    @Test
    fun `Bad named entity varepsilon without a semicolon`() = assertTokenization(
        input = "&varepsilon",
        expectedTokens = listOf(
            *chars("&varepsilon").toTypedArray()
        )
    )

    @Test
    fun `Named entity varepsilon with a semicolon`() = assertTokenization(
        input = "&varepsilon;",
        expectedTokens = listOf(
            char('ϵ')
        )
    )

    @Test
    fun `Bad named entity varkappa without a semicolon`() = assertTokenization(
        input = "&varkappa",
        expectedTokens = listOf(
            *chars("&varkappa").toTypedArray()
        )
    )

    @Test
    fun `Named entity varkappa with a semicolon`() = assertTokenization(
        input = "&varkappa;",
        expectedTokens = listOf(
            char('ϰ')
        )
    )

    @Test
    fun `Bad named entity varnothing without a semicolon`() = assertTokenization(
        input = "&varnothing",
        expectedTokens = listOf(
            *chars("&varnothing").toTypedArray()
        )
    )

    @Test
    fun `Named entity varnothing with a semicolon`() = assertTokenization(
        input = "&varnothing;",
        expectedTokens = listOf(
            char('∅')
        )
    )

    @Test
    fun `Bad named entity varphi without a semicolon`() = assertTokenization(
        input = "&varphi",
        expectedTokens = listOf(
            *chars("&varphi").toTypedArray()
        )
    )

    @Test
    fun `Named entity varphi with a semicolon`() = assertTokenization(
        input = "&varphi;",
        expectedTokens = listOf(
            char('ϕ')
        )
    )

    @Test
    fun `Bad named entity varpi without a semicolon`() = assertTokenization(
        input = "&varpi",
        expectedTokens = listOf(
            *chars("&varpi").toTypedArray()
        )
    )

    @Test
    fun `Named entity varpi with a semicolon`() = assertTokenization(
        input = "&varpi;",
        expectedTokens = listOf(
            char('ϖ')
        )
    )

    @Test
    fun `Bad named entity varpropto without a semicolon`() = assertTokenization(
        input = "&varpropto",
        expectedTokens = listOf(
            *chars("&varpropto").toTypedArray()
        )
    )

    @Test
    fun `Named entity varpropto with a semicolon`() = assertTokenization(
        input = "&varpropto;",
        expectedTokens = listOf(
            char('∝')
        )
    )

    @Test
    fun `Bad named entity varr without a semicolon`() = assertTokenization(
        input = "&varr",
        expectedTokens = listOf(
            *chars("&varr").toTypedArray()
        )
    )

    @Test
    fun `Named entity varr with a semicolon`() = assertTokenization(
        input = "&varr;",
        expectedTokens = listOf(
            char('↕')
        )
    )

    @Test
    fun `Bad named entity varrho without a semicolon`() = assertTokenization(
        input = "&varrho",
        expectedTokens = listOf(
            *chars("&varrho").toTypedArray()
        )
    )

    @Test
    fun `Named entity varrho with a semicolon`() = assertTokenization(
        input = "&varrho;",
        expectedTokens = listOf(
            char('ϱ')
        )
    )

    @Test
    fun `Bad named entity varsigma without a semicolon`() = assertTokenization(
        input = "&varsigma",
        expectedTokens = listOf(
            *chars("&varsigma").toTypedArray()
        )
    )

    @Test
    fun `Named entity varsigma with a semicolon`() = assertTokenization(
        input = "&varsigma;",
        expectedTokens = listOf(
            char('ς')
        )
    )

    @Test
    fun `Bad named entity varsubsetneq without a semicolon`() = assertTokenization(
        input = "&varsubsetneq",
        expectedTokens = listOf(
            *chars("&varsubsetneq").toTypedArray()
        )
    )

    @Test
    fun `Named entity varsubsetneq with a semicolon`() = assertTokenization(
        input = "&varsubsetneq;",
        expectedTokens = listOf(
            *chars("⊊︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity varsubsetneqq without a semicolon`() = assertTokenization(
        input = "&varsubsetneqq",
        expectedTokens = listOf(
            *chars("&varsubsetneqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity varsubsetneqq with a semicolon`() = assertTokenization(
        input = "&varsubsetneqq;",
        expectedTokens = listOf(
            *chars("⫋︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity varsupsetneq without a semicolon`() = assertTokenization(
        input = "&varsupsetneq",
        expectedTokens = listOf(
            *chars("&varsupsetneq").toTypedArray()
        )
    )

    @Test
    fun `Named entity varsupsetneq with a semicolon`() = assertTokenization(
        input = "&varsupsetneq;",
        expectedTokens = listOf(
            *chars("⊋︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity varsupsetneqq without a semicolon`() = assertTokenization(
        input = "&varsupsetneqq",
        expectedTokens = listOf(
            *chars("&varsupsetneqq").toTypedArray()
        )
    )

    @Test
    fun `Named entity varsupsetneqq with a semicolon`() = assertTokenization(
        input = "&varsupsetneqq;",
        expectedTokens = listOf(
            *chars("⫌︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity vartheta without a semicolon`() = assertTokenization(
        input = "&vartheta",
        expectedTokens = listOf(
            *chars("&vartheta").toTypedArray()
        )
    )

    @Test
    fun `Named entity vartheta with a semicolon`() = assertTokenization(
        input = "&vartheta;",
        expectedTokens = listOf(
            char('ϑ')
        )
    )

    @Test
    fun `Bad named entity vartriangleleft without a semicolon`() = assertTokenization(
        input = "&vartriangleleft",
        expectedTokens = listOf(
            *chars("&vartriangleleft").toTypedArray()
        )
    )

    @Test
    fun `Named entity vartriangleleft with a semicolon`() = assertTokenization(
        input = "&vartriangleleft;",
        expectedTokens = listOf(
            char('⊲')
        )
    )

    @Test
    fun `Bad named entity vartriangleright without a semicolon`() = assertTokenization(
        input = "&vartriangleright",
        expectedTokens = listOf(
            *chars("&vartriangleright").toTypedArray()
        )
    )

    @Test
    fun `Named entity vartriangleright with a semicolon`() = assertTokenization(
        input = "&vartriangleright;",
        expectedTokens = listOf(
            char('⊳')
        )
    )

    @Test
    fun `Bad named entity vcy without a semicolon`() = assertTokenization(
        input = "&vcy",
        expectedTokens = listOf(
            *chars("&vcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity vcy with a semicolon`() = assertTokenization(
        input = "&vcy;",
        expectedTokens = listOf(
            char('в')
        )
    )

    @Test
    fun `Bad named entity vdash without a semicolon`() = assertTokenization(
        input = "&vdash",
        expectedTokens = listOf(
            *chars("&vdash").toTypedArray()
        )
    )

    @Test
    fun `Named entity vdash with a semicolon`() = assertTokenization(
        input = "&vdash;",
        expectedTokens = listOf(
            char('⊢')
        )
    )

    @Test
    fun `Bad named entity vee without a semicolon`() = assertTokenization(
        input = "&vee",
        expectedTokens = listOf(
            *chars("&vee").toTypedArray()
        )
    )

    @Test
    fun `Named entity vee with a semicolon`() = assertTokenization(
        input = "&vee;",
        expectedTokens = listOf(
            char('∨')
        )
    )

    @Test
    fun `Bad named entity veebar without a semicolon`() = assertTokenization(
        input = "&veebar",
        expectedTokens = listOf(
            *chars("&veebar").toTypedArray()
        )
    )

    @Test
    fun `Named entity veebar with a semicolon`() = assertTokenization(
        input = "&veebar;",
        expectedTokens = listOf(
            char('⊻')
        )
    )

    @Test
    fun `Bad named entity veeeq without a semicolon`() = assertTokenization(
        input = "&veeeq",
        expectedTokens = listOf(
            *chars("&veeeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity veeeq with a semicolon`() = assertTokenization(
        input = "&veeeq;",
        expectedTokens = listOf(
            char('≚')
        )
    )

    @Test
    fun `Bad named entity vellip without a semicolon`() = assertTokenization(
        input = "&vellip",
        expectedTokens = listOf(
            *chars("&vellip").toTypedArray()
        )
    )

    @Test
    fun `Named entity vellip with a semicolon`() = assertTokenization(
        input = "&vellip;",
        expectedTokens = listOf(
            char('⋮')
        )
    )

    @Test
    fun `Bad named entity verbar without a semicolon`() = assertTokenization(
        input = "&verbar",
        expectedTokens = listOf(
            *chars("&verbar").toTypedArray()
        )
    )

    @Test
    fun `Named entity verbar with a semicolon`() = assertTokenization(
        input = "&verbar;",
        expectedTokens = listOf(
            char('|')
        )
    )

    @Test
    fun `Bad named entity vert without a semicolon`() = assertTokenization(
        input = "&vert",
        expectedTokens = listOf(
            *chars("&vert").toTypedArray()
        )
    )

    @Test
    fun `Named entity vert with a semicolon`() = assertTokenization(
        input = "&vert;",
        expectedTokens = listOf(
            char('|')
        )
    )

    @Test
    fun `Bad named entity vfr without a semicolon`() = assertTokenization(
        input = "&vfr",
        expectedTokens = listOf(
            *chars("&vfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity vfr with a semicolon`() = assertTokenization(
        input = "&vfr;",
        expectedTokens = listOf(
            *chars("𝔳").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity vltri without a semicolon`() = assertTokenization(
        input = "&vltri",
        expectedTokens = listOf(
            *chars("&vltri").toTypedArray()
        )
    )

    @Test
    fun `Named entity vltri with a semicolon`() = assertTokenization(
        input = "&vltri;",
        expectedTokens = listOf(
            char('⊲')
        )
    )

    @Test
    fun `Bad named entity vnsub without a semicolon`() = assertTokenization(
        input = "&vnsub",
        expectedTokens = listOf(
            *chars("&vnsub").toTypedArray()
        )
    )

    @Test
    fun `Named entity vnsub with a semicolon`() = assertTokenization(
        input = "&vnsub;",
        expectedTokens = listOf(
            *chars("⊂⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity vnsup without a semicolon`() = assertTokenization(
        input = "&vnsup",
        expectedTokens = listOf(
            *chars("&vnsup").toTypedArray()
        )
    )

    @Test
    fun `Named entity vnsup with a semicolon`() = assertTokenization(
        input = "&vnsup;",
        expectedTokens = listOf(
            *chars("⊃⃒").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity vopf without a semicolon`() = assertTokenization(
        input = "&vopf",
        expectedTokens = listOf(
            *chars("&vopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity vopf with a semicolon`() = assertTokenization(
        input = "&vopf;",
        expectedTokens = listOf(
            *chars("𝕧").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity vprop without a semicolon`() = assertTokenization(
        input = "&vprop",
        expectedTokens = listOf(
            *chars("&vprop").toTypedArray()
        )
    )

    @Test
    fun `Named entity vprop with a semicolon`() = assertTokenization(
        input = "&vprop;",
        expectedTokens = listOf(
            char('∝')
        )
    )

    @Test
    fun `Bad named entity vrtri without a semicolon`() = assertTokenization(
        input = "&vrtri",
        expectedTokens = listOf(
            *chars("&vrtri").toTypedArray()
        )
    )

    @Test
    fun `Named entity vrtri with a semicolon`() = assertTokenization(
        input = "&vrtri;",
        expectedTokens = listOf(
            char('⊳')
        )
    )

    @Test
    fun `Bad named entity vscr without a semicolon`() = assertTokenization(
        input = "&vscr",
        expectedTokens = listOf(
            *chars("&vscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity vscr with a semicolon`() = assertTokenization(
        input = "&vscr;",
        expectedTokens = listOf(
            *chars("𝓋").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity vsubnE without a semicolon`() = assertTokenization(
        input = "&vsubnE",
        expectedTokens = listOf(
            *chars("&vsubnE").toTypedArray()
        )
    )

    @Test
    fun `Named entity vsubnE with a semicolon`() = assertTokenization(
        input = "&vsubnE;",
        expectedTokens = listOf(
            *chars("⫋︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity vsubne without a semicolon`() = assertTokenization(
        input = "&vsubne",
        expectedTokens = listOf(
            *chars("&vsubne").toTypedArray()
        )
    )

    @Test
    fun `Named entity vsubne with a semicolon`() = assertTokenization(
        input = "&vsubne;",
        expectedTokens = listOf(
            *chars("⊊︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity vsupnE without a semicolon`() = assertTokenization(
        input = "&vsupnE",
        expectedTokens = listOf(
            *chars("&vsupnE").toTypedArray()
        )
    )

    @Test
    fun `Named entity vsupnE with a semicolon`() = assertTokenization(
        input = "&vsupnE;",
        expectedTokens = listOf(
            *chars("⫌︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity vsupne without a semicolon`() = assertTokenization(
        input = "&vsupne",
        expectedTokens = listOf(
            *chars("&vsupne").toTypedArray()
        )
    )

    @Test
    fun `Named entity vsupne with a semicolon`() = assertTokenization(
        input = "&vsupne;",
        expectedTokens = listOf(
            *chars("⊋︀").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity vzigzag without a semicolon`() = assertTokenization(
        input = "&vzigzag",
        expectedTokens = listOf(
            *chars("&vzigzag").toTypedArray()
        )
    )

    @Test
    fun `Named entity vzigzag with a semicolon`() = assertTokenization(
        input = "&vzigzag;",
        expectedTokens = listOf(
            char('⦚')
        )
    )

    @Test
    fun `Bad named entity wcirc without a semicolon`() = assertTokenization(
        input = "&wcirc",
        expectedTokens = listOf(
            *chars("&wcirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity wcirc with a semicolon`() = assertTokenization(
        input = "&wcirc;",
        expectedTokens = listOf(
            char('ŵ')
        )
    )

    @Test
    fun `Bad named entity wedbar without a semicolon`() = assertTokenization(
        input = "&wedbar",
        expectedTokens = listOf(
            *chars("&wedbar").toTypedArray()
        )
    )

    @Test
    fun `Named entity wedbar with a semicolon`() = assertTokenization(
        input = "&wedbar;",
        expectedTokens = listOf(
            char('⩟')
        )
    )

    @Test
    fun `Bad named entity wedge without a semicolon`() = assertTokenization(
        input = "&wedge",
        expectedTokens = listOf(
            *chars("&wedge").toTypedArray()
        )
    )

    @Test
    fun `Named entity wedge with a semicolon`() = assertTokenization(
        input = "&wedge;",
        expectedTokens = listOf(
            char('∧')
        )
    )

    @Test
    fun `Bad named entity wedgeq without a semicolon`() = assertTokenization(
        input = "&wedgeq",
        expectedTokens = listOf(
            *chars("&wedgeq").toTypedArray()
        )
    )

    @Test
    fun `Named entity wedgeq with a semicolon`() = assertTokenization(
        input = "&wedgeq;",
        expectedTokens = listOf(
            char('≙')
        )
    )

    @Test
    fun `Bad named entity weierp without a semicolon`() = assertTokenization(
        input = "&weierp",
        expectedTokens = listOf(
            *chars("&weierp").toTypedArray()
        )
    )

    @Test
    fun `Named entity weierp with a semicolon`() = assertTokenization(
        input = "&weierp;",
        expectedTokens = listOf(
            char('℘')
        )
    )

    @Test
    fun `Bad named entity wfr without a semicolon`() = assertTokenization(
        input = "&wfr",
        expectedTokens = listOf(
            *chars("&wfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity wfr with a semicolon`() = assertTokenization(
        input = "&wfr;",
        expectedTokens = listOf(
            *chars("𝔴").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity wopf without a semicolon`() = assertTokenization(
        input = "&wopf",
        expectedTokens = listOf(
            *chars("&wopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity wopf with a semicolon`() = assertTokenization(
        input = "&wopf;",
        expectedTokens = listOf(
            *chars("𝕨").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity wp without a semicolon`() = assertTokenization(
        input = "&wp",
        expectedTokens = listOf(
            *chars("&wp").toTypedArray()
        )
    )

    @Test
    fun `Named entity wp with a semicolon`() = assertTokenization(
        input = "&wp;",
        expectedTokens = listOf(
            char('℘')
        )
    )

    @Test
    fun `Bad named entity wr without a semicolon`() = assertTokenization(
        input = "&wr",
        expectedTokens = listOf(
            *chars("&wr").toTypedArray()
        )
    )

    @Test
    fun `Named entity wr with a semicolon`() = assertTokenization(
        input = "&wr;",
        expectedTokens = listOf(
            char('≀')
        )
    )

    @Test
    fun `Bad named entity wreath without a semicolon`() = assertTokenization(
        input = "&wreath",
        expectedTokens = listOf(
            *chars("&wreath").toTypedArray()
        )
    )

    @Test
    fun `Named entity wreath with a semicolon`() = assertTokenization(
        input = "&wreath;",
        expectedTokens = listOf(
            char('≀')
        )
    )

    @Test
    fun `Bad named entity wscr without a semicolon`() = assertTokenization(
        input = "&wscr",
        expectedTokens = listOf(
            *chars("&wscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity wscr with a semicolon`() = assertTokenization(
        input = "&wscr;",
        expectedTokens = listOf(
            *chars("𝓌").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity xcap without a semicolon`() = assertTokenization(
        input = "&xcap",
        expectedTokens = listOf(
            *chars("&xcap").toTypedArray()
        )
    )

    @Test
    fun `Named entity xcap with a semicolon`() = assertTokenization(
        input = "&xcap;",
        expectedTokens = listOf(
            char('⋂')
        )
    )

    @Test
    fun `Bad named entity xcirc without a semicolon`() = assertTokenization(
        input = "&xcirc",
        expectedTokens = listOf(
            *chars("&xcirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity xcirc with a semicolon`() = assertTokenization(
        input = "&xcirc;",
        expectedTokens = listOf(
            char('◯')
        )
    )

    @Test
    fun `Bad named entity xcup without a semicolon`() = assertTokenization(
        input = "&xcup",
        expectedTokens = listOf(
            *chars("&xcup").toTypedArray()
        )
    )

    @Test
    fun `Named entity xcup with a semicolon`() = assertTokenization(
        input = "&xcup;",
        expectedTokens = listOf(
            char('⋃')
        )
    )

    @Test
    fun `Bad named entity xdtri without a semicolon`() = assertTokenization(
        input = "&xdtri",
        expectedTokens = listOf(
            *chars("&xdtri").toTypedArray()
        )
    )

    @Test
    fun `Named entity xdtri with a semicolon`() = assertTokenization(
        input = "&xdtri;",
        expectedTokens = listOf(
            char('▽')
        )
    )

    @Test
    fun `Bad named entity xfr without a semicolon`() = assertTokenization(
        input = "&xfr",
        expectedTokens = listOf(
            *chars("&xfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity xfr with a semicolon`() = assertTokenization(
        input = "&xfr;",
        expectedTokens = listOf(
            *chars("𝔵").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity xhArr without a semicolon`() = assertTokenization(
        input = "&xhArr",
        expectedTokens = listOf(
            *chars("&xhArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity xhArr with a semicolon`() = assertTokenization(
        input = "&xhArr;",
        expectedTokens = listOf(
            char('⟺')
        )
    )

    @Test
    fun `Bad named entity xharr without a semicolon`() = assertTokenization(
        input = "&xharr",
        expectedTokens = listOf(
            *chars("&xharr").toTypedArray()
        )
    )

    @Test
    fun `Named entity xharr with a semicolon`() = assertTokenization(
        input = "&xharr;",
        expectedTokens = listOf(
            char('⟷')
        )
    )

    @Test
    fun `Bad named entity xi without a semicolon`() = assertTokenization(
        input = "&xi",
        expectedTokens = listOf(
            *chars("&xi").toTypedArray()
        )
    )

    @Test
    fun `Named entity xi with a semicolon`() = assertTokenization(
        input = "&xi;",
        expectedTokens = listOf(
            char('ξ')
        )
    )

    @Test
    fun `Bad named entity xlArr without a semicolon`() = assertTokenization(
        input = "&xlArr",
        expectedTokens = listOf(
            *chars("&xlArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity xlArr with a semicolon`() = assertTokenization(
        input = "&xlArr;",
        expectedTokens = listOf(
            char('⟸')
        )
    )

    @Test
    fun `Bad named entity xlarr without a semicolon`() = assertTokenization(
        input = "&xlarr",
        expectedTokens = listOf(
            *chars("&xlarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity xlarr with a semicolon`() = assertTokenization(
        input = "&xlarr;",
        expectedTokens = listOf(
            char('⟵')
        )
    )

    @Test
    fun `Bad named entity xmap without a semicolon`() = assertTokenization(
        input = "&xmap",
        expectedTokens = listOf(
            *chars("&xmap").toTypedArray()
        )
    )

    @Test
    fun `Named entity xmap with a semicolon`() = assertTokenization(
        input = "&xmap;",
        expectedTokens = listOf(
            char('⟼')
        )
    )

    @Test
    fun `Bad named entity xnis without a semicolon`() = assertTokenization(
        input = "&xnis",
        expectedTokens = listOf(
            *chars("&xnis").toTypedArray()
        )
    )

    @Test
    fun `Named entity xnis with a semicolon`() = assertTokenization(
        input = "&xnis;",
        expectedTokens = listOf(
            char('⋻')
        )
    )

    @Test
    fun `Bad named entity xodot without a semicolon`() = assertTokenization(
        input = "&xodot",
        expectedTokens = listOf(
            *chars("&xodot").toTypedArray()
        )
    )

    @Test
    fun `Named entity xodot with a semicolon`() = assertTokenization(
        input = "&xodot;",
        expectedTokens = listOf(
            char('⨀')
        )
    )

    @Test
    fun `Bad named entity xopf without a semicolon`() = assertTokenization(
        input = "&xopf",
        expectedTokens = listOf(
            *chars("&xopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity xopf with a semicolon`() = assertTokenization(
        input = "&xopf;",
        expectedTokens = listOf(
            *chars("𝕩").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity xoplus without a semicolon`() = assertTokenization(
        input = "&xoplus",
        expectedTokens = listOf(
            *chars("&xoplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity xoplus with a semicolon`() = assertTokenization(
        input = "&xoplus;",
        expectedTokens = listOf(
            char('⨁')
        )
    )

    @Test
    fun `Bad named entity xotime without a semicolon`() = assertTokenization(
        input = "&xotime",
        expectedTokens = listOf(
            *chars("&xotime").toTypedArray()
        )
    )

    @Test
    fun `Named entity xotime with a semicolon`() = assertTokenization(
        input = "&xotime;",
        expectedTokens = listOf(
            char('⨂')
        )
    )

    @Test
    fun `Bad named entity xrArr without a semicolon`() = assertTokenization(
        input = "&xrArr",
        expectedTokens = listOf(
            *chars("&xrArr").toTypedArray()
        )
    )

    @Test
    fun `Named entity xrArr with a semicolon`() = assertTokenization(
        input = "&xrArr;",
        expectedTokens = listOf(
            char('⟹')
        )
    )

    @Test
    fun `Bad named entity xrarr without a semicolon`() = assertTokenization(
        input = "&xrarr",
        expectedTokens = listOf(
            *chars("&xrarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity xrarr with a semicolon`() = assertTokenization(
        input = "&xrarr;",
        expectedTokens = listOf(
            char('⟶')
        )
    )

    @Test
    fun `Bad named entity xscr without a semicolon`() = assertTokenization(
        input = "&xscr",
        expectedTokens = listOf(
            *chars("&xscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity xscr with a semicolon`() = assertTokenization(
        input = "&xscr;",
        expectedTokens = listOf(
            *chars("𝓍").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity xsqcup without a semicolon`() = assertTokenization(
        input = "&xsqcup",
        expectedTokens = listOf(
            *chars("&xsqcup").toTypedArray()
        )
    )

    @Test
    fun `Named entity xsqcup with a semicolon`() = assertTokenization(
        input = "&xsqcup;",
        expectedTokens = listOf(
            char('⨆')
        )
    )

    @Test
    fun `Bad named entity xuplus without a semicolon`() = assertTokenization(
        input = "&xuplus",
        expectedTokens = listOf(
            *chars("&xuplus").toTypedArray()
        )
    )

    @Test
    fun `Named entity xuplus with a semicolon`() = assertTokenization(
        input = "&xuplus;",
        expectedTokens = listOf(
            char('⨄')
        )
    )

    @Test
    fun `Bad named entity xutri without a semicolon`() = assertTokenization(
        input = "&xutri",
        expectedTokens = listOf(
            *chars("&xutri").toTypedArray()
        )
    )

    @Test
    fun `Named entity xutri with a semicolon`() = assertTokenization(
        input = "&xutri;",
        expectedTokens = listOf(
            char('△')
        )
    )

    @Test
    fun `Bad named entity xvee without a semicolon`() = assertTokenization(
        input = "&xvee",
        expectedTokens = listOf(
            *chars("&xvee").toTypedArray()
        )
    )

    @Test
    fun `Named entity xvee with a semicolon`() = assertTokenization(
        input = "&xvee;",
        expectedTokens = listOf(
            char('⋁')
        )
    )

    @Test
    fun `Bad named entity xwedge without a semicolon`() = assertTokenization(
        input = "&xwedge",
        expectedTokens = listOf(
            *chars("&xwedge").toTypedArray()
        )
    )

    @Test
    fun `Named entity xwedge with a semicolon`() = assertTokenization(
        input = "&xwedge;",
        expectedTokens = listOf(
            char('⋀')
        )
    )

    @Test
    fun `Named entity yacute without a semicolon`() = assertTokenization(
        input = "&yacute",
        expectedTokens = listOf(
            char('ý')
        )
    )

    @Test
    fun `Named entity yacute with a semicolon`() = assertTokenization(
        input = "&yacute;",
        expectedTokens = listOf(
            char('ý')
        )
    )

    @Test
    fun `Bad named entity yacy without a semicolon`() = assertTokenization(
        input = "&yacy",
        expectedTokens = listOf(
            *chars("&yacy").toTypedArray()
        )
    )

    @Test
    fun `Named entity yacy with a semicolon`() = assertTokenization(
        input = "&yacy;",
        expectedTokens = listOf(
            char('я')
        )
    )

    @Test
    fun `Bad named entity ycirc without a semicolon`() = assertTokenization(
        input = "&ycirc",
        expectedTokens = listOf(
            *chars("&ycirc").toTypedArray()
        )
    )

    @Test
    fun `Named entity ycirc with a semicolon`() = assertTokenization(
        input = "&ycirc;",
        expectedTokens = listOf(
            char('ŷ')
        )
    )

    @Test
    fun `Bad named entity ycy without a semicolon`() = assertTokenization(
        input = "&ycy",
        expectedTokens = listOf(
            *chars("&ycy").toTypedArray()
        )
    )

    @Test
    fun `Named entity ycy with a semicolon`() = assertTokenization(
        input = "&ycy;",
        expectedTokens = listOf(
            char('ы')
        )
    )

    @Test
    fun `Named entity yen without a semicolon`() = assertTokenization(
        input = "&yen",
        expectedTokens = listOf(
            char('¥')
        )
    )

    @Test
    fun `Named entity yen with a semicolon`() = assertTokenization(
        input = "&yen;",
        expectedTokens = listOf(
            char('¥')
        )
    )

    @Test
    fun `Bad named entity yfr without a semicolon`() = assertTokenization(
        input = "&yfr",
        expectedTokens = listOf(
            *chars("&yfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity yfr with a semicolon`() = assertTokenization(
        input = "&yfr;",
        expectedTokens = listOf(
            *chars("𝔶").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity yicy without a semicolon`() = assertTokenization(
        input = "&yicy",
        expectedTokens = listOf(
            *chars("&yicy").toTypedArray()
        )
    )

    @Test
    fun `Named entity yicy with a semicolon`() = assertTokenization(
        input = "&yicy;",
        expectedTokens = listOf(
            char('ї')
        )
    )

    @Test
    fun `Bad named entity yopf without a semicolon`() = assertTokenization(
        input = "&yopf",
        expectedTokens = listOf(
            *chars("&yopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity yopf with a semicolon`() = assertTokenization(
        input = "&yopf;",
        expectedTokens = listOf(
            *chars("𝕪").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity yscr without a semicolon`() = assertTokenization(
        input = "&yscr",
        expectedTokens = listOf(
            *chars("&yscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity yscr with a semicolon`() = assertTokenization(
        input = "&yscr;",
        expectedTokens = listOf(
            *chars("𝓎").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity yucy without a semicolon`() = assertTokenization(
        input = "&yucy",
        expectedTokens = listOf(
            *chars("&yucy").toTypedArray()
        )
    )

    @Test
    fun `Named entity yucy with a semicolon`() = assertTokenization(
        input = "&yucy;",
        expectedTokens = listOf(
            char('ю')
        )
    )

    @Test
    fun `Named entity yuml without a semicolon`() = assertTokenization(
        input = "&yuml",
        expectedTokens = listOf(
            char('ÿ')
        )
    )

    @Test
    fun `Named entity yuml with a semicolon`() = assertTokenization(
        input = "&yuml;",
        expectedTokens = listOf(
            char('ÿ')
        )
    )

    @Test
    fun `Bad named entity zacute without a semicolon`() = assertTokenization(
        input = "&zacute",
        expectedTokens = listOf(
            *chars("&zacute").toTypedArray()
        )
    )

    @Test
    fun `Named entity zacute with a semicolon`() = assertTokenization(
        input = "&zacute;",
        expectedTokens = listOf(
            char('ź')
        )
    )

    @Test
    fun `Bad named entity zcaron without a semicolon`() = assertTokenization(
        input = "&zcaron",
        expectedTokens = listOf(
            *chars("&zcaron").toTypedArray()
        )
    )

    @Test
    fun `Named entity zcaron with a semicolon`() = assertTokenization(
        input = "&zcaron;",
        expectedTokens = listOf(
            char('ž')
        )
    )

    @Test
    fun `Bad named entity zcy without a semicolon`() = assertTokenization(
        input = "&zcy",
        expectedTokens = listOf(
            *chars("&zcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity zcy with a semicolon`() = assertTokenization(
        input = "&zcy;",
        expectedTokens = listOf(
            char('з')
        )
    )

    @Test
    fun `Bad named entity zdot without a semicolon`() = assertTokenization(
        input = "&zdot",
        expectedTokens = listOf(
            *chars("&zdot").toTypedArray()
        )
    )

    @Test
    fun `Named entity zdot with a semicolon`() = assertTokenization(
        input = "&zdot;",
        expectedTokens = listOf(
            char('ż')
        )
    )

    @Test
    fun `Bad named entity zeetrf without a semicolon`() = assertTokenization(
        input = "&zeetrf",
        expectedTokens = listOf(
            *chars("&zeetrf").toTypedArray()
        )
    )

    @Test
    fun `Named entity zeetrf with a semicolon`() = assertTokenization(
        input = "&zeetrf;",
        expectedTokens = listOf(
            char('ℨ')
        )
    )

    @Test
    fun `Bad named entity zeta without a semicolon`() = assertTokenization(
        input = "&zeta",
        expectedTokens = listOf(
            *chars("&zeta").toTypedArray()
        )
    )

    @Test
    fun `Named entity zeta with a semicolon`() = assertTokenization(
        input = "&zeta;",
        expectedTokens = listOf(
            char('ζ')
        )
    )

    @Test
    fun `Bad named entity zfr without a semicolon`() = assertTokenization(
        input = "&zfr",
        expectedTokens = listOf(
            *chars("&zfr").toTypedArray()
        )
    )

    @Test
    fun `Named entity zfr with a semicolon`() = assertTokenization(
        input = "&zfr;",
        expectedTokens = listOf(
            *chars("𝔷").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity zhcy without a semicolon`() = assertTokenization(
        input = "&zhcy",
        expectedTokens = listOf(
            *chars("&zhcy").toTypedArray()
        )
    )

    @Test
    fun `Named entity zhcy with a semicolon`() = assertTokenization(
        input = "&zhcy;",
        expectedTokens = listOf(
            char('ж')
        )
    )

    @Test
    fun `Bad named entity zigrarr without a semicolon`() = assertTokenization(
        input = "&zigrarr",
        expectedTokens = listOf(
            *chars("&zigrarr").toTypedArray()
        )
    )

    @Test
    fun `Named entity zigrarr with a semicolon`() = assertTokenization(
        input = "&zigrarr;",
        expectedTokens = listOf(
            char('⇝')
        )
    )

    @Test
    fun `Bad named entity zopf without a semicolon`() = assertTokenization(
        input = "&zopf",
        expectedTokens = listOf(
            *chars("&zopf").toTypedArray()
        )
    )

    @Test
    fun `Named entity zopf with a semicolon`() = assertTokenization(
        input = "&zopf;",
        expectedTokens = listOf(
            *chars("𝕫").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity zscr without a semicolon`() = assertTokenization(
        input = "&zscr",
        expectedTokens = listOf(
            *chars("&zscr").toTypedArray()
        )
    )

    @Test
    fun `Named entity zscr with a semicolon`() = assertTokenization(
        input = "&zscr;",
        expectedTokens = listOf(
            *chars("𝓏").toTypedArray()
        )
    )

    @Test
    fun `Bad named entity zwj without a semicolon`() = assertTokenization(
        input = "&zwj",
        expectedTokens = listOf(
            *chars("&zwj").toTypedArray()
        )
    )

    @Test
    fun `Named entity zwj with a semicolon`() = assertTokenization(
        input = "&zwj;",
        expectedTokens = listOf(
            char('‍')
        )
    )

    @Test
    fun `Bad named entity zwnj without a semicolon`() = assertTokenization(
        input = "&zwnj",
        expectedTokens = listOf(
            *chars("&zwnj").toTypedArray()
        )
    )

    @Test
    fun `Named entity zwnj with a semicolon`() = assertTokenization(
        input = "&zwnj;",
        expectedTokens = listOf(
            char('‌')
        )
    )
}