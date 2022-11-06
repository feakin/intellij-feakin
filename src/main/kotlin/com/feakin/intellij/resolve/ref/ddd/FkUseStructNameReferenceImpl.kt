package com.feakin.intellij.resolve.ref.ddd

import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkStructDeclaration
import com.feakin.intellij.psi.FkUseStructName
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.psi.PsiElement

class FkUseStructNameReferenceImpl(element: FkUseStructName) : FkReferenceBase<FkUseStructName>(element) {
    override fun multiResolve(): List<FkElement> {
        val collection =
            FkNamedElementIndex.findElementsByName(element.identifier.text, element.project)
                .filterIsInstance<FkStructDeclaration>()
                .toCollection(mutableListOf())

        return collection
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkUseStructName && super.isReferenceTo(element)
    }
}