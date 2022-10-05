package com.feakin.intellij.ide.search

import com.feakin.intellij.psi.FeakinNamedElement
import com.intellij.find.findUsages.FindUsagesHandler
import com.intellij.find.findUsages.FindUsagesHandlerFactory
import com.intellij.psi.PsiElement

class FkFindUsagesHandlerFactory : FindUsagesHandlerFactory() {
    override fun canFindUsages(element: PsiElement): Boolean = element is FeakinNamedElement

    override fun createFindUsagesHandler(element: PsiElement, forHighlightUsages: Boolean): FindUsagesHandler {
        val secondaryElements = if (!forHighlightUsages) findSecondaryElements(element) else emptyList()
        return FkFindUsagesHandler(element, secondaryElements.toTypedArray())
    }

    private fun findSecondaryElements(_element: PsiElement): List<PsiElement> {
        return emptyList()
    }
}
