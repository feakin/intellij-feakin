package com.feakin.intellij.edit

import com.feakin.intellij.FeakinFile
import com.feakin.intellij.psi.FeakinContextMapDeclaration
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import java.util.*

class FeakinFoldingBuilder : FoldingBuilderEx() {
    override fun getPlaceholderText(node: ASTNode): String {
        return "..."
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        if (element !is FeakinFile) {
            return FoldingDescriptor.EMPTY
        }
        val structs = PsiTreeUtil.getChildrenOfType(element, FeakinContextMapDeclaration::class.java)
            ?: return descriptors.toTypedArray()
        for (struct in structs) {
            val structNameDeclaration = struct.contextMapName
            val nameEnd = structNameDeclaration.node.textRange.startOffset
            val structEnd = struct.node.textRange.endOffset
            val foldingDescriptor = FoldingDescriptor(
                Objects.requireNonNull(struct),
                TextRange(nameEnd, structEnd)
            )
            descriptors.add(foldingDescriptor)
        }
        return descriptors.toTypedArray()
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        return true
    }
}