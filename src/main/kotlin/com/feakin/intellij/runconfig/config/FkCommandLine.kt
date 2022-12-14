package com.feakin.intellij.runconfig.config

class FkCommandLine(
    var main: String,
    var impl: String,
    private val subcommand: String,
    private val funcName: String = "",
    private val path: String,
) {
    fun toCommandString(): String {
        return this.toCommand().joinToString(" ")
    }

    fun toCommand(): List<String> {
        val cmdList = mutableListOf("fkl", subcommand, "--main", main)
        if (subcommand == "run") {
            cmdList += listOf("--func", funcName)
        }

        if (impl.isNotEmpty()) {
            cmdList.addAll(listOf("--impl", impl))
        }

        if (path.isNotEmpty()) {
            cmdList += listOf("--path", path)
        }

        return cmdList
    }
}