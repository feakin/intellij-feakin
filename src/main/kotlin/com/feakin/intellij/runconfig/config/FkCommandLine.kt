package com.feakin.intellij.runconfig.config

class FkCommandLine(
    var path: String,
    var impl: String,
    val subcommand: String,
    val funcName: String = "",
) {
    fun toCommandString(): String {
        return this.toCommand().joinToString(" ")
    }

    fun toCommand(): List<String> {
        if (subcommand == "run") {
            return listOf("fkl", subcommand, "--path", path, "--impl", impl, "--func", funcName)
        }

        return listOf("fkl", subcommand, "--path", path, "--impl", impl)
    }
}