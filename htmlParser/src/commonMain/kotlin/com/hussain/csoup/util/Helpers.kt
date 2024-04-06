package com.hussain.csoup.util

import com.hussain.csoup.tokenization.states.AttributeValueDoubleQuoted
import com.hussain.csoup.tokenization.states.AttributeValueSingleQuoted
import com.hussain.csoup.tokenization.states.AttributeValueUnquoted
import com.hussain.csoup.tokenization.states.TokenizationState

internal fun <K, V> MutableMap<K, V>.updateKey(oldKey: K, newKey: K) {
    this[oldKey]?.let { value ->
        this.remove(oldKey)
        this[newKey] = value
    }
}

internal fun TokenizationState.isAttributeState(): Boolean {
    return this is AttributeValueUnquoted
            || this is AttributeValueSingleQuoted
            || this is AttributeValueDoubleQuoted
}