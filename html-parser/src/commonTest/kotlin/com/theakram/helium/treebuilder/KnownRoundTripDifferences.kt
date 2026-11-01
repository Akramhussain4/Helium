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

package com.theakram.helium.treebuilder

import com.theakram.helium.tree.Namespaces
import com.theakram.helium.tree.NodeType
import com.theakram.helium.tree.TreeNode

// The HTML fragment serialization algorithm's note: "It is possible that the output of this algorithm, if parsed with
// an HTML parser, will not return the original tree structure." These are the ways it doesn't in the tree
// construction tests, each with the spec text that makes it so, and a check that the run's difference is that one.

/**
 * Why a run's tree is different after a round trip. [appliesTo] checks that the difference is this one, given the
 * first tree, the serialized HTML and the reparsed tree, so that an entry can't hide a different difference.
 */
internal class RoundTripDifference(
    val reason: String,
    val appliesTo: (first: TreeNode, serialized: String, second: TreeNode) -> Boolean
)

private val PLAINTEXT_END_TAG = RoundTripDifference(
    "The serializer appends an end tag to every element that doesn't serialize as void (\"followed by \"</\", " +
        "tagname, and U+003E (>)\"), but nothing ends the PLAINTEXT state, which a plaintext start tag switches the " +
        "tokenizer to (\"Anything else: Emit the current input character as a character token.\"). Reparsed, " +
        "everything in the serialized plaintext element that isn't text, the </plaintext> end tag and everything " +
        "after it are the plaintext element's text."
) { _, _, second -> second.hasTextContaining("</plaintext>") }

private val SCRIPT_DOUBLE_ESCAPED = RoundTripDifference(
    "The script's text, which the parser ended at EOF, contains \"<!--<script\", which leaves the tokenizer in the " +
        "script data double escaped state or one of its dash and end states, and the serializer appends it " +
        "literally followed by \"</script>\". Reparsed, that end tag only ends the double escape (script data " +
        "double escape end state: \"If the temporary buffer is \"script\", then switch to the script data escaped " +
        "state.\"), so it and the rest of the markup become the script's text."
) { _, _, second -> second.hasTextContaining("</script>") }

private val DOCTYPE_IDENTIFIERS = RoundTripDifference(
    "The serializer writes a doctype's name only (\"Append \"<!DOCTYPE \", followed by the value of current " +
        "node's name and U+003E (>).\"), so its public and system identifiers are lost. Where they put the document " +
        "in quirks mode, so is that, which changes how a p element followed by a table parses: \"If the Document is " +
        "not set to quirks mode, and the stack of open elements has a p element in button scope, then close a p " +
        "element.\""
) { first, _, second -> first.doctypeHasIdentifiers() && !second.doctypeHasIdentifiers() }

private val LEADING_NEWLINE = RoundTripDifference(
    "\"For historical reasons, this algorithm does not roundtrip an initial U+000A (LF) character in pre, " +
        "textarea, or listing elements\": the element's text starts with a newline the parser kept because a " +
        "first one was dropped, and reparsing drops that one too."
) { _, serialized, _ -> Regex("<(pre|textarea|listing)[^>]*>\n").containsMatchIn(serialized) }

private val NESTED_FORMATTING = RoundTripDifference(
    "The parser's error handling nested an a element in another a element, or a nobr element in another nobr " +
        "element: foster parenting, an adoption agency run that stopped early, or a marker that a foster parented " +
        "marquee left in the list of active formatting elements. Reparsed, the inner start tag closes the outer " +
        "element first: in body's \"If the list of active formatting elements contains an a element between the " +
        "end of the list and the last marker on the list (...), then this is a parse error; run the adoption agency " +
        "algorithm for the token\" and \"If the stack of open elements has a nobr element in scope, then this is a " +
        "parse error; run the adoption agency algorithm for the token\". The spec's own example is \"<a><table><a>\"."
) { first, _, _ -> first.hasElementInSameNamedElement("a") || first.hasElementInSameNamedElement("nobr") }

private val NESTED_FORM = RoundTripDifference(
    "The </form> in the table cleared the form element pointer but left the form open, since a table bounds its " +
        "scope (\"If node is null or if the stack of open elements does not have node in scope, then this is a parse " +
        "error; return and ignore the token.\"), so the second form start tag made a form in the table in the form. " +
        "Reparsed, the outer form sets the pointer, so in table ignores the inner start tag: \"Parse error. If the " +
        "form element pointer is not null, and the parser is not parsing template contents, then ignore the " +
        "token.\" The spec's own example is a form in a div in another form."
) { first, _, _ -> first.hasElementInSameNamedElement("form") }

