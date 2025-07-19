package com.theakram.helium.tokenizer

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import org.junit.Test
import java.io.File

internal class TestsGenerator {

    @Test
    fun main() {
        val jsonContent = File("tests.json").readText()
        val json = Json { ignoreUnknownKeys = true }
        val testSuite = json.decodeFromString<TestSuite>(jsonContent)

        val testNameCounts = mutableMapOf<String, Int>()
        val generatedTests = buildString {
            appendLine("import kotlin.test.Test")
            appendLine()
            appendLine("class TokenizerTest3 : TokenizerTestBase() {")

            testSuite.tests.forEach { testCase ->
                val testName = generateUniqueTestName(testCase.description, testNameCounts)
                append(generateTest(testCase, testName))
            }

            appendLine("}")
        }

        File("src/commonTest/kotlin/com/hussain/csoup/tokenizer/TokenizerTest3.kt").writeText(generatedTests)
    }

    private fun generateUniqueTestName(description: String, testNameCounts: MutableMap<String, Int>): String {
        // Remove all characters that aren't allowed in Kotlin identifiers
        val cleanedName = description
            .replace(Regex("[^A-Za-z0-9 ]"), "") // Only allow letters, numbers, and spaces
            .replace(Regex("\\s+"), " ") // Normalize multiple spaces to single space
            .trim()

        // Get current count for this base name
        val count = testNameCounts.getOrDefault(cleanedName, 0)
        testNameCounts[cleanedName] = count + 1

        // Only add suffix if it's a duplicate
        return if (count > 0) {
            "`$cleanedName ${count + 1}`"
        } else {
            "`$cleanedName`"
        }
    }

    private fun parseTokenOutput(output: List<JsonElement>): List<Any?> {
        return output.map { element ->
            when (element) {
                is JsonNull -> null
                is JsonPrimitive -> {
                    when {
                        element.isString -> element.content
                        element.booleanOrNull != null -> element.boolean
                        else -> element.content
                    }
                }

                is JsonObject -> element.entries
                    .associate { it.key to (it.value as JsonPrimitive).content }

                else -> throw IllegalArgumentException("Unexpected token type: $element")
            }
        }
    }

    private fun generateTest(testCase: TestCase, testName: String): String {
        return buildString {
            appendLine()
            appendLine("    @Test")
            appendLine("    fun ${testName}() = assertTokenization(")
            appendLine("        input = \"${testCase.input.escapeString()}\",")
            appendLine("        expectedTokens = listOf(")
            appendLine(generateExpectedTokens(testCase.output.map { parseTokenOutput(it) }))
            appendLine("        )")
            appendLine("    )")
        }
    }

    private fun generateExpectedTokens(output: List<List<Any?>>): String {
        return output.joinToString(",\n            ") { token ->
            when (token[0]) {
                "StartTag" -> {
                    val name = token[1] as String
                    val attributes = token[2] as Map<*, *>
                    val selfClosing = if (token.size > 3) token[3] as Boolean else false
                    "startTag(name = \"$name\"${
                        if (attributes.isNotEmpty()) ", attributes = mapOf(${
                            attributes.entries.joinToString { "\"${it.key}\" to \"${it.value}\"" }
                        })" else ""
                    }${if (selfClosing) ", selfClosing = true" else ""})"
                }

                "EndTag" -> "endTag(\"${token[1]}\")"
                "Character" -> {
                    val text = token[1] as String
                    if (text.length == 1) "char('${text.escapeString()}')" else "*chars(\"${text.escapeString()}\").toTypedArray()"
                }

                "Comment" -> "comment(\"${token[1]}\")"
                "DOCTYPE" -> ""
                else -> throw IllegalArgumentException("Unknown token type: ${token[0]}")
            }
        }
    }

    private fun String.escapeString(): String {
        return this.replace("\\", "\\\\")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t")
            .replace("\"", "\\\"")
            .replace("\u0000", "\\u0000")
    }

    @Serializable
    private data class TestSuite(
        val tests: List<TestCase>
    )

    @Serializable
    private data class TestCase(
        val description: String,
        val input: String,
        val output: List<List<JsonElement>>,
        val errors: List<ErrorInfo>? = null
    )

    @Serializable
    private data class ErrorInfo(
        val code: String,
        val line: Int,
        val col: Int
    )
}