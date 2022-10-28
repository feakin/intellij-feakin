package com.feakin.intellij.runconfig.config

import com.feakin.intellij.psi.FkEndpointDeclaration
import com.feakin.intellij.psi.FkImplDeclaration
import com.feakin.intellij.psi.FkLayeredDeclaration
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.SystemIndependent

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
        return FkCommandLine(path, implName, "run", "http-request", "")
    }
}

data class RunGuardingConfig(
    override val commandName: String,
    override val main: String,
    override val sourceElement: FkLayeredDeclaration,
    val rootPath: @SystemIndependent @NonNls String
) : BaseConfig<FkLayeredDeclaration>(commandName, rootPath, sourceElement) {
    override val implName: String = sourceElement.identifier.text ?: ""
    override val configurationName: String = "Guard $implName"

    override val fkCommandLine = fromLayeredDecl(sourceElement, main, rootPath)

    private fun fromLayeredDecl(
        sourceElement: FkLayeredDeclaration,
        config: String,
        rootPath: @SystemIndependent @NonNls String
    ): FkCommandLine {
        val implName = sourceElement.identifier.text ?: ""
        return FkCommandLine(config, implName, "run", "guarding", rootPath)
    }
}

class GencodeConfig(
    override val commandName: String,
    override val main: String,
    override val sourceElement: FkImplDeclaration
) : BaseConfig<FkImplDeclaration>(commandName, main, sourceElement) {
    override val configurationName: String = "Gen $implName"
    override val fkCommandLine = fromImplDecl(sourceElement, main)

    override fun fromImplDecl(decl: FkImplDeclaration, path: String): FkCommandLine {
        val implName = decl.identifier.text ?: ""
        return FkCommandLine(path, implName, "gen", path = "")
    }
}