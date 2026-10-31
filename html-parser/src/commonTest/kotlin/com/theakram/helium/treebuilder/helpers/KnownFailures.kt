/*
 * MIT License
 *
 * Copyright (c) 2023-2026 Mohammed Akram Hussain
 * Website: https://theakram.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.theakram.helium.treebuilder.helpers

/**
 * Tree construction tests that are skipped because the parser doesn't match them yet, with the reason for each.
 * A change that makes a test pass removes its entry, so this list is also the list of remaining work.
 */
internal object KnownFailures {

    private const val PROCESSING_INSTRUCTIONS =
        "Processing instructions aren't supported yet (https://github.com/whatwg/html/commit/320c05f)"

    private const val FORM_POINTER_IN_TEMPLATE =
        "The form element pointer isn't ignored inside template contents (https://github.com/whatwg/html/commit/9ed7f27)"

    private const val SELECTEDCONTENT =
        "selectedcontent is kept up to date by DOM insertion steps, which a parser without a DOM doesn't run"

    private const val ADOPTION_AGENCY_MARKER =
        "The adoption agency algorithm doesn't match the expected tree when a closed marquee's marker remains"

    private val failures = mapOf(
        "tests1.dat:551" to PROCESSING_INSTRUCTIONS,
        "tests1.dat:600" to PROCESSING_INSTRUCTIONS,
        "tests1.dat:636" to PROCESSING_INSTRUCTIONS,
        "html5test-com.dat:129" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:1" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:10" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:20" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:29" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:38" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:48" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:59" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:68" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:77" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:86" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:95" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:105" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:114" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:123" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:132" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:141" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:150" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:160" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:169" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:178" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:187" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:196" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:205" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:214" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:223" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:232" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:241" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:250" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:259" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:268" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:277" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:286" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:295" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:304" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:313" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:322" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:331" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:340" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:349" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:358" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:367" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:376" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:385" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:394" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:403" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:412" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:421" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:430" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:439" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:448" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:457" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:466" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:475" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:484" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:493" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:502" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:511" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:520" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:529" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:538" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:547" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:556" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:565" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:574" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:583" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:907" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:915" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:923" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:931" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:939" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:947" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:964" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:974" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:984" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:994" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:1026" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:1035" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:1044" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:1053" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:1062" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:1071" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:1080" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:1111" to PROCESSING_INSTRUCTIONS,
        "processing-instructions.dat:1122" to PROCESSING_INSTRUCTIONS,
        "template.dat:1724" to FORM_POINTER_IN_TEMPLATE,
        "template.dat:1764" to FORM_POINTER_IN_TEMPLATE,
        "template.dat:1817" to FORM_POINTER_IN_TEMPLATE,
        "webkit02.dat:692" to SELECTEDCONTENT,
        "webkit02.dat:706" to SELECTEDCONTENT,
        "webkit02.dat:732" to SELECTEDCONTENT,
        "webkit02.dat:748" to SELECTEDCONTENT,
        "adoption02.dat:41" to ADOPTION_AGENCY_MARKER
    )

    fun reasonFor(filename: String, lineNumber: Int): String? = failures["$filename:$lineNumber"]
}
