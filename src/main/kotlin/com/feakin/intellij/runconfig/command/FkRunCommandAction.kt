package com.feakin.intellij.runconfig.command

import com.intellij.ide.actions.runAnything.RunAnythingManager
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction

open class FkRunCommandAction : DumbAwareAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val runAnythingManager = RunAnythingManager.getInstance(project)
        runAnythingManager.show("fkl ", false, e)
    }
}