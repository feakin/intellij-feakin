package com.feakin.intellij.stubs.ext

import com.feakin.intellij.lexer.FkElementTypes.STRING_LITERAL
import com.feakin.intellij.psi.FkIncludeDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.impl.FkIncludeReferenceImpl
import com.feakin.intellij.stubs.FkIncludeStub
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType

abstract class FkIncludeMixin : FkStubbedNamedElementImpl<FkIncludeStub>, FkIncludeDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkIncludeStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override val referenceName: String get() {
        val stub = greenStub
        return stub?.name ?: super.referenceName
    }

    override val referenceNameElement: PsiElement get() = node.findChildByType(STRING_LITERAL)?.psi ?: this

    override fun getReference(): FkReference = FkIncludeReferenceImpl(this)
}
