package com.feakin.intellij.linemarkers

import com.feakin.intellij.psi.FkEndpointDeclaration
import com.feakin.intellij.runconfig.command.FkRunEndpointConfigurationProducer
import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement

class FkEndpointRequestLineMarkerContributor : RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        if (element !is FkEndpointDeclaration) return null
        val state = FkRunEndpointConfigurationProducer().findRequest(listOf(element)) ?: return null

        val actions = ExecutorAction.getActions(0)
        return Info(
            AllIcons.RunConfigurations.TestState.Run,
            { state.configurationName },
            *actions
        )
    }
}
