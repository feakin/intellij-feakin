package com.feakin.intellij.runconfig.ui

import com.feakin.intellij.runconfig.FkCommandConfiguration
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent

open class FkCommandConfigurationEditor(project: Project) : SettingsEditor<FkCommandConfiguration>() {
    private val command: FkCommandLineEditor = FkCommandLineEditor(project, FkCommandCompletionProvider());

    override fun resetEditorFrom(configuration: FkCommandConfiguration) {
        command.text = configuration.command
    }

    override fun applyEditorTo(configuration: FkCommandConfiguration) {
        configuration.command = command.text
    }

    @Suppress("UnstableApiUsage")
    override fun createEditor(): JComponent = panel {
        row("Command:") {
            cell(command)
                .align(AlignX.FILL)
                .resizableColumn()
        }
    }
}
