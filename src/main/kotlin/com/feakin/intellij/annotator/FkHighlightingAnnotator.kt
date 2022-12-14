package com.feakin.intellij.annotator

import com.feakin.intellij.colors.FkColors
import com.feakin.intellij.ide.editor.elementType
import com.feakin.intellij.lexer.FkElementTypes
import com.feakin.intellij.psi.FkCustomKeyword
import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkSourceSetName
import com.feakin.intellij.psi.FkStructEntry
import com.feakin.intellij.psi.FkVariableBody
import com.feakin.intellij.psi.FkVariableDeclaration
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement

class FkHighlightingAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val parent = element.parent as? FkElement ?: return
        when (element.elementType) {
            FkElementTypes.IDENTIFIER -> {
                highlightIdentifier(parent, holder)
            }
        }
    }

    private fun highlightIdentifier(parent: FkElement, holder: AnnotationHolder) {
        when (parent) {
            is FkCustomKeyword, is FkSourceSetName -> {
                val color = FkColors.CUSTOM
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .textAttributes(color.textAttributesKey)
                    .create()
            }
            is FkVariableDeclaration -> {
                val color = FkColors.VARIABLE
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .textAttributes(color.textAttributesKey)
                    .create()
            }
            is FkStructEntry -> {
                val color = FkColors.FIELD
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .textAttributes(color.textAttributesKey)
                    .create()
            }
        }
    }

}