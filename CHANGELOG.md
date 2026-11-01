# Changelog

All notable changes to this project are documented here. The format is based on
[Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and the project follows
[Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- `HTMLParser.errors` also reports the parse errors of tree construction, including
  `non-void-html-element-start-tag-with-trailing-solidus`

### Changed

- `ParseError.code` is nullable (breaking): the spec gives most tree construction parse errors no dedicated code, so
  theirs is null

## [0.1.0]

First release.

- HTML tokenizer and tree construction following the WHATWG HTML parsing algorithm
- `HTMLParser` for parsing documents into a `TreeNode` tree, with tokenizer parse errors
- Targets JVM, JS, Wasm and Kotlin/Native (Apple, Linux, Windows and Android NDK)

[Unreleased]: https://github.com/Akramhussain4/Helium/compare/v0.1.0...HEAD
[0.1.0]: https://github.com/Akramhussain4/Helium/releases/tag/v0.1.0
