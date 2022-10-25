package com.feakin.intellij.runconfig.config

import com.feakin.intellij.psi.FkLayeredDeclaration
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.SystemIndependent

data class RunGuardingConfig(
    val commandName: String,
    val config: String,
    val sourceElement: FkLayeredDeclaration,
    val rootPath: @SystemIndependent @NonNls String
) {
    private val implName: String = sourceElement.identifier.text ?: ""
    val configurationName: String = "Guard $implName"

    val fkCommandLine = fromImplDecl(sourceElement, config, rootPath)

    private fun fromImplDecl(
        sourceElement: FkLayeredDeclaration,
        config: String,
        rootPath: @SystemIndependent @NonNls String
    ): FkCommandLine {
        val implName = sourceElement.identifier.text ?: ""
        val subcommand = "run"
        return FkCommandLine(config, implName, subcommand, "guarding", rootPath)
    }
}
