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

package com.theakram.helium.treebuilder.modes

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tree.QuirksMode
import com.theakram.helium.tree.TreeNode
import com.theakram.helium.treebuilder.InsertionLocation
import com.theakram.helium.treebuilder.InsertionMode
import com.theakram.helium.treebuilder.InsertionModeTransition
import com.theakram.helium.treebuilder.TreeBuilder
import com.theakram.helium.treebuilder.continueInCurrentMode
import com.theakram.helium.treebuilder.reprocessIn
import com.theakram.helium.treebuilder.switchTo
import com.theakram.helium.util.isAsciiWhitespace
import com.theakram.helium.util.equalsAsciiCaseInsensitive
import com.theakram.helium.util.startsWithAsciiCaseInsensitive

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#the-initial-insertion-mode
 */
internal object Initial : InsertionMode {
    override fun process(token: Token, treeBuilder: TreeBuilder): InsertionModeTransition {
        return when (token) {
            is Token.Character if token.codePoint.isAsciiWhitespace() -> continueInCurrentMode()

            is Token.Comment -> {
                treeBuilder.insertComment(token.data, InsertionLocation(treeBuilder.document, null))
                continueInCurrentMode()
            }

            is Token.ProcessingInstruction -> {
                treeBuilder.insertProcessingInstruction(token.target, token.data, InsertionLocation(treeBuilder.document, null))
                continueInCurrentMode()
            }

            is Token.DocType -> {
                treeBuilder.insertDoctype(
                    name = token.name ?: "",
                    publicId = token.publicId ?: "",
                    systemId = token.systemId ?: ""
                )

                if (!treeBuilder.document.isIframeSrcdoc && !treeBuilder.document.parserCannotChangeMode) {
                    val quirksMode = determineQuirksMode(token)
                    treeBuilder.document.quirksMode = quirksMode
                }

                switchTo(BeforeHTML)
            }

            is Token.EOF -> {
                // Parse error
                if (!treeBuilder.document.isIframeSrcdoc) {
                    if (!treeBuilder.document.parserCannotChangeMode) {
                        treeBuilder.document.quirksMode = QuirksMode.QUIRKS
                    }
                }
                reprocessIn(BeforeHTML)
            }

            else -> {
                if (!treeBuilder.document.isIframeSrcdoc) {
                    // Parse error (not tracked yet)
                    if (!treeBuilder.document.parserCannotChangeMode) {
                        treeBuilder.document.quirksMode = QuirksMode.QUIRKS
                    }
                }
                reprocessIn(BeforeHTML)
            }
        }
    }

    private fun determineQuirksMode(token: Token.DocType): QuirksMode = when {
        shouldUseQuirksMode(token) -> QuirksMode.QUIRKS
        shouldUseLimitedQuirksMode(token) -> QuirksMode.LIMITED_QUIRKS
        else -> QuirksMode.NO_QUIRKS
    }

    private fun shouldUseQuirksMode(token: Token.DocType): Boolean {
        val name = token.name.orEmpty()
        val publicId = token.publicId.orEmpty()
        val systemId = token.systemId.orEmpty()

        return token.forceQuirks || name != "html"
                || QUIRKS_MODE_EXACT_PUBLIC_IDS.any { publicId.equalsAsciiCaseInsensitive(it) } || systemId.equalsAsciiCaseInsensitive("http://www.ibm.com/data/dtd/v11/ibmxhtml1-transitional.dtd") || QUIRKS_MODE_PUBLIC_ID_PREFIXES.any { publicId.startsWithAsciiCaseInsensitive(it) } ||
                systemId.isEmpty() && QUIRKS_MODE_SYSTEM_ID_MISSING_PREFIXES.any {
            publicId.startsWithAsciiCaseInsensitive(it)
        }
    }

    private fun shouldUseLimitedQuirksMode(token: Token.DocType): Boolean {
        val publicId = token.publicId.orEmpty()
        val systemId = token.systemId.orEmpty()

        return LIMITED_QUIRKS_MODE_PREFIXES.any { publicId.startsWithAsciiCaseInsensitive(it) }
                || systemId.isNotEmpty() && LIMITED_QUIRKS_SYSTEM_ID_NOT_MISSING_PREFIXES.any {
            publicId.startsWithAsciiCaseInsensitive(it)
        }
    }

    private val QUIRKS_MODE_EXACT_PUBLIC_IDS = setOf(
        "-//W3O//DTD W3 HTML Strict 3.0//EN//",
        "-/W3C/DTD HTML 4.0 Transitional/EN",
        "HTML"
    )

