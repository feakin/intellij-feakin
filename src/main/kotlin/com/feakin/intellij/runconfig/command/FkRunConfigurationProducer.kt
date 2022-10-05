package com.feakin.intellij.runconfig.command

import com.feakin.intellij.psi.FeakinImplDeclaration
import com.feakin.intellij.runconfig.FkCommandConfiguration
import com.feakin.intellij.runconfig.FkCommandConfigurationType
import com.feakin.intellij.runconfig.config.RunImplConfig
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement

private typealias RunImplConfigProvider = (List<PsiElement>) -> RunImplConfig?

class FkRunConfigurationProducer : LazyRunConfigurationProducer<FkCommandConfiguration>() {
    private val commandName: String = "gen"

    private val runConfigProviders: MutableList<RunImplConfigProvider> = mutableListOf()
    override fun getConfigurationFactory(): ConfigurationFactory {
        return FkCommandConfigurationType.getInstance().factory
    }

    init {
        registerConfigProvider { elements -> createConfigFor<FeakinImplDeclaration>(elements) }
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

    fun findImplConfig(elements: List<PsiElement>): RunImplConfig? {
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
        val implConfig = findImplConfig(context) ?: return false
        sourceElement.set(implConfig.sourceElement)

        configuration.name = implConfig.configurationName
        configuration.setCommand(implConfig.fkCommandLine)

        return true
    }

    private fun findImplConfig(context: ConfigurationContext): RunImplConfig? {
        val elements = LangDataKeys.PSI_ELEMENT_ARRAY.getData(context.dataContext)?.toList()
            ?: context.location?.psiElement?.let { listOf(it) }
        return elements?.let { findImplConfig(it) }
    }

    override fun isConfigurationFromContext(
        configuration: FkCommandConfiguration,
        context: ConfigurationContext
    ): Boolean {
        val implConfig = findImplConfig(context) ?: return false
        configuration.name = implConfig.configurationName
        configuration.setCommand(implConfig.fkCommandLine)

        return true
    }
}
