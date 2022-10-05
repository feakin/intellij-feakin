package com.feakin.intellij.runconfig.ui

import com.feakin.intellij.runconfig.FkCommandConfiguration
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import javax.swing.JComponent

open class FkCommandConfigurationEditor(project: Project) : SettingsEditor<FkCommandConfiguration>() {
    private val command: FkCommandLineEditor = FkCommandLineEditor(project, FkCommandCompletionProvider());
//    todo: config for SDK.
//    protected val workingDirectory: LabeledComponent<TextFieldWithBrowseButton> =
//        WorkingDirectoryComponent()

    override fun resetEditorFrom(configuration: FkCommandConfiguration) {
        command.text = configuration.command
    }

    override fun applyEditorTo(configuration: FkCommandConfiguration) {
        configuration.command = command.text
    }

    @Suppress("UnstableApiUsage")
    override fun createEditor(): JComponent = panel {
        row("Command:") {
            cell(command).horizontalAlign(HorizontalAlign.FILL)
        }
//        row(workingDirectory.label) {
//            fullWidthCell(workingDirectory)
//                .resizableColumn()
//        }
    }
}

/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */
//private class WorkingDirectoryComponent : LabeledComponent<TextFieldWithBrowseButton>() {
//    init {
//        component = TextFieldWithBrowseButton().apply {
//            val fileChooser = FileChooserDescriptorFactory.createSingleFolderDescriptor().apply {
//                title = ExecutionBundle.message("select.working.directory.message")
//            }
//            addBrowseFolderListener(null, null, null, fileChooser)
//        }
//        text = ExecutionBundle.message("run.configuration.working.directory.label")
//    }
//}
//
//fun <T : JComponent> Row.fullWidthCell(component: T): Cell<T> {
//    return cell(component)
//        .horizontalAlign(HorizontalAlign.FILL)
//}
