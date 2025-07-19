package com.theakram.helium

import com.theakram.helium.tokenization.Token
import com.theakram.helium.tokenization.Tokenizer
import com.theakram.helium.treebuilder.TreeBuilder

public class HTMLParser {

    private val tokenizer = Tokenizer(onTokenEmitted = ::processToken)
    private val treeBuilder = TreeBuilder()

    public fun parse(html: String) {
        val normalizedHtml = normalizeHtml(html)
        tokenizer.tokenize(normalizedHtml)
    }

    private fun processToken(token: Token) {
        treeBuilder.process(token)
    }

    private fun normalizeHtml(html: String): String {
        // Step 1 & 2: Normalize newlines
        val normalizedNewlines = html
            .replace("\r\n", "\n")
            .replace("\r", "\n")

        // Step 3: Strip and collapse ASCII whitespace, specifically outside of HTML tags
        return Regex("(>\\s+<)|(\\s+)").replace(normalizedNewlines) {
            if (it.groups[1] != null) // Matches '> <'
                "><"
            else // Matches other whitespaces
                " "
        }.trim()
    }
}