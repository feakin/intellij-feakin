package com.feakin.intellij.highlight

import com.feakin.intellij.lexer.FkElementTypes
import com.intellij.psi.tree.TokenSet

object FkTokenTypeSets {
    var KEY_WORDS = TokenSet.create(
        FkElementTypes.CONTEXT_KEYWORD,
        FkElementTypes.CONTEXT_MAP_KEYWORD,
        FkElementTypes.ENTITY_KEYWORD,
        FkElementTypes.AGGREGATE_KEYWORD,
        FkElementTypes.VALUE_OBJECT_KEYWORD,
        FkElementTypes.STRUCT_KEYWORD,

        // Fk impl API
        FkElementTypes.IMPL_KEYWORD,
        FkElementTypes.ENDPOINT_KEYWORD,
        FkElementTypes.REQUEST_KEYWORD,
        FkElementTypes.RESPONSE_KEYWORD,
        FkElementTypes.AUTH_KEYWORD,

        // http method
        FkElementTypes.GET_KEYWORD,
        FkElementTypes.POST_KEYWORD,
        FkElementTypes.PUT_KEYWORD,
        FkElementTypes.DELETE_KEYWORD,
        FkElementTypes.PATCH_KEYWORD,
        FkElementTypes.HEAD_KEYWORD,
        FkElementTypes.OPTIONS_KEYWORD,

        // flow
        FkElementTypes.VIA_KEYWORD,
        FkElementTypes.SEND_KEYWORD,
        FkElementTypes.TO_KEYWORD,
        FkElementTypes.RECEIVE_KEYWORD,
        FkElementTypes.FLOW_KEYWORD,
    )
}