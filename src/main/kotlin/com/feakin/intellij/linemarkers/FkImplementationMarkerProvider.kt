@file:Suppress("Indentation", "ParameterListWrapping")

package com.feakin.intellij.linemarkers

import com.feakin.intellij.psi.FeakinImplDeclaration
import com.feakin.intellij.runconfig.command.FkRunConfigurationProducer
import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement

class FkImplementationMarkerProvider : RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        if (element !is FeakinImplDeclaration) return null
        val state = FkRunConfigurationProducer().findImplConfig(listOf(element)) ?: return null

        val actions = ExecutorAction.getActions(0)
        return Info(
            AllIcons.RunConfigurations.TestState.Run,
            { state.configurationName },
            *actions
        )
    }
}
