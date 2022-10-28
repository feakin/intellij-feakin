package com.feakin.intellij.runconfig.command

import com.feakin.intellij.psi.FkLayeredDeclaration
import com.feakin.intellij.runconfig.FkCommandConfiguration
import com.feakin.intellij.runconfig.FkCommandConfigurationType
import com.feakin.intellij.runconfig.FkRunState
import com.feakin.intellij.runconfig.config.RunGuardingConfig
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement

class FkLayeredGuardingConfigurationProducer : BaseLazyRunConfigurationProducer<RunGuardingConfig>() {
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

    fun findLayered(elements: List<PsiElement>): RunGuardingConfig? {
        for (provider in runConfigProviders) {
            val config = provider(elements)
            if (config != null) return config
        }

        return null
    }

    override fun setupConfigurationFromContext(
        configuration: FkCommandConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>
    ): Boolean {
        val layered = findLayeredContext(context) ?: return false
        sourceElement.set(layered.sourceElement)

        configuration.name = layered.configurationName
        configuration.setCommand(layered.fkCommandLine)

        return true
    }

    private fun findLayeredContext(context: ConfigurationContext): RunGuardingConfig? {
        val elements = context.location?.psiElement?.let { listOf(it) }
        return elements?.let { findLayered(it) }
    }

    override fun isConfigurationFromContext(
        configuration: FkCommandConfiguration,
        context: ConfigurationContext
    ): Boolean {
        val implConfig = findLayeredContext(context) ?: return false
        configuration.name = implConfig.configurationName
        configuration.setCommand(implConfig.fkCommandLine)

        return true
    }
}