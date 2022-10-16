package com.feakin.intellij.stubs.ext

import com.feakin.intellij.lexer.FkElementTypes.STRING_LITERAL
import com.feakin.intellij.psi.FkPath
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.resolve.ref.FkReference
import com.feakin.intellij.resolve.ref.impl.FkPathReferenceImpl
import com.feakin.intellij.stubs.FkPathStub
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.stubs.IStubElementType

abstract class FkPathMixin : FkStubbedNamedElementImpl<FkPathStub>, FkPath {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkPathStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override val referenceName: String = referenceNameElement.text

    final override val referenceNameElement: PsiElement
        get() {
            // todo: change by path
            return node.findChildByType(STRING_LITERAL)?.psi ?: this
        }

    override fun getReference(): FkReference? {
        if (node.text.length > 2) {
            return FkPathReferenceImpl(this)
        }

        return null
    }
}
