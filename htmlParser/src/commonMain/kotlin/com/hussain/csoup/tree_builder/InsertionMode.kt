package com.hussain.csoup.tree_builder

import com.hussain.csoup.tokenization.Token

internal interface InsertionMode {
    fun process(token: Token, builder: TreeBuilder)
}

//internal data object BeforeHTML : InsertionMode
//internal data object BeforeHead : InsertionMode
//internal data object InHead : InsertionMode
//internal data object InHeadNoScript : InsertionMode
//internal data object AfterHead : InsertionMode
//internal data object InBody : InsertionMode
//internal data object Text : InsertionMode
//internal data object InTable : InsertionMode
//internal data object InTableText : InsertionMode
//internal data object InCaption : InsertionMode
//internal data object InColumnGroup : InsertionMode
//internal data object InTableBody : InsertionMode
//internal data object InRow : InsertionMode
//internal data object InCell : InsertionMode
//internal data object InSelect : InsertionMode
//internal data object InSelectInTable : InsertionMode
//internal data object AfterBody : InsertionMode
//internal data object InFrameSet : InsertionMode
//internal data object AfterFrameSet : InsertionMode
//internal data object AfterAfterBody : InsertionMode
//internal data object AfterAfterFrameSet : InsertionMode