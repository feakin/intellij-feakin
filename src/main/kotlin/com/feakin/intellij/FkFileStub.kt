package com.feakin.intellij

import com.intellij.psi.PsiFile
import com.intellij.psi.StubBuilder
import com.intellij.psi.stubs.*
import com.intellij.psi.tree.IStubFileElementType

class FkFileStub(file: FkFile): PsiFileStubImpl<FkFile>(file) {

    override fun getType() = Type
    object Type : IStubFileElementType<FkFileStub>(FkLanguage) {
        override fun getStubVersion(): Int = 1

        override fun getExternalId(): String = "Feakin.file"

        override fun serialize(stub: FkFileStub, dataStream: StubOutputStream) {
            super.serialize(stub, dataStream)
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkFileStub {
            return super.deserialize(dataStream, parentStub)
        }

        override fun getBuilder(): StubBuilder = object : DefaultStubBuilder() {
            override fun createStubForFile(file: PsiFile): StubElement<*> {
                return super.createStubForFile(file)
            }
        }
    }
}
