"""
Regenerates TokenizerTest3.kt from html5lib-tests' tokenizer/test3.test, which has too many cases to translate by
hand. Run it from the repository root after updating the html5lib-tests submodule:

    python3 scripts/generate-tokenizer-test3.py
"""
import json, re, unicodedata

# Invisible, combining and non-character code points are written as escapes so that no editor can change them
def is_invisible(ch):
    return ch != ' ' and unicodedata.category(ch) in ('Zs', 'Cf', 'Mn', 'Me', 'Cc', 'Co', 'Cs', 'Zl', 'Zp')

def escape(ch):
    o = ord(ch)
    if o < 0x10000: return '\\u%04X' % o
    o -= 0x10000
    return '\\u%04X\\u%04X' % (0xD800 + (o >> 10), 0xDC00 + (o & 0x3FF))

def kstr(s):
    out = []
    for ch in s:
        o = ord(ch)
        if ch == '\\': out.append('\\\\')
        elif ch == '"': out.append('\\"')
        elif ch == '$': out.append('\\$')
        elif ch == '\t': out.append('\\t')
        elif ch == '\n': out.append('\\n')
        elif ch == '\r': out.append('\\r')
        elif o < 0x20 or o == 0x7f: out.append('\\u%04x' % o)
        elif is_invisible(ch) or o > 0xFFFF: out.append(escape(ch))
        else: out.append(ch)
    return '"' + ''.join(out) + '"'

def kchar(c):
    o = ord(c)
    if c == '\\': return "'\\\\'"
    if c == "'": return "'\\''"
    if c == '\t': return "'\\t'"
    if c == '\n': return "'\\n'"
    if c == '\r': return "'\\r'"
    if c == '`': return "'\\u0060'"
    if o < 0x20 or o == 0x7f: return "'\\u%04x'" % o
    if is_invisible(c): return "'" + escape(c) + "'"
    return "'" + c + "'"

def name(desc, i):
    c = re.sub(r'[^A-Za-z0-9 ]', '', desc); c = re.sub(r' +', ' ', c).strip()
    if c == '': c = 'test'
    elif c[0].isdigit(): c = 'test ' + c
    return f'{c} {i}'

def token(t):
    kind = t[0]
    if kind == 'Character':
        utf16_length = len(t[1].encode('utf-16-le')) // 2
        return kchar(t[1]).join(['char(', ')']) if utf16_length == 1 else f'*chars({kstr(t[1])}).toTypedArray()'
    if kind == 'Comment':
        return f'comment({kstr(t[1])})'
    if kind == 'EndTag':
        return f'endTag({kstr(t[1])})'
    if kind == 'StartTag':
        args = [f'name = {kstr(t[1])}']
        if len(t) > 2 and t[2]:
            args.append('attributes = mapOf(' + ', '.join(f'{kstr(k)} to {kstr(v)}' for k, v in t[2].items()) + ')')
        if len(t) > 3 and t[3]:
            args.append('selfClosing = true')
        return f'startTag({", ".join(args)})'
    if kind == 'DOCTYPE':
        args = [f'name = {kstr(t[1]) if t[1] is not None else "null"}']
        if t[2] is not None: args.append(f'publicId = {kstr(t[2])}')
        if t[3] is not None: args.append(f'systemId = {kstr(t[3])}')
        if not t[4]: args.append('forceQuirks = true')
        return f'doctype({", ".join(args)})'
    if kind == 'ProcessingInstruction':
        return f'processingInstruction(target = {kstr(t[1])}, data = {kstr(t[2])})'
    raise ValueError(kind)

def generate(tests, header):
    out = [header.rstrip('\n'), '', 'package com.theakram.helium.tokenizer', '', 'import kotlin.test.Test', '', '/**',
           f' * Contains all {len(tests)} test from here:',
           ' * https://github.com/html5lib/html5lib-tests/blob/master/tokenizer/test3.test', ' *', ' */',
           '@Suppress("RemoveRedundantBackticks")', 'internal class TokenizerTest3 : TokenizerTestBase() {']
    for i, t in enumerate(tests):
        toks = ',\n'.join('            ' + token(x) for x in t['output'])
        out += ['', '    @Test', f'    fun `{name(t["description"], i)}`() = assertTokenization(',
                f'        input = {kstr(t["input"])},', '        expectedTokens = listOf(']
        if toks: out.append(toks)
        out += ['        )', '    )']
    out.append('}')
    return '\n'.join(out) + '\n'

TEST_DATA = 'html-parser/src/commonTest/resources/html5lib-tests/tokenizer/test3.test'
OUTPUT = 'html-parser/src/commonTest/kotlin/com/theakram/helium/tokenizer/TokenizerTest3.kt'

if __name__ == '__main__':
    tests = json.load(open(TEST_DATA, encoding='utf-8'))['tests']
    # Keep the license header of the file being replaced
    existing = open(OUTPUT, encoding='utf-8').read()
    header = existing[:existing.index('\npackage ') + 1]
    with open(OUTPUT, 'w', encoding='utf-8') as out:
        out.write(generate(tests, header))
    print(f'Wrote {len(tests)} tests to {OUTPUT}')
