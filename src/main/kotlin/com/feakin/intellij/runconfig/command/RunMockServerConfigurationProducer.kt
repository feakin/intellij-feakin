package com.feakin.intellij.runconfig.command

import com.feakin.intellij.psi.FkServerDecl
import com.feakin.intellij.runconfig.config.RunMockServerConfig
import com.intellij.psi.PsiElement

class RunMockServerConfigurationProducer : BaseLazyRunConfigurationProducer<RunMockServerConfig, FkServerDecl>() {
    init {
        registerConfigProvider { elements -> createConfigFor<FkServerDecl>(elements) }
    }

    private inline fun <reified T : FkServerDecl> createConfigFor(
        elements: List<PsiElement>
    ): RunMockServerConfig? {
        val path = elements.firstOrNull()?.containingFile?.virtualFile?.path ?: return null
        val sourceElement = elements.firstOrNull { it is T } ?: return null
        return RunMockServerConfig(commandName, path, sourceElement as FkServerDecl)
    }

    private fun registerConfigProvider(provider: (List<PsiElement>) -> RunMockServerConfig?) {
        runConfigProviders.add(provider)
    }
}
