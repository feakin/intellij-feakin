package com.feakin.intellij.runconfig.config

class FkCommandLine(
    var path: String,
    var impl: String,
    private val subcommand: String,
    private val funcName: String = "",
) {
    fun toCommandString(): String {
        return this.toCommand().joinToString(" ")
    }

    fun toCommand(): List<String> {
        val cmdList = mutableListOf("fkl", subcommand, "--path", path, "--impl", impl)

        if (subcommand == "run") {
            cmdList += listOf("--func", funcName)
        }

        return cmdList
    }
}