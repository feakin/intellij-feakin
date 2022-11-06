package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkUseAggregateName
import com.feakin.intellij.psi.impl.FkElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.ddd.FkUseAggregateNameReferenceImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class FkUseAggregateNameImplMixin(node: ASTNode) : FkElementImpl(node), FkUseAggregateName {
    override val referenceName: String get() = referenceNameElement.text

    override val referenceNameElement: PsiElement get() = identifier

    override fun getReference(): FkReference = FkUseAggregateNameReferenceImpl(this)
}
