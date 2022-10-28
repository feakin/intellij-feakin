package com.feakin.intellij.linemarkers

import com.feakin.intellij.psi.FkLayeredDeclaration
import com.feakin.intellij.runconfig.command.FkLayeredGuardingConfigurationProducer
import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement

class FkLayeredGuardingLineMarkerContributor : RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        if (element !is FkLayeredDeclaration) return null
        val state = FkLayeredGuardingConfigurationProducer().findConfig(listOf(element)) ?: return null

        val actions = ExecutorAction.getActions(0)
        return Info(
            AllIcons.RunConfigurations.TestState.Run,
            { state.configurationName },
            *actions
        )
    }
}