package com.feakin.intellij.runconfig.command

import com.feakin.intellij.psi.FkDatasourceDecl
import com.feakin.intellij.runconfig.config.RunDatabaseConfig
import com.intellij.psi.PsiElement

class RunDatasourceConfigurationProducer : BaseLazyRunConfigurationProducer<RunDatabaseConfig, FkDatasourceDecl>() {
    init {
        registerConfigProvider { elements -> createConfigFor<FkDatasourceDecl>(elements) }
    }

    private inline fun <reified T : FkDatasourceDecl> createConfigFor(
        elements: List<PsiElement>
    ): RunDatabaseConfig? {
        val path = elements.firstOrNull()?.containingFile?.virtualFile?.path ?: return null
        val sourceElement = elements.firstOrNull { it is T } ?: return null
        return RunDatabaseConfig(commandName, path, sourceElement as FkDatasourceDecl)
    }

    private fun registerConfigProvider(provider: (List<PsiElement>) -> RunDatabaseConfig?) {
        runConfigProviders.add(provider)
    }
}
