package com.feakin.intellij.runconfig.command

import com.feakin.intellij.FkFile
import com.feakin.intellij.psi.FeakinStructDeclaration
import com.feakin.intellij.runconfig.FkCommandConfiguration
import com.feakin.intellij.runconfig.FkCommandConfigurationType
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.util.PsiTreeUtil

class FkRunConfigurationProducer : LazyRunConfigurationProducer<FkCommandConfiguration>() {
    override fun getConfigurationFactory(): ConfigurationFactory {
        return FkCommandConfigurationType.getInstance().factory
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

        val fn = location.psiElement.ancestorStrict<FeakinStructDeclaration>()

        configuration.name = "Run ${fn?.name} gen "
        configuration.command = "Run ${fn?.name} gen "

        return true
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

inline fun <reified T : PsiElement> PsiElement.ancestorStrict(): T? =
    PsiTreeUtil.getParentOfType(this, T::class.java, /* strict */ true)
