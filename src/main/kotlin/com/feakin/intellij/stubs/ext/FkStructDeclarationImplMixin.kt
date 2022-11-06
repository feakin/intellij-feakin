package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkStructDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.ddd.FkStructDeclReferenceImpl
import com.feakin.intellij.stubs.FkStructDeclarationStub
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType

abstract class FkStructDeclarationImplMixin : FkStubbedNamedElementImpl<FkStructDeclarationStub>, FkStructDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkStructDeclarationStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override fun getReference(): FkReference = FkStructDeclReferenceImpl(this)

    override val referenceNameElement: PsiElement get() = identifier

    override val referenceName: String get() = referenceNameElement.text
}
