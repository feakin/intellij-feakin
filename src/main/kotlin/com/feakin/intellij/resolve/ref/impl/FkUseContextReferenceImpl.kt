package com.feakin.intellij.resolve.ref.impl

import com.feakin.intellij.psi.FkContextName
import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.resolve.ref.FkReferenceBase

class FkUseContextReferenceImpl(element: FkContextName) : FkReferenceBase<FkContextName>(element) {
    override fun multiResolve(): List<FkElement> {
        return emptyList()
    }
}