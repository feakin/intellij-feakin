package com.feakin.intellij.ide.search

import com.feakin.intellij.psi.FkNamedElement
import com.intellij.lang.HelpID
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement

class FkFindUsagesProvider : FindUsagesProvider {
    override fun getWordsScanner() = FkWordScanner()

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean = psiElement is FkNamedElement

    override fun getHelpId(psiElement: PsiElement): String = HelpID.FIND_OTHER_USAGES

    override fun getType(element: PsiElement): String = ""

    override fun getDescriptiveName(element: PsiElement): String = (element as? FkNamedElement)?.name.orEmpty()

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String = ""

}