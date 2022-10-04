package com.feakin.intellij.runconfig

import com.intellij.execution.Executor
import com.intellij.execution.filters.TextConsoleBuilderImpl
import com.intellij.execution.testframework.sm.SMTestRunnerConnectionUtil
import com.intellij.execution.ui.ConsoleView
import com.intellij.openapi.project.Project
import com.intellij.psi.search.ExecutionSearchScopes

// todo: custom console viewer
class FkConsoleBuilder(project: Project, private val config: FkCommandConfiguration, private val executor: Executor) : TextConsoleBuilderImpl(project, ExecutionSearchScopes.executionScope(project, config)) {
    private val FRAMEWORK_NAME: String = "Feakin CLI"

    override fun getConsole(): ConsoleView {
        val consoleProperties = config.createTestConsoleProperties(executor)
        val consoleView = SMTestRunnerConnectionUtil.createConsole(FRAMEWORK_NAME, consoleProperties!!)
        filters.forEach { consoleView.addMessageFilter(it) }
        return consoleView
    }
}
