package com.feakin.intellij.psi

import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.tree.IElementType

interface FkElement : NavigatablePsiElement {
    val tokenType: IElementType?
}
