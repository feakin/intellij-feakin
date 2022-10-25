package com.feakin.intellij.runconfig.command

import com.feakin.intellij.psi.FkEndpointDeclaration
import com.feakin.intellij.runconfig.FkCommandConfiguration
import com.feakin.intellij.runconfig.FkCommandConfigurationType
import com.feakin.intellij.runconfig.FkRunState
import com.feakin.intellij.runconfig.config.RunEndpointConfig
import com.intellij.execution.PsiLocation
import com.intellij.execution.RunManager
import com.intellij.execution.RunnerAndConfigurationSettings
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.ConfigurationFromContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement

private typealias SendRequestConfigProvider = (List<PsiElement>) -> RunEndpointConfig?

class FkEndpointConfigurationProducer : FkLazyRunConfigurationProducer() {
    companion object {
        private val log: Logger = logger<FkRunState>()
    }

    private val commandName: String = "run"
    private val runConfigProviders: MutableList<SendRequestConfigProvider> = mutableListOf()

    override fun getConfigurationFactory(): ConfigurationFactory {
        return FkCommandConfigurationType.getInstance().factory
    }

    init {
        registerConfigProvider { elements -> createConfigFor<FkEndpointDeclaration>(elements) }
        log.debug("Registered ${runConfigProviders.size} config providers")
    }

    private inline fun <reified T : FkEndpointDeclaration> createConfigFor(
        elements: List<PsiElement>
    ): RunEndpointConfig? {
        val path = elements.firstOrNull()?.containingFile?.virtualFile?.path ?: return null
        val sourceElement = elements.firstOrNull { it is T } ?: return null
        return RunEndpointConfig(commandName, path, sourceElement as FkEndpointDeclaration)
    }

    private fun registerConfigProvider(provider: SendRequestConfigProvider) {
        runConfigProviders.add(provider)
    }

    override fun setupConfigurationFromContext(
        configuration: FkCommandConfiguration,
        context: ConfigurationContext,
        sourceElement: Ref<PsiElement>
    ): Boolean {
        val endpoint = findIEndpointByContext(context) ?: return false
        sourceElement.set(endpoint.sourceElement)

        configuration.name = endpoint.configurationName
        configuration.setCommand(endpoint.fkCommandLine)

        return true
    }

    fun findRequest(elements: List<PsiElement>): RunEndpointConfig? {
        for (provider in runConfigProviders) {
            val config = provider(elements)
            if (config != null) return config
        }

        return null
    }

    private fun findIEndpointByContext(context: ConfigurationContext): RunEndpointConfig? {
        val elements = context.location?.psiElement?.let { listOf(it) }
        return elements?.let { findRequest(it) }
    }

    override fun isConfigurationFromContext(
        configuration: FkCommandConfiguration,
        context: ConfigurationContext
    ): Boolean {
        val implConfig = findIEndpointByContext(context) ?: return false
        configuration.name = implConfig.configurationName
        configuration.setCommand(implConfig.fkCommandLine)

        return true
    }
}