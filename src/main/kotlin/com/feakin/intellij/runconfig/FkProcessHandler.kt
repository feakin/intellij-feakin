package com.feakin.intellij.runconfig

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.configurations.PtyCommandLine
import com.intellij.execution.process.AnsiEscapeDecoder
import com.intellij.execution.process.KillableProcessHandler
import com.intellij.openapi.util.Key
import com.intellij.util.io.BaseDataReader
import com.intellij.util.io.BaseOutputReader

class FkProcessHandler(commandLine: GeneralCommandLine) : KillableProcessHandler(commandLine),
    AnsiEscapeDecoder.ColoredTextAcceptor {
    override fun coloredTextAvailable(text: String, attributes: Key<*>) {
        super.notifyTextAvailable(text, attributes)
    }

    init {
        setHasPty(commandLine is PtyCommandLine)
        setShouldDestroyProcessRecursively(!hasPty())
    }

    override fun readerOptions(): BaseOutputReader.Options = object : BaseOutputReader.Options() {
        override fun policy(): BaseDataReader.SleepingPolicy =
            if (hasPty() || java.lang.Boolean.getBoolean("output.reader.blocking.mode")) {
                BaseDataReader.SleepingPolicy.BLOCKING
            } else {
                BaseDataReader.SleepingPolicy.NON_BLOCKING
            }

        override fun splitToLines(): Boolean = !hasPty()
    }

}
