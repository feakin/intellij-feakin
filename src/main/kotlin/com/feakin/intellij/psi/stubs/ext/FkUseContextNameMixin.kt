package com.feakin.intellij.psi.stubs.ext

import com.feakin.intellij.psi.FkContextName
import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.impl.FkElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class FkUseContextNameMixin(node: ASTNode) : FkElementImpl(node), FkContextName {
    override val referenceNameElement: PsiElement get() = identifier

    override fun getReference(): FkReference = FkUseContextReferenceImpl(this)
}

class FkUseContextReferenceImpl(element: FkContextName) : FkReferenceBase<FkContextName>(element) {
    override fun multiResolve(): List<FkElement> {
        return emptyList()
    }
}