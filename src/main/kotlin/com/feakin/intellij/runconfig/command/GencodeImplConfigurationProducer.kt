package com.feakin.intellij.runconfig.command

import com.feakin.intellij.psi.FkImplDeclaration
import com.feakin.intellij.runconfig.config.GencodeConfig
import com.intellij.psi.PsiElement

class GencodeImplConfigurationProducer : BaseLazyRunConfigurationProducer<GencodeConfig, FkImplDeclaration>() {
    override val commandName: String = "gen"

    init {
        registerConfigProvider { elements -> createConfigFor<FkImplDeclaration>(elements) }
    }

    private inline fun <reified T : FkImplDeclaration> createConfigFor(
        elements: List<PsiElement>
    ): GencodeConfig? {
        val path = elements.firstOrNull()?.containingFile?.virtualFile?.path ?: return null
        val sourceElement = elements.firstOrNull { it is T } ?: return null
        return GencodeConfig(commandName, path, sourceElement as FkImplDeclaration)
    }

    private fun registerConfigProvider(provider: (List<PsiElement>) -> GencodeConfig?) {
        runConfigProviders.add(provider)
    }
}
