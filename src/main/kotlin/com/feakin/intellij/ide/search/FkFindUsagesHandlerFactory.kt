package com.feakin.intellij.ide.search

import com.feakin.intellij.psi.FkNamedElement
import com.intellij.find.findUsages.FindUsagesHandler
import com.intellij.find.findUsages.FindUsagesHandlerFactory
import com.intellij.psi.PsiElement

class FkFindUsagesHandlerFactory : FindUsagesHandlerFactory() {
    override fun canFindUsages(element: PsiElement): Boolean = element is FkNamedElement

    override fun createFindUsagesHandler(element: PsiElement, forHighlightUsages: Boolean): FindUsagesHandler {
        val secondaryElements = if (!forHighlightUsages) findSecondaryElements() else emptyList()
        return FkFindUsagesHandler(element, secondaryElements.toTypedArray())
    }

    private fun findSecondaryElements(): List<PsiElement> {
        return emptyList()
    }
}
