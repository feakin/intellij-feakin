package com.feakin.intellij.buildtool

import com.intellij.task.ProjectTask
import com.intellij.task.ProjectTaskRunner

class FkBuildTaskRunner : ProjectTaskRunner() {
    override fun canRun(projectTask: ProjectTask): Boolean {
        return true
    }
}