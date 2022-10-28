package com.feakin.intellij.runconfig.command

import com.feakin.intellij.psi.FkLayeredDeclaration
import com.feakin.intellij.runconfig.config.RunGuardingConfig
import com.intellij.psi.PsiElement

class RunGuardingConfigurationProducer :
    BaseLazyRunConfigurationProducer<RunGuardingConfig, FkLayeredDeclaration>() {
    init {
        registerConfigProvider { elements -> createConfigFor<FkLayeredDeclaration>(elements) }
    }

    private inline fun <reified T : FkLayeredDeclaration> createConfigFor(
        elements: List<PsiElement>
    ): RunGuardingConfig? {
        val config = elements.firstOrNull()?.containingFile?.virtualFile?.path ?: return null
        val sourceElement = elements.firstOrNull { it is T } ?: return null

        val rootPath = sourceElement.project.basePath ?: return null
        return RunGuardingConfig(commandName, config, sourceElement as FkLayeredDeclaration, rootPath)
    }

    private fun registerConfigProvider(provider: (List<PsiElement>) -> RunGuardingConfig?) {
        runConfigProviders.add(provider)
    }
}