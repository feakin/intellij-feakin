package com.feakin.intellij.resolve.ref

import com.feakin.intellij.psi.FkElement
import com.intellij.psi.PsiPolyVariantReference

interface FkReference : PsiPolyVariantReference {
    override fun getElement(): FkElement

    override fun resolve(): FkElement?

    fun multiResolve(): List<FkElement>
}


