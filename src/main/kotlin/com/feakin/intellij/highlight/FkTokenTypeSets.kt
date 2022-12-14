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
        FkElementTypes.DOMAIN_EVENT_KEYWORD,

        // Fk impl API
        FkElementTypes.IMPL_KEYWORD,
        FkElementTypes.ENDPOINT_KEYWORD,
        FkElementTypes.REQUEST_KEYWORD,
        FkElementTypes.RESPONSE_KEYWORD,
        FkElementTypes.AUTH_KEYWORD,

        // flow
        FkElementTypes.VIA_KEYWORD,
        FkElementTypes.SEND_KEYWORD,
        FkElementTypes.TO_KEYWORD,
        FkElementTypes.RECEIVE_KEYWORD,
        FkElementTypes.FLOW_KEYWORD,

        //layered
        FkElementTypes.LAYERED_KEYWORD,
        FkElementTypes.LAYER_KEYWORD,
        FkElementTypes.DEPENDENCY_KEYWORD,
        FkElementTypes.AGGREGATE_S_KEYWORD,
        FkElementTypes.ENTITY_S_KEYWORD,

        //sourceSet
        FkElementTypes.SOURCE_SET_KEYWORD,
        FkElementTypes.WHEN_KEYWORD,
        FkElementTypes.IS_KEYWORD,
        FkElementTypes.VAR_KEYWORD,
        FkElementTypes.DEF_KEYWORD,

        //env
        FkElementTypes.ENV_KEYWORD,
        FkElementTypes.DATASOURCE_KEYWORD,
        FkElementTypes.SERVER_KEYWORD,

        FkElementTypes.CUSTOM_KEYWORD
    )
}