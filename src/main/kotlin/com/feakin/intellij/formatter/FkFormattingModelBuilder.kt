package com.feakin.intellij.formatter

import com.feakin.intellij.FkLanguage
import com.feakin.intellij.highlight.FeakinTokenTypeSets
import com.feakin.intellij.lexer.FeakinElementTypes.*
import com.intellij.formatting.*
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import org.jetbrains.annotations.NotNull

val BLOCK_LIKE = TokenSet.create(IMPL_BODY, CONTEXT_BODY, CONTEXT_MAP_BODY, ENDPOINT_BODY, REQUEST_BODY)
val FK_KEYWORDS = FeakinTokenTypeSets.KEY_WORDS;

class FkFormattingModelBuilder : FormattingModelBuilder {
    private fun createSpaceBuilder(settings: CodeStyleSettings): SpacingBuilder {
        return SpacingBuilder(settings, FkLanguage)
            .after(COMMA).spacing(1, 1, 0, true, 0)
            .before(COMMA).spaceIf(false)
            .after(COLON).spaceIf(true)
            .before(COLON).spaceIf(false)
            .after(SEMICOLON).spaceIf(true)
            .before(SEMICOLON).spaceIf(false)

            .between(LBRACE, RBRACE).spacing(0, 0, 0, false, 0)
            .afterInside(LBRACE, BLOCK_LIKE).parentDependentLFSpacing(1, 1, true, 0)
            .beforeInside(RBRACE, BLOCK_LIKE).parentDependentLFSpacing(1, 1, true, 0)

            .around(FK_KEYWORDS).spaces(1)
            .applyForEach(BLOCK_LIKE) { before(it).spaces(1) }
    }

    @NotNull
    override fun createModel(@NotNull context: FormattingContext): FormattingModel {
        val settings = context.codeStyleSettings
        val element = context.psiElement
        val block = FkFormattingBlock(
            element.node, null, Indent.getNoneIndent(), null, settings, createSpaceBuilder(settings)
        )
        return FormattingModelProvider.createFormattingModelForPsiFile(context.containingFile, block, settings)
    }
}

/*
 *
 * Copyright (c) 2015 Aleksey Kladov, Evgeny Kurbatsky, Alexey Kudinkin and contributors
 * Copyright (c) 2016 JetBrains
 *
 */
private inline fun SpacingBuilder.applyForEach(
    tokenSet: TokenSet, block: SpacingBuilder.(IElementType) -> SpacingBuilder
): SpacingBuilder {
    var self = this
    for (tt in tokenSet.types) {
        self = block(this, tt)
    }
    return self
}
