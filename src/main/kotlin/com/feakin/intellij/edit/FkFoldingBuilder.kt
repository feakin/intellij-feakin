package com.feakin.intellij.edit

import com.feakin.intellij.FkFile
import com.feakin.intellij.lexer.FkElementTypes.LBRACE
import com.feakin.intellij.lexer.FkElementTypes.RBRACE
import com.feakin.intellij.psi.*
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.CustomFoldingBuilder
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.TokenSet
import com.intellij.psi.util.PsiTreeUtil

class FkFoldingBuilder : CustomFoldingBuilder(), DumbAware {

    override fun buildLanguageFoldRegions(
        descriptors: MutableList<FoldingDescriptor>,
        root: PsiElement,
        document: Document,
        quick: Boolean
    ) {
        if (root !is FkFile) return

        val visitor = FoldingVisitor(descriptors)
        PsiTreeUtil.processElements(root) { it.accept(visitor); true }
    }

    override fun getLanguagePlaceholderText(node: ASTNode, range: TextRange): String {
        when (node.elementType) {
            LBRACE -> return " { "
            RBRACE -> return " }"
        }
        return when (node.psi) {
            is PsiComment -> "/* ... */"
            else -> "{...}"
        }
    }

    override fun isRegionCollapsedByDefault(node: ASTNode): Boolean {
        return node.elementType in COLLAPSED_BY_DEFAULT
    }

    private companion object {
        val COLLAPSED_BY_DEFAULT = TokenSet.create(LBRACE, RBRACE)
    }

    private class FoldingVisitor(private val descriptors: MutableList<FoldingDescriptor>) : FkVisitor() {
        override fun visitContextBody(o: FkContextBody) = fold(o)

        override fun visitContextMapBody(o: FkContextMapBody) = fold(o)

        override fun visitAggregateBody(o: FkAggregateBody) = fold(o)

        override fun visitEntityBody(o: FkEntityBody) = fold(o)

        override fun visitStructBody(o: FkStructBody) = fold(o)

        override fun visitValueObjectBody(o: FkValueObjectBody) = fold(o)

        override fun visitImplBody(o: FkImplBody) = fold(o)

        override fun visitFlowBody(o: FkFlowBody) = fold(o)

        override fun visitEndpointBody(o: FkEndpointBody) = fold(o)

        override fun visitSourceSetDeclBody(o: FkSourceSetDeclBody) = fold(o)

        override fun visitSourceSetItemBody(o: FkSourceSetItemBody) = fold(o)

        override fun visitDefBody(o: FkDefBody) = fold(o)

        override fun visitLayeredBody(o: FkLayeredBody) = fold(o)

        override fun visitEnvBody(o: FkEnvBody) = fold(o)

        override fun visitDatasourceBody(o: FkDatasourceBody) = fold(o)

        override fun visitServerBody(o: FkServerBody) = fold(o)

        override fun visitCustomBody(o: FkCustomBody) = fold(o)

        private fun fold(element: PsiElement) {
            descriptors += FoldingDescriptor(element.node, element.textRange)
        }
    }
}