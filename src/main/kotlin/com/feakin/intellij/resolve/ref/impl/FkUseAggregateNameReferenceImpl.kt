package com.feakin.intellij.resolve.ref.impl

import com.feakin.intellij.psi.FkAggregateDeclaration
import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkUseAggregateName
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.psi.PsiElement

class FkUseAggregateNameReferenceImpl(element: FkUseAggregateName) : FkReferenceBase<FkUseAggregateName>(element) {
    override fun multiResolve(): List<FkElement> {
        val collection =
            FkNamedElementIndex.findElementsByName(element.identifier.text, element.project)
                .filterIsInstance<FkAggregateDeclaration>()
                .toCollection(mutableListOf())

        return collection
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkUseAggregateName && super.isReferenceTo(element)
    }
}