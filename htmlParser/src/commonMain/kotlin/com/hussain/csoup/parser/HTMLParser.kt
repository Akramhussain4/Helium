package com.hussain.csoup.parser

import com.hussain.csoup.tokenization.Tokenizer

public class HTMLParser {

    public fun parse(html: String) {
        val normalizedHtml = normalizeHtml(html)
        val tokenizer = Tokenizer()
        tokenizer.tokenize(normalizedHtml)
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