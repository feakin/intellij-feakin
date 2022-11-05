package com.feakin.intellij.resolve.ref.normal

import com.feakin.intellij.psi.FkDepNode
import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkLayerDeclaration
import com.feakin.intellij.resolve.indexes.FkNamedElementIndex
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.psi.PsiElement

class FkDepNodeReferenceImpl(element: FkDepNode) : FkReferenceBase<FkDepNode>(element) {
    override fun multiResolve(): List<FkElement> {
        val collection =
            element.identifier?.let {
                FkNamedElementIndex.findElementsByName(it.text, element.project)
                    .filterIsInstance<FkLayerDeclaration>()
                    .toCollection(mutableListOf())
            }

        return collection?.toList() ?: emptyList()
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkDepNode && super.isReferenceTo(element)
    }
}