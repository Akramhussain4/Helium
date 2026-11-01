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

/**
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#serializes-as-void
 *
 * "An element serializes as void if its element type is one of the void elements, or is basefont, bgsound, frame,
 * keygen, or param."
 */
private val SERIALIZES_AS_VOID = setOf(
    // Spec: https://html.spec.whatwg.org/multipage/syntax.html#void-elements
    "area", "base", "br", "col", "embed", "hr", "img", "input", "link", "meta", "source", "track", "wbr",
    "basefont", "bgsound", "frame", "keygen", "param"
)

/** The elements whose Text children the HTML fragment serialization algorithm appends literally. */
private val LITERAL_TEXT_PARENTS = setOf("style", "script", "xmp", "iframe", "noembed", "noframes", "plaintext")

private fun TreeNode.isHtmlElement(): Boolean = type == NodeType.ELEMENT && namespace == Namespaces.HTML

internal val TreeNode.serializesAsVoid: Boolean
    get() = isHtmlElement() && localName in SERIALIZES_AS_VOID

/**
 * Serializes trees to HTML, with the HTML fragment serialization algorithm. Instead of recursing into each child
 * element as the spec does, it keeps the elements it is inside on a stack and appends each one's end tag as it leaves
 * it, so that deep trees don't overflow the call stack, and it appends everything to one builder.
 *
 * Helium has no shadow DOM and no custom elements, so the steps for shadow hosts (serializableShadowRoots and
 * shadowRoots, which write `<template shadowrootmode>`) and for an element's is value are left out: no node is a
 * shadow host, and an element's is value is always null.
 *
 * [scriptingEnabled] says whether scripting is enabled for the nodes, which decides whether a noscript element's text
 * is appended literally. It is enabled only for a node whose node document has a browsing context, so it is disabled
 * in template contents whatever [scriptingEnabled] says: their documents, created by the appropriate template contents
 * owner document algorithm, have none.
 *
 * Spec: https://html.spec.whatwg.org/multipage/parsing.html#serialising-html-fragments
 */
internal class HtmlSerializer(private val scriptingEnabled: Boolean) {

    private val output = StringBuilder()

    /**
     * A node whose children are being serialized: [element] gets its end tag once all of [container]'s children have
     * been, and [container] is the node whose children they are, which is a template's contents rather than the
     * template itself. [parent] is the parent the DOM would give those children, which is [container] unless that is
     * a document fragment Helium inserted as a node.
     */
    private class Frame(
        val element: TreeNode?,
        val container: TreeNode?,
        val parent: TreeNode?,
        val inTemplateContents: Boolean
    ) {
        var nextChild = 0
    }

    private fun pushFrame(node: TreeNode, parent: TreeNode?, inTemplateContents: Boolean) {
        val container = node.contents()
        val isElement = node.type == NodeType.ELEMENT
        frames.add(
            Frame(
                element = node.takeIf { isElement },
                container = container,
                parent = if (isElement) container else parent,
                inTemplateContents = inTemplateContents || node.isTemplate
            )
        )
    }

    private val frames = ArrayList<Frame>()

    /**
     * The HTML fragment serialization algorithm: serializes [node]'s children, not [node] itself.
     *
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#html-fragment-serialisation-algorithm
     */
    fun serializeChildren(node: TreeNode): String {
        // Step 1: "If the node serializes as void, then return the empty string."
        if (node.serializesAsVoid) return ""

        // Step 2 is output. Step 3: "If the node is a template element, then let the node instead be the template
        // element's template contents (a DocumentFragment node)."
        // Step 4 is for shadow hosts, which Helium doesn't have.
        val container = node.contents()
        frames.add(Frame(null, container, container, node.isInInertTemplateDocument() || node.isTemplate))

        // Steps 5-6:
        serializeFrames()
        return output.toString()
    }

