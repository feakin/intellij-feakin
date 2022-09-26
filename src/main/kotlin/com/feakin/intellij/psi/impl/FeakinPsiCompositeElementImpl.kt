package com.feakin.intellij.psi.impl

import com.feakin.intellij.psi.FeakinPsiCompositeElement
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.tree.IElementType

open class FeakinPsiCompositeElementImpl(node: ASTNode) : ASTWrapperPsiElement(node),
    FeakinPsiCompositeElement {
    override val tokenType: IElementType?
        get() = null
}
