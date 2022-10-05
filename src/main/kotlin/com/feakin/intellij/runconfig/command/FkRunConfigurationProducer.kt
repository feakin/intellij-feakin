package com.feakin.intellij.runconfig.command

import com.feakin.intellij.FkFile
import com.feakin.intellij.psi.FeakinImplDeclaration
import com.feakin.intellij.runconfig.FkCommandConfiguration
import com.feakin.intellij.runconfig.FkCommandConfigurationType
import com.feakin.intellij.runconfig.FkCommandLine
import com.feakin.intellij.runconfig.implementation.RunImplConfig
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager

private typealias RunImplConfigProvider = (List<PsiElement>) -> RunImplConfig?

class FkRunConfigurationProducer : LazyRunConfigurationProducer<FkCommandConfiguration>() {
    companion object {
        private val log: Logger = logger<FkRunConfigurationProducer>()
    }

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
        val implEl = elements.firstOrNull() as? FeakinImplDeclaration? ?: return null
        val implName: String = implEl.implName.toString()

        val sourceElement = elements.firstOrNull { it is T } ?: return null
        return RunImplConfig(commandName, path, implName, sourceElement)
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
        val location = context.location ?: return false
        val file = location.virtualFile ?: return false

        val psiFile = PsiManager.getInstance(location.project).findFile(file)
        if (psiFile !is FkFile) return false
        val element = sourceElement.get()
        if (element !is FeakinImplDeclaration) return false

        val implName = element.implName.nameComponent.identifier.text

        configuration.name = "Run $implName gen"
        val fkCommandLine = fromImplDecl(element, psiFile)

        configuration.commandLine = fkCommandLine
        configuration.command = "gen"

        return true
    }

    private fun fromImplDecl(feakinImplDecl: FeakinImplDeclaration, psiFile: FkFile): FkCommandLine {
        val path = psiFile.virtualFile.path
        val implName = feakinImplDecl.implName.nameComponent.identifier.text
        return FkCommandLine(path, implName)
    }

    override fun isConfigurationFromContext(
        configuration: FkCommandConfiguration,
        context: ConfigurationContext
    ): Boolean {
        val location = context.location ?: return false
        val file = location.virtualFile ?: return false

        val psiFile = PsiManager.getInstance(location.project).findFile(file)
        if (psiFile !is FkFile) return false

        return true
    }
}
