package com.feakin.intellij.stubs.ext.misc

import com.feakin.intellij.psi.FkLayerDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.misc.FkLayerDeclarationReferenceImpl
import com.feakin.intellij.stubs.FkLayerDeclarationStub
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType

abstract class FkLayerDeclarationImplMixin : FkStubbedNamedElementImpl<FkLayerDeclarationStub>, FkLayerDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkLayerDeclarationStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override fun getReference(): FkReference = FkLayerDeclarationReferenceImpl(this)

    override val referenceNameElement: PsiElement get() = identifier
    override val referenceName: String get() = referenceNameElement.text
}
