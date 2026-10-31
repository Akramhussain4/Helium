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

package com.theakram.helium.tokenizer

import com.theakram.helium.treebuilder.TokenizerStateType
import kotlin.test.Test

/**
 * Script data state transitions that html5lib's tokenizer tests don't reach.
 *
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#script-data-state
 */
internal class ScriptDataTest : TokenizerTestBase() {

    @Test
    fun `end tag closes script inside escaped comment`() = assertTokenization(
        input = "<!--x</script>",
        expectedTokens = listOf(
            *chars("<!--x").toTypedArray(),
            endTag("script")
        ),
        initialState = TokenizerStateType.SCRIPT_DATA,
        lastStartTag = "script"
    )

    @Test
    fun `end tag closes script after escaped comment ends`() = assertTokenization(
        input = "<!--x--></script>",
        expectedTokens = listOf(
            *chars("<!--x-->").toTypedArray(),
            endTag("script")
        ),
        initialState = TokenizerStateType.SCRIPT_DATA,
        lastStartTag = "script"
    )

    @Test
    fun `empty escaped comment returns to script data`() = assertTokenization(
        input = "<!--></script>",
        expectedTokens = listOf(
            *chars("<!-->").toTypedArray(),
            endTag("script")
        ),
        initialState = TokenizerStateType.SCRIPT_DATA,
        lastStartTag = "script"
    )

    @Test
    fun `uppercase script tag starts and ends double escaping`() = assertTokenization(
        input = "<!--<SCRIPT></SCRIPT>--></script>",
        expectedTokens = listOf(
            *chars("<!--<SCRIPT></SCRIPT>-->").toTypedArray(),
            endTag("script")
        ),
        initialState = TokenizerStateType.SCRIPT_DATA,
        lastStartTag = "script"
    )

    @Test
    fun `tag name other than script does not double escape`() = assertTokenization(
        input = "<!--<scripts></script>",
        expectedTokens = listOf(
            *chars("<!--<scripts>").toTypedArray(),
            endTag("script")
        ),
        initialState = TokenizerStateType.SCRIPT_DATA,
        lastStartTag = "script"
    )

    @Test
    fun `end tag not matching last start tag stays escaped`() = assertTokenization(
        input = "<!--</style>--></script>",
        expectedTokens = listOf(
            *chars("<!--</style>-->").toTypedArray(),
            endTag("script")
        ),
        initialState = TokenizerStateType.SCRIPT_DATA,
        lastStartTag = "script"
    )

    @Test
    fun `end tag with attributes closes script`() = assertTokenization(
        input = "x</script foo=\"bar\">y",
        expectedTokens = listOf(
            char('x'),
            endTag("script", attributes = mapOf("foo" to "bar")),
            char('y')
        ),
        initialState = TokenizerStateType.SCRIPT_DATA,
        lastStartTag = "script"
    )

    @Test
    fun `end tag closes script inside escaped comment after dash`() = assertTokenization(
        input = "<!--x-</script>",
        expectedTokens = listOf(
            *chars("<!--x-").toTypedArray(),
            endTag("script")
        ),
        initialState = TokenizerStateType.SCRIPT_DATA,
        lastStartTag = "script"
    )
}
