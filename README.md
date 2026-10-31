# Helium

[![Maven Central](https://img.shields.io/maven-central/v/com.theakram/helium)](https://central.sonatype.com/artifact/com.theakram/helium)
[![CI](https://github.com/Akramhussain4/Helium/actions/workflows/ci.yml/badge.svg)](https://github.com/Akramhussain4/Helium/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

An HTML parser for Kotlin Multiplatform, written in pure Kotlin with no dependencies, that follows the
[WHATWG HTML parsing algorithm](https://html.spec.whatwg.org/multipage/parsing.html). It parses documents the way
browsers do, recovering from malformed markup instead of rejecting it.

## Installation

Helium is published to Maven Central. In a multiplatform project, add it to `commonMain`:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("com.theakram:helium:0.1.0")
        }
    }
}
```

On the JVM or Android, add it like any other dependency:

```kotlin
dependencies {
    implementation("com.theakram:helium:0.1.0")
}
```

### Supported platforms

| Platform     | Targets                                                                                       |
|--------------|-----------------------------------------------------------------------------------------------|
| JVM          | `jvm` (Java 11+, usable from Android)                                                         |
| JavaScript   | `js` (browser and Node.js)                                                                    |
| WebAssembly  | `wasmJs` (browser and Node.js)                                                                |
| Apple        | `iosArm64`, `iosSimulatorArm64`, `macosArm64`, `tvosArm64`, `tvosSimulatorArm64`, `watchosArm32`, `watchosArm64`, `watchosDeviceArm64`, `watchosSimulatorArm64` |
| Linux        | `linuxX64`, `linuxArm64`                                                                      |
| Windows      | `mingwX64`                                                                                    |
| Android NDK  | `androidNativeArm32`, `androidNativeArm64`, `androidNativeX86`, `androidNativeX64`            |

## Usage

```kotlin
val parser = HTMLParser()
val document = parser.parse("<title>Hello</title><p class=greeting>Hi <b>there</b>")

// Missing <html>, <head> and <body> elements are created, just as a browser would
val body = document.findChildElement("html")?.findChildElement("body")
val paragraph = body?.findChildElement("p")
paragraph?.getAttribute("class") // "greeting"

println(document.toTreeString())
```

```
| #document
|   <html>
|     <head>
|       <title>
|         "Hello"
|     <body>
|       <p>
|         class="greeting"
|         "Hi "
|         <b>
|           "there"
```

Each node is a `TreeNode` with a `type` (element, text, comment, doctype, …), `children`, `parent` and sibling
links, and for elements a `localName`, `namespace` and `attributes`. SVG and MathML content is placed in its own
namespace (see `Namespaces`).

Malformed markup doesn't need special handling, but problems the tokenizer detects are available afterwards, using
the spec's error codes:

```kotlin
parser.parse("<p>&notanentity</p>")
parser.errors // [ParseError(missing-semicolon-after-character-reference at position 4)]
```

A parser can be reused: every call to `parse` returns a new, independent document. Instances aren't thread-safe.

To parse markup as the contents of an element, the way setting `innerHTML` does, use `parseFragment` with that
element as the context. The context decides how the markup is read, so table rows parse inside a `<tbody>`:

```kotlin
val context = TreeNode.createElement(localName = "tbody")
val rows = parser.parseFragment("<tr><td>1<td>2", context) // [<tr>]
```

By default, Helium parses like a browser with JavaScript enabled, so `<noscript>` content is raw text. To parse it
as markup instead, as a browser with JavaScript disabled does, create the parser with `HTMLParser(scriptingEnabled = false)`.

## Status

Helium is pre-1.0 and its API may change between minor versions.

Conformance is measured against the standard conformance suite for HTML parsers, which started in
[html5lib-tests](https://github.com/html5lib/html5lib-tests); its tree construction tests now live in
[web-platform-tests](https://github.com/web-platform-tests/wpt/tree/master/html/syntax/parsing/resources):

- **Tokenization:** produces the expected tokens for the html5lib tokenizer tests. The reported parse errors aren't
  checked against the suite yet.
- **Tree construction:** passes 3,888 of the 3,898 test runs (99%), document and fragment tests alike, each with
  scripting enabled and disabled where the test allows both. The rest are listed with their reasons in
  [`KnownFailures`](html-parser/src/commonTest/kotlin/com/theakram/helium/treebuilder/helpers/KnownFailures.kt).
  The `scripted_*` tests aren't run, since they need a JavaScript engine.

Not supported yet:

- Complete error reporting: `errors` only contains tokenizer errors, and not all of those yet
- Serializing a tree back to HTML

## Building

The tokenizer tests use html5lib-tests as a git submodule, so clone with submodules:

```shell
git clone --recurse-submodules https://github.com/Akramhussain4/Helium.git
./gradlew check
```

`check` runs the tests on every target the host can run. Running the Apple tests on macOS needs Xcode, not just the
Command Line Tools. It also fails if the public API changed without an
updated API dump; run `./gradlew updateKotlinAbi` to update it after an intentional change.

The tree construction tests are copied from web-platform-tests into `html-parser/src/commonTest/resources/tree-construction`.
To update them, run `scripts/update-tree-construction-tests.sh` with a web-platform-tests commit. After updating the
html5lib-tests submodule, regenerate `TokenizerTest3` with `python3 scripts/generate-tokenizer-test3.py`.

## License

Helium is available under the [MIT License](LICENSE). It includes data from the WHATWG HTML Living Standard; see
[THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md).
