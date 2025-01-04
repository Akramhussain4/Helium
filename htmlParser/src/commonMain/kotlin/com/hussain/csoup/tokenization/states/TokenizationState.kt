package com.hussain.csoup.tokenization.states

import com.hussain.csoup.tokenization.Tokenizer

internal interface TokenizationState {
    fun consume(codePoint: Int, tokenizer: Tokenizer): StateTransition
}

internal data class StateTransition(
    val nextState: TokenizationState,
    val action: Action
)

internal sealed interface Action {
    data object ConsumeNext : Action
    data object Reconsume : Action
    data object None : Action
}

internal fun consumeNextChar(switchTo: () -> TokenizationState) = StateTransition(
    nextState = switchTo(),
    action = Action.ConsumeNext
)

internal fun reconsumeCurrentChar(switchTo: () -> TokenizationState) = StateTransition(
    nextState = switchTo(),
    action = Action.Reconsume
)

internal fun endOfFile(switchTo: () -> TokenizationState) = StateTransition(
    nextState = switchTo(),
    action = Action.None
)


//internal data object RcData : TokenizationState()
//internal data object RawText : TokenizationState()
//internal data object ScriptData : TokenizationState()
//internal data object PlainText : TokenizationState()
//internal data object RcDataLessThanSign : TokenizationState()
//internal data object RcDataEndTagOpen : TokenizationState()
//internal data object RcDataEndTagName : TokenizationState()
//internal data object RawTextLessThanSign : TokenizationState()
//internal data object RawTextEndTagOpen : TokenizationState()
//internal data object RawTextEndTagName : TokenizationState()
//internal data object ScriptDataLessThanSign : TokenizationState()
//internal data object ScriptDataEndTagOpen : TokenizationState()
//internal data object ScriptDataEndTagName : TokenizationState()
//internal data object ScriptDataEscapeStart : TokenizationState()
//internal data object ScriptDataEscapeStartDash : TokenizationState()
//internal data object ScriptDataEscaped : TokenizationState()
//internal data object ScriptDataEscapedDash : TokenizationState()
//internal data object ScriptDataEscapedDashDash : TokenizationState()
//internal data object ScriptDataEscapedLessThanSign : TokenizationState()
//internal data object ScriptDataEscapedEndTagOpen : TokenizationState()
//internal data object ScriptDataEscapedEndTagName : TokenizationState()
//internal data object ScriptDataDoubleEscapeStart : TokenizationState()
//internal data object ScriptDataDoubleEscaped : TokenizationState()
//internal data object ScriptDataDoubleEscapedDash : TokenizationState()
//internal data object ScriptDataDoubleEscapedDashDash : TokenizationState()
//internal data object ScriptDataDoubleEscapedLessThanSign : TokenizationState()
//internal data object ScriptDataDoubleEscapeEnd : TokenizationState()
//internal data object CommentStart : TokenizationState()
//internal data object CommentStartDash : TokenizationState()
//internal data object Comment : TokenizationState()
//internal data object CommentLessThanSign : TokenizationState()
//internal data object CommentLessThanSignBang : TokenizationState()
//internal data object CommentLessThanSignBangDash : TokenizationState()
//internal data object CommentLessThanSignBangDashDash : TokenizationState()
//internal data object CommentEndDash : TokenizationState()
//internal data object CommentEnd : TokenizationState()
//internal data object CommentEndBang : TokenizationState()
//internal data object DocType : TokenizationState()
//internal data object BeforeDocTypeName : TokenizationState()
//internal data object DocTypeName : TokenizationState()
//internal data object AfterDocTypeName : TokenizationState()
//internal data object AfterDocTypePublicKeyword : TokenizationState()
//internal data object BeforeDocTypePublicIdentifier : TokenizationState()
//internal data object DocTypePublicIdentifierDoubleQuoted : TokenizationState()
//internal data object DocTypePublicIdentifierSingleQuoted : TokenizationState()
//internal data object AfterDocTypePublicIdentifier : TokenizationState()
//internal data object BetweenDocTypePublicAndSystemIdentifiers : TokenizationState()
//internal data object AfterDocTypeSystemKeyword : TokenizationState()
//internal data object BeforeDocTypeSystemIdentifier : TokenizationState()
//internal data object DocTypeSystemIdentifierDoubleQuoted : TokenizationState()
//internal data object DocTypeSystemIdentifierSingleQuoted : TokenizationState()
//internal data object AfterDocTypeSystemIdentifier : TokenizationState()
//internal data object BogusDocType : TokenizationState()
//internal data object CDataSection : TokenizationState()
//internal data object CDataSectionBracket : TokenizationState()
//internal data object CDataSectionEnd : TokenizationState()