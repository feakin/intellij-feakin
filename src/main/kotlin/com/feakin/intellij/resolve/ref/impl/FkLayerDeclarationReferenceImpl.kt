package com.feakin.intellij.resolve.ref.impl

import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkLayerDeclaration
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.psi.PsiElement

class FkLayerDeclarationReferenceImpl(element: FkLayerDeclaration) : FkReferenceBase<FkLayerDeclaration>(element) {
    override fun multiResolve(): List<FkElement> {
        return listOf()
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkLayerDeclaration && super.isReferenceTo(element)
    }
}