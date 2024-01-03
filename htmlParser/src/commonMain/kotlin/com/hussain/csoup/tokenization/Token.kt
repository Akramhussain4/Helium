package com.hussain.csoup.tokenization

internal sealed class Token {

    data object DocType : Token()

    data class StartTag(
        val name: String,
        val selfClosing: Boolean = false,
        val attributes: Map<String, String> = emptyMap()
    ) : Token()

    data class EndTag(val name: String) : Token()

    data class Comment(val data: String) : Token()

    data class Character(val data: Char) : Token()

    data class EOF(val data: String) : Token()
}