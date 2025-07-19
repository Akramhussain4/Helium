package com.theakram.helium.tokenizer

import kotlin.test.Test

/**
 * Contains all 69 tests from here:
 * https://github.com/html5lib/html5lib-tests/blob/master/tokenizer/test1.test
 *
 * The commented (35) test cases are for Tokenization states that haven't been implemented yet.
 */
internal class TokenizerTest1 : TokenizerTestBase() {

//    @Test
//    fun `Correct Doctype lowercase`() = assertTokenization(
//        input = "<!DOCTYPE html>",
//        expectedTokens = listOf(
//            Token.DOCTYPE(name = "html", publicId = null, systemId = null, forceQuirks = true)
//        )
//    )

//    @Test
//    fun `Correct Doctype uppercase`() = assertTokenization(
//        input = "<!DOCTYPE HTML>",
//        expectedTokens = listOf(
//            Token.DOCTYPE(name = "html", publicId = null, systemId = null, forceQuirks = true)
//        )
//    )

//    @Test
//    fun `Correct Doctype mixed case`() = assertTokenization(
//        input = "<!DOCTYPE HtMl>",
//        expectedTokens = listOf(
//            Token.DOCTYPE(name = "html", publicId = null, systemId = null, forceQuirks = true)
//        )
//    )

//    @Test
//    fun `Correct Doctype case with EOF`() = assertTokenization(
//        input = "<!DOCTYPE HtMl",
//        expectedTokens = listOf(
//            Token.DOCTYPE(name = "html", publicId = null, systemId = null, forceQuirks = false)
//        )
//    )

//    @Test
//    fun `Truncated doctype start`() = assertTokenization(
//        input = "<!DOC>",
//        expectedTokens = listOf(
//            Token.Comment(data = "DOC")
//        )
//    )

//    @Test
//    fun `Doctype in error`() = assertTokenization(
//        input = "<!DOCTYPE foo>",
//        expectedTokens = listOf(
//            Token.DOCTYPE(name = "foo", publicId = null, systemId = null, forceQuirks = true)
//        )
//    )

    @Test
    fun `Single Start Tag`() = assertTokenization(
        input = "<h>",
        expectedTokens = listOf(
            startTag(name = "h")
        )
    )

    @Test
    fun `Empty end tag`() = assertTokenization(
        input = "</>",
        expectedTokens = emptyList()
    )

    @Test
    fun `Empty start tag`() = assertTokenization(
        input = "<>",
        expectedTokens = chars("<>")
    )

    @Test
    fun `Start Tag w-attribute`() = assertTokenization(
        input = "<h a='b'>",
        expectedTokens = listOf(
            startTag(name = "h", attributes = mapOf("a" to "b"))
        )
    )

    @Test
    fun `Start Tag w-attribute no quotes`() = assertTokenization(
        input = "<h a=b>",
        expectedTokens = listOf(
            startTag(name = "h", attributes = mapOf("a" to "b"))
        )
    )

    @Test
    fun `Start-End Tag`() = assertTokenization(
        input = "<h></h>",
        expectedTokens = listOf(
            startTag(name = "h"),
            endTag("h")
        )
    )

    @Test
    fun `Two unclosed start tags`() = assertTokenization(
        input = "<p>One<p>Two",
        expectedTokens = listOf(
            startTag(name = "p"),
            *chars("One").toTypedArray(),
            startTag(name = "p"),
            *chars("Two").toTypedArray()
        )
    )

    @Test
    fun `End Tag w-attribute`() = assertTokenization(
        input = "<h></h a='b'>",
        expectedTokens = listOf(
            startTag(name = "h"),
            endTag("h")
        )
    )

    @Test
    fun `Multiple atts`() = assertTokenization(
        input = "<h a='b' c='d'>",
        expectedTokens = listOf(
            startTag(name = "h", attributes = mapOf("a" to "b", "c" to "d"))
        )
    )

    @Test
    fun `Multiple atts no space`() = assertTokenization(
        input = "<h a='b'c='d'>",
        expectedTokens = listOf(
            startTag(name = "h", attributes = mapOf("a" to "b", "c" to "d"))
        )
    )

