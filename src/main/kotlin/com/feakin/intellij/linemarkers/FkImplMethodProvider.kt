package com.feakin.intellij.linemarkers

import com.feakin.intellij.FkIcons
import com.feakin.intellij.psi.FkViaMethodDeclaration
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.psi.PsiElement
import java.awt.event.MouseEvent

class FkImplMethodProvider : LineMarkerProvider {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<PsiElement>? {
        if (element !is FkViaMethodDeclaration) return null

        return LineMarkerInfo(
            element,
            element.textRange,
            FkIcons.Method,
            { "create Method call (TODO)" },
            { _: MouseEvent?, _: PsiElement? -> run {} },
            GutterIconRenderer.Alignment.LEFT,
            { "Method Call" }
        )
    }
}