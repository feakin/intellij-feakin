package com.feakin.intellij.resolve.ref.impl

import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkIncludeDeclaration
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.psi.PsiElement

class FkIncludeReferenceImpl(element: FkIncludeDeclaration) : FkReferenceBase<FkIncludeDeclaration>(element) {
    override fun multiResolve(): List<FkElement> {
        return emptyList()
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkIncludeDeclaration && super.isReferenceTo(element)
    }
}