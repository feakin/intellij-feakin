package com.feakin.intellij.resolve.ref

import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkElement

class FkContextDeclReferenceImpl(
    element: FkContextDeclaration
) : FkReferenceBase<FkContextDeclaration>(element) {
    override fun multiResolve(): List<FkElement> {
        return emptyList()
    }
}