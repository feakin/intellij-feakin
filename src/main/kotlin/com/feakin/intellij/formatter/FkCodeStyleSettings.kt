package com.feakin.intellij.formatter

import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CustomCodeStyleSettings

// todo: add settings
@Suppress("PropertyName")
class FkCodeStyleSettings(container: CodeStyleSettings) :
    CustomCodeStyleSettings(FkCodeStyleSettings::class.java.simpleName, container) {

    @JvmField
    var ALIGN_RET_TYPE: Boolean = true

    @JvmField
    var ALIGN_WHERE_CLAUSE: Boolean = false

    @JvmField
    var ALIGN_TYPE_PARAMS: Boolean = false

    @JvmField
    var ALIGN_WHERE_BOUNDS: Boolean = true

    @JvmField
    var INDENT_WHERE_CLAUSE: Boolean = true

    @JvmField
    var ALLOW_ONE_LINE_MATCH: Boolean = false

    @JvmField
    var MIN_NUMBER_OF_BLANKS_BETWEEN_ITEMS: Int = 1

    @JvmField
    var PRESERVE_PUNCTUATION: Boolean = false

    @JvmField
    var SPACE_AROUND_ASSOC_TYPE_BINDING: Boolean = false
}

val CodeStyleSettings.feakin: FkCodeStyleSettings
    get() = getCustomSettings(FkCodeStyleSettings::class.java)