    @Test
    fun `Repeated attr`() = assertTokenization(
        input = "<h a='b' a='d'>",
        expectedTokens = listOf(
            startTag(name = "h", attributes = mapOf("a" to "b"))
        )
    )

//    @Test
//    fun `Simple comment`() = assertTokenization(
//        input = "<!--comment-->",
//        expectedTokens = listOf(
//            comment("comment")
//        )
//    )

//    @Test
//    fun `Comment Central dash no space`() = assertTokenization(
//        input = "<!----->",
//        expectedTokens = listOf(
//            comment("-")
//        )
//    )

//    @Test
//    fun `Comment two central dashes`() = assertTokenization(
//        input = "<!-- --comment -->",
//        expectedTokens = listOf(
//            comment(" --comment ")
//        )
//    )

//    @Test
//    fun `Comment central less-than bang`() = assertTokenization(
//        input = "<!--<!-->",
//        expectedTokens = listOf(
//            comment("<!")
//        )
//    )

//    @Test
//    fun `Unfinished comment`() = assertTokenization(
//        input = "<!--comment",
//        expectedTokens = listOf(
//            comment("comment")
//        )
//    )

//    @Test
//    fun `Unfinished comment after start of nested comment`() = assertTokenization(
//        input = "<!-- <!--",
//        expectedTokens = listOf(
//            comment(" <!")
//        )
//    )

//    @Test
//    fun `Start of a comment`() = assertTokenization(
//        input = "<!-",
//        expectedTokens = listOf(
//            comment("-")
//        )
//    )

//    @Test
//    fun `Short comment`() = assertTokenization(
//        input = "<!-->",
//        expectedTokens = listOf(
//            comment("")
//        )
//    )

//    @Test
//    fun `Short comment two`() = assertTokenization(
//        input = "<!--->",
//        expectedTokens = listOf(
//            comment("")
//        )
//    )

//    @Test
//    fun `Short comment three`() = assertTokenization(
//        input = "<!---->",
//        expectedTokens = listOf(
//            comment("")
//        )
//    )

//    @Test
//    fun `less than in comment`() = assertTokenization(
//        input = "<!-- <test-->",
//        expectedTokens = listOf(
//            comment(" <test")
//        )
//    )

//    @Test
//    fun `less than x2 in comment`() = assertTokenization(
//        input = "<!--<<-->",
//        expectedTokens = listOf(
//            comment("<<")
//        )
//    )

//    @Test
//    fun `less than ! in comment`() = assertTokenization(
//        input = "<!-- <!test-->",
//        expectedTokens = listOf(
//            comment(" <!test")
//        )
//    )

//    @Test
//    fun `less than !- in comment`() = assertTokenization(
//        input = "<!-- <!-test-->",
//        expectedTokens = listOf(
//            comment(" <!-test")
//        )
//    )

//    @Test
//    fun `Nested comment`() = assertTokenization(
//        input = "<!-- <!--test-->",
//        expectedTokens = listOf(
//            comment(" <!--test")
//        )
//    )

//    @Test
//    fun `Nested comment with extra less than`() = assertTokenization(
//        input = "<!-- <<!--test-->",
//        expectedTokens = listOf(
//            comment(" <<!--test")
//        )
//    )

//    @Test
//    fun `less than in script data`() = assertTokenization(
//        input = "<test-->",
//        expectedTokens = chars("<test-->")
//    )

//    @Test
//    fun `less than! in script data`() = assertTokenization(
//        input = "<!test-->",
//        expectedTokens = chars("<!test-->")
//    )

//    @Test
//    fun `less than!- in script data`() = assertTokenization(
//        input = "<!-test-->",
//        expectedTokens = chars("<!-test-->")
//    )

//    @Test
//    fun `Escaped script data`() = assertTokenization(
//        input = "<!--test-->",
//        expectedTokens = chars("<!--test-->")
//    )

//    @Test
//    fun `less than in script HTML comment`() = assertTokenization(
//        input = "<!-- < test -->",
//        expectedTokens = chars("<!-- < test -->")
//    )

//    @Test
//    fun `less than- in script HTML comment`() = assertTokenization(
//        input = "<!-- </ test -->",
//        expectedTokens = chars("<!-- </ test -->")
//    )

//    @Test
//    fun `Start tag in script HTML comment`() = assertTokenization(
//        input = "<!-- <test> -->",
//        expectedTokens = chars("<!-- <test> -->")
//    )

//    @Test
//    fun `End tag in script HTML comment`() = assertTokenization(
//        input = "<!-- </test> -->",
//        expectedTokens = chars("<!-- </test> -->")
//    )

//    @Test
//    fun `- in script HTML comment double escaped`() = assertTokenization(
//        input = "<!--<script>-</script>-->",
//        expectedTokens = chars("<!--<script>-</script>-->")
//    )

//    @Test
//    fun `-- in script HTML comment double escaped`() = assertTokenization(
//        input = "<!--<script>--</script>-->",
//        expectedTokens = chars("<!--<script>--</script>-->")
//    )

//    @Test
//    fun `--- in script HTML comment double escaped`() = assertTokenization(
//        input = "<!--<script>---</script>-->",
//        expectedTokens = chars("<!--<script>---</script>-->")
//    )

//    @Test
//    fun `- spaced in script HTML comment double escaped`() = assertTokenization(
//        input = "<!--<script> - </script>-->",
//        expectedTokens = chars("<!--<script> - </script>-->")
//    )

//    @Test
//    fun `-- spaced in script HTML comment double escaped`() = assertTokenization(
//        input = "<!--<script> -- </script>-->",
//        expectedTokens = chars("<!--<script> -- </script>-->")
//    )

