package com.feakin.intellij.highlight

import com.feakin.intellij.lexer.FeakinTypes
import com.intellij.psi.tree.TokenSet

object FeakinTokenTypeSets {
    var KEY_WORDS = TokenSet
        .create(
            FeakinTypes.CONTEXT_KEYWORD,
            FeakinTypes.CONTEXT_MAP_KEYWORD
        )
}