package com.feakin.intellij.runconfig.config

import com.feakin.intellij.psi.FkLayeredDeclaration

data class RunGuardingConfig(
    val commandName: String,
    val path: String,
    val sourceElement: FkLayeredDeclaration
) {
    private val implName: String = sourceElement.identifier.text ?: ""
    val configurationName: String = "Gen $implName"

    val fkCommandLine = fromImplDecl(sourceElement, path)
}

private fun fromImplDecl(feakinImplDecl: FkLayeredDeclaration, path: String): FkCommandLine {
    val implName = feakinImplDecl.identifier.text ?: ""
    val subcommand = "run"
    return FkCommandLine(path, implName, subcommand, "http-request", "")
}
