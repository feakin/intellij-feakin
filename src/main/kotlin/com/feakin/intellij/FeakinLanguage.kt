package com.feakin.intellij

import com.intellij.lang.Language

object FeakinLanguage : Language("Feakin", "text/feakin", "text/x-feakin", "application/x-feakin") {
    override fun isCaseSensitive() = true

    override fun getDisplayName() = "Feakin"
}