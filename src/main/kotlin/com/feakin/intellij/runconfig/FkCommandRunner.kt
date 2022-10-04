package com.feakin.intellij.runconfig

import com.intellij.execution.ExecutionManager
import com.intellij.execution.ExecutionResult
import com.intellij.execution.configurations.RunProfile
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.configurations.RunnerSettings
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.ProgramRunner
import com.intellij.execution.ui.ExecutionUiService
import com.intellij.execution.ui.RunContentDescriptor
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.fileEditor.FileDocumentManager
import org.jetbrains.annotations.NonNls
import org.jetbrains.concurrency.resolvedPromise

class FkCommandRunner : ProgramRunner<RunnerSettings> {
    companion object {
        private val log: Logger = logger<FkCommandRunner>()
    }
    override fun getRunnerId(): @NonNls String {
        return "FkCommandRunner"
    }

    override fun canRun(executorId: String, profile: RunProfile): Boolean {
        if (executorId != DefaultRunExecutor.EXECUTOR_ID || profile !is FkCommandConfiguration) return false

        return true
    }

    override fun execute(environment: ExecutionEnvironment) {
        val state = environment.state ?: return
        @Suppress("UnstableApiUsage")
        ExecutionManager.getInstance(environment.project).startRunProfile(environment) {
            resolvedPromise(doExecute(state, environment))
        }
    }

    private fun doExecute(state: RunProfileState, environment: ExecutionEnvironment): RunContentDescriptor? {
        val configuration = environment.runProfile
        if (configuration is FkCommandConfiguration) {
            FileDocumentManager.getInstance().saveAllDocuments()
            return showRunContent(state.execute(environment.executor, this), environment)
        } else {
            return null
        }
    }

    @Suppress("UnstableApiUsage")
    private fun showRunContent(
        executionResult: ExecutionResult?,
        environment: ExecutionEnvironment
    ): RunContentDescriptor? {
        return executionResult?.let {
            ExecutionUiService.getInstance().showRunContent(it, environment)
        }
    }
}

//class FkRunnerSettings : RunnerSettings {
//    @Throws(InvalidDataException::class)
//    override fun readExternal(element: Element) {
//    }
//
//    @Throws(WriteExternalException::class)
//    override fun writeExternal(element: Element) {
//    }
//}