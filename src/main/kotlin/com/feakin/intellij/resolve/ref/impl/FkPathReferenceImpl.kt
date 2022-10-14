package com.feakin.intellij.resolve.ref.impl

import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkPath
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.psi.PsiElement

class FkPathReferenceImpl(element: FkPath) : FkReferenceBase<FkPath>(element) {
    override fun multiResolve(): List<FkElement> {
//        FkPathIndex.findElementsByName(element.referenceName, element.project)
        return emptyList()
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkPath && super.isReferenceTo(element)
    }
}