package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkUseValueObjectName
import com.feakin.intellij.psi.impl.FkElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.ddd.FkUseValueObjectNameReferenceImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class FkUseValueObjectNameImplMixin(node: ASTNode) : FkElementImpl(node), FkUseValueObjectName {
    override val referenceName: String get() = referenceNameElement.text

    override val referenceNameElement: PsiElement get() = identifier

    override fun getReference(): FkReference = FkUseValueObjectNameReferenceImpl(this)
}
