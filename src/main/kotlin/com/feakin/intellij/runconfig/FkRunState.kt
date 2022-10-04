package com.feakin.intellij.runconfig

import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project

class FkRunState(environment: ExecutionEnvironment, private val config: FkCommandConfiguration) :
    CommandLineState(environment) {
    var commands: List<String> = listOf()

    companion object {
        private val log: Logger = logger<FkRunState>()
    }

    init {
//        val project: Project = environment.project
//        consoleBuilder = FkConsoleBuilder(project, config, environment.executor)
    }

    override fun startProcess(): ProcessHandler {
        log.info("startProcess")
        commands = listOf(
            "fkl", config.command, "--impl", config.commandLine.impl, "--path", config.commandLine.path
        )

        val commandLine = GeneralCommandLine(commands)
        log.debug("Executing Feakin command: `${commandLine}`")
        val handler = FkProcessHandler(commandLine)
        ProcessTerminatedListener.attach(handler)
        return handler
    }
}
