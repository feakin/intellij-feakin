package com.feakin.intellij

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.Icon

object FeakinFileType : LanguageFileType(FeakinLanguage) {

    override fun getName(): String = "Feakin File"

    override fun getIcon(): Icon = FkIcons.FILE

    override fun getDefaultExtension(): String = "fkl"

    override fun getCharset(file: VirtualFile, content: ByteArray): String = "UTF-8"

    override fun getDescription(): String = "Feakin DSL file"
}