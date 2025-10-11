/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
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

package com.theakram.helium.tree

/**
 * Document quirks mode as defined by the WHATWG HTML specification.
 *
 * The quirks mode affects how CSS is rendered and how certain HTML constructs are parsed.
 * It's determined by the DOCTYPE declaration at the beginning of the document.
 *
 * Spec references:
 * - Quirks mode: https://dom.spec.whatwg.org/#concept-document-quirks
 */
public enum class QuirksMode {
    /**
     * No-quirks mode (standards mode).
     * Modern DOCTYPE declarations trigger this mode.
     */
    NO_QUIRKS,

    /**
     * Quirks mode.
     * Triggered by missing or old DOCTYPE declarations.
     * Emulates legacy browser behavior for backward compatibility.
     */
    QUIRKS,

    /**
     * Limited-quirks mode.
     * Triggered by certain transitional DOCTYPEs.
     * A hybrid between no-quirks and quirks mode.
     */
    LIMITED_QUIRKS
}
