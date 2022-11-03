package com.feakin.intellij.annotator

import com.feakin.intellij.colors.FkColors
import com.feakin.intellij.ide.editor.elementType
import com.feakin.intellij.lexer.FkElementTypes
import com.feakin.intellij.psi.FkElement
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement

class FkHttpMethodHighlightingAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val parent = element.parent as? FkElement ?: return
        when (parent) {
            FkElementTypes.HTTP_METHOD -> {
                val color = FkColors.METHOD_CALL
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .textAttributes(color.textAttributesKey)
                    .create()
            }
        }
    }
}