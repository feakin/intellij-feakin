package com.feakin.intellij.resolve.ref.ddd

import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkEntityDeclaration
import com.feakin.intellij.psi.FkUseEntityName
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.psi.PsiElement

class FkUseEntityNameReferenceImpl(element: FkUseEntityName) : FkReferenceBase<FkUseEntityName>(element) {
    override fun multiResolve(): List<FkElement> {
        val collection =
            FkNamedElementIndex.findElementsByName(element.identifier.text, element.project)
                .filterIsInstance<FkEntityDeclaration>()
                .toCollection(mutableListOf())

        return collection
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkUseEntityName && super.isReferenceTo(element)
    }
}