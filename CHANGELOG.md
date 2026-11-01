# Changelog

All notable changes to this project are documented here. The format is based on
[Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and the project follows
[Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- `HTMLParser.errors` also reports the parse errors of tree construction, including
  `non-void-html-element-start-tag-with-trailing-solidus`
- `TreeNode.setAttributeNS(namespace, qualifiedName, value)`, as the DOM defines it

### Changed

- `TreeNode.createElement` accepts a null namespace, for elements in no namespace
- `TreeNode`'s attribute methods match names as the DOM does: `getAttribute`, `hasAttribute` and `setAttribute`
  compare qualified names, ASCII lowercasing the argument only on HTML elements, and the `NS` methods compare
  namespace and local name exactly. `setAttribute` changes an existing attribute in place and throws
  `IllegalArgumentException` for a name that is not a valid attribute local name
- `TreeNode.setAttributeNS(namespace, prefix, localName, value)` is the DOM's "set an attribute value": it matches an
  existing attribute by namespace and local name exactly and changes its value in place, keeping its prefix, instead
  of replacing a case-insensitively matching one
- `ParseError.code` is nullable (breaking): the spec gives most tree construction parse errors no dedicated code, so
  theirs is null

## [0.1.0]

First release.

- HTML tokenizer and tree construction following the WHATWG HTML parsing algorithm
- `HTMLParser` for parsing documents into a `TreeNode` tree, with tokenizer parse errors
- Targets JVM, JS, Wasm and Kotlin/Native (Apple, Linux, Windows and Android NDK)

[Unreleased]: https://github.com/Akramhussain4/Helium/compare/v0.1.0...HEAD
[0.1.0]: https://github.com/Akramhussain4/Helium/releases/tag/v0.1.0
