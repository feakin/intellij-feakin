package com.feakin.intellij.buildtool

import com.intellij.execution.configurations.RunProfile
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.configurations.RunnerSettings
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.GenericProgramRunner
import com.intellij.execution.ui.RunContentDescriptor
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.util.InvalidDataException
import com.intellij.openapi.util.WriteExternalException
import org.jdom.Element

open class FkBuildTaskRunner : GenericProgramRunner<FkRunnerSettings>() {
    companion object {
        const val RUNNER_ID: String = "FkBuildTaskRunner"
    }

    private val log: Logger = Logger.getInstance(FkBuildTaskRunner::class.java)

    override fun getRunnerId(): String {
        return RUNNER_ID
    }

    override fun canRun(executorId: String, profile: RunProfile): Boolean {
        return true
    }

    override fun doExecute(state: RunProfileState, environment: ExecutionEnvironment): RunContentDescriptor? {
        log.info("FkBuildTaskRunner.doExecute")
        return super.doExecute(state, environment)
    }
}

class FkRunnerSettings : RunnerSettings {
    @Throws(InvalidDataException::class)
    override fun readExternal(element: Element) {
    }

    @Throws(WriteExternalException::class)
    override fun writeExternal(element: Element) {
    }
}