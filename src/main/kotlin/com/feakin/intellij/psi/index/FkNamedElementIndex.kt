package com.feakin.intellij.psi.index

import com.feakin.intellij.psi.FkNamedElement
import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndexKey

class FkNamedElementIndex : StringStubIndexExtension<FkNamedElement>() {
    override fun getVersion(): Int = 0

    override fun getKey(): StubIndexKey<String, FkNamedElement> = KEY

    companion object {
        val KEY: StubIndexKey<String, FkNamedElement> =
            StubIndexKey.createIndexKey("com.feakin.intellij.psi.stubs.FkNamedElementIndex")

    }
}

