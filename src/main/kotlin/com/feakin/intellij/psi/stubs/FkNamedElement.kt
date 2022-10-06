package com.feakin.intellij.psi.stubs

import com.feakin.intellij.psi.FeakinNamedElement
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndex
import com.intellij.psi.stubs.StubIndexKey

class FkNamedElementIndex : StringStubIndexExtension<FeakinNamedElement>() {
    override fun getKey(): StubIndexKey<String, FeakinNamedElement> = KEY

    companion object {
        val KEY: StubIndexKey<String, FeakinNamedElement> =
            StubIndexKey.createIndexKey("com.feakin.intellij.psi.stubs.FkNamedElementIndex")

    }
}

