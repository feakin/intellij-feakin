package com.feakin.intellij.runconfig.command

import com.feakin.intellij.psi.FkImplDeclaration
import com.feakin.intellij.runconfig.config.GenImplConfig
import com.intellij.psi.PsiElement

class FkGenImplConfigurationProducer : BaseLazyRunConfigurationProducer<GenImplConfig, FkImplDeclaration>() {
    override val commandName: String = "gen"

    init {
        registerConfigProvider { elements -> createConfigFor<FkImplDeclaration>(elements) }
    }

    private inline fun <reified T : FkImplDeclaration> createConfigFor(
        elements: List<PsiElement>
    ): GenImplConfig? {
        val path = elements.firstOrNull()?.containingFile?.virtualFile?.path ?: return null
        val sourceElement = elements.firstOrNull { it is T } ?: return null
        return GenImplConfig(commandName, path, sourceElement as FkImplDeclaration)
    }

    private fun registerConfigProvider(provider: (List<PsiElement>) -> GenImplConfig?) {
        runConfigProviders.add(provider)
    }
}
