package com.feakin.intellij.runconfig

import com.feakin.intellij.runconfig.ui.FkCommandConfigurationEditor
import com.intellij.execution.Executor
import com.intellij.execution.configurations.LocatableConfigurationBase
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project

class FkCommandConfiguration(project: Project, name: String, fkConfigurationFactory: FkConfigurationFactory) :
    LocatableConfigurationBase<RunProfileState>(project, fkConfigurationFactory, name) {
    var command: String = "fkl run"

    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState {
        return FkRunState(environment, this)
    }

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration> {
        return FkCommandConfigurationEditor(project)
    }


}