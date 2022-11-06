package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkUseEntityName
import com.feakin.intellij.psi.impl.FkElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.ddd.FkUseEntityNameReferenceImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class FkUseEntityNameImplMixin(node: ASTNode) : FkElementImpl(node), FkUseEntityName {
    override val referenceName: String get() = referenceNameElement.text

    override val referenceNameElement: PsiElement get() = identifier

    override fun getReference(): FkReference = FkUseEntityNameReferenceImpl(this)
}
