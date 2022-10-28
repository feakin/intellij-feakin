@file:Suppress("Indentation", "ParameterListWrapping")

package com.feakin.intellij.linemarkers

import com.feakin.intellij.psi.FkImplDeclaration
import com.feakin.intellij.runconfig.command.FkGenImplConfigurationProducer
import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement

class FkImplLineMarkerContributor : RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        if (element !is FkImplDeclaration) return null
        val state = FkGenImplConfigurationProducer().findConfig(listOf(element)) ?: return null

        val actions = ExecutorAction.getActions(0)
        return Info(
            AllIcons.RunConfigurations.TestState.Run,
            { state.configurationName },
            *actions
        )
    }
}
