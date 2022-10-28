@file:Suppress("Indentation", "ParameterListWrapping")

package com.feakin.intellij.linemarkers

import com.feakin.intellij.psi.FkImplDeclaration
import com.feakin.intellij.runconfig.command.GencodeImplConfigurationProducer
import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement

class FkCodegenImplLineMarkerContributor : RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        if (element !is FkImplDeclaration) return null
        val state = GencodeImplConfigurationProducer().findConfig(listOf(element)) ?: return null

        val actions = ExecutorAction.getActions(0)
        return Info(
            AllIcons.RunConfigurations.TestState.Run,
            { state.configurationName },
            *actions
        )
    }
}
