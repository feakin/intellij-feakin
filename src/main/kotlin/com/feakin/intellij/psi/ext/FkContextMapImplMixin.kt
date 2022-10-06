package com.feakin.intellij.psi.ext

import com.feakin.intellij.psi.FkContextMapDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.psi.stubs.FkContextMapDeclStub
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType

abstract class FkContextMapImplMixin : FkStubbedNamedElementImpl<FkContextMapDeclStub>, FkContextMapDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkContextMapDeclStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)


    override fun getContext(): PsiElement? {
        return super.getContext()
    }
}
