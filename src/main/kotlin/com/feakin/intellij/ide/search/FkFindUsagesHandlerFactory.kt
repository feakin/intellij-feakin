package com.feakin.intellij.ide.search

import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkNamedElement
import com.intellij.find.findUsages.FindUsagesHandler
import com.intellij.find.findUsages.FindUsagesHandlerFactory
import com.intellij.psi.PsiElement
import com.intellij.psi.search.searches.ReferencesSearch

class FkFindUsagesHandlerFactory : FindUsagesHandlerFactory() {
    override fun canFindUsages(element: PsiElement): Boolean = element is FkNamedElement

    override fun createFindUsagesHandler(element: PsiElement, forHighlightUsages: Boolean): FindUsagesHandler {
        val secondaryElements = if (!forHighlightUsages) findSecondaryElements(element) else emptyList()
        return FkFindUsagesHandler(element, secondaryElements.toTypedArray())
    }

    private fun findSecondaryElements(element: PsiElement): List<PsiElement> {
        when(element) {
            is FkContextDeclaration -> {
                return ReferencesSearch.search(element).findAll().map { it.element }
            }
            else -> return emptyList()
        }
    }
}
