package com.feakin.intellij.runconfig.command

import com.feakin.intellij.psi.FkEndpointDeclaration
import com.feakin.intellij.runconfig.config.RunEndpointConfig
import com.intellij.psi.PsiElement

class RunEndpointConfigurationProducer : BaseLazyRunConfigurationProducer<RunEndpointConfig, FkEndpointDeclaration>() {
    init {
        registerConfigProvider { elements -> createConfigFor<FkEndpointDeclaration>(elements) }
    }

    private inline fun <reified T : FkEndpointDeclaration> createConfigFor(
        elements: List<PsiElement>
    ): RunEndpointConfig? {
        val path = elements.firstOrNull()?.containingFile?.virtualFile?.path ?: return null
        val sourceElement = elements.firstOrNull { it is T } ?: return null
        return RunEndpointConfig(commandName, path, sourceElement as FkEndpointDeclaration)
    }

    private fun registerConfigProvider(provider: (List<PsiElement>) -> RunEndpointConfig?) {
        runConfigProviders.add(provider)
    }
}