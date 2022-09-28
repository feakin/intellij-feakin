package com.feakin.intellij.runconfig.ui

import com.feakin.intellij.runconfig.FkCommandConfiguration
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import javax.swing.JComponent

class FkCommandConfigurationEditor(project: Project) : SettingsEditor<FkCommandConfiguration>() {

    private val command: FkCommandLineEditor = FkCommandLineEditor(project, FkCommandCompletionProvider());

    override fun resetEditorFrom(configuration: FkCommandConfiguration) {
        command.text = configuration.command
    }

    override fun applyEditorTo(configuration: FkCommandConfiguration) {
        configuration.command = command.text
    }

    override fun createEditor(): JComponent = panel {
        row("Command:") {
            cell(command).horizontalAlign(HorizontalAlign.FILL)
        }
    }
}