    /**
     * Serializes [node] itself: for an element, its start tag, its contents and its end tag, as the DOM's outerHTML
     * getter does by serializing a fictional node whose only child is the element. Other nodes are serialized as the
     * HTML fragment serialization algorithm serializes them as a child of their parent, so a Text node in a script
     * element is appended literally, and a Document or DocumentFragment, which has no markup of its own, as its
     * children.
     *
     * Spec: https://html.spec.whatwg.org/multipage/dynamic-markup-insertion.html#dom-element-outerhtml
     */
    fun serializeNode(node: TreeNode): String {
        val inTemplateContents = node.isInInertTemplateDocument()
        if (appendChild(node, node.parent, inTemplateContents)) {
            pushFrame(node, node, inTemplateContents)
            serializeFrames()
        }
        return output.toString()
    }

    /** Serializes the children of the containers on [frames], and the descendants they lead to. */
    private fun serializeFrames() {
        while (frames.isNotEmpty()) {
            val frame = frames.last()
            val children = frame.container?.children.orEmpty()
            if (frame.nextChild == children.size) {
                frames.removeAt(frames.lastIndex)
                // Step 5.2, element: "followed by "</", tagname, and U+003E (>)."
                frame.element?.let { output.append("</").append(it.tagName()).append('>') }
                continue
            }
            // Step 5: "For each child node of the node, in tree order, run the following steps:"
            // Step 5.1: "Let current node be the child node being processed."
            val currentNode = children[frame.nextChild++]
            if (appendChild(currentNode, frame.parent, frame.inTemplateContents)) {
                // Step 5.2, element: "Append the value of running the HTML fragment serialization algorithm with
                // current node, serializableShadowRoots, and shadowRoots (thus recursing into this algorithm for that
                // node)", which is done by serializing the new frame's children before going on with this one's
                pushFrame(currentNode, frame.parent, frame.inTemplateContents)
            }
        }
    }

    /**
     * Step 5.2: "Append the appropriate string from the following list to s". [parent] is the parent of
     * [currentNode]. Returns whether [currentNode]'s children are to be serialized next, followed by its end tag if
     * it is an element.
     */
    private fun appendChild(currentNode: TreeNode, parent: TreeNode?, inTemplateContents: Boolean): Boolean {
        when (currentNode.type) {
            NodeType.ELEMENT -> {
                appendStartTag(currentNode)
                // "If current node serializes as void, then continue on to the next child node at this point."
                return !currentNode.serializesAsVoid
            }

            // A CDATASection node is a Text node, so the parser's CDATA sections, which it inserts as Text nodes, and
            // the ones made with the constructor are serialized alike
            NodeType.TEXT, NodeType.CDATA_SECTION -> {
                val data = currentNode.data.orEmpty()
                // "If the parent of current node is a style, script, xmp, iframe, noembed, noframes, or plaintext
                // element, or if the parent of current node is a noscript element and scripting is enabled for the
                // node, then append the value of current node's data literally."
                val literal = parent != null && parent.isHtmlElement() && (
                    parent.localName in LITERAL_TEXT_PARENTS ||
                        (parent.localName == "noscript" && scriptingEnabled && !inTemplateContents)
                    )
                // "Otherwise, append the value of current node's data, escaped as described below."
                if (literal) output.append(data) else appendEscaped(data, attributeMode = false)
            }

            // "Append "<!--", followed by the value of current node's data, and "-->"."
            NodeType.COMMENT -> output.append("<!--").append(currentNode.data.orEmpty()).append("-->")

            // "Append "<?", followed by the value of current node's target, U+0020 SPACE, the value of current node's
            // data, and "?>"."
            NodeType.PROCESSING_INSTRUCTION -> output.append("<?").append(currentNode.localName.orEmpty()).append(' ')
                .append(currentNode.data.orEmpty()).append("?>")

            // "Append "<!DOCTYPE ", followed by the value of current node's name and U+003E (>)."
            NodeType.DOCUMENT_TYPE -> output.append("<!DOCTYPE ").append(currentNode.localName.orEmpty()).append('>')

            // A DOM node can't have these as children, but Helium inserts a document fragment as a node rather than
            // by moving its children. Its children are serialized in its place, where the DOM would have put them.
            NodeType.DOCUMENT, NodeType.DOCUMENT_FRAGMENT -> return true
        }
        return false
    }

