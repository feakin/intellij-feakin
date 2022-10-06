/*
 *
 * Copyright (c) 2015 Aleksey Kladov, Evgeny Kurbatsky, Alexey Kudinkin and contributors
 * Copyright (c) 2016 JetBrains
 *
 */

package com.feakin.intellij.formatter

import com.feakin.intellij.FkLanguage
import com.feakin.intellij.lexer.FkElementTypes
import com.intellij.formatting.SpacingBuilder
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet

private inline fun SpacingBuilder.applyForEach(
    tokenSet: TokenSet, block: SpacingBuilder.(IElementType) -> SpacingBuilder
): SpacingBuilder {
    var self = this
    for (tt in tokenSet.types) {
        self = block(this, tt)
    }
    return self
}

fun createSpaceBuilder(settings: CodeStyleSettings): SpacingBuilder {
    return SpacingBuilder(settings, FkLanguage)
        .after(FkElementTypes.COMMA).spacing(1, 1, 0, true, 0)
        .before(FkElementTypes.COMMA).spaceIf(false)
        .after(FkElementTypes.COLON).spaceIf(true)
        .before(FkElementTypes.COLON).spaceIf(false)
        .after(FkElementTypes.SEMICOLON).spaceIf(true)
        .before(FkElementTypes.SEMICOLON).spaceIf(false)

        //== empty parens
        .between(FkElementTypes.LBRACE, FkElementTypes.RBRACE).spacing(0, 0, 0, false, 0)

         //== Handling block
        .afterInside(FkElementTypes.LBRACE, BLOCK_LIKE).parentDependentLFSpacing(1, 1, true, 0)
        .beforeInside(FkElementTypes.RBRACE, BLOCK_LIKE).parentDependentLFSpacing(1, 1, true, 0)

        .around(FK_KEYWORDS).spaces(1)
        .applyForEach(BLOCK_LIKE) { before(it).spaces(1) }
}