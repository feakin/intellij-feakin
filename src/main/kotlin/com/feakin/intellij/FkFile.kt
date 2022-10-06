package com.feakin.intellij

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiFile
import com.intellij.psi.StubBuilder
import com.intellij.psi.stubs.*
import com.intellij.psi.tree.IStubFileElementType

class FkFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, FkLanguage) {
    override fun getFileType(): FileType = FkFileType

    override fun getOriginalFile(): FkFile = super.getOriginalFile() as FkFile

    override fun toString(): String = "Feakin"

    override fun getStub(): FkFileStub? = super.getStub() as FkFileStub?
}

class FkFileStub(file: FkFile?, private val flags: Int) : PsiFileStubImpl<FkFile>(file) {

    override fun getType() = Type

    object Type : IStubFileElementType<FkFileStub>(FkLanguage) {
        override fun getStubVersion(): Int = 1

        override fun getExternalId(): String = "Feakin.file"

        override fun serialize(stub: FkFileStub, dataStream: StubOutputStream) {
            dataStream.writeByte(stub.flags)
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?): FkFileStub {
            return FkFileStub(null, dataStream.readUnsignedByte())
        }

        override fun getBuilder(): StubBuilder = object : DefaultStubBuilder() {
            override fun createStubForFile(file: PsiFile): StubElement<*> {
                return FkFileStub(file as FkFile, 0)
            }
        }
    }
}