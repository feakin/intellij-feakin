package com.feakin.intellij.runconfig

import com.feakin.intellij.runconfig.ui.FkCommandConfigurationEditor
import com.intellij.execution.Executor
import com.intellij.execution.configurations.LocatableConfigurationBase
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.configurations.RunConfigurationWithSuppressedDefaultDebugAction
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import org.jdom.Element

class FkCommandConfiguration(project: Project, name: String, fkConfigurationFactory: FkConfigurationFactory) :
    LocatableConfigurationBase<RunProfileState>(project, fkConfigurationFactory, name),
    RunConfigurationWithSuppressedDefaultDebugAction {

    var command: String = ""
    var commandLine: FkCommandLine = FkCommandLine("", "", "");

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState {
        return FkRunState(environment, this)
    }

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration> {
        return FkCommandConfigurationEditor(project)
    }

    fun setCommand(fkCommandLine: FkCommandLine) {
        this.commandLine = fkCommandLine
        this.command = fkCommandLine.toCommandString()
    }

    override fun suggestedName(): String = command.substringBefore(' ').capitalize()

    override fun writeExternal(element: Element) {
        super.writeExternal(element)
        element.writeString("command", command)
    }

    override fun readExternal(element: Element) {
        super.readExternal(element)
        element.readString("command")?.let { command = it }
    }
}

fun Element.writeString(name: String, value: String) {
    val opt = Element("option")
    opt.setAttribute("name", name)
    opt.setAttribute("value", value)
    addContent(opt)
}

fun Element.readString(name: String): String? =
    children
        .find { it.name == "option" && it.getAttributeValue("name") == name }
        ?.getAttributeValue("value")
