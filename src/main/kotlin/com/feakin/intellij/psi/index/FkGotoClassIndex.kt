package com.feakin.intellij.psi.index

import com.feakin.intellij.psi.FeakinContextMapDeclaration
import com.feakin.intellij.psi.FkNamedElement
import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndexKey

class FkGotoClassIndex : StringStubIndexExtension<FeakinContextMapDeclaration>() {
    override fun getKey(): StubIndexKey<String, FeakinContextMapDeclaration> = KEY

    companion object {
        val KEY: StubIndexKey<String, FeakinContextMapDeclaration> =
            StubIndexKey.createIndexKey("com.feakin.intellij.psi.index.FkGotoClassIndex")
    }
}