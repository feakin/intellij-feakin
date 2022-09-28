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
    )
}