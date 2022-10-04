package com.feakin.intellij.highlight

import com.feakin.intellij.lexer.FeakinElementTypes
import com.intellij.psi.tree.TokenSet

object FeakinTokenTypeSets {
    var KEY_WORDS = TokenSet.create(
        FeakinElementTypes.CONTEXT_KEYWORD,
        FeakinElementTypes.CONTEXT_MAP_KEYWORD,
        FeakinElementTypes.ENTITY_KEYWORD,
        FeakinElementTypes.AGGREGATE_KEYWORD,
        FeakinElementTypes.VALUE_OBJECT_KEYWORD,
        FeakinElementTypes.STRUCT_KEYWORD,

        // Feakin impl API
        FeakinElementTypes.IMPL_KEYWORD,
        FeakinElementTypes.ENDPOINT_KEYWORD,
        FeakinElementTypes.REQUEST_KEYWORD,
        FeakinElementTypes.RESPONSE_KEYWORD,
        FeakinElementTypes.AUTH_KEYWORD,

        // http method
        FeakinElementTypes.GET_KEYWORD,
        FeakinElementTypes.POST_KEYWORD,
        FeakinElementTypes.PUT_KEYWORD,
        FeakinElementTypes.DELETE_KEYWORD,
        FeakinElementTypes.PATCH_KEYWORD,
        FeakinElementTypes.HEAD_KEYWORD,
        FeakinElementTypes.OPTIONS_KEYWORD,

        // flow
        FeakinElementTypes.VIA_KEYWORD,
        FeakinElementTypes.SEND_KEYWORD,
        FeakinElementTypes.TO_KEYWORD,
        FeakinElementTypes.RECEIVE_KEYWORD,
        FeakinElementTypes.FLOW_KEYWORD,
//        FeakinElementTypes.FROM_KEYWORD,
    )
}