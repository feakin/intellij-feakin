package com.feakin.intellij.runconfig

import com.intellij.execution.ExecutionManager
import com.intellij.execution.ExecutionResult
import com.intellij.execution.configurations.RunProfile
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.configurations.RunnerSettings
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.ProgramRunner
import com.intellij.execution.ui.ExecutionUiService
import com.intellij.execution.ui.RunContentDescriptor
import com.intellij.openapi.fileEditor.FileDocumentManager
import org.jetbrains.annotations.NonNls
import org.jetbrains.concurrency.resolvedPromise

class FkCommandRunner : ProgramRunner<RunnerSettings> {
    override fun getRunnerId(): @NonNls String {
        return "FkCommandRunner"
    }

    override fun canRun(executorId: String, profile: RunProfile): Boolean {
        println("FkCommandRunner.canRun$profile")
        return false
    }

    override fun execute(environment: ExecutionEnvironment) {
        val state = environment.state ?: return
        @Suppress("UnstableApiUsage")
        ExecutionManager.getInstance(environment.project).startRunProfile(environment) {
            resolvedPromise(doExecute(state, environment))
        }
    }

    private fun doExecute(state: RunProfileState, environment: ExecutionEnvironment): RunContentDescriptor? {
        FileDocumentManager.getInstance().saveAllDocuments()
        // getExecutableCommandLine
        return showRunContent(state.execute(environment.executor, this), environment)
    }

    @Suppress("UnstableApiUsage")
    private fun showRunContent(executionResult: ExecutionResult?, environment: ExecutionEnvironment): RunContentDescriptor? {
        return executionResult?.let {
            ExecutionUiService.getInstance().showRunContent(it, environment)
        }
    }
}