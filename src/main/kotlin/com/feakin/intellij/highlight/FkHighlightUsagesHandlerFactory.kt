package com.feakin.intellij.highlight

import com.feakin.intellij.lexer.FkElementTypes.IDENTIFIER
import com.feakin.intellij.psi.FkCustomKeyword
import com.intellij.codeInsight.highlighting.HighlightUsagesHandlerBase
import com.intellij.codeInsight.highlighting.HighlightUsagesHandlerFactoryBase
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.util.Consumer

class FkHighlightUsagesHandlerFactory : HighlightUsagesHandlerFactoryBase() {
    override fun createHighlightUsagesHandler(
        editor: Editor,
        file: PsiFile,
        target: PsiElement
    ): HighlightUsagesHandlerBase<*>? {
        if (!(target is LeafPsiElement && target.elementType == IDENTIFIER)) return null
        val refExpr = target.parent as? FkCustomKeyword ?: return null

        return object : HighlightUsagesHandlerBase<FkCustomKeyword>(editor, file) {
            override fun getTargets() = listOf(refExpr)

            override fun selectTargets(
                targets: MutableList<out FkCustomKeyword>,
                selectionConsumer: Consumer<in MutableList<out FkCustomKeyword>>
            ) = selectionConsumer.consume(targets)


            override fun computeUsages(targets: MutableList<out FkCustomKeyword>) {
                addOccurrence(refExpr)
            }
        }
    }

}