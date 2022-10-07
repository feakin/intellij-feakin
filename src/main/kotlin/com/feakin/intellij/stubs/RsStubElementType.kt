package com.feakin.intellij.stubs

import com.feakin.intellij.FkLanguage
import com.feakin.intellij.psi.FkElement
import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.IndexSink
import com.intellij.psi.stubs.StubElement

abstract class FkStubElementType<StubT : StubElement<*>, PsiT : FkElement>(
    debugName: String
) : IStubElementType<StubT, PsiT>(debugName, FkLanguage) {

    final override fun getExternalId(): String {
        return "fkl.${super.toString()}"
    }

    override fun indexStub(stub: StubT, sink: IndexSink) {
    }
}
