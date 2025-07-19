package com.theakram.helium.tokenization.util

import com.theakram.helium.tokenization.states.AttributeValueDoubleQuoted
import com.theakram.helium.tokenization.states.AttributeValueSingleQuoted
import com.theakram.helium.tokenization.states.AttributeValueUnquoted
import com.theakram.helium.tokenization.states.TokenizationState
import com.theakram.helium.util.Chars

internal fun <K, V> MutableMap<K, V>.updateKey(oldKey: K, newKey: K) {
    this[oldKey]?.let { value ->
        remove(oldKey)
        this[newKey] = value
    }
}

internal fun TokenizationState.isAttributeState(): Boolean {
    return this is AttributeValueUnquoted
            || this is AttributeValueSingleQuoted
            || this is AttributeValueDoubleQuoted
}

@Suppress("FunctionName")
internal fun Int.IS(vararg codepoints: Chars) = codepoints.any { this == it.char.code }