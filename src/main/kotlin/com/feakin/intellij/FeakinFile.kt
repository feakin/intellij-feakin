package com.feakin.intellij

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class FeakinFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, FeakinLanguage) {
    override fun getFileType(): FileType = FeakinFileType;

    override fun getOriginalFile(): FeakinFile = super.getOriginalFile() as FeakinFile

    override fun toString(): String {
        return "Feakin"
    }
}