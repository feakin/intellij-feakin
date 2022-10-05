package com.feakin.intellij.runconfig

import com.feakin.intellij.runconfig.config.FkCommandLine
import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger

class FkRunState(
    environment: ExecutionEnvironment,
    private val config: FkCommandConfiguration,
    private val commandLine: FkCommandLine
) : CommandLineState(environment) {

    companion object {
        private val log: Logger = logger<FkRunState>()
    }

    override fun startProcess(): ProcessHandler {
        val commandLine = GeneralCommandLine(commandLine.toCommand())
        log.debug("Executing Feakin command: `${commandLine}`")

        val handler = FkProcessHandler(commandLine)
        ProcessTerminatedListener.attach(handler)
        return handler
    }
}
