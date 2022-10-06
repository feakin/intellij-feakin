package com.feakin.intellij.psi.impl

import com.feakin.intellij.psi.FkElement
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.tree.IElementType

open class FkElementImpl(node: ASTNode) : ASTWrapperPsiElement(node),
    FkElement {
    override val tokenType: IElementType?
        get() = null
}
