package com.feakin.intellij.ide.editor

import com.feakin.intellij.psi.FkFlowBody
import com.feakin.intellij.psi.FkStepDeclaration
import com.intellij.codeInsight.editorActions.ExtendWordSelectionHandlerBase
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class FkFlowEntrySelectionHandler : ExtendWordSelectionHandlerBase() {
    override fun canSelect(e: PsiElement): Boolean = e is FkStepDeclaration

    override fun select(e: PsiElement, editorText: CharSequence, cursorOffset: Int, editor: Editor): List<TextRange> {
        val result = super.select(e, editorText, cursorOffset, editor) ?: mutableListOf()
        val flow = e.parent as FkFlowBody
        val flowStart = flow.startOffset
        val flowEnd = flow.endOffset
        result += TextRange(flowStart, flowEnd)
        return result
    }
}