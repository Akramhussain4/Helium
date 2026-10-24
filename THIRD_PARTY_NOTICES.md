# Third-Party Notices

Helium is licensed under the MIT License (see [LICENSE](LICENSE)). It includes
or is derived from the following third-party material.

## WHATWG HTML Living Standard

- **Used for:** the named character reference table
  (`html-parser/src/commonMain/kotlin/com/theakram/helium/tokenization/util/CharRefMap.kt`),
  the numeric character reference replacement table, and algorithm steps quoted
  in source comments.
- **Source:** https://html.spec.whatwg.org/
- **Copyright:** © WHATWG (Apple, Google, Mozilla, Microsoft)
- **License:** [Creative Commons Attribution 4.0 International (CC BY 4.0)](https://creativecommons.org/licenses/by/4.0/)

The data has been converted to Kotlin source; its content is otherwise unchanged.

## html5lib-tests

- **Used for:** the tokenizer and tree-construction test suites. It is included as
  a git submodule at `html-parser/src/commonTest/resources/html5lib-tests`; it is
  used only by tests and is not part of the published library.
- **Source:** https://github.com/html5lib/html5lib-tests
- **Copyright:** © 2006-2013 James Graham, Geoffrey Sneddon, and other contributors
- **License:** MIT (see the `LICENSE` file in the submodule)

Several tokenizer test classes in `html-parser/src/commonTest` are Kotlin
translations of html5lib-tests test cases and are covered by the same license.
