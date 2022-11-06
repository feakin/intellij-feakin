package com.feakin.intellij.resolve.ref.ddd

import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkUseValueObjectName
import com.feakin.intellij.psi.FkValueObjectDeclaration
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.psi.PsiElement

class FkUseValueObjectNameReferenceImpl(element: FkUseValueObjectName) : FkReferenceBase<FkUseValueObjectName>(element) {
    override fun multiResolve(): List<FkElement> {
        val collection =
            FkNamedElementIndex.findElementsByName(element.identifier.text, element.project)
                .filterIsInstance<FkValueObjectDeclaration>()
                .toCollection(mutableListOf())

        return collection
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkUseValueObjectName && super.isReferenceTo(element)
    }
}