package com.feakin.intellij.runconfig

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.AnsiEscapeDecoder
import com.intellij.execution.process.KillableProcessHandler
import com.intellij.openapi.util.Key

class FkProcessHandler(commandLine: GeneralCommandLine) : KillableProcessHandler(commandLine), AnsiEscapeDecoder.ColoredTextAcceptor {
    override fun coloredTextAvailable(text: String, attributes: Key<*>) {
        super.notifyTextAvailable(text, attributes)
    }
}
