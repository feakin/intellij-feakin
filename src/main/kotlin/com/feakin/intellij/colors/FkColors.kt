package com.feakin.intellij.colors

import com.feakin.intellij.FkBundle
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.util.NlsContexts
import java.util.function.Supplier

@Suppress("UnstableApiUsage")
enum class FkColors(humanName: Supplier<@NlsContexts.AttributeDescriptor String>, default: TextAttributesKey? = null) {
    COMMENT(FkBundle.messagePointer("settings.feakin.color.inline.doc"), DefaultLanguageHighlighterColors.DOC_COMMENT),
    ;

    val textAttributesKey = TextAttributesKey.createTextAttributesKey("com.feakin.$name", default)
    val attributesDescriptor = AttributesDescriptor(humanName, textAttributesKey)
}