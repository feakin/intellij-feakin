package com.feakin.intellij.ide.editor

import com.feakin.intellij.lexer.FkElementTypes.STRING_LITERAL
import com.intellij.codeInsight.editorActions.ExtendWordSelectionHandlerBase
import com.intellij.codeInsight.editorActions.SelectWordUtil
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.TokenSet

class FkStringLiteralSelectionHandler : ExtendWordSelectionHandlerBase() {
    override fun canSelect(e: PsiElement): Boolean = e.elementType in TokenSet.create(STRING_LITERAL)

    override fun select(e: PsiElement, editorText: CharSequence, cursorOffset: Int, editor: Editor): List<TextRange> {
        val result = super.select(e, editorText, cursorOffset, editor) ?: mutableListOf()
        return result
    }
}
