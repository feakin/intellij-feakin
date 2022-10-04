package com.feakin.intellij.linemarkers

import com.feakin.intellij.FkIcons
import com.feakin.intellij.psi.FeakinViaMessageDeclaration
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.psi.PsiElement
import java.awt.event.MouseEvent

class FkImplMessageProvider : LineMarkerProvider {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<PsiElement>? {
        if (element !is FeakinViaMessageDeclaration) return null

        return LineMarkerInfo(
            element,
            element.textRange,
            FkIcons.Message,
            { "Message Queue" },
            { _: MouseEvent?, _: PsiElement? -> run {} },
            GutterIconRenderer.Alignment.LEFT,
            { "Message Queue" }
        )
    }
}