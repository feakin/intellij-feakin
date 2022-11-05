package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkDepNode
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.normal.FkDepNodeReferenceImpl
import com.feakin.intellij.stubs.FkDepNodeStub
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType

abstract class FkDepNodeMixin : FkStubbedNamedElementImpl<FkDepNodeStub>, FkDepNode {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkDepNodeStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override fun getReference(): FkReference = FkDepNodeReferenceImpl(this)

    override val referenceNameElement: PsiElement get() = identifier ?: this

    override val referenceName: String get() = referenceNameElement.text
}
