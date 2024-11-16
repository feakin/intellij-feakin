package com.feakin.intellij.colors

import com.feakin.intellij.FkBundle
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.util.NlsContexts
import java.util.function.Supplier

enum class FkColors(humanName: Supplier<@NlsContexts.AttributeDescriptor String>, default: TextAttributesKey? = null) {
    COMMENT(FkBundle.messagePointer("settings.feakin.color.inline.doc"), DefaultLanguageHighlighterColors.DOC_COMMENT),
    INCLUDE(FkBundle.messagePointer("settings.feakin.color.include"), DefaultLanguageHighlighterColors.IDENTIFIER),
    CUSTOM(FkBundle.messagePointer("settings.feakin.color.env.custom"), DefaultLanguageHighlighterColors.DOC_COMMENT),
    FIELD(FkBundle.messagePointer("settings.feakin.color.field"), DefaultLanguageHighlighterColors.INSTANCE_FIELD),
    METHOD_CALL(FkBundle.messagePointer("settings.feakin.color.flow.method.call"), DefaultLanguageHighlighterColors.INSTANCE_FIELD),
    VARIABLE(FkBundle.messagePointer("settings.feakin.color.variable"), DefaultLanguageHighlighterColors.LOCAL_VARIABLE),
    STRING(FkBundle.messagePointer("settings.feakin.color.string"), DefaultLanguageHighlighterColors.STRING),
    CONSTANT(FkBundle.messagePointer("settings.feakin.color.constant"), DefaultLanguageHighlighterColors.CONSTANT),
    KEYWORD(FkBundle.messagePointer("settings.feakin.color.keyword"), DefaultLanguageHighlighterColors.KEYWORD),
    IDENTIFIER(FkBundle.messagePointer("settings.feakin.color.identifier"), DefaultLanguageHighlighterColors.IDENTIFIER),
    NUMBER(FkBundle.messagePointer("settings.feakin.color.number"), DefaultLanguageHighlighterColors.NUMBER),
    ;

    val textAttributesKey = TextAttributesKey.createTextAttributesKey("com.feakin.$name", default)
    val attributesDescriptor = AttributesDescriptor(humanName, textAttributesKey)
}
