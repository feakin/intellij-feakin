package com.feakin.intellij.formatter

import com.feakin.intellij.FkLanguage
import com.intellij.formatting.Alignment
import com.intellij.formatting.SpacingBuilder
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CommonCodeStyleSettings

data class FkFmtContext(
    val commonSettings: CommonCodeStyleSettings,
    val spacingBuilder: SpacingBuilder,
    val sharedAlignment: Alignment? = null,
) {
    companion object {
        fun create(settings: CodeStyleSettings): FkFmtContext {
            val commonSettings = settings.getCommonSettings(FkLanguage)
            return FkFmtContext(commonSettings, createSpaceBuilder(settings))
        }
    }
}
