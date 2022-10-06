package com.feakin.intellij.psi.ext

import com.feakin.intellij.psi.FeakinContextMapDeclaration
import com.feakin.intellij.psi.FkStubbedNamedElementImpl
import com.feakin.intellij.psi.stubs.FkContextMapDeclStub
import com.intellij.lang.ASTNode
import com.intellij.psi.stubs.IStubElementType

abstract class FkContextMapImplMixin : FkStubbedNamedElementImpl<FkContextMapDeclStub>, FeakinContextMapDeclaration {
    constructor(node: ASTNode) : super(node)

    constructor(stub: FkContextMapDeclStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

}
