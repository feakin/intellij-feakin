package com.feakin.intellij.linemarkers

import com.feakin.intellij.psi.FkServerDecl
import com.feakin.intellij.runconfig.command.RunMockServerConfigurationProducer
import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement

class FkRunMockServerMarkerContributor : RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        if (element !is FkServerDecl) return null
        val state = RunMockServerConfigurationProducer().findConfig(listOf(element)) ?: return null

        val actions = ExecutorAction.getActions(0)
        return Info(
            AllIcons.RunConfigurations.TestState.Run,
            { state.configurationName },
            *actions
        )
    }
}