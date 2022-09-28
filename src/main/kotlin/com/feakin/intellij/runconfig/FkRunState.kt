package com.feakin.intellij.runconfig

import com.intellij.execution.configurations.CommandLineState
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger

class FkRunState(environment: ExecutionEnvironment, config: FkCommandConfiguration) : CommandLineState(environment) {
    companion object {
        private val log: Logger = logger<FkRunState>()
    }

    override fun startProcess(): ProcessHandler {
        log.info("startProcess")

        // todo: get command from config
        val handler = FkProcessHandler(GeneralCommandLine("fkl", "gen"))
        ProcessTerminatedListener.attach(handler)
        return handler
    }
}
