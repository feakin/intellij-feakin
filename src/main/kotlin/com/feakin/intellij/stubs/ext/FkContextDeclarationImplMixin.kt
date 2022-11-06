package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.ddd.FkContextDeclReferenceImpl
import com.feakin.intellij.stubs.FkContextDeclarationStub
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType

abstract class FkContextDeclarationImplMixin : FkStubbedNamedElementImpl<FkContextDeclarationStub>, FkContextDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkContextDeclarationStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override fun getReference(): FkReference = FkContextDeclReferenceImpl(this)

    override val referenceNameElement: PsiElement get() = identifier

    override val referenceName: String get() = referenceNameElement.text
}
