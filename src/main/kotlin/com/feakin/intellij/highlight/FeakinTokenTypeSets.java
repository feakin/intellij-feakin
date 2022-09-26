package com.feakin.intellij.highlight;

import com.feakin.intellij.lexer.FeakinTypes;
import com.intellij.psi.tree.TokenSet;

public class FeakinTokenTypeSets {
    static TokenSet KEY_WORDS = TokenSet
            .create(
                    FeakinTypes.CONTEXT_KEYWORD,
                    FeakinTypes.CONTEXT_MAP_KEYWORD
            );
}
