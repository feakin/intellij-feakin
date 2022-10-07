package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkContextName
import com.feakin.intellij.psi.impl.FkElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.impl.FkContextNameReferenceImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class FkContextNameImplMixin(node: ASTNode) : FkElementImpl(node), FkContextName {
    override val referenceNameElement: PsiElement get() = identifier

    override fun getReference(): FkReference = FkContextNameReferenceImpl(this)
}
