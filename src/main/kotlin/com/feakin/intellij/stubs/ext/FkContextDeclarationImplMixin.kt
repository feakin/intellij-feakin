package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.impl.FkContextDeclReferenceImpl
import com.feakin.intellij.stubs.FkContextDeclStub
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType

abstract class FkContextDeclarationImplMixin : FkStubbedNamedElementImpl<FkContextDeclStub>, FkContextDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkContextDeclStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override fun getReference(): FkReference = FkContextDeclReferenceImpl(this)

    override val referenceNameElement: PsiElement get() = identifier ?: this

    override val referenceName: String get() = referenceNameElement.text
}
