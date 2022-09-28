package com.feakin.intellij.runconfig.ui

import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.editor.EditorModificationUtil
import com.intellij.util.TextFieldCompletionProvider

class FkCommandCompletionProvider : TextFieldCompletionProvider() {
    override fun addCompletionVariants(text: String, offset: Int, prefix: String, result: CompletionResultSet) {
        val element = LookupElementBuilder.create("fkl auto").withInsertHandler { ctx, _ ->
            ctx.addSuffix(" ")
        }

        result.addElement(element)
    }

}

fun InsertionContext.addSuffix(suffix: String) {
    document.insertString(selectionEndOffset, suffix)
    EditorModificationUtil.moveCaretRelatively(editor, suffix.length)
}
