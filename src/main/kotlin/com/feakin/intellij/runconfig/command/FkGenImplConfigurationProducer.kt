/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package com.feakin.intellij.runconfig.command

import com.feakin.intellij.psi.FkImplDeclaration
import com.feakin.intellij.runconfig.FkCommandConfiguration
import com.feakin.intellij.runconfig.FkCommandConfigurationType
import com.feakin.intellij.runconfig.FkRunState
import com.feakin.intellij.runconfig.config.GenImplConfig
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement

class FkGenImplConfigurationProducer : BaseLazyRunConfigurationProducer<GenImplConfig>() {
    companion object {
        private val log: Logger = logger<FkRunState>()
    }

    override val commandName: String = "gen"

    override fun getConfigurationFactory(): ConfigurationFactory {
        return FkCommandConfigurationType.getInstance().factory
    }

    init {
        registerConfigProvider { elements -> createConfigFor<FkImplDeclaration>(elements) }
        log.debug("Registered ${runConfigProviders.size} config providers")
    }

    private inline fun <reified T : FkImplDeclaration> createConfigFor(
        elements: List<PsiElement>
    ): GenImplConfig? {
        val path = elements.firstOrNull()?.containingFile?.virtualFile?.path ?: return null
        val sourceElement = elements.firstOrNull { it is T } ?: return null
        return GenImplConfig(commandName, path, sourceElement as FkImplDeclaration)
    }

    private fun registerConfigProvider(provider: (List<PsiElement>) -> GenImplConfig?) {
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

    fun findImplConfig(elements: List<PsiElement>): GenImplConfig? {
        for (provider in runConfigProviders) {
            val config = provider(elements)
            if (config != null) return config
        }

        return null
    }

    private fun findImplConfigByContext(context: ConfigurationContext): GenImplConfig? {
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

}