private val NESTED_P = RoundTripDifference(
    "The </p> in the table inserted a p element in the outer p element, before the table, which quirks mode " +
        "left inside the p. Reparsed, the inner p's start tag closes the outer one: \"If the stack of open " +
        "elements has a p element in button scope, then close a p element.\" The spec's note gives a p element " +
        "containing a ul element as an example."
) { first, _, _ -> first.hasElementInSameNamedElement("p") }

private val CARRIAGE_RETURN = RoundTripDifference(
    "A character reference put a U+000D CR in the text, which the serializer writes as is, and reparsing " +
        "normalizes it to U+000A LF: \"Before the tokenization stage, the input stream must be preprocessed by " +
        "normalizing newlines. Thus, newlines in HTML DOMs are represented by U+000A LF characters, and there are " +
        "never any U+000D CR characters in the input to the tokenization stage.\""
) { _, serialized, _ -> '\r' in serialized }

/** Calls [action] with each node in this tree, template contents included, without recursing. */
private fun TreeNode.forEachNode(action: (TreeNode) -> Unit) {
    val pending = ArrayDeque(listOf(this))
    while (pending.isNotEmpty()) {
        val node = pending.removeLast()
        action(node)
        pending.addAll(node.children)
        node.templateContent?.let { pending.add(it) }
    }
}

private fun TreeNode.hasTextContaining(text: String): Boolean {
    var found = false
    forEachNode { if (it.type == NodeType.TEXT && it.data.orEmpty().contains(text)) found = true }
    return found
}

private fun TreeNode.doctypeHasIdentifiers(): Boolean =
    children.any { it.type == NodeType.DOCUMENT_TYPE && (!it.publicId.isNullOrEmpty() || !it.systemId.isNullOrEmpty()) }

/** Whether an HTML element named [localName] has an ancestor with the same name. */
private fun TreeNode.hasElementInSameNamedElement(localName: String): Boolean {
    fun TreeNode.isNamed() = type == NodeType.ELEMENT && namespace == Namespaces.HTML && this.localName == localName
    var found = false
    forEachNode { node ->
        if (node.isNamed()) {
            var ancestor = node.parent
            while (ancestor != null && !ancestor.isNamed()) ancestor = ancestor.parent
            if (ancestor != null) found = true
        }
    }
    return found
}

/**
 * The tree construction test runs whose tree is different after serializing it and parsing the result, with the
 * reason for each, keyed by file, line and scripting mode. See [SerializationRoundTripTests].
 */
internal object KnownRoundTripDifferences {

