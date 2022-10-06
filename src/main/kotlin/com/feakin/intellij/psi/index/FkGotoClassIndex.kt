package com.feakin.intellij.psi.index

import com.feakin.intellij.psi.FkContextMapDeclaration
import com.feakin.intellij.psi.FkNamedElement
import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndexKey

class FkGotoClassIndex : StringStubIndexExtension<FkContextMapDeclaration>() {
    override fun getKey(): StubIndexKey<String, FkContextMapDeclaration> = KEY

    companion object {
        val KEY: StubIndexKey<String, FkContextMapDeclaration> =
            StubIndexKey.createIndexKey("com.feakin.intellij.psi.index.FkGotoClassIndex")
    }
}