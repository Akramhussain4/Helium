package com.theakram.helium.tokenization

internal sealed interface Token {

    data object DocType : Token

    data class StartTag(
        val name: String,
        val selfClosing: Boolean = false,
        val attributes: MutableMap<String, String> = LinkedHashMap()
    ) : Token {

        fun addAttribute(name: String, value: String) {
            if (!attributes.containsKey(name)) {
                attributes[name.lowercase()] = value
            }
        }
    }

    data class EndTag(val name: String) : Token

    data class Comment(val data: String) : Token

    data class Character(val data: Char) : Token
}