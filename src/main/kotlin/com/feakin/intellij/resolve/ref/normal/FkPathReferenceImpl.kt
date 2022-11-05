package com.feakin.intellij.resolve.ref.normal

import com.feakin.intellij.psi.FkElement
import com.feakin.intellij.psi.FkPath
import com.feakin.intellij.resolve.ref.FkReferenceBase
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.vfs.VfsUtilCore
import com.intellij.psi.PsiElement

class FkPathReferenceImpl(element: FkPath) : FkReferenceBase<FkPath>(element) {
    override fun multiResolve(): List<FkElement> {
        val textRange = TextRange.from(1, element.textLength - 2)
        var path = element.text.substring(textRange.startOffset, textRange.endOffset)

        if (!path.endsWith(".fkl")) {
            path += ".fkl"
        }

        val file = element.containingFile
        val relativeFile = VfsUtilCore.findRelativeFile(path, file.virtualFile.parent)
        return if (relativeFile != null) {
            val psiFile = relativeFile.let { element.manager.findFile(it) }
            listOf(psiFile?.children?.get(0) as FkElement)
        } else {
            emptyList()
        }
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is FkPath && super.isReferenceTo(element)
    }
}