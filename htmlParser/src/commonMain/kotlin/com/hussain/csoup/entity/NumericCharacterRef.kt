package com.hussain.csoup.entity

internal val NumCharRef by lazy {
    mapOf(
        0x80 to mapOf(0x20AC to "€"),
        0x82 to mapOf(0x201A to "‚"),
        0x83 to mapOf(0x0192 to "ƒ"),
        0x84 to mapOf(0x201E to "„"),
        0x85 to mapOf(0x2026 to "…"),
        0x86 to mapOf(0x2020 to "†"),
        0x87 to mapOf(0x2021 to "‡"),
        0x88 to mapOf(0x02C6 to "ˆ"),
        0x89 to mapOf(0x2030 to "‰"),
        0x8A to mapOf(0x0160 to "Š"),
        0x8B to mapOf(0x2039 to "‹"),
        0x8C to mapOf(0x0152 to "Œ"),
        0x8E to mapOf(0x017D to "Ž"),
        0x91 to mapOf(0x2018 to "‘"),
        0x92 to mapOf(0x2019 to "’"),
        0x93 to mapOf(0x201C to "“"),
        0x94 to mapOf(0x201D to "”"),
        0x95 to mapOf(0x2022 to "•"),
        0x96 to mapOf(0x2013 to "–"),
        0x97 to mapOf(0x2014 to "—"),
        0x98 to mapOf(0x02DC to "˜"),
        0x99 to mapOf(0x2122 to "™"),
        0x9A to mapOf(0x0161 to "š"),
        0x9B to mapOf(0x203A to "›"),
        0x9C to mapOf(0x0153 to "œ"),
        0x9E to mapOf(0x017E to "ž"),
        0x9F to mapOf(0x0178 to "Ÿ")
    )
}