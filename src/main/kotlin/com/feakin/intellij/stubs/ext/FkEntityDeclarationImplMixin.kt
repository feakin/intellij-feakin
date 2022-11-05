package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkEntityDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.ddd.FkEntityDeclReferenceImpl
import com.feakin.intellij.stubs.FkEntityDeclStub
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType

abstract class FkEntityDeclarationImplMixin : FkStubbedNamedElementImpl<FkEntityDeclStub>, FkEntityDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkEntityDeclStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override fun getReference(): FkReference = FkEntityDeclReferenceImpl(this)

    override val referenceNameElement: PsiElement get() = identifier

    override val referenceName: String get() = referenceNameElement.text
}
