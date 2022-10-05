package com.feakin.intellij.ide.search

import com.feakin.intellij.lexer.FeakinElementTypes.*
import com.feakin.intellij.lexer.FkLexer
import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.psi.tree.TokenSet

class FkWordScanner : DefaultWordsScanner(
    FkLexer(),
    TokenSet.create(IDENTIFIER),
    TokenSet.create(BLOCK_COMMENT, COMMENT),
    TokenSet.create(STRING_LITERAL)
)
