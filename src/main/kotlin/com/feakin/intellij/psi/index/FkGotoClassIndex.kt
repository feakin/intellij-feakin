package com.feakin.intellij.psi.index

import com.feakin.intellij.psi.FkNamedElement
import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndexKey

class FkGotoClassIndex : StringStubIndexExtension<FkNamedElement>() {
    override fun getKey(): StubIndexKey<String, FkNamedElement> = KEY

    companion object {
        val KEY: StubIndexKey<String, FkNamedElement> =
            StubIndexKey.createIndexKey("com.feakin.intellij.psi.index.FkGotoClassIndex")
    }
}