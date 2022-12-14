package com.feakin.intellij.linemarkers

import com.feakin.intellij.psi.FkDatasourceDecl
import com.feakin.intellij.runconfig.command.RunDatasourceConfigurationProducer
import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement

class FkRunDatasourceMarkerContributor : RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        if (element !is FkDatasourceDecl) return null
        val state = RunDatasourceConfigurationProducer().findConfig(listOf(element)) ?: return null

        val actions = ExecutorAction.getActions(0)
        return Info(
            AllIcons.RunConfigurations.TestState.Run,
            { state.configurationName },
            *actions
        )
    }
}