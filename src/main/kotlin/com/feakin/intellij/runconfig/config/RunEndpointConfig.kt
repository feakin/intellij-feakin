package com.feakin.intellij.runconfig.config

import com.feakin.intellij.psi.FkEndpointDeclaration
import com.feakin.intellij.psi.FkImplDeclaration

data class RunEndpointConfig(
    override val commandName: String,
    override val main: String,
    override val sourceElement: FkEndpointDeclaration
) : BaseConfig<FkEndpointDeclaration>(commandName, main, sourceElement) {
    val parent = (sourceElement.parent.parent as FkImplDeclaration)
    override val implName: String = parent.identifier.text ?: ""
    override val configurationName: String = "Req $implName"
    override val fkCommandLine = fromImplDecl(parent, main)

    private fun fromImplDecl(feakinImplDecl: FkImplDeclaration, path: String): FkCommandLine {
        val implName = feakinImplDecl.identifier.text ?: ""
        val subcommand = "run"
        return FkCommandLine(path, implName, subcommand, "http-request", "")
    }
}
