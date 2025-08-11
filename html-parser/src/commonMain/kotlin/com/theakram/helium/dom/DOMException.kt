package com.theakram.helium.dom

/**
 * Represents an exceptional event that occurs as a result of calling a method or accessing a property of a web API.
 * This is how errors are signaled in DOM APIs.
 *
 * Spec: https://webidl.spec.whatwg.org/#idl-DOMException
 */
public class DOMException(
    override val message: String = "",
    public val name: String = "Error"
) : Exception(message) {

    public val code: Short
        get() = nameToCodeMap[name] ?: 0

    public companion object {
        // Legacy exception codes
        public const val INDEX_SIZE_ERR: Short = 1
        public const val DOMSTRING_SIZE_ERR: Short = 2
        public const val HIERARCHY_REQUEST_ERR: Short = 3
        public const val WRONG_DOCUMENT_ERR: Short = 4
        public const val INVALID_CHARACTER_ERR: Short = 5
        public const val NO_DATA_ALLOWED_ERR: Short = 6
        public const val NO_MODIFICATION_ALLOWED_ERR: Short = 7
        public const val NOT_FOUND_ERR: Short = 8
        public const val NOT_SUPPORTED_ERR: Short = 9
        public const val INUSE_ATTRIBUTE_ERR: Short = 10
        public const val INVALID_STATE_ERR: Short = 11
        public const val SYNTAX_ERR: Short = 12
        public const val INVALID_MODIFICATION_ERR: Short = 13
        public const val NAMESPACE_ERR: Short = 14
        public const val INVALID_ACCESS_ERR: Short = 15
        public const val VALIDATION_ERR: Short = 16
        public const val TYPE_MISMATCH_ERR: Short = 17
        public const val SECURITY_ERR: Short = 18
        public const val NETWORK_ERR: Short = 19
        public const val ABORT_ERR: Short = 20
        public const val URL_MISMATCH_ERR: Short = 21
        public const val QUOTA_EXCEEDED_ERR: Short = 22
        public const val TIMEOUT_ERR: Short = 23
        public const val INVALID_NODE_TYPE_ERR: Short = 24
        public const val DATA_CLONE_ERR: Short = 25

        private val nameToCodeMap = mapOf(
            "IndexSizeError" to INDEX_SIZE_ERR,
            "HierarchyRequestError" to HIERARCHY_REQUEST_ERR,
            "WrongDocumentError" to WRONG_DOCUMENT_ERR,
            "InvalidCharacterError" to INVALID_CHARACTER_ERR,
            "NoDataAllowedError" to NO_DATA_ALLOWED_ERR,
            "NoModificationAllowedError" to NO_MODIFICATION_ALLOWED_ERR,
            "NotFoundError" to NOT_FOUND_ERR,
            "NotSupportedError" to NOT_SUPPORTED_ERR,
            "InUseAttributeError" to INUSE_ATTRIBUTE_ERR,
            "InvalidStateError" to INVALID_STATE_ERR,
            "SyntaxError" to SYNTAX_ERR,
            "InvalidModificationError" to INVALID_MODIFICATION_ERR,
            "NamespaceError" to NAMESPACE_ERR,
            "InvalidAccessError" to INVALID_ACCESS_ERR,
            "SecurityError" to SECURITY_ERR,
            "NetworkError" to NETWORK_ERR,
            "AbortError" to ABORT_ERR,
            "URLMismatchError" to URL_MISMATCH_ERR,
            "QuotaExceededError" to QUOTA_EXCEEDED_ERR,
            "TimeoutError" to TIMEOUT_ERR,
            "InvalidNodeTypeError" to INVALID_NODE_TYPE_ERR,
            "DataCloneError" to DATA_CLONE_ERR
        )
    }
}