package com.feakin.intellij.runconfig.config

import com.feakin.intellij.psi.FkEndpointDeclaration
import com.feakin.intellij.psi.FkImplDeclaration

data class RunEndpointConfig(
    val commandName: String,
    val path: String,
    val sourceElement: FkEndpointDeclaration
) {
    val parent = (sourceElement.parent.parent as FkImplDeclaration)
    private val implName: String = parent.identifier.text ?: ""
    val configurationName: String = "Req $implName"

    val fkCommandLine = fromImplDecl(parent, path)
}

private fun fromImplDecl(feakinImplDecl: FkImplDeclaration, path: String): FkCommandLine {
    val implName = feakinImplDecl.identifier.text ?: ""
    val subcommand = "run"
    return FkCommandLine(path, implName, subcommand, "http-request")
}
