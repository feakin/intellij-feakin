package com.feakin.intellij.runconfig.implementation

import com.intellij.psi.PsiElement
import com.intellij.util.Function

data class RunImplConfig(
    val commandName: String,
    val path: String,
    val impl: String,
    val sourceElement: PsiElement
) {
    val configurationName: Function<in PsiElement, String> = Function {
        "$commandName $path $impl"
    }
}