package com.feakin.intellij.resolve.ref.use

import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkUseContextName
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.psi.PsiElement

class FkUseContextNameReferenceImpl(element: FkUseContextName) : FkReferenceBase<FkUseContextName>(element) {
    override fun multiResolve(): List<FkElement> {
        val collection =
            FkNamedElementIndex.findElementsByName(element.identifier.text, element.project)
                .filterIsInstance<FkContextDeclaration>()
                .toCollection(mutableListOf())

        return collection
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkUseContextName && super.isReferenceTo(element)
    }
}