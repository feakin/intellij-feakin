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
    INCLUDE(FkBundle.messagePointer("settings.feakin.color.include"), DefaultLanguageHighlighterColors.IDENTIFIER),
    CUSTOM(FkBundle.messagePointer("settings.feakin.color.env.custom"), DefaultLanguageHighlighterColors.DOC_COMMENT),
    FIELD(FkBundle.messagePointer("settings.feakin.color.field"), DefaultLanguageHighlighterColors.INSTANCE_FIELD),
    METHOD_CALL(FkBundle.messagePointer("settings.feakin.color.field"), DefaultLanguageHighlighterColors.INSTANCE_FIELD),
    VARIABLE(FkBundle.messagePointer("settings.feakin.color.variable"), DefaultLanguageHighlighterColors.LOCAL_VARIABLE),
    ;

    val textAttributesKey = TextAttributesKey.createTextAttributesKey("com.feakin.$name", default)
    val attributesDescriptor = AttributesDescriptor(humanName, textAttributesKey)
}
