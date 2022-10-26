package com.feakin.intellij.resolve.ref.impl

import com.feakin.intellij.psi.FkDepNode
import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkLayerDeclaration
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.psi.PsiElement

class FkDepNodeReferenceImpl(element: FkDepNode) : FkReferenceBase<FkDepNode>(element) {
    override fun multiResolve(): List<FkElement> {
        val collection =
            FkNamedElementIndex.findElementsByName(element.text, element.project)
                .filterIsInstance<FkLayerDeclaration>()
                .toCollection(mutableListOf())

        return collection
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkLayerDeclaration && super.isReferenceTo(element)
    }
}