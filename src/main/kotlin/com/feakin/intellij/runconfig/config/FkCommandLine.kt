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
        val cmdList = listOf("fkl", subcommand, "--path", path, "--impl", impl)

        if (subcommand == "run") {
            cmdList.containsAll(listOf("--func", funcName))
        }

        return cmdList
    }
}