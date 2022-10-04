package com.feakin.intellij.runconfig

import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project

class FkRunState(environment: ExecutionEnvironment, config: FkCommandConfiguration) : CommandLineState(environment) {
    val commands: List<String> = listOf()

    companion object {
        private val log: Logger = logger<FkRunState>()
    }

    init {
        val project: Project = environment.project
        consoleBuilder = FkConsoleBuilder(project, config, environment.executor)
    }

    override fun startProcess(): ProcessHandler {
        log.info("startProcess")

        // todo: get command from config
        val handler = FkProcessHandler(GeneralCommandLine("fkl", "gen"))
        ProcessTerminatedListener.attach(handler)
        return handler
    }
}
