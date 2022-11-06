package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkUseStructName
import com.feakin.intellij.psi.impl.FkElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.ddd.FkUseStructNameReferenceImpl
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

abstract class FkUseStructNameImplMixin(node: ASTNode) : FkElementImpl(node), FkUseStructName {
    override val referenceName: String get() = referenceNameElement.text

    override val referenceNameElement: PsiElement get() = identifier

    override fun getReference(): FkReference = FkUseStructNameReferenceImpl(this)
}
