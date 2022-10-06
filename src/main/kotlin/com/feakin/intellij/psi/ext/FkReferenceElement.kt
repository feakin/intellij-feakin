package com.feakin.intellij.psi.ext

import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.resolve.ref.FkReference
import com.intellij.psi.PsiElement

interface FkReferenceElementBase : FkElement {
    val referenceNameElement: PsiElement?

    val referenceName: String? get() = referenceNameElement?.text
}

interface FkReferenceElement : FkReferenceElementBase {
    override fun getReference(): FkReference?
}

interface FkMandatoryReferenceElement : FkReferenceElement {
    override val referenceNameElement: PsiElement

    override val referenceName: String get() = referenceNameElement.text

    override fun getReference(): FkReference
}
