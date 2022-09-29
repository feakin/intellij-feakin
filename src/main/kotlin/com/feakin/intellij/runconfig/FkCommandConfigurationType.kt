package com.feakin.intellij.runconfig

import com.feakin.intellij.FkIcons
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationTypeBase
import com.intellij.execution.configurations.ConfigurationTypeUtil
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.project.Project

class FkCommandConfigurationType :
    ConfigurationTypeBase("FkRunAppConfigType", "Feakin", "Feakin DSL generator", FkIcons.RUN) {
    val factory: ConfigurationFactory get() = configurationFactories.single()

    init {
        addFactory(FkConfigurationFactory(this))
    }

    companion object {
        fun getInstance(): FkCommandConfigurationType =
            ConfigurationTypeUtil.findConfigurationType(FkCommandConfigurationType::class.java)
    }
}

class FkConfigurationFactory(type: FkCommandConfigurationType) : ConfigurationFactory(type) {
    override fun getId(): String = ID

    override fun createTemplateConfiguration(project: Project): RunConfiguration {
        return FkCommandConfiguration(project, "Fkl", this)
    }

    companion object {
        const val ID: String = "Fkl Command"
    }
}
