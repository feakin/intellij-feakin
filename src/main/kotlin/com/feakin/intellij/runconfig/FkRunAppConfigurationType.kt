package com.feakin.intellij.runconfig

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationTypeBase
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.icons.AllIcons
import com.intellij.openapi.project.Project

class FkRunAppConfigurationType :
    ConfigurationTypeBase("FkRunAppConfigType", "FKL", "Run Generator", AllIcons.RunConfigurations.TestState.Run) {
    init {
        addFactory(FkConfigurationFactory(this))
    }
}

class FkConfigurationFactory(type: FkRunAppConfigurationType) : ConfigurationFactory(type) {
    override fun getId(): String = ID

    override fun createTemplateConfiguration(project: Project): RunConfiguration {
        return FkCommandConfiguration(project, "Fkl", this)
    }

    companion object {
        const val ID: String = "Fkl Command"
    }
}
