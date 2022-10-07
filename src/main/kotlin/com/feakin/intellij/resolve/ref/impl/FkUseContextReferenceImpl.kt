package com.feakin.intellij.resolve.ref.impl

import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkContextName
import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.stubs.index.FkNamedElementIndex
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.psi.PsiElement

class FkUseContextReferenceImpl(element: FkContextName) : FkReferenceBase<FkContextName>(element) {
    override fun multiResolve(): List<FkElement> {
        val collection =
            FkNamedElementIndex.findElementsByName(element.identifier.text, element.project)
                .filterIsInstance<FkContextDeclaration>()
                .toCollection(mutableListOf())

        return collection
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkContextName && super.isReferenceTo(element)
    }
}