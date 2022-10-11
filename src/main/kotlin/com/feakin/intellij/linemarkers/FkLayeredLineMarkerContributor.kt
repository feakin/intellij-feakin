package com.feakin.intellij.linemarkers

import com.feakin.intellij.FkIcons
import com.feakin.intellij.psi.FkLayeredDeclaration
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.psi.PsiElement
import java.awt.event.MouseEvent

class FkLayeredLineMarkerContributor : LineMarkerProvider {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<PsiElement>? {
        if (element !is FkLayeredDeclaration) return null

        return LineMarkerInfo(
            element,
            element.textRange,
            FkIcons.FILE,
            { "Layered Line Marker" },
            { _: MouseEvent?, _: PsiElement? -> run {} },
            GutterIconRenderer.Alignment.LEFT,
            { "Message Queue" }
        )
    }
}