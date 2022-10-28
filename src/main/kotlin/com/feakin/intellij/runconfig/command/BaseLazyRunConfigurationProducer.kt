package com.feakin.intellij.runconfig.command

import com.feakin.intellij.runconfig.FkCommandConfiguration
import com.feakin.intellij.runconfig.FkCommandConfigurationType
import com.feakin.intellij.runconfig.config.RunEndpointConfig
import com.intellij.execution.PsiLocation
import com.intellij.execution.RunManager
import com.intellij.execution.RunnerAndConfigurationSettings
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.ConfigurationFromContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.psi.PsiElement


abstract class BaseLazyRunConfigurationProducer<T> : LazyRunConfigurationProducer<FkCommandConfiguration>() {

    open val commandName: String = "run"
    val runConfigProviders: MutableList<(List<PsiElement>) -> T?> = mutableListOf()

    override fun getConfigurationFactory(): ConfigurationFactory {
        return FkCommandConfigurationType.getInstance().factory
    }

    override fun findExistingConfiguration(context: ConfigurationContext): RunnerAndConfigurationSettings? {
        val preferredConfig = createConfigurationFromContext(context) ?: return null
        val runManager = RunManager.getInstance(context.project)
        val configurations = getConfigurationSettingsList(runManager)
        for (configurationSettings in configurations) {
            if (preferredConfig.configuration.isSame(configurationSettings.configuration)) {
                return configurationSettings
            }
        }

        return null
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