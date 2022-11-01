package com.feakin.intellij.formatter

import com.feakin.intellij.highlight.FkTokenTypeSets
import com.feakin.intellij.lexer.FkElementTypes.*
import com.intellij.formatting.*
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.TokenSet
import org.jetbrains.annotations.NotNull

val BLOCK_LIKE = TokenSet.create(
    CONTEXT_BODY,
    CONTEXT_MAP_BODY,
    AGGREGATE_BODY,
    ENTITY_BODY,
    VALUE_OBJECT_BODY,
    STRUCT_BODY,

    IMPL_BODY,
    ENDPOINT_BODY,

    FLOW_BODY,
    LAYERED_BODY,
    LAYER_BODY,
    DEPENDENCY_BODY,

    SOURCE_SET_DECL_BODY,
    SOURCE_SET_ITEM_BODY,

    WHEN_BODY,
    WHEN_ENTRY_BODY,
    VARIABLE_BODY,
    DEF_BODY,

    ENV_BODY,
    DATASOURCE_BODY,
    SERVER_BODY,

    CUSTOM_BODY
)

val FK_KEYWORDS = FkTokenTypeSets.KEY_WORDS;

class FkFormattingModelBuilder : FormattingModelBuilder {
    override fun getRangeAffectingIndent(file: PsiFile?, offset: Int, elementAtOffset: ASTNode?): TextRange? = null

    @NotNull
    override fun createModel(@NotNull context: FormattingContext): FormattingModel {
        val settings = context.codeStyleSettings
        val element = context.psiElement
        val ctx = FkFmtContext.create(settings)
        val block = createBlock(null, null, element.node, Indent.getNoneIndent(), ctx)
        return FormattingModelProvider.createFormattingModelForPsiFile(context.containingFile, block, settings)
    }

    companion object {
        fun createBlock(
            alignment: Alignment?,
            wrap: Wrap?,
            node: ASTNode,
            indent: Indent,
            ctx: FkFmtContext
        ): FkFormattingBlock {
            return FkFormattingBlock(node, alignment, indent, wrap, ctx)
        }

    }
}
