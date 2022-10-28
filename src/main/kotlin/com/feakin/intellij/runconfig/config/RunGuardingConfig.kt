package com.feakin.intellij.runconfig.config

import com.feakin.intellij.psi.FkLayeredDeclaration
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.SystemIndependent

data class RunGuardingConfig(
    override val commandName: String,
    override val main: String,
    override val sourceElement: FkLayeredDeclaration,
    val rootPath: @SystemIndependent @NonNls String
) : BaseConfig<FkLayeredDeclaration>(commandName, rootPath, sourceElement) {
    override val implName: String = sourceElement.identifier.text ?: ""
    override val configurationName: String = "Guard $implName"

    override val fkCommandLine = fromImplDecl(sourceElement, main, rootPath)

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
