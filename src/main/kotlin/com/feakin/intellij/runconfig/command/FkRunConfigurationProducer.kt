package com.feakin.intellij.runconfig.command

import com.feakin.intellij.psi.FeakinImplDeclaration
import com.feakin.intellij.runconfig.FkCommandConfiguration
import com.feakin.intellij.runconfig.FkCommandConfigurationType
import com.feakin.intellij.runconfig.FkRunState
import com.feakin.intellij.runconfig.config.RunImplConfig
import com.intellij.execution.PsiLocation
import com.intellij.execution.RunManager
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.ConfigurationFromContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement

private typealias RunImplConfigProvider = (List<PsiElement>) -> RunImplConfig?

class FkRunConfigurationProducer : LazyRunConfigurationProducer<FkCommandConfiguration>() {
    companion object {
        private val log: Logger = logger<FkRunState>()
    }

    private val commandName: String = "gen"

    // lost some config in here
    private val runConfigProviders: MutableList<RunImplConfigProvider> = mutableListOf()
    override fun getConfigurationFactory(): ConfigurationFactory {
        return FkCommandConfigurationType.getInstance().factory
    }

    init {
        registerConfigProvider { elements -> createConfigFor<FeakinImplDeclaration>(elements) }
        log.debug("Registered ${runConfigProviders.size} config providers")
    }

    private inline fun <reified T : FeakinImplDeclaration> createConfigFor(
        elements: List<PsiElement>
    ): RunImplConfig? {
        val path = elements.firstOrNull()?.containingFile?.virtualFile?.path ?: return null
        val sourceElement = elements.firstOrNull { it is T } ?: return null
        return RunImplConfig(commandName, path, sourceElement as FeakinImplDeclaration)
    }

    private fun registerConfigProvider(provider: RunImplConfigProvider) {
        runConfigProviders.add(provider)
    }

    override fun setupConfigurationFromContext(
        configuration: FkCommandConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>
    ): Boolean {
        val implConfig = findImplConfigByContext(context) ?: return false
        sourceElement.set(implConfig.sourceElement)

        configuration.name = implConfig.configurationName
        configuration.setCommand(implConfig.fkCommandLine)

        return true
    }

    fun findImplConfig(elements: List<PsiElement>): RunImplConfig? {
        for (provider in runConfigProviders) {
            val config = provider(elements)
            if (config != null) return config
        }

        return null
    }

    private fun findImplConfigByContext(context: ConfigurationContext): RunImplConfig? {
        val elements = context.location?.psiElement?.let { listOf(it) }
        return elements?.let { findImplConfig(it) }
    }

    override fun isConfigurationFromContext(
        configuration: FkCommandConfiguration,
        context: ConfigurationContext
    ): Boolean {
        val implConfig = findImplConfigByContext(context) ?: return false
        configuration.name = implConfig.configurationName
        configuration.setCommand(implConfig.fkCommandLine)

        return true
    }

    override fun findOrCreateConfigurationFromContext(context: ConfigurationContext): ConfigurationFromContext? {
        val preferredConfig = createConfigurationFromContext(context) ?: return null

        val psiElement = preferredConfig.sourceElement
        val locationFromContext = context.location ?: return null
        val locationFromElement = PsiLocation.fromPsiElement(psiElement, locationFromContext.module)
        if (locationFromElement != null) {
            val settings = findExistingConfiguration(context)
            if (preferredConfig.configuration.isSame(settings?.configuration)) {
                preferredConfig.setConfigurationSettings(settings)
            } else {
                RunManager.getInstance(context.project).setUniqueNameIfNeeded(preferredConfig.configuration)
            }
        }
        return preferredConfig
    }

    private fun RunConfiguration.isSame(other: RunConfiguration?): Boolean =
        when {
            this === other -> true
            this !is FkCommandConfiguration || other !is FkCommandConfiguration -> equals(other)
            command != other.command -> false
            else -> true
        }

}
