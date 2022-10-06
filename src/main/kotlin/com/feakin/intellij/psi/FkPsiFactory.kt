package com.feakin.intellij.psi

import com.feakin.intellij.FkFile
import com.feakin.intellij.FkFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiTreeUtil

class FkPsiFactory(private val project: Project) {
    fun createFile(text: CharSequence): FkFile {
        return createPsiFile(text) as FkFile
    }

    private fun createPsiFile(text: CharSequence): PsiFile =
        PsiFileFactory.getInstance(project)
            .createFileFromText("DUMMY.fkl", FkFileType, text) as FkFile

    private inline fun <reified T : FkElement> createFromText(code: CharSequence): T? =
        createFile(code).descendantOfTypeStrict()

    fun createContext(text: String): FkContextDeclaration? = createFromText(text)

    fun createImpl(text: String): FkImplDeclaration? = createFromText(text)

    fun createIdentifier(name: String): PsiElement {
        return createImpl("impl ${name} {} ")?.identifier ?: error("Failed to create identifier for name `$name`")
    }
}

inline fun <reified T : PsiElement> PsiElement.descendantOfTypeStrict(): T? =
    PsiTreeUtil.findChildOfType(this, T::class.java, /* strict */ true)
