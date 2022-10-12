package com.feakin.intellij.linemarkers

import com.feakin.intellij.FkIcons
import com.feakin.intellij.psi.FkLayeredDeclaration
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.psi.PsiElement
import java.awt.event.MouseEvent

class FkLayeredLineMarkerProvider : LineMarkerProvider {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<PsiElement>? {
        if (element !is FkLayeredDeclaration) return null

        return LineMarkerInfo(
            element,
            element.textRange,
            FkIcons.RUN,
            { "Layered check (TODO)" },
            { _: MouseEvent?, _: PsiElement? -> run {} },
            GutterIconRenderer.Alignment.LEFT,
            { "Message Queue" }
        )
    }
}