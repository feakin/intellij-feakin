package com.feakin.intellij

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class FeakinFileType : LanguageFileType(FeakinLanguage) {
    override fun getName(): String {
        return "Feakin File"
    }

    override fun getDescription(): String {
        return "Feakin language file"
    }

    override fun getDefaultExtension(): String {
        return "fkl"
    }

    override fun getIcon(): Icon {
        return FeakinIcons.FILE
    }

    companion object {
        val INSTANCE = FeakinFileType()
    }
}