    /** Step 5.2, element: the start tag. */
    private fun appendStartTag(element: TreeNode) {
        // "Append U+003C (<), followed by tagname."
        output.append('<').append(element.tagName())
        // "For each attribute that the element has, append U+0020 SPACE, the attribute's serialized name as described
        // below, "="", the attribute's value, escaped as described below in attribute mode, and U+0022 (")."
        for (attribute in element.attributes) {
            output.append(' ')
            appendSerializedName(attribute)
            output.append("=\"")
            appendEscaped(attribute.value, attributeMode = true)
            output.append('"')
        }
        // "Append U+003E (>)."
        output.append('>')
    }

    /**
     * "An attribute's serialized name for the purposes of the previous paragraph must be determined as follows:"
     */
    private fun appendSerializedName(attribute: Attribute) {
        when (attribute.namespace) {
            // "If the attribute has no namespace: The attribute's serialized name is the attribute's local name."
            null -> output.append(attribute.localName)
            // "If the attribute is in the XML namespace: The attribute's serialized name is "xml:" followed by the
            // attribute's local name."
            Namespaces.XML -> output.append("xml:").append(attribute.localName)
            // "If the attribute is in the XMLNS namespace and the attribute's local name is xmlns: The attribute's
            // serialized name is "xmlns"." "If the attribute is in the XMLNS namespace and the attribute's local name
            // is not xmlns: The attribute's serialized name is "xmlns:" followed by the attribute's local name."
            Namespaces.XMLNS ->
                if (attribute.localName == "xmlns") output.append("xmlns")
                else output.append("xmlns:").append(attribute.localName)
            // "If the attribute is in the XLink namespace: The attribute's serialized name is "xlink:" followed by the
            // attribute's local name."
            Namespaces.XLINK -> output.append("xlink:").append(attribute.localName)
            // "If the attribute is in some other namespace: The attribute's serialized name is the attribute's
            // qualified name."
            else -> output.append(attribute.name)
        }
    }

    /**
     * Spec: https://html.spec.whatwg.org/multipage/parsing.html#escapingString
     *
     * The steps replace each character on its own, so a single pass that replaces whichever it finds gives the same
     * result: none of the replacements contains a character a later step replaces.
     */
    private fun appendEscaped(value: String, attributeMode: Boolean) {
        var unescapedFrom = 0
        for (index in value.indices) {
            val replacement = when (value[index]) {
                // Step 1: "Replace any occurrence of the "&" character by the string "&amp;"."
                '&' -> "&amp;"
                // Step 2: "Replace any occurrences of the U+00A0 NO-BREAK SPACE character by the string "&nbsp;"."
                ' ' -> "&nbsp;"
                // Step 3: "Replace any occurrences of the "<" character by the string "&lt;"."
                '<' -> "&lt;"
                // Step 4: "Replace any occurrences of the ">" character by the string "&gt;"."
                '>' -> "&gt;"
                // Step 5: "If the algorithm was invoked in the attribute mode, then replace any occurrences of the
                // """ character by the string "&quot;"."
                '"' -> if (attributeMode) "&quot;" else continue
                else -> continue
            }
            output.append(value, unescapedFrom, index).append(replacement)
            unescapedFrom = index + 1
        }
        output.append(value, unescapedFrom, value.length)
    }

    private companion object {
        /**
         * Step 5.2, element: "If current node is an element in the HTML namespace, the MathML namespace, or the SVG
         * namespace, then let tagname be current node's local name. Otherwise, let tagname be current node's
         * qualified name."
         */
        fun TreeNode.tagName(): String = when (namespace) {
            Namespaces.HTML, Namespaces.MATHML, Namespaces.SVG -> localName.orEmpty()
            else -> name.orEmpty()
        }

        /** The node whose children are serialized as [this] node's: a template's contents, or the node itself. */
        fun TreeNode.contents(): TreeNode? = if (isTemplate) templateContent else this

        /**
         * Whether this node's node document is one that holds template contents, which has no browsing context. This
         * follows [TreeNode.nodeDocument] without calling it, since that creates the template contents' document if
         * it doesn't exist yet: template contents always have such a document.
         */
        fun TreeNode.isInInertTemplateDocument(): Boolean {
            val root = root
            return when {
                root.type == NodeType.DOCUMENT -> root.isInertTemplateDocument
                root.rootNodeDocument != null -> root.rootNodeDocument?.isInertTemplateDocument == true
                else -> root.host != null
            }
        }
    }
}
