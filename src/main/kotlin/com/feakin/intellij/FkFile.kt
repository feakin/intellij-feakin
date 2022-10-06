package com.feakin.intellij

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.stubs.StubElement

class FkFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, FkLanguage) {
    override fun getFileType(): FileType = FkFileType

    override fun getOriginalFile(): FkFile = super.getOriginalFile() as FkFile

    override fun toString(): String = "Feakin"

    override fun getStub(): FkFileStub {
        return super.getStub() as FkFileStub
    }
}
