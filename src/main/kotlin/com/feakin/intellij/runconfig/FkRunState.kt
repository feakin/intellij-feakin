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

    companion object {
        private val log: Logger = logger<FkRunState>()
    }

    override fun startProcess(): ProcessHandler {
        val commands = config.commandLine.toCommand()
        val commandLine = GeneralCommandLine(commands)

        log.debug("Executing Feakin command: `${commandLine}`")
        val handler = FkProcessHandler(commandLine)
        ProcessTerminatedListener.attach(handler)
        return handler
    }
}
