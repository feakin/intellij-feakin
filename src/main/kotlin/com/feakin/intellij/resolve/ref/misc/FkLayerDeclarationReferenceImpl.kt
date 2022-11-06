package com.feakin.intellij.resolve.ref.misc

import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkLayerDeclaration
import com.feakin.intellij.psi.FkLayeredBody
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.psi.PsiElement

class FkLayerDeclarationReferenceImpl(element: FkLayerDeclaration) : FkReferenceBase<FkLayerDeclaration>(element) {
    override fun multiResolve(): List<FkElement> {
        var result: List<FkElement> = emptyList()
        when (val parent = element.parent) {
            is FkLayeredBody -> {
                result = parent.dependency?.dependencyBody?.dependencyRuleList?.filter {
                    it.depSource.text == element.identifier.text || it.depTarget.text == element.identifier.text
                } ?: emptyList()
            }
        }

        return result
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkLayerDeclaration && super.isReferenceTo(element)
    }
}