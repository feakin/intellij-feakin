package com.feakin.intellij.edit

import com.feakin.intellij.FkFile
import com.feakin.intellij.lexer.FeakinElementTypes.LBRACE
import com.feakin.intellij.lexer.FeakinElementTypes.RBRACE
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

class FeakinFoldingBuilder : CustomFoldingBuilder(), DumbAware {

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

    private class FoldingVisitor(private val descriptors: MutableList<FoldingDescriptor>) : FeakinVisitor() {
        override fun visitContextBody(o: FeakinContextBody) = fold(o)

        override fun visitContextMapBody(o: FeakinContextMapBody) = fold(o)

        override fun visitAggregateBody(o: FeakinAggregateBody) = fold(o)

        override fun visitEntityBody(o: FeakinEntityBody) = fold(o)

        override fun visitStructBody(o: FeakinStructBody) = fold(o)

        override fun visitValueObjectBody(o: FeakinValueObjectBody) = fold(o)

        override fun visitImplBody(o: FeakinImplBody) = fold(o)
        override fun visitFlowBody(o: FeakinFlowBody) = fold(o)
        override fun visitEndpointBody(o: FeakinEndpointBody) = fold(o)

        private fun fold(element: PsiElement) {
            descriptors += FoldingDescriptor(element.node, element.textRange)
        }
    }
}