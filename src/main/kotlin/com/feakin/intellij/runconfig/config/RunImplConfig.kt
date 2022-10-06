package com.feakin.intellij.runconfig.config

import com.feakin.intellij.psi.FkImplDeclaration

data class RunImplConfig(
    val commandName: String,
    val path: String,
    val sourceElement: FkImplDeclaration
) {
    private val implName: String = sourceElement.implName?.nameComponent?.identifier?.text ?: ""
    val configurationName: String = "Gen $implName"

    val fkCommandLine = fromImplDecl(sourceElement, path)
}

private fun fromImplDecl(feakinImplDecl: FkImplDeclaration, path: String): FkCommandLine {
    val implName = feakinImplDecl.implName?.nameComponent?.identifier?.text ?: ""
    val subcommand = "gen"
    return FkCommandLine(path, implName, subcommand)
}

