package com.feakin.intellij.runconfig.command

import com.feakin.intellij.runconfig.FkCommandConfiguration
import com.intellij.execution.PsiLocation
import com.intellij.execution.RunManager
import com.intellij.execution.RunnerAndConfigurationSettings
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.ConfigurationFromContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.RunConfiguration

abstract class FkLazyRunConfigurationProducer: LazyRunConfigurationProducer<FkCommandConfiguration>() {
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