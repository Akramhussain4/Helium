package com.hussain.csoup.util

internal fun <K, V> MutableMap<K, V>.updateKey(oldKey: K, newKey: K) {
    this[oldKey]?.let { value ->
        this.remove(oldKey)
        this[newKey] = value
    }
}