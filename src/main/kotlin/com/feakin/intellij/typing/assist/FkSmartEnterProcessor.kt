package com.feakin.intellij.typing.assist

import com.intellij.lang.SmartEnterProcessorWithFixers
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

class FkSmartEnterProcessor : SmartEnterProcessorWithFixers() {
    init {
        addEnterProcessors(
            PlainEnterProcessor()
        )
    }

    private class PlainEnterProcessor : FixEnterProcessor() {
        override fun doEnter(atCaret: PsiElement, file: PsiFile, editor: Editor, modified: Boolean): Boolean {
            plainEnter(editor)
            return true
        }
    }
}

