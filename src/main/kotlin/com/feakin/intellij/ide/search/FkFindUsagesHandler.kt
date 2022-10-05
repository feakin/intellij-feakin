package com.feakin.intellij.ide.search

import com.intellij.find.findUsages.FindUsagesHandler
import com.intellij.psi.PsiElement

class FkFindUsagesHandler(psiElement: PsiElement, private val secondaryElements: Array<PsiElement>) : FindUsagesHandler(psiElement) {
    override fun getSecondaryElements(): Array<PsiElement> = secondaryElements
}
