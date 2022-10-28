package com.feakin.intellij.runconfig.config

import com.feakin.intellij.psi.FkImplDeclaration

class GenImplConfig(
    override val commandName: String,
    override val main: String,
    override val sourceElement: FkImplDeclaration
) : BaseConfig<FkImplDeclaration>(commandName, main, sourceElement) {
    override val implName: String = sourceElement.identifier.text ?: ""
    override val configurationName: String = "Gen $implName"
    override val fkCommandLine = fromImplDecl(sourceElement, main)

    override fun fromImplDecl(decl: FkImplDeclaration, path: String): FkCommandLine {
        val implName = decl.identifier.text ?: ""
        val subcommand = "gen"
        return FkCommandLine(path, implName, subcommand, path = "")
    }
}
