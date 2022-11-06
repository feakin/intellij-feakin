package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkValueObjectDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.ddd.FkValueObjectDeclReferenceImpl
import com.feakin.intellij.stubs.FkValueObjectDeclStub
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType

abstract class FkValueObjectDeclarationImplMixin : FkStubbedNamedElementImpl<FkValueObjectDeclStub>, FkValueObjectDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkValueObjectDeclStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override fun getReference(): FkReference = FkValueObjectDeclReferenceImpl(this)

    override val referenceNameElement: PsiElement get() = identifier

    override val referenceName: String get() = referenceNameElement.text
}
