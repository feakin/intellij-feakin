package com.feakin.intellij.runconfig

import com.intellij.execution.ExecutionResult
import com.intellij.execution.configurations.RunProfile
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.configurations.RunnerSettings
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.GenericProgramRunner
import com.intellij.execution.ui.ExecutionUiService
import com.intellij.execution.ui.RunContentDescriptor
import com.intellij.openapi.fileEditor.FileDocumentManager
import org.jetbrains.annotations.NonNls

class FkCommandRunner : GenericProgramRunner<RunnerSettings>() {
    override fun getRunnerId(): @NonNls String = "FkCommandRunner"

    override fun canRun(executorId: String, profile: RunProfile): Boolean {
        if (executorId != DefaultRunExecutor.EXECUTOR_ID || profile !is FkCommandConfiguration) return false
        return true
    }

    override fun doExecute(state: RunProfileState, environment: ExecutionEnvironment): RunContentDescriptor? {
        if (environment.runProfile !is FkCommandConfiguration) return null

        FileDocumentManager.getInstance().saveAllDocuments()
        return showRunContent(state.execute(environment.executor, this), environment)
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

