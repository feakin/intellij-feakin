package com.feakin.intellij.psi.stubs.ext

import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.psi.stubs.FkContextDeclStub
import com.intellij.lang.ASTNode
import com.intellij.psi.stubs.IStubElementType

abstract class FkContextDeclMixin : FkStubbedNamedElementImpl<FkContextDeclStub>, FkContextDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkContextDeclStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

//    override fun getReference(): FkReference = FkContextDeclReferenceImpl(this)

//    override val referenceNameElement: PsiElement
//        get() = checkNotNull(identifier) {
//            "Context must contain identifier: $this $text at ${containingFile.virtualFile.path}"
//        }

//    override val referenceName: String get() = greenStub?.name ?: super.referenceName
}
