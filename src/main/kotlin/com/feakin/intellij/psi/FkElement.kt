package com.feakin.intellij.psi

import com.intellij.extapi.psi.StubBasedPsiElementBase
import com.intellij.lang.ASTNode
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.StubElement
import com.intellij.psi.tree.IElementType

interface FkElement : NavigatablePsiElement {
    val tokenType: IElementType?
}

abstract class FkStubbedElementImpl<StubT : StubElement<*>> : StubBasedPsiElementBase<StubT>, FkElement {

    constructor(node: ASTNode) : super(node)

    constructor(stub: StubT, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override fun toString(): String = "${javaClass.simpleName}($elementType)"

    override val tokenType: IElementType? get() = node.elementType
}