    @Test
    fun `Ampersand EOF`() = assertTokenization(
        input = "&",
        expectedTokens = chars("&")
    )

    @Test
    fun `Ampersand ampersand EOF`() = assertTokenization(
        input = "&&",
        expectedTokens = chars("&&")
    )

    @Test
    fun `Ampersand space EOF`() = assertTokenization(
        input = "& ",
        expectedTokens = chars("& ")
    )

    @Test
    fun `Unfinished entity`() = assertTokenization(
        input = "&f",
        expectedTokens = chars("&f")
    )

    @Test
    fun `Ampersand number sign`() = assertTokenization(
        input = "&#",
        expectedTokens = chars("&#")
    )

    @Test
    fun `Unfinished numeric entity`() = assertTokenization(
        input = "&#x",
        expectedTokens = chars("&#x")
    )

    @Test
    fun `Entity with trailing semicolon 1`() = assertTokenization(
        input = "I'm &not;it",
        expectedTokens = chars("I'm \u00ACit")
    )

    @Test
    fun `Entity with trailing semicolon 2`() = assertTokenization(
        input = "I'm &notin;",
        expectedTokens = chars("I'm \u2209")
    )

    @Test
    fun `Entity without trailing semicolon 1`() = assertTokenization(
        input = "I'm &notit",
        expectedTokens = chars("I'm \u00ACit")
    )

    @Test
    fun `Entity without trailing semicolon 2`() = assertTokenization(
        input = "I'm &notin",
        expectedTokens = chars("I'm \u00ACin")
    )

    @Test
    fun `Partial entity match at end of file`() = assertTokenization(
        input = "I'm &no",
        expectedTokens = chars("I'm &no")
    )

    @Test
    fun `Non-ASCII character reference name`() = assertTokenization(
        input = "&\u00AC;",
        expectedTokens = chars("&\u00AC;")
    )

    @Test
    fun `ASCII decimal entity`() = assertTokenization(
        input = "&#0036;",
        expectedTokens = chars("$")
    )

    @Test
    fun `ASCII hexadecimal entity`() = assertTokenization(
        input = "&#x3f;",
        expectedTokens = chars("?")
    )

    @Test
    fun `Hexadecimal entity in attribute`() = assertTokenization(
        input = "<h a='&#x3f;'></h>",
        expectedTokens = listOf(
            startTag(name = "h", attributes = mapOf("a" to "?")),
            endTag("h")
        )
    )

    @Test
    fun `Entity in attribute without semicolon ending in x`() = assertTokenization(
        input = "<h a='&notx'>",
        expectedTokens = listOf(
            startTag(name = "h", attributes = mapOf("a" to "&notx"))
        )
    )

    @Test
    fun `Entity in attribute without semicolon ending in 1`() = assertTokenization(
        input = "<h a='&not1'>",
        expectedTokens = listOf(
            startTag(name = "h", attributes = mapOf("a" to "&not1"))
        )
    )

    @Test
    fun `Entity in attribute without semicolon ending in i`() = assertTokenization(
        input = "<h a='&noti'>",
        expectedTokens = listOf(
            startTag(name = "h", attributes = mapOf("a" to "&noti"))
        )
    )

    @Test
    fun `Entity in attribute without semicolon`() = assertTokenization(
        input = "<h a='&COPY'>",
        expectedTokens = listOf(
            startTag(name = "h", attributes = mapOf("a" to "\u00A9"))
        )
    )

    @Test
    fun `Unquoted attribute ending in ampersand`() = assertTokenization(
        input = "<s o=& t>",
        expectedTokens = listOf(
            startTag(name = "s", attributes = mapOf("o" to "&", "t" to ""))
        )
    )

    @Test
    fun `Unquoted attribute at end of tag with final character of and with tag followed by characters`() = assertTokenization(
        input = "<a a=a&>foo",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "a&")),
            *chars("foo").toTypedArray()
        )
    )

    @Test
    fun `plaintext element`() = assertTokenization(
        input = "<plaintext>foobar",
        expectedTokens = listOf(
            startTag(name = "plaintext"),
            *chars("foobar").toTypedArray()
        )
    )

    @Test
    fun `Open angled bracket in unquoted attribute value state`() = assertTokenization(
        input = "<a a=f<>",
        expectedTokens = listOf(
            startTag(name = "a", attributes = mapOf("a" to "f<"))
        )
    )
}