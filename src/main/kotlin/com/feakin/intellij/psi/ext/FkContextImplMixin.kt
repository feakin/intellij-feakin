package com.feakin.intellij.psi.ext

import com.feakin.intellij.psi.FkContextDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.psi.stubs.FkContextDeclStub
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType

abstract class FkContextImplMixin : FkStubbedNamedElementImpl<FkContextDeclStub>, FkContextDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkContextDeclStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override fun getContext(): PsiElement? {
        return super.getContext()
    }
}
