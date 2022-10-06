package com.feakin.intellij.psi.stubs

import com.feakin.intellij.psi.FeakinNamedElement
import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndexKey

class FkGotoClassIndex : StringStubIndexExtension<FeakinNamedElement>() {
    override fun getKey(): StubIndexKey<String, FeakinNamedElement> = KEY

    companion object {
        val KEY: StubIndexKey<String, FeakinNamedElement> =
            StubIndexKey.createIndexKey("com.feakin.intellij.psi.stubs.FkGotoClassIndex")
    }
}