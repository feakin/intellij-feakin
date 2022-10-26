package com.feakin.intellij.stubs.ext

import com.feakin.intellij.psi.FkLayerDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.stubs.FkLayerDeclarationStub
import com.intellij.lang.ASTNode
import com.intellij.psi.stubs.IStubElementType

abstract class FkLayerDeclarationImplMixin : FkStubbedNamedElementImpl<FkLayerDeclarationStub>, FkLayerDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkLayerDeclarationStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)
}
