package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkUseContextName
import com.feakin.intellij.psi.impl.FkElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.ddd.FkUseContextNameReferenceImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class FkUseContextNameImplMixin(node: ASTNode) : FkElementImpl(node), FkUseContextName {
    override val referenceName: String get() = referenceNameElement.text

    override val referenceNameElement: PsiElement get() = identifier

    override fun getReference(): FkReference = FkUseContextNameReferenceImpl(this)
}