    private val differences = mapOf(
        "tests1.dat:373 (script on)" to NESTED_FORMATTING,
        "tests1.dat:373 (script off)" to NESTED_FORMATTING,
        "tests1.dat:1141 (script on)" to NESTED_FORMATTING,
        "tests1.dat:1141 (script off)" to NESTED_FORMATTING,
        "tests1.dat:1379 (script on)" to NESTED_FORMATTING,
        "tests1.dat:1379 (script off)" to NESTED_FORMATTING,
        "tests1.dat:1551 (script on)" to NESTED_FORMATTING,
        "tests1.dat:1551 (script off)" to NESTED_FORMATTING,
        "tests2.dat:159 (script on)" to PLAINTEXT_END_TAG,
        "tests2.dat:159 (script off)" to PLAINTEXT_END_TAG,
        "tests2.dat:177 (script on)" to PLAINTEXT_END_TAG,
        "tests2.dat:177 (script off)" to PLAINTEXT_END_TAG,
        "tests3.dat:75 (script on)" to LEADING_NEWLINE,
        "tests3.dat:75 (script off)" to LEADING_NEWLINE,
        "tests3.dat:147 (script on)" to LEADING_NEWLINE,
        "tests3.dat:147 (script off)" to LEADING_NEWLINE,
        "tests3.dat:227 (script on)" to LEADING_NEWLINE,
        "tests3.dat:227 (script off)" to LEADING_NEWLINE,
        "tests6.dat:603 (script on)" to DOCTYPE_IDENTIFIERS,
        "tests6.dat:603 (script off)" to DOCTYPE_IDENTIFIERS,
        "tests16.dat:403 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:403 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:418 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:418 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:433 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:433 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:448 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:448 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:463 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:463 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:478 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:478 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:493 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:493 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:654 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:654 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:669 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:669 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:684 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:684 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:699 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:699 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:714 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:714 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:729 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:729 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1705 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1705 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1720 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1720 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1735 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1735 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1750 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1750 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1765 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1765 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1780 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1780 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1795 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1795 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1956 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1956 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1971 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1971 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1986 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:1986 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:2001 (script on)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:2001 (script off)" to SCRIPT_DOUBLE_ESCAPED,
        "tests16.dat:2587 (script on)" to NESTED_FORM,
        "tests16.dat:2587 (script off)" to NESTED_FORM,
        "tests18.dat:1 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:1 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:13 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:13 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:25 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:25 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:37 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:37 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:49 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:64 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:64 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:76 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:76 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:88 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:88 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:114 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:114 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:141 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:141 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:169 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:169 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:186 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:186 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:200 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:200 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:227 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:227 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:240 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:240 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:267 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:267 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:281 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:281 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:317 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:317 (script off)" to PLAINTEXT_END_TAG,
        "tests18.dat:355 (script on)" to PLAINTEXT_END_TAG,
        "tests18.dat:355 (script off)" to PLAINTEXT_END_TAG,
        "tests19.dat:60 (script on)" to PLAINTEXT_END_TAG,
        "tests19.dat:60 (script off)" to PLAINTEXT_END_TAG,
        "tests19.dat:1423 (script on)" to PLAINTEXT_END_TAG,
        "tests19.dat:1423 (script off)" to PLAINTEXT_END_TAG,
        "tests20.dat:444 (script on)" to PLAINTEXT_END_TAG,
        "tests20.dat:444 (script off)" to PLAINTEXT_END_TAG,
        "tests20.dat:536 (script on)" to NESTED_P,
        "tests20.dat:536 (script off)" to NESTED_P,
        "tests26.dat:71 (script on)" to NESTED_FORMATTING,
        "tests26.dat:71 (script off)" to NESTED_FORMATTING,
        "adoption02.dat:58 (script on)" to NESTED_FORMATTING,
        "adoption02.dat:58 (script off)" to NESTED_FORMATTING,
        "doctype01.dat:168 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:168 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:179 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:179 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:190 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:190 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:285 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:285 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:296 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:296 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:310 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:310 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:321 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:321 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:335 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:335 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:358 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:358 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:369 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:369 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:396 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:396 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:408 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:408 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:420 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:420 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:433 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:433 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:446 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:446 (script off)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:461 (script on)" to DOCTYPE_IDENTIFIERS,
        "doctype01.dat:461 (script off)" to DOCTYPE_IDENTIFIERS,
        "foreign-fragment.dat:481 (script on)" to PLAINTEXT_END_TAG,
        "foreign-fragment.dat:481 (script off)" to PLAINTEXT_END_TAG,
        "plain-text-unsafe.dat:1 (script on)" to CARRIAGE_RETURN,
        "plain-text-unsafe.dat:1 (script off)" to CARRIAGE_RETURN,
        "plain-text-unsafe.dat:131 (script on)" to PLAINTEXT_END_TAG,
        "plain-text-unsafe.dat:131 (script off)" to PLAINTEXT_END_TAG,
        "plain-text-unsafe.dat:436 (script on)" to LEADING_NEWLINE,
        "plain-text-unsafe.dat:436 (script off)" to LEADING_NEWLINE,
        "plain-text-unsafe.dat:450 (script on)" to LEADING_NEWLINE,
        "plain-text-unsafe.dat:450 (script off)" to LEADING_NEWLINE,
        "quirks01.dat:1 (script on)" to DOCTYPE_IDENTIFIERS,
        "quirks01.dat:1 (script off)" to DOCTYPE_IDENTIFIERS,
        "quirks01.dat:15 (script on)" to DOCTYPE_IDENTIFIERS,
        "quirks01.dat:15 (script off)" to DOCTYPE_IDENTIFIERS,
        "quirks01.dat:28 (script on)" to DOCTYPE_IDENTIFIERS,
        "quirks01.dat:28 (script off)" to DOCTYPE_IDENTIFIERS,
        "quirks01.dat:41 (script on)" to DOCTYPE_IDENTIFIERS,
        "quirks01.dat:41 (script off)" to DOCTYPE_IDENTIFIERS,
        "template.dat:1692 (script on)" to NESTED_FORMATTING,
        "template.dat:1692 (script off)" to NESTED_FORMATTING,
        "webkit02.dat:320 (script on)" to PLAINTEXT_END_TAG,
        "webkit02.dat:320 (script off)" to PLAINTEXT_END_TAG,
        "webkit02.dat:350 (script on)" to PLAINTEXT_END_TAG,
        "webkit02.dat:350 (script off)" to PLAINTEXT_END_TAG
    )

    val ids: Set<String> get() = differences.keys

    operator fun get(id: String): RoundTripDifference? = differences[id]
}
