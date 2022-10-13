package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkIncludeDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.stubs.FkIncludeStub
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType

abstract class FkIncludeMixin : FkStubbedNamedElementImpl<FkIncludeStub>, FkIncludeDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkIncludeStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override fun getContext(): PsiElement? {
        return super.getContext()
    }
}