    private val QUIRKS_MODE_PUBLIC_ID_PREFIXES = listOf(
        "+//Silmaril//dtd html Pro v0r11 19970101//",
        "-//AS//DTD HTML 3.0 asWedit + extensions//",
        "-//AdvaSoft Ltd//DTD HTML 3.0 asWedit + extensions//",
        "-//IETF//DTD HTML 2.0 Level 1//",
        "-//IETF//DTD HTML 2.0 Level 2//",
        "-//IETF//DTD HTML 2.0 Strict Level 1//",
        "-//IETF//DTD HTML 2.0 Strict Level 2//",
        "-//IETF//DTD HTML 2.0 Strict//",
        "-//IETF//DTD HTML 2.0//",
        "-//IETF//DTD HTML 2.1E//",
        "-//IETF//DTD HTML 3.0//",
        "-//IETF//DTD HTML 3.2 Final//",
        "-//IETF//DTD HTML 3.2//",
        "-//IETF//DTD HTML 3//",
        "-//IETF//DTD HTML Level 0//",
        "-//IETF//DTD HTML Level 1//",
        "-//IETF//DTD HTML Level 2//",
        "-//IETF//DTD HTML Level 3//",
        "-//IETF//DTD HTML Strict Level 0//",
        "-//IETF//DTD HTML Strict Level 1//",
        "-//IETF//DTD HTML Strict Level 2//",
        "-//IETF//DTD HTML Strict Level 3//",
        "-//IETF//DTD HTML Strict//",
        "-//IETF//DTD HTML//",
        "-//Metrius//DTD Metrius Presentational//",
        "-//Microsoft//DTD Internet Explorer 2.0 HTML Strict//",
        "-//Microsoft//DTD Internet Explorer 2.0 HTML//",
        "-//Microsoft//DTD Internet Explorer 2.0 Tables//",
        "-//Microsoft//DTD Internet Explorer 3.0 HTML Strict//",
        "-//Microsoft//DTD Internet Explorer 3.0 HTML//",
        "-//Microsoft//DTD Internet Explorer 3.0 Tables//",
        "-//Netscape Comm. Corp.//DTD HTML//",
        "-//Netscape Comm. Corp.//DTD Strict HTML//",
        "-//O'Reilly and Associates//DTD HTML 2.0//",
        "-//O'Reilly and Associates//DTD HTML Extended 1.0//",
        "-//O'Reilly and Associates//DTD HTML Extended Relaxed 1.0//",
        "-//SQ//DTD HTML 2.0 HoTMetaL + extensions//",
        "-//SoftQuad Software//DTD HoTMetaL PRO 6.0::19990601::extensions to HTML 4.0//",
        "-//SoftQuad//DTD HoTMetaL PRO 4.0::19971010::extensions to HTML 4.0//",
        "-//Spyglass//DTD HTML 2.0 Extended//",
        "-//Sun Microsystems Corp.//DTD HotJava HTML//",
        "-//Sun Microsystems Corp.//DTD HotJava Strict HTML//",
        "-//W3C//DTD HTML 3 1995-03-24//",
        "-//W3C//DTD HTML 3.2 Draft//",
        "-//W3C//DTD HTML 3.2 Final//",
        "-//W3C//DTD HTML 3.2//",
        "-//W3C//DTD HTML 3.2S Draft//",
        "-//W3C//DTD HTML 4.0 Frameset//",
        "-//W3C//DTD HTML 4.0 Transitional//",
        "-//W3C//DTD HTML Experimental 19960712//",
        "-//W3C//DTD HTML Experimental 970421//",
        "-//W3C//DTD W3 HTML//",
        "-//W3O//DTD W3 HTML 3.0//",
        "-//WebTechs//DTD Mozilla HTML 2.0//",
        "-//WebTechs//DTD Mozilla HTML//"
    )

    private val QUIRKS_MODE_SYSTEM_ID_MISSING_PREFIXES = listOf(
        "-//W3C//DTD HTML 4.01 Frameset//",
        "-//W3C//DTD HTML 4.01 Transitional//"
    )

    private val LIMITED_QUIRKS_MODE_PREFIXES = listOf(
        "-//W3C//DTD XHTML 1.0 Frameset//",
        "-//W3C//DTD XHTML 1.0 Transitional//"
    )

    private val LIMITED_QUIRKS_SYSTEM_ID_NOT_MISSING_PREFIXES = listOf(
        "-//W3C//DTD HTML 4.01 Frameset//",
        "-//W3C//DTD HTML 4.01 Transitional//"
    )
}
