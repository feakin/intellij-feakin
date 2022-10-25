package com.feakin.intellij.runconfig

import com.feakin.intellij.runconfig.config.FkCommandLine
import com.feakin.intellij.runconfig.ui.FkCommandConfigurationEditor
import com.intellij.execution.Executor
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.LocatableConfigurationBase
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import org.jdom.Element

class FkCommandConfiguration(project: Project, name: String, factory: ConfigurationFactory) :
    LocatableConfigurationBase<RunProfileState>(project, factory, name) {

    // for debugging
    var command: String = ""
    var commandLine: FkCommandLine = FkCommandLine("", "", "", path = "");

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState {
        return FkRunState(environment, this, commandLine)
    }

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration> {
        return FkCommandConfigurationEditor(project)
    }

    fun setCommand(fkCommandLine: FkCommandLine) {
        this.commandLine = fkCommandLine
        this.command = fkCommandLine.toCommandString()
    }

    override fun writeExternal(element: Element) {
        super.writeExternal(element)
        element.writeString("command", command)
        element.writeString("path", commandLine.main)
        element.writeString("impl", commandLine.impl)
    }

    override fun readExternal(element: Element) {
        super.readExternal(element)
        element.readString("command")?.let { command = it }
        element.readString("path")?.let { commandLine.main = it }
        element.readString("impl")?.let { commandLine.impl = it }
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
