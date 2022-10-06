package com.feakin.intellij

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiFile
import com.intellij.psi.StubBuilder
import com.intellij.psi.stubs.*
import com.intellij.psi.tree.IStubFileElementType
import javax.swing.Icon

class FkFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, FkLanguage) {
    override fun getFileType(): FileType = FkFileType

    override fun getOriginalFile(): FkFile = super.getOriginalFile() as FkFile

    override fun toString(): String = "Feakin"

    override fun getStub(): FkFileStub?  = super.getStub() as FkFileStub?

    override fun getIcon(flags: Int): Icon? {
        return super.getIcon(flags)
    }
}

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