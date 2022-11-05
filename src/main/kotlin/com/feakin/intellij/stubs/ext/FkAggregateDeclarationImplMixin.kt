package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkAggregateDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.impl.FkAggregateDeclReferenceImpl
import com.feakin.intellij.stubs.FkAggregateDeclStub
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType

abstract class FkAggregateDeclarationImplMixin : FkStubbedNamedElementImpl<FkAggregateDeclStub>, FkAggregateDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkAggregateDeclStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override fun getReference(): FkReference = FkAggregateDeclReferenceImpl(this)

    override val referenceNameElement: PsiElement get() = identifier

    override val referenceName: String get() = referenceNameElement.text
}